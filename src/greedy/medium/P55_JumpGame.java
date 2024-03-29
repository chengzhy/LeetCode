package greedy.medium;

/**
 * 跳跃游戏
 * <a href="https://leetcode.cn/problems/jump-game/">🔗</a>
 *
 * 给定一个非负整数数组 nums ，你最初位于数组的 第一个下标 。
 *
 * 数组中的每个元素代表你在该位置可以跳跃的最大长度。
 *
 * 判断你是否能够到达最后一个下标。
 *
 *  
 *
 * 示例 1：
 *
 * 输入：nums = [2,3,1,1,4]
 * 输出：true
 * 解释：可以先跳 1 步，从下标 0 到达下标 1, 然后再从下标 1 跳 3 步到达最后一个下标。
 * 示例 2：
 *
 * 输入：nums = [3,2,1,0,4]
 * 输出：false
 * 解释：无论怎样，总会到达下标为 3 的位置。但该下标的最大跳跃长度是 0 ， 所以永远不可能到达最后一个下标。
 *  
 *
 * 提示：
 *
 * 1 <= nums.length <= 3 * 104
 * 0 <= nums[i] <= 105
 *
 * @author chengzhy
 * @date 2021/12/20 13:34
 */
public class P55_JumpGame {

    public boolean canJump(int[] nums) {
        /**
         * 笨
         */
        /*for (int i = 0; i < nums.length; ) {
            if (i + nums[i] >= nums.length - 1) {
                return true;
            }
            if (nums[i] == 0) {
                return false;
            }
            int temp = 0, j = 1;
            for (; j <= nums[i]; j++) {
                temp += nums[i + j];
                if (temp >= nums[i]) {
                    break;
                }
            }
            i += (j == nums[i] + 1) ? j - 1 : j;
        }
        return false;*/
        /**
         * 贪心算法官方解答
         *
         * 当前遍历到的位置x，如果它在最远可以到达的位置的范围内，那么我们就可以从起点通过若干次跳跃到达该位置
         * 在遍历的过程中，如果最远可以到达的位置大于等于数组中的最后一个位置，那就说明最后一个位置可达，我们就可以直接返回 True 作为答案。
         * 反之，如果在遍历结束后，最后一个位置仍然不可达，我们就返回 False 作为答案。
         */
        int rightMost = 0;
        for (int i = 0; i < nums.length; i++) {
            if (i <= rightMost) {
                rightMost = Math.max(rightMost, i + nums[i]);
                if (rightMost >= nums.length - 1) {
                    return true;
                }
            }
        }
        return false;
    }

}
