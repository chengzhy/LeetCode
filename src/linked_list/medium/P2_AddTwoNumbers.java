package linked_list.medium;

/**
 * 两数相加
 * <a href="https://leetcode-cn.com/problems/add-two-numbers/">🔗</a>
 *
 * You are given two non-empty linked lists representing two non-negative integers. The digits are stored in reverse order and each of their nodes contain a single digit. Add the two numbers and return it as a linked list.
 *
 * You may assume the two numbers do not contain any leading zero, except the number 0 itself.
 *
 * Example:
 *
 * Input: (2 -> 4 -> 3) + (5 -> 6 -> 4)
 * Output: 7 -> 0 -> 8
 * Explanation: 342 + 465 = 807.
 *
 * 给出两个 非空 的链表用来表示两个非负的整数。其中，它们各自的位数是按照 逆序 的方式存储的，并且它们的每个节点只能存储 一位 数字。
 * 如果，我们将这两个数相加起来，则会返回一个新的链表来表示它们的和。
 */
/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 *
 * @author chengzhy
 * @date 2022/1/27 16:00
 */
public class P2_AddTwoNumbers {

    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode result = new ListNode(0);
        ListNode temp = result;
        int a = 0;
        while (l1 != null && l2 != null){
            int sum = l1.val + l2.val + a;
            a = 0;
            if (sum > 9){
                temp.val = sum%10;
                a = sum/10;
            } else{
                temp.val = sum;
            }
            l1 = l1.next;
            l2 = l2.next;
            if (l1 != null && l2 != null){
                temp.next = new ListNode(0);
                temp = temp.next;
            }
        }
        while (l1 != null){
            int sum = l1.val + a;
            if (sum > 9){
                temp.next = new ListNode(sum%10);
                temp = temp.next;
                a = sum/10;
            } else{
                temp.next = new ListNode(sum);
                temp = temp.next;
                a = 0;
            }
            l1 = l1.next;
        }
        while (l2 != null){
            int sum = l2.val + a;
            if (sum > 9){
                temp.next = new ListNode(sum%10);
                temp = temp.next;
                a = sum/10;
            } else{
                temp.next = new ListNode(sum);
                temp = temp.next;
                a = 0;
            }
            l2 = l2.next;
        }
        if (a > 0){
            temp.next = new ListNode(a);
        }
        return result;
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
