package dynamic_programming.medium;

/**
 * 分割等和子集
 * <a href="https://leetcode-cn.com/problems/partition-equal-subset-sum/">🔗</a>
 *
 * 给你一个 只包含正整数 的 非空 数组 nums 。请你判断是否可以将这个数组分割成两个子集，使得两个子集的元素和相等。
 *
 *  
 *
 * 示例 1：
 *
 * 输入：nums = [1,5,11,5]
 * 输出：true
 * 解释：数组可以分割成 [1, 5, 5] 和 [11] 。
 * 示例 2：
 *
 * 输入：nums = [1,2,3,5]
 * 输出：false
 * 解释：数组不能分割成两个元素和相等的子集。
 *  
 *
 * 提示：
 *
 * 1 <= nums.length <= 200
 * 1 <= nums[i] <= 100
 *
 * @author chengzhy
 * @date 2022/4/7 11:08
 */
public class P416_PartitionEqualSubsetSum {

    public boolean canPartition(int[] nums) {
        int sum = 0;
        for (int num : nums) {
            sum += num;
        }
        if ((sum & 1) == 0) {
            sum >>= 1;
            /**
             * dp[j]数组表示容量为j的背包，所能放物品的最大价值是dp[j]
             * 根据题意可以将其转换为01背包问题，从nums中(物品)找到价值为sum / 2的子集即可
             * 物品的重量和价值都等于nums[i]
             */
            int[] dp = new int[sum + 1];
            // 遍历物品
            for (int i = 0; i < nums.length; i++) {
                // 遍历背包
                for (int j = sum; j >= nums[i]; j--) {
                    dp[j] = Math.max(dp[j], dp[j - nums[i]] + nums[i]);
                }
            }
            // 因为物品的重量和价值都是nums[i]，所以容量为j的背包所能放的物品的最大价值dp[j]一定是≤j的
            return dp[sum] == sum;
        }
        return false;
    }

}
