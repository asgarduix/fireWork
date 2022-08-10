package com.asi.huanan.service.dao.mybatis.model;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class LogFriFacBrokerExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public LogFriFacBrokerExample() {
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

        public Criteria andBrokerIdIsNull() {
            addCriterion("broker_id is null");
            return (Criteria) this;
        }

        public Criteria andBrokerIdIsNotNull() {
            addCriterion("broker_id is not null");
            return (Criteria) this;
        }

        public Criteria andBrokerIdEqualTo(String value) {
            addCriterion("broker_id =", value, "brokerId");
            return (Criteria) this;
        }

        public Criteria andBrokerIdNotEqualTo(String value) {
            addCriterion("broker_id <>", value, "brokerId");
            return (Criteria) this;
        }

        public Criteria andBrokerIdGreaterThan(String value) {
            addCriterion("broker_id >", value, "brokerId");
            return (Criteria) this;
        }

        public Criteria andBrokerIdGreaterThanOrEqualTo(String value) {
            addCriterion("broker_id >=", value, "brokerId");
            return (Criteria) this;
        }

        public Criteria andBrokerIdLessThan(String value) {
            addCriterion("broker_id <", value, "brokerId");
            return (Criteria) this;
        }

        public Criteria andBrokerIdLessThanOrEqualTo(String value) {
            addCriterion("broker_id <=", value, "brokerId");
            return (Criteria) this;
        }

        public Criteria andBrokerIdLike(String value) {
            addCriterion("broker_id like", value, "brokerId");
            return (Criteria) this;
        }

        public Criteria andBrokerIdNotLike(String value) {
            addCriterion("broker_id not like", value, "brokerId");
            return (Criteria) this;
        }

        public Criteria andBrokerIdIn(List<String> values) {
            addCriterion("broker_id in", values, "brokerId");
            return (Criteria) this;
        }

        public Criteria andBrokerIdNotIn(List<String> values) {
            addCriterion("broker_id not in", values, "brokerId");
            return (Criteria) this;
        }

        public Criteria andBrokerIdBetween(String value1, String value2) {
            addCriterion("broker_id between", value1, value2, "brokerId");
            return (Criteria) this;
        }

        public Criteria andBrokerIdNotBetween(String value1, String value2) {
            addCriterion("broker_id not between", value1, value2, "brokerId");
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

        public Criteria andFacTypeIsNull() {
            addCriterion("fac_type is null");
            return (Criteria) this;
        }

        public Criteria andFacTypeIsNotNull() {
            addCriterion("fac_type is not null");
            return (Criteria) this;
        }

        public Criteria andFacTypeEqualTo(String value) {
            addCriterion("fac_type =", value, "facType");
            return (Criteria) this;
        }

        public Criteria andFacTypeNotEqualTo(String value) {
            addCriterion("fac_type <>", value, "facType");
            return (Criteria) this;
        }

        public Criteria andFacTypeGreaterThan(String value) {
            addCriterion("fac_type >", value, "facType");
            return (Criteria) this;
        }

        public Criteria andFacTypeGreaterThanOrEqualTo(String value) {
            addCriterion("fac_type >=", value, "facType");
            return (Criteria) this;
        }

        public Criteria andFacTypeLessThan(String value) {
            addCriterion("fac_type <", value, "facType");
            return (Criteria) this;
        }

        public Criteria andFacTypeLessThanOrEqualTo(String value) {
            addCriterion("fac_type <=", value, "facType");
            return (Criteria) this;
        }

        public Criteria andFacTypeLike(String value) {
            addCriterion("fac_type like", value, "facType");
            return (Criteria) this;
        }

        public Criteria andFacTypeNotLike(String value) {
            addCriterion("fac_type not like", value, "facType");
            return (Criteria) this;
        }

        public Criteria andFacTypeIn(List<String> values) {
            addCriterion("fac_type in", values, "facType");
            return (Criteria) this;
        }

        public Criteria andFacTypeNotIn(List<String> values) {
            addCriterion("fac_type not in", values, "facType");
            return (Criteria) this;
        }

        public Criteria andFacTypeBetween(String value1, String value2) {
            addCriterion("fac_type between", value1, value2, "facType");
            return (Criteria) this;
        }

        public Criteria andFacTypeNotBetween(String value1, String value2) {
            addCriterion("fac_type not between", value1, value2, "facType");
            return (Criteria) this;
        }

        public Criteria andShareRateIsNull() {
            addCriterion("share_rate is null");
            return (Criteria) this;
        }

        public Criteria andShareRateIsNotNull() {
            addCriterion("share_rate is not null");
            return (Criteria) this;
        }

        public Criteria andShareRateEqualTo(BigDecimal value) {
            addCriterion("share_rate =", value, "shareRate");
            return (Criteria) this;
        }

        public Criteria andShareRateNotEqualTo(BigDecimal value) {
            addCriterion("share_rate <>", value, "shareRate");
            return (Criteria) this;
        }

        public Criteria andShareRateGreaterThan(BigDecimal value) {
            addCriterion("share_rate >", value, "shareRate");
            return (Criteria) this;
        }

        public Criteria andShareRateGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("share_rate >=", value, "shareRate");
            return (Criteria) this;
        }

        public Criteria andShareRateLessThan(BigDecimal value) {
            addCriterion("share_rate <", value, "shareRate");
            return (Criteria) this;
        }

        public Criteria andShareRateLessThanOrEqualTo(BigDecimal value) {
            addCriterion("share_rate <=", value, "shareRate");
            return (Criteria) this;
        }

        public Criteria andShareRateIn(List<BigDecimal> values) {
            addCriterion("share_rate in", values, "shareRate");
            return (Criteria) this;
        }

        public Criteria andShareRateNotIn(List<BigDecimal> values) {
            addCriterion("share_rate not in", values, "shareRate");
            return (Criteria) this;
        }

        public Criteria andShareRateBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("share_rate between", value1, value2, "shareRate");
            return (Criteria) this;
        }

        public Criteria andShareRateNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("share_rate not between", value1, value2, "shareRate");
            return (Criteria) this;
        }

        public Criteria andRinComIdOldIsNull() {
            addCriterion("rin_com_id_OLD is null");
            return (Criteria) this;
        }

        public Criteria andRinComIdOldIsNotNull() {
            addCriterion("rin_com_id_OLD is not null");
            return (Criteria) this;
        }

        public Criteria andRinComIdOldEqualTo(String value) {
            addCriterion("rin_com_id_OLD =", value, "rinComIdOld");
            return (Criteria) this;
        }

        public Criteria andRinComIdOldNotEqualTo(String value) {
            addCriterion("rin_com_id_OLD <>", value, "rinComIdOld");
            return (Criteria) this;
        }

        public Criteria andRinComIdOldGreaterThan(String value) {
            addCriterion("rin_com_id_OLD >", value, "rinComIdOld");
            return (Criteria) this;
        }

        public Criteria andRinComIdOldGreaterThanOrEqualTo(String value) {
            addCriterion("rin_com_id_OLD >=", value, "rinComIdOld");
            return (Criteria) this;
        }

        public Criteria andRinComIdOldLessThan(String value) {
            addCriterion("rin_com_id_OLD <", value, "rinComIdOld");
            return (Criteria) this;
        }

        public Criteria andRinComIdOldLessThanOrEqualTo(String value) {
            addCriterion("rin_com_id_OLD <=", value, "rinComIdOld");
            return (Criteria) this;
        }

        public Criteria andRinComIdOldLike(String value) {
            addCriterion("rin_com_id_OLD like", value, "rinComIdOld");
            return (Criteria) this;
        }

        public Criteria andRinComIdOldNotLike(String value) {
            addCriterion("rin_com_id_OLD not like", value, "rinComIdOld");
            return (Criteria) this;
        }

        public Criteria andRinComIdOldIn(List<String> values) {
            addCriterion("rin_com_id_OLD in", values, "rinComIdOld");
            return (Criteria) this;
        }

        public Criteria andRinComIdOldNotIn(List<String> values) {
            addCriterion("rin_com_id_OLD not in", values, "rinComIdOld");
            return (Criteria) this;
        }

        public Criteria andRinComIdOldBetween(String value1, String value2) {
            addCriterion("rin_com_id_OLD between", value1, value2, "rinComIdOld");
            return (Criteria) this;
        }

        public Criteria andRinComIdOldNotBetween(String value1, String value2) {
            addCriterion("rin_com_id_OLD not between", value1, value2, "rinComIdOld");
            return (Criteria) this;
        }

        public Criteria andBrokerIdOldIsNull() {
            addCriterion("broker_id_OLD is null");
            return (Criteria) this;
        }

        public Criteria andBrokerIdOldIsNotNull() {
            addCriterion("broker_id_OLD is not null");
            return (Criteria) this;
        }

        public Criteria andBrokerIdOldEqualTo(String value) {
            addCriterion("broker_id_OLD =", value, "brokerIdOld");
            return (Criteria) this;
        }

        public Criteria andBrokerIdOldNotEqualTo(String value) {
            addCriterion("broker_id_OLD <>", value, "brokerIdOld");
            return (Criteria) this;
        }

        public Criteria andBrokerIdOldGreaterThan(String value) {
            addCriterion("broker_id_OLD >", value, "brokerIdOld");
            return (Criteria) this;
        }

        public Criteria andBrokerIdOldGreaterThanOrEqualTo(String value) {
            addCriterion("broker_id_OLD >=", value, "brokerIdOld");
            return (Criteria) this;
        }

        public Criteria andBrokerIdOldLessThan(String value) {
            addCriterion("broker_id_OLD <", value, "brokerIdOld");
            return (Criteria) this;
        }

        public Criteria andBrokerIdOldLessThanOrEqualTo(String value) {
            addCriterion("broker_id_OLD <=", value, "brokerIdOld");
            return (Criteria) this;
        }

        public Criteria andBrokerIdOldLike(String value) {
            addCriterion("broker_id_OLD like", value, "brokerIdOld");
            return (Criteria) this;
        }

        public Criteria andBrokerIdOldNotLike(String value) {
            addCriterion("broker_id_OLD not like", value, "brokerIdOld");
            return (Criteria) this;
        }

        public Criteria andBrokerIdOldIn(List<String> values) {
            addCriterion("broker_id_OLD in", values, "brokerIdOld");
            return (Criteria) this;
        }

        public Criteria andBrokerIdOldNotIn(List<String> values) {
            addCriterion("broker_id_OLD not in", values, "brokerIdOld");
            return (Criteria) this;
        }

        public Criteria andBrokerIdOldBetween(String value1, String value2) {
            addCriterion("broker_id_OLD between", value1, value2, "brokerIdOld");
            return (Criteria) this;
        }

        public Criteria andBrokerIdOldNotBetween(String value1, String value2) {
            addCriterion("broker_id_OLD not between", value1, value2, "brokerIdOld");
            return (Criteria) this;
        }

        public Criteria andBrkcommIsNull() {
            addCriterion("brkcomm is null");
            return (Criteria) this;
        }

        public Criteria andBrkcommIsNotNull() {
            addCriterion("brkcomm is not null");
            return (Criteria) this;
        }

        public Criteria andBrkcommEqualTo(BigDecimal value) {
            addCriterion("brkcomm =", value, "brkcomm");
            return (Criteria) this;
        }

        public Criteria andBrkcommNotEqualTo(BigDecimal value) {
            addCriterion("brkcomm <>", value, "brkcomm");
            return (Criteria) this;
        }

        public Criteria andBrkcommGreaterThan(BigDecimal value) {
            addCriterion("brkcomm >", value, "brkcomm");
            return (Criteria) this;
        }

        public Criteria andBrkcommGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("brkcomm >=", value, "brkcomm");
            return (Criteria) this;
        }

        public Criteria andBrkcommLessThan(BigDecimal value) {
            addCriterion("brkcomm <", value, "brkcomm");
            return (Criteria) this;
        }

        public Criteria andBrkcommLessThanOrEqualTo(BigDecimal value) {
            addCriterion("brkcomm <=", value, "brkcomm");
            return (Criteria) this;
        }

        public Criteria andBrkcommIn(List<BigDecimal> values) {
            addCriterion("brkcomm in", values, "brkcomm");
            return (Criteria) this;
        }

        public Criteria andBrkcommNotIn(List<BigDecimal> values) {
            addCriterion("brkcomm not in", values, "brkcomm");
            return (Criteria) this;
        }

        public Criteria andBrkcommBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("brkcomm between", value1, value2, "brkcomm");
            return (Criteria) this;
        }

        public Criteria andBrkcommNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("brkcomm not between", value1, value2, "brkcomm");
            return (Criteria) this;
        }

        public Criteria andBrktaxIsNull() {
            addCriterion("brktax is null");
            return (Criteria) this;
        }

        public Criteria andBrktaxIsNotNull() {
            addCriterion("brktax is not null");
            return (Criteria) this;
        }

        public Criteria andBrktaxEqualTo(BigDecimal value) {
            addCriterion("brktax =", value, "brktax");
            return (Criteria) this;
        }

        public Criteria andBrktaxNotEqualTo(BigDecimal value) {
            addCriterion("brktax <>", value, "brktax");
            return (Criteria) this;
        }

        public Criteria andBrktaxGreaterThan(BigDecimal value) {
            addCriterion("brktax >", value, "brktax");
            return (Criteria) this;
        }

        public Criteria andBrktaxGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("brktax >=", value, "brktax");
            return (Criteria) this;
        }

        public Criteria andBrktaxLessThan(BigDecimal value) {
            addCriterion("brktax <", value, "brktax");
            return (Criteria) this;
        }

        public Criteria andBrktaxLessThanOrEqualTo(BigDecimal value) {
            addCriterion("brktax <=", value, "brktax");
            return (Criteria) this;
        }

        public Criteria andBrktaxIn(List<BigDecimal> values) {
            addCriterion("brktax in", values, "brktax");
            return (Criteria) this;
        }

        public Criteria andBrktaxNotIn(List<BigDecimal> values) {
            addCriterion("brktax not in", values, "brktax");
            return (Criteria) this;
        }

        public Criteria andBrktaxBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("brktax between", value1, value2, "brktax");
            return (Criteria) this;
        }

        public Criteria andBrktaxNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("brktax not between", value1, value2, "brktax");
            return (Criteria) this;
        }

        public Criteria andBrkpremIsNull() {
            addCriterion("brkprem is null");
            return (Criteria) this;
        }

        public Criteria andBrkpremIsNotNull() {
            addCriterion("brkprem is not null");
            return (Criteria) this;
        }

        public Criteria andBrkpremEqualTo(BigDecimal value) {
            addCriterion("brkprem =", value, "brkprem");
            return (Criteria) this;
        }

        public Criteria andBrkpremNotEqualTo(BigDecimal value) {
            addCriterion("brkprem <>", value, "brkprem");
            return (Criteria) this;
        }

        public Criteria andBrkpremGreaterThan(BigDecimal value) {
            addCriterion("brkprem >", value, "brkprem");
            return (Criteria) this;
        }

        public Criteria andBrkpremGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("brkprem >=", value, "brkprem");
            return (Criteria) this;
        }

        public Criteria andBrkpremLessThan(BigDecimal value) {
            addCriterion("brkprem <", value, "brkprem");
            return (Criteria) this;
        }

        public Criteria andBrkpremLessThanOrEqualTo(BigDecimal value) {
            addCriterion("brkprem <=", value, "brkprem");
            return (Criteria) this;
        }

        public Criteria andBrkpremIn(List<BigDecimal> values) {
            addCriterion("brkprem in", values, "brkprem");
            return (Criteria) this;
        }

        public Criteria andBrkpremNotIn(List<BigDecimal> values) {
            addCriterion("brkprem not in", values, "brkprem");
            return (Criteria) this;
        }

        public Criteria andBrkpremBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("brkprem between", value1, value2, "brkprem");
            return (Criteria) this;
        }

        public Criteria andBrkpremNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("brkprem not between", value1, value2, "brkprem");
            return (Criteria) this;
        }

        public Criteria andBrktypeIsNull() {
            addCriterion("brktype is null");
            return (Criteria) this;
        }

        public Criteria andBrktypeIsNotNull() {
            addCriterion("brktype is not null");
            return (Criteria) this;
        }

        public Criteria andBrktypeEqualTo(String value) {
            addCriterion("brktype =", value, "brktype");
            return (Criteria) this;
        }

        public Criteria andBrktypeNotEqualTo(String value) {
            addCriterion("brktype <>", value, "brktype");
            return (Criteria) this;
        }

        public Criteria andBrktypeGreaterThan(String value) {
            addCriterion("brktype >", value, "brktype");
            return (Criteria) this;
        }

        public Criteria andBrktypeGreaterThanOrEqualTo(String value) {
            addCriterion("brktype >=", value, "brktype");
            return (Criteria) this;
        }

        public Criteria andBrktypeLessThan(String value) {
            addCriterion("brktype <", value, "brktype");
            return (Criteria) this;
        }

        public Criteria andBrktypeLessThanOrEqualTo(String value) {
            addCriterion("brktype <=", value, "brktype");
            return (Criteria) this;
        }

        public Criteria andBrktypeLike(String value) {
            addCriterion("brktype like", value, "brktype");
            return (Criteria) this;
        }

        public Criteria andBrktypeNotLike(String value) {
            addCriterion("brktype not like", value, "brktype");
            return (Criteria) this;
        }

        public Criteria andBrktypeIn(List<String> values) {
            addCriterion("brktype in", values, "brktype");
            return (Criteria) this;
        }

        public Criteria andBrktypeNotIn(List<String> values) {
            addCriterion("brktype not in", values, "brktype");
            return (Criteria) this;
        }

        public Criteria andBrktypeBetween(String value1, String value2) {
            addCriterion("brktype between", value1, value2, "brktype");
            return (Criteria) this;
        }

        public Criteria andBrktypeNotBetween(String value1, String value2) {
            addCriterion("brktype not between", value1, value2, "brktype");
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

        public Criteria andTaxRateIsNull() {
            addCriterion("tax_rate is null");
            return (Criteria) this;
        }

        public Criteria andTaxRateIsNotNull() {
            addCriterion("tax_rate is not null");
            return (Criteria) this;
        }

        public Criteria andTaxRateEqualTo(BigDecimal value) {
            addCriterion("tax_rate =", value, "taxRate");
            return (Criteria) this;
        }

        public Criteria andTaxRateNotEqualTo(BigDecimal value) {
            addCriterion("tax_rate <>", value, "taxRate");
            return (Criteria) this;
        }

        public Criteria andTaxRateGreaterThan(BigDecimal value) {
            addCriterion("tax_rate >", value, "taxRate");
            return (Criteria) this;
        }

        public Criteria andTaxRateGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("tax_rate >=", value, "taxRate");
            return (Criteria) this;
        }

        public Criteria andTaxRateLessThan(BigDecimal value) {
            addCriterion("tax_rate <", value, "taxRate");
            return (Criteria) this;
        }

        public Criteria andTaxRateLessThanOrEqualTo(BigDecimal value) {
            addCriterion("tax_rate <=", value, "taxRate");
            return (Criteria) this;
        }

        public Criteria andTaxRateIn(List<BigDecimal> values) {
            addCriterion("tax_rate in", values, "taxRate");
            return (Criteria) this;
        }

        public Criteria andTaxRateNotIn(List<BigDecimal> values) {
            addCriterion("tax_rate not in", values, "taxRate");
            return (Criteria) this;
        }

        public Criteria andTaxRateBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("tax_rate between", value1, value2, "taxRate");
            return (Criteria) this;
        }

        public Criteria andTaxRateNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("tax_rate not between", value1, value2, "taxRate");
            return (Criteria) this;
        }

        public Criteria andCedeAmtIsNull() {
            addCriterion("cede_amt is null");
            return (Criteria) this;
        }

        public Criteria andCedeAmtIsNotNull() {
            addCriterion("cede_amt is not null");
            return (Criteria) this;
        }

        public Criteria andCedeAmtEqualTo(Long value) {
            addCriterion("cede_amt =", value, "cedeAmt");
            return (Criteria) this;
        }

        public Criteria andCedeAmtNotEqualTo(Long value) {
            addCriterion("cede_amt <>", value, "cedeAmt");
            return (Criteria) this;
        }

        public Criteria andCedeAmtGreaterThan(Long value) {
            addCriterion("cede_amt >", value, "cedeAmt");
            return (Criteria) this;
        }

        public Criteria andCedeAmtGreaterThanOrEqualTo(Long value) {
            addCriterion("cede_amt >=", value, "cedeAmt");
            return (Criteria) this;
        }

        public Criteria andCedeAmtLessThan(Long value) {
            addCriterion("cede_amt <", value, "cedeAmt");
            return (Criteria) this;
        }

        public Criteria andCedeAmtLessThanOrEqualTo(Long value) {
            addCriterion("cede_amt <=", value, "cedeAmt");
            return (Criteria) this;
        }

        public Criteria andCedeAmtIn(List<Long> values) {
            addCriterion("cede_amt in", values, "cedeAmt");
            return (Criteria) this;
        }

        public Criteria andCedeAmtNotIn(List<Long> values) {
            addCriterion("cede_amt not in", values, "cedeAmt");
            return (Criteria) this;
        }

        public Criteria andCedeAmtBetween(Long value1, Long value2) {
            addCriterion("cede_amt between", value1, value2, "cedeAmt");
            return (Criteria) this;
        }

        public Criteria andCedeAmtNotBetween(Long value1, Long value2) {
            addCriterion("cede_amt not between", value1, value2, "cedeAmt");
            return (Criteria) this;
        }

        public Criteria andExcessBgnIsNull() {
            addCriterion("excess_bgn is null");
            return (Criteria) this;
        }

        public Criteria andExcessBgnIsNotNull() {
            addCriterion("excess_bgn is not null");
            return (Criteria) this;
        }

        public Criteria andExcessBgnEqualTo(Long value) {
            addCriterion("excess_bgn =", value, "excessBgn");
            return (Criteria) this;
        }

        public Criteria andExcessBgnNotEqualTo(Long value) {
            addCriterion("excess_bgn <>", value, "excessBgn");
            return (Criteria) this;
        }

        public Criteria andExcessBgnGreaterThan(Long value) {
            addCriterion("excess_bgn >", value, "excessBgn");
            return (Criteria) this;
        }

        public Criteria andExcessBgnGreaterThanOrEqualTo(Long value) {
            addCriterion("excess_bgn >=", value, "excessBgn");
            return (Criteria) this;
        }

        public Criteria andExcessBgnLessThan(Long value) {
            addCriterion("excess_bgn <", value, "excessBgn");
            return (Criteria) this;
        }

        public Criteria andExcessBgnLessThanOrEqualTo(Long value) {
            addCriterion("excess_bgn <=", value, "excessBgn");
            return (Criteria) this;
        }

        public Criteria andExcessBgnIn(List<Long> values) {
            addCriterion("excess_bgn in", values, "excessBgn");
            return (Criteria) this;
        }

        public Criteria andExcessBgnNotIn(List<Long> values) {
            addCriterion("excess_bgn not in", values, "excessBgn");
            return (Criteria) this;
        }

        public Criteria andExcessBgnBetween(Long value1, Long value2) {
            addCriterion("excess_bgn between", value1, value2, "excessBgn");
            return (Criteria) this;
        }

        public Criteria andExcessBgnNotBetween(Long value1, Long value2) {
            addCriterion("excess_bgn not between", value1, value2, "excessBgn");
            return (Criteria) this;
        }

        public Criteria andExcessLimitIsNull() {
            addCriterion("excess_limit is null");
            return (Criteria) this;
        }

        public Criteria andExcessLimitIsNotNull() {
            addCriterion("excess_limit is not null");
            return (Criteria) this;
        }

        public Criteria andExcessLimitEqualTo(Long value) {
            addCriterion("excess_limit =", value, "excessLimit");
            return (Criteria) this;
        }

        public Criteria andExcessLimitNotEqualTo(Long value) {
            addCriterion("excess_limit <>", value, "excessLimit");
            return (Criteria) this;
        }

        public Criteria andExcessLimitGreaterThan(Long value) {
            addCriterion("excess_limit >", value, "excessLimit");
            return (Criteria) this;
        }

        public Criteria andExcessLimitGreaterThanOrEqualTo(Long value) {
            addCriterion("excess_limit >=", value, "excessLimit");
            return (Criteria) this;
        }

        public Criteria andExcessLimitLessThan(Long value) {
            addCriterion("excess_limit <", value, "excessLimit");
            return (Criteria) this;
        }

        public Criteria andExcessLimitLessThanOrEqualTo(Long value) {
            addCriterion("excess_limit <=", value, "excessLimit");
            return (Criteria) this;
        }

        public Criteria andExcessLimitIn(List<Long> values) {
            addCriterion("excess_limit in", values, "excessLimit");
            return (Criteria) this;
        }

        public Criteria andExcessLimitNotIn(List<Long> values) {
            addCriterion("excess_limit not in", values, "excessLimit");
            return (Criteria) this;
        }

        public Criteria andExcessLimitBetween(Long value1, Long value2) {
            addCriterion("excess_limit between", value1, value2, "excessLimit");
            return (Criteria) this;
        }

        public Criteria andExcessLimitNotBetween(Long value1, Long value2) {
            addCriterion("excess_limit not between", value1, value2, "excessLimit");
            return (Criteria) this;
        }

        public Criteria andBrkprem100IsNull() {
            addCriterion("brkprem100 is null");
            return (Criteria) this;
        }

        public Criteria andBrkprem100IsNotNull() {
            addCriterion("brkprem100 is not null");
            return (Criteria) this;
        }

        public Criteria andBrkprem100EqualTo(BigDecimal value) {
            addCriterion("brkprem100 =", value, "brkprem100");
            return (Criteria) this;
        }

        public Criteria andBrkprem100NotEqualTo(BigDecimal value) {
            addCriterion("brkprem100 <>", value, "brkprem100");
            return (Criteria) this;
        }

        public Criteria andBrkprem100GreaterThan(BigDecimal value) {
            addCriterion("brkprem100 >", value, "brkprem100");
            return (Criteria) this;
        }

        public Criteria andBrkprem100GreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("brkprem100 >=", value, "brkprem100");
            return (Criteria) this;
        }

        public Criteria andBrkprem100LessThan(BigDecimal value) {
            addCriterion("brkprem100 <", value, "brkprem100");
            return (Criteria) this;
        }

        public Criteria andBrkprem100LessThanOrEqualTo(BigDecimal value) {
            addCriterion("brkprem100 <=", value, "brkprem100");
            return (Criteria) this;
        }

        public Criteria andBrkprem100In(List<BigDecimal> values) {
            addCriterion("brkprem100 in", values, "brkprem100");
            return (Criteria) this;
        }

        public Criteria andBrkprem100NotIn(List<BigDecimal> values) {
            addCriterion("brkprem100 not in", values, "brkprem100");
            return (Criteria) this;
        }

        public Criteria andBrkprem100Between(BigDecimal value1, BigDecimal value2) {
            addCriterion("brkprem100 between", value1, value2, "brkprem100");
            return (Criteria) this;
        }

        public Criteria andBrkprem100NotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("brkprem100 not between", value1, value2, "brkprem100");
            return (Criteria) this;
        }

        public Criteria andBrkcommRateIsNull() {
            addCriterion("brkcomm_rate is null");
            return (Criteria) this;
        }

        public Criteria andBrkcommRateIsNotNull() {
            addCriterion("brkcomm_rate is not null");
            return (Criteria) this;
        }

        public Criteria andBrkcommRateEqualTo(BigDecimal value) {
            addCriterion("brkcomm_rate =", value, "brkcommRate");
            return (Criteria) this;
        }

        public Criteria andBrkcommRateNotEqualTo(BigDecimal value) {
            addCriterion("brkcomm_rate <>", value, "brkcommRate");
            return (Criteria) this;
        }

        public Criteria andBrkcommRateGreaterThan(BigDecimal value) {
            addCriterion("brkcomm_rate >", value, "brkcommRate");
            return (Criteria) this;
        }

        public Criteria andBrkcommRateGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("brkcomm_rate >=", value, "brkcommRate");
            return (Criteria) this;
        }

        public Criteria andBrkcommRateLessThan(BigDecimal value) {
            addCriterion("brkcomm_rate <", value, "brkcommRate");
            return (Criteria) this;
        }

        public Criteria andBrkcommRateLessThanOrEqualTo(BigDecimal value) {
            addCriterion("brkcomm_rate <=", value, "brkcommRate");
            return (Criteria) this;
        }

        public Criteria andBrkcommRateIn(List<BigDecimal> values) {
            addCriterion("brkcomm_rate in", values, "brkcommRate");
            return (Criteria) this;
        }

        public Criteria andBrkcommRateNotIn(List<BigDecimal> values) {
            addCriterion("brkcomm_rate not in", values, "brkcommRate");
            return (Criteria) this;
        }

        public Criteria andBrkcommRateBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("brkcomm_rate between", value1, value2, "brkcommRate");
            return (Criteria) this;
        }

        public Criteria andBrkcommRateNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("brkcomm_rate not between", value1, value2, "brkcommRate");
            return (Criteria) this;
        }

        public Criteria andBrktaxRateIsNull() {
            addCriterion("brktax_rate is null");
            return (Criteria) this;
        }

        public Criteria andBrktaxRateIsNotNull() {
            addCriterion("brktax_rate is not null");
            return (Criteria) this;
        }

        public Criteria andBrktaxRateEqualTo(BigDecimal value) {
            addCriterion("brktax_rate =", value, "brktaxRate");
            return (Criteria) this;
        }

        public Criteria andBrktaxRateNotEqualTo(BigDecimal value) {
            addCriterion("brktax_rate <>", value, "brktaxRate");
            return (Criteria) this;
        }

        public Criteria andBrktaxRateGreaterThan(BigDecimal value) {
            addCriterion("brktax_rate >", value, "brktaxRate");
            return (Criteria) this;
        }

        public Criteria andBrktaxRateGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("brktax_rate >=", value, "brktaxRate");
            return (Criteria) this;
        }

        public Criteria andBrktaxRateLessThan(BigDecimal value) {
            addCriterion("brktax_rate <", value, "brktaxRate");
            return (Criteria) this;
        }

        public Criteria andBrktaxRateLessThanOrEqualTo(BigDecimal value) {
            addCriterion("brktax_rate <=", value, "brktaxRate");
            return (Criteria) this;
        }

        public Criteria andBrktaxRateIn(List<BigDecimal> values) {
            addCriterion("brktax_rate in", values, "brktaxRate");
            return (Criteria) this;
        }

        public Criteria andBrktaxRateNotIn(List<BigDecimal> values) {
            addCriterion("brktax_rate not in", values, "brktaxRate");
            return (Criteria) this;
        }

        public Criteria andBrktaxRateBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("brktax_rate between", value1, value2, "brktaxRate");
            return (Criteria) this;
        }

        public Criteria andBrktaxRateNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("brktax_rate not between", value1, value2, "brktaxRate");
            return (Criteria) this;
        }

        public Criteria andBrkexcessBgnIsNull() {
            addCriterion("brkexcess_bgn is null");
            return (Criteria) this;
        }

        public Criteria andBrkexcessBgnIsNotNull() {
            addCriterion("brkexcess_bgn is not null");
            return (Criteria) this;
        }

        public Criteria andBrkexcessBgnEqualTo(Long value) {
            addCriterion("brkexcess_bgn =", value, "brkexcessBgn");
            return (Criteria) this;
        }

        public Criteria andBrkexcessBgnNotEqualTo(Long value) {
            addCriterion("brkexcess_bgn <>", value, "brkexcessBgn");
            return (Criteria) this;
        }

        public Criteria andBrkexcessBgnGreaterThan(Long value) {
            addCriterion("brkexcess_bgn >", value, "brkexcessBgn");
            return (Criteria) this;
        }

        public Criteria andBrkexcessBgnGreaterThanOrEqualTo(Long value) {
            addCriterion("brkexcess_bgn >=", value, "brkexcessBgn");
            return (Criteria) this;
        }

        public Criteria andBrkexcessBgnLessThan(Long value) {
            addCriterion("brkexcess_bgn <", value, "brkexcessBgn");
            return (Criteria) this;
        }

        public Criteria andBrkexcessBgnLessThanOrEqualTo(Long value) {
            addCriterion("brkexcess_bgn <=", value, "brkexcessBgn");
            return (Criteria) this;
        }

        public Criteria andBrkexcessBgnIn(List<Long> values) {
            addCriterion("brkexcess_bgn in", values, "brkexcessBgn");
            return (Criteria) this;
        }

        public Criteria andBrkexcessBgnNotIn(List<Long> values) {
            addCriterion("brkexcess_bgn not in", values, "brkexcessBgn");
            return (Criteria) this;
        }

        public Criteria andBrkexcessBgnBetween(Long value1, Long value2) {
            addCriterion("brkexcess_bgn between", value1, value2, "brkexcessBgn");
            return (Criteria) this;
        }

        public Criteria andBrkexcessBgnNotBetween(Long value1, Long value2) {
            addCriterion("brkexcess_bgn not between", value1, value2, "brkexcessBgn");
            return (Criteria) this;
        }

        public Criteria andBrkexcessLimitIsNull() {
            addCriterion("brkexcess_limit is null");
            return (Criteria) this;
        }

        public Criteria andBrkexcessLimitIsNotNull() {
            addCriterion("brkexcess_limit is not null");
            return (Criteria) this;
        }

        public Criteria andBrkexcessLimitEqualTo(Long value) {
            addCriterion("brkexcess_limit =", value, "brkexcessLimit");
            return (Criteria) this;
        }

        public Criteria andBrkexcessLimitNotEqualTo(Long value) {
            addCriterion("brkexcess_limit <>", value, "brkexcessLimit");
            return (Criteria) this;
        }

        public Criteria andBrkexcessLimitGreaterThan(Long value) {
            addCriterion("brkexcess_limit >", value, "brkexcessLimit");
            return (Criteria) this;
        }

        public Criteria andBrkexcessLimitGreaterThanOrEqualTo(Long value) {
            addCriterion("brkexcess_limit >=", value, "brkexcessLimit");
            return (Criteria) this;
        }

        public Criteria andBrkexcessLimitLessThan(Long value) {
            addCriterion("brkexcess_limit <", value, "brkexcessLimit");
            return (Criteria) this;
        }

        public Criteria andBrkexcessLimitLessThanOrEqualTo(Long value) {
            addCriterion("brkexcess_limit <=", value, "brkexcessLimit");
            return (Criteria) this;
        }

        public Criteria andBrkexcessLimitIn(List<Long> values) {
            addCriterion("brkexcess_limit in", values, "brkexcessLimit");
            return (Criteria) this;
        }

        public Criteria andBrkexcessLimitNotIn(List<Long> values) {
            addCriterion("brkexcess_limit not in", values, "brkexcessLimit");
            return (Criteria) this;
        }

        public Criteria andBrkexcessLimitBetween(Long value1, Long value2) {
            addCriterion("brkexcess_limit between", value1, value2, "brkexcessLimit");
            return (Criteria) this;
        }

        public Criteria andBrkexcessLimitNotBetween(Long value1, Long value2) {
            addCriterion("brkexcess_limit not between", value1, value2, "brkexcessLimit");
            return (Criteria) this;
        }

        public Criteria andBrkripremNtIsNull() {
            addCriterion("brkRIprem_NT is null");
            return (Criteria) this;
        }

        public Criteria andBrkripremNtIsNotNull() {
            addCriterion("brkRIprem_NT is not null");
            return (Criteria) this;
        }

        public Criteria andBrkripremNtEqualTo(BigDecimal value) {
            addCriterion("brkRIprem_NT =", value, "brkripremNt");
            return (Criteria) this;
        }

        public Criteria andBrkripremNtNotEqualTo(BigDecimal value) {
            addCriterion("brkRIprem_NT <>", value, "brkripremNt");
            return (Criteria) this;
        }

        public Criteria andBrkripremNtGreaterThan(BigDecimal value) {
            addCriterion("brkRIprem_NT >", value, "brkripremNt");
            return (Criteria) this;
        }

        public Criteria andBrkripremNtGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("brkRIprem_NT >=", value, "brkripremNt");
            return (Criteria) this;
        }

        public Criteria andBrkripremNtLessThan(BigDecimal value) {
            addCriterion("brkRIprem_NT <", value, "brkripremNt");
            return (Criteria) this;
        }

        public Criteria andBrkripremNtLessThanOrEqualTo(BigDecimal value) {
            addCriterion("brkRIprem_NT <=", value, "brkripremNt");
            return (Criteria) this;
        }

        public Criteria andBrkripremNtIn(List<BigDecimal> values) {
            addCriterion("brkRIprem_NT in", values, "brkripremNt");
            return (Criteria) this;
        }

        public Criteria andBrkripremNtNotIn(List<BigDecimal> values) {
            addCriterion("brkRIprem_NT not in", values, "brkripremNt");
            return (Criteria) this;
        }

        public Criteria andBrkripremNtBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("brkRIprem_NT between", value1, value2, "brkripremNt");
            return (Criteria) this;
        }

        public Criteria andBrkripremNtNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("brkRIprem_NT not between", value1, value2, "brkripremNt");
            return (Criteria) this;
        }

        public Criteria andBrkricommNtIsNull() {
            addCriterion("brkRIcomm_NT is null");
            return (Criteria) this;
        }

        public Criteria andBrkricommNtIsNotNull() {
            addCriterion("brkRIcomm_NT is not null");
            return (Criteria) this;
        }

        public Criteria andBrkricommNtEqualTo(BigDecimal value) {
            addCriterion("brkRIcomm_NT =", value, "brkricommNt");
            return (Criteria) this;
        }

        public Criteria andBrkricommNtNotEqualTo(BigDecimal value) {
            addCriterion("brkRIcomm_NT <>", value, "brkricommNt");
            return (Criteria) this;
        }

        public Criteria andBrkricommNtGreaterThan(BigDecimal value) {
            addCriterion("brkRIcomm_NT >", value, "brkricommNt");
            return (Criteria) this;
        }

        public Criteria andBrkricommNtGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("brkRIcomm_NT >=", value, "brkricommNt");
            return (Criteria) this;
        }

        public Criteria andBrkricommNtLessThan(BigDecimal value) {
            addCriterion("brkRIcomm_NT <", value, "brkricommNt");
            return (Criteria) this;
        }

        public Criteria andBrkricommNtLessThanOrEqualTo(BigDecimal value) {
            addCriterion("brkRIcomm_NT <=", value, "brkricommNt");
            return (Criteria) this;
        }

        public Criteria andBrkricommNtIn(List<BigDecimal> values) {
            addCriterion("brkRIcomm_NT in", values, "brkricommNt");
            return (Criteria) this;
        }

        public Criteria andBrkricommNtNotIn(List<BigDecimal> values) {
            addCriterion("brkRIcomm_NT not in", values, "brkricommNt");
            return (Criteria) this;
        }

        public Criteria andBrkricommNtBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("brkRIcomm_NT between", value1, value2, "brkricommNt");
            return (Criteria) this;
        }

        public Criteria andBrkricommNtNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("brkRIcomm_NT not between", value1, value2, "brkricommNt");
            return (Criteria) this;
        }

        public Criteria andBrkripremOrgIsNull() {
            addCriterion("brkRIprem_ORG is null");
            return (Criteria) this;
        }

        public Criteria andBrkripremOrgIsNotNull() {
            addCriterion("brkRIprem_ORG is not null");
            return (Criteria) this;
        }

        public Criteria andBrkripremOrgEqualTo(BigDecimal value) {
            addCriterion("brkRIprem_ORG =", value, "brkripremOrg");
            return (Criteria) this;
        }

        public Criteria andBrkripremOrgNotEqualTo(BigDecimal value) {
            addCriterion("brkRIprem_ORG <>", value, "brkripremOrg");
            return (Criteria) this;
        }

        public Criteria andBrkripremOrgGreaterThan(BigDecimal value) {
            addCriterion("brkRIprem_ORG >", value, "brkripremOrg");
            return (Criteria) this;
        }

        public Criteria andBrkripremOrgGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("brkRIprem_ORG >=", value, "brkripremOrg");
            return (Criteria) this;
        }

        public Criteria andBrkripremOrgLessThan(BigDecimal value) {
            addCriterion("brkRIprem_ORG <", value, "brkripremOrg");
            return (Criteria) this;
        }

        public Criteria andBrkripremOrgLessThanOrEqualTo(BigDecimal value) {
            addCriterion("brkRIprem_ORG <=", value, "brkripremOrg");
            return (Criteria) this;
        }

        public Criteria andBrkripremOrgIn(List<BigDecimal> values) {
            addCriterion("brkRIprem_ORG in", values, "brkripremOrg");
            return (Criteria) this;
        }

        public Criteria andBrkripremOrgNotIn(List<BigDecimal> values) {
            addCriterion("brkRIprem_ORG not in", values, "brkripremOrg");
            return (Criteria) this;
        }

        public Criteria andBrkripremOrgBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("brkRIprem_ORG between", value1, value2, "brkripremOrg");
            return (Criteria) this;
        }

        public Criteria andBrkripremOrgNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("brkRIprem_ORG not between", value1, value2, "brkripremOrg");
            return (Criteria) this;
        }

        public Criteria andBrkricommOrgIsNull() {
            addCriterion("brkRIcomm_ORG is null");
            return (Criteria) this;
        }

        public Criteria andBrkricommOrgIsNotNull() {
            addCriterion("brkRIcomm_ORG is not null");
            return (Criteria) this;
        }

        public Criteria andBrkricommOrgEqualTo(BigDecimal value) {
            addCriterion("brkRIcomm_ORG =", value, "brkricommOrg");
            return (Criteria) this;
        }

        public Criteria andBrkricommOrgNotEqualTo(BigDecimal value) {
            addCriterion("brkRIcomm_ORG <>", value, "brkricommOrg");
            return (Criteria) this;
        }

        public Criteria andBrkricommOrgGreaterThan(BigDecimal value) {
            addCriterion("brkRIcomm_ORG >", value, "brkricommOrg");
            return (Criteria) this;
        }

        public Criteria andBrkricommOrgGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("brkRIcomm_ORG >=", value, "brkricommOrg");
            return (Criteria) this;
        }

        public Criteria andBrkricommOrgLessThan(BigDecimal value) {
            addCriterion("brkRIcomm_ORG <", value, "brkricommOrg");
            return (Criteria) this;
        }

        public Criteria andBrkricommOrgLessThanOrEqualTo(BigDecimal value) {
            addCriterion("brkRIcomm_ORG <=", value, "brkricommOrg");
            return (Criteria) this;
        }

        public Criteria andBrkricommOrgIn(List<BigDecimal> values) {
            addCriterion("brkRIcomm_ORG in", values, "brkricommOrg");
            return (Criteria) this;
        }

        public Criteria andBrkricommOrgNotIn(List<BigDecimal> values) {
            addCriterion("brkRIcomm_ORG not in", values, "brkricommOrg");
            return (Criteria) this;
        }

        public Criteria andBrkricommOrgBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("brkRIcomm_ORG between", value1, value2, "brkricommOrg");
            return (Criteria) this;
        }

        public Criteria andBrkricommOrgNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("brkRIcomm_ORG not between", value1, value2, "brkricommOrg");
            return (Criteria) this;
        }

        public Criteria andBrkritaxNtIsNull() {
            addCriterion("brkRItax_NT is null");
            return (Criteria) this;
        }

        public Criteria andBrkritaxNtIsNotNull() {
            addCriterion("brkRItax_NT is not null");
            return (Criteria) this;
        }

        public Criteria andBrkritaxNtEqualTo(BigDecimal value) {
            addCriterion("brkRItax_NT =", value, "brkritaxNt");
            return (Criteria) this;
        }

        public Criteria andBrkritaxNtNotEqualTo(BigDecimal value) {
            addCriterion("brkRItax_NT <>", value, "brkritaxNt");
            return (Criteria) this;
        }

        public Criteria andBrkritaxNtGreaterThan(BigDecimal value) {
            addCriterion("brkRItax_NT >", value, "brkritaxNt");
            return (Criteria) this;
        }

        public Criteria andBrkritaxNtGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("brkRItax_NT >=", value, "brkritaxNt");
            return (Criteria) this;
        }

        public Criteria andBrkritaxNtLessThan(BigDecimal value) {
            addCriterion("brkRItax_NT <", value, "brkritaxNt");
            return (Criteria) this;
        }

        public Criteria andBrkritaxNtLessThanOrEqualTo(BigDecimal value) {
            addCriterion("brkRItax_NT <=", value, "brkritaxNt");
            return (Criteria) this;
        }

        public Criteria andBrkritaxNtIn(List<BigDecimal> values) {
            addCriterion("brkRItax_NT in", values, "brkritaxNt");
            return (Criteria) this;
        }

        public Criteria andBrkritaxNtNotIn(List<BigDecimal> values) {
            addCriterion("brkRItax_NT not in", values, "brkritaxNt");
            return (Criteria) this;
        }

        public Criteria andBrkritaxNtBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("brkRItax_NT between", value1, value2, "brkritaxNt");
            return (Criteria) this;
        }

        public Criteria andBrkritaxNtNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("brkRItax_NT not between", value1, value2, "brkritaxNt");
            return (Criteria) this;
        }

        public Criteria andBrkritaxOrgIsNull() {
            addCriterion("brkRItax_ORG is null");
            return (Criteria) this;
        }

        public Criteria andBrkritaxOrgIsNotNull() {
            addCriterion("brkRItax_ORG is not null");
            return (Criteria) this;
        }

        public Criteria andBrkritaxOrgEqualTo(BigDecimal value) {
            addCriterion("brkRItax_ORG =", value, "brkritaxOrg");
            return (Criteria) this;
        }

        public Criteria andBrkritaxOrgNotEqualTo(BigDecimal value) {
            addCriterion("brkRItax_ORG <>", value, "brkritaxOrg");
            return (Criteria) this;
        }

        public Criteria andBrkritaxOrgGreaterThan(BigDecimal value) {
            addCriterion("brkRItax_ORG >", value, "brkritaxOrg");
            return (Criteria) this;
        }

        public Criteria andBrkritaxOrgGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("brkRItax_ORG >=", value, "brkritaxOrg");
            return (Criteria) this;
        }

        public Criteria andBrkritaxOrgLessThan(BigDecimal value) {
            addCriterion("brkRItax_ORG <", value, "brkritaxOrg");
            return (Criteria) this;
        }

        public Criteria andBrkritaxOrgLessThanOrEqualTo(BigDecimal value) {
            addCriterion("brkRItax_ORG <=", value, "brkritaxOrg");
            return (Criteria) this;
        }

        public Criteria andBrkritaxOrgIn(List<BigDecimal> values) {
            addCriterion("brkRItax_ORG in", values, "brkritaxOrg");
            return (Criteria) this;
        }

        public Criteria andBrkritaxOrgNotIn(List<BigDecimal> values) {
            addCriterion("brkRItax_ORG not in", values, "brkritaxOrg");
            return (Criteria) this;
        }

        public Criteria andBrkritaxOrgBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("brkRItax_ORG between", value1, value2, "brkritaxOrg");
            return (Criteria) this;
        }

        public Criteria andBrkritaxOrgNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("brkRItax_ORG not between", value1, value2, "brkritaxOrg");
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