package backtracking.medium;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 组合总和 II
 *
 * 给定一个候选人编号的集合 candidates 和一个目标数 target ，找出 candidates 中所有可以使数字和为 target 的组合。
 *
 * candidates 中的每个数字在每个组合中只能使用 一次 。
 *
 * 注意：解集不能包含重复的组合。 
 *
 *  
 *
 * 示例 1:
 *
 * 输入: candidates = [10,1,2,7,6,1,5], target = 8,
 * 输出:
 * [
 * [1,1,6],
 * [1,2,5],
 * [1,7],
 * [2,6]
 * ]
 * 示例 2:
 *
 * 输入: candidates = [2,5,2,1,2], target = 5,
 * 输出:
 * [
 * [1,2,2],
 * [5]
 * ]
 *  
 *
 * 提示:
 *
 * 1 <= candidates.length <= 100
 * 1 <= candidates[i] <= 50
 * 1 <= target <= 30
 *
 * @author chengzhy
 * @date 2022/3/23 10:29
 */
public class P40_CombinationSumII {

    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        List<List<Integer>> result = new ArrayList<>();
        Arrays.sort(candidates);
        backtracking(candidates, target, 0, new boolean[candidates.length], result, new ArrayList<>());
        return result;
    }

    private void backtracking(int[] candidates, int target, int start, boolean[] used,
                              List<List<Integer>> result, List<Integer> tempList) {
        if (target < 0) {
            return;
        }
        if (target == 0) {
            result.add(new ArrayList<>(tempList));
            return;
        }
        // 从start位置开始添加元素
        for (int i = start; i < candidates.length; i++) {
            // 去重逻辑
            if (used[i] || (i > 0 && candidates[i] == candidates[i - 1] && !used[i - 1])) {
                continue;
            }
            tempList.add(candidates[i]);
            used[i] = true;
            backtracking(candidates, target - candidates[i], i + 1, used, result, tempList);
            used[i] = false;
            tempList.remove(tempList.size() - 1);
        }
    }

}
