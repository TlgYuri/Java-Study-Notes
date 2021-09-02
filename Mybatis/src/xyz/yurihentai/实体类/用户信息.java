package xyz.yurihentai.实体类;

import xyz.yurihentai.枚举.性别枚举;
import lombok.Data;

import java.util.Date;

@Data
public class 用户信息 {

    private String 用户编号;

    private String 姓名;

    // 通过性别枚举处理器(TypeHandler)进行了映射
    private 性别枚举 性别;

    private String 年龄;

    private Date 生日;

    private String 身份证号;

    private Date 创建时间;

    private Date 更新时间;

}
