package array.easy;

/**
 * 1 比特与 2 比特字符
 * <a href="https://leetcode.cn/problems/1-bit-and-2-bit-characters/">🔗</a>
 *
 * 有两种特殊字符：
 *
 * 第一种字符可以用一比特 0 表示
 * 第二种字符可以用两比特（10 或 11）表示
 * 给你一个以 0 结尾的二进制数组 bits ，如果最后一个字符必须是一个一比特字符，则返回 true 。
 *
 *  
 *
 * 示例 1:
 *
 * 输入: bits = [1, 0, 0]
 * 输出: true
 * 解释: 唯一的解码方式是将其解析为一个两比特字符和一个一比特字符。
 * 所以最后一个字符是一比特字符。
 * 示例 2:
 *
 * 输入：bits = [1,1,1,0]
 * 输出：false
 * 解释：唯一的解码方式是将其解析为两比特字符和两比特字符。
 * 所以最后一个字符不是一比特字符。
 *  
 *
 * 提示:
 *
 * 1 <= bits.length <= 1000
 * bits[i] 为 0 或 1
 *
 * @author chengzhy
 * @date 2022/2/21 9:07
 */
public class P717_1bitAnd2bitCharacters {

    public boolean isOneBitCharacter(int[] bits) {
        for (int i = 0; i < bits.length; i++) {
            if (bits[i] == 1) {
                if (i == bits.length - 2) {
                    // 当前bits位为1且为倒数第二个数，就是false
                    return false;
                }
                // 当前bits位为1时就跳过下一个位置
                i++;
            }
        }
        // 遍历完了就是true
        return true;
    }

}
