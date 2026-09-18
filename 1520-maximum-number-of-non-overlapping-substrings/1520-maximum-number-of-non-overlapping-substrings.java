class Solution {
    public List<String> maxNumOfSubstrings(String s) {

        int n = s.length();

        int[] first = new int[26];
        int[] last = new int[26];

        // Initialize first positions
        Arrays.fill(first, -1);

        // Find first and last occurrence of every character
        for (int i = 0; i < n; i++) {
            int c = s.charAt(i) - 'a';

            if (first[c] == -1) {
                first[c] = i;
            }

            last[c] = i;
        }

        List<int[]> intervals = new ArrayList<>();

        // Try to create a valid interval for every character
        for (int c = 0; c < 26; c++) {

            if (first[c] == -1)
                continue;

            int l = first[c];
            int r = last[c];

            boolean valid = true;

            for (int i = l; i <= r; i++) {

                int current = s.charAt(i) - 'a';

                // This character started before our interval
                if (first[current] < l) {
                    valid = false;
                    break;
                }

                // Need to include all occurrences of this character
                r = Math.max(r, last[current]);
            }

            if (valid) {
                intervals.add(new int[]{l, r});
            }
        }

        // Sort by ending position
        intervals.sort((a, b) -> Integer.compare(a[1], b[1]));

        List<String> ans = new ArrayList<>();

        int prevEnd = -1;

        for (int[] interval : intervals) {

            int l = interval[0];
            int r = interval[1];

            // Non-overlapping
            if (l > prevEnd) {
                ans.add(s.substring(l, r + 1));
                prevEnd = r;
            }
        }

        return ans;
    }
}