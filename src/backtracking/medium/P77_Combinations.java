package backtracking.medium;

import java.util.ArrayList;
import java.util.List;

/**
 * 组合
 * <a href="https://leetcode.cn/problems/combinations/">🔗</a>
 *
 * 给定两个整数 n 和 k，返回范围 [1, n] 中所有可能的 k 个数的组合。
 *
 * 你可以按 任何顺序 返回答案。
 *
 *  
 *
 * 示例 1：
 *
 * 输入：n = 4, k = 2
 * 输出：
 * [
 *   [2,4],
 *   [3,4],
 *   [2,3],
 *   [1,2],
 *   [1,3],
 *   [1,4],
 * ]
 * 示例 2：
 *
 * 输入：n = 1, k = 1
 * 输出：[[1]]
 *  
 *
 * 提示：
 *
 * 1 <= n <= 20
 * 1 <= k <= n
 *
 * @author chengzhy
 * @date 2022/2/6 14:11
 */
public class P77_Combinations {

    public List<List<Integer>> combine(int n, int k) {
        List<List<Integer>> result = new ArrayList<>();
        backtracking(result, n, k, 0, new ArrayList<>());
        return result;
    }

    private void backtracking(List<List<Integer>> result, int n, int k, int begin, List<Integer> list) {
        if (list.size() == k) {
            result.add(new ArrayList<>(list));
            return;
        }
        for (int i = begin; i < n; i++) {
            list.add(i + 1);
            backtracking(result, n, k, i + 1, list);
            list.remove(list.size() - 1);
        }
    }

}
