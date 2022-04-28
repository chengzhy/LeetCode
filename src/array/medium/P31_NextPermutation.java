package array.medium;

import java.util.Arrays;

/**
 * 下一个排列
 * <a href="https://leetcode.cn/problems/next-permutation/">🔗</a>
 *
 * 实现获取 下一个排列 的函数，算法需要将给定数字序列重新排列成字典序中下一个更大的排列。
 *
 * 如果不存在下一个更大的排列，则将数字重新排列成最小的排列（即升序排列）。
 *
 * 必须 原地 修改，只允许使用额外常数空间。
 *
 *  
 *
 * 示例 1：
 *
 * 输入：nums = [1,2,3]
 * 输出：[1,3,2]
 * 示例 2：
 *
 * 输入：nums = [3,2,1]
 * 输出：[1,2,3]
 * 示例 3：
 *
 * 输入：nums = [1,1,5]
 * 输出：[1,5,1]
 * 示例 4：
 *
 * 输入：nums = [1]
 * 输出：[1]
 *  
 *
 * 提示：
 *
 * 1 <= nums.length <= 100
 * 0 <= nums[i] <= 100
 *
 * @author chengzhy
 * @date 2022/1/27 16:00
 **/
public class P31_NextPermutation {

    public void nextPermutation(int[] nums) {
        // 长度小于2直接返回
        if (nums.length < 2) return;

        for (int i=nums.length-1; i>0; i--) {
            // 比前一个数字大
            if (nums[i] > nums[i-1]) {
                int minIndex = i;
                // 从第i个开始往后找比i-1大且更接近的数
                for (int j=i; j<nums.length; j++) {
                    if (nums[j]<nums[minIndex] && nums[j]>nums[i-1]) {
                        minIndex = j;
                    }
                }
                // 交换位置
                int temp = nums[i-1];
                nums[i-1] =  nums[minIndex];
                nums[minIndex] = temp;
                // 将第i个之后的数升序排列，结束
                Arrays.sort(nums, i, nums.length);
                return;
            }
        }

        // 若为降序，再重新升序
        Arrays.sort(nums);
    }

}
