package bit_manipulation.easy;

/**
 * 交替位二进制数
 * <a href="https://leetcode.cn/problems/binary-number-with-alternating-bits/">🔗</a>
 *
 * 给定一个正整数，检查它的二进制表示是否总是 0、1 交替出现：换句话说，就是二进制表示中相邻两位的数字永不相同。
 *
 *  
 *
 * 示例 1：
 *
 * 输入：n = 5
 * 输出：true
 * 解释：5 的二进制表示是：101
 * 示例 2：
 *
 * 输入：n = 7
 * 输出：false
 * 解释：7 的二进制表示是：111.
 * 示例 3：
 *
 * 输入：n = 11
 * 输出：false
 * 解释：11 的二进制表示是：1011.
 *  
 *
 * 提示：
 *
 * 1 <= n <= 231 - 1
 *
 * @author chengzhy
 * @date 2022/3/28 19:22
 */
public class P693_BinaryNumberWithAlternatingBits {

    public boolean hasAlternatingBits(int n) {
        /**
         * 如 010101 右移一位得到 001010
         * 二者异或之后得到011111 (这一步是关键,只有交替出现01，异或后才能得到结果0111111...11)
         * 为了判断 异或后的结果是否满足(0111111...11)类型的结果
         * 011111 加上1 为100000
         * 011111 与 100000按位相与 结果为000000 ， 也就是0;
         */
        int m = n ^ (n >> 1);
        return (m & (m + 1)) == 0;
    }

}
