package dynamic_programming.hard;

/**
 * 买卖股票的最佳时机 IV
 * <a href="https://leetcode-cn.com/problems/best-time-to-buy-and-sell-stock-iv/">🔗</a>
 *
 * 给定一个整数数组 prices ，它的第 i 个元素 prices[i] 是一支给定的股票在第 i 天的价格。
 *
 * 设计一个算法来计算你所能获取的最大利润。你最多可以完成 k 笔交易。
 *
 * 注意：你不能同时参与多笔交易（你必须在再次购买前出售掉之前的股票）。
 *
 *  
 *
 * 示例 1：
 *
 * 输入：k = 2, prices = [2,4,1]
 * 输出：2
 * 解释：在第 1 天 (股票价格 = 2) 的时候买入，在第 2 天 (股票价格 = 4) 的时候卖出，这笔交易所能获得利润 = 4-2 = 2 。
 * 示例 2：
 *
 * 输入：k = 2, prices = [3,2,6,5,0,3]
 * 输出：7
 * 解释：在第 2 天 (股票价格 = 2) 的时候买入，在第 3 天 (股票价格 = 6) 的时候卖出, 这笔交易所能获得利润 = 6-2 = 4 。
 *      随后，在第 5 天 (股票价格 = 0) 的时候买入，在第 6 天 (股票价格 = 3) 的时候卖出, 这笔交易所能获得利润 = 3-0 = 3 。
 *  
 *
 * 提示：
 *
 * 0 <= k <= 100
 * 0 <= prices.length <= 1000
 * 0 <= prices[i] <= 1000
 *
 * @author chengzhy
 * @date 2022/2/7 15:41
 */
public class P188_BestTimeToBuyAndSellStockIV {

    public int maxProfit(int k, int[] prices) {
        /**
         * 思路如同题123 买卖股票的最佳时机 III
         * 只不过变成了k次交易，是对题123的抽象
         * k次交易就对应了有1+2*k种状态，1为未买入，2*k代表第k次买入和卖出
         * 然后直接沿着题123的思路进行状态转移方程的赋值即可
         */
        if (k < 1 || prices.length == 0) {
            return 0;
        }
        int[] dp = new int[(k << 1) + 1];
        for (int i = 1; i < dp.length; i += 2) {
            dp[i] = -prices[0];
        }
        for (int i = 0; i < prices.length; i++) {
            for (int j = 1; j < dp.length; j++) {
                dp[j] = Math.max(dp[j], ((j & 1) == 1) ? dp[j - 1] - prices[i] : dp[j - 1] + prices[i]);
            }
        }
        return dp[dp.length - 1];
    }

}
