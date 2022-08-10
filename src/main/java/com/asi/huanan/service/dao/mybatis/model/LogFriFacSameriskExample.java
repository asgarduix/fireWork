package com.asi.huanan.service.dao.mybatis.model;

import java.util.ArrayList;
import java.util.List;

public class LogFriFacSameriskExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public LogFriFacSameriskExample() {
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

        public Criteria andSamerisk1IsNull() {
            addCriterion("samerisk1 is null");
            return (Criteria) this;
        }

        public Criteria andSamerisk1IsNotNull() {
            addCriterion("samerisk1 is not null");
            return (Criteria) this;
        }

        public Criteria andSamerisk1EqualTo(String value) {
            addCriterion("samerisk1 =", value, "samerisk1");
            return (Criteria) this;
        }

        public Criteria andSamerisk1NotEqualTo(String value) {
            addCriterion("samerisk1 <>", value, "samerisk1");
            return (Criteria) this;
        }

        public Criteria andSamerisk1GreaterThan(String value) {
            addCriterion("samerisk1 >", value, "samerisk1");
            return (Criteria) this;
        }

        public Criteria andSamerisk1GreaterThanOrEqualTo(String value) {
            addCriterion("samerisk1 >=", value, "samerisk1");
            return (Criteria) this;
        }

        public Criteria andSamerisk1LessThan(String value) {
            addCriterion("samerisk1 <", value, "samerisk1");
            return (Criteria) this;
        }

        public Criteria andSamerisk1LessThanOrEqualTo(String value) {
            addCriterion("samerisk1 <=", value, "samerisk1");
            return (Criteria) this;
        }

        public Criteria andSamerisk1Like(String value) {
            addCriterion("samerisk1 like", value, "samerisk1");
            return (Criteria) this;
        }

        public Criteria andSamerisk1NotLike(String value) {
            addCriterion("samerisk1 not like", value, "samerisk1");
            return (Criteria) this;
        }

        public Criteria andSamerisk1In(List<String> values) {
            addCriterion("samerisk1 in", values, "samerisk1");
            return (Criteria) this;
        }

        public Criteria andSamerisk1NotIn(List<String> values) {
            addCriterion("samerisk1 not in", values, "samerisk1");
            return (Criteria) this;
        }

        public Criteria andSamerisk1Between(String value1, String value2) {
            addCriterion("samerisk1 between", value1, value2, "samerisk1");
            return (Criteria) this;
        }

        public Criteria andSamerisk1NotBetween(String value1, String value2) {
            addCriterion("samerisk1 not between", value1, value2, "samerisk1");
            return (Criteria) this;
        }

        public Criteria andSamerisk2IsNull() {
            addCriterion("samerisk2 is null");
            return (Criteria) this;
        }

        public Criteria andSamerisk2IsNotNull() {
            addCriterion("samerisk2 is not null");
            return (Criteria) this;
        }

        public Criteria andSamerisk2EqualTo(String value) {
            addCriterion("samerisk2 =", value, "samerisk2");
            return (Criteria) this;
        }

        public Criteria andSamerisk2NotEqualTo(String value) {
            addCriterion("samerisk2 <>", value, "samerisk2");
            return (Criteria) this;
        }

        public Criteria andSamerisk2GreaterThan(String value) {
            addCriterion("samerisk2 >", value, "samerisk2");
            return (Criteria) this;
        }

        public Criteria andSamerisk2GreaterThanOrEqualTo(String value) {
            addCriterion("samerisk2 >=", value, "samerisk2");
            return (Criteria) this;
        }

        public Criteria andSamerisk2LessThan(String value) {
            addCriterion("samerisk2 <", value, "samerisk2");
            return (Criteria) this;
        }

        public Criteria andSamerisk2LessThanOrEqualTo(String value) {
            addCriterion("samerisk2 <=", value, "samerisk2");
            return (Criteria) this;
        }

        public Criteria andSamerisk2Like(String value) {
            addCriterion("samerisk2 like", value, "samerisk2");
            return (Criteria) this;
        }

        public Criteria andSamerisk2NotLike(String value) {
            addCriterion("samerisk2 not like", value, "samerisk2");
            return (Criteria) this;
        }

        public Criteria andSamerisk2In(List<String> values) {
            addCriterion("samerisk2 in", values, "samerisk2");
            return (Criteria) this;
        }

        public Criteria andSamerisk2NotIn(List<String> values) {
            addCriterion("samerisk2 not in", values, "samerisk2");
            return (Criteria) this;
        }

        public Criteria andSamerisk2Between(String value1, String value2) {
            addCriterion("samerisk2 between", value1, value2, "samerisk2");
            return (Criteria) this;
        }

        public Criteria andSamerisk2NotBetween(String value1, String value2) {
            addCriterion("samerisk2 not between", value1, value2, "samerisk2");
            return (Criteria) this;
        }

        public Criteria andSamerisk3IsNull() {
            addCriterion("samerisk3 is null");
            return (Criteria) this;
        }

        public Criteria andSamerisk3IsNotNull() {
            addCriterion("samerisk3 is not null");
            return (Criteria) this;
        }

        public Criteria andSamerisk3EqualTo(String value) {
            addCriterion("samerisk3 =", value, "samerisk3");
            return (Criteria) this;
        }

        public Criteria andSamerisk3NotEqualTo(String value) {
            addCriterion("samerisk3 <>", value, "samerisk3");
            return (Criteria) this;
        }

        public Criteria andSamerisk3GreaterThan(String value) {
            addCriterion("samerisk3 >", value, "samerisk3");
            return (Criteria) this;
        }

        public Criteria andSamerisk3GreaterThanOrEqualTo(String value) {
            addCriterion("samerisk3 >=", value, "samerisk3");
            return (Criteria) this;
        }

        public Criteria andSamerisk3LessThan(String value) {
            addCriterion("samerisk3 <", value, "samerisk3");
            return (Criteria) this;
        }

        public Criteria andSamerisk3LessThanOrEqualTo(String value) {
            addCriterion("samerisk3 <=", value, "samerisk3");
            return (Criteria) this;
        }

        public Criteria andSamerisk3Like(String value) {
            addCriterion("samerisk3 like", value, "samerisk3");
            return (Criteria) this;
        }

        public Criteria andSamerisk3NotLike(String value) {
            addCriterion("samerisk3 not like", value, "samerisk3");
            return (Criteria) this;
        }

        public Criteria andSamerisk3In(List<String> values) {
            addCriterion("samerisk3 in", values, "samerisk3");
            return (Criteria) this;
        }

        public Criteria andSamerisk3NotIn(List<String> values) {
            addCriterion("samerisk3 not in", values, "samerisk3");
            return (Criteria) this;
        }

        public Criteria andSamerisk3Between(String value1, String value2) {
            addCriterion("samerisk3 between", value1, value2, "samerisk3");
            return (Criteria) this;
        }

        public Criteria andSamerisk3NotBetween(String value1, String value2) {
            addCriterion("samerisk3 not between", value1, value2, "samerisk3");
            return (Criteria) this;
        }

        public Criteria andSamerisk4IsNull() {
            addCriterion("samerisk4 is null");
            return (Criteria) this;
        }

        public Criteria andSamerisk4IsNotNull() {
            addCriterion("samerisk4 is not null");
            return (Criteria) this;
        }

        public Criteria andSamerisk4EqualTo(String value) {
            addCriterion("samerisk4 =", value, "samerisk4");
            return (Criteria) this;
        }

        public Criteria andSamerisk4NotEqualTo(String value) {
            addCriterion("samerisk4 <>", value, "samerisk4");
            return (Criteria) this;
        }

        public Criteria andSamerisk4GreaterThan(String value) {
            addCriterion("samerisk4 >", value, "samerisk4");
            return (Criteria) this;
        }

        public Criteria andSamerisk4GreaterThanOrEqualTo(String value) {
            addCriterion("samerisk4 >=", value, "samerisk4");
            return (Criteria) this;
        }

        public Criteria andSamerisk4LessThan(String value) {
            addCriterion("samerisk4 <", value, "samerisk4");
            return (Criteria) this;
        }

        public Criteria andSamerisk4LessThanOrEqualTo(String value) {
            addCriterion("samerisk4 <=", value, "samerisk4");
            return (Criteria) this;
        }

        public Criteria andSamerisk4Like(String value) {
            addCriterion("samerisk4 like", value, "samerisk4");
            return (Criteria) this;
        }

        public Criteria andSamerisk4NotLike(String value) {
            addCriterion("samerisk4 not like", value, "samerisk4");
            return (Criteria) this;
        }

        public Criteria andSamerisk4In(List<String> values) {
            addCriterion("samerisk4 in", values, "samerisk4");
            return (Criteria) this;
        }

        public Criteria andSamerisk4NotIn(List<String> values) {
            addCriterion("samerisk4 not in", values, "samerisk4");
            return (Criteria) this;
        }

        public Criteria andSamerisk4Between(String value1, String value2) {
            addCriterion("samerisk4 between", value1, value2, "samerisk4");
            return (Criteria) this;
        }

        public Criteria andSamerisk4NotBetween(String value1, String value2) {
            addCriterion("samerisk4 not between", value1, value2, "samerisk4");
            return (Criteria) this;
        }

        public Criteria andSamerisk5IsNull() {
            addCriterion("samerisk5 is null");
            return (Criteria) this;
        }

        public Criteria andSamerisk5IsNotNull() {
            addCriterion("samerisk5 is not null");
            return (Criteria) this;
        }

        public Criteria andSamerisk5EqualTo(String value) {
            addCriterion("samerisk5 =", value, "samerisk5");
            return (Criteria) this;
        }

        public Criteria andSamerisk5NotEqualTo(String value) {
            addCriterion("samerisk5 <>", value, "samerisk5");
            return (Criteria) this;
        }

        public Criteria andSamerisk5GreaterThan(String value) {
            addCriterion("samerisk5 >", value, "samerisk5");
            return (Criteria) this;
        }

        public Criteria andSamerisk5GreaterThanOrEqualTo(String value) {
            addCriterion("samerisk5 >=", value, "samerisk5");
            return (Criteria) this;
        }

        public Criteria andSamerisk5LessThan(String value) {
            addCriterion("samerisk5 <", value, "samerisk5");
            return (Criteria) this;
        }

        public Criteria andSamerisk5LessThanOrEqualTo(String value) {
            addCriterion("samerisk5 <=", value, "samerisk5");
            return (Criteria) this;
        }

        public Criteria andSamerisk5Like(String value) {
            addCriterion("samerisk5 like", value, "samerisk5");
            return (Criteria) this;
        }

        public Criteria andSamerisk5NotLike(String value) {
            addCriterion("samerisk5 not like", value, "samerisk5");
            return (Criteria) this;
        }

        public Criteria andSamerisk5In(List<String> values) {
            addCriterion("samerisk5 in", values, "samerisk5");
            return (Criteria) this;
        }

        public Criteria andSamerisk5NotIn(List<String> values) {
            addCriterion("samerisk5 not in", values, "samerisk5");
            return (Criteria) this;
        }

        public Criteria andSamerisk5Between(String value1, String value2) {
            addCriterion("samerisk5 between", value1, value2, "samerisk5");
            return (Criteria) this;
        }

        public Criteria andSamerisk5NotBetween(String value1, String value2) {
            addCriterion("samerisk5 not between", value1, value2, "samerisk5");
            return (Criteria) this;
        }

        public Criteria andSamerisk6IsNull() {
            addCriterion("samerisk6 is null");
            return (Criteria) this;
        }

        public Criteria andSamerisk6IsNotNull() {
            addCriterion("samerisk6 is not null");
            return (Criteria) this;
        }

        public Criteria andSamerisk6EqualTo(String value) {
            addCriterion("samerisk6 =", value, "samerisk6");
            return (Criteria) this;
        }

        public Criteria andSamerisk6NotEqualTo(String value) {
            addCriterion("samerisk6 <>", value, "samerisk6");
            return (Criteria) this;
        }

        public Criteria andSamerisk6GreaterThan(String value) {
            addCriterion("samerisk6 >", value, "samerisk6");
            return (Criteria) this;
        }

        public Criteria andSamerisk6GreaterThanOrEqualTo(String value) {
            addCriterion("samerisk6 >=", value, "samerisk6");
            return (Criteria) this;
        }

        public Criteria andSamerisk6LessThan(String value) {
            addCriterion("samerisk6 <", value, "samerisk6");
            return (Criteria) this;
        }

        public Criteria andSamerisk6LessThanOrEqualTo(String value) {
            addCriterion("samerisk6 <=", value, "samerisk6");
            return (Criteria) this;
        }

        public Criteria andSamerisk6Like(String value) {
            addCriterion("samerisk6 like", value, "samerisk6");
            return (Criteria) this;
        }

        public Criteria andSamerisk6NotLike(String value) {
            addCriterion("samerisk6 not like", value, "samerisk6");
            return (Criteria) this;
        }

        public Criteria andSamerisk6In(List<String> values) {
            addCriterion("samerisk6 in", values, "samerisk6");
            return (Criteria) this;
        }

        public Criteria andSamerisk6NotIn(List<String> values) {
            addCriterion("samerisk6 not in", values, "samerisk6");
            return (Criteria) this;
        }

        public Criteria andSamerisk6Between(String value1, String value2) {
            addCriterion("samerisk6 between", value1, value2, "samerisk6");
            return (Criteria) this;
        }

        public Criteria andSamerisk6NotBetween(String value1, String value2) {
            addCriterion("samerisk6 not between", value1, value2, "samerisk6");
            return (Criteria) this;
        }

        public Criteria andSamerisk7IsNull() {
            addCriterion("samerisk7 is null");
            return (Criteria) this;
        }

        public Criteria andSamerisk7IsNotNull() {
            addCriterion("samerisk7 is not null");
            return (Criteria) this;
        }

        public Criteria andSamerisk7EqualTo(String value) {
            addCriterion("samerisk7 =", value, "samerisk7");
            return (Criteria) this;
        }

        public Criteria andSamerisk7NotEqualTo(String value) {
            addCriterion("samerisk7 <>", value, "samerisk7");
            return (Criteria) this;
        }

        public Criteria andSamerisk7GreaterThan(String value) {
            addCriterion("samerisk7 >", value, "samerisk7");
            return (Criteria) this;
        }

        public Criteria andSamerisk7GreaterThanOrEqualTo(String value) {
            addCriterion("samerisk7 >=", value, "samerisk7");
            return (Criteria) this;
        }

        public Criteria andSamerisk7LessThan(String value) {
            addCriterion("samerisk7 <", value, "samerisk7");
            return (Criteria) this;
        }

        public Criteria andSamerisk7LessThanOrEqualTo(String value) {
            addCriterion("samerisk7 <=", value, "samerisk7");
            return (Criteria) this;
        }

        public Criteria andSamerisk7Like(String value) {
            addCriterion("samerisk7 like", value, "samerisk7");
            return (Criteria) this;
        }

        public Criteria andSamerisk7NotLike(String value) {
            addCriterion("samerisk7 not like", value, "samerisk7");
            return (Criteria) this;
        }

        public Criteria andSamerisk7In(List<String> values) {
            addCriterion("samerisk7 in", values, "samerisk7");
            return (Criteria) this;
        }

        public Criteria andSamerisk7NotIn(List<String> values) {
            addCriterion("samerisk7 not in", values, "samerisk7");
            return (Criteria) this;
        }

        public Criteria andSamerisk7Between(String value1, String value2) {
            addCriterion("samerisk7 between", value1, value2, "samerisk7");
            return (Criteria) this;
        }

        public Criteria andSamerisk7NotBetween(String value1, String value2) {
            addCriterion("samerisk7 not between", value1, value2, "samerisk7");
            return (Criteria) this;
        }

        public Criteria andSamerisk8IsNull() {
            addCriterion("samerisk8 is null");
            return (Criteria) this;
        }

