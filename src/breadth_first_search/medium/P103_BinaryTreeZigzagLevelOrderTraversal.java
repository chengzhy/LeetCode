package breadth_first_search.medium;

import java.util.*;

/**
 * 二叉树的锯齿形层序遍历
 * <a href="https://leetcode-cn.com/problems/binary-tree-zigzag-level-order-traversal/">🔗</a>
 *
 * 给你二叉树的根节点 root ，返回其节点值的 锯齿形层序遍历 。（即先从左往右，再从右往左进行下一层遍历，以此类推，层与层之间交替进行）。
 *
 *  
 *
 * 示例 1：
 *
 *
 * 输入：root = [3,9,20,null,null,15,7]
 * 输出：[[3],[20,9],[15,7]]
 * 示例 2：
 *
 * 输入：root = [1]
 * 输出：[[1]]
 * 示例 3：
 *
 * 输入：root = []
 * 输出：[]
 *  
 *
 * 提示：
 *
 * 树中节点数目在范围 [0, 2000] 内
 * -100 <= Node.val <= 100
 *
 * @author chengzhy
 * @date 2022/3/10 16:46
 */
public class P103_BinaryTreeZigzagLevelOrderTraversal {

    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        List<List<Integer>> result = new ArrayList<>();
        if (Objects.nonNull(root)) {
            // 使用双端队列
            Deque<TreeNode> deque = new ArrayDeque<>();
            deque.offer(root);
            // 奇数层为true，偶数层为false
            boolean flag = true;
            List<Integer> tempList = new ArrayList<>();
            while (!deque.isEmpty()) {
                int size = deque.size();
                tempList.clear();
                for (int i = 0; i < size; i++) {
                    TreeNode treeNode;
                    if (flag) {
                        // 奇数层，从队首出队，将其左、右子节点加入队尾
                        treeNode = deque.pollFirst();
                        if (Objects.nonNull(treeNode.left)) {
                            deque.offerLast(treeNode.left);
                        }
                        if (Objects.nonNull(treeNode.right)) {
                            deque.offerLast(treeNode.right);
                        }
                    } else {
                        // 偶数层，从队尾出队，将其右、左子节点加入队首
                        treeNode = deque.pollLast();
                        if (Objects.nonNull(treeNode.right)) {
                            deque.offerFirst(treeNode.right);
                        }
                        if (Objects.nonNull(treeNode.left)) {
                            deque.offerFirst(treeNode.left);
                        }
                    }
                    tempList.add(treeNode.val);
                }
                flag = !flag;
                result.add(new ArrayList<>(tempList));
            }
        }
        return result;
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
