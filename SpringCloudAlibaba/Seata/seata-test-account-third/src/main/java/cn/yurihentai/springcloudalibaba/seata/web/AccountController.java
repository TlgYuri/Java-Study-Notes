package cn.yurihentai.springcloudalibaba.seata.web;

import cn.yurihentai.springcloudalibaba.seata.bean.common.CommonResult;
import cn.yurihentai.springcloudalibaba.seata.service.AccountService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

@AllArgsConstructor
@RestController
@RequestMapping("/account/")
public class AccountController {

    private AccountService accountService;

    @PostMapping("decrease")
    public CommonResult decrease(@RequestParam Long userId, @RequestParam BigDecimal money, @RequestParam boolean error) {
        long decrease = accountService.decrease(userId, money, error);
        if (decrease > 0) {
            return new CommonResult(200, "操作成功", decrease);
        } else {
            return new CommonResult(500, "操作失败");
        }
    }



}