        public Criteria andSamerisk8IsNotNull() {
            addCriterion("samerisk8 is not null");
            return (Criteria) this;
        }

        public Criteria andSamerisk8EqualTo(String value) {
            addCriterion("samerisk8 =", value, "samerisk8");
            return (Criteria) this;
        }

        public Criteria andSamerisk8NotEqualTo(String value) {
            addCriterion("samerisk8 <>", value, "samerisk8");
            return (Criteria) this;
        }

        public Criteria andSamerisk8GreaterThan(String value) {
            addCriterion("samerisk8 >", value, "samerisk8");
            return (Criteria) this;
        }

        public Criteria andSamerisk8GreaterThanOrEqualTo(String value) {
            addCriterion("samerisk8 >=", value, "samerisk8");
            return (Criteria) this;
        }

        public Criteria andSamerisk8LessThan(String value) {
            addCriterion("samerisk8 <", value, "samerisk8");
            return (Criteria) this;
        }

        public Criteria andSamerisk8LessThanOrEqualTo(String value) {
            addCriterion("samerisk8 <=", value, "samerisk8");
            return (Criteria) this;
        }

        public Criteria andSamerisk8Like(String value) {
            addCriterion("samerisk8 like", value, "samerisk8");
            return (Criteria) this;
        }

        public Criteria andSamerisk8NotLike(String value) {
            addCriterion("samerisk8 not like", value, "samerisk8");
            return (Criteria) this;
        }

        public Criteria andSamerisk8In(List<String> values) {
            addCriterion("samerisk8 in", values, "samerisk8");
            return (Criteria) this;
        }

        public Criteria andSamerisk8NotIn(List<String> values) {
            addCriterion("samerisk8 not in", values, "samerisk8");
            return (Criteria) this;
        }

        public Criteria andSamerisk8Between(String value1, String value2) {
            addCriterion("samerisk8 between", value1, value2, "samerisk8");
            return (Criteria) this;
        }

        public Criteria andSamerisk8NotBetween(String value1, String value2) {
            addCriterion("samerisk8 not between", value1, value2, "samerisk8");
            return (Criteria) this;
        }

        public Criteria andSamerisk9IsNull() {
            addCriterion("samerisk9 is null");
            return (Criteria) this;
        }

        public Criteria andSamerisk9IsNotNull() {
            addCriterion("samerisk9 is not null");
            return (Criteria) this;
        }

        public Criteria andSamerisk9EqualTo(String value) {
            addCriterion("samerisk9 =", value, "samerisk9");
            return (Criteria) this;
        }

        public Criteria andSamerisk9NotEqualTo(String value) {
            addCriterion("samerisk9 <>", value, "samerisk9");
            return (Criteria) this;
        }

        public Criteria andSamerisk9GreaterThan(String value) {
            addCriterion("samerisk9 >", value, "samerisk9");
            return (Criteria) this;
        }

        public Criteria andSamerisk9GreaterThanOrEqualTo(String value) {
            addCriterion("samerisk9 >=", value, "samerisk9");
            return (Criteria) this;
        }

        public Criteria andSamerisk9LessThan(String value) {
            addCriterion("samerisk9 <", value, "samerisk9");
            return (Criteria) this;
        }

        public Criteria andSamerisk9LessThanOrEqualTo(String value) {
            addCriterion("samerisk9 <=", value, "samerisk9");
            return (Criteria) this;
        }

        public Criteria andSamerisk9Like(String value) {
            addCriterion("samerisk9 like", value, "samerisk9");
            return (Criteria) this;
        }

        public Criteria andSamerisk9NotLike(String value) {
            addCriterion("samerisk9 not like", value, "samerisk9");
            return (Criteria) this;
        }

        public Criteria andSamerisk9In(List<String> values) {
            addCriterion("samerisk9 in", values, "samerisk9");
            return (Criteria) this;
        }

        public Criteria andSamerisk9NotIn(List<String> values) {
            addCriterion("samerisk9 not in", values, "samerisk9");
            return (Criteria) this;
        }

        public Criteria andSamerisk9Between(String value1, String value2) {
            addCriterion("samerisk9 between", value1, value2, "samerisk9");
            return (Criteria) this;
        }

        public Criteria andSamerisk9NotBetween(String value1, String value2) {
            addCriterion("samerisk9 not between", value1, value2, "samerisk9");
            return (Criteria) this;
        }

        public Criteria andSamerisk10IsNull() {
            addCriterion("samerisk10 is null");
            return (Criteria) this;
        }

        public Criteria andSamerisk10IsNotNull() {
            addCriterion("samerisk10 is not null");
            return (Criteria) this;
        }

        public Criteria andSamerisk10EqualTo(String value) {
            addCriterion("samerisk10 =", value, "samerisk10");
            return (Criteria) this;
        }

        public Criteria andSamerisk10NotEqualTo(String value) {
            addCriterion("samerisk10 <>", value, "samerisk10");
            return (Criteria) this;
        }

        public Criteria andSamerisk10GreaterThan(String value) {
            addCriterion("samerisk10 >", value, "samerisk10");
            return (Criteria) this;
        }

        public Criteria andSamerisk10GreaterThanOrEqualTo(String value) {
            addCriterion("samerisk10 >=", value, "samerisk10");
            return (Criteria) this;
        }

        public Criteria andSamerisk10LessThan(String value) {
            addCriterion("samerisk10 <", value, "samerisk10");
            return (Criteria) this;
        }

        public Criteria andSamerisk10LessThanOrEqualTo(String value) {
            addCriterion("samerisk10 <=", value, "samerisk10");
            return (Criteria) this;
        }

        public Criteria andSamerisk10Like(String value) {
            addCriterion("samerisk10 like", value, "samerisk10");
            return (Criteria) this;
        }

        public Criteria andSamerisk10NotLike(String value) {
            addCriterion("samerisk10 not like", value, "samerisk10");
            return (Criteria) this;
        }

        public Criteria andSamerisk10In(List<String> values) {
            addCriterion("samerisk10 in", values, "samerisk10");
            return (Criteria) this;
        }

        public Criteria andSamerisk10NotIn(List<String> values) {
            addCriterion("samerisk10 not in", values, "samerisk10");
            return (Criteria) this;
        }

        public Criteria andSamerisk10Between(String value1, String value2) {
            addCriterion("samerisk10 between", value1, value2, "samerisk10");
            return (Criteria) this;
        }

        public Criteria andSamerisk10NotBetween(String value1, String value2) {
            addCriterion("samerisk10 not between", value1, value2, "samerisk10");
            return (Criteria) this;
        }

        public Criteria andSamerisk11IsNull() {
            addCriterion("samerisk11 is null");
            return (Criteria) this;
        }

        public Criteria andSamerisk11IsNotNull() {
            addCriterion("samerisk11 is not null");
            return (Criteria) this;
        }

        public Criteria andSamerisk11EqualTo(String value) {
            addCriterion("samerisk11 =", value, "samerisk11");
            return (Criteria) this;
        }

        public Criteria andSamerisk11NotEqualTo(String value) {
            addCriterion("samerisk11 <>", value, "samerisk11");
            return (Criteria) this;
        }

        public Criteria andSamerisk11GreaterThan(String value) {
            addCriterion("samerisk11 >", value, "samerisk11");
            return (Criteria) this;
        }

        public Criteria andSamerisk11GreaterThanOrEqualTo(String value) {
            addCriterion("samerisk11 >=", value, "samerisk11");
            return (Criteria) this;
        }

        public Criteria andSamerisk11LessThan(String value) {
            addCriterion("samerisk11 <", value, "samerisk11");
            return (Criteria) this;
        }

        public Criteria andSamerisk11LessThanOrEqualTo(String value) {
            addCriterion("samerisk11 <=", value, "samerisk11");
            return (Criteria) this;
        }

        public Criteria andSamerisk11Like(String value) {
            addCriterion("samerisk11 like", value, "samerisk11");
            return (Criteria) this;
        }

        public Criteria andSamerisk11NotLike(String value) {
            addCriterion("samerisk11 not like", value, "samerisk11");
            return (Criteria) this;
        }

        public Criteria andSamerisk11In(List<String> values) {
            addCriterion("samerisk11 in", values, "samerisk11");
            return (Criteria) this;
        }

        public Criteria andSamerisk11NotIn(List<String> values) {
            addCriterion("samerisk11 not in", values, "samerisk11");
            return (Criteria) this;
        }

        public Criteria andSamerisk11Between(String value1, String value2) {
            addCriterion("samerisk11 between", value1, value2, "samerisk11");
            return (Criteria) this;
        }

        public Criteria andSamerisk11NotBetween(String value1, String value2) {
            addCriterion("samerisk11 not between", value1, value2, "samerisk11");
            return (Criteria) this;
        }

        public Criteria andSamerisk12IsNull() {
            addCriterion("samerisk12 is null");
            return (Criteria) this;
        }

        public Criteria andSamerisk12IsNotNull() {
            addCriterion("samerisk12 is not null");
            return (Criteria) this;
        }

        public Criteria andSamerisk12EqualTo(String value) {
            addCriterion("samerisk12 =", value, "samerisk12");
            return (Criteria) this;
        }

        public Criteria andSamerisk12NotEqualTo(String value) {
            addCriterion("samerisk12 <>", value, "samerisk12");
            return (Criteria) this;
        }

        public Criteria andSamerisk12GreaterThan(String value) {
            addCriterion("samerisk12 >", value, "samerisk12");
            return (Criteria) this;
        }

        public Criteria andSamerisk12GreaterThanOrEqualTo(String value) {
            addCriterion("samerisk12 >=", value, "samerisk12");
            return (Criteria) this;
        }

        public Criteria andSamerisk12LessThan(String value) {
            addCriterion("samerisk12 <", value, "samerisk12");
            return (Criteria) this;
        }

        public Criteria andSamerisk12LessThanOrEqualTo(String value) {
            addCriterion("samerisk12 <=", value, "samerisk12");
            return (Criteria) this;
        }

        public Criteria andSamerisk12Like(String value) {
            addCriterion("samerisk12 like", value, "samerisk12");
            return (Criteria) this;
        }

        public Criteria andSamerisk12NotLike(String value) {
            addCriterion("samerisk12 not like", value, "samerisk12");
            return (Criteria) this;
        }

        public Criteria andSamerisk12In(List<String> values) {
            addCriterion("samerisk12 in", values, "samerisk12");
            return (Criteria) this;
        }

        public Criteria andSamerisk12NotIn(List<String> values) {
            addCriterion("samerisk12 not in", values, "samerisk12");
            return (Criteria) this;
        }

        public Criteria andSamerisk12Between(String value1, String value2) {
            addCriterion("samerisk12 between", value1, value2, "samerisk12");
            return (Criteria) this;
        }

        public Criteria andSamerisk12NotBetween(String value1, String value2) {
            addCriterion("samerisk12 not between", value1, value2, "samerisk12");
            return (Criteria) this;
        }

        public Criteria andSamerisk13IsNull() {
            addCriterion("samerisk13 is null");
            return (Criteria) this;
        }

        public Criteria andSamerisk13IsNotNull() {
            addCriterion("samerisk13 is not null");
            return (Criteria) this;
        }

        public Criteria andSamerisk13EqualTo(String value) {
            addCriterion("samerisk13 =", value, "samerisk13");
            return (Criteria) this;
        }

        public Criteria andSamerisk13NotEqualTo(String value) {
            addCriterion("samerisk13 <>", value, "samerisk13");
            return (Criteria) this;
        }

        public Criteria andSamerisk13GreaterThan(String value) {
            addCriterion("samerisk13 >", value, "samerisk13");
            return (Criteria) this;
        }

        public Criteria andSamerisk13GreaterThanOrEqualTo(String value) {
            addCriterion("samerisk13 >=", value, "samerisk13");
            return (Criteria) this;
        }

        public Criteria andSamerisk13LessThan(String value) {
            addCriterion("samerisk13 <", value, "samerisk13");
            return (Criteria) this;
        }

        public Criteria andSamerisk13LessThanOrEqualTo(String value) {
            addCriterion("samerisk13 <=", value, "samerisk13");
            return (Criteria) this;
        }

        public Criteria andSamerisk13Like(String value) {
            addCriterion("samerisk13 like", value, "samerisk13");
            return (Criteria) this;
        }

        public Criteria andSamerisk13NotLike(String value) {
            addCriterion("samerisk13 not like", value, "samerisk13");
            return (Criteria) this;
        }

        public Criteria andSamerisk13In(List<String> values) {
            addCriterion("samerisk13 in", values, "samerisk13");
            return (Criteria) this;
        }

        public Criteria andSamerisk13NotIn(List<String> values) {
            addCriterion("samerisk13 not in", values, "samerisk13");
            return (Criteria) this;
        }

        public Criteria andSamerisk13Between(String value1, String value2) {
            addCriterion("samerisk13 between", value1, value2, "samerisk13");
            return (Criteria) this;
        }

        public Criteria andSamerisk13NotBetween(String value1, String value2) {
            addCriterion("samerisk13 not between", value1, value2, "samerisk13");
            return (Criteria) this;
        }

        public Criteria andSamerisk14IsNull() {
            addCriterion("samerisk14 is null");
            return (Criteria) this;
        }

        public Criteria andSamerisk14IsNotNull() {
            addCriterion("samerisk14 is not null");
            return (Criteria) this;
        }

        public Criteria andSamerisk14EqualTo(String value) {
            addCriterion("samerisk14 =", value, "samerisk14");
            return (Criteria) this;
        }

        public Criteria andSamerisk14NotEqualTo(String value) {
            addCriterion("samerisk14 <>", value, "samerisk14");
            return (Criteria) this;
        }

        public Criteria andSamerisk14GreaterThan(String value) {
            addCriterion("samerisk14 >", value, "samerisk14");
            return (Criteria) this;
        }

        public Criteria andSamerisk14GreaterThanOrEqualTo(String value) {
            addCriterion("samerisk14 >=", value, "samerisk14");
            return (Criteria) this;
        }

        public Criteria andSamerisk14LessThan(String value) {
            addCriterion("samerisk14 <", value, "samerisk14");
            return (Criteria) this;
        }

        public Criteria andSamerisk14LessThanOrEqualTo(String value) {
            addCriterion("samerisk14 <=", value, "samerisk14");
            return (Criteria) this;
        }

        public Criteria andSamerisk14Like(String value) {
            addCriterion("samerisk14 like", value, "samerisk14");
            return (Criteria) this;
        }

        public Criteria andSamerisk14NotLike(String value) {
            addCriterion("samerisk14 not like", value, "samerisk14");
            return (Criteria) this;
        }

        public Criteria andSamerisk14In(List<String> values) {
            addCriterion("samerisk14 in", values, "samerisk14");
            return (Criteria) this;
        }

        public Criteria andSamerisk14NotIn(List<String> values) {
            addCriterion("samerisk14 not in", values, "samerisk14");
            return (Criteria) this;
        }

        public Criteria andSamerisk14Between(String value1, String value2) {
            addCriterion("samerisk14 between", value1, value2, "samerisk14");
            return (Criteria) this;
        }

        public Criteria andSamerisk14NotBetween(String value1, String value2) {
            addCriterion("samerisk14 not between", value1, value2, "samerisk14");
            return (Criteria) this;
        }

        public Criteria andPolicy1IsNull() {
            addCriterion("policy1 is null");
            return (Criteria) this;
        }

        public Criteria andPolicy1IsNotNull() {
            addCriterion("policy1 is not null");
            return (Criteria) this;
        }

        public Criteria andPolicy1EqualTo(String value) {
            addCriterion("policy1 =", value, "policy1");
            return (Criteria) this;
        }

        public Criteria andPolicy1NotEqualTo(String value) {
            addCriterion("policy1 <>", value, "policy1");
            return (Criteria) this;
        }

        public Criteria andPolicy1GreaterThan(String value) {
            addCriterion("policy1 >", value, "policy1");
            return (Criteria) this;
        }

