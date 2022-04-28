package math.medium;

import java.util.ArrayList;
import java.util.List;

/**
 * 最简分数
 * <a href="https://leetcode.cn/problems/simplified-fractions/">🔗</a>
 *
 * 给你一个整数 n ，请你返回所有 0 到 1 之间（不包括 0 和 1）满足分母小于等于  n 的 最简 分数 。分数可以以 任意 顺序返回。
 *
 *  
 *
 * 示例 1：
 *
 * 输入：n = 2
 * 输出：["1/2"]
 * 解释："1/2" 是唯一一个分母小于等于 2 的最简分数。
 * 示例 2：
 *
 * 输入：n = 3
 * 输出：["1/2","1/3","2/3"]
 * 示例 3：
 *
 * 输入：n = 4
 * 输出：["1/2","1/3","1/4","2/3","3/4"]
 * 解释："2/4" 不是最简分数，因为它可以化简为 "1/2" 。
 * 示例 4：
 *
 * 输入：n = 1
 * 输出：[]
 *  
 *
 * 提示：
 *
 * 1 <= n <= 100
 *
 * @author chengzhy
 * @date 2022/2/10 9:31
 */
public class P1447_SimplifiedFractions {

    public List<String> simplifiedFractions(int n) {
        List<String> result = (n < 2) ? new ArrayList<>(0) : new ArrayList<>();
        for (int i = 1; i <= n; i++) {
            for (int j = i + 1; j <= n; j++) {
                // 判断是否是最简分数==>判断两个数的最大公约数是否为1
                if (gcd(i, j) == 1) {
                    result.add(i + "/" + j);
                }
            }
        }
        return result;
    }

    private int gcd(int a, int b) {
        /**
         * 用大数对小数求余，若余数为0，则除数为最大公约数。
         * 若余数不为0，将此余数作为除数，小数作为被除数，重新求余，直到余数为0为止。此时的最大公约数为余数。
         * 例如：27和6.  27%6=3.  6%3=0.  所以最大公约数为3。
         */
        return a == 0 ? b : gcd((b % a), a);
    }

}
