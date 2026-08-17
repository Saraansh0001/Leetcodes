class Solution {

    int[][] dp;
    int[] prefix;

    public int stoneGameV(int[] stoneValue) {

        int n = stoneValue.length;

        dp = new int[n][n];
        prefix = new int[n + 1];

        // Prefix sum
        for (int i = 0; i < n; i++) {
            prefix[i + 1] = prefix[i] + stoneValue[i];
        }

        return solve(stoneValue, 0, n - 1);
    }

    int solve(int[] stones, int i, int j) {

        // Only one stone
        if (i == j) {
            return 0;
        }

        // Already calculated
        if (dp[i][j] != 0) {
            return dp[i][j];
        }

        int ans = 0;

        for (int k = i; k < j; k++) {

            // Sum of left part
            int left = prefix[k + 1] - prefix[i];

            // Sum of right part
            int right = prefix[j + 1] - prefix[k + 1];

            if (left < right) {

                ans = Math.max(
                    ans,
                    left + solve(stones, i, k)
                );

            } else if (left > right) {

                ans = Math.max(
                    ans,
                    right + solve(stones, k + 1, j)
                );

            } else {

                ans = Math.max(
                    ans,
                    Math.max(
                        left + solve(stones, i, k),
                        right + solve(stones, k + 1, j)
                    )
                );
            }
        }

        return dp[i][j] = ans;
    }
}