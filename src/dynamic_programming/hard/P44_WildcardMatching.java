package dynamic_programming.hard;

/**
 * 通配符匹配
 * <a href="https://leetcode-cn.com/problems/wildcard-matching/">🔗</a>
 *
 * 给定一个字符串 (s) 和一个字符模式 (p) ，实现一个支持 '?' 和 '*' 的通配符匹配。
 *
 * '?' 可以匹配任何单个字符。
 * '*' 可以匹配任意字符串（包括空字符串）。
 * 两个字符串完全匹配才算匹配成功。
 *
 * 说明:
 *
 * s 可能为空，且只包含从 a-z 的小写字母。
 * p 可能为空，且只包含从 a-z 的小写字母，以及字符 ? 和 *。
 * 示例 1:
 *
 * 输入:
 * s = "aa"
 * p = "a"
 * 输出: false
 * 解释: "a" 无法匹配 "aa" 整个字符串。
 * 示例 2:
 *
 * 输入:
 * s = "aa"
 * p = "*"
 * 输出: true
 * 解释: '*' 可以匹配任意字符串。
 * 示例 3:
 *
 * 输入:
 * s = "cb"
 * p = "?a"
 * 输出: false
 * 解释: '?' 可以匹配 'c', 但第二个 'a' 无法匹配 'b'。
 * 示例 4:
 *
 * 输入:
 * s = "adceb"
 * p = "*a*b"
 * 输出: true
 * 解释: 第一个 '*' 可以匹配空字符串, 第二个 '*' 可以匹配字符串 "dce".
 * 示例 5:
 *
 * 输入:
 * s = "acdcb"
 * p = "a*c?b"
 * 输出: false
 *
 * @author chengzhy
 * @date 2022/2/18 13:49
 */
public class P44_WildcardMatching {

    public boolean isMatch(String s, String p) {
        int m = s.length(), n = p.length();
        // dp[i][j] 表示字符串s的前i个字符和模式p的前j个字符是否能匹配
        boolean[][] dp = new boolean[m + 1][n + 1];
        /**
         * 边界条件：
         * 若s p都为空，则匹配，即dp[0][0] = true
         * 若s不为空 p为空，则不匹配，即dp[i][0] = false，因为boolean数组默认值为false，不用管
         * 若s为空 p不为空，只有p为连续的星号才能匹配空字符串，若某个位置不为*号，后续的都为false
         */
        dp[0][0] = true;
        // 处理边界条件为s为空 p不为空的情况
        for (int j = 1; j <= n; j++) {
            if (p.charAt(j - 1) != '*') {
                break;
            }
            dp[0][j] = true;
        }
        // 正常情况
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if (p.charAt(j - 1) == '*') {
                    /**
                     * 若p(j)为'*'，则dp[i][j]情况为
                     * 1.把*当成a-z字符，看s(i)与p(j-1)是否匹配 或者
                     * 2.把*当成空，看s(i-1)和p(j)是否匹配
                     */
                    dp[i][j] = dp[i][j - 1] || dp[i - 1][j];
                } else if (p.charAt(j - 1) == '?' || s.charAt(i - 1) == p.charAt(j - 1)) {
                    // p(j)为'?'或者s(i) == p(j)，则dp[i][j]看dp[i-1][j-1]是否匹配
                    dp[i][j] = dp[i - 1][j - 1];
                }
            }
        }
        return dp[m][n];
    }

}
