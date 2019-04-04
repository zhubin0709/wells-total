package com.wells.pojo;

import java.util.ArrayList;
import java.util.List;

public class SysPermissionCriteria {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public SysPermissionCriteria() {
        oredCriteria = new ArrayList<Criteria>();
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
            criteria = new ArrayList<Criterion>();
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

        public Criteria andPrivilegeIdIsNull() {
            addCriterion("privilege_id is null");
            return (Criteria) this;
        }

        public Criteria andPrivilegeIdIsNotNull() {
            addCriterion("privilege_id is not null");
            return (Criteria) this;
        }

        public Criteria andPrivilegeIdEqualTo(Integer value) {
            addCriterion("privilege_id =", value, "privilegeId");
            return (Criteria) this;
        }

        public Criteria andPrivilegeIdNotEqualTo(Integer value) {
            addCriterion("privilege_id <>", value, "privilegeId");
            return (Criteria) this;
        }

        public Criteria andPrivilegeIdGreaterThan(Integer value) {
            addCriterion("privilege_id >", value, "privilegeId");
            return (Criteria) this;
        }

        public Criteria andPrivilegeIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("privilege_id >=", value, "privilegeId");
            return (Criteria) this;
        }

        public Criteria andPrivilegeIdLessThan(Integer value) {
            addCriterion("privilege_id <", value, "privilegeId");
            return (Criteria) this;
        }

        public Criteria andPrivilegeIdLessThanOrEqualTo(Integer value) {
            addCriterion("privilege_id <=", value, "privilegeId");
            return (Criteria) this;
        }

        public Criteria andPrivilegeIdIn(List<Integer> values) {
            addCriterion("privilege_id in", values, "privilegeId");
            return (Criteria) this;
        }

        public Criteria andPrivilegeIdNotIn(List<Integer> values) {
            addCriterion("privilege_id not in", values, "privilegeId");
            return (Criteria) this;
        }

        public Criteria andPrivilegeIdBetween(Integer value1, Integer value2) {
            addCriterion("privilege_id between", value1, value2, "privilegeId");
            return (Criteria) this;
        }

        public Criteria andPrivilegeIdNotBetween(Integer value1, Integer value2) {
            addCriterion("privilege_id not between", value1, value2, "privilegeId");
            return (Criteria) this;
        }

        public Criteria andSubjectIsNull() {
            addCriterion("subject is null");
            return (Criteria) this;
        }

        public Criteria andSubjectIsNotNull() {
            addCriterion("subject is not null");
            return (Criteria) this;
        }

        public Criteria andSubjectEqualTo(String value) {
            addCriterion("subject =", value, "subject");
            return (Criteria) this;
        }

        public Criteria andSubjectNotEqualTo(String value) {
            addCriterion("subject <>", value, "subject");
            return (Criteria) this;
        }

        public Criteria andSubjectGreaterThan(String value) {
            addCriterion("subject >", value, "subject");
            return (Criteria) this;
        }

        public Criteria andSubjectGreaterThanOrEqualTo(String value) {
            addCriterion("subject >=", value, "subject");
            return (Criteria) this;
        }

        public Criteria andSubjectLessThan(String value) {
            addCriterion("subject <", value, "subject");
            return (Criteria) this;
        }

        public Criteria andSubjectLessThanOrEqualTo(String value) {
            addCriterion("subject <=", value, "subject");
            return (Criteria) this;
        }

        public Criteria andSubjectLike(String value) {
            addCriterion("subject like", value, "subject");
            return (Criteria) this;
        }

        public Criteria andSubjectNotLike(String value) {
            addCriterion("subject not like", value, "subject");
            return (Criteria) this;
        }

        public Criteria andSubjectIn(List<String> values) {
            addCriterion("subject in", values, "subject");
            return (Criteria) this;
        }

        public Criteria andSubjectNotIn(List<String> values) {
            addCriterion("subject not in", values, "subject");
            return (Criteria) this;
        }

