package dynamic_programming.medium;

/**
 * 最佳买卖股票时机含冷冻期
 * <a href="https://leetcode-cn.com/problems/best-time-to-buy-and-sell-stock-with-cooldown/">🔗</a>
 *
 * 给定一个整数数组prices，其中第  prices[i] 表示第 i 天的股票价格 。​
 *
 * 设计一个算法计算出最大利润。在满足以下约束条件下，你可以尽可能地完成更多的交易（多次买卖一支股票）:
 *
 * 卖出股票后，你无法在第二天买入股票 (即冷冻期为 1 天)。
 * 注意：你不能同时参与多笔交易（你必须在再次购买前出售掉之前的股票）。
 *
 *  
 *
 * 示例 1:
 *
 * 输入: prices = [1,2,3,0,2]
 * 输出: 3
 * 解释: 对应的交易状态为: [买入, 卖出, 冷冻期, 买入, 卖出]
 * 示例 2:
 *
 * 输入: prices = [1]
 * 输出: 0
 *  
 *
 * 提示：
 *
 * 1 <= prices.length <= 5000
 * 0 <= prices[i] <= 1000
 *
 * @author chengzhy
 * @date 2022/2/12 14:17
 */
public class P309_BestTimeToBuyAndSellStockWithCooldown {

    public int maxProfit(int[] prices) {
        /**
         * dp[i][j]表示第i天结束后所能获得的最大收益，状态有三种：0-持有股票，1-未持有股票且在冷冻期，2-未持有股票且不在冷冻期
         * 注意：如果第i天结束之后处于冷冻期，那么第i+1天无法买入股票。
         */
        int[][] dp = new int[prices.length][3];
        dp[0][0] = -prices[0];
        for (int i = 1; i < prices.length; i++) {
            // 第i天结束后持有股票所能获得的最大收益为 前一天持有股票最大收益或者前一天未持有股票且不在冷冻期而后今天刚买入
            dp[i][0] = Math.max(dp[i - 1][0], dp[i - 1][2] - prices[i]);
            // 第i天结束后未持有股票且在冷冻期所能获得的最大收益为 前一天持有股票今天刚卖出(刚卖出后今天结束就会处于冷冻期)
            dp[i][1] = dp[i - 1][0] + prices[i];
            // 第i天结束后未持有股票且不在冷冻期所能获得的最大收益为 前一天结束后未持有股票且不在冷冻期或前一天结束后未持有股票且在冷冻期
            dp[i][2] = Math.max(dp[i - 1][2], dp[i - 1][1]);
        }
        return Math.max(dp[prices.length - 1][1], dp[prices.length - 1][2]);
    }

}
