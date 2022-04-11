package two_pointers.easy;

/**
 * 移动零
 * <a href="https://leetcode-cn.com/problems/move-zeroes/">🔗</a>
 *
 * 给定一个数组 nums，编写一个函数将所有 0 移动到数组的末尾，同时保持非零元素的相对顺序。
 *
 * 请注意 ，必须在不复制数组的情况下原地对数组进行操作。
 *
 *  
 *
 * 示例 1:
 *
 * 输入: nums = [0,1,0,3,12]
 * 输出: [1,3,12,0,0]
 * 示例 2:
 *
 * 输入: nums = [0]
 * 输出: [0]
 *  
 *
 * 提示:
 *
 * 1 <= nums.length <= 104
 * -231 <= nums[i] <= 231 - 1
 *  
 *
 * 进阶：你能尽量减少完成的操作次数吗？
 *
 * @author chengzhy
 * @date 2022/1/29 15:47
 */
public class P283_MoveZeroes {

    public void moveZeroes(int[] nums) {
        /**
         * 太慢了
         */
        /*for (int i = 0, j; i < nums.length - 1; i++) {
            if (nums[i] == 0) {
                for (j = i + 1; j < nums.length; j++) {
                    if (nums[j] != 0) {
                        nums[i] = nums[j];
                        nums[j] = 0;
                        break;
                    }
                }
            }
        }*/
        /**
         * 参考评论(巧妙)：第一个指针直接去寻找不为0放到前面，等放置完后再将第二个指针后的置为0即可
         */
        int j = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != 0) {
                nums[j++] = nums[i];
            }
        }
        while (j < nums.length) {
            nums[j++] = 0;
        }
    }

}
