package math.medium;

/**
 * 查找给定哈希值的子串
 *
 * 给定整数 p 和 modulo ，一个长度为 k 且下标从 0 开始的字符串 s 的哈希值按照如下函数计算：
 *
 * hash(s, p, modulo) = (val(s[0]) * p0 + val(s[1]) * p1 + ... + val(s[k-1]) * pk-1) modulood modulo.
 * 其中 val(s[i]) 表示 s[i] 在字母表中的下标，从 val('a') = 1 到 val('z') = 26 。
 *
 * 给你一个字符串 s 和整数 power，moduloodulo，k 和 hashValue 。请你返回 s 中 第一个 长度为 k 的 子串 sub ，满足 hash(sub, power, moduloodulo) == hashValue 。
 *
 * 测试数据保证一定 存在 至少一个这样的子串。
 *
 * 子串 定义为一个字符串中连续非空字符组成的序列。
 *
 *
 *
 * 示例 1：
 *
 * 输入：s = "leetcode", power = 7, moduloodulo = 20, k = 2, hashValue = 0
 * 输出："ee"
 * 解释："ee" 的哈希值为 hash("ee", 7, 20) = (5 * 1 + 5 * 7) modulood 20 = 40 modulood 20 = 0 。
 * "ee" 是长度为 2 的第一个哈希值为 0 的子串，所以我们返回 "ee" 。
 * 示例 2：
 *
 * 输入：s = "fbxzaad", power = 31, moduloodulo = 100, k = 3, hashValue = 32
 * 输出："fbx"
 * 解释："fbx" 的哈希值为 hash("fbx", 31, 100) = (6 * 1 + 2 * 31 + 24 * 312) modulood 100 = 23132 modulood 100 = 32 。
 * "bxz" 的哈希值为 hash("bxz", 31, 100) = (2 * 1 + 24 * 31 + 26 * 312) modulood 100 = 25732 modulood 100 = 32 。
 * "fbx" 是长度为 3 的第一个哈希值为 32 的子串，所以我们返回 "fbx" 。
 * 注意，"bxz" 的哈希值也为 32 ，但是它在字符串中比 "fbx" 更晚出现。
 *
 *
 * 提示：
 *
 * 1 <= k <= s.length <= 2 * 104
 * 1 <= power, moduloodulo <= 109
 * 0 <= hashValue < moduloodulo
 * s 只包含小写英文字母。
 * 测试数据保证一定 存在 满足条件的子串。
 *
 * @author chengzhy
 * @date 2022/1/30 11:46
 */
public class P5994_FindSubstringWithGivenHashValue {

    public String subStrHash(String s, int power, int modulo, int k, int hashValue) {
        /**
         * 前置知识-模运算的两个公式：
         * 公式 1： (a * b) % m = ((a % m) * (b % m)) % m
         * 公式 2： (a + b) % m = (a % m + b % m) % m -->
         * ((a + b) + c) % m = ((a + b) % m + c % m) % m = ((a % m + b % m) % m + c % m) % m
         *
         * 窗口大小k固定，从0每次向右移动一位，hash值的变化为：
         *
         * hash = ((hash - val[i]) / power + val[i+k] * (power ^ k-1)) % modulo
         *
         * 由于除法取模不满足分配率(可利用逆元解决)计算困难，即
         *
         * (a / b) % modulo != ((a % modulo) / (b % modulo)) % modulo
         *
         * 可令窗口从n-1每次先左移动一位，hash值的变化为：
         *
         * hash = ((hash - val[n-i] * (power ^ k-1)) * power + val[n-i-1-k]) % modulo
         *
         * 例如abcd = a + bp + cp^2 + dp^3 = a + (b + cp + dp^2)p
         * bcde = b + cp +dp^2 + ep^3
         * 所以hash(i) = hash(i + 1) * power + val(s[i]) - val(s[i + k]) * power^k
         */
        long h = 0, p = 1, index = s.length() - k;
        for (int i = s.length() - 1; i >= s.length() - k; i--) {
            h = (h * power + s.charAt(i) - 'a' + 1) % modulo;
            p = p * power % modulo;
        }
        for (int i = s.length() - k - 1; i >= 0; i--) {
            if ((h = (h * power + s.charAt(i) - 'a' + 1 - (s.charAt(i + k) - 'a' + 1) * p % modulo + modulo) % modulo) == hashValue) {
                index = i;
            }
        }
        return s.substring((int) index, (int) index + k);
    }

}
