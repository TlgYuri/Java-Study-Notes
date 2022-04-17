package cn.yurihentai.实体类;

import cn.yurihentai.实体类.基类.实体基类;
import lombok.Data;

@Data
public class 用户 extends 实体基类 {

    private String 用户名;

    private String 账号;

    private String 密码;

    private 用户信息 用户信息;

}
