package com.asi.huanan.service.dao.mybatis.mapper;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.asi.huanan.service.dao.mybatis.model.Batchlog;
import com.asi.huanan.service.dao.mybatis.model.BatchlogExample;

public interface BatchlogMapper {
	/**
	 * This method was generated by MyBatis Generator. This method corresponds to
	 * the database table batchlog
	 *
	 * @mbg.generated Wed Nov 24 10:08:48 CST 2021
	 */
	long countByExample(BatchlogExample example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to
	 * the database table batchlog
	 *
	 * @mbg.generated Wed Nov 24 10:08:48 CST 2021
	 */
	int deleteByExample(BatchlogExample example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to
	 * the database table batchlog
	 *
	 * @mbg.generated Wed Nov 24 10:08:48 CST 2021
	 */
	int insert(Batchlog record);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to
	 * the database table batchlog
	 *
	 * @mbg.generated Wed Nov 24 10:08:48 CST 2021
	 */
	int insertSelective(Batchlog record);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to
	 * the database table batchlog
	 *
	 * @mbg.generated Wed Nov 24 10:08:48 CST 2021
	 */
	List<Batchlog> selectByExampleWithBLOBs(BatchlogExample example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to
	 * the database table batchlog
	 *
	 * @mbg.generated Wed Nov 24 10:08:48 CST 2021
	 */
	List<Batchlog> selectByExample(BatchlogExample example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to
	 * the database table batchlog
	 *
	 * @mbg.generated Wed Nov 24 10:08:48 CST 2021
	 */
	int updateByExampleSelective(@Param("record") Batchlog record, @Param("example") BatchlogExample example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to
	 * the database table batchlog
	 *
	 * @mbg.generated Wed Nov 24 10:08:48 CST 2021
	 */
	int updateByExampleWithBLOBs(@Param("record") Batchlog record, @Param("example") BatchlogExample example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to
	 * the database table batchlog
	 *
	 * @mbg.generated Wed Nov 24 10:08:48 CST 2021
	 */
	int updateByExample(@Param("record") Batchlog record, @Param("example") BatchlogExample example);

	// 再保臨分到期通知列印_排程執行batchlog table
	@Insert("<script> INSERT INTO batchlog(taskid, logtime) " + " values(#{record.taskid,jdbcType=VARCHAR}, "
			+ " #{record.logtime,jdbcType=TIMESTAMP}) " + "</script>")
	int insertLog(@Param("record") Batchlog modelLog);

	// 排程執行_batchlog table_start
//	@Update("UPDATE batchlog "
//			+ "SET taskid = #{taskid,jdbcType=VARCHAR}, logtime = #{date,jdbcType=TIMESTAMP}, logdescription = '========== 分保卡列印開始! =============' "
//			+ "WHERE taskid = #{taskid,jdbcType=VARCHAR} ")
//	int updateLog_start(@Param("taskid") String taskid, @Param("date") Date date);

	// 排程執行_batchlog table_start
	@Insert("<script> INSERT INTO batchlog(taskid, logtime,  logdescription) "
			+ " values(#{taskid,jdbcType=VARCHAR}, #{date,jdbcType=TIMESTAMP}, '========== 分保卡列印開始! =============') "
			+ "</script>")
	int insertLog_start(@Param("taskid") String taskid, @Param("date") Date date);

	// 排程執行_batchlog table_noData
	@Insert("<script> INSERT INTO batchlog(taskid, logtime,  logdescription) "
			+ " values(#{taskid,jdbcType=VARCHAR}, #{date,jdbcType=TIMESTAMP}, '無參數資料可執行, 請確認同險卡列印區間') " + "</script>")
	int insertLog_noData(@Param("taskid") String taskid, @Param("date") Date date);

	// 排程執行_batchlog table_error
	@Insert("<script> INSERT INTO batchlog(taskid, logtime,  logdescription) "
			+ " values(#{taskid,jdbcType=VARCHAR}, #{date,jdbcType=TIMESTAMP}, 'BatchRpt報表資料檔新增錯誤, 請聯絡資訊室人員處理') "
			+ "</script>")
	int insertLog_error(@Param("taskid") String taskid, @Param("date") Date date);

	// 排程執行_batchlog table_end
	@Insert("<script> INSERT INTO batchlog(taskid, logtime,  logdescription) "
			+ " values(#{taskid,jdbcType=VARCHAR}, #{date,jdbcType=TIMESTAMP}, '========== 分保卡列印完成! =============') "
			+ "</script>")
	int insertLog_end(@Param("taskid") String taskid, @Param("date") Date date);

	// batch002A
	@Select("<script>" + " SELECT A.seq, A.logdescription " + " FROM batchlog A "
			+ " WHERE taskid = #{taskid,jdbcType=VARCHAR} " + "</script>")
	List<Batchlog> queryLogUseID(@Param("taskid") String taskid);
	
	//batch002A 作業細項說明_模糊查詢
	@Select("<script>" + " SELECT A.seq, A.logdescription " + " FROM batchlog A "
			+ " WHERE taskid = #{taskid,jdbcType=VARCHAR} " 
			+ " and (A.logdescription like #{keyword,jdbcType=VARCHAR} "
			+ " or A.seq like #{keyword,jdbcType=VARCHAR})"
			+ "</script>")
	List<Batchlog> queryByContent(@Param("keyword") String keyword, @Param("taskid") String taskid);
}