package dynamicProgramming.hard;

/**
 * @description 正则表达式匹配
 *
 * Given an input string (s) and a pattern (p), implement regular expression matching with support for '.' and '*'.
 *
 * '.' Matches any single character.
 * '*' Matches zero or more of the preceding element.
 * The matching should cover the entire input string (not partial).
 *
 * Note:
 *
 * s could be empty and contains only lowercase letters a-z.
 * p could be empty and contains only lowercase letters a-z, and characters like . or *.
 * Example 1:
 *
 * Input:
 * s = "aa"
 * p = "a"
 * Output: false
 * Explanation: "a" does not match the entire string "aa".
 * Example 2:
 *
 * Input:
 * s = "aa"
 * p = "a*"
 * Output: true
 * Explanation: '*' means zero or more of the preceding element, 'a'. Therefore, by repeating 'a' once, it becomes "aa".
 * Example 3:
 *
 * Input:
 * s = "ab"
 * p = ".*"
 * Output: true
 * Explanation: ".*" means "zero or more (*) of any character (.)".
 * Example 4:
 *
 * Input:
 * s = "aab"
 * p = "c*a*b"
 * Output: true
 * Explanation: c can be repeated 0 times, a can be repeated 1 time. Therefore, it matches "aab".
 * Example 5:
 *
 * Input:
 * s = "mississippi"
 * p = "mis*is*p*."
 * Output: false
 *
 * 给你一个字符串 s 和一个字符规律 p，请你来实现一个支持 '.' 和 '*' 的正则表达式匹配。
 * '.' 匹配任意单个字符
 * '*' 匹配零个或多个前面的那一个元素
 * 所谓匹配，是要涵盖 整个 字符串 s的，而不是部分字符串。
 * 说明:
 * s 可能为空，且只包含从 a-z 的小写字母。
 * p 可能为空，且只包含从 a-z 的小写字母，以及字符 . 和 *。
 */
public class P10_RegularExpressionMatching {
    public boolean isMatch(String s, String p) {
        if (s.equals(p)) {
            return true;
        }

        char[] sArr = s.toCharArray();
        char[] pArr = p.toCharArray();

        // dp[i][j] => is s[0, i - 1] match p[0, j - 1] ?
        boolean[][] dp = new boolean[sArr.length + 1][pArr.length + 1];

        dp[0][0] = true;

        for (int i = 1; i <= pArr.length; ++i) {
            dp[0][i] = pArr[i - 1] == '*' ? dp[0][i - 2] : false;
        }

        for (int i = 1; i <= sArr.length; ++i) {
            for (int j = 1; j <= pArr.length; ++j) {
                if (sArr[i - 1] == pArr[j - 1] || pArr[j - 1] == '.') {
                    // 看 s[0,...i-1] 和 p[0,...j-1]
                    dp[i][j] = dp[i - 1][j - 1];
                }

                if (pArr[j - 1] == '*') {
                    // 看 s[0,...i] 和 p[0,...j-2]
                    dp[i][j] |= dp[i][j - 2];

                    if (pArr[j - 2] == sArr[i - 1] || pArr[j - 2] == '.') {
                        // 看 s[0,...i-1] 和 p[0,...j]
                        dp[i][j] |= dp[i - 1][j];
                    }
                }
            }
        }

        return dp[sArr.length][pArr.length];
    }

}
