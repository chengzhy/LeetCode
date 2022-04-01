package dynamic_programming.easy;

/**
 * 判断子序列
 *
 * 给定字符串 s 和 t ，判断 s 是否为 t 的子序列。
 *
 * 字符串的一个子序列是原始字符串删除一些（也可以不删除）字符而不改变剩余字符相对位置形成的新字符串。（例如，"ace"是"abcde"的一个子序列，而"aec"不是）。
 *
 * 进阶：
 *
 * 如果有大量输入的 S，称作 S1, S2, ... , Sk 其中 k >= 10亿，你需要依次检查它们是否为 T 的子序列。在这种情况下，你会怎样改变代码？
 *
 * 致谢：
 *
 * 特别感谢 @pbrother 添加此问题并且创建所有测试用例。
 *
 *  
 *
 * 示例 1：
 *
 * 输入：s = "abc", t = "ahbgdc"
 * 输出：true
 * 示例 2：
 *
 * 输入：s = "axc", t = "ahbgdc"
 * 输出：false
 *  
 *
 * 提示：
 *
 * 0 <= s.length <= 100
 * 0 <= t.length <= 10^4
 * 两个字符串都只由小写字符组成。
 *
 * @author chengzhy
 * @date 2022/4/1 14:05
 */
public class P392_IsSubsequence {

    public boolean isSubsequence(String s, String t) {
        int m = s.length(), n = t.length();
        if (m > n) {
            return false;
        }
        // dp[i][j]数组表示s的前i个字母中和t的前j个字母中是否有公共子序列，加1是有空字符串的情况
        boolean[][] dp = new boolean[m + 1][n + 1];
        // 初始化s为空字符串的情况
        for (int i = 0; i <= n; i++) {
            dp[0][i] = true;
        }
        for (int j = 1; j <= n; j++) {
            for (int i = 1; i <= m; i++) {
                if (j < i) {
                    dp[i][j] = false;
                } else if (t.charAt(j - 1) == s.charAt(i - 1)) {
                    // 相等看前i - 1和前j - 1字母
                    dp[i][j] = dp[i - 1][j - 1];
                } else {
                    if (j == i) {
                        dp[i][j] = false;
                    } else {
                        dp[i][j] = dp[i][j - 1];
                    }
                }
            }
        }
        return dp[m][n];
        /**
         * 双指针做法：贪心
         */
        /*int i = 0, j = 0;
        while (i < m && j < n) {
            if (s.charAt(i) == t.charAt(j)) {
                i++;
            }
            j++;
        }
        return i == m;*/
    }

}
