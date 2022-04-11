package dynamic_programming.medium;

/**
 * 删除并获得点数
 * <a href="https://leetcode-cn.com/problems/delete-and-earn/">🔗</a>
 *
 * 给你一个整数数组 nums ，你可以对它进行一些操作。
 *
 * 每次操作中，选择任意一个 nums[i] ，删除它并获得 nums[i] 的点数。之后，你必须删除 所有 等于 nums[i] - 1 和 nums[i] + 1 的元素。
 *
 * 开始你拥有 0 个点数。返回你能通过这些操作获得的最大点数。
 *
 *  
 *
 * 示例 1：
 *
 * 输入：nums = [3,4,2]
 * 输出：6
 * 解释：
 * 删除 4 获得 4 个点数，因此 3 也被删除。
 * 之后，删除 2 获得 2 个点数。总共获得 6 个点数。
 * 示例 2：
 *
 * 输入：nums = [2,2,3,3,3,4]
 * 输出：9
 * 解释：
 * 删除 3 获得 3 个点数，接着要删除两个 2 和 4 。
 * 之后，再次删除 3 获得 3 个点数，再次删除 3 获得 3 个点数。
 * 总共获得 9 个点数。
 *  
 *
 * 提示：
 *
 * 1 <= nums.length <= 2 * 104
 * 1 <= nums[i] <= 104
 *
 * @author chengzhy
 * @date 2022/2/5 21:28
 */
public class P740_DeleteAndEarn {

    public int deleteAndEarn(int[] nums) {
        /**
         * 参考评论思路：
         * 题意为选择一个数n，就不能选择n-1和n+1，这类似于打家劫舍选择一个节点就不能选择其相邻的节点一样
         * 因此可以将其转化为打家劫舍问题，用一个新数组记录nums中各个数的个数，来转换为解决打家劫舍这个新数组的问题
         * 举个例子：
         * nums = [2, 2, 3, 3, 3, 4]
         * 构造后：
         * all=[0, 2, 3, 1];
         * 就是代表着2的个数有2个，3的个数有3个，4的个数有1个。
         * 其实这样就可以变成打家劫舍的问题了呗。
         */
        if (nums.length == 1) {
            return nums[0];
        }
        int max = 0;
        for (int i = 0; i < nums.length; i++) {
            max = Math.max(max, nums[i]);
        }
        // 一个新数组记录nums中各个数的个数
        int[] numCount = new int[max];
        for (int num : nums) {
            numCount[num - 1]++;
        }
        // 转换后直接延用打家劫舍问题解决方案
        if (max == 2) {
            return Math.max(numCount[0], numCount[1] << 1);
        }
        int dp1 = numCount[0], dp2 = Math.max(numCount[0], numCount[1] << 1);
        for (int i = 2; i < numCount.length; i++) {
            int temp = dp2;
            dp2 = Math.max(dp1 + numCount[i] * (i + 1), dp2);
            dp1 = temp;
        }
        return dp2;
    }

}
