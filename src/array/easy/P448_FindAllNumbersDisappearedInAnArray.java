package array.easy;

import java.util.ArrayList;
import java.util.List;

/**
 * 找到所有数组中消失的数字
 * <a href="https://leetcode.cn/problems/find-all-numbers-disappeared-in-an-array/">🔗</a>
 *
 * 给你一个含 n 个整数的数组 nums ，其中 nums[i] 在区间 [1, n] 内。请你找出所有在 [1, n] 范围内但没有出现在 nums 中的数字，并以数组的形式返回结果。
 *
 *  
 *
 * 示例 1：
 *
 * 输入：nums = [4,3,2,7,8,2,3,1]
 * 输出：[5,6]
 * 示例 2：
 *
 * 输入：nums = [1,1]
 * 输出：[2]
 *  
 *
 * 提示：
 *
 * n == nums.length
 * 1 <= n <= 105
 * 1 <= nums[i] <= n
 * 进阶：你能在不使用额外空间且时间复杂度为 O(n) 的情况下解决这个问题吗? 你可以假定返回的数组不算在额外空间内。
 *
 * @author chengzhy
 * @date 2022/2/2 13:14
 */
public class P448_FindAllNumbersDisappearedInAnArray {

    public List<Integer> findDisappearedNumbers(int[] nums) {
        List<Integer> result = new ArrayList<>();
        /**
         * 不用额外空间的解法(参考评论)：
         * 将所有正数作为数组下标，置对应数组值为负值。那么，仍为正数的位置即为（未出现过）消失的数字。
         */
        for (int i = 0; i < nums.length; i++) {
            nums[Math.abs(nums[i]) - 1] = -Math.abs(nums[Math.abs(nums[i]) - 1]);
        }
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] > 0) {
                result.add(i + 1);
            }
        }
        /*boolean[] arrays = new boolean[nums.length];
        for (int i = 0; i < nums.length; i++) {
            arrays[nums[i] - 1] = true;
        }
        for (int i = 0; i < nums.length; i++) {
            if (!arrays[i]) {
                result.add(i + 1);
            }
        }*/
        return result;
    }

}
