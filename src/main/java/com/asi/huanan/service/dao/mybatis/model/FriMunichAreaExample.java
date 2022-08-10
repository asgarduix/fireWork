package com.asi.huanan.service.dao.mybatis.model;

import java.util.ArrayList;
import java.util.List;

public class FriMunichAreaExample {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table fri_munich_area
     *
     * @mbg.generated Thu Oct 28 11:19:29 CST 2021
     */
    protected String orderByClause;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table fri_munich_area
     *
     * @mbg.generated Thu Oct 28 11:19:29 CST 2021
     */
    protected boolean distinct;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table fri_munich_area
     *
     * @mbg.generated Thu Oct 28 11:19:29 CST 2021
     */
    protected List<Criteria> oredCriteria;

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table fri_munich_area
     *
     * @mbg.generated Thu Oct 28 11:19:29 CST 2021
     */
    public FriMunichAreaExample() {
        oredCriteria = new ArrayList<>();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table fri_munich_area
     *
     * @mbg.generated Thu Oct 28 11:19:29 CST 2021
     */
    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table fri_munich_area
     *
     * @mbg.generated Thu Oct 28 11:19:29 CST 2021
     */
    public String getOrderByClause() {
        return orderByClause;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table fri_munich_area
     *
     * @mbg.generated Thu Oct 28 11:19:29 CST 2021
     */
    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table fri_munich_area
     *
     * @mbg.generated Thu Oct 28 11:19:29 CST 2021
     */
    public boolean isDistinct() {
        return distinct;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table fri_munich_area
     *
     * @mbg.generated Thu Oct 28 11:19:29 CST 2021
     */
    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table fri_munich_area
     *
     * @mbg.generated Thu Oct 28 11:19:29 CST 2021
     */
    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table fri_munich_area
     *
     * @mbg.generated Thu Oct 28 11:19:29 CST 2021
     */
    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table fri_munich_area
     *
     * @mbg.generated Thu Oct 28 11:19:29 CST 2021
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
     * This method corresponds to the database table fri_munich_area
     *
     * @mbg.generated Thu Oct 28 11:19:29 CST 2021
     */
    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table fri_munich_area
     *
     * @mbg.generated Thu Oct 28 11:19:29 CST 2021
     */
    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table fri_munich_area
     *
     * @mbg.generated Thu Oct 28 11:19:29 CST 2021
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

        public Criteria andNaturalIdIsNull() {
            addCriterion("natural_id is null");
            return (Criteria) this;
        }

        public Criteria andNaturalIdIsNotNull() {
            addCriterion("natural_id is not null");
            return (Criteria) this;
        }

        public Criteria andNaturalIdEqualTo(String value) {
            addCriterion("natural_id =", value, "naturalId");
            return (Criteria) this;
        }

        public Criteria andNaturalIdNotEqualTo(String value) {
            addCriterion("natural_id <>", value, "naturalId");
            return (Criteria) this;
        }

        public Criteria andNaturalIdGreaterThan(String value) {
            addCriterion("natural_id >", value, "naturalId");
            return (Criteria) this;
        }

        public Criteria andNaturalIdGreaterThanOrEqualTo(String value) {
            addCriterion("natural_id >=", value, "naturalId");
            return (Criteria) this;
        }

        public Criteria andNaturalIdLessThan(String value) {
            addCriterion("natural_id <", value, "naturalId");
            return (Criteria) this;
        }

        public Criteria andNaturalIdLessThanOrEqualTo(String value) {
            addCriterion("natural_id <=", value, "naturalId");
            return (Criteria) this;
        }

        public Criteria andNaturalIdLike(String value) {
            addCriterion("natural_id like", value, "naturalId");
            return (Criteria) this;
        }

        public Criteria andNaturalIdNotLike(String value) {
            addCriterion("natural_id not like", value, "naturalId");
            return (Criteria) this;
        }

        public Criteria andNaturalIdIn(List<String> values) {
            addCriterion("natural_id in", values, "naturalId");
            return (Criteria) this;
        }

        public Criteria andNaturalIdNotIn(List<String> values) {
            addCriterion("natural_id not in", values, "naturalId");
            return (Criteria) this;
        }

        public Criteria andNaturalIdBetween(String value1, String value2) {
            addCriterion("natural_id between", value1, value2, "naturalId");
            return (Criteria) this;
        }

        public Criteria andNaturalIdNotBetween(String value1, String value2) {
            addCriterion("natural_id not between", value1, value2, "naturalId");
            return (Criteria) this;
        }

        public Criteria andCityNameIsNull() {
            addCriterion("city_name is null");
            return (Criteria) this;
        }

        public Criteria andCityNameIsNotNull() {
            addCriterion("city_name is not null");
            return (Criteria) this;
        }

        public Criteria andCityNameEqualTo(String value) {
            addCriterion("city_name =", value, "cityName");
            return (Criteria) this;
        }

        public Criteria andCityNameNotEqualTo(String value) {
            addCriterion("city_name <>", value, "cityName");
            return (Criteria) this;
        }

        public Criteria andCityNameGreaterThan(String value) {
            addCriterion("city_name >", value, "cityName");
            return (Criteria) this;
        }

        public Criteria andCityNameGreaterThanOrEqualTo(String value) {
            addCriterion("city_name >=", value, "cityName");
            return (Criteria) this;
        }

        public Criteria andCityNameLessThan(String value) {
            addCriterion("city_name <", value, "cityName");
            return (Criteria) this;
        }

        public Criteria andCityNameLessThanOrEqualTo(String value) {
            addCriterion("city_name <=", value, "cityName");
            return (Criteria) this;
        }

        public Criteria andCityNameLike(String value) {
            addCriterion("city_name like", value, "cityName");
            return (Criteria) this;
        }

        public Criteria andCityNameNotLike(String value) {
            addCriterion("city_name not like", value, "cityName");
            return (Criteria) this;
        }

        public Criteria andCityNameIn(List<String> values) {
            addCriterion("city_name in", values, "cityName");
            return (Criteria) this;
        }

        public Criteria andCityNameNotIn(List<String> values) {
            addCriterion("city_name not in", values, "cityName");
            return (Criteria) this;
        }

        public Criteria andCityNameBetween(String value1, String value2) {
            addCriterion("city_name between", value1, value2, "cityName");
            return (Criteria) this;
        }

        public Criteria andCityNameNotBetween(String value1, String value2) {
            addCriterion("city_name not between", value1, value2, "cityName");
            return (Criteria) this;
        }

        public Criteria andMunichIdIsNull() {
            addCriterion("munich_id is null");
            return (Criteria) this;
        }

        public Criteria andMunichIdIsNotNull() {
            addCriterion("munich_id is not null");
            return (Criteria) this;
        }

        public Criteria andMunichIdEqualTo(String value) {
            addCriterion("munich_id =", value, "munichId");
            return (Criteria) this;
        }

        public Criteria andMunichIdNotEqualTo(String value) {
            addCriterion("munich_id <>", value, "munichId");
            return (Criteria) this;
        }

        public Criteria andMunichIdGreaterThan(String value) {
            addCriterion("munich_id >", value, "munichId");
            return (Criteria) this;
        }

        public Criteria andMunichIdGreaterThanOrEqualTo(String value) {
            addCriterion("munich_id >=", value, "munichId");
            return (Criteria) this;
        }

        public Criteria andMunichIdLessThan(String value) {
            addCriterion("munich_id <", value, "munichId");
            return (Criteria) this;
        }

        public Criteria andMunichIdLessThanOrEqualTo(String value) {
            addCriterion("munich_id <=", value, "munichId");
            return (Criteria) this;
        }

        public Criteria andMunichIdLike(String value) {
            addCriterion("munich_id like", value, "munichId");
            return (Criteria) this;
        }

        public Criteria andMunichIdNotLike(String value) {
            addCriterion("munich_id not like", value, "munichId");
            return (Criteria) this;
        }

        public Criteria andMunichIdIn(List<String> values) {
            addCriterion("munich_id in", values, "munichId");
            return (Criteria) this;
        }

        public Criteria andMunichIdNotIn(List<String> values) {
            addCriterion("munich_id not in", values, "munichId");
            return (Criteria) this;
        }

        public Criteria andMunichIdBetween(String value1, String value2) {
            addCriterion("munich_id between", value1, value2, "munichId");
            return (Criteria) this;
        }

        public Criteria andMunichIdNotBetween(String value1, String value2) {
            addCriterion("munich_id not between", value1, value2, "munichId");
            return (Criteria) this;
        }

        public Criteria andMunichDescIsNull() {
            addCriterion("munich_desc is null");
            return (Criteria) this;
        }

        public Criteria andMunichDescIsNotNull() {
            addCriterion("munich_desc is not null");
            return (Criteria) this;
        }

        public Criteria andMunichDescEqualTo(String value) {
            addCriterion("munich_desc =", value, "munichDesc");
            return (Criteria) this;
        }

        public Criteria andMunichDescNotEqualTo(String value) {
            addCriterion("munich_desc <>", value, "munichDesc");
            return (Criteria) this;
        }

        public Criteria andMunichDescGreaterThan(String value) {
            addCriterion("munich_desc >", value, "munichDesc");
            return (Criteria) this;
        }

        public Criteria andMunichDescGreaterThanOrEqualTo(String value) {
            addCriterion("munich_desc >=", value, "munichDesc");
            return (Criteria) this;
        }

        public Criteria andMunichDescLessThan(String value) {
            addCriterion("munich_desc <", value, "munichDesc");
            return (Criteria) this;
        }

        public Criteria andMunichDescLessThanOrEqualTo(String value) {
            addCriterion("munich_desc <=", value, "munichDesc");
            return (Criteria) this;
        }

        public Criteria andMunichDescLike(String value) {
            addCriterion("munich_desc like", value, "munichDesc");
            return (Criteria) this;
        }

        public Criteria andMunichDescNotLike(String value) {
            addCriterion("munich_desc not like", value, "munichDesc");
            return (Criteria) this;
        }

        public Criteria andMunichDescIn(List<String> values) {
            addCriterion("munich_desc in", values, "munichDesc");
            return (Criteria) this;
        }

        public Criteria andMunichDescNotIn(List<String> values) {
            addCriterion("munich_desc not in", values, "munichDesc");
            return (Criteria) this;
        }

        public Criteria andMunichDescBetween(String value1, String value2) {
            addCriterion("munich_desc between", value1, value2, "munichDesc");
            return (Criteria) this;
        }

        public Criteria andMunichDescNotBetween(String value1, String value2) {
            addCriterion("munich_desc not between", value1, value2, "munichDesc");
            return (Criteria) this;
        }
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table fri_munich_area
     *
     * @mbg.generated do_not_delete_during_merge Thu Oct 28 11:19:29 CST 2021
     */
    public static class Criteria extends GeneratedCriteria {
        protected Criteria() {
            super();
        }
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table fri_munich_area
     *
     * @mbg.generated Thu Oct 28 11:19:29 CST 2021
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