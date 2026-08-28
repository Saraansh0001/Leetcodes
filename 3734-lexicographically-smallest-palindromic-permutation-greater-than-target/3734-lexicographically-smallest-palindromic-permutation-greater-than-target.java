class Solution {
    public String lexPalindromicPermutation(String s, String target) {
        int n = s.length();
        int[] count = new int[26];

        for (char c : s.toCharArray()) {
            count[c - 'a']++;
        }

        // A palindrome can have at most one character with odd frequency
        int odd = 0;
        for (int x : count) {
            if (x % 2 != 0) odd++;
        }

        if (odd > 1) return "";

        int halfLen = n / 2;

        // Counts available for the left half
        int[] half = new int[26];
        for (int i = 0; i < 26; i++) {
            half[i] = count[i] / 2;
        }

        char middle = 0;
        if (n % 2 == 1) {
            for (int i = 0; i < 26; i++) {
                if (count[i] % 2 == 1) {
                    middle = (char) ('a' + i);
                    break;
                }
            }
        }

        String best = null;

        /*
         * Try every possible position where our palindrome
         * becomes greater than target.
         *
         * Positions before i are exactly equal to target.
         * At i, choose the smallest character > target[i].
         */
        for (int i = 0; i < halfLen; i++) {
            int[] rem = half.clone();

            // Match target[0 ... i-1]
            boolean possible = true;

            for (int j = 0; j < i; j++) {
                int x = target.charAt(j) - 'a';

                if (rem[x] == 0) {
                    possible = false;
                    break;
                }

                rem[x]--;
            }

            if (!possible) break;

            // Choose the smallest character greater than target[i]
            int chosen = -1;

            for (int c = target.charAt(i) - 'a' + 1; c < 26; c++) {
                if (rem[c] > 0) {
                    chosen = c;
                    break;
                }
            }

            if (chosen == -1) continue;

            rem[chosen]--;

            // Build the smallest possible left half
            StringBuilder left = new StringBuilder();

            for (int j = 0; j < i; j++) {
                left.append(target.charAt(j));
            }

            left.append((char) ('a' + chosen));

            for (int c = 0; c < 26; c++) {
                while (rem[c] > 0) {
                    left.append((char) ('a' + c));
                    rem[c]--;
                }
            }

            String candidate = makePalindrome(left.toString(), middle);

            if (candidate.compareTo(target) > 0) {
                if (best == null || candidate.compareTo(best) < 0) {
                    best = candidate;
                }
            }
        }

        /*
         * Also try the palindrome whose entire left half
         * matches target's left half.
         */
        int[] rem = half.clone();
        StringBuilder left = new StringBuilder();

        boolean possible = true;

        for (int i = 0; i < halfLen; i++) {
            int x = target.charAt(i) - 'a';

            if (rem[x] == 0) {
                possible = false;
                break;
            }

            rem[x]--;
            left.append(target.charAt(i));
        }

        if (possible) {
            String candidate = makePalindrome(left.toString(), middle);

            if (candidate.compareTo(target) > 0) {
                if (best == null || candidate.compareTo(best) < 0) {
                    best = candidate;
                }
            }
        }

        return best == null ? "" : best;
    }

    private String makePalindrome(String left, char middle) {
        StringBuilder result = new StringBuilder();

        result.append(left);

        if (middle != 0) {
            result.append(middle);
        }

        result.append(new StringBuilder(left).reverse());

        return result.toString();
    }
}