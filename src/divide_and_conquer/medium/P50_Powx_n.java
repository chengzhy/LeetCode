package divide_and_conquer.medium;

/**
 * Pow(x, n)
 * <a href="https://leetcode-cn.com/problems/powx-n/">🔗</a>
 *
 * 实现 pow(x, n) ，即计算 x 的 n 次幂函数（即，xn ）。
 *
 *  
 *
 * 示例 1：
 *
 * 输入：x = 2.00000, n = 10
 * 输出：1024.00000
 * 示例 2：
 *
 * 输入：x = 2.10000, n = 3
 * 输出：9.26100
 * 示例 3：
 *
 * 输入：x = 2.00000, n = -2
 * 输出：0.25000
 * 解释：2-2 = 1/22 = 1/4 = 0.25
 *  
 *
 * 提示：
 *
 * -100.0 < x < 100.0
 * -231 <= n <= 231-1
 * -104 <= xn <= 104
 *
 * @author chengzhy
 * @date 2022/3/17 11:13
 */
public class P50_Powx_n {

    public double myPow(double x, int n) {
        // 要防止n为负数时的越界情况
        long N = n;
        return (N >= 0) ? divideAndConquer(x, N) : 1.0 / divideAndConquer(x, -N);
    }

    private double divideAndConquer(double x, long N) {
        if (N == 0) {
            return 1.0;
        }
        double y = divideAndConquer(x, N / 2);
        return ((N & 1) == 0) ? y * y : y * y * x;
    }

}
