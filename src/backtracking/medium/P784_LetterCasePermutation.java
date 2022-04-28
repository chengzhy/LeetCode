package backtracking.medium;

import java.util.ArrayList;
import java.util.List;

/**
 * 字母大小写全排列
 * <a href="https://leetcode.cn/problems/letter-case-permutation/">🔗</a>
 *
 * 给定一个字符串 s ，通过将字符串 s 中的每个字母转变大小写，我们可以获得一个新的字符串。
 *
 * 返回 所有可能得到的字符串集合 。以 任意顺序 返回输出。
 *
 *  
 *
 * 示例 1：
 *
 * 输入：s = "a1b2"
 * 输出：["a1b2", "a1B2", "A1b2", "A1B2"]
 * 示例 2:
 *
 * 输入: s = "3z4"
 * 输出: ["3z4","3Z4"]
 *  
 *
 * 提示:
 *
 * 1 <= s.length <= 12
 * s 由小写英文字母、大写英文字母和数字组成
 *
 * @author chengzhy
 * @date 2022/3/10 16:26
 */
public class P784_LetterCasePermutation {

    public List<String> letterCasePermutation(String s) {
        List<String> result = new ArrayList<>();
        backtracking(s.toCharArray(), 0, result);
        return result;
    }

    private void backtracking(char[] chars, int start, List<String> result) {
        result.add(new String(chars));
        for (int i = start; i < chars.length; i++) {
            char c = chars[i];
            if (Character.isLetter(c)) {
                // 是字母则把当前位置字母进行变换
                chars[i] = (Character.isLowerCase(c)) ? Character.toUpperCase(c) : Character.toLowerCase(c);
                backtracking(chars, i + 1, result);
                chars[i] = c;
            }
        }
    }

}
