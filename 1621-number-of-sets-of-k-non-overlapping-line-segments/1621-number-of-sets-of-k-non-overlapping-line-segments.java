class Solution {

    static final long MOD = 1000000007;

    public int numberOfSets(int n, int k) {

        int N = n + k - 1;
        int R = 2 * k;

        long[] fact = new long[N + 1];

        fact[0] = 1;

        for (int i = 1; i <= N; i++) {
            fact[i] = fact[i - 1] * i % MOD;
        }

        // C(N, R) = N! / (R! * (N-R)!)
        long ans = fact[N];

        ans = ans * power(fact[R], MOD - 2) % MOD;
        ans = ans * power(fact[N - R], MOD - 2) % MOD;

        return (int) ans;
    }

    private long power(long a, long b) {

        long result = 1;

        while (b > 0) {

            if ((b & 1) == 1) {
                result = result * a % MOD;
            }

            a = a * a % MOD;
            b >>= 1;
        }

        return result;
    }
}