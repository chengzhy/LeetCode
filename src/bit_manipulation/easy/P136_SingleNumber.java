package bit_manipulation.easy;

/**
 * 只出现一次的数字
 * <a href="https://leetcode.cn/problems/single-number/">🔗</a>
 *
 * 给定一个非空整数数组，除了某个元素只出现一次以外，其余每个元素均出现两次。找出那个只出现了一次的元素。
 *
 * 说明：
 *
 * 你的算法应该具有线性时间复杂度。 你可以不使用额外空间来实现吗？
 *
 * 示例 1:
 *
 * 输入: [2,2,1]
 * 输出: 1
 * 示例 2:
 *
 * 输入: [4,1,2,1,2]
 * 输出: 4
 *
 * @author chengzhy
 * @date 2021/11/5 10:40
 */
public class P136_SingleNumber {

    public int singleNumber(int[] nums) {
        int result = 0;
        // a^c^c^d^d^...n^n = a
        for (int num : nums) {
            result ^= num;
        }
        return result;
    }

}
