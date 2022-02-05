package backtracking.medium;

/**
 * 黄金矿工
 *
 * 你要开发一座金矿，地质勘测学家已经探明了这座金矿中的资源分布，并用大小为 m * n 的网格 grid 进行了标注。每个单元格中的整数就表示这一单元格中的黄金数量；如果该单元格是空的，那么就是 0。
 *
 * 为了使收益最大化，矿工需要按以下规则来开采黄金：
 *
 * 每当矿工进入一个单元，就会收集该单元格中的所有黄金。
 * 矿工每次可以从当前位置向上下左右四个方向走。
 * 每个单元格只能被开采（进入）一次。
 * 不得开采（进入）黄金数目为 0 的单元格。
 * 矿工可以从网格中 任意一个 有黄金的单元格出发或者是停止。
 *  
 *
 * 示例 1：
 *
 * 输入：grid = [[0,6,0],[5,8,7],[0,9,0]]
 * 输出：24
 * 解释：
 * [[0,6,0],
 *  [5,8,7],
 *  [0,9,0]]
 * 一种收集最多黄金的路线是：9 -> 8 -> 7。
 * 示例 2：
 *
 * 输入：grid = [[1,0,7],[2,0,6],[3,4,5],[0,3,0],[9,0,20]]
 * 输出：28
 * 解释：
 * [[1,0,7],
 *  [2,0,6],
 *  [3,4,5],
 *  [0,3,0],
 *  [9,0,20]]
 * 一种收集最多黄金的路线是：1 -> 2 -> 3 -> 4 -> 5 -> 6 -> 7。
 *  
 *
 * 提示：
 *
 * 1 <= grid.length, grid[i].length <= 15
 * 0 <= grid[i][j] <= 100
 * 最多 25 个单元格中有黄金。
 *
 * @author chengzhy
 * @date 2022/2/5 20:54
 */
public class P1219_PathWithMaximumGold {

    private int result = 0;

    private int[][] grid;

    public int getMaximumGold(int[][] grid) {
        this.grid = grid;
        for (int i = 0; i < this.grid.length; i++) {
            for (int j = 0; j < this.grid[0].length; j++) {
                // 单元格的值大于0进行挖矿
                if (this.grid[i][j] > 0) {
                    backtracking(i, j, 0);
                }
            }
        }
        return result;
    }

    private void backtracking(int i, int j, int gold) {
        // 边界值或单元格为0的情况
        if (i == -1 || j == -1 || i == grid.length || j == grid[0].length || grid[i][j] == 0) {
            return;
        }
        // temp记录当前位置的黄金情况以便后续恢复现场
        int temp = grid[i][j];
        // 获得的黄金数
        gold += grid[i][j];
        // 将该位置置为0表示该位置已被开采了
        grid[i][j] = 0;
        // 结果取最大值
        result = Math.max(result, gold);
        /**
         * 向上下左右四个方向挖矿
         */
        backtracking(i + 1, j, gold);
        backtracking(i - 1, j, gold);
        backtracking(i, j + 1, gold);
        backtracking(i, j - 1, gold);
        // 回溯的核心就是恢复现场
        grid[i][j] = temp;
    }

}
