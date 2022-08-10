package com.asi.huanan.service.dao.mybatis.model;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class LogFriFacRateExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public LogFriFacRateExample() {
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

        public Criteria andContentIsNull() {
            addCriterion("content is null");
            return (Criteria) this;
        }

        public Criteria andContentIsNotNull() {
            addCriterion("content is not null");
            return (Criteria) this;
        }

        public Criteria andContentEqualTo(String value) {
            addCriterion("content =", value, "content");
            return (Criteria) this;
        }

        public Criteria andContentNotEqualTo(String value) {
            addCriterion("content <>", value, "content");
            return (Criteria) this;
        }

        public Criteria andContentGreaterThan(String value) {
            addCriterion("content >", value, "content");
            return (Criteria) this;
        }

        public Criteria andContentGreaterThanOrEqualTo(String value) {
            addCriterion("content >=", value, "content");
            return (Criteria) this;
        }

        public Criteria andContentLessThan(String value) {
            addCriterion("content <", value, "content");
            return (Criteria) this;
        }

        public Criteria andContentLessThanOrEqualTo(String value) {
            addCriterion("content <=", value, "content");
            return (Criteria) this;
        }

        public Criteria andContentLike(String value) {
            addCriterion("content like", value, "content");
            return (Criteria) this;
        }

        public Criteria andContentNotLike(String value) {
            addCriterion("content not like", value, "content");
            return (Criteria) this;
        }

        public Criteria andContentIn(List<String> values) {
            addCriterion("content in", values, "content");
            return (Criteria) this;
        }

        public Criteria andContentNotIn(List<String> values) {
            addCriterion("content not in", values, "content");
            return (Criteria) this;
        }

        public Criteria andContentBetween(String value1, String value2) {
            addCriterion("content between", value1, value2, "content");
            return (Criteria) this;
        }

        public Criteria andContentNotBetween(String value1, String value2) {
            addCriterion("content not between", value1, value2, "content");
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

        public Criteria andPremIsNull() {
            addCriterion("prem is null");
            return (Criteria) this;
        }

        public Criteria andPremIsNotNull() {
            addCriterion("prem is not null");
            return (Criteria) this;
        }

        public Criteria andPremEqualTo(Long value) {
            addCriterion("prem =", value, "prem");
            return (Criteria) this;
        }

        public Criteria andPremNotEqualTo(Long value) {
            addCriterion("prem <>", value, "prem");
            return (Criteria) this;
        }

        public Criteria andPremGreaterThan(Long value) {
            addCriterion("prem >", value, "prem");
            return (Criteria) this;
        }

        public Criteria andPremGreaterThanOrEqualTo(Long value) {
            addCriterion("prem >=", value, "prem");
            return (Criteria) this;
        }

        public Criteria andPremLessThan(Long value) {
            addCriterion("prem <", value, "prem");
            return (Criteria) this;
        }

        public Criteria andPremLessThanOrEqualTo(Long value) {
            addCriterion("prem <=", value, "prem");
            return (Criteria) this;
        }

        public Criteria andPremIn(List<Long> values) {
            addCriterion("prem in", values, "prem");
            return (Criteria) this;
        }

        public Criteria andPremNotIn(List<Long> values) {
            addCriterion("prem not in", values, "prem");
            return (Criteria) this;
        }

        public Criteria andPremBetween(Long value1, Long value2) {
            addCriterion("prem between", value1, value2, "prem");
            return (Criteria) this;
        }

        public Criteria andPremNotBetween(Long value1, Long value2) {
            addCriterion("prem not between", value1, value2, "prem");
            return (Criteria) this;
        }

        public Criteria andFacPremIsNull() {
            addCriterion("fac_prem is null");
            return (Criteria) this;
        }

        public Criteria andFacPremIsNotNull() {
            addCriterion("fac_prem is not null");
            return (Criteria) this;
        }

        public Criteria andFacPremEqualTo(Long value) {
            addCriterion("fac_prem =", value, "facPrem");
            return (Criteria) this;
        }

        public Criteria andFacPremNotEqualTo(Long value) {
            addCriterion("fac_prem <>", value, "facPrem");
            return (Criteria) this;
        }

        public Criteria andFacPremGreaterThan(Long value) {
            addCriterion("fac_prem >", value, "facPrem");
            return (Criteria) this;
        }

        public Criteria andFacPremGreaterThanOrEqualTo(Long value) {
            addCriterion("fac_prem >=", value, "facPrem");
            return (Criteria) this;
        }

        public Criteria andFacPremLessThan(Long value) {
            addCriterion("fac_prem <", value, "facPrem");
            return (Criteria) this;
        }

        public Criteria andFacPremLessThanOrEqualTo(Long value) {
            addCriterion("fac_prem <=", value, "facPrem");
            return (Criteria) this;
        }

        public Criteria andFacPremIn(List<Long> values) {
            addCriterion("fac_prem in", values, "facPrem");
            return (Criteria) this;
        }

        public Criteria andFacPremNotIn(List<Long> values) {
            addCriterion("fac_prem not in", values, "facPrem");
            return (Criteria) this;
        }

        public Criteria andFacPremBetween(Long value1, Long value2) {
            addCriterion("fac_prem between", value1, value2, "facPrem");
            return (Criteria) this;
        }

        public Criteria andFacPremNotBetween(Long value1, Long value2) {
            addCriterion("fac_prem not between", value1, value2, "facPrem");
            return (Criteria) this;
        }

        public Criteria andPremRateIsNull() {
            addCriterion("prem_rate is null");
            return (Criteria) this;
        }

        public Criteria andPremRateIsNotNull() {
            addCriterion("prem_rate is not null");
            return (Criteria) this;
        }

        public Criteria andPremRateEqualTo(BigDecimal value) {
            addCriterion("prem_rate =", value, "premRate");
            return (Criteria) this;
        }

        public Criteria andPremRateNotEqualTo(BigDecimal value) {
            addCriterion("prem_rate <>", value, "premRate");
            return (Criteria) this;
        }

        public Criteria andPremRateGreaterThan(BigDecimal value) {
            addCriterion("prem_rate >", value, "premRate");
            return (Criteria) this;
        }

        public Criteria andPremRateGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("prem_rate >=", value, "premRate");
            return (Criteria) this;
        }

        public Criteria andPremRateLessThan(BigDecimal value) {
            addCriterion("prem_rate <", value, "premRate");
            return (Criteria) this;
        }

        public Criteria andPremRateLessThanOrEqualTo(BigDecimal value) {
            addCriterion("prem_rate <=", value, "premRate");
            return (Criteria) this;
        }

        public Criteria andPremRateIn(List<BigDecimal> values) {
            addCriterion("prem_rate in", values, "premRate");
            return (Criteria) this;
        }

        public Criteria andPremRateNotIn(List<BigDecimal> values) {
            addCriterion("prem_rate not in", values, "premRate");
            return (Criteria) this;
        }

        public Criteria andPremRateBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("prem_rate between", value1, value2, "premRate");
            return (Criteria) this;
        }

        public Criteria andPremRateNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("prem_rate not between", value1, value2, "premRate");
            return (Criteria) this;
        }

        public Criteria andFacPremRateIsNull() {
            addCriterion("fac_prem_rate is null");
            return (Criteria) this;
        }

        public Criteria andFacPremRateIsNotNull() {
            addCriterion("fac_prem_rate is not null");
            return (Criteria) this;
        }

        public Criteria andFacPremRateEqualTo(BigDecimal value) {
            addCriterion("fac_prem_rate =", value, "facPremRate");
            return (Criteria) this;
        }

        public Criteria andFacPremRateNotEqualTo(BigDecimal value) {
            addCriterion("fac_prem_rate <>", value, "facPremRate");
            return (Criteria) this;
        }

        public Criteria andFacPremRateGreaterThan(BigDecimal value) {
            addCriterion("fac_prem_rate >", value, "facPremRate");
            return (Criteria) this;
        }

        public Criteria andFacPremRateGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("fac_prem_rate >=", value, "facPremRate");
            return (Criteria) this;
        }

        public Criteria andFacPremRateLessThan(BigDecimal value) {
            addCriterion("fac_prem_rate <", value, "facPremRate");
            return (Criteria) this;
        }

        public Criteria andFacPremRateLessThanOrEqualTo(BigDecimal value) {
            addCriterion("fac_prem_rate <=", value, "facPremRate");
            return (Criteria) this;
        }

        public Criteria andFacPremRateIn(List<BigDecimal> values) {
            addCriterion("fac_prem_rate in", values, "facPremRate");
            return (Criteria) this;
        }

        public Criteria andFacPremRateNotIn(List<BigDecimal> values) {
            addCriterion("fac_prem_rate not in", values, "facPremRate");
            return (Criteria) this;
        }

        public Criteria andFacPremRateBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("fac_prem_rate between", value1, value2, "facPremRate");
            return (Criteria) this;
        }

        public Criteria andFacPremRateNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("fac_prem_rate not between", value1, value2, "facPremRate");
            return (Criteria) this;
        }

        public Criteria andCommRateIsNull() {
            addCriterion("comm_rate is null");
            return (Criteria) this;
        }

        public Criteria andCommRateIsNotNull() {
            addCriterion("comm_rate is not null");
            return (Criteria) this;
        }

        public Criteria andCommRateEqualTo(BigDecimal value) {
            addCriterion("comm_rate =", value, "commRate");
            return (Criteria) this;
        }

        public Criteria andCommRateNotEqualTo(BigDecimal value) {
            addCriterion("comm_rate <>", value, "commRate");
            return (Criteria) this;
        }

        public Criteria andCommRateGreaterThan(BigDecimal value) {
            addCriterion("comm_rate >", value, "commRate");
            return (Criteria) this;
        }

        public Criteria andCommRateGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("comm_rate >=", value, "commRate");
            return (Criteria) this;
        }

        public Criteria andCommRateLessThan(BigDecimal value) {
            addCriterion("comm_rate <", value, "commRate");
            return (Criteria) this;
        }

        public Criteria andCommRateLessThanOrEqualTo(BigDecimal value) {
            addCriterion("comm_rate <=", value, "commRate");
            return (Criteria) this;
        }

        public Criteria andCommRateIn(List<BigDecimal> values) {
            addCriterion("comm_rate in", values, "commRate");
            return (Criteria) this;
        }

        public Criteria andCommRateNotIn(List<BigDecimal> values) {
            addCriterion("comm_rate not in", values, "commRate");
            return (Criteria) this;
        }

        public Criteria andCommRateBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("comm_rate between", value1, value2, "commRate");
            return (Criteria) this;
        }

        public Criteria andCommRateNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("comm_rate not between", value1, value2, "commRate");
            return (Criteria) this;
        }

        public Criteria andLimitRateIsNull() {
            addCriterion("limit_rate is null");
            return (Criteria) this;
        }

        public Criteria andLimitRateIsNotNull() {
            addCriterion("limit_rate is not null");
            return (Criteria) this;
        }

        public Criteria andLimitRateEqualTo(BigDecimal value) {
            addCriterion("limit_rate =", value, "limitRate");
            return (Criteria) this;
        }

        public Criteria andLimitRateNotEqualTo(BigDecimal value) {
            addCriterion("limit_rate <>", value, "limitRate");
            return (Criteria) this;
        }

        public Criteria andLimitRateGreaterThan(BigDecimal value) {
            addCriterion("limit_rate >", value, "limitRate");
            return (Criteria) this;
        }

        public Criteria andLimitRateGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("limit_rate >=", value, "limitRate");
            return (Criteria) this;
        }

        public Criteria andLimitRateLessThan(BigDecimal value) {
            addCriterion("limit_rate <", value, "limitRate");
            return (Criteria) this;
        }

        public Criteria andLimitRateLessThanOrEqualTo(BigDecimal value) {
            addCriterion("limit_rate <=", value, "limitRate");
            return (Criteria) this;
        }

        public Criteria andLimitRateIn(List<BigDecimal> values) {
            addCriterion("limit_rate in", values, "limitRate");
            return (Criteria) this;
        }

        public Criteria andLimitRateNotIn(List<BigDecimal> values) {
            addCriterion("limit_rate not in", values, "limitRate");
            return (Criteria) this;
        }

        public Criteria andLimitRateBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("limit_rate between", value1, value2, "limitRate");
            return (Criteria) this;
        }

        public Criteria andLimitRateNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("limit_rate not between", value1, value2, "limitRate");
            return (Criteria) this;
        }

        public Criteria andLimitIsNull() {
            addCriterion("limit is null");
            return (Criteria) this;
        }

        public Criteria andLimitIsNotNull() {
            addCriterion("limit is not null");
            return (Criteria) this;
        }

        public Criteria andLimitEqualTo(Long value) {
            addCriterion("limit =", value, "limit");
            return (Criteria) this;
        }

        public Criteria andLimitNotEqualTo(Long value) {
            addCriterion("limit <>", value, "limit");
            return (Criteria) this;
        }

        public Criteria andLimitGreaterThan(Long value) {
            addCriterion("limit >", value, "limit");
            return (Criteria) this;
        }

        public Criteria andLimitGreaterThanOrEqualTo(Long value) {
            addCriterion("limit >=", value, "limit");
            return (Criteria) this;
        }

        public Criteria andLimitLessThan(Long value) {
            addCriterion("limit <", value, "limit");
            return (Criteria) this;
        }

        public Criteria andLimitLessThanOrEqualTo(Long value) {
            addCriterion("limit <=", value, "limit");
            return (Criteria) this;
        }

        public Criteria andLimitIn(List<Long> values) {
            addCriterion("limit in", values, "limit");
            return (Criteria) this;
        }

        public Criteria andLimitNotIn(List<Long> values) {
            addCriterion("limit not in", values, "limit");
            return (Criteria) this;
        }

        public Criteria andLimitBetween(Long value1, Long value2) {
            addCriterion("limit between", value1, value2, "limit");
            return (Criteria) this;
        }

        public Criteria andLimitNotBetween(Long value1, Long value2) {
            addCriterion("limit not between", value1, value2, "limit");
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