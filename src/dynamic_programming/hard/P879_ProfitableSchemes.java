package dynamic_programming.hard;

/**
 * 盈利计划
 * <a href="https://leetcode.cn/problems/profitable-schemes/">🔗</a>
 *
 * 集团里有 n 名员工，他们可以完成各种各样的工作创造利润。
 *
 * 第 i 种工作会产生 profit[i] 的利润，它要求 group[i] 名成员共同参与。如果成员参与了其中一项工作，就不能参与另一项工作。
 *
 * 工作的任何至少产生 minProfit 利润的子集称为 盈利计划 。并且工作的成员总数最多为 n 。
 *
 * 有多少种计划可以选择？因为答案很大，所以 返回结果模 10^9 + 7 的值。
 *
 *  
 *
 * 示例 1：
 *
 * 输入：n = 5, minProfit = 3, group = [2,2], profit = [2,3]
 * 输出：2
 * 解释：至少产生 3 的利润，该集团可以完成工作 0 和工作 1 ，或仅完成工作 1 。
 * 总的来说，有两种计划。
 * 示例 2：
 *
 * 输入：n = 10, minProfit = 5, group = [2,3,5], profit = [6,7,8]
 * 输出：7
 * 解释：至少产生 5 的利润，只要完成其中一种工作就行，所以该集团可以完成任何工作。
 * 有 7 种可能的计划：(0)，(1)，(2)，(0,1)，(0,2)，(1,2)，以及 (0,1,2) 。
 *  
 *
 * 提示：
 *
 * 1 <= n <= 100
 * 0 <= minProfit <= 100
 * 1 <= group.length <= 100
 * 1 <= group[i] <= 100
 * profit.length == group.length
 * 0 <= profit[i] <= 100
 *
 * @author chengzhy
 * @date 2022/4/9 10:32
 */
public class P879_ProfitableSchemes {

    public int profitableSchemes(int n, int minProfit, int[] group, int[] profit) {
        int length = profit.length;
        final int MOD = (int)1e9 + 7;
        // dp[i][j][k]数组表示在前i个工作中，拥有j个人且能达到的最小利润为k的计划数
        int[][][] dp = new int[length + 1][n + 1][minProfit + 1];
        // 初始化临界值，没有工作且最小利润为0的情况下计划数都为1
        for (int i = 0; i <= n; i++) {
            dp[0][i][0] = 1;
        }
        for (int i = 1; i <= length; i++) {
            int num = group[i - 1], p = profit[i - 1];
            for (int j = 0; j <= n; j++) {
                for (int k = 0; k <= minProfit; k++) {
                    if (j < num) {
                        // 当前人数小于该工作所需的人数，就不能做这个工作了
                        dp[i][j][k] = dp[i - 1][j][k];
                    } else {
                        // 当前人数大于等于该工作所需的人数，就可以做这个工作了
                        // if (k <= p) {
                            /**
                             * 如果当前工作的利润就已经可以达到所需的最小利润，那么计划数就等于
                             * 不做这个工作 + 在没做该任务之前工作的最小利润为0的基础上 做这个工作 的计划数
                             */
                            // dp[i][j][k] = (dp[i - 1][j][k] + dp[i - 1][j - num][0]) % MOD;
                        // } else {
                            /**
                             * 如果当前工作的利润就未能达到所需的最小利润，那么计划数就等于
                             * 不做这个工作 + 做这个工作 的计划数
                             *
                             * 为什么这里也需要加上不做这个工作的计划数呢？
                             * 这是因为这里和比较最小利润只是针对每一个工作的利润来说的，并不是之前所有工作的利润和
                             * 那么就会有虽然当前工作利润小于所需的最小利润，但是之前几个工作的利润和已经达到了最小利润这种情况
                             * 所以需要加上不做这个工作的计划数
                             */
                            // dp[i][j][k] = (dp[i - 1][j][k] + dp[i - 1][j - num][k - p]) % MOD;
                        // }
                        // 将上面代码简化
                        dp[i][j][k] = (dp[i - 1][j][k] + dp[i - 1][j - num][Math.max(0, k - p)]) % MOD;
                    }
                }
            }
        }
        return dp[length][n][minProfit];
        // 三维dp数组简化成二维dp数组
        /*int[][] dp = new int[n + 1][minProfit + 1];
        // 初始化临界值，没有工作且最小利润为0的情况下计划数都为1
        for (int i = 0; i <= n; i++) {
            dp[i][0] = 1;
        }
        for (int i = 1; i <= length; i++) {
            int num = group[i - 1], p = profit[i - 1];
            for (int j = n; j >= num; j--) {
                // 注意k这里是>=0
                for (int k = minProfit; k >= 0; k--) {
                    dp[j][k] = (dp[j][k] + dp[j - num][Math.max(0, k - p)]) % MOD;
                }
            }
        }
        return dp[n][minProfit];*/
    }

}
