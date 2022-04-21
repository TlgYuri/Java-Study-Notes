package cn.yurihentai.springcloudalibaba.seata.service;

import cn.yurihentai.springcloudalibaba.seata.bean.common.CommonResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.math.BigDecimal;

@FeignClient(value = "seata-service-account")
public interface AccountService {

    @PostMapping("/account/decrease")
    CommonResult decrease(@RequestParam Long userId, @RequestParam BigDecimal money, @RequestParam boolean error);

}