package xyz.yurihentai.实体类;

import lombok.Data;
import xyz.yurihentai.实体类.基类.实体基类;

@Data
public class 用户 extends 实体基类 {

    private String 昵称;

    private String 账号;

    private String 密码;

    private 用户信息 用户信息;

}
