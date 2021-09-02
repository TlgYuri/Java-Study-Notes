package xyz.yurihentai.映射;

import xyz.yurihentai.实体类.用户;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface 用户映射 {

    public 用户 查找(String 用户编号);

    public 用户 where示例(String 用户编号);

    public 用户 trim示例(String 用户编号);

    public 用户 choose示例(用户 用户);

    public Integer set示例(用户 用户);

    public List<用户> foreach示例(@Param("用户编号集") List<String> 用户编号集);

}
