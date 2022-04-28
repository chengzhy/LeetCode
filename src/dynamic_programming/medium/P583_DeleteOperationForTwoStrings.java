package dynamic_programming.medium;

/**
 * 两个字符串的删除操作
 * <a href="https://leetcode.cn/problems/delete-operation-for-two-strings/">🔗</a>
 *
 * 给定两个单词 word1 和 word2 ，返回使得 word1 和  word2 相同所需的最小步数。
 *
 * 每步 可以删除任意一个字符串中的一个字符。
 *
 *  
 *
 * 示例 1：
 *
 * 输入: word1 = "sea", word2 = "eat"
 * 输出: 2
 * 解释: 第一步将 "sea" 变为 "ea" ，第二步将 "eat "变为 "ea"
 * 示例  2:
 *
 * 输入：word1 = "leetcode", word2 = "etco"
 * 输出：4
 *  
 *
 * 提示：
 *
 * 1 <= word1.length, word2.length <= 500
 * word1 和 word2 只包含小写英文字母
 *
 * @author chengzhy
 * @date 2022/4/1 15:16
 */
public class P583_DeleteOperationForTwoStrings {

    public int minDistance(String word1, String word2) {
        int m = word1.length(), n = word2.length();
        // dp[i][j]数组表示将word1的前i个字母和word2的前j个字母变成一样所需要的最少步数
        int[][] dp = new int[m + 1][n + 1];
        // 初始化word2为空字符串的情况
        for (int i = 0; i <= m; i++) {
            dp[i][0] = i;
        }
        // 初始化word1为空字符串的情况
        for (int i = 0; i <= n; i++) {
            dp[0][i] = i;
        }
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if (word1.charAt(i - 1) == word2.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1];
                } else {
                    /**
                     * 取 使word1(0-i)和word2(0-j-1)相同的最少操作次数，加上删除word2的第j个字母的一次操作
                     * 和 使word1(0-i-1)和word2(0,j)相同的最少操作次数，加上删除word1的第i个字母的一次操作
                     * 二者中的最小值
                     */
                    dp[i][j] = Math.min(dp[i][j - 1], dp[i - 1][j]) + 1;
                }
            }
        }
        return dp[m][n];
    }

}
