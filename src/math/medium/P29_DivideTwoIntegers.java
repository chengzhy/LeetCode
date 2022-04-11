package math.medium;

/**
 * 两数相除
 * <a href="https://leetcode-cn.com/problems/divide-two-integers/">🔗</a>
 *
 * 给定两个整数，被除数 dividend 和除数 divisor。将两数相除，要求不使用乘法、除法和 mod 运算符。
 *
 * 返回被除数 dividend 除以除数 divisor 得到的商。
 *
 * 整数除法的结果应当截去（truncate）其小数部分，例如：truncate(8.345) = 8 以及 truncate(-2.7335) = -2
 *
 *  
 *
 * 示例 1:
 *
 * 输入: dividend = 10, divisor = 3
 * 输出: 3
 * 解释: 10/3 = truncate(3.33333..) = truncate(3) = 3
 * 示例 2:
 *
 * 输入: dividend = 7, divisor = -3
 * 输出: -2
 * 解释: 7/-3 = truncate(-2.33333..) = -2
 *  
 *
 * 提示：
 *
 * 被除数和除数均为 32 位有符号整数。
 * 除数不为 0。
 * 假设我们的环境只能存储 32 位有符号整数，其数值范围是 [−231,  231 − 1]。本题中，如果除法结果溢出，则返回 231 − 1。
 *
 * @author chengzhy
 * @date 2022/1/27 16:00
 **/
public class P29_DivideTwoIntegers {

    public int divide(int dividend, int divisor) {
        /**
         * 略慢
         */
        /*if (dividend == 0) return 0;
        if (divisor == 0) return Integer.MAX_VALUE;
        long quotient = 0;
        long tempDividend = dividend;
        long tempDivisor = divisor;
        boolean plus = true;
        if ((dividend<0 && divisor>0) || (dividend>0 && divisor<0)) {
            plus = false;
        }
        if (dividend < 0) tempDividend = -tempDividend;
        if (divisor < 0) tempDivisor = -tempDivisor;
        while (tempDividend >= tempDivisor) {
            long k = 1;
            long temp = tempDivisor;
            while(tempDividend >= temp) {
                tempDividend -= temp;
                quotient += k;
                k += k;
                temp += temp;
            }
        }
        if (plus) {
            return quotient > Integer.MAX_VALUE ? Integer.MAX_VALUE : (int) quotient;
        } else {
            return quotient < Integer.MIN_VALUE ? Integer.MAX_VALUE : (int) -quotient;
        }*/

        /**
         * 解题思路：我们首先想到的是减法，能被减多少次，那么商就为多少，但是明显减法的效率太低
         *
         * 那么我们可以用位移法，因为计算机在做位移时效率特别高，向左移1相当于乘以2，向右位移1相当于除以2
         *
         * 我们可以把一个dividend（被除数）先除以2^n，n最初为31，不断减小n去试探,当某个n满足dividend/2^n>=divisor时，
         * 表示我们找到了一个足够大的数，这个数*divisor是不大于dividend的，所以我们就可以减去2^n个divisor，以此类推
         *
         * 我们可以以100/3为例
         * 2^n是1，2，4，8...2^31这种数，当n为31时，这个数特别大，100/2^n是一个很小的数，肯定是小于3的，所以循环下来，
         * 当n=5时，100/32=3, 刚好是大于等于3的，这时我们将100-32*3=4，也就是减去了32个3，接下来我们再处理4，同样手法可以再减去一个3
         * 所以一共是减去了33个3，所以商就是33
         * 这其中得处理一些特殊的数，比如divisor是不能为0的，Integer.MIN_VALUE和Integer.MAX_VALUE
         */
        if (dividend == 0) return 0;
        if (dividend == Integer.MIN_VALUE && divisor == -1) return Integer.MAX_VALUE;
        // 用异或来计算是否符号相异
        boolean plus = (dividend ^ divisor) < 0;
        long tempDividend = Math.abs((long) dividend);
        long tempDivisor = Math.abs((long) divisor);
        int quotient = 0;
        for (int i=31; i>=0; i--) {
            // 找出足够大的数2^n*divisor
            if ((tempDividend>>i) >= tempDivisor) {
                // 将结果加上2^n
                quotient += 1<<i;
                // 将被除数减去2^n*divisor
                tempDividend -= tempDivisor<<i;
            }
        }
        //符号相异取反
        return plus ? -quotient : quotient;
    }

}
