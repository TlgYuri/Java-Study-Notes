package cn.yurihentai.springcloudalibaba.seata.service;

import cn.yurihentai.springcloudalibaba.seata.bean.dto.OrderDTO;
import cn.yurihentai.springcloudalibaba.seata.bean.entity.Order;

public interface OrderService {

    /** 创建订单 */
    Order createOrder(OrderDTO dto);

}