package cn.yurihentai.springcloud.sleuth.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProviderController {

    @GetMapping("provide")
    public String provide() {
        return "provider message";
    }

}