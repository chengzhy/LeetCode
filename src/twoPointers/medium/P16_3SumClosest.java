package twoPointers.medium;

import java.util.*;

/**
 * @description 最接近的三数之和
 *
 * 给定一个包括 n 个整数的数组 nums 和 一个目标值 target。找出 nums 中的三个整数，使得它们的和与 target 最接近。返回这三个数的和。假定每组输入只存在唯一答案。
 *
 *  
 *
 * 示例：
 *
 * 输入：nums = [-1,2,1,-4], target = 1
 * 输出：2
 * 解释：与 target 最接近的和是 2 (-1 + 2 + 1 = 2) 。
 *  
 *
 * 提示：
 *
 * 3 <= nums.length <= 10^3
 * -10^3 <= nums[i] <= 10^3
 * -10^4 <= target <= 10^4
 *
 **/
public class P16_3SumClosest {

    public int threeSumClosest(int[] nums, int target) {
        // 先排序
        Arrays.sort(nums);
        // 取个默认值
        int closestNum = nums[0] + nums[1] + nums[2];
        for (int i=0; i<nums.length-2; i++) {
            // 设置两个指针
            int l = i + 1, r = nums.length - 1;
            while (l < r) {
                int threeSum = nums[i] + nums[l] + nums[r];
                if (Math.abs(threeSum-target) < Math.abs(closestNum-target)) {
                    closestNum = threeSum;
                }
                if (threeSum > target) {
                    // 和大于target，右指针往左移
                    r--;
                } else if (threeSum < target) {
                    // 和小于target，左指针往右移
                    l++;
                } else {
                    // 相等直接返回target
                    return target;
                }
            }
        }
        return closestNum;
    }

}
