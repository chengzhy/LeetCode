package backtracking.medium;

import java.util.ArrayList;
import java.util.List;

/**
 * 全排列
 *
 * 给定一个不含重复数字的数组 nums ，返回其 所有可能的全排列 。你可以 按任意顺序 返回答案。
 *
 *  
 *
 * 示例 1：
 *
 * 输入：nums = [1,2,3]
 * 输出：[[1,2,3],[1,3,2],[2,1,3],[2,3,1],[3,1,2],[3,2,1]]
 * 示例 2：
 *
 * 输入：nums = [0,1]
 * 输出：[[0,1],[1,0]]
 * 示例 3：
 *
 * 输入：nums = [1]
 * 输出：[[1]]
 *  
 *
 * 提示：
 *
 * 1 <= nums.length <= 6
 * -10 <= nums[i] <= 10
 * nums 中的所有整数 互不相同
 *
 * @author chengzhy
 * @date 2021/11/12 9:59
 */
public class P46_Permutations {

    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        backtracking(result, nums, new ArrayList<>(nums.length), new boolean[nums.length]);
        return result;
    }

    private void backtracking(List<List<Integer>> result, int[] nums, List<Integer> temp, boolean[] visited) {
        if (temp.size() == nums.length) {
            result.add(new ArrayList<>(temp));
            return;
        }
        for (int i = 0; i < nums.length; i++) {
            if (!visited[i]) {
                visited[i] = true;
                temp.add(nums[i]);
                backtracking(result, nums, temp, visited);
                visited[i] = false;
                temp.remove(temp.size()-1);
            }
        }
    }

}
