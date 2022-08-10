package com.asi.huanan.service.customerize;

import java.io.File;
import java.io.FileOutputStream;
import java.text.ParseException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.ibatis.session.SqlSession;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.CellStyle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.asi.huanan.service.dao.mybatis.mapper.BatchlogMapper;
import com.asi.huanan.service.dao.mybatis.mapper.BatchparameterMapper;
import com.asi.huanan.service.dao.mybatis.mapper.BatchqueueMapper;
import com.asi.huanan.service.dao.mybatis.mapper.customerize.CustomerizeMapper;
import com.asi.huanan.service.dao.mybatis.model.Batchqueue;
import com.asi.huanan.service.dao.mybatis.model.BatchqueueExample;
import com.asi.huanan.service.dao.mybatis.model.BatchqueueExample.Criteria;
import com.asi.huanan.service.dao.mybatis.model.customerize.Rin1302Table;
import com.asi.mechanism.SpringProperty;
import com.asi.mechanism.service.connector.MyBatisConnector;
import com.asi.swissknife.Asiutil;

@Service
public class Rin1302Service {

	private Log log = LogFactory.getLog(Rin1302Service.class);

	@Autowired
	private MyBatisConnector mybatis;

	/** Excel檔名日期部分的formatter */
	private static final DateTimeFormatter fileNameDateTimeFormatter = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");
	
	/** Excel資料查詢日期formater*/
	private static final DateTimeFormatter rin1302DateFormatter = DateTimeFormatter.ofPattern("yyyy/MM/dd");

	/** Excel 標題 */
	private static final List<String> headTitles = List.of("合約號", "保單號", "被保險人", "使用性質", "100% TSI", "共保比", "SC TSI",
			"到期日", "標的地址", "再保人", "分出比");

	/**
	 * 排程輸出Excel
	 * 
	 * @throws Exception
	 */
	public void rin1302PrintExcel() throws Exception {

		SqlSession sqlSession = mybatis.createSqlSessionFactory().openSession();
		BatchqueueMapper batchqueueMapper = sqlSession.getMapper(BatchqueueMapper.class);
		BatchlogMapper batchlogMapper = sqlSession.getMapper(BatchlogMapper.class);
		CustomerizeMapper customerizeMapper = sqlSession.getMapper(CustomerizeMapper.class);
		BatchparameterMapper batchparameterMapper = sqlSession.getMapper(BatchparameterMapper.class);

		try {
			// 查詢此次排程所需列印的Taskid
			List<String> taskidList = queryBatchqueueTaskid(batchqueueMapper);

			for (int i = 0; i < taskidList.size(); i++) {
				String taskid = taskidList.get(i);
				try {
					// 將batchqueue狀態改為1:執行中
					updateBatchqueueStatus1AndStartTime(batchqueueMapper, taskid);
					sqlSession.commit();

					// 查詢Excel資料
					List<Rin1302Table> rin1302TableList = queryRin1302Table(customerizeMapper,
							toDate(batchparameterMapper.queryDateValue1(taskid)),
							toDate(batchparameterMapper.queryDateValue2(taskid)));
					// batchlog訊息：無參數資料可執行, 請確認同險卡列印區間
					if (rin1302TableList.isEmpty()) {
						batchlogMapper.insertLog_noData(taskid, new Date());
					}
					// 產生檔案名稱
					String pathName = new StringBuilder().append(SpringProperty.localFileForExcel).append("Rin1302_")
							.append(LocalDateTime.now().format(fileNameDateTimeFormatter)).append("_").append(i)
							.append(".xls").toString();
					// batchlog訊息：========== 分保卡列印開始! =============
					batchlogMapper.insertLog_start(taskid, new Date());
					// 列印Excel
					exportExcel(rin1302TableList, pathName);
					// batchlog訊息：========== 分保卡列印完成! =============
					batchlogMapper.insertLog_end(taskid, new Date());

					// 將batchqueue狀態改為2:完成
					updateBatchqueueStatus2AndEndTime(batchqueueMapper, taskid, pathName);
					sqlSession.commit();
				} catch (Exception e) {
					// 輸出Excel失敗流程
					sqlSession.rollback();

					try {
						exportExcelFailProcess(batchlogMapper, batchqueueMapper, taskid);
						sqlSession.commit();
					} catch (Exception e1) {
						sqlSession.rollback();

						log.debug(e1.toString());
						Arrays.asList(e1.getStackTrace()).forEach(sub -> log.debug(sub.toString()));
					}

					log.debug(e.toString());
					Arrays.asList(e.getStackTrace()).forEach(sub -> log.debug(sub.toString()));

				}
			}
		} catch (Exception e) {
			throw e;
		} finally {
			sqlSession.close();
		}

	}

