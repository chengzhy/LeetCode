/**
 * You are given two non-empty linked lists representing two non-negative integers. The digits are stored in reverse order and each of their nodes contain a single digit. Add the two numbers and return it as a linked list.
 *
 * You may assume the two numbers do not contain any leading zero, except the number 0 itself.
 *
 * Example:
 *
 * Input: (2 -> 4 -> 3) + (5 -> 6 -> 4)
 * Output: 7 -> 0 -> 8
 * Explanation: 342 + 465 = 807.
 */
/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
public class AddTwoNumbers {
    int val;
    AddTwoNumbers next;
    AddTwoNumbers(int x) { val = x; }

    public static void main(String[] args) {
        AddTwoNumbers l1 = new AddTwoNumbers(1);
        /*l1.next = new AddTwoNumbers(4);
        l1.next.next = new AddTwoNumbers(3);*/
        AddTwoNumbers l2 = new AddTwoNumbers(9);
        l2.next = new AddTwoNumbers(9);
//        l2.next.next = new AddTwoNumbers(4);
        Solution solution = new Solution();
        AddTwoNumbers result = solution.addTwoNumbers(l1, l2);
    }
}
class Solution {
    public AddTwoNumbers addTwoNumbers(AddTwoNumbers l1, AddTwoNumbers l2) {
        AddTwoNumbers result = new AddTwoNumbers(0);
        AddTwoNumbers temp = result;
        int a = 0;
        while (l1 != null && l2 != null){
            int sum = l1.val + l2.val + a;
            a = 0;
            if (sum > 9){
                temp.val = sum%10;
                a = sum/10;
            }else temp.val = sum;
            l1 = l1.next;
            l2 = l2.next;
            if (l1 != null && l2 != null){
                temp.next = new AddTwoNumbers(0);
                temp = temp.next;
            }
        }
        while (l1 != null){
            int sum = l1.val + a;
            if (sum > 9){
                temp.next = new AddTwoNumbers(sum%10);
                temp = temp.next;
                a = sum/10;
            }else{
                temp.next = new AddTwoNumbers(sum);
                temp = temp.next;
                a = 0;
            }
            l1 = l1.next;
        }
        while (l2 != null){
            int sum = l2.val + a;
            if (sum > 9){
                temp.next = new AddTwoNumbers(sum%10);
                temp = temp.next;
                a = sum/10;
            }else{
                temp.next = new AddTwoNumbers(sum);
                temp = temp.next;
                a = 0;
            }
            l2 = l2.next;
        }
        if (a > 0){
            temp.next = new AddTwoNumbers(a);
        }
        return result;
    }
}
