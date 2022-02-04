package com.zhangxiang.lesson.linkedlist;

import com.zhangxiang.lesson.common.List;

/**
 * @author: zhangxiang
 * @createTime: 2022年02月04日 15:00:58
 * @desc:
 */
public class Main {
    public static void main(String[] args) {
        List<Integer> linkedList = new LinkedList<>();
        linkedList.add(1);
        linkedList.add(2);
        linkedList.add(2,3);
        linkedList.add(4);
        System.out.println(linkedList.indexOf(4));
        System.out.println(linkedList.indexOf(-1));
        System.out.println(linkedList.contains(3));
        linkedList.remove(0);
        linkedList.remove(1);
        System.out.println(linkedList);
    }
}
