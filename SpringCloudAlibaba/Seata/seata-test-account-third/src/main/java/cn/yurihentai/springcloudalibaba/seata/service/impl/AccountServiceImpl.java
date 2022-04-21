package cn.yurihentai.springcloudalibaba.seata.service.impl;

import cn.yurihentai.springcloudalibaba.seata.mapper.AccountMapper;
import cn.yurihentai.springcloudalibaba.seata.service.AccountService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
@AllArgsConstructor
public class AccountServiceImpl implements AccountService {

    private AccountMapper accountMapper;

    @Override
    public long decrease(Long userId, BigDecimal money, boolean error) {
        if (error) {
            throw new RuntimeException("交易失败！");
        }
        return accountMapper.decrease(userId, money);
    }
}