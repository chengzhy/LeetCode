package sliding_window.medium;

/**
 * 替换后的最长重复字符
 * <a href="https://leetcode-cn.com/problems/longest-repeating-character-replacement/">🔗</a>
 *
 * 给你一个字符串 s 和一个整数 k 。你可以选择字符串中的任一字符，并将其更改为任何其他大写英文字符。该操作最多可执行 k 次。
 *
 * 在执行上述操作后，返回包含相同字母的最长子字符串的长度。
 *
 *  
 *
 * 示例 1：
 *
 * 输入：s = "ABAB", k = 2
 * 输出：4
 * 解释：用两个'A'替换为两个'B',反之亦然。
 * 示例 2：
 *
 * 输入：s = "AABABBA", k = 1
 * 输出：4
 * 解释：
 * 将中间的一个'A'替换为'B',字符串变为 "AABBBBA"。
 * 子串 "BBBB" 有最长重复字母, 答案为 4。
 *  
 *
 * 提示：
 *
 * 1 <= s.length <= 105
 * s 仅由大写英文字母组成
 * 0 <= k <= s.length
 *
 * @author chengzhy
 * @date 2022/3/31 11:23
 */
public class P424_LongestRepeatingCharacterReplacement {

    public int characterReplacement(String s, int k) {
        /**
         * 核心思想：当滑动窗口的大小(right - left + 1) <= 当前窗口中最多字母的个数 + k，说明这个窗口内是够换的
         * 若 > ,则说明不够了，再整体向右移动整个滑动窗口(left++, right++)来寻找满足条件的窗口
         */
        int left = 0, right = 0, maxNum = 0;
        int[] count = new int[26];
        char[] chars = s.toCharArray();
        while (right < chars.length) {
            int index = chars[right++] - 'A';
            count[index]++;
            if (count[index] > maxNum) {
                maxNum = count[index];
            }
            if (right - left > maxNum + k) {
                count[chars[left++] - 'A']--;
                /**
                 * 这里解释一下左指针右移后为什么不用去更新maxNum:
                 * 因为当maxNum和k一定的时候，区间的最大长度也是固定的，
                 * 这个最大长度就是maxNum + k，最终答案一定是不小于这个值的；
                 * 所以这里没有必要再去更新maxNum的值，如果后续区间有count[i] > maxNum的情况，再去更新
                 */
            }
        }
        return right - left;
    }

}
