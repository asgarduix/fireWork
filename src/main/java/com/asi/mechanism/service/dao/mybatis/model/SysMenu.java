package com.asi.mechanism.service.dao.mybatis.model;

import java.math.BigDecimal;
import java.util.Date;

public class SysMenu {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sys_menu.menu_id
     *
     * @mbg.generated Thu Feb 24 17:16:45 CST 2022
     */
    private BigDecimal menuId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sys_menu.menu_upper_id
     *
     * @mbg.generated Thu Feb 24 17:16:45 CST 2022
     */
    private BigDecimal menuUpperId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sys_menu.index_id
     *
     * @mbg.generated Thu Feb 24 17:16:45 CST 2022
     */
    private BigDecimal indexId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sys_menu.menu_name
     *
     * @mbg.generated Thu Feb 24 17:16:45 CST 2022
     */
    private String menuName;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sys_menu.menu_func_url
     *
     * @mbg.generated Thu Feb 24 17:16:45 CST 2022
     */
    private String menuFuncUrl;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sys_menu.menu_item_url
     *
     * @mbg.generated Thu Feb 24 17:16:45 CST 2022
     */
    private String menuItemUrl;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sys_menu.menu_item_class
     *
     * @mbg.generated Thu Feb 24 17:16:45 CST 2022
     */
    private String menuItemClass;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sys_menu.index_sort
     *
     * @mbg.generated Thu Feb 24 17:16:45 CST 2022
     */
    private Integer indexSort;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sys_menu.enable_mark
     *
     * @mbg.generated Thu Feb 24 17:16:45 CST 2022
     */
    private String enableMark;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sys_menu.crt_aka_id
     *
     * @mbg.generated Thu Feb 24 17:16:45 CST 2022
     */
    private String crtAkaId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sys_menu.crt_date
     *
     * @mbg.generated Thu Feb 24 17:16:45 CST 2022
     */
    private Date crtDate;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sys_menu.entry_aka_id
     *
     * @mbg.generated Thu Feb 24 17:16:45 CST 2022
     */
    private String entryAkaId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sys_menu.entry_date
     *
     * @mbg.generated Thu Feb 24 17:16:45 CST 2022
     */
    private Date entryDate;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sys_menu.menu_id
     *
     * @return the value of sys_menu.menu_id
     *
     * @mbg.generated Thu Feb 24 17:16:45 CST 2022
     */
    public BigDecimal getMenuId() {
        return menuId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sys_menu.menu_id
     *
     * @param menuId the value for sys_menu.menu_id
     *
     * @mbg.generated Thu Feb 24 17:16:45 CST 2022
     */
    public void setMenuId(BigDecimal menuId) {
        this.menuId = menuId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sys_menu.menu_upper_id
     *
     * @return the value of sys_menu.menu_upper_id
     *
     * @mbg.generated Thu Feb 24 17:16:45 CST 2022
     */
    public BigDecimal getMenuUpperId() {
        return menuUpperId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sys_menu.menu_upper_id
     *
     * @param menuUpperId the value for sys_menu.menu_upper_id
     *
     * @mbg.generated Thu Feb 24 17:16:45 CST 2022
     */
    public void setMenuUpperId(BigDecimal menuUpperId) {
        this.menuUpperId = menuUpperId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sys_menu.index_id
     *
     * @return the value of sys_menu.index_id
     *
     * @mbg.generated Thu Feb 24 17:16:45 CST 2022
     */
    public BigDecimal getIndexId() {
        return indexId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sys_menu.index_id
     *
     * @param indexId the value for sys_menu.index_id
     *
     * @mbg.generated Thu Feb 24 17:16:45 CST 2022
     */
    public void setIndexId(BigDecimal indexId) {
        this.indexId = indexId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sys_menu.menu_name
     *
     * @return the value of sys_menu.menu_name
     *
     * @mbg.generated Thu Feb 24 17:16:45 CST 2022
     */
    public String getMenuName() {
        return menuName;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sys_menu.menu_name
     *
     * @param menuName the value for sys_menu.menu_name
     *
     * @mbg.generated Thu Feb 24 17:16:45 CST 2022
     */
    public void setMenuName(String menuName) {
        this.menuName = menuName;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sys_menu.menu_func_url
     *
     * @return the value of sys_menu.menu_func_url
     *
     * @mbg.generated Thu Feb 24 17:16:45 CST 2022
     */
    public String getMenuFuncUrl() {
        return menuFuncUrl;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sys_menu.menu_func_url
     *
     * @param menuFuncUrl the value for sys_menu.menu_func_url
     *
     * @mbg.generated Thu Feb 24 17:16:45 CST 2022
     */
    public void setMenuFuncUrl(String menuFuncUrl) {
        this.menuFuncUrl = menuFuncUrl;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sys_menu.menu_item_url
     *
     * @return the value of sys_menu.menu_item_url
     *
     * @mbg.generated Thu Feb 24 17:16:45 CST 2022
     */
    public String getMenuItemUrl() {
        return menuItemUrl;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sys_menu.menu_item_url
     *
     * @param menuItemUrl the value for sys_menu.menu_item_url
     *
     * @mbg.generated Thu Feb 24 17:16:45 CST 2022
     */
    public void setMenuItemUrl(String menuItemUrl) {
        this.menuItemUrl = menuItemUrl;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sys_menu.menu_item_class
     *
     * @return the value of sys_menu.menu_item_class
     *
     * @mbg.generated Thu Feb 24 17:16:45 CST 2022
     */
    public String getMenuItemClass() {
        return menuItemClass;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sys_menu.menu_item_class
     *
     * @param menuItemClass the value for sys_menu.menu_item_class
     *
     * @mbg.generated Thu Feb 24 17:16:45 CST 2022
     */
    public void setMenuItemClass(String menuItemClass) {
        this.menuItemClass = menuItemClass;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sys_menu.index_sort
     *
     * @return the value of sys_menu.index_sort
     *
     * @mbg.generated Thu Feb 24 17:16:45 CST 2022
     */
    public Integer getIndexSort() {
        return indexSort;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sys_menu.index_sort
     *
     * @param indexSort the value for sys_menu.index_sort
     *
     * @mbg.generated Thu Feb 24 17:16:45 CST 2022
     */
    public void setIndexSort(Integer indexSort) {
        this.indexSort = indexSort;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sys_menu.enable_mark
     *
     * @return the value of sys_menu.enable_mark
     *
     * @mbg.generated Thu Feb 24 17:16:45 CST 2022
     */
    public String getEnableMark() {
        return enableMark;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sys_menu.enable_mark
     *
     * @param enableMark the value for sys_menu.enable_mark
     *
     * @mbg.generated Thu Feb 24 17:16:45 CST 2022
     */
    public void setEnableMark(String enableMark) {
        this.enableMark = enableMark;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sys_menu.crt_aka_id
     *
     * @return the value of sys_menu.crt_aka_id
     *
     * @mbg.generated Thu Feb 24 17:16:45 CST 2022
     */
    public String getCrtAkaId() {
        return crtAkaId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sys_menu.crt_aka_id
     *
     * @param crtAkaId the value for sys_menu.crt_aka_id
     *
     * @mbg.generated Thu Feb 24 17:16:45 CST 2022
     */
    public void setCrtAkaId(String crtAkaId) {
        this.crtAkaId = crtAkaId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sys_menu.crt_date
     *
     * @return the value of sys_menu.crt_date
     *
     * @mbg.generated Thu Feb 24 17:16:45 CST 2022
     */
    public Date getCrtDate() {
        return crtDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sys_menu.crt_date
     *
     * @param crtDate the value for sys_menu.crt_date
     *
     * @mbg.generated Thu Feb 24 17:16:45 CST 2022
     */
    public void setCrtDate(Date crtDate) {
        this.crtDate = crtDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sys_menu.entry_aka_id
     *
     * @return the value of sys_menu.entry_aka_id
     *
     * @mbg.generated Thu Feb 24 17:16:45 CST 2022
     */
    public String getEntryAkaId() {
        return entryAkaId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sys_menu.entry_aka_id
     *
     * @param entryAkaId the value for sys_menu.entry_aka_id
     *
     * @mbg.generated Thu Feb 24 17:16:45 CST 2022
     */
    public void setEntryAkaId(String entryAkaId) {
        this.entryAkaId = entryAkaId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sys_menu.entry_date
     *
     * @return the value of sys_menu.entry_date
     *
     * @mbg.generated Thu Feb 24 17:16:45 CST 2022
     */
    public Date getEntryDate() {
        return entryDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sys_menu.entry_date
     *
     * @param entryDate the value for sys_menu.entry_date
     *
     * @mbg.generated Thu Feb 24 17:16:45 CST 2022
     */
    public void setEntryDate(Date entryDate) {
        this.entryDate = entryDate;
    }
}