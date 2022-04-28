package sliding_window.medium;

import java.util.HashMap;
import java.util.Map;

/**
 * 字符串的排列
 * <a href="https://leetcode.cn/problems/permutation-in-string/">🔗</a>
 *
 * 给你两个字符串 s1 和 s2 ，写一个函数来判断 s2 是否包含 s1 的排列。如果是，返回 true ；否则，返回 false 。
 *
 * 换句话说，s1 的排列之一是 s2 的 子串 。
 *
 *  
 *
 * 示例 1：
 *
 * 输入：s1 = "ab" s2 = "eidbaooo"
 * 输出：true
 * 解释：s2 包含 s1 的排列之一 ("ba").
 * 示例 2：
 *
 * 输入：s1= "ab" s2 = "eidboaoo"
 * 输出：false
 *  
 *
 * 提示：
 *
 * 1 <= s1.length, s2.length <= 104
 * s1 和 s2 仅包含小写字母
 *
 * @author chengzhy
 * @date 2022/2/10 12:24
 */
public class P567_PermutationInString {

    public boolean checkInclusion(String s1, String s2) {
        if (s1.length() > s2.length()) {
            return false;
        }
        /**
         * 慢
         */
        /*Map<Character, Integer> s1Map = new HashMap<>();
        Map<Character, Integer> s2Map = new HashMap<>();
        for (int i = 0; i < s1.length(); i++) {
            // 存数据
            s1Map.put(s1.charAt(i), s1Map.getOrDefault(s1.charAt(i), 0) + 1);
            s2Map.put(s2.charAt(i), s2Map.getOrDefault(s2.charAt(i), 0) + 1);
        }
        int start = 0, end = s1.length() - 1;
        while (end < s2.length()) {
            if (end >= s1.length()) {
                // end >= s1.length()，向s2Map中添加该字母
                s2Map.put(s2.charAt(end), s2Map.getOrDefault(s2.charAt(end), 0) + 1);
            }
            if (s1Map.containsKey(s2.charAt(start)) && s1Map.containsKey(s2.charAt(end))) {
                // 判断是否各个字母数量相等==>是子串
                boolean flag = true;
                for (Map.Entry<Character, Integer> entry : s1Map.entrySet()) {
                    if (!s2Map.containsKey(entry.getKey()) ||
                            !entry.getValue().equals(s2Map.get(entry.getKey()))) {
                        flag = false;
                        break;
                    }
                }
                if (flag) {
                    return true;
                }
            }
            // 更新start位置的字母情况
            if (s2Map.get(s2.charAt(start)).equals(1)) {
                s2Map.remove(s2.charAt(start));
            } else {
                s2Map.put(s2.charAt(start), s2Map.get(s2.charAt(start)) - 1);
            }
            start++;
            end++;
        }*/
        int[] cnt = new int[26];
        for (int i = 0; i < s1.length(); i++) {
            cnt[s1.charAt(i) - 'a']--;
            cnt[s2.charAt(i) - 'a']++;
        }
        // diff记录两个字符串不相等字母的情况
        int diff = 0;
        for (int c : cnt) {
            if (c != 0) {
                ++diff;
            }
        }
        if (diff == 0) {
            return true;
        }
        for (int i = s1.length(); i < s2.length(); i++) {
            int x = s2.charAt(i) - 'a', y = s2.charAt(i - s1.length()) - 'a';
            if (x == y) {
                // 一进一出的字母相等，无关紧要
                continue;
            }
            // 进来的字母未+1之前=0，不同+1
            if (cnt[x] == 0) {
                diff++;
            }
            cnt[x]++;
            // 进来的字母+1之后=0，不同-1
            if (cnt[x] == 0) {
                diff--;
            }
            // 出去的字母未-1之前=0，不同+1
            if (cnt[y] == 0) {
                diff++;
            }
            cnt[y]--;
            // 出去的字母-1之后=0，不同-1
            if (cnt[y] == 0) {
                diff--;
            }
            if (diff == 0) {
                return true;
            }
        }
        return false;
    }

}
