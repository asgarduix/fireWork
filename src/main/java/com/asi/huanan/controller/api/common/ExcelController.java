package com.asi.huanan.controller.api.common;

import java.io.ByteArrayOutputStream;
import java.util.Arrays;

import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ExcelController {
	private static Logger log = LogManager.getLogger(ExcelController.class);

	/**
	 * 產生excel檔案
	 * @param workbook workbook物件
	 * @param response
	 * @param fileName 檔案名稱
	 */
	public void createExcel(HSSFWorkbook workbook, HttpServletResponse response, String fileName) {
		try {
			response.setHeader("Content-type","application/vnd.ms-excel");
	        // 解決匯出檔名中文亂碼
	        response.setCharacterEncoding("UTF-8");
	        response.setHeader("Content-Disposition","attachment;filename="+new String(fileName.getBytes("UTF-8"),"ISO-8859-1")+".xls");
	        workbook.write(response.getOutputStream());
		} catch (Exception e) {
			log.debug(e.toString());
			Arrays.asList(e.getStackTrace()).stream().forEach(sube -> log.debug(sube.toString()));
		}
	}
	
	/**
	 * 取得生成Excel資料
	 * @param workbook
	 * @param fileName
	 * @return
	 */
	public byte[] getExcelBytes(HSSFWorkbook workbook) {
		byte[] excelByteArr = null;

		try {
	        ByteArrayOutputStream os = new ByteArrayOutputStream();
	        workbook.write(os);
	        excelByteArr = os.toByteArray();
	     
		} catch (Exception e) {
			log.debug(e.toString());
			Arrays.asList(e.getStackTrace()).stream().forEach(sube -> log.debug(sube.toString()));
		}
		return excelByteArr;
	}
	
}
