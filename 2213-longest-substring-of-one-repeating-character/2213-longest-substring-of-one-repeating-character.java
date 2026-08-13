class Solution {

    int[] tree, pref, suff, best;
    char[] leftChar, rightChar;
    char[] s;

    public int[] longestRepeating(String s, String queryCharacters, int[] queryIndices) {

        int n = s.length();
        int k = queryIndices.length;

        // Convert String to mutable char array
        this.s = s.toCharArray();

        int size = 4 * n + 5;

        tree = new int[size];
        pref = new int[size];
        suff = new int[size];
        best = new int[size];

        leftChar = new char[size];
        rightChar = new char[size];

        build(1, 0, n - 1);

        int[] ans = new int[k];

        for (int i = 0; i < k; i++) {

            int index = queryIndices[i];
            char c = queryCharacters.charAt(i);

            // IMPORTANT: update the char array, NOT the String
            this.s[index] = c;

            update(1, 0, n - 1, index);

            ans[i] = best[1];
        }

        return ans;
    }

    void build(int node, int l, int r) {

        if (l == r) {

            tree[node] = 1;
            pref[node] = 1;
            suff[node] = 1;
            best[node] = 1;

            leftChar[node] = s[l];
            rightChar[node] = s[l];

            return;
        }

        int mid = (l + r) / 2;

        build(node * 2, l, mid);
        build(node * 2 + 1, mid + 1, r);

        merge(node);
    }

    void update(int node, int l, int r, int index) {

        if (l == r) {

            tree[node] = 1;
            pref[node] = 1;
            suff[node] = 1;
            best[node] = 1;

            leftChar[node] = s[l];
            rightChar[node] = s[l];

            return;
        }

        int mid = (l + r) / 2;

        if (index <= mid) {
            update(node * 2, l, mid, index);
        } else {
            update(node * 2 + 1, mid + 1, r, index);
        }

        merge(node);
    }

    void merge(int node) {

        int left = node * 2;
        int right = node * 2 + 1;

        // Length of current segment
        tree[node] = tree[left] + tree[right];

        // Boundary characters
        leftChar[node] = leftChar[left];
        rightChar[node] = rightChar[right];

        // Prefix
        pref[node] = pref[left];

        if (pref[left] == tree[left] &&
            rightChar[left] == leftChar[right]) {

            pref[node] += pref[right];
        }

        // Suffix
        suff[node] = suff[right];

        if (suff[right] == tree[right] &&
            rightChar[left] == leftChar[right]) {

            suff[node] += suff[left];
        }

        // Best consecutive repeating substring
        best[node] = Math.max(best[left], best[right]);

        // Combination across middle
        if (rightChar[left] == leftChar[right]) {

            best[node] = Math.max(
                best[node],
                suff[left] + pref[right]
            );
        }
    }
}