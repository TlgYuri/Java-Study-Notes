package cn.yurihentai.springcloudalibaba.seata.mapper;

import cn.yurihentai.springcloudalibaba.seata.bean.entity.Order;
import org.apache.ibatis.annotations.Param;

public interface OrderMapper {

    void add(Order order);

    /** 完成订单 */
    long complete(@Param("userId") Long userId);

}