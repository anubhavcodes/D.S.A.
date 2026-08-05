import java.util.*;

class Solution {
    public List<Integer> remainingMethods(int n, int k, int[][] invocations) {
        // Step 1: Build the directed graph adjacency list
        List<Integer>[] graph = new ArrayList[n];
        for (int i = 0; i < n; i++) {
            graph[i] = new ArrayList<>();
        }
        for (int[] invocation : invocations) {
            graph[invocation[0]].add(invocation[1]);
        }

        // Step 2: Mark all methods reachable from k as suspicious using BFS/DFS
        boolean[] isSuspicious = new boolean[n];
        dfs(k, graph, isSuspicious);

        // Step 3: Check if any non-suspicious method invokes a suspicious method
        boolean externalDependencyFound = false;
        for (int u = 0; u < n; u++) {
            if (!isSuspicious[u]) { // u is non-suspicious
                for (int v : graph[u]) {
                    if (isSuspicious[v]) { // u invokes a suspicious method v
                        externalDependencyFound = true;
                        break;
                    }
                }
            }
            if (externalDependencyFound) {
                break;
            }
        }
        List<Integer> result = new ArrayList<>();
        if (externalDependencyFound) {
            for (int i = 0; i < n; i++) {
                result.add(i);
            }
        } else {
            for (int i = 0; i < n; i++) {
                if (!isSuspicious[i]) {
                    result.add(i);
                }
            }
        }

        return result;
    }

    private void dfs(int u, List<Integer>[] graph, boolean[] isSuspicious) {
        isSuspicious[u] = true;
        for (int v : graph[u]) {
            if (!isSuspicious[v]) {
                dfs(v, graph, isSuspicious);
            }
        }
    }
}