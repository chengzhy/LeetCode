package two_pointers.easy;

import java.util.Objects;

/**
 * 回文链表
 *
 * 给你一个单链表的头节点 head ，请你判断该链表是否为回文链表。如果是，返回 true ；否则，返回 false 。
 *
 *  
 *
 * 示例 1：
 *
 *
 * 输入：head = [1,2,2,1]
 * 输出：true
 * 示例 2：
 *
 *
 * 输入：head = [1,2]
 * 输出：false
 *  
 *
 * 提示：
 *
 * 链表中节点数目在范围[1, 105] 内
 * 0 <= Node.val <= 9
 *  
 *
 * 进阶：你能否用 O(n) 时间复杂度和 O(1) 空间复杂度解决此题？
 *
 * @author chengzhy
 * @date 2022/1/29 15:17
 */
public class P234_PalindromeLinkedList {

    public boolean isPalindrome(ListNode head) {
        // 快慢指针找到中间节点
        ListNode slow = head, fast = head;
        while (Objects.nonNull(fast.next) && Objects.nonNull(fast.next.next)) {
            fast = fast.next.next;
            slow = slow.next;
        }
        // 反转后半部分链表
        slow = reverseList(slow.next);
        // 判断是否为回文
        while (Objects.nonNull(slow)) {
            if (head.val != slow.val) {
                return false;
            }
            head = head.next;
            slow = slow.next;
        }
        return true;
    }

    /**
     * 题206 反转链表
     *
     * @param head 链表头节点
     * @return 反转后的链表头节点
     */
    private ListNode reverseList(ListNode head) {
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
    }

    public static class ListNode {
        int val;
        ListNode next;
        ListNode() {}
        ListNode(int val) {
            this.val = val;
        }
        ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }
    }

}
