class Solution {
    int[][] dp;
    int[] prefix;

    public int stoneGameII(int[] piles) {
        int n = piles.length;

        dp = new int[n][n + 1];
        prefix = new int[n + 1];

        // Suffix/prefix sum
        for (int i = 0; i < n; i++) {
            prefix[i + 1] = prefix[i] + piles[i];
        }

        return solve(piles, 0, 1);
    }

    private int solve(int[] piles, int i, int M) {
        int n = piles.length;

        if (i >= n) {
            return 0;
        }

        if (dp[i][M] != 0) {
            return dp[i][M];
        }

        int total = prefix[n] - prefix[i];
        int best = 0;

        // Take X piles, where 1 <= X <= 2*M
        for (int X = 1; X <= 2 * M && i + X <= n; X++) {

            // Stones Alice gets now
            int taken = prefix[i + X] - prefix[i];

            // Bob gets the best possible result from the remaining piles
            int bob = solve(piles, i + X, Math.max(M, X));

            // Alice wants to maximize her total
            best = Math.max(best, total - bob);
        }

        return dp[i][M] = best;
    }
}