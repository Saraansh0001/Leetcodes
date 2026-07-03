class Solution {
    public int arrangeCoins(int n) {

        int lo = 1;
        int hi = n;
        int ans = 0;

        while (lo <= hi) {
            int mid = lo + (hi - lo) / 2;

            long coins = (long) mid * (mid + 1) / 2;

            if (coins == n) return mid;
            else if (coins < n) {
                ans = mid;
                lo = mid + 1;
            } else {
                hi = mid - 1;
            }
        }

        return ans;
    }
}