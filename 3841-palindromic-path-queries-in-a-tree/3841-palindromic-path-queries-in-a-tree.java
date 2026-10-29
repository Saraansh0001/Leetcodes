
import java.util.*;

class Solution {
    private int LOG;
    private int[] tin, tout, parent, depth, charMask, initialPrefix;
    private int[][] up;
    private Fenwick bit;
    private List<Integer>[] adj;
    private int timer;

    class Fenwick {
        int[] tree;
        int n;

        Fenwick(int n) {
            this.n = n;
            tree = new int[n + 2];
        }

        void update(int i, int val) {
            for (; i <= n; i += i & -i) {
                tree[i] ^= val;
            }
        }

        // XOR update on inclusive interval [l, r]
        void rangeUpdate(int l, int r, int val) {
            update(l, val);
            update(r + 1, val);
        }

        int query(int i) {
            int ans = 0;
            for (; i > 0; i -= i & -i) {
                ans ^= tree[i];
            }
            return ans;
        }
    }

    public List<Boolean> palindromePath(
            int n, int[][] edges, String s, String[] queries) {

        adj = new ArrayList[n];
        for (int i = 0; i < n; i++) {
            adj[i] = new ArrayList<>();
        }

        for (int[] e : edges) {
            adj[e[0]].add(e[1]);
            adj[e[1]].add(e[0]);
        }

        charMask = new int[n];
        for (int i = 0; i < n; i++) {
            charMask[i] = 1 << (s.charAt(i) - 'a');
        }

        tin = new int[n];
        tout = new int[n];
        parent = new int[n];
        depth = new int[n];
        initialPrefix = new int[n];

        // Iterative DFS avoids stack overflow on a deep tree.
        int[] stackNode = new int[n];
        int[] stackParent = new int[n];
        int[] stackIndex = new int[n];
        int top = 0;

        stackNode[0] = 0;
        stackParent[0] = -1;
        stackIndex[0] = 0;
        parent[0] = 0;
        initialPrefix[0] = charMask[0];

        timer = 0;

        while (top >= 0) {
            int u = stackNode[top];

            if (stackIndex[top] == 0) {
                tin[u] = ++timer;
            }

            if (stackIndex[top] < adj[u].size()) {
                int v = adj[u].get(stackIndex[top]++);

                if (v == stackParent[top]) {
                    continue;
                }

                parent[v] = u;
                depth[v] = depth[u] + 1;
                initialPrefix[v] =
                        initialPrefix[u] ^ charMask[v];

                top++;
                stackNode[top] = v;
                stackParent[top] = u;
                stackIndex[top] = 0;
            } else {
                tout[u] = timer;
                top--;
            }
        }

        // Binary lifting table for LCA.
        LOG = 1;
        while ((1 << LOG) <= n) {
            LOG++;
        }

        up = new int[LOG][n];

        for (int u = 0; u < n; u++) {
            up[0][u] = parent[u];
        }

        for (int j = 1; j < LOG; j++) {
            for (int u = 0; u < n; u++) {
                up[j][u] = up[j - 1][up[j - 1][u]];
            }
        }

        bit = new Fenwick(n);
        List<Boolean> answer = new ArrayList<>();

        for (String q : queries) {
            String[] parts = q.split(" ");

            if (parts[0].equals("update")) {
                int u = Integer.parseInt(parts[1]);
                char c = parts[2].charAt(0);

                int newMask = 1 << (c - 'a');
                int delta = charMask[u] ^ newMask;

                // Changing u affects every root path through u.
                bit.rangeUpdate(tin[u], tout[u], delta);

                charMask[u] = newMask;
            } else {
                int u = Integer.parseInt(parts[1]);
                int v = Integer.parseInt(parts[2]);

                int lca = getLCA(u, v);

                int prefixU =
                        initialPrefix[u] ^ bit.query(tin[u]);
                int prefixV =
                        initialPrefix[v] ^ bit.query(tin[v]);

                int mask = prefixU ^ prefixV ^ charMask[lca];

                // Zero or one odd-frequency character.
                answer.add((mask & (mask - 1)) == 0);
            }
        }

        return answer;
    }

    private int getLCA(int u, int v) {
        if (depth[u] < depth[v]) {
            int temp = u;
            u = v;
            v = temp;
        }

        int diff = depth[u] - depth[v];

        for (int j = 0; j < LOG; j++) {
            if ((diff & (1 << j)) != 0) {
                u = up[j][u];
            }
        }

        if (u == v) {
            return u;
        }

        for (int j = LOG - 1; j >= 0; j--) {
            if (up[j][u] != up[j][v]) {
                u = up[j][u];
                v = up[j][v];
            }
        }

        return parent[u];
    }
}
