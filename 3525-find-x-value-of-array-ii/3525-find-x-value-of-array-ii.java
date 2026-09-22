class Solution {

    class Node {
        int[] pref;   // number of prefixes with each remainder
        int[] suff;   // number of suffixes with each remainder
        int[] cnt;    // number of subarrays with each remainder
        int prod;     // product of whole segment % k

        Node(int k) {
            pref = new int[k];
            suff = new int[k];
            cnt = new int[k];
        }
    }

    int n, k;
    Node[] tree;

    public int[] resultArray(int[] nums, int k, int[][] queries) {

        this.n = nums.length;
        this.k = k;

        tree = new Node[4 * n];

        build(1, 0, n - 1, nums);

        int[] ans = new int[queries.length];

        for (int q = 0; q < queries.length; q++) {

            int index = queries[q][0];
            int value = queries[q][1];
            int start = queries[q][2];
            int x = queries[q][3];

            // Persistent update
            update(1, 0, n - 1, index, value);

            // We only need the range [start, n-1]
            Node res = query(1, 0, n - 1, start, n - 1);

            // Prefixes of this range are exactly the possible
            // remaining arrays after removing a suffix.
            ans[q] = res.pref[x];
        }

        return ans;
    }

    // ---------------------------------------------------------
    // BUILD
    // ---------------------------------------------------------

    void build(int node, int l, int r, int[] nums) {

        if (l == r) {

            tree[node] = new Node(k);

            int rem = nums[l] % k;

            tree[node].prod = rem;
            tree[node].pref[rem] = 1;
            tree[node].suff[rem] = 1;
            tree[node].cnt[rem] = 1;

            return;
        }

        int mid = (l + r) / 2;

        build(node * 2, l, mid, nums);
        build(node * 2 + 1, mid + 1, r, nums);

        tree[node] = merge(tree[node * 2], tree[node * 2 + 1]);
    }

    // ---------------------------------------------------------
    // MERGE
    // ---------------------------------------------------------

    Node merge(Node left, Node right) {

        Node res = new Node(k);

        // Product of complete segment
        res.prod = (left.prod * right.prod) % k;

        // -----------------------------------------------------
        // Prefixes
        // -----------------------------------------------------

        // Prefix lies completely in left
        for (int r = 0; r < k; r++) {
            res.pref[r] += left.pref[r];
        }

        // Prefix goes through entire left + some prefix of right
        for (int a = 0; a < k; a++) {

            if (left.prod == a) {

                for (int b = 0; b < k; b++) {

                    int rem = (a * b) % k;

                    res.pref[rem] += right.pref[b];
                }
            }
        }

        // -----------------------------------------------------
        // Suffixes
        // -----------------------------------------------------

        // Suffix lies completely in right
        for (int r = 0; r < k; r++) {
            res.suff[r] += right.suff[r];
        }

        // Suffix starts in left and goes through all of right
        for (int a = 0; a < k; a++) {

            for (int b = 0; b < k; b++) {

                int rem = (a * right.prod) % k;

                res.suff[rem] += left.suff[a];

                break;
            }
        }

        // -----------------------------------------------------
        // Subarrays
        // -----------------------------------------------------

        // Subarrays completely inside left
        for (int r = 0; r < k; r++) {
            res.cnt[r] += left.cnt[r];
        }

        // Subarrays completely inside right
        for (int r = 0; r < k; r++) {
            res.cnt[r] += right.cnt[r];
        }

        // Subarrays crossing left -> right
        for (int a = 0; a < k; a++) {

            for (int b = 0; b < k; b++) {

                int rem = (a * b) % k;

                res.cnt[rem] += left.suff[a] * right.pref[b];
            }
        }

        return res;
    }

    // ---------------------------------------------------------
    // UPDATE
    // ---------------------------------------------------------

    void update(int node, int l, int r, int index, int value) {

        if (l == r) {

            tree[node] = new Node(k);

            int rem = value % k;

            tree[node].prod = rem;
            tree[node].pref[rem] = 1;
            tree[node].suff[rem] = 1;
            tree[node].cnt[rem] = 1;

            return;
        }

        int mid = (l + r) / 2;

        if (index <= mid) {
            update(node * 2, l, mid, index, value);
        } else {
            update(node * 2 + 1, mid + 1, r, index, value);
        }

        tree[node] = merge(tree[node * 2], tree[node * 2 + 1]);
    }

    // ---------------------------------------------------------
    // RANGE QUERY
    // ---------------------------------------------------------

    Node query(int node, int l, int r, int ql, int qr) {

        if (ql <= l && r <= qr) {
            return tree[node];
        }

        int mid = (l + r) / 2;

        if (qr <= mid) {
            return query(node * 2, l, mid, ql, qr);
        }

        if (ql > mid) {
            return query(node * 2 + 1, mid + 1, r, ql, qr);
        }

        Node left = query(node * 2, l, mid, ql, qr);
        Node right = query(node * 2 + 1, mid + 1, r, ql, qr);

        return merge(left, right);
    }
}