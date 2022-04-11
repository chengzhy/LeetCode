package linked_list.hard;

/**
 * K 个一组翻转链表
 * <a href="https://leetcode-cn.com/problems/reverse-nodes-in-k-group/">🔗</a>
 *
 * 给你一个链表，每 k 个节点一组进行翻转，请你返回翻转后的链表。
 *
 * k 是一个正整数，它的值小于或等于链表的长度。
 *
 * 如果节点总数不是 k 的整数倍，那么请将最后剩余的节点保持原有顺序。
 *
 *  
 *
 * 示例：
 *
 * 给你这个链表：1->2->3->4->5
 *
 * 当 k = 2 时，应当返回: 2->1->4->3->5
 *
 * 当 k = 3 时，应当返回: 3->2->1->4->5
 *
 *  
 *
 * 说明：
 *
 * 你的算法只能使用常数的额外空间。
 * 你不能只是单纯的改变节点内部的值，而是需要实际进行节点交换。
 *
 * @author chengzhy
 * @date 2022/1/27 16:00
 **/
public class P25_ReverseNodesInKGroup {

    public ListNode reverseKGroup(ListNode head, int k) {
        /**
         * 计数
         */
        int count = 0;
        ListNode p = head;
        while (p != null) {
            p = p.next;
            count++;
        }

        /**
         * 翻转
         */
        // 数量小于k，直接返回head
        if (k > count) return head;
        // p指向头指针
        p = head;
        // p1职责：(1)一开始是翻转后的尾部指针 (2)后来是翻转后的头部指针
        // p2职责：翻转前的当前指针
        // p3职责：翻转前的当前指针的后一个指针
        ListNode p1 = null, p2 = head, p3 = head;
        for (int i=0; i<k; i++) {
            // p3指向p2的下一个指针
            p3 = p2.next;
            // p2指向p1
            p2.next = p1;
            // p1跳转到当前指针
            p1 = p2;
            // p2跳转到下一指针
            p2 = p3;
        }
        // p此时是翻转后的尾部指针
        // 继续翻转
        p.next = reverseKGroup(p2, k);
        return p1;
    }

    public static class ListNode {
        int val;
        ListNode next;
        ListNode() {}
        ListNode(int val) { this.val = val; }
        ListNode(int val, ListNode next) { this.val = val; this.next = next; }
    }

}
