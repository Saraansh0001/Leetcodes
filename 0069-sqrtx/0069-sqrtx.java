class Solution {
    public int mySqrt(int x) {
        if (x == 0 || x == 1)
            return x;

        int lo = 1, hi = x, ans = 0;

        while (lo <= hi) {
            int mid = lo + (hi - lo) / 2;
            long sq = 1L * mid * mid;

            if (sq == x )
                return mid;
            else if (sq < x) {
                ans = mid;
                lo = mid + 1;
            } else {
                hi = mid - 1;
            }
        }

        return ans;

    }
}