	/**
	 * 查詢此次執行的Taskid
	 * 
	 * @param batchqueueMapper
	 * @return
	 */
	private List<String> queryBatchqueueTaskid(BatchqueueMapper batchqueueMapper) {
		BatchqueueExample batchqueueExampleSearching = new BatchqueueExample();
		batchqueueExampleSearching.setOrderByClause("submittime asc");
		Criteria criteria = batchqueueExampleSearching.createCriteria();
		criteria.andProcessstatusEqualTo((byte) 0);
		criteria.andBatchidEqualTo("Rin1302P");
		criteria.andSubmittimeLessThan(new Date());
		List<Batchqueue> batchqueueList = batchqueueMapper.selectByExample(batchqueueExampleSearching);

		if (batchqueueList == null) {
			log.debug("returnList is null");
			return Collections.emptyList();
		} else {
			log.debug("筆數:" + batchqueueList.size());
			return batchqueueList.stream().map(Batchqueue::getTaskid).collect(Collectors.toList());
		}
	}

	/**
	 * 將batchqueue狀態改為1並新增開始時間:執行中
	 * 
	 * @param batchqueueMapper
	 * @param taskid
	 */
	private void updateBatchqueueStatus1AndStartTime(BatchqueueMapper batchqueueMapper, String taskid) {
		BatchqueueExample batchqueueExample = new BatchqueueExample();
		batchqueueExample.createCriteria().andTaskidEqualTo(taskid);

		Batchqueue batchqueue = new Batchqueue();
		batchqueue.setProcessstatus((byte) 1);
		batchqueue.setStarttime(new Date());

		batchqueueMapper.updateByExampleSelective(batchqueue, batchqueueExample);
	}

	/**
	 * 查詢Excel資料
	 * 
	 * @param customerizeMapper
	 * @param date1
	 * @param date2
	 * @return
	 * @throws ParseException
	 */
	private List<Rin1302Table> queryRin1302Table(CustomerizeMapper customerizeMapper, Date bgnDate, Date endDate)
			throws ParseException {
		return customerizeMapper.getRin1302MainData(bgnDate, endDate);
	}

	/**
	 * 輸出Excel
	 * 
	 * @param rin1302TableList
	 * @param taskid
	 * @param batchlogMapper
	 * @param batchqueueMapper
	 * @param counter
	 * @return
	 * @throws Exception
	 */
	private void exportExcel(List<Rin1302Table> rin1302TableList, String pathName) throws Exception {

		try (HSSFWorkbook workbook = createWorkbook(rin1302TableList);
				FileOutputStream fos = new FileOutputStream(new File(pathName))) {
			// excel輸出
			workbook.write(fos);
			fos.flush();
		}
	}

