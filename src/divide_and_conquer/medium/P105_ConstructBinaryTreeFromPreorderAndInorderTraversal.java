package divide_and_conquer.medium;

import java.util.HashMap;
import java.util.Map;

/**
 * 从前序与中序遍历序列构造二叉树
 * <a href="https://leetcode.cn/problems/construct-binary-tree-from-preorder-and-inorder-traversal/">🔗</a>
 *
 * 给定两个整数数组 preorder 和 inorder ，其中 preorder 是二叉树的先序遍历， inorder 是同一棵树的中序遍历，请构造二叉树并返回其根节点。
 *
 *  
 *
 * 示例 1:
 *
 *
 * 输入: preorder = [3,9,20,15,7], inorder = [9,3,15,20,7]
 * 输出: [3,9,20,null,null,15,7]
 * 示例 2:
 *
 * 输入: preorder = [-1], inorder = [-1]
 * 输出: [-1]
 *  
 *
 * 提示:
 *
 * 1 <= preorder.length <= 3000
 * inorder.length == preorder.length
 * -3000 <= preorder[i], inorder[i] <= 3000
 * preorder 和 inorder 均 无重复 元素
 * inorder 均出现在 preorder
 * preorder 保证 为二叉树的前序遍历序列
 * inorder 保证 为二叉树的中序遍历序列
 *
 * @author chengzhy
 * @date 2022/2/11 9:57
 */
public class P105_ConstructBinaryTreeFromPreorderAndInorderTraversal {
    private Map<Integer, Integer> map = new HashMap<>();

    public TreeNode buildTree(int[] preorder, int[] inorder) {
        for (int i = 0; i < inorder.length; i++) {
            map.put(inorder[i], i);
        }
        return buildTree(preorder, inorder, 0, preorder.length - 1,
                0, inorder.length - 1);
    }

    /**
     * 构造树
     *
     * @param preorder 前序遍历
     * @param inorder 中序遍历
     * @param preorderLeft 前序遍历左边界
     * @param preorderRight 前序遍历右边界
     * @param inorderLeft 中序遍历左边界
     * @param inorderRight 中序遍历右边界
     * @return 树节点
     */
    private TreeNode buildTree(int[] preorder, int[] inorder, int preorderLeft, int preorderRight,
                               int inorderLeft, int inorderRight) {
        if (preorderLeft > preorderRight) {
            return null;
        }
        // perorderRootIndex为前序遍历根节点位置，inorderRootIndex为中序遍历根节点位置，leftTreeSize可以从中序遍历的根节点位置-中序遍历左边界得到
        int perorderRootIndex = preorderLeft, inorderRootIndex = map.get(preorder[perorderRootIndex]),
                leftTreeSize = inorderRootIndex - inorderLeft;
        // 根节点一定是前序遍历的左边界
        TreeNode root = new TreeNode(preorder[perorderRootIndex]);
        // 构造左子树，从根节点的前序和中序左子树范围内构造
        root.left = buildTree(preorder, inorder, perorderRootIndex + 1, perorderRootIndex + leftTreeSize,
                inorderLeft, inorderRootIndex - 1);
        // 构造右子树，从根节点的前序和中序右子树范围内构造
        root.right = buildTree(preorder, inorder, perorderRootIndex + leftTreeSize + 1, preorderRight,
                inorderRootIndex + 1, inorderRight);
        return root;
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
