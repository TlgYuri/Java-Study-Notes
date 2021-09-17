package xyz.yurihentai.bean.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@ApiModel
@Data
public class RequestDTO {

    @ApiModelProperty("时间")
    private Object date;

    @ApiModelProperty("值")
    private String value;

    @ApiModelProperty("键")
    private String key;

    @ApiModelProperty("是否覆盖")
    private boolean recover;

}
