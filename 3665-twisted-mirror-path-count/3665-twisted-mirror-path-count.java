class Solution {
    public int uniquePaths(int[][] grid) {

        int m = grid.length;
        int n = grid[0].length;

        int MOD = 1000000007;

        long[][] right = new long[m][n];
        long[][] down = new long[m][n];

        // Starting point
        right[0][0] = 1;

        for (int i = 0; i < m; i++) {

            for (int j = 0; j < n; j++) {

                long r = right[i][j];
                long d = down[i][j];

                // Mirror cell
                if (grid[i][j] == 1) {

                    // Entered from left -> reflected DOWN
                    if (i + 1 < m) {
                        down[i + 1][j] =
                            (down[i + 1][j] + r) % MOD;
                    }

                    // Entered from above -> reflected RIGHT
                    if (j + 1 < n) {
                        right[i][j + 1] =
                            (right[i][j + 1] + d) % MOD;
                    }

                } 

                // Empty cell
                else {

                    long ways = (r + d) % MOD;

                    // Move RIGHT
                    if (j + 1 < n) {
                        right[i][j + 1] =
                            (right[i][j + 1] + ways) % MOD;
                    }

                    // Move DOWN
                    if (i + 1 < m) {
                        down[i + 1][j] =
                            (down[i + 1][j] + ways) % MOD;
                    }
                }
            }
        }

        return (int)((right[m - 1][n - 1]
                    + down[m - 1][n - 1]) % MOD);
    }
}