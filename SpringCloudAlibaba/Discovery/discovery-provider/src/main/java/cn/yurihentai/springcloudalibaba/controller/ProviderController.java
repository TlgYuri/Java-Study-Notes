package cn.yurihentai.springcloudalibaba.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProviderController {

    @GetMapping("provider")
    public String provider() {
        return "nacos provider";
    }

}