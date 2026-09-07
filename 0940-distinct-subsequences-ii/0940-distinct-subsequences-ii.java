class Solution {
    public int distinctSubseqII(String s) {

        int MOD = 1_000_000_007;
        int n = s.length();

        long[] dp = new long[n + 1];

        dp[0] = 1;

        long[] last = new long[26];

        for (int i = 1; i <= n; i++) {

            char ch = s.charAt(i - 1);
            int c = ch - 'a';

            dp[i] = (2 * dp[i - 1]) % MOD;

            dp[i] = (dp[i] - last[c] + MOD) % MOD;

            last[c] = dp[i - 1];
        }

        return (int)((dp[n] - 1 + MOD) % MOD);
    }
}