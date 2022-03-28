package dynamic_programming.medium;

/**
 * 最长重复子数组
 *
 * 给两个整数数组 nums1 和 nums2 ，返回 两个数组中 公共的 、长度最长的子数组的长度 。
 *
 *  
 *
 * 示例 1：
 *
 * 输入：nums1 = [1,2,3,2,1], nums2 = [3,2,1,4,7]
 * 输出：3
 * 解释：长度最长的公共子数组是 [3,2,1] 。
 * 示例 2：
 *
 * 输入：nums1 = [0,0,0,0,0], nums2 = [0,0,0,0,0]
 * 输出：5
 *  
 *
 * 提示：
 *
 * 1 <= nums1.length, nums2.length <= 1000
 * 0 <= nums1[i], nums2[i] <= 100
 *
 * @author chengzhy
 * @date 2022/3/28 15:30
 */
public class P718_MaximumLengthOfRepeatedSubarray {

    public int findLength(int[] nums1, int[] nums2) {
        // 子数组默认连续
        int m = nums1.length, n = nums2.length;
        /**
         * dp[i][j]数组表示以nums1的第i个数字结尾的子数组(不一定从下标0开始)和
         * 以nums2的第j个数字结尾的子数组(不一定从下标0开始)其中拥有公共部分的长度
         */
        int result = 0;
        /*int[][] dp = new int[m][n];
        // 初始化
        for (int i = 0; i < m; i++) {
            // 当nums1中的第i个数字等于nums2中的第0个数字
            if (nums1[i] == nums2[0]) {
                dp[i][0] = 1;
                result = 1;
            }
        }
        for (int j = 0; j < n; j++) {
            // 当nums2中的第j个数字等于nums1中的第0个数字
            if (nums2[j] == nums1[0]) {
                dp[0][j] = 1;
                result = 1;
            }
        }
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                if (nums1[i] == nums2[j]) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                }
                // 更新result的值
                if (dp[i][j] > result) {
                    result = dp[i][j];
                }
            }
        }*/
        /**
         * 上面过程简化一下，将dp数组的i和j都加1，就能简化上面的初始化过程
         */
        int[][] dp = new int[m + 1][n + 1];
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if (nums1[i - 1] == nums2[j - 1]) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                }
                if (dp[i][j] > result) {
                    result = dp[i][j];
                }
            }
        }
        return result;
    }

}
