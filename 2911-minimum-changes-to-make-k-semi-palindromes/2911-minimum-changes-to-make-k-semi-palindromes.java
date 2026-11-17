
class Solution {
    public int minimumChanges(String s, int k) {
        int n = s.length();

        int[][] cost = new int[n][n];

        // Step 1: Precompute costs for every substring
        for (int len = 2; len <= n; len++) {

            for (int i = 0; i + len <= n; i++) {

                int j = i + len - 1;
                cost[i][j] = Integer.MAX_VALUE;

                // Try every proper divisor
                for (int d = 1; d < len; d++) {

                    if (len % d != 0) {
                        continue;
                    }

                    int changes = 0;

                    // Check each group for palindrome
                    for (int start = 0; start < d; start++) {

                        int left = start;
                        int right = len - d + start;

                        while (left < right) {

                            if (s.charAt(i + left) !=
                                s.charAt(i + right)) {
                                changes++;
                            }

                            left += d;
                            right -= d;
                        }
                    }

                    cost[i][j] = Math.min(
                        cost[i][j], changes
                    );
                }
            }
        }

        // Step 2: DP
        int INF = 1000000000;

        int[][] dp = new int[n + 1][k + 1];

        for (int i = 0; i <= n; i++) {
            Arrays.fill(dp[i], INF);
        }

        dp[0][0] = 0;

        // Step 3: Try all partitions
        for (int parts = 1; parts <= k; parts++) {

            for (int i = 2 * parts; i <= n; i++) {

                for (int j = 2 * (parts - 1);
                     j <= i - 2; j++) {

                    dp[i][parts] = Math.min(
                        dp[i][parts],
                        dp[j][parts - 1] + cost[j][i - 1]
                    );
                }
            }
        }

        return dp[n][k];
    }
}
