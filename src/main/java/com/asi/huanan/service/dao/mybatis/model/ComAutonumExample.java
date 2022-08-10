package com.asi.huanan.service.dao.mybatis.model;

import java.util.ArrayList;
import java.util.List;

public class ComAutonumExample {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table com_autonum
     *
     * @mbg.generated Wed Nov 24 14:30:27 CST 2021
     */
    protected String orderByClause;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table com_autonum
     *
     * @mbg.generated Wed Nov 24 14:30:27 CST 2021
     */
    protected boolean distinct;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table com_autonum
     *
     * @mbg.generated Wed Nov 24 14:30:27 CST 2021
     */
    protected List<Criteria> oredCriteria;

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table com_autonum
     *
     * @mbg.generated Wed Nov 24 14:30:27 CST 2021
     */
    public ComAutonumExample() {
        oredCriteria = new ArrayList<>();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table com_autonum
     *
     * @mbg.generated Wed Nov 24 14:30:27 CST 2021
     */
    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table com_autonum
     *
     * @mbg.generated Wed Nov 24 14:30:27 CST 2021
     */
    public String getOrderByClause() {
        return orderByClause;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table com_autonum
     *
     * @mbg.generated Wed Nov 24 14:30:27 CST 2021
     */
    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table com_autonum
     *
     * @mbg.generated Wed Nov 24 14:30:27 CST 2021
     */
    public boolean isDistinct() {
        return distinct;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table com_autonum
     *
     * @mbg.generated Wed Nov 24 14:30:27 CST 2021
     */
    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table com_autonum
     *
     * @mbg.generated Wed Nov 24 14:30:27 CST 2021
     */
    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table com_autonum
     *
     * @mbg.generated Wed Nov 24 14:30:27 CST 2021
     */
    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table com_autonum
     *
     * @mbg.generated Wed Nov 24 14:30:27 CST 2021
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
     * This method corresponds to the database table com_autonum
     *
     * @mbg.generated Wed Nov 24 14:30:27 CST 2021
     */
    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table com_autonum
     *
     * @mbg.generated Wed Nov 24 14:30:27 CST 2021
     */
    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table com_autonum
     *
     * @mbg.generated Wed Nov 24 14:30:27 CST 2021
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

        public Criteria andCompanyCodeIsNull() {
            addCriterion("company_code is null");
            return (Criteria) this;
        }

        public Criteria andCompanyCodeIsNotNull() {
            addCriterion("company_code is not null");
            return (Criteria) this;
        }

        public Criteria andCompanyCodeEqualTo(String value) {
            addCriterion("company_code =", value, "companyCode");
            return (Criteria) this;
        }

        public Criteria andCompanyCodeNotEqualTo(String value) {
            addCriterion("company_code <>", value, "companyCode");
            return (Criteria) this;
        }

        public Criteria andCompanyCodeGreaterThan(String value) {
            addCriterion("company_code >", value, "companyCode");
            return (Criteria) this;
        }

        public Criteria andCompanyCodeGreaterThanOrEqualTo(String value) {
            addCriterion("company_code >=", value, "companyCode");
            return (Criteria) this;
        }

        public Criteria andCompanyCodeLessThan(String value) {
            addCriterion("company_code <", value, "companyCode");
            return (Criteria) this;
        }

        public Criteria andCompanyCodeLessThanOrEqualTo(String value) {
            addCriterion("company_code <=", value, "companyCode");
            return (Criteria) this;
        }

        public Criteria andCompanyCodeLike(String value) {
            addCriterion("company_code like", value, "companyCode");
            return (Criteria) this;
        }

        public Criteria andCompanyCodeNotLike(String value) {
            addCriterion("company_code not like", value, "companyCode");
            return (Criteria) this;
        }

        public Criteria andCompanyCodeIn(List<String> values) {
            addCriterion("company_code in", values, "companyCode");
            return (Criteria) this;
        }

        public Criteria andCompanyCodeNotIn(List<String> values) {
            addCriterion("company_code not in", values, "companyCode");
            return (Criteria) this;
        }

        public Criteria andCompanyCodeBetween(String value1, String value2) {
            addCriterion("company_code between", value1, value2, "companyCode");
            return (Criteria) this;
        }

        public Criteria andCompanyCodeNotBetween(String value1, String value2) {
            addCriterion("company_code not between", value1, value2, "companyCode");
            return (Criteria) this;
        }

        public Criteria andFunctionIdIsNull() {
            addCriterion("function_id is null");
            return (Criteria) this;
        }

        public Criteria andFunctionIdIsNotNull() {
            addCriterion("function_id is not null");
            return (Criteria) this;
        }

        public Criteria andFunctionIdEqualTo(String value) {
            addCriterion("function_id =", value, "functionId");
            return (Criteria) this;
        }

        public Criteria andFunctionIdNotEqualTo(String value) {
            addCriterion("function_id <>", value, "functionId");
            return (Criteria) this;
        }

        public Criteria andFunctionIdGreaterThan(String value) {
            addCriterion("function_id >", value, "functionId");
            return (Criteria) this;
        }

        public Criteria andFunctionIdGreaterThanOrEqualTo(String value) {
            addCriterion("function_id >=", value, "functionId");
            return (Criteria) this;
        }

        public Criteria andFunctionIdLessThan(String value) {
            addCriterion("function_id <", value, "functionId");
            return (Criteria) this;
        }

        public Criteria andFunctionIdLessThanOrEqualTo(String value) {
            addCriterion("function_id <=", value, "functionId");
            return (Criteria) this;
        }

        public Criteria andFunctionIdLike(String value) {
            addCriterion("function_id like", value, "functionId");
            return (Criteria) this;
        }

        public Criteria andFunctionIdNotLike(String value) {
            addCriterion("function_id not like", value, "functionId");
            return (Criteria) this;
        }

        public Criteria andFunctionIdIn(List<String> values) {
            addCriterion("function_id in", values, "functionId");
            return (Criteria) this;
        }

        public Criteria andFunctionIdNotIn(List<String> values) {
            addCriterion("function_id not in", values, "functionId");
            return (Criteria) this;
        }

        public Criteria andFunctionIdBetween(String value1, String value2) {
            addCriterion("function_id between", value1, value2, "functionId");
            return (Criteria) this;
        }

        public Criteria andFunctionIdNotBetween(String value1, String value2) {
            addCriterion("function_id not between", value1, value2, "functionId");
            return (Criteria) this;
        }

        public Criteria andOperateTypeIsNull() {
            addCriterion("operate_type is null");
            return (Criteria) this;
        }

        public Criteria andOperateTypeIsNotNull() {
            addCriterion("operate_type is not null");
            return (Criteria) this;
        }

        public Criteria andOperateTypeEqualTo(String value) {
            addCriterion("operate_type =", value, "operateType");
            return (Criteria) this;
        }

        public Criteria andOperateTypeNotEqualTo(String value) {
            addCriterion("operate_type <>", value, "operateType");
            return (Criteria) this;
        }

        public Criteria andOperateTypeGreaterThan(String value) {
            addCriterion("operate_type >", value, "operateType");
            return (Criteria) this;
        }

        public Criteria andOperateTypeGreaterThanOrEqualTo(String value) {
            addCriterion("operate_type >=", value, "operateType");
            return (Criteria) this;
        }

        public Criteria andOperateTypeLessThan(String value) {
            addCriterion("operate_type <", value, "operateType");
            return (Criteria) this;
        }

        public Criteria andOperateTypeLessThanOrEqualTo(String value) {
            addCriterion("operate_type <=", value, "operateType");
            return (Criteria) this;
        }

        public Criteria andOperateTypeLike(String value) {
            addCriterion("operate_type like", value, "operateType");
            return (Criteria) this;
        }

        public Criteria andOperateTypeNotLike(String value) {
            addCriterion("operate_type not like", value, "operateType");
            return (Criteria) this;
        }

        public Criteria andOperateTypeIn(List<String> values) {
            addCriterion("operate_type in", values, "operateType");
            return (Criteria) this;
        }

        public Criteria andOperateTypeNotIn(List<String> values) {
            addCriterion("operate_type not in", values, "operateType");
            return (Criteria) this;
        }

        public Criteria andOperateTypeBetween(String value1, String value2) {
            addCriterion("operate_type between", value1, value2, "operateType");
            return (Criteria) this;
        }

        public Criteria andOperateTypeNotBetween(String value1, String value2) {
            addCriterion("operate_type not between", value1, value2, "operateType");
            return (Criteria) this;
        }

        public Criteria andNumberCodeIsNull() {
            addCriterion("number_code is null");
            return (Criteria) this;
        }

        public Criteria andNumberCodeIsNotNull() {
            addCriterion("number_code is not null");
            return (Criteria) this;
        }

        public Criteria andNumberCodeEqualTo(String value) {
            addCriterion("number_code =", value, "numberCode");
            return (Criteria) this;
        }

        public Criteria andNumberCodeNotEqualTo(String value) {
            addCriterion("number_code <>", value, "numberCode");
            return (Criteria) this;
        }

        public Criteria andNumberCodeGreaterThan(String value) {
            addCriterion("number_code >", value, "numberCode");
            return (Criteria) this;
        }

        public Criteria andNumberCodeGreaterThanOrEqualTo(String value) {
            addCriterion("number_code >=", value, "numberCode");
            return (Criteria) this;
        }

        public Criteria andNumberCodeLessThan(String value) {
            addCriterion("number_code <", value, "numberCode");
            return (Criteria) this;
        }

        public Criteria andNumberCodeLessThanOrEqualTo(String value) {
            addCriterion("number_code <=", value, "numberCode");
            return (Criteria) this;
        }

        public Criteria andNumberCodeLike(String value) {
            addCriterion("number_code like", value, "numberCode");
            return (Criteria) this;
        }

        public Criteria andNumberCodeNotLike(String value) {
            addCriterion("number_code not like", value, "numberCode");
            return (Criteria) this;
        }

        public Criteria andNumberCodeIn(List<String> values) {
            addCriterion("number_code in", values, "numberCode");
            return (Criteria) this;
        }

        public Criteria andNumberCodeNotIn(List<String> values) {
            addCriterion("number_code not in", values, "numberCode");
            return (Criteria) this;
        }

        public Criteria andNumberCodeBetween(String value1, String value2) {
            addCriterion("number_code between", value1, value2, "numberCode");
            return (Criteria) this;
        }

        public Criteria andNumberCodeNotBetween(String value1, String value2) {
            addCriterion("number_code not between", value1, value2, "numberCode");
            return (Criteria) this;
        }

        public Criteria andNumYearIsNull() {
            addCriterion("num_year is null");
            return (Criteria) this;
        }

        public Criteria andNumYearIsNotNull() {
            addCriterion("num_year is not null");
            return (Criteria) this;
        }

        public Criteria andNumYearEqualTo(String value) {
            addCriterion("num_year =", value, "numYear");
            return (Criteria) this;
        }

        public Criteria andNumYearNotEqualTo(String value) {
            addCriterion("num_year <>", value, "numYear");
            return (Criteria) this;
        }

        public Criteria andNumYearGreaterThan(String value) {
            addCriterion("num_year >", value, "numYear");
            return (Criteria) this;
        }

        public Criteria andNumYearGreaterThanOrEqualTo(String value) {
            addCriterion("num_year >=", value, "numYear");
            return (Criteria) this;
        }

        public Criteria andNumYearLessThan(String value) {
            addCriterion("num_year <", value, "numYear");
            return (Criteria) this;
        }

        public Criteria andNumYearLessThanOrEqualTo(String value) {
            addCriterion("num_year <=", value, "numYear");
            return (Criteria) this;
        }

        public Criteria andNumYearLike(String value) {
            addCriterion("num_year like", value, "numYear");
            return (Criteria) this;
        }

        public Criteria andNumYearNotLike(String value) {
            addCriterion("num_year not like", value, "numYear");
            return (Criteria) this;
        }

        public Criteria andNumYearIn(List<String> values) {
            addCriterion("num_year in", values, "numYear");
            return (Criteria) this;
        }

        public Criteria andNumYearNotIn(List<String> values) {
            addCriterion("num_year not in", values, "numYear");
            return (Criteria) this;
        }

        public Criteria andNumYearBetween(String value1, String value2) {
            addCriterion("num_year between", value1, value2, "numYear");
            return (Criteria) this;
        }

        public Criteria andNumYearNotBetween(String value1, String value2) {
            addCriterion("num_year not between", value1, value2, "numYear");
            return (Criteria) this;
        }

        public Criteria andNumMonthIsNull() {
            addCriterion("num_month is null");
            return (Criteria) this;
        }

        public Criteria andNumMonthIsNotNull() {
            addCriterion("num_month is not null");
            return (Criteria) this;
        }

        public Criteria andNumMonthEqualTo(String value) {
            addCriterion("num_month =", value, "numMonth");
            return (Criteria) this;
        }

        public Criteria andNumMonthNotEqualTo(String value) {
            addCriterion("num_month <>", value, "numMonth");
            return (Criteria) this;
        }

        public Criteria andNumMonthGreaterThan(String value) {
            addCriterion("num_month >", value, "numMonth");
            return (Criteria) this;
        }

        public Criteria andNumMonthGreaterThanOrEqualTo(String value) {
            addCriterion("num_month >=", value, "numMonth");
            return (Criteria) this;
        }

        public Criteria andNumMonthLessThan(String value) {
            addCriterion("num_month <", value, "numMonth");
            return (Criteria) this;
        }

        public Criteria andNumMonthLessThanOrEqualTo(String value) {
            addCriterion("num_month <=", value, "numMonth");
            return (Criteria) this;
        }

        public Criteria andNumMonthLike(String value) {
            addCriterion("num_month like", value, "numMonth");
            return (Criteria) this;
        }

        public Criteria andNumMonthNotLike(String value) {
            addCriterion("num_month not like", value, "numMonth");
            return (Criteria) this;
        }

        public Criteria andNumMonthIn(List<String> values) {
            addCriterion("num_month in", values, "numMonth");
            return (Criteria) this;
        }

        public Criteria andNumMonthNotIn(List<String> values) {
            addCriterion("num_month not in", values, "numMonth");
            return (Criteria) this;
        }

        public Criteria andNumMonthBetween(String value1, String value2) {
            addCriterion("num_month between", value1, value2, "numMonth");
            return (Criteria) this;
        }

        public Criteria andNumMonthNotBetween(String value1, String value2) {
            addCriterion("num_month not between", value1, value2, "numMonth");
            return (Criteria) this;
        }

        public Criteria andNumDayIsNull() {
            addCriterion("num_day is null");
            return (Criteria) this;
        }

        public Criteria andNumDayIsNotNull() {
            addCriterion("num_day is not null");
            return (Criteria) this;
        }

        public Criteria andNumDayEqualTo(String value) {
            addCriterion("num_day =", value, "numDay");
            return (Criteria) this;
        }

        public Criteria andNumDayNotEqualTo(String value) {
            addCriterion("num_day <>", value, "numDay");
            return (Criteria) this;
        }

        public Criteria andNumDayGreaterThan(String value) {
            addCriterion("num_day >", value, "numDay");
            return (Criteria) this;
        }

        public Criteria andNumDayGreaterThanOrEqualTo(String value) {
            addCriterion("num_day >=", value, "numDay");
            return (Criteria) this;
        }

        public Criteria andNumDayLessThan(String value) {
            addCriterion("num_day <", value, "numDay");
            return (Criteria) this;
        }

        public Criteria andNumDayLessThanOrEqualTo(String value) {
            addCriterion("num_day <=", value, "numDay");
            return (Criteria) this;
        }

        public Criteria andNumDayLike(String value) {
            addCriterion("num_day like", value, "numDay");
            return (Criteria) this;
        }

        public Criteria andNumDayNotLike(String value) {
            addCriterion("num_day not like", value, "numDay");
            return (Criteria) this;
        }

        public Criteria andNumDayIn(List<String> values) {
            addCriterion("num_day in", values, "numDay");
            return (Criteria) this;
        }

        public Criteria andNumDayNotIn(List<String> values) {
            addCriterion("num_day not in", values, "numDay");
            return (Criteria) this;
        }

        public Criteria andNumDayBetween(String value1, String value2) {
            addCriterion("num_day between", value1, value2, "numDay");
            return (Criteria) this;
        }

        public Criteria andNumDayNotBetween(String value1, String value2) {
            addCriterion("num_day not between", value1, value2, "numDay");
            return (Criteria) this;
        }

        public Criteria andSerialIsNull() {
            addCriterion("serial is null");
            return (Criteria) this;
        }

        public Criteria andSerialIsNotNull() {
            addCriterion("serial is not null");
            return (Criteria) this;
        }

        public Criteria andSerialEqualTo(Integer value) {
            addCriterion("serial =", value, "serial");
            return (Criteria) this;
        }

        public Criteria andSerialNotEqualTo(Integer value) {
            addCriterion("serial <>", value, "serial");
            return (Criteria) this;
        }

        public Criteria andSerialGreaterThan(Integer value) {
            addCriterion("serial >", value, "serial");
            return (Criteria) this;
        }

        public Criteria andSerialGreaterThanOrEqualTo(Integer value) {
            addCriterion("serial >=", value, "serial");
            return (Criteria) this;
        }

        public Criteria andSerialLessThan(Integer value) {
            addCriterion("serial <", value, "serial");
            return (Criteria) this;
        }

        public Criteria andSerialLessThanOrEqualTo(Integer value) {
            addCriterion("serial <=", value, "serial");
            return (Criteria) this;
        }

        public Criteria andSerialIn(List<Integer> values) {
            addCriterion("serial in", values, "serial");
            return (Criteria) this;
        }

        public Criteria andSerialNotIn(List<Integer> values) {
            addCriterion("serial not in", values, "serial");
            return (Criteria) this;
        }

        public Criteria andSerialBetween(Integer value1, Integer value2) {
            addCriterion("serial between", value1, value2, "serial");
            return (Criteria) this;
        }

        public Criteria andSerialNotBetween(Integer value1, Integer value2) {
            addCriterion("serial not between", value1, value2, "serial");
            return (Criteria) this;
        }
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table com_autonum
     *
     * @mbg.generated do_not_delete_during_merge Wed Nov 24 14:30:27 CST 2021
     */
    public static class Criteria extends GeneratedCriteria {
        protected Criteria() {
            super();
        }
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table com_autonum
     *
     * @mbg.generated Wed Nov 24 14:30:27 CST 2021
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