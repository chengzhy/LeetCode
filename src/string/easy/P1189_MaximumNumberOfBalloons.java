package string.easy;

import java.util.Arrays;

/**
 * “气球” 的最大数量
 * <a href="https://leetcode.cn/problems/maximum-number-of-balloons/">🔗</a>
 *
 * 给你一个字符串 text，你需要使用 text 中的字母来拼凑尽可能多的单词 "balloon"（气球）。
 *
 * 字符串 text 中的每个字母最多只能被使用一次。请你返回最多可以拼凑出多少个单词 "balloon"。
 *
 *  
 *
 * 示例 1：
 *
 *
 *
 * 输入：text = "nlaebolko"
 * 输出：1
 * 示例 2：
 *
 *
 *
 * 输入：text = "loonbalxballpoon"
 * 输出：2
 * 示例 3：
 *
 * 输入：text = "leetcode"
 * 输出：0
 *  
 *
 * 提示：
 *
 * 1 <= text.length <= 10^4
 * text 全部由小写英文字母组成
 *
 * @author chengzhy
 * @date 2022/2/13 10:09
 */
public class P1189_MaximumNumberOfBalloons {

    public int maxNumberOfBalloons(String text) {
        // 记录balloon中各个字母出现的次数
        int[] count = new int[5];
        for (int i = 0; i < text.length(); i++) {
            char c = text.charAt(i);
            if (c == 'a') {
                count[0]++;
            } else if (c == 'b') {
                count[1]++;
            } else if (c == 'l') {
                count[2]++;
            } else if (c == 'n') {
                count[3]++;
            } else if (c == 'o') {
                count[4]++;
            }
        }
        // 因为l和o都出现两次，所以判断的时候先把它俩除以2
        count[2] >>= 1;
        count[4] >>= 1;
        // 计算其中出现次数的最小值即为所能组成balloon的个数
        return Arrays.stream(count).min().getAsInt();
    }

}
