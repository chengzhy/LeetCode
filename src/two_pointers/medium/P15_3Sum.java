package two_pointers.medium;

import java.util.*;

/**
 * 三数之和
 *
 * 给你一个包含 n 个整数的数组 nums，判断 nums 中是否存在三个元素 a，b，c ，使得 a + b + c = 0 ？请你找出所有和为 0 且不重复的三元组。
 *
 * 注意：答案中不可以包含重复的三元组。
 *
 *  
 *
 * 示例 1：
 *
 * 输入：nums = [-1,0,1,2,-1,-4]
 * 输出：[[-1,-1,2],[-1,0,1]]
 * 示例 2：
 *
 * 输入：nums = []
 * 输出：[]
 * 示例 3：
 *
 * 输入：nums = [0]
 * 输出：[]
 *  
 *
 * 提示：
 *
 * 0 <= nums.length <= 3000
 * -105 <= nums[i] <= 105
 *
 * @author chengzhy
 * @date 2022/1/27 16:00
 **/
public class P15_3Sum {

    /**
     * 看的答案
     */
    public List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> list = new ArrayList<>();
        if (nums == null || nums.length < 3) {
            return list;
        }
        // 从小到大排序
        Arrays.sort(nums);
        // 第一个到倒数第三个
        for (int i=0; i<nums.length-2;) {
            // 如果当前数字大于0，则三数之和一定大于0，所以结束循环
            if (nums[i] > 0) {
                break;
            }
            int l = i + 1, r = nums.length - 1;
            while (l < r) {
                int sum = nums[i] + nums[l] + nums[r];
                if (sum == 0) {
                    list.add(Arrays.asList(nums[i], nums[l], nums[r]));
                    // 左右指针去重 & l+1 & r-1
                    while (l < r && nums[l] == nums[++l]);
                    while (l < r && nums[r] == nums[--r]);
                } else if (sum < 0) {
                    while (l < r && nums[l] == nums[++l]);
                } else {
                    while (l < r && nums[r] == nums[--r]);
                }
            }
            // 定值去重
            try {
                while (nums[i] == nums[++i]);
            } catch (ArrayIndexOutOfBoundsException e) {
            }
        }
        return list;
    }

}
