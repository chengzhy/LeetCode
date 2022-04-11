package bit_manipulation.medium;

/**
 * 只出现一次的数字 II
 * <a href="https://leetcode-cn.com/problems/single-number-ii/">🔗</a>
 *
 * 给你一个整数数组 nums ，除某个元素仅出现 一次 外，其余每个元素都恰出现 三次 。请你找出并返回那个只出现了一次的元素。
 *
 *  
 *
 * 示例 1：
 *
 * 输入：nums = [2,2,3,2]
 * 输出：3
 * 示例 2：
 *
 * 输入：nums = [0,1,0,1,0,1,99]
 * 输出：99
 *  
 *
 * 提示：
 *
 * 1 <= nums.length <= 3 * 104
 * -231 <= nums[i] <= 231 - 1
 * nums 中，除某个元素仅出现 一次 外，其余每个元素都恰出现 三次
 *  
 *
 * 进阶：你的算法应该具有线性时间复杂度。 你可以不使用额外空间来实现吗？
 *
 * @author chengzhy
 * @date 2021/11/5 10:44
 */
public class P137_SingleNumberII {

    public int singleNumber(int[] nums) {
        int a = 0, b = 0;
        /**
         * 0^x = x; x^x = 0; x&~x = 0; x&~0 = x;
         *
         * 一开始a = 0, b = 0;
         * x第一次出现后，b = (b ^ x) & ~a的结果为b = x, a = (a ^ x) & ~b的结果为(此时因为b = x了，所以)a = 0;
         * x第二次出现：b = (b ^ x) & ~a, b = (x ^ x) & ~0, b = 0; a = (a ^ x) & ~b 化简， a = (0 ^ x) & ~0, a = x;
         * x第三次出现：b = (b ^ x) & ~a, b = (0 ^ x) & ~x, b = 0; a = (a ^ x) & ~b 化简， a = (x ^ x) & ~0, a = 0;
         * 所以出现三次同一个数，a和b最终都变回了0。
         * 只出现一次的数，按照上面x第一次出现的规律可知a = 0, b = x;因此最后返回b。
         */
        for (int num : nums) {
            b = (b ^ num) & ~a;
            a = (a ^ num) & ~b;
        }
        return b;
    }

}
