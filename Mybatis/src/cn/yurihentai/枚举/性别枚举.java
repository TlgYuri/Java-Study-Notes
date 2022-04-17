package cn.yurihentai.枚举;

public enum 性别枚举 {
    男(1),女(0);

    private Integer 性别;

    private 性别枚举(Integer 性别){
        this.性别 = 性别;
    }

    public Integer 性别() {
        return this.性别;
    }
}
