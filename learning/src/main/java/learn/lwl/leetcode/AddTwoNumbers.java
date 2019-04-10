package learn.lwl.leetcode;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * Created with IDEA
 * author:wenleili@sohu-inc.com
 * Date:2019/1/27
 * Time:16:58
 **/
public class AddTwoNumbers {
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode num1 = l1;
        ListNode num2 = l2;
        ListNode head = new ListNode(0);
        ListNode current = head;
        int add = 0;
        while (num1 != null || num2 != null) {
            int tmp = (num1 != null ? num1.val : 0)
                    + (num2 != null ? num2.val : 0)
                    + add;
            add = tmp / 10;
            ListNode node = new ListNode(tmp % 10);
            current.next = node;
            current = current.next;
            num1 = num1 != null ? num1.next : null;
            num2 = num2 != null ? num2.next : null;

        }
        if (add > 0) {
            current.next = new ListNode(add);
        }
        return head.next;
    }

    public ListNode reverseNode(ListNode head) {
        if (null == head) {
            return null;
        }
        ListNode pre = head;
        ListNode current = head.next;
        pre.next = null;
        while (current != null) {
            ListNode next = current.next;
            current.next = pre;
            pre = current;
            current = next;
        }
        return pre;

    }

    public ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode first = dummy;
        ListNode second = dummy;
        for (int i = 1; i <= n + 1; i++) {
            first = first.next;
        }
        while (first != null) {
            first = first.next;
            second = second.next;
        }
        second.next = second.next.next;
        return dummy.next;
    }

    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        ListNode left = l1;
        ListNode right = l2;
        ListNode dummy = new ListNode(-1);
        ListNode cur = dummy;
        while (left != null && right != null) {
            if (right.val < left.val) {
                cur.next = right;
                right = right.next;
            } else {
                cur.next = left;
                left = left.next;
            }
            cur = cur.next;
        }
        if (left != null) {
            cur.next = left;
        }
        if (right != null) {
            cur.next = right;
        }
        return dummy.next;
    }

    public ListNode mergeKLists(ListNode[] lists) {
        ListNode dummy = new ListNode(-1);
        Map<Integer, ListNode> tmp = new HashMap<>();
        int index = 0;
        for (ListNode node : lists) {
            tmp.put(index++, node);
        }
        while (tmp.size() > 1) {
            int max = Integer.MIN_VALUE;
            int maxIndex = 0;
            Set<Integer> keys = tmp.keySet();
            for (Integer i : keys) {
                if (tmp.get(i).val > max) {
                    max = tmp.get(i).val;
                    maxIndex = i;
                }
            }


        }
        return dummy.next;

    }

    public static void main(String[] args) {
        ListNode num1 = new ListNode(2);
        ListNode num2 = new ListNode(3);
        ListNode num3 = new ListNode(6);
        num3.next = null;
        num2.next = num3;
        num1.next = num2;
        ListNode num4 = new ListNode(4);
        ListNode num5 = new ListNode(7);
        ListNode num6 = new ListNode(9);
        num4.next = num5;
        num5.next = num6;
        num6.next = null;
        AddTwoNumbers solution = new AddTwoNumbers();
//        ListNode node = solution.addTwoNumbers(num1, num4);
//        System.out.println(node);
//        ListNode del = solution.removeNthFromEnd(num3, 1);
//        System.out.println(del);
        System.out.println(solution.mergeTwoLists(num1, num4));
    }
}
