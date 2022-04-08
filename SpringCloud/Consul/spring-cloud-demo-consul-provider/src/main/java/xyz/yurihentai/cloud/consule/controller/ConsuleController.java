package xyz.yurihentai.cloud.consule.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @description：
 * @author： Yuri
 * @date： 2022/4/8 11:45:14
 */
@RestController
public class ConsuleController {

    @GetMapping("provider")
    public String provider() {
        return "provider";
    }

}