	/**
	 * 產生HSSFWorkbook
	 * 
	 * @param rin1302TableList excel資料
	 * @return
	 */
	public HSSFWorkbook createWorkbook(List<Rin1302Table> rin1302TableList) {

		HSSFWorkbook workbook = new HSSFWorkbook();

		// 建立建立sheet
		HSSFSheet sheet = null;
		if (rin1302TableList.isEmpty()) {
			// 建立建立sheet
			sheet = workbook.createSheet("查無資料可列印");
		} else {
			// 建立建立sheet
			sheet = workbook.createSheet("Rin1302火再保臨分到期通知列印");
		}

		// 建立單元格樣式
		CellStyle cellStyle = workbook.createCellStyle();

		HSSFRow headerRow = sheet.createRow(0);
		for (int i = 0; i < headTitles.size(); i++) {
			headerRow.createCell(i).setCellStyle(cellStyle);
			headerRow.createCell(i).setCellValue(headTitles.get(i));
		}

		Asiutil util = new Asiutil();
		// 建立資料
		for (int i = 0; i < rin1302TableList.size(); i++) {
			HSSFRow row = sheet.createRow(i + 1);
			row.createCell(0).setCellStyle(cellStyle);
			row.createCell(0).setCellValue(rin1302TableList.get(i).getCession_no());
			row.createCell(1).setCellStyle(cellStyle);
			row.createCell(1).setCellValue(rin1302TableList.get(i).getPolicy_no());
			row.createCell(2).setCellStyle(cellStyle);
			row.createCell(2).setCellValue(rin1302TableList.get(i).getInsurant());
			row.createCell(3).setCellStyle(cellStyle);
			row.createCell(3).setCellValue(rin1302TableList.get(i).getUse_prop());
			row.createCell(4).setCellStyle(cellStyle);
			row.createCell(4).setCellValue(String.valueOf(rin1302TableList.get(i).getAll_amt()));
			row.createCell(5).setCellStyle(cellStyle);
			row.createCell(5).setCellValue(String.valueOf(rin1302TableList.get(i).getCoins_rate()));
			row.createCell(6).setCellStyle(cellStyle);
			row.createCell(6).setCellValue(String.valueOf(rin1302TableList.get(i).getAmt()));
			row.createCell(7).setCellStyle(cellStyle);
			row.createCell(7)
					.setCellValue(util.processDateToString(rin1302TableList.get(i).getTreaty_dend(), "yyyy/MM/dd"));
			row.createCell(8).setCellStyle(cellStyle);
			row.createCell(8).setCellValue(rin1302TableList.get(i).getAddress());
			row.createCell(9).setCellStyle(cellStyle);
			row.createCell(9).setCellValue(rin1302TableList.get(i).getEname());
			row.createCell(10).setCellStyle(cellStyle);
			row.createCell(10).setCellValue(String.valueOf(rin1302TableList.get(i).getShare_rate()));
		}

		return workbook;
	}

	/**
	 * 將batchqueue狀態改為2並新增結束時間:執行完成
	 * 
	 * @param batchqueueMapper
	 * @param taskid
	 * @param pathName
	 */
	private void updateBatchqueueStatus2AndEndTime(BatchqueueMapper batchqueueMapper, String taskid, String pathName) {
		// batchqueue，update，執行完成
		BatchqueueExample batchqueueExample = new BatchqueueExample();
		Criteria criteria = batchqueueExample.createCriteria();
		criteria.andTaskidEqualTo(taskid);

		Batchqueue model = new Batchqueue();
		model.setProcessstatus((byte) 2);
		model.setEndtime(new Date());
		model.setBatchreprotaccesspath(pathName);

		batchqueueMapper.updateByExampleSelective(model, batchqueueExample);
	}

	/**
	 * 輸出報表失敗後流程
	 */
	private void exportExcelFailProcess(BatchlogMapper batchlogMapper, BatchqueueMapper batchqueueMapper,
			String taskid) {
		// batchlog訊息：BatchRpt報表資料檔新增錯誤, 請聯絡資訊室人員處理
		batchlogMapper.insertLog_error(taskid, new Date());

		// batchqueue，update，4:執行失敗
		BatchqueueExample batchqueueExample = new BatchqueueExample();
		Criteria criteria2 = batchqueueExample.createCriteria();
		criteria2.andTaskidEqualTo(taskid);
		Batchqueue model = new Batchqueue();
		model.setProcessstatus((byte) 4);
		model.setEndtime(new Date());
		batchqueueMapper.updateByExampleSelective(model, batchqueueExample);
	}

	private Date toDate(String date) {
		return Date.from(
				LocalDate.parse(date, rin1302DateFormatter).atStartOfDay().atZone(ZoneId.systemDefault()).toInstant());
	}

}
