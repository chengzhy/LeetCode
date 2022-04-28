package backtracking.medium;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 子集 II
 * <a href="https://leetcode.cn/problems/subsets-ii/">🔗</a>
 *
 * 给你一个整数数组 nums ，其中可能包含重复元素，请你返回该数组所有可能的子集（幂集）。
 *
 * 解集 不能 包含重复的子集。返回的解集中，子集可以按 任意顺序 排列。
 *
 *  
 *
 * 示例 1：
 *
 * 输入：nums = [1,2,2]
 * 输出：[[],[1],[1,2],[1,2,2],[2],[2,2]]
 * 示例 2：
 *
 * 输入：nums = [0]
 * 输出：[[],[0]]
 *  
 *
 * 提示：
 *
 * 1 <= nums.length <= 10
 * -10 <= nums[i] <= 10
 *
 * @author chengzhy
 * @date 2022/3/10 15:05
 */
public class P90_SubsetsII {

    public List<List<Integer>> subsetsWithDup(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        // 先排序保证顺序好判断是否重复
        Arrays.sort(nums);
        backtracking(nums, 0, result, new ArrayList<>(), new boolean[nums.length]);
        return result;
    }

    private void backtracking(int[] nums, int start, List<List<Integer>> result,
                              List<Integer> temp, boolean[] used) {
        result.add(new ArrayList<>(temp));
        for (int i = start; i < nums.length; i++) {
            /**
             * 此判断为去重关键，当该位置的数使用过 或者 当前位置的数下标大于0并且与前一个数相等并且前一个数未使用过
             * 则跳过继续下一个数
             */
            if (used[i] || i > 0 && nums[i] == nums[i - 1] && !used[i - 1]) {
                continue;
            }
            temp.add(nums[i]);
            used[i] = true;
            backtracking(nums, i + 1, result, temp, used);
            used[i] = false;
            temp.remove(temp.size() - 1);
        }
    }

}
