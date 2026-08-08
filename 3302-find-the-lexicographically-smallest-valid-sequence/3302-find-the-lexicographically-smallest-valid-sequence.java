class Solution {
    public int[] validSequence(String word1, String word2) {

        int n = word1.length();
        int m = word2.length();

        // dp[i] = maximum number of characters of word2
        // that can be matched exactly using word1[i...]
        int[] dp = new int[n + 1];

        int j = m - 1;

        for (int i = n - 1; i >= 0; i--) {
            dp[i] = dp[i + 1];

            if (j >= 0 && word1.charAt(i) == word2.charAt(j)) {
                dp[i]++;
                j--;
            }
        }

        int[] ans = new int[m];

        int i = 0;
        j = 0;

        // Greedily construct the lexicographically smallest answer
        while (i < n && j < m) {

            // Best case: exact match
            if (word1.charAt(i) == word2.charAt(j)) {

                ans[j] = i;
                j++;

            } else {

                // Use our one allowed modification here
                // only if the remaining characters can be matched exactly.
                if (dp[i + 1] >= m - j - 1) {

                    ans[j] = i;
                    j++;
                    i++;

                    // We have used the one mismatch.
                    break;
                }
            }

            i++;
        }

        // Not enough characters
        if (j < m && i >= n) {
            return new int[0];
        }

        // After using the mismatch, match the rest exactly.
        while (j < m && i < n) {

            if (word1.charAt(i) == word2.charAt(j)) {
                ans[j] = i;
                j++;
            }

            i++;
        }

        // Couldn't form word2
        if (j < m) {
            return new int[0];
        }

        return ans;
    }
}