class Solution {
    public ListNode removeNthFromEnd(ListNode head, int n) {

        int size = 0;
        ListNode temp = head;

        while (temp != null) {
            temp = temp.next;
            size++;
        }

        int kthFromEnd = size - n - 1;

        if (kthFromEnd == -1) {
            return head.next;
        }

        temp = head;

        for (int i = 0; i < kthFromEnd; i++) {
            temp = temp.next;
        }

        temp.next = temp.next.next;

        return head;
    }
}