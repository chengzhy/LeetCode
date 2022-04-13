package dynamic_programming.hard;

import java.util.Arrays;

/**
 * 数位成本和为目标值的最大数字
 * <a href="https://leetcode-cn.com/problems/form-largest-integer-with-digits-that-add-up-to-target/">🔗</a>
 *
 * 给你一个整数数组 cost 和一个整数 target 。请你返回满足如下规则可以得到的 最大 整数：
 *
 * 给当前结果添加一个数位（i + 1）的成本为 cost[i] （cost 数组下标从 0 开始）。
 * 总成本必须恰好等于 target 。
 * 添加的数位中没有数字 0 。
 * 由于答案可能会很大，请你以字符串形式返回。
 *
 * 如果按照上述要求无法得到任何整数，请你返回 "0" 。
 *
 *  
 *
 * 示例 1：
 *
 * 输入：cost = [4,3,2,5,6,7,2,5,5], target = 9
 * 输出："7772"
 * 解释：添加数位 '7' 的成本为 2 ，添加数位 '2' 的成本为 3 。所以 "7772" 的代价为 2*3+ 3*1 = 9 。 "977" 也是满足要求的数字，但 "7772" 是较大的数字。
 *  数字     成本
 *   1  ->   4
 *   2  ->   3
 *   3  ->   2
 *   4  ->   5
 *   5  ->   6
 *   6  ->   7
 *   7  ->   2
 *   8  ->   5
 *   9  ->   5
 * 示例 2：
 *
 * 输入：cost = [7,6,5,5,5,6,8,7,8], target = 12
 * 输出："85"
 * 解释：添加数位 '8' 的成本是 7 ，添加数位 '5' 的成本是 5 。"85" 的成本为 7 + 5 = 12 。
 * 示例 3：
 *
 * 输入：cost = [2,4,6,2,4,6,4,4,4], target = 5
 * 输出："0"
 * 解释：总成本是 target 的条件下，无法生成任何整数。
 * 示例 4：
 *
 * 输入：cost = [6,10,15,40,40,40,40,40,40], target = 47
 * 输出："32211"
 *  
 *
 * 提示：
 *
 * cost.length == 9
 * 1 <= cost[i] <= 5000
 * 1 <= target <= 5000
 *
 * @author chengzhy
 * @date 2022/4/13 11:24
 */
public class P1449_FormLargestIntegerWithDigitsThatAddUpToTarget {

    public String largestNumber(int[] cost, int target) {
        // dp[i][j]数组表示从前i个数字中任取，总成本恰好为j的最大整数(注意这里的恰好)
        String[][] dp = new String[10][target + 1];
        for (int i = 0; i < 10; i++) {
            dp[i][0] = "";
        }
        for (int i = 1; i <= target; i++) {
            // "#"表示从前i个数字中任取总成本不等于target的情况。
            dp[0][i] = "#";
        }
        // 先遍历物品，直接将数加到字符串的开头即可
        for (int i = 1; i <= 9; i++) {
            // 再遍历背包
            for (int j = 1; j <= target; j++) {
                if (j < cost[i - 1]) {
                    // 背包容量小于当前物品，则不能放当前物品
                    dp[i][j] = dp[i - 1][j];
                } else {
                    if (dp[i][j - cost[i - 1]].equals("#")) {
                        // 表示能放当前物品，但是由于题目说总成本要恰好等于j，而背包容量为j - cost[i - 1]时没有满足恰好等于
                        dp[i][j] = dp[i - 1][j];
                    } else {
                        dp[i][j] = getMax(dp[i - 1][j], i + dp[i][j - cost[i - 1]]);
                    }
                }
            }
        }
        return dp[9][target].equals("#") ? "0" : dp[9][target];
        // 二维dp数组优化成一维dp数组
        /*String[] dp = new String[target + 1];
        dp[0] = "";
        for (int i = 1; i <= target; i++) {
            dp[i] = "#";
        }
        // 先遍历物品，直接将数加到字符串的开头即可
        for (int i = 1; i <= 9; i++) {
            // 再遍历背包
            for (int j = cost[i - 1]; j <= target; j++) {
                if (!dp[j - cost[i - 1]].equals("#")) {
                    dp[j] = getMax(dp[j], i + dp[j - cost[i - 1]]);
                }
            }
        }
        return dp[target].equals("#") ? "0" : dp[target];*/
        // 官方题解的dp[]数组含义是使用前i个数位且花费总成本恰好为j时的最大位数
        /*int[] dp = new int[target + 1];
        // 若花费总成本无法为j，则规定其为Integer.MIN_VALUE。
        Arrays.fill(dp, Integer.MIN_VALUE);
        dp[0] = 0;
        // 先遍历物品
        for (int c : cost) {
            // 再遍历背包
            for (int j = c; j <= target; j++) {
                dp[j] = Math.max(dp[j], dp[j - c] + 1);
            }
        }
        if (dp[target] < 0) {
            return "0";
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 9, j = target; i >= 1; i--) {
            // 在状态倒退时，直接根据dp[j]与dp[j − cost[i - 1]] + 1是否相等来判断，若二者相等则说明是从dp[j − cost[i - 1]]转移而来，即选择了第i个数位
            for (int c = cost[i - 1]; j >= c && dp[j] == dp[j - c] + 1; j -= c) {
                sb.append(i);
            }
        }
        return sb.toString();*/
    }

    private String getMax(String a, String b) {
        if (a.length() > b.length()) {
            return a;
        } else if (a.length() < b.length()) {
            return b;
        } else {
            return (a.compareTo(b) > 0) ? a : b;
        }
    }

}
