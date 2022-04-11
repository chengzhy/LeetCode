package breadth_first_search.medium;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 地图分析
 * <a href="https://leetcode-cn.com/problems/as-far-from-land-as-possible/">🔗</a>
 *
 * 你现在手里有一份大小为 n x n 的 网格 grid，上面的每个 单元格 都用 0 和 1 标记好了。其中 0 代表海洋，1 代表陆地。
 *
 * 请你找出一个海洋单元格，这个海洋单元格到离它最近的陆地单元格的距离是最大的，并返回该距离。如果网格上只有陆地或者海洋，请返回 -1。
 *
 * 我们这里说的距离是「曼哈顿距离」（ Manhattan Distance）：(x0, y0) 和 (x1, y1) 这两个单元格之间的距离是 |x0 - x1| + |y0 - y1| 。
 *
 *  
 *
 * 示例 1：
 *
 *
 *
 * 输入：grid = [[1,0,1],[0,0,0],[1,0,1]]
 * 输出：2
 * 解释：
 * 海洋单元格 (1, 1) 和所有陆地单元格之间的距离都达到最大，最大距离为 2。
 * 示例 2：
 *
 *
 *
 * 输入：grid = [[1,0,0],[0,0,0],[0,0,0]]
 * 输出：4
 * 解释：
 * 海洋单元格 (2, 2) 和所有陆地单元格之间的距离都达到最大，最大距离为 4。
 *  
 *
 * 提示：
 *
 * n == grid.length
 * n == grid[i].length
 * 1 <= n <= 100
 * grid[i][j] 不是 0 就是 1
 *
 * @author chengzhy
 * @date 2022/3/31 12:23
 */
public class P1162_AsFarFromLandAsPossible {
    public static final int[][] DIRS = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

    public int maxDistance(int[][] grid) {
        int m = grid.length, n = grid[0].length, maxDistance = -1;
        Queue<int[]> queue = new LinkedList<>();
        int[][] distance = new int[m][n];
        boolean[][] visited = new boolean[m][n];
        // 将所有陆地加入队列，从陆地开始遍历
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 1) {
                    queue.offer(new int[]{i, j});
                    visited[i][j] = true;
                }
            }
        }
        while (!queue.isEmpty()) {
            int[] temp = queue.poll();
            int i = temp[0], j = temp[1];
            // 从4个方向继续遍历
            for (int k = 0; k < 4; k++) {
                int newi = i + DIRS[k][0], newj = j + DIRS[k][1];
                // 将未访问过的海洋加入队列并更新距离
                if (newi >= 0 && newi < m && newj >= 0 && newj < n && !visited[newi][newj]) {
                    distance[newi][newj] = distance[i][j] + 1;
                    maxDistance = Math.max(maxDistance, distance[newi][newj]);
                    queue.offer(new int[]{newi, newj});
                    visited[newi][newj] = true;
                }
            }
        }
        return maxDistance;
    }

}
