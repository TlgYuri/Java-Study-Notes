package xyz.yurihentai.controller;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class test {
    private String test;
    public void setTest(String test) {
        this.test = test;
    }
    public String getTest( ) {
        return this.test;
    }
    public static void main(String[] args) throws Exception {
        List<String> shitList = new CopyOnWriteArrayList<>();
        shitList.add("Fuck");
        shitList.add("u");
        for(final String shit : shitList) {
            System.out.println(shit);
        }
    }
}
