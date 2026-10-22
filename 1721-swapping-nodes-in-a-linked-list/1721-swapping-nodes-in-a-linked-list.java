class Solution {
    public ListNode swapNodes(ListNode head, int k) {

        // Find the kth node from the beginning
        ListNode first = head;

        for (int i = 1; i < k; i++) {
            first = first.next;
        }

        // Find the kth node from the end
        ListNode fast = first;
        ListNode second = head;

        while (fast.next != null) {
            fast = fast.next;
            second = second.next;
        }

        // Swap their values
        int temp = first.val;
        first.val = second.val;
        second.val = temp;

        return head;
    }
}