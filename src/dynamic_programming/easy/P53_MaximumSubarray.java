package dynamic_programming.easy;

/**
 * 最大子数组和
 * <a href="https://leetcode-cn.com/problems/maximum-subarray/">🔗</a>
 *
 * 给你一个整数数组 nums ，请你找出一个具有最大和的连续子数组（子数组最少包含一个元素），返回其最大和。
 *
 * 子数组 是数组中的一个连续部分。
 *
 *  
 *
 * 示例 1：
 *
 * 输入：nums = [-2,1,-3,4,-1,2,1,-5,4]
 * 输出：6
 * 解释：连续子数组 [4,-1,2,1] 的和最大，为 6 。
 * 示例 2：
 *
 * 输入：nums = [1]
 * 输出：1
 * 示例 3：
 *
 * 输入：nums = [5,4,-1,7,8]
 * 输出：23
 *  
 *
 * 提示：
 *
 * 1 <= nums.length <= 105
 * -104 <= nums[i] <= 104
 *  
 *
 * 进阶：如果你已经实现复杂度为 O(n) 的解法，尝试使用更为精妙的 分治法 求解。
 *
 * @author chengzhy
 * @date 2022/1/20 20:42
 */
public class P53_MaximumSubarray {

    public int maxSubArray(int[] nums) {
        /**
         * 假设nums数组的长度是n，下标从0到n−1。
         *
         * 我们用f(i)代表以第i个数结尾的「连续子数组的最大和」，那么很显然我们要求的答案就是：
         *
         * \max_{0 \leq i \leq n-1} \{ f(i) \}
         * 0≤i≤n−1
         * max
         * ​
         *  {f(i)}
         *
         * 因此我们只需要求出每个位置的f(i)，然后返回f数组中的最大值即可。那么我们如何求f(i) 呢？
         * 我们可以考虑nums[i]单独成为一段还是加入f(i−1)对应的那一段，这取决于nums[i]和f(i-1)+nums[i]的大小，我们希望获得一个比较大的，于是可以写出这样的动态规划转移方程：
         *
         * f(i)=max{f(i−1)+nums[i],nums[i]}
         *
         * 不难给出一个时间复杂度O(n)、空间复杂度O(n)的实现，即用一个f数组来保存f(i)的值，用一个循环求出所有f(i)。考虑到f(i)只和f(i−1)相关，于是我们可以只用一个变量pre来维护对于当前f(i)的f(i−1)的值是多少，从而让空间复杂度降低到O(1)，这有点类似「滚动数组」的思想。
         */
        int pre = 0, max = nums[0];
        for (int num : nums) {
            // 以下为核心：如果前边累加后还不如自己本身大，那就把前边的都扔掉，从自己本身重新开始累加。
            pre = Math.max(pre + num, num);
            max = Math.max(max, pre);
        }
        return max;
    }

}
