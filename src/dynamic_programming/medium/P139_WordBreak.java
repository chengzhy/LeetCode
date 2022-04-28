package dynamic_programming.medium;

import java.util.List;

/**
 * 单词拆分
 * <a href="https://leetcode.cn/problems/word-break/">🔗</a>
 *
 * 给你一个字符串 s 和一个字符串列表 wordDict 作为字典。请你判断是否可以利用字典中出现的单词拼接出 s 。
 *
 * 注意：不要求字典中出现的单词全部都使用，并且字典中的单词可以重复使用。
 *
 *  
 *
 * 示例 1：
 *
 * 输入: s = "leetcode", wordDict = ["leet", "code"]
 * 输出: true
 * 解释: 返回 true 因为 "leetcode" 可以由 "leet" 和 "code" 拼接成。
 * 示例 2：
 *
 * 输入: s = "applepenapple", wordDict = ["apple", "pen"]
 * 输出: true
 * 解释: 返回 true 因为 "applepenapple" 可以由 "apple" "pen" "apple" 拼接成。
 *      注意，你可以重复使用字典中的单词。
 * 示例 3：
 *
 * 输入: s = "catsandog", wordDict = ["cats", "dog", "sand", "and", "cat"]
 * 输出: false
 *  
 *
 * 提示：
 *
 * 1 <= s.length <= 300
 * 1 <= wordDict.length <= 1000
 * 1 <= wordDict[i].length <= 20
 * s 和 wordDict[i] 仅有小写英文字母组成
 * wordDict 中的所有字符串 互不相同
 *
 * @author chengzhy
 * @date 2022/4/13 10:08
 */
public class P139_WordBreak {

    public boolean wordBreak(String s, List<String> wordDict) {
        int m = wordDict.size(), n = s.length();
        /**
         * 此题完全背包二维dp数组做不出来
         */
        // dp[j]数组表示用字典中的单词能否组成前j个字符串
        boolean[] dp = new boolean[n + 1];
        // 初始化边界值
        dp[0] = true;
        // 因为每个字符串都需所有的单词组合一遍，因此将背包重量的循环放到外面，物品个数的循环在里面
        // 先遍历背包
        for (int j = 1; j <= n; j++) {
            // 再遍历物品
            for (int i = 0; i < j; i++) {
                // 如果s[0...i]之间的字符串能够拼凑出 && s[i...j]之间的字符串也在字典中存在
                if (dp[i] && wordDict.contains(s.substring(i, j))) {
                    // 那么说明s[0...i] + s[i...j] 拼凑出来字符串可以用字典拼出
                    dp[j] = true;
                }
            }
        }
        return dp[n];
    }

}
