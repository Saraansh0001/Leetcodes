import java.util.*;

class Solution {
    public int minMoves(String[] classroom, int energy) {
        int m = classroom.length;
        int n = classroom[0].length();

        int[][] litter = new int[m][n];
        int sr = 0, sc = 0;
        int count = 0;

        // Find S and assign each L a bit number
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                char c = classroom[i].charAt(j);

                if (c == 'S') {
                    sr = i;
                    sc = j;
                } else if (c == 'L') {
                    litter[i][j] = count++;
                }
            }
        }

        if (count == 0) {
            return 0;
        }

        int totalMasks = 1 << count;

        // visited[row][col][energy][mask]
        boolean[][][][] visited =
            new boolean[m][n][energy + 1][totalMasks];

        Queue<int[]> q = new LinkedList<>();

        // Initially, all litter is uncollected
        int initialMask = totalMasks - 1;

        q.offer(new int[]{sr, sc, energy, initialMask});
        visited[sr][sc][energy][initialMask] = true;

        int[] dr = {-1, 1, 0, 0};
        int[] dc = {0, 0, -1, 1};

        int moves = 0;

        while (!q.isEmpty()) {
            int size = q.size();

            while (size-- > 0) {
                int[] cur = q.poll();

                int r = cur[0];
                int c = cur[1];
                int e = cur[2];
                int mask = cur[3];

                // All litter collected
                if (mask == 0) {
                    return moves;
                }

                // No energy = cannot make another move
                if (e == 0) {
                    continue;
                }

                for (int d = 0; d < 4; d++) {
                    int nr = r + dr[d];
                    int nc = c + dc[d];

                    // Outside grid / obstacle
                    if (nr < 0 || nr >= m || nc < 0 || nc >= n) {
                        continue;
                    }

                    if (classroom[nr].charAt(nc) == 'X') {
                        continue;
                    }

                    char cell = classroom[nr].charAt(nc);

                    // Moving normally costs 1 energy.
                    // R resets energy to maximum.
                    int newEnergy;

                    if (cell == 'R') {
                        newEnergy = energy;
                    } else {
                        newEnergy = e - 1;
                    }

                    int newMask = mask;

                    // Collect litter
                    if (cell == 'L') {
                        int bit = litter[nr][nc];
                        newMask &= ~(1 << bit);
                    }

                    if (!visited[nr][nc][newEnergy][newMask]) {
                        visited[nr][nc][newEnergy][newMask] = true;

                        q.offer(new int[]{
                            nr, nc, newEnergy, newMask
                        });
                    }
                }
            }

            moves++;
        }

        return -1;
    }
}