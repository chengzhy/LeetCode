package string.easy;

/**
 * 有效的字母异位词
 * <a href="https://leetcode.cn/problems/valid-anagram/">🔗</a>
 *
 * 给定两个字符串 s 和 t ，编写一个函数来判断 t 是否是 s 的字母异位词。
 *
 * 注意：若 s 和 t 中每个字符出现的次数都相同，则称 s 和 t 互为字母异位词。
 *
 *  
 *
 * 示例 1:
 *
 * 输入: s = "anagram", t = "nagaram"
 * 输出: true
 * 示例 2:
 *
 * 输入: s = "rat", t = "car"
 * 输出: false
 *  
 *
 * 提示:
 *
 * 1 <= s.length, t.length <= 5 * 104
 * s 和 t 仅包含小写字母
 *  
 *
 * 进阶: 如果输入字符串包含 unicode 字符怎么办？你能否调整你的解法来应对这种情况？
 *
 * @author chengzhy
 * @date 2022/3/18 10:37
 */
public class P242_ValidAnagram {
    private static final char A = 'a';

    public boolean isAnagram(String s, String t) {
        if (s.length() != t.length()) {
            return false;
        }
        int[] chars = new int[26];
        for (char c : s.toCharArray()) {
            chars[c - A]++;
        }
        for (char c : t.toCharArray()) {
            chars[c - A]--;
        }
        for (int count : chars) {
            if (count != 0) {
                return false;
            }
        }
        return true;
    }

}
