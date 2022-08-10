package com.asi.huanan.service.dao.mybatis.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class FriComCreditExample {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table fri_com_credit
     *
     * @mbg.generated Sun Sep 26 17:31:48 CST 2021
     */
    protected String orderByClause;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table fri_com_credit
     *
     * @mbg.generated Sun Sep 26 17:31:48 CST 2021
     */
    protected boolean distinct;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table fri_com_credit
     *
     * @mbg.generated Sun Sep 26 17:31:48 CST 2021
     */
    protected List<Criteria> oredCriteria;

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table fri_com_credit
     *
     * @mbg.generated Sun Sep 26 17:31:48 CST 2021
     */
    public FriComCreditExample() {
        oredCriteria = new ArrayList<>();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table fri_com_credit
     *
     * @mbg.generated Sun Sep 26 17:31:48 CST 2021
     */
    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table fri_com_credit
     *
     * @mbg.generated Sun Sep 26 17:31:48 CST 2021
     */
    public String getOrderByClause() {
        return orderByClause;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table fri_com_credit
     *
     * @mbg.generated Sun Sep 26 17:31:48 CST 2021
     */
    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table fri_com_credit
     *
     * @mbg.generated Sun Sep 26 17:31:48 CST 2021
     */
    public boolean isDistinct() {
        return distinct;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table fri_com_credit
     *
     * @mbg.generated Sun Sep 26 17:31:48 CST 2021
     */
    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table fri_com_credit
     *
     * @mbg.generated Sun Sep 26 17:31:48 CST 2021
     */
    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table fri_com_credit
     *
     * @mbg.generated Sun Sep 26 17:31:48 CST 2021
     */
    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table fri_com_credit
     *
     * @mbg.generated Sun Sep 26 17:31:48 CST 2021
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
     * This method corresponds to the database table fri_com_credit
     *
     * @mbg.generated Sun Sep 26 17:31:48 CST 2021
     */
    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table fri_com_credit
     *
     * @mbg.generated Sun Sep 26 17:31:48 CST 2021
     */
    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table fri_com_credit
     *
     * @mbg.generated Sun Sep 26 17:31:48 CST 2021
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

        public Criteria andRinComIdIsNull() {
            addCriterion("rin_com_id is null");
            return (Criteria) this;
        }

        public Criteria andRinComIdIsNotNull() {
            addCriterion("rin_com_id is not null");
            return (Criteria) this;
        }

        public Criteria andRinComIdEqualTo(String value) {
            addCriterion("rin_com_id =", value, "rinComId");
            return (Criteria) this;
        }

        public Criteria andRinComIdNotEqualTo(String value) {
            addCriterion("rin_com_id <>", value, "rinComId");
            return (Criteria) this;
        }

        public Criteria andRinComIdGreaterThan(String value) {
            addCriterion("rin_com_id >", value, "rinComId");
            return (Criteria) this;
        }

        public Criteria andRinComIdGreaterThanOrEqualTo(String value) {
            addCriterion("rin_com_id >=", value, "rinComId");
            return (Criteria) this;
        }

        public Criteria andRinComIdLessThan(String value) {
            addCriterion("rin_com_id <", value, "rinComId");
            return (Criteria) this;
        }

        public Criteria andRinComIdLessThanOrEqualTo(String value) {
            addCriterion("rin_com_id <=", value, "rinComId");
            return (Criteria) this;
        }

        public Criteria andRinComIdLike(String value) {
            addCriterion("rin_com_id like", value, "rinComId");
            return (Criteria) this;
        }

        public Criteria andRinComIdNotLike(String value) {
            addCriterion("rin_com_id not like", value, "rinComId");
            return (Criteria) this;
        }

        public Criteria andRinComIdIn(List<String> values) {
            addCriterion("rin_com_id in", values, "rinComId");
            return (Criteria) this;
        }

        public Criteria andRinComIdNotIn(List<String> values) {
            addCriterion("rin_com_id not in", values, "rinComId");
            return (Criteria) this;
        }

        public Criteria andRinComIdBetween(String value1, String value2) {
            addCriterion("rin_com_id between", value1, value2, "rinComId");
            return (Criteria) this;
        }

        public Criteria andRinComIdNotBetween(String value1, String value2) {
            addCriterion("rin_com_id not between", value1, value2, "rinComId");
            return (Criteria) this;
        }

        public Criteria andCreditOrganIsNull() {
            addCriterion("credit_organ is null");
            return (Criteria) this;
        }

        public Criteria andCreditOrganIsNotNull() {
            addCriterion("credit_organ is not null");
            return (Criteria) this;
        }

        public Criteria andCreditOrganEqualTo(String value) {
            addCriterion("credit_organ =", value, "creditOrgan");
            return (Criteria) this;
        }

        public Criteria andCreditOrganNotEqualTo(String value) {
            addCriterion("credit_organ <>", value, "creditOrgan");
            return (Criteria) this;
        }

        public Criteria andCreditOrganGreaterThan(String value) {
            addCriterion("credit_organ >", value, "creditOrgan");
            return (Criteria) this;
        }

        public Criteria andCreditOrganGreaterThanOrEqualTo(String value) {
            addCriterion("credit_organ >=", value, "creditOrgan");
            return (Criteria) this;
        }

        public Criteria andCreditOrganLessThan(String value) {
            addCriterion("credit_organ <", value, "creditOrgan");
            return (Criteria) this;
        }

        public Criteria andCreditOrganLessThanOrEqualTo(String value) {
            addCriterion("credit_organ <=", value, "creditOrgan");
            return (Criteria) this;
        }

        public Criteria andCreditOrganLike(String value) {
            addCriterion("credit_organ like", value, "creditOrgan");
            return (Criteria) this;
        }

        public Criteria andCreditOrganNotLike(String value) {
            addCriterion("credit_organ not like", value, "creditOrgan");
            return (Criteria) this;
        }

        public Criteria andCreditOrganIn(List<String> values) {
            addCriterion("credit_organ in", values, "creditOrgan");
            return (Criteria) this;
        }

        public Criteria andCreditOrganNotIn(List<String> values) {
            addCriterion("credit_organ not in", values, "creditOrgan");
            return (Criteria) this;
        }

        public Criteria andCreditOrganBetween(String value1, String value2) {
            addCriterion("credit_organ between", value1, value2, "creditOrgan");
            return (Criteria) this;
        }

        public Criteria andCreditOrganNotBetween(String value1, String value2) {
            addCriterion("credit_organ not between", value1, value2, "creditOrgan");
            return (Criteria) this;
        }

        public Criteria andCreditDateIsNull() {
            addCriterion("credit_date is null");
            return (Criteria) this;
        }

        public Criteria andCreditDateIsNotNull() {
            addCriterion("credit_date is not null");
            return (Criteria) this;
        }

        public Criteria andCreditDateEqualTo(Date value) {
            addCriterion("credit_date =", value, "creditDate");
            return (Criteria) this;
        }

        public Criteria andCreditDateNotEqualTo(Date value) {
            addCriterion("credit_date <>", value, "creditDate");
            return (Criteria) this;
        }

        public Criteria andCreditDateGreaterThan(Date value) {
            addCriterion("credit_date >", value, "creditDate");
            return (Criteria) this;
        }

        public Criteria andCreditDateGreaterThanOrEqualTo(Date value) {
            addCriterion("credit_date >=", value, "creditDate");
            return (Criteria) this;
        }

        public Criteria andCreditDateLessThan(Date value) {
            addCriterion("credit_date <", value, "creditDate");
            return (Criteria) this;
        }

        public Criteria andCreditDateLessThanOrEqualTo(Date value) {
            addCriterion("credit_date <=", value, "creditDate");
            return (Criteria) this;
        }

        public Criteria andCreditDateIn(List<Date> values) {
            addCriterion("credit_date in", values, "creditDate");
            return (Criteria) this;
        }

        public Criteria andCreditDateNotIn(List<Date> values) {
            addCriterion("credit_date not in", values, "creditDate");
            return (Criteria) this;
        }

        public Criteria andCreditDateBetween(Date value1, Date value2) {
            addCriterion("credit_date between", value1, value2, "creditDate");
            return (Criteria) this;
        }

        public Criteria andCreditDateNotBetween(Date value1, Date value2) {
            addCriterion("credit_date not between", value1, value2, "creditDate");
            return (Criteria) this;
        }

        public Criteria andCreditLevelIsNull() {
            addCriterion("credit_level is null");
            return (Criteria) this;
        }

        public Criteria andCreditLevelIsNotNull() {
            addCriterion("credit_level is not null");
            return (Criteria) this;
        }

        public Criteria andCreditLevelEqualTo(String value) {
            addCriterion("credit_level =", value, "creditLevel");
            return (Criteria) this;
        }

        public Criteria andCreditLevelNotEqualTo(String value) {
            addCriterion("credit_level <>", value, "creditLevel");
            return (Criteria) this;
        }

        public Criteria andCreditLevelGreaterThan(String value) {
            addCriterion("credit_level >", value, "creditLevel");
            return (Criteria) this;
        }

        public Criteria andCreditLevelGreaterThanOrEqualTo(String value) {
            addCriterion("credit_level >=", value, "creditLevel");
            return (Criteria) this;
        }

        public Criteria andCreditLevelLessThan(String value) {
            addCriterion("credit_level <", value, "creditLevel");
            return (Criteria) this;
        }

        public Criteria andCreditLevelLessThanOrEqualTo(String value) {
            addCriterion("credit_level <=", value, "creditLevel");
            return (Criteria) this;
        }

        public Criteria andCreditLevelLike(String value) {
            addCriterion("credit_level like", value, "creditLevel");
            return (Criteria) this;
        }

        public Criteria andCreditLevelNotLike(String value) {
            addCriterion("credit_level not like", value, "creditLevel");
            return (Criteria) this;
        }

        public Criteria andCreditLevelIn(List<String> values) {
            addCriterion("credit_level in", values, "creditLevel");
            return (Criteria) this;
        }

        public Criteria andCreditLevelNotIn(List<String> values) {
            addCriterion("credit_level not in", values, "creditLevel");
            return (Criteria) this;
        }

        public Criteria andCreditLevelBetween(String value1, String value2) {
            addCriterion("credit_level between", value1, value2, "creditLevel");
            return (Criteria) this;
        }

        public Criteria andCreditLevelNotBetween(String value1, String value2) {
            addCriterion("credit_level not between", value1, value2, "creditLevel");
            return (Criteria) this;
        }
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table fri_com_credit
     *
     * @mbg.generated do_not_delete_during_merge Sun Sep 26 17:31:48 CST 2021
     */
    public static class Criteria extends GeneratedCriteria {
        protected Criteria() {
            super();
        }
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table fri_com_credit
     *
     * @mbg.generated Sun Sep 26 17:31:48 CST 2021
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