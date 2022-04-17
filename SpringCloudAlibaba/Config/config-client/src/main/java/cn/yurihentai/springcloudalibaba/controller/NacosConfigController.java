package cn.yurihentai.springcloudalibaba.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RefreshScope // 配置自动更新 (Nacos会记录配置文件的历史版本，默认保留30天  此外nacos还有一键回滚功能，并且回滚操作会触发配置更新
@RestController
public class NacosConfigController {

    @Value("${config.info:error}")
    private  String configInfo;

    @GetMapping("getConfig")
    public String getConfig() {
        return "从nacos获取到的configInfo为：" + configInfo;
    }

}