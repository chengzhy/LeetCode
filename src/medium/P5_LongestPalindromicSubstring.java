package medium;

/**
 * @description 最长回文子串
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
 */
public class P5_LongestPalindromicSubstring {
    public String longestPalindrome(String s) {
        if (s.length() == 0) {
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
        return sb.toString();
    }

    public static void main(String[] args) {
        P5_LongestPalindromicSubstring l = new P5_LongestPalindromicSubstring();
        String result = l.longestPalindrome("babab");
        System.out.println(result);
    }
}
