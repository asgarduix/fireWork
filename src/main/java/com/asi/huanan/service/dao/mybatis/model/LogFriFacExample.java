package com.asi.huanan.service.dao.mybatis.model;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class LogFriFacExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public LogFriFacExample() {
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

        public Criteria andSlipTypeIsNull() {
            addCriterion("slip_type is null");
            return (Criteria) this;
        }

        public Criteria andSlipTypeIsNotNull() {
            addCriterion("slip_type is not null");
            return (Criteria) this;
        }

        public Criteria andSlipTypeEqualTo(String value) {
            addCriterion("slip_type =", value, "slipType");
            return (Criteria) this;
        }

        public Criteria andSlipTypeNotEqualTo(String value) {
            addCriterion("slip_type <>", value, "slipType");
            return (Criteria) this;
        }

        public Criteria andSlipTypeGreaterThan(String value) {
            addCriterion("slip_type >", value, "slipType");
            return (Criteria) this;
        }

        public Criteria andSlipTypeGreaterThanOrEqualTo(String value) {
            addCriterion("slip_type >=", value, "slipType");
            return (Criteria) this;
        }

        public Criteria andSlipTypeLessThan(String value) {
            addCriterion("slip_type <", value, "slipType");
            return (Criteria) this;
        }

        public Criteria andSlipTypeLessThanOrEqualTo(String value) {
            addCriterion("slip_type <=", value, "slipType");
            return (Criteria) this;
        }

        public Criteria andSlipTypeLike(String value) {
            addCriterion("slip_type like", value, "slipType");
            return (Criteria) this;
        }

        public Criteria andSlipTypeNotLike(String value) {
            addCriterion("slip_type not like", value, "slipType");
            return (Criteria) this;
        }

        public Criteria andSlipTypeIn(List<String> values) {
            addCriterion("slip_type in", values, "slipType");
            return (Criteria) this;
        }

        public Criteria andSlipTypeNotIn(List<String> values) {
            addCriterion("slip_type not in", values, "slipType");
            return (Criteria) this;
        }

        public Criteria andSlipTypeBetween(String value1, String value2) {
            addCriterion("slip_type between", value1, value2, "slipType");
            return (Criteria) this;
        }

        public Criteria andSlipTypeNotBetween(String value1, String value2) {
            addCriterion("slip_type not between", value1, value2, "slipType");
            return (Criteria) this;
        }

        public Criteria andCessionNoIsNull() {
            addCriterion("cession_no is null");
            return (Criteria) this;
        }

        public Criteria andCessionNoIsNotNull() {
            addCriterion("cession_no is not null");
            return (Criteria) this;
        }

        public Criteria andCessionNoEqualTo(String value) {
            addCriterion("cession_no =", value, "cessionNo");
            return (Criteria) this;
        }

        public Criteria andCessionNoNotEqualTo(String value) {
            addCriterion("cession_no <>", value, "cessionNo");
            return (Criteria) this;
        }

        public Criteria andCessionNoGreaterThan(String value) {
            addCriterion("cession_no >", value, "cessionNo");
            return (Criteria) this;
        }

        public Criteria andCessionNoGreaterThanOrEqualTo(String value) {
            addCriterion("cession_no >=", value, "cessionNo");
            return (Criteria) this;
        }

        public Criteria andCessionNoLessThan(String value) {
            addCriterion("cession_no <", value, "cessionNo");
            return (Criteria) this;
        }

        public Criteria andCessionNoLessThanOrEqualTo(String value) {
            addCriterion("cession_no <=", value, "cessionNo");
            return (Criteria) this;
        }

        public Criteria andCessionNoLike(String value) {
            addCriterion("cession_no like", value, "cessionNo");
            return (Criteria) this;
        }

        public Criteria andCessionNoNotLike(String value) {
            addCriterion("cession_no not like", value, "cessionNo");
            return (Criteria) this;
        }

        public Criteria andCessionNoIn(List<String> values) {
            addCriterion("cession_no in", values, "cessionNo");
            return (Criteria) this;
        }

        public Criteria andCessionNoNotIn(List<String> values) {
            addCriterion("cession_no not in", values, "cessionNo");
            return (Criteria) this;
        }

        public Criteria andCessionNoBetween(String value1, String value2) {
            addCriterion("cession_no between", value1, value2, "cessionNo");
            return (Criteria) this;
        }

        public Criteria andCessionNoNotBetween(String value1, String value2) {
            addCriterion("cession_no not between", value1, value2, "cessionNo");
            return (Criteria) this;
        }

        public Criteria andPreSlipNoIsNull() {
            addCriterion("pre_slip_no is null");
            return (Criteria) this;
        }

        public Criteria andPreSlipNoIsNotNull() {
            addCriterion("pre_slip_no is not null");
            return (Criteria) this;
        }

        public Criteria andPreSlipNoEqualTo(String value) {
            addCriterion("pre_slip_no =", value, "preSlipNo");
            return (Criteria) this;
        }

        public Criteria andPreSlipNoNotEqualTo(String value) {
            addCriterion("pre_slip_no <>", value, "preSlipNo");
            return (Criteria) this;
        }

        public Criteria andPreSlipNoGreaterThan(String value) {
            addCriterion("pre_slip_no >", value, "preSlipNo");
            return (Criteria) this;
        }

        public Criteria andPreSlipNoGreaterThanOrEqualTo(String value) {
            addCriterion("pre_slip_no >=", value, "preSlipNo");
            return (Criteria) this;
        }

        public Criteria andPreSlipNoLessThan(String value) {
            addCriterion("pre_slip_no <", value, "preSlipNo");
            return (Criteria) this;
        }

        public Criteria andPreSlipNoLessThanOrEqualTo(String value) {
            addCriterion("pre_slip_no <=", value, "preSlipNo");
            return (Criteria) this;
        }

        public Criteria andPreSlipNoLike(String value) {
            addCriterion("pre_slip_no like", value, "preSlipNo");
            return (Criteria) this;
        }

        public Criteria andPreSlipNoNotLike(String value) {
            addCriterion("pre_slip_no not like", value, "preSlipNo");
            return (Criteria) this;
        }

        public Criteria andPreSlipNoIn(List<String> values) {
            addCriterion("pre_slip_no in", values, "preSlipNo");
            return (Criteria) this;
        }

        public Criteria andPreSlipNoNotIn(List<String> values) {
            addCriterion("pre_slip_no not in", values, "preSlipNo");
            return (Criteria) this;
        }

        public Criteria andPreSlipNoBetween(String value1, String value2) {
            addCriterion("pre_slip_no between", value1, value2, "preSlipNo");
            return (Criteria) this;
        }

        public Criteria andPreSlipNoNotBetween(String value1, String value2) {
            addCriterion("pre_slip_no not between", value1, value2, "preSlipNo");
            return (Criteria) this;
        }

        public Criteria andPreCessionNoIsNull() {
            addCriterion("pre_cession_no is null");
            return (Criteria) this;
        }

        public Criteria andPreCessionNoIsNotNull() {
            addCriterion("pre_cession_no is not null");
            return (Criteria) this;
        }

        public Criteria andPreCessionNoEqualTo(String value) {
            addCriterion("pre_cession_no =", value, "preCessionNo");
            return (Criteria) this;
        }

        public Criteria andPreCessionNoNotEqualTo(String value) {
            addCriterion("pre_cession_no <>", value, "preCessionNo");
            return (Criteria) this;
        }

        public Criteria andPreCessionNoGreaterThan(String value) {
            addCriterion("pre_cession_no >", value, "preCessionNo");
            return (Criteria) this;
        }

        public Criteria andPreCessionNoGreaterThanOrEqualTo(String value) {
            addCriterion("pre_cession_no >=", value, "preCessionNo");
            return (Criteria) this;
        }

        public Criteria andPreCessionNoLessThan(String value) {
            addCriterion("pre_cession_no <", value, "preCessionNo");
            return (Criteria) this;
        }

        public Criteria andPreCessionNoLessThanOrEqualTo(String value) {
            addCriterion("pre_cession_no <=", value, "preCessionNo");
            return (Criteria) this;
        }

        public Criteria andPreCessionNoLike(String value) {
            addCriterion("pre_cession_no like", value, "preCessionNo");
            return (Criteria) this;
        }

        public Criteria andPreCessionNoNotLike(String value) {
            addCriterion("pre_cession_no not like", value, "preCessionNo");
            return (Criteria) this;
        }

        public Criteria andPreCessionNoIn(List<String> values) {
            addCriterion("pre_cession_no in", values, "preCessionNo");
            return (Criteria) this;
        }

        public Criteria andPreCessionNoNotIn(List<String> values) {
            addCriterion("pre_cession_no not in", values, "preCessionNo");
            return (Criteria) this;
        }

        public Criteria andPreCessionNoBetween(String value1, String value2) {
            addCriterion("pre_cession_no between", value1, value2, "preCessionNo");
            return (Criteria) this;
        }

        public Criteria andPreCessionNoNotBetween(String value1, String value2) {
            addCriterion("pre_cession_no not between", value1, value2, "preCessionNo");
            return (Criteria) this;
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

        public Criteria andTreatyDateIsNull() {
            addCriterion("treaty_date is null");
            return (Criteria) this;
        }

        public Criteria andTreatyDateIsNotNull() {
            addCriterion("treaty_date is not null");
            return (Criteria) this;
        }

        public Criteria andTreatyDateEqualTo(Date value) {
            addCriterion("treaty_date =", value, "treatyDate");
            return (Criteria) this;
        }

        public Criteria andTreatyDateNotEqualTo(Date value) {
            addCriterion("treaty_date <>", value, "treatyDate");
            return (Criteria) this;
        }

        public Criteria andTreatyDateGreaterThan(Date value) {
            addCriterion("treaty_date >", value, "treatyDate");
            return (Criteria) this;
        }

        public Criteria andTreatyDateGreaterThanOrEqualTo(Date value) {
            addCriterion("treaty_date >=", value, "treatyDate");
            return (Criteria) this;
        }

        public Criteria andTreatyDateLessThan(Date value) {
            addCriterion("treaty_date <", value, "treatyDate");
            return (Criteria) this;
        }

        public Criteria andTreatyDateLessThanOrEqualTo(Date value) {
            addCriterion("treaty_date <=", value, "treatyDate");
            return (Criteria) this;
        }

        public Criteria andTreatyDateIn(List<Date> values) {
            addCriterion("treaty_date in", values, "treatyDate");
            return (Criteria) this;
        }

        public Criteria andTreatyDateNotIn(List<Date> values) {
            addCriterion("treaty_date not in", values, "treatyDate");
            return (Criteria) this;
        }

        public Criteria andTreatyDateBetween(Date value1, Date value2) {
            addCriterion("treaty_date between", value1, value2, "treatyDate");
            return (Criteria) this;
        }

        public Criteria andTreatyDateNotBetween(Date value1, Date value2) {
            addCriterion("treaty_date not between", value1, value2, "treatyDate");
            return (Criteria) this;
        }

        public Criteria andCessionNameIsNull() {
            addCriterion("cession_name is null");
            return (Criteria) this;
        }

        public Criteria andCessionNameIsNotNull() {
            addCriterion("cession_name is not null");
            return (Criteria) this;
        }

        public Criteria andCessionNameEqualTo(String value) {
            addCriterion("cession_name =", value, "cessionName");
            return (Criteria) this;
        }

        public Criteria andCessionNameNotEqualTo(String value) {
            addCriterion("cession_name <>", value, "cessionName");
            return (Criteria) this;
        }

        public Criteria andCessionNameGreaterThan(String value) {
            addCriterion("cession_name >", value, "cessionName");
            return (Criteria) this;
        }

        public Criteria andCessionNameGreaterThanOrEqualTo(String value) {
            addCriterion("cession_name >=", value, "cessionName");
            return (Criteria) this;
        }

        public Criteria andCessionNameLessThan(String value) {
            addCriterion("cession_name <", value, "cessionName");
            return (Criteria) this;
        }

        public Criteria andCessionNameLessThanOrEqualTo(String value) {
            addCriterion("cession_name <=", value, "cessionName");
            return (Criteria) this;
        }

        public Criteria andCessionNameLike(String value) {
            addCriterion("cession_name like", value, "cessionName");
            return (Criteria) this;
        }

        public Criteria andCessionNameNotLike(String value) {
            addCriterion("cession_name not like", value, "cessionName");
            return (Criteria) this;
        }

        public Criteria andCessionNameIn(List<String> values) {
            addCriterion("cession_name in", values, "cessionName");
            return (Criteria) this;
        }

        public Criteria andCessionNameNotIn(List<String> values) {
            addCriterion("cession_name not in", values, "cessionName");
            return (Criteria) this;
        }

        public Criteria andCessionNameBetween(String value1, String value2) {
            addCriterion("cession_name between", value1, value2, "cessionName");
            return (Criteria) this;
        }

        public Criteria andCessionNameNotBetween(String value1, String value2) {
            addCriterion("cession_name not between", value1, value2, "cessionName");
            return (Criteria) this;
        }

        public Criteria andRiskNoIsNull() {
            addCriterion("risk_no is null");
            return (Criteria) this;
        }

        public Criteria andRiskNoIsNotNull() {
            addCriterion("risk_no is not null");
            return (Criteria) this;
        }

        public Criteria andRiskNoEqualTo(String value) {
            addCriterion("risk_no =", value, "riskNo");
            return (Criteria) this;
        }

        public Criteria andRiskNoNotEqualTo(String value) {
            addCriterion("risk_no <>", value, "riskNo");
            return (Criteria) this;
        }

        public Criteria andRiskNoGreaterThan(String value) {
            addCriterion("risk_no >", value, "riskNo");
            return (Criteria) this;
        }

        public Criteria andRiskNoGreaterThanOrEqualTo(String value) {
            addCriterion("risk_no >=", value, "riskNo");
            return (Criteria) this;
        }

        public Criteria andRiskNoLessThan(String value) {
            addCriterion("risk_no <", value, "riskNo");
            return (Criteria) this;
        }

        public Criteria andRiskNoLessThanOrEqualTo(String value) {
            addCriterion("risk_no <=", value, "riskNo");
            return (Criteria) this;
        }

        public Criteria andRiskNoLike(String value) {
            addCriterion("risk_no like", value, "riskNo");
            return (Criteria) this;
        }

        public Criteria andRiskNoNotLike(String value) {
            addCriterion("risk_no not like", value, "riskNo");
            return (Criteria) this;
        }

        public Criteria andRiskNoIn(List<String> values) {
            addCriterion("risk_no in", values, "riskNo");
            return (Criteria) this;
        }

        public Criteria andRiskNoNotIn(List<String> values) {
            addCriterion("risk_no not in", values, "riskNo");
            return (Criteria) this;
        }

        public Criteria andRiskNoBetween(String value1, String value2) {
            addCriterion("risk_no between", value1, value2, "riskNo");
            return (Criteria) this;
        }

        public Criteria andRiskNoNotBetween(String value1, String value2) {
            addCriterion("risk_no not between", value1, value2, "riskNo");
            return (Criteria) this;
        }

        public Criteria andTreatyDbgnIsNull() {
            addCriterion("treaty_dbgn is null");
            return (Criteria) this;
        }

        public Criteria andTreatyDbgnIsNotNull() {
            addCriterion("treaty_dbgn is not null");
            return (Criteria) this;
        }

        public Criteria andTreatyDbgnEqualTo(Date value) {
            addCriterion("treaty_dbgn =", value, "treatyDbgn");
            return (Criteria) this;
        }

        public Criteria andTreatyDbgnNotEqualTo(Date value) {
            addCriterion("treaty_dbgn <>", value, "treatyDbgn");
            return (Criteria) this;
        }

        public Criteria andTreatyDbgnGreaterThan(Date value) {
            addCriterion("treaty_dbgn >", value, "treatyDbgn");
            return (Criteria) this;
        }

        public Criteria andTreatyDbgnGreaterThanOrEqualTo(Date value) {
            addCriterion("treaty_dbgn >=", value, "treatyDbgn");
            return (Criteria) this;
        }

        public Criteria andTreatyDbgnLessThan(Date value) {
            addCriterion("treaty_dbgn <", value, "treatyDbgn");
            return (Criteria) this;
        }

        public Criteria andTreatyDbgnLessThanOrEqualTo(Date value) {
            addCriterion("treaty_dbgn <=", value, "treatyDbgn");
            return (Criteria) this;
        }

        public Criteria andTreatyDbgnIn(List<Date> values) {
            addCriterion("treaty_dbgn in", values, "treatyDbgn");
            return (Criteria) this;
        }

        public Criteria andTreatyDbgnNotIn(List<Date> values) {
            addCriterion("treaty_dbgn not in", values, "treatyDbgn");
            return (Criteria) this;
        }

        public Criteria andTreatyDbgnBetween(Date value1, Date value2) {
            addCriterion("treaty_dbgn between", value1, value2, "treatyDbgn");
            return (Criteria) this;
        }

        public Criteria andTreatyDbgnNotBetween(Date value1, Date value2) {
            addCriterion("treaty_dbgn not between", value1, value2, "treatyDbgn");
            return (Criteria) this;
        }

        public Criteria andTreatyDendIsNull() {
            addCriterion("treaty_dend is null");
            return (Criteria) this;
        }

        public Criteria andTreatyDendIsNotNull() {
            addCriterion("treaty_dend is not null");
            return (Criteria) this;
        }

        public Criteria andTreatyDendEqualTo(Date value) {
            addCriterion("treaty_dend =", value, "treatyDend");
            return (Criteria) this;
        }

        public Criteria andTreatyDendNotEqualTo(Date value) {
            addCriterion("treaty_dend <>", value, "treatyDend");
            return (Criteria) this;
        }

        public Criteria andTreatyDendGreaterThan(Date value) {
            addCriterion("treaty_dend >", value, "treatyDend");
            return (Criteria) this;
        }

        public Criteria andTreatyDendGreaterThanOrEqualTo(Date value) {
            addCriterion("treaty_dend >=", value, "treatyDend");
            return (Criteria) this;
        }

        public Criteria andTreatyDendLessThan(Date value) {
            addCriterion("treaty_dend <", value, "treatyDend");
            return (Criteria) this;
        }

        public Criteria andTreatyDendLessThanOrEqualTo(Date value) {
            addCriterion("treaty_dend <=", value, "treatyDend");
            return (Criteria) this;
        }

        public Criteria andTreatyDendIn(List<Date> values) {
            addCriterion("treaty_dend in", values, "treatyDend");
            return (Criteria) this;
        }

        public Criteria andTreatyDendNotIn(List<Date> values) {
            addCriterion("treaty_dend not in", values, "treatyDend");
            return (Criteria) this;
        }

        public Criteria andTreatyDendBetween(Date value1, Date value2) {
            addCriterion("treaty_dend between", value1, value2, "treatyDend");
            return (Criteria) this;
        }

        public Criteria andTreatyDendNotBetween(Date value1, Date value2) {
            addCriterion("treaty_dend not between", value1, value2, "treatyDend");
            return (Criteria) this;
        }

        public Criteria andDaysIsNull() {
            addCriterion("days is null");
            return (Criteria) this;
        }

        public Criteria andDaysIsNotNull() {
            addCriterion("days is not null");
            return (Criteria) this;
        }

        public Criteria andDaysEqualTo(Short value) {
            addCriterion("days =", value, "days");
            return (Criteria) this;
        }

        public Criteria andDaysNotEqualTo(Short value) {
            addCriterion("days <>", value, "days");
            return (Criteria) this;
        }

        public Criteria andDaysGreaterThan(Short value) {
            addCriterion("days >", value, "days");
            return (Criteria) this;
        }

        public Criteria andDaysGreaterThanOrEqualTo(Short value) {
            addCriterion("days >=", value, "days");
            return (Criteria) this;
        }

        public Criteria andDaysLessThan(Short value) {
            addCriterion("days <", value, "days");
            return (Criteria) this;
        }

        public Criteria andDaysLessThanOrEqualTo(Short value) {
            addCriterion("days <=", value, "days");
            return (Criteria) this;
        }

        public Criteria andDaysIn(List<Short> values) {
            addCriterion("days in", values, "days");
            return (Criteria) this;
        }

        public Criteria andDaysNotIn(List<Short> values) {
            addCriterion("days not in", values, "days");
            return (Criteria) this;
        }

        public Criteria andDaysBetween(Short value1, Short value2) {
            addCriterion("days between", value1, value2, "days");
            return (Criteria) this;
        }

        public Criteria andDaysNotBetween(Short value1, Short value2) {
            addCriterion("days not between", value1, value2, "days");
            return (Criteria) this;
        }

        public Criteria andRiskTypeIsNull() {
            addCriterion("risk_type is null");
            return (Criteria) this;
        }

        public Criteria andRiskTypeIsNotNull() {
            addCriterion("risk_type is not null");
            return (Criteria) this;
        }

        public Criteria andRiskTypeEqualTo(String value) {
            addCriterion("risk_type =", value, "riskType");
            return (Criteria) this;
        }

        public Criteria andRiskTypeNotEqualTo(String value) {
            addCriterion("risk_type <>", value, "riskType");
            return (Criteria) this;
        }

        public Criteria andRiskTypeGreaterThan(String value) {
            addCriterion("risk_type >", value, "riskType");
            return (Criteria) this;
        }

        public Criteria andRiskTypeGreaterThanOrEqualTo(String value) {
            addCriterion("risk_type >=", value, "riskType");
            return (Criteria) this;
        }

        public Criteria andRiskTypeLessThan(String value) {
            addCriterion("risk_type <", value, "riskType");
            return (Criteria) this;
        }

        public Criteria andRiskTypeLessThanOrEqualTo(String value) {
            addCriterion("risk_type <=", value, "riskType");
            return (Criteria) this;
        }

        public Criteria andRiskTypeLike(String value) {
            addCriterion("risk_type like", value, "riskType");
            return (Criteria) this;
        }

        public Criteria andRiskTypeNotLike(String value) {
            addCriterion("risk_type not like", value, "riskType");
            return (Criteria) this;
        }

        public Criteria andRiskTypeIn(List<String> values) {
            addCriterion("risk_type in", values, "riskType");
            return (Criteria) this;
        }

        public Criteria andRiskTypeNotIn(List<String> values) {
            addCriterion("risk_type not in", values, "riskType");
            return (Criteria) this;
        }

        public Criteria andRiskTypeBetween(String value1, String value2) {
            addCriterion("risk_type between", value1, value2, "riskType");
            return (Criteria) this;
        }

        public Criteria andRiskTypeNotBetween(String value1, String value2) {
            addCriterion("risk_type not between", value1, value2, "riskType");
            return (Criteria) this;
        }

        public Criteria andDeductDescIsNull() {
            addCriterion("deduct_desc is null");
            return (Criteria) this;
        }

        public Criteria andDeductDescIsNotNull() {
            addCriterion("deduct_desc is not null");
            return (Criteria) this;
        }

        public Criteria andDeductDescEqualTo(String value) {
            addCriterion("deduct_desc =", value, "deductDesc");
            return (Criteria) this;
        }

        public Criteria andDeductDescNotEqualTo(String value) {
            addCriterion("deduct_desc <>", value, "deductDesc");
            return (Criteria) this;
        }

        public Criteria andDeductDescGreaterThan(String value) {
            addCriterion("deduct_desc >", value, "deductDesc");
            return (Criteria) this;
        }

        public Criteria andDeductDescGreaterThanOrEqualTo(String value) {
            addCriterion("deduct_desc >=", value, "deductDesc");
            return (Criteria) this;
        }

        public Criteria andDeductDescLessThan(String value) {
            addCriterion("deduct_desc <", value, "deductDesc");
            return (Criteria) this;
        }

        public Criteria andDeductDescLessThanOrEqualTo(String value) {
            addCriterion("deduct_desc <=", value, "deductDesc");
            return (Criteria) this;
        }

        public Criteria andDeductDescLike(String value) {
            addCriterion("deduct_desc like", value, "deductDesc");
            return (Criteria) this;
        }

        public Criteria andDeductDescNotLike(String value) {
            addCriterion("deduct_desc not like", value, "deductDesc");
            return (Criteria) this;
        }

        public Criteria andDeductDescIn(List<String> values) {
            addCriterion("deduct_desc in", values, "deductDesc");
            return (Criteria) this;
        }

        public Criteria andDeductDescNotIn(List<String> values) {
            addCriterion("deduct_desc not in", values, "deductDesc");
            return (Criteria) this;
        }

        public Criteria andDeductDescBetween(String value1, String value2) {
            addCriterion("deduct_desc between", value1, value2, "deductDesc");
            return (Criteria) this;
        }

        public Criteria andDeductDescNotBetween(String value1, String value2) {
            addCriterion("deduct_desc not between", value1, value2, "deductDesc");
            return (Criteria) this;
        }

        public Criteria andPolicyNoIsNull() {
            addCriterion("policy_no is null");
            return (Criteria) this;
        }

        public Criteria andPolicyNoIsNotNull() {
            addCriterion("policy_no is not null");
            return (Criteria) this;
        }

        public Criteria andPolicyNoEqualTo(String value) {
            addCriterion("policy_no =", value, "policyNo");
            return (Criteria) this;
        }

        public Criteria andPolicyNoNotEqualTo(String value) {
            addCriterion("policy_no <>", value, "policyNo");
            return (Criteria) this;
        }

        public Criteria andPolicyNoGreaterThan(String value) {
            addCriterion("policy_no >", value, "policyNo");
            return (Criteria) this;
        }

        public Criteria andPolicyNoGreaterThanOrEqualTo(String value) {
            addCriterion("policy_no >=", value, "policyNo");
            return (Criteria) this;
        }

        public Criteria andPolicyNoLessThan(String value) {
            addCriterion("policy_no <", value, "policyNo");
            return (Criteria) this;
        }

        public Criteria andPolicyNoLessThanOrEqualTo(String value) {
            addCriterion("policy_no <=", value, "policyNo");
            return (Criteria) this;
        }

        public Criteria andPolicyNoLike(String value) {
            addCriterion("policy_no like", value, "policyNo");
            return (Criteria) this;
        }

        public Criteria andPolicyNoNotLike(String value) {
            addCriterion("policy_no not like", value, "policyNo");
            return (Criteria) this;
        }

        public Criteria andPolicyNoIn(List<String> values) {
            addCriterion("policy_no in", values, "policyNo");
            return (Criteria) this;
        }

        public Criteria andPolicyNoNotIn(List<String> values) {
            addCriterion("policy_no not in", values, "policyNo");
            return (Criteria) this;
        }

        public Criteria andPolicyNoBetween(String value1, String value2) {
            addCriterion("policy_no between", value1, value2, "policyNo");
            return (Criteria) this;
        }

        public Criteria andPolicyNoNotBetween(String value1, String value2) {
            addCriterion("policy_no not between", value1, value2, "policyNo");
            return (Criteria) this;
        }

        public Criteria andInsurantIsNull() {
            addCriterion("insurant is null");
            return (Criteria) this;
        }

        public Criteria andInsurantIsNotNull() {
            addCriterion("insurant is not null");
            return (Criteria) this;
        }

        public Criteria andInsurantEqualTo(String value) {
            addCriterion("insurant =", value, "insurant");
            return (Criteria) this;
        }

        public Criteria andInsurantNotEqualTo(String value) {
            addCriterion("insurant <>", value, "insurant");
            return (Criteria) this;
        }

        public Criteria andInsurantGreaterThan(String value) {
            addCriterion("insurant >", value, "insurant");
            return (Criteria) this;
        }

        public Criteria andInsurantGreaterThanOrEqualTo(String value) {
            addCriterion("insurant >=", value, "insurant");
            return (Criteria) this;
        }

        public Criteria andInsurantLessThan(String value) {
            addCriterion("insurant <", value, "insurant");
            return (Criteria) this;
        }

        public Criteria andInsurantLessThanOrEqualTo(String value) {
            addCriterion("insurant <=", value, "insurant");
            return (Criteria) this;
        }

        public Criteria andInsurantLike(String value) {
            addCriterion("insurant like", value, "insurant");
            return (Criteria) this;
        }

        public Criteria andInsurantNotLike(String value) {
            addCriterion("insurant not like", value, "insurant");
            return (Criteria) this;
        }

        public Criteria andInsurantIn(List<String> values) {
            addCriterion("insurant in", values, "insurant");
            return (Criteria) this;
        }

        public Criteria andInsurantNotIn(List<String> values) {
            addCriterion("insurant not in", values, "insurant");
            return (Criteria) this;
        }

        public Criteria andInsurantBetween(String value1, String value2) {
            addCriterion("insurant between", value1, value2, "insurant");
            return (Criteria) this;
        }

        public Criteria andInsurantNotBetween(String value1, String value2) {
            addCriterion("insurant not between", value1, value2, "insurant");
            return (Criteria) this;
        }

        public Criteria andAddressIsNull() {
            addCriterion("address is null");
            return (Criteria) this;
        }

        public Criteria andAddressIsNotNull() {
            addCriterion("address is not null");
            return (Criteria) this;
        }

        public Criteria andAddressEqualTo(String value) {
            addCriterion("address =", value, "address");
            return (Criteria) this;
        }

        public Criteria andAddressNotEqualTo(String value) {
            addCriterion("address <>", value, "address");
            return (Criteria) this;
        }

        public Criteria andAddressGreaterThan(String value) {
            addCriterion("address >", value, "address");
            return (Criteria) this;
        }

        public Criteria andAddressGreaterThanOrEqualTo(String value) {
            addCriterion("address >=", value, "address");
            return (Criteria) this;
        }

        public Criteria andAddressLessThan(String value) {
            addCriterion("address <", value, "address");
            return (Criteria) this;
        }

        public Criteria andAddressLessThanOrEqualTo(String value) {
            addCriterion("address <=", value, "address");
            return (Criteria) this;
        }

        public Criteria andAddressLike(String value) {
            addCriterion("address like", value, "address");
            return (Criteria) this;
        }

        public Criteria andAddressNotLike(String value) {
            addCriterion("address not like", value, "address");
            return (Criteria) this;
        }

        public Criteria andAddressIn(List<String> values) {
            addCriterion("address in", values, "address");
            return (Criteria) this;
        }

        public Criteria andAddressNotIn(List<String> values) {
            addCriterion("address not in", values, "address");
            return (Criteria) this;
        }

        public Criteria andAddressBetween(String value1, String value2) {
            addCriterion("address between", value1, value2, "address");
            return (Criteria) this;
        }

        public Criteria andAddressNotBetween(String value1, String value2) {
            addCriterion("address not between", value1, value2, "address");
            return (Criteria) this;
        }

        public Criteria andUsePropIsNull() {
            addCriterion("use_prop is null");
            return (Criteria) this;
        }

        public Criteria andUsePropIsNotNull() {
            addCriterion("use_prop is not null");
            return (Criteria) this;
        }

        public Criteria andUsePropEqualTo(String value) {
            addCriterion("use_prop =", value, "useProp");
            return (Criteria) this;
        }

        public Criteria andUsePropNotEqualTo(String value) {
            addCriterion("use_prop <>", value, "useProp");
            return (Criteria) this;
        }

        public Criteria andUsePropGreaterThan(String value) {
            addCriterion("use_prop >", value, "useProp");
            return (Criteria) this;
        }

        public Criteria andUsePropGreaterThanOrEqualTo(String value) {
            addCriterion("use_prop >=", value, "useProp");
            return (Criteria) this;
        }

        public Criteria andUsePropLessThan(String value) {
            addCriterion("use_prop <", value, "useProp");
            return (Criteria) this;
        }

        public Criteria andUsePropLessThanOrEqualTo(String value) {
            addCriterion("use_prop <=", value, "useProp");
            return (Criteria) this;
        }

        public Criteria andUsePropLike(String value) {
            addCriterion("use_prop like", value, "useProp");
            return (Criteria) this;
        }

        public Criteria andUsePropNotLike(String value) {
            addCriterion("use_prop not like", value, "useProp");
            return (Criteria) this;
        }

        public Criteria andUsePropIn(List<String> values) {
            addCriterion("use_prop in", values, "useProp");
            return (Criteria) this;
        }

        public Criteria andUsePropNotIn(List<String> values) {
            addCriterion("use_prop not in", values, "useProp");
            return (Criteria) this;
        }

        public Criteria andUsePropBetween(String value1, String value2) {
            addCriterion("use_prop between", value1, value2, "useProp");
            return (Criteria) this;
        }

        public Criteria andUsePropNotBetween(String value1, String value2) {
            addCriterion("use_prop not between", value1, value2, "useProp");
            return (Criteria) this;
        }

        public Criteria andConstructIsNull() {
            addCriterion("construct is null");
            return (Criteria) this;
        }

        public Criteria andConstructIsNotNull() {
            addCriterion("construct is not null");
            return (Criteria) this;
        }

        public Criteria andConstructEqualTo(String value) {
            addCriterion("construct =", value, "construct");
            return (Criteria) this;
        }

        public Criteria andConstructNotEqualTo(String value) {
            addCriterion("construct <>", value, "construct");
            return (Criteria) this;
        }

        public Criteria andConstructGreaterThan(String value) {
            addCriterion("construct >", value, "construct");
            return (Criteria) this;
        }

        public Criteria andConstructGreaterThanOrEqualTo(String value) {
            addCriterion("construct >=", value, "construct");
            return (Criteria) this;
        }

        public Criteria andConstructLessThan(String value) {
            addCriterion("construct <", value, "construct");
            return (Criteria) this;
        }

        public Criteria andConstructLessThanOrEqualTo(String value) {
            addCriterion("construct <=", value, "construct");
            return (Criteria) this;
        }

        public Criteria andConstructLike(String value) {
            addCriterion("construct like", value, "construct");
            return (Criteria) this;
        }

        public Criteria andConstructNotLike(String value) {
            addCriterion("construct not like", value, "construct");
            return (Criteria) this;
        }

        public Criteria andConstructIn(List<String> values) {
            addCriterion("construct in", values, "construct");
            return (Criteria) this;
        }

        public Criteria andConstructNotIn(List<String> values) {
            addCriterion("construct not in", values, "construct");
            return (Criteria) this;
        }

        public Criteria andConstructBetween(String value1, String value2) {
            addCriterion("construct between", value1, value2, "construct");
            return (Criteria) this;
        }

        public Criteria andConstructNotBetween(String value1, String value2) {
            addCriterion("construct not between", value1, value2, "construct");
            return (Criteria) this;
        }

        public Criteria andCoverageIsNull() {
            addCriterion("coverage is null");
            return (Criteria) this;
        }

        public Criteria andCoverageIsNotNull() {
            addCriterion("coverage is not null");
            return (Criteria) this;
        }

        public Criteria andCoverageEqualTo(String value) {
            addCriterion("coverage =", value, "coverage");
            return (Criteria) this;
        }

        public Criteria andCoverageNotEqualTo(String value) {
            addCriterion("coverage <>", value, "coverage");
            return (Criteria) this;
        }

        public Criteria andCoverageGreaterThan(String value) {
            addCriterion("coverage >", value, "coverage");
            return (Criteria) this;
        }

        public Criteria andCoverageGreaterThanOrEqualTo(String value) {
            addCriterion("coverage >=", value, "coverage");
            return (Criteria) this;
        }

        public Criteria andCoverageLessThan(String value) {
            addCriterion("coverage <", value, "coverage");
            return (Criteria) this;
        }

        public Criteria andCoverageLessThanOrEqualTo(String value) {
            addCriterion("coverage <=", value, "coverage");
            return (Criteria) this;
        }

        public Criteria andCoverageLike(String value) {
            addCriterion("coverage like", value, "coverage");
            return (Criteria) this;
        }

        public Criteria andCoverageNotLike(String value) {
            addCriterion("coverage not like", value, "coverage");
            return (Criteria) this;
        }

        public Criteria andCoverageIn(List<String> values) {
            addCriterion("coverage in", values, "coverage");
            return (Criteria) this;
        }

        public Criteria andCoverageNotIn(List<String> values) {
            addCriterion("coverage not in", values, "coverage");
            return (Criteria) this;
        }

        public Criteria andCoverageBetween(String value1, String value2) {
            addCriterion("coverage between", value1, value2, "coverage");
            return (Criteria) this;
        }

        public Criteria andCoverageNotBetween(String value1, String value2) {
            addCriterion("coverage not between", value1, value2, "coverage");
            return (Criteria) this;
        }

        public Criteria andTreatyDprtIsNull() {
            addCriterion("treaty_dprt is null");
            return (Criteria) this;
        }

        public Criteria andTreatyDprtIsNotNull() {
            addCriterion("treaty_dprt is not null");
            return (Criteria) this;
        }

        public Criteria andTreatyDprtEqualTo(Date value) {
            addCriterion("treaty_dprt =", value, "treatyDprt");
            return (Criteria) this;
        }

        public Criteria andTreatyDprtNotEqualTo(Date value) {
            addCriterion("treaty_dprt <>", value, "treatyDprt");
            return (Criteria) this;
        }

        public Criteria andTreatyDprtGreaterThan(Date value) {
            addCriterion("treaty_dprt >", value, "treatyDprt");
            return (Criteria) this;
        }

        public Criteria andTreatyDprtGreaterThanOrEqualTo(Date value) {
            addCriterion("treaty_dprt >=", value, "treatyDprt");
            return (Criteria) this;
        }

        public Criteria andTreatyDprtLessThan(Date value) {
            addCriterion("treaty_dprt <", value, "treatyDprt");
            return (Criteria) this;
        }

        public Criteria andTreatyDprtLessThanOrEqualTo(Date value) {
            addCriterion("treaty_dprt <=", value, "treatyDprt");
            return (Criteria) this;
        }

        public Criteria andTreatyDprtIn(List<Date> values) {
            addCriterion("treaty_dprt in", values, "treatyDprt");
            return (Criteria) this;
        }

        public Criteria andTreatyDprtNotIn(List<Date> values) {
            addCriterion("treaty_dprt not in", values, "treatyDprt");
            return (Criteria) this;
        }

        public Criteria andTreatyDprtBetween(Date value1, Date value2) {
            addCriterion("treaty_dprt between", value1, value2, "treatyDprt");
            return (Criteria) this;
        }

        public Criteria andTreatyDprtNotBetween(Date value1, Date value2) {
            addCriterion("treaty_dprt not between", value1, value2, "treatyDprt");
            return (Criteria) this;
        }

        public Criteria andAcctFlagIsNull() {
            addCriterion("acct_flag is null");
            return (Criteria) this;
        }

        public Criteria andAcctFlagIsNotNull() {
            addCriterion("acct_flag is not null");
            return (Criteria) this;
        }

        public Criteria andAcctFlagEqualTo(String value) {
            addCriterion("acct_flag =", value, "acctFlag");
            return (Criteria) this;
        }

        public Criteria andAcctFlagNotEqualTo(String value) {
            addCriterion("acct_flag <>", value, "acctFlag");
            return (Criteria) this;
        }

        public Criteria andAcctFlagGreaterThan(String value) {
            addCriterion("acct_flag >", value, "acctFlag");
            return (Criteria) this;
        }

        public Criteria andAcctFlagGreaterThanOrEqualTo(String value) {
            addCriterion("acct_flag >=", value, "acctFlag");
            return (Criteria) this;
        }

        public Criteria andAcctFlagLessThan(String value) {
            addCriterion("acct_flag <", value, "acctFlag");
            return (Criteria) this;
        }

        public Criteria andAcctFlagLessThanOrEqualTo(String value) {
            addCriterion("acct_flag <=", value, "acctFlag");
            return (Criteria) this;
        }

        public Criteria andAcctFlagLike(String value) {
            addCriterion("acct_flag like", value, "acctFlag");
            return (Criteria) this;
        }

        public Criteria andAcctFlagNotLike(String value) {
            addCriterion("acct_flag not like", value, "acctFlag");
            return (Criteria) this;
        }

        public Criteria andAcctFlagIn(List<String> values) {
            addCriterion("acct_flag in", values, "acctFlag");
            return (Criteria) this;
        }

        public Criteria andAcctFlagNotIn(List<String> values) {
            addCriterion("acct_flag not in", values, "acctFlag");
            return (Criteria) this;
        }

        public Criteria andAcctFlagBetween(String value1, String value2) {
            addCriterion("acct_flag between", value1, value2, "acctFlag");
            return (Criteria) this;
        }

        public Criteria andAcctFlagNotBetween(String value1, String value2) {
            addCriterion("acct_flag not between", value1, value2, "acctFlag");
            return (Criteria) this;
        }

        public Criteria andAcctDprtIsNull() {
            addCriterion("acct_dprt is null");
            return (Criteria) this;
        }

        public Criteria andAcctDprtIsNotNull() {
            addCriterion("acct_dprt is not null");
            return (Criteria) this;
        }

        public Criteria andAcctDprtEqualTo(Date value) {
            addCriterion("acct_dprt =", value, "acctDprt");
            return (Criteria) this;
        }

        public Criteria andAcctDprtNotEqualTo(Date value) {
            addCriterion("acct_dprt <>", value, "acctDprt");
            return (Criteria) this;
        }

        public Criteria andAcctDprtGreaterThan(Date value) {
            addCriterion("acct_dprt >", value, "acctDprt");
            return (Criteria) this;
        }

        public Criteria andAcctDprtGreaterThanOrEqualTo(Date value) {
            addCriterion("acct_dprt >=", value, "acctDprt");
            return (Criteria) this;
        }

        public Criteria andAcctDprtLessThan(Date value) {
            addCriterion("acct_dprt <", value, "acctDprt");
            return (Criteria) this;
        }

        public Criteria andAcctDprtLessThanOrEqualTo(Date value) {
            addCriterion("acct_dprt <=", value, "acctDprt");
            return (Criteria) this;
        }

        public Criteria andAcctDprtIn(List<Date> values) {
            addCriterion("acct_dprt in", values, "acctDprt");
            return (Criteria) this;
        }

        public Criteria andAcctDprtNotIn(List<Date> values) {
            addCriterion("acct_dprt not in", values, "acctDprt");
            return (Criteria) this;
        }

        public Criteria andAcctDprtBetween(Date value1, Date value2) {
            addCriterion("acct_dprt between", value1, value2, "acctDprt");
            return (Criteria) this;
        }

        public Criteria andAcctDprtNotBetween(Date value1, Date value2) {
            addCriterion("acct_dprt not between", value1, value2, "acctDprt");
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

        public Criteria andTreatysetIsNull() {
            addCriterion("TreatySet is null");
            return (Criteria) this;
        }

        public Criteria andTreatysetIsNotNull() {
            addCriterion("TreatySet is not null");
            return (Criteria) this;
        }

        public Criteria andTreatysetEqualTo(String value) {
            addCriterion("TreatySet =", value, "treatyset");
            return (Criteria) this;
        }

        public Criteria andTreatysetNotEqualTo(String value) {
            addCriterion("TreatySet <>", value, "treatyset");
            return (Criteria) this;
        }

        public Criteria andTreatysetGreaterThan(String value) {
            addCriterion("TreatySet >", value, "treatyset");
            return (Criteria) this;
        }

        public Criteria andTreatysetGreaterThanOrEqualTo(String value) {
            addCriterion("TreatySet >=", value, "treatyset");
            return (Criteria) this;
        }

        public Criteria andTreatysetLessThan(String value) {
            addCriterion("TreatySet <", value, "treatyset");
            return (Criteria) this;
        }

        public Criteria andTreatysetLessThanOrEqualTo(String value) {
            addCriterion("TreatySet <=", value, "treatyset");
            return (Criteria) this;
        }

        public Criteria andTreatysetLike(String value) {
            addCriterion("TreatySet like", value, "treatyset");
            return (Criteria) this;
        }

        public Criteria andTreatysetNotLike(String value) {
            addCriterion("TreatySet not like", value, "treatyset");
            return (Criteria) this;
        }

        public Criteria andTreatysetIn(List<String> values) {
            addCriterion("TreatySet in", values, "treatyset");
            return (Criteria) this;
        }

        public Criteria andTreatysetNotIn(List<String> values) {
            addCriterion("TreatySet not in", values, "treatyset");
            return (Criteria) this;
        }

        public Criteria andTreatysetBetween(String value1, String value2) {
            addCriterion("TreatySet between", value1, value2, "treatyset");
            return (Criteria) this;
        }

        public Criteria andTreatysetNotBetween(String value1, String value2) {
            addCriterion("TreatySet not between", value1, value2, "treatyset");
            return (Criteria) this;
        }

        public Criteria andCurrencyIsNull() {
            addCriterion("currency is null");
            return (Criteria) this;
        }

        public Criteria andCurrencyIsNotNull() {
            addCriterion("currency is not null");
            return (Criteria) this;
        }

        public Criteria andCurrencyEqualTo(String value) {
            addCriterion("currency =", value, "currency");
            return (Criteria) this;
        }

        public Criteria andCurrencyNotEqualTo(String value) {
            addCriterion("currency <>", value, "currency");
            return (Criteria) this;
        }

        public Criteria andCurrencyGreaterThan(String value) {
            addCriterion("currency >", value, "currency");
            return (Criteria) this;
        }

        public Criteria andCurrencyGreaterThanOrEqualTo(String value) {
            addCriterion("currency >=", value, "currency");
            return (Criteria) this;
        }

        public Criteria andCurrencyLessThan(String value) {
            addCriterion("currency <", value, "currency");
            return (Criteria) this;
        }

        public Criteria andCurrencyLessThanOrEqualTo(String value) {
            addCriterion("currency <=", value, "currency");
            return (Criteria) this;
        }

        public Criteria andCurrencyLike(String value) {
            addCriterion("currency like", value, "currency");
            return (Criteria) this;
        }

        public Criteria andCurrencyNotLike(String value) {
            addCriterion("currency not like", value, "currency");
            return (Criteria) this;
        }

        public Criteria andCurrencyIn(List<String> values) {
            addCriterion("currency in", values, "currency");
            return (Criteria) this;
        }

        public Criteria andCurrencyNotIn(List<String> values) {
            addCriterion("currency not in", values, "currency");
            return (Criteria) this;
        }

        public Criteria andCurrencyBetween(String value1, String value2) {
            addCriterion("currency between", value1, value2, "currency");
            return (Criteria) this;
        }

        public Criteria andCurrencyNotBetween(String value1, String value2) {
            addCriterion("currency not between", value1, value2, "currency");
            return (Criteria) this;
        }

        public Criteria andCurrencyexchangerateIsNull() {
            addCriterion("currencyExchangeRate is null");
            return (Criteria) this;
        }

        public Criteria andCurrencyexchangerateIsNotNull() {
            addCriterion("currencyExchangeRate is not null");
            return (Criteria) this;
        }

        public Criteria andCurrencyexchangerateEqualTo(BigDecimal value) {
            addCriterion("currencyExchangeRate =", value, "currencyexchangerate");
            return (Criteria) this;
        }

        public Criteria andCurrencyexchangerateNotEqualTo(BigDecimal value) {
            addCriterion("currencyExchangeRate <>", value, "currencyexchangerate");
            return (Criteria) this;
        }

        public Criteria andCurrencyexchangerateGreaterThan(BigDecimal value) {
            addCriterion("currencyExchangeRate >", value, "currencyexchangerate");
            return (Criteria) this;
        }

        public Criteria andCurrencyexchangerateGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("currencyExchangeRate >=", value, "currencyexchangerate");
            return (Criteria) this;
        }

        public Criteria andCurrencyexchangerateLessThan(BigDecimal value) {
            addCriterion("currencyExchangeRate <", value, "currencyexchangerate");
            return (Criteria) this;
        }

        public Criteria andCurrencyexchangerateLessThanOrEqualTo(BigDecimal value) {
            addCriterion("currencyExchangeRate <=", value, "currencyexchangerate");
            return (Criteria) this;
        }

        public Criteria andCurrencyexchangerateIn(List<BigDecimal> values) {
            addCriterion("currencyExchangeRate in", values, "currencyexchangerate");
            return (Criteria) this;
        }

        public Criteria andCurrencyexchangerateNotIn(List<BigDecimal> values) {
            addCriterion("currencyExchangeRate not in", values, "currencyexchangerate");
            return (Criteria) this;
        }

        public Criteria andCurrencyexchangerateBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("currencyExchangeRate between", value1, value2, "currencyexchangerate");
            return (Criteria) this;
        }

        public Criteria andCurrencyexchangerateNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("currencyExchangeRate not between", value1, value2, "currencyexchangerate");
            return (Criteria) this;
        }

        public Criteria andPolicyNoSeqIsNull() {
            addCriterion("policy_no_seq is null");
            return (Criteria) this;
        }

        public Criteria andPolicyNoSeqIsNotNull() {
            addCriterion("policy_no_seq is not null");
            return (Criteria) this;
        }

        public Criteria andPolicyNoSeqEqualTo(String value) {
            addCriterion("policy_no_seq =", value, "policyNoSeq");
            return (Criteria) this;
        }

        public Criteria andPolicyNoSeqNotEqualTo(String value) {
            addCriterion("policy_no_seq <>", value, "policyNoSeq");
            return (Criteria) this;
        }

        public Criteria andPolicyNoSeqGreaterThan(String value) {
            addCriterion("policy_no_seq >", value, "policyNoSeq");
            return (Criteria) this;
        }

        public Criteria andPolicyNoSeqGreaterThanOrEqualTo(String value) {
            addCriterion("policy_no_seq >=", value, "policyNoSeq");
            return (Criteria) this;
        }

        public Criteria andPolicyNoSeqLessThan(String value) {
            addCriterion("policy_no_seq <", value, "policyNoSeq");
            return (Criteria) this;
        }

        public Criteria andPolicyNoSeqLessThanOrEqualTo(String value) {
            addCriterion("policy_no_seq <=", value, "policyNoSeq");
            return (Criteria) this;
        }

        public Criteria andPolicyNoSeqLike(String value) {
            addCriterion("policy_no_seq like", value, "policyNoSeq");
            return (Criteria) this;
        }

        public Criteria andPolicyNoSeqNotLike(String value) {
            addCriterion("policy_no_seq not like", value, "policyNoSeq");
            return (Criteria) this;
        }

        public Criteria andPolicyNoSeqIn(List<String> values) {
            addCriterion("policy_no_seq in", values, "policyNoSeq");
            return (Criteria) this;
        }

        public Criteria andPolicyNoSeqNotIn(List<String> values) {
            addCriterion("policy_no_seq not in", values, "policyNoSeq");
            return (Criteria) this;
        }

        public Criteria andPolicyNoSeqBetween(String value1, String value2) {
            addCriterion("policy_no_seq between", value1, value2, "policyNoSeq");
            return (Criteria) this;
        }

        public Criteria andPolicyNoSeqNotBetween(String value1, String value2) {
            addCriterion("policy_no_seq not between", value1, value2, "policyNoSeq");
            return (Criteria) this;
        }

        public Criteria andLastupdatedatetimeIsNull() {
            addCriterion("LastUpdateDateTime is null");
            return (Criteria) this;
        }

        public Criteria andLastupdatedatetimeIsNotNull() {
            addCriterion("LastUpdateDateTime is not null");
            return (Criteria) this;
        }

        public Criteria andLastupdatedatetimeEqualTo(Date value) {
            addCriterion("LastUpdateDateTime =", value, "lastupdatedatetime");
            return (Criteria) this;
        }

        public Criteria andLastupdatedatetimeNotEqualTo(Date value) {
            addCriterion("LastUpdateDateTime <>", value, "lastupdatedatetime");
            return (Criteria) this;
        }

        public Criteria andLastupdatedatetimeGreaterThan(Date value) {
            addCriterion("LastUpdateDateTime >", value, "lastupdatedatetime");
            return (Criteria) this;
        }

        public Criteria andLastupdatedatetimeGreaterThanOrEqualTo(Date value) {
            addCriterion("LastUpdateDateTime >=", value, "lastupdatedatetime");
            return (Criteria) this;
        }

        public Criteria andLastupdatedatetimeLessThan(Date value) {
            addCriterion("LastUpdateDateTime <", value, "lastupdatedatetime");
            return (Criteria) this;
        }

        public Criteria andLastupdatedatetimeLessThanOrEqualTo(Date value) {
            addCriterion("LastUpdateDateTime <=", value, "lastupdatedatetime");
            return (Criteria) this;
        }

        public Criteria andLastupdatedatetimeIn(List<Date> values) {
            addCriterion("LastUpdateDateTime in", values, "lastupdatedatetime");
            return (Criteria) this;
        }

        public Criteria andLastupdatedatetimeNotIn(List<Date> values) {
            addCriterion("LastUpdateDateTime not in", values, "lastupdatedatetime");
            return (Criteria) this;
        }

        public Criteria andLastupdatedatetimeBetween(Date value1, Date value2) {
            addCriterion("LastUpdateDateTime between", value1, value2, "lastupdatedatetime");
            return (Criteria) this;
        }

        public Criteria andLastupdatedatetimeNotBetween(Date value1, Date value2) {
            addCriterion("LastUpdateDateTime not between", value1, value2, "lastupdatedatetime");
            return (Criteria) this;
        }

        public Criteria andLastupdateaccountIsNull() {
            addCriterion("LastUpdateAccount is null");
            return (Criteria) this;
        }

        public Criteria andLastupdateaccountIsNotNull() {
            addCriterion("LastUpdateAccount is not null");
            return (Criteria) this;
        }

        public Criteria andLastupdateaccountEqualTo(String value) {
            addCriterion("LastUpdateAccount =", value, "lastupdateaccount");
            return (Criteria) this;
        }

        public Criteria andLastupdateaccountNotEqualTo(String value) {
            addCriterion("LastUpdateAccount <>", value, "lastupdateaccount");
            return (Criteria) this;
        }

        public Criteria andLastupdateaccountGreaterThan(String value) {
            addCriterion("LastUpdateAccount >", value, "lastupdateaccount");
            return (Criteria) this;
        }

        public Criteria andLastupdateaccountGreaterThanOrEqualTo(String value) {
            addCriterion("LastUpdateAccount >=", value, "lastupdateaccount");
            return (Criteria) this;
        }

        public Criteria andLastupdateaccountLessThan(String value) {
            addCriterion("LastUpdateAccount <", value, "lastupdateaccount");
            return (Criteria) this;
        }

        public Criteria andLastupdateaccountLessThanOrEqualTo(String value) {
            addCriterion("LastUpdateAccount <=", value, "lastupdateaccount");
            return (Criteria) this;
        }

        public Criteria andLastupdateaccountLike(String value) {
            addCriterion("LastUpdateAccount like", value, "lastupdateaccount");
            return (Criteria) this;
        }

        public Criteria andLastupdateaccountNotLike(String value) {
            addCriterion("LastUpdateAccount not like", value, "lastupdateaccount");
            return (Criteria) this;
        }

        public Criteria andLastupdateaccountIn(List<String> values) {
            addCriterion("LastUpdateAccount in", values, "lastupdateaccount");
            return (Criteria) this;
        }

        public Criteria andLastupdateaccountNotIn(List<String> values) {
            addCriterion("LastUpdateAccount not in", values, "lastupdateaccount");
            return (Criteria) this;
        }

        public Criteria andLastupdateaccountBetween(String value1, String value2) {
            addCriterion("LastUpdateAccount between", value1, value2, "lastupdateaccount");
            return (Criteria) this;
        }

        public Criteria andLastupdateaccountNotBetween(String value1, String value2) {
            addCriterion("LastUpdateAccount not between", value1, value2, "lastupdateaccount");
            return (Criteria) this;
        }

        public Criteria andMkovseIsNull() {
            addCriterion("mkovse is null");
            return (Criteria) this;
        }

        public Criteria andMkovseIsNotNull() {
            addCriterion("mkovse is not null");
            return (Criteria) this;
        }

        public Criteria andMkovseEqualTo(String value) {
            addCriterion("mkovse =", value, "mkovse");
            return (Criteria) this;
        }

        public Criteria andMkovseNotEqualTo(String value) {
            addCriterion("mkovse <>", value, "mkovse");
            return (Criteria) this;
        }

        public Criteria andMkovseGreaterThan(String value) {
            addCriterion("mkovse >", value, "mkovse");
            return (Criteria) this;
        }

        public Criteria andMkovseGreaterThanOrEqualTo(String value) {
            addCriterion("mkovse >=", value, "mkovse");
            return (Criteria) this;
        }

        public Criteria andMkovseLessThan(String value) {
            addCriterion("mkovse <", value, "mkovse");
            return (Criteria) this;
        }

        public Criteria andMkovseLessThanOrEqualTo(String value) {
            addCriterion("mkovse <=", value, "mkovse");
            return (Criteria) this;
        }

        public Criteria andMkovseLike(String value) {
            addCriterion("mkovse like", value, "mkovse");
            return (Criteria) this;
        }

        public Criteria andMkovseNotLike(String value) {
            addCriterion("mkovse not like", value, "mkovse");
            return (Criteria) this;
        }

        public Criteria andMkovseIn(List<String> values) {
            addCriterion("mkovse in", values, "mkovse");
            return (Criteria) this;
        }

        public Criteria andMkovseNotIn(List<String> values) {
            addCriterion("mkovse not in", values, "mkovse");
            return (Criteria) this;
        }

        public Criteria andMkovseBetween(String value1, String value2) {
            addCriterion("mkovse between", value1, value2, "mkovse");
            return (Criteria) this;
        }

        public Criteria andMkovseNotBetween(String value1, String value2) {
            addCriterion("mkovse not between", value1, value2, "mkovse");
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