package math.easy;

/**
 * 回文数
 * <a href="https://leetcode-cn.com/problems/palindrome-number/">🔗</a>
 *
 * Determine whether an integer is a palindrome. An integer is a palindrome when it reads the same backward as forward.
 *
 * Example 1:
 *
 * Input: 121
 * Output: true
 * Example 2:
 *
 * Input: -121
 * Output: false
 * Explanation: From left to right, it reads -121. From right to left, it becomes 121-. Therefore it is not a palindrome.
 * Example 3:
 *
 * Input: 10
 * Output: false
 * Explanation: Reads 01 from right to left. Therefore it is not a palindrome.
 * Follow up:
 *
 * Coud you solve it without converting the integer to a string?
 *
 * 判断一个整数是否是回文数。回文数是指正序（从左向右）和倒序（从右向左）读都是一样的整数。
 *
 * @author chengzhy
 * @date 2022/1/27 16:00
 */
public class P9_PalindromeNumber {
    public boolean isPalindrome(int x) {
        if (x < 0) {
            return false;
        }
        if (x == 0) {
            return true;
        }
        int result = 0, temp = 0, sum = x;
        while (sum != 0){
            temp = sum%10;
            result = result*10 + temp;
            sum = sum/10;
        }
        if (result == x) {
            return true;
        } else {
            return false;
        }
    }

}
