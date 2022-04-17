package cn.yurihentai.java;

import java.util.Arrays;

/**
 * @author Yuri
 */
public class Test {

    public static void main(String[] args) {
        // 冒泡排序
        int[] array = new int[]{11,45,14,19,19,8,10};
        for(int i = 0; i < array.length-1; i++) {
            for(int j = 0; j < array.length-1-i; j++) {
                if(array[j] > array[j+1]) {
                    int temp = array[j];
                    array[j] = array[j+1];
                    array[j+1] = temp;
                }
            }
        }
        System.out.println(Arrays.toString(array));
    }

}