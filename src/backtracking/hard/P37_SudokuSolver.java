package backtracking.hard;

/**
 * 解数独
 *
 * 编写一个程序，通过填充空格来解决数独问题。
 *
 * 一个数独的解法需遵循如下规则：
 *
 * 数字 1-9 在每一行只能出现一次。
 * 数字 1-9 在每一列只能出现一次。
 * 数字 1-9 在每一个以粗实线分隔的 3x3 宫内只能出现一次。
 * 空白格用 '.' 表示。
 *
 *
 *
 * 一个数独。
 *
 *
 *
 * 答案被标成红色。
 *
 * 提示：
 *
 * 给定的数独序列只包含数字 1-9 和字符 '.' 。
 * 你可以假设给定的数独只有唯一解。
 * 给定数独永远是 9x9 形式的。
 *
 * @author chengzhy
 * @date 2022/1/27 16:00
 **/
public class P37_SudokuSolver {
    /**
     * 最优算法，可惜看不懂
     */
    /*public void solveSudoku(char[][] board) {
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                char c = board[i][j];
                if (c != '.') {
                    int num = c - '0' -1;
                    flip(num, i, j);
                }
            }
        }
        while (true){
            boolean modified = false;
            for (int i = 0; i < 9; i++) {
                for (int j = 0; j < 9; j++) {
                    char c = board[i][j];
                    if (c == '.') {
                        int mask = ~(rows[i] | cols[j] | grids[i/3][j/3]) & 0x1ff;
                        if((mask & (mask-1))==0){
                            modified = true;
                            int num = Integer.bitCount(mask -1);
                            flip(num,i,j);
                            board[i][j] = (char)(num + 1+'0');
                        }
                    }
                }
            }
            if(!modified)break;
        }
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                char c = board[i][j];
                if (c == '.') spaces.add(new int[]{i, j});
            }
        }
        backTrace(board,0);
    }

    int[] rows = new int[9];
    int[] cols = new int[9];
    int[][] grids = new int[3][3];
    List<int[]> spaces = new ArrayList<>();
    boolean valid = false;
    private void backTrace(char[][] board, int pos) {
        if(spaces.size()==pos){
            valid = true;
            return;
        }
        int[] space = spaces.get(pos);
        int i = space[0],j = space[1];
        int mask = ~(rows[i] | cols[j]|grids[i/3][j/3]) & 0x1ff;
        for (; mask!=0 && !valid; mask &= (mask-1)) {
            int numMask = mask & (-mask);
            int num = Integer.bitCount(numMask -1);
            board[i][j] = (char)(num + 1 + '0');
            flip(num,i,j);
            backTrace(board, pos+1);
            flip(num,i,j);
        }
    }

    public void flip(int num, int i, int j){
        rows[i] ^= (1 << num);
        cols[j] ^= (1 << num);
        grids[i / 3][j / 3] ^= (1 << num);
    }*/

    public void solveSudoku(char[][] board) {
        /**
         * 看的懂的答案
         */
        /**
         * 记录某行，某位数字是否已经被摆放
         */
        boolean[][] row = new boolean[9][9];
        /**
         * 记录某列，某位数字是否已经被摆放
         */
        boolean[][] col = new boolean[9][9];
        /**
         * 记录某 3x3 宫格内，某位数字是否已经被摆放
         */
        boolean[][] block = new boolean[9][9];
        // 标记
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (board[i][j] != '.') {
                    int num = board[i][j] - '1';
                    row[i][num] = true;
                    col[j][num] = true;
                    // blockIndex = i / 3 * 3 + j / 3，取整
                    block[i / 3 * 3 + j / 3][num] = true;
                }
            }
        }
        dfs(board, row, col, block, 0, 0);
    }

    private boolean dfs(char[][] board, boolean[][] row, boolean[][] col, boolean[][] block, int i, int j) {
        // 找寻空位置
        while (board[i][j] != '.') {
            if (++j >= 9) {
                i++;
                j = 0;
            }
            if (i >= 9) {
                return true;
            }
        }
        // 从0-9填入
        for (int num = 0; num < 9; num++) {
            int blockIndex = i / 3 * 3 + j / 3;
            // 如果符合填入条件
            if (!row[i][num] && !col[j][num] && !block[blockIndex][num]) {
                // 递归
                board[i][j] = (char) ('1' + num);
                row[i][num] = true;
                col[j][num] = true;
                block[blockIndex][num] = true;
                if (dfs(board, row, col, block, i, j)) {
                    return true;
                } else {
                    // 回溯
                    row[i][num] = false;
                    col[j][num] = false;
                    block[blockIndex][num] = false;
                    board[i][j] = '.';
                }
            }
        }
        return false;
    }
}
