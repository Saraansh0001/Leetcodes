class Solution {
    public int uniqueXorTriplets(int[] nums) {
        int max = 0;
        for (int x : nums) max = Math.max(max, x);

        int LIM = 1;
        while (LIM <= max) LIM <<= 1;

        boolean[][] dp = new boolean[4][LIM];
        dp[0][0] = true;

        // Unlimited reuse of every value
        for (int k = 1; k <= 3; k++) {
            for (int x = 0; x < LIM; x++) {
                if (!dp[k - 1][x]) continue;
                for (int v : nums) {
                    dp[k][x ^ v] = true;
                }
            }
        }

        int ans = 0;
        for (boolean b : dp[3]) {
            if (b) ans++;
        }
        return ans;
    }
}