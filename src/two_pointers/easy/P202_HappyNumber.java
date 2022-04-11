package two_pointers.easy;

import java.util.HashSet;
import java.util.Set;

/**
 * 快乐数
 * <a href="https://leetcode-cn.com/problems/happy-number/">🔗</a>
 *
 * 编写一个算法来判断一个数 n 是不是快乐数。
 *
 * 「快乐数」 定义为：
 *
 * 对于一个正整数，每一次将该数替换为它每个位置上的数字的平方和。
 * 然后重复这个过程直到这个数变为 1，也可能是 无限循环 但始终变不到 1。
 * 如果这个过程 结果为 1，那么这个数就是快乐数。
 * 如果 n 是 快乐数 就返回 true ；不是，则返回 false 。
 *
 *  
 *
 * 示例 1：
 *
 * 输入：n = 19
 * 输出：true
 * 解释：
 * 12 + 92 = 82
 * 82 + 22 = 68
 * 62 + 82 = 100
 * 12 + 02 + 02 = 1
 * 示例 2：
 *
 * 输入：n = 2
 * 输出：false
 *  
 *
 * 提示：
 *
 * 1 <= n <= 231 - 1
 *
 * @author chengzhy
 * @date 2022/3/18 10:14
 */
public class P202_HappyNumber {

    public boolean isHappy(int n) {
        /*Set<Integer> set = new HashSet<>();
        while (!set.contains(n)) {
            if (n == 1) {
                return true;
            }
            set.add(n);
            int temp = 0;
            while (n > 0) {
                temp += Math.pow(n % 10, 2);
                n /= 10;
            }
            n = temp;
        }
        return false;*/
        int slow = n;
        int fast = getNext(n);
        while (fast != 1 && slow != fast) {
            slow = getNext(slow);
            fast = getNext(getNext(fast));
        }
        return fast == 1;
    }

    private int getNext(int n) {
        int temp = 0;
        while (n > 0) {
            int d = n % 10;
            n = n / 10;
            temp += d * d;
        }
        return temp;
    }

}
