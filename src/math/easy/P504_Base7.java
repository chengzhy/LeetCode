package math.easy;

/**
 * 七进制数
 *
 * 给定一个整数 num，将其转化为 7 进制，并以字符串形式输出。
 *
 *  
 *
 * 示例 1:
 *
 * 输入: num = 100
 * 输出: "202"
 * 示例 2:
 *
 * 输入: num = -7
 * 输出: "-10"
 *  
 *
 * 提示：
 *
 * -107 <= num <= 107
 *
 * @author chengzhy
 * @date 2022/3/7 11:22
 */
public class P504_Base7 {

    public String convertToBase7(int num) {
        if (num == 0) {
            return "0";
        }
        StringBuilder sb = new StringBuilder();
        int temp = Math.abs(num);
        while (temp > 0) {
            sb.append(temp % 7);
            temp /= 7;
        }
        if (num < 0) {
            sb.append('-');
        }
        return sb.reverse().toString();
    }

}
