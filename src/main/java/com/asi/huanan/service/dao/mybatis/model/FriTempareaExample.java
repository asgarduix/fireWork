package com.asi.huanan.service.dao.mybatis.model;

import java.util.ArrayList;
import java.util.List;

public class FriTempareaExample {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table fri_temparea
     *
     * @mbg.generated Thu Nov 25 14:21:33 CST 2021
     */
    protected String orderByClause;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table fri_temparea
     *
     * @mbg.generated Thu Nov 25 14:21:33 CST 2021
     */
    protected boolean distinct;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table fri_temparea
     *
     * @mbg.generated Thu Nov 25 14:21:33 CST 2021
     */
    protected List<Criteria> oredCriteria;

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table fri_temparea
     *
     * @mbg.generated Thu Nov 25 14:21:33 CST 2021
     */
    public FriTempareaExample() {
        oredCriteria = new ArrayList<>();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table fri_temparea
     *
     * @mbg.generated Thu Nov 25 14:21:33 CST 2021
     */
    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table fri_temparea
     *
     * @mbg.generated Thu Nov 25 14:21:33 CST 2021
     */
    public String getOrderByClause() {
        return orderByClause;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table fri_temparea
     *
     * @mbg.generated Thu Nov 25 14:21:33 CST 2021
     */
    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table fri_temparea
     *
     * @mbg.generated Thu Nov 25 14:21:33 CST 2021
     */
    public boolean isDistinct() {
        return distinct;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table fri_temparea
     *
     * @mbg.generated Thu Nov 25 14:21:33 CST 2021
     */
    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table fri_temparea
     *
     * @mbg.generated Thu Nov 25 14:21:33 CST 2021
     */
    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table fri_temparea
     *
     * @mbg.generated Thu Nov 25 14:21:33 CST 2021
     */
    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table fri_temparea
     *
     * @mbg.generated Thu Nov 25 14:21:33 CST 2021
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
     * This method corresponds to the database table fri_temparea
     *
     * @mbg.generated Thu Nov 25 14:21:33 CST 2021
     */
    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table fri_temparea
     *
     * @mbg.generated Thu Nov 25 14:21:33 CST 2021
     */
    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table fri_temparea
     *
     * @mbg.generated Thu Nov 25 14:21:33 CST 2021
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

        public Criteria andAreaCodeIsNull() {
            addCriterion("area_code is null");
            return (Criteria) this;
        }

        public Criteria andAreaCodeIsNotNull() {
            addCriterion("area_code is not null");
            return (Criteria) this;
        }

        public Criteria andAreaCodeEqualTo(String value) {
            addCriterion("area_code =", value, "areaCode");
            return (Criteria) this;
        }

        public Criteria andAreaCodeNotEqualTo(String value) {
            addCriterion("area_code <>", value, "areaCode");
            return (Criteria) this;
        }

        public Criteria andAreaCodeGreaterThan(String value) {
            addCriterion("area_code >", value, "areaCode");
            return (Criteria) this;
        }

        public Criteria andAreaCodeGreaterThanOrEqualTo(String value) {
            addCriterion("area_code >=", value, "areaCode");
            return (Criteria) this;
        }

        public Criteria andAreaCodeLessThan(String value) {
            addCriterion("area_code <", value, "areaCode");
            return (Criteria) this;
        }

        public Criteria andAreaCodeLessThanOrEqualTo(String value) {
            addCriterion("area_code <=", value, "areaCode");
            return (Criteria) this;
        }

        public Criteria andAreaCodeLike(String value) {
            addCriterion("area_code like", value, "areaCode");
            return (Criteria) this;
        }

        public Criteria andAreaCodeNotLike(String value) {
            addCriterion("area_code not like", value, "areaCode");
            return (Criteria) this;
        }

        public Criteria andAreaCodeIn(List<String> values) {
            addCriterion("area_code in", values, "areaCode");
            return (Criteria) this;
        }

        public Criteria andAreaCodeNotIn(List<String> values) {
            addCriterion("area_code not in", values, "areaCode");
            return (Criteria) this;
        }

        public Criteria andAreaCodeBetween(String value1, String value2) {
            addCriterion("area_code between", value1, value2, "areaCode");
            return (Criteria) this;
        }

        public Criteria andAreaCodeNotBetween(String value1, String value2) {
            addCriterion("area_code not between", value1, value2, "areaCode");
            return (Criteria) this;
        }

        public Criteria andProcCountIsNull() {
            addCriterion("proc_count is null");
            return (Criteria) this;
        }

        public Criteria andProcCountIsNotNull() {
            addCriterion("proc_count is not null");
            return (Criteria) this;
        }

        public Criteria andProcCountEqualTo(Short value) {
            addCriterion("proc_count =", value, "procCount");
            return (Criteria) this;
        }

        public Criteria andProcCountNotEqualTo(Short value) {
            addCriterion("proc_count <>", value, "procCount");
            return (Criteria) this;
        }

        public Criteria andProcCountGreaterThan(Short value) {
            addCriterion("proc_count >", value, "procCount");
            return (Criteria) this;
        }

        public Criteria andProcCountGreaterThanOrEqualTo(Short value) {
            addCriterion("proc_count >=", value, "procCount");
            return (Criteria) this;
        }

        public Criteria andProcCountLessThan(Short value) {
            addCriterion("proc_count <", value, "procCount");
            return (Criteria) this;
        }

        public Criteria andProcCountLessThanOrEqualTo(Short value) {
            addCriterion("proc_count <=", value, "procCount");
            return (Criteria) this;
        }

        public Criteria andProcCountIn(List<Short> values) {
            addCriterion("proc_count in", values, "procCount");
            return (Criteria) this;
        }

        public Criteria andProcCountNotIn(List<Short> values) {
            addCriterion("proc_count not in", values, "procCount");
            return (Criteria) this;
        }

        public Criteria andProcCountBetween(Short value1, Short value2) {
            addCriterion("proc_count between", value1, value2, "procCount");
            return (Criteria) this;
        }

        public Criteria andProcCountNotBetween(Short value1, Short value2) {
            addCriterion("proc_count not between", value1, value2, "procCount");
            return (Criteria) this;
        }
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table fri_temparea
     *
     * @mbg.generated do_not_delete_during_merge Thu Nov 25 14:21:33 CST 2021
     */
    public static class Criteria extends GeneratedCriteria {
        protected Criteria() {
            super();
        }
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table fri_temparea
     *
     * @mbg.generated Thu Nov 25 14:21:33 CST 2021
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