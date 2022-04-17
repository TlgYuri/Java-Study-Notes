package cn.yurihentai.实体类;

import cn.yurihentai.实体类.基类.实体基类;
import lombok.Data;
import cn.yurihentai.枚举.性别枚举;

import java.util.Date;

@Data
public class 用户信息 extends 实体基类 {

    private String 姓名;

    // 通过性别枚举处理器(TypeHandler)进行了映射
    private 性别枚举 性别;

    private String 年龄;

    private Date 生日;

    private String 身份证号;

}
