package math.hard;

/**
 * 到达终点
 * <a href="https://leetcode.cn/problems/reaching-points/">🔗</a>
 *
 * 给定四个整数 sx , sy ，tx 和 ty，如果通过一系列的转换可以从起点 (sx, sy) 到达终点 (tx, ty)，则返回 true，否则返回 false。
 *
 * 从点 (x, y) 可以转换到 (x, x+y)  或者 (x+y, y)。
 *
 *  
 *
 * 示例 1:
 *
 * 输入: sx = 1, sy = 1, tx = 3, ty = 5
 * 输出: true
 * 解释:
 * 可以通过以下一系列转换从起点转换到终点：
 * (1, 1) -> (1, 2)
 * (1, 2) -> (3, 2)
 * (3, 2) -> (3, 5)
 * 示例 2:
 *
 * 输入: sx = 1, sy = 1, tx = 2, ty = 2
 * 输出: false
 * 示例 3:
 *
 * 输入: sx = 1, sy = 1, tx = 1, ty = 1
 * 输出: true
 *  
 *
 * 提示:
 *
 * 1 <= sx, sy, tx, ty <= 109
 *
 * @author chengzhy
 * @date 2022/4/9 17:49
 */
public class P780_ReachingPoints {

    public boolean reachingPoints(int sx, int sy, int tx, int ty) {
        /**
         * 此题的难点在于：从(sx, sy)->(tx, ty)会有非常多种情况，类似二叉树
         * 如果从正向搜索会十分困难，于是可以将此问题从反向搜索
         * 转换为从(tx, ty)->(sx, sy)，这样只会有一条路，
         * 就是将tx、ty中较大值减去较小值，因为顺推的时候是(x, y)可以转换到(x, x + y)或者(x + y, y)，
         * 则逆推的时候只能将较大者减去较小者
         */
        while (tx > 0 && ty > 0) {
            if (sx == tx && sy == ty) {
                return true;
            }
            // tx:999999995 ty:7超时，因为两者相差太大，不优化相减的过程会超出栈空间
            // 需要优化成当tx ty相差太大时，让tx ty快速逼近sx sy
            if (tx < ty) {
                // ty -= tx;
                // ty - sy是目标与起始值在y的差距，我们需要一次减去n * tx达到快速逼近sy的目的
                ty -= Math.max((ty - sy) / tx, 1) * tx;
            } else {
                // tx -= ty;
                // tx - sx是目标与起始值在x的差距，我们需要一次减去n * ty达到快速逼近sx的目的
                tx -= Math.max((tx - sx) / ty, 1) * ty;
            }
        }
        return false;
    }

}
