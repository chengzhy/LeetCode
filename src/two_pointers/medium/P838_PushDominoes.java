package two_pointers.medium;

/**
 * 推多米诺
 * <a href="https://leetcode-cn.com/problems/push-dominoes/">🔗</a>
 *
 * n 张多米诺骨牌排成一行，将每张多米诺骨牌垂直竖立。在开始时，同时把一些多米诺骨牌向左或向右推。
 *
 * 每过一秒，倒向左边的多米诺骨牌会推动其左侧相邻的多米诺骨牌。同样地，倒向右边的多米诺骨牌也会推动竖立在其右侧的相邻多米诺骨牌。
 *
 * 如果一张垂直竖立的多米诺骨牌的两侧同时有多米诺骨牌倒下时，由于受力平衡， 该骨牌仍然保持不变。
 *
 * 就这个问题而言，我们会认为一张正在倒下的多米诺骨牌不会对其它正在倒下或已经倒下的多米诺骨牌施加额外的力。
 *
 * 给你一个字符串 dominoes 表示这一行多米诺骨牌的初始状态，其中：
 *
 * dominoes[i] = 'L'，表示第 i 张多米诺骨牌被推向左侧，
 * dominoes[i] = 'R'，表示第 i 张多米诺骨牌被推向右侧，
 * dominoes[i] = '.'，表示没有推动第 i 张多米诺骨牌。
 * 返回表示最终状态的字符串。
 *
 *  
 * 示例 1：
 *
 * 输入：dominoes = "RR.L"
 * 输出："RR.L"
 * 解释：第一张多米诺骨牌没有给第二张施加额外的力。
 * 示例 2：
 *
 *
 * 输入：dominoes = ".L.R...LR..L.."
 * 输出："LL.RR.LLRRLL.."
 *  
 *
 * 提示：
 *
 * n == dominoes.length
 * 1 <= n <= 105
 * dominoes[i] 为 'L'、'R' 或 '.'
 *
 * @author chengzhy
 * @date 2022/2/21 17:27
 */
public class P838_PushDominoes {
    private static final char LEFT = 'L';
    private static final char RIGHT = 'R';
    private static final char STANDING_DOMINO = '.';

    public String pushDominoes(String dominoes) {
        char[] chars = dominoes.toCharArray();
        int i = 0;
        // 若一开始为’.‘，则模拟其左边有一个向左推的多米诺骨牌
        char left = LEFT;
        while (i < chars.length) {
            int j = i;
            // 找到一段连续的没有被推动的骨牌
            while (j < chars.length && chars[j] == STANDING_DOMINO) {
                j++;
            }
            // 这里有可能最右边为'.'，则模拟其右边有一个向右推的多米诺骨牌
            char right = (j < chars.length) ? chars[j] : RIGHT;
            if (left == right) {
                // 两个推的方向相同，则中间还未被推倒的多米诺骨牌也会变成该方向
                while (i < j) {
                    chars[i++] = right;
                }
            } else if (left == RIGHT && right == LEFT) {
                // 两个推的方向相向
                int k = j - 1;
                // 其中的多米诺骨牌往中间倒
                while (i < k) {
                    chars[i++] = RIGHT;
                    chars[k--] = LEFT;
                }
            }
            // 接着往下一个区间寻找
            left = right;
            i = j + 1;
        }
        return new String(chars);
    }

}
