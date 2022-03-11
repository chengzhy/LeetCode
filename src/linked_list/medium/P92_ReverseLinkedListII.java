package linked_list.medium;

import java.util.Objects;

/**
 * 反转链表 II
 *
 * 给你单链表的头指针 head 和两个整数 left 和 right ，其中 left <= right 。请你反转从位置 left 到位置 right 的链表节点，返回 反转后的链表 。
 *  
 *
 * 示例 1：
 *
 *
 * 输入：head = [1,2,3,4,5], left = 2, right = 4
 * 输出：[1,4,3,2,5]
 * 示例 2：
 *
 * 输入：head = [5], left = 1, right = 1
 * 输出：[5]
 *  
 *
 * 提示：
 *
 * 链表中节点数目为 n
 * 1 <= n <= 500
 * -500 <= Node.val <= 500
 * 1 <= left <= right <= n
 *  
 *
 * 进阶： 你可以使用一趟扫描完成反转吗？
 *
 * @author chengzhy
 * @date 2022/3/11 13:59
 */
public class P92_ReverseLinkedListII {

    public ListNode reverseBetween(ListNode head, int left, int right) {
        // 重点在于设置一个虚拟节点防止边界值问题
        ListNode fakeNode = new ListNode(-1), start = fakeNode;
        fakeNode.next = head;
        // start节点走到要变换区间的前一个节点
        for (int i = 1; i < left; i++) {
            start = start.next;
        }
        if (Objects.nonNull(start) && Objects.nonNull(start.next)) {
            ListNode pre = start.next, p = pre.next;
            // count用来计数保证变换的节点在区间范围内
            int count = 2;
            // 采用题206的变化步骤
            while (Objects.nonNull(p) && count++ <= right - left + 1) {
                pre.next = p.next;
                p.next = start.next;
                start.next = p;
                p = pre.next;
            }
        }
        return fakeNode.next;
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
