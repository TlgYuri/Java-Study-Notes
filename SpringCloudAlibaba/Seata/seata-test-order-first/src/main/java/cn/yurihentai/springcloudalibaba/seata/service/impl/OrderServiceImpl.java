package cn.yurihentai.springcloudalibaba.seata.service.impl;

import cn.yurihentai.springcloudalibaba.seata.bean.common.CommonResult;
import cn.yurihentai.springcloudalibaba.seata.bean.dto.OrderDTO;
import cn.yurihentai.springcloudalibaba.seata.bean.entity.Order;
import cn.yurihentai.springcloudalibaba.seata.mapper.OrderMapper;
import cn.yurihentai.springcloudalibaba.seata.service.AccountService;
import cn.yurihentai.springcloudalibaba.seata.service.OrderService;
import cn.yurihentai.springcloudalibaba.seata.service.StorageService;
import io.seata.spring.annotation.GlobalTransactional;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class OrderServiceImpl implements OrderService {

    private static final Logger LOGGER = LoggerFactory.getLogger(OrderServiceImpl.class);

    private OrderMapper orderMapper;
    private StorageService storageService;
    private AccountService accountService;

    /**
     * *@GlobalTransactional:
     * seata的分布式事务管理  方法中调用的任何服务节点一旦抛出异常  会将所有节点执行的数据库操作执行回滚
     */
    @GlobalTransactional
    @Override
    public Order createOrder(OrderDTO dto) {
        Order order = new Order();
        BeanUtils.copyProperties(dto, order);
        LOGGER.info("------->下单开始");
        orderMapper.add(order);
        // 本应用创建订单失败
        if(order.getId() == null) {
            throw new RuntimeException("订单创建失败！");
        }

        //远程调用库存服务扣减库存
        LOGGER.info("------->order-service中扣减库存开始");
        CommonResult storageResult = storageService.decrease(order.getProductId(), order.getCount());
        if(storageResult.getCode() != 200) {
            throw new RuntimeException("库存扣减失败！");
        }
        LOGGER.info("------->order-service中扣减库存结束");

        //远程调用账户服务扣减余额
        LOGGER.info("------->order-service中扣减余额开始");
        CommonResult accountResult = accountService.decrease(order.getUserId(), order.getMoney(), dto.getError());
        if(accountResult.getCode() != 200) {
            throw new RuntimeException("库存扣减失败！");
        }
        LOGGER.info("------->order-service中扣减余额结束");

        //修改订单状态为已完成
        LOGGER.info("------->order-service中修改订单状态开始");
        long complete = orderMapper.complete(order.getUserId());
        if (complete <= 0) {
            throw new RuntimeException("更新订单状态失败！");
        }
        LOGGER.info("------->order-service中修改订单状态结束");

        LOGGER.info("------->下单结束");
        return order;
    }

}