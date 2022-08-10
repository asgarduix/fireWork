package com.asi.huanan.service.dao.mybatis.mapper;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.asi.huanan.service.dao.mybatis.model.Mrexpf;
import com.asi.huanan.service.dao.mybatis.model.MrexpfExample;

public interface MrexpfMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table mrexpf
     *
     * @mbg.generated Mon Dec 27 14:52:51 CST 2021
     */
    long countByExample(MrexpfExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table mrexpf
     *
     * @mbg.generated Mon Dec 27 14:52:51 CST 2021
     */
    int deleteByExample(MrexpfExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table mrexpf
     *
     * @mbg.generated Mon Dec 27 14:52:51 CST 2021
     */
    int insert(Mrexpf record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table mrexpf
     *
     * @mbg.generated Mon Dec 27 14:52:51 CST 2021
     */
    int insertSelective(Mrexpf record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table mrexpf
     *
     * @mbg.generated Mon Dec 27 14:52:51 CST 2021
     */
    List<Mrexpf> selectByExample(MrexpfExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table mrexpf
     *
     * @mbg.generated Mon Dec 27 14:52:51 CST 2021
     */
    int updateByExampleSelective(@Param("record") Mrexpf record, @Param("example") MrexpfExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table mrexpf
     *
     * @mbg.generated Mon Dec 27 14:52:51 CST 2021
     */
    int updateByExample(@Param("record") Mrexpf record, @Param("example") MrexpfExample example);
    
    @Select({"<script>"
			," SELECT DISTINCT curncy,exrate "
			," FROM mrexpf"
			," WHERE crtdat = (SELECT max(crtdat) FROM mrexpf where crtdat &lt;= #{queryDate})"
			,"</script>"})
	List<Mrexpf> queryByCrtdat(@Param("queryDate") Date queryDate);

    @Select({"<script>"
		," SELECT DISTINCT curncy,exrate "
		," FROM mrexpf"
		," WHERE crtdat = (SELECT max(crtdat) FROM mrexpf where crtdat &lt;= #{queryDate})"
		," AND curncy = #{currency} "
		,"</script>"})
	List<Mrexpf> queryCurrency(@Param("currency") String currency, @Param("queryDate") Date now);
    
    
}