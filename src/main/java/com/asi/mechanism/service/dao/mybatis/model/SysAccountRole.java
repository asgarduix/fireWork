package com.asi.mechanism.service.dao.mybatis.model;

import java.util.Date;

public class SysAccountRole extends SysAccountRoleKey {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column SYS_ACCOUNT_ROLE.CRT_USERID
     *
     * @mbg.generated Tue Jan 19 16:03:51 CST 2021
     */
    private String crtUserid;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column SYS_ACCOUNT_ROLE.CRT_DATE
     *
     * @mbg.generated Tue Jan 19 16:03:51 CST 2021
     */
    private Date crtDate;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column SYS_ACCOUNT_ROLE.ENTRY_USERID
     *
     * @mbg.generated Tue Jan 19 16:03:51 CST 2021
     */
    private String entryUserid;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column SYS_ACCOUNT_ROLE.ENTRY_DATE
     *
     * @mbg.generated Tue Jan 19 16:03:51 CST 2021
     */
    private Date entryDate;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column SYS_ACCOUNT_ROLE.CRT_USERID
     *
     * @return the value of SYS_ACCOUNT_ROLE.CRT_USERID
     *
     * @mbg.generated Tue Jan 19 16:03:51 CST 2021
     */
    public String getCrtUserid() {
        return crtUserid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column SYS_ACCOUNT_ROLE.CRT_USERID
     *
     * @param crtUserid the value for SYS_ACCOUNT_ROLE.CRT_USERID
     *
     * @mbg.generated Tue Jan 19 16:03:51 CST 2021
     */
    public void setCrtUserid(String crtUserid) {
        this.crtUserid = crtUserid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column SYS_ACCOUNT_ROLE.CRT_DATE
     *
     * @return the value of SYS_ACCOUNT_ROLE.CRT_DATE
     *
     * @mbg.generated Tue Jan 19 16:03:51 CST 2021
     */
    public Date getCrtDate() {
        return crtDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column SYS_ACCOUNT_ROLE.CRT_DATE
     *
     * @param crtDate the value for SYS_ACCOUNT_ROLE.CRT_DATE
     *
     * @mbg.generated Tue Jan 19 16:03:51 CST 2021
     */
    public void setCrtDate(Date crtDate) {
        this.crtDate = crtDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column SYS_ACCOUNT_ROLE.ENTRY_USERID
     *
     * @return the value of SYS_ACCOUNT_ROLE.ENTRY_USERID
     *
     * @mbg.generated Tue Jan 19 16:03:51 CST 2021
     */
    public String getEntryUserid() {
        return entryUserid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column SYS_ACCOUNT_ROLE.ENTRY_USERID
     *
     * @param entryUserid the value for SYS_ACCOUNT_ROLE.ENTRY_USERID
     *
     * @mbg.generated Tue Jan 19 16:03:51 CST 2021
     */
    public void setEntryUserid(String entryUserid) {
        this.entryUserid = entryUserid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column SYS_ACCOUNT_ROLE.ENTRY_DATE
     *
     * @return the value of SYS_ACCOUNT_ROLE.ENTRY_DATE
     *
     * @mbg.generated Tue Jan 19 16:03:51 CST 2021
     */
    public Date getEntryDate() {
        return entryDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column SYS_ACCOUNT_ROLE.ENTRY_DATE
     *
     * @param entryDate the value for SYS_ACCOUNT_ROLE.ENTRY_DATE
     *
     * @mbg.generated Tue Jan 19 16:03:51 CST 2021
     */
    public void setEntryDate(Date entryDate) {
        this.entryDate = entryDate;
    }
}