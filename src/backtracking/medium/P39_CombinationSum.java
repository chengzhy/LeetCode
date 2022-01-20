package backtracking.medium;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 组合总和
 *
 * 给你一个 无重复元素 的整数数组 candidates 和一个目标整数 target ，找出 candidates 中可以使数字和为目标数 target 的 所有不同组合 ，并以列表形式返回。你可以按 任意顺序 返回这些组合。
 *
 * candidates 中的 同一个 数字可以 无限制重复被选取 。如果至少一个数字的被选数量不同，则两种组合是不同的。 
 *
 * 对于给定的输入，保证和为 target 的不同组合数少于 150 个。
 *
 *  
 *
 * 示例 1：
 *
 * 输入：candidates = [2,3,6,7], target = 7
 * 输出：[[2,2,3],[7]]
 * 解释：
 * 2 和 3 可以形成一组候选，2 + 2 + 3 = 7 。注意 2 可以使用多次。
 * 7 也是一个候选， 7 = 7 。
 * 仅有这两种组合。
 * 示例 2：
 *
 * 输入: candidates = [2,3,5], target = 8
 * 输出: [[2,2,2,2],[2,3,3],[3,5]]
 * 示例 3：
 *
 * 输入: candidates = [2], target = 1
 * 输出: []
 * 示例 4：
 *
 * 输入: candidates = [1], target = 1
 * 输出: [[1]]
 * 示例 5：
 *
 * 输入: candidates = [1], target = 2
 * 输出: [[1,1]]
 *  
 *
 * 提示：
 *
 * 1 <= candidates.length <= 30
 * 1 <= candidates[i] <= 200
 * candidate 中的每个元素都 互不相同
 * 1 <= target <= 500
 *
 * @author chengzhy
 * @date 2021/12/17 16:55
 */
public class P39_CombinationSum {

    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>> result = new ArrayList<>();
        // 先从小到达排序
        Arrays.sort(candidates);
        // 回溯
        backtracking(result, candidates, 0, target, new ArrayList<>());
        return result;
    }

    /**
     * 回溯法
     *
     * @param result 结果集
     * @param candidates 候选数组
     * @param position 开始寻找的位置
     * @param target 目标值
     * @param candidate 候选子集
     */
    private void backtracking(List<List<Integer>> result, int[] candidates, int position,
                              int target, List<Integer> candidate) {
        for (int i = position; i < candidates.length; i++) {
            // 如果target - candidates[i] >= candidates[i]，先把candidates[i]加入集合中
            if (target >= (candidates[i] << 1)) {
                candidate.add(candidates[i]);
                // 继续从i位置寻找值为target-candidates[i]
                backtracking(result, candidates, i, target - candidates[i], candidate);
            } else if (target == candidates[i]) {
                // 如果相等，将此candidates[i]加入结果集，至此找到一个候选结果
                candidate.add(candidates[i]);
                result.add(new ArrayList<>(candidate));
                // 移除最后一个值，以便下一次寻找
                candidate.remove(candidate.size() - 1);
            }
        }
        // 该数组遍历后都没有找到候选值，则继续移除最后一个值，以便下一次寻找
        if (!candidate.isEmpty()) {
            candidate.remove(candidate.size() - 1);
        }
    }

}
