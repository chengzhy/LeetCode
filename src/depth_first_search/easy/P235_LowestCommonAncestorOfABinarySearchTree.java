package depth_first_search.easy;

/**
 * 二叉搜索树的最近公共祖先
 * <a href="https://leetcode-cn.com/problems/lowest-common-ancestor-of-a-binary-search-tree/">🔗</a>
 *
 * 给定一个二叉搜索树, 找到该树中两个指定节点的最近公共祖先。
 *
 * 百度百科中最近公共祖先的定义为：“对于有根树 T 的两个结点 p、q，最近公共祖先表示为一个结点 x，满足 x 是 p、q 的祖先且 x 的深度尽可能大（一个节点也可以是它自己的祖先）。”
 *
 * 例如，给定如下二叉搜索树:  root = [6,2,8,0,4,7,9,null,null,3,5]
 *
 *
 *
 *  
 *
 * 示例 1:
 *
 * 输入: root = [6,2,8,0,4,7,9,null,null,3,5], p = 2, q = 8
 * 输出: 6
 * 解释: 节点 2 和节点 8 的最近公共祖先是 6。
 * 示例 2:
 *
 * 输入: root = [6,2,8,0,4,7,9,null,null,3,5], p = 2, q = 4
 * 输出: 2
 * 解释: 节点 2 和节点 4 的最近公共祖先是 2, 因为根据定义最近公共祖先节点可以为节点本身。
 *  
 *
 * 说明:
 *
 * 所有节点的值都是唯一的。
 * p、q 为不同节点且均存在于给定的二叉搜索树中。
 *
 * @author chengzhy
 * @date 2022/1/20 11:44
 */
public class P235_LowestCommonAncestorOfABinarySearchTree {

    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        /**
         * 二叉搜索树(BST)特性：它或者是一棵空树，或者是具有下列性质的二叉树：
         * 若它的左子树不空，则左子树上所有结点的值均小于它的根结点的值；
         * 若它的右子树不空，则右子树上所有结点的值均大于它的根结点的值；
         * 它的左、右子树也分别为二叉排序树。
         */
        // 利用二叉搜索树的特性，如果p,q两个节点分别在root左右两侧，则最近公共祖先节点就是root
        // 如果在同一侧，则若同在左侧，寻找root的左子树，若同在右侧，寻找root的右子树
        if ((p.val - root.val) * (q.val - root.val) <= 0) {
            return root;
        } else if (Math.max(p.val, q.val) < root.val) {
            return lowestCommonAncestor(root.left, p, q);
        } else {
            return lowestCommonAncestor(root.right, p, q);
        }
    }

    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int val) {
            this.val = val;
        }
    }

}
