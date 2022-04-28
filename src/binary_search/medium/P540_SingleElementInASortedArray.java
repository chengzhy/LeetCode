package binary_search.medium;

/**
 * 有序数组中的单一元素
 * <a href="https://leetcode.cn/problems/single-element-in-a-sorted-array/">🔗</a>
 *
 * 给你一个仅由整数组成的有序数组，其中每个元素都会出现两次，唯有一个数只会出现一次。
 *
 * 请你找出并返回只出现一次的那个数。
 *
 * 你设计的解决方案必须满足 O(log n) 时间复杂度和 O(1) 空间复杂度。
 *
 *  
 *
 * 示例 1:
 *
 * 输入: nums = [1,1,2,3,3,4,4,8,8]
 * 输出: 2
 * 示例 2:
 *
 * 输入: nums =  [3,3,7,7,10,11,11]
 * 输出: 10
 *  
 *
 * 提示:
 *
 * 1 <= nums.length <= 105
 * 0 <= nums[i] <= 105
 *
 * @author chengzhy
 * @date 2022/2/14 9:21
 */
public class P540_SingleElementInASortedArray {

    public int singleNonDuplicate(int[] nums) {
        int left = 0, right = nums.length - 1;
        while (left < right) {
            int mid = left + (right - left) / 2;
            // 如果中间位置的数与左右两边都不相等，则返回该数
            if (nums[mid] != nums[mid - 1] && nums[mid] != nums[mid + 1]) {
                return nums[mid];
            }
            if (nums[mid] == nums[mid + 1]) {
                // 如果与右边数相等并且该位置为偶下标，则left = mid，反之right = mid - 1;
                left = ((mid & 1) == 0) ? mid : left;
                right = ((mid & 1) == 0) ? right : mid - 1;
            } else {
                // 如果与左边数相等并且该位置为偶下标，则right = mid，反之left = mid + 1;
                left = ((mid & 1) == 0) ? left : mid + 1;
                right = ((mid & 1) == 0) ? mid : right;
            }
        }
        return nums[left];
    }

}
