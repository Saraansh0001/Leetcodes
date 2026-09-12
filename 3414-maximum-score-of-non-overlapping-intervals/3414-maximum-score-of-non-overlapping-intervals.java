import java.util.*;

class Solution {

    static class Interval {
        int l, r, w, idx;

        Interval(int l, int r, int w, int idx) {
            this.l = l;
            this.r = r;
            this.w = w;
            this.idx = idx;
        }
    }

    static class Result {
        long score;
        int[] indices;

        Result(long score, int[] indices) {
            this.score = score;
            this.indices = indices;
        }
    }

    public int[] maximumWeight(List<List<Integer>> intervals) {

        int n = intervals.size();

        Interval[] arr = new Interval[n];

        for (int i = 0; i < n; i++) {
            arr[i] = new Interval(
                intervals.get(i).get(0),
                intervals.get(i).get(1),
                intervals.get(i).get(2),
                i
            );
        }

        Arrays.sort(arr, (a, b) -> {
            if (a.l != b.l)
                return Integer.compare(a.l, b.l);

            return Integer.compare(a.r, b.r);
        });

        Result[][] dp = new Result[n + 1][5];

        // Base case: choosing 0 intervals
        for (int i = 0; i <= n; i++) {
            dp[i][0] = new Result(0, new int[0]);
        }

        // Base case: no intervals left
        for (int k = 1; k <= 4; k++) {
            dp[n][k] = new Result(0, new int[0]);
        }

        for (int i = n - 1; i >= 0; i--) {

            for (int k = 1; k <= 4; k++) {

                // Don't take current interval
                Result skip = dp[i + 1][k];

                // Take current interval
                int next = findNext(arr, i + 1, arr[i].r);

                Result nextResult = dp[next][k - 1];

                int[] chosen = new int[nextResult.indices.length + 1];

                chosen[0] = arr[i].idx;

                for (int j = 0; j < nextResult.indices.length; j++) {
                    chosen[j + 1] = nextResult.indices[j];
                }

                Arrays.sort(chosen);

                Result take = new Result(
                    arr[i].w + nextResult.score,
                    chosen
                );

                dp[i][k] = better(take, skip);
            }
        }

        return dp[0][4].indices;
    }

    static int findNext(Interval[] arr, int start, int right) {

        int lo = start;
        int hi = arr.length;

        while (lo < hi) {

            int mid = lo + (hi - lo) / 2;

            if (arr[mid].l > right)
                hi = mid;
            else
                lo = mid + 1;
        }

        return lo;
    }

    static Result better(Result a, Result b) {

        if (a.score != b.score)
            return a.score > b.score ? a : b;

        return Arrays.compare(a.indices, b.indices) < 0 ? a : b;
    }
}