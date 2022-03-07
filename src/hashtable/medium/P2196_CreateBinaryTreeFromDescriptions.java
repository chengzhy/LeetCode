package hashtable.medium;

import java.util.*;

/**
 * 根据描述创建二叉树
 *
 * 给你一个二维整数数组 descriptions ，其中 descriptions[i] = [parenti, childi, isLefti] 表示 parenti 是 childi 在 二叉树 中的 父节点，二叉树中各节点的值 互不相同 。此外：
 *
 * 如果 isLefti == 1 ，那么 childi 就是 parenti 的左子节点。
 * 如果 isLefti == 0 ，那么 childi 就是 parenti 的右子节点。
 * 请你根据 descriptions 的描述来构造二叉树并返回其 根节点 。
 *
 * 测试用例会保证可以构造出 有效 的二叉树。
 *
 *
 *
 * 示例 1：
 *
 *
 *
 * 输入：descriptions = [[20,15,1],[20,17,0],[50,20,1],[50,80,0],[80,19,1]]
 * 输出：[50,20,80,15,17,19]
 * 解释：根节点是值为 50 的节点，因为它没有父节点。
 * 结果二叉树如上图所示。
 * 示例 2：
 *
 *
 *
 * 输入：descriptions = [[1,2,1],[2,3,0],[3,4,1]]
 * 输出：[1,2,null,null,3,4]
 * 解释：根节点是值为 1 的节点，因为它没有父节点。
 * 结果二叉树如上图所示。
 *
 *
 * 提示：
 *
 * 1 <= descriptions.length <= 104
 * descriptions[i].length == 3
 * 1 <= parenti, childi <= 105
 * 0 <= isLefti <= 1
 * descriptions 所描述的二叉树是一棵有效二叉树
 *
 * @author chengzhy
 * @date 2022/3/6 11:24
 */
public class P2196_CreateBinaryTreeFromDescriptions {

    public TreeNode createBinaryTree(int[][] descriptions) {
        /** map记录树节点 */
        Map<Integer, TreeNode> map = new HashMap<>();
        /** set记录孩子节点的值 */
        Set<Integer> childs = new HashSet<>();
        for (int[] description : descriptions) {
            TreeNode parent = map.get(description[0]);
            if (Objects.isNull(parent)) {
                parent = new TreeNode(description[0]);
                map.put(description[0], parent);
            }
            TreeNode child = map.get(description[1]);
            if (Objects.isNull(child)) {
                child = new TreeNode(description[1]);
                map.put(description[1], child);
            }
            if (description[2] == 1) {
                parent.left = child;
            } else {
                parent.right = child;
            }
            childs.add(description[1]);
        }
        for (Map.Entry<Integer, TreeNode> entry : map.entrySet()) {
            if (!childs.contains(entry.getKey())) {
                return entry.getValue();
            }
        }
        return null;
    }

    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode() {}
        TreeNode(int val) { this.val = val; }
        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }

}
