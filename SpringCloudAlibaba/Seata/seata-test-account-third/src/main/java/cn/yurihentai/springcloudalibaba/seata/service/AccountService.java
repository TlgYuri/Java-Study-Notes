package cn.yurihentai.springcloudalibaba.seata.service;

import java.math.BigDecimal;

public interface AccountService {

    long decrease( Long userId, BigDecimal money, boolean error);

}