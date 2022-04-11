package string.medium;

/**
 * 复数乘法
 * <a href="https://leetcode-cn.com/problems/complex-number-multiplication/">🔗</a>
 *
 * 复数 可以用字符串表示，遵循 "实部+虚部i" 的形式，并满足下述条件：
 *
 * 实部 是一个整数，取值范围是 [-100, 100]
 * 虚部 也是一个整数，取值范围是 [-100, 100]
 * i2 == -1
 * 给你两个字符串表示的复数 num1 和 num2 ，请你遵循复数表示形式，返回表示它们乘积的字符串。
 *
 *  
 *
 * 示例 1：
 *
 * 输入：num1 = "1+1i", num2 = "1+1i"
 * 输出："0+2i"
 * 解释：(1 + i) * (1 + i) = 1 + i2 + 2 * i = 2i ，你需要将它转换为 0+2i 的形式。
 * 示例 2：
 *
 * 输入：num1 = "1+-1i", num2 = "1+-1i"
 * 输出："0+-2i"
 * 解释：(1 - i) * (1 - i) = 1 + i2 - 2 * i = -2i ，你需要将它转换为 0+-2i 的形式。
 *  
 *
 * 提示：
 *
 * num1 和 num2 都是有效的复数表示。
 *
 * @author chengzhy
 * @date 2022/2/25 15:29
 */
public class P537_ComplexNumberMultiplication {
    private static final char PLUS = '+';
    private static final char I = 'i';

    public String complexNumberMultiply(String num1, String num2) {
        int real, imaginary;
        StringBuilder sb = new StringBuilder();
        int num1Real = Integer.valueOf(num1.substring(0, num1.indexOf(PLUS)));
        int num1Imaginary = Integer.valueOf(num1.substring(num1.indexOf(PLUS) + 1, num1.length() - 1));
        int num2Real = Integer.valueOf(num2.substring(0, num2.indexOf(PLUS)));
        int num2Imaginary = Integer.valueOf(num2.substring(num2.indexOf(PLUS) + 1, num2.length() - 1));
        real = num1Real * num2Real - num1Imaginary * num2Imaginary;
        imaginary = num1Real * num2Imaginary + num1Imaginary * num2Real;
        return sb.append(real).append(PLUS).append(imaginary).append(I).toString();
    }

}
