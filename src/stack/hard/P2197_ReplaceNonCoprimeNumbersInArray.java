package stack.hard;

import java.util.ArrayList;
import java.util.List;

/**
 * 替换数组中的非互质数
 * <a href="https://leetcode-cn.com/problems/replace-non-coprime-numbers-in-array/">🔗</a>
 *
 * 给你一个整数数组 nums 。请你对数组执行下述操作：
 *
 * 从 nums 中找出 任意 两个 相邻 的 非互质 数。
 * 如果不存在这样的数，终止 这一过程。
 * 否则，删除这两个数，并 替换 为它们的 最小公倍数（Least Common Multiple，LCM）。
 * 只要还能找出两个相邻的非互质数就继续 重复 这一过程。
 * 返回修改后得到的 最终 数组。可以证明的是，以 任意 顺序替换相邻的非互质数都可以得到相同的结果。
 *
 * 生成的测试用例可以保证最终数组中的值 小于或者等于 108 。
 *
 * 两个数字 x 和 y 满足 非互质数 的条件是：GCD(x, y) > 1 ，其中 GCD(x, y) 是 x 和 y 的 最大公约数 。
 *
 *  
 *
 * 示例 1 ：
 *
 * 输入：nums = [6,4,3,2,7,6,2]
 * 输出：[12,7,6]
 * 解释：
 * - (6, 4) 是一组非互质数，且 LCM(6, 4) = 12 。得到 nums = [12,3,2,7,6,2] 。
 * - (12, 3) 是一组非互质数，且 LCM(12, 3) = 12 。得到 nums = [12,2,7,6,2] 。
 * - (12, 2) 是一组非互质数，且 LCM(12, 2) = 12 。得到 nums = [12,7,6,2] 。
 * - (6, 2) 是一组非互质数，且 LCM(6, 2) = 6 。得到 nums = [12,7,6] 。
 * 现在，nums 中不存在相邻的非互质数。
 * 因此，修改后得到的最终数组是 [12,7,6] 。
 * 注意，存在其他方法可以获得相同的最终数组。
 * 示例 2 ：
 *
 * 输入：nums = [2,2,1,1,3,3,3]
 * 输出：[2,1,1,3]
 * 解释：
 * - (3, 3) 是一组非互质数，且 LCM(3, 3) = 3 。得到 nums = [2,2,1,1,3,3] 。
 * - (3, 3) 是一组非互质数，且 LCM(3, 3) = 3 。得到 nums = [2,2,1,1,3] 。
 * - (2, 2) 是一组非互质数，且 LCM(2, 2) = 2 。得到 nums = [2,1,1,3] 。
 * 现在，nums 中不存在相邻的非互质数。
 * 因此，修改后得到的最终数组是 [2,1,1,3] 。
 * 注意，存在其他方法可以获得相同的最终数组。
 *  
 *
 * 提示：
 *
 * 1 <= nums.length <= 105
 * 1 <= nums[i] <= 105
 * 生成的测试用例可以保证最终数组中的值 小于或者等于 108 。
 *
 * @author chengzhy
 * @date 2022/3/7 14:58
 */
public class P2197_ReplaceNonCoprimeNumbersInArray {

    public List<Integer> replaceNonCoprimes(int[] nums) {
        // 模拟栈
        List<Integer> result = new ArrayList<>();
        result.add(nums[0]);
        for (int i = 1; i < nums.length; i++) {
            int current = nums[i];
            while (!result.isEmpty()) {
                // 取结果集中最右边的元素与nums[i]计算最小公倍数
                int left = result.get(result.size() - 1);
                int gcd = gcd(left, current);
                if (gcd > 1) {
                    result.remove(result.size() - 1);
                    // 注意left * current可能超过int所能表示的范围
                    current = (int) (((long) left * current) / gcd);
                } else {
                    break;
                }
            }
            result.add(current);
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
