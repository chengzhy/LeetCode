package dynamic_programming.hard;

/**
 * 编辑距离
 *
 * 给你两个单词 word1 和 word2， 请返回将 word1 转换成 word2 所使用的最少操作数  。
 *
 * 你可以对一个单词进行如下三种操作：
 *
 * 插入一个字符
 * 删除一个字符
 * 替换一个字符
 *  
 *
 * 示例 1：
 *
 * 输入：word1 = "horse", word2 = "ros"
 * 输出：3
 * 解释：
 * horse -> rorse (将 'h' 替换为 'r')
 * rorse -> rose (删除 'r')
 * rose -> ros (删除 'e')
 * 示例 2：
 *
 * 输入：word1 = "intention", word2 = "execution"
 * 输出：5
 * 解释：
 * intention -> inention (删除 't')
 * inention -> enention (将 'i' 替换为 'e')
 * enention -> exention (将 'n' 替换为 'x')
 * exention -> exection (将 'n' 替换为 'c')
 * exection -> execution (插入 'u')
 *  
 *
 * 提示：
 *
 * 0 <= word1.length, word2.length <= 500
 * word1 和 word2 由小写英文字母组成
 *
 * @author chengzhy
 * @date 2022/3/18 11:08
 */
public class P72_EditDistance {

    public int minDistance(String word1, String word2) {
        int length1 = word1.length(), length2 = word2.length();
        // dp[i][j]数组表示word1的前i个字母转变成word2的前j个字母所需要的最小操作数
        int[][] dp = new int[length1 + 1][length2 + 1];
        // 初始化dp[i][0]情况，即word1的前i个字母变成word2的前0个字母(即变成空)所需要的最小操作数
        for (int i = 0; i <= length1; i++) {
            // 此种情况的最小操作数即为删除前i个字母的数量
            dp[i][0] = i;
        }
        // 初始化dp[0][j]情况，即word1的前0个字母(即空添加字母)变成word2的前j个字母所需要的最小操作数
        for (int j = 0; j <= length2; j++) {
            // 此种情况的最小操作数即为添加前j个字母的数量
            dp[0][j] = j;
        }
        for (int i = 1; i <= length1; i++) {
            for (int j = 1; j <= length2; j++) {
                /**
                 * 如果word1的第i个字母和word2的第j个字母相等
                 * 则所需要的最小操作数是word1的前i - 1个字母变成word2的前j - 1个字母的最小操作数
                 * 即dp[i][j] = dp[i - 1][j - 1];
                 */
                if (word1.charAt(i - 1) == word2.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1];
                } else {
                    /**
                     * 如果word1的第i个字母和word2的第j个字母不相等
                     * 那么取增删替操作的最小值+1
                     * 增对应dp[i][j - 1] + 1，表示word1的前i个字母只变成了word2的前j - 1个字母，所以需要增加一个字母
                     * 删对应dp[i - 1][j] + 1，表示word1的前i - 1个字母变成了word2的前j个字母，需要删除多余的word1的第i个字母
                     * 替对应dp[i - 1][j - 1] + 1，表示word1的前i - 1个字母变成了word2的前j - 1个字母，剩下一位只需替换即可
                     */
                    dp[i][j] = 1 + Math.min(Math.min(dp[i][j - 1], dp[i - 1][j]), dp[i - 1][j - 1]);
                }
            }
        }
        return dp[length1][length2];
    }

}
