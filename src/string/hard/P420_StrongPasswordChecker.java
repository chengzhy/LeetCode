package string.hard;

/**
 * 强密码检验器
 * <a href="https://leetcode-cn.com/problems/strong-password-checker/">🔗</a>
 *
 * 如果一个密码满足下述所有条件，则认为这个密码是强密码：
 * 由至少 6 个，至多 20 个字符组成。
 * 至少包含 一个小写 字母，一个大写 字母，和 一个数字 。
 * 同一字符 不能 连续出现三次 (比如 "...aaa..." 是不允许的, 但是 "...aa...a..." 如果满足其他条件也可以算是强密码)。
 * 给你一个字符串 password ，返回 将 password 修改到满足强密码条件需要的最少修改步数。如果 password 已经是强密码，则返回 0 。
 *
 * 在一步修改操作中，你可以：
 *
 * 插入一个字符到 password ，
 * 从 password 中删除一个字符，或
 * 用另一个字符来替换 password 中的某个字符。
 *  
 *
 * 示例 1：
 *
 * 输入：password = "a"
 * 输出：5
 * 示例 2：
 *
 * 输入：password = "aA1"
 * 输出：3
 * 示例 3：
 *
 * 输入：password = "1337C0d3"
 * 输出：0
 *  
 *
 * 提示：
 *
 * 1 <= password.length <= 50
 * password 由字母、数字、点 '.' 或者感叹号 '!'
 *
 * @author chengzhy
 * @date 2022/4/2 11:06
 */
public class P420_StrongPasswordChecker {

