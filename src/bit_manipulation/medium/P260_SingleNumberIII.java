package bit_manipulation.medium;

/**
 * 只出现一次的数字 III
 * <a href="https://leetcode.cn/problems/single-number-iii/">🔗</a>
 *
 * 给定一个整数数组 nums，其中恰好有两个元素只出现一次，其余所有元素均出现两次。 找出只出现一次的那两个元素。你可以按 任意顺序 返回答案。
 *
 *  
 *
 * 进阶：你的算法应该具有线性时间复杂度。你能否仅使用常数空间复杂度来实现？
 *
 *  
 *
 * 示例 1：
 *
 * 输入：nums = [1,2,1,3,2,5]
 * 输出：[3,5]
 * 解释：[5, 3] 也是有效的答案。
 * 示例 2：
 *
 * 输入：nums = [-1,0]
 * 输出：[-1,0]
 * 示例 3：
 *
 * 输入：nums = [0,1]
 * 输出：[1,0]
 * 提示：
 *
 * 2 <= nums.length <= 3 * 104
 * -231 <= nums[i] <= 231 - 1
 * 除两个只出现一次的整数外，nums 中的其他数字都出现两次
 *
 * @author chengzhy
 * @date 2021/10/30 11:16
 */
public class P260_SingleNumberIII {

    public int[] singleNumber(int[] nums) {
        int xor = 0;
        // a^b^c^c^d^d^...n^n = a^b
        for (int num : nums) {
            xor ^= num;
        }
        // 取xor = a^b的二进制数最右边为1的数
        xor &= -xor;
        int[] results = new int[2];
        // 将nums分为两组，一组可以得出a，一组可以得出b
        // 例如一组为a^c^c^e^e = a，一组为b^d^d^f^f = b
        for (int num : nums) {
            if ((num & xor) == 0) {
                results[0] ^= num;
            } else {
                results[1] ^= num;
            }
        }
        return results;
    }

}
