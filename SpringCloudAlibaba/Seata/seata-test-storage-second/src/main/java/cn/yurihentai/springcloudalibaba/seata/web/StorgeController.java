package cn.yurihentai.springcloudalibaba.seata.web;

import cn.yurihentai.springcloudalibaba.seata.bean.common.CommonResult;
import cn.yurihentai.springcloudalibaba.seata.service.StorageService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@AllArgsConstructor
@RestController
@RequestMapping("/storage/")
public class StorgeController {

    private StorageService storageService;

    @PostMapping("decrease")
    public CommonResult decrease(@RequestParam Long productId, @RequestParam Integer count) {
        long decrease = storageService.decrease(productId, count);
        if (decrease > 0) {
            return new CommonResult(200, "操作成功", decrease);
        } else {
            return new CommonResult(500, "操作失败");
        }
    }

}