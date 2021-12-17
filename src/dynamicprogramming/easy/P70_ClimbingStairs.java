package dynamicprogramming.easy;

/**
 * 爬楼梯
 *
 * 假设你正在爬楼梯。需要 n 阶你才能到达楼顶。
 *
 * 每次你可以爬 1 或 2 个台阶。你有多少种不同的方法可以爬到楼顶呢？
 *
 * 注意：给定 n 是一个正整数。
 *
 * 示例 1：
 *
 * 输入： 2
 * 输出： 2
 * 解释： 有两种方法可以爬到楼顶。
 * 1.  1 阶 + 1 阶
 * 2.  2 阶
 * 示例 2：
 *
 * 输入： 3
 * 输出： 3
 * 解释： 有三种方法可以爬到楼顶。
 * 1.  1 阶 + 1 阶 + 1 阶
 * 2.  1 阶 + 2 阶
 * 3.  2 阶 + 1 阶
 *
 * @author chengzhy
 * @date 2021/12/17 14:40
 */
public class P70_ClimbingStairs {

    public int climbStairs(int n) {
        // f(x) = f(x - 1) + f(x - 2);爬到第x级台阶的方案数，考虑最后一步可能跨了一级台阶，也可能跨了两级台阶
        int a = 0, b = 0, count = 1;
        for (int i = 1; i <= n; i++) {
            a = b;
            b = count;
            count = a + b;
        }
        return count;
    }

}
