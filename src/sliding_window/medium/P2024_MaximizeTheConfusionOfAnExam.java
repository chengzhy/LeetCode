package sliding_window.medium;

/**
 * 考试的最大困扰度
 * <a href="https://leetcode.cn/problems/maximize-the-confusion-of-an-exam/">🔗</a>
 *
 * 一位老师正在出一场由 n 道判断题构成的考试，每道题的答案为 true （用 'T' 表示）或者 false （用 'F' 表示）。老师想增加学生对自己做出答案的不确定性，方法是 最大化 有 连续相同 结果的题数。（也就是连续出现 true 或者连续出现 false）。
 *
 * 给你一个字符串 answerKey ，其中 answerKey[i] 是第 i 个问题的正确结果。除此以外，还给你一个整数 k ，表示你能进行以下操作的最多次数：
 *
 * 每次操作中，将问题的正确答案改为 'T' 或者 'F' （也就是将 answerKey[i] 改为 'T' 或者 'F' ）。
 * 请你返回在不超过 k 次操作的情况下，最大 连续 'T' 或者 'F' 的数目。
 *
 *  
 *
 * 示例 1：
 *
 * 输入：answerKey = "TTFF", k = 2
 * 输出：4
 * 解释：我们可以将两个 'F' 都变为 'T' ，得到 answerKey = "TTTT" 。
 * 总共有四个连续的 'T' 。
 * 示例 2：
 *
 * 输入：answerKey = "TFFT", k = 1
 * 输出：3
 * 解释：我们可以将最前面的 'T' 换成 'F' ，得到 answerKey = "FFFT" 。
 * 或者，我们可以将第二个 'T' 换成 'F' ，得到 answerKey = "TFFF" 。
 * 两种情况下，都有三个连续的 'F' 。
 * 示例 3：
 *
 * 输入：answerKey = "TTFTTFTT", k = 1
 * 输出：5
 * 解释：我们可以将第一个 'F' 换成 'T' ，得到 answerKey = "TTTTTFTT" 。
 * 或者我们可以将第二个 'F' 换成 'T' ，得到 answerKey = "TTFTTTTT" 。
 * 两种情况下，都有五个连续的 'T' 。
 *  
 *
 * 提示：
 *
 * n == answerKey.length
 * 1 <= n <= 5 * 104
 * answerKey[i] 要么是 'T' ，要么是 'F'
 * 1 <= k <= n
 *
 * @author chengzhy
 * @date 2022/3/29 11:25
 */
public class P2024_MaximizeTheConfusionOfAnExam {

    public int maxConsecutiveAnswers(String answerKey, int k) {
        int left = 0, right = 0, tNum = 0, fNum = 0;
        char[] chars = answerKey.toCharArray();
        while (right < chars.length) {
            // 右指针往右移动
            char c = chars[right++];
            if (c == 'T') {
                tNum++;
            } else {
                fNum++;
            }
            /**
             * 当发现当前滑动窗口内数量最小的字母 > k了，说明置换次数不够用了
             * 整体把滑动窗口右移，看下一个区域是否满足条件
             * 整体右移的时候已经可以知道最符合条件的最大区间是[left, right - 1]
             * 若整体右移后若有满足的区间，更新right指针即可，
             * 若整体右移直至最后也没找到满足条件的区间，最大长度就是right - 1 - left + 1 = right - left
             */
            if (Math.min(tNum, fNum) > k) {
                char out = chars[left++];
                if (out == 'T') {
                    tNum--;
                } else {
                    fNum--;
                }
            }
        }
        return right - left;
    }

}
