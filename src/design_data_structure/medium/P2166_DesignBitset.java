package design_data_structure.medium;

import java.util.Arrays;

/**
 * 设计位集
 * <a href="https://leetcode.cn/problems/design-bitset/">🔗</a>
 *
 * 位集 Bitset 是一种能以紧凑形式存储位的数据结构。
 *
 * 请你实现 Bitset 类。
 *
 * Bitset(int size) 用 size 个位初始化 Bitset ，所有位都是 0 。
 * void fix(int idx) 将下标为 idx 的位上的值更新为 1 。如果值已经是 1 ，则不会发生任何改变。
 * void unfix(int idx) 将下标为 idx 的位上的值更新为 0 。如果值已经是 0 ，则不会发生任何改变。
 * void flip() 翻转 Bitset 中每一位上的值。换句话说，所有值为 0 的位将会变成 1 ，反之亦然。
 * boolean all() 检查 Bitset 中 每一位 的值是否都是 1 。如果满足此条件，返回 true ；否则，返回 false 。
 * boolean one() 检查 Bitset 中 是否 至少一位 的值是 1 。如果满足此条件，返回 true ；否则，返回 false 。
 * int count() 返回 Bitset 中值为 1 的位的 总数 。
 * String toString() 返回 Bitset 的当前组成情况。注意，在结果字符串中，第 i 个下标处的字符应该与 Bitset 中的第 i 位一致。
 *  
 *
 * 示例：
 *
 * 输入
 * ["Bitset", "fix", "fix", "flip", "all", "unfix", "flip", "one", "unfix", "count", "toString"]
 * [[5], [3], [1], [], [], [0], [], [], [0], [], []]
 * 输出
 * [null, null, null, null, false, null, null, true, null, 2, "01010"]
 *
 * 解释
 * Bitset bs = new Bitset(5); // bitset = "00000".
 * bs.fix(3);     // 将 idx = 3 处的值更新为 1 ，此时 bitset = "00010" 。
 * bs.fix(1);     // 将 idx = 1 处的值更新为 1 ，此时 bitset = "01010" 。
 * bs.flip();     // 翻转每一位上的值，此时 bitset = "10101" 。
 * bs.all();      // 返回 False ，bitset 中的值不全为 1 。
 * bs.unfix(0);   // 将 idx = 0 处的值更新为 0 ，此时 bitset = "00101" 。
 * bs.flip();     // 翻转每一位上的值，此时 bitset = "11010" 。
 * bs.one();      // 返回 True ，至少存在一位的值为 1 。
 * bs.unfix(0);   // 将 idx = 0 处的值更新为 0 ，此时 bitset = "01010" 。
 * bs.count();    // 返回 2 ，当前有 2 位的值为 1 。
 * bs.toString(); // 返回 "01010" ，即 bitset 的当前组成情况。
 *  
 *
 * 提示：
 *
 * 1 <= size <= 105
 * 0 <= idx <= size - 1
 * 至多调用 fix、unfix、flip、all、one、count 和 toString 方法 总共 105 次
 * 至少调用 all、one、count 或 toString 方法一次
 * 至多调用 toString 方法 5 次
 *
 * @author chengzhy
 * @date 2022/2/7 10:03
 */
public class P2166_DesignBitset {

    public static class Bitset {
        private char[] chars;

        // 是否翻转
        private boolean flip;

        private int size;

        // 记录Bitset中值为1的位的总数
        private int count;

        private static final char ZERO_BIT = '0';

        private static final char ONE_BIT = '1';

        public Bitset(int size) {
            this.size = size;
            chars = new char[size];
            Arrays.fill(chars, ZERO_BIT);
        }

        public void fix(int idx) {
            if (flip) {
                // 翻转了，若当前位为1，将其变为0
                if (chars[idx] == ONE_BIT) {
                    count++;
                }
                chars[idx] = ZERO_BIT;
            } else {
                if (chars[idx] == ZERO_BIT) {
                    count++;
                }
                chars[idx] = ONE_BIT;
            }
        }

        public void unfix(int idx) {
            if (flip) {
                // 翻转了，若当前位为0，将其变为1
                if (chars[idx] == ZERO_BIT) {
                    count--;
                }
                chars[idx] = ONE_BIT;
            } else {
                if (chars[idx] == ONE_BIT) {
                    count--;
                }
                chars[idx] = ZERO_BIT;
            }
        }

        public void flip() {
            flip = (flip) ? false : true;
            count = size - count;
        }

        public boolean all() {
            return count == size;
        }

        public boolean one() {
            return count > 0;
        }

        public int count() {
            return count;
        }

        @Override
        public String toString() {
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < chars.length; i++) {
                sb.append((flip) ? ((chars[i] == ZERO_BIT) ? ONE_BIT : ZERO_BIT) : chars[i]);
            }
            return sb.toString();
        }
    }

}
