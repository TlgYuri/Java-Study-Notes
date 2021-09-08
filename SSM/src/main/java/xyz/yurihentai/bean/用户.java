package xyz.yurihentai.bean;

import java.io.Serializable;
import java.util.Date;

public class 用户 implements Serializable {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column 用户表.主键
     *
     * @mbg.generated
     */
    private Long 主键;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column 用户表.昵称
     *
     * @mbg.generated
     */
    private String 昵称;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column 用户表.账号
     *
     * @mbg.generated
     */
    private String 账号;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column 用户表.密码
     *
     * @mbg.generated
     */
    private String 密码;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column 用户表.创建时间
     *
     * @mbg.generated
     */
    private Date 创建时间;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column 用户表.修改时间
     *
     * @mbg.generated
     */
    private Date 修改时间;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column 用户表.是否删除
     *
     * @mbg.generated
     */
    private Boolean 是否删除;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table 用户表
     *
     * @mbg.generated
     */
    private static final long serialVersionUID = 1L;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column 用户表.主键
     *
     * @return the value of 用户表.主键
     *
     * @mbg.generated
     */
    public Long get主键() {
        return 主键;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column 用户表.主键
     *
     * @param 主键 the value for 用户表.主键
     *
     * @mbg.generated
     */
    public void set主键(Long 主键) {
        this.主键 = 主键;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column 用户表.昵称
     *
     * @return the value of 用户表.昵称
     *
     * @mbg.generated
     */
    public String get昵称() {
        return 昵称;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column 用户表.昵称
     *
     * @param 昵称 the value for 用户表.昵称
     *
     * @mbg.generated
     */
    public void set昵称(String 昵称) {
        this.昵称 = 昵称 == null ? null : 昵称.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column 用户表.账号
     *
     * @return the value of 用户表.账号
     *
     * @mbg.generated
     */
    public String get账号() {
        return 账号;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column 用户表.账号
     *
     * @param 账号 the value for 用户表.账号
     *
     * @mbg.generated
     */
    public void set账号(String 账号) {
        this.账号 = 账号 == null ? null : 账号.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column 用户表.密码
     *
     * @return the value of 用户表.密码
     *
     * @mbg.generated
     */
    public String get密码() {
        return 密码;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column 用户表.密码
     *
     * @param 密码 the value for 用户表.密码
     *
     * @mbg.generated
     */
    public void set密码(String 密码) {
        this.密码 = 密码 == null ? null : 密码.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column 用户表.创建时间
     *
     * @return the value of 用户表.创建时间
     *
     * @mbg.generated
     */
    public Date get创建时间() {
        return 创建时间;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column 用户表.创建时间
     *
     * @param 创建时间 the value for 用户表.创建时间
     *
     * @mbg.generated
     */
    public void set创建时间(Date 创建时间) {
        this.创建时间 = 创建时间;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column 用户表.修改时间
     *
     * @return the value of 用户表.修改时间
     *
     * @mbg.generated
     */
    public Date get修改时间() {
        return 修改时间;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column 用户表.修改时间
     *
     * @param 修改时间 the value for 用户表.修改时间
     *
     * @mbg.generated
     */
    public void set修改时间(Date 修改时间) {
        this.修改时间 = 修改时间;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column 用户表.是否删除
     *
     * @return the value of 用户表.是否删除
     *
     * @mbg.generated
     */
    public Boolean get是否删除() {
        return 是否删除;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column 用户表.是否删除
     *
     * @param 是否删除 the value for 用户表.是否删除
     *
     * @mbg.generated
     */
    public void set是否删除(Boolean 是否删除) {
        this.是否删除 = 是否删除;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table 用户表
     *
     * @mbg.generated
     */
    @Override
    public boolean equals(Object that) {
        if (this == that) {
            return true;
        }
        if (that == null) {
            return false;
        }
        if (getClass() != that.getClass()) {
            return false;
        }
        用户 other = (用户) that;
        return (this.get主键() == null ? other.get主键() == null : this.get主键().equals(other.get主键()))
            && (this.get昵称() == null ? other.get昵称() == null : this.get昵称().equals(other.get昵称()))
            && (this.get账号() == null ? other.get账号() == null : this.get账号().equals(other.get账号()))
            && (this.get密码() == null ? other.get密码() == null : this.get密码().equals(other.get密码()))
            && (this.get创建时间() == null ? other.get创建时间() == null : this.get创建时间().equals(other.get创建时间()))
            && (this.get修改时间() == null ? other.get修改时间() == null : this.get修改时间().equals(other.get修改时间()))
            && (this.get是否删除() == null ? other.get是否删除() == null : this.get是否删除().equals(other.get是否删除()));
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table 用户表
     *
     * @mbg.generated
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((get主键() == null) ? 0 : get主键().hashCode());
        result = prime * result + ((get昵称() == null) ? 0 : get昵称().hashCode());
        result = prime * result + ((get账号() == null) ? 0 : get账号().hashCode());
        result = prime * result + ((get密码() == null) ? 0 : get密码().hashCode());
        result = prime * result + ((get创建时间() == null) ? 0 : get创建时间().hashCode());
        result = prime * result + ((get修改时间() == null) ? 0 : get修改时间().hashCode());
        result = prime * result + ((get是否删除() == null) ? 0 : get是否删除().hashCode());
        return result;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table 用户表
     *
     * @mbg.generated
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", 主键=").append(主键);
        sb.append(", 昵称=").append(昵称);
        sb.append(", 账号=").append(账号);
        sb.append(", 密码=").append(密码);
        sb.append(", 创建时间=").append(创建时间);
        sb.append(", 修改时间=").append(修改时间);
        sb.append(", 是否删除=").append(是否删除);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}