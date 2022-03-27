package com.zhangxiang.lesson.trie;

import java.util.HashMap;

/**
 * @author: zhangxiang
 * @createTime: 2022年03月21日 20:42:38
 * @desc:
 */
public class Trie<V> {
    private int size;
    private Node<V> root;

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public void clear() {
        size = 0;
        root = null;
    }

    public V get(String key) {
        Node<V> node = node(key);
        return node == null || !node.word? null : node.value;
    }

    public boolean contains(String key) {
        Node<V> node = node(key);
        return node != null && node.word;
    }

    public V add(String key, V value) {
        checkKey(key);
        if (root == null){
            root = new Node<>();
        }
        Node<V> node = this.root;
        for (int i = 0; i < key.length(); i++) {
            char c = key.charAt(i);
            Node<V> childNode = node.getChildren().get(c);
            if (childNode == null) {
                childNode = new Node<>();
                node.getChildren().put(c, childNode);
            }
            node = childNode;
        }
        if (node.word) {
            V oldValue = node.value;
            node.value = value;
            return oldValue;
        }
        node.word = true;
        node.value = value;
        size++;
        return null;
    }

    public V remove(String key) {
        Node<V> node = node(key);
        if (node == null || !node.word) {
            return null;
        }
        size--;
        V oldValue = node.value;
        if (!node.getChildren().isEmpty()) {
            node.word = false;
            node.value = null;
            return oldValue;
        }
        for (int i = key.length() - 1; i >= 0; i--) {
            String substring = key.substring(0, i);
            Node<V> vNode = node(substring);
            vNode.getChildren().remove(key.charAt(i));
            if (!vNode.children.isEmpty()) {
                return oldValue;
            }
        }
        root = null;
        return oldValue;
    }

    public boolean startsWith(String str) {
        return node(str) != null;
    }

    private Node<V> node(String key) {
        if (root == null) {
            return null;
        }
        checkKey(key);
        Node<V> node = this.root;
        for (int i = 0; i < key.length(); i++) {
            node = node.getChildren().get(key.charAt(i));
            if (node == null) {
                return null;
            }
        }
        return node;
    }

    private void checkKey(String key) {
        if (key == null || key.length() == 0) {
            throw new IllegalArgumentException("Key must not be empty");
        }
    }

    private static class Node<V> {
        HashMap<Character, Node<V>> children;
        V value;
        boolean word;//是否是单词的结尾

        public HashMap<Character, Node<V>> getChildren() {
            if(null == children){
                children = new HashMap<>();
            }
            return children;
        }
    }
}
