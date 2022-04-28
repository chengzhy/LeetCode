package hashtable.medium;

import java.util.HashMap;
import java.util.Map;

/**
 * 使数组变成交替数组的最少操作数
 * <a href="https://leetcode.cn/problems/minimum-operations-to-make-the-array-alternating/">🔗</a>
 *
 * 给你一个下标从 0 开始的数组 nums ，该数组由 n 个正整数组成。
 *
 * 如果满足下述条件，则数组 nums 是一个 交替数组 ：
 *
 * nums[i - 2] == nums[i] ，其中 2 <= i <= n - 1 。
 * nums[i - 1] != nums[i] ，其中 1 <= i <= n - 1 。
 * 在一步 操作 中，你可以选择下标 i 并将 nums[i] 更改 为 任一 正整数。
 *
 * 返回使数组变成交替数组的 最少操作数 。
 *
 *
 *
 * 示例 1：
 *
 * 输入：nums = [3,1,3,2,4,3]
 * 输出：3
 * 解释：
 * 使数组变成交替数组的方法之一是将该数组转换为 [3,1,3,1,3,1] 。
 * 在这种情况下，操作数为 3 。
 * 可以证明，操作数少于 3 的情况下，无法使数组变成交替数组。
 * 示例 2：
 *
 * 输入：nums = [1,2,2,2,2]
 * 输出：2
 * 解释：
 * 使数组变成交替数组的方法之一是将该数组转换为 [1,2,1,2,1].
 * 在这种情况下，操作数为 2 。
 * 注意，数组不能转换成 [2,2,2,2,2] 。因为在这种情况下，nums[0] == nums[1]，不满足交替数组的条件。
 *
 *
 * 提示：
 *
 * 1 <= nums.length <= 105
 * 1 <= nums[i] <= 105
 *
 * @author chengzhy
 * @date 2022/2/13 10:36
 */
public class P2170_MinimumOperationsToMakeTheArrayAlternating {

    public int minimumOperations(int[] nums) {
        if (nums.length == 1) {
            return 0;
        }
        // oddMap奇数下标数集合，evenMap为偶数下标数集合
        Map<Integer, Integer> oddMap = new HashMap<>();
        Map<Integer, Integer> evenMap = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            if ((i & 1) == 0) {
                evenMap.put(nums[i], evenMap.getOrDefault(nums[i], 0) + 1);
            } else {
                oddMap.put(nums[i], oddMap.getOrDefault(nums[i], 0) + 1);
            }
        }
        /**
         * 分别从两个map中寻找key数量第一和第二的数量
         */
        int oddMaxKey = 0, oddFirstMaxNum = 0, oddSecondMaxNum = 0;
        for (Map.Entry<Integer, Integer> entry : oddMap.entrySet()) {
            if (entry.getValue() > oddFirstMaxNum) {
                oddSecondMaxNum = oddFirstMaxNum;
                oddFirstMaxNum = entry.getValue();
                oddMaxKey = entry.getKey();
            } else if (entry.getValue() > oddSecondMaxNum) {
                oddSecondMaxNum = entry.getValue();
            }
        }
        int evenMaxKey = 0, evenFirstMaxNum = 0, evenSecondMaxNum = 0;
        for (Map.Entry<Integer, Integer> entry : evenMap.entrySet()) {
            if (entry.getValue() > evenFirstMaxNum) {
                evenSecondMaxNum = evenFirstMaxNum;
                evenFirstMaxNum = entry.getValue();
                evenMaxKey = entry.getKey();
            } else if (entry.getValue() > evenSecondMaxNum) {
                evenSecondMaxNum = entry.getValue();
            }
        }
        if (oddMaxKey != evenMaxKey) {
            // key不相等直接减
            return nums.length - oddFirstMaxNum - evenFirstMaxNum;
        } else {
            // 相等再看奇第一+偶第二 奇第二+偶第一哪个大
            return nums.length - Math.max(oddFirstMaxNum + evenSecondMaxNum, oddSecondMaxNum + evenFirstMaxNum);
        }
    }

}
