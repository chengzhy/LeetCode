package prefix_sum.medium;

/**
 * 蜡烛之间的盘子
 *
 * 给你一个长桌子，桌子上盘子和蜡烛排成一列。给你一个下标从 0 开始的字符串 s ，它只包含字符 '*' 和 '|' ，其中 '*' 表示一个 盘子 ，'|' 表示一支 蜡烛 。
 *
 * 同时给你一个下标从 0 开始的二维整数数组 queries ，其中 queries[i] = [lefti, righti] 表示 子字符串 s[lefti...righti] （包含左右端点的字符）。对于每个查询，你需要找到 子字符串中 在 两支蜡烛之间 的盘子的 数目 。如果一个盘子在 子字符串中 左边和右边 都 至少有一支蜡烛，那么这个盘子满足在 两支蜡烛之间 。
 *
 * 比方说，s = "||**||**|*" ，查询 [3, 8] ，表示的是子字符串 "*||**|" 。子字符串中在两支蜡烛之间的盘子数目为 2 ，子字符串中右边两个盘子在它们左边和右边 都 至少有一支蜡烛。
 * 请你返回一个整数数组 answer ，其中 answer[i] 是第 i 个查询的答案。
 *
 *  
 *
 * 示例 1:
 *
 *
 *
 * 输入：s = "**|**|***|", queries = [[2,5],[5,9]]
 * 输出：[2,3]
 * 解释：
 * - queries[0] 有两个盘子在蜡烛之间。
 * - queries[1] 有三个盘子在蜡烛之间。
 * 示例 2:
 *
 *
 *
 * 输入：s = "***|**|*****|**||**|*", queries = [[1,17],[4,5],[14,17],[5,11],[15,16]]
 * 输出：[9,0,0,0,0]
 * 解释：
 * - queries[0] 有 9 个盘子在蜡烛之间。
 * - 另一个查询没有盘子在蜡烛之间。
 *  
 *
 * 提示：
 *
 * 3 <= s.length <= 105
 * s 只包含字符 '*' 和 '|' 。
 * 1 <= queries.length <= 105
 * queries[i].length == 2
 * 0 <= lefti <= righti < s.length
 *
 * @author chengzhy
 * @date 2022/3/8 16:53
 */
public class P2055_PlatesBetweenCandles {
    private static final char CANDLE = '|';
    private static final int CANDLE_VALUE = -1;

    public int[] platesBetweenCandles(String s, int[][] queries) {
        /**
         * preSum用来计算0位置到当前位置盘子的数量，这样两个位置之间的盘子数量即可通过相减得到
         * leftCandle用来记录每个位置左侧最近蜡烛的位置
         * rightCandle用来记录每个位置右侧最近蜡烛的位置
         */
        int[] result = new int[queries.length], preSum = new int[s.length()],
                leftCandle = new int[s.length()], rightCandle = new int[s.length()];
        /**
         * leftIndex为每个位置左侧最近蜡烛的位置
         * rightIndex为每个位置右侧最近蜡烛的位置
         */
        int leftIndex = CANDLE_VALUE, rightIndex = CANDLE_VALUE;
        for (int i = 0, j = s.length() - 1, sum = 0; i < s.length(); i++, j--) {
            if (s.charAt(i) == CANDLE) {
                leftIndex = i;
            } else {
                sum++;
            }
            leftCandle[i] = leftIndex;
            preSum[i] = sum;
            if (s.charAt(j) == CANDLE) {
                rightIndex = j;
            }
            rightCandle[j] = rightIndex;
        }
        int j = 0;
        for (int[] query : queries) {
            /**
             * 核心思想：
             * x：区间左端点右侧的第一个蜡烛位置即为整个区间最左侧的蜡烛
             * y：区间右端点左侧的第一个蜡烛位置即为整个区间最右侧的蜡烛
             */
            int x = rightCandle[query[0]], y = leftCandle[query[1]];
            result[j++] = (x == -1 || y == -1 || x >= y) ? 0 : preSum[y] - preSum[x];
        }
        return result;
    }

}
