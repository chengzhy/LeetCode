package depth_first_search.medium;

/**
 * 统计最高分的节点数目
 * <a href="https://leetcode.cn/problems/count-nodes-with-the-highest-score/">🔗</a>
 *
 * 给你一棵根节点为 0 的 二叉树 ，它总共有 n 个节点，节点编号为 0 到 n - 1 。同时给你一个下标从 0 开始的整数数组 parents 表示这棵树，其中 parents[i] 是节点 i 的父节点。由于节点 0 是根，所以 parents[0] == -1 。
 *
 * 一个子树的 大小 为这个子树内节点的数目。每个节点都有一个与之关联的 分数 。求出某个节点分数的方法是，将这个节点和与它相连的边全部 删除 ，剩余部分是若干个 非空 子树，这个节点的 分数 为所有这些子树 大小的乘积 。
 *
 * 请你返回有 最高得分 节点的 数目 。
 *
 *  
 *
 * 示例 1:
 *
 *
 *
 * 输入：parents = [-1,2,0,2,0]
 * 输出：3
 * 解释：
 * - 节点 0 的分数为：3 * 1 = 3
 * - 节点 1 的分数为：4 = 4
 * - 节点 2 的分数为：1 * 1 * 2 = 2
 * - 节点 3 的分数为：4 = 4
 * - 节点 4 的分数为：4 = 4
 * 最高得分为 4 ，有三个节点得分为 4 （分别是节点 1，3 和 4 ）。
 * 示例 2：
 *
 *
 *
 * 输入：parents = [-1,2,0]
 * 输出：2
 * 解释：
 * - 节点 0 的分数为：2 = 2
 * - 节点 1 的分数为：2 = 2
 * - 节点 2 的分数为：1 * 1 = 1
 * 最高分数为 2 ，有两个节点分数为 2 （分别为节点 0 和 1 ）。
 *  
 *
 * 提示：
 *
 * n == parents.length
 * 2 <= n <= 105
 * parents[0] == -1
 * 对于 i != 0 ，有 0 <= parents[i] <= n - 1
 * parents 表示一棵二叉树。
 *
 * @author chengzhy
 * @date 2022/3/11 10:52
 */
public class P2049_CountNodesWithTheHighestScore {
    private int n;
    private int count = 0;
    private long maxScore = Long.MIN_VALUE;

    public int countHighestScoreNodes(int[] parents) {
        n = parents.length;
        // leftChild[]数组表示下标i的左孩子的下标，rightChild同理
        int[] leftChild = new int[n], rightChild = new int[n];
        for (int i = 0; i < n; i++) {
            leftChild[i] = -1;
            rightChild[i] = -1;
        }
        for (int i = 1; i < n; i++) {
            int p = parents[i];
            if (leftChild[p] == -1) {
                leftChild[p] = i;
            } else {
                rightChild[p] = i;
            }
        }
        dfs(0, leftChild, rightChild);
        return count;
    }

    private int dfs(int node, int[] leftChild, int[] rightChild) {
        if (node == -1) {
            return 0;
        }
        // 递归计算节点的左子树和右子树的节点树
        int leftCount = dfs(leftChild[node], leftChild, rightChild);
        int rightCount = dfs(rightChild[node], leftChild, rightChild);
        // parentCount为去掉该节点后其父子树的节点个数
        int parentCount = n - leftCount - rightCount - 1;
        // 在此直接计算分数
        long score = (long) transferTreeNum(parentCount) * transferTreeNum(leftCount) *
                transferTreeNum(rightCount);
        if (score == maxScore) {
            count++;
        } else if (score > maxScore) {
            count = 1;
            maxScore = score;
        }
        // 返回的是某节点及其子树的数量
        return leftCount + rightCount + 1;
    }

    private int transferTreeNum(int count) {
        // 对于子树为0的情况，根据题意将值置为1
        return (count <= 1) ? 1 : count;
    }

}
