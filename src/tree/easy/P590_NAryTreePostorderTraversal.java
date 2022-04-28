package tree.easy;

import java.util.*;

/**
 * N 叉树的后序遍历
 * <a href="https://leetcode.cn/problems/n-ary-tree-postorder-traversal/">🔗</a>
 *
 * 给定一个 n 叉树的根节点 root ，返回 其节点值的 后序遍历 。
 *
 * n 叉树 在输入中按层序遍历进行序列化表示，每组子节点由空值 null 分隔（请参见示例）。
 *
 *  
 *
 * 示例 1：
 *
 *
 *
 * 输入：root = [1,null,3,2,4,null,5,6]
 * 输出：[5,6,3,2,4,1]
 * 示例 2：
 *
 *
 *
 * 输入：root = [1,null,2,3,4,5,null,null,6,7,null,8,null,9,10,null,null,11,null,12,null,13,null,null,14]
 * 输出：[2,6,14,11,7,3,12,8,4,13,9,10,5,1]
 *  
 *
 * 提示：
 *
 * 节点总数在范围 [0, 104] 内
 * 0 <= Node.val <= 104
 * n 叉树的高度小于或等于 1000
 *  
 *
 * 进阶：递归法很简单，你可以使用迭代法完成此题吗?
 *
 * @author chengzhy
 * @date 2022/3/12 20:12
 */
public class P590_NAryTreePostorderTraversal {

    public List<Integer> postorder(Node root) {
        List<Integer> postorderList = new ArrayList<>();
        // dfs(root, postorderList);
        if (Objects.nonNull(root)) {
            Deque<Node> stack = new ArrayDeque<>();
            Set<Node> visited = new HashSet<>();
            stack.push(root);
            while (!stack.isEmpty()) {
                Node node = stack.peek();
                /* 如果当前节点为叶子节点或者当前节点的子节点已经遍历过了 */
                if (node.children.size() == 0 || visited.contains(node)) {
                    stack.pop();
                    postorderList.add(node.val);
                    continue;
                }
                // 从右往左往栈中添加节点保证是后序
                for (int i = node.children.size() - 1; i >= 0; i--) {
                    stack.push(node.children.get(i));
                }
                // 表示该节点的子节点已经添加进栈了
                visited.add(node);
            }
        }
        return postorderList;
    }

    private void dfs(Node root, List<Integer> postorderList) {
        if (Objects.nonNull(root)) {
            for (Node node : root.children) {
                dfs(node, postorderList);
            }
            postorderList.add(root.val);
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
