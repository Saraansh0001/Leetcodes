class Solution {

    static class Pair {
        int node;
        long dist;

        Pair(int node, long dist) {
            this.node = node;
            this.dist = dist;
        }
    }

    public int findMaxPathScore(int[][] edges, boolean[] online, long k) {

        int n = online.length;

        List<int[]>[] graph = new ArrayList[n];
        for (int i = 0; i < n; i++)
            graph[i] = new ArrayList<>();

        int low = Integer.MAX_VALUE;
        int high = Integer.MIN_VALUE;

        for (int[] e : edges) {
            int u = e[0], v = e[1], w = e[2];

            if ((u != 0 && u != n - 1 && !online[u]) ||
                (v != 0 && v != n - 1 && !online[v]))
                continue;

            graph[u].add(new int[]{v, w});

            low = Math.min(low, w);
            high = Math.max(high, w);
        }

        if (low == Integer.MAX_VALUE)
            return -1;

        if (!check(graph, n, low, k))
            return -1;

        int ans = low;

        while (low <= high) {
            int mid = low + (high - low) / 2;

            if (check(graph, n, mid, k)) {
                ans = mid;
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }

        return ans;
    }

    private boolean check(List<int[]>[] graph, int n, int limit, long k) {

        long[] dist = new long[n];
        Arrays.fill(dist, Long.MAX_VALUE);

        PriorityQueue<Pair> pq = new PriorityQueue<>(
                (a, b) -> Long.compare(a.dist, b.dist));

        dist[0] = 0;
        pq.offer(new Pair(0, 0));

        while (!pq.isEmpty()) {

            Pair cur = pq.poll();

            if (cur.dist != dist[cur.node])
                continue;

            if (cur.dist > k)
                continue;

            if (cur.node == n - 1)
                return true;

            for (int[] edge : graph[cur.node]) {

                int next = edge[0];
                int cost = edge[1];

                if (cost < limit)
                    continue;

                long nd = cur.dist + cost;

                if (nd < dist[next]) {
                    dist[next] = nd;
                    pq.offer(new Pair(next, nd));
                }
            }
        }

        return false;
    }
}