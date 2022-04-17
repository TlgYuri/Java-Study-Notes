package cn.yurihentai.springcloud.stream.controller;

import cn.yurihentai.springcloud.stream.mq.IMessageProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProviderController {

    @Autowired
    private IMessageProvider messageProvider;

    @GetMapping(value = "/send")
    public String sendMessage()
    {
        return messageProvider.send();
    }

}