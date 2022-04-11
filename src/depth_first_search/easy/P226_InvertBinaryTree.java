package depth_first_search.easy;

import java.util.Objects;

/**
 * 翻转二叉树
 * <a href="https://leetcode-cn.com/problems/invert-binary-tree/">🔗</a>
 *
 * 翻转一棵二叉树。
 *
 * 示例：
 *
 * 输入：
 *
 *      4
 *    /   \
 *   2     7
 *  / \   / \
 * 1   3 6   9
 * 输出：
 *
 *      4
 *    /   \
 *   7     2
 *  / \   / \
 * 9   6 3   1
 * 备注:
 * 这个问题是受到 Max Howell 的 原问题 启发的 ：
 *
 * 谷歌：我们90％的工程师使用您编写的软件(Homebrew)，但是您却无法在面试时在白板上写出翻转二叉树这道题，这太糟糕了。
 *
 * @author chengzhy
 * @date 2021/12/17 15:48
 */
public class P226_InvertBinaryTree {

    public TreeNode invertTree(TreeNode root) {
        // dfs 前序遍历
        if (Objects.nonNull(root)) {
            TreeNode rightNode = root.right;
            root.right = invertTree(root.left);
            root.left = invertTree(rightNode);
        }
        return root;
    }

    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode() {}
        TreeNode(int val) { this.val = val; }
        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }

}
