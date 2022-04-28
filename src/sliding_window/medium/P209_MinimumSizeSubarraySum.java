package sliding_window.medium;

/**
 * 长度最小的子数组
 * <a href="https://leetcode.cn/problems/minimum-size-subarray-sum/">🔗</a>
 *
 * 给定一个含有 n 个正整数的数组和一个正整数 target 。
 *
 * 找出该数组中满足其和 ≥ target 的长度最小的 连续子数组 [numsl, numsl+1, ..., numsr-1, numsr] ，并返回其长度。如果不存在符合条件的子数组，返回 0 。
 *
 *  
 *
 * 示例 1：
 *
 * 输入：target = 7, nums = [2,3,1,2,4,3]
 * 输出：2
 * 解释：子数组 [4,3] 是该条件下的长度最小的子数组。
 * 示例 2：
 *
 * 输入：target = 4, nums = [1,4,4]
 * 输出：1
 * 示例 3：
 *
 * 输入：target = 11, nums = [1,1,1,1,1,1,1,1]
 * 输出：0
 *  
 *
 * 提示：
 *
 * 1 <= target <= 109
 * 1 <= nums.length <= 105
 * 1 <= nums[i] <= 105
 *  
 *
 * 进阶：
 *
 * 如果你已经实现 O(n) 时间复杂度的解法, 请尝试设计一个 O(n log(n)) 时间复杂度的解法。
 *
 * @author chengzhy
 * @date 2022/2/10 12:10
 */
public class P209_MinimumSizeSubarraySum {

    public int minSubArrayLen(int target, int[] nums) {
        int start = 0, end = 0, result = Integer.MAX_VALUE, sum = 0;
        // 滑动窗口
        while (end < nums.length) {
            sum += nums[end];
            while (sum >= target) {
                // 如果大于目标值，左指针+1看看是否还满足，这样就可以得到小的结果
                result = Math.min(result, end - start + 1);
                sum -= nums[start++];
            }
            end++;
        }
        return result == Integer.MAX_VALUE ? 0 : result;
    }

}
