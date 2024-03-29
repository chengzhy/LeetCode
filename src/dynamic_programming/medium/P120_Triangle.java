package dynamic_programming.medium;

import java.util.List;

/**
 * 三角形最小路径和
 * <a href="https://leetcode.cn/problems/triangle/">🔗</a>
 *
 * 给定一个三角形 triangle ，找出自顶向下的最小路径和。
 *
 * 每一步只能移动到下一行中相邻的结点上。相邻的结点 在这里指的是 下标 与 上一层结点下标 相同或者等于 上一层结点下标 + 1 的两个结点。也就是说，如果正位于当前行的下标 i ，那么下一步可以移动到下一行的下标 i 或 i + 1 。
 *
 *  
 *
 * 示例 1：
 *
 * 输入：triangle = [[2],[3,4],[6,5,7],[4,1,8,3]]
 * 输出：11
 * 解释：如下面简图所示：
 *    2
 *   3 4
 *  6 5 7
 * 4 1 8 3
 * 自顶向下的最小路径和为 11（即，2 + 3 + 5 + 1 = 11）。
 * 示例 2：
 *
 * 输入：triangle = [[-10]]
 * 输出：-10
 *  
 *
 * 提示：
 *
 * 1 <= triangle.length <= 200
 * triangle[0].length == 1
 * triangle[i].length == triangle[i - 1].length + 1
 * -104 <= triangle[i][j] <= 104
 *  
 *
 * 进阶：
 *
 * 你可以只使用 O(n) 的额外空间（n 为三角形的总行数）来解决这个问题吗？
 *
 * @author chengzhy
 * @date 2022/4/1 12:55
 */
public class P120_Triangle {

    public int minimumTotal(List<List<Integer>> triangle) {
        int n = triangle.size();
        /*// dp[i][j]数组表示点(i, j)到三角形底边的最小路径和
        int[][] dp = new int[n + 1][n + 1];
        // 从三角形底边开始往顶部遍历
        for (int i = n - 1; i >= 0; i--) {
            for (int j = 0; j <= i; j++) {
                *//**
                 * 状态转移方程：点(i, j)到底边的最小路径和 = 点(i, j)的值 + 下一行点(i + 1, j)到底边的最小路径和
                 * 与点(i + 1, j + 1)到底边的最小路径和二者间的最小值
                 *//*
                dp[i][j] = Math.min(dp[i + 1][j], dp[i + 1][j + 1]) + triangle.get(i).get(j);
            }
        }
        return dp[0][0];*/
        // 因为上面的状态转移方程只用到了下一行的数据，因此可以将dp数组简化成一维数组
        // dp[i]数组表示第i行到三角形底边的最小路径和
        int[] dp = new int[n + 1];
        for (int i = n - 1; i >= 0; i--) {
            for (int j = 0; j <= i; j++) {
                dp[j] = Math.min(dp[j], dp[j + 1]) + triangle.get(i).get(j);
            }
        }
        return dp[0];
        /**
         * 再来个正向dp的做法
         */
        // dp[i][j]数组表示从三角形的顶点到点(i, j)的最小路径和
        /*int[][] dp = new int[n][n];
        dp[0][0] = triangle.get(0).get(0);
        for (int i = 1; i < n; i++) {
            // 边界值的情况
            dp[i][0] = dp[i - 1][0] + triangle.get(i).get(0);
            for (int j = 1; j < i; j++) {
                dp[i][j] = Math.min(dp[i - 1][j - 1], dp[i - 1][j]) + triangle.get(i).get(j);
            }
            dp[i][i] = dp[i - 1][i - 1] + triangle.get(i).get(i);
        }
        int min = dp[n - 1][0];
        for (int i = 1; i < n; i++) {
            min = Math.min(min, dp[n - 1][i]);
        }
        return min;*/
    }

}
