package hashtable.hard;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * 网格照明
 * <a href="https://leetcode-cn.com/problems/grid-illumination/">🔗</a>
 *
 * 在大小为 n x n 的网格 grid 上，每个单元格都有一盏灯，最初灯都处于 关闭 状态。
 *
 * 给你一个由灯的位置组成的二维数组 lamps ，其中 lamps[i] = [rowi, coli] 表示 打开 位于 grid[rowi][coli] 的灯。即便同一盏灯可能在 lamps 中多次列出，不会影响这盏灯处于 打开 状态。
 *
 * 当一盏灯处于打开状态，它将会照亮 自身所在单元格 以及同一 行 、同一 列 和两条 对角线 上的 所有其他单元格 。
 *
 * 另给你一个二维数组 queries ，其中 queries[j] = [rowj, colj] 。对于第 j 个查询，如果单元格 [rowj, colj] 是被照亮的，则查询结果为 1 ，否则为 0 。在第 j 次查询之后 [按照查询的顺序] ，关闭 位于单元格 grid[rowj][colj] 上及相邻 8 个方向上（与单元格 grid[rowi][coli] 共享角或边）的任何灯。
 *
 * 返回一个整数数组 ans 作为答案， ans[j] 应等于第 j 次查询 queries[j] 的结果，1 表示照亮，0 表示未照亮。
 *
 *  
 *
 * 示例 1：
 *
 *
 * 输入：n = 5, lamps = [[0,0],[4,4]], queries = [[1,1],[1,0]]
 * 输出：[1,0]
 * 解释：最初所有灯都是关闭的。在执行查询之前，打开位于 [0, 0] 和 [4, 4] 的灯。第 0 次查询检查 grid[1][1] 是否被照亮（蓝色方框）。该单元格被照亮，所以 ans[0] = 1 。然后，关闭红色方框中的所有灯。
 *
 * 第 1 次查询检查 grid[1][0] 是否被照亮（蓝色方框）。该单元格没有被照亮，所以 ans[1] = 0 。然后，关闭红色矩形中的所有灯。
 *
 * 示例 2：
 *
 * 输入：n = 5, lamps = [[0,0],[4,4]], queries = [[1,1],[1,1]]
 * 输出：[1,1]
 * 示例 3：
 *
 * 输入：n = 5, lamps = [[0,0],[0,4]], queries = [[0,4],[0,1],[1,4]]
 * 输出：[1,1,0]
 *  
 *
 * 提示：
 *
 * 1 <= n <= 109
 * 0 <= lamps.length <= 20000
 * 0 <= queries.length <= 20000
 * lamps[i].length == 2
 * 0 <= rowi, coli < n
 * queries[j].length == 2
 * 0 <= rowj, colj < n
 *
 * @author chengzhy
 * @date 2022/2/8 10:25
 */
public class P1001_GridIllumination {

    public int[] gridIllumination(int n, int[][] lamps, int[][] queries) {
        /*int[] result = new int[queries.length];
        if (lamps.length > 0 && queries.length > 0) {
            for (int i = 0; i < lamps.length; i++) {

            }
            for (int i = 0; i < queries.length; i++) {

            }
        }
        return result;*/
        /**
         * 参考官方题解：row记录某行点亮的次数，col记录某列点亮的次数
         * diagonal记录正对角线点亮的次数，antiDiagonal记录反对角线点亮的次数
         * points记录打开的灯泡
         */
        int[] result = new int[queries.length];
        Map<Integer, Integer> row = new HashMap<>();
        Map<Integer, Integer> col = new HashMap<>();
        Map<Integer, Integer> diagonal = new HashMap<>();
        Map<Integer, Integer> antiDiagonal = new HashMap<>();
        Set<Long> points = new HashSet<>();
        for (int[] lamp : lamps) {
            // 记录灯泡和单元格照亮情况
            if (!points.add(hash(lamp[0], lamp[1]))) {
                continue;
            }
            row.put(lamp[0], row.getOrDefault(lamp[0], 0) + 1);
            col.put(lamp[1], col.getOrDefault(lamp[1], 0) + 1);
            diagonal.put(lamp[0] - lamp[1], diagonal.getOrDefault(lamp[0] - lamp[1], 0) + 1);
            antiDiagonal.put(lamp[0] + lamp[1], antiDiagonal.getOrDefault(lamp[0] + lamp[1], 0) + 1);
        }
        for (int i = 0; i < queries.length; i++) {
            int r = queries[i][0], c = queries[i][1];
            // 判断(r, c)是否被照亮
            if (row.getOrDefault(r, 0) > 0) {
                result[i] = 1;
            } else if (col.getOrDefault(c, 0) > 0) {
                result[i] = 1;
            } else if (diagonal.getOrDefault(r - c, 0) > 0) {
                result[i] = 1;
            } else if (antiDiagonal.getOrDefault(r + c, 0) > 0) {
                result[i] = 1;
            }
            // 根据题意关灯，从r-1->r+1, c-1->c+1范围内关灯
            for (int x = r - 1; x <= r + 1; x++) {
                for (int y = c - 1; y <= c + 1; y++) {
                    if (x < 0 || y < 0 || x >= n || y >= n) {
                        // 边界情况
                        continue;
                    }
                    if (points.remove(hash(x, y))) {
                        // 如果关了灯，将行列正反对角线照亮情况更新
                        row.put(x, row.get(x) - 1);
                        if (row.get(x) == 0) {
                            // 如果不照亮了，移除该行，以下同理
                            row.remove(x);
                        }
                        col.put(y, col.get(y) - 1);
                        if (col.get(y) == 0) {
                            col.remove(y);
                        }
                        diagonal.put(x - y, diagonal.get(x - y) - 1);
                        if (diagonal.get(x - y) == 0) {
                            diagonal.remove(x - y);
                        }
                        antiDiagonal.put(x + y, antiDiagonal.get(x + y) - 1);
                        if (antiDiagonal.get(x + y) == 0) {
                            antiDiagonal.remove(x + y);
                        }
                    }
                }
            }
        }
        return result;
    }

    private long hash(int x, int y) {
        return (long) x + ((long) y << 32);
    }

}
