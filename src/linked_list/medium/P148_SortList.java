package linked_list.medium;

import java.util.Objects;

/**
 * 排序链表
 * <a href="https://leetcode.cn/problems/sort-list/">🔗</a>
 *
 * 给你链表的头结点 head ，请将其按 升序 排列并返回 排序后的链表 。
 *
 * 进阶：
 *
 * 你可以在 O(n log n) 时间复杂度和常数级空间复杂度下，对链表进行排序吗？
 *  
 *
 * 示例 1：
 *
 *
 * 输入：head = [4,2,1,3]
 * 输出：[1,2,3,4]
 * 示例 2：
 *
 *
 * 输入：head = [-1,5,3,4,0]
 * 输出：[-1,0,3,4,5]
 * 示例 3：
 *
 * 输入：head = []
 * 输出：[]
 *  
 *
 * 提示：
 *
 * 链表中节点的数目在范围 [0, 5 * 104] 内
 * -105 <= Node.val <= 105
 *
 * @author chengzhy
 * @date 2021/12/13 17:22
 */
public class P148_SortList {

    public ListNode sortList(ListNode head) {
        return mergeSort(head);
    }

    /**
     * 归并排序(参考评论)
     *
     * @param head 某指针
     * @return 排序后的某段指针头节点
     */
    private ListNode mergeSort(ListNode head) {
        if (Objects.isNull(head) || Objects.isNull(head.next)) {
            return head;
        }
        ListNode slow = head, fast = head.next.next, left, right;
        // 通过快慢指针寻找中间点
        while (Objects.nonNull(fast) && Objects.nonNull(fast.next)) {
            slow = slow.next;
            fast = fast.next.next;
        }
        // 排序右半部节点
        right = mergeSort(slow.next);
        // 为了能排序左半部节点，将中间指针的next置为null
        slow.next = null;
        left = mergeSort(head);
        // 合并两段指针将其变成有序
        return merge(left, right);
    }

    /**
     * 将两端指针合并排序
     *
     * @param left 指针
     * @param right 指针
     * @return 排序合并后的头指针
     */
    private ListNode merge(ListNode left, ListNode right) {
        ListNode tempHead = new ListNode(-1), p = tempHead;
        while (Objects.nonNull(left) && Objects.nonNull(right)) {
            if (left.val < right.val) {
                p.next = left;
                left = left.next;
            } else {
                p.next = right;
                right = right.next;
            }
            p = p.next;
        }
        p.next = Objects.isNull(left) ? right : left;
        return tempHead.next;
    }

    public static class ListNode{
        int val;
        ListNode next;
        ListNode() {}
        ListNode(int val) { this.val = val; }
        ListNode(int val, ListNode next) { this.val = val; this.next = next; }
    }

}
