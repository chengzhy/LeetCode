package string.easy;

/**
 * 最长公共前缀
 * <a href="https://leetcode.cn/problems/longest-common-prefix/">🔗</a>
 *
 * 编写一个函数来查找字符串数组中的最长公共前缀。
 *
 * 如果不存在公共前缀，返回空字符串 ""。
 *
 *  
 *
 * 示例 1：
 *
 * 输入：strs = ["flower","flow","flight"]
 * 输出："fl"
 * 示例 2：
 *
 * 输入：strs = ["dog","racecar","car"]
 * 输出：""
 * 解释：输入不存在公共前缀。
 *  
 *
 * 提示：
 *
 * 0 <= strs.length <= 200
 * 0 <= strs[i].length <= 200
 * strs[i] 仅由小写英文字母组成
 *
 * @author chengzhy
 * @date 2022/1/27 16:00
 **/
public class P14_LongestCommonPrefix {

    public String longestCommonPrefix(String[] strs) {
        /**
         * 垃圾解，时间复杂度太8行了
         */
        /*StringBuilder prefix = new StringBuilder("");
        if (strs.length > 1) {
            for (int i=0; i<201; i++) {
                if ("".equals(strs[0])) {
                    break;
                }
                if (i >= strs[0].length()) {
                    break;
                }
                // 依次取第一个字符串的第0-i位
                prefix.append(strs[0].substring(i, i+1));
                boolean flag = false;
                for (int j=1; j<strs.length; j++) {
                    // 没有就停止寻找
                    if (!strs[j].startsWith(prefix.toString())) {
                        flag = true;
                        break;
                    }
                }
                // 删除刚刚加上的一个字母
                if (flag) {
                    prefix.delete(i, i+1);
                    break;
                }
            }
        } else {
            try {
                prefix.append(strs[0]);
            } catch (ArrayIndexOutOfBoundsException e) {
            }
        }
        return prefix.toString();*/

        /**
         * 牛逼解，拍案叫绝
         */
        if (strs.length == 0) {
            return "";
        }
        // 公共前缀比所有字符串都短，随便选一个先
        String prefix = strs[0];
        for (String string : strs) {
            while(!string.startsWith(prefix)){
                if (prefix.length() == 0) {
                    return "";
                }
                // 公共前缀不匹配就让它变短！
                prefix = prefix.substring(0, prefix.length()-1);
            }
        }
        return prefix;

    }

}
