package math.medium;

/**
 * 旋转图像
 * <a href="https://leetcode.cn/problems/rotate-image/">🔗</a>
 *
 * 给定一个 n × n 的二维矩阵 matrix 表示一个图像。请你将图像顺时针旋转 90 度。
 *
 * 你必须在 原地 旋转图像，这意味着你需要直接修改输入的二维矩阵。请不要 使用另一个矩阵来旋转图像。
 *
 *  
 *
 * 示例 1：
 *
 *
 * 输入：matrix = [[1,2,3],[4,5,6],[7,8,9]]
 * 输出：[[7,4,1],[8,5,2],[9,6,3]]
 * 示例 2：
 *
 *
 * 输入：matrix = [[5,1,9,11],[2,4,8,10],[13,3,6,7],[15,14,12,16]]
 * 输出：[[15,13,2,5],[14,3,4,1],[12,6,8,9],[16,7,10,11]]
 *  
 *
 * 提示：
 *
 * n == matrix.length == matrix[i].length
 * 1 <= n <= 20
 * -1000 <= matrix[i][j] <= 1000
 *
 * @author chengzhy
 * @date 2022/1/20 18:54
 */
public class P48_RotateImage {


    public void rotate(int[][] matrix) {
        /**
         * 1 2 3     7 8 9     7 4 1
         * 4 5 6 --> 4 5 6 --> 8 5 2
         * 7 8 9     1 2 3     9 6 3
         *     上下对换  斜对角对换
         */
        // 上下对换
        int length = matrix.length;
        for (int i = 0; i < (length >> 1); i++) {
            int[] temp = matrix[i];
            matrix[i] = matrix[length - 1 - i];
            matrix[length - 1 - i] = temp;
        }
        // 斜对角对换
        for (int i = 0; i < length; i++) {
            for (int j = i; j < length; j++) {
                if (i != j) {
                    int temp = matrix[i][j];
                    matrix[i][j] = matrix[j][i];
                    matrix[j][i] = temp;
                }
            }
        }
    }

}
