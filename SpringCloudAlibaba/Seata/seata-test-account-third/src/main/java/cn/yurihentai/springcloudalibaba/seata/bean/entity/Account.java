package cn.yurihentai.springcloudalibaba.seata.bean.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class Account {

    private Long id;

    /** 用户id */
    private Long userId;

    /** 总额度 */
    private BigDecimal total;

    /** 已用额度 */
    private BigDecimal used;

    /** 剩余额度 */
    private BigDecimal residue;

}