        public Criteria andSubjectBetween(String value1, String value2) {
            addCriterion("subject between", value1, value2, "subject");
            return (Criteria) this;
        }

        public Criteria andSubjectNotBetween(String value1, String value2) {
            addCriterion("subject not between", value1, value2, "subject");
            return (Criteria) this;
        }

        public Criteria andSubjectValueIsNull() {
            addCriterion("subject_value is null");
            return (Criteria) this;
        }

        public Criteria andSubjectValueIsNotNull() {
            addCriterion("subject_value is not null");
            return (Criteria) this;
        }

        public Criteria andSubjectValueEqualTo(Integer value) {
            addCriterion("subject_value =", value, "subjectValue");
            return (Criteria) this;
        }

        public Criteria andSubjectValueNotEqualTo(Integer value) {
            addCriterion("subject_value <>", value, "subjectValue");
            return (Criteria) this;
        }

        public Criteria andSubjectValueGreaterThan(Integer value) {
            addCriterion("subject_value >", value, "subjectValue");
            return (Criteria) this;
        }

        public Criteria andSubjectValueGreaterThanOrEqualTo(Integer value) {
            addCriterion("subject_value >=", value, "subjectValue");
            return (Criteria) this;
        }

        public Criteria andSubjectValueLessThan(Integer value) {
            addCriterion("subject_value <", value, "subjectValue");
            return (Criteria) this;
        }

        public Criteria andSubjectValueLessThanOrEqualTo(Integer value) {
            addCriterion("subject_value <=", value, "subjectValue");
            return (Criteria) this;
        }

        public Criteria andSubjectValueIn(List<Integer> values) {
            addCriterion("subject_value in", values, "subjectValue");
            return (Criteria) this;
        }

        public Criteria andSubjectValueNotIn(List<Integer> values) {
            addCriterion("subject_value not in", values, "subjectValue");
            return (Criteria) this;
        }

        public Criteria andSubjectValueBetween(Integer value1, Integer value2) {
            addCriterion("subject_value between", value1, value2, "subjectValue");
            return (Criteria) this;
        }

        public Criteria andSubjectValueNotBetween(Integer value1, Integer value2) {
            addCriterion("subject_value not between", value1, value2, "subjectValue");
            return (Criteria) this;
        }

        public Criteria andAccessIsNull() {
            addCriterion("access is null");
            return (Criteria) this;
        }

        public Criteria andAccessIsNotNull() {
            addCriterion("access is not null");
            return (Criteria) this;
        }

        public Criteria andAccessEqualTo(String value) {
            addCriterion("access =", value, "access");
            return (Criteria) this;
        }

        public Criteria andAccessNotEqualTo(String value) {
            addCriterion("access <>", value, "access");
            return (Criteria) this;
        }

        public Criteria andAccessGreaterThan(String value) {
            addCriterion("access >", value, "access");
            return (Criteria) this;
        }

        public Criteria andAccessGreaterThanOrEqualTo(String value) {
            addCriterion("access >=", value, "access");
            return (Criteria) this;
        }

        public Criteria andAccessLessThan(String value) {
            addCriterion("access <", value, "access");
            return (Criteria) this;
        }

        public Criteria andAccessLessThanOrEqualTo(String value) {
            addCriterion("access <=", value, "access");
            return (Criteria) this;
        }

        public Criteria andAccessLike(String value) {
            addCriterion("access like", value, "access");
            return (Criteria) this;
        }

        public Criteria andAccessNotLike(String value) {
            addCriterion("access not like", value, "access");
            return (Criteria) this;
        }

        public Criteria andAccessIn(List<String> values) {
            addCriterion("access in", values, "access");
            return (Criteria) this;
        }

        public Criteria andAccessNotIn(List<String> values) {
            addCriterion("access not in", values, "access");
            return (Criteria) this;
        }

        public Criteria andAccessBetween(String value1, String value2) {
            addCriterion("access between", value1, value2, "access");
            return (Criteria) this;
        }

