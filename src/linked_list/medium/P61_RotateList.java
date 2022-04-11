package linked_list.medium;

import java.util.Objects;

/**
 * 旋转链表
 * <a href="https://leetcode-cn.com/problems/rotate-list/">🔗</a>
 *
 * 给你一个链表的头节点 head ，旋转链表，将链表每个节点向右移动 k 个位置。
 *
 *  
 *
 * 示例 1：
 *
 *
 * 输入：head = [1,2,3,4,5], k = 2
 * 输出：[4,5,1,2,3]
 * 示例 2：
 *
 *
 * 输入：head = [0,1,2], k = 4
 * 输出：[2,0,1]
 *  
 *
 * 提示：
 *
 * 链表中节点的数目在范围 [0, 500] 内
 * -100 <= Node.val <= 100
 * 0 <= k <= 2 * 109
 *
 * @author chengzhy
 * @date 2022/1/24 18:49
 */
public class P61_RotateList {

    public ListNode rotateRight(ListNode head, int k) {
        if (Objects.nonNull(head)) {
            int length = 1, step;
            // 尾指针
            ListNode tail = head;
            while (Objects.nonNull(tail.next)) {
                length++;
                tail = tail.next;
            }
            // 算实际移动步数
            step = k % length;
            if (step != 0) {
                ListNode p = head;
                // 找到移动后的尾节点
                for (int i = 0; i < length - step - 1; i++) {
                    p = p.next;
                }
                // 尾指针指向head
                tail.next = head;
                // head指向新的头节点
                head = p.next;
                // 尾节点置空
                p.next = null;
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
