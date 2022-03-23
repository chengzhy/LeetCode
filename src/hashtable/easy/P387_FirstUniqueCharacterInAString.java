package hashtable.easy;

import java.util.HashMap;
import java.util.Map;

/**
 * 字符串中的第一个唯一字符
 *
 * 给定一个字符串 s ，找到 它的第一个不重复的字符，并返回它的索引 。如果不存在，则返回 -1 。
 *
 *  
 *
 * 示例 1：
 *
 * 输入: s = "leetcode"
 * 输出: 0
 * 示例 2:
 *
 * 输入: s = "loveleetcode"
 * 输出: 2
 * 示例 3:
 *
 * 输入: s = "aabb"
 * 输出: -1
 *  
 *
 * 提示:
 *
 * 1 <= s.length <= 105
 * s 只包含小写字母
 *
 * @author chengzhy
 * @date 2022/3/23 14:27
 */
public class P387_FirstUniqueCharacterInAString {

    public int firstUniqChar(String s) {
        int index = s.length();
        Map<Character, Integer> map = new HashMap<>();
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (map.containsKey(c)) {
                // 如果出现重复的字母，将索引置为-1
                map.put(c, -1);
            } else {
                // 存字母第一次出现的索引
                map.put(c, i);
            }
        }
        for (Map.Entry<Character, Integer> entry : map.entrySet()) {
            int temp = entry.getValue();
            // 寻找只出现一次并且索引最小的字母
            if (temp != -1 && temp < index) {
                index = temp;
            }
        }
        return (index == s.length()) ? -1 : index;
    }

}
