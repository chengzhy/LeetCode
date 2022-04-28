package dynamic_programming.easy;

import java.util.ArrayList;
import java.util.List;

/**
 * 杨辉三角
 * <a href="https://leetcode.cn/problems/pascals-triangle/">🔗</a>
 *
 * 给定一个非负整数 numRows，生成「杨辉三角」的前 numRows 行。
 *
 * 在「杨辉三角」中，每个数是它左上方和右上方的数的和。
 *
 *
 *
 *  
 *
 * 示例 1:
 *
 * 输入: numRows = 5
 * 输出: [[1],[1,1],[1,2,1],[1,3,3,1],[1,4,6,4,1]]
 * 示例 2:
 *
 * 输入: numRows = 1
 * 输出: [[1]]
 *  
 *
 * 提示:
 *
 * 1 <= numRows <= 30
 *
 * @author chengzhy
 * @date 2022/3/16 11:46
 */
public class P118_PascalsTriangle {

    public List<List<Integer>> generate(int numRows) {
        List<List<Integer>> result = new ArrayList<>();
        // 第一层的情况
        List<Integer> first = new ArrayList<>(1);
        first.add(1);
        result.add(first);
        for (int i = 2; i <= numRows; i++) {
            List<Integer> preList = result.get(i - 2);
            List<Integer> tempList = new ArrayList<>(i);
            for (int j = 1; j <= i; j++) {
                if (j == 1 || j == i) {
                    // 两边直接加1
                    tempList.add(1);
                } else {
                    // 根据上一层的情况得到值
                    tempList.add(preList.get(j - 2) + preList.get(j - 1));
                }
            }
            result.add(tempList);
        }
        return result;
    }

}
