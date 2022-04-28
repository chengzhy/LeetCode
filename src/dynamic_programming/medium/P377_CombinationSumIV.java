package dynamic_programming.medium;

/**
 * 组合总和 Ⅳ
 * <a href="https://leetcode.cn/problems/combination-sum-iv/">🔗</a>
 *
 * 给你一个由 不同 整数组成的数组 nums ，和一个目标整数 target 。请你从 nums 中找出并返回总和为 target 的元素组合的个数。
 *
 * 题目数据保证答案符合 32 位整数范围。
 *
 *  
 *
 * 示例 1：
 *
 * 输入：nums = [1,2,3], target = 4
 * 输出：7
 * 解释：
 * 所有可能的组合为：
 * (1, 1, 1, 1)
 * (1, 1, 2)
 * (1, 2, 1)
 * (1, 3)
 * (2, 1, 1)
 * (2, 2)
 * (3, 1)
 * 请注意，顺序不同的序列被视作不同的组合。
 * 示例 2：
 *
 * 输入：nums = [9], target = 3
 * 输出：0
 *  
 *
 * 提示：
 *
 * 1 <= nums.length <= 200
 * 1 <= nums[i] <= 1000
 * nums 中的所有元素 互不相同
 * 1 <= target <= 1000
 *  
 *
 * 进阶：如果给定的数组中含有负数会发生什么？问题会产生何种变化？如果允许负数出现，需要向题目中添加哪些限制条件？
 *
 * @author chengzhy
 * @date 2022/4/12 9:53
 */
public class P377_CombinationSumIV {

    public int combinationSum4(int[] nums, int target) {
        int m = nums.length;
        // dp[i][j]数组表示在前i个nums中任取数，能凑成总和为j的元素组合个数
        int[][] dp = new int[m + 1][target + 1];
        // 初始化边界值
        for (int i = 0; i <= m; i++) {
            dp[i][0] = 1;
        }
        // 注意这里的遍历顺序，要先遍历背包，再遍历物品，这样就是求排列数(顺序不同)
        for (int j = 1; j <= target; j++) {
            for (int i = 1; i <= m; i++) {
                // 从第一个数开始到第i个数中任取
                for (int k = 1; k <= i; k++) {
                    if (j >= nums[k - 1]) {
                        // 表示最后选取的数为nums[k - 1]的组合数来进行累加
                        dp[i][j] += dp[i][j - nums[k - 1]];
                    }
                }
            }
        }
        /*dp[0][0] = 1;
        for (int i = 1; i <= m; i++) {
            for (int j = 0; j <= target; j++) {
                if (j < nums[i - 1]) {
                    dp[i][j] = dp[i - 1][j];
                } else {
                    *//**
                     * 注意题目说顺序不同的序列被视作不同的组合
                     * 所以对dp[i][j]而言，组合中的最后一个数字可以选择nums中的任意数值
                     * 最后一个数选nums[0], 组合数为dp[i][j - nums[0]]
                     * 最后一个数选nums[1], 组合数为dp[i][j - nums[1]]
                     * ...
                     * dp[i][j] = 以上组合数相加(注意j >= nums[i])
                     *//*
                    for (int k = 1; k <= i; k++) {
                        if (j >= nums[k - 1]) {
                            dp[i][j] += dp[i][j - nums[k - 1]];
                        }
                    }
                }
            }
        }*/
        return dp[m][target];
        // 二维dp数组优化成一维dp数组
        /*int[] dp = new int[target + 1];
        dp[0] = 1;
        for (int j = 1; j <= target; j++) {
            // 这里实际上省略了上面三层循环的第二层循环
            for (int i = 1; i <= m; i++) {
                if (j >= nums[i - 1]) {
                    dp[j] += dp[j - nums[i - 1]];
                }
            }
        }
        return dp[target];*/
    }

}
