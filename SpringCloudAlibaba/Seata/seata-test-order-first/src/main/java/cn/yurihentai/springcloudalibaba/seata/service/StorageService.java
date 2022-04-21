package cn.yurihentai.springcloudalibaba.seata.service;

import cn.yurihentai.springcloudalibaba.seata.bean.common.CommonResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value = "seata-service-storage")
public interface StorageService {

    /** 扣库存 */
    @PostMapping(value = "/storage/decrease")
    CommonResult decrease(@RequestParam("productId") Long productId, @RequestParam("count") Integer count);
}