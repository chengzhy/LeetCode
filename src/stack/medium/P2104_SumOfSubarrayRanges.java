package stack.medium;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * 子数组范围和
 *
 * 给你一个整数数组 nums 。nums 中，子数组的 范围 是子数组中最大元素和最小元素的差值。
 *
 * 返回 nums 中 所有 子数组范围的 和 。
 *
 * 子数组是数组中一个连续 非空 的元素序列。
 *
 *  
 *
 * 示例 1：
 *
 * 输入：nums = [1,2,3]
 * 输出：4
 * 解释：nums 的 6 个子数组如下所示：
 * [1]，范围 = 最大 - 最小 = 1 - 1 = 0
 * [2]，范围 = 2 - 2 = 0
 * [3]，范围 = 3 - 3 = 0
 * [1,2]，范围 = 2 - 1 = 1
 * [2,3]，范围 = 3 - 2 = 1
 * [1,2,3]，范围 = 3 - 1 = 2
 * 所有范围的和是 0 + 0 + 0 + 1 + 1 + 2 = 4
 * 示例 2：
 *
 * 输入：nums = [1,3,3]
 * 输出：4
 * 解释：nums 的 6 个子数组如下所示：
 * [1]，范围 = 最大 - 最小 = 1 - 1 = 0
 * [3]，范围 = 3 - 3 = 0
 * [3]，范围 = 3 - 3 = 0
 * [1,3]，范围 = 3 - 1 = 2
 * [3,3]，范围 = 3 - 3 = 0
 * [1,3,3]，范围 = 3 - 1 = 2
 * 所有范围的和是 0 + 0 + 0 + 2 + 0 + 2 = 4
 * 示例 3：
 *
 * 输入：nums = [4,-2,-3,4,1]
 * 输出：59
 * 解释：nums 中所有子数组范围的和是 59
 *  
 *
 * 提示：
 *
 * 1 <= nums.length <= 1000
 * -109 <= nums[i] <= 109
 *  
 *
 * 进阶：你可以设计一种时间复杂度为 O(n) 的解决方案吗？
 *
 * @author chengzhy
 * @date 2022/3/8 18:44
 */
public class P2104_SumOfSubarrayRanges {

    public long subArrayRanges(int[] nums) {
        // O(n^2)复杂度
        /*long result = 0L;
        for (int i = 0; i < nums.length; i++) {
            int min = Integer.MAX_VALUE, max = Integer.MIN_VALUE;
            for (int j = i; j < nums.length; j++) {
                // 从索引i到j的子阵列的最小/大值等于min/max of（从索引i到j-1的子阵列的最小/大值）和nums[j]
                min = Math.min(min, nums[j]);
                max = Math.max(max, nums[j]);
                result += max - min;
            }
        }
        return result;*/
        // 官方题解：单调栈做法，复杂度为O(n)
        int n = nums.length;
        int[] minLeft = new int[n];
        int[] minRight = new int[n];
        int[] maxLeft = new int[n];
        int[] maxRight = new int[n];
        Deque<Integer> minStack = new ArrayDeque<Integer>();
        Deque<Integer> maxStack = new ArrayDeque<Integer>();
        for (int i = 0; i < n; i++) {
            while (!minStack.isEmpty() && nums[minStack.peek()] > nums[i]) {
                minStack.pop();
            }
            minLeft[i] = minStack.isEmpty() ? -1 : minStack.peek();
            minStack.push(i);

            // 如果 nums[maxStack.peek()] == nums[i], 那么根据定义，
            // nums[maxStack.peek()] 逻辑上小于 nums[i]，因为 maxStack.peek() < i
            while (!maxStack.isEmpty() && nums[maxStack.peek()] <= nums[i]) {
                maxStack.pop();
            }
            maxLeft[i] = maxStack.isEmpty() ? -1 : maxStack.peek();
            maxStack.push(i);
        }
        minStack.clear();
        maxStack.clear();
        for (int i = n - 1; i >= 0; i--) {
            // 如果 nums[minStack.peek()] == nums[i], 那么根据定义，
            // nums[minStack.peek()] 逻辑上大于 nums[i]，因为 minStack.peek() > i
            while (!minStack.isEmpty() && nums[minStack.peek()] >= nums[i]) {
                minStack.pop();
            }
            minRight[i] = minStack.isEmpty() ? n : minStack.peek();
            minStack.push(i);

            while (!maxStack.isEmpty() && nums[maxStack.peek()] < nums[i]) {
                maxStack.pop();
            }
            maxRight[i] = maxStack.isEmpty() ? n : maxStack.peek();
            maxStack.push(i);
        }

        long sumMax = 0, sumMin = 0;
        for (int i = 0; i < n; i++) {
            sumMax += (long) (maxRight[i] - i) * (i - maxLeft[i]) * nums[i];
            sumMin += (long) (minRight[i] - i) * (i - minLeft[i]) * nums[i];
        }
        return sumMax - sumMin;
    }

    public static void main(String[] args) {
        P2104_SumOfSubarrayRanges p = new P2104_SumOfSubarrayRanges();
        // 4,-2,-3,4,1
        System.out.println(p.subArrayRanges(new int[]{1,2,3}));
    }

}
