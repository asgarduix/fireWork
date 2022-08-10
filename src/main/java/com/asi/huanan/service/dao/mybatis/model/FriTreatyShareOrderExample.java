package com.asi.huanan.service.dao.mybatis.model;

import java.util.ArrayList;
import java.util.List;

public class FriTreatyShareOrderExample {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table fri_treaty_share_order
     *
     * @mbg.generated Thu Oct 28 14:51:39 CST 2021
     */
    protected String orderByClause;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table fri_treaty_share_order
     *
     * @mbg.generated Thu Oct 28 14:51:39 CST 2021
     */
    protected boolean distinct;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table fri_treaty_share_order
     *
     * @mbg.generated Thu Oct 28 14:51:39 CST 2021
     */
    protected List<Criteria> oredCriteria;

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table fri_treaty_share_order
     *
     * @mbg.generated Thu Oct 28 14:51:39 CST 2021
     */
    public FriTreatyShareOrderExample() {
        oredCriteria = new ArrayList<>();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table fri_treaty_share_order
     *
     * @mbg.generated Thu Oct 28 14:51:39 CST 2021
     */
    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table fri_treaty_share_order
     *
     * @mbg.generated Thu Oct 28 14:51:39 CST 2021
     */
    public String getOrderByClause() {
        return orderByClause;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table fri_treaty_share_order
     *
     * @mbg.generated Thu Oct 28 14:51:39 CST 2021
     */
    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table fri_treaty_share_order
     *
     * @mbg.generated Thu Oct 28 14:51:39 CST 2021
     */
    public boolean isDistinct() {
        return distinct;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table fri_treaty_share_order
     *
     * @mbg.generated Thu Oct 28 14:51:39 CST 2021
     */
    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table fri_treaty_share_order
     *
     * @mbg.generated Thu Oct 28 14:51:39 CST 2021
     */
    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table fri_treaty_share_order
     *
     * @mbg.generated Thu Oct 28 14:51:39 CST 2021
     */
    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table fri_treaty_share_order
     *
     * @mbg.generated Thu Oct 28 14:51:39 CST 2021
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
     * This method corresponds to the database table fri_treaty_share_order
     *
     * @mbg.generated Thu Oct 28 14:51:39 CST 2021
     */
    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table fri_treaty_share_order
     *
     * @mbg.generated Thu Oct 28 14:51:39 CST 2021
     */
    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table fri_treaty_share_order
     *
     * @mbg.generated Thu Oct 28 14:51:39 CST 2021
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

        public Criteria andTreatyYearIsNull() {
            addCriterion("treaty_year is null");
            return (Criteria) this;
        }

        public Criteria andTreatyYearIsNotNull() {
            addCriterion("treaty_year is not null");
            return (Criteria) this;
        }

        public Criteria andTreatyYearEqualTo(String value) {
            addCriterion("treaty_year =", value, "treatyYear");
            return (Criteria) this;
        }

        public Criteria andTreatyYearNotEqualTo(String value) {
            addCriterion("treaty_year <>", value, "treatyYear");
            return (Criteria) this;
        }

        public Criteria andTreatyYearGreaterThan(String value) {
            addCriterion("treaty_year >", value, "treatyYear");
            return (Criteria) this;
        }

        public Criteria andTreatyYearGreaterThanOrEqualTo(String value) {
            addCriterion("treaty_year >=", value, "treatyYear");
            return (Criteria) this;
        }

        public Criteria andTreatyYearLessThan(String value) {
            addCriterion("treaty_year <", value, "treatyYear");
            return (Criteria) this;
        }

        public Criteria andTreatyYearLessThanOrEqualTo(String value) {
            addCriterion("treaty_year <=", value, "treatyYear");
            return (Criteria) this;
        }

        public Criteria andTreatyYearLike(String value) {
            addCriterion("treaty_year like", value, "treatyYear");
            return (Criteria) this;
        }

        public Criteria andTreatyYearNotLike(String value) {
            addCriterion("treaty_year not like", value, "treatyYear");
            return (Criteria) this;
        }

        public Criteria andTreatyYearIn(List<String> values) {
            addCriterion("treaty_year in", values, "treatyYear");
            return (Criteria) this;
        }

        public Criteria andTreatyYearNotIn(List<String> values) {
            addCriterion("treaty_year not in", values, "treatyYear");
            return (Criteria) this;
        }

        public Criteria andTreatyYearBetween(String value1, String value2) {
            addCriterion("treaty_year between", value1, value2, "treatyYear");
            return (Criteria) this;
        }

        public Criteria andTreatyYearNotBetween(String value1, String value2) {
            addCriterion("treaty_year not between", value1, value2, "treatyYear");
            return (Criteria) this;
        }

        public Criteria andPolicyTypeIsNull() {
            addCriterion("policy_type is null");
            return (Criteria) this;
        }

        public Criteria andPolicyTypeIsNotNull() {
            addCriterion("policy_type is not null");
            return (Criteria) this;
        }

        public Criteria andPolicyTypeEqualTo(String value) {
            addCriterion("policy_type =", value, "policyType");
            return (Criteria) this;
        }

        public Criteria andPolicyTypeNotEqualTo(String value) {
            addCriterion("policy_type <>", value, "policyType");
            return (Criteria) this;
        }

        public Criteria andPolicyTypeGreaterThan(String value) {
            addCriterion("policy_type >", value, "policyType");
            return (Criteria) this;
        }

        public Criteria andPolicyTypeGreaterThanOrEqualTo(String value) {
            addCriterion("policy_type >=", value, "policyType");
            return (Criteria) this;
        }

        public Criteria andPolicyTypeLessThan(String value) {
            addCriterion("policy_type <", value, "policyType");
            return (Criteria) this;
        }

        public Criteria andPolicyTypeLessThanOrEqualTo(String value) {
            addCriterion("policy_type <=", value, "policyType");
            return (Criteria) this;
        }

        public Criteria andPolicyTypeLike(String value) {
            addCriterion("policy_type like", value, "policyType");
            return (Criteria) this;
        }

        public Criteria andPolicyTypeNotLike(String value) {
            addCriterion("policy_type not like", value, "policyType");
            return (Criteria) this;
        }

        public Criteria andPolicyTypeIn(List<String> values) {
            addCriterion("policy_type in", values, "policyType");
            return (Criteria) this;
        }

        public Criteria andPolicyTypeNotIn(List<String> values) {
            addCriterion("policy_type not in", values, "policyType");
            return (Criteria) this;
        }

        public Criteria andPolicyTypeBetween(String value1, String value2) {
            addCriterion("policy_type between", value1, value2, "policyType");
            return (Criteria) this;
        }

        public Criteria andPolicyTypeNotBetween(String value1, String value2) {
            addCriterion("policy_type not between", value1, value2, "policyType");
            return (Criteria) this;
        }

        public Criteria andShareOrderIsNull() {
            addCriterion("share_order is null");
            return (Criteria) this;
        }

        public Criteria andShareOrderIsNotNull() {
            addCriterion("share_order is not null");
            return (Criteria) this;
        }

        public Criteria andShareOrderEqualTo(Short value) {
            addCriterion("share_order =", value, "shareOrder");
            return (Criteria) this;
        }

        public Criteria andShareOrderNotEqualTo(Short value) {
            addCriterion("share_order <>", value, "shareOrder");
            return (Criteria) this;
        }

        public Criteria andShareOrderGreaterThan(Short value) {
            addCriterion("share_order >", value, "shareOrder");
            return (Criteria) this;
        }

        public Criteria andShareOrderGreaterThanOrEqualTo(Short value) {
            addCriterion("share_order >=", value, "shareOrder");
            return (Criteria) this;
        }

        public Criteria andShareOrderLessThan(Short value) {
            addCriterion("share_order <", value, "shareOrder");
            return (Criteria) this;
        }

        public Criteria andShareOrderLessThanOrEqualTo(Short value) {
            addCriterion("share_order <=", value, "shareOrder");
            return (Criteria) this;
        }

        public Criteria andShareOrderIn(List<Short> values) {
            addCriterion("share_order in", values, "shareOrder");
            return (Criteria) this;
        }

        public Criteria andShareOrderNotIn(List<Short> values) {
            addCriterion("share_order not in", values, "shareOrder");
            return (Criteria) this;
        }

        public Criteria andShareOrderBetween(Short value1, Short value2) {
            addCriterion("share_order between", value1, value2, "shareOrder");
            return (Criteria) this;
        }

        public Criteria andShareOrderNotBetween(Short value1, Short value2) {
            addCriterion("share_order not between", value1, value2, "shareOrder");
            return (Criteria) this;
        }

        public Criteria andTreatyNoIsNull() {
            addCriterion("treaty_no is null");
            return (Criteria) this;
        }

        public Criteria andTreatyNoIsNotNull() {
            addCriterion("treaty_no is not null");
            return (Criteria) this;
        }

        public Criteria andTreatyNoEqualTo(String value) {
            addCriterion("treaty_no =", value, "treatyNo");
            return (Criteria) this;
        }

        public Criteria andTreatyNoNotEqualTo(String value) {
            addCriterion("treaty_no <>", value, "treatyNo");
            return (Criteria) this;
        }

        public Criteria andTreatyNoGreaterThan(String value) {
            addCriterion("treaty_no >", value, "treatyNo");
            return (Criteria) this;
        }

        public Criteria andTreatyNoGreaterThanOrEqualTo(String value) {
            addCriterion("treaty_no >=", value, "treatyNo");
            return (Criteria) this;
        }

        public Criteria andTreatyNoLessThan(String value) {
            addCriterion("treaty_no <", value, "treatyNo");
            return (Criteria) this;
        }

        public Criteria andTreatyNoLessThanOrEqualTo(String value) {
            addCriterion("treaty_no <=", value, "treatyNo");
            return (Criteria) this;
        }

        public Criteria andTreatyNoLike(String value) {
            addCriterion("treaty_no like", value, "treatyNo");
            return (Criteria) this;
        }

        public Criteria andTreatyNoNotLike(String value) {
            addCriterion("treaty_no not like", value, "treatyNo");
            return (Criteria) this;
        }

        public Criteria andTreatyNoIn(List<String> values) {
            addCriterion("treaty_no in", values, "treatyNo");
            return (Criteria) this;
        }

        public Criteria andTreatyNoNotIn(List<String> values) {
            addCriterion("treaty_no not in", values, "treatyNo");
            return (Criteria) this;
        }

        public Criteria andTreatyNoBetween(String value1, String value2) {
            addCriterion("treaty_no between", value1, value2, "treatyNo");
            return (Criteria) this;
        }

        public Criteria andTreatyNoNotBetween(String value1, String value2) {
            addCriterion("treaty_no not between", value1, value2, "treatyNo");
            return (Criteria) this;
        }
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table fri_treaty_share_order
     *
     * @mbg.generated do_not_delete_during_merge Thu Oct 28 14:51:39 CST 2021
     */
    public static class Criteria extends GeneratedCriteria {
        protected Criteria() {
            super();
        }
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table fri_treaty_share_order
     *
     * @mbg.generated Thu Oct 28 14:51:39 CST 2021
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