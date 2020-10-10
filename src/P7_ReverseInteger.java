/**
 * Given a 32-bit signed integer, reverse digits of an integer.
 *
 * Example 1:
 *
 * Input: 123
 * Output: 321
 * Example 2:
 *
 * Input: -123
 * Output: -321
 * Example 3:
 *
 * Input: 120
 * Output: 21
 * Note:
 * Assume we are dealing with an environment which could only store integers within the 32-bit signed integer range: [−231,  231 − 1]. For the purpose of this problem, assume that your function returns 0 when the reversed integer overflows.
 *
 * 给出一个 32 位的有符号整数，你需要将这个整数中每位上的数字进行反转。
 */
public class P7_ReverseInteger {
    public int reverse(int x) {
        long result = 0;
        while(x!=0){
            result = result*10+ x%10;
            x=x/10;
        }
        if((int)result != result){
            return 0;
        }else{
            return (int)result;
        }
    }

    public static void main(String[] args) {
        P7_ReverseInteger r = new P7_ReverseInteger();
        System.out.println(r.reverse(1200));
    }
}
