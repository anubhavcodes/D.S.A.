import java.util.*;
class Solution {
    private List<int[]>[] graph;
    private boolean[] visited;
    private int ans = Integer.MAX_VALUE;
    public int minScore(int n, int[][] roads) {
        graph = new ArrayList[n + 1];
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
        visited = new boolean[n + 1];
        dfs(1);
        return ans;
    }
    private void dfs(int node) {
        visited[node] = true;
        for (int[] nei : graph[node]) {
            ans = Math.min(ans, nei[1]);
            if (!visited[nei[0]]) {
                dfs(nei[0]);
            }
        }
    }
}