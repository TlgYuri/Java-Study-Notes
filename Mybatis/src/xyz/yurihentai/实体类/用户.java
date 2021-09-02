package xyz.yurihentai.实体类;

import lombok.Data;

import java.util.Date;

@Data
public class 用户 {

    private String 用户编号;

    private String 昵称;

    private String 账号;

    private String 密码;

    private Date 创建时间;

    private Date 更新时间;

    private 用户信息 用户信息;

}
