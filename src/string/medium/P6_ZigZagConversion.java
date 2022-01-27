package string.medium;

/**
 * Z字形变换
 *
 * The string "PAYPALISHIRING" is written in a zigzag pattern on a given number of rows like this: (you may want to display this pattern in a fixed font for better legibility)
 *
 * P   A   H   N
 * A P L S I I G
 * Y   I   R
 * And then read line by line: "PAHNAPLSIIGYIR"
 *
 * Write the code that will take a string and make this conversion given a number of rows:
 *
 * string convert(string s, int numRows);
 * Example 1:
 *
 * Input: s = "PAYPALISHIRING", numRows = 3
 * Output: "PAHNAPLSIIGYIR"
 * Example 2:
 *
 * Input: s = "PAYPALISHIRING", numRows = 4
 * Output: "PINALSIGYAHRPI"
 * Explanation:
 *
 * P     I    N
 * A   L S  I G
 * Y A   H R
 * P     I
 *
 * 将一个给定字符串根据给定的行数，以从上往下、从左到右进行 Z 字形排列。
 * 比如输入字符串为 "LEETCODEISHIRING" 行数为 3 时，排列如下：
 * L   C   I   R
 * E T O E S I I G
 * E   D   H   N
 * 之后，你的输出需要从左往右逐行读取，产生出一个新的字符串，比如："LCIRETOESIIGEDHN"。
 * 请你实现这个将字符串进行指定行数变换的函数：
 * string convert(string s, int numRows);
 *
 * @author chengzhy
 * @date 2022/1/27 16:00
 */
public class P6_ZigZagConversion {
    public String convert(String s, int numRows) {
        /**
         * 做法复杂
         */
        /*if (s.length()==0) return "";
        if (numRows == 1) return s;
        Map<Integer, StringBuilder> map = new HashMap<>();
        StringBuilder sb = new StringBuilder("");
        StringBuilder temp;
        int count = 1, flag = 0;
        for (int i=0; i<s.length(); i++){
            temp = new StringBuilder("");
            if (map.containsKey(count)){
                temp = map.get(count);
            }
             if (flag == 0){//递增
                 temp.append(s.charAt(i));
                 map.put(count, temp);
                 if (count == numRows){
                     flag = 1;
                     count--;
                     continue;
                 }
                 count++;
             }else {//递减
                 temp.append(s.charAt(i));
                 map.put(count, temp);
                 if (count == 1){
                     flag = 0;
                     count++;
                     continue;
                 }
                 count--;
             }
        }
        for (int i=1; i<=numRows; i++){
            if (map.containsKey(i)) sb.append(map.get(i));
        }
        return sb.toString();*/
        if(s.length() < 3 || numRows < 2) {
            return s;
        }
        char[] chars = new char[s.length()];
        int arraySpot = 0;

        /**
         * 先装第一行
         */
        for (int i = 0; i < s.length(); i += (numRows + numRows -2)) {
            chars[arraySpot] = s.charAt(i);
            arraySpot++;
        }

        int skip = (numRows - 2) * 2;//跳过的个数
        int start = 1;
        while(skip > 1) {
            for (int i = start; i < s.length(); i += (numRows + numRows-2)) {
                chars[arraySpot] = s.charAt(i);
                arraySpot++;
                if((i + skip) < s.length()){
                    chars[arraySpot] = s.charAt(i+skip);
                    arraySpot++;
                }
            }
            skip -= 2;
            start++;
        }
        for (int i = numRows - 1; i < s.length(); i += (numRows + numRows - 2)) {
            chars[arraySpot] = s.charAt(i);
            arraySpot++;
        }
        return new String(chars);
    }

}
