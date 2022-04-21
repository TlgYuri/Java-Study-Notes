package cn.yurihentai.springcloudalibaba.seata.enums;

public enum OrderStatusEnum {

    /** 创建中：0 */
    CREATING("创建中", 0),
    COMPLETE("已完成", 1);

    private String status;
    private int value;

    private OrderStatusEnum(String status, int value) {
        this.status = status;
        this.value = value;
    }

}