    public int strongPasswordChecker(String password) {
        int length = password.length(), step = 0,
                // lack为必须补充的字符个数
                hasLower = 0, hasUpper = 0, hasDigit = 0, lack;
        char[] chars = password.toCharArray();
        for (char c : chars) {
            if (Character.isLowerCase(c)) {
                // 有小写字母
                hasLower = 1;
            } else if (Character.isUpperCase(c)) {
                // 有大写字母
                hasUpper = 1;
            } else if (Character.isDigit(c)) {
                // 有数字
                hasDigit = 1;
            }
        }
        lack = 3 - hasLower - hasUpper - hasDigit;
        if (length < 6) {
            /**
             * 当给定的字符串长度严格小于6时，我们可以发现「删除一个字符」的操作是没有意义的，
             * 因为为了使得长度满足要求，我们需要至少添加6−n个字符，
             * 而每使用「删除一个字符」的操作，我们就需要一次额外的「添加一个字符」的操作来保证长度。
             * 由于我们「删除一个字符」的目的只可能是因为该字符连续出现了三次或以上，
             * 因此我们不妨在原本被删除的字符的两侧均「添加一个字符」，使用相同次数的操作达到同样（或者更好）的结果。
             *
             * 如果字符串中出现不超过4个连续相同的字符，「替换一个字符」的操作也是没有意义的，
             * 因为「替换一个字符」的目的只可能是将连续相同的字符中间的某个字符替换成不同的字符，
             * 而这个数量不超过4时，我们在中间的位置添加一个不同的字符，也可以达到同样（或者更好）的结果。
             *
             * 如果字符串中出现了5个连续相同的字符，那么「替换一个字符」的操作同样也是没有意义的。
             * 因为此时字符的种类只有一种，至少需要两次操作才能达到字符种类的要求。
             * 而我们在中间字符的两侧分别添加一个不同种类的字符，即可满足要求，并且操作次数最少。
             *
             * 因此，我们证明出了在这种情况下，只有「添加一个字符」的操作是有意义的。
             * 因此，该操作的次数为6−n与3−字符种类中的较大值，即需要保证字符串长度和字符种类均满足要求。
             */
            step = Math.max(6 - length, lack);
        } else if (length <= 20) {
            /**
             * 当给定的字符串长度在[6,20]范围内，我们可以发现「添加一个字符」和「删除一个字符」的操作是没有意义的，
             * 这是因为在长度已经满足要求的前提下，我们只需要再满足：（1）包含全部的3类字符；（2）同一字符不连续出现3次。
             *
             * 对于（1）而言，「删除一个字符」与该要求相反，而如果我们选择「添加一个字符」以增加字符的种类数，
             * 我们也可以「替换一个字符」，将当前数量较多的那一种字符替换成一种新的字符。
             *
             * 对于（2）而言，如果「删除一个字符」，我们也可以将待删除的那个字符替换成与周围字符均不相同的字符；
             * 如果「添加一个字符」，我们也可以将添加位置两侧字符中的其中一个替换成待添加的字符，这样的结果均是一致的。
             *
             * 因此，我们只需要考虑「替换一个字符」这一种操作就行了。
             * 对于连续的k个相同的字符，我们可以替换其中k/3个，使得不存在3个连续相同的字符（即每数3个字符就替换一次）。
             * 同时，我们还需要保证最终字符串包含全部的3类字符，因此替换操作的次数为所有k/3之和与3−(字符种类)中的较大值。
             */
            int replace = 0, count = 0;
            char pre = '#';
            for (char c : chars) {
                if (c == pre) {
                    count++;
                } else {
                    replace += count / 3;
                    pre = c;
                    count = 1;
                }
            }
            replace += count / 3;
            step = Math.max(replace, lack);
        } else {
            /**
             * 当给定的字符串长度严格大于 2020 时，类似地我们可以发现「添加一个字符」的操作是没有意义的，
             * 但此时「替换一个字符」和「删除一个字符」这两种操作都是必不可少的。
             * 这是因为「删除一个字符」会起到调整（减少）字符串长度的作用，而当字符串长度满足要求，但仍然存在3个连续相同字符的时候，
             * 「替换一个字符」的操作在上一类讨论中被证明是比「删除一个字符」更优的。
             *
             * 那么我们首先可以分别求出「替换一个字符」和「删除一个字符」需要的次数。
             * 对于「替换一个字符」，与上一类讨论一样，需要的次数为所有的k/3之和；而对于「删除一个字符」，需要的次数为n−20。
             *
             * 然而在删除字符的过程中，连续相同的字符数量也会变少。
             * 根据kmod3的值的不同，我们可以得出如下关于k/3值的变化的结论：
             * 如果kmod3=0，那么删除1个字符后，k/3的值会减少1，随后每删除3个字符，k/3的值会再减少1；
             * 如果kmod3=1，那么删除2个字符后，k/3的值会减少1，随后每删除3个字符，k/3的值会再减少1；
             * 如果kmod3=2，那么删除3个字符后，k/3的值会减少1；
             *
             * 因此在删除字符时，我们优先从所有kmod3=0的连续相同字符组中删除1个字符
             * （这样可以使得「替换一个字符」的操作次数同时减少1），
             * 其次从所有kmod3=1的连续相同字符组中删除2个字符（这样可以使得「替换一个字符」的操作次数同时减少 11）；
             * 最后每删除3个字符，可以使得「替换一个字符」的操作次数减少1。
             *
             * 最终「删除一个字符」需要的次数为n−20，「替换一个字符」需要的次数为 ((所有的k/3之和)) ，
             * 但可以通过删除字符省去若干次操作，最后得到的真正需要的操作次数还需要与3−(字符种类)取较大值。
             */
            // 替换次数和删除次数
            int replace = 0, remove = length - 20;
            // k mod 3 = 1 的组数，即删除 2 个字符可以减少 1 次替换操作
            int rm2 = 0;
            int count = 0;
            char pre = '#';
            for (char c : chars) {
                if (c == pre) {
                    count++;
                } else {
                    if (remove > 0 && count >= 3) {
                        if (count % 3 == 0) {
                            // 如果是 k % 3 = 0 的组，那么优先删除 1 个字符，减少 1 次替换操作
                            remove--;
                            replace--;
                        } else if (count % 3 == 1) {
                            // 如果是 k % 3 = 1 的组，那么存下来备用
                            rm2++;
                        }
                        // k % 3 = 2 的组无需显式考虑
                    }
                    replace += count / 3;
                    count = 1;
                    pre = c;
                }
            }
            if (remove > 0 && count >= 3) {
                if (count % 3 == 0) {
                    // 如果是 k % 3 = 0 的组，那么优先删除 1 个字符，减少 1 次替换操作
                    remove--;
                    replace--;
                } else if (count % 3 == 1) {
                    // 如果是 k % 3 = 1 的组，那么存下来备用
                    rm2++;
                }
            }
            replace += count / 3;
            // 使用 k % 3 = 1 的组的数量，由剩余的替换次数、组数和剩余的删除次数共同决定
            int use2 = Math.min(Math.min(replace, rm2), remove / 2);
            replace -= use2;
            remove -= use2 * 2;
            // 由于每有一次替换次数就一定有 3 个连续相同的字符（k / 3 决定），因此这里可以直接计算出使用 k % 3 = 2 的组的数量
            int use3 = Math.min(replace, remove / 3);
            replace -= use3;
            remove -= use3 * 3;
            step = (length - 20) + Math.max(replace, lack);
        }
        return step;
    }

}
