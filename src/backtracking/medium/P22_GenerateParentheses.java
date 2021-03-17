package backtracking.medium;

import java.util.ArrayList;
import java.util.List;

/**
 * @description 括号生成
 *
 * 数字 n 代表生成括号的对数，请你设计一个函数，用于能够生成所有可能的并且 有效的 括号组合。
 *
 *  
 *
 * 示例 1：
 *
 * 输入：n = 3
 * 输出：["((()))","(()())","(())()","()(())","()()()"]
 * 示例 2：
 *
 * 输入：n = 1
 * 输出：["()"]
 *  
 *
 * 提示：
 *
 * 1 <= n <= 8
 *
 **/
public class P22_GenerateParentheses {

    public List<String> generateParenthesis(int n) {
        List<String> list = new ArrayList<>();
        // 递归
        generate(list, "", 0, 0, n);
        return list;
    }

    // count1统计“(”的个数，count2统计“)”的个数
    public void generate(List<String> res , String ans, int count1, int count2, int n){
        if (count1 > n || count2 > n) return;
        if (count1 == n && count2 == n) res.add(ans);
        if (count1 >= count2) {
            String ans1 = ans;
            // 加”(“
            generate(res, ans+"(", count1+1, count2, n);
            // 加”)“
            generate(res, ans1+")", count1, count2+1, n);
        }
    }

}
