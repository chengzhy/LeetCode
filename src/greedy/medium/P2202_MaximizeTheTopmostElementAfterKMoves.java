package greedy.medium;

/**
 * K 次操作后最大化顶端元素
 * <a href="https://leetcode.cn/problems/maximize-the-topmost-element-after-k-moves/">🔗</a>
 *
 * 给你一个下标从 0 开始的整数数组 nums ，它表示一个 栈 ，其中 nums[0] 是栈顶的元素。
 *
 * 每一次操作中，你可以执行以下操作 之一 ：
 *
 * 如果栈非空，那么 删除 栈顶端的元素。
 * 如果有超过 1 个或者多个被删除且不在栈中的元素，你可以从它们中选择任何一个，添加 回栈顶，这个元素成为新的栈顶元素。
 * 同时给你一个整数 k ，它表示你总共需要执行操作的次数。
 *
 * 请你返回 恰好 执行 k 次操作以后，栈顶元素的 最大值 。如果执行完 k 次操作以后，栈一定为空，请你返回 -1 。
 *
 *
 *
 * 示例 1：
 *
 * 输入：nums = [5,2,2,4,0,6], k = 4
 * 输出：5
 * 解释：
 * 4 次操作后，栈顶元素为 5 的方法之一为：
 * - 第 1 次操作：删除栈顶元素 5 ，栈变为 [2,2,4,0,6] 。
 * - 第 2 次操作：删除栈顶元素 2 ，栈变为 [2,4,0,6] 。
 * - 第 3 次操作：删除栈顶元素 2 ，栈变为 [4,0,6] 。
 * - 第 4 次操作：将 5 添加回栈顶，栈变为 [5,4,0,6] 。
 * 注意，这不是最后栈顶元素为 5 的唯一方式。但可以证明，4 次操作以后 5 是能得到的最大栈顶元素。
 * 示例 2：
 *
 * 输入：nums = [2], k = 1
 * 输出：-1
 * 解释：
 * 第 1 次操作中，我们唯一的选择是将栈顶元素弹出栈。
 * 由于 1 次操作后无法得到一个非空的栈，所以我们返回 -1 。
 *
 *
 * 提示：
 *
 * 1 <= nums.length <= 105
 * 0 <= nums[i], k <= 109
 *
 * @author chengzhy
 * @date 2022/3/13 10:59
 */
public class P2202_MaximizeTheTopmostElementAfterKMoves {

    public int maximumTop(int[] nums, int k) {
        int maxNum = -1;
        // 只有一个元素时，若k为奇数则栈必定为空
        if (nums.length == 1 && (k & 1) == 1) {
            return maxNum;
        }
        if (nums.length < k) {
            // 元素数量小于操作次数k时，每个元素都有机会变成栈顶元素，所以栈顶最大值即为元素的最大值
            for (int i = 0; i < nums.length; i++) {
                maxNum = (nums[i] > maxNum) ? nums[i] : maxNum;
            }
        } else {
            /**
             * 元素数量小于操作次数k时，返回前k - 1个元素中的最大值与第k + 1个元素(即下标为k)的元素的更大的元素
             * (因为当操作次数到达k - 1时，这时就要决定是取回之前的元素还是继续弹出元素，这都取决于这两个部分哪个值大)
             * 元素数量等于操作次数k时，返回前k - 1个元素中的最大值
             * (因为若等于，如果最后一个操作将最后一个元素弹出，栈必为空，所以结果一定为前k - 1个元素中的最大值)
             */
            for (int i = 0; i < k - 1; i++) {
                maxNum = (nums[i] > maxNum) ? nums[i] : maxNum;
            }
            if (k < nums.length) {
                maxNum = (nums[k] > maxNum) ? nums[k] : maxNum;
            }
        }
        return maxNum;
    }

}
