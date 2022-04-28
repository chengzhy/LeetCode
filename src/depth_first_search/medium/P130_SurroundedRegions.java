package depth_first_search.medium;

/**
 * 被围绕的区域
 * <a href="https://leetcode.cn/problems/surrounded-regions/">🔗</a>
 *
 * 给你一个 m x n 的矩阵 board ，由若干字符 'X' 和 'O' ，找到所有被 'X' 围绕的区域，并将这些区域里所有的 'O' 用 'X' 填充。
 *  
 *
 * 示例 1：
 *
 *
 * 输入：board = [["X","X","X","X"],["X","O","O","X"],["X","X","O","X"],["X","O","X","X"]]
 * 输出：[["X","X","X","X"],["X","X","X","X"],["X","X","X","X"],["X","O","X","X"]]
 * 解释：被围绕的区间不会存在于边界上，换句话说，任何边界上的 'O' 都不会被填充为 'X'。 任何不在边界上，或不与边界上的 'O' 相连的 'O' 最终都会被填充为 'X'。如果两个元素在水平或垂直方向相邻，则称它们是“相连”的。
 * 示例 2：
 *
 * 输入：board = [["X"]]
 * 输出：[["X"]]
 *  
 *
 * 提示：
 *
 * m == board.length
 * n == board[i].length
 * 1 <= m, n <= 200
 * board[i][j] 为 'X' 或 'O'
 *
 * @author chengzhy
 * @date 2022/2/12 12:51
 */
public class P130_SurroundedRegions {
    private static final char X = 'X';
    private static final char O = 'O';

    public void solve(char[][] board) {
        int m = board.length, n = board[0].length;
        boolean[][] leave = new boolean[m][n];
        for (int i = 0; i < m; i++) {
            dfs(board, i, 0, leave);
            dfs(board, i, n - 1, leave);
        }
        for (int i = 0; i < n; i++) {
            dfs(board, 0, i, leave);
            dfs(board, m - 1, i, leave);
        }
        for (int i = 1; i < m - 1; i++) {
            for (int j = 1; j < n - 1; j++) {
                if (board[i][j] == O && !leave[i][j]) {
                    board[i][j] = X;
                }
            }
        }
    }

    private void dfs(char[][] board, int i, int j, boolean[][] leave) {
        if (i == -1 || j == -1 || i == board.length || j == board[0].length || board[i][j] == X || leave[i][j]) {
            return;
        }
        leave[i][j] = true;
        dfs(board, i - 1, j, leave);
        dfs(board, i + 1, j, leave);
        dfs(board, i, j - 1, leave);
        dfs(board, i, j + 1, leave);
    }

}
