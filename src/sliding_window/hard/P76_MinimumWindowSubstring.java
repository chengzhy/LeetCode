package sliding_window.hard;

/**
 * 最小覆盖子串
 * <a href="https://leetcode.cn/problems/minimum-window-substring/">🔗</a>
 *
 * 给你一个字符串 s 、一个字符串 t 。返回 s 中涵盖 t 所有字符的最小子串。如果 s 中不存在涵盖 t 所有字符的子串，则返回空字符串 "" 。
 *
 *  
 *
 * 注意：
 *
 * 对于 t 中重复字符，我们寻找的子字符串中该字符数量必须不少于 t 中该字符数量。
 * 如果 s 中存在这样的子串，我们保证它是唯一的答案。
 *  
 *
 * 示例 1：
 *
 * 输入：s = "ADOBECODEBANC", t = "ABC"
 * 输出："BANC"
 * 示例 2：
 *
 * 输入：s = "a", t = "a"
 * 输出："a"
 * 示例 3:
 *
 * 输入: s = "a", t = "aa"
 * 输出: ""
 * 解释: t 中两个字符 'a' 均应包含在 s 的子串中，
 * 因此没有符合条件的子字符串，返回空字符串。
 *  
 *
 * 提示：
 *
 * 1 <= s.length, t.length <= 105
 * s 和 t 由英文字母组成
 *  
 *
 * 进阶：你能设计一个在 o(n) 时间内解决此问题的算法吗？
 *
 * @author chengzhy
 * @date 2022/3/17 11:54
 */
public class P76_MinimumWindowSubstring {

    public String minWindow(String s, String t) {
        if (s.length() < t.length()) {
            return "";
        }
        int left = 0, right = 0, count = t.length(), resultStart = 0, minLength = Integer.MAX_VALUE;
        // 记录所需要的字符
        int[] need = new int[128];
        for (int i = 0; i < t.length(); i++) {
            need[t.charAt(i)]++;
        }
        while (right < s.length()) {
            char c = s.charAt(right);
            // 说明是需要的字母，count减1
            if (need[c] > 0) {
                count--;
            }
            need[c]--;
            // 说明目前已经包含了所有需要的字母
            if (count == 0) {
                // 直到左指针到达需要的字母的位置，因为不需要的字母need值一定小于0，需要的字母need值一定等于0
                while (need[s.charAt(left)] < 0) {
                    ++need[s.charAt(left++)];
                }
                // 更新最小子串的情况
                if (right - left + 1 < minLength) {
                    minLength = right - left + 1;
                    resultStart = left;
                }
                // 左指针继续往右走后一定不满足包含了所有需要的字母
                ++need[s.charAt(left++)];
                count++;
            }
            right++;
        }
        return (minLength == Integer.MAX_VALUE) ? "" : s.substring(resultStart, resultStart + minLength);
    }

}
