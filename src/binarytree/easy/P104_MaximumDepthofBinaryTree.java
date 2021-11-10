package binarytree.easy;

import java.util.ArrayDeque;
import java.util.Objects;
import java.util.Queue;

/**
 * 二叉树的最大深度
 *
 * 给定一个二叉树，找出其最大深度。
 *
 * 二叉树的深度为根节点到最远叶子节点的最长路径上的节点数。
 *
 * 说明: 叶子节点是指没有子节点的节点。
 *
 * 示例：
 * 给定二叉树 [3,9,20,null,null,15,7]，
 *
 *     3
 *    / \
 *   9  20
 *     /  \
 *    15   7
 * 返回它的最大深度 3 。
 *
 * @author chengzhy
 * @date 2021/11/10 15:09
 */
public class P104_MaximumDepthofBinaryTree {

    public int maxDepth(TreeNode root) {
        /**
         * bfs 慢
         */
        /*int depth = 0;
        if (Objects.nonNull(root)) {
            Queue<TreeNode> queue = new ArrayDeque<>();
            queue.offer(root);
            while (!queue.isEmpty()) {
                int size = queue.size();
                for (int i = 0; i < size; i++) {
                    TreeNode treeNode = queue.poll();
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
        return depth;*/
        /**
         * dfs+分治 快
         */
        return Objects.isNull(root) ? 0 : Math.max(maxDepth(root.left), maxDepth(root.right)) + 1;
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
