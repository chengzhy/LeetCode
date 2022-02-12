package depth_first_search.medium;

/**
 * 飞地的数量
 *
 * 给你一个大小为 m x n 的二进制矩阵 grid ，其中 0 表示一个海洋单元格、1 表示一个陆地单元格。
 *
 * 一次 移动 是指从一个陆地单元格走到另一个相邻（上、下、左、右）的陆地单元格或跨过 grid 的边界。
 *
 * 返回网格中 无法 在任意次数的移动中离开网格边界的陆地单元格的数量。
 *
 *  
 *
 * 示例 1：
 *
 *
 * 输入：grid = [[0,0,0,0],[1,0,1,0],[0,1,1,0],[0,0,0,0]]
 * 输出：3
 * 解释：有三个 1 被 0 包围。一个 1 没有被包围，因为它在边界上。
 * 示例 2：
 *
 *
 * 输入：grid = [[0,1,1,0],[0,0,1,0],[0,0,1,0],[0,0,0,0]]
 * 输出：0
 * 解释：所有 1 都在边界上或可以到达边界。
 *  
 *
 * 提示：
 *
 * m == grid.length
 * n == grid[i].length
 * 1 <= m, n <= 500
 * grid[i][j] 的值为 0 或 1
 *
 * @author chengzhy
 * @date 2022/2/12 11:01
 */
public class P1020_NumberOfEnclaves {

    public int numEnclaves(int[][] grid) {
        int m = grid.length, n = grid[0].length, num = 0;
        // 判断是否可以离开的数组
        boolean[][] leave = new boolean[m][n];
        // 从边界开始寻找
        for (int i = 0; i < m; i++) {
            dfs(grid, i, 0, leave);
            dfs(grid, i, n - 1, leave);
        }
        for (int i = 0; i < n; i++) {
            dfs(grid, 0, i, leave);
            dfs(grid, m - 1, i, leave);
        }
        // 遍历为1且不能离开的点
        for (int i = 1; i < m - 1; i++) {
            for (int j = 1; j < n - 1; j++) {
                if (grid[i][j] == 1 && !leave[i][j]) {
                    num++;
                }
            }
        }
        return num;
    }

    private void dfs(int[][] grid, int i, int j, boolean[][] leave) {
        if (i == -1 || j == -1 || i == grid.length || j == grid[0].length || grid[i][j] == 0 || leave[i][j]) {
            return;
        }
        /**
         * 因为从边界开始找，所以边界为1的值一定可以离开；
         * 然后嵌套向四个方向寻找为1的点，如果旁边有相邻的1点，则该相邻的点也可以离开，层层递归
         */
        leave[i][j] = true;
        dfs(grid, i - 1, j, leave);
        dfs(grid, i, j - 1, leave);
        dfs(grid, i + 1, j, leave);
        dfs(grid, i, j  + 1, leave);
    }

}
