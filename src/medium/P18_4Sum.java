package medium;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @description 四数之和
 *
 * 给定一个包含 n 个整数的数组 nums 和一个目标值 target，判断 nums 中是否存在四个元素 a，b，c 和 d ，使得 a + b + c + d 的值与 target 相等？找出所有满足条件且不重复的四元组。
 *
 * 注意：
 *
 * 答案中不可以包含重复的四元组。
 *
 * 示例：
 *
 * 给定数组 nums = [1, 0, -1, 0, -2, 2]，和 target = 0。
 *
 * 满足要求的四元组集合为：
 * [
 *   [-1,  0, 0, 1],
 *   [-2, -1, 1, 2],
 *   [-2,  0, 0, 2]
 * ]
 *
 **/
public class P18_4Sum {

    public List<List<Integer>> fourSum(int[] nums, int target) {
        List<List<Integer>> list = new ArrayList<>();
        if (nums == null || nums.length < 4) {
            return list;
        }
        Arrays.sort(nums);
        for (int i=0; i<nums.length-3; i++) {
            // 定值去重
            if (i > 0 && nums[i] == nums[i-1]) {
                continue;
            }
            // 如果当前数字乘以4大于target，则四数之和一定大于target，所以结束循环
            if (4*nums[i] > target) {
                break;
            }
            // tail尾部指针，l左指针，r右指针
            int tail = nums.length-1, l = i+1, r = tail-1;
            while (tail>i+2) {
                // 重新定位尾指针
                if (l>=r) {
                    // 尾指针去重 tail-1
                    while (i < tail && nums[tail] == nums[--tail]);
                    l = i+1;
                    r = tail-1;
                    continue;
                }
                int fourSum = nums[i] + nums[l] + nums[r] + nums[tail];
                if (target == fourSum) {
                    list.add(Arrays.asList(nums[i], nums[l], nums[r], nums[tail]));
                    // 左右指针去重 & l+1 & r-1
                    while (l < r && nums[l] == nums[++l]);
                    while (l < r && nums[r] == nums[--r]);
                } else if (target > fourSum) {
                    // 四数之和小于target,左指针右移
                    while (l < r && nums[l] == nums[++l]);
                } else {
                    // 四数之和大于target，右指针左移
                    while (l < r && nums[r] == nums[--r]);
                }
            }
        }
        return list;
    }

}
