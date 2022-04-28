package dynamic_programming.hard;

/**
 * 地下城游戏
 * <a href="https://leetcode.cn/problems/dungeon-game/">🔗</a>
 *
 * 一些恶魔抓住了公主（P）并将她关在了地下城的右下角。地下城是由 M x N 个房间组成的二维网格。我们英勇的骑士（K）最初被安置在左上角的房间里，他必须穿过地下城并通过对抗恶魔来拯救公主。
 *
 * 骑士的初始健康点数为一个正整数。如果他的健康点数在某一时刻降至 0 或以下，他会立即死亡。
 *
 * 有些房间由恶魔守卫，因此骑士在进入这些房间时会失去健康点数（若房间里的值为负整数，则表示骑士将损失健康点数）；其他房间要么是空的（房间里的值为 0），要么包含增加骑士健康点数的魔法球（若房间里的值为正整数，则表示骑士将增加健康点数）。
 *
 * 为了尽快到达公主，骑士决定每次只向右或向下移动一步。
 *
 *  
 *
 * 编写一个函数来计算确保骑士能够拯救到公主所需的最低初始健康点数。
 *
 * 例如，考虑到如下布局的地下城，如果骑士遵循最佳路径 右 -> 右 -> 下 -> 下，则骑士的初始健康点数至少为 7。
 *
 * -2 (K)	-3	3
 * -5	-10	1
 * 10	30	-5 (P)
 *  
 *
 * 说明:
 *
 * 骑士的健康点数没有上限。
 *
 * 任何房间都可能对骑士的健康点数造成威胁，也可能增加骑士的健康点数，包括骑士进入的左上角房间以及公主被监禁的右下角房间。
 *
 * @author chengzhy
 * @date 2022/3/9 12:47
 */
public class P174_DungeonGame {

    public int calculateMinimumHP(int[][] dungeon) {
        int m = dungeon.length, n = dungeon[0].length;
        // dp[i][j]数组表示从公主处到达(i, j)位置骑士所需的最低健康点数(反向dp)
        int[][] dp = new int[m][n];
        // 右下角的初始值为 若dungeon[i][j]小于0，那么最低健康点数为1-dungeon[i][j]，否则为1即可
        dp[m - 1][n - 1] = (dungeon[m - 1][n - 1] < 0) ? 1 - dungeon[m - 1][n - 1] : 1;
        /**
         * 初始化边界值，如果当前格是加血的并且比下一步骑士所需的最低健康点数大，则当前格子骑士所需的最低健康点数为1即可满足条件
         * 否则就是下一步骑士所需的最低健康点数 - 当前格的值
         */
        for (int i = m - 2; i >= 0; i--) {
            dp[i][n - 1] = (dungeon[i][n - 1] > 0 && dungeon[i][n - 1] >= dp[i + 1][n - 1]) ? 1 :
                    dp[i + 1][n - 1] - dungeon[i][n - 1];
        }
        for (int i = n - 2; i >= 0; i--) {
            dp[m - 1][i] = (dungeon[m - 1][i] > 0 && dungeon[m - 1][i] >= dp[m - 1][i + 1]) ? 1 :
                    dp[m - 1][i + 1] - dungeon[m - 1][i];
        }
        for (int i = m - 2; i >= 0; i--) {
            for (int j = n - 2; j >= 0; j--) {
                // 当前骑士所需的最低健康点数为 Math.max(下一步(向下或向右)的最小点数 - 当前格的值, 1)
                // 当前格的值比下一步(向下或向右)的最小点数大时说明是加血的量都能超过下一步所需的点数
                int min = Math.min(dp[i + 1][j], dp[i][j + 1]);
                dp[i][j] = Math.max(min - dungeon[i][j], 1);
            }
        }
        return dp[0][0];
    }

}
