package backtracking.hard;

/**
 * 最多可达成的换楼请求数目
 * <a href="https://leetcode.cn/problems/maximum-number-of-achievable-transfer-requests/">🔗</a>
 *
 * 我们有 n 栋楼，编号从 0 到 n - 1 。每栋楼有若干员工。由于现在是换楼的季节，部分员工想要换一栋楼居住。
 *
 * 给你一个数组 requests ，其中 requests[i] = [fromi, toi] ，表示一个员工请求从编号为 fromi 的楼搬到编号为 toi 的楼。
 *
 * 一开始 所有楼都是满的，所以从请求列表中选出的若干个请求是可行的需要满足 每栋楼员工净变化为 0 。意思是每栋楼 离开 的员工数目 等于 该楼 搬入 的员工数数目。比方说 n = 3 且两个员工要离开楼 0 ，一个员工要离开楼 1 ，一个员工要离开楼 2 ，如果该请求列表可行，应该要有两个员工搬入楼 0 ，一个员工搬入楼 1 ，一个员工搬入楼 2 。
 *
 * 请你从原请求列表中选出若干个请求，使得它们是一个可行的请求列表，并返回所有可行列表中最大请求数目。
 *
 *  
 *
 * 示例 1：
 *
 *
 *
 * 输入：n = 5, requests = [[0,1],[1,0],[0,1],[1,2],[2,0],[3,4]]
 * 输出：5
 * 解释：请求列表如下：
 * 从楼 0 离开的员工为 x 和 y ，且他们都想要搬到楼 1 。
 * 从楼 1 离开的员工为 a 和 b ，且他们分别想要搬到楼 2 和 0 。
 * 从楼 2 离开的员工为 z ，且他想要搬到楼 0 。
 * 从楼 3 离开的员工为 c ，且他想要搬到楼 4 。
 * 没有员工从楼 4 离开。
 * 我们可以让 x 和 b 交换他们的楼，以满足他们的请求。
 * 我们可以让 y，a 和 z 三人在三栋楼间交换位置，满足他们的要求。
 * 所以最多可以满足 5 个请求。
 * 示例 2：
 *
 *
 *
 * 输入：n = 3, requests = [[0,0],[1,2],[2,1]]
 * 输出：3
 * 解释：请求列表如下：
 * 从楼 0 离开的员工为 x ，且他想要回到原来的楼 0 。
 * 从楼 1 离开的员工为 y ，且他想要搬到楼 2 。
 * 从楼 2 离开的员工为 z ，且他想要搬到楼 1 。
 * 我们可以满足所有的请求。
 * 示例 3：
 *
 * 输入：n = 4, requests = [[0,3],[3,1],[1,2],[2,0]]
 * 输出：4
 *  
 *
 * 提示：
 *
 * 1 <= n <= 20
 * 1 <= requests.length <= 16
 * requests[i].length == 2
 * 0 <= fromi, toi < n
 *
 * @author chengzhy
 * @date 2022/2/28 10:27
 */
public class P1601_MaximumNumberOfAchievableTransferRequests {
    /** delta记录楼变化情况 */
    private int[] delta;
    /** zero为delta中0的个数 */
    private int result = 0, count = 0, zero, n;

    public int maximumRequests(int n, int[][] requests) {
        /**
         * 我们可以通过回溯的方式枚举每一个请求是否被选择。
         * 定义函数backtracking(pos)表示我们正在枚举第pos个请求。同时，我们使用数组delta记录每一栋楼的员工变化量，以及变量count记录被选择的请求数量。
         * 对于第pos个请求[x,y]，如果选择该请求，那么就需要将delta[x]的值减1，delta[y]的值加1，count的值加1；如果不选择该请求，则不需要进行任何操作。
         * 在这之后，我们调用backtracking(pos+1)枚举下一个请求。
         * 如果我们枚举完了全部请求，则需要判断是否满足要求，也就是判断delta中的所有值是否均为0。若满足要求，则更新答案的最大值。
         * 代码实现时，可以在修改delta的同时维护delta中的0的个数，记作zero，初始值为n。如果delta[x]增加或减少前为0，则将zero减1；
         * 如果delta[x]增加或减少后为0，则将zero加1。
         */
        delta = new int[n];
        zero = n;
        this.n = n;
        backtracking(requests, 0);
        return result;
    }

    public void backtracking(int[][] requests, int pos) {
        if (pos == requests.length) {
            if (zero == n) {
                result = Math.max(result, count);
            }
            return;
        }

        // 不选 requests[pos]
        backtracking(requests, pos + 1);

        // 选 requests[pos]
        int z = zero;
        count++;
        int[] r = requests[pos];
        // x为要搬出去的楼，y为要搬进去的楼
        int x = r[0], y = r[1];
        zero -= (delta[x] == 0) ? 1 : 0;
        delta[x]--;
        zero += (delta[x] == 0) ? 1 : 0;
        zero -= (delta[y] == 0) ? 1 : 0;
        delta[y]++;
        zero += (delta[y] == 0) ? 1 : 0;
        backtracking(requests, pos + 1);
        delta[y]--;
        delta[x]++;
        count--;
        zero = z;
    }

}
