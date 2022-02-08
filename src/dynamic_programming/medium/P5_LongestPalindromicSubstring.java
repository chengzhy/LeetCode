package dynamic_programming.medium;

/**
 * 最长回文子串
 *
 * Given a string s, find the longest palindromic substring in s. You may assume that the maximum length of s is 1000.
 *
 * Example 1:
 *
 * Input: "babad"
 * Output: "bab"
 * Note: "aba" is also a valid answer.
 * Example 2:
 *
 * Input: "cbbd"
 * Output: "bb"
 *
 * 给定一个字符串 s，找到 s 中最长的回文子串。你可以假设 s 的最大长度为 1000。
 *
 * @author chengzhy
 * @date 2022/1/27 16:00
 */
public class P5_LongestPalindromicSubstring {
    public String longestPalindrome(String s) {
        /*if (s.length() == 0) {
            return s;
        }
        char[] temp = new char[2*s.length()+4];
        int[] len = new int[2*s.length()+4];

        temp[0] = '@';
        for (int i=1; i<=2*s.length(); i+=2){
            temp[i] = '#';
            temp[i+1] = s.charAt(i/2);
        }
        temp[2*s.length()+1] = '#';
        temp[2*s.length()+2] = '$';
        temp[2*s.length()+3] = 0;

        int mx=0, ans=0, po=0;//mx即为当前计算回文串最右边字符的最大值
        for (int i=1; i<=2*s.length()+1; i++){
            if (mx > i){
                len[i] = Math.min(mx-i, len[2*po-i]);//在Len[j]和mx-i中取个小
            } else {
                len[i] = 1;
            }
            while (temp[i-len[i]] == temp[i+len[i]]) {
                len[i]++;
            }
            if (len[i]+i > mx){//若新计算的回文串右端点位置大于mx，要更新po和mx的值
                mx = len[i] + i;
                po = i;
            }
            ans = Math.max(ans, len[i]);
        }
        StringBuilder sb = new StringBuilder("");
        //ans-1返回Len[i]中的最大值-1即为原串的最长回文子串额长度
        for (int i=1; i<2*s.length()+1; i++){
            if (len[i] == ans){
                for (int j=i-(ans-1); j<=i+(ans-1); j++){
                    if (temp[j] != '#'){
                        sb.append(temp[j]);
                    }
                }
                break;
            }
        }
        return sb.toString();*/
        /**
         * 边界情况即为子串长度为1或2的情况。我们枚举每一种边界情况，并从对应的子串开始不断地向两边扩展。
         * 如果两边的字母相同，我们就可以继续扩展，例如从P(i+1,j-1)扩展到 P(i,j)；
         * 如果两边的字母不同，我们就可以停止扩展，因为在这之后的子串都不能是回文串了。
         * 聪明的读者此时应该可以发现，「边界情况」对应的子串实际上就是我们「扩展」出的回文串的「回文中心」。
         * 方法二的本质即为：我们枚举所有的「回文中心」并尝试「扩展」，直到无法扩展为止，此时的回文串长度即为此「回文中心」下的最长回文串长度。
         * 我们对所有的长度求出最大值，即可得到最终的答案。
         */
        if (s.length() < 1) {
            return s;
        }
        int start = 0, end = 0;
        for (int i = 0; i < s.length(); i++) {
            int len1 = expandAroundCenter(s, i, i);
            int len2 = expandAroundCenter(s, i, i + 1);
            int len = Math.max(len1, len2);
            if (len > end - start) {
                start = i - (len - 1) / 2;
                end = i + len / 2;
            }
        }
        return s.substring(start, end + 1);
    }

    private int expandAroundCenter(String s, int left, int right) {
        while (left >= 0 && right < s.length() && s.charAt(left) == s.charAt(right)) {
            --left;
            ++right;
        }
        return right - left - 1;
    }

}
