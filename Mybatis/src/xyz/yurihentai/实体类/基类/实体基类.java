package xyz.yurihentai.实体类.基类;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class 实体基类 implements Serializable {

    private String 主键;

    private Date 创建时间;

    private Date 修改时间;

}
