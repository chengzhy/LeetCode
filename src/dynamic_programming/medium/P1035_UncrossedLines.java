package dynamic_programming.medium;

/**
 * 不相交的线
 * <a href="https://leetcode.cn/problems/uncrossed-lines/">🔗</a>
 *
 * 在两条独立的水平线上按给定的顺序写下 nums1 和 nums2 中的整数。
 *
 * 现在，可以绘制一些连接两个数字 nums1[i] 和 nums2[j] 的直线，这些直线需要同时满足满足：
 *
 *  nums1[i] == nums2[j]
 * 且绘制的直线不与任何其他连线（非水平线）相交。
 * 请注意，连线即使在端点也不能相交：每个数字只能属于一条连线。
 *
 * 以这种方法绘制线条，并返回可以绘制的最大连线数。
 *
 *  
 *
 * 示例 1：
 *
 *
 * 输入：nums1 = [1,4,2], nums2 = [1,2,4]
 * 输出：2
 * 解释：可以画出两条不交叉的线，如上图所示。
 * 但无法画出第三条不相交的直线，因为从 nums1[1]=4 到 nums2[2]=4 的直线将与从 nums1[2]=2 到 nums2[1]=2 的直线相交。
 * 示例 2：
 *
 * 输入：nums1 = [2,5,1,2,5], nums2 = [10,5,2,1,5,2]
 * 输出：3
 * 示例 3：
 *
 * 输入：nums1 = [1,3,7,1,7,5], nums2 = [1,9,2,5,1]
 * 输出：2
 *  
 *
 * 提示：
 *
 * 1 <= nums1.length, nums2.length <= 500
 * 1 <= nums1[i], nums2[j] <= 2000
 *
 * @author chengzhy
 * @date 2022/3/28 17:30
 */
public class P1035_UncrossedLines {

    public int maxUncrossedLines(int[] nums1, int[] nums2) {
        // 与题1143类似，子序列问题
        /**
         * 直线不能相交，这就是说明在数组A中找到一个与数组B相同的子序列，且这个子序列不能改变相对顺序，
         * 只要相对顺序不改变，链接相同数字的直线就不会相交。
         * 其实就是求两个字符串的最长公共子序列的长度。
         */
        int m = nums1.length, n = nums2.length;
        // dp[i][j]数组表示nums1的前i个数和nums2的前j个数中公共子序列的最长长度
        int[][] dp = new int[m + 1][n + 1];
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if (nums1[i - 1] == nums2[j - 1]) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                } else {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                }
            }
        }
        return dp[m][n];
    }

}
