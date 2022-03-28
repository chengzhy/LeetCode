package dynamic_programming.easy;

import java.util.Arrays;

/**
 * 最长连续递增序列
 *
 * 给定一个未经排序的整数数组，找到最长且 连续递增的子序列，并返回该序列的长度。
 *
 * 连续递增的子序列 可以由两个下标 l 和 r（l < r）确定，如果对于每个 l <= i < r，都有 nums[i] < nums[i + 1] ，那么子序列 [nums[l], nums[l + 1], ..., nums[r - 1], nums[r]] 就是连续递增子序列。
 *
 *  
 *
 * 示例 1：
 *
 * 输入：nums = [1,3,5,4,7]
 * 输出：3
 * 解释：最长连续递增序列是 [1,3,5], 长度为3。
 * 尽管 [1,3,5,7] 也是升序的子序列, 但它不是连续的，因为 5 和 7 在原数组里被 4 隔开。
 * 示例 2：
 *
 * 输入：nums = [2,2,2,2,2]
 * 输出：1
 * 解释：最长连续递增序列是 [2], 长度为1。
 *  
 *
 * 提示：
 *
 * 1 <= nums.length <= 104
 * -109 <= nums[i] <= 109
 *
 * @author chengzhy
 * @date 2022/3/28 14:49
 */
public class P674_LongestContinuousIncreasingSubsequence {

    public int findLengthOfLCIS(int[] nums) {
        // dp[i]数组表示以nums数组的第i个数字结尾(不一定是从下标0开始)的子序列中连续递增的子序列最长的长度
        int[] dp = new int[nums.length];
        Arrays.fill(dp, 1);
        int result = 1;
        for (int i = 1; i < dp.length; i++) {
            // 注意与题300的不同在于此处要求是连续递增
            if (nums[i] > nums[i - 1]) {
                dp[i] = dp[i - 1] + 1;
            }
            /**
             * 对于nums[i] <= nums[i - 1]的情况
             * 当前位置的dp[i]的值从1开始
             */
            // 更新result的值
            if (dp[i] > result) {
                result = dp[i];
            }
        }
        return result;
    }

}
