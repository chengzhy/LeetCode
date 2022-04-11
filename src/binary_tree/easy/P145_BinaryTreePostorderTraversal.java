package binary_tree.easy;

import java.util.*;

/**
 * 二叉树的后序遍历
 * <a href="https://leetcode-cn.com/problems/binary-tree-postorder-traversal/">🔗</a>
 *
 * 给定一个二叉树，返回它的 后序 遍历。
 *
 * 示例:
 *
 * 输入: [1,null,2,3]
 *    1
 *     \
 *      2
 *     /
 *    3
 *
 * 输出: [3,2,1]
 * 进阶: 递归算法很简单，你可以通过迭代算法完成吗？
 *
 * @author chengzhy
 * @date 2021/11/11 16:21
 */
public class P145_BinaryTreePostorderTraversal {

    public List<Integer> postorderTraversal(TreeNode root) {
        if (Objects.isNull(root)) {
            return Collections.emptyList();
        }
        List<Integer> result = new ArrayList<>();
        /**
         * 非递归
         */
        Deque<TreeNode> stack = new ArrayDeque<>();
        TreeNode p = null;
        while (Objects.nonNull(root) || !stack.isEmpty()) {
            if (Objects.nonNull(root)) {
                stack.push(root);
                root = root.left;
            } else {
                root = stack.peek();
                if (Objects.isNull(root.right) || root.right == p) {
                    result.add(root.val);
                    p = root;
                    stack.pop();
                    root = null;
                } else {
                    root = root.right;
                }
            }
        }
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
