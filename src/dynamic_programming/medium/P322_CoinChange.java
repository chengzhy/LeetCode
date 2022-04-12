package dynamic_programming.medium;

/**
 * 零钱兑换
 * <a href="https://leetcode-cn.com/problems/coin-change/">🔗</a>
 *
 * 给你一个整数数组 coins ，表示不同面额的硬币；以及一个整数 amount ，表示总金额。
 *
 * 计算并返回可以凑成总金额所需的 最少的硬币个数 。如果没有任何一种硬币组合能组成总金额，返回 -1 。
 *
 * 你可以认为每种硬币的数量是无限的。
 *
 *  
 *
 * 示例 1：
 *
 * 输入：coins = [1, 2, 5], amount = 11
 * 输出：3
 * 解释：11 = 5 + 5 + 1
 * 示例 2：
 *
 * 输入：coins = [2], amount = 3
 * 输出：-1
 * 示例 3：
 *
 * 输入：coins = [1], amount = 0
 * 输出：0
 *  
 *
 * 提示：
 *
 * 1 <= coins.length <= 12
 * 1 <= coins[i] <= 231 - 1
 * 0 <= amount <= 104
 *
 * @author chengzhy
 * @date 2022/4/12 11:49
 */
public class P322_CoinChange {

    public int coinChange(int[] coins, int amount) {
        int m = coins.length;
        // dp[i][j]数组表示在前i个硬币中任取硬币，能凑成总金额为j的最少的硬币个数
        int[][] dp = new int[m + 1][amount + 1];
        // 初始化边界值
        for (int i = 1; i <= amount; i++) {
            // 没有硬币的情况，这里不用设成Integer.MAX_VALUE，因为最小价值的硬币为1，最多数量就是amount，这里直接amount + 1即可
            dp[0][i] = amount + 1;
        }
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= amount; j++) {
                if (j < coins[i - 1]) {
                    dp[i][j] = dp[i - 1][j];
                } else {
                    dp[i][j] = Math.min(dp[i - 1][j], dp[i][j - coins[i - 1]] + 1);
                }
            }
        }
        return (dp[m][amount] > amount) ? -1 : dp[m][amount];
        // 二维dp数组优化成一维dp数组
        /*int[] dp = new int[amount + 1];
        for (int i = 1; i <= amount; i++) {
            // 没有硬币的情况
            dp[i] = amount + 1;
        }
        for (int i = 1; i <= m; i++) {
            for (int j = coins[i - 1]; j <= amount; j++) {
                dp[j] = Math.min(dp[j], dp[j - coins[i - 1]] + 1);
            }
        }
        return (dp[amount] > amount) ? -1 : dp[amount];*/
    }

}
