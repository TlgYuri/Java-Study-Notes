package cn.yurihentai.springcloudalibaba.seata.service.impl;

import cn.yurihentai.springcloudalibaba.seata.mapper.StorageMapper;
import cn.yurihentai.springcloudalibaba.seata.service.StorageService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class StorageServiceImpl implements StorageService {

    private StorageMapper storageMapper;

    @Override
    public long decrease(Long productId, Integer count) {
        return storageMapper.decrease(productId, count);
    }
}