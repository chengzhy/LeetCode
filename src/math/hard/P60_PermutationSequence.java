package math.hard;

/**
 * 排列序列
 * <a href="https://leetcode.cn/problems/permutation-sequence/">🔗</a>
 *
 * 给出集合 [1,2,3,...,n]，其所有元素共有 n! 种排列。
 *
 * 按大小顺序列出所有排列情况，并一一标记，当 n = 3 时, 所有排列如下：
 *
 * "123"
 * "132"
 * "213"
 * "231"
 * "312"
 * "321"
 * 给定 n 和 k，返回第 k 个排列。
 *
 *  
 *
 * 示例 1：
 *
 * 输入：n = 3, k = 3
 * 输出："213"
 * 示例 2：
 *
 * 输入：n = 4, k = 9
 * 输出："2314"
 * 示例 3：
 *
 * 输入：n = 3, k = 1
 * 输出："123"
 *  
 *
 * 提示：
 *
 * 1 <= n <= 9
 * 1 <= k <= n!
 *
 * @author chengzhy
 * @date 2022/3/14 14:32
 */
public class P60_PermutationSequence {

    public String getPermutation(int n, int k) {
        StringBuilder sb = new StringBuilder();
        // 用于存放(n - 1)!的值
        int[] factorial = new int[n];
        factorial[0] = 1;
        for (int i = 1; i < factorial.length; i++) {
            factorial[i] = factorial[i - 1] * i;
        }
        // 记录i+1的值是否被用过
        boolean[] used = new boolean[n];
        /**
         * 数学思想：
         * 如果k <= (n - 1)!，则排列的第一个字母为1
         * 如果(n - 1)! < k <= 2 * (n - 1)!，则排列的第一个字母为2
         * ...
         * 如果(n - 1)(n - 1)! < k <= n * (n - 1)!，则排列的第一个字母为n
         * 两边同时除以(n - 1)!得 n - 1 < k/(n - 1)! <= n
         * 因此，第k个排列的首字母为an = (向下取整除法运算)(k - 1)/(n - 1)! + 1;
         * 当确定了首字母后，也可以按照同样的思路确定后续字母，例如第二个字母
         * 以(1,n)\a1最小的元素为a2的排列有(n - 2)!
         * 以(1,n)\a1次小的元素为a2的排列有(n - 2)!
         * 以(1,n)\a1最大的元素为a2的排列有(n - 2)!
         * 在确定了ai后，这n − i + 1个元素的第k个排列，
         * 就等于ai之后跟着剩余n − i个元素的第(k − 1) mod(n − i)! + 1个排列。
         */
        k--;
        for (int i = 1; i <= n; i++) {
            // order是通过公式计算出来的，暂时不会这个公式推导
            int order = k / factorial[n - i] + 1, count = 0;
            for (int j = 1; j <= n; j++) {
                if (!used[j - 1]) {
                    // 计数是第几个未使用的数字
                    count++;
                }
                // 找到对应位置的数字
                if (order == count) {
                    sb.append(j);
                    used[j - 1] = true;
                    break;
                }
            }
            // 暂时不会这个公式推导
            k %= factorial[n - i];
        }
        return sb.toString();
    }

}
