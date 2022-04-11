package dynamic_programming.hard;

/**
 * 不同的子序列
 * <a href="https://leetcode-cn.com/problems/distinct-subsequences/">🔗</a>
 *
 * 给定一个字符串 s 和一个字符串 t ，计算在 s 的子序列中 t 出现的个数。
 *
 * 字符串的一个 子序列 是指，通过删除一些（也可以不删除）字符且不干扰剩余字符相对位置所组成的新字符串。（例如，"ACE" 是 "ABCDE" 的一个子序列，而 "AEC" 不是）
 *
 * 题目数据保证答案符合 32 位带符号整数范围。
 *
 *  
 *
 * 示例 1：
 *
 * 输入：s = "rabbbit", t = "rabbit"
 * 输出：3
 * 解释：
 * 如下图所示, 有 3 种可以从 s 中得到 "rabbit" 的方案。
 * rabbbit
 * rabbbit
 * rabbbit
 * 示例 2：
 *
 * 输入：s = "babgbag", t = "bag"
 * 输出：5
 * 解释：
 * 如下图所示, 有 5 种可以从 s 中得到 "bag" 的方案。
 * babgbag
 * babgbag
 * babgbag
 * babgbag
 * babgbag
 *  
 *
 * 提示：
 *
 * 0 <= s.length, t.length <= 1000
 * s 和 t 由英文字母组成
 *
 * @author chengzhy
 * @date 2022/3/21 14:05
 */
public class P115_DistinctSubsequences {

    public int numDistinct(String s, String t) {
        if (s.length() < t.length()) {
            return 0;
        }
        // dp[i][j]数组表示以s的第i个字母结尾的子序列中 能出现以t的第j个字母结尾的字符串 的子序列的数量(0时表示空字符串)
        int m = s.length(), n = t.length();
        // 加1是有空字符串的情况
        int[][] dp = new int[m + 1][n + 1];
        // 以s的第i个字母结尾的子序列中 能出现以t的第0个字母结尾(即空字符串) 的 子序列的数量，该子序列只有也为空字符串时才满足
        for (int i = 0; i <= m; i++) {
            dp[i][0] = 1;
        }
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if (s.charAt(i - 1) == t.charAt(j - 1)) {
                    /**
                     * 当s的第i个字母和t的第j个字母相等时，
                     * 一部分是用s[i]来和t[j]匹配，那么个数为dp[i - 1][j - 1]；
                     * 一部分是不用s[i]来和t[j]匹配(让s[i - 1]和t[j]匹配)，个数为dp[i - 1][j]。
                     * 为什么有不用s[i]来和t[j]匹配的情况：
                     * 例如s：bagg和t：bag，s[3]和t[2]是相同的，但是字符串s也可以不用s[3]来匹配，即用s[0]s[1]s[2]组成的bag。
                     */
                    dp[i][j] = dp[i - 1][j - 1] + dp[i - 1][j];
                } else {
                    /**
                     * 当s的第i个字母和t的第j个字母不相等时，
                     * 即只有不用s[i]来和t[j]匹配的情况(让s[i - 1]和t[j]匹配)，个数为dp[i - 1][j]
                     */
                    dp[i][j] = dp[i - 1][j];
                }
            }
        }
        return dp[m][n];
    }

}
