package com.asi.huanan.service.dao.mybatis.model;

import java.util.Date;

public class FriComCreditKey {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column fri_com_credit.rin_com_id
     *
     * @mbg.generated Sun Sep 26 17:31:48 CST 2021
     */
    private String rinComId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column fri_com_credit.credit_organ
     *
     * @mbg.generated Sun Sep 26 17:31:48 CST 2021
     */
    private String creditOrgan;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column fri_com_credit.credit_date
     *
     * @mbg.generated Sun Sep 26 17:31:48 CST 2021
     */
    private Date creditDate;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column fri_com_credit.rin_com_id
     *
     * @return the value of fri_com_credit.rin_com_id
     *
     * @mbg.generated Sun Sep 26 17:31:48 CST 2021
     */
    public String getRinComId() {
        return rinComId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column fri_com_credit.rin_com_id
     *
     * @param rinComId the value for fri_com_credit.rin_com_id
     *
     * @mbg.generated Sun Sep 26 17:31:48 CST 2021
     */
    public void setRinComId(String rinComId) {
        this.rinComId = rinComId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column fri_com_credit.credit_organ
     *
     * @return the value of fri_com_credit.credit_organ
     *
     * @mbg.generated Sun Sep 26 17:31:48 CST 2021
     */
    public String getCreditOrgan() {
        return creditOrgan;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column fri_com_credit.credit_organ
     *
     * @param creditOrgan the value for fri_com_credit.credit_organ
     *
     * @mbg.generated Sun Sep 26 17:31:48 CST 2021
     */
    public void setCreditOrgan(String creditOrgan) {
        this.creditOrgan = creditOrgan;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column fri_com_credit.credit_date
     *
     * @return the value of fri_com_credit.credit_date
     *
     * @mbg.generated Sun Sep 26 17:31:48 CST 2021
     */
    public Date getCreditDate() {
        return creditDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column fri_com_credit.credit_date
     *
     * @param creditDate the value for fri_com_credit.credit_date
     *
     * @mbg.generated Sun Sep 26 17:31:48 CST 2021
     */
    public void setCreditDate(Date creditDate) {
        this.creditDate = creditDate;
    }
}