package bit_manipulation.easy;

/**
 * 最长的美好子字符串
 *
 * 当一个字符串 s 包含的每一种字母的大写和小写形式 同时 出现在 s 中，就称这个字符串 s 是 美好 字符串。比方说，"abABB" 是美好字符串，因为 'A' 和 'a' 同时出现了，且 'B' 和 'b' 也同时出现了。然而，"abA" 不是美好字符串因为 'b' 出现了，而 'B' 没有出现。
 *
 * 给你一个字符串 s ，请你返回 s 最长的 美好子字符串 。如果有多个答案，请你返回 最早 出现的一个。如果不存在美好子字符串，请你返回一个空字符串。
 *
 *  
 *
 * 示例 1：
 *
 * 输入：s = "YazaAay"
 * 输出："aAa"
 * 解释："aAa" 是一个美好字符串，因为这个子串中仅含一种字母，其小写形式 'a' 和大写形式 'A' 也同时出现了。
 * "aAa" 是最长的美好子字符串。
 * 示例 2：
 *
 * 输入：s = "Bb"
 * 输出："Bb"
 * 解释："Bb" 是美好字符串，因为 'B' 和 'b' 都出现了。整个字符串也是原字符串的子字符串。
 * 示例 3：
 *
 * 输入：s = "c"
 * 输出：""
 * 解释：没有美好子字符串。
 * 示例 4：
 *
 * 输入：s = "dDzeE"
 * 输出："dD"
 * 解释："dD" 和 "eE" 都是最长美好子字符串。
 * 由于有多个美好子字符串，返回 "dD" ，因为它出现得最早。
 *  
 *
 * 提示：
 *
 * 1 <= s.length <= 100
 * s 只包含大写和小写英文字母。
 *
 * @author chengzhy
 * @date 2022/2/1 9:58
 */
public class P1763_LongestNiceSubstring {

    public String longestNiceSubstring(String s) {
        int p = 0, maxLength = 0;
        for (int i = 0; i < s.length(); i++) {
            // 位运算，刚好字母总共就26个，能在int的数值范围内表示，所以用bitcount
            int lower = 0, upper = 0;
            for (int j = i; j < s.length(); j++) {
                /**
                 * 如果是小写，将该字母在26个中其对应的位置标1，例如c也就是1左移2位，因为c比a多两位，然后记录小写的值再与其或运算来记录
                 * 大写字母同理
                 */
                if (Character.isLowerCase(s.charAt(j))) {
                    lower |= 1 << (s.charAt(j) - 'a');
                } else {
                    upper |= 1 << (s.charAt(j) - 'A');
                }
                // 如果有完美并且大于最长的，记录此字符串的起始位置和长度
                if (lower == upper && j - i + 1 > maxLength) {
                    p = i;
                    maxLength = j - i + 1;
                }
            }
        }
        return s.substring(p, p + maxLength);
    }

}
