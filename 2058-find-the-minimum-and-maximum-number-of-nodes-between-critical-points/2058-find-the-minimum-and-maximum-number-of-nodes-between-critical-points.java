class Solution {
    public int[] nodesBetweenCriticalPoints(ListNode head) {
        int[] ans = {-1, -1};

        if (head == null || head.next == null || head.next.next == null) {
            return ans;
        }

        ListNode prev = head;
        ListNode curr = head.next;

        int index = 1;
        int first = -1;
        int last = -1;
        int minDist = Integer.MAX_VALUE;

        while (curr.next != null) {
            // Check if curr is a local maximum or minimum
            if ((curr.val > prev.val && curr.val > curr.next.val) ||
                (curr.val < prev.val && curr.val < curr.next.val)) {

                if (first == -1) {
                    // First critical point
                    first = index;
                } else {
                    // Distance from previous critical point
                    minDist = Math.min(minDist, index - last);
                }

                // Update latest critical point
                last = index;
            }

            prev = curr;
            curr = curr.next;
            index++;
        }

        // Fewer than 2 critical points
        if (first == -1 || first == last) {
            return ans;
        }

        int maxDist = last - first;

        return new int[]{minDist, maxDist};
    }
}