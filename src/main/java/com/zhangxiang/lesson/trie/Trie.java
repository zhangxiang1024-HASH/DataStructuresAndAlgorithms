package com.zhangxiang.lesson.trie;

import java.util.HashMap;

/**
 * @author: zhangxiang
 * @createTime: 2022年03月21日 20:42:38
 * @desc:
 */
public class Trie<V> {
    private int size;

    public int size() {
        return 0;
    }

    public boolean isEmpty() {
        return false;
    }

    public void clear() {

    }

    public V get(String key) {
        return null;
    }

    public boolean contains(String key) {
        return false;
    }

    public V add(String key, V value) {
        return null;
    }

    public V remove(String key) {
        return null;
    }

    public boolean startsWith(String str) {
        return false;
    }

    private static class Node<V> {
        HashMap<Character, Node<V>> children;
        V value;
        boolean word;//是否是单词的结尾
    }
}
