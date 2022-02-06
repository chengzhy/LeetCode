package array.easy;

import java.util.HashMap;
import java.util.Map;

/**
 * 唯一元素的和
 *
 * 给你一个整数数组 nums 。数组中唯一元素是那些只出现 恰好一次 的元素。
 *
 * 请你返回 nums 中唯一元素的 和 。
 *
 *  
 *
 * 示例 1：
 *
 * 输入：nums = [1,2,3,2]
 * 输出：4
 * 解释：唯一元素为 [1,3] ，和为 4 。
 * 示例 2：
 *
 * 输入：nums = [1,1,1,1,1]
 * 输出：0
 * 解释：没有唯一元素，和为 0 。
 * 示例 3 ：
 *
 * 输入：nums = [1,2,3,4,5]
 * 输出：15
 * 解释：唯一元素为 [1,2,3,4,5] ，和为 15 。
 *  
 *
 * 提示：
 *
 * 1 <= nums.length <= 100
 * 1 <= nums[i] <= 100
 *
 * @author chengzhy
 * @date 2022/2/6 10:22
 */
public class P1748_SumOfUniqueElements {

    public int sumOfUnique(int[] nums) {
        int sum = 0;
        // Map<Integer, Boolean> map = new HashMap<>();
        int[] count = new int[100];
        for (int num : nums) {
            /*if (!map.containsKey(num)) {
                sum += num;
                map.put(num, true);
            } else if (map.get(num)) {
                sum -= num;
                map.put(num, false);
            }*/
            if (count[num - 1]++ == 0) {
                sum += num;
            } else if (count[num - 1] == 2) {
                sum -= num;
            }
        }
        return sum;
    }

}
