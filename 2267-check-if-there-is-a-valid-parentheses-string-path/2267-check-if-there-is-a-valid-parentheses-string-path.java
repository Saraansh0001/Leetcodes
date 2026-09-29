class Solution {

    int m, n;
    Boolean[][][] dp;

    public boolean hasValidPath(char[][] grid) {

        m = grid.length;
        n = grid[0].length;

        // Total number of cells in every path
        // must be even for a valid parentheses string.
        if ((m + n - 1) % 2 == 1) {
            return false;
        }

        // A valid string cannot start with ')'
        // or end with '('
        if (grid[0][0] == ')' || grid[m - 1][n - 1] == '(') {
            return false;
        }

        dp = new Boolean[m][n][m + n];

        return dfs(grid, 0, 0, 0);
    }

    private boolean dfs(char[][] grid, int i, int j, int balance) {

        // Add current cell to balance
        if (grid[i][j] == '(') {
            balance++;
        } else {
            balance--;
        }

        // Invalid prefix
        if (balance < 0) {
            return false;
        }

        // Reached destination
        if (i == m - 1 && j == n - 1) {
            return balance == 0;
        }

        // Already calculated this state
        if (dp[i][j][balance] != null) {
            return dp[i][j][balance];
        }

        boolean down = false;
        boolean right = false;

        // Move DOWN
        if (i + 1 < m) {
            down = dfs(grid, i + 1, j, balance);
        }

        // Move RIGHT
        if (j + 1 < n) {
            right = dfs(grid, i, j + 1, balance);
        }

        return dp[i][j][balance] = down || right;
    }
}