        public Criteria andAccessNotBetween(String value1, String value2) {
            addCriterion("access not between", value1, value2, "access");
            return (Criteria) this;
        }

        public Criteria andAccessValueIsNull() {
            addCriterion("access_value is null");
            return (Criteria) this;
        }

        public Criteria andAccessValueIsNotNull() {
            addCriterion("access_value is not null");
            return (Criteria) this;
        }

        public Criteria andAccessValueEqualTo(Integer value) {
            addCriterion("access_value =", value, "accessValue");
            return (Criteria) this;
        }

        public Criteria andAccessValueNotEqualTo(Integer value) {
            addCriterion("access_value <>", value, "accessValue");
            return (Criteria) this;
        }

        public Criteria andAccessValueGreaterThan(Integer value) {
            addCriterion("access_value >", value, "accessValue");
            return (Criteria) this;
        }

        public Criteria andAccessValueGreaterThanOrEqualTo(Integer value) {
            addCriterion("access_value >=", value, "accessValue");
            return (Criteria) this;
        }

        public Criteria andAccessValueLessThan(Integer value) {
            addCriterion("access_value <", value, "accessValue");
            return (Criteria) this;
        }

        public Criteria andAccessValueLessThanOrEqualTo(Integer value) {
            addCriterion("access_value <=", value, "accessValue");
            return (Criteria) this;
        }

        public Criteria andAccessValueIn(List<Integer> values) {
            addCriterion("access_value in", values, "accessValue");
            return (Criteria) this;
        }

        public Criteria andAccessValueNotIn(List<Integer> values) {
            addCriterion("access_value not in", values, "accessValue");
            return (Criteria) this;
        }

        public Criteria andAccessValueBetween(Integer value1, Integer value2) {
            addCriterion("access_value between", value1, value2, "accessValue");
            return (Criteria) this;
        }

        public Criteria andAccessValueNotBetween(Integer value1, Integer value2) {
            addCriterion("access_value not between", value1, value2, "accessValue");
            return (Criteria) this;
        }

        public Criteria andOperationIsNull() {
            addCriterion("operation is null");
            return (Criteria) this;
        }

        public Criteria andOperationIsNotNull() {
            addCriterion("operation is not null");
            return (Criteria) this;
        }

        public Criteria andOperationEqualTo(Integer value) {
            addCriterion("operation =", value, "operation");
            return (Criteria) this;
        }

        public Criteria andOperationNotEqualTo(Integer value) {
            addCriterion("operation <>", value, "operation");
            return (Criteria) this;
        }

        public Criteria andOperationGreaterThan(Integer value) {
            addCriterion("operation >", value, "operation");
            return (Criteria) this;
        }

        public Criteria andOperationGreaterThanOrEqualTo(Integer value) {
            addCriterion("operation >=", value, "operation");
            return (Criteria) this;
        }

        public Criteria andOperationLessThan(Integer value) {
            addCriterion("operation <", value, "operation");
            return (Criteria) this;
        }

        public Criteria andOperationLessThanOrEqualTo(Integer value) {
            addCriterion("operation <=", value, "operation");
            return (Criteria) this;
        }

        public Criteria andOperationIn(List<Integer> values) {
            addCriterion("operation in", values, "operation");
            return (Criteria) this;
        }

        public Criteria andOperationNotIn(List<Integer> values) {
            addCriterion("operation not in", values, "operation");
            return (Criteria) this;
        }

        public Criteria andOperationBetween(Integer value1, Integer value2) {
            addCriterion("operation between", value1, value2, "operation");
            return (Criteria) this;
        }

        public Criteria andOperationNotBetween(Integer value1, Integer value2) {
            addCriterion("operation not between", value1, value2, "operation");
            return (Criteria) this;
        }

        public Criteria andSubjectLikeInsensitive(String value) {
            addCriterion("upper(subject) like", value.toUpperCase(), "subject");
            return (Criteria) this;
        }

        public Criteria andAccessLikeInsensitive(String value) {
            addCriterion("upper(access) like", value.toUpperCase(), "access");
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