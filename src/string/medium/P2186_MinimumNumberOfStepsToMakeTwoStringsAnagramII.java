package string.medium;

/**
 * 使两字符串互为字母异位词的最少步骤数
 * <a href="https://leetcode.cn/problems/minimum-number-of-steps-to-make-two-strings-anagram-ii/">🔗</a>
 *
 * 给你两个字符串 s 和 t 。在一步操作中，你可以给 s 或者 t 追加 任一字符 。
 *
 * 返回使 s 和 t 互为 字母异位词 所需的最少步骤数。
 *
 * 字母异位词 指字母相同但是顺序不同（或者相同）的字符串。
 *
 *
 *
 * 示例 1：
 *
 * 输入：s = "leetcode", t = "coats"
 * 输出：7
 * 解释：
 * - 执行 2 步操作，将 "as" 追加到 s = "leetcode" 中，得到 s = "leetcodeas" 。
 * - 执行 5 步操作，将 "leede" 追加到 t = "coats" 中，得到 t = "coatsleede" 。
 * "leetcodeas" 和 "coatsleede" 互为字母异位词。
 * 总共用去 2 + 5 = 7 步。
 * 可以证明，无法用少于 7 步操作使这两个字符串互为字母异位词。
 * 示例 2：
 *
 * 输入：s = "night", t = "thing"
 * 输出：0
 * 解释：给出的字符串已经互为字母异位词。因此，不需要任何进一步操作。
 *
 *
 * 提示：
 *
 * 1 <= s.length, t.length <= 2 * 105
 * s 和 t 由小写英文字符组成
 *
 * @author chengzhy
 * @date 2022/2/27 10:32
 */
public class P2186_MinimumNumberOfStepsToMakeTwoStringsAnagramII {
    private static final char A = 'a';

    public int minSteps(String s, String t) {
        // 根据题意可以分别定义两个26个字母的字符数组，步数即为二者相差的字母数
        int[] sChars = new int[26], tChars = new int[26];
        int step = 0;
        for (int i = 0; i < s.length(); i++) {
            sChars[s.charAt(i) - A]++;
        }
        for (int i = 0; i < t.length(); i++) {
            tChars[t.charAt(i) - A]++;
        }
        for (int i = 0; i < 26; i++) {
            if (sChars[i] != tChars[i]) {
                step += Math.abs(sChars[i] - tChars[i]);
            }
        }
        return step;
    }

}
