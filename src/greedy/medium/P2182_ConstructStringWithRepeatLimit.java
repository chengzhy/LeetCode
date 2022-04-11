package greedy.medium;

/**
 * 构造限制重复的字符串
 * <a href="https://leetcode-cn.com/problems/construct-string-with-repeat-limit/">🔗</a>
 *
 * 给你一个字符串 s 和一个整数 repeatLimit ，用 s 中的字符构造一个新字符串 repeatLimitedString ，使任何字母 连续 出现的次数都不超过 repeatLimit 次。你不必使用 s 中的全部字符。
 *
 * 返回 字典序最大的 repeatLimitedString 。
 *
 * 如果在字符串 a 和 b 不同的第一个位置，字符串 a 中的字母在字母表中出现时间比字符串 b 对应的字母晚，则认为字符串 a 比字符串 b 字典序更大 。如果字符串中前 min(a.length, b.length) 个字符都相同，那么较长的字符串字典序更大。
 *
 *  
 *
 * 示例 1：
 *
 * 输入：s = "cczazcc", repeatLimit = 3
 * 输出："zzcccac"
 * 解释：使用 s 中的所有字符来构造 repeatLimitedString "zzcccac"。
 * 字母 'a' 连续出现至多 1 次。
 * 字母 'c' 连续出现至多 3 次。
 * 字母 'z' 连续出现至多 2 次。
 * 因此，没有字母连续出现超过 repeatLimit 次，字符串是一个有效的 repeatLimitedString 。
 * 该字符串是字典序最大的 repeatLimitedString ，所以返回 "zzcccac" 。
 * 注意，尽管 "zzcccca" 字典序更大，但字母 'c' 连续出现超过 3 次，所以它不是一个有效的 repeatLimitedString 。
 * 示例 2：
 *
 * 输入：s = "aababab", repeatLimit = 2
 * 输出："bbabaa"
 * 解释：
 * 使用 s 中的一些字符来构造 repeatLimitedString "bbabaa"。
 * 字母 'a' 连续出现至多 2 次。
 * 字母 'b' 连续出现至多 2 次。
 * 因此，没有字母连续出现超过 repeatLimit 次，字符串是一个有效的 repeatLimitedString 。
 * 该字符串是字典序最大的 repeatLimitedString ，所以返回 "bbabaa" 。
 * 注意，尽管 "bbabaaa" 字典序更大，但字母 'a' 连续出现超过 2 次，所以它不是一个有效的 repeatLimitedString 。
 *  
 *
 * 提示：
 *
 * 1 <= repeatLimit <= s.length <= 105
 * s 由小写英文字母组成
 *
 * @author chengzhy
 * @date 2022/2/20 16:32
 */
public class P2182_ConstructStringWithRepeatLimit {
    private static final char A = 'a';

    public String repeatLimitedString(String s, int repeatLimit) {
        // 因为s只由小写字母构成，所以定义一个数组长度为26
        int[] count = new int[26];
        for (char c : s.toCharArray()) {
            count[c - A]++;
        }
        int num = s.length();
        StringBuilder sb = new StringBuilder();
        char lastChar = ' ';
        // 此值判断前面是否还有未用过的排序大的字母
        boolean flag = false;
        while (num > 0) {
            int i;
            // 从字母z开始往前遍历
            for (i = count.length; i >= 0; i--) {
                // 字母数大于0
                if (count[i] > 0) {
                    // 单词的最后一个字母不等于当前位置的字母
                    if (lastChar != (char) (A + i)) {
                        lastChar = (char) (A + i);
                        int addNum;
                        // 前面还有未用过的排序大的字母，只添加一个当前位置的字母
                        if (flag) {
                            addNum = 1;
                            count[i] -= 1;
                            flag = false;
                        } else {
                            // 前面没有未用过的排序大的字母
                            if (count[i] > repeatLimit) {
                                // 字母数大于限制数，则标记前面还有未用过的排序大的字母
                                addNum = repeatLimit;
                                count[i] -= repeatLimit;
                                flag = true;
                            } else {
                                // 小于则全部加进去即可
                                addNum = count[i];
                                count[i] = 0;
                            }
                        }
                        for (int j = 0; j < addNum; j++) {
                            sb.append(lastChar);
                        }
                        num -= addNum;
                        // 跳出当前循环，下一次继续从大开始找
                        break;
                    }
                }
            }
            // 说明26个字母都已经遍历过了，结束添加过程
            if (i < 0) {
                break;
            }
        }
        return sb.toString();
    }

}
