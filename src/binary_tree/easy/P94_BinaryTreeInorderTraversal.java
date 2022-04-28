package binary_tree.easy;

import java.util.*;

/**
 * 二叉树的中序遍历
 * <a href="https://leetcode.cn/problems/binary-tree-inorder-traversal/">🔗</a>
 *
 * 给定一个二叉树的根节点 root ，返回它的 中序 遍历。
 *
 *  
 *
 * 示例 1：
 *
 *
 * 输入：root = [1,null,2,3]
 * 输出：[1,3,2]
 * 示例 2：
 *
 * 输入：root = []
 * 输出：[]
 * 示例 3：
 *
 * 输入：root = [1]
 * 输出：[1]
 * 示例 4：
 *
 *
 * 输入：root = [1,2]
 * 输出：[2,1]
 * 示例 5：
 *
 *
 * 输入：root = [1,null,2]
 * 输出：[1,2]
 *  
 *
 * 提示：
 *
 * 树中节点数目在范围 [0, 100] 内
 * -100 <= Node.val <= 100
 *  
 *
 * 进阶: 递归算法很简单，你可以通过迭代算法完成吗？
 *
 * @author chengzhy
 * @date 2021/11/8 10:39
 */
public class P94_BinaryTreeInorderTraversal {

    public List<Integer> inorderTraversal(TreeNode root) {
        if (Objects.isNull(root)) {
            return Collections.emptyList();
        }
        List<Integer> result = new ArrayList<>();
        /**
         * 来个非递归
         */
        Deque<TreeNode> stack = new ArrayDeque<>();
        while (Objects.nonNull(root) || !stack.isEmpty()) {
            if (Objects.nonNull(root)) {
                stack.push(root);
                root = root.left;
            } else {
                TreeNode treeNode = stack.pop();
                result.add(treeNode.val);
                root = treeNode.right;
            }
        }
        // dfs(root, result);
        return result;
    }

    private void dfs(TreeNode treeNode, List<Integer> result) {
        if (Objects.isNull(treeNode)) {
            return;
        }
        dfs(treeNode.left, result);
        result.add(treeNode.val);
        dfs(treeNode.right, result);
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
