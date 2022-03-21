package matrix.medium;

import java.util.ArrayList;
import java.util.List;

/**
 * 螺旋矩阵
 *
 * 给你一个 m 行 n 列的矩阵 matrix ，请按照 顺时针螺旋顺序 ，返回矩阵中的所有元素。
 *
 *  
 *
 * 示例 1：
 *
 *
 * 输入：matrix = [[1,2,3],[4,5,6],[7,8,9]]
 * 输出：[1,2,3,6,9,8,7,4,5]
 * 示例 2：
 *
 *
 * 输入：matrix = [[1,2,3,4],[5,6,7,8],[9,10,11,12]]
 * 输出：[1,2,3,4,8,12,11,10,9,5,6,7]
 *  
 *
 * 提示：
 *
 * m == matrix.length
 * n == matrix[i].length
 * 1 <= m, n <= 10
 * -100 <= matrix[i][j] <= 100
 *
 * @author chengzhy
 * @date 2022/3/21 12:55
 */
public class P54_SpiralMatrix {

    public List<Integer> spiralOrder(int[][] matrix) {
        List<Integer> result = new ArrayList<>();
        // top down为上下边界，left right为左右边界
        int top = 0, down = matrix.length - 1, left = 0, right = matrix[0].length - 1;
        // 直接模拟
        while (true) {
            // 从左往右
            for (int i = left; i <= right; i++) {
                result.add(matrix[top][i]);
            }
            // 上边界下移
            top++;
            if (top > down) {
                break;
            }
            // 从上往下
            for (int i = top; i <= down; i++) {
                result.add(matrix[i][right]);
            }
            // 右边界左移
            right--;
            if (left > right) {
                break;
            }
            // 从右往左
            for (int i = right; i >= left; i--) {
                result.add(matrix[down][i]);
            }
            // 下边界上移
            down--;
            if (top > down) {
                break;
            }
            // 从下往上
            for (int i = down; i >= top; i--) {
                result.add(matrix[i][left]);
            }
            // 左边界右移
            left++;
            if (left > right) {
                break;
            }
        }
        return result;
    }

}
