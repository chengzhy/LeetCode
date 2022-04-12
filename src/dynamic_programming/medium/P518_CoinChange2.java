package dynamic_programming.medium;

/**
 * 零钱兑换 II
 * <a href="https://leetcode-cn.com/problems/coin-change-2/">🔗</a>
 *
 * 给你一个整数数组 coins 表示不同面额的硬币，另给一个整数 amount 表示总金额。
 *
 * 请你计算并返回可以凑成总金额的硬币组合数。如果任何硬币组合都无法凑出总金额，返回 0 。
 *
 * 假设每一种面额的硬币有无限个。 
 *
 * 题目数据保证结果符合 32 位带符号整数。
 *
 *  
 *
 * 示例 1：
 *
 * 输入：amount = 5, coins = [1, 2, 5]
 * 输出：4
 * 解释：有四种方式可以凑成总金额：
 * 5=5
 * 5=2+2+1
 * 5=2+1+1+1
 * 5=1+1+1+1+1
 * 示例 2：
 *
 * 输入：amount = 3, coins = [2]
 * 输出：0
 * 解释：只用面额 2 的硬币不能凑成总金额 3 。
 * 示例 3：
 *
 * 输入：amount = 10, coins = [10]
 * 输出：1
 *  
 *
 * 提示：
 *
 * 1 <= coins.length <= 300
 * 1 <= coins[i] <= 5000
 * coins 中的所有值 互不相同
 * 0 <= amount <= 5000
 *
 * @author chengzhy
 * @date 2022/4/12 9:30
 */
public class P518_CoinChange2 {

    public int change(int amount, int[] coins) {
        int m = coins.length;
        // dp[i][j]数组表示在前i个coins中任取硬币，能凑成总金额为j的组合数
        int[][] dp = new int[m + 1][amount + 1];
        // 没有任何硬币且总金额为0的方案数是1
        dp[0][0] = 1;
        for (int i = 1; i <= m; i++) {
            for (int j = 0; j <= amount; j++) {
                if (j < coins[i - 1]) {
                    // 当前背包所需要的金额j小于硬币的面值，就不能选这个硬币
                    dp[i][j] = dp[i - 1][j];
                } else {
                    // 组合数 = 不选这个硬币的组合数 + 选这个硬币的组合数(因为硬币可以重复选，所以是dp[i][j - coins[i - 1]])
                    dp[i][j] = dp[i - 1][j] + dp[i][j - coins[i - 1]];
                }
            }
        }
        return dp[m][amount];
        // 二维dp数组优化成一维dp数组
        /*int[] dp = new int[amount + 1];
        dp[0] = 1;
        for (int i = 1; i <= m; i++) {
            for (int j = coins[i - 1]; j <= amount; j++) {
                dp[j] += dp[j - coins[i - 1]];
            }
        }
        return dp[amount];*/
    }

}
