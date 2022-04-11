package brainteaser.medium;

/**
 * 统计道路上的碰撞次数
 * <a href="https://leetcode-cn.com/problems/count-collisions-on-a-road/">🔗</a>
 *
 * 在一条无限长的公路上有 n 辆汽车正在行驶。汽车按从左到右的顺序按从 0 到 n - 1 编号，每辆车都在一个 独特的 位置。
 *
 * 给你一个下标从 0 开始的字符串 directions ，长度为 n 。directions[i] 可以是 'L'、'R' 或 'S' 分别表示第 i 辆车是向 左 、向 右 或者 停留 在当前位置。每辆车移动时 速度相同 。
 *
 * 碰撞次数可以按下述方式计算：
 *
 * 当两辆移动方向 相反 的车相撞时，碰撞次数加 2 。
 * 当一辆移动的车和一辆静止的车相撞时，碰撞次数加 1 。
 * 碰撞发生后，涉及的车辆将无法继续移动并停留在碰撞位置。除此之外，汽车不能改变它们的状态或移动方向。
 *
 * 返回在这条道路上发生的 碰撞总次数 。
 *
 *
 *
 * 示例 1：
 *
 * 输入：directions = "RLRSLL"
 * 输出：5
 * 解释：
 * 将会在道路上发生的碰撞列出如下：
 * - 车 0 和车 1 会互相碰撞。由于它们按相反方向移动，碰撞数量变为 0 + 2 = 2 。
 * - 车 2 和车 3 会互相碰撞。由于 3 是静止的，碰撞数量变为 2 + 1 = 3 。
 * - 车 3 和车 4 会互相碰撞。由于 3 是静止的，碰撞数量变为 3 + 1 = 4 。
 * - 车 4 和车 5 会互相碰撞。在车 4 和车 3 碰撞之后，车 4 会待在碰撞位置，接着和车 5 碰撞。碰撞数量变为 4 + 1 = 5 。
 * 因此，将会在道路上发生的碰撞总次数是 5 。
 * 示例 2：
 *
 * 输入：directions = "LLRR"
 * 输出：0
 * 解释：
 * 不存在会发生碰撞的车辆。因此，将会在道路上发生的碰撞总次数是 0 。
 *
 *
 * 提示：
 *
 * 1 <= directions.length <= 105
 * directions[i] 的值为 'L'、'R' 或 'S'
 *
 * @author chengzhy
 * @date 2022/3/20 11:01
 */
public class P2211_CountCollisionsOnARoad {
    private static final char LEFT = 'L';
    private static final char RIGHT = 'R';
    private static final char STOP = 'S';

    public int countCollisions(String directions) {
        /**
         * 根据题意可知：左侧边为L或右侧边为R的车是不会相撞的，那么中间的车一定会被撞停
         * 因此只需统计中间的、一开始没有停止的车辆数（即不是’S’的车辆数）即可
         */
        int count = 0, left = 0, right = directions.length() - 1;
        char[] chars = directions.toCharArray();
        // 从左往右找第一个不为L的位置
        while (left <= right && chars[left] == LEFT) {
            left++;
        }
        // 从右往左找第一个不为R的位置
        while (left <= right && chars[right] == RIGHT) {
            right--;
        }
        // 区间中的车辆数即为所求的碰撞次数
        for (int i = left; i <= right; i++) {
            if (chars[i] != STOP) {
                count++;
            }
        }
        return count;
    }

}
