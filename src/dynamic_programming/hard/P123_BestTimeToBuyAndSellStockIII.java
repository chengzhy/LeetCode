package dynamic_programming.hard;

/**
 * 买卖股票的最佳时机 III
 * <a href="https://leetcode-cn.com/problems/best-time-to-buy-and-sell-stock-iii/">🔗</a>
 *
 * 给定一个数组，它的第 i 个元素是一支给定的股票在第 i 天的价格。
 *
 * 设计一个算法来计算你所能获取的最大利润。你最多可以完成 两笔 交易。
 *
 * 注意：你不能同时参与多笔交易（你必须在再次购买前出售掉之前的股票）。
 *
 *  
 *
 * 示例 1:
 *
 * 输入：prices = [3,3,5,0,0,3,1,4]
 * 输出：6
 * 解释：在第 4 天（股票价格 = 0）的时候买入，在第 6 天（股票价格 = 3）的时候卖出，这笔交易所能获得利润 = 3-0 = 3 。
 *      随后，在第 7 天（股票价格 = 1）的时候买入，在第 8 天 （股票价格 = 4）的时候卖出，这笔交易所能获得利润 = 4-1 = 3 。
 * 示例 2：
 *
 * 输入：prices = [1,2,3,4,5]
 * 输出：4
 * 解释：在第 1 天（股票价格 = 1）的时候买入，在第 5 天 （股票价格 = 5）的时候卖出, 这笔交易所能获得利润 = 5-1 = 4 。  
 *      注意你不能在第 1 天和第 2 天接连购买股票，之后再将它们卖出。  
 *      因为这样属于同时参与了多笔交易，你必须在再次购买前出售掉之前的股票。
 * 示例 3：
 *
 * 输入：prices = [7,6,4,3,1]
 * 输出：0
 * 解释：在这个情况下, 没有交易完成, 所以最大利润为 0。
 * 示例 4：
 *
 * 输入：prices = [1]
 * 输出：0
 *  
 *
 * 提示：
 *
 * 1 <= prices.length <= 105
 * 0 <= prices[i] <= 105
 *
 * @author chengzhy
 * @date 2022/2/7 15:07
 */
public class P123_BestTimeToBuyAndSellStockIII {

    public int maxProfit(int[] prices) {
        /**
         * dp数组表示第i天五种状态下所获得的最大利润
         * 5种状态：0-未买入 1-第一次买入 2-第一次卖出 3-第二次买入 4-第二次卖出
         * 对于0-未买入状态，最大利润始终为0
         * 对于1-第一次买入状态，可能是前一天第一次买入或者前一天未买入今天刚买入
         * 即dp[i][1] = Math.max(dp[i - 1][1], -prices[i])
         * 对于2-第一次卖出状态，可能是前一天第一次卖出或者前一天第一次买入今天刚卖出
         * 即Math.max(dp[i - 1][2], dp[i - 1][1] + prices[i])
         * 对于3-第二次买入状态，可能是前一天第二次买入或者前一天第一次卖出今天刚买入
         * 即dp[i][3] = Math.max(dp[i - 1][3], dp[i - 1][2] - prices[i])
         * 对于4-第二次卖出状态，可能是前一天第二次卖出或者前一天第二次买入今天刚卖出
         * 即dp[i][3] = Math.max(dp[i - 1][3], dp[i - 1][2] - prices[i])
         */
        /*int[][] dp = new int[prices.length][5];
        dp[0][1] = -prices[0];
        dp[0][3] = -prices[0];
        for (int i = 1; i < prices.length; i++) {
            dp[i][1] = Math.max(dp[i - 1][1], -prices[i]);
            dp[i][2] = Math.max(dp[i - 1][2], dp[i - 1][1] + prices[i]);
            dp[i][3] = Math.max(dp[i - 1][3], dp[i - 1][2] - prices[i]);
            dp[i][4] = Math.max(dp[i - 1][4], dp[i - 1][3] + prices[i]);
        }
        return dp[prices.length - 1][4];*/

        // 空间优化，不用二维数组了，反正用的是前一天的数据，刚好可以拿来利用
        int[] dp = new int[5];
        dp[1] = -prices[0];
        dp[3] = -prices[0];
        for (int i = 0; i < prices.length; i++) {
            dp[1] = Math.max(dp[1], -prices[i]);
            dp[2] = Math.max(dp[2], dp[1] + prices[i]);
            dp[3] = Math.max(dp[3], dp[2] - prices[i]);
            dp[4] = Math.max(dp[4], dp[3] + prices[i]);
        }
        return dp[4];
    }

}
