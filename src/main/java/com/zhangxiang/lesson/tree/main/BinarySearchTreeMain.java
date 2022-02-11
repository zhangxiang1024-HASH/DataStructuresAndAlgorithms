package com.zhangxiang.lesson.tree.main;

import com.zhangxiang.lesson.tree.BinarySearchTree;
import com.zhangxiang.lesson.tree.printer.BinaryTrees;

/**
 * @author: zhangxiang
 * @createTime: 2022年02月10日 16:29:09
 * @desc:
 */
public class BinarySearchTreeMain {
    public static void main(String[] args) {
        Integer[] data = new Integer[]{7,4,9,2,5,8,11,1};
        BinarySearchTree<Integer> binarySearchTree = new BinarySearchTree<>();
        /*BinarySearchTree<Integer> binarySearchTree = new BinarySearchTree<>(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o2.intValue() - o1.intValue();
            }
        });*/
        for (int i = 0; i < data.length; i++) {
            binarySearchTree.add(data[i]);
        }
        BinaryTrees.println(binarySearchTree);
        System.out.println("前序遍历:");
        binarySearchTree.preorderTraversal(node -> System.out.println(node.element));
        System.out.println("中序遍历:");
        binarySearchTree.inorderTraversal(node -> System.out.println(node.element));
        System.out.println("后序遍历:");
        binarySearchTree.postorderTraversal(node -> System.out.println(node.element));
        System.out.println("层序遍历:");
        binarySearchTree.levelOrderTraversal(node -> System.out.println(node.element));

        System.out.println(binarySearchTree.height());
        System.out.println(binarySearchTree.isComplete());
    }
}
