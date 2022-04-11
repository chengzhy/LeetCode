package depth_first_search.hard;

/**
 * 从先序遍历还原二叉树
 * <a href="https://leetcode-cn.com/problems/recover-a-tree-from-preorder-traversal/">🔗</a>
 *
 * 我们从二叉树的根节点 root 开始进行深度优先搜索。
 *
 * 在遍历中的每个节点处，我们输出 D 条短划线（其中 D 是该节点的深度），然后输出该节点的值。（如果节点的深度为 D，则其直接子节点的深度为 D + 1。根节点的深度为 0）。
 *
 * 如果节点只有一个子节点，那么保证该子节点为左子节点。
 *
 * 给出遍历输出 S，还原树并返回其根节点 root。
 *
 *  
 *
 * 示例 1：
 *
 *
 *
 * 输入："1-2--3--4-5--6--7"
 * 输出：[1,2,5,3,4,6,7]
 * 示例 2：
 *
 *
 *
 * 输入："1-2--3---4-5--6---7"
 * 输出：[1,2,5,3,null,6,null,4,null,7]
 * 示例 3：
 *
 *
 *
 * 输入："1-401--349---90--88"
 * 输出：[1,401,null,349,88,90]
 *  
 *
 * 提示：
 *
 * 原始树中的节点数介于 1 和 1000 之间。
 * 每个节点的值介于 1 和 10 ^ 9 之间。
 *
 * @author chengzhy
 * @date 2022/3/14 15:09
 */
public class P1028_RecoverATreeFromPreorderTraversal {
    /** 表示字符串遍历到的位置 */
    private int index;

    public TreeNode recoverFromPreorder(String traversal) {
        if (traversal == null || traversal.length() == 0) {
            return null;
        }
        return dfs(traversal, 0);
    }

    private TreeNode dfs(String traversal, int depth) {
        int currentDepth = 0;
        // 查找‘-’连续的个数来表示当前深度
        while (index < traversal.length() && traversal.charAt(index) == '-') {
            currentDepth++;
            index++;
        }
        // 当前深度不等于期望深度，表示当前元素不是上一轮递归元素的子节点，且上一轮递归元素应该是叶子节点
        if (currentDepth != depth) {
            // 根据回溯的思想需要将index回退到本次递归前的位置
            index -= currentDepth;
            return null;
        }
        // 计算节点的值
        int value = 0;
        while (index < traversal.length() && Character.isDigit(traversal.charAt(index))) {
            value = value * 10 + (traversal.charAt(index) - '0');
            index++;
        }
        TreeNode root = new TreeNode(value);
        // 左右子树深度加1
        root.left = dfs(traversal, depth + 1);
        root.right = dfs(traversal, depth + 1);
        return root;
    }

    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode() {}
        TreeNode(int val) {
            this.val = val;
        }
        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }

}
