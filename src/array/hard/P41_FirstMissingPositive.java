package array.hard;

/**
 * 缺失的第一个正数
 *
 * 给你一个未排序的整数数组 nums ，请你找出其中没有出现的最小的正整数。
 *
 * 请你实现时间复杂度为 O(n) 并且只使用常数级别额外空间的解决方案。
 *  
 *
 * 示例 1：
 *
 * 输入：nums = [1,2,0]
 * 输出：3
 * 示例 2：
 *
 * 输入：nums = [3,4,-1,1]
 * 输出：2
 * 示例 3：
 *
 * 输入：nums = [7,8,9,11,12]
 * 输出：1
 *  
 *
 * 提示：
 *
 * 1 <= nums.length <= 5 * 105
 * -231 <= nums[i] <= 231 - 1
 *
 * @author chengzhy
 * @date 2022/2/17 13:29
 */
public class P41_FirstMissingPositive {

    public int firstMissingPositive(int[] nums) {
        /*int[] temp = new int[nums.length];
        for (int num : nums) {
            if (num > 0 && num <= temp.length) {
                // 将大于0并且小于temp长度的num添加到对应位置
                temp[num - 1] = num;
            }
        }
        for (int i = 0; i < temp.length; i++) {
            if (temp[i] == 0) {
                // 如果为0，此位置即为最小未出现的正整数
                return i + 1;
            }
        }
        // 否则就是当前长度+1
        return temp.length + 1;*/
        for (int i = 0; i < nums.length; i++) {
            // 将小于等于0的数标记为数组长度+1的值
            if (nums[i] <= 0) {
                nums[i] = nums.length + 1;
            }
        }
        for (int i = 0; i < nums.length; i++) {
            // 取绝对值，若该数绝对值在1-n范围内，则将nums[该数]标记为负数
            int num = Math.abs(nums[i]);
            if (num > 0 && num <= nums.length) {
                nums[num - 1] = -Math.abs(nums[num - 1]);
            }
        }
        for (int i = 0; i < nums.length; i++) {
            // 如果某位置大于0，说明该位置的数没出现过
            if (nums[i] > 0) {
                return i + 1;
            }
        }
        // 若都没有找到，说明最小正整数为nums.length + 1
        return nums.length + 1;
    }

}
