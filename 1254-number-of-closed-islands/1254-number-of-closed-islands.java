class Solution {
    public int closedIsland(int[][] grid) {
        int m = grid.length, n = grid[0].length;
        int count = 0;

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 0 && dfs(grid, i, j)) {
                    count++;
                }
            }
        }
        return count;
    }

    boolean dfs(int[][] grid, int i, int j) {
        if (i < 0 || j < 0 || i >= grid.length || j >= grid[0].length)
            return false;

        if (grid[i][j] == 1)
            return true;

        grid[i][j] = 1;

        boolean a = dfs(grid, i + 1, j);
        boolean b = dfs(grid, i - 1, j);
        boolean c = dfs(grid, i, j + 1);
        boolean d = dfs(grid, i, j - 1);

        return a && b && c && d;
    }
}