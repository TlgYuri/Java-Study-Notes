package cn.yurihentai.springcloud.stream.mq.impl;

import cn.yurihentai.springcloud.stream.mq.IMessageProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.messaging.Source;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.support.MessageBuilder;

import java.util.UUID;

// 可以理解为是一个消息的发送管道的定义
@EnableBinding(Source.class)
public class MessageProvider implements IMessageProvider {

    @Autowired
    private MessageChannel output; // 消息的发送管道

    @Override
    public String send() {
        String serial = UUID.randomUUID().toString();
        this.output.send(MessageBuilder.withPayload(serial).build()); // 创建并发送消息
        return serial;
    }
}