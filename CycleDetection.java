/**
 * Definition for singly-linked list.
 * class ListNode {
 *     public int val;
 *     public ListNode next;
 *     ListNode(int x) { val = x; next = null; }
 * }
 */
public class Solution {
    public ListNode detectCycle(ListNode a) {
        
        ListNode head = a;
        ListNode slow = head, fast = head;
        ListNode loop = head;
        boolean hasCycle = false;
        while(slow!=null && fast!=null && fast.next!=null)
        {
            slow = slow.next;
            fast = fast.next.next;
            if(slow == fast)
            {
               loop = slow;
               hasCycle = true;
               break;
            }
        }
        if(!hasCycle)
            return null;
        ListNode ptr1 = loop, ptr2 = loop;
        int k = 1, i;
        while (ptr1.next != ptr2) {
            ptr1 = ptr1.next;
            k++;
        }
        ptr1 = head;
        ptr2 = head;
        for (i = 0; i < k; i++) {
            ptr2 = ptr2.next;
        }
        while (ptr2 != ptr1) {
            ptr1 = ptr1.next;
            ptr2 = ptr2.next;
        }
        return ptr2;
    }
}
