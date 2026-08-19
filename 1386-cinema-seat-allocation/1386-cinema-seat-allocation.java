class Solution {
    public int maxNumberOfFamilies(int n, int[][] reservedSeats) {
        // Each row can normally fit 2 groups
        int ans = 2 * n;

        // Store reserved seats row-wise
        HashMap<Integer, Integer> map = new HashMap<>();

        for (int[] seat : reservedSeats) {
            int row = seat[0];
            int s = seat[1];

            // Only seats 2-9 matter
            if (s >= 2 && s <= 9) {
                map.put(row, map.getOrDefault(row, 0) | (1 << s));
            }
        }

        // Check only rows having reserved seats
        for (int mask : map.values()) {
            boolean left = (mask & ((1 << 2) | (1 << 3) | (1 << 4) | (1 << 5))) == 0;
            boolean middle = (mask & ((1 << 4) | (1 << 5) | (1 << 6) | (1 << 7))) == 0;
            boolean right = (mask & ((1 << 6) | (1 << 7) | (1 << 8) | (1 << 9))) == 0;

            if (left && right) {
                // Both groups can fit
                ans -= 0;
            } else if (left || middle || right) {
                // Only one group can fit
                ans -= 1;
            } else {
                // No group can fit
                ans -= 2;
            }
        }

        return ans;
    }
}