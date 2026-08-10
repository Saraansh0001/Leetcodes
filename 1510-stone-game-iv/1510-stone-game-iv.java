class Solution {
    public boolean winnerSquareGame(int n) {
        boolean[] dp = new boolean[n + 1];

        // dp[i] = true  -> current player can win with i stones
        // dp[i] = false -> current player loses with i stones

        for (int i = 1; i <= n; i++) {
            for (int j = 1; j * j <= i; j++) {
                // Remove j*j stones.
                // If the opponent loses from the remaining stones,
                // current player wins.
                if (!dp[i - j * j]) {
                    dp[i] = true;
                    break;
                }
            }
        }

        return dp[n];
    }
}