package dynamic_programming.medium;

/**
 * 最后一块石头的重量 II
 *
 * 有一堆石头，用整数数组 stones 表示。其中 stones[i] 表示第 i 块石头的重量。
 *
 * 每一回合，从中选出任意两块石头，然后将它们一起粉碎。假设石头的重量分别为 x 和 y，且 x <= y。那么粉碎的可能结果如下：
 *
 * 如果 x == y，那么两块石头都会被完全粉碎；
 * 如果 x != y，那么重量为 x 的石头将会完全粉碎，而重量为 y 的石头新重量为 y-x。
 * 最后，最多只会剩下一块 石头。返回此石头 最小的可能重量 。如果没有石头剩下，就返回 0。
 *
 *  
 *
 * 示例 1：
 *
 * 输入：stones = [2,7,4,1,8,1]
 * 输出：1
 * 解释：
 * 组合 2 和 4，得到 2，所以数组转化为 [2,7,1,8,1]，
 * 组合 7 和 8，得到 1，所以数组转化为 [2,1,1,1]，
 * 组合 2 和 1，得到 1，所以数组转化为 [1,1,1]，
 * 组合 1 和 1，得到 0，所以数组转化为 [1]，这就是最优值。
 * 示例 2：
 *
 * 输入：stones = [31,26,33,21,40]
 * 输出：5
 *  
 *
 * 提示：
 *
 * 1 <= stones.length <= 30
 * 1 <= stones[i] <= 100
 *
 * @author chengzhy
 * @date 2022/4/7 11:47
 */
public class P1049_LastStoneWeightII {

    public int lastStoneWeightII(int[] stones) {
        int weight = 0;
        for (int stone : stones) {
            weight += stone;
        }
        /**
         * 参考代码随想录题解：此题和题416思路一样，本质上是尽量让石头分成重量相同的两堆，
         * 相撞之后剩下的石头最小，所以背包容量设为石头总重量的一半(石头总重量为奇数时除以2刚好是向下取整)
         * 此题目就相当于转换成求背包容量为石头总重量的一半时怎么装石头才能最接近装满
         * 因为石头的价值就是石头的重量，所以容量为j的背包所能放的石头的最大价值dp[j]一定是≤j的
         */
        // 背包容量为石头总重量的一半
        int bagWeight = weight >> 1;
        // dp[j]数组表示容量为j的背包，能够背dp[j]这么多价值的石头
        int[] dp = new int[bagWeight + 1];
        for (int i = 0; i < stones.length; i++) {
            for (int j = bagWeight; j >= stones[i]; j--) {
                dp[j] = Math.max(dp[j], dp[j - stones[i]] + stones[i]);
            }
        }
        // 返回差值
        return weight - (dp[bagWeight] << 1);
    }

}
