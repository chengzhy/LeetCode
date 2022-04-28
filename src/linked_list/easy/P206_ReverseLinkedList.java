package linked_list.easy;

import java.util.Objects;

/**
 * 反转链表
 * <a href="https://leetcode.cn/problems/reverse-linked-list/">🔗</a>
 *
 * 给你单链表的头节点 head ，请你反转链表，并返回反转后的链表。
 *  
 *
 * 示例 1：
 *
 *
 * 输入：head = [1,2,3,4,5]
 * 输出：[5,4,3,2,1]
 * 示例 2：
 *
 *
 * 输入：head = [1,2]
 * 输出：[2,1]
 * 示例 3：
 *
 * 输入：head = []
 * 输出：[]
 *  
 *
 * 提示：
 *
 * 链表中节点的数目范围是 [0, 5000]
 * -5000 <= Node.val <= 5000
 *  
 *
 * 进阶：链表可以选用迭代或递归方式完成反转。你能否用两种方法解决这道题？
 *
 * @author chengzhy
 * @date 2021/12/10 16:43
 */
public class P206_ReverseLinkedList {

    public ListNode reverseList(ListNode head) {
        /**
         * 迭代法
         */
        if (Objects.nonNull(head) && Objects.nonNull(head.next)) {
            ListNode pre = head, p = head.next;
            while (Objects.nonNull(p)) {
                pre.next = p.next;
                p.next = head;
                head = p;
                p = pre.next;
            }
        }
        return head;

        /**
         * 递归法(参考官方解答)
         * 比较巧妙，图解如下
         * 5→4→3→null
         *       ↑
         *       2
         *       ↑
         *       1
         *       ↑
         *    newHead
         */
        /*if (head == null || head.next == null) {
            return head;
        }
        ListNode newHead = reverseList(head.next);
        head.next.next = head;
        head.next = null;
        return newHead;*/
    }

    public static class ListNode {
        int val;
        ListNode next;
        ListNode() {}
        ListNode(int val) { this.val = val; }
        ListNode(int val, ListNode next) { this.val = val; this.next = next; }
    }

}
