package cn.yurihentai.springcloudalibaba.seata.mapper;

import org.apache.ibatis.annotations.Param;

public interface StorageMapper {

    long decrease(@Param("productId") Long productId, @Param("count") Integer count);

}