        public Criteria andPolicy1GreaterThanOrEqualTo(String value) {
            addCriterion("policy1 >=", value, "policy1");
            return (Criteria) this;
        }

        public Criteria andPolicy1LessThan(String value) {
            addCriterion("policy1 <", value, "policy1");
            return (Criteria) this;
        }

        public Criteria andPolicy1LessThanOrEqualTo(String value) {
            addCriterion("policy1 <=", value, "policy1");
            return (Criteria) this;
        }

        public Criteria andPolicy1Like(String value) {
            addCriterion("policy1 like", value, "policy1");
            return (Criteria) this;
        }

        public Criteria andPolicy1NotLike(String value) {
            addCriterion("policy1 not like", value, "policy1");
            return (Criteria) this;
        }

        public Criteria andPolicy1In(List<String> values) {
            addCriterion("policy1 in", values, "policy1");
            return (Criteria) this;
        }

        public Criteria andPolicy1NotIn(List<String> values) {
            addCriterion("policy1 not in", values, "policy1");
            return (Criteria) this;
        }

        public Criteria andPolicy1Between(String value1, String value2) {
            addCriterion("policy1 between", value1, value2, "policy1");
            return (Criteria) this;
        }

        public Criteria andPolicy1NotBetween(String value1, String value2) {
            addCriterion("policy1 not between", value1, value2, "policy1");
            return (Criteria) this;
        }

        public Criteria andPolicy2IsNull() {
            addCriterion("policy2 is null");
            return (Criteria) this;
        }

        public Criteria andPolicy2IsNotNull() {
            addCriterion("policy2 is not null");
            return (Criteria) this;
        }

        public Criteria andPolicy2EqualTo(String value) {
            addCriterion("policy2 =", value, "policy2");
            return (Criteria) this;
        }

        public Criteria andPolicy2NotEqualTo(String value) {
            addCriterion("policy2 <>", value, "policy2");
            return (Criteria) this;
        }

        public Criteria andPolicy2GreaterThan(String value) {
            addCriterion("policy2 >", value, "policy2");
            return (Criteria) this;
        }

        public Criteria andPolicy2GreaterThanOrEqualTo(String value) {
            addCriterion("policy2 >=", value, "policy2");
            return (Criteria) this;
        }

        public Criteria andPolicy2LessThan(String value) {
            addCriterion("policy2 <", value, "policy2");
            return (Criteria) this;
        }

        public Criteria andPolicy2LessThanOrEqualTo(String value) {
            addCriterion("policy2 <=", value, "policy2");
            return (Criteria) this;
        }

        public Criteria andPolicy2Like(String value) {
            addCriterion("policy2 like", value, "policy2");
            return (Criteria) this;
        }

        public Criteria andPolicy2NotLike(String value) {
            addCriterion("policy2 not like", value, "policy2");
            return (Criteria) this;
        }

        public Criteria andPolicy2In(List<String> values) {
            addCriterion("policy2 in", values, "policy2");
            return (Criteria) this;
        }

        public Criteria andPolicy2NotIn(List<String> values) {
            addCriterion("policy2 not in", values, "policy2");
            return (Criteria) this;
        }

        public Criteria andPolicy2Between(String value1, String value2) {
            addCriterion("policy2 between", value1, value2, "policy2");
            return (Criteria) this;
        }

        public Criteria andPolicy2NotBetween(String value1, String value2) {
            addCriterion("policy2 not between", value1, value2, "policy2");
            return (Criteria) this;
        }

        public Criteria andPolicy3IsNull() {
            addCriterion("policy3 is null");
            return (Criteria) this;
        }

        public Criteria andPolicy3IsNotNull() {
            addCriterion("policy3 is not null");
            return (Criteria) this;
        }

        public Criteria andPolicy3EqualTo(String value) {
            addCriterion("policy3 =", value, "policy3");
            return (Criteria) this;
        }

        public Criteria andPolicy3NotEqualTo(String value) {
            addCriterion("policy3 <>", value, "policy3");
            return (Criteria) this;
        }

        public Criteria andPolicy3GreaterThan(String value) {
            addCriterion("policy3 >", value, "policy3");
            return (Criteria) this;
        }

        public Criteria andPolicy3GreaterThanOrEqualTo(String value) {
            addCriterion("policy3 >=", value, "policy3");
            return (Criteria) this;
        }

        public Criteria andPolicy3LessThan(String value) {
            addCriterion("policy3 <", value, "policy3");
            return (Criteria) this;
        }

        public Criteria andPolicy3LessThanOrEqualTo(String value) {
            addCriterion("policy3 <=", value, "policy3");
            return (Criteria) this;
        }

        public Criteria andPolicy3Like(String value) {
            addCriterion("policy3 like", value, "policy3");
            return (Criteria) this;
        }

        public Criteria andPolicy3NotLike(String value) {
            addCriterion("policy3 not like", value, "policy3");
            return (Criteria) this;
        }

        public Criteria andPolicy3In(List<String> values) {
            addCriterion("policy3 in", values, "policy3");
            return (Criteria) this;
        }

        public Criteria andPolicy3NotIn(List<String> values) {
            addCriterion("policy3 not in", values, "policy3");
            return (Criteria) this;
        }

        public Criteria andPolicy3Between(String value1, String value2) {
            addCriterion("policy3 between", value1, value2, "policy3");
            return (Criteria) this;
        }

        public Criteria andPolicy3NotBetween(String value1, String value2) {
            addCriterion("policy3 not between", value1, value2, "policy3");
            return (Criteria) this;
        }

        public Criteria andPolicy4IsNull() {
            addCriterion("policy4 is null");
            return (Criteria) this;
        }

        public Criteria andPolicy4IsNotNull() {
            addCriterion("policy4 is not null");
            return (Criteria) this;
        }

        public Criteria andPolicy4EqualTo(String value) {
            addCriterion("policy4 =", value, "policy4");
            return (Criteria) this;
        }

        public Criteria andPolicy4NotEqualTo(String value) {
            addCriterion("policy4 <>", value, "policy4");
            return (Criteria) this;
        }

        public Criteria andPolicy4GreaterThan(String value) {
            addCriterion("policy4 >", value, "policy4");
            return (Criteria) this;
        }

        public Criteria andPolicy4GreaterThanOrEqualTo(String value) {
            addCriterion("policy4 >=", value, "policy4");
            return (Criteria) this;
        }

        public Criteria andPolicy4LessThan(String value) {
            addCriterion("policy4 <", value, "policy4");
            return (Criteria) this;
        }

        public Criteria andPolicy4LessThanOrEqualTo(String value) {
            addCriterion("policy4 <=", value, "policy4");
            return (Criteria) this;
        }

        public Criteria andPolicy4Like(String value) {
            addCriterion("policy4 like", value, "policy4");
            return (Criteria) this;
        }

        public Criteria andPolicy4NotLike(String value) {
            addCriterion("policy4 not like", value, "policy4");
            return (Criteria) this;
        }

        public Criteria andPolicy4In(List<String> values) {
            addCriterion("policy4 in", values, "policy4");
            return (Criteria) this;
        }

        public Criteria andPolicy4NotIn(List<String> values) {
            addCriterion("policy4 not in", values, "policy4");
            return (Criteria) this;
        }

        public Criteria andPolicy4Between(String value1, String value2) {
            addCriterion("policy4 between", value1, value2, "policy4");
            return (Criteria) this;
        }

        public Criteria andPolicy4NotBetween(String value1, String value2) {
            addCriterion("policy4 not between", value1, value2, "policy4");
            return (Criteria) this;
        }

        public Criteria andPolicy5IsNull() {
            addCriterion("policy5 is null");
            return (Criteria) this;
        }

        public Criteria andPolicy5IsNotNull() {
            addCriterion("policy5 is not null");
            return (Criteria) this;
        }

        public Criteria andPolicy5EqualTo(String value) {
            addCriterion("policy5 =", value, "policy5");
            return (Criteria) this;
        }

        public Criteria andPolicy5NotEqualTo(String value) {
            addCriterion("policy5 <>", value, "policy5");
            return (Criteria) this;
        }

        public Criteria andPolicy5GreaterThan(String value) {
            addCriterion("policy5 >", value, "policy5");
            return (Criteria) this;
        }

        public Criteria andPolicy5GreaterThanOrEqualTo(String value) {
            addCriterion("policy5 >=", value, "policy5");
            return (Criteria) this;
        }

        public Criteria andPolicy5LessThan(String value) {
            addCriterion("policy5 <", value, "policy5");
            return (Criteria) this;
        }

        public Criteria andPolicy5LessThanOrEqualTo(String value) {
            addCriterion("policy5 <=", value, "policy5");
            return (Criteria) this;
        }

        public Criteria andPolicy5Like(String value) {
            addCriterion("policy5 like", value, "policy5");
            return (Criteria) this;
        }

        public Criteria andPolicy5NotLike(String value) {
            addCriterion("policy5 not like", value, "policy5");
            return (Criteria) this;
        }

        public Criteria andPolicy5In(List<String> values) {
            addCriterion("policy5 in", values, "policy5");
            return (Criteria) this;
        }

        public Criteria andPolicy5NotIn(List<String> values) {
            addCriterion("policy5 not in", values, "policy5");
            return (Criteria) this;
        }

        public Criteria andPolicy5Between(String value1, String value2) {
            addCriterion("policy5 between", value1, value2, "policy5");
            return (Criteria) this;
        }

        public Criteria andPolicy5NotBetween(String value1, String value2) {
            addCriterion("policy5 not between", value1, value2, "policy5");
            return (Criteria) this;
        }

        public Criteria andPolicy6IsNull() {
            addCriterion("policy6 is null");
            return (Criteria) this;
        }

        public Criteria andPolicy6IsNotNull() {
            addCriterion("policy6 is not null");
            return (Criteria) this;
        }

        public Criteria andPolicy6EqualTo(String value) {
            addCriterion("policy6 =", value, "policy6");
            return (Criteria) this;
        }

        public Criteria andPolicy6NotEqualTo(String value) {
            addCriterion("policy6 <>", value, "policy6");
            return (Criteria) this;
        }

        public Criteria andPolicy6GreaterThan(String value) {
            addCriterion("policy6 >", value, "policy6");
            return (Criteria) this;
        }

        public Criteria andPolicy6GreaterThanOrEqualTo(String value) {
            addCriterion("policy6 >=", value, "policy6");
            return (Criteria) this;
        }

        public Criteria andPolicy6LessThan(String value) {
            addCriterion("policy6 <", value, "policy6");
            return (Criteria) this;
        }

        public Criteria andPolicy6LessThanOrEqualTo(String value) {
            addCriterion("policy6 <=", value, "policy6");
            return (Criteria) this;
        }

        public Criteria andPolicy6Like(String value) {
            addCriterion("policy6 like", value, "policy6");
            return (Criteria) this;
        }

        public Criteria andPolicy6NotLike(String value) {
            addCriterion("policy6 not like", value, "policy6");
            return (Criteria) this;
        }

        public Criteria andPolicy6In(List<String> values) {
            addCriterion("policy6 in", values, "policy6");
            return (Criteria) this;
        }

        public Criteria andPolicy6NotIn(List<String> values) {
            addCriterion("policy6 not in", values, "policy6");
            return (Criteria) this;
        }

        public Criteria andPolicy6Between(String value1, String value2) {
            addCriterion("policy6 between", value1, value2, "policy6");
            return (Criteria) this;
        }

        public Criteria andPolicy6NotBetween(String value1, String value2) {
            addCriterion("policy6 not between", value1, value2, "policy6");
            return (Criteria) this;
        }

        public Criteria andPolicy7IsNull() {
            addCriterion("policy7 is null");
            return (Criteria) this;
        }

        public Criteria andPolicy7IsNotNull() {
            addCriterion("policy7 is not null");
            return (Criteria) this;
        }

        public Criteria andPolicy7EqualTo(String value) {
            addCriterion("policy7 =", value, "policy7");
            return (Criteria) this;
        }

        public Criteria andPolicy7NotEqualTo(String value) {
            addCriterion("policy7 <>", value, "policy7");
            return (Criteria) this;
        }

        public Criteria andPolicy7GreaterThan(String value) {
            addCriterion("policy7 >", value, "policy7");
            return (Criteria) this;
        }

        public Criteria andPolicy7GreaterThanOrEqualTo(String value) {
            addCriterion("policy7 >=", value, "policy7");
            return (Criteria) this;
        }

        public Criteria andPolicy7LessThan(String value) {
            addCriterion("policy7 <", value, "policy7");
            return (Criteria) this;
        }

        public Criteria andPolicy7LessThanOrEqualTo(String value) {
            addCriterion("policy7 <=", value, "policy7");
            return (Criteria) this;
        }

        public Criteria andPolicy7Like(String value) {
            addCriterion("policy7 like", value, "policy7");
            return (Criteria) this;
        }

        public Criteria andPolicy7NotLike(String value) {
            addCriterion("policy7 not like", value, "policy7");
            return (Criteria) this;
        }

        public Criteria andPolicy7In(List<String> values) {
            addCriterion("policy7 in", values, "policy7");
            return (Criteria) this;
        }

        public Criteria andPolicy7NotIn(List<String> values) {
            addCriterion("policy7 not in", values, "policy7");
            return (Criteria) this;
        }

        public Criteria andPolicy7Between(String value1, String value2) {
            addCriterion("policy7 between", value1, value2, "policy7");
            return (Criteria) this;
        }

        public Criteria andPolicy7NotBetween(String value1, String value2) {
            addCriterion("policy7 not between", value1, value2, "policy7");
            return (Criteria) this;
        }

        public Criteria andPolicy8IsNull() {
            addCriterion("policy8 is null");
            return (Criteria) this;
        }

        public Criteria andPolicy8IsNotNull() {
            addCriterion("policy8 is not null");
            return (Criteria) this;
        }

        public Criteria andPolicy8EqualTo(String value) {
            addCriterion("policy8 =", value, "policy8");
            return (Criteria) this;
        }

        public Criteria andPolicy8NotEqualTo(String value) {
            addCriterion("policy8 <>", value, "policy8");
            return (Criteria) this;
        }

        public Criteria andPolicy8GreaterThan(String value) {
            addCriterion("policy8 >", value, "policy8");
            return (Criteria) this;
        }

        public Criteria andPolicy8GreaterThanOrEqualTo(String value) {
            addCriterion("policy8 >=", value, "policy8");
            return (Criteria) this;
        }

        public Criteria andPolicy8LessThan(String value) {
            addCriterion("policy8 <", value, "policy8");
            return (Criteria) this;
        }

        public Criteria andPolicy8LessThanOrEqualTo(String value) {
            addCriterion("policy8 <=", value, "policy8");
            return (Criteria) this;
        }

        public Criteria andPolicy8Like(String value) {
            addCriterion("policy8 like", value, "policy8");
            return (Criteria) this;
        }

        public Criteria andPolicy8NotLike(String value) {
            addCriterion("policy8 not like", value, "policy8");
            return (Criteria) this;
        }

        public Criteria andPolicy8In(List<String> values) {
            addCriterion("policy8 in", values, "policy8");
            return (Criteria) this;
        }

        public Criteria andPolicy8NotIn(List<String> values) {
            addCriterion("policy8 not in", values, "policy8");
            return (Criteria) this;
        }

        public Criteria andPolicy8Between(String value1, String value2) {
            addCriterion("policy8 between", value1, value2, "policy8");
            return (Criteria) this;
        }

        public Criteria andPolicy8NotBetween(String value1, String value2) {
            addCriterion("policy8 not between", value1, value2, "policy8");
            return (Criteria) this;
        }

        public Criteria andPolicy9IsNull() {
            addCriterion("policy9 is null");
            return (Criteria) this;
        }

        public Criteria andPolicy9IsNotNull() {
            addCriterion("policy9 is not null");
            return (Criteria) this;
        }

        public Criteria andPolicy9EqualTo(String value) {
            addCriterion("policy9 =", value, "policy9");
            return (Criteria) this;
        }

        public Criteria andPolicy9NotEqualTo(String value) {
            addCriterion("policy9 <>", value, "policy9");
            return (Criteria) this;
        }

        public Criteria andPolicy9GreaterThan(String value) {
            addCriterion("policy9 >", value, "policy9");
            return (Criteria) this;
        }

        public Criteria andPolicy9GreaterThanOrEqualTo(String value) {
            addCriterion("policy9 >=", value, "policy9");
            return (Criteria) this;
        }

        public Criteria andPolicy9LessThan(String value) {
            addCriterion("policy9 <", value, "policy9");
            return (Criteria) this;
        }

        public Criteria andPolicy9LessThanOrEqualTo(String value) {
            addCriterion("policy9 <=", value, "policy9");
            return (Criteria) this;
        }

        public Criteria andPolicy9Like(String value) {
            addCriterion("policy9 like", value, "policy9");
            return (Criteria) this;
        }

        public Criteria andPolicy9NotLike(String value) {
            addCriterion("policy9 not like", value, "policy9");
            return (Criteria) this;
        }

        public Criteria andPolicy9In(List<String> values) {
            addCriterion("policy9 in", values, "policy9");
            return (Criteria) this;
        }

        public Criteria andPolicy9NotIn(List<String> values) {
            addCriterion("policy9 not in", values, "policy9");
            return (Criteria) this;
        }

        public Criteria andPolicy9Between(String value1, String value2) {
            addCriterion("policy9 between", value1, value2, "policy9");
            return (Criteria) this;
        }

        public Criteria andPolicy9NotBetween(String value1, String value2) {
            addCriterion("policy9 not between", value1, value2, "policy9");
            return (Criteria) this;
        }

        public Criteria andPolicy10IsNull() {
            addCriterion("policy10 is null");
            return (Criteria) this;
        }

        public Criteria andPolicy10IsNotNull() {
            addCriterion("policy10 is not null");
            return (Criteria) this;
        }

        public Criteria andPolicy10EqualTo(String value) {
            addCriterion("policy10 =", value, "policy10");
            return (Criteria) this;
        }

        public Criteria andPolicy10NotEqualTo(String value) {
            addCriterion("policy10 <>", value, "policy10");
            return (Criteria) this;
        }

        public Criteria andPolicy10GreaterThan(String value) {
            addCriterion("policy10 >", value, "policy10");
            return (Criteria) this;
        }

        public Criteria andPolicy10GreaterThanOrEqualTo(String value) {
            addCriterion("policy10 >=", value, "policy10");
            return (Criteria) this;
        }

        public Criteria andPolicy10LessThan(String value) {
            addCriterion("policy10 <", value, "policy10");
            return (Criteria) this;
        }

        public Criteria andPolicy10LessThanOrEqualTo(String value) {
            addCriterion("policy10 <=", value, "policy10");
            return (Criteria) this;
        }

        public Criteria andPolicy10Like(String value) {
            addCriterion("policy10 like", value, "policy10");
            return (Criteria) this;
        }

        public Criteria andPolicy10NotLike(String value) {
            addCriterion("policy10 not like", value, "policy10");
            return (Criteria) this;
        }

        public Criteria andPolicy10In(List<String> values) {
            addCriterion("policy10 in", values, "policy10");
            return (Criteria) this;
        }

        public Criteria andPolicy10NotIn(List<String> values) {
            addCriterion("policy10 not in", values, "policy10");
            return (Criteria) this;
        }

        public Criteria andPolicy10Between(String value1, String value2) {
            addCriterion("policy10 between", value1, value2, "policy10");
            return (Criteria) this;
        }

        public Criteria andPolicy10NotBetween(String value1, String value2) {
            addCriterion("policy10 not between", value1, value2, "policy10");
            return (Criteria) this;
        }

        public Criteria andEndorse1IsNull() {
            addCriterion("endorse1 is null");
            return (Criteria) this;
        }

        public Criteria andEndorse1IsNotNull() {
            addCriterion("endorse1 is not null");
            return (Criteria) this;
        }

        public Criteria andEndorse1EqualTo(String value) {
            addCriterion("endorse1 =", value, "endorse1");
            return (Criteria) this;
        }

        public Criteria andEndorse1NotEqualTo(String value) {
            addCriterion("endorse1 <>", value, "endorse1");
            return (Criteria) this;
        }

        public Criteria andEndorse1GreaterThan(String value) {
            addCriterion("endorse1 >", value, "endorse1");
            return (Criteria) this;
        }

        public Criteria andEndorse1GreaterThanOrEqualTo(String value) {
            addCriterion("endorse1 >=", value, "endorse1");
            return (Criteria) this;
        }

        public Criteria andEndorse1LessThan(String value) {
            addCriterion("endorse1 <", value, "endorse1");
            return (Criteria) this;
        }

        public Criteria andEndorse1LessThanOrEqualTo(String value) {
            addCriterion("endorse1 <=", value, "endorse1");
            return (Criteria) this;
        }

        public Criteria andEndorse1Like(String value) {
            addCriterion("endorse1 like", value, "endorse1");
            return (Criteria) this;
        }

        public Criteria andEndorse1NotLike(String value) {
            addCriterion("endorse1 not like", value, "endorse1");
            return (Criteria) this;
        }

        public Criteria andEndorse1In(List<String> values) {
            addCriterion("endorse1 in", values, "endorse1");
            return (Criteria) this;
        }

        public Criteria andEndorse1NotIn(List<String> values) {
            addCriterion("endorse1 not in", values, "endorse1");
            return (Criteria) this;
        }

        public Criteria andEndorse1Between(String value1, String value2) {
            addCriterion("endorse1 between", value1, value2, "endorse1");
            return (Criteria) this;
        }

        public Criteria andEndorse1NotBetween(String value1, String value2) {
            addCriterion("endorse1 not between", value1, value2, "endorse1");
            return (Criteria) this;
        }

        public Criteria andEndorse2IsNull() {
            addCriterion("endorse2 is null");
            return (Criteria) this;
        }

        public Criteria andEndorse2IsNotNull() {
            addCriterion("endorse2 is not null");
            return (Criteria) this;
        }

        public Criteria andEndorse2EqualTo(String value) {
            addCriterion("endorse2 =", value, "endorse2");
            return (Criteria) this;
        }

        public Criteria andEndorse2NotEqualTo(String value) {
            addCriterion("endorse2 <>", value, "endorse2");
            return (Criteria) this;
        }

        public Criteria andEndorse2GreaterThan(String value) {
            addCriterion("endorse2 >", value, "endorse2");
            return (Criteria) this;
        }

        public Criteria andEndorse2GreaterThanOrEqualTo(String value) {
            addCriterion("endorse2 >=", value, "endorse2");
            return (Criteria) this;
        }

        public Criteria andEndorse2LessThan(String value) {
            addCriterion("endorse2 <", value, "endorse2");
            return (Criteria) this;
        }

        public Criteria andEndorse2LessThanOrEqualTo(String value) {
            addCriterion("endorse2 <=", value, "endorse2");
            return (Criteria) this;
        }

        public Criteria andEndorse2Like(String value) {
            addCriterion("endorse2 like", value, "endorse2");
            return (Criteria) this;
        }

        public Criteria andEndorse2NotLike(String value) {
            addCriterion("endorse2 not like", value, "endorse2");
            return (Criteria) this;
        }

        public Criteria andEndorse2In(List<String> values) {
            addCriterion("endorse2 in", values, "endorse2");
            return (Criteria) this;
        }

        public Criteria andEndorse2NotIn(List<String> values) {
            addCriterion("endorse2 not in", values, "endorse2");
            return (Criteria) this;
        }

        public Criteria andEndorse2Between(String value1, String value2) {
            addCriterion("endorse2 between", value1, value2, "endorse2");
            return (Criteria) this;
        }

        public Criteria andEndorse2NotBetween(String value1, String value2) {
            addCriterion("endorse2 not between", value1, value2, "endorse2");
            return (Criteria) this;
        }

        public Criteria andEndorse3IsNull() {
            addCriterion("endorse3 is null");
            return (Criteria) this;
        }

        public Criteria andEndorse3IsNotNull() {
            addCriterion("endorse3 is not null");
            return (Criteria) this;
        }

        public Criteria andEndorse3EqualTo(String value) {
            addCriterion("endorse3 =", value, "endorse3");
            return (Criteria) this;
        }

        public Criteria andEndorse3NotEqualTo(String value) {
            addCriterion("endorse3 <>", value, "endorse3");
            return (Criteria) this;
        }

        public Criteria andEndorse3GreaterThan(String value) {
            addCriterion("endorse3 >", value, "endorse3");
            return (Criteria) this;
        }

        public Criteria andEndorse3GreaterThanOrEqualTo(String value) {
            addCriterion("endorse3 >=", value, "endorse3");
            return (Criteria) this;
        }

        public Criteria andEndorse3LessThan(String value) {
            addCriterion("endorse3 <", value, "endorse3");
            return (Criteria) this;
        }

        public Criteria andEndorse3LessThanOrEqualTo(String value) {
            addCriterion("endorse3 <=", value, "endorse3");
            return (Criteria) this;
        }

        public Criteria andEndorse3Like(String value) {
            addCriterion("endorse3 like", value, "endorse3");
            return (Criteria) this;
        }

        public Criteria andEndorse3NotLike(String value) {
            addCriterion("endorse3 not like", value, "endorse3");
            return (Criteria) this;
        }

        public Criteria andEndorse3In(List<String> values) {
            addCriterion("endorse3 in", values, "endorse3");
            return (Criteria) this;
        }

        public Criteria andEndorse3NotIn(List<String> values) {
            addCriterion("endorse3 not in", values, "endorse3");
            return (Criteria) this;
        }

        public Criteria andEndorse3Between(String value1, String value2) {
            addCriterion("endorse3 between", value1, value2, "endorse3");
            return (Criteria) this;
        }

        public Criteria andEndorse3NotBetween(String value1, String value2) {
            addCriterion("endorse3 not between", value1, value2, "endorse3");
            return (Criteria) this;
        }

        public Criteria andEndorse4IsNull() {
            addCriterion("endorse4 is null");
            return (Criteria) this;
        }

        public Criteria andEndorse4IsNotNull() {
            addCriterion("endorse4 is not null");
            return (Criteria) this;
        }

        public Criteria andEndorse4EqualTo(String value) {
            addCriterion("endorse4 =", value, "endorse4");
            return (Criteria) this;
        }

        public Criteria andEndorse4NotEqualTo(String value) {
            addCriterion("endorse4 <>", value, "endorse4");
            return (Criteria) this;
        }

        public Criteria andEndorse4GreaterThan(String value) {
            addCriterion("endorse4 >", value, "endorse4");
            return (Criteria) this;
        }

        public Criteria andEndorse4GreaterThanOrEqualTo(String value) {
            addCriterion("endorse4 >=", value, "endorse4");
            return (Criteria) this;
        }

        public Criteria andEndorse4LessThan(String value) {
            addCriterion("endorse4 <", value, "endorse4");
            return (Criteria) this;
        }

        public Criteria andEndorse4LessThanOrEqualTo(String value) {
            addCriterion("endorse4 <=", value, "endorse4");
            return (Criteria) this;
        }

        public Criteria andEndorse4Like(String value) {
            addCriterion("endorse4 like", value, "endorse4");
            return (Criteria) this;
        }

        public Criteria andEndorse4NotLike(String value) {
            addCriterion("endorse4 not like", value, "endorse4");
            return (Criteria) this;
        }

        public Criteria andEndorse4In(List<String> values) {
            addCriterion("endorse4 in", values, "endorse4");
            return (Criteria) this;
        }

        public Criteria andEndorse4NotIn(List<String> values) {
            addCriterion("endorse4 not in", values, "endorse4");
            return (Criteria) this;
        }

        public Criteria andEndorse4Between(String value1, String value2) {
            addCriterion("endorse4 between", value1, value2, "endorse4");
            return (Criteria) this;
        }

        public Criteria andEndorse4NotBetween(String value1, String value2) {
            addCriterion("endorse4 not between", value1, value2, "endorse4");
            return (Criteria) this;
        }

        public Criteria andEndorse5IsNull() {
            addCriterion("endorse5 is null");
            return (Criteria) this;
        }

        public Criteria andEndorse5IsNotNull() {
            addCriterion("endorse5 is not null");
            return (Criteria) this;
        }

        public Criteria andEndorse5EqualTo(String value) {
            addCriterion("endorse5 =", value, "endorse5");
            return (Criteria) this;
        }

        public Criteria andEndorse5NotEqualTo(String value) {
            addCriterion("endorse5 <>", value, "endorse5");
            return (Criteria) this;
        }

        public Criteria andEndorse5GreaterThan(String value) {
            addCriterion("endorse5 >", value, "endorse5");
            return (Criteria) this;
        }

        public Criteria andEndorse5GreaterThanOrEqualTo(String value) {
            addCriterion("endorse5 >=", value, "endorse5");
            return (Criteria) this;
        }

        public Criteria andEndorse5LessThan(String value) {
            addCriterion("endorse5 <", value, "endorse5");
            return (Criteria) this;
        }

        public Criteria andEndorse5LessThanOrEqualTo(String value) {
            addCriterion("endorse5 <=", value, "endorse5");
            return (Criteria) this;
        }

        public Criteria andEndorse5Like(String value) {
            addCriterion("endorse5 like", value, "endorse5");
            return (Criteria) this;
        }

        public Criteria andEndorse5NotLike(String value) {
            addCriterion("endorse5 not like", value, "endorse5");
            return (Criteria) this;
        }

        public Criteria andEndorse5In(List<String> values) {
            addCriterion("endorse5 in", values, "endorse5");
            return (Criteria) this;
        }

        public Criteria andEndorse5NotIn(List<String> values) {
            addCriterion("endorse5 not in", values, "endorse5");
            return (Criteria) this;
        }

        public Criteria andEndorse5Between(String value1, String value2) {
            addCriterion("endorse5 between", value1, value2, "endorse5");
            return (Criteria) this;
        }

        public Criteria andEndorse5NotBetween(String value1, String value2) {
            addCriterion("endorse5 not between", value1, value2, "endorse5");
            return (Criteria) this;
        }

        public Criteria andEndorse6IsNull() {
            addCriterion("endorse6 is null");
            return (Criteria) this;
        }

        public Criteria andEndorse6IsNotNull() {
            addCriterion("endorse6 is not null");
            return (Criteria) this;
        }

        public Criteria andEndorse6EqualTo(String value) {
            addCriterion("endorse6 =", value, "endorse6");
            return (Criteria) this;
        }

        public Criteria andEndorse6NotEqualTo(String value) {
            addCriterion("endorse6 <>", value, "endorse6");
            return (Criteria) this;
        }

        public Criteria andEndorse6GreaterThan(String value) {
            addCriterion("endorse6 >", value, "endorse6");
            return (Criteria) this;
        }

        public Criteria andEndorse6GreaterThanOrEqualTo(String value) {
            addCriterion("endorse6 >=", value, "endorse6");
            return (Criteria) this;
        }

        public Criteria andEndorse6LessThan(String value) {
            addCriterion("endorse6 <", value, "endorse6");
            return (Criteria) this;
        }

        public Criteria andEndorse6LessThanOrEqualTo(String value) {
            addCriterion("endorse6 <=", value, "endorse6");
            return (Criteria) this;
        }

        public Criteria andEndorse6Like(String value) {
            addCriterion("endorse6 like", value, "endorse6");
            return (Criteria) this;
        }

        public Criteria andEndorse6NotLike(String value) {
            addCriterion("endorse6 not like", value, "endorse6");
            return (Criteria) this;
        }

        public Criteria andEndorse6In(List<String> values) {
            addCriterion("endorse6 in", values, "endorse6");
            return (Criteria) this;
        }

        public Criteria andEndorse6NotIn(List<String> values) {
            addCriterion("endorse6 not in", values, "endorse6");
            return (Criteria) this;
        }

        public Criteria andEndorse6Between(String value1, String value2) {
            addCriterion("endorse6 between", value1, value2, "endorse6");
            return (Criteria) this;
        }

        public Criteria andEndorse6NotBetween(String value1, String value2) {
            addCriterion("endorse6 not between", value1, value2, "endorse6");
            return (Criteria) this;
        }

        public Criteria andEndorse7IsNull() {
            addCriterion("endorse7 is null");
            return (Criteria) this;
        }

        public Criteria andEndorse7IsNotNull() {
            addCriterion("endorse7 is not null");
            return (Criteria) this;
        }

        public Criteria andEndorse7EqualTo(String value) {
            addCriterion("endorse7 =", value, "endorse7");
            return (Criteria) this;
        }

        public Criteria andEndorse7NotEqualTo(String value) {
            addCriterion("endorse7 <>", value, "endorse7");
            return (Criteria) this;
        }

        public Criteria andEndorse7GreaterThan(String value) {
            addCriterion("endorse7 >", value, "endorse7");
            return (Criteria) this;
        }

        public Criteria andEndorse7GreaterThanOrEqualTo(String value) {
            addCriterion("endorse7 >=", value, "endorse7");
            return (Criteria) this;
        }

        public Criteria andEndorse7LessThan(String value) {
            addCriterion("endorse7 <", value, "endorse7");
            return (Criteria) this;
        }

        public Criteria andEndorse7LessThanOrEqualTo(String value) {
            addCriterion("endorse7 <=", value, "endorse7");
            return (Criteria) this;
        }

        public Criteria andEndorse7Like(String value) {
            addCriterion("endorse7 like", value, "endorse7");
            return (Criteria) this;
        }

        public Criteria andEndorse7NotLike(String value) {
            addCriterion("endorse7 not like", value, "endorse7");
            return (Criteria) this;
        }

        public Criteria andEndorse7In(List<String> values) {
            addCriterion("endorse7 in", values, "endorse7");
            return (Criteria) this;
        }

        public Criteria andEndorse7NotIn(List<String> values) {
            addCriterion("endorse7 not in", values, "endorse7");
            return (Criteria) this;
        }

        public Criteria andEndorse7Between(String value1, String value2) {
            addCriterion("endorse7 between", value1, value2, "endorse7");
            return (Criteria) this;
        }

        public Criteria andEndorse7NotBetween(String value1, String value2) {
            addCriterion("endorse7 not between", value1, value2, "endorse7");
            return (Criteria) this;
        }

        public Criteria andEndorse8IsNull() {
            addCriterion("endorse8 is null");
            return (Criteria) this;
        }

        public Criteria andEndorse8IsNotNull() {
            addCriterion("endorse8 is not null");
            return (Criteria) this;
        }

        public Criteria andEndorse8EqualTo(String value) {
            addCriterion("endorse8 =", value, "endorse8");
            return (Criteria) this;
        }

        public Criteria andEndorse8NotEqualTo(String value) {
            addCriterion("endorse8 <>", value, "endorse8");
            return (Criteria) this;
        }

        public Criteria andEndorse8GreaterThan(String value) {
            addCriterion("endorse8 >", value, "endorse8");
            return (Criteria) this;
        }

        public Criteria andEndorse8GreaterThanOrEqualTo(String value) {
            addCriterion("endorse8 >=", value, "endorse8");
            return (Criteria) this;
        }

        public Criteria andEndorse8LessThan(String value) {
            addCriterion("endorse8 <", value, "endorse8");
            return (Criteria) this;
        }

        public Criteria andEndorse8LessThanOrEqualTo(String value) {
            addCriterion("endorse8 <=", value, "endorse8");
            return (Criteria) this;
        }

        public Criteria andEndorse8Like(String value) {
            addCriterion("endorse8 like", value, "endorse8");
            return (Criteria) this;
        }

        public Criteria andEndorse8NotLike(String value) {
            addCriterion("endorse8 not like", value, "endorse8");
            return (Criteria) this;
        }

        public Criteria andEndorse8In(List<String> values) {
            addCriterion("endorse8 in", values, "endorse8");
            return (Criteria) this;
        }

        public Criteria andEndorse8NotIn(List<String> values) {
            addCriterion("endorse8 not in", values, "endorse8");
            return (Criteria) this;
        }

        public Criteria andEndorse8Between(String value1, String value2) {
            addCriterion("endorse8 between", value1, value2, "endorse8");
            return (Criteria) this;
        }

        public Criteria andEndorse8NotBetween(String value1, String value2) {
            addCriterion("endorse8 not between", value1, value2, "endorse8");
            return (Criteria) this;
        }

        public Criteria andEndorse9IsNull() {
            addCriterion("endorse9 is null");
            return (Criteria) this;
        }

        public Criteria andEndorse9IsNotNull() {
            addCriterion("endorse9 is not null");
            return (Criteria) this;
        }

        public Criteria andEndorse9EqualTo(String value) {
            addCriterion("endorse9 =", value, "endorse9");
            return (Criteria) this;
        }

        public Criteria andEndorse9NotEqualTo(String value) {
            addCriterion("endorse9 <>", value, "endorse9");
            return (Criteria) this;
        }

        public Criteria andEndorse9GreaterThan(String value) {
            addCriterion("endorse9 >", value, "endorse9");
            return (Criteria) this;
        }

        public Criteria andEndorse9GreaterThanOrEqualTo(String value) {
            addCriterion("endorse9 >=", value, "endorse9");
            return (Criteria) this;
        }

        public Criteria andEndorse9LessThan(String value) {
            addCriterion("endorse9 <", value, "endorse9");
            return (Criteria) this;
        }

        public Criteria andEndorse9LessThanOrEqualTo(String value) {
            addCriterion("endorse9 <=", value, "endorse9");
            return (Criteria) this;
        }

        public Criteria andEndorse9Like(String value) {
            addCriterion("endorse9 like", value, "endorse9");
            return (Criteria) this;
        }

        public Criteria andEndorse9NotLike(String value) {
            addCriterion("endorse9 not like", value, "endorse9");
            return (Criteria) this;
        }

        public Criteria andEndorse9In(List<String> values) {
            addCriterion("endorse9 in", values, "endorse9");
            return (Criteria) this;
        }

        public Criteria andEndorse9NotIn(List<String> values) {
            addCriterion("endorse9 not in", values, "endorse9");
            return (Criteria) this;
        }

        public Criteria andEndorse9Between(String value1, String value2) {
            addCriterion("endorse9 between", value1, value2, "endorse9");
            return (Criteria) this;
        }

        public Criteria andEndorse9NotBetween(String value1, String value2) {
            addCriterion("endorse9 not between", value1, value2, "endorse9");
            return (Criteria) this;
        }

        public Criteria andEndorse10IsNull() {
            addCriterion("endorse10 is null");
            return (Criteria) this;
        }

        public Criteria andEndorse10IsNotNull() {
            addCriterion("endorse10 is not null");
            return (Criteria) this;
        }

        public Criteria andEndorse10EqualTo(String value) {
            addCriterion("endorse10 =", value, "endorse10");
            return (Criteria) this;
        }

        public Criteria andEndorse10NotEqualTo(String value) {
            addCriterion("endorse10 <>", value, "endorse10");
            return (Criteria) this;
        }

        public Criteria andEndorse10GreaterThan(String value) {
            addCriterion("endorse10 >", value, "endorse10");
            return (Criteria) this;
        }

        public Criteria andEndorse10GreaterThanOrEqualTo(String value) {
            addCriterion("endorse10 >=", value, "endorse10");
            return (Criteria) this;
        }

        public Criteria andEndorse10LessThan(String value) {
            addCriterion("endorse10 <", value, "endorse10");
            return (Criteria) this;
        }

        public Criteria andEndorse10LessThanOrEqualTo(String value) {
            addCriterion("endorse10 <=", value, "endorse10");
            return (Criteria) this;
        }

        public Criteria andEndorse10Like(String value) {
            addCriterion("endorse10 like", value, "endorse10");
            return (Criteria) this;
        }

        public Criteria andEndorse10NotLike(String value) {
            addCriterion("endorse10 not like", value, "endorse10");
            return (Criteria) this;
        }

        public Criteria andEndorse10In(List<String> values) {
            addCriterion("endorse10 in", values, "endorse10");
            return (Criteria) this;
        }

        public Criteria andEndorse10NotIn(List<String> values) {
            addCriterion("endorse10 not in", values, "endorse10");
            return (Criteria) this;
        }

        public Criteria andEndorse10Between(String value1, String value2) {
            addCriterion("endorse10 between", value1, value2, "endorse10");
            return (Criteria) this;
        }

        public Criteria andEndorse10NotBetween(String value1, String value2) {
            addCriterion("endorse10 not between", value1, value2, "endorse10");
            return (Criteria) this;
        }

        public Criteria andSamerisk15IsNull() {
            addCriterion("samerisk15 is null");
            return (Criteria) this;
        }

        public Criteria andSamerisk15IsNotNull() {
            addCriterion("samerisk15 is not null");
            return (Criteria) this;
        }

        public Criteria andSamerisk15EqualTo(String value) {
            addCriterion("samerisk15 =", value, "samerisk15");
            return (Criteria) this;
        }

        public Criteria andSamerisk15NotEqualTo(String value) {
            addCriterion("samerisk15 <>", value, "samerisk15");
            return (Criteria) this;
        }

        public Criteria andSamerisk15GreaterThan(String value) {
            addCriterion("samerisk15 >", value, "samerisk15");
            return (Criteria) this;
        }

        public Criteria andSamerisk15GreaterThanOrEqualTo(String value) {
            addCriterion("samerisk15 >=", value, "samerisk15");
            return (Criteria) this;
        }

        public Criteria andSamerisk15LessThan(String value) {
            addCriterion("samerisk15 <", value, "samerisk15");
            return (Criteria) this;
        }

        public Criteria andSamerisk15LessThanOrEqualTo(String value) {
            addCriterion("samerisk15 <=", value, "samerisk15");
            return (Criteria) this;
        }

        public Criteria andSamerisk15Like(String value) {
            addCriterion("samerisk15 like", value, "samerisk15");
            return (Criteria) this;
        }

        public Criteria andSamerisk15NotLike(String value) {
            addCriterion("samerisk15 not like", value, "samerisk15");
            return (Criteria) this;
        }

        public Criteria andSamerisk15In(List<String> values) {
            addCriterion("samerisk15 in", values, "samerisk15");
            return (Criteria) this;
        }

        public Criteria andSamerisk15NotIn(List<String> values) {
            addCriterion("samerisk15 not in", values, "samerisk15");
            return (Criteria) this;
        }

        public Criteria andSamerisk15Between(String value1, String value2) {
            addCriterion("samerisk15 between", value1, value2, "samerisk15");
            return (Criteria) this;
        }

        public Criteria andSamerisk15NotBetween(String value1, String value2) {
            addCriterion("samerisk15 not between", value1, value2, "samerisk15");
            return (Criteria) this;
        }

        public Criteria andSamerisk16IsNull() {
            addCriterion("samerisk16 is null");
            return (Criteria) this;
        }

        public Criteria andSamerisk16IsNotNull() {
            addCriterion("samerisk16 is not null");
            return (Criteria) this;
        }

        public Criteria andSamerisk16EqualTo(String value) {
            addCriterion("samerisk16 =", value, "samerisk16");
            return (Criteria) this;
        }

        public Criteria andSamerisk16NotEqualTo(String value) {
            addCriterion("samerisk16 <>", value, "samerisk16");
            return (Criteria) this;
        }

        public Criteria andSamerisk16GreaterThan(String value) {
            addCriterion("samerisk16 >", value, "samerisk16");
            return (Criteria) this;
        }

        public Criteria andSamerisk16GreaterThanOrEqualTo(String value) {
            addCriterion("samerisk16 >=", value, "samerisk16");
            return (Criteria) this;
        }

        public Criteria andSamerisk16LessThan(String value) {
            addCriterion("samerisk16 <", value, "samerisk16");
            return (Criteria) this;
        }

        public Criteria andSamerisk16LessThanOrEqualTo(String value) {
            addCriterion("samerisk16 <=", value, "samerisk16");
            return (Criteria) this;
        }

        public Criteria andSamerisk16Like(String value) {
            addCriterion("samerisk16 like", value, "samerisk16");
            return (Criteria) this;
        }

        public Criteria andSamerisk16NotLike(String value) {
            addCriterion("samerisk16 not like", value, "samerisk16");
            return (Criteria) this;
        }

        public Criteria andSamerisk16In(List<String> values) {
            addCriterion("samerisk16 in", values, "samerisk16");
            return (Criteria) this;
        }

        public Criteria andSamerisk16NotIn(List<String> values) {
            addCriterion("samerisk16 not in", values, "samerisk16");
            return (Criteria) this;
        }

        public Criteria andSamerisk16Between(String value1, String value2) {
            addCriterion("samerisk16 between", value1, value2, "samerisk16");
            return (Criteria) this;
        }

        public Criteria andSamerisk16NotBetween(String value1, String value2) {
            addCriterion("samerisk16 not between", value1, value2, "samerisk16");
            return (Criteria) this;
        }

        public Criteria andSamerisk17IsNull() {
            addCriterion("samerisk17 is null");
            return (Criteria) this;
        }

        public Criteria andSamerisk17IsNotNull() {
            addCriterion("samerisk17 is not null");
            return (Criteria) this;
        }

        public Criteria andSamerisk17EqualTo(String value) {
            addCriterion("samerisk17 =", value, "samerisk17");
            return (Criteria) this;
        }

        public Criteria andSamerisk17NotEqualTo(String value) {
            addCriterion("samerisk17 <>", value, "samerisk17");
            return (Criteria) this;
        }

        public Criteria andSamerisk17GreaterThan(String value) {
            addCriterion("samerisk17 >", value, "samerisk17");
            return (Criteria) this;
        }

        public Criteria andSamerisk17GreaterThanOrEqualTo(String value) {
            addCriterion("samerisk17 >=", value, "samerisk17");
            return (Criteria) this;
        }

        public Criteria andSamerisk17LessThan(String value) {
            addCriterion("samerisk17 <", value, "samerisk17");
            return (Criteria) this;
        }

        public Criteria andSamerisk17LessThanOrEqualTo(String value) {
            addCriterion("samerisk17 <=", value, "samerisk17");
            return (Criteria) this;
        }

        public Criteria andSamerisk17Like(String value) {
            addCriterion("samerisk17 like", value, "samerisk17");
            return (Criteria) this;
        }

        public Criteria andSamerisk17NotLike(String value) {
            addCriterion("samerisk17 not like", value, "samerisk17");
            return (Criteria) this;
        }

        public Criteria andSamerisk17In(List<String> values) {
            addCriterion("samerisk17 in", values, "samerisk17");
            return (Criteria) this;
        }

        public Criteria andSamerisk17NotIn(List<String> values) {
            addCriterion("samerisk17 not in", values, "samerisk17");
            return (Criteria) this;
        }

        public Criteria andSamerisk17Between(String value1, String value2) {
            addCriterion("samerisk17 between", value1, value2, "samerisk17");
            return (Criteria) this;
        }

        public Criteria andSamerisk17NotBetween(String value1, String value2) {
            addCriterion("samerisk17 not between", value1, value2, "samerisk17");
            return (Criteria) this;
        }

        public Criteria andSamerisk18IsNull() {
            addCriterion("samerisk18 is null");
            return (Criteria) this;
        }

        public Criteria andSamerisk18IsNotNull() {
            addCriterion("samerisk18 is not null");
            return (Criteria) this;
        }

        public Criteria andSamerisk18EqualTo(String value) {
            addCriterion("samerisk18 =", value, "samerisk18");
            return (Criteria) this;
        }

        public Criteria andSamerisk18NotEqualTo(String value) {
            addCriterion("samerisk18 <>", value, "samerisk18");
            return (Criteria) this;
        }

        public Criteria andSamerisk18GreaterThan(String value) {
            addCriterion("samerisk18 >", value, "samerisk18");
            return (Criteria) this;
        }

        public Criteria andSamerisk18GreaterThanOrEqualTo(String value) {
            addCriterion("samerisk18 >=", value, "samerisk18");
            return (Criteria) this;
        }

        public Criteria andSamerisk18LessThan(String value) {
            addCriterion("samerisk18 <", value, "samerisk18");
            return (Criteria) this;
        }

        public Criteria andSamerisk18LessThanOrEqualTo(String value) {
            addCriterion("samerisk18 <=", value, "samerisk18");
            return (Criteria) this;
        }

        public Criteria andSamerisk18Like(String value) {
            addCriterion("samerisk18 like", value, "samerisk18");
            return (Criteria) this;
        }

        public Criteria andSamerisk18NotLike(String value) {
            addCriterion("samerisk18 not like", value, "samerisk18");
            return (Criteria) this;
        }

        public Criteria andSamerisk18In(List<String> values) {
            addCriterion("samerisk18 in", values, "samerisk18");
            return (Criteria) this;
        }

        public Criteria andSamerisk18NotIn(List<String> values) {
            addCriterion("samerisk18 not in", values, "samerisk18");
            return (Criteria) this;
        }

        public Criteria andSamerisk18Between(String value1, String value2) {
            addCriterion("samerisk18 between", value1, value2, "samerisk18");
            return (Criteria) this;
        }

        public Criteria andSamerisk18NotBetween(String value1, String value2) {
            addCriterion("samerisk18 not between", value1, value2, "samerisk18");
            return (Criteria) this;
        }

        public Criteria andSamerisk19IsNull() {
            addCriterion("samerisk19 is null");
            return (Criteria) this;
        }

        public Criteria andSamerisk19IsNotNull() {
            addCriterion("samerisk19 is not null");
            return (Criteria) this;
        }

        public Criteria andSamerisk19EqualTo(String value) {
            addCriterion("samerisk19 =", value, "samerisk19");
            return (Criteria) this;
        }

        public Criteria andSamerisk19NotEqualTo(String value) {
            addCriterion("samerisk19 <>", value, "samerisk19");
            return (Criteria) this;
        }

        public Criteria andSamerisk19GreaterThan(String value) {
            addCriterion("samerisk19 >", value, "samerisk19");
            return (Criteria) this;
        }

        public Criteria andSamerisk19GreaterThanOrEqualTo(String value) {
            addCriterion("samerisk19 >=", value, "samerisk19");
            return (Criteria) this;
        }

        public Criteria andSamerisk19LessThan(String value) {
            addCriterion("samerisk19 <", value, "samerisk19");
            return (Criteria) this;
        }

        public Criteria andSamerisk19LessThanOrEqualTo(String value) {
            addCriterion("samerisk19 <=", value, "samerisk19");
            return (Criteria) this;
        }

        public Criteria andSamerisk19Like(String value) {
            addCriterion("samerisk19 like", value, "samerisk19");
            return (Criteria) this;
        }

        public Criteria andSamerisk19NotLike(String value) {
            addCriterion("samerisk19 not like", value, "samerisk19");
            return (Criteria) this;
        }

        public Criteria andSamerisk19In(List<String> values) {
            addCriterion("samerisk19 in", values, "samerisk19");
            return (Criteria) this;
        }

        public Criteria andSamerisk19NotIn(List<String> values) {
            addCriterion("samerisk19 not in", values, "samerisk19");
            return (Criteria) this;
        }

        public Criteria andSamerisk19Between(String value1, String value2) {
            addCriterion("samerisk19 between", value1, value2, "samerisk19");
            return (Criteria) this;
        }

        public Criteria andSamerisk19NotBetween(String value1, String value2) {
            addCriterion("samerisk19 not between", value1, value2, "samerisk19");
            return (Criteria) this;
        }

        public Criteria andSamerisk20IsNull() {
            addCriterion("samerisk20 is null");
            return (Criteria) this;
        }

        public Criteria andSamerisk20IsNotNull() {
            addCriterion("samerisk20 is not null");
            return (Criteria) this;
        }

        public Criteria andSamerisk20EqualTo(String value) {
            addCriterion("samerisk20 =", value, "samerisk20");
            return (Criteria) this;
        }

        public Criteria andSamerisk20NotEqualTo(String value) {
            addCriterion("samerisk20 <>", value, "samerisk20");
            return (Criteria) this;
        }

        public Criteria andSamerisk20GreaterThan(String value) {
            addCriterion("samerisk20 >", value, "samerisk20");
            return (Criteria) this;
        }

        public Criteria andSamerisk20GreaterThanOrEqualTo(String value) {
            addCriterion("samerisk20 >=", value, "samerisk20");
            return (Criteria) this;
        }

        public Criteria andSamerisk20LessThan(String value) {
            addCriterion("samerisk20 <", value, "samerisk20");
            return (Criteria) this;
        }

        public Criteria andSamerisk20LessThanOrEqualTo(String value) {
            addCriterion("samerisk20 <=", value, "samerisk20");
            return (Criteria) this;
        }

        public Criteria andSamerisk20Like(String value) {
            addCriterion("samerisk20 like", value, "samerisk20");
            return (Criteria) this;
        }

        public Criteria andSamerisk20NotLike(String value) {
            addCriterion("samerisk20 not like", value, "samerisk20");
            return (Criteria) this;
        }

        public Criteria andSamerisk20In(List<String> values) {
            addCriterion("samerisk20 in", values, "samerisk20");
            return (Criteria) this;
        }

        public Criteria andSamerisk20NotIn(List<String> values) {
            addCriterion("samerisk20 not in", values, "samerisk20");
            return (Criteria) this;
        }

        public Criteria andSamerisk20Between(String value1, String value2) {
            addCriterion("samerisk20 between", value1, value2, "samerisk20");
            return (Criteria) this;
        }

        public Criteria andSamerisk20NotBetween(String value1, String value2) {
            addCriterion("samerisk20 not between", value1, value2, "samerisk20");
            return (Criteria) this;
        }

        public Criteria andPolicy11IsNull() {
            addCriterion("policy11 is null");
            return (Criteria) this;
        }

        public Criteria andPolicy11IsNotNull() {
            addCriterion("policy11 is not null");
            return (Criteria) this;
        }

        public Criteria andPolicy11EqualTo(String value) {
            addCriterion("policy11 =", value, "policy11");
            return (Criteria) this;
        }

        public Criteria andPolicy11NotEqualTo(String value) {
            addCriterion("policy11 <>", value, "policy11");
            return (Criteria) this;
        }

        public Criteria andPolicy11GreaterThan(String value) {
            addCriterion("policy11 >", value, "policy11");
            return (Criteria) this;
        }

        public Criteria andPolicy11GreaterThanOrEqualTo(String value) {
            addCriterion("policy11 >=", value, "policy11");
            return (Criteria) this;
        }

        public Criteria andPolicy11LessThan(String value) {
            addCriterion("policy11 <", value, "policy11");
            return (Criteria) this;
        }

        public Criteria andPolicy11LessThanOrEqualTo(String value) {
            addCriterion("policy11 <=", value, "policy11");
            return (Criteria) this;
        }

        public Criteria andPolicy11Like(String value) {
            addCriterion("policy11 like", value, "policy11");
            return (Criteria) this;
        }

        public Criteria andPolicy11NotLike(String value) {
            addCriterion("policy11 not like", value, "policy11");
            return (Criteria) this;
        }

        public Criteria andPolicy11In(List<String> values) {
            addCriterion("policy11 in", values, "policy11");
            return (Criteria) this;
        }

        public Criteria andPolicy11NotIn(List<String> values) {
            addCriterion("policy11 not in", values, "policy11");
            return (Criteria) this;
        }

        public Criteria andPolicy11Between(String value1, String value2) {
            addCriterion("policy11 between", value1, value2, "policy11");
            return (Criteria) this;
        }

        public Criteria andPolicy11NotBetween(String value1, String value2) {
            addCriterion("policy11 not between", value1, value2, "policy11");
            return (Criteria) this;
        }

        public Criteria andPolicy12IsNull() {
            addCriterion("policy12 is null");
            return (Criteria) this;
        }

        public Criteria andPolicy12IsNotNull() {
            addCriterion("policy12 is not null");
            return (Criteria) this;
        }

        public Criteria andPolicy12EqualTo(String value) {
            addCriterion("policy12 =", value, "policy12");
            return (Criteria) this;
        }

        public Criteria andPolicy12NotEqualTo(String value) {
            addCriterion("policy12 <>", value, "policy12");
            return (Criteria) this;
        }

        public Criteria andPolicy12GreaterThan(String value) {
            addCriterion("policy12 >", value, "policy12");
            return (Criteria) this;
        }

        public Criteria andPolicy12GreaterThanOrEqualTo(String value) {
            addCriterion("policy12 >=", value, "policy12");
            return (Criteria) this;
        }

        public Criteria andPolicy12LessThan(String value) {
            addCriterion("policy12 <", value, "policy12");
            return (Criteria) this;
        }

        public Criteria andPolicy12LessThanOrEqualTo(String value) {
            addCriterion("policy12 <=", value, "policy12");
            return (Criteria) this;
        }

        public Criteria andPolicy12Like(String value) {
            addCriterion("policy12 like", value, "policy12");
            return (Criteria) this;
        }

        public Criteria andPolicy12NotLike(String value) {
            addCriterion("policy12 not like", value, "policy12");
            return (Criteria) this;
        }

        public Criteria andPolicy12In(List<String> values) {
            addCriterion("policy12 in", values, "policy12");
            return (Criteria) this;
        }

        public Criteria andPolicy12NotIn(List<String> values) {
            addCriterion("policy12 not in", values, "policy12");
            return (Criteria) this;
        }

        public Criteria andPolicy12Between(String value1, String value2) {
            addCriterion("policy12 between", value1, value2, "policy12");
            return (Criteria) this;
        }

        public Criteria andPolicy12NotBetween(String value1, String value2) {
            addCriterion("policy12 not between", value1, value2, "policy12");
            return (Criteria) this;
        }

        public Criteria andPolicy13IsNull() {
            addCriterion("policy13 is null");
            return (Criteria) this;
        }

        public Criteria andPolicy13IsNotNull() {
            addCriterion("policy13 is not null");
            return (Criteria) this;
        }

        public Criteria andPolicy13EqualTo(String value) {
            addCriterion("policy13 =", value, "policy13");
            return (Criteria) this;
        }

        public Criteria andPolicy13NotEqualTo(String value) {
            addCriterion("policy13 <>", value, "policy13");
            return (Criteria) this;
        }

        public Criteria andPolicy13GreaterThan(String value) {
            addCriterion("policy13 >", value, "policy13");
            return (Criteria) this;
        }

        public Criteria andPolicy13GreaterThanOrEqualTo(String value) {
            addCriterion("policy13 >=", value, "policy13");
            return (Criteria) this;
        }

        public Criteria andPolicy13LessThan(String value) {
            addCriterion("policy13 <", value, "policy13");
            return (Criteria) this;
        }

        public Criteria andPolicy13LessThanOrEqualTo(String value) {
            addCriterion("policy13 <=", value, "policy13");
            return (Criteria) this;
        }

        public Criteria andPolicy13Like(String value) {
            addCriterion("policy13 like", value, "policy13");
            return (Criteria) this;
        }

        public Criteria andPolicy13NotLike(String value) {
            addCriterion("policy13 not like", value, "policy13");
            return (Criteria) this;
        }

        public Criteria andPolicy13In(List<String> values) {
            addCriterion("policy13 in", values, "policy13");
            return (Criteria) this;
        }

        public Criteria andPolicy13NotIn(List<String> values) {
            addCriterion("policy13 not in", values, "policy13");
            return (Criteria) this;
        }

        public Criteria andPolicy13Between(String value1, String value2) {
            addCriterion("policy13 between", value1, value2, "policy13");
            return (Criteria) this;
        }

        public Criteria andPolicy13NotBetween(String value1, String value2) {
            addCriterion("policy13 not between", value1, value2, "policy13");
            return (Criteria) this;
        }

        public Criteria andPolicy14IsNull() {
            addCriterion("policy14 is null");
            return (Criteria) this;
        }

        public Criteria andPolicy14IsNotNull() {
            addCriterion("policy14 is not null");
            return (Criteria) this;
        }

        public Criteria andPolicy14EqualTo(String value) {
            addCriterion("policy14 =", value, "policy14");
            return (Criteria) this;
        }

        public Criteria andPolicy14NotEqualTo(String value) {
            addCriterion("policy14 <>", value, "policy14");
            return (Criteria) this;
        }

        public Criteria andPolicy14GreaterThan(String value) {
            addCriterion("policy14 >", value, "policy14");
            return (Criteria) this;
        }

        public Criteria andPolicy14GreaterThanOrEqualTo(String value) {
            addCriterion("policy14 >=", value, "policy14");
            return (Criteria) this;
        }

        public Criteria andPolicy14LessThan(String value) {
            addCriterion("policy14 <", value, "policy14");
            return (Criteria) this;
        }

        public Criteria andPolicy14LessThanOrEqualTo(String value) {
            addCriterion("policy14 <=", value, "policy14");
            return (Criteria) this;
        }

        public Criteria andPolicy14Like(String value) {
            addCriterion("policy14 like", value, "policy14");
            return (Criteria) this;
        }

        public Criteria andPolicy14NotLike(String value) {
            addCriterion("policy14 not like", value, "policy14");
            return (Criteria) this;
        }

        public Criteria andPolicy14In(List<String> values) {
            addCriterion("policy14 in", values, "policy14");
            return (Criteria) this;
        }

        public Criteria andPolicy14NotIn(List<String> values) {
            addCriterion("policy14 not in", values, "policy14");
            return (Criteria) this;
        }

        public Criteria andPolicy14Between(String value1, String value2) {
            addCriterion("policy14 between", value1, value2, "policy14");
            return (Criteria) this;
        }

        public Criteria andPolicy14NotBetween(String value1, String value2) {
            addCriterion("policy14 not between", value1, value2, "policy14");
            return (Criteria) this;
        }

        public Criteria andPolicy15IsNull() {
            addCriterion("policy15 is null");
            return (Criteria) this;
        }

        public Criteria andPolicy15IsNotNull() {
            addCriterion("policy15 is not null");
            return (Criteria) this;
        }

        public Criteria andPolicy15EqualTo(String value) {
            addCriterion("policy15 =", value, "policy15");
            return (Criteria) this;
        }

        public Criteria andPolicy15NotEqualTo(String value) {
            addCriterion("policy15 <>", value, "policy15");
            return (Criteria) this;
        }

        public Criteria andPolicy15GreaterThan(String value) {
            addCriterion("policy15 >", value, "policy15");
            return (Criteria) this;
        }

        public Criteria andPolicy15GreaterThanOrEqualTo(String value) {
            addCriterion("policy15 >=", value, "policy15");
            return (Criteria) this;
        }

        public Criteria andPolicy15LessThan(String value) {
            addCriterion("policy15 <", value, "policy15");
            return (Criteria) this;
        }

        public Criteria andPolicy15LessThanOrEqualTo(String value) {
            addCriterion("policy15 <=", value, "policy15");
            return (Criteria) this;
        }

        public Criteria andPolicy15Like(String value) {
            addCriterion("policy15 like", value, "policy15");
            return (Criteria) this;
        }

        public Criteria andPolicy15NotLike(String value) {
            addCriterion("policy15 not like", value, "policy15");
            return (Criteria) this;
        }

        public Criteria andPolicy15In(List<String> values) {
            addCriterion("policy15 in", values, "policy15");
            return (Criteria) this;
        }

        public Criteria andPolicy15NotIn(List<String> values) {
            addCriterion("policy15 not in", values, "policy15");
            return (Criteria) this;
        }

        public Criteria andPolicy15Between(String value1, String value2) {
            addCriterion("policy15 between", value1, value2, "policy15");
            return (Criteria) this;
        }

        public Criteria andPolicy15NotBetween(String value1, String value2) {
            addCriterion("policy15 not between", value1, value2, "policy15");
            return (Criteria) this;
        }

        public Criteria andPolicy16IsNull() {
            addCriterion("policy16 is null");
            return (Criteria) this;
        }

        public Criteria andPolicy16IsNotNull() {
            addCriterion("policy16 is not null");
            return (Criteria) this;
        }

        public Criteria andPolicy16EqualTo(String value) {
            addCriterion("policy16 =", value, "policy16");
            return (Criteria) this;
        }

        public Criteria andPolicy16NotEqualTo(String value) {
            addCriterion("policy16 <>", value, "policy16");
            return (Criteria) this;
        }

        public Criteria andPolicy16GreaterThan(String value) {
            addCriterion("policy16 >", value, "policy16");
            return (Criteria) this;
        }

        public Criteria andPolicy16GreaterThanOrEqualTo(String value) {
            addCriterion("policy16 >=", value, "policy16");
            return (Criteria) this;
        }

        public Criteria andPolicy16LessThan(String value) {
            addCriterion("policy16 <", value, "policy16");
            return (Criteria) this;
        }

        public Criteria andPolicy16LessThanOrEqualTo(String value) {
            addCriterion("policy16 <=", value, "policy16");
            return (Criteria) this;
        }

        public Criteria andPolicy16Like(String value) {
            addCriterion("policy16 like", value, "policy16");
            return (Criteria) this;
        }

        public Criteria andPolicy16NotLike(String value) {
            addCriterion("policy16 not like", value, "policy16");
            return (Criteria) this;
        }

        public Criteria andPolicy16In(List<String> values) {
            addCriterion("policy16 in", values, "policy16");
            return (Criteria) this;
        }

        public Criteria andPolicy16NotIn(List<String> values) {
            addCriterion("policy16 not in", values, "policy16");
            return (Criteria) this;
        }

        public Criteria andPolicy16Between(String value1, String value2) {
            addCriterion("policy16 between", value1, value2, "policy16");
            return (Criteria) this;
        }

        public Criteria andPolicy16NotBetween(String value1, String value2) {
            addCriterion("policy16 not between", value1, value2, "policy16");
            return (Criteria) this;
        }

        public Criteria andPolicy17IsNull() {
            addCriterion("policy17 is null");
            return (Criteria) this;
        }

        public Criteria andPolicy17IsNotNull() {
            addCriterion("policy17 is not null");
            return (Criteria) this;
        }

        public Criteria andPolicy17EqualTo(String value) {
            addCriterion("policy17 =", value, "policy17");
            return (Criteria) this;
        }

        public Criteria andPolicy17NotEqualTo(String value) {
            addCriterion("policy17 <>", value, "policy17");
            return (Criteria) this;
        }

        public Criteria andPolicy17GreaterThan(String value) {
            addCriterion("policy17 >", value, "policy17");
            return (Criteria) this;
        }

        public Criteria andPolicy17GreaterThanOrEqualTo(String value) {
            addCriterion("policy17 >=", value, "policy17");
            return (Criteria) this;
        }

        public Criteria andPolicy17LessThan(String value) {
            addCriterion("policy17 <", value, "policy17");
            return (Criteria) this;
        }

        public Criteria andPolicy17LessThanOrEqualTo(String value) {
            addCriterion("policy17 <=", value, "policy17");
            return (Criteria) this;
        }

        public Criteria andPolicy17Like(String value) {
            addCriterion("policy17 like", value, "policy17");
            return (Criteria) this;
        }

        public Criteria andPolicy17NotLike(String value) {
            addCriterion("policy17 not like", value, "policy17");
            return (Criteria) this;
        }

        public Criteria andPolicy17In(List<String> values) {
            addCriterion("policy17 in", values, "policy17");
            return (Criteria) this;
        }

        public Criteria andPolicy17NotIn(List<String> values) {
            addCriterion("policy17 not in", values, "policy17");
            return (Criteria) this;
        }

        public Criteria andPolicy17Between(String value1, String value2) {
            addCriterion("policy17 between", value1, value2, "policy17");
            return (Criteria) this;
        }

        public Criteria andPolicy17NotBetween(String value1, String value2) {
            addCriterion("policy17 not between", value1, value2, "policy17");
            return (Criteria) this;
        }

        public Criteria andPolicy18IsNull() {
            addCriterion("policy18 is null");
            return (Criteria) this;
        }

        public Criteria andPolicy18IsNotNull() {
            addCriterion("policy18 is not null");
            return (Criteria) this;
        }

        public Criteria andPolicy18EqualTo(String value) {
            addCriterion("policy18 =", value, "policy18");
            return (Criteria) this;
        }

        public Criteria andPolicy18NotEqualTo(String value) {
            addCriterion("policy18 <>", value, "policy18");
            return (Criteria) this;
        }

        public Criteria andPolicy18GreaterThan(String value) {
            addCriterion("policy18 >", value, "policy18");
            return (Criteria) this;
        }

        public Criteria andPolicy18GreaterThanOrEqualTo(String value) {
            addCriterion("policy18 >=", value, "policy18");
            return (Criteria) this;
        }

        public Criteria andPolicy18LessThan(String value) {
            addCriterion("policy18 <", value, "policy18");
            return (Criteria) this;
        }

        public Criteria andPolicy18LessThanOrEqualTo(String value) {
            addCriterion("policy18 <=", value, "policy18");
            return (Criteria) this;
        }

        public Criteria andPolicy18Like(String value) {
            addCriterion("policy18 like", value, "policy18");
            return (Criteria) this;
        }

        public Criteria andPolicy18NotLike(String value) {
            addCriterion("policy18 not like", value, "policy18");
            return (Criteria) this;
        }

        public Criteria andPolicy18In(List<String> values) {
            addCriterion("policy18 in", values, "policy18");
            return (Criteria) this;
        }

        public Criteria andPolicy18NotIn(List<String> values) {
            addCriterion("policy18 not in", values, "policy18");
            return (Criteria) this;
        }

        public Criteria andPolicy18Between(String value1, String value2) {
            addCriterion("policy18 between", value1, value2, "policy18");
            return (Criteria) this;
        }

        public Criteria andPolicy18NotBetween(String value1, String value2) {
            addCriterion("policy18 not between", value1, value2, "policy18");
            return (Criteria) this;
        }

        public Criteria andPolicy19IsNull() {
            addCriterion("policy19 is null");
            return (Criteria) this;
        }

        public Criteria andPolicy19IsNotNull() {
            addCriterion("policy19 is not null");
            return (Criteria) this;
        }

        public Criteria andPolicy19EqualTo(String value) {
            addCriterion("policy19 =", value, "policy19");
            return (Criteria) this;
        }

        public Criteria andPolicy19NotEqualTo(String value) {
            addCriterion("policy19 <>", value, "policy19");
            return (Criteria) this;
        }

        public Criteria andPolicy19GreaterThan(String value) {
            addCriterion("policy19 >", value, "policy19");
            return (Criteria) this;
        }

        public Criteria andPolicy19GreaterThanOrEqualTo(String value) {
            addCriterion("policy19 >=", value, "policy19");
            return (Criteria) this;
        }

        public Criteria andPolicy19LessThan(String value) {
            addCriterion("policy19 <", value, "policy19");
            return (Criteria) this;
        }

        public Criteria andPolicy19LessThanOrEqualTo(String value) {
            addCriterion("policy19 <=", value, "policy19");
            return (Criteria) this;
        }

        public Criteria andPolicy19Like(String value) {
            addCriterion("policy19 like", value, "policy19");
            return (Criteria) this;
        }

        public Criteria andPolicy19NotLike(String value) {
            addCriterion("policy19 not like", value, "policy19");
            return (Criteria) this;
        }

        public Criteria andPolicy19In(List<String> values) {
            addCriterion("policy19 in", values, "policy19");
            return (Criteria) this;
        }

        public Criteria andPolicy19NotIn(List<String> values) {
            addCriterion("policy19 not in", values, "policy19");
            return (Criteria) this;
        }

        public Criteria andPolicy19Between(String value1, String value2) {
            addCriterion("policy19 between", value1, value2, "policy19");
            return (Criteria) this;
        }

        public Criteria andPolicy19NotBetween(String value1, String value2) {
            addCriterion("policy19 not between", value1, value2, "policy19");
            return (Criteria) this;
        }

        public Criteria andPolicy20IsNull() {
            addCriterion("policy20 is null");
            return (Criteria) this;
        }

        public Criteria andPolicy20IsNotNull() {
            addCriterion("policy20 is not null");
            return (Criteria) this;
        }

        public Criteria andPolicy20EqualTo(String value) {
            addCriterion("policy20 =", value, "policy20");
            return (Criteria) this;
        }

        public Criteria andPolicy20NotEqualTo(String value) {
            addCriterion("policy20 <>", value, "policy20");
            return (Criteria) this;
        }

        public Criteria andPolicy20GreaterThan(String value) {
            addCriterion("policy20 >", value, "policy20");
            return (Criteria) this;
        }

        public Criteria andPolicy20GreaterThanOrEqualTo(String value) {
            addCriterion("policy20 >=", value, "policy20");
            return (Criteria) this;
        }

        public Criteria andPolicy20LessThan(String value) {
            addCriterion("policy20 <", value, "policy20");
            return (Criteria) this;
        }

        public Criteria andPolicy20LessThanOrEqualTo(String value) {
            addCriterion("policy20 <=", value, "policy20");
            return (Criteria) this;
        }

        public Criteria andPolicy20Like(String value) {
            addCriterion("policy20 like", value, "policy20");
            return (Criteria) this;
        }

        public Criteria andPolicy20NotLike(String value) {
            addCriterion("policy20 not like", value, "policy20");
            return (Criteria) this;
        }

        public Criteria andPolicy20In(List<String> values) {
            addCriterion("policy20 in", values, "policy20");
            return (Criteria) this;
        }

        public Criteria andPolicy20NotIn(List<String> values) {
            addCriterion("policy20 not in", values, "policy20");
            return (Criteria) this;
        }

        public Criteria andPolicy20Between(String value1, String value2) {
            addCriterion("policy20 between", value1, value2, "policy20");
            return (Criteria) this;
        }

        public Criteria andPolicy20NotBetween(String value1, String value2) {
            addCriterion("policy20 not between", value1, value2, "policy20");
            return (Criteria) this;
        }

        public Criteria andEndorse11IsNull() {
            addCriterion("endorse11 is null");
            return (Criteria) this;
        }

        public Criteria andEndorse11IsNotNull() {
            addCriterion("endorse11 is not null");
            return (Criteria) this;
        }

        public Criteria andEndorse11EqualTo(String value) {
            addCriterion("endorse11 =", value, "endorse11");
            return (Criteria) this;
        }

        public Criteria andEndorse11NotEqualTo(String value) {
            addCriterion("endorse11 <>", value, "endorse11");
            return (Criteria) this;
        }

        public Criteria andEndorse11GreaterThan(String value) {
            addCriterion("endorse11 >", value, "endorse11");
            return (Criteria) this;
        }

        public Criteria andEndorse11GreaterThanOrEqualTo(String value) {
            addCriterion("endorse11 >=", value, "endorse11");
            return (Criteria) this;
        }

        public Criteria andEndorse11LessThan(String value) {
            addCriterion("endorse11 <", value, "endorse11");
            return (Criteria) this;
        }

        public Criteria andEndorse11LessThanOrEqualTo(String value) {
            addCriterion("endorse11 <=", value, "endorse11");
            return (Criteria) this;
        }

        public Criteria andEndorse11Like(String value) {
            addCriterion("endorse11 like", value, "endorse11");
            return (Criteria) this;
        }

        public Criteria andEndorse11NotLike(String value) {
            addCriterion("endorse11 not like", value, "endorse11");
            return (Criteria) this;
        }

        public Criteria andEndorse11In(List<String> values) {
            addCriterion("endorse11 in", values, "endorse11");
            return (Criteria) this;
        }

        public Criteria andEndorse11NotIn(List<String> values) {
            addCriterion("endorse11 not in", values, "endorse11");
            return (Criteria) this;
        }

        public Criteria andEndorse11Between(String value1, String value2) {
            addCriterion("endorse11 between", value1, value2, "endorse11");
            return (Criteria) this;
        }

        public Criteria andEndorse11NotBetween(String value1, String value2) {
            addCriterion("endorse11 not between", value1, value2, "endorse11");
            return (Criteria) this;
        }

        public Criteria andEndorse12IsNull() {
            addCriterion("endorse12 is null");
            return (Criteria) this;
        }

        public Criteria andEndorse12IsNotNull() {
            addCriterion("endorse12 is not null");
            return (Criteria) this;
        }

        public Criteria andEndorse12EqualTo(String value) {
            addCriterion("endorse12 =", value, "endorse12");
            return (Criteria) this;
        }

        public Criteria andEndorse12NotEqualTo(String value) {
            addCriterion("endorse12 <>", value, "endorse12");
            return (Criteria) this;
        }

        public Criteria andEndorse12GreaterThan(String value) {
            addCriterion("endorse12 >", value, "endorse12");
            return (Criteria) this;
        }

        public Criteria andEndorse12GreaterThanOrEqualTo(String value) {
            addCriterion("endorse12 >=", value, "endorse12");
            return (Criteria) this;
        }

        public Criteria andEndorse12LessThan(String value) {
            addCriterion("endorse12 <", value, "endorse12");
            return (Criteria) this;
        }

        public Criteria andEndorse12LessThanOrEqualTo(String value) {
            addCriterion("endorse12 <=", value, "endorse12");
            return (Criteria) this;
        }

        public Criteria andEndorse12Like(String value) {
            addCriterion("endorse12 like", value, "endorse12");
            return (Criteria) this;
        }

        public Criteria andEndorse12NotLike(String value) {
            addCriterion("endorse12 not like", value, "endorse12");
            return (Criteria) this;
        }

        public Criteria andEndorse12In(List<String> values) {
            addCriterion("endorse12 in", values, "endorse12");
            return (Criteria) this;
        }

        public Criteria andEndorse12NotIn(List<String> values) {
            addCriterion("endorse12 not in", values, "endorse12");
            return (Criteria) this;
        }

        public Criteria andEndorse12Between(String value1, String value2) {
            addCriterion("endorse12 between", value1, value2, "endorse12");
            return (Criteria) this;
        }

        public Criteria andEndorse12NotBetween(String value1, String value2) {
            addCriterion("endorse12 not between", value1, value2, "endorse12");
            return (Criteria) this;
        }

        public Criteria andEndorse13IsNull() {
            addCriterion("endorse13 is null");
            return (Criteria) this;
        }

        public Criteria andEndorse13IsNotNull() {
            addCriterion("endorse13 is not null");
            return (Criteria) this;
        }

        public Criteria andEndorse13EqualTo(String value) {
            addCriterion("endorse13 =", value, "endorse13");
            return (Criteria) this;
        }

        public Criteria andEndorse13NotEqualTo(String value) {
            addCriterion("endorse13 <>", value, "endorse13");
            return (Criteria) this;
        }

        public Criteria andEndorse13GreaterThan(String value) {
            addCriterion("endorse13 >", value, "endorse13");
            return (Criteria) this;
        }

        public Criteria andEndorse13GreaterThanOrEqualTo(String value) {
            addCriterion("endorse13 >=", value, "endorse13");
            return (Criteria) this;
        }

