import java.util.*;

class Group {
    int start, length;
    Group(int s, int l) {
        start = s;
        length = l;
    }
}

class SparseTable {
    private final int[][] st;

    SparseTable(int[] nums) {
        int n = nums.length;
        int lg = bitLength(n);
        st = new int[lg + 1][Math.max(1, n)];

        if (n == 0) return;

        System.arraycopy(nums, 0, st[0], 0, n);

        for (int k = 1; k <= lg; k++) {
            for (int i = 0; i + (1 << k) <= n; i++) {
                st[k][i] = Math.max(st[k - 1][i],
                        st[k - 1][i + (1 << (k - 1))]);
            }
        }
    }

    int query(int l, int r) {
        int k = bitLength(r - l + 1) - 1;
        return Math.max(st[k][l], st[k][r - (1 << k) + 1]);
    }

    private int bitLength(int x) {
        if (x <= 0) return 0;
        return 32 - Integer.numberOfLeadingZeros(x);
    }
}

class Solution {

    public List<Integer> maxActiveSectionsAfterTrade(String s, int[][] queries) {
        int ones = 0;
        for (char c : s.toCharArray())
            if (c == '1') ones++;

        Pair info = getZeroGroups(s);
        List<Group> zeroGroups = info.groups;
        int[] zeroGroupIndex = info.idx;

        if (zeroGroups.isEmpty()) {
            List<Integer> ans = new ArrayList<>();
            for (int i = 0; i < queries.length; i++)
                ans.add(ones);
            return ans;
        }

        SparseTable st = new SparseTable(getZeroMergeLengths(zeroGroups));

        List<Integer> ans = new ArrayList<>();

        for (int[] q : queries) {
            int l = q[0], r = q[1];

            int left = zeroGroupIndex[l] == -1 ? -1 :
                    zeroGroups.get(zeroGroupIndex[l]).length -
                    (l - zeroGroups.get(zeroGroupIndex[l]).start);

            int right = zeroGroupIndex[r] == -1 ? -1 :
                    r - zeroGroups.get(zeroGroupIndex[r]).start + 1;

            int startAdj = zeroGroupIndex[l] + 1;
            int endAdj = (s.charAt(r) == '1'
                    ? zeroGroupIndex[r]
                    : zeroGroupIndex[r] - 1) - 1;

            int best = ones;

            if (s.charAt(l) == '0' &&
                s.charAt(r) == '0' &&
                zeroGroupIndex[l] + 1 == zeroGroupIndex[r]) {

                best = Math.max(best, ones + left + right);

            } else if (startAdj <= endAdj) {

                best = Math.max(best,
                        ones + st.query(startAdj, endAdj));
            }

            if (s.charAt(l) == '0' &&
                zeroGroupIndex[l] + 1 <=
                (s.charAt(r) == '1'
                        ? zeroGroupIndex[r]
                        : zeroGroupIndex[r] - 1)) {

                best = Math.max(best,
                        ones + left +
                        zeroGroups.get(zeroGroupIndex[l] + 1).length);
            }

            if (s.charAt(r) == '0' &&
                zeroGroupIndex[l] < zeroGroupIndex[r] - 1) {

                best = Math.max(best,
                        ones + right +
                        zeroGroups.get(zeroGroupIndex[r] - 1).length);
            }

            ans.add(best);
        }

        return ans;
    }

    private static class Pair {
        List<Group> groups;
        int[] idx;
        Pair(List<Group> g, int[] i) {
            groups = g;
            idx = i;
        }
    }

    private Pair getZeroGroups(String s) {
        List<Group> groups = new ArrayList<>();
        int[] idx = new int[s.length()];

        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '0') {
                if (i > 0 && s.charAt(i - 1) == '0')
                    groups.get(groups.size() - 1).length++;
                else
                    groups.add(new Group(i, 1));
            }
            idx[i] = groups.size() - 1;
        }

        return new Pair(groups, idx);
    }

    private int[] getZeroMergeLengths(List<Group> groups) {
        if (groups.size() <= 1) return new int[0];

        int[] res = new int[groups.size() - 1];
        for (int i = 0; i + 1 < groups.size(); i++)
            res[i] = groups.get(i).length + groups.get(i + 1).length;

        return res;
    }
}