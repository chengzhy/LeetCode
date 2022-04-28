package array.medium;

/**
 * 搜索二维矩阵 II
 * <a href="https://leetcode.cn/problems/search-a-2d-matrix-ii/">🔗</a>
 *
 * 编写一个高效的算法来搜索 m x n 矩阵 matrix 中的一个目标值 target 。该矩阵具有以下特性：
 *
 * 每行的元素从左到右升序排列。
 * 每列的元素从上到下升序排列。
 *  
 *
 * 示例 1：
 *
 *
 * 输入：matrix = [[1,4,7,11,15],[2,5,8,12,19],[3,6,9,16,22],[10,13,14,17,24],[18,21,23,26,30]], target = 5
 * 输出：true
 * 示例 2：
 *
 *
 * 输入：matrix = [[1,4,7,11,15],[2,5,8,12,19],[3,6,9,16,22],[10,13,14,17,24],[18,21,23,26,30]], target = 20
 * 输出：false
 *  
 *
 * 提示：
 *
 * m == matrix.length
 * n == matrix[i].length
 * 1 <= n, m <= 300
 * -109 <= matrix[i][j] <= 109
 * 每行的所有元素从左到右升序排列
 * 每列的所有元素从上到下升序排列
 * -109 <= target <= 109
 *
 * @author chengzhy
 * @date 2022/2/14 9:10
 */
public class P240_SearchA2DMatrixII {

    public boolean searchMatrix(int[][] matrix, int target) {
        // 根据题意可以进行Z字型查找，一开始查找的点为矩阵右上角
        int i = 0, j = matrix[0].length - 1;
        while (i < matrix.length && j >= 0) {
            if (matrix[i][j] == target) {
                return true;
            } else if (matrix[i][j] < target) {
                // 说明目标在当前位置的下方
                i++;
            } else {
                // 说明目标在当前位置的左侧
                j--;
            }
        }
        return false;
    }

}
