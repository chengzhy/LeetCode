package breadth_first_search.medium;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 01 矩阵
 *
 * 给定一个由 0 和 1 组成的矩阵 mat ，请输出一个大小相同的矩阵，其中每一个格子是 mat 中对应位置元素到最近的 0 的距离。
 *
 * 两个相邻元素间的距离为 1 。
 *
 *  
 *
 * 示例 1：
 *
 *
 *
 * 输入：mat = [[0,0,0],[0,1,0],[0,0,0]]
 * 输出：[[0,0,0],[0,1,0],[0,0,0]]
 * 示例 2：
 *
 *
 *
 * 输入：mat = [[0,0,0],[0,1,0],[1,1,1]]
 * 输出：[[0,0,0],[0,1,0],[1,2,1]]
 *  
 *
 * 提示：
 *
 * m == mat.length
 * n == mat[i].length
 * 1 <= m, n <= 104
 * 1 <= m * n <= 104
 * mat[i][j] is either 0 or 1.
 * mat 中至少有一个 0 
 *
 * @author chengzhy
 * @date 2022/3/30 15:08
 */
public class P542_01Matrix {
    public static final int[][] DIRS = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

    public int[][] updateMatrix(int[][] mat) {
        /**
         * 参考官方题解：多源最短路径(BFS)
         * 原先思路可以从矩阵中的1开始向四个方向搜索，但是此方法不是最优解，会超时
         * 换个思路：从矩阵中的0开始向四个方向搜索，就能保证每一步到最近的1的距离一定最短
         *
         * 对于Tree的BFS(典型的单源BFS)：首先把 root 节点入队，再一层一层遍历就行了。
         * 对于图的BFS(多源BFS)做法其实也是一样，与Tree的BFS的区别注意以下两条：
         * 1.Tree只有1个root，而图可以有多个源点，所以首先需要把多个源点都入队；
         * 2.Tree是有向的因此不需要标识是否访问过，而对于无向图来说，必须得标志是否访问过，
         * 并且为了防止某个节点多次入队，需要在其入队之前就将其设置成已访问！
         */
        int m = mat.length, n = mat[0].length;
        int[][] result = new int[m][n];
        boolean[][] visited = new boolean[m][n];
        Queue<int[]> queue = new LinkedList<>();
        // 首先将所有的0都入队，并将0的点设置为访问过
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (mat[i][j] == 0) {
                    queue.offer(new int[]{i, j});
                    visited[i][j] = true;
                }
            }
        }
        while (!queue.isEmpty()) {
            int[] temp = queue.poll();
            int i = temp[0], j = temp[1];
            // 找4个方向上的点
            for (int k = 0; k < 4; k++) {
                int ni = i + DIRS[k][0], nj = j + DIRS[k][1];
                // 在矩阵范围内并且未访问过
                if (ni >= 0 && ni < m && nj >= 0 && nj < n && !visited[ni][nj]) {
                    result[ni][nj] = result[i][j] + 1;
                    // 将此节点加入队列，以便从这个节点继续搜索
                    queue.offer(new int[]{ni, nj});
                    visited[ni][nj] = true;
                }
            }
        }
        return result;
    }

}
