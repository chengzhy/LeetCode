package matrix.medium;

/**
 * 螺旋矩阵 II
 * <a href="https://leetcode-cn.com/problems/spiral-matrix-ii/">🔗</a>
 *
 * 给你一个正整数 n ，生成一个包含 1 到 n2 所有元素，且元素按顺时针顺序螺旋排列的 n x n 正方形矩阵 matrix 。
 *
 *  
 *
 * 示例 1：
 *
 *
 * 输入：n = 3
 * 输出：[[1,2,3],[8,9,4],[7,6,5]]
 * 示例 2：
 *
 * 输入：n = 1
 * 输出：[[1]]
 *  
 *
 * 提示：
 *
 * 1 <= n <= 20
 *
 * @author chengzhy
 * @date 2022/3/23 10:53
 */
public class P59_SpiralMatrixII {

    public int[][] generateMatrix(int n) {
        int[][] result = new int[n][n];
        int top = 0, down = n - 1, left = 0, right = n - 1, k = 1;
        while (true) {
            for (int i = left; i <= right; i++) {
                result[top][i] = k++;
            }
            // 上边界下移
            top++;
            if (top > down) {
                break;
            }
            for (int i = top; i <= down; i++) {
                result[i][right] = k++;
            }
            // 右边界左移
            right--;
            if (left > right) {
                break;
            }
            for (int i = right; i >= left; i--) {
                result[down][i] = k++;
            }
            // 下边界上移
            down--;
            if (top > down) {
                break;
            }
            for (int i = down; i >= top; i--) {
                result[i][left] = k++;
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
