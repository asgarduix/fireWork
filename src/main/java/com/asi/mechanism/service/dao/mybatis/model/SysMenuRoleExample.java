package com.asi.mechanism.service.dao.mybatis.model;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class SysMenuRoleExample {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table sys_menu_role
     *
     * @mbg.generated Tue Jun 08 16:18:13 CST 2021
     */
    protected String orderByClause;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table sys_menu_role
     *
     * @mbg.generated Tue Jun 08 16:18:13 CST 2021
     */
    protected boolean distinct;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table sys_menu_role
     *
     * @mbg.generated Tue Jun 08 16:18:13 CST 2021
     */
    protected List<Criteria> oredCriteria;

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_menu_role
     *
     * @mbg.generated Tue Jun 08 16:18:13 CST 2021
     */
    public SysMenuRoleExample() {
        oredCriteria = new ArrayList<>();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_menu_role
     *
     * @mbg.generated Tue Jun 08 16:18:13 CST 2021
     */
    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_menu_role
     *
     * @mbg.generated Tue Jun 08 16:18:13 CST 2021
     */
    public String getOrderByClause() {
        return orderByClause;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_menu_role
     *
     * @mbg.generated Tue Jun 08 16:18:13 CST 2021
     */
    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_menu_role
     *
     * @mbg.generated Tue Jun 08 16:18:13 CST 2021
     */
    public boolean isDistinct() {
        return distinct;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_menu_role
     *
     * @mbg.generated Tue Jun 08 16:18:13 CST 2021
     */
    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_menu_role
     *
     * @mbg.generated Tue Jun 08 16:18:13 CST 2021
     */
    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_menu_role
     *
     * @mbg.generated Tue Jun 08 16:18:13 CST 2021
     */
    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_menu_role
     *
     * @mbg.generated Tue Jun 08 16:18:13 CST 2021
     */
    public Criteria createCriteria() {
        Criteria criteria = createCriteriaInternal();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_menu_role
     *
     * @mbg.generated Tue Jun 08 16:18:13 CST 2021
     */
    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_menu_role
     *
     * @mbg.generated Tue Jun 08 16:18:13 CST 2021
     */
    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table sys_menu_role
     *
     * @mbg.generated Tue Jun 08 16:18:13 CST 2021
     */
    protected abstract static class GeneratedCriteria {
        protected List<Criterion> criteria;

        protected GeneratedCriteria() {
            super();
            criteria = new ArrayList<>();
        }

        public boolean isValid() {
            return criteria.size() > 0;
        }

        public List<Criterion> getAllCriteria() {
            return criteria;
        }

        public List<Criterion> getCriteria() {
            return criteria;
        }

        protected void addCriterion(String condition) {
            if (condition == null) {
                throw new RuntimeException("Value for condition cannot be null");
            }
            criteria.add(new Criterion(condition));
        }

        protected void addCriterion(String condition, Object value, String property) {
            if (value == null) {
                throw new RuntimeException("Value for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value));
        }

        protected void addCriterion(String condition, Object value1, Object value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value1, value2));
        }

        public Criteria andUserRoleIsNull() {
            addCriterion("user_role is null");
            return (Criteria) this;
        }

        public Criteria andUserRoleIsNotNull() {
            addCriterion("user_role is not null");
            return (Criteria) this;
        }

        public Criteria andUserRoleEqualTo(String value) {
            addCriterion("user_role =", value, "userRole");
            return (Criteria) this;
        }

        public Criteria andUserRoleNotEqualTo(String value) {
            addCriterion("user_role <>", value, "userRole");
            return (Criteria) this;
        }

        public Criteria andUserRoleGreaterThan(String value) {
            addCriterion("user_role >", value, "userRole");
            return (Criteria) this;
        }

        public Criteria andUserRoleGreaterThanOrEqualTo(String value) {
            addCriterion("user_role >=", value, "userRole");
            return (Criteria) this;
        }

        public Criteria andUserRoleLessThan(String value) {
            addCriterion("user_role <", value, "userRole");
            return (Criteria) this;
        }

        public Criteria andUserRoleLessThanOrEqualTo(String value) {
            addCriterion("user_role <=", value, "userRole");
            return (Criteria) this;
        }

        public Criteria andUserRoleLike(String value) {
            addCriterion("user_role like", value, "userRole");
            return (Criteria) this;
        }

        public Criteria andUserRoleNotLike(String value) {
            addCriterion("user_role not like", value, "userRole");
            return (Criteria) this;
        }

        public Criteria andUserRoleIn(List<String> values) {
            addCriterion("user_role in", values, "userRole");
            return (Criteria) this;
        }

        public Criteria andUserRoleNotIn(List<String> values) {
            addCriterion("user_role not in", values, "userRole");
            return (Criteria) this;
        }

        public Criteria andUserRoleBetween(String value1, String value2) {
            addCriterion("user_role between", value1, value2, "userRole");
            return (Criteria) this;
        }

        public Criteria andUserRoleNotBetween(String value1, String value2) {
            addCriterion("user_role not between", value1, value2, "userRole");
            return (Criteria) this;
        }

        public Criteria andMenuIdIsNull() {
            addCriterion("menu_id is null");
            return (Criteria) this;
        }

        public Criteria andMenuIdIsNotNull() {
            addCriterion("menu_id is not null");
            return (Criteria) this;
        }

        public Criteria andMenuIdEqualTo(BigDecimal value) {
            addCriterion("menu_id =", value, "menuId");
            return (Criteria) this;
        }

        public Criteria andMenuIdNotEqualTo(BigDecimal value) {
            addCriterion("menu_id <>", value, "menuId");
            return (Criteria) this;
        }

        public Criteria andMenuIdGreaterThan(BigDecimal value) {
            addCriterion("menu_id >", value, "menuId");
            return (Criteria) this;
        }

        public Criteria andMenuIdGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("menu_id >=", value, "menuId");
            return (Criteria) this;
        }

        public Criteria andMenuIdLessThan(BigDecimal value) {
            addCriterion("menu_id <", value, "menuId");
            return (Criteria) this;
        }

        public Criteria andMenuIdLessThanOrEqualTo(BigDecimal value) {
            addCriterion("menu_id <=", value, "menuId");
            return (Criteria) this;
        }

        public Criteria andMenuIdIn(List<BigDecimal> values) {
            addCriterion("menu_id in", values, "menuId");
            return (Criteria) this;
        }

        public Criteria andMenuIdNotIn(List<BigDecimal> values) {
            addCriterion("menu_id not in", values, "menuId");
            return (Criteria) this;
        }

        public Criteria andMenuIdBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("menu_id between", value1, value2, "menuId");
            return (Criteria) this;
        }

        public Criteria andMenuIdNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("menu_id not between", value1, value2, "menuId");
            return (Criteria) this;
        }

        public Criteria andEnableMarkIsNull() {
            addCriterion("enable_mark is null");
            return (Criteria) this;
        }

        public Criteria andEnableMarkIsNotNull() {
            addCriterion("enable_mark is not null");
            return (Criteria) this;
        }

        public Criteria andEnableMarkEqualTo(String value) {
            addCriterion("enable_mark =", value, "enableMark");
            return (Criteria) this;
        }

        public Criteria andEnableMarkNotEqualTo(String value) {
            addCriterion("enable_mark <>", value, "enableMark");
            return (Criteria) this;
        }

        public Criteria andEnableMarkGreaterThan(String value) {
            addCriterion("enable_mark >", value, "enableMark");
            return (Criteria) this;
        }

        public Criteria andEnableMarkGreaterThanOrEqualTo(String value) {
            addCriterion("enable_mark >=", value, "enableMark");
            return (Criteria) this;
        }

        public Criteria andEnableMarkLessThan(String value) {
            addCriterion("enable_mark <", value, "enableMark");
            return (Criteria) this;
        }

        public Criteria andEnableMarkLessThanOrEqualTo(String value) {
            addCriterion("enable_mark <=", value, "enableMark");
            return (Criteria) this;
        }

        public Criteria andEnableMarkLike(String value) {
            addCriterion("enable_mark like", value, "enableMark");
            return (Criteria) this;
        }

        public Criteria andEnableMarkNotLike(String value) {
            addCriterion("enable_mark not like", value, "enableMark");
            return (Criteria) this;
        }

        public Criteria andEnableMarkIn(List<String> values) {
            addCriterion("enable_mark in", values, "enableMark");
            return (Criteria) this;
        }

        public Criteria andEnableMarkNotIn(List<String> values) {
            addCriterion("enable_mark not in", values, "enableMark");
            return (Criteria) this;
        }

        public Criteria andEnableMarkBetween(String value1, String value2) {
            addCriterion("enable_mark between", value1, value2, "enableMark");
            return (Criteria) this;
        }

        public Criteria andEnableMarkNotBetween(String value1, String value2) {
            addCriterion("enable_mark not between", value1, value2, "enableMark");
            return (Criteria) this;
        }

        public Criteria andCrtUseridIsNull() {
            addCriterion("crt_userid is null");
            return (Criteria) this;
        }

        public Criteria andCrtUseridIsNotNull() {
            addCriterion("crt_userid is not null");
            return (Criteria) this;
        }

        public Criteria andCrtUseridEqualTo(String value) {
            addCriterion("crt_userid =", value, "crtUserid");
            return (Criteria) this;
        }

        public Criteria andCrtUseridNotEqualTo(String value) {
            addCriterion("crt_userid <>", value, "crtUserid");
            return (Criteria) this;
        }

        public Criteria andCrtUseridGreaterThan(String value) {
            addCriterion("crt_userid >", value, "crtUserid");
            return (Criteria) this;
        }

        public Criteria andCrtUseridGreaterThanOrEqualTo(String value) {
            addCriterion("crt_userid >=", value, "crtUserid");
            return (Criteria) this;
        }

        public Criteria andCrtUseridLessThan(String value) {
            addCriterion("crt_userid <", value, "crtUserid");
            return (Criteria) this;
        }

        public Criteria andCrtUseridLessThanOrEqualTo(String value) {
            addCriterion("crt_userid <=", value, "crtUserid");
            return (Criteria) this;
        }

        public Criteria andCrtUseridLike(String value) {
            addCriterion("crt_userid like", value, "crtUserid");
            return (Criteria) this;
        }

        public Criteria andCrtUseridNotLike(String value) {
            addCriterion("crt_userid not like", value, "crtUserid");
            return (Criteria) this;
        }

        public Criteria andCrtUseridIn(List<String> values) {
            addCriterion("crt_userid in", values, "crtUserid");
            return (Criteria) this;
        }

        public Criteria andCrtUseridNotIn(List<String> values) {
            addCriterion("crt_userid not in", values, "crtUserid");
            return (Criteria) this;
        }

        public Criteria andCrtUseridBetween(String value1, String value2) {
            addCriterion("crt_userid between", value1, value2, "crtUserid");
            return (Criteria) this;
        }

        public Criteria andCrtUseridNotBetween(String value1, String value2) {
            addCriterion("crt_userid not between", value1, value2, "crtUserid");
            return (Criteria) this;
        }

        public Criteria andCrtDateIsNull() {
            addCriterion("crt_date is null");
            return (Criteria) this;
        }

        public Criteria andCrtDateIsNotNull() {
            addCriterion("crt_date is not null");
            return (Criteria) this;
        }

        public Criteria andCrtDateEqualTo(Date value) {
            addCriterion("crt_date =", value, "crtDate");
            return (Criteria) this;
        }

        public Criteria andCrtDateNotEqualTo(Date value) {
            addCriterion("crt_date <>", value, "crtDate");
            return (Criteria) this;
        }

        public Criteria andCrtDateGreaterThan(Date value) {
            addCriterion("crt_date >", value, "crtDate");
            return (Criteria) this;
        }

        public Criteria andCrtDateGreaterThanOrEqualTo(Date value) {
            addCriterion("crt_date >=", value, "crtDate");
            return (Criteria) this;
        }

        public Criteria andCrtDateLessThan(Date value) {
            addCriterion("crt_date <", value, "crtDate");
            return (Criteria) this;
        }

        public Criteria andCrtDateLessThanOrEqualTo(Date value) {
            addCriterion("crt_date <=", value, "crtDate");
            return (Criteria) this;
        }

        public Criteria andCrtDateIn(List<Date> values) {
            addCriterion("crt_date in", values, "crtDate");
            return (Criteria) this;
        }

        public Criteria andCrtDateNotIn(List<Date> values) {
            addCriterion("crt_date not in", values, "crtDate");
            return (Criteria) this;
        }

        public Criteria andCrtDateBetween(Date value1, Date value2) {
            addCriterion("crt_date between", value1, value2, "crtDate");
            return (Criteria) this;
        }

        public Criteria andCrtDateNotBetween(Date value1, Date value2) {
            addCriterion("crt_date not between", value1, value2, "crtDate");
            return (Criteria) this;
        }

        public Criteria andEntryUseridIsNull() {
            addCriterion("entry_userid is null");
            return (Criteria) this;
        }

        public Criteria andEntryUseridIsNotNull() {
            addCriterion("entry_userid is not null");
            return (Criteria) this;
        }

        public Criteria andEntryUseridEqualTo(String value) {
            addCriterion("entry_userid =", value, "entryUserid");
            return (Criteria) this;
        }

        public Criteria andEntryUseridNotEqualTo(String value) {
            addCriterion("entry_userid <>", value, "entryUserid");
            return (Criteria) this;
        }

        public Criteria andEntryUseridGreaterThan(String value) {
            addCriterion("entry_userid >", value, "entryUserid");
            return (Criteria) this;
        }

        public Criteria andEntryUseridGreaterThanOrEqualTo(String value) {
            addCriterion("entry_userid >=", value, "entryUserid");
            return (Criteria) this;
        }

        public Criteria andEntryUseridLessThan(String value) {
            addCriterion("entry_userid <", value, "entryUserid");
            return (Criteria) this;
        }

        public Criteria andEntryUseridLessThanOrEqualTo(String value) {
            addCriterion("entry_userid <=", value, "entryUserid");
            return (Criteria) this;
        }

        public Criteria andEntryUseridLike(String value) {
            addCriterion("entry_userid like", value, "entryUserid");
            return (Criteria) this;
        }

        public Criteria andEntryUseridNotLike(String value) {
            addCriterion("entry_userid not like", value, "entryUserid");
            return (Criteria) this;
        }

        public Criteria andEntryUseridIn(List<String> values) {
            addCriterion("entry_userid in", values, "entryUserid");
            return (Criteria) this;
        }

        public Criteria andEntryUseridNotIn(List<String> values) {
            addCriterion("entry_userid not in", values, "entryUserid");
            return (Criteria) this;
        }

        public Criteria andEntryUseridBetween(String value1, String value2) {
            addCriterion("entry_userid between", value1, value2, "entryUserid");
            return (Criteria) this;
        }

        public Criteria andEntryUseridNotBetween(String value1, String value2) {
            addCriterion("entry_userid not between", value1, value2, "entryUserid");
            return (Criteria) this;
        }

        public Criteria andEntryDateIsNull() {
            addCriterion("entry_date is null");
            return (Criteria) this;
        }

        public Criteria andEntryDateIsNotNull() {
            addCriterion("entry_date is not null");
            return (Criteria) this;
        }

        public Criteria andEntryDateEqualTo(Date value) {
            addCriterion("entry_date =", value, "entryDate");
            return (Criteria) this;
        }

        public Criteria andEntryDateNotEqualTo(Date value) {
            addCriterion("entry_date <>", value, "entryDate");
            return (Criteria) this;
        }

        public Criteria andEntryDateGreaterThan(Date value) {
            addCriterion("entry_date >", value, "entryDate");
            return (Criteria) this;
        }

        public Criteria andEntryDateGreaterThanOrEqualTo(Date value) {
            addCriterion("entry_date >=", value, "entryDate");
            return (Criteria) this;
        }

        public Criteria andEntryDateLessThan(Date value) {
            addCriterion("entry_date <", value, "entryDate");
            return (Criteria) this;
        }

        public Criteria andEntryDateLessThanOrEqualTo(Date value) {
            addCriterion("entry_date <=", value, "entryDate");
            return (Criteria) this;
        }

        public Criteria andEntryDateIn(List<Date> values) {
            addCriterion("entry_date in", values, "entryDate");
            return (Criteria) this;
        }

        public Criteria andEntryDateNotIn(List<Date> values) {
            addCriterion("entry_date not in", values, "entryDate");
            return (Criteria) this;
        }

        public Criteria andEntryDateBetween(Date value1, Date value2) {
            addCriterion("entry_date between", value1, value2, "entryDate");
            return (Criteria) this;
        }

        public Criteria andEntryDateNotBetween(Date value1, Date value2) {
            addCriterion("entry_date not between", value1, value2, "entryDate");
            return (Criteria) this;
        }
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table sys_menu_role
     *
     * @mbg.generated do_not_delete_during_merge Tue Jun 08 16:18:13 CST 2021
     */
    public static class Criteria extends GeneratedCriteria {
        protected Criteria() {
            super();
        }
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table sys_menu_role
     *
     * @mbg.generated Tue Jun 08 16:18:13 CST 2021
     */
    public static class Criterion {
        private String condition;

        private Object value;

        private Object secondValue;

        private boolean noValue;

        private boolean singleValue;

        private boolean betweenValue;

        private boolean listValue;

        private String typeHandler;

        public String getCondition() {
            return condition;
        }

        public Object getValue() {
            return value;
        }

        public Object getSecondValue() {
            return secondValue;
        }

        public boolean isNoValue() {
            return noValue;
        }

        public boolean isSingleValue() {
            return singleValue;
        }

        public boolean isBetweenValue() {
            return betweenValue;
        }

        public boolean isListValue() {
            return listValue;
        }

        public String getTypeHandler() {
            return typeHandler;
        }

        protected Criterion(String condition) {
            super();
            this.condition = condition;
            this.typeHandler = null;
            this.noValue = true;
        }

        protected Criterion(String condition, Object value, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.typeHandler = typeHandler;
            if (value instanceof List<?>) {
                this.listValue = true;
            } else {
                this.singleValue = true;
            }
        }

        protected Criterion(String condition, Object value) {
            this(condition, value, null);
        }

        protected Criterion(String condition, Object value, Object secondValue, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.secondValue = secondValue;
            this.typeHandler = typeHandler;
            this.betweenValue = true;
        }

        protected Criterion(String condition, Object value, Object secondValue) {
            this(condition, value, secondValue, null);
        }
    }
}