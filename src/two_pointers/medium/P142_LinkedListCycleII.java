package two_pointers.medium;

import java.util.Objects;

/**
 * 环形链表 II
 * <a href="https://leetcode.cn/problems/linked-list-cycle-ii/">🔗</a>
 *
 * 给定一个链表，返回链表开始入环的第一个节点。 如果链表无环，则返回 null。
 *
 * 为了表示给定链表中的环，我们使用整数 pos 来表示链表尾连接到链表中的位置（索引从 0 开始）。 如果 pos 是 -1，则在该链表中没有环。注意，pos 仅仅是用于标识环的情况，并不会作为参数传递到函数中。
 *
 * 说明：不允许修改给定的链表。
 *
 * 进阶：
 *
 * 你是否可以使用 O(1) 空间解决此题？
 *  
 *
 * 示例 1：
 *
 *
 *
 * 输入：head = [3,2,0,-4], pos = 1
 * 输出：返回索引为 1 的链表节点
 * 解释：链表中有一个环，其尾部连接到第二个节点。
 * 示例 2：
 *
 *
 *
 * 输入：head = [1,2], pos = 0
 * 输出：返回索引为 0 的链表节点
 * 解释：链表中有一个环，其尾部连接到第一个节点。
 * 示例 3：
 *
 *
 *
 * 输入：head = [1], pos = -1
 * 输出：返回 null
 * 解释：链表中没有环。
 *  
 *
 * 提示：
 *
 * 链表中节点的数目范围在范围 [0, 104] 内
 * -105 <= Node.val <= 105
 * pos 的值为 -1 或者链表中的一个有效索引
 *
 * @author chengzhy
 * @date 2021/11/11 10:18
 */
public class P142_LinkedListCycleII {

    public ListNode detectCycle(ListNode head) {
        /**
         * 慢
         */
        /*if (Objects.isNull(head) || Objects.isNull(head.next)) {
            return null;
        }
        ListNode p = head;
        Set<Integer> set = new HashSet<>();
        while (true) {
            if (Objects.isNull(p)) {
                return null;
            }
            if (set.contains(p.hashCode())) {
                return p;
            } else {
                set.add(p.hashCode());
                p = p.next;
            }
        }*/

        /**
         * 数学题
         * 设head-->第一个环节点距离为a，已知快慢指针必在环内相遇，设第一个环节点-->相遇节点的距离为b
         * 则慢指针走过的距离为a+b，快指针走过的距离为2(a+b)，由于是环，因此快指针比慢指针多走a+b的距离
         * 因为第一个环节点-->相遇节点的距离为b，所以相遇节点-->第一换节点的距离为a
         * 由此得出，慢指针再走a距离就会到达第一个环节点，而已设head-->第一个环节点距离为a
         * 因此当快慢指针相遇后，设一指针从head节点开始与慢指针一起走，当两个相遇时相遇的节点一定是第一个环节点
         * 画图更加清晰
         */
        if (Objects.isNull(head) || Objects.isNull(head.next)) {
            return null;
        }
        ListNode slow = head, fast = slow;
        while (Objects.nonNull(fast)) {
            if (Objects.isNull(fast.next)) {
                return null;
            }
            fast = fast.next.next;
            slow = slow.next;
            if (fast == slow) {
                ListNode p = head;
                while (p != slow) {
                    p = p.next;
                    slow = slow.next;
                }
                return p;
            }
        }
        return null;
    }

    public static class ListNode {
        int val;
        ListNode next;
        ListNode(int val) {
            this.val = val;
            next = null;
        }
    }

}
