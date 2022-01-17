package depth_first_search.medium;

import java.util.*;

/**
 * @description 电话号码的字母组合
 *
 * 给定一个仅包含数字 2-9 的字符串，返回所有它能表示的字母组合。答案可以按 任意顺序 返回。
 *
 * 给出数字到字母的映射如下（与电话按键相同）。注意 1 不对应任何字母。
 *
 *
 *
 *  
 *
 * 示例 1：
 *
 * 输入：digits = "23"
 * 输出：["ad","ae","af","bd","be","bf","cd","ce","cf"]
 * 示例 2：
 *
 * 输入：digits = ""
 * 输出：[]
 * 示例 3：
 *
 * 输入：digits = "2"
 * 输出：["a","b","c"]
 *  
 *
 * 提示：
 *
 * 0 <= digits.length <= 4
 * digits[i] 是范围 ['2', '9'] 的一个数字。
 *
 **/
public class P17_LetterCombinationsOfAPhoneNumber {

    private List<String> list = new ArrayList<>();
    private Map<String, List<String>> map = new HashMap<>(11);

    public List<String> letterCombinations(String digits) {
        /**
         * 垃圾解法
         */
        /*Map<String, String[]> map = new HashMap<>();
        map.put("2", "abc".split(""));
        map.put("3", "def".split(""));
        map.put("4", "ghi".split(""));
        map.put("5", "jkl".split(""));
        map.put("6", "mno".split(""));
        map.put("7", "pqrs".split(""));
        map.put("8", "tuv".split(""));
        map.put("9", "wxyz".split(""));
        List<String> list = new ArrayList<>();
        if (digits == null || "".equals(digits)) {
            return list;
        }
        String[] digit = digits.split("");
        for (int i=0; i<digit.length; i++) {
            if (i == 0) {
                list = new ArrayList<>(Arrays.asList(map.get(digit[i])));
                continue;
            }
            list = cartesian(list, map.get(digit[i]));
        }
        return list;*/

        /**
         * 深度优先搜索dfs
         */
        if (digits.length() == 0) {
            return list;
        }
        map.put("2", new ArrayList<>(Arrays.asList("a","b","c")));
        map.put("3", new ArrayList<>(Arrays.asList("d","e","f")));
        map.put("4", new ArrayList<>(Arrays.asList("g","h","i")));
        map.put("5", new ArrayList<>(Arrays.asList("j","k","l")));
        map.put("6", new ArrayList<>(Arrays.asList("m","n","o")));
        map.put("7", new ArrayList<>(Arrays.asList("p","q","r","s")));
        map.put("8", new ArrayList<>(Arrays.asList("t","u","v")));
        map.put("9", new ArrayList<>(Arrays.asList("w","x","y","z")));
        // start是灵魂
        dfs(digits, 0, new StringBuilder());
        return list;
    }

    /*private List<String> cartesian(List<String> list, String[] mapList) {
        List<String> newList = new ArrayList<>();
        for (int i=0; i<list.size(); i++) {
            for (int j=0; j<mapList.length; j++) {
                newList.add(list.get(i) + mapList[j]);
            }
        }
        return newList;
    }*/

    private void dfs(String digits, int start, StringBuilder stringBuilder) {
        // start等于字符串长度时，说明已经深度遍历完最后一个数字，返回
        if (start == digits.length()) {
            list.add(stringBuilder.toString());
            return;
        }
        // 获取第start个数字对应的字母，start+1
        List<String> arr = map.get(digits.substring(start, ++start));
        for (int i=0; i<arr.size(); i++) {
            stringBuilder.append(arr.get(i));
            // 递归，继续获取加1后的start开始的之后的数字
            dfs(digits, start, stringBuilder);
            // 遍历完后删除最后一个字母
            stringBuilder.deleteCharAt(stringBuilder.length()-1);
        }
    }

}
