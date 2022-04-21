package cn.yurihentai.springcloudalibaba.seata.service;

public interface StorageService {

    /** 扣减库存 */
    long decrease(Long productId, Integer count);

}