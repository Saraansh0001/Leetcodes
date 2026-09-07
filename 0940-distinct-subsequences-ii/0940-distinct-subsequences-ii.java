class Solution {
    public int distinctSubseqII(String s) {

        int MOD = 1_000_000_007;
        int n = s.length();

        // dp[i] = number of distinct subsequences
        // including empty subsequence using first i characters
        long[] dp = new long[n + 1];

        dp[0] = 1;

        // last[c] = dp value before the previous occurrence of c
        long[] last = new long[26];

        for (int i = 1; i <= n; i++) {

            char ch = s.charAt(i - 1);
            int c = ch - 'a';

            dp[i] = (2 * dp[i - 1]) % MOD;

            // Remove duplicates caused by previous occurrence of ch
            dp[i] = (dp[i] - last[c] + MOD) % MOD;

            // For future occurrences of this character
            last[c] = dp[i - 1];
        }

        // Remove empty subsequence
        return (int)((dp[n] - 1 + MOD) % MOD);
    }
}