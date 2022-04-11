package graph.hard;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * 重构一棵树的方案数
 * <a href="https://leetcode-cn.com/problems/number-of-ways-to-reconstruct-a-tree/">🔗</a>
 *
 * 给你一个数组 pairs ，其中 pairs[i] = [xi, yi] ，并且满足：
 *
 * pairs 中没有重复元素
 * xi < yi
 * 令 ways 为满足下面条件的有根树的方案数：
 *
 * 树所包含的所有节点值都在 pairs 中。
 * 一个数对 [xi, yi] 出现在 pairs 中 当且仅当 xi 是 yi 的祖先或者 yi 是 xi 的祖先。
 * 注意：构造出来的树不一定是二叉树。
 * 两棵树被视为不同的方案当存在至少一个节点在两棵树中有不同的父节点。
 *
 * 请你返回：
 *
 * 如果 ways == 0 ，返回 0 。
 * 如果 ways == 1 ，返回 1 。
 * 如果 ways > 1 ，返回 2 。
 * 一棵 有根树 指的是只有一个根节点的树，所有边都是从根往外的方向。
 *
 * 我们称从根到一个节点路径上的任意一个节点（除去节点本身）都是该节点的 祖先 。根节点没有祖先。
 *
 *  
 *
 * 示例 1：
 *
 *
 * 输入：pairs = [[1,2],[2,3]]
 * 输出：1
 * 解释：如上图所示，有且只有一个符合规定的有根树。
 * 示例 2：
 *
 *
 * 输入：pairs = [[1,2],[2,3],[1,3]]
 * 输出：2
 * 解释：有多个符合规定的有根树，其中三个如上图所示。
 * 示例 3：
 *
 * 输入：pairs = [[1,2],[2,3],[2,4],[1,5]]
 * 输出：0
 * 解释：没有符合规定的有根树。
 *  
 *
 * 提示：
 *
 * 1 <= pairs.length <= 105
 * 1 <= xi < yi <= 500
 * pairs 中的元素互不相同。
 *
 * @author chengzhy
 * @date 2022/2/16 13:36
 */
public class P1719_NumberOfWaysToReconstructATree {

    public int checkWays(int[][] pairs) {
        Map<Integer, Set<Integer>> adj = new HashMap<Integer, Set<Integer>>();
        for (int[] p : pairs) {
            adj.putIfAbsent(p[0], new HashSet<Integer>());
            adj.putIfAbsent(p[1], new HashSet<Integer>());
            adj.get(p[0]).add(p[1]);
            adj.get(p[1]).add(p[0]);
        }
        /* 检测是否存在根节点*/
        int root = -1;
        Set<Map.Entry<Integer, Set<Integer>>> entries = adj.entrySet();
        for (Map.Entry<Integer, Set<Integer>> entry : entries) {
            int node = entry.getKey();
            Set<Integer> neighbours = entry.getValue();
            if (neighbours.size() == adj.size() - 1) {
                root = node;
            }
        }
        if (root == -1) {
            return 0;
        }

        int res = 1;
        for (Map.Entry<Integer, Set<Integer>> entry : entries) {
            int node = entry.getKey();
            Set<Integer> neighbours = entry.getValue();
            if (node == root) {
                continue;
            }
            int currDegree = neighbours.size();
            int parent = -1;
            int parentDegree = Integer.MAX_VALUE;

            /* 根据 degree 的大小找到 node 的父节点 parent */
            for (int neighbour : neighbours) {
                if (adj.get(neighbour).size() < parentDegree && adj.get(neighbour).size() >= currDegree) {
                    parent = neighbour;
                    parentDegree = adj.get(neighbour).size();
                }
            }
            if (parent == -1) {
                return 0;
            }

            /* 检测 neighbours 是否是 adj[parent] 的子集 */
            for (int neighbour : neighbours) {
                if (neighbour == parent) {
                    continue;
                }
                if (!adj.get(parent).contains(neighbour)) {
                    return 0;
                }
            }
            if (parentDegree == currDegree) {
                res = 2;
            }
        }
        return res;
    }

}
