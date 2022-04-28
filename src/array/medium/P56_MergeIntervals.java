package array.medium;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

/**
 * 合并区间
 * <a href="https://leetcode.cn/problems/merge-intervals/">🔗</a>
 *
 * 以数组 intervals 表示若干个区间的集合，其中单个区间为 intervals[i] = [starti, endi] 。请你合并所有重叠的区间，并返回 一个不重叠的区间数组，该数组需恰好覆盖输入中的所有区间 。
 *
 *  
 *
 * 示例 1：
 *
 * 输入：intervals = [[1,3],[2,6],[8,10],[15,18]]
 * 输出：[[1,6],[8,10],[15,18]]
 * 解释：区间 [1,3] 和 [2,6] 重叠, 将它们合并为 [1,6].
 * 示例 2：
 *
 * 输入：intervals = [[1,4],[4,5]]
 * 输出：[[1,5]]
 * 解释：区间 [1,4] 和 [4,5] 可被视为重叠区间。
 *  
 *
 * 提示：
 *
 * 1 <= intervals.length <= 104
 * intervals[i].length == 2
 * 0 <= starti <= endi <= 104
 *
 * @author chengzhy
 * @date 2022/2/15 11:02
 */
public class P56_MergeIntervals {

    public int[][] merge(int[][] intervals) {
        // 先将这个二维数组排序，主要是对这个api不熟悉
        Arrays.sort(intervals, Comparator.comparingInt(intervals2 -> intervals2[0]));
        // 用list存这个数组，主要是不知道list能这样转数组list.toArray(new int[list.size()][])
        List<int[]> list = new ArrayList<>();
        for (int i = 0; i < intervals.length; i++) {
            int left = intervals[i][0], right = intervals[i][1];
            if (list.isEmpty() || list.get(list.size() - 1)[1] < left) {
                // 如果数组为空或者list最后一个数组的右区间比当前的左区间小，说明没有重叠直接添加
                list.add(intervals[i]);
            } else {
                // 否则让list最后一个数组的右区间等于与当前的右区间的最大值
                list.get(list.size() - 1)[1] = Math.max(list.get(list.size() - 1)[1], right);
            }
        }
        return list.toArray(new int[list.size()][]);
    }

}
