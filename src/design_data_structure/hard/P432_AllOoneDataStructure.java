package design_data_structure.hard;

import java.util.*;

/**
 * 全 O(1) 的数据结构
 * <a href="https://leetcode-cn.com/problems/all-oone-data-structure/">🔗</a>
 *
 * 请你设计一个用于存储字符串计数的数据结构，并能够返回计数最小和最大的字符串。
 *
 * 实现 AllOne 类：
 *
 * AllOne() 初始化数据结构的对象。
 * inc(String key) 字符串 key 的计数增加 1 。如果数据结构中尚不存在 key ，那么插入计数为 1 的 key 。
 * dec(String key) 字符串 key 的计数减少 1 。如果 key 的计数在减少后为 0 ，那么需要将这个 key 从数据结构中删除。测试用例保证：在减少计数前，key 存在于数据结构中。
 * getMaxKey() 返回任意一个计数最大的字符串。如果没有元素存在，返回一个空字符串 "" 。
 * getMinKey() 返回任意一个计数最小的字符串。如果没有元素存在，返回一个空字符串 "" 。
 *  
 *
 * 示例：
 *
 * 输入
 * ["AllOne", "inc", "inc", "getMaxKey", "getMinKey", "inc", "getMaxKey", "getMinKey"]
 * [[], ["hello"], ["hello"], [], [], ["leet"], [], []]
 * 输出
 * [null, null, null, "hello", "hello", null, "hello", "leet"]
 *
 * 解释
 * AllOne allOne = new AllOne();
 * allOne.inc("hello");
 * allOne.inc("hello");
 * allOne.getMaxKey(); // 返回 "hello"
 * allOne.getMinKey(); // 返回 "hello"
 * allOne.inc("leet");
 * allOne.getMaxKey(); // 返回 "hello"
 * allOne.getMinKey(); // 返回 "leet"
 *  
 *
 * 提示：
 *
 * 1 <= key.length <= 10
 * key 由小写英文字母组成
 * 测试用例保证：在每次调用 dec 时，数据结构中总存在 key
 * 最多调用 inc、dec、getMaxKey 和 getMinKey 方法 5 * 104 次
 *
 * @author chengzhy
 * @date 2022/3/16 10:28
 */
public class P432_AllOoneDataStructure {

    public static class AllOne {
        private Map<String, Integer> map;
        private TreeMap<Integer, Set<String>> treeMap;

        public AllOne() {
            map = new HashMap<>();
            treeMap = new TreeMap<>();
        }

        public void inc(String key) {
            map.put(key, map.getOrDefault(key, 0) + 1);
            int count = map.get(key);
            if (count > 1) {
                // 该key之前已经出现过，则删除之前计数中的key
                if (treeMap.get(count - 1).size() == 1) {
                    // 如果当前计数set中只有其一个，则直接移除这个计数set
                    treeMap.remove(count - 1);
                } else {
                    // 否则就移除其中的这个key
                    treeMap.get(count - 1).remove(key);
                }
            }
            // 向新计数set中添加key
            treeMap.putIfAbsent(count, new HashSet<>());
            treeMap.get(count).add(key);
        }

        public void dec(String key) {
            int count = map.get(key);
            if (count == 1) {
                map.remove(key);
            } else {
                map.put(key, count - 1);
                // 向count - 1的set中添加key
                treeMap.putIfAbsent(count - 1, new HashSet<>());
                treeMap.get(count - 1).add(key);
            }
            // 删除计数set中的key
            if (treeMap.get(count).size() == 1) {
                treeMap.remove(count);
            } else {
                treeMap.get(count).remove(key);
            }
        }

        public String getMaxKey() {
            if (!map.isEmpty()) {
                Map.Entry<Integer, Set<String>> lastEntry = treeMap.lastEntry();
                for (String s : lastEntry.getValue()) {
                    return s;
                }
            }
            return "";
        }

        public String getMinKey() {
            if (!map.isEmpty()) {
                Map.Entry<Integer, Set<String>> firstEntry = treeMap.firstEntry();
                for (String s : firstEntry.getValue()) {
                    return s;
                }
            }
            return "";
        }

        /**
         * 官方题解做法：一个双向链表的每个节点Node存一个字符串集合keys(set)，和一个正整数count(key出现的次数)
         * 链表从头到尾的每个节点的count值单调递增（但不一定连续）
         * 一个map存key和key对应的链表节点Node
         */
        /*Node root;
        Map<String, Node> nodes;

        public AllOne() {
            root = new Node();
            root.prev = root;
            root.next = root;  // 初始化链表哨兵，下面判断节点的 next 若为 root，则表示 next 为空（prev 同理）
            nodes = new HashMap<String, Node>();
        }

        public void inc(String key) {
            if (nodes.containsKey(key)) {
                Node cur = nodes.get(key);
                Node nxt = cur.next;
                if (nxt == root || nxt.count > cur.count + 1) {
                    nodes.put(key, cur.insert(new Node(key, cur.count + 1)));
                } else {
                    nxt.keys.add(key);
                    nodes.put(key, nxt);
                }
                cur.keys.remove(key);
                if (cur.keys.isEmpty()) {
                    cur.remove();
                }
            } else {  // key 不在链表中
                if (root.next == root || root.next.count > 1) {
                    nodes.put(key, root.insert(new Node(key, 1)));
                } else {
                    root.next.keys.add(key);
                    nodes.put(key, root.next);
                }
            }
        }

        public void dec(String key) {
            Node cur = nodes.get(key);
            if (cur.count == 1) {  // key 仅出现一次，将其移出 nodes
                nodes.remove(key);
            } else {
                Node pre = cur.prev;
                if (pre == root || pre.count < cur.count - 1) {
                    nodes.put(key, cur.prev.insert(new Node(key, cur.count - 1)));
                } else {
                    pre.keys.add(key);
                    nodes.put(key, pre);
                }
            }
            cur.keys.remove(key);
            if (cur.keys.isEmpty()) {
                cur.remove();
            }
        }

        public String getMaxKey() {
            return root.prev != null ? root.prev.keys.iterator().next() : "";
        }

        public String getMinKey() {
            return root.next != null ? root.next.keys.iterator().next() : "";
        }*/
    }

    public static class Node {
        Node prev;
        Node next;
        Set<String> keys;
        int count;

        public Node() {
            this("", 0);
        }

        public Node(String key, int count) {
            this.count = count;
            keys = new HashSet<String>();
            keys.add(key);
        }

        public Node insert(Node node) {  // 在 this 后插入 node
            node.prev = this;
            node.next = this.next;
            node.prev.next = node;
            node.next.prev = node;
            return node;
        }

        public void remove() {
            this.prev.next = this.next;
            this.next.prev = this.prev;
        }
    }

}
