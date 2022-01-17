package two_pointers.easy;

/**
 * @description 实现 strStr()
 *
 * 实现 strStr() 函数。
 *
 * 给定一个 haystack 字符串和一个 needle 字符串，在 haystack 字符串中找出 needle 字符串出现的第一个位置 (从0开始)。如果不存在，则返回  -1。
 *
 * 示例 1:
 *
 * 输入: haystack = "hello", needle = "ll"
 * 输出: 2
 * 示例 2:
 *
 * 输入: haystack = "aaaaa", needle = "bba"
 * 输出: -1
 * 说明:
 *
 * 当 needle 是空字符串时，我们应当返回什么值呢？这是一个在面试中很好的问题。
 *
 * 对于本题而言，当 needle 是空字符串时我们应当返回 0 。这与C语言的 strstr() 以及 Java的 indexOf() 定义相符。
 *
 **/
public class P28_ImplementstrStr {

    public int strStr(String haystack, String needle) {
        char[] source = haystack.toCharArray(), target = needle.toCharArray();
        int sourceCount = haystack.length(), targetCount = needle.length();
        // 如果haystack为空
        if (sourceCount == 0) {
            // 如果needle为空，返回0；否则返回-1
            return (targetCount == 0 ? sourceCount : -1);
        }
        // 如果needle为空，返回0
        if (targetCount == 0) {
            return 0;
        }
        // first:needle第一个字符
        char first = target[0];
        // max:循环时i指针的最大位置
        int max = sourceCount - targetCount;
        // haystack遍历寻找
        for (int i = 0; i <= max; i++) {
            // 寻找第一个匹配的字符
            /* Look for first character. */
            if (source[i] != first) {
                while (++i <= max && source[i] != first);
            }
            // 找到第一个字符后，开始匹配之后的字符
            /* Found first character, now look at the rest of v2 */
            if (i <= max) {
                // j为匹配的第一个字符的下一个字符
                int j = i + 1;
                // end位置为从source第i个位置开始往后移动target的长度的位置
                int end = j + targetCount - 1;
                // 从第二个字符开始寻找，只有一个字符的情况基本不走这个循环了
                for (int k = 1; j < end && source[j]
                        == target[k]; j++, k++);
                // 如果都匹配完了
                if (j == end) {
                    /* Found whole string. */
                    return i;
                }
            }
        }
        return -1;
    }

}
