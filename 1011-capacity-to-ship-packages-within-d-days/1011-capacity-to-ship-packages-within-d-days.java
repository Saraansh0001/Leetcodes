class Solution {
    public int shipWithinDays(int[] weights, int days) {

        int lo = 0;
        int hi = 0;
        int ans = 0;

        for (int num : weights) {
            hi += num;
            lo = Math.max(lo, num);
        }

        while (lo <= hi) {

            int mid = lo + (hi - lo) / 2;

            if (isPossible(mid, weights) <= days) {

                ans = mid;
                hi = mid - 1;

            } else {

                lo = mid + 1;
            }
        }

        return ans;
    }

    private int isPossible(int capacity, int[] nums) {

        int c = capacity;
        int day = 1;

        for (int num : nums) {

            if (capacity >= num) {

                capacity -= num;

            } else {

                day++;
                capacity = c - num;
            }
        }

        return day;
    }
}