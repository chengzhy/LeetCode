package depth_first_search.easy;

import java.util.Objects;

/**
 * 平衡二叉树
 * <a href="https://leetcode-cn.com/problems/balanced-binary-tree/">🔗</a>
 *
 * 给定一个二叉树，判断它是否是高度平衡的二叉树。
 *
 * 本题中，一棵高度平衡二叉树定义为：
 *
 * 一个二叉树每个节点 的左右两个子树的高度差的绝对值不超过 1 。
 *
 *  
 *
 * 示例 1：
 *
 *
 * 输入：root = [3,9,20,null,null,15,7]
 * 输出：true
 * 示例 2：
 *
 *
 * 输入：root = [1,2,2,3,3,null,null,4,4]
 * 输出：false
 * 示例 3：
 *
 * 输入：root = []
 * 输出：true
 *  
 *
 * 提示：
 *
 * 树中的节点数在范围 [0, 5000] 内
 * -104 <= Node.val <= 104
 *
 * @author chengzhy
 * @date 2022/2/8 13:22
 */
public class P110_BalancedBinaryTree {

    public boolean isBalanced(TreeNode root) {
        return height(root) >= 0;
    }

    private int height(TreeNode treeNode) {
        if (Objects.isNull(treeNode)) {
            return 0;
        }
        int leftHeight = height(treeNode.left);
        int rightHeight = height(treeNode.right);
        if (leftHeight == -1 || rightHeight == -1 || Math.abs(leftHeight - rightHeight) > 1) {
            // 如果左右子树高度为-1，说明不平衡
            return -1;
        }
        return Math.max(leftHeight, rightHeight) + 1;
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
