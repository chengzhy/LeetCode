package array.medium;

import java.util.Arrays;

/**
 * 重排数字的最小值
 * <a href="https://leetcode-cn.com/problems/smallest-value-of-the-rearranged-number/">🔗</a>
 *
 * 给你一个整数 num 。重排 num 中的各位数字，使其值 最小化 且不含 任何 前导零。
 *
 * 返回不含前导零且值最小的重排数字。
 *
 * 注意，重排各位数字后，num 的符号不会改变。
 *
 *
 *
 * 示例 1：
 *
 * 输入：num = 310
 * 输出：103
 * 解释：310 中各位数字的可行排列有：013、031、103、130、301、310 。
 * 不含任何前导零且值最小的重排数字是 103 。
 * 示例 2：
 *
 * 输入：num = -7605
 * 输出：-7650
 * 解释：-7605 中各位数字的部分可行排列为：-7650、-6705、-5076、-0567。
 * 不含任何前导零且值最小的重排数字是 -7650 。
 *
 *
 * 提示：
 *
 * -1015 <= num <= 1015
 *
 * @author chengzhy
 * @date 2022/2/6 11:02
 */
public class P2165_SmallestValueOfTheRearrangedNumber {

    public long smallestNumber(long num) {
        char[] chars = String.valueOf(Math.abs(num)).toCharArray();
        // 排序
        Arrays.sort(chars);
        if (num >= 0) {
            if (chars[0] == '0') {
                // 若开头为0，则去找最近不为0的来互换
                for (int i = 1; i < chars.length; i++) {
                    if (chars[i] != '0') {
                        chars[0] = chars[i];
                        chars[i] = '0';
                        break;
                    }
                }
            }
            return Long.valueOf(new String(chars));
        } else {
            // 直接计算
            long result = 0;
            for (int i = 0; i < chars.length; i++) {
                result += (chars[i] - '0') * Math.pow(10, i);
            }
            return -result;
        }
    }

}
