package dynamic_programming.medium;

/**
 * 一和零
 * <a href="https://leetcode-cn.com/problems/ones-and-zeroes/">🔗</a>
 *
 * 给你一个二进制字符串数组 strs 和两个整数 m 和 n 。
 *
 * 请你找出并返回 strs 的最大子集的长度，该子集中 最多 有 m 个 0 和 n 个 1 。
 *
 * 如果 x 的所有元素也是 y 的元素，集合 x 是集合 y 的 子集 。
 *
 *  
 *
 * 示例 1：
 *
 * 输入：strs = ["10", "0001", "111001", "1", "0"], m = 5, n = 3
 * 输出：4
 * 解释：最多有 5 个 0 和 3 个 1 的最大子集是 {"10","0001","1","0"} ，因此答案是 4 。
 * 其他满足题意但较小的子集包括 {"0001","1"} 和 {"10","1","0"} 。{"111001"} 不满足题意，因为它含 4 个 1 ，大于 n 的值 3 。
 * 示例 2：
 *
 * 输入：strs = ["10", "0", "1"], m = 1, n = 1
 * 输出：2
 * 解释：最大的子集是 {"0", "1"} ，所以答案是 2 。
 *  
 *
 * 提示：
 *
 * 1 <= strs.length <= 600
 * 1 <= strs[i].length <= 100
 * strs[i] 仅由 '0' 和 '1' 组成
 * 1 <= m, n <= 100
 *
 * @author chengzhy
 * @date 2022/4/8 15:21
 */
public class P474_OnesAndZeroes {

    public int findMaxForm(String[] strs, int m, int n) {
        int length = strs.length;
        /*// dp[i][j][k]数组表示在前i个字符串中，满足m个0和n个1的情况下最多拥有的字符串的数量
        int[][][] dp = new int[length + 1][m + 1][n + 1];
        // i = 0即不选任何字符串时 最多拥有的字符串的数量为0
        for (int i = 1; i <= length; i++) {
            String str = strs[i - 1];
            int zeroNum = getZeroNum(str), oneNum = str.length() - zeroNum;
            for (int j = 0; j <= m; j++) {
                for (int k = 0; k <= n; k++) {
                    if (j < zeroNum || k < oneNum) {
                        // 当前字符串的0和1数量超过了m或n的情况，只能不选当前字符串
                        dp[i][j][k] = dp[i - 1][j][k];
                    } else {
                        dp[i][j][k] = Math.max(dp[i - 1][j][k], dp[i - 1][j - zeroNum][k - oneNum] + 1);
                    }
                }
            }
        }
        return dp[length][m][n];*/
        // 三维dp数组简化成二维dp数组
        int[][] dp = new int[m + 1][n + 1];
        for (int i = 1; i <= length; i++) {
            String str = strs[i - 1];
            int zeroNum = getZeroNum(str), oneNum = str.length() - zeroNum;
            for (int j = m; j >= zeroNum; j--) {
                for (int k = n; k >= oneNum; k--) {
                    dp[j][k] = Math.max(dp[j][k], dp[j - zeroNum][k - oneNum] + 1);
                }
            }
        }
        return dp[m][n];
    }

    private int getZeroNum(String str) {
        int count = 0;
        for (char c : str.toCharArray()) {
            if (c == '0') {
                count++;
            }
        }
        return count;
    }

}
