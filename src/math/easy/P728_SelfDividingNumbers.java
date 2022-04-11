package math.easy;

import java.util.ArrayList;
import java.util.List;

/**
 * 自除数
 * <a href="https://leetcode-cn.com/problems/self-dividing-numbers/">🔗</a>
 *
 * 自除数 是指可以被它包含的每一位数整除的数。
 *
 * 例如，128 是一个 自除数 ，因为 128 % 1 == 0，128 % 2 == 0，128 % 8 == 0。
 * 自除数 不允许包含 0 。
 *
 * 给定两个整数 left 和 right ，返回一个列表，列表的元素是范围 [left, right] 内所有的 自除数 。
 *
 *  
 *
 * 示例 1：
 *
 * 输入：left = 1, right = 22
 * 输出：[1, 2, 3, 4, 5, 6, 7, 8, 9, 11, 12, 15, 22]
 * 示例 2:
 *
 * 输入：left = 47, right = 85
 * 输出：[48,55,66,77]
 *  
 *
 * 提示：
 *
 * 1 <= left <= right <= 104
 *
 * @author chengzhy
 * @date 2022/3/31 11:08
 */
public class P728_SelfDividingNumbers {

    public List<Integer> selfDividingNumbers(int left, int right) {
        List<Integer> result = new ArrayList<>();
        for (int i = left; i <= right; i++) {
            int temp = i;
            while (temp != 0 && (temp % 10) != 0
                    && (i % (temp % 10)) == 0) {
                temp /= 10;
            }
            if (temp == 0) {
                result.add(i);
            }
        }
        return result;
    }

}
