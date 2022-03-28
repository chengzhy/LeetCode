package two_pointers.easy;

/**
 * 反转字符串中的单词 III
 *
 * 给定一个字符串 s ，你需要反转字符串中每个单词的字符顺序，同时仍保留空格和单词的初始顺序。
 *
 *  
 *
 * 示例 1：
 *
 * 输入：s = "Let's take LeetCode contest"
 * 输出："s'teL ekat edoCteeL tsetnoc"
 * 示例 2:
 *
 * 输入： s = "God Ding"
 * 输出："doG gniD"
 *  
 *
 * 提示：
 *
 * 1 <= s.length <= 5 * 104
 * s 包含可打印的 ASCII 字符。
 * s 不包含任何开头或结尾空格。
 * s 里 至少 有一个词。
 * s 中的所有单词都用一个空格隔开。
 *
 * @author chengzhy
 * @date 2022/3/28 11:43
 */
public class P557_ReverseWordsInAStringIII {

    public String reverseWords(String s) {
        char[] chars = s.toCharArray();
        int left = 0, right = 1;
        while (right <= chars.length) {
            if (right == chars.length || chars[right] == ' ') {
                reverseString(chars, left, right - 1);
                left = ++right;
            } else {
                right++;
            }
        }
        return new String(chars);
    }

    private void reverseString(char[] chars, int left, int right) {
        while (left < right) {
            char temp = chars[left];
            chars[left++] = chars[right];
            chars[right--] = temp;
        }
    }

}
