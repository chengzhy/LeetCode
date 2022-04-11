package breadth_first_search.easy;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Objects;

/**
 * 二叉树的最小深度
 * <a href="https://leetcode-cn.com/problems/minimum-depth-of-binary-tree/">🔗</a>
 *
 * 给定一个二叉树，找出其最小深度。
 *
 * 最小深度是从根节点到最近叶子节点的最短路径上的节点数量。
 *
 * 说明：叶子节点是指没有子节点的节点。
 *
 *  
 *
 * 示例 1：
 *
 *
 * 输入：root = [3,9,20,null,null,15,7]
 * 输出：2
 * 示例 2：
 *
 * 输入：root = [2,null,3,null,4,null,5,null,6]
 * 输出：5
 *  
 *
 * 提示：
 *
 * 树中节点数的范围在 [0, 105] 内
 * -1000 <= Node.val <= 1000
 *
 * @author chengzhy
 * @date 2021/11/10 15:55
 */
public class P111_MinimumDepthofBinaryTree {

    public int minDepth(TreeNode root) {
        int depth = 0;
        if (Objects.nonNull(root)) {
            Deque<TreeNode> queue = new ArrayDeque<>();
            queue.offer(root);
            depth++;
            while (!queue.isEmpty()) {
                int size = queue.size();
                for (int i = 0; i < size; i++) {
                    TreeNode treeNode = queue.poll();
                    if (Objects.isNull(treeNode.left) && Objects.isNull(treeNode.right)) {
                        // 没有子节点直接返回结果
                        return depth;
                    }
                    if (Objects.nonNull(treeNode.left)) {
                        queue.offer(treeNode.left);
                    }
                    if (Objects.nonNull(treeNode.right)) {
                        queue.offer(treeNode.right);
                    }
                }
                depth++;
            }
        }
        return depth;
    }

    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode() {}

        TreeNode(int val) {
            this.val = val;
        }

        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }

}
