package math.medium;

/**
 * 整数转罗马数字
 * <a href="https://leetcode.cn/problems/integer-to-roman/">🔗</a>
 *
 * 罗马数字包含以下七种字符： I， V， X， L，C，D 和 M。
 *
 * 字符          数值
 * I             1
 * V             5
 * X             10
 * L             50
 * C             100
 * D             500
 * M             1000
 * 例如， 罗马数字 2 写做 II ，即为两个并列的 1。12 写做 XII ，即为 X + II 。 27 写做  XXVII, 即为 XX + V + II 。
 *
 * 通常情况下，罗马数字中小的数字在大的数字的右边。但也存在特例，例如 4 不写做 IIII，而是 IV。数字 1 在数字 5 的左边，所表示的数等于大数 5 减小数 1 得到的数值 4 。同样地，数字 9 表示为 IX。这个特殊的规则只适用于以下六种情况：
 *
 * I 可以放在 V (5) 和 X (10) 的左边，来表示 4 和 9。
 * X 可以放在 L (50) 和 C (100) 的左边，来表示 40 和 90。 
 * C 可以放在 D (500) 和 M (1000) 的左边，来表示 400 和 900。
 * 给定一个整数，将其转为罗马数字。输入确保在 1 到 3999 的范围内。
 *
 * 示例 1:
 *
 * 输入: 3
 * 输出: "III"
 * 示例 2:
 *
 * 输入: 4
 * 输出: "IV"
 * 示例 3:
 *
 * 输入: 9
 * 输出: "IX"
 * 示例 4:
 *
 * 输入: 58
 * 输出: "LVIII"
 * 解释: L = 50, V = 5, III = 3.
 * 示例 5:
 *
 * 输入: 1994
 * 输出: "MCMXCIV"
 * 解释: M = 1000, CM = 900, XC = 90, IV = 4.
 *  
 *
 * 提示：
 *
 * 1 <= num <= 3999
 *
 * @author chengzhy
 * @date 2022/1/27 16:00
 **/
public class P12_IntegerToRoman {

    public String intToRoman(int num) {
        /*StringBuilder roman = new StringBuilder();
        while (num > 0) {
            // 1-5
            if (num >= 1 && num < 5) {
                // 特殊情况
                if (num/4 > 0) {
                    roman.append("IV");
                    num = num % 4;
                } else {
                    int count = num/1;
                    for (int i=0; i<count; i++) {
                        roman.append("I");
                    }
                    num = num % 1;
                }
            } else if (num >= 5 && num < 10) { // 5-10
                // 特殊情况
                if (num/9 > 0) {
                    roman.append("IX");
                    num = num % 9;
                } else {
                    int count = num/5;
                    for (int i=0; i<count; i++) {
                        roman.append("V");
                    }
                    num = num % 5;
                }
            } else if (num >= 10 && num < 50) { // 10-50
                // 特殊情况
                if (num/40 > 0) {
                    roman.append("XL");
                    num = num % 40;
                } else {
                    int count = num/10;
                    for (int i=0; i<count; i++) {
                        roman.append("X");
                    }
                    num = num % 10;
                }
            } else if (num >= 50 && num < 100) { // 50-100
                // 特殊情况
                if (num/90 > 0) {
                    roman.append("XC");
                    num = num % 90;
                } else {
                    int count = num/50;
                    for (int i=0; i<count; i++) {
                        roman.append("L");
                    }
                    num = num % 50;
                }
            } else if (num >= 100 && num < 500) { // 100-500
                // 特殊情况
                if (num/400 > 0) {
                    roman.append("CD");
                    num = num % 400;
                } else {
                    int count = num/100;
                    for (int i=0; i<count; i++) {
                        roman.append("C");
                    }
                    num = num % 100;
                }
            } else if (num >= 500 && num < 1000) { // 500-1000
                // 特殊情况
                if (num/900 > 0) {
                    roman.append("CM");
                    num = num % 900;
                } else {
                    int count = num/500;
                    for (int i=0; i<count; i++) {
                        roman.append("D");
                    }
                    num = num % 500;
                }
            } else if (num >= 1000 && num <= 3999) { // 1000-3999
                int count = num/1000;
                for (int i=0; i<count; i++) {
                    roman.append("M");
                }
                num = num % 1000;
            } else {
                throw new IllegalArgumentException("num超出范围");
            }
        }
        return roman.toString();*/

        /**
         * 简化版写法(看着舒服)，虽然说内存占用跟上面一样
         */
        int values[] = {1000,900,500,400,100,90,50,40,10,9,5,4,1};
        String reps[] = {"M","CM","D","CD","C","XC","L","XL","X","IX","V","IV","I"};
        StringBuilder res = new StringBuilder();
        for(int i=0; i<13; i++){
            while(num >= values[i]){
                num -= values[i];
                res.append(reps[i]);
            }
        }
        return res.toString();
    }

}
