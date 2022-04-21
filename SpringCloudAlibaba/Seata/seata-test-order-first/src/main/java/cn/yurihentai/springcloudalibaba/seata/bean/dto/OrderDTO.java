package cn.yurihentai.springcloudalibaba.seata.bean.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class OrderDTO {

    private Long userId;

    private Long productId;

    private Integer count;

    private BigDecimal money;

    private Boolean error;

}