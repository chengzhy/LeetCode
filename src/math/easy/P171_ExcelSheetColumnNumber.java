package math.easy;

/**
 * Excel 表列序号
 *
 * 给你一个字符串 columnTitle ，表示 Excel 表格中的列名称。返回 该列名称对应的列序号 。
 *
 * 例如：
 *
 * A -> 1
 * B -> 2
 * C -> 3
 * ...
 * Z -> 26
 * AA -> 27
 * AB -> 28
 * ...
 *  
 *
 * 示例 1:
 *
 * 输入: columnTitle = "A"
 * 输出: 1
 * 示例 2:
 *
 * 输入: columnTitle = "AB"
 * 输出: 28
 * 示例 3:
 *
 * 输入: columnTitle = "ZY"
 * 输出: 701
 *  
 *
 * 提示：
 *
 * 1 <= columnTitle.length <= 7
 * columnTitle 仅由大写英文组成
 * columnTitle 在范围 ["A", "FXSHRXW"] 内
 *
 * @author chengzhy
 * @date 2022/3/16 12:05
 */
public class P171_ExcelSheetColumnNumber {
    private static final char A = 'A';

    public int titleToNumber(String columnTitle) {
        int number = 0, n = columnTitle.length();
        for (int i = n - 1; i >= 0; i--) {
            // number += (字母 - ‘A' + 1) * 26^(n - i + 1)，类似26进制转10进制
            number += (columnTitle.charAt(i) - A + 1) * Math.pow(26, n - i - 1);
        }
        return number;
    }

}
