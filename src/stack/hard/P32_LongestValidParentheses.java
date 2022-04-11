package stack.hard;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * 最长有效括号
 * <a href="https://leetcode-cn.com/problems/longest-valid-parentheses/">🔗</a>
 *
 * 给你一个只包含 '(' 和 ')' 的字符串，找出最长有效（格式正确且连续）括号子串的长度。
 *
 *  
 *
 * 示例 1：
 *
 * 输入：s = "(()"
 * 输出：2
 * 解释：最长有效括号子串是 "()"
 * 示例 2：
 *
 * 输入：s = ")()())"
 * 输出：4
 * 解释：最长有效括号子串是 "()()"
 * 示例 3：
 *
 * 输入：s = ""
 * 输出：0
 *  
 *
 * 提示：
 *
 * 0 <= s.length <= 3 * 104
 * s[i] 为 '(' 或 ')'
 *
 * @author chengzhy
 * @date 2022/1/27 16:00
 **/
public class P32_LongestValidParentheses {

    public int longestValidParentheses(String s) {
        if (s.length() < 2) return 0;

        /**
         * 利用栈模拟，将所有无法匹配的括号的位置全部置1，变成了寻找最长的连续的0的长度
         */
        int count = 0, max = 0;
        // 栈存无法匹配的括号的位置
        Deque<Integer> stack = new ArrayDeque<>();
        char[] chars = s.toCharArray();
        int[] arr = new int[s.length()];
        for (int i=0; i<chars.length; i++) {
            if (chars[i] == '(') {
                stack.push(i);
            } else if (stack.isEmpty()) {
                // 多余的)的位置标1
                arr[i] = 1;
            } else {
                // 匹配到)弹出(的位置
                stack.pop();
            }
        }
        // 无法匹配的括号的位置设为1
        while (!stack.isEmpty()) {
            arr[stack.pop()] = 1;
        }
        // 寻找最长的连续的0的长度
        for (int i=0; i<arr.length; i++) {
            if (arr[i] == 1) {
                count = 0;
                continue;
            }
            max = Math.max(max, ++count);
        }
        return max;
    }

}
