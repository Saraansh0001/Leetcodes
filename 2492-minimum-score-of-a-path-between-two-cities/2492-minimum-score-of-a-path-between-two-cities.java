class Solution {

    int ans = Integer.MAX_VALUE;

    public int minScore(int n, int[][] roads) {

        List<int[]>[] graph = new ArrayList[n + 1];

        for (int i = 1; i <= n; i++) {
            graph[i] = new ArrayList<>();
        }

        for (int[] road : roads) {
            int u = road[0];
            int v = road[1];
            int d = road[2];

            graph[u].add(new int[]{v, d});
            graph[v].add(new int[]{u, d});
        }

        boolean[] vis = new boolean[n + 1];
        dfs(1, graph, vis);

        return ans;
    }

    private void dfs(int node, List<int[]>[] graph, boolean[] vis) {

        vis[node] = true;

        for (int[] edge : graph[node]) {
            int next = edge[0];
            int dist = edge[1];

            ans = Math.min(ans, dist);

            if (!vis[next]) {
                dfs(next, graph, vis);
            }
        }
    }
}