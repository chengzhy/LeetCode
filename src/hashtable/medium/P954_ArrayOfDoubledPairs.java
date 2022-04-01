package hashtable.medium;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * 二倍数对数组
 *
 * 给定一个长度为偶数的整数数组 arr，只有对 arr 进行重组后可以满足 “对于每个 0 <= i < len(arr) / 2，都有 arr[2 * i + 1] = 2 * arr[2 * i]” 时，返回 true；否则，返回 false。
 *
 *  
 *
 * 示例 1：
 *
 * 输入：arr = [3,1,3,6]
 * 输出：false
 * 示例 2：
 *
 * 输入：arr = [2,1,2,6]
 * 输出：false
 * 示例 3：
 *
 * 输入：arr = [4,-2,2,-4]
 * 输出：true
 * 解释：可以用 [-2,-4] 和 [2,4] 这两组组成 [-2,-4,2,4] 或是 [2,4,-2,-4]
 *  
 *
 * 提示：
 *
 * 0 <= arr.length <= 3 * 104
 * arr.length 是偶数
 * -105 <= arr[i] <= 105
 *
 * @author chengzhy
 * @date 2022/4/1 11:47
 */
public class P954_ArrayOfDoubledPairs {

    public boolean canReorderDoubled(int[] arr) {
        Arrays.sort(arr);
        // 存arr中每个数出现的次数
        Map<Integer, Integer> map = new HashMap<>();
        for (int num : arr) {
            if (map.containsKey(num << 1)) {
                // map中有其2倍的数
                if (map.get(num << 1) == 1) {
                    // 等于1移除
                    map.remove(num << 1);
                } else {
                    // 大于1则减1
                    map.put(num << 1, map.get(num << 1) - 1);
                }
            } else if (((num & 1) == 0) && map.containsKey(num >> 1)) {
                // 如果当前数是偶数并且有它一半的数
                if (map.get(num >> 1) == 1) {
                    // 等于1移除
                    map.remove(num >> 1);
                } else {
                    // 大于1则减1
                    map.put(num >> 1, map.get(num >> 1) - 1);
                }
            } else {
                map.put(num, map.getOrDefault(num, 0) + 1);
            }
        }
        return map.isEmpty();
    }

}
