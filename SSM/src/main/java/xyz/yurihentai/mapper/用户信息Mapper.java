package xyz.yurihentai.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import xyz.yurihentai.bean.用户信息;
import xyz.yurihentai.bean.用户信息Example;

public interface 用户信息Mapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table 用户信息表
     *
     * @mbg.generated
     */
    long countByExample(用户信息Example example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table 用户信息表
     *
     * @mbg.generated
     */
    int deleteByExample(用户信息Example example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table 用户信息表
     *
     * @mbg.generated
     */
    int deleteByPrimaryKey(Long 主键);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table 用户信息表
     *
     * @mbg.generated
     */
    int insert(用户信息 record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table 用户信息表
     *
     * @mbg.generated
     */
    int insertSelective(用户信息 record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table 用户信息表
     *
     * @mbg.generated
     */
    List<用户信息> selectByExample(用户信息Example example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table 用户信息表
     *
     * @mbg.generated
     */
    用户信息 selectByPrimaryKey(Long 主键);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table 用户信息表
     *
     * @mbg.generated
     */
    int updateByExampleSelective(@Param("record") 用户信息 record, @Param("example") 用户信息Example example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table 用户信息表
     *
     * @mbg.generated
     */
    int updateByExample(@Param("record") 用户信息 record, @Param("example") 用户信息Example example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table 用户信息表
     *
     * @mbg.generated
     */
    int updateByPrimaryKeySelective(用户信息 record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table 用户信息表
     *
     * @mbg.generated
     */
    int updateByPrimaryKey(用户信息 record);
}