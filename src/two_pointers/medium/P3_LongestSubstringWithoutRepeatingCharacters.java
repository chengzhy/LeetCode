package two_pointers.medium;

/**
 * @description 无重复字符的最长子串
 *
 * Given a string, find the length of the longest substring without repeating characters.
 *
 * Example 1:
 *
 * Input: "abcabcbb"
 * Output: 3
 * Explanation: The answer is "abc", with the length of 3.
 * Example 2:
 *
 * Input: "bbbbb"
 * Output: 1
 * Explanation: The answer is "b", with the length of 1.
 * Example 3:
 *
 * Input: "pwwkew"
 * Output: 3
 * Explanation: The answer is "wke", with the length of 3.
 *              Note that the answer must be a substring, "pwke" is a subsequence and not a substring.
 *
 * 给定一个字符串，请你找出其中不含有重复字符的 最长子串 的长度。
 */
public class P3_LongestSubstringWithoutRepeatingCharacters {
    public int lengthOfLongestSubstring(String s) {
        /**
         * 慢
         */
        /*char[] chars = s.toCharArray();
        if (chars.length == 0) return 0;
        Map<Character, Integer> map = new HashMap<>();
        int i, j, max = 1;
        for (i=0; i<chars.length-1; i++){
            map.put(chars[i], i);
            for (j=i+1; j<chars.length; j++){
                if (map.containsKey(chars[j])){
                    map = new HashMap<>();
                    j++;
                    break;
                }else {
                    map.put(chars[j], j);
                    if (j-i+1 > max) max = j-i+1;
                }
            }
            if (max >= chars.length-i) return max;
        }
        return max;*/
        int lookup[] =  new int[128], i, len = s.length(), count, max;
        char tmp;
        if(len == 0) {
            return 0;
        }
        max = 0;
        count = 0;//索引，标记最新重复的位置
        for(i = 0; i < 128; i++){
            lookup[i] = -1;
        }
        for(i = 0; i < len; i++) {
            tmp = s.charAt(i);
            if(lookup[tmp] > count){
                count = lookup[tmp];
            }
            lookup[tmp] = i+1;
            max = Math.max(max, i - count + 1);
        }
        return max;
    }

}
