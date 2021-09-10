package xyz.yurihentai.springboot.mapper;

import org.apache.ibatis.annotations.Param;
import xyz.yurihentai.springboot.bean.角色;
import xyz.yurihentai.springboot.bean.角色Example;

import java.util.List;

public interface 角色Mapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table 角色表
     *
     * @mbg.generated
     */
    long countByExample(角色Example example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table 角色表
     *
     * @mbg.generated
     */
    int deleteByExample(角色Example example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table 角色表
     *
     * @mbg.generated
     */
    int deleteByPrimaryKey(Long 编号);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table 角色表
     *
     * @mbg.generated
     */
    int insert(角色 record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table 角色表
     *
     * @mbg.generated
     */
    int insertSelective(角色 record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table 角色表
     *
     * @mbg.generated
     */
    List<角色> selectByExample(角色Example example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table 角色表
     *
     * @mbg.generated
     */
    角色 selectByPrimaryKey(Long 编号);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table 角色表
     *
     * @mbg.generated
     */
    int updateByExampleSelective(@Param("record") 角色 record, @Param("example") 角色Example example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table 角色表
     *
     * @mbg.generated
     */
    int updateByExample(@Param("record") 角色 record, @Param("example") 角色Example example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table 角色表
     *
     * @mbg.generated
     */
    int updateByPrimaryKeySelective(角色 record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table 角色表
     *
     * @mbg.generated
     */
    int updateByPrimaryKey(角色 record);

    List<角色> 通过用户编号查找用户角色(Long 用户编号);

}