package math.medium;

/**
 * 字符串相乘
 * <a href="https://leetcode.cn/problems/multiply-strings/">🔗</a>
 *
 * 给定两个以字符串形式表示的非负整数 num1 和 num2，返回 num1 和 num2 的乘积，它们的乘积也表示为字符串形式。
 *
 * 示例 1:
 *
 * 输入: num1 = "2", num2 = "3"
 * 输出: "6"
 * 示例 2:
 *
 * 输入: num1 = "123", num2 = "456"
 * 输出: "56088"
 * 说明：
 *
 * num1 和 num2 的长度小于110。
 * num1 和 num2 只包含数字 0-9。
 * num1 和 num2 均不以零开头，除非是数字 0 本身。
 * 不能使用任何标准库的大数类型（比如 BigInteger）或直接将输入转换为整数来处理。
 *
 * @author chengzhy
 * @date 2021/11/8 11:38
 */
public class P43_MultiplyStrings {

    private static final String ZERO = "0";
    private static final String ONE = "1";

    public String multiply(String num1, String num2) {
        /*if (ZERO.equals(num1) || ZERO.equals(num2)) {
            return ZERO;
        }
        if (ONE.equals(num1)) {
            return num2;
        }
        if (ONE.equals(num2)) {
            return num1;
        }

        int[] multiplyResult = new int[num1.length() + num2.length()];
        for (int i = num1.length()-1; i >= 0; i--) {
            for (int j = num2.length()-1; j >= 0; j--) {
                int bitResult = (num1.charAt(i)-'0') * (num2.charAt(j)-'0');
                bitResult += multiplyResult[i+j+1];
                multiplyResult[i+j] += bitResult / 10;
                multiplyResult[i+j+1] = bitResult % 10;
            }
        }

        StringBuilder result = new StringBuilder(multiplyResult.length);
        int k = multiplyResult[0] == 0 ? 1 : 0;
        while (k < multiplyResult.length) {
            result.append(multiplyResult[k++]);
        }
        return result.toString();*/

        /**
         * 1ms解
         */
        if (ZERO.equals(num1) || ZERO.equals(num2)) {
            return ZERO;
        }
        if (ONE.equals(num1)) {
            return num2;
        }
        if (ONE.equals(num2)) {
            return num1;
        }

        int m = num1.length(), n = num2.length();
        int[] str = new int[m + n];
        for (int i = m-1; i >= 0; i--) {
            int x = num1.charAt(i) - '0';
            for (int j = n-1; j >= 0; j--) {
                int y = num2.charAt(j) - '0';
                str[i+j+1] += x * y;
            }
        }
        for (int i = m+n-1; i > 0; i--) {
            str[i-1] += str[i] / 10;
            str[i] = str[i] % 10;
        }

        StringBuilder result = new StringBuilder();
        int k = str[0] == 0 ? 1 : 0;
        while (k < str.length) {
            result.append(str[k++]);
        }
        return result.toString();
    }

}
