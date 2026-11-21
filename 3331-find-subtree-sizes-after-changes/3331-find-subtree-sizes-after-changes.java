class Solution {
    List<Integer>[] tree;
    List<Integer>[] newTree;
    int[] last;
    int[] newParent;
    int[] ans;
    String s;

    public int[] findSubtreeSizes(int[] parent, String str) {
        int n = parent.length;
        s = str;

        tree = new ArrayList[n];
        newTree = new ArrayList[n];

        for (int i = 0; i < n; i++) {
            tree[i] = new ArrayList<>();
            newTree[i] = new ArrayList<>();
        }

        for (int i = 1; i < n; i++) {
            tree[parent[i]].add(i);
        }

        last = new int[26];
        Arrays.fill(last, -1);

        newParent = parent.clone();

        dfs1(0);

        for (int i = 1; i < n; i++) {
            newTree[newParent[i]].add(i);
        }

        ans = new int[n];
        dfs2(0);

        return ans;
    }

    void dfs1(int node) {
        int c = s.charAt(node) - 'a';

        int old = last[c];

        if (old != -1) {
            newParent[node] = old;
        }

        last[c] = node;

        for (int child : tree[node]) {
            dfs1(child);
        }

        last[c] = old;
    }

    int dfs2(int node) {
        int size = 1;

        for (int child : newTree[node]) {
            size += dfs2(child);
        }

        ans[node] = size;
        return size;
    }
}
