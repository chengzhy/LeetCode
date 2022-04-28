package two_pointers.hard;

/**
 * 接雨水
 * <a href="https://leetcode.cn/problems/trapping-rain-water/">🔗</a>
 *
 * 给定 n 个非负整数表示每个宽度为 1 的柱子的高度图，计算按此排列的柱子，下雨之后能接多少雨水。
 *
 *  
 *
 * 示例 1：
 *
 *
 *
 * 输入：height = [0,1,0,2,1,0,1,3,2,1,2,1]
 * 输出：6
 * 解释：上面是由数组 [0,1,0,2,1,0,1,3,2,1,2,1] 表示的高度图，在这种情况下，可以接 6 个单位的雨水（蓝色部分表示雨水）。
 * 示例 2：
 *
 * 输入：height = [4,2,0,3,2,5]
 * 输出：9
 *  
 *
 * 提示：
 *
 * n == height.length
 * 1 <= n <= 2 * 104
 * 0 <= height[i] <= 105
 *
 * @author chengzhy
 * @date 2021/11/5 15:15
 */
public class P42_TrappingRainWater {

    /**
     * 对于下标i，下雨后水能到达的最大高度等于下标i两边的最大高度的最小值;
     * 下标i处能接的雨水量等于下标i处的水能到达的最大高度减去height[i]。
     */
    public int trap(int[] height) {
        int result = 0;
        // 双指针
        int left = 0, right = height.length - 1,
                leftMax = 0, rightMax = 0;
        while (left < right) {
            // 记录左边最大高度
            leftMax = Math.max(leftMax, height[left]);
            // 记录右边最大高度
            rightMax = Math.max(rightMax, height[right]);
            if (height[left] < height[right]) {
                // 左边小算左边：最大高度-height[i]
                result += leftMax - height[left++];
            } else {
                // 右边小算右边：最大高度-height[i]
                result += rightMax - height[right--];
            }
        }
        return result;
    }

}
