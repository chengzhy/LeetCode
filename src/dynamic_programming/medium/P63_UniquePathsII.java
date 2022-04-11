package dynamic_programming.medium;

/**
 * 不同路径 II
 * <a href="https://leetcode-cn.com/problems/unique-paths-ii/">🔗</a>
 *
 * 一个机器人位于一个 m x n 网格的左上角 （起始点在下图中标记为 “Start” ）。
 *
 * 机器人每次只能向下或者向右移动一步。机器人试图达到网格的右下角（在下图中标记为 “Finish”）。
 *
 * 现在考虑网格中有障碍物。那么从左上角到右下角将会有多少条不同的路径？
 *
 * 网格中的障碍物和空位置分别用 1 和 0 来表示。
 *
 *  
 *
 * 示例 1：
 *
 *
 * 输入：obstacleGrid = [[0,0,0],[0,1,0],[0,0,0]]
 * 输出：2
 * 解释：3x3 网格的正中间有一个障碍物。
 * 从左上角到右下角一共有 2 条不同的路径：
 * 1. 向右 -> 向右 -> 向下 -> 向下
 * 2. 向下 -> 向下 -> 向右 -> 向右
 * 示例 2：
 *
 *
 * 输入：obstacleGrid = [[0,1],[0,0]]
 * 输出：1
 *  
 *
 * 提示：
 *
 * m == obstacleGrid.length
 * n == obstacleGrid[i].length
 * 1 <= m, n <= 100
 * obstacleGrid[i][j] 为 0 或 1
 *
 * @author chengzhy
 * @date 2022/3/9 11:34
 */
public class P63_UniquePathsII {
    private static final int OBSTACLE = 1;
    private static final int SPACE = 0;

    public int uniquePathsWithObstacles(int[][] obstacleGrid) {
        int[][] dp = new int[obstacleGrid.length][obstacleGrid[0].length];
        /**
         * 初始化边界值注意点：如果当前位置是障碍，则后续边界值都不能到达，因此直接停止赋值
         */
        for (int i = 0; i < dp.length; i++) {
            if (obstacleGrid[i][0] == SPACE) {
                dp[i][0] = 1;
            } else {
                break;
            }
        }
        for (int i = 0; i < dp[0].length; i++) {
            if (obstacleGrid[0][i] == SPACE) {
                dp[0][i] = 1;
            } else {
                break;
            }
        }
        for (int i = 1; i < dp.length; i++) {
            for (int j = 1; j < dp[0].length; j++) {
                dp[i][j] = (obstacleGrid[i][j] == OBSTACLE) ? 0 : dp[i][j - 1] + dp[i - 1][j];
            }
        }
        return dp[dp.length - 1][dp[0].length - 1];
    }

}
