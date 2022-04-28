package depth_first_search.easy;

import java.util.Objects;

/**
 * 根据二叉树创建字符串
 * <a href="https://leetcode.cn/problems/construct-string-from-binary-tree/">🔗</a>
 *
 * 你需要采用前序遍历的方式，将一个二叉树转换成一个由括号和整数组成的字符串。
 *
 * 空节点则用一对空括号 "()" 表示。而且你需要省略所有不影响字符串与原始二叉树之间的一对一映射关系的空括号对。
 *
 * 示例 1:
 *
 * 输入: 二叉树: [1,2,3,4]
 *        1
 *      /   \
 *     2     3
 *    /
 *   4
 *
 * 输出: "1(2(4))(3)"
 *
 * 解释: 原本将是“1(2(4)())(3())”，
 * 在你省略所有不必要的空括号对之后，
 * 它将是“1(2(4))(3)”。
 * 示例 2:
 *
 * 输入: 二叉树: [1,2,3,null,4]
 *        1
 *      /   \
 *     2     3
 *      \
 *       4
 *
 * 输出: "1(2()(4))(3)"
 *
 * 解释: 和第一个示例相似，
 * 除了我们不能省略第一个对括号来中断输入和输出之间的一对一映射关系。
 *
 * @author chengzhy
 * @date 2022/3/21 11:10
 */
public class P606_ConstructStringFromBinaryTree {

    public String tree2str(TreeNode root) {
        StringBuilder sb = new StringBuilder();
        dfs(root, sb);
        return sb.substring(1, sb.length() - 1);
    }

    private void dfs(TreeNode root, StringBuilder sb) {
        sb.append("(").append(root.val);
        if (Objects.nonNull(root.left)) {
            dfs(root.left, sb);
        } else if (Objects.nonNull(root.right)) {
            // 左子树为空右子树不为空，则需要添加"()"
            sb.append("()");
        }
        if (Objects.nonNull(root.right)) {
            dfs(root.right, sb);
        }
        sb.append(")");
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
