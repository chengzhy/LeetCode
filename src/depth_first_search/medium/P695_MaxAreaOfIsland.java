package depth_first_search.medium;

/**
 * 岛屿的最大面积
 *
 * 给你一个大小为 m x n 的二进制矩阵 grid 。
 *
 * 岛屿 是由一些相邻的 1 (代表土地) 构成的组合，这里的「相邻」要求两个 1 必须在 水平或者竖直的四个方向上 相邻。你可以假设 grid 的四个边缘都被 0（代表水）包围着。
 *
 * 岛屿的面积是岛上值为 1 的单元格的数目。
 *
 * 计算并返回 grid 中最大的岛屿面积。如果没有岛屿，则返回面积为 0 。
 *
 *  
 *
 * 示例 1：
 *
 *
 * 输入：grid = [[0,0,1,0,0,0,0,1,0,0,0,0,0],[0,0,0,0,0,0,0,1,1,1,0,0,0],[0,1,1,0,1,0,0,0,0,0,0,0,0],[0,1,0,0,1,1,0,0,1,0,1,0,0],[0,1,0,0,1,1,0,0,1,1,1,0,0],[0,0,0,0,0,0,0,0,0,0,1,0,0],[0,0,0,0,0,0,0,1,1,1,0,0,0],[0,0,0,0,0,0,0,1,1,0,0,0,0]]
 * 输出：6
 * 解释：答案不应该是 11 ，因为岛屿只能包含水平或垂直这四个方向上的 1 。
 * 示例 2：
 *
 * 输入：grid = [[0,0,0,0,0,0,0,0]]
 * 输出：0
 *  
 *
 * 提示：
 *
 * m == grid.length
 * n == grid[i].length
 * 1 <= m, n <= 50
 * grid[i][j] 为 0 或 1
 *
 * @author chengzhy
 * @date 2022/2/12 13:30
 */
public class P695_MaxAreaOfIsland {
    private static final int WATER = 0;
    private static final int LAND = 1;
    private int area = 0;

    public int maxAreaOfIsland(int[][] grid) {
        int maxArea = Integer.MIN_VALUE;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                // 计算岛屿的面积
                if (grid[i][j] == LAND) {
                    // 新到一块岛屿，面积从0开始计算
                    area = 0;
                    dfs(grid, i, j);
                    // 取最大值
                    maxArea = Math.max(maxArea, area);
                }
            }
        }
        return Math.max(maxArea, area);
    }

    private void dfs(int[][] grid, int i, int j) {
        if (i == -1 || j == -1 || i == grid.length || j == grid[0].length || grid[i][j] == WATER) {
            return;
        }
        // 将相邻的陆地变为水来判断是否属于一个岛屿里的陆地
        grid[i][j] = WATER;
        // 面积+1
        area++;
        dfs(grid, i - 1, j);
        dfs(grid, i + 1, j);
        dfs(grid, i, j - 1);
        dfs(grid, i, j + 1);
    }

}
