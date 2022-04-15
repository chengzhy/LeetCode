package design_data_structure.hard;

import java.util.*;

/**
 * LFU 缓存
 * <a href="https://leetcode-cn.com/problems/lfu-cache/">🔗</a>
 *
 * 请你为 最不经常使用（LFU）缓存算法设计并实现数据结构。
 *
 * 实现 LFUCache 类：
 *
 * LFUCache(int capacity) - 用数据结构的容量 capacity 初始化对象
 * int get(int key) - 如果键 key 存在于缓存中，则获取键的值，否则返回 -1 。
 * void put(int key, int value) - 如果键 key 已存在，则变更其值；如果键不存在，请插入键值对。当缓存达到其容量 capacity 时，则应该在插入新项之前，移除最不经常使用的项。在此问题中，当存在平局（即两个或更多个键具有相同使用频率）时，应该去除 最近最久未使用 的键。
 * 为了确定最不常使用的键，可以为缓存中的每个键维护一个 使用计数器 。使用计数最小的键是最久未使用的键。
 *
 * 当一个键首次插入到缓存中时，它的使用计数器被设置为 1 (由于 put 操作)。对缓存中的键执行 get 或 put 操作，使用计数器的值将会递增。
 *
 * 函数 get 和 put 必须以 O(1) 的平均时间复杂度运行。
 *
 *  
 *
 * 示例：
 *
 * 输入：
 * ["LFUCache", "put", "put", "get", "put", "get", "get", "put", "get", "get", "get"]
 * [[2], [1, 1], [2, 2], [1], [3, 3], [2], [3], [4, 4], [1], [3], [4]]
 * 输出：
 * [null, null, null, 1, null, -1, 3, null, -1, 3, 4]
 *
 * 解释：
 * // cnt(x) = 键 x 的使用计数
 * // cache=[] 将显示最后一次使用的顺序（最左边的元素是最近的）
 * LFUCache lfu = new LFUCache(2);
 * lfu.put(1, 1);   // cache=[1,_], cnt(1)=1
 * lfu.put(2, 2);   // cache=[2,1], cnt(2)=1, cnt(1)=1
 * lfu.get(1);      // 返回 1
 *                  // cache=[1,2], cnt(2)=1, cnt(1)=2
 * lfu.put(3, 3);   // 去除键 2 ，因为 cnt(2)=1 ，使用计数最小
 *                  // cache=[3,1], cnt(3)=1, cnt(1)=2
 * lfu.get(2);      // 返回 -1（未找到）
 * lfu.get(3);      // 返回 3
 *                  // cache=[3,1], cnt(3)=2, cnt(1)=2
 * lfu.put(4, 4);   // 去除键 1 ，1 和 3 的 cnt 相同，但 1 最久未使用
 *                  // cache=[4,3], cnt(4)=1, cnt(3)=2
 * lfu.get(1);      // 返回 -1（未找到）
 * lfu.get(3);      // 返回 3
 *                  // cache=[3,4], cnt(4)=1, cnt(3)=3
 * lfu.get(4);      // 返回 4
 *                  // cache=[3,4], cnt(4)=2, cnt(3)=3
 *  
 *
 * 提示：
 *
 * 0 <= capacity <= 104
 * 0 <= key <= 105
 * 0 <= value <= 109
 * 最多调用 2 * 105 次 get 和 put 方法
 *
 * @author chengzhy
 * @date 2022/4/14 19:03
 */
public class P460_LFUCache {

    public static class LFUCache {
        private Map<Integer, Node> cache;
        private Map<Integer, LinkedHashSet<Node>> frequencyMap;
        private int capacity;
        private int size;
        private int minFrequency;

        public LFUCache(int capacity) {
            this.capacity = capacity;
            cache = new HashMap<>(this.capacity);
            frequencyMap = new HashMap<>();
        }

        public int get(int key) {
            Node node = cache.get(key);
            if (node == null) {
                return -1;
            }
            node.frequency++;
            // 将该Node移动到对应的频率队列中
            addNodeToNewList(node);
            return node.value;
        }

        public void put(int key, int value) {
            if (capacity == 0) {
                return;
            }
            Node node = cache.get(key);
            if (node == null) {
                node = new Node(key, value);
                // 必须在这里判断是否要移除最不经常使用的节点，不然新加入的节点就直接被移除了
                if (size == capacity) {
                    // 移除最不经常使用的节点
                    removeEldestNode();
                }
                cache.put(key, node);
                minFrequency = 0;
                size++;
            } else {
                node.value = value;
                node.frequency++;
            }
            // 将该Node移动到对应的频率队列中
            addNodeToNewList(node);
        }

        /**
         * 将Node移动到对应的频率队列中
         *
         * @param node 节点
         */
        private void addNodeToNewList(Node node) {
            if (node.frequency > 0) {
                // 将该节点从原先的队列删除
                removeNodeOnOldList(node);
            }
            // 将该节点加入新的频率队列
            LinkedHashSet<Node> frequencyList = frequencyMap.get(node.frequency);
            if (frequencyList == null) {
                frequencyList = new LinkedHashSet<>();
                frequencyMap.put(node.frequency, frequencyList);
            }
            frequencyList.add(node);
        }

        /**
         * 移除最不经常使用的节点
         */
        private void removeEldestNode() {
            LinkedHashSet<Node> minFrequencyList = frequencyMap.get(minFrequency);
            Node eldestNode = minFrequencyList.iterator().next();
            if (minFrequencyList.size() == 1) {
                frequencyMap.remove(minFrequency);
                // 这里不用minFrequency++，因为移除最不经常使用的节点一定发生在添加新节点时，那么之后minFrequency一定为0
            } else {
                minFrequencyList.remove(eldestNode);
            }
            cache.remove(eldestNode.key);
            size--;
        }

        /**
         * 将节点从原先的队列删除
         *
         * @param node
         */
        private void removeNodeOnOldList(Node node) {
            int oldFrequency = node.frequency - 1;
            LinkedHashSet<Node> oldFrequencyList = frequencyMap.get(oldFrequency);
            if (oldFrequencyList.size() == 1) {
                frequencyMap.remove(oldFrequency);
                if (minFrequency == oldFrequency) {
                    minFrequency = node.frequency;
                }
            } else {
                oldFrequencyList.remove(node);
            }
        }

        private class Node {
            private int key;
            private int value;
            // 使用频率
            private int frequency;

            public Node(int key, int value) {
                this.key = key;
                this.value = value;
            }
        }
    }

}
