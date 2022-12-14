package com.asi.huanan.service.dao.mybatis.model;

import java.util.ArrayList;
import java.util.List;

public class FriFacRemExample {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table fri_fac_rem
     *
     * @mbg.generated Sun Jan 02 18:01:01 CST 2022
     */
    protected String orderByClause;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table fri_fac_rem
     *
     * @mbg.generated Sun Jan 02 18:01:01 CST 2022
     */
    protected boolean distinct;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table fri_fac_rem
     *
     * @mbg.generated Sun Jan 02 18:01:01 CST 2022
     */
    protected List<Criteria> oredCriteria;

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table fri_fac_rem
     *
     * @mbg.generated Sun Jan 02 18:01:01 CST 2022
     */
    public FriFacRemExample() {
        oredCriteria = new ArrayList<>();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table fri_fac_rem
     *
     * @mbg.generated Sun Jan 02 18:01:01 CST 2022
     */
    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table fri_fac_rem
     *
     * @mbg.generated Sun Jan 02 18:01:01 CST 2022
     */
    public String getOrderByClause() {
        return orderByClause;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table fri_fac_rem
     *
     * @mbg.generated Sun Jan 02 18:01:01 CST 2022
     */
    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table fri_fac_rem
     *
     * @mbg.generated Sun Jan 02 18:01:01 CST 2022
     */
    public boolean isDistinct() {
        return distinct;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table fri_fac_rem
     *
     * @mbg.generated Sun Jan 02 18:01:01 CST 2022
     */
    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table fri_fac_rem
     *
     * @mbg.generated Sun Jan 02 18:01:01 CST 2022
     */
    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table fri_fac_rem
     *
     * @mbg.generated Sun Jan 02 18:01:01 CST 2022
     */
    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table fri_fac_rem
     *
     * @mbg.generated Sun Jan 02 18:01:01 CST 2022
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
     * This method corresponds to the database table fri_fac_rem
     *
     * @mbg.generated Sun Jan 02 18:01:01 CST 2022
     */
    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table fri_fac_rem
     *
     * @mbg.generated Sun Jan 02 18:01:01 CST 2022
     */
    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table fri_fac_rem
     *
     * @mbg.generated Sun Jan 02 18:01:01 CST 2022
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

        public Criteria andSlipNoIsNull() {
            addCriterion("slip_no is null");
            return (Criteria) this;
        }

        public Criteria andSlipNoIsNotNull() {
            addCriterion("slip_no is not null");
            return (Criteria) this;
        }

        public Criteria andSlipNoEqualTo(String value) {
            addCriterion("slip_no =", value, "slipNo");
            return (Criteria) this;
        }

        public Criteria andSlipNoNotEqualTo(String value) {
            addCriterion("slip_no <>", value, "slipNo");
            return (Criteria) this;
        }

        public Criteria andSlipNoGreaterThan(String value) {
            addCriterion("slip_no >", value, "slipNo");
            return (Criteria) this;
        }

        public Criteria andSlipNoGreaterThanOrEqualTo(String value) {
            addCriterion("slip_no >=", value, "slipNo");
            return (Criteria) this;
        }

        public Criteria andSlipNoLessThan(String value) {
            addCriterion("slip_no <", value, "slipNo");
            return (Criteria) this;
        }

        public Criteria andSlipNoLessThanOrEqualTo(String value) {
            addCriterion("slip_no <=", value, "slipNo");
            return (Criteria) this;
        }

        public Criteria andSlipNoLike(String value) {
            addCriterion("slip_no like", value, "slipNo");
            return (Criteria) this;
        }

        public Criteria andSlipNoNotLike(String value) {
            addCriterion("slip_no not like", value, "slipNo");
            return (Criteria) this;
        }

        public Criteria andSlipNoIn(List<String> values) {
            addCriterion("slip_no in", values, "slipNo");
            return (Criteria) this;
        }

        public Criteria andSlipNoNotIn(List<String> values) {
            addCriterion("slip_no not in", values, "slipNo");
            return (Criteria) this;
        }

        public Criteria andSlipNoBetween(String value1, String value2) {
            addCriterion("slip_no between", value1, value2, "slipNo");
            return (Criteria) this;
        }

        public Criteria andSlipNoNotBetween(String value1, String value2) {
            addCriterion("slip_no not between", value1, value2, "slipNo");
            return (Criteria) this;
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

        public Criteria andRemarkTypeIsNull() {
            addCriterion("remark_type is null");
            return (Criteria) this;
        }

        public Criteria andRemarkTypeIsNotNull() {
            addCriterion("remark_type is not null");
            return (Criteria) this;
        }

        public Criteria andRemarkTypeEqualTo(String value) {
            addCriterion("remark_type =", value, "remarkType");
            return (Criteria) this;
        }

        public Criteria andRemarkTypeNotEqualTo(String value) {
            addCriterion("remark_type <>", value, "remarkType");
            return (Criteria) this;
        }

        public Criteria andRemarkTypeGreaterThan(String value) {
            addCriterion("remark_type >", value, "remarkType");
            return (Criteria) this;
        }

        public Criteria andRemarkTypeGreaterThanOrEqualTo(String value) {
            addCriterion("remark_type >=", value, "remarkType");
            return (Criteria) this;
        }

        public Criteria andRemarkTypeLessThan(String value) {
            addCriterion("remark_type <", value, "remarkType");
            return (Criteria) this;
        }

        public Criteria andRemarkTypeLessThanOrEqualTo(String value) {
            addCriterion("remark_type <=", value, "remarkType");
            return (Criteria) this;
        }

        public Criteria andRemarkTypeLike(String value) {
            addCriterion("remark_type like", value, "remarkType");
            return (Criteria) this;
        }

        public Criteria andRemarkTypeNotLike(String value) {
            addCriterion("remark_type not like", value, "remarkType");
            return (Criteria) this;
        }

        public Criteria andRemarkTypeIn(List<String> values) {
            addCriterion("remark_type in", values, "remarkType");
            return (Criteria) this;
        }

        public Criteria andRemarkTypeNotIn(List<String> values) {
            addCriterion("remark_type not in", values, "remarkType");
            return (Criteria) this;
        }

        public Criteria andRemarkTypeBetween(String value1, String value2) {
            addCriterion("remark_type between", value1, value2, "remarkType");
            return (Criteria) this;
        }

        public Criteria andRemarkTypeNotBetween(String value1, String value2) {
            addCriterion("remark_type not between", value1, value2, "remarkType");
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

        public Criteria andSerialEqualTo(Short value) {
            addCriterion("serial =", value, "serial");
            return (Criteria) this;
        }

        public Criteria andSerialNotEqualTo(Short value) {
            addCriterion("serial <>", value, "serial");
            return (Criteria) this;
        }

        public Criteria andSerialGreaterThan(Short value) {
            addCriterion("serial >", value, "serial");
            return (Criteria) this;
        }

        public Criteria andSerialGreaterThanOrEqualTo(Short value) {
            addCriterion("serial >=", value, "serial");
            return (Criteria) this;
        }

        public Criteria andSerialLessThan(Short value) {
            addCriterion("serial <", value, "serial");
            return (Criteria) this;
        }

        public Criteria andSerialLessThanOrEqualTo(Short value) {
            addCriterion("serial <=", value, "serial");
            return (Criteria) this;
        }

        public Criteria andSerialIn(List<Short> values) {
            addCriterion("serial in", values, "serial");
            return (Criteria) this;
        }

        public Criteria andSerialNotIn(List<Short> values) {
            addCriterion("serial not in", values, "serial");
            return (Criteria) this;
        }

        public Criteria andSerialBetween(Short value1, Short value2) {
            addCriterion("serial between", value1, value2, "serial");
            return (Criteria) this;
        }

        public Criteria andSerialNotBetween(Short value1, Short value2) {
            addCriterion("serial not between", value1, value2, "serial");
            return (Criteria) this;
        }

        public Criteria andRemarkIsNull() {
            addCriterion("remark is null");
            return (Criteria) this;
        }

        public Criteria andRemarkIsNotNull() {
            addCriterion("remark is not null");
            return (Criteria) this;
        }

        public Criteria andRemarkEqualTo(String value) {
            addCriterion("remark =", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkNotEqualTo(String value) {
            addCriterion("remark <>", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkGreaterThan(String value) {
            addCriterion("remark >", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkGreaterThanOrEqualTo(String value) {
            addCriterion("remark >=", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkLessThan(String value) {
            addCriterion("remark <", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkLessThanOrEqualTo(String value) {
            addCriterion("remark <=", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkLike(String value) {
            addCriterion("remark like", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkNotLike(String value) {
            addCriterion("remark not like", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkIn(List<String> values) {
            addCriterion("remark in", values, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkNotIn(List<String> values) {
            addCriterion("remark not in", values, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkBetween(String value1, String value2) {
            addCriterion("remark between", value1, value2, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkNotBetween(String value1, String value2) {
            addCriterion("remark not between", value1, value2, "remark");
            return (Criteria) this;
        }
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table fri_fac_rem
     *
     * @mbg.generated do_not_delete_during_merge Sun Jan 02 18:01:01 CST 2022
     */
    public static class Criteria extends GeneratedCriteria {
        protected Criteria() {
            super();
        }
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table fri_fac_rem
     *
     * @mbg.generated Sun Jan 02 18:01:01 CST 2022
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