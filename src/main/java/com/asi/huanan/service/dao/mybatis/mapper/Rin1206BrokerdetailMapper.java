package com.asi.huanan.service.dao.mybatis.mapper;

import com.asi.huanan.service.dao.mybatis.model.Rin1206Brokerdetail;
import com.asi.huanan.service.dao.mybatis.model.Rin1206BrokerdetailExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface Rin1206BrokerdetailMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table RIN1206_BrokerDetail
     *
     * @mbg.generated Mon Dec 20 13:58:52 CST 2021
     */
    long countByExample(Rin1206BrokerdetailExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table RIN1206_BrokerDetail
     *
     * @mbg.generated Mon Dec 20 13:58:52 CST 2021
     */
    int deleteByExample(Rin1206BrokerdetailExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table RIN1206_BrokerDetail
     *
     * @mbg.generated Mon Dec 20 13:58:52 CST 2021
     */
    int insert(Rin1206Brokerdetail record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table RIN1206_BrokerDetail
     *
     * @mbg.generated Mon Dec 20 13:58:52 CST 2021
     */
    int insertSelective(Rin1206Brokerdetail record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table RIN1206_BrokerDetail
     *
     * @mbg.generated Mon Dec 20 13:58:52 CST 2021
     */
    List<Rin1206Brokerdetail> selectByExample(Rin1206BrokerdetailExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table RIN1206_BrokerDetail
     *
     * @mbg.generated Mon Dec 20 13:58:52 CST 2021
     */
    int updateByExampleSelective(@Param("record") Rin1206Brokerdetail record, @Param("example") Rin1206BrokerdetailExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table RIN1206_BrokerDetail
     *
     * @mbg.generated Mon Dec 20 13:58:52 CST 2021
     */
    int updateByExample(@Param("record") Rin1206Brokerdetail record, @Param("example") Rin1206BrokerdetailExample example);
}