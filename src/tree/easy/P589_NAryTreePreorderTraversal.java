package tree.easy;

import java.util.*;

/**
 * N 叉树的前序遍历
 * <a href="https://leetcode.cn/problems/n-ary-tree-preorder-traversal/">🔗</a>
 *
 * 给定一个 n 叉树的根节点  root ，返回 其节点值的 前序遍历 。
 *
 * n 叉树 在输入中按层序遍历进行序列化表示，每组子节点由空值 null 分隔（请参见示例）。
 *
 *
 * 示例 1：
 *
 *
 *
 * 输入：root = [1,null,3,2,4,null,5,6]
 * 输出：[1,3,5,6,2,4]
 * 示例 2：
 *
 *
 *
 * 输入：root = [1,null,2,3,4,5,null,null,6,7,null,8,null,9,10,null,null,11,null,12,null,13,null,null,14]
 * 输出：[1,2,3,6,7,11,14,4,8,12,5,9,13,10]
 *  
 *
 * 提示：
 *
 * 节点总数在范围 [0, 104]内
 * 0 <= Node.val <= 104
 * n 叉树的高度小于或等于 1000
 *  
 *
 * 进阶：递归法很简单，你可以使用迭代法完成此题吗?
 *
 * @author chengzhy
 * @date 2022/3/10 13:57
 */
public class P589_NAryTreePreorderTraversal {

    public List<Integer> preorder(Node root) {
        List<Integer> preorderList = new ArrayList<>();
        // dfs(root, preorderList);
        if (Objects.nonNull(root)) {
            Deque<Node> stack = new ArrayDeque<>();
            stack.push(root);
            while (!stack.isEmpty()) {
                Node node = stack.pop();
                preorderList.add(node.val);
                // 利用栈需要先将最右子节点加入栈中
                for (int i = node.children.size() - 1; i >= 0; i--) {
                    stack.push(node.children.get(i));
                }
            }
        }
        return preorderList;
    }

    private void dfs(Node root, List<Integer> preorderList) {
        if (Objects.isNull(root)) {
            return;
        }
        preorderList.add(root.val);
        for (Node node : root.children) {
            dfs(node, preorderList);
        }
    }

    public static class Node {
        public int val;
        public List<Node> children;
        public Node() {
        }
        public Node(int val) {
            this.val = val;
        }
        public Node(int val, List<Node> children) {
            this.val = val;
            this.children = children;
        }
    }

}
