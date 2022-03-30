package breadth_first_search.medium;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 腐烂的橘子
 *
 * 在给定的 m x n 网格 grid 中，每个单元格可以有以下三个值之一：
 *
 * 值 0 代表空单元格；
 * 值 1 代表新鲜橘子；
 * 值 2 代表腐烂的橘子。
 * 每分钟，腐烂的橘子 周围 4 个方向上相邻 的新鲜橘子都会腐烂。
 *
 * 返回 直到单元格中没有新鲜橘子为止所必须经过的最小分钟数。如果不可能，返回 -1 。
 *
 *  
 *
 * 示例 1：
 *
 *
 *
 * 输入：grid = [[2,1,1],[1,1,0],[0,1,1]]
 * 输出：4
 * 示例 2：
 *
 * 输入：grid = [[2,1,1],[0,1,1],[1,0,1]]
 * 输出：-1
 * 解释：左下角的橘子（第 2 行， 第 0 列）永远不会腐烂，因为腐烂只会发生在 4 个正向上。
 * 示例 3：
 *
 * 输入：grid = [[0,2]]
 * 输出：0
 * 解释：因为 0 分钟时已经没有新鲜橘子了，所以答案就是 0 。
 *  
 *
 * 提示：
 *
 * m == grid.length
 * n == grid[i].length
 * 1 <= m, n <= 10
 * grid[i][j] 仅为 0、1 或 2
 *
 * @author chengzhy
 * @date 2022/3/30 18:35
 */
public class P994_RottingOranges {
    public static final int[][] DIRS = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

    public int orangesRotting(int[][] grid) {
        int m = grid.length, n = grid[0].length, minute = 0, freshNum = 0;
        Queue<int[]> queue = new LinkedList<>();
        boolean[][] rotten = new boolean[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 0) {
                    // 空位置标识为腐烂
                    rotten[i][j] = true;
                } else if (grid[i][j] == 1) {
                    // 统计新鲜橘子的个数
                    freshNum++;
                } else {
                    // 将腐烂橘子加入队列遍历
                    queue.offer(new int[]{i, j});
                    rotten[i][j] = true;
                }
            }
        }
        // 队列不为空并且有新鲜的橘子
        while (!queue.isEmpty() && freshNum != 0) {
            int size = queue.size();
            // 通过size来区分轮数
            for (int k = 0; k < size; k++) {
                int[] temp = queue.poll();
                int i = temp[0], j = temp[1];
                for (int l = 0; l < 4; l++) {
                    int newi = i + DIRS[l][0], newj = j + DIRS[l][1];
                    if (newi >= 0 && newi < m && newj >= 0 && newj < n && !rotten[newi][newj]) {
                        freshNum--;
                        queue.offer(new int[]{newi, newj});
                        rotten[newi][newj] = true;
                    }
                }
            }
            // 计时
            minute++;
        }
        return (freshNum == 0) ? minute : -1;
    }

}
