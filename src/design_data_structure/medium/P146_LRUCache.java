package design_data_structure.medium;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * LRU 缓存
 * <a href="https://leetcode-cn.com/problems/lru-cache/">🔗</a>
 *
 * 请你设计并实现一个满足  LRU (最近最少使用) 缓存 约束的数据结构。
 * 实现 LRUCache 类：
 * LRUCache(int capacity) 以 正整数 作为容量 capacity 初始化 LRU 缓存
 * int get(int key) 如果关键字 key 存在于缓存中，则返回关键字的值，否则返回 -1 。
 * void put(int key, int value) 如果关键字 key 已经存在，则变更其数据值 value ；如果不存在，则向缓存中插入该组 key-value 。如果插入操作导致关键字数量超过 capacity ，则应该 逐出 最久未使用的关键字。
 * 函数 get 和 put 必须以 O(1) 的平均时间复杂度运行。
 *
 *  
 *
 * 示例：
 *
 * 输入
 * ["LRUCache", "put", "put", "get", "put", "get", "put", "get", "get", "get"]
 * [[2], [1, 1], [2, 2], [1], [3, 3], [2], [4, 4], [1], [3], [4]]
 * 输出
 * [null, null, null, 1, null, -1, null, -1, 3, 4]
 *
 * 解释
 * LRUCache lRUCache = new LRUCache(2);
 * lRUCache.put(1, 1); // 缓存是 {1=1}
 * lRUCache.put(2, 2); // 缓存是 {1=1, 2=2}
 * lRUCache.get(1);    // 返回 1
 * lRUCache.put(3, 3); // 该操作会使得关键字 2 作废，缓存是 {1=1, 3=3}
 * lRUCache.get(2);    // 返回 -1 (未找到)
 * lRUCache.put(4, 4); // 该操作会使得关键字 1 作废，缓存是 {4=4, 3=3}
 * lRUCache.get(1);    // 返回 -1 (未找到)
 * lRUCache.get(3);    // 返回 3
 * lRUCache.get(4);    // 返回 4
 *  
 *
 * 提示：
 *
 * 1 <= capacity <= 3000
 * 0 <= key <= 10000
 * 0 <= value <= 105
 * 最多调用 2 * 105 次 get 和 put
 *
 * @author chengzhy
 * @date 2022/2/16 9:34
 */
public class P146_LRUCache {

    public static class LRUCache extends LinkedHashMap<Integer, Integer> {
        private int capacity;

        public LRUCache(int capacity) {
            super(capacity, 0.75f, true);
            this.capacity = capacity;
        }

        public int get(int key) {
            return super.getOrDefault(key, -1);
        }

        public void put(int key, int value) {
            super.put(key, value);
        }

        /**
         * Returns <tt>true</tt> if this map should remove its eldest entry.
         * This method is invoked by <tt>put</tt> and <tt>putAll</tt> after
         * inserting a new entry into the map.  It provides the implementor
         * with the opportunity to remove the eldest entry each time a new one
         * is added.  This is useful if the map represents a cache: it allows
         * the map to reduce memory consumption by deleting stale entries.
         *
         * <p>Sample use: this override will allow the map to grow up to 100
         * entries and then delete the eldest entry each time a new entry is
         * added, maintaining a steady state of 100 entries.
         * <pre>
         *     private static final int MAX_ENTRIES = 100;
         *
         *     protected boolean removeEldestEntry(Map.Entry eldest) {
         *        return size() &gt; MAX_ENTRIES;
         *     }
         * </pre>
         *
         * <p>This method typically does not modify the map in any way,
         * instead allowing the map to modify itself as directed by its
         * return value.  It <i>is</i> permitted for this method to modify
         * the map directly, but if it does so, it <i>must</i> return
         * <tt>false</tt> (indicating that the map should not attempt any
         * further modification).  The effects of returning <tt>true</tt>
         * after modifying the map from within this method are unspecified.
         *
         * <p>This implementation merely returns <tt>false</tt> (so that this
         * map acts like a normal map - the eldest element is never removed).
         *
         * @param eldest The least recently inserted entry in the map, or if
         *               this is an access-ordered map, the least recently accessed
         *               entry.  This is the entry that will be removed it this
         *               method returns <tt>true</tt>.  If the map was empty prior
         *               to the <tt>put</tt> or <tt>putAll</tt> invocation resulting
         *               in this invocation, this will be the entry that was just
         *               inserted; in other words, if the map contains a single
         *               entry, the eldest entry is also the newest.
         * @return <tt>true</tt> if the eldest entry should be removed
         * from the map; <tt>false</tt> if it should be retained.
         */
        @Override
        protected boolean removeEldestEntry(Map.Entry<Integer, Integer> eldest) {
            return size() > capacity;
        }
    }

    /*public static class LRUCache {
        private int capacity;
        private HashMap<Integer, Node> hashMap = new HashMap<>();
        private int size;
        private Node head = new Node();
        private Node tail = new Node();

        public LRUCache(int capacity) {
            this.capacity = capacity;
            this.size = 0;
            head.next = tail;
            tail.pre = head;
        }

        public int get(int key) {
            Node node = hashMap.get(key);
            if (node == null) {
                return -1;
            }
            // 移动该节点到表头第一位置
            moveNewestNode(node);
            return node.value;
        }

        public void put(int key, int value) {
            Node node = hashMap.get(key);
            if (node == null) {
                node = new Node(key, value);
                hashMap.put(key, node);
                // 添加该节点到表头第一位置
                addNode(node);
            } else {
                node.value = value;
                // 移动该节点到表头第一位置
                moveNewestNode(node);
                return;
            }
            if (size > capacity) {
                // 删除最老的节点
                removeEldestNode(tail.pre);
            }
        }

        *//**
         * 向链表开头添加元素
         *
         * @param node 节点
         *//*
        private void addNode(Node node) {
            node.next = head.next;
            head.next.pre = node;
            node.pre = head;
            head.next = node;
            size++;
        }

        *//**
         * 移动节点到表头
         *
         * @param node 节点
         *//*
        private void moveNewestNode(Node node) {
            Node pre = node.pre, next = node.next;
            if (pre != null && next != null) {
                pre.next = next;
                next.pre = pre;
            }
            node.next = head.next;
            head.next.pre = node;
            node.pre = head;
            head.next = node;
        }

        *//**
         * 删除最老的节点
         *
         * @param node 节点
         *//*
        private void removeEldestNode(Node node) {
            Node pre = node.pre, next = node.next;
            pre.next = next;
            next.pre = pre;
            node.pre = null;
            node.next = null;
            hashMap.remove(node.key, node);
            size--;
        }

        private class Node {
            private int key;
            private int value;
            private Node pre;
            private Node next;
            public Node() {
            }
            public Node(int key, int value) {
                this.key = key;
                this.value = value;
            }
        }
    }*/

}
