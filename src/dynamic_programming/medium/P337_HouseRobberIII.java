package dynamic_programming.medium;

import java.util.Objects;

/**
 * 打家劫舍 III
 * <a href="https://leetcode-cn.com/problems/house-robber-iii/">🔗</a>
 *
 * 小偷又发现了一个新的可行窃的地区。这个地区只有一个入口，我们称之为 root 。
 *
 * 除了 root 之外，每栋房子有且只有一个“父“房子与之相连。一番侦察之后，聪明的小偷意识到“这个地方的所有房屋的排列类似于一棵二叉树”。 如果 两个直接相连的房子在同一天晚上被打劫 ，房屋将自动报警。
 *
 * 给定二叉树的 root 。返回 在不触动警报的情况下 ，小偷能够盗取的最高金额 。
 *
 *  
 *
 * 示例 1:
 *
 *
 *
 * 输入: root = [3,2,3,null,3,null,1]
 * 输出: 7
 * 解释: 小偷一晚能够盗取的最高金额 3 + 3 + 1 = 7
 * 示例 2:
 *
 *
 *
 * 输入: root = [3,4,5,1,3,null,1]
 * 输出: 9
 * 解释: 小偷一晚能够盗取的最高金额 4 + 5 = 9
 *  
 *
 * 提示：
 *
 * 树的节点数在 [1, 104] 范围内
 * 0 <= Node.val <= 104
 *
 * @author chengzhy
 * @date 2022/4/6 12:47
 */
public class P337_HouseRobberIII {

    public int rob(TreeNode root) {
        int[] dp = dfs(root);
        return Math.max(dp[0], dp[1]);
    }

    private int[] dfs(TreeNode treeNode) {
        // dp[2]数组:dp[0]表示偷该节点所能获得的最高金额，dp[1]表示不偷节点所能获得的最高金额
        int[] dp = new int[2];
        if (Objects.nonNull(treeNode)) {
            // 左子节点偷与不偷的金额
            int[] left = dfs(treeNode.left);
            // 右子节点偷与不偷的金额
            int[] right = dfs(treeNode.right);
            // 若偷当前节点，所能获得的最高金额为 偷当前节点的金额 + 不偷左子节点所能获得的最高金额 + 不偷右子节点所能获得的最高金额
            dp[0] = treeNode.val + left[1] + right[1];
            // 若不偷当前节点，所能获得的最高金额为 偷或不偷左子节点所能获得的最高金额 + 偷或不偷右子节点所能获得的最高金额
            dp[1] = Math.max(left[0], left[1]) + Math.max(right[0], right[1]);
        }
        return dp;
    }

    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode() {
        }
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
