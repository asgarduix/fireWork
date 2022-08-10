package com.asi.huanan.service.dao.mybatis.model;

import java.util.ArrayList;
import java.util.List;

public class LogFriFacNpropExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public LogFriFacNpropExample() {
        oredCriteria = new ArrayList<>();
    }

    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    public String getOrderByClause() {
        return orderByClause;
    }

    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    public boolean isDistinct() {
        return distinct;
    }

    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    public Criteria createCriteria() {
        Criteria criteria = createCriteriaInternal();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }

    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

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

        public Criteria andLogSeqIsNull() {
            addCriterion("LOG_seq is null");
            return (Criteria) this;
        }

        public Criteria andLogSeqIsNotNull() {
            addCriterion("LOG_seq is not null");
            return (Criteria) this;
        }

        public Criteria andLogSeqEqualTo(String value) {
            addCriterion("LOG_seq =", value, "logSeq");
            return (Criteria) this;
        }

        public Criteria andLogSeqNotEqualTo(String value) {
            addCriterion("LOG_seq <>", value, "logSeq");
            return (Criteria) this;
        }

        public Criteria andLogSeqGreaterThan(String value) {
            addCriterion("LOG_seq >", value, "logSeq");
            return (Criteria) this;
        }

        public Criteria andLogSeqGreaterThanOrEqualTo(String value) {
            addCriterion("LOG_seq >=", value, "logSeq");
            return (Criteria) this;
        }

        public Criteria andLogSeqLessThan(String value) {
            addCriterion("LOG_seq <", value, "logSeq");
            return (Criteria) this;
        }

        public Criteria andLogSeqLessThanOrEqualTo(String value) {
            addCriterion("LOG_seq <=", value, "logSeq");
            return (Criteria) this;
        }

        public Criteria andLogSeqLike(String value) {
            addCriterion("LOG_seq like", value, "logSeq");
            return (Criteria) this;
        }

        public Criteria andLogSeqNotLike(String value) {
            addCriterion("LOG_seq not like", value, "logSeq");
            return (Criteria) this;
        }

        public Criteria andLogSeqIn(List<String> values) {
            addCriterion("LOG_seq in", values, "logSeq");
            return (Criteria) this;
        }

        public Criteria andLogSeqNotIn(List<String> values) {
            addCriterion("LOG_seq not in", values, "logSeq");
            return (Criteria) this;
        }

        public Criteria andLogSeqBetween(String value1, String value2) {
            addCriterion("LOG_seq between", value1, value2, "logSeq");
            return (Criteria) this;
        }

        public Criteria andLogSeqNotBetween(String value1, String value2) {
            addCriterion("LOG_seq not between", value1, value2, "logSeq");
            return (Criteria) this;
        }

        public Criteria andPropertyNoIsNull() {
            addCriterion("property_no is null");
            return (Criteria) this;
        }

        public Criteria andPropertyNoIsNotNull() {
            addCriterion("property_no is not null");
            return (Criteria) this;
        }

        public Criteria andPropertyNoEqualTo(String value) {
            addCriterion("property_no =", value, "propertyNo");
            return (Criteria) this;
        }

        public Criteria andPropertyNoNotEqualTo(String value) {
            addCriterion("property_no <>", value, "propertyNo");
            return (Criteria) this;
        }

        public Criteria andPropertyNoGreaterThan(String value) {
            addCriterion("property_no >", value, "propertyNo");
            return (Criteria) this;
        }

        public Criteria andPropertyNoGreaterThanOrEqualTo(String value) {
            addCriterion("property_no >=", value, "propertyNo");
            return (Criteria) this;
        }

        public Criteria andPropertyNoLessThan(String value) {
            addCriterion("property_no <", value, "propertyNo");
            return (Criteria) this;
        }

        public Criteria andPropertyNoLessThanOrEqualTo(String value) {
            addCriterion("property_no <=", value, "propertyNo");
            return (Criteria) this;
        }

        public Criteria andPropertyNoLike(String value) {
            addCriterion("property_no like", value, "propertyNo");
            return (Criteria) this;
        }

        public Criteria andPropertyNoNotLike(String value) {
            addCriterion("property_no not like", value, "propertyNo");
            return (Criteria) this;
        }

        public Criteria andPropertyNoIn(List<String> values) {
            addCriterion("property_no in", values, "propertyNo");
            return (Criteria) this;
        }

        public Criteria andPropertyNoNotIn(List<String> values) {
            addCriterion("property_no not in", values, "propertyNo");
            return (Criteria) this;
        }

        public Criteria andPropertyNoBetween(String value1, String value2) {
            addCriterion("property_no between", value1, value2, "propertyNo");
            return (Criteria) this;
        }

        public Criteria andPropertyNoNotBetween(String value1, String value2) {
            addCriterion("property_no not between", value1, value2, "propertyNo");
            return (Criteria) this;
        }

        public Criteria andPropertyNameIsNull() {
            addCriterion("property_name is null");
            return (Criteria) this;
        }

        public Criteria andPropertyNameIsNotNull() {
            addCriterion("property_name is not null");
            return (Criteria) this;
        }

        public Criteria andPropertyNameEqualTo(String value) {
            addCriterion("property_name =", value, "propertyName");
            return (Criteria) this;
        }

        public Criteria andPropertyNameNotEqualTo(String value) {
            addCriterion("property_name <>", value, "propertyName");
            return (Criteria) this;
        }

        public Criteria andPropertyNameGreaterThan(String value) {
            addCriterion("property_name >", value, "propertyName");
            return (Criteria) this;
        }

        public Criteria andPropertyNameGreaterThanOrEqualTo(String value) {
            addCriterion("property_name >=", value, "propertyName");
            return (Criteria) this;
        }

        public Criteria andPropertyNameLessThan(String value) {
            addCriterion("property_name <", value, "propertyName");
            return (Criteria) this;
        }

        public Criteria andPropertyNameLessThanOrEqualTo(String value) {
            addCriterion("property_name <=", value, "propertyName");
            return (Criteria) this;
        }

        public Criteria andPropertyNameLike(String value) {
            addCriterion("property_name like", value, "propertyName");
            return (Criteria) this;
        }

        public Criteria andPropertyNameNotLike(String value) {
            addCriterion("property_name not like", value, "propertyName");
            return (Criteria) this;
        }

        public Criteria andPropertyNameIn(List<String> values) {
            addCriterion("property_name in", values, "propertyName");
            return (Criteria) this;
        }

        public Criteria andPropertyNameNotIn(List<String> values) {
            addCriterion("property_name not in", values, "propertyName");
            return (Criteria) this;
        }

        public Criteria andPropertyNameBetween(String value1, String value2) {
            addCriterion("property_name between", value1, value2, "propertyName");
            return (Criteria) this;
        }

        public Criteria andPropertyNameNotBetween(String value1, String value2) {
            addCriterion("property_name not between", value1, value2, "propertyName");
            return (Criteria) this;
        }

        public Criteria andAmtIsNull() {
            addCriterion("amt is null");
            return (Criteria) this;
        }

        public Criteria andAmtIsNotNull() {
            addCriterion("amt is not null");
            return (Criteria) this;
        }

        public Criteria andAmtEqualTo(Long value) {
            addCriterion("amt =", value, "amt");
            return (Criteria) this;
        }

        public Criteria andAmtNotEqualTo(Long value) {
            addCriterion("amt <>", value, "amt");
            return (Criteria) this;
        }

        public Criteria andAmtGreaterThan(Long value) {
            addCriterion("amt >", value, "amt");
            return (Criteria) this;
        }

        public Criteria andAmtGreaterThanOrEqualTo(Long value) {
            addCriterion("amt >=", value, "amt");
            return (Criteria) this;
        }

        public Criteria andAmtLessThan(Long value) {
            addCriterion("amt <", value, "amt");
            return (Criteria) this;
        }

        public Criteria andAmtLessThanOrEqualTo(Long value) {
            addCriterion("amt <=", value, "amt");
            return (Criteria) this;
        }

        public Criteria andAmtIn(List<Long> values) {
            addCriterion("amt in", values, "amt");
            return (Criteria) this;
        }

        public Criteria andAmtNotIn(List<Long> values) {
            addCriterion("amt not in", values, "amt");
            return (Criteria) this;
        }

        public Criteria andAmtBetween(Long value1, Long value2) {
            addCriterion("amt between", value1, value2, "amt");
            return (Criteria) this;
        }

        public Criteria andAmtNotBetween(Long value1, Long value2) {
            addCriterion("amt not between", value1, value2, "amt");
            return (Criteria) this;
        }
    }

    public static class Criteria extends GeneratedCriteria {
        protected Criteria() {
            super();
        }
    }

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