package sliding_window.easy;

import java.util.HashSet;
import java.util.Set;

/**
 * 存在重复元素 II
 * <a href="https://leetcode.cn/problems/contains-duplicate-ii/">🔗</a>
 *
 * 给你一个整数数组 nums 和一个整数 k ，判断数组中是否存在两个 不同的索引 i 和 j ，满足 nums[i] == nums[j] 且 abs(i - j) <= k 。如果存在，返回 true ；否则，返回 false 。
 *
 *  
 *
 * 示例 1：
 *
 * 输入：nums = [1,2,3,1], k = 3
 * 输出：true
 * 示例 2：
 *
 * 输入：nums = [1,0,1,1], k = 1
 * 输出：true
 * 示例 3：
 *
 * 输入：nums = [1,2,3,1,2,3], k = 2
 * 输出：false
 *  
 *
 *  
 *
 * 提示：
 *
 * 1 <= nums.length <= 105
 * -109 <= nums[i] <= 109
 * 0 <= k <= 105
 *
 * @author chengzhy
 * @date 2022/7/11 22:00
 */
public class P219_ContainsDuplicateII {

    public boolean containsNearbyDuplicate(int[] nums, int k) {
        // 此处方法太慢了
        /*for (int i = 0; i < nums.length; i++) {
            for (int j = Math.min(nums.length - 1, i + k); j > i; j--) {
                if (nums[i] == nums[j]) {
                    return true;
                }
            }
        }
        return false;*/
        // 利用Set存储元素
        Set<Integer> set = new HashSet<>();
        for (int i = 0; i < nums.length; i++) {
            // 滑动窗口长度超出k
            if (i > k) {
                set.remove(nums[i - k - 1]);
            }
            // 添加元素
            if (!set.add(nums[i])) {
                return true;
            }
        }
        return false;
    }

}
