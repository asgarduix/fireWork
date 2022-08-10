package com.asi.mechanism.service.dao.mybatis.model;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class SysIndexRoleExample {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table sys_index_role
     *
     * @mbg.generated Thu Feb 24 17:16:45 CST 2022
     */
    protected String orderByClause;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table sys_index_role
     *
     * @mbg.generated Thu Feb 24 17:16:45 CST 2022
     */
    protected boolean distinct;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table sys_index_role
     *
     * @mbg.generated Thu Feb 24 17:16:45 CST 2022
     */
    protected List<Criteria> oredCriteria;

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_index_role
     *
     * @mbg.generated Thu Feb 24 17:16:45 CST 2022
     */
    public SysIndexRoleExample() {
        oredCriteria = new ArrayList<>();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_index_role
     *
     * @mbg.generated Thu Feb 24 17:16:45 CST 2022
     */
    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_index_role
     *
     * @mbg.generated Thu Feb 24 17:16:45 CST 2022
     */
    public String getOrderByClause() {
        return orderByClause;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_index_role
     *
     * @mbg.generated Thu Feb 24 17:16:45 CST 2022
     */
    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_index_role
     *
     * @mbg.generated Thu Feb 24 17:16:45 CST 2022
     */
    public boolean isDistinct() {
        return distinct;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_index_role
     *
     * @mbg.generated Thu Feb 24 17:16:45 CST 2022
     */
    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_index_role
     *
     * @mbg.generated Thu Feb 24 17:16:45 CST 2022
     */
    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_index_role
     *
     * @mbg.generated Thu Feb 24 17:16:45 CST 2022
     */
    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_index_role
     *
     * @mbg.generated Thu Feb 24 17:16:45 CST 2022
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
     * This method corresponds to the database table sys_index_role
     *
     * @mbg.generated Thu Feb 24 17:16:45 CST 2022
     */
    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_index_role
     *
     * @mbg.generated Thu Feb 24 17:16:45 CST 2022
     */
    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table sys_index_role
     *
     * @mbg.generated Thu Feb 24 17:16:45 CST 2022
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

        public Criteria andIndexIdIsNull() {
            addCriterion("index_id is null");
            return (Criteria) this;
        }

        public Criteria andIndexIdIsNotNull() {
            addCriterion("index_id is not null");
            return (Criteria) this;
        }

        public Criteria andIndexIdEqualTo(BigDecimal value) {
            addCriterion("index_id =", value, "indexId");
            return (Criteria) this;
        }

        public Criteria andIndexIdNotEqualTo(BigDecimal value) {
            addCriterion("index_id <>", value, "indexId");
            return (Criteria) this;
        }

        public Criteria andIndexIdGreaterThan(BigDecimal value) {
            addCriterion("index_id >", value, "indexId");
            return (Criteria) this;
        }

        public Criteria andIndexIdGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("index_id >=", value, "indexId");
            return (Criteria) this;
        }

        public Criteria andIndexIdLessThan(BigDecimal value) {
            addCriterion("index_id <", value, "indexId");
            return (Criteria) this;
        }

        public Criteria andIndexIdLessThanOrEqualTo(BigDecimal value) {
            addCriterion("index_id <=", value, "indexId");
            return (Criteria) this;
        }

        public Criteria andIndexIdIn(List<BigDecimal> values) {
            addCriterion("index_id in", values, "indexId");
            return (Criteria) this;
        }

        public Criteria andIndexIdNotIn(List<BigDecimal> values) {
            addCriterion("index_id not in", values, "indexId");
            return (Criteria) this;
        }

        public Criteria andIndexIdBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("index_id between", value1, value2, "indexId");
            return (Criteria) this;
        }

        public Criteria andIndexIdNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("index_id not between", value1, value2, "indexId");
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

        public Criteria andCrtAkaIdIsNull() {
            addCriterion("crt_aka_id is null");
            return (Criteria) this;
        }

        public Criteria andCrtAkaIdIsNotNull() {
            addCriterion("crt_aka_id is not null");
            return (Criteria) this;
        }

        public Criteria andCrtAkaIdEqualTo(String value) {
            addCriterion("crt_aka_id =", value, "crtAkaId");
            return (Criteria) this;
        }

        public Criteria andCrtAkaIdNotEqualTo(String value) {
            addCriterion("crt_aka_id <>", value, "crtAkaId");
            return (Criteria) this;
        }

        public Criteria andCrtAkaIdGreaterThan(String value) {
            addCriterion("crt_aka_id >", value, "crtAkaId");
            return (Criteria) this;
        }

        public Criteria andCrtAkaIdGreaterThanOrEqualTo(String value) {
            addCriterion("crt_aka_id >=", value, "crtAkaId");
            return (Criteria) this;
        }

        public Criteria andCrtAkaIdLessThan(String value) {
            addCriterion("crt_aka_id <", value, "crtAkaId");
            return (Criteria) this;
        }

        public Criteria andCrtAkaIdLessThanOrEqualTo(String value) {
            addCriterion("crt_aka_id <=", value, "crtAkaId");
            return (Criteria) this;
        }

        public Criteria andCrtAkaIdLike(String value) {
            addCriterion("crt_aka_id like", value, "crtAkaId");
            return (Criteria) this;
        }

        public Criteria andCrtAkaIdNotLike(String value) {
            addCriterion("crt_aka_id not like", value, "crtAkaId");
            return (Criteria) this;
        }

        public Criteria andCrtAkaIdIn(List<String> values) {
            addCriterion("crt_aka_id in", values, "crtAkaId");
            return (Criteria) this;
        }

        public Criteria andCrtAkaIdNotIn(List<String> values) {
            addCriterion("crt_aka_id not in", values, "crtAkaId");
            return (Criteria) this;
        }

        public Criteria andCrtAkaIdBetween(String value1, String value2) {
            addCriterion("crt_aka_id between", value1, value2, "crtAkaId");
            return (Criteria) this;
        }

        public Criteria andCrtAkaIdNotBetween(String value1, String value2) {
            addCriterion("crt_aka_id not between", value1, value2, "crtAkaId");
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

        public Criteria andEntryAkaIdIsNull() {
            addCriterion("entry_aka_id is null");
            return (Criteria) this;
        }

        public Criteria andEntryAkaIdIsNotNull() {
            addCriterion("entry_aka_id is not null");
            return (Criteria) this;
        }

        public Criteria andEntryAkaIdEqualTo(String value) {
            addCriterion("entry_aka_id =", value, "entryAkaId");
            return (Criteria) this;
        }

        public Criteria andEntryAkaIdNotEqualTo(String value) {
            addCriterion("entry_aka_id <>", value, "entryAkaId");
            return (Criteria) this;
        }

        public Criteria andEntryAkaIdGreaterThan(String value) {
            addCriterion("entry_aka_id >", value, "entryAkaId");
            return (Criteria) this;
        }

        public Criteria andEntryAkaIdGreaterThanOrEqualTo(String value) {
            addCriterion("entry_aka_id >=", value, "entryAkaId");
            return (Criteria) this;
        }

        public Criteria andEntryAkaIdLessThan(String value) {
            addCriterion("entry_aka_id <", value, "entryAkaId");
            return (Criteria) this;
        }

        public Criteria andEntryAkaIdLessThanOrEqualTo(String value) {
            addCriterion("entry_aka_id <=", value, "entryAkaId");
            return (Criteria) this;
        }

        public Criteria andEntryAkaIdLike(String value) {
            addCriterion("entry_aka_id like", value, "entryAkaId");
            return (Criteria) this;
        }

        public Criteria andEntryAkaIdNotLike(String value) {
            addCriterion("entry_aka_id not like", value, "entryAkaId");
            return (Criteria) this;
        }

        public Criteria andEntryAkaIdIn(List<String> values) {
            addCriterion("entry_aka_id in", values, "entryAkaId");
            return (Criteria) this;
        }

        public Criteria andEntryAkaIdNotIn(List<String> values) {
            addCriterion("entry_aka_id not in", values, "entryAkaId");
            return (Criteria) this;
        }

        public Criteria andEntryAkaIdBetween(String value1, String value2) {
            addCriterion("entry_aka_id between", value1, value2, "entryAkaId");
            return (Criteria) this;
        }

        public Criteria andEntryAkaIdNotBetween(String value1, String value2) {
            addCriterion("entry_aka_id not between", value1, value2, "entryAkaId");
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
     * This class corresponds to the database table sys_index_role
     *
     * @mbg.generated do_not_delete_during_merge Thu Feb 24 17:16:45 CST 2022
     */
    public static class Criteria extends GeneratedCriteria {
        protected Criteria() {
            super();
        }
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table sys_index_role
     *
     * @mbg.generated Thu Feb 24 17:16:45 CST 2022
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