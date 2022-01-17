package binary_tree.medium;

import java.util.*;

/**
 * 二叉树的层序遍历 II
 *
 * 给定一个二叉树，返回其节点值自底向上的层序遍历。 （即按从叶子节点所在层到根节点所在的层，逐层从左向右遍历）
 *
 * 例如：
 * 给定二叉树 [3,9,20,null,null,15,7],
 *
 *     3
 *    / \
 *   9  20
 *     /  \
 *    15   7
 * 返回其自底向上的层序遍历为：
 *
 * [
 *   [15,7],
 *   [9,20],
 *   [3]
 * ]
 *
 * @author chengzhy
 * @date 2021/11/11 9:05
 */
public class P107_BinaryTreeLevelOrderTraversalII {

    public List<List<Integer>> levelOrderBottom(TreeNode root) {
        /**
         * 笨，列表直接反转就完事了
         */
        /*if (Objects.isNull(root)) {
            return Collections.emptyList();
        }
        Deque<List<Integer>> resultQueue = new ArrayDeque<>();
        Deque<TreeNode> queue = new ArrayDeque<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            int size = queue.size();
            List<Integer> tempList = new ArrayList<>();
            for (int i = 0; i < size; i++) {
                TreeNode treeNode = queue.poll();
                tempList.add(treeNode.val);
                if (Objects.nonNull(treeNode.left)) {
                    queue.offer(treeNode.left);
                }
                if (Objects.nonNull(treeNode.right)) {
                    queue.offer(treeNode.right);
                }
            }
            resultQueue.push(tempList);
        }
        List<List<Integer>> result = new ArrayList<>(resultQueue.size());
        while (!resultQueue.isEmpty()) {
            result.add(resultQueue.pop());
        }
        return result;*/

        if (Objects.isNull(root)) {
            return Collections.emptyList();
        }
        List<List<Integer>> result = new ArrayList<>();
        Deque<TreeNode> queue = new ArrayDeque<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            int size = queue.size();
            List<Integer> tempList = new ArrayList<>(size);
            for (int i = 0; i < size; i++) {
                TreeNode treeNode = queue.poll();
                tempList.add(treeNode.val);
                if (Objects.nonNull(treeNode.left)) {
                    queue.offer(treeNode.left);
                }
                if (Objects.nonNull(treeNode.right)) {
                    queue.offer(treeNode.right);
                }
            }
            result.add(tempList);
        }
        Collections.reverse(result);
        return result;
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
