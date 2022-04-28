package dynamic_programming.medium;

/**
 * 完全平方数
 * <a href="https://leetcode.cn/problems/perfect-squares/">🔗</a>
 *
 * 给你一个整数 n ，返回 和为 n 的完全平方数的最少数量 。
 *
 * 完全平方数 是一个整数，其值等于另一个整数的平方；换句话说，其值等于一个整数自乘的积。例如，1、4、9 和 16 都是完全平方数，而 3 和 11 不是。
 *
 *  
 *
 * 示例 1：
 *
 * 输入：n = 12
 * 输出：3
 * 解释：12 = 4 + 4 + 4
 * 示例 2：
 *
 * 输入：n = 13
 * 输出：2
 * 解释：13 = 4 + 9
 *  
 * 提示：
 *
 * 1 <= n <= 104
 *
 * @author chengzhy
 * @date 2022/4/13 9:36
 */
public class P279_PerfectSquares {

    public int numSquares(int n) {
        int m = (int) Math.sqrt(n);
        // dp[i][j]数组表示在前i个数中任取数，其平方和等于j的最少数量
        int[][] dp = new int[m + 1][n + 1];
        // 初始化边界值，注意dp[i][0]初始化为0
        for (int i = 1; i <= n; i++) {
            dp[0][i] = n + 1;
        }
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if (j < i * i) {
                    dp[i][j] = dp[i - 1][j];
                } else {
                    dp[i][j] = Math.min(dp[i - 1][j], dp[i][j - i * i] + 1);
                }
            }
        }
        return dp[m][n];
        // 二维dp数组优化成一维dp数组
        /*int[] dp = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            dp[i] = n + 1;
        }
        for (int i = 1; i <= m; i++) {
            for (int j = i * i; j <= n; j++) {
                dp[j] = Math.min(dp[j], dp[j - i * i] + 1);
            }
        }
        return dp[n];*/
    }

}
