package com.asi.huanan.service.dao.mybatis.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class LogFriFacSharesExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public LogFriFacSharesExample() {
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

        public Criteria andEndorseNoIsNull() {
            addCriterion("endorse_no is null");
            return (Criteria) this;
        }

        public Criteria andEndorseNoIsNotNull() {
            addCriterion("endorse_no is not null");
            return (Criteria) this;
        }

        public Criteria andEndorseNoEqualTo(String value) {
            addCriterion("endorse_no =", value, "endorseNo");
            return (Criteria) this;
        }

        public Criteria andEndorseNoNotEqualTo(String value) {
            addCriterion("endorse_no <>", value, "endorseNo");
            return (Criteria) this;
        }

        public Criteria andEndorseNoGreaterThan(String value) {
            addCriterion("endorse_no >", value, "endorseNo");
            return (Criteria) this;
        }

        public Criteria andEndorseNoGreaterThanOrEqualTo(String value) {
            addCriterion("endorse_no >=", value, "endorseNo");
            return (Criteria) this;
        }

        public Criteria andEndorseNoLessThan(String value) {
            addCriterion("endorse_no <", value, "endorseNo");
            return (Criteria) this;
        }

        public Criteria andEndorseNoLessThanOrEqualTo(String value) {
            addCriterion("endorse_no <=", value, "endorseNo");
            return (Criteria) this;
        }

        public Criteria andEndorseNoLike(String value) {
            addCriterion("endorse_no like", value, "endorseNo");
            return (Criteria) this;
        }

        public Criteria andEndorseNoNotLike(String value) {
            addCriterion("endorse_no not like", value, "endorseNo");
            return (Criteria) this;
        }

        public Criteria andEndorseNoIn(List<String> values) {
            addCriterion("endorse_no in", values, "endorseNo");
            return (Criteria) this;
        }

        public Criteria andEndorseNoNotIn(List<String> values) {
            addCriterion("endorse_no not in", values, "endorseNo");
            return (Criteria) this;
        }

        public Criteria andEndorseNoBetween(String value1, String value2) {
            addCriterion("endorse_no between", value1, value2, "endorseNo");
            return (Criteria) this;
        }

        public Criteria andEndorseNoNotBetween(String value1, String value2) {
            addCriterion("endorse_no not between", value1, value2, "endorseNo");
            return (Criteria) this;
        }

        public Criteria andAddrNoIsNull() {
            addCriterion("addr_no is null");
            return (Criteria) this;
        }

        public Criteria andAddrNoIsNotNull() {
            addCriterion("addr_no is not null");
            return (Criteria) this;
        }

        public Criteria andAddrNoEqualTo(Integer value) {
            addCriterion("addr_no =", value, "addrNo");
            return (Criteria) this;
        }

        public Criteria andAddrNoNotEqualTo(Integer value) {
            addCriterion("addr_no <>", value, "addrNo");
            return (Criteria) this;
        }

        public Criteria andAddrNoGreaterThan(Integer value) {
            addCriterion("addr_no >", value, "addrNo");
            return (Criteria) this;
        }

        public Criteria andAddrNoGreaterThanOrEqualTo(Integer value) {
            addCriterion("addr_no >=", value, "addrNo");
            return (Criteria) this;
        }

        public Criteria andAddrNoLessThan(Integer value) {
            addCriterion("addr_no <", value, "addrNo");
            return (Criteria) this;
        }

        public Criteria andAddrNoLessThanOrEqualTo(Integer value) {
            addCriterion("addr_no <=", value, "addrNo");
            return (Criteria) this;
        }

        public Criteria andAddrNoIn(List<Integer> values) {
            addCriterion("addr_no in", values, "addrNo");
            return (Criteria) this;
        }

        public Criteria andAddrNoNotIn(List<Integer> values) {
            addCriterion("addr_no not in", values, "addrNo");
            return (Criteria) this;
        }

        public Criteria andAddrNoBetween(Integer value1, Integer value2) {
            addCriterion("addr_no between", value1, value2, "addrNo");
            return (Criteria) this;
        }

        public Criteria andAddrNoNotBetween(Integer value1, Integer value2) {
            addCriterion("addr_no not between", value1, value2, "addrNo");
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

        public Criteria andDutyDbgnIsNull() {
            addCriterion("duty_dbgn is null");
            return (Criteria) this;
        }

        public Criteria andDutyDbgnIsNotNull() {
            addCriterion("duty_dbgn is not null");
            return (Criteria) this;
        }

        public Criteria andDutyDbgnEqualTo(Date value) {
            addCriterion("duty_dbgn =", value, "dutyDbgn");
            return (Criteria) this;
        }

        public Criteria andDutyDbgnNotEqualTo(Date value) {
            addCriterion("duty_dbgn <>", value, "dutyDbgn");
            return (Criteria) this;
        }

        public Criteria andDutyDbgnGreaterThan(Date value) {
            addCriterion("duty_dbgn >", value, "dutyDbgn");
            return (Criteria) this;
        }

        public Criteria andDutyDbgnGreaterThanOrEqualTo(Date value) {
            addCriterion("duty_dbgn >=", value, "dutyDbgn");
            return (Criteria) this;
        }

        public Criteria andDutyDbgnLessThan(Date value) {
            addCriterion("duty_dbgn <", value, "dutyDbgn");
            return (Criteria) this;
        }

        public Criteria andDutyDbgnLessThanOrEqualTo(Date value) {
            addCriterion("duty_dbgn <=", value, "dutyDbgn");
            return (Criteria) this;
        }

        public Criteria andDutyDbgnIn(List<Date> values) {
            addCriterion("duty_dbgn in", values, "dutyDbgn");
            return (Criteria) this;
        }

        public Criteria andDutyDbgnNotIn(List<Date> values) {
            addCriterion("duty_dbgn not in", values, "dutyDbgn");
            return (Criteria) this;
        }

        public Criteria andDutyDbgnBetween(Date value1, Date value2) {
            addCriterion("duty_dbgn between", value1, value2, "dutyDbgn");
            return (Criteria) this;
        }

        public Criteria andDutyDbgnNotBetween(Date value1, Date value2) {
            addCriterion("duty_dbgn not between", value1, value2, "dutyDbgn");
            return (Criteria) this;
        }

        public Criteria andDutyDendIsNull() {
            addCriterion("duty_dend is null");
            return (Criteria) this;
        }

        public Criteria andDutyDendIsNotNull() {
            addCriterion("duty_dend is not null");
            return (Criteria) this;
        }

        public Criteria andDutyDendEqualTo(Date value) {
            addCriterion("duty_dend =", value, "dutyDend");
            return (Criteria) this;
        }

        public Criteria andDutyDendNotEqualTo(Date value) {
            addCriterion("duty_dend <>", value, "dutyDend");
            return (Criteria) this;
        }

        public Criteria andDutyDendGreaterThan(Date value) {
            addCriterion("duty_dend >", value, "dutyDend");
            return (Criteria) this;
        }

        public Criteria andDutyDendGreaterThanOrEqualTo(Date value) {
            addCriterion("duty_dend >=", value, "dutyDend");
            return (Criteria) this;
        }

        public Criteria andDutyDendLessThan(Date value) {
            addCriterion("duty_dend <", value, "dutyDend");
            return (Criteria) this;
        }

        public Criteria andDutyDendLessThanOrEqualTo(Date value) {
            addCriterion("duty_dend <=", value, "dutyDend");
            return (Criteria) this;
        }

        public Criteria andDutyDendIn(List<Date> values) {
            addCriterion("duty_dend in", values, "dutyDend");
            return (Criteria) this;
        }

        public Criteria andDutyDendNotIn(List<Date> values) {
            addCriterion("duty_dend not in", values, "dutyDend");
            return (Criteria) this;
        }

        public Criteria andDutyDendBetween(Date value1, Date value2) {
            addCriterion("duty_dend between", value1, value2, "dutyDend");
            return (Criteria) this;
        }

        public Criteria andDutyDendNotBetween(Date value1, Date value2) {
            addCriterion("duty_dend not between", value1, value2, "dutyDend");
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

        public Criteria andAmtTypIsNull() {
            addCriterion("amt_typ is null");
            return (Criteria) this;
        }

        public Criteria andAmtTypIsNotNull() {
            addCriterion("amt_typ is not null");
            return (Criteria) this;
        }

        public Criteria andAmtTypEqualTo(Long value) {
            addCriterion("amt_typ =", value, "amtTyp");
            return (Criteria) this;
        }

        public Criteria andAmtTypNotEqualTo(Long value) {
            addCriterion("amt_typ <>", value, "amtTyp");
            return (Criteria) this;
        }

        public Criteria andAmtTypGreaterThan(Long value) {
            addCriterion("amt_typ >", value, "amtTyp");
            return (Criteria) this;
        }

        public Criteria andAmtTypGreaterThanOrEqualTo(Long value) {
            addCriterion("amt_typ >=", value, "amtTyp");
            return (Criteria) this;
        }

        public Criteria andAmtTypLessThan(Long value) {
            addCriterion("amt_typ <", value, "amtTyp");
            return (Criteria) this;
        }

        public Criteria andAmtTypLessThanOrEqualTo(Long value) {
            addCriterion("amt_typ <=", value, "amtTyp");
            return (Criteria) this;
        }

        public Criteria andAmtTypIn(List<Long> values) {
            addCriterion("amt_typ in", values, "amtTyp");
            return (Criteria) this;
        }

        public Criteria andAmtTypNotIn(List<Long> values) {
            addCriterion("amt_typ not in", values, "amtTyp");
            return (Criteria) this;
        }

        public Criteria andAmtTypBetween(Long value1, Long value2) {
            addCriterion("amt_typ between", value1, value2, "amtTyp");
            return (Criteria) this;
        }

        public Criteria andAmtTypNotBetween(Long value1, Long value2) {
            addCriterion("amt_typ not between", value1, value2, "amtTyp");
            return (Criteria) this;
        }

        public Criteria andPremTypIsNull() {
            addCriterion("prem_typ is null");
            return (Criteria) this;
        }

        public Criteria andPremTypIsNotNull() {
            addCriterion("prem_typ is not null");
            return (Criteria) this;
        }

        public Criteria andPremTypEqualTo(Long value) {
            addCriterion("prem_typ =", value, "premTyp");
            return (Criteria) this;
        }

        public Criteria andPremTypNotEqualTo(Long value) {
            addCriterion("prem_typ <>", value, "premTyp");
            return (Criteria) this;
        }

        public Criteria andPremTypGreaterThan(Long value) {
            addCriterion("prem_typ >", value, "premTyp");
            return (Criteria) this;
        }

        public Criteria andPremTypGreaterThanOrEqualTo(Long value) {
            addCriterion("prem_typ >=", value, "premTyp");
            return (Criteria) this;
        }

        public Criteria andPremTypLessThan(Long value) {
            addCriterion("prem_typ <", value, "premTyp");
            return (Criteria) this;
        }

        public Criteria andPremTypLessThanOrEqualTo(Long value) {
            addCriterion("prem_typ <=", value, "premTyp");
            return (Criteria) this;
        }

        public Criteria andPremTypIn(List<Long> values) {
            addCriterion("prem_typ in", values, "premTyp");
            return (Criteria) this;
        }

        public Criteria andPremTypNotIn(List<Long> values) {
            addCriterion("prem_typ not in", values, "premTyp");
            return (Criteria) this;
        }

        public Criteria andPremTypBetween(Long value1, Long value2) {
            addCriterion("prem_typ between", value1, value2, "premTyp");
            return (Criteria) this;
        }

        public Criteria andPremTypNotBetween(Long value1, Long value2) {
            addCriterion("prem_typ not between", value1, value2, "premTyp");
            return (Criteria) this;
        }

        public Criteria andAmtEarIsNull() {
            addCriterion("amt_ear is null");
            return (Criteria) this;
        }

        public Criteria andAmtEarIsNotNull() {
            addCriterion("amt_ear is not null");
            return (Criteria) this;
        }

        public Criteria andAmtEarEqualTo(Long value) {
            addCriterion("amt_ear =", value, "amtEar");
            return (Criteria) this;
        }

        public Criteria andAmtEarNotEqualTo(Long value) {
            addCriterion("amt_ear <>", value, "amtEar");
            return (Criteria) this;
        }

        public Criteria andAmtEarGreaterThan(Long value) {
            addCriterion("amt_ear >", value, "amtEar");
            return (Criteria) this;
        }

        public Criteria andAmtEarGreaterThanOrEqualTo(Long value) {
            addCriterion("amt_ear >=", value, "amtEar");
            return (Criteria) this;
        }

        public Criteria andAmtEarLessThan(Long value) {
            addCriterion("amt_ear <", value, "amtEar");
            return (Criteria) this;
        }

        public Criteria andAmtEarLessThanOrEqualTo(Long value) {
            addCriterion("amt_ear <=", value, "amtEar");
            return (Criteria) this;
        }

        public Criteria andAmtEarIn(List<Long> values) {
            addCriterion("amt_ear in", values, "amtEar");
            return (Criteria) this;
        }

        public Criteria andAmtEarNotIn(List<Long> values) {
            addCriterion("amt_ear not in", values, "amtEar");
            return (Criteria) this;
        }

        public Criteria andAmtEarBetween(Long value1, Long value2) {
            addCriterion("amt_ear between", value1, value2, "amtEar");
            return (Criteria) this;
        }

        public Criteria andAmtEarNotBetween(Long value1, Long value2) {
            addCriterion("amt_ear not between", value1, value2, "amtEar");
            return (Criteria) this;
        }

        public Criteria andPremEarIsNull() {
            addCriterion("prem_ear is null");
            return (Criteria) this;
        }

        public Criteria andPremEarIsNotNull() {
            addCriterion("prem_ear is not null");
            return (Criteria) this;
        }

        public Criteria andPremEarEqualTo(Long value) {
            addCriterion("prem_ear =", value, "premEar");
            return (Criteria) this;
        }

        public Criteria andPremEarNotEqualTo(Long value) {
            addCriterion("prem_ear <>", value, "premEar");
            return (Criteria) this;
        }

        public Criteria andPremEarGreaterThan(Long value) {
            addCriterion("prem_ear >", value, "premEar");
            return (Criteria) this;
        }

        public Criteria andPremEarGreaterThanOrEqualTo(Long value) {
            addCriterion("prem_ear >=", value, "premEar");
            return (Criteria) this;
        }

        public Criteria andPremEarLessThan(Long value) {
            addCriterion("prem_ear <", value, "premEar");
            return (Criteria) this;
        }

        public Criteria andPremEarLessThanOrEqualTo(Long value) {
            addCriterion("prem_ear <=", value, "premEar");
            return (Criteria) this;
        }

        public Criteria andPremEarIn(List<Long> values) {
            addCriterion("prem_ear in", values, "premEar");
            return (Criteria) this;
        }

        public Criteria andPremEarNotIn(List<Long> values) {
            addCriterion("prem_ear not in", values, "premEar");
            return (Criteria) this;
        }

        public Criteria andPremEarBetween(Long value1, Long value2) {
            addCriterion("prem_ear between", value1, value2, "premEar");
            return (Criteria) this;
        }

        public Criteria andPremEarNotBetween(Long value1, Long value2) {
            addCriterion("prem_ear not between", value1, value2, "premEar");
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