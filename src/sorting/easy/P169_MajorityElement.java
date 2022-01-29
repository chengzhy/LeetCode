package sorting.easy;

import java.util.Arrays;

/**
 * 多数元素
 *
 * 给定一个大小为 n 的数组，找到其中的多数元素。多数元素是指在数组中出现次数 大于 ⌊ n/2 ⌋ 的元素。
 *
 * 你可以假设数组是非空的，并且给定的数组总是存在多数元素。
 *
 *  
 *
 * 示例 1：
 *
 * 输入：[3,2,3]
 * 输出：3
 * 示例 2：
 *
 * 输入：[2,2,1,1,1,2,2]
 * 输出：2
 *  
 *
 * 进阶：
 *
 * 尝试设计时间复杂度为 O(n)、空间复杂度为 O(1) 的算法解决此问题。
 *
 * @author chengzhy
 * @date 2022/1/29 11:35
 */
public class P169_MajorityElement {

    public int majorityElement(int[] nums) {
        Arrays.sort(nums);
        /*int result = 0, i, j = 0, count = 0;
        for (i = 0; i < nums.length; i++) {
            if (nums[i] != nums[j]) {
                if (i - j > count) {
                    count = i - j;
                    result = nums[j];
                }
                j = i--;
            }
        }
        if (i - j > count) {
            result = nums[j];
        }
        return result;*/
        /**
         * 官方参考答案：巧解，因为题目设定多数元素是指在数组中出现次数大于⌊n/2⌋的元素并且一定有众数，
         * 所以排序后的nums[n/2]一定为众数。
         */
        return nums[nums.length / 2];
    }

}
