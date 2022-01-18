package greedy.medium;

/**
 * 跳跃游戏 II
 *
 * 给你一个非负整数数组 nums ，你最初位于数组的第一个位置。
 *
 * 数组中的每个元素代表你在该位置可以跳跃的最大长度。
 *
 * 你的目标是使用最少的跳跃次数到达数组的最后一个位置。
 *
 * 假设你总是可以到达数组的最后一个位置。
 *
 *  
 *
 * 示例 1:
 *
 * 输入: nums = [2,3,1,1,4]
 * 输出: 2
 * 解释: 跳到最后一个位置的最小跳跃数是 2。
 *      从下标为 0 跳到下标为 1 的位置，跳 1 步，然后跳 3 步到达数组的最后一个位置。
 * 示例 2:
 *
 * 输入: nums = [2,3,0,1,4]
 * 输出: 2
 *  
 *
 * 提示:
 *
 * 1 <= nums.length <= 104
 * 0 <= nums[i] <= 1000
 *
 * @author chengzhy
 * @date 2021/12/20 17:26
 */
public class P45_JumpGameII {

    public int jump(int[] nums) {
        // 慢
        /*if (nums.length == 1) {
            return 0;
        }
        if (nums[0] >= nums.length - 1) {
            return 1;
        }
        int count = 0, jumpMax = nums[0], position = 0;
        for (int i = 0; i < nums.length - 1; i++) {
            if (i <= jumpMax) {
                if (i + nums[i] >= nums.length - 1) {
                    count+=2;
                    break;
                } else {
                    position = (i + nums[i] > position + nums[position]) ? i : position;
                }
            } else {
                jumpMax = position + nums[position];
                i = position;
                count++;
            }
        }
        return count;*/

        // 官方解答快
        int end = 0, steps = 0, maxPosition = 0;
        for (int i = 0; i < nums.length - 1; i++) {
            // 算最远距离
            maxPosition = Math.max(maxPosition, i + nums[i]);
            // 因为必定可达，每次找到可到达的最远位置+1，就可以在线性时间内得到最少的跳跃次数。
            if (i == end) {
                end = maxPosition;
                steps++;
            }
        }
        return steps;
    }

}
