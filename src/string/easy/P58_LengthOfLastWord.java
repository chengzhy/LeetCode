package string.easy;

/**
 * 最后一个单词的长度
 * <a href="https://leetcode.cn/problems/length-of-last-word/">🔗</a>
 *
 * 给你一个字符串 s，由若干单词组成，单词前后用一些空格字符隔开。返回字符串中最后一个单词的长度。
 *
 * 单词 是指仅由字母组成、不包含任何空格字符的最大子字符串。
 *
 *  
 *
 * 示例 1：
 *
 * 输入：s = "Hello World"
 * 输出：5
 * 示例 2：
 *
 * 输入：s = "   fly me   to   the moon  "
 * 输出：4
 * 示例 3：
 *
 * 输入：s = "luffy is still joyboy"
 * 输出：6
 *  
 *
 * 提示：
 *
 * 1 <= s.length <= 104
 * s 仅有英文字母和空格 ' ' 组成
 * s 中至少存在一个单词
 *
 * @author chengzhy
 * @date 2022/2/8 12:38
 */
public class P58_LengthOfLastWord {

    public int lengthOfLastWord(String s) {
        /*String[] array = s.split(" ");
        return array[array.length - 1].length();*/
        int length = 0;
        for (int i = s.length() - 1; i >= 0; i--) {
            if (s.charAt(i) == ' ') {
                if (length != 0) {
                    return length;
                }
            } else {
                length++;
            }
        }
        return length;
    }

}
