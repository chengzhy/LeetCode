package string.easy;

/**
 * 二进制求和
 * <a href="https://leetcode.cn/problems/add-binary/">🔗</a>
 *
 * 给你两个二进制字符串，返回它们的和（用二进制表示）。
 *
 * 输入为 非空 字符串且只包含数字 1 和 0。
 *
 *  
 *
 * 示例 1:
 *
 * 输入: a = "11", b = "1"
 * 输出: "100"
 * 示例 2:
 *
 * 输入: a = "1010", b = "1011"
 * 输出: "10101"
 *  
 *
 * 提示：
 *
 * 每个字符串仅由字符 '0' 或 '1' 组成。
 * 1 <= a.length, b.length <= 10^4
 * 字符串如果不是 "0" ，就都不含前导零。
 *
 * @author chengzhy
 * @date 2022/3/14 13:27
 */
public class P67_AddBinary {
    private static final char ZERO = '0';

    public String addBinary(String a, String b) {
        StringBuilder sb = new StringBuilder();
        int carry = 0, i, j;
        for (i = a.length() - 1, j = b.length() - 1; i >= 0 && j >= 0; i--, j--) {
            int sum = (a.charAt(i) - ZERO) + (b.charAt(j) - ZERO) + carry;
            sb.append(sum & 1);
            carry = sum >> 1;
        }
        while (i >= 0) {
            int sum = (a.charAt(i--) - ZERO) + carry;
            sb.append(sum & 1);
            carry = sum >> 1;
        }
        while (j >= 0) {
            int sum = (b.charAt(j--) - ZERO) + carry;
            sb.append(sum & 1);
            carry = sum >> 1;
        }
        if (carry == 1) {
            sb.append(carry);
        }
        return sb.reverse().toString();
    }

}
