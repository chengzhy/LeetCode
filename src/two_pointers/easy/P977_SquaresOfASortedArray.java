package two_pointers.easy;

/**
 * 有序数组的平方
 * <a href="https://leetcode.cn/problems/squares-of-a-sorted-array/">🔗</a>
 *
 *给你一个按 非递减顺序 排序的整数数组 nums，返回 每个数字的平方 组成的新数组，要求也按 非递减顺序 排序。
 *
 *  
 *
 * 示例 1：
 *
 * 输入：nums = [-4,-1,0,3,10]
 * 输出：[0,1,9,16,100]
 * 解释：平方后，数组变为 [16,1,0,9,100]
 * 排序后，数组变为 [0,1,9,16,100]
 * 示例 2：
 *
 * 输入：nums = [-7,-3,2,3,11]
 * 输出：[4,9,9,49,121]
 *  
 *
 * 提示：
 *
 * 1 <= nums.length <= 104
 * -104 <= nums[i] <= 104
 * nums 已按 非递减顺序 排序
 *  
 *
 * 进阶：
 *
 * 请你设计时间复杂度为 O(n) 的算法解决本问题
 *
 * @author chengzhy
 * @date 2022/3/22 11:32
 */
public class P977_SquaresOfASortedArray {

    public int[] sortedSquares(int[] nums) {
        int[] result = new int[nums.length];
        int minAbs = Integer.MAX_VALUE, minIndex = 0;
        // 找到绝对值最小的数的位置，该位置就是平方后最小的数
        for (int i = 0; i < nums.length; i++) {
            if (Math.abs(nums[i]) < minAbs) {
                minAbs = Math.abs(nums[i]);
                minIndex = i;
            }
        }
        // 双指针
        int left = minIndex, right = left + 1, j = 0, temp;
        while (left >= 0 && right < nums.length) {
            temp = (Math.abs(nums[left]) < Math.abs(nums[right])) ? nums[left--] : nums[right++];
            result[j++] = temp * temp;
        }
        while (left >= 0) {
            temp = nums[left--];
            result[j++] = temp * temp;
        }
        while (right < nums.length) {
            temp = nums[right++];
            result[j++] = temp * temp;
        }
        return result;
    }

}
