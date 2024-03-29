package dynamic_programming.hard;

/**
 * 正则表达式匹配
 * <a href="https://leetcode.cn/problems/regular-expression-matching/">🔗</a>
 *
 * 给你一个字符串 s 和一个字符规律 p，请你来实现一个支持 '.' 和 '*' 的正则表达式匹配。
 *
 * '.' 匹配任意单个字符
 * '*' 匹配零个或多个前面的那一个元素
 * 所谓匹配，是要涵盖 整个 字符串 s的，而不是部分字符串。
 *
 *  
 * 示例 1：
 *
 * 输入：s = "aa", p = "a"
 * 输出：false
 * 解释："a" 无法匹配 "aa" 整个字符串。
 * 示例 2:
 *
 * 输入：s = "aa", p = "a*"
 * 输出：true
 * 解释：因为 '*' 代表可以匹配零个或多个前面的那一个元素, 在这里前面的元素就是 'a'。因此，字符串 "aa" 可被视为 'a' 重复了一次。
 * 示例 3：
 *
 * 输入：s = "ab", p = ".*"
 * 输出：true
 * 解释：".*" 表示可匹配零个或多个（'*'）任意字符（'.'）。
 *  
 *
 * 提示：
 *
 * 1 <= s.length <= 20
 * 1 <= p.length <= 30
 * s 只包含从 a-z 的小写字母。
 * p 只包含从 a-z 的小写字母，以及字符 . 和 *。
 * 保证每次出现字符 * 时，前面都匹配到有效的字符
 *
 * @author chengzhy
 * @date 2022/1/27 16:00
 */
public class P10_RegularExpressionMatching {
    public boolean isMatch(String s, String p) {
        int m = s.length(), n = p.length();
        // dp[i][j] s的前i个字符和p的前j个字符是否匹配
        boolean[][] dp = new boolean[m + 1][n + 1];
        dp[0][0] = true;
        for (int j = 1; j <= p.length(); j++) {
            // 因为题目保证每次出现字符*时，前面都匹配到有效的字符，所以dp[0][j - 2]不会有越界
            dp[0][j] = p.charAt(j - 1) == '*' ? dp[0][j - 2] : false;
        }
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if (s.charAt(i - 1) == p.charAt(j - 1) || p.charAt(j - 1) == '.') {
                    dp[i][j] = dp[i - 1][j - 1];
                } else if (p.charAt(j - 1) == '*') {
                    if (s.charAt(i - 1) != p.charAt(j - 2) && p.charAt(j - 2) != '.') {
                        /**
                         * 前一个都不能匹配上 s[i]，* 也无能为力，只能让前一个字符消失，也就是匹配0次前一个字符。
                         * 例子(ab, abc * )
                         */
                        dp[i][j] = dp[i][j - 2];
                    } else {
                        /**
                         * 例子(##b, ###b*)
                         * dp[i][j-1]就是去掉*的那部分
                         * dp[i][j-2]就是去掉多余的b*
                         * dp[i-1][j]就是看s里b多不多，多个字符匹配的情况
                         */
                        dp[i][j] = dp[i][j - 1] || dp[i][j - 2] || dp[i - 1][j];
                    }
                }
            }
        }
        return dp[m][n];
    }

}
