package depth_first_search.medium;

import java.util.Objects;

/**
 * 验证二叉搜索树
 * <a href="https://leetcode-cn.com/problems/validate-binary-search-tree/">🔗</a>
 *
 * 给你一个二叉树的根节点 root ，判断其是否是一个有效的二叉搜索树。
 *
 * 有效 二叉搜索树定义如下：
 *
 * 节点的左子树只包含 小于 当前节点的数。
 * 节点的右子树只包含 大于 当前节点的数。
 * 所有左子树和右子树自身必须也是二叉搜索树。
 *  
 *
 * 示例 1：
 *
 *
 * 输入：root = [2,1,3]
 * 输出：true
 * 示例 2：
 *
 *
 * 输入：root = [5,1,4,null,null,3,6]
 * 输出：false
 * 解释：根节点的值是 5 ，但是右子节点的值是 4 。
 *  
 *
 * 提示：
 *
 * 树中节点数目范围在[1, 104] 内
 * -231 <= Node.val <= 231 - 1
 *
 * @author chengzhy
 * @date 2022/1/26 17:16
 */
public class P98_ValidateBinarySearchTree {

    public boolean isValidBST(TreeNode root) {
        return dfs(root, Long.MIN_VALUE, Long.MAX_VALUE);
    }

    /**
     * 核心思想 左子树中的最大值 < treeNode.val < 右子树中的最小值
     *
     * @param treeNode 树节点
     * @param leftMax 左子树中的最大值
     * @param rightMin 右子树中的最小值
     * @return 是否为二叉搜索树
     */
    private boolean dfs(TreeNode treeNode, long leftMax, long rightMin) {
        if (Objects.isNull(treeNode)) {
            return true;
        }
        if (treeNode.val <= leftMax || treeNode.val >= rightMin) {
            return false;
        }
        /**
         * 核心步骤：左子树中的最大值(leftMax) < treeNode.val < 右子树中的最小值(rightMin)
         * 当递归进左子树时，上界rightMin应改为treeNode.val，因为左子树里所有节点的值均小于它的根节点的值
         * 同理当递归进右子树时，下界leftMax应改为treeNode.val，因为右子树里所有节点的值均大于它的根节点的值
         */
        return dfs(treeNode.left, leftMax, treeNode.val) && dfs(treeNode.right, treeNode.val, rightMin);
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
