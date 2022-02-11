package sliding_window.easy;

import java.util.Arrays;

/**
 * 学生分数的最小差值
 *
 * 给你一个 下标从 0 开始 的整数数组 nums ，其中 nums[i] 表示第 i 名学生的分数。另给你一个整数 k 。
 *
 * 从数组中选出任意 k 名学生的分数，使这 k 个分数间 最高分 和 最低分 的 差值 达到 最小化 。
 *
 * 返回可能的 最小差值 。
 *
 *  
 *
 * 示例 1：
 *
 * 输入：nums = [90], k = 1
 * 输出：0
 * 解释：选出 1 名学生的分数，仅有 1 种方法：
 * - [90] 最高分和最低分之间的差值是 90 - 90 = 0
 * 可能的最小差值是 0
 * 示例 2：
 *
 * 输入：nums = [9,4,1,7], k = 2
 * 输出：2
 * 解释：选出 2 名学生的分数，有 6 种方法：
 * - [9,4,1,7] 最高分和最低分之间的差值是 9 - 4 = 5
 * - [9,4,1,7] 最高分和最低分之间的差值是 9 - 1 = 8
 * - [9,4,1,7] 最高分和最低分之间的差值是 9 - 7 = 2
 * - [9,4,1,7] 最高分和最低分之间的差值是 4 - 1 = 3
 * - [9,4,1,7] 最高分和最低分之间的差值是 7 - 4 = 3
 * - [9,4,1,7] 最高分和最低分之间的差值是 7 - 1 = 6
 * 可能的最小差值是 2
 *  
 *
 * 提示：
 *
 * 1 <= k <= nums.length <= 1000
 * 0 <= nums[i] <= 105
 *
 * @author chengzhy
 * @date 2022/2/11 8:26
 */
public class P1984_MinimumDifferenceBetweenHighestAndLowestOfKScores {

    public int minimumDifference(int[] nums, int k) {
        if (nums.length < 2 || k == 1) {
            return 0;
        }
        // 先排序
        Arrays.sort(nums);
        // 再滑动窗口
        int result = Integer.MAX_VALUE, start = 0, end = (nums.length > k) ? k - 1 : nums.length - 1;
        while (end < nums.length) {
            result = Math.min(result, nums[end++] - nums[start++]);
        }
        return result;
    }

}