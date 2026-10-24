import java.util.*;

class Solution {
    public ListNode removeNodes(ListNode head) {
        Stack<ListNode> st = new Stack<>();

        ListNode temp = head;

        while (temp != null) {
            while (!st.isEmpty() && st.peek().val < temp.val) {
                st.pop();
            }

            st.push(temp);
            temp = temp.next;
        }

        ListNode dummy = new ListNode(0);
        ListNode curr = dummy;

        for (ListNode node : st) {
            curr.next = node;
            curr = curr.next;
        }

        curr.next = null;

        return dummy.next;
    }
}
