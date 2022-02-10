package stack.medium;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;

/**
 * 下一个更大元素 II
 *
 * 给定一个循环数组（最后一个元素的下一个元素是数组的第一个元素），输出每个元素的下一个更大元素。数字 x 的下一个更大的元素是按数组遍历顺序，这个数字之后的第一个比它更大的数，这意味着你应该循环地搜索它的下一个更大的数。如果不存在，则输出 -1。
 *
 * 示例 1:
 *
 * 输入: [1,2,1]
 * 输出: [2,-1,2]
 * 解释: 第一个 1 的下一个更大的数是 2；
 * 数字 2 找不到下一个更大的数；
 * 第二个 1 的下一个最大的数需要循环搜索，结果也是 2。
 * 注意: 输入数组的长度不会超过 10000。
 *
 * @author chengzhy
 * @date 2022/2/10 11:02
 */
public class P503_NextGreaterElementII {

    public int[] nextGreaterElements(int[] nums) {
        int[] result = new int[nums.length];
        Deque<Integer> stack = new ArrayDeque<>();
        /*for (int i = 0; i < nums.length; i++) {
            result[i] = -1;
            while (!stack.isEmpty() && nums[i] > nums[stack.peek()]) {
                result[stack.pop()] = nums[i];
            }
            stack.push(i);
        }
        if (!stack.isEmpty()) {
            // 若栈内还有元素，则从0-栈内最底部元素下标再遍历一遍
            for (int i = 0; i <= stack.peekLast(); i++) {
                while (!stack.isEmpty() && nums[i] > nums[stack.peek()]) {
                    result[stack.pop()] = nums[i];
                }
                stack.push(i);
            }
        }*/
        // 代码优化，循环数组通过下标求余来循环
        Arrays.fill(result, -1);
        for (int i = 0; i < (nums.length << 1) - 1; i++) {
            while (!stack.isEmpty() && nums[i % nums.length] > nums[stack.peek()]) {
                result[stack.pop()] = nums[i % nums.length];
            }
            stack.push(i % nums.length);
        }
        return result;
    }

}
