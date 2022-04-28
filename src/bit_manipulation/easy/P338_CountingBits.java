package bit_manipulation.easy;

/**
 * 比特位计数
 * <a href="https://leetcode.cn/problems/counting-bits/">🔗</a>
 *
 * 给你一个整数 n ，对于 0 <= i <= n 中的每个 i ，计算其二进制表示中 1 的个数 ，返回一个长度为 n + 1 的数组 ans 作为答案。
 *
 *  
 *
 * 示例 1：
 *
 * 输入：n = 2
 * 输出：[0,1,1]
 * 解释：
 * 0 --> 0
 * 1 --> 1
 * 2 --> 10
 * 示例 2：
 *
 * 输入：n = 5
 * 输出：[0,1,1,2,1,2]
 * 解释：
 * 0 --> 0
 * 1 --> 1
 * 2 --> 10
 * 3 --> 11
 * 4 --> 100
 * 5 --> 101
 *  
 *
 * 提示：
 *
 * 0 <= n <= 105
 *  
 *
 * 进阶：
 *
 * 很容易就能实现时间复杂度为 O(n log n) 的解决方案，你可以在线性时间复杂度 O(n) 内用一趟扫描解决此问题吗？
 * 你能不使用任何内置函数解决此问题吗？（如，C++ 中的 __builtin_popcount ）
 *
 * @author chengzhy
 * @date 2022/1/29 16:17
 */
public class P338_CountingBits {

    public int[] countBits(int n) {
        int[] ans = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            /**
             * 核心思想：能被2整除的数二进制中1的个数一定为1，若不能整除余数一定为1
             * 3-->11
             * 6-->110 6/2=3
             * 7-->111 7/2=3...1
             * ans[i] = ans[i/2] + (i&1); (i&1)可以理解为i若除以2余数为1，则i&1一定为1，否则为0
             */
            ans[i] = ans[i >> 1] + (i & 1);
        }
        return ans;
    }

}
