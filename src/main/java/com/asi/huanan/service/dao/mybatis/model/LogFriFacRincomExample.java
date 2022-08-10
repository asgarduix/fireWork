package com.asi.huanan.service.dao.mybatis.model;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class LogFriFacRincomExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public LogFriFacRincomExample() {
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

        public Criteria andShareStatusIsNull() {
            addCriterion("share_status is null");
            return (Criteria) this;
        }

        public Criteria andShareStatusIsNotNull() {
            addCriterion("share_status is not null");
            return (Criteria) this;
        }

        public Criteria andShareStatusEqualTo(String value) {
            addCriterion("share_status =", value, "shareStatus");
            return (Criteria) this;
        }

        public Criteria andShareStatusNotEqualTo(String value) {
            addCriterion("share_status <>", value, "shareStatus");
            return (Criteria) this;
        }

        public Criteria andShareStatusGreaterThan(String value) {
            addCriterion("share_status >", value, "shareStatus");
            return (Criteria) this;
        }

        public Criteria andShareStatusGreaterThanOrEqualTo(String value) {
            addCriterion("share_status >=", value, "shareStatus");
            return (Criteria) this;
        }

        public Criteria andShareStatusLessThan(String value) {
            addCriterion("share_status <", value, "shareStatus");
            return (Criteria) this;
        }

        public Criteria andShareStatusLessThanOrEqualTo(String value) {
            addCriterion("share_status <=", value, "shareStatus");
            return (Criteria) this;
        }

        public Criteria andShareStatusLike(String value) {
            addCriterion("share_status like", value, "shareStatus");
            return (Criteria) this;
        }

        public Criteria andShareStatusNotLike(String value) {
            addCriterion("share_status not like", value, "shareStatus");
            return (Criteria) this;
        }

        public Criteria andShareStatusIn(List<String> values) {
            addCriterion("share_status in", values, "shareStatus");
            return (Criteria) this;
        }

        public Criteria andShareStatusNotIn(List<String> values) {
            addCriterion("share_status not in", values, "shareStatus");
            return (Criteria) this;
        }

        public Criteria andShareStatusBetween(String value1, String value2) {
            addCriterion("share_status between", value1, value2, "shareStatus");
            return (Criteria) this;
        }

        public Criteria andShareStatusNotBetween(String value1, String value2) {
            addCriterion("share_status not between", value1, value2, "shareStatus");
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

        public Criteria andHandlingRateIsNull() {
            addCriterion("handling_rate is null");
            return (Criteria) this;
        }

        public Criteria andHandlingRateIsNotNull() {
            addCriterion("handling_rate is not null");
            return (Criteria) this;
        }

        public Criteria andHandlingRateEqualTo(BigDecimal value) {
            addCriterion("handling_rate =", value, "handlingRate");
            return (Criteria) this;
        }

        public Criteria andHandlingRateNotEqualTo(BigDecimal value) {
            addCriterion("handling_rate <>", value, "handlingRate");
            return (Criteria) this;
        }

        public Criteria andHandlingRateGreaterThan(BigDecimal value) {
            addCriterion("handling_rate >", value, "handlingRate");
            return (Criteria) this;
        }

        public Criteria andHandlingRateGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("handling_rate >=", value, "handlingRate");
            return (Criteria) this;
        }

        public Criteria andHandlingRateLessThan(BigDecimal value) {
            addCriterion("handling_rate <", value, "handlingRate");
            return (Criteria) this;
        }

        public Criteria andHandlingRateLessThanOrEqualTo(BigDecimal value) {
            addCriterion("handling_rate <=", value, "handlingRate");
            return (Criteria) this;
        }

        public Criteria andHandlingRateIn(List<BigDecimal> values) {
            addCriterion("handling_rate in", values, "handlingRate");
            return (Criteria) this;
        }

        public Criteria andHandlingRateNotIn(List<BigDecimal> values) {
            addCriterion("handling_rate not in", values, "handlingRate");
            return (Criteria) this;
        }

        public Criteria andHandlingRateBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("handling_rate between", value1, value2, "handlingRate");
            return (Criteria) this;
        }

        public Criteria andHandlingRateNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("handling_rate not between", value1, value2, "handlingRate");
            return (Criteria) this;
        }

        public Criteria andBrokerRateIsNull() {
            addCriterion("broker_rate is null");
            return (Criteria) this;
        }

        public Criteria andBrokerRateIsNotNull() {
            addCriterion("broker_rate is not null");
            return (Criteria) this;
        }

        public Criteria andBrokerRateEqualTo(BigDecimal value) {
            addCriterion("broker_rate =", value, "brokerRate");
            return (Criteria) this;
        }

        public Criteria andBrokerRateNotEqualTo(BigDecimal value) {
            addCriterion("broker_rate <>", value, "brokerRate");
            return (Criteria) this;
        }

        public Criteria andBrokerRateGreaterThan(BigDecimal value) {
            addCriterion("broker_rate >", value, "brokerRate");
            return (Criteria) this;
        }

        public Criteria andBrokerRateGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("broker_rate >=", value, "brokerRate");
            return (Criteria) this;
        }

        public Criteria andBrokerRateLessThan(BigDecimal value) {
            addCriterion("broker_rate <", value, "brokerRate");
            return (Criteria) this;
        }

        public Criteria andBrokerRateLessThanOrEqualTo(BigDecimal value) {
            addCriterion("broker_rate <=", value, "brokerRate");
            return (Criteria) this;
        }

        public Criteria andBrokerRateIn(List<BigDecimal> values) {
            addCriterion("broker_rate in", values, "brokerRate");
            return (Criteria) this;
        }

        public Criteria andBrokerRateNotIn(List<BigDecimal> values) {
            addCriterion("broker_rate not in", values, "brokerRate");
            return (Criteria) this;
        }

        public Criteria andBrokerRateBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("broker_rate between", value1, value2, "brokerRate");
            return (Criteria) this;
        }

        public Criteria andBrokerRateNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("broker_rate not between", value1, value2, "brokerRate");
            return (Criteria) this;
        }

        public Criteria andDiscountRateIsNull() {
            addCriterion("discount_rate is null");
            return (Criteria) this;
        }

        public Criteria andDiscountRateIsNotNull() {
            addCriterion("discount_rate is not null");
            return (Criteria) this;
        }

        public Criteria andDiscountRateEqualTo(BigDecimal value) {
            addCriterion("discount_rate =", value, "discountRate");
            return (Criteria) this;
        }

        public Criteria andDiscountRateNotEqualTo(BigDecimal value) {
            addCriterion("discount_rate <>", value, "discountRate");
            return (Criteria) this;
        }

        public Criteria andDiscountRateGreaterThan(BigDecimal value) {
            addCriterion("discount_rate >", value, "discountRate");
            return (Criteria) this;
        }

        public Criteria andDiscountRateGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("discount_rate >=", value, "discountRate");
            return (Criteria) this;
        }

        public Criteria andDiscountRateLessThan(BigDecimal value) {
            addCriterion("discount_rate <", value, "discountRate");
            return (Criteria) this;
        }

        public Criteria andDiscountRateLessThanOrEqualTo(BigDecimal value) {
            addCriterion("discount_rate <=", value, "discountRate");
            return (Criteria) this;
        }

        public Criteria andDiscountRateIn(List<BigDecimal> values) {
            addCriterion("discount_rate in", values, "discountRate");
            return (Criteria) this;
        }

        public Criteria andDiscountRateNotIn(List<BigDecimal> values) {
            addCriterion("discount_rate not in", values, "discountRate");
            return (Criteria) this;
        }

        public Criteria andDiscountRateBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("discount_rate between", value1, value2, "discountRate");
            return (Criteria) this;
        }

        public Criteria andDiscountRateNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("discount_rate not between", value1, value2, "discountRate");
            return (Criteria) this;
        }

        public Criteria andCedePremIsNull() {
            addCriterion("cede_prem is null");
            return (Criteria) this;
        }

        public Criteria andCedePremIsNotNull() {
            addCriterion("cede_prem is not null");
            return (Criteria) this;
        }

        public Criteria andCedePremEqualTo(Long value) {
            addCriterion("cede_prem =", value, "cedePrem");
            return (Criteria) this;
        }

        public Criteria andCedePremNotEqualTo(Long value) {
            addCriterion("cede_prem <>", value, "cedePrem");
            return (Criteria) this;
        }

        public Criteria andCedePremGreaterThan(Long value) {
            addCriterion("cede_prem >", value, "cedePrem");
            return (Criteria) this;
        }

        public Criteria andCedePremGreaterThanOrEqualTo(Long value) {
            addCriterion("cede_prem >=", value, "cedePrem");
            return (Criteria) this;
        }

        public Criteria andCedePremLessThan(Long value) {
            addCriterion("cede_prem <", value, "cedePrem");
            return (Criteria) this;
        }

        public Criteria andCedePremLessThanOrEqualTo(Long value) {
            addCriterion("cede_prem <=", value, "cedePrem");
            return (Criteria) this;
        }

        public Criteria andCedePremIn(List<Long> values) {
            addCriterion("cede_prem in", values, "cedePrem");
            return (Criteria) this;
        }

        public Criteria andCedePremNotIn(List<Long> values) {
            addCriterion("cede_prem not in", values, "cedePrem");
            return (Criteria) this;
        }

        public Criteria andCedePremBetween(Long value1, Long value2) {
            addCriterion("cede_prem between", value1, value2, "cedePrem");
            return (Criteria) this;
        }

        public Criteria andCedePremNotBetween(Long value1, Long value2) {
            addCriterion("cede_prem not between", value1, value2, "cedePrem");
            return (Criteria) this;
        }

        public Criteria andRefNoIsNull() {
            addCriterion("ref_no is null");
            return (Criteria) this;
        }

        public Criteria andRefNoIsNotNull() {
            addCriterion("ref_no is not null");
            return (Criteria) this;
        }

        public Criteria andRefNoEqualTo(String value) {
            addCriterion("ref_no =", value, "refNo");
            return (Criteria) this;
        }

        public Criteria andRefNoNotEqualTo(String value) {
            addCriterion("ref_no <>", value, "refNo");
            return (Criteria) this;
        }

        public Criteria andRefNoGreaterThan(String value) {
            addCriterion("ref_no >", value, "refNo");
            return (Criteria) this;
        }

        public Criteria andRefNoGreaterThanOrEqualTo(String value) {
            addCriterion("ref_no >=", value, "refNo");
            return (Criteria) this;
        }

        public Criteria andRefNoLessThan(String value) {
            addCriterion("ref_no <", value, "refNo");
            return (Criteria) this;
        }

        public Criteria andRefNoLessThanOrEqualTo(String value) {
            addCriterion("ref_no <=", value, "refNo");
            return (Criteria) this;
        }

        public Criteria andRefNoLike(String value) {
            addCriterion("ref_no like", value, "refNo");
            return (Criteria) this;
        }

        public Criteria andRefNoNotLike(String value) {
            addCriterion("ref_no not like", value, "refNo");
            return (Criteria) this;
        }

        public Criteria andRefNoIn(List<String> values) {
            addCriterion("ref_no in", values, "refNo");
            return (Criteria) this;
        }

        public Criteria andRefNoNotIn(List<String> values) {
            addCriterion("ref_no not in", values, "refNo");
            return (Criteria) this;
        }

        public Criteria andRefNoBetween(String value1, String value2) {
            addCriterion("ref_no between", value1, value2, "refNo");
            return (Criteria) this;
        }

        public Criteria andRefNoNotBetween(String value1, String value2) {
            addCriterion("ref_no not between", value1, value2, "refNo");
            return (Criteria) this;
        }

        public Criteria andAnswerDateIsNull() {
            addCriterion("answer_date is null");
            return (Criteria) this;
        }

        public Criteria andAnswerDateIsNotNull() {
            addCriterion("answer_date is not null");
            return (Criteria) this;
        }

        public Criteria andAnswerDateEqualTo(Date value) {
            addCriterion("answer_date =", value, "answerDate");
            return (Criteria) this;
        }

        public Criteria andAnswerDateNotEqualTo(Date value) {
            addCriterion("answer_date <>", value, "answerDate");
            return (Criteria) this;
        }

        public Criteria andAnswerDateGreaterThan(Date value) {
            addCriterion("answer_date >", value, "answerDate");
            return (Criteria) this;
        }

        public Criteria andAnswerDateGreaterThanOrEqualTo(Date value) {
            addCriterion("answer_date >=", value, "answerDate");
            return (Criteria) this;
        }

        public Criteria andAnswerDateLessThan(Date value) {
            addCriterion("answer_date <", value, "answerDate");
            return (Criteria) this;
        }

        public Criteria andAnswerDateLessThanOrEqualTo(Date value) {
            addCriterion("answer_date <=", value, "answerDate");
            return (Criteria) this;
        }

        public Criteria andAnswerDateIn(List<Date> values) {
            addCriterion("answer_date in", values, "answerDate");
            return (Criteria) this;
        }

        public Criteria andAnswerDateNotIn(List<Date> values) {
            addCriterion("answer_date not in", values, "answerDate");
            return (Criteria) this;
        }

        public Criteria andAnswerDateBetween(Date value1, Date value2) {
            addCriterion("answer_date between", value1, value2, "answerDate");
            return (Criteria) this;
        }

        public Criteria andAnswerDateNotBetween(Date value1, Date value2) {
            addCriterion("answer_date not between", value1, value2, "answerDate");
            return (Criteria) this;
        }

        public Criteria andPaidDateIsNull() {
            addCriterion("paid_date is null");
            return (Criteria) this;
        }

        public Criteria andPaidDateIsNotNull() {
            addCriterion("paid_date is not null");
            return (Criteria) this;
        }

        public Criteria andPaidDateEqualTo(Date value) {
            addCriterion("paid_date =", value, "paidDate");
            return (Criteria) this;
        }

        public Criteria andPaidDateNotEqualTo(Date value) {
            addCriterion("paid_date <>", value, "paidDate");
            return (Criteria) this;
        }

        public Criteria andPaidDateGreaterThan(Date value) {
            addCriterion("paid_date >", value, "paidDate");
            return (Criteria) this;
        }

        public Criteria andPaidDateGreaterThanOrEqualTo(Date value) {
            addCriterion("paid_date >=", value, "paidDate");
            return (Criteria) this;
        }

        public Criteria andPaidDateLessThan(Date value) {
            addCriterion("paid_date <", value, "paidDate");
            return (Criteria) this;
        }

        public Criteria andPaidDateLessThanOrEqualTo(Date value) {
            addCriterion("paid_date <=", value, "paidDate");
            return (Criteria) this;
        }

        public Criteria andPaidDateIn(List<Date> values) {
            addCriterion("paid_date in", values, "paidDate");
            return (Criteria) this;
        }

        public Criteria andPaidDateNotIn(List<Date> values) {
            addCriterion("paid_date not in", values, "paidDate");
            return (Criteria) this;
        }

        public Criteria andPaidDateBetween(Date value1, Date value2) {
            addCriterion("paid_date between", value1, value2, "paidDate");
            return (Criteria) this;
        }

        public Criteria andPaidDateNotBetween(Date value1, Date value2) {
            addCriterion("paid_date not between", value1, value2, "paidDate");
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

        public Criteria andBillNoExternalIsNull() {
            addCriterion("bill_no_external is null");
            return (Criteria) this;
        }

        public Criteria andBillNoExternalIsNotNull() {
            addCriterion("bill_no_external is not null");
            return (Criteria) this;
        }

        public Criteria andBillNoExternalEqualTo(String value) {
            addCriterion("bill_no_external =", value, "billNoExternal");
            return (Criteria) this;
        }

        public Criteria andBillNoExternalNotEqualTo(String value) {
            addCriterion("bill_no_external <>", value, "billNoExternal");
            return (Criteria) this;
        }

        public Criteria andBillNoExternalGreaterThan(String value) {
            addCriterion("bill_no_external >", value, "billNoExternal");
            return (Criteria) this;
        }

        public Criteria andBillNoExternalGreaterThanOrEqualTo(String value) {
            addCriterion("bill_no_external >=", value, "billNoExternal");
            return (Criteria) this;
        }

        public Criteria andBillNoExternalLessThan(String value) {
            addCriterion("bill_no_external <", value, "billNoExternal");
            return (Criteria) this;
        }

        public Criteria andBillNoExternalLessThanOrEqualTo(String value) {
            addCriterion("bill_no_external <=", value, "billNoExternal");
            return (Criteria) this;
        }

        public Criteria andBillNoExternalLike(String value) {
            addCriterion("bill_no_external like", value, "billNoExternal");
            return (Criteria) this;
        }

        public Criteria andBillNoExternalNotLike(String value) {
            addCriterion("bill_no_external not like", value, "billNoExternal");
            return (Criteria) this;
        }

        public Criteria andBillNoExternalIn(List<String> values) {
            addCriterion("bill_no_external in", values, "billNoExternal");
            return (Criteria) this;
        }

        public Criteria andBillNoExternalNotIn(List<String> values) {
            addCriterion("bill_no_external not in", values, "billNoExternal");
            return (Criteria) this;
        }

        public Criteria andBillNoExternalBetween(String value1, String value2) {
            addCriterion("bill_no_external between", value1, value2, "billNoExternal");
            return (Criteria) this;
        }

        public Criteria andBillNoExternalNotBetween(String value1, String value2) {
            addCriterion("bill_no_external not between", value1, value2, "billNoExternal");
            return (Criteria) this;
        }

        public Criteria andTransferStatusIsNull() {
            addCriterion("transfer_status is null");
            return (Criteria) this;
        }

        public Criteria andTransferStatusIsNotNull() {
            addCriterion("transfer_status is not null");
            return (Criteria) this;
        }

        public Criteria andTransferStatusEqualTo(String value) {
            addCriterion("transfer_status =", value, "transferStatus");
            return (Criteria) this;
        }

        public Criteria andTransferStatusNotEqualTo(String value) {
            addCriterion("transfer_status <>", value, "transferStatus");
            return (Criteria) this;
        }

        public Criteria andTransferStatusGreaterThan(String value) {
            addCriterion("transfer_status >", value, "transferStatus");
            return (Criteria) this;
        }

        public Criteria andTransferStatusGreaterThanOrEqualTo(String value) {
            addCriterion("transfer_status >=", value, "transferStatus");
            return (Criteria) this;
        }

        public Criteria andTransferStatusLessThan(String value) {
            addCriterion("transfer_status <", value, "transferStatus");
            return (Criteria) this;
        }

        public Criteria andTransferStatusLessThanOrEqualTo(String value) {
            addCriterion("transfer_status <=", value, "transferStatus");
            return (Criteria) this;
        }

        public Criteria andTransferStatusLike(String value) {
            addCriterion("transfer_status like", value, "transferStatus");
            return (Criteria) this;
        }

        public Criteria andTransferStatusNotLike(String value) {
            addCriterion("transfer_status not like", value, "transferStatus");
            return (Criteria) this;
        }

        public Criteria andTransferStatusIn(List<String> values) {
            addCriterion("transfer_status in", values, "transferStatus");
            return (Criteria) this;
        }

        public Criteria andTransferStatusNotIn(List<String> values) {
            addCriterion("transfer_status not in", values, "transferStatus");
            return (Criteria) this;
        }

        public Criteria andTransferStatusBetween(String value1, String value2) {
            addCriterion("transfer_status between", value1, value2, "transferStatus");
            return (Criteria) this;
        }

        public Criteria andTransferStatusNotBetween(String value1, String value2) {
            addCriterion("transfer_status not between", value1, value2, "transferStatus");
            return (Criteria) this;
        }

        public Criteria andTransferDateIsNull() {
            addCriterion("transfer_date is null");
            return (Criteria) this;
        }

        public Criteria andTransferDateIsNotNull() {
            addCriterion("transfer_date is not null");
            return (Criteria) this;
        }

        public Criteria andTransferDateEqualTo(Date value) {
            addCriterion("transfer_date =", value, "transferDate");
            return (Criteria) this;
        }

        public Criteria andTransferDateNotEqualTo(Date value) {
            addCriterion("transfer_date <>", value, "transferDate");
            return (Criteria) this;
        }

        public Criteria andTransferDateGreaterThan(Date value) {
            addCriterion("transfer_date >", value, "transferDate");
            return (Criteria) this;
        }

        public Criteria andTransferDateGreaterThanOrEqualTo(Date value) {
            addCriterion("transfer_date >=", value, "transferDate");
            return (Criteria) this;
        }

        public Criteria andTransferDateLessThan(Date value) {
            addCriterion("transfer_date <", value, "transferDate");
            return (Criteria) this;
        }

        public Criteria andTransferDateLessThanOrEqualTo(Date value) {
            addCriterion("transfer_date <=", value, "transferDate");
            return (Criteria) this;
        }

        public Criteria andTransferDateIn(List<Date> values) {
            addCriterion("transfer_date in", values, "transferDate");
            return (Criteria) this;
        }

        public Criteria andTransferDateNotIn(List<Date> values) {
            addCriterion("transfer_date not in", values, "transferDate");
            return (Criteria) this;
        }

        public Criteria andTransferDateBetween(Date value1, Date value2) {
            addCriterion("transfer_date between", value1, value2, "transferDate");
            return (Criteria) this;
        }

        public Criteria andTransferDateNotBetween(Date value1, Date value2) {
            addCriterion("transfer_date not between", value1, value2, "transferDate");
            return (Criteria) this;
        }

        public Criteria andPrintDateIsNull() {
            addCriterion("print_date is null");
            return (Criteria) this;
        }

        public Criteria andPrintDateIsNotNull() {
            addCriterion("print_date is not null");
            return (Criteria) this;
        }

        public Criteria andPrintDateEqualTo(Date value) {
            addCriterion("print_date =", value, "printDate");
            return (Criteria) this;
        }

        public Criteria andPrintDateNotEqualTo(Date value) {
            addCriterion("print_date <>", value, "printDate");
            return (Criteria) this;
        }

        public Criteria andPrintDateGreaterThan(Date value) {
            addCriterion("print_date >", value, "printDate");
            return (Criteria) this;
        }

        public Criteria andPrintDateGreaterThanOrEqualTo(Date value) {
            addCriterion("print_date >=", value, "printDate");
            return (Criteria) this;
        }

        public Criteria andPrintDateLessThan(Date value) {
            addCriterion("print_date <", value, "printDate");
            return (Criteria) this;
        }

        public Criteria andPrintDateLessThanOrEqualTo(Date value) {
            addCriterion("print_date <=", value, "printDate");
            return (Criteria) this;
        }

        public Criteria andPrintDateIn(List<Date> values) {
            addCriterion("print_date in", values, "printDate");
            return (Criteria) this;
        }

        public Criteria andPrintDateNotIn(List<Date> values) {
            addCriterion("print_date not in", values, "printDate");
            return (Criteria) this;
        }

        public Criteria andPrintDateBetween(Date value1, Date value2) {
            addCriterion("print_date between", value1, value2, "printDate");
            return (Criteria) this;
        }

        public Criteria andPrintDateNotBetween(Date value1, Date value2) {
            addCriterion("print_date not between", value1, value2, "printDate");
            return (Criteria) this;
        }

        public Criteria andPaidDateExpectIsNull() {
            addCriterion("paid_date_expect is null");
            return (Criteria) this;
        }

        public Criteria andPaidDateExpectIsNotNull() {
            addCriterion("paid_date_expect is not null");
            return (Criteria) this;
        }

        public Criteria andPaidDateExpectEqualTo(Date value) {
            addCriterion("paid_date_expect =", value, "paidDateExpect");
            return (Criteria) this;
        }

        public Criteria andPaidDateExpectNotEqualTo(Date value) {
            addCriterion("paid_date_expect <>", value, "paidDateExpect");
            return (Criteria) this;
        }

        public Criteria andPaidDateExpectGreaterThan(Date value) {
            addCriterion("paid_date_expect >", value, "paidDateExpect");
            return (Criteria) this;
        }

        public Criteria andPaidDateExpectGreaterThanOrEqualTo(Date value) {
            addCriterion("paid_date_expect >=", value, "paidDateExpect");
            return (Criteria) this;
        }

        public Criteria andPaidDateExpectLessThan(Date value) {
            addCriterion("paid_date_expect <", value, "paidDateExpect");
            return (Criteria) this;
        }

        public Criteria andPaidDateExpectLessThanOrEqualTo(Date value) {
            addCriterion("paid_date_expect <=", value, "paidDateExpect");
            return (Criteria) this;
        }

        public Criteria andPaidDateExpectIn(List<Date> values) {
            addCriterion("paid_date_expect in", values, "paidDateExpect");
            return (Criteria) this;
        }

        public Criteria andPaidDateExpectNotIn(List<Date> values) {
            addCriterion("paid_date_expect not in", values, "paidDateExpect");
            return (Criteria) this;
        }

        public Criteria andPaidDateExpectBetween(Date value1, Date value2) {
            addCriterion("paid_date_expect between", value1, value2, "paidDateExpect");
            return (Criteria) this;
        }

        public Criteria andPaidDateExpectNotBetween(Date value1, Date value2) {
            addCriterion("paid_date_expect not between", value1, value2, "paidDateExpect");
            return (Criteria) this;
        }

        public Criteria andLastupdatedateIsNull() {
            addCriterion("LastUpdateDate is null");
            return (Criteria) this;
        }

        public Criteria andLastupdatedateIsNotNull() {
            addCriterion("LastUpdateDate is not null");
            return (Criteria) this;
        }

        public Criteria andLastupdatedateEqualTo(Date value) {
            addCriterion("LastUpdateDate =", value, "lastupdatedate");
            return (Criteria) this;
        }

        public Criteria andLastupdatedateNotEqualTo(Date value) {
            addCriterion("LastUpdateDate <>", value, "lastupdatedate");
            return (Criteria) this;
        }

        public Criteria andLastupdatedateGreaterThan(Date value) {
            addCriterion("LastUpdateDate >", value, "lastupdatedate");
            return (Criteria) this;
        }

        public Criteria andLastupdatedateGreaterThanOrEqualTo(Date value) {
            addCriterion("LastUpdateDate >=", value, "lastupdatedate");
            return (Criteria) this;
        }

        public Criteria andLastupdatedateLessThan(Date value) {
            addCriterion("LastUpdateDate <", value, "lastupdatedate");
            return (Criteria) this;
        }

        public Criteria andLastupdatedateLessThanOrEqualTo(Date value) {
            addCriterion("LastUpdateDate <=", value, "lastupdatedate");
            return (Criteria) this;
        }

        public Criteria andLastupdatedateIn(List<Date> values) {
            addCriterion("LastUpdateDate in", values, "lastupdatedate");
            return (Criteria) this;
        }

        public Criteria andLastupdatedateNotIn(List<Date> values) {
            addCriterion("LastUpdateDate not in", values, "lastupdatedate");
            return (Criteria) this;
        }

        public Criteria andLastupdatedateBetween(Date value1, Date value2) {
            addCriterion("LastUpdateDate between", value1, value2, "lastupdatedate");
            return (Criteria) this;
        }

        public Criteria andLastupdatedateNotBetween(Date value1, Date value2) {
            addCriterion("LastUpdateDate not between", value1, value2, "lastupdatedate");
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

        public Criteria andOrgpremIsNull() {
            addCriterion("OrgPrem is null");
            return (Criteria) this;
        }

        public Criteria andOrgpremIsNotNull() {
            addCriterion("OrgPrem is not null");
            return (Criteria) this;
        }

        public Criteria andOrgpremEqualTo(BigDecimal value) {
            addCriterion("OrgPrem =", value, "orgprem");
            return (Criteria) this;
        }

        public Criteria andOrgpremNotEqualTo(BigDecimal value) {
            addCriterion("OrgPrem <>", value, "orgprem");
            return (Criteria) this;
        }

        public Criteria andOrgpremGreaterThan(BigDecimal value) {
            addCriterion("OrgPrem >", value, "orgprem");
            return (Criteria) this;
        }

        public Criteria andOrgpremGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("OrgPrem >=", value, "orgprem");
            return (Criteria) this;
        }

        public Criteria andOrgpremLessThan(BigDecimal value) {
            addCriterion("OrgPrem <", value, "orgprem");
            return (Criteria) this;
        }

        public Criteria andOrgpremLessThanOrEqualTo(BigDecimal value) {
            addCriterion("OrgPrem <=", value, "orgprem");
            return (Criteria) this;
        }

        public Criteria andOrgpremIn(List<BigDecimal> values) {
            addCriterion("OrgPrem in", values, "orgprem");
            return (Criteria) this;
        }

        public Criteria andOrgpremNotIn(List<BigDecimal> values) {
            addCriterion("OrgPrem not in", values, "orgprem");
            return (Criteria) this;
        }

        public Criteria andOrgpremBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("OrgPrem between", value1, value2, "orgprem");
            return (Criteria) this;
        }

        public Criteria andOrgpremNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("OrgPrem not between", value1, value2, "orgprem");
            return (Criteria) this;
        }

        public Criteria andOrgcommIsNull() {
            addCriterion("OrgComm is null");
            return (Criteria) this;
        }

        public Criteria andOrgcommIsNotNull() {
            addCriterion("OrgComm is not null");
            return (Criteria) this;
        }

        public Criteria andOrgcommEqualTo(BigDecimal value) {
            addCriterion("OrgComm =", value, "orgcomm");
            return (Criteria) this;
        }

        public Criteria andOrgcommNotEqualTo(BigDecimal value) {
            addCriterion("OrgComm <>", value, "orgcomm");
            return (Criteria) this;
        }

        public Criteria andOrgcommGreaterThan(BigDecimal value) {
            addCriterion("OrgComm >", value, "orgcomm");
            return (Criteria) this;
        }

        public Criteria andOrgcommGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("OrgComm >=", value, "orgcomm");
            return (Criteria) this;
        }

        public Criteria andOrgcommLessThan(BigDecimal value) {
            addCriterion("OrgComm <", value, "orgcomm");
            return (Criteria) this;
        }

        public Criteria andOrgcommLessThanOrEqualTo(BigDecimal value) {
            addCriterion("OrgComm <=", value, "orgcomm");
            return (Criteria) this;
        }

        public Criteria andOrgcommIn(List<BigDecimal> values) {
            addCriterion("OrgComm in", values, "orgcomm");
            return (Criteria) this;
        }

        public Criteria andOrgcommNotIn(List<BigDecimal> values) {
            addCriterion("OrgComm not in", values, "orgcomm");
            return (Criteria) this;
        }

        public Criteria andOrgcommBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("OrgComm between", value1, value2, "orgcomm");
            return (Criteria) this;
        }

        public Criteria andOrgcommNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("OrgComm not between", value1, value2, "orgcomm");
            return (Criteria) this;
        }

        public Criteria andOrgtaxIsNull() {
            addCriterion("OrgTax is null");
            return (Criteria) this;
        }

        public Criteria andOrgtaxIsNotNull() {
            addCriterion("OrgTax is not null");
            return (Criteria) this;
        }

        public Criteria andOrgtaxEqualTo(BigDecimal value) {
            addCriterion("OrgTax =", value, "orgtax");
            return (Criteria) this;
        }

        public Criteria andOrgtaxNotEqualTo(BigDecimal value) {
            addCriterion("OrgTax <>", value, "orgtax");
            return (Criteria) this;
        }

        public Criteria andOrgtaxGreaterThan(BigDecimal value) {
            addCriterion("OrgTax >", value, "orgtax");
            return (Criteria) this;
        }

        public Criteria andOrgtaxGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("OrgTax >=", value, "orgtax");
            return (Criteria) this;
        }

        public Criteria andOrgtaxLessThan(BigDecimal value) {
            addCriterion("OrgTax <", value, "orgtax");
            return (Criteria) this;
        }

        public Criteria andOrgtaxLessThanOrEqualTo(BigDecimal value) {
            addCriterion("OrgTax <=", value, "orgtax");
            return (Criteria) this;
        }

        public Criteria andOrgtaxIn(List<BigDecimal> values) {
            addCriterion("OrgTax in", values, "orgtax");
            return (Criteria) this;
        }

        public Criteria andOrgtaxNotIn(List<BigDecimal> values) {
            addCriterion("OrgTax not in", values, "orgtax");
            return (Criteria) this;
        }

        public Criteria andOrgtaxBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("OrgTax between", value1, value2, "orgtax");
            return (Criteria) this;
        }

        public Criteria andOrgtaxNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("OrgTax not between", value1, value2, "orgtax");
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