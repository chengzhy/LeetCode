package dynamic_programming.medium;

/**
 * 目标和
 *
 * 给你一个整数数组 nums 和一个整数 target 。
 *
 * 向数组中的每个整数前添加 '+' 或 '-' ，然后串联起所有整数，可以构造一个 表达式 ：
 *
 * 例如，nums = [2, 1] ，可以在 2 之前添加 '+' ，在 1 之前添加 '-' ，然后串联起来得到表达式 "+2-1" 。
 * 返回可以通过上述方法构造的、运算结果等于 target 的不同 表达式 的数目。
 *
 *  
 *
 * 示例 1：
 *
 * 输入：nums = [1,1,1,1,1], target = 3
 * 输出：5
 * 解释：一共有 5 种方法让最终目标和为 3 。
 * -1 + 1 + 1 + 1 + 1 = 3
 * +1 - 1 + 1 + 1 + 1 = 3
 * +1 + 1 - 1 + 1 + 1 = 3
 * +1 + 1 + 1 - 1 + 1 = 3
 * +1 + 1 + 1 + 1 - 1 = 3
 * 示例 2：
 *
 * 输入：nums = [1], target = 1
 * 输出：1
 *  
 *
 * 提示：
 *
 * 1 <= nums.length <= 20
 * 0 <= nums[i] <= 1000
 * 0 <= sum(nums[i]) <= 1000
 * -1000 <= target <= 1000
 *
 * @author chengzhy
 * @date 2022/4/8 13:18
 */
public class P494_TargetSum {

    public int findTargetSumWays(int[] nums, int target) {
        /**
         * 根据题意可知，要构成target，一定是分成两个集合(一个是专门+的，一个是专门-的)
         * 设专门-号的数的和为x，则(sum - x) - x = target，==>x = (sum - target) / 2;
         * 就可以转换为选取nums中的前i个元素使得元素之和为x有几种方法
         */
        int sum = 0;
        for (int num : nums) {
            sum += num;
        }
        if (((sum - target) & 1) == 1 || sum < target) {
            // sum - target为奇数时 或者 sum小于target时 是没有方法能够成的
            return 0;
        }
        int x = (sum - target) >> 1;
        // dp[i][j]数组表示在nums的前i个数中选取元素，使得这些元素之和等于j的方案数。
        int[][] dp = new int[nums.length + 1][x + 1];
        // 初始化边界值：没有元素可被选取时，为0的方案数为1，>0的方案数为0
        dp[0][0] = 1;
        for (int i = 1; i <= nums.length; i++) {
            for (int j = 0; j <= x; j++) {
                if (j < nums[i - 1]) {
                    // 要构成的元素之和j小于当前选取的元素的值，dp[i][j]就等于不选当前元素即dp[i - 1][j]的方案数
                    dp[i][j] = dp[i - 1][j];
                } else {
                    // dp[i][j] = 不选当前元素的方案数 + 选当前元素的方案数
                    dp[i][j] = dp[i - 1][j] + dp[i - 1][j - nums[i - 1]];
                }
            }
        }
        return dp[nums.length][x];
    }

}
