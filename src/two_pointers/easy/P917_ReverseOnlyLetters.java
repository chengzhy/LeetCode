package two_pointers.easy;

/**
 * 仅仅反转字母
 * <a href="https://leetcode.cn/problems/reverse-only-letters/">🔗</a>
 *
 * 给你一个字符串 s ，根据下述规则反转字符串：
 *
 * 所有非英文字母保留在原有位置。
 * 所有英文字母（小写或大写）位置反转。
 * 返回反转后的 s 。
 *
 *  
 *
 * 示例 1：
 *
 * 输入：s = "ab-cd"
 * 输出："dc-ba"
 * 示例 2：
 *
 * 输入：s = "a-bC-dEf-ghIj"
 * 输出："j-Ih-gfE-dCba"
 * 示例 3：
 *
 * 输入：s = "Test1ng-Leet=code-Q!"
 * 输出："Qedo1ct-eeLg=ntse-T!"
 *  
 *
 * 提示
 *
 * 1 <= s.length <= 100
 * s 仅由 ASCII 值在范围 [33, 122] 的字符组成
 * s 不含 '\"' 或 '\\'
 *
 * @author chengzhy
 * @date 2022/2/23 19:31
 */
public class P917_ReverseOnlyLetters {

    public String reverseOnlyLetters(String s) {
        char[] chars = s.toCharArray();
        int i = 0, j = s.length() - 1;
        while (i < j) {
            // 找到从左往右第一个字母
            while (i < s.length() && !Character.isLetter(chars[i])) {
                i++;
            }
            // 找到从右往左第一个字母
            while (j >= 0 && !Character.isLetter(chars[j])) {
                j--;
            }
            // 交换
            if (i < j) {
                char temp = chars[j];
                chars[j--] = chars[i];
                chars[i++] = temp;
            }
        }
        return new String(chars);
    }

}
