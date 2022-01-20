package depth_first_search.medium;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Objects;

/**
 * 二叉树的最近公共祖先
 *
 * 给定一个二叉树, 找到该树中两个指定节点的最近公共祖先。
 *
 * 百度百科中最近公共祖先的定义为：“对于有根树 T 的两个节点 p、q，最近公共祖先表示为一个节点 x，满足 x 是 p、q 的祖先且 x 的深度尽可能大（一个节点也可以是它自己的祖先）。”
 *
 *  
 *
 * 示例 1：
 *
 *
 * 输入：root = [3,5,1,6,2,0,8,null,null,7,4], p = 5, q = 1
 * 输出：3
 * 解释：节点 5 和节点 1 的最近公共祖先是节点 3 。
 * 示例 2：
 *
 *
 * 输入：root = [3,5,1,6,2,0,8,null,null,7,4], p = 5, q = 4
 * 输出：5
 * 解释：节点 5 和节点 4 的最近公共祖先是节点 5 。因为根据定义最近公共祖先节点可以为节点本身。
 * 示例 3：
 *
 * 输入：root = [1,2], p = 1, q = 2
 * 输出：1
 *  
 *
 * 提示：
 *
 * 树中节点数目在范围 [2, 105] 内。
 * -109 <= Node.val <= 109
 * 所有 Node.val 互不相同 。
 * p != q
 * p 和 q 均存在于给定的二叉树中。
 *
 * @author chengzhy
 * @date 2022/1/18 19:26
 */
public class P236_LowestCommonAncestorOfABinaryTree {

    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        /*// 设置两个栈分别存p,q的祖先节点
        Deque<TreeNode> pParentStack = new ArrayDeque<>();
        Deque<TreeNode> qParentStack = new ArrayDeque<>();
        dfs(root, p, pParentStack);
        dfs(root, q, qParentStack);
        // 找到深度最深的公共祖先节点
        while (!pParentStack.isEmpty() && !qParentStack.isEmpty()) {
            if (pParentStack.peek().val == qParentStack.peek().val) {
                root = pParentStack.pop();
                qParentStack.pop();
            } else {
                break;
            }
        }
        return root;*/

        /**
         * 以下为更简洁的做法
         */
        if (Objects.isNull(root) || p == root || q == root) {
            return root;
        }
        TreeNode left = lowestCommonAncestor(root.left, p, q);
        TreeNode right = lowestCommonAncestor(root.right, p, q);
        // 如果这两个节点为左右子节点，那么此root即为最近公共祖先
        if (Objects.nonNull(left) && Objects.nonNull(right)) {
            return root;
        }
        // 如果这两个子节点都在树的一侧，返回不为空的那侧的结果
        return Objects.nonNull(left) ? left : right;
    }

    /**
     * 记录节点的父节点
     *
     * @param root 树根节点
     * @param t 要寻找的节点
     * @param stack 存放要寻找的节点的父节点
     * @return 是否找到
     */
    private boolean dfs(TreeNode root, TreeNode t, Deque<TreeNode> stack) {
        if (Objects.isNull(root)) {
            return false;
        }
        if (root.val == t.val) {
            stack.push(t);
            return true;
        }
        if (dfs(root.left, t, stack) || dfs(root.right, t, stack)) {
            stack.push(root);
            return true;
        } else {
            return false;
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
