
import java.util.*;

class Solution {
    public int smallestUniqueSubarray(int[] nums) {
        int n = nums.length;

        // Coordinate compression
        int[] sorted = nums.clone();
        Arrays.sort(sorted);

        int[] unique = new int[n];
        int classes = 0;

        for (int x : sorted) {
            if (classes == 0 || unique[classes - 1] != x) {
                unique[classes++] = x;
            }
        }

        int[] rank = new int[n];

        for (int i = 0; i < n; i++) {
            rank[i] = Arrays.binarySearch(unique, 0, classes, nums[i]);
        }

        // Build Suffix Array
        int[] sa = new int[n];
        int[] temp = new int[n];
        int[] newRank = new int[n];

        for (int i = 0; i < n; i++) {
            sa[i] = i;
        }

        for (int k = 1; k < n; k *= 2) {
            countingSort(sa, temp, rank, k, n, classes);
            countingSort(temp, sa, rank, 0, n, classes);

            int newClasses = 1;
            newRank[sa[0]] = 0;

            for (int i = 1; i < n; i++) {
                int a = sa[i - 1];
                int b = sa[i];

                int a2 = a + k < n ? rank[a + k] : -1;
                int b2 = b + k < n ? rank[b + k] : -1;

                if (rank[a] != rank[b] || a2 != b2) {
                    newClasses++;
                }

                newRank[b] = newClasses - 1;
            }

            int[] swap = rank;
            rank = newRank;
            newRank = swap;

            classes = newClasses;

            if (classes == n) break;
        }

        // Build LCP Array
        int[] pos = new int[n];
        int[] lcp = new int[n];

        for (int i = 0; i < n; i++) {
            pos[sa[i]] = i;
        }

        int h = 0;

        for (int i = 0; i < n; i++) {
            int p = pos[i];

            if (p == 0) {
                h = 0;
                continue;
            }

            int j = sa[p - 1];

            while (i + h < n && j + h < n &&
                   nums[i + h] == nums[j + h]) {
                h++;
            }

            lcp[p] = h;

            if (h > 0) h--;
        }

        // Find shortest unique subarray
        int ans = n;

        for (int i = 0; i < n; i++) {
            int maxLcp = lcp[i];

            if (i + 1 < n) {
                maxLcp = Math.max(maxLcp, lcp[i + 1]);
            }

            int len = maxLcp + 1;

            if (len <= n - sa[i]) {
                ans = Math.min(ans, len);
            }
        }

        return ans;
    }

    private void countingSort(int[] input, int[] output,
                              int[] rank, int offset,
                              int n, int classes) {

        int[] count = new int[classes + 1];

        for (int idx : input) {
            int key = idx + offset < n
                    ? rank[idx + offset] + 1 : 0;

            count[key]++;
        }

        for (int i = 1; i < count.length; i++) {
            count[i] += count[i - 1];
        }

        for (int i = n - 1; i >= 0; i--) {
            int idx = input[i];

            int key = idx + offset < n
                    ? rank[idx + offset] + 1 : 0;

            output[--count[key]] = idx;
        }
    }
}
