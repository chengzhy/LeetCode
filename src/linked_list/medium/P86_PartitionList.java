package linked_list.medium;

import java.util.Objects;

/**
 * 分隔链表
 *
 * 给你一个链表的头节点 head 和一个特定值 x ，请你对链表进行分隔，使得所有 小于 x 的节点都出现在 大于或等于 x 的节点之前。
 *
 * 你应当 保留 两个分区中每个节点的初始相对位置。
 *
 *  
 *
 * 示例 1：
 *
 *
 * 输入：head = [1,4,3,2,5,2], x = 3
 * 输出：[1,2,2,4,3,5]
 * 示例 2：
 *
 * 输入：head = [2,1], x = 2
 * 输出：[1,2]
 *  
 *
 * 提示：
 *
 * 链表中节点的数目在范围 [0, 200] 内
 * -100 <= Node.val <= 100
 * -200 <= x <= 200
 *
 * @author chengzhy
 * @date 2021/11/9 9:42
 */
public class P86_PartitionList {

    public ListNode partition(ListNode head, int x) {
        // pre前继指针 p当前指针 partition分隔点指针
        ListNode pre = head, p = (pre == null) ? null : pre.next, partition = head;
        while (Objects.nonNull(p)) {
            if (pre.val>=x && p.val<x) {
                if (head.val < x) {
                    // 正常情况
                    p = p.next;
                    pre.next.next = partition.next;
                    partition.next = pre.next;
                    partition = partition.next;
                    pre.next = p;
                } else {
                    // 5 4 3 2 1 x=2情况
                    head = p;
                    p = p.next;
                    pre.next.next = partition;
                    pre.next = p;
                    partition = head;
                }
            } else {
                if (pre.val<x && p.val<x) {
                    // 2 0 4 1 3 x=4情况
                    partition = p;
                }
                pre = p;
                p = p.next;
            }
        }
        return head;
    }

    public static class ListNode {
        int val;
        ListNode next;
        ListNode() {}
        ListNode(int val) { this.val = val; }
        ListNode(int val, ListNode next) { this.val = val; this.next = next; }
    }

}
