import java.util.*;

class Solution {
    private List<Integer>[][] memoDP;

    public String smallestNumber(String num, long t) {
        // 1. Factorize t into prime counts (2, 3, 5, 7)
        int[] need = factorize(t);
        if (need == null) return "-1"; // Prime factor > 7 exists

        int n = num.length();
        char[] result = new char[n];

        // 2. Try to find a valid number of length n (>= num)
        if (dfs(0, false, need, num, result)) {
            return new String(result);
        }

        // 3. If no valid number of length n exists, construct the smallest
        // number of length max(n + 1, minDigitsNeeded)
        int minLen = minDigits(need);
        int targetLen = Math.max(n + 1, minLen);

        return constructSmallest(targetLen, need);
    }

    private int[] factorize(long t) {
        int[] cnt = new int[4]; // 0->2, 1->3, 2->5, 3->7
        int[] primes = {2, 3, 5, 7};
        for (int i = 0; i < 4; i++) {
            while (t % primes[i] == 0) {
                cnt[i]++;
                t /= primes[i];
            }
        }
        if (t > 1) return null; // Contains prime factors > 7
        return cnt;
    }

    private boolean dfs(int pos, boolean greater, int[] need, String num, char[] result) {
        if (pos == num.length()) {
            return isSatisfied(need);
        }

        // Pruning: if remaining positions are fewer than minimum required digits
        int rem = num.length() - pos;
        if (minDigits(need) > rem) {
            return false;
        }

        int low = greater ? 1 : (num.charAt(pos) - '0');
        if (low == 0) low = 1; // Zeroes not allowed (product becomes 0)

        for (int d = low; d <= 9; d++) {
            int[] nextNeed = need.clone();
            reduce(nextNeed, d);

            boolean nextGreater = greater || (d > (num.charAt(pos) - '0'));
            result[pos] = (char) ('0' + d);

            if (dfs(pos + 1, nextGreater, nextNeed, num, result)) {
                return true;
            }
        }
        return false;
    }

    private void reduce(int[] need, int d) {
        if (d == 2) { need[0]--; }
        else if (d == 3) { need[1]--; }
        else if (d == 4) { need[0] -= 2; }
        else if (d == 5) { need[2]--; }
        else if (d == 6) { need[0]--; need[1]--; }
        else if (d == 7) { need[3]--; }
        else if (d == 8) { need[0] -= 3; }
        else if (d == 9) { need[1] -= 2; }

        for (int i = 0; i < 4; i++) {
            if (need[i] < 0) need[i] = 0;
        }
    }

    private boolean isSatisfied(int[] need) {
        for (int x : need) {
            if (x > 0) return false;
        }
        return true;
    }

    private int minDigits(int[] need) {
        int count = need[2] + need[3]; // 5s and 7s are fixed
        List<Integer> best23 = getBest23(need[0], need[1]);
        return count + best23.size();
    }

    // Dynamic Programming to find the minimum/best digits for factors 2 and 3
    @SuppressWarnings("unchecked")
    private List<Integer> getBest23(int c2, int c3) {
        if (c2 <= 0 && c3 <= 0) return new ArrayList<>();
        
        if (memoDP == null) {
            memoDP = new List[65][45];
        }
        c2 = Math.max(0, c2);
        c3 = Math.max(0, c3);
        
        if (memoDP[c2][c3] != null) return memoDP[c2][c3];

        List<Integer> best = null;
        int[][] choices = {
            {9, 0, 2}, {8, 3, 0}, {6, 1, 1},
            {4, 2, 0}, {3, 0, 1}, {2, 1, 0}
        };

        for (int[] ch : choices) {
            int digit = ch[0], use2 = ch[1], use3 = ch[2];
            if (c2 >= use2 && c3 >= use3) {
                List<Integer> sub = getBest23(c2 - use2, c3 - use3);
                List<Integer> candidate = new ArrayList<>(sub);
                candidate.add(digit);

                if (best == null || compareDigitLists(candidate, best) < 0) {
                    best = candidate;
                }
            }
        }

        if (best == null) best = new ArrayList<>();
        memoDP[c2][c3] = best;
        return best;
    }

    private int compareDigitLists(List<Integer> a, List<Integer> b) {
        if (a.size() != b.size()) return Integer.compare(a.size(), b.size());
        List<Integer> sa = new ArrayList<>(a);
        List<Integer> sb = new ArrayList<>(b);
        Collections.sort(sa);
        Collections.sort(sb);
        for (int i = 0; i < sa.size(); i++) {
            if (!sa.get(i).equals(sb.get(i))) {
                return Integer.compare(sa.get(i), sb.get(i));
            }
        }
        return 0;
    }

    private String constructSmallest(int targetLen, int[] need) {
        List<Integer> digits = new ArrayList<>();

        for (int i = 0; i < need[3]; i++) digits.add(7);
        for (int i = 0; i < need[2]; i++) digits.add(5);

        digits.addAll(getBest23(need[0], need[1]));
        Collections.sort(digits);

        StringBuilder sb = new StringBuilder();
        int onesNeeded = targetLen - digits.size();
        for (int i = 0; i < onesNeeded; i++) {
            sb.append('1');
        }
        for (int d : digits) {
            sb.append(d);
        }

        return sb.toString();
    }
}