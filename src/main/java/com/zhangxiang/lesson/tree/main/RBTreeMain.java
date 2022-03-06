package com.zhangxiang.lesson.tree.main;

import com.zhangxiang.lesson.tree.RBTree;
import com.zhangxiang.lesson.tree.printer.BinaryTrees;

import java.util.HashSet;

/**
 * @author: zhangxiang
 * @createTime: 2022年03月02日 19:26:25
 * @desc:
 */
public class RBTreeMain {
    public static void main(String[] args) {
        Integer[] data = new Integer[]{55, 87, 56, 74, 96, 22, 62, 20, 70, 68, 90, 50};
        RBTree<Integer> rbTree = new RBTree<>();
        for (Integer integer : data) {
            rbTree.add(integer);
        }
        for (int i = 0; i < data.length; i++) {
            rbTree.remove(data[i]);
            BinaryTrees.println(rbTree);
        }
    }
}
