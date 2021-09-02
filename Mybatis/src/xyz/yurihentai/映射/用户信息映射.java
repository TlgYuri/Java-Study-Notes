package xyz.yurihentai.映射;

import xyz.yurihentai.实体类.用户信息;
import org.apache.ibatis.annotations.MapKey;
import org.apache.ibatis.annotations.Param;

import java.util.Map;

public interface 用户信息映射 {

    public 用户信息 查找(String 主键);

    public Integer 新增(用户信息 用户信息);

    public Integer 更新(用户信息 用户信息);

    // 单个基本类型、包装类和字符串 的参数不需要处理 多个会被封装为Map，key为“0”，“1”...或者“param1”，“param2”...
    // 使用注解”@Param“可以指定参数被封装进map时的key
    // 也可以将多个参数封装为map后直接传递map
    // 数组封装后的默认key是“array”
    // Collection集合对应的key是“collection”，如果是List集合也可以使用”list“作为key获取参数
    public Integer 删除(@Param("主键") String 主键);

    public Integer 真删(@Param("主键") String 主键);

    @MapKey("主键")  //查询结果返回为map集合，设置对象的”主键“属性为map的key
    public Map<String, 用户信息> 获取所有用户();

}
