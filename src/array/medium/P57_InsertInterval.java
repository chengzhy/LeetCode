package array.medium;

import java.util.ArrayList;
import java.util.List;

/**
 * 插入区间
 * <a href="https://leetcode-cn.com/problems/insert-interval/">🔗</a>
 *
 * 给你一个 无重叠的 ，按照区间起始端点排序的区间列表。
 *
 * 在列表中插入一个新的区间，你需要确保列表中的区间仍然有序且不重叠（如果有必要的话，可以合并区间）。
 *
 *  
 *
 * 示例 1：
 *
 * 输入：intervals = [[1,3],[6,9]], newInterval = [2,5]
 * 输出：[[1,5],[6,9]]
 * 示例 2：
 *
 * 输入：intervals = [[1,2],[3,5],[6,7],[8,10],[12,16]], newInterval = [4,8]
 * 输出：[[1,2],[3,10],[12,16]]
 * 解释：这是因为新的区间 [4,8] 与 [3,5],[6,7],[8,10] 重叠。
 * 示例 3：
 *
 * 输入：intervals = [], newInterval = [5,7]
 * 输出：[[5,7]]
 * 示例 4：
 *
 * 输入：intervals = [[1,5]], newInterval = [2,3]
 * 输出：[[1,5]]
 * 示例 5：
 *
 * 输入：intervals = [[1,5]], newInterval = [2,7]
 * 输出：[[1,7]]
 *  
 *
 * 提示：
 *
 * 0 <= intervals.length <= 104
 * intervals[i].length == 2
 * 0 <= intervals[i][0] <= intervals[i][1] <= 105
 * intervals 根据 intervals[i][0] 按 升序 排列
 * newInterval.length == 2
 * 0 <= newInterval[0] <= newInterval[1] <= 105
 *
 * @author chengzhy
 * @date 2022/2/15 11:40
 */
public class P57_InsertInterval {

    public int[][] insert(int[][] intervals, int[] newInterval) {
        List<int[]> list = new ArrayList<>();
        /**
         * 因为intervals根据intervals[i][0]按升序排列，所以不会出现当前区间比结果集中的第一个还小
         * 所以可以先将newInterval加入结果中
         */
        list.add(newInterval);
        for (int i = 0; i < intervals.length; i++) {
            int left = intervals[i][0], right = intervals[i][1];
            int[] last = list.get(list.size() - 1);
            if (last[1] < left) {
                // 如果最后一个的右边界比当前的左边界小，直接添加
                list.add(intervals[i]);
            } else if (last[0] > right) {
                // 如果最后一个的左边界比当前的右边界大，说明当前区间比list中的最后一个小，将其加到最后一个的前面
                list.set(list.size() - 1, intervals[i]);
                list.add(last);
            } else {
                // 如果重叠了，左边界取二者最小值，右边界取二者最大值
                last[0] = Math.min(last[0], left);
                last[1] = Math.max(last[1], right);
            }
        }
        return list.toArray(new int[list.size()][]);
    }

}
