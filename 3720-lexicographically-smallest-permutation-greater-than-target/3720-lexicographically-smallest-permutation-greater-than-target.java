class Solution {
    public String lexGreaterPermutation(String S, String target) {

        int[] count = new int[26];

        for (char c : S.toCharArray()) {
            count[c - 'a']++;
        }

        char[] ans = target.toCharArray();
        int n = target.length();

        for (int i = 0; i < n; i++) {

            int idx = target.charAt(i) - 'a';

            // Try to keep target[i] same
            if (count[idx] > 0) {
                count[idx]--;
                continue;
            }

            // target[i] is unavailable.
            // Try the smallest character greater than target[i].
            for (int c = idx + 1; c < 26; c++) {

                if (count[c] > 0) {

                    ans[i] = (char) ('a' + c);
                    count[c]--;

                    // Fill remaining positions with smallest characters
                    int k = i + 1;

                    for (int x = 0; x < 26; x++) {
                        while (count[x] > 0) {
                            ans[k++] = (char) ('a' + x);
                            count[x]--;
                        }
                    }

                    return new String(ans);
                }
            }

            // No greater character at this position.
            // Backtrack to an earlier position.
            for (int j = i - 1; j >= 0; j--) {

                count[ans[j] - 'a']++;

                int old = target.charAt(j) - 'a';

                for (int c = old + 1; c < 26; c++) {

                    if (count[c] > 0) {

                        ans[j] = (char) ('a' + c);
                        count[c]--;

                        int k = j + 1;

                        for (int x = 0; x < 26; x++) {
                            while (count[x] > 0) {
                                ans[k++] = (char) ('a' + x);
                                count[x]--;
                            }
                        }

                        return new String(ans);
                    }
                }
            }

            return "";
        }

        // target itself can be formed.
        // Need something strictly greater, so backtrack.
        for (int i = n - 1; i >= 0; i--) {

            count[ans[i] - 'a']++;

            int idx = target.charAt(i) - 'a';

            for (int c = idx + 1; c < 26; c++) {

                if (count[c] > 0) {

                    ans[i] = (char) ('a' + c);
                    count[c]--;

                    int k = i + 1;

                    for (int x = 0; x < 26; x++) {
                        while (count[x] > 0) {
                            ans[k++] = (char) ('a' + x);
                            count[x]--;
                        }
                    }

                    return new String(ans);
                }
            }
        }

        return "";
    }
}