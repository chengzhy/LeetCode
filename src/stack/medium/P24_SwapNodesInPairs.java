package stack.medium;

/**
 * 两两交换链表中的节点
 * <a href="https://leetcode.cn/problems/swap-nodes-in-pairs/">🔗</a>
 *
 * 给定一个链表，两两交换其中相邻的节点，并返回交换后的链表。
 *
 * 你不能只是单纯的改变节点内部的值，而是需要实际的进行节点交换。
 *
 *  
 *
 * 示例 1：
 *
 *
 * 输入：head = [1,2,3,4]
 * 输出：[2,1,4,3]
 * 示例 2：
 *
 * 输入：head = []
 * 输出：[]
 * 示例 3：
 *
 * 输入：head = [1]
 * 输出：[1]
 *  
 *
 * 提示：
 *
 * 链表中节点的数目在范围 [0, 100] 内
 * 0 <= Node.val <= 100
 *  
 *
 * 进阶：你能在不修改链表节点值的情况下解决这个问题吗?（也就是说，仅修改节点本身。）
 *
 * @author chengzhy
 * @date 2022/1/27 16:00
 **/
public class P24_SwapNodesInPairs {

    public int total = 0;

    public ListNode swapPairs(ListNode head) {
        if (head == null) return null;
        ListNode p = head;
        total++;
        if (p.next != null) {
            p.next = swapPairs(p.next);
            if (total%2!=0) {
                ListNode temp = p.next;
                p.next = temp.next;
                temp.next = p;
                p = temp;
            }
        }
        total--;
        return p;
    }

    public static class ListNode {
        int val;
        ListNode next;
        ListNode() {}
        ListNode(int val) { this.val = val; }
        ListNode(int val, ListNode next) { this.val = val; this.next = next; }
    }

}
