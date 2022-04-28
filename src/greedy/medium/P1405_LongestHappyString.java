package greedy.medium;

/**
 * 最长快乐字符串
 * <a href="https://leetcode.cn/problems/longest-happy-string/">🔗</a>
 *
 * 如果字符串中不含有任何 'aaa'，'bbb' 或 'ccc' 这样的字符串作为子串，那么该字符串就是一个「快乐字符串」。
 *
 * 给你三个整数 a，b ，c，请你返回 任意一个 满足下列全部条件的字符串 s：
 *
 * s 是一个尽可能长的快乐字符串。
 * s 中 最多 有a 个字母 'a'、b 个字母 'b'、c 个字母 'c' 。
 * s 中只含有 'a'、'b' 、'c' 三种字母。
 * 如果不存在这样的字符串 s ，请返回一个空字符串 ""。
 *
 *  
 *
 * 示例 1：
 *
 * 输入：a = 1, b = 1, c = 7
 * 输出："ccaccbcc"
 * 解释："ccbccacc" 也是一种正确答案。
 * 示例 2：
 *
 * 输入：a = 2, b = 2, c = 1
 * 输出："aabbc"
 * 示例 3：
 *
 * 输入：a = 7, b = 1, c = 0
 * 输出："aabaa"
 * 解释：这是该测试用例的唯一正确答案。
 *  
 *
 * 提示：
 *
 * 0 <= a, b, c <= 100
 * a + b + c > 0
 *
 * @author chengzhy
 * @date 2022/2/7 9:19
 */
public class P1405_LongestHappyString {

    public String longestDiverseString(int a, int b, int c) {
        StringBuilder sb = new StringBuilder();
        // 记录最后一个字母
        char lastChar = ' ';
        // 记录连续相同字母的个数
        int sameCharCount = 0;
        while (a != 0 || b != 0 || c != 0) {
            char temp;
            // 连续相同字母的个数小于2
            if (sameCharCount < 2) {
                if (a >= b && a >= c) {
                    // a最多
                    temp = 'a';
                    a -= 1;
                } else if (b >= a && b >= c) {
                    // b最多
                    temp = 'b';
                    b -= 1;
                } else {
                    // c最多
                    temp = 'c';
                    c -= 1;
                }
                // 更新相同字母的个数
                sameCharCount = (lastChar == temp) ? sameCharCount + 1 : 1;
            } else {
                // 连续相同字母的个数等于2
                if (lastChar == 'a') {
                    // 以a结尾，找bc中最大的
                    if (b == c && b == 0) {
                        break;
                    } else if (b >= c) {
                        temp = 'b';
                        b -= 1;
                    } else {
                        temp = 'c';
                        c -= 1;
                    }
                } else if (lastChar == 'b') {
                    // 以b结尾，找ac中最大的
                    if (a == c && a == 0) {
                        break;
                    } else if (a >= c) {
                        temp = 'a';
                        a -= 1;
                    } else {
                        temp = 'c';
                        c -= 1;
                    }
                } else {
                    // 以c结尾，找ab中最大的
                    if (a == b && a == 0) {
                        break;
                    } else if (a >= b) {
                        temp = 'a';
                        a -= 1;
                    } else {
                        temp = 'b';
                        b -= 1;
                    }
                }
                sameCharCount = 1;
            }
            sb.append(temp);
            lastChar = temp;
        }
        return sb.toString();
    }

}
