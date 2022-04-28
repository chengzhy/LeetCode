package stack.easy;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * 有效的括号
 * <a href="https://leetcode.cn/problems/valid-parentheses/">🔗</a>
 *
 * 给定一个只包括 '('，')'，'{'，'}'，'['，']' 的字符串 s ，判断字符串是否有效。
 *
 * 有效字符串需满足：
 *
 * 左括号必须用相同类型的右括号闭合。
 * 左括号必须以正确的顺序闭合。
 *  
 *
 * 示例 1：
 *
 * 输入：s = "()"
 * 输出：true
 * 示例 2：
 *
 * 输入：s = "()[]{}"
 * 输出：true
 * 示例 3：
 *
 * 输入：s = "(]"
 * 输出：false
 * 示例 4：
 *
 * 输入：s = "([)]"
 * 输出：false
 * 示例 5：
 *
 * 输入：s = "{[]}"
 * 输出：true
 *  
 *
 * 提示：
 *
 * 1 <= s.length <= 104
 * s 仅由括号 '()[]{}' 组成
 *
 * @author chengzhy
 * @date 2022/1/27 16:00
 **/
public class P20_ValidParentheses {

    public boolean isValid(String s) {
        // 通过栈来操作
        Deque<Character> stack = new ArrayDeque<>();
        for (char c : s.toCharArray()) {
            if ('(' == c) {
                stack.push(')');
            } else if ('{' == c) {
                stack.push('}');
            } else if ('[' == c) {
                stack.push(']');
            } else if (stack.isEmpty() || c != stack.pop()) {
                // 此时栈为空，
                return false;
            }
        }
        return stack.isEmpty();
    }

}