        public Criteria andEndorse13LessThan(String value) {
            addCriterion("endorse13 <", value, "endorse13");
            return (Criteria) this;
        }

        public Criteria andEndorse13LessThanOrEqualTo(String value) {
            addCriterion("endorse13 <=", value, "endorse13");
            return (Criteria) this;
        }

        public Criteria andEndorse13Like(String value) {
            addCriterion("endorse13 like", value, "endorse13");
            return (Criteria) this;
        }

        public Criteria andEndorse13NotLike(String value) {
            addCriterion("endorse13 not like", value, "endorse13");
            return (Criteria) this;
        }

        public Criteria andEndorse13In(List<String> values) {
            addCriterion("endorse13 in", values, "endorse13");
            return (Criteria) this;
        }

        public Criteria andEndorse13NotIn(List<String> values) {
            addCriterion("endorse13 not in", values, "endorse13");
            return (Criteria) this;
        }

        public Criteria andEndorse13Between(String value1, String value2) {
            addCriterion("endorse13 between", value1, value2, "endorse13");
            return (Criteria) this;
        }

        public Criteria andEndorse13NotBetween(String value1, String value2) {
            addCriterion("endorse13 not between", value1, value2, "endorse13");
            return (Criteria) this;
        }

        public Criteria andEndorse14IsNull() {
            addCriterion("endorse14 is null");
            return (Criteria) this;
        }

        public Criteria andEndorse14IsNotNull() {
            addCriterion("endorse14 is not null");
            return (Criteria) this;
        }

        public Criteria andEndorse14EqualTo(String value) {
            addCriterion("endorse14 =", value, "endorse14");
            return (Criteria) this;
        }

        public Criteria andEndorse14NotEqualTo(String value) {
            addCriterion("endorse14 <>", value, "endorse14");
            return (Criteria) this;
        }

        public Criteria andEndorse14GreaterThan(String value) {
            addCriterion("endorse14 >", value, "endorse14");
            return (Criteria) this;
        }

        public Criteria andEndorse14GreaterThanOrEqualTo(String value) {
            addCriterion("endorse14 >=", value, "endorse14");
            return (Criteria) this;
        }

        public Criteria andEndorse14LessThan(String value) {
            addCriterion("endorse14 <", value, "endorse14");
            return (Criteria) this;
        }

        public Criteria andEndorse14LessThanOrEqualTo(String value) {
            addCriterion("endorse14 <=", value, "endorse14");
            return (Criteria) this;
        }

        public Criteria andEndorse14Like(String value) {
            addCriterion("endorse14 like", value, "endorse14");
            return (Criteria) this;
        }

        public Criteria andEndorse14NotLike(String value) {
            addCriterion("endorse14 not like", value, "endorse14");
            return (Criteria) this;
        }

        public Criteria andEndorse14In(List<String> values) {
            addCriterion("endorse14 in", values, "endorse14");
            return (Criteria) this;
        }

        public Criteria andEndorse14NotIn(List<String> values) {
            addCriterion("endorse14 not in", values, "endorse14");
            return (Criteria) this;
        }

        public Criteria andEndorse14Between(String value1, String value2) {
            addCriterion("endorse14 between", value1, value2, "endorse14");
            return (Criteria) this;
        }

        public Criteria andEndorse14NotBetween(String value1, String value2) {
            addCriterion("endorse14 not between", value1, value2, "endorse14");
            return (Criteria) this;
        }

        public Criteria andEndorse15IsNull() {
            addCriterion("endorse15 is null");
            return (Criteria) this;
        }

        public Criteria andEndorse15IsNotNull() {
            addCriterion("endorse15 is not null");
            return (Criteria) this;
        }

        public Criteria andEndorse15EqualTo(String value) {
            addCriterion("endorse15 =", value, "endorse15");
            return (Criteria) this;
        }

        public Criteria andEndorse15NotEqualTo(String value) {
            addCriterion("endorse15 <>", value, "endorse15");
            return (Criteria) this;
        }

        public Criteria andEndorse15GreaterThan(String value) {
            addCriterion("endorse15 >", value, "endorse15");
            return (Criteria) this;
        }

        public Criteria andEndorse15GreaterThanOrEqualTo(String value) {
            addCriterion("endorse15 >=", value, "endorse15");
            return (Criteria) this;
        }

        public Criteria andEndorse15LessThan(String value) {
            addCriterion("endorse15 <", value, "endorse15");
            return (Criteria) this;
        }

        public Criteria andEndorse15LessThanOrEqualTo(String value) {
            addCriterion("endorse15 <=", value, "endorse15");
            return (Criteria) this;
        }

        public Criteria andEndorse15Like(String value) {
            addCriterion("endorse15 like", value, "endorse15");
            return (Criteria) this;
        }

        public Criteria andEndorse15NotLike(String value) {
            addCriterion("endorse15 not like", value, "endorse15");
            return (Criteria) this;
        }

        public Criteria andEndorse15In(List<String> values) {
            addCriterion("endorse15 in", values, "endorse15");
            return (Criteria) this;
        }

        public Criteria andEndorse15NotIn(List<String> values) {
            addCriterion("endorse15 not in", values, "endorse15");
            return (Criteria) this;
        }

        public Criteria andEndorse15Between(String value1, String value2) {
            addCriterion("endorse15 between", value1, value2, "endorse15");
            return (Criteria) this;
        }

        public Criteria andEndorse15NotBetween(String value1, String value2) {
            addCriterion("endorse15 not between", value1, value2, "endorse15");
            return (Criteria) this;
        }

        public Criteria andEndorse16IsNull() {
            addCriterion("endorse16 is null");
            return (Criteria) this;
        }

        public Criteria andEndorse16IsNotNull() {
            addCriterion("endorse16 is not null");
            return (Criteria) this;
        }

        public Criteria andEndorse16EqualTo(String value) {
            addCriterion("endorse16 =", value, "endorse16");
            return (Criteria) this;
        }

        public Criteria andEndorse16NotEqualTo(String value) {
            addCriterion("endorse16 <>", value, "endorse16");
            return (Criteria) this;
        }

        public Criteria andEndorse16GreaterThan(String value) {
            addCriterion("endorse16 >", value, "endorse16");
            return (Criteria) this;
        }

        public Criteria andEndorse16GreaterThanOrEqualTo(String value) {
            addCriterion("endorse16 >=", value, "endorse16");
            return (Criteria) this;
        }

        public Criteria andEndorse16LessThan(String value) {
            addCriterion("endorse16 <", value, "endorse16");
            return (Criteria) this;
        }

        public Criteria andEndorse16LessThanOrEqualTo(String value) {
            addCriterion("endorse16 <=", value, "endorse16");
            return (Criteria) this;
        }

        public Criteria andEndorse16Like(String value) {
            addCriterion("endorse16 like", value, "endorse16");
            return (Criteria) this;
        }

        public Criteria andEndorse16NotLike(String value) {
            addCriterion("endorse16 not like", value, "endorse16");
            return (Criteria) this;
        }

        public Criteria andEndorse16In(List<String> values) {
            addCriterion("endorse16 in", values, "endorse16");
            return (Criteria) this;
        }

        public Criteria andEndorse16NotIn(List<String> values) {
            addCriterion("endorse16 not in", values, "endorse16");
            return (Criteria) this;
        }

        public Criteria andEndorse16Between(String value1, String value2) {
            addCriterion("endorse16 between", value1, value2, "endorse16");
            return (Criteria) this;
        }

        public Criteria andEndorse16NotBetween(String value1, String value2) {
            addCriterion("endorse16 not between", value1, value2, "endorse16");
            return (Criteria) this;
        }

        public Criteria andEndorse17IsNull() {
            addCriterion("endorse17 is null");
            return (Criteria) this;
        }

        public Criteria andEndorse17IsNotNull() {
            addCriterion("endorse17 is not null");
            return (Criteria) this;
        }

        public Criteria andEndorse17EqualTo(String value) {
            addCriterion("endorse17 =", value, "endorse17");
            return (Criteria) this;
        }

        public Criteria andEndorse17NotEqualTo(String value) {
            addCriterion("endorse17 <>", value, "endorse17");
            return (Criteria) this;
        }

        public Criteria andEndorse17GreaterThan(String value) {
            addCriterion("endorse17 >", value, "endorse17");
            return (Criteria) this;
        }

        public Criteria andEndorse17GreaterThanOrEqualTo(String value) {
            addCriterion("endorse17 >=", value, "endorse17");
            return (Criteria) this;
        }

        public Criteria andEndorse17LessThan(String value) {
            addCriterion("endorse17 <", value, "endorse17");
            return (Criteria) this;
        }

        public Criteria andEndorse17LessThanOrEqualTo(String value) {
            addCriterion("endorse17 <=", value, "endorse17");
            return (Criteria) this;
        }

        public Criteria andEndorse17Like(String value) {
            addCriterion("endorse17 like", value, "endorse17");
            return (Criteria) this;
        }

        public Criteria andEndorse17NotLike(String value) {
            addCriterion("endorse17 not like", value, "endorse17");
            return (Criteria) this;
        }

        public Criteria andEndorse17In(List<String> values) {
            addCriterion("endorse17 in", values, "endorse17");
            return (Criteria) this;
        }

        public Criteria andEndorse17NotIn(List<String> values) {
            addCriterion("endorse17 not in", values, "endorse17");
            return (Criteria) this;
        }

        public Criteria andEndorse17Between(String value1, String value2) {
            addCriterion("endorse17 between", value1, value2, "endorse17");
            return (Criteria) this;
        }

        public Criteria andEndorse17NotBetween(String value1, String value2) {
            addCriterion("endorse17 not between", value1, value2, "endorse17");
            return (Criteria) this;
        }

        public Criteria andEndorse18IsNull() {
            addCriterion("endorse18 is null");
            return (Criteria) this;
        }

        public Criteria andEndorse18IsNotNull() {
            addCriterion("endorse18 is not null");
            return (Criteria) this;
        }

        public Criteria andEndorse18EqualTo(String value) {
            addCriterion("endorse18 =", value, "endorse18");
            return (Criteria) this;
        }

        public Criteria andEndorse18NotEqualTo(String value) {
            addCriterion("endorse18 <>", value, "endorse18");
            return (Criteria) this;
        }

        public Criteria andEndorse18GreaterThan(String value) {
            addCriterion("endorse18 >", value, "endorse18");
            return (Criteria) this;
        }

        public Criteria andEndorse18GreaterThanOrEqualTo(String value) {
            addCriterion("endorse18 >=", value, "endorse18");
            return (Criteria) this;
        }

        public Criteria andEndorse18LessThan(String value) {
            addCriterion("endorse18 <", value, "endorse18");
            return (Criteria) this;
        }

        public Criteria andEndorse18LessThanOrEqualTo(String value) {
            addCriterion("endorse18 <=", value, "endorse18");
            return (Criteria) this;
        }

        public Criteria andEndorse18Like(String value) {
            addCriterion("endorse18 like", value, "endorse18");
            return (Criteria) this;
        }

        public Criteria andEndorse18NotLike(String value) {
            addCriterion("endorse18 not like", value, "endorse18");
            return (Criteria) this;
        }

        public Criteria andEndorse18In(List<String> values) {
            addCriterion("endorse18 in", values, "endorse18");
            return (Criteria) this;
        }

        public Criteria andEndorse18NotIn(List<String> values) {
            addCriterion("endorse18 not in", values, "endorse18");
            return (Criteria) this;
        }

        public Criteria andEndorse18Between(String value1, String value2) {
            addCriterion("endorse18 between", value1, value2, "endorse18");
            return (Criteria) this;
        }

        public Criteria andEndorse18NotBetween(String value1, String value2) {
            addCriterion("endorse18 not between", value1, value2, "endorse18");
            return (Criteria) this;
        }

        public Criteria andEndorse19IsNull() {
            addCriterion("endorse19 is null");
            return (Criteria) this;
        }

        public Criteria andEndorse19IsNotNull() {
            addCriterion("endorse19 is not null");
            return (Criteria) this;
        }

        public Criteria andEndorse19EqualTo(String value) {
            addCriterion("endorse19 =", value, "endorse19");
            return (Criteria) this;
        }

        public Criteria andEndorse19NotEqualTo(String value) {
            addCriterion("endorse19 <>", value, "endorse19");
            return (Criteria) this;
        }

        public Criteria andEndorse19GreaterThan(String value) {
            addCriterion("endorse19 >", value, "endorse19");
            return (Criteria) this;
        }

        public Criteria andEndorse19GreaterThanOrEqualTo(String value) {
            addCriterion("endorse19 >=", value, "endorse19");
            return (Criteria) this;
        }

        public Criteria andEndorse19LessThan(String value) {
            addCriterion("endorse19 <", value, "endorse19");
            return (Criteria) this;
        }

        public Criteria andEndorse19LessThanOrEqualTo(String value) {
            addCriterion("endorse19 <=", value, "endorse19");
            return (Criteria) this;
        }

        public Criteria andEndorse19Like(String value) {
            addCriterion("endorse19 like", value, "endorse19");
            return (Criteria) this;
        }

        public Criteria andEndorse19NotLike(String value) {
            addCriterion("endorse19 not like", value, "endorse19");
            return (Criteria) this;
        }

        public Criteria andEndorse19In(List<String> values) {
            addCriterion("endorse19 in", values, "endorse19");
            return (Criteria) this;
        }

        public Criteria andEndorse19NotIn(List<String> values) {
            addCriterion("endorse19 not in", values, "endorse19");
            return (Criteria) this;
        }

        public Criteria andEndorse19Between(String value1, String value2) {
            addCriterion("endorse19 between", value1, value2, "endorse19");
            return (Criteria) this;
        }

        public Criteria andEndorse19NotBetween(String value1, String value2) {
            addCriterion("endorse19 not between", value1, value2, "endorse19");
            return (Criteria) this;
        }

        public Criteria andEndorse20IsNull() {
            addCriterion("endorse20 is null");
            return (Criteria) this;
        }

        public Criteria andEndorse20IsNotNull() {
            addCriterion("endorse20 is not null");
            return (Criteria) this;
        }

        public Criteria andEndorse20EqualTo(String value) {
            addCriterion("endorse20 =", value, "endorse20");
            return (Criteria) this;
        }

        public Criteria andEndorse20NotEqualTo(String value) {
            addCriterion("endorse20 <>", value, "endorse20");
            return (Criteria) this;
        }

        public Criteria andEndorse20GreaterThan(String value) {
            addCriterion("endorse20 >", value, "endorse20");
            return (Criteria) this;
        }

        public Criteria andEndorse20GreaterThanOrEqualTo(String value) {
            addCriterion("endorse20 >=", value, "endorse20");
            return (Criteria) this;
        }

        public Criteria andEndorse20LessThan(String value) {
            addCriterion("endorse20 <", value, "endorse20");
            return (Criteria) this;
        }

        public Criteria andEndorse20LessThanOrEqualTo(String value) {
            addCriterion("endorse20 <=", value, "endorse20");
            return (Criteria) this;
        }

        public Criteria andEndorse20Like(String value) {
            addCriterion("endorse20 like", value, "endorse20");
            return (Criteria) this;
        }

        public Criteria andEndorse20NotLike(String value) {
            addCriterion("endorse20 not like", value, "endorse20");
            return (Criteria) this;
        }

        public Criteria andEndorse20In(List<String> values) {
            addCriterion("endorse20 in", values, "endorse20");
            return (Criteria) this;
        }

        public Criteria andEndorse20NotIn(List<String> values) {
            addCriterion("endorse20 not in", values, "endorse20");
            return (Criteria) this;
        }

        public Criteria andEndorse20Between(String value1, String value2) {
            addCriterion("endorse20 between", value1, value2, "endorse20");
            return (Criteria) this;
        }

        public Criteria andEndorse20NotBetween(String value1, String value2) {
            addCriterion("endorse20 not between", value1, value2, "endorse20");
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