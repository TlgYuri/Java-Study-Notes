package cn.yurihentai.java.concurrent.lock;

/**
 * @descryption 保存的数据状态
 * @author Yuri
 * @date 2021/12/21 11:45:14
 */
public enum ThreadStatus {

    // 数据为null
    NULL_STATUS(0, "数据为null"),
    // 数据为空
    EMPTY_STATUS(1, "数据为空");

    ThreadStatus(int code, String description) {
        this.code = code;
        this.description = description;
    }

    private int code;
    private String description;

    public int getCode() {
        return code;
    }

    public String getDescription() {
        return description;
    }

}