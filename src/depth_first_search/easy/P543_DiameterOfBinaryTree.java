package depth_first_search.easy;

import java.util.Objects;

/**
 * 二叉树的直径
 * <a href="https://leetcode.cn/problems/diameter-of-binary-tree/">🔗</a>
 *
 * 给定一棵二叉树，你需要计算它的直径长度。一棵二叉树的直径长度是任意两个结点路径长度中的最大值。这条路径可能穿过也可能不穿过根结点。
 *
 *  
 *
 * 示例 :
 * 给定二叉树
 *
 *           1
 *          / \
 *         2   3
 *        / \
 *       4   5
 * 返回 3, 它的长度是路径 [4,2,1,3] 或者 [5,2,1,3]。
 *
 *  
 *
 * 注意：两结点之间的路径长度是以它们之间边的数目表示。
 *
 * @author chengzhy
 * @date 2022/2/1 15:44
 */
public class P543_DiameterOfBinaryTree {

    private int maxDiameter = 0;

    public int diameterOfBinaryTree(TreeNode root) {
        /**
         * 二叉树中任意两个结点路径长度中的最大值一定为左子树最深节点到右子树最深节点
         */
        maxDepth(root);
        return maxDiameter;
    }

    private int maxDepth(TreeNode treeNode) {
        if (Objects.isNull(treeNode)) {
            return 0;
        }
        int leftDepth = maxDepth(treeNode.left), rightDepth = maxDepth(treeNode.right);
        maxDiameter = Math.max(maxDiameter, leftDepth + rightDepth);
        // 返回父节点到某侧叶子节点的最深深度
        return Math.max(leftDepth, rightDepth) + 1;
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
