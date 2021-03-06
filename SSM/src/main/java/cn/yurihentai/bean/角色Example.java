package cn.yurihentai.bean;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class 角色Example {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table 角色表
     *
     * @mbg.generated
     */
    protected String orderByClause;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table 角色表
     *
     * @mbg.generated
     */
    protected boolean distinct;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table 角色表
     *
     * @mbg.generated
     */
    protected List<Criteria> oredCriteria;

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table 角色表
     *
     * @mbg.generated
     */
    public 角色Example() {
        oredCriteria = new ArrayList<Criteria>();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table 角色表
     *
     * @mbg.generated
     */
    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table 角色表
     *
     * @mbg.generated
     */
    public String getOrderByClause() {
        return orderByClause;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table 角色表
     *
     * @mbg.generated
     */
    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table 角色表
     *
     * @mbg.generated
     */
    public boolean isDistinct() {
        return distinct;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table 角色表
     *
     * @mbg.generated
     */
    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table 角色表
     *
     * @mbg.generated
     */
    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table 角色表
     *
     * @mbg.generated
     */
    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table 角色表
     *
     * @mbg.generated
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
     * This method corresponds to the database table 角色表
     *
     * @mbg.generated
     */
    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table 角色表
     *
     * @mbg.generated
     */
    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table 角色表
     *
     * @mbg.generated
     */
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

        public Criteria and编号IsNull() {
            addCriterion("编号 is null");
            return (Criteria) this;
        }

        public Criteria and编号IsNotNull() {
            addCriterion("编号 is not null");
            return (Criteria) this;
        }

        public Criteria and编号EqualTo(Long value) {
            addCriterion("编号 =", value, "编号");
            return (Criteria) this;
        }

        public Criteria and编号NotEqualTo(Long value) {
            addCriterion("编号 <>", value, "编号");
            return (Criteria) this;
        }

        public Criteria and编号GreaterThan(Long value) {
            addCriterion("编号 >", value, "编号");
            return (Criteria) this;
        }

        public Criteria and编号GreaterThanOrEqualTo(Long value) {
            addCriterion("编号 >=", value, "编号");
            return (Criteria) this;
        }

        public Criteria and编号LessThan(Long value) {
            addCriterion("编号 <", value, "编号");
            return (Criteria) this;
        }

        public Criteria and编号LessThanOrEqualTo(Long value) {
            addCriterion("编号 <=", value, "编号");
            return (Criteria) this;
        }

        public Criteria and编号In(List<Long> values) {
            addCriterion("编号 in", values, "编号");
            return (Criteria) this;
        }

        public Criteria and编号NotIn(List<Long> values) {
            addCriterion("编号 not in", values, "编号");
            return (Criteria) this;
        }

        public Criteria and编号Between(Long value1, Long value2) {
            addCriterion("编号 between", value1, value2, "编号");
            return (Criteria) this;
        }

        public Criteria and编号NotBetween(Long value1, Long value2) {
            addCriterion("编号 not between", value1, value2, "编号");
            return (Criteria) this;
        }

        public Criteria and名称IsNull() {
            addCriterion("名称 is null");
            return (Criteria) this;
        }

        public Criteria and名称IsNotNull() {
            addCriterion("名称 is not null");
            return (Criteria) this;
        }

        public Criteria and名称EqualTo(String value) {
            addCriterion("名称 =", value, "名称");
            return (Criteria) this;
        }

        public Criteria and名称NotEqualTo(String value) {
            addCriterion("名称 <>", value, "名称");
            return (Criteria) this;
        }

        public Criteria and名称GreaterThan(String value) {
            addCriterion("名称 >", value, "名称");
            return (Criteria) this;
        }

        public Criteria and名称GreaterThanOrEqualTo(String value) {
            addCriterion("名称 >=", value, "名称");
            return (Criteria) this;
        }

        public Criteria and名称LessThan(String value) {
            addCriterion("名称 <", value, "名称");
            return (Criteria) this;
        }

        public Criteria and名称LessThanOrEqualTo(String value) {
            addCriterion("名称 <=", value, "名称");
            return (Criteria) this;
        }

        public Criteria and名称Like(String value) {
            addCriterion("名称 like", value, "名称");
            return (Criteria) this;
        }

        public Criteria and名称NotLike(String value) {
            addCriterion("名称 not like", value, "名称");
            return (Criteria) this;
        }

        public Criteria and名称In(List<String> values) {
            addCriterion("名称 in", values, "名称");
            return (Criteria) this;
        }

        public Criteria and名称NotIn(List<String> values) {
            addCriterion("名称 not in", values, "名称");
            return (Criteria) this;
        }

        public Criteria and名称Between(String value1, String value2) {
            addCriterion("名称 between", value1, value2, "名称");
            return (Criteria) this;
        }

        public Criteria and名称NotBetween(String value1, String value2) {
            addCriterion("名称 not between", value1, value2, "名称");
            return (Criteria) this;
        }

        public Criteria and编码IsNull() {
            addCriterion("编码 is null");
            return (Criteria) this;
        }

        public Criteria and编码IsNotNull() {
            addCriterion("编码 is not null");
            return (Criteria) this;
        }

        public Criteria and编码EqualTo(String value) {
            addCriterion("编码 =", value, "编码");
            return (Criteria) this;
        }

        public Criteria and编码NotEqualTo(String value) {
            addCriterion("编码 <>", value, "编码");
            return (Criteria) this;
        }

        public Criteria and编码GreaterThan(String value) {
            addCriterion("编码 >", value, "编码");
            return (Criteria) this;
        }

        public Criteria and编码GreaterThanOrEqualTo(String value) {
            addCriterion("编码 >=", value, "编码");
            return (Criteria) this;
        }

        public Criteria and编码LessThan(String value) {
            addCriterion("编码 <", value, "编码");
            return (Criteria) this;
        }

        public Criteria and编码LessThanOrEqualTo(String value) {
            addCriterion("编码 <=", value, "编码");
            return (Criteria) this;
        }

        public Criteria and编码Like(String value) {
            addCriterion("编码 like", value, "编码");
            return (Criteria) this;
        }

        public Criteria and编码NotLike(String value) {
            addCriterion("编码 not like", value, "编码");
            return (Criteria) this;
        }

        public Criteria and编码In(List<String> values) {
            addCriterion("编码 in", values, "编码");
            return (Criteria) this;
        }

        public Criteria and编码NotIn(List<String> values) {
            addCriterion("编码 not in", values, "编码");
            return (Criteria) this;
        }

        public Criteria and编码Between(String value1, String value2) {
            addCriterion("编码 between", value1, value2, "编码");
            return (Criteria) this;
        }

        public Criteria and编码NotBetween(String value1, String value2) {
            addCriterion("编码 not between", value1, value2, "编码");
            return (Criteria) this;
        }

        public Criteria and创建时间IsNull() {
            addCriterion("创建时间 is null");
            return (Criteria) this;
        }

        public Criteria and创建时间IsNotNull() {
            addCriterion("创建时间 is not null");
            return (Criteria) this;
        }

        public Criteria and创建时间EqualTo(Date value) {
            addCriterion("创建时间 =", value, "创建时间");
            return (Criteria) this;
        }

        public Criteria and创建时间NotEqualTo(Date value) {
            addCriterion("创建时间 <>", value, "创建时间");
            return (Criteria) this;
        }

        public Criteria and创建时间GreaterThan(Date value) {
            addCriterion("创建时间 >", value, "创建时间");
            return (Criteria) this;
        }

        public Criteria and创建时间GreaterThanOrEqualTo(Date value) {
            addCriterion("创建时间 >=", value, "创建时间");
            return (Criteria) this;
        }

        public Criteria and创建时间LessThan(Date value) {
            addCriterion("创建时间 <", value, "创建时间");
            return (Criteria) this;
        }

        public Criteria and创建时间LessThanOrEqualTo(Date value) {
            addCriterion("创建时间 <=", value, "创建时间");
            return (Criteria) this;
        }

        public Criteria and创建时间In(List<Date> values) {
            addCriterion("创建时间 in", values, "创建时间");
            return (Criteria) this;
        }

        public Criteria and创建时间NotIn(List<Date> values) {
            addCriterion("创建时间 not in", values, "创建时间");
            return (Criteria) this;
        }

        public Criteria and创建时间Between(Date value1, Date value2) {
            addCriterion("创建时间 between", value1, value2, "创建时间");
            return (Criteria) this;
        }

        public Criteria and创建时间NotBetween(Date value1, Date value2) {
            addCriterion("创建时间 not between", value1, value2, "创建时间");
            return (Criteria) this;
        }

        public Criteria and修改时间IsNull() {
            addCriterion("修改时间 is null");
            return (Criteria) this;
        }

        public Criteria and修改时间IsNotNull() {
            addCriterion("修改时间 is not null");
            return (Criteria) this;
        }

        public Criteria and修改时间EqualTo(Date value) {
            addCriterion("修改时间 =", value, "修改时间");
            return (Criteria) this;
        }

        public Criteria and修改时间NotEqualTo(Date value) {
            addCriterion("修改时间 <>", value, "修改时间");
            return (Criteria) this;
        }

        public Criteria and修改时间GreaterThan(Date value) {
            addCriterion("修改时间 >", value, "修改时间");
            return (Criteria) this;
        }

        public Criteria and修改时间GreaterThanOrEqualTo(Date value) {
            addCriterion("修改时间 >=", value, "修改时间");
            return (Criteria) this;
        }

        public Criteria and修改时间LessThan(Date value) {
            addCriterion("修改时间 <", value, "修改时间");
            return (Criteria) this;
        }

        public Criteria and修改时间LessThanOrEqualTo(Date value) {
            addCriterion("修改时间 <=", value, "修改时间");
            return (Criteria) this;
        }

        public Criteria and修改时间In(List<Date> values) {
            addCriterion("修改时间 in", values, "修改时间");
            return (Criteria) this;
        }

        public Criteria and修改时间NotIn(List<Date> values) {
            addCriterion("修改时间 not in", values, "修改时间");
            return (Criteria) this;
        }

        public Criteria and修改时间Between(Date value1, Date value2) {
            addCriterion("修改时间 between", value1, value2, "修改时间");
            return (Criteria) this;
        }

        public Criteria and修改时间NotBetween(Date value1, Date value2) {
            addCriterion("修改时间 not between", value1, value2, "修改时间");
            return (Criteria) this;
        }

        public Criteria and是否删除IsNull() {
            addCriterion("是否删除 is null");
            return (Criteria) this;
        }

        public Criteria and是否删除IsNotNull() {
            addCriterion("是否删除 is not null");
            return (Criteria) this;
        }

        public Criteria and是否删除EqualTo(Boolean value) {
            addCriterion("是否删除 =", value, "是否删除");
            return (Criteria) this;
        }

        public Criteria and是否删除NotEqualTo(Boolean value) {
            addCriterion("是否删除 <>", value, "是否删除");
            return (Criteria) this;
        }

        public Criteria and是否删除GreaterThan(Boolean value) {
            addCriterion("是否删除 >", value, "是否删除");
            return (Criteria) this;
        }

        public Criteria and是否删除GreaterThanOrEqualTo(Boolean value) {
            addCriterion("是否删除 >=", value, "是否删除");
            return (Criteria) this;
        }

        public Criteria and是否删除LessThan(Boolean value) {
            addCriterion("是否删除 <", value, "是否删除");
            return (Criteria) this;
        }

        public Criteria and是否删除LessThanOrEqualTo(Boolean value) {
            addCriterion("是否删除 <=", value, "是否删除");
            return (Criteria) this;
        }

        public Criteria and是否删除In(List<Boolean> values) {
            addCriterion("是否删除 in", values, "是否删除");
            return (Criteria) this;
        }

        public Criteria and是否删除NotIn(List<Boolean> values) {
            addCriterion("是否删除 not in", values, "是否删除");
            return (Criteria) this;
        }

        public Criteria and是否删除Between(Boolean value1, Boolean value2) {
            addCriterion("是否删除 between", value1, value2, "是否删除");
            return (Criteria) this;
        }

        public Criteria and是否删除NotBetween(Boolean value1, Boolean value2) {
            addCriterion("是否删除 not between", value1, value2, "是否删除");
            return (Criteria) this;
        }
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table 角色表
     *
     * @mbg.generated do_not_delete_during_merge
     */
    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table 角色表
     *
     * @mbg.generated
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