package com.asi.mechanism.service.dao.mybatis.model;

public class SysAccountRoleKey {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column SYS_ACCOUNT_ROLE.USER_ID
     *
     * @mbg.generated Tue Jan 19 16:03:51 CST 2021
     */
    private String userId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column SYS_ACCOUNT_ROLE.USER_ROLE
     *
     * @mbg.generated Tue Jan 19 16:03:51 CST 2021
     */
    private String userRole;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column SYS_ACCOUNT_ROLE.USER_ID
     *
     * @return the value of SYS_ACCOUNT_ROLE.USER_ID
     *
     * @mbg.generated Tue Jan 19 16:03:51 CST 2021
     */
    public String getUserId() {
        return userId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column SYS_ACCOUNT_ROLE.USER_ID
     *
     * @param userId the value for SYS_ACCOUNT_ROLE.USER_ID
     *
     * @mbg.generated Tue Jan 19 16:03:51 CST 2021
     */
    public void setUserId(String userId) {
        this.userId = userId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column SYS_ACCOUNT_ROLE.USER_ROLE
     *
     * @return the value of SYS_ACCOUNT_ROLE.USER_ROLE
     *
     * @mbg.generated Tue Jan 19 16:03:51 CST 2021
     */
    public String getUserRole() {
        return userRole;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column SYS_ACCOUNT_ROLE.USER_ROLE
     *
     * @param userRole the value for SYS_ACCOUNT_ROLE.USER_ROLE
     *
     * @mbg.generated Tue Jan 19 16:03:51 CST 2021
     */
    public void setUserRole(String userRole) {
        this.userRole = userRole;
    }
}