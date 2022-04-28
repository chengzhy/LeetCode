package backtracking.medium;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 全排列 II
 * <a href="https://leetcode.cn/problems/permutations-ii/">🔗</a>
 *
 * 给定一个可包含重复数字的序列 nums ，按任意顺序 返回所有不重复的全排列。
 *
 *  
 *
 * 示例 1：
 *
 * 输入：nums = [1,1,2]
 * 输出：
 * [[1,1,2],
 *  [1,2,1],
 *  [2,1,1]]
 * 示例 2：
 *
 * 输入：nums = [1,2,3]
 * 输出：[[1,2,3],[1,3,2],[2,1,3],[2,3,1],[3,1,2],[3,2,1]]
 *  
 *
 * 提示：
 *
 * 1 <= nums.length <= 8
 * -10 <= nums[i] <= 10
 *
 * @author chengzhy
 * @date 2022/2/10 9:33
 */
public class P47_PermutationsII {

    public List<List<Integer>> permuteUnique(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        // 先排序保证顺序好判断是否重复
        Arrays.sort(nums);
        backtracking(result, nums, new ArrayList<>(nums.length), new boolean[nums.length]);
        return result;
    }

    private void backtracking(List<List<Integer>> result, int[] nums, List<Integer> list, boolean[] used) {
        if (list.size() == nums.length) {
            result.add(new ArrayList<>(list));
            return;
        }
        for (int i = 0; i < nums.length; i++) {
            /**
             * 此判断为去重关键，当该位置的数使用过 或者 当前位置的数下标大于0并且与前一个数相等并且前一个数未使用过
             * 则跳过继续下一个数
             */
            if (used[i] || (i > 0 && nums[i] == nums[i - 1] && !used[i - 1])) {
                continue;
            }
            list.add(nums[i]);
            used[i] = true;
            backtracking(result, nums, list, used);
            used[i] = false;
            list.remove(list.size() - 1);
        }
    }

}
