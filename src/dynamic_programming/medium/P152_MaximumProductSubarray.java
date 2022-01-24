package dynamic_programming.medium;

/**
 * 乘积最大子数组
 *
 * 给你一个整数数组 nums ，请你找出数组中乘积最大的连续子数组（该子数组中至少包含一个数字），并返回该子数组所对应的乘积。
 *
 *  
 *
 * 示例 1:
 *
 * 输入: [2,3,-2,4]
 * 输出: 6
 * 解释: 子数组 [2,3] 有最大乘积 6。
 * 示例 2:
 *
 * 输入: [-2,0,-1]
 * 输出: 0
 * 解释: 结果不能为 2, 因为 [-2,-1] 不是子数组。
 *
 * @author chengzhy
 * @date 2022/1/21 22:01
 */
public class P152_MaximumProductSubarray {

    public int maxProduct(int[] nums) {
        /**
         * 首先假设存在某个最大乘积，然后对数组遍历，在经过每个元素的时候，有以下四种情况：
         * 如果该元素为正数：
         *     如果到上一个元素为止的最大乘积也是正数，那么直接乘上就好了，同样的最大乘积也会变得更大
         *     如果到上一个元素为止的最大乘积是负数，那么最大乘积就会变成该元素本身，且连续性被断掉
         * 如果该元素为负数：
         *     如果到上一个元素为止的最大乘积也是负数，那么直接乘上就好了，同样的最大乘积也会变得更大
         *     如果到上一个元素为止的最大乘积是正数，那么最大乘积就会不变，且连续性被断掉
         * 以上四种情况中说到的最大乘积都是临时最大乘积，每遍历新的元素都需要进行比较来确定真正的最大乘积。
         * 如果细心的话就可以发现，如果要得到乘以当前元素以后的最大乘积，需要记录最大乘积，也要记录最小乘积，因为最小值可能翻身变最大值。
         */
        int max = 1, min = 1, maxProduct = Integer.MIN_VALUE;
        for (int num : nums) {
            if (num < 0) {
                int temp = max;
                max = min;
                min = temp;
            }
            max = Math.max(max * num, num);
            min = Math.min(min * num, num);
            maxProduct = Math.max(maxProduct, max);
        }
        return maxProduct;
    }

}
