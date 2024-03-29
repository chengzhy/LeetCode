package matrix.easy;

import java.util.*;

/**
 * 矩阵中的幸运数
 * <a href="https://leetcode.cn/problems/lucky-numbers-in-a-matrix/">🔗</a>
 *
 * 给你一个 m * n 的矩阵，矩阵中的数字 各不相同 。请你按 任意 顺序返回矩阵中的所有幸运数。
 *
 * 幸运数是指矩阵中满足同时下列两个条件的元素：
 *
 * 在同一行的所有元素中最小
 * 在同一列的所有元素中最大
 *  
 *
 * 示例 1：
 *
 * 输入：matrix = [[3,7,8],[9,11,13],[15,16,17]]
 * 输出：[15]
 * 解释：15 是唯一的幸运数，因为它是其所在行中的最小值，也是所在列中的最大值。
 * 示例 2：
 *
 * 输入：matrix = [[1,10,4,2],[9,3,8,7],[15,16,17,12]]
 * 输出：[12]
 * 解释：12 是唯一的幸运数，因为它是其所在行中的最小值，也是所在列中的最大值。
 * 示例 3：
 *
 * 输入：matrix = [[7,8],[1,2]]
 * 输出：[7]
 *  
 *
 * 提示：
 *
 * m == mat.length
 * n == mat[i].length
 * 1 <= n, m <= 50
 * 1 <= matrix[i][j] <= 10^5
 * 矩阵中的所有元素都是不同的
 *
 * @author chengzhy
 * @date 2022/2/15 9:46
 */
public class P1380_LuckyNumbersInAMatrix {

    public List<Integer> luckyNumbers (int[][] matrix) {
        List<Integer> list = new ArrayList<>();
        // set先保存每行的最小值
        Set<Integer> set = new HashSet<>();
        // 找每行的最小值
        for (int i = 0; i < matrix.length; i++) {
            int min = Integer.MAX_VALUE;
            for (int j = 0; j < matrix[0].length; j++) {
                min = Math.min(min, matrix[i][j]);
            }
            set.add(min);
        }
        // 找每列的最大值，如果set中有，直接添加到list中
        for (int j = 0; j < matrix[0].length; j++) {
            int max = 0;
            for (int i = 0; i < matrix.length; i++) {
                max = Math.max(max, matrix[i][j]);
            }
            if (set.contains(max)) {
                list.add(max);
            }
        }
        return list;
    }

}
