package binary_search.medium;

/**
 * 寻找重复数
 *
 * 给定一个包含 n + 1 个整数的数组 nums ，其数字都在 [1, n] 范围内（包括 1 和 n），可知至少存在一个重复的整数。
 *
 * 假设 nums 只有 一个重复的整数 ，返回 这个重复的数 。
 *
 * 你设计的解决方案必须 不修改 数组 nums 且只用常量级 O(1) 的额外空间。
 *
 *  
 *
 * 示例 1：
 *
 * 输入：nums = [1,3,4,2,2]
 * 输出：2
 * 示例 2：
 *
 * 输入：nums = [3,1,3,4,2]
 * 输出：3
 *  
 *
 * 提示：
 *
 * 1 <= n <= 105
 * nums.length == n + 1
 * 1 <= nums[i] <= n
 * nums 中 只有一个整数 出现 两次或多次 ，其余整数均只出现 一次
 *  
 *
 * 进阶：
 *
 * 如何证明 nums 中至少存在一个重复的数字?
 * 你可以设计一个线性级时间复杂度 O(n) 的解决方案吗？
 *
 * @author chengzhy
 * @date 2022/2/17 13:57
 */
public class P287_FindTheDuplicateNumber {

    public int findDuplicate(int[] nums) {
        /**
         * 参考官方题解思路：
         * 举例{1,3,4,2,2}
         * 若没有重复元素，1-n则小于当前数的个数也为1-n，即
         * nums  1 2 3 4
         * count 1 2 3 4
         * 若重复元素个数为2，比如重复元素为3，那么nums和count情况如下：
         * nums  1 2 3 4
         * count 1 2 4 5
         * 若重复元素个数大于2，必然会造成有一个元素为缺失元素，此时又有两种情况
         * 若缺失元素大于重复元素，比如重复元素为3有3个，缺失元素为4，则nums和count情况如下：
         * nums  1 2 3 4 5
         * count 1 2 5 0 6
         * 若缺失元素小于重复元素，比如重复元素为3有3个，缺失元素为2，则nums和count情况如下：
         * nums  1 2 3 4 5
         * count 1 0 4 5 6
         * 由此可见，当有重复元素时，重复元素所在区间的数小于等于其自身的个数一定大于它本身这个数
         * 因此通过折半查找去寻找这个区间的第一个数，即为重复的数
         */
        int left = 1, right = nums.length - 1, mid, result = 0;
        while (left <= right) {
            // 假设重复的数为mid
            mid = left + (right - left) / 2;
            // 计数：小于mid的个数
            int count = 0;
            for (int num : nums) {
                if (num <= mid) {
                    count++;
                }
            }
            if (count <= mid) {
                // 如果计数小于等于mid，说明重复的数在mid这个数的右边
                left = mid + 1;
            } else {
                // 如果大于，说明这个重复的数就在这个区间内，先记录一下这个数，然后往左边区间寻找是否还有符合的数
                right = mid - 1;
                result = mid;
            }
        }
        return result;
    }

}
