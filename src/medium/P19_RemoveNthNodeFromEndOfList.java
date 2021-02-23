package medium;

/**
 * @description 删除链表的倒数第 N 个结点
 *
 * 给你一个链表，删除链表的倒数第 n 个结点，并且返回链表的头结点。
 *
 * 进阶：你能尝试使用一趟扫描实现吗？
 *
 *  
 *
 * 示例 1：
 *
 *
 * 输入：head = [1,2,3,4,5], n = 2
 * 输出：[1,2,3,5]
 * 示例 2：
 *
 * 输入：head = [1], n = 1
 * 输出：[]
 * 示例 3：
 *
 * 输入：head = [1,2], n = 1
 * 输出：[1]
 *  
 *
 * 提示：
 *
 * 链表中结点的数目为 sz
 * 1 <= sz <= 30
 * 0 <= Node.val <= 100
 * 1 <= n <= sz
 *
 **/
public class P19_RemoveNthNodeFromEndOfList {

    // 总数
    public int total = 0;
    // 计数
    public int count = 0;
    // 临时指针
    public ListNode p;

    public ListNode removeNthFromEnd(ListNode head, int n) {
        /**
         * 0ms
         */
        /*ListNode temp = head;
        total++;
        if (temp.next != null) {
            temp.next = removeNthFromEnd(temp.next, n);
            count++;
            if (count == n) {
                // 倒数第n个，临时指针指向倒数第n-1个，倒数第n个next置为null
                p = temp.next;
                temp.next = null;
            } else if (count == n+1) {
                // 倒数第n+1个指向临时指针
                temp.next = p;
            }
        } else {
            // 尾指针，开始计数
            count = 1;
        }
        // 倒数第n个为正数第1个时，返回临时指针
        if (total == count && total == n) {
            temp = p;
        }
        return temp;*/

        /**
         * 1ms
         */
        ListNode temp = head;
        total++;
        if (temp.next != null) {
            temp.next = removeNthFromEnd(temp.next, n);
            count++;
            if (count == n+1) {
                // 倒数第n+1个指向临时指针
                temp.next = temp.next.next;
            }
        } else {
            // 尾指针，开始计数
            count = 1;
        }
        // 倒数第n个为正数第1个时，返回临时指针
        if (total == count && total == n) {
            temp = temp.next;
        }
        return temp;
    }

}

class ListNode {
    int val;
    ListNode next;
    ListNode() {}
    ListNode(int val) { this.val = val; }
    ListNode(int val, ListNode next) { this.val = val; this.next = next; }
}
