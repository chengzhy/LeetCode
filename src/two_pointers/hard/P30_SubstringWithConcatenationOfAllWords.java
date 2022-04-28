package two_pointers.hard;

import java.util.*;

/**
 * 串联所有单词的子串
 * <a href="https://leetcode.cn/problems/substring-with-concatenation-of-all-words/">🔗</a>
 *
 * 给定一个字符串 s 和一些长度相同的单词 words。找出 s 中恰好可以由 words 中所有单词串联形成的子串的起始位置。
 *
 * 注意子串要与 words 中的单词完全匹配，中间不能有其他字符，但不需要考虑 words 中单词串联的顺序。
 *
 *  
 *
 * 示例 1：
 *
 * 输入：
 *   s = "barfoothefoobarman",
 *   words = ["foo","bar"]
 * 输出：[0,9]
 * 解释：
 * 从索引 0 和 9 开始的子串分别是 "barfoo" 和 "foobar" 。
 * 输出的顺序不重要, [9,0] 也是有效答案。
 * 示例 2：
 *
 * 输入：
 *   s = "wordgoodgoodgoodbestword",
 *   words = ["word","good","best","word"]
 * 输出：[]
 *
 * @author chengzhy
 * @date 2022/1/27 16:00
 **/
public class P30_SubstringWithConcatenationOfAllWords {

    public List<Integer> findSubstring(String s, String[] words) {
        /**
         * 麻了麻了，超时8太行
         */
        /*List<Integer> result = new ArrayList<>();
        if (s.length()!=0 && words.length!=0) {
            Stack<Integer> stack = new Stack<>();
            find(s, words, new StringBuilder(), 0, 0, new Stack<>(), stack);
            while (!stack.isEmpty()) {
                result.add(stack.pop());
            }
        }
        return result;*/

        /**
         * 参考答案
         */
        List<Integer> res = new ArrayList<>();
        if (s.length() == 0 || words.length == 0) return res;
        // 每个词的长度
        int wl = words[0].length();
        // 词的数量
        int wc = words.length;
        // 词排列组合的长度
        int win = wc * wl;

        /**
         * 记录单个词出现的次数
         */
        Map<String,Integer> map = new HashMap<>();
        for (String word : words) {
            if (map.get(word) == null) map.put(word, 0);
            map.put(word, map.get(word)+1);
        }

        /**
         * wordNums[]按顺序存储单个词出现的次数
         */
        int[] wordNums = new int[map.size()];
        int i = 0;
        for (Integer n : map.values()) {
            wordNums[i] = n;
            i++;
        }

        /**
         * f[]为第n个词出现的起始位置打上标记，第一个词标记1，第二个词标记2...
         */
        int[] f = new int[s.length()];
        int wn = 1;
        for(String w : map.keySet()) {
            int index = -2;
            while (index != -1) {
                if (index == -2) index = -1;
                index = s.indexOf(w, index+1);
                if (index >= 0) f[index] = wn;
            }
            wn++;
        }

        /**
         * 滑块查找
         */
        int[] wordNumsWin = new int[map.size()];
        // j+win为滑块的末尾位置
        for (int j=0; j+win<=s.length(); j++) {
            // 跳过不是起始点的位置
            while (f[j] == 0) {
                j++;
                // 滑块末尾超出s的长度则返回
                if (j+win > s.length()) return res;
            }
            // k指针指向j
            int k = j;
            // k小于滑块末尾位置
            while (k < j+win) {
                // 如果该位置是词的起始位置
                if(f[k] != 0) {
                    // 该词数+1
                    wordNumsWin[f[k]-1]++;
                    // 如果该词计数>该词原本的数量，说明匹配的词不对，跳出循环
                    if (wordNumsWin[f[k]-1] > wordNums[f[k]-1]) break;
                } else break;
                // 跳过该词，指向下一个词应该出现的位置
                k += wl;
            }
            // k移动到了滑块的末尾
            if (k == j+win) {
                //compare，进行比较
                int mm=0;
                for (; mm<map.size(); mm++) {
                    if (wordNumsWin[mm] != wordNums[mm]) break;
                }
                // 如果每个词出现的次数都对的上，说明匹配成功，加入结果集
                if (mm == map.size()) res.add(j);
            }
            // clean the wordNumsWin，清空wordNumsWin[]
            for (int mm=0; mm<map.size(); mm++) wordNumsWin[mm] = 0;
        }

        return res;
    }

    /*private void find(String s, String[] words, StringBuilder sb, int count, int index, Stack<Integer> indexStack, Stack<Integer> resultStack) {
        if (count == words.length) {
            if (s.indexOf(sb.toString()) != -1) {
                if (!resultStack.contains(s.indexOf(sb.toString())+index)) {
                    resultStack.add(s.indexOf(sb.toString())+index);
                }
                index = index + s.indexOf(sb.toString())+1;
                find(s.substring(s.indexOf(sb.toString())+1), words, sb, count, index, indexStack, resultStack);
            }
            return;
        }
        for (int i=0; i<words.length; i++) {
            if (!indexStack.contains(i)) {
                if (s.indexOf(words[i]) != -1 && s.indexOf(sb.toString()) != -1) {
                    int position = sb.length();
                    sb.append(words[i]);
                    indexStack.add(i);
                    find(s, words, sb, count+1, index, indexStack, resultStack);
                    sb.delete(position, sb.length());
                    indexStack.pop();
                } else return;
            }
        }
    }*/

}
