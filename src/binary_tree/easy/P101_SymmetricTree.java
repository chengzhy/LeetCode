package binary_tree.easy;

import java.util.Objects;

/**
 * 对称二叉树
 *
 * 给你一个二叉树的根节点 root ， 检查它是否轴对称。
 *
 *  
 *
 * 示例 1：
 *
 *
 * 输入：root = [1,2,2,3,4,4,3]
 * 输出：true
 * 示例 2：
 *
 *
 * 输入：root = [1,2,2,null,3,null,3]
 * 输出：false
 *  
 *
 * 提示：
 *
 * 树中节点数目在范围 [1, 1000] 内
 * -100 <= Node.val <= 100
 *  
 *
 * 进阶：你可以运用递归和迭代两种方法解决这个问题吗？
 *
 * @author chengzhy
 * @date 2022/1/24 20:24
 */
public class P101_SymmetricTree {

    public boolean isSymmetric(TreeNode root) {
        return dfs(root.left, root.right);
    }

    /**
     * 思路如下：
     * 1.怎么判断一棵树是不是对称二叉树？ 答案：如果所给根节点，为空，那么是对称。如果不为空的话，当他的左子树与右子树对称时，他对称
     * 2.那么怎么知道左子树与右子树对不对称呢？在这我直接叫为左树和右树 答案：如果左树的左孩子与右树的右孩子对称，左树的右孩子与右树的左孩子对称，那么这个左树和右树就对称。
     * 仔细读这句话，是不是有点绕？怎么感觉有一个功能A我想实现，但我去实现A的时候又要用到A实现后的功能呢？
     * 当你思考到这里的时候，递归点已经出现了： 递归点：我在尝试判断左树与右树对称的条件时，发现其跟两树的孩子的对称情况有关系。
     * 想到这里，你不必有太多疑问，上手去按思路写代码，函数A（左树，右树）功能是返回是否对称
     * def 函数A（左树，右树）： 左树节点值等于右树节点值且函数A（左树的左子树，右树的右子树），函数A（左树的右子树，右树的左子树）均为真才返回真
     *
     * @param left 左节点
     * @param right 右节点
     * @return 是否对称
     */
    private boolean dfs(TreeNode left, TreeNode right) {
        if (Objects.isNull(left) && Objects.isNull(right)) {
            return true;
        }
        if (Objects.isNull(left) || Objects.isNull(right) || left.val != right.val) {
            return false;
        }
        return dfs(left.left, right.right) && dfs(left.right, right.left);
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
