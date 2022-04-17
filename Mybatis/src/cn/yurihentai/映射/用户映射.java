package cn.yurihentai.映射;

import cn.yurihentai.实体类.用户;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface 用户映射 {

    public 用户 查找(String 编号);

    public 用户 where示例(String 编号);

    public 用户 trim示例(String 编号);

    public 用户 choose示例(用户 用户);

    public Integer set示例(用户 用户);

    public List<用户> foreach示例(@Param("编号集") List<String> 编号集);

}
