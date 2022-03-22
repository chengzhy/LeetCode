package binary_search.easy;

/**
 * x 的平方根
 *
 * 给你一个非负整数 x ，计算并返回 x 的 算术平方根 。
 *
 * 由于返回类型是整数，结果只保留 整数部分 ，小数部分将被 舍去 。
 *
 * 注意：不允许使用任何内置指数函数和算符，例如 pow(x, 0.5) 或者 x ** 0.5 。
 *
 *  
 *
 * 示例 1：
 *
 * 输入：x = 4
 * 输出：2
 * 示例 2：
 *
 * 输入：x = 8
 * 输出：2
 * 解释：8 的算术平方根是 2.82842..., 由于返回类型是整数，小数部分将被舍去。
 *  
 *
 * 提示：
 *
 * 0 <= x <= 231 - 1
 *
 * @author chengzhy
 * @date 2022/3/15 13:19
 */
public class P69_SqrtX {

    public int mySqrt(int x) {
        // 二分查找
        int left = 0, right = x;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            // 注意计算mid * mid可能溢出，所以应该转换为long
            if ((long) mid * mid <= x) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return right;
    }

}
