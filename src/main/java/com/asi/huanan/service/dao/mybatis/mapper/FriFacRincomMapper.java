package com.asi.huanan.service.dao.mybatis.mapper;

import com.asi.huanan.service.dao.mybatis.model.FriFacRincom;
import com.asi.huanan.service.dao.mybatis.model.FriFacRincomExample;
import com.asi.huanan.service.dao.mybatis.model.FriFacRincomKey;
import com.asi.huanan.vo.Rin1303UpdateStatusByNoIdVOReq;

import java.util.Date;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

public interface FriFacRincomMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table fri_fac_rincom
     *
     * @mbg.generated Wed Dec 01 10:30:27 CST 2021
     */
    long countByExample(FriFacRincomExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table fri_fac_rincom
     *
     * @mbg.generated Wed Dec 01 10:30:27 CST 2021
     */
    int deleteByExample(FriFacRincomExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table fri_fac_rincom
     *
     * @mbg.generated Wed Dec 01 10:30:27 CST 2021
     */
    int deleteByPrimaryKey(FriFacRincomKey key);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table fri_fac_rincom
     *
     * @mbg.generated Wed Dec 01 10:30:27 CST 2021
     */
    int insert(FriFacRincom record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table fri_fac_rincom
     *
     * @mbg.generated Wed Dec 01 10:30:27 CST 2021
     */
    int insertSelective(FriFacRincom record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table fri_fac_rincom
     *
     * @mbg.generated Wed Dec 01 10:30:27 CST 2021
     */
    List<FriFacRincom> selectByExample(FriFacRincomExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table fri_fac_rincom
     *
     * @mbg.generated Wed Dec 01 10:30:27 CST 2021
     */
    FriFacRincom selectByPrimaryKey(FriFacRincomKey key);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table fri_fac_rincom
     *
     * @mbg.generated Wed Dec 01 10:30:27 CST 2021
     */
    int updateByExampleSelective(@Param("record") FriFacRincom record, @Param("example") FriFacRincomExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table fri_fac_rincom
     *
     * @mbg.generated Wed Dec 01 10:30:27 CST 2021
     */
    int updateByExample(@Param("record") FriFacRincom record, @Param("example") FriFacRincomExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table fri_fac_rincom
     *
     * @mbg.generated Wed Dec 01 10:30:27 CST 2021
     */
    int updateByPrimaryKeySelective(FriFacRincom record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table fri_fac_rincom
     *
     * @mbg.generated Wed Dec 01 10:30:27 CST 2021
     */
    int updateByPrimaryKey(FriFacRincom record);
    
    
    @Update("<script>update fri_fac_rincom "
    		+ "set bill_no_external = #{record.billNoExternal,jdbcType=VARCHAR}, transfer_status = #{record.transferStatus,jdbcType=VARCHAR} "
    		+ "where slip_no = #{record.slipNo,jdbcType=VARCHAR} "
    		+ "and rin_com_id = #{record.rinComId,jdbcType=VARCHAR} </script>")
    int updateStatusByNoId(@Param("record") Rin1303UpdateStatusByNoIdVOReq record);
    
    
    @Select("<script>select transfer_status  "
    		+ "from fri_fac_rincom " + 
    		"where slip_no = #{slipNo} " + 
    		"and rin_com_id = #{rinComId}</script>")
    
    String checkTransferStatus(@Param("slipNo") String slipNo, @Param("rinComId") String rinComId);
    
    @Update("<script> update fri_fac_rincom "
    		+ "set acct_flag = 'Y' , transfer_status = 'Y' "
    		+ "<if test='printDate != null'> "
    		+ ",print_date = #{printDate,jdbcType=TIMESTAMP} "
			+ "</if> "		
    		+ "where slip_no = #{slipNo} "
    		+ "and rin_com_id = #{rinComId} "
    		+ "</script>")
    void updateRin1303AcctFlag(@Param("printDate") Date printDate, @Param("slipNo") String slipNo, @Param("rinComId") String rinComId);
}