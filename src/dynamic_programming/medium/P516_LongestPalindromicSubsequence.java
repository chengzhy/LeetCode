package dynamic_programming.medium;

/**
 * 最长回文子序列
 * <a href="https://leetcode-cn.com/problems/longest-palindromic-subsequence/">🔗</a>
 *
 * 给你一个字符串 s ，找出其中最长的回文子序列，并返回该序列的长度。
 *
 * 子序列定义为：不改变剩余字符顺序的情况下，删除某些字符或者不删除任何字符形成的一个序列。
 *
 *  
 *
 * 示例 1：
 *
 * 输入：s = "bbbab"
 * 输出：4
 * 解释：一个可能的最长回文子序列为 "bbbb" 。
 * 示例 2：
 *
 * 输入：s = "cbbd"
 * 输出：2
 * 解释：一个可能的最长回文子序列为 "bb" 。
 *  
 *
 * 提示：
 *
 * 1 <= s.length <= 1000
 * s 仅由小写英文字母组成
 *
 * @author chengzhy
 * @date 2022/4/2 16:46
 */
public class P516_LongestPalindromicSubsequence {

    public int longestPalindromeSubseq(String s) {
        int length = s.length();
        // dp[i][j]数组表示s的[i, j]区间中回文子序列的最长长度
        int[][] dp = new int[length][length];
        // 初始化一个字母区间的情况
        for (int i = 0; i < length; i++) {
            dp[i][i] = 1;
        }
        for (int i = length - 1; i >= 0; i--) {
            // 注意这里j的初始值，j要大于i才可以，j<i，区间[i, j]就不成立，等于是二维数组的右上角的范围才是可操作的
            for (int j = i + 1; j < length; j++) {
                if (s.charAt(i) == s.charAt(j)) {
                    // 两个字母相等，就等于区间[i + 1, j - 1]的回文子序列的最长长度 + 2
                    dp[i][j] = dp[i + 1][j - 1] + 2;
                } else {
                    // 不相等，就取 区间[i + 1, j] 和 区间[i, j - 1] 二者的最大值
                    dp[i][j] = Math.max(dp[i + 1][j], dp[i][j - 1]);
                }
            }
        }
        return dp[0][length - 1];
    }

}
