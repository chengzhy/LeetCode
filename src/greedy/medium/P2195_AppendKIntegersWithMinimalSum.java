package greedy.medium;

import java.util.Arrays;

/**
 * 向数组中追加 K 个整数
 * <a href="https://leetcode.cn/problems/append-k-integers-with-minimal-sum/">🔗</a>
 *
 * 给你一个整数数组 nums 和一个整数 k 。请你向 nums 中追加 k 个 未 出现在 nums 中的、互不相同 的 正 整数，并使结果数组的元素和 最小 。
 *
 * 返回追加到 nums 中的 k 个整数之和。
 *
 *
 *
 * 示例 1：
 *
 * 输入：nums = [1,4,25,10,25], k = 2
 * 输出：5
 * 解释：在该解法中，向数组中追加的两个互不相同且未出现的正整数是 2 和 3 。
 * nums 最终元素和为 1 + 4 + 25 + 10 + 25 + 2 + 3 = 70 ，这是所有情况中的最小值。
 * 所以追加到数组中的两个整数之和是 2 + 3 = 5 ，所以返回 5 。
 * 示例 2：
 *
 * 输入：nums = [5,6], k = 6
 * 输出：25
 * 解释：在该解法中，向数组中追加的两个互不相同且未出现的正整数是 1 、2 、3 、4 、7 和 8 。
 * nums 最终元素和为 5 + 6 + 1 + 2 + 3 + 4 + 7 + 8 = 36 ，这是所有情况中的最小值。
 * 所以追加到数组中的两个整数之和是 1 + 2 + 3 + 4 + 7 + 8 = 25 ，所以返回 25 。
 *
 *
 * 提示：
 *
 * 1 <= nums.length <= 105
 * 1 <= nums[i], k <= 109
 *
 * @author chengzhy
 * @date 2022/3/6 10:45
 */
public class P2195_AppendKIntegersWithMinimalSum {

    public long minimalKSum(int[] nums, int k) {
        Arrays.sort(nums);
        // start为起始计数的值
        long result = 0L, start = 1L, count;
        for (int i = 0; i < nums.length && k > 0; i++) {
            if (start < nums[i]) {
                // 个数
                count = ((nums[i] - start) > k) ? k : nums[i] - start;
                // 求和公式
                result += ((start + start + count - 1) * count) >> 1;
                k -= count;
            }
            start = (long) nums[i] + 1;
        }
        // 如果遍历完k还有剩余，说明还需要再添加剩余的
        if (k > 0) {
            result += ((start + start + k - 1) * k) >> 1;
        }
        return result;
    }

}
