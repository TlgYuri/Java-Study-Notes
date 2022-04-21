package cn.yurihentai.springcloudalibaba.seata.web;

import cn.yurihentai.springcloudalibaba.seata.bean.common.CommonResult;
import cn.yurihentai.springcloudalibaba.seata.bean.dto.OrderDTO;
import cn.yurihentai.springcloudalibaba.seata.bean.entity.Order;
import cn.yurihentai.springcloudalibaba.seata.service.OrderService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@AllArgsConstructor
@RestController
@RequestMapping("/order/")
public class OrderController {

    private OrderService orderService;

    @PostMapping("order")
    public CommonResult<String> createOrder(@RequestBody OrderDTO dto) {
        Order order = orderService.createOrder(dto);
        return new CommonResult(HttpStatus.OK.value(), "订单创建成功", order.getId());
    }

}