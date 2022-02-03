package com.zhangxiang.lesson.dynamicarray;


/**
 * @author: zhangxiang
 * @createTime: 2022年02月03日 18:36:31
 * @desc:
 */
public class Main {
    public static void main(String[] args) {
        DynamicArray dynamicArray = new DynamicArray(8);
        dynamicArray.add(1);
        dynamicArray.add(2);
        dynamicArray.add(99);
//        dynamicArray.remove(0);
        dynamicArray.add(dynamicArray.size()-1,66);
        System.out.println(dynamicArray);
    }
}
