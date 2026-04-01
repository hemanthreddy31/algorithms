# Maximum Flow (Edmonds-Karp)

## 1. Problem Title
Maximum Flow (Edmonds-Karp)

## 2. Difficulty Level (Easy / Medium / Hard)
Hard

## 3. Problem Statement
Given directed graph capacities and source s, sink t, compute maximum possible flow from s to t.

## 4. Input Format
n: node count, edges: list [u,v,capacity], s: source, t: sink.

## 5. Output Format
Return integer maximum flow value.

## 6. Constraints (very important for interviews)
- 2 <= n <= 200
- 0 <= edges.length <= 5000
- 0 <= capacity <= 10^6

## 7. Example Inputs and Outputs
Example 1
```text
Input: n=4, edges=[[0,1,3],[0,2,2],[1,2,1],[1,3,2],[2,3,4]], s=0, t=3
Output: 5
Explanation: Max flow uses augmenting paths until none remain.
```

## 8. Edge Cases
- No path from s to t => 0.
- Multiple parallel edges.
- Zero-capacity edges.

## 9. Brute Force Approach Explanation
Enumerate all possible flow assignments satisfying constraints (intractable).

## 10. Optimal Approach Explanation
Edmonds-Karp repeatedly finds shortest augmenting path in residual graph using BFS.

## 11. Step-by-Step Logic
1. Build residual capacity matrix.
2. Run BFS from source to sink storing parent pointers.
3. Find bottleneck capacity on found path.
4. Augment flow and update forward/reverse residual capacities.

## 12. Time Complexity Analysis
O(V * E^2).

## 13. Space Complexity Analysis
O(V^2) for residual matrix.

## 14. Clean and Production-Quality Java Code
```java
import java.util.*;

class MaxFlow {
    public int edmondsKarp(int n, int[][] edges, int s, int t) {
        int[][] capacity = new int[n][n];
        List<List<Integer>> graph = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            graph.add(new ArrayList<>());
        }

        for (int[] e : edges) {
            int u = e[0], v = e[1], cap = e[2];
            capacity[u][v] += cap;
            graph.get(u).add(v);
            graph.get(v).add(u);
        }

        int flow = 0;
        int[] parent = new int[n];

        while (bfs(graph, capacity, s, t, parent)) {
            int aug = Integer.MAX_VALUE;
            for (int v = t; v != s; v = parent[v]) {
                int u = parent[v];
                aug = Math.min(aug, capacity[u][v]);
            }

            for (int v = t; v != s; v = parent[v]) {
                int u = parent[v];
                capacity[u][v] -= aug;
                capacity[v][u] += aug;
            }

            flow += aug;
        }

        return flow;
    }

    private boolean bfs(List<List<Integer>> graph, int[][] capacity, int s, int t, int[] parent) {
        Arrays.fill(parent, -1);
        parent[s] = s;
        Queue<Integer> q = new ArrayDeque<>();
        q.offer(s);

        while (!q.isEmpty()) {
            int u = q.poll();
            for (int v : graph.get(u)) {
                if (parent[v] == -1 && capacity[u][v] > 0) {
                    parent[v] = u;
                    if (v == t) {
                        return true;
                    }
                    q.offer(v);
                }
            }
        }

        return false;
    }
}
```

## 15. Dry Run Example
Path 0->1->3 adds 2, path 0->2->3 adds 2, path 0->1->2->3 adds 1 => total 5.

## 16. Common Interview Follow-Up Questions
- When to use Dinic instead?
- How do max-flow and min-cut relate?

## 17. Alternative Solutions if available
- Dinic O(EV^2) worst-case but faster in practice.
- Push-relabel for dense graphs.

## 18. Real Interview Context (why companies ask this problem)
Advanced optimization question for senior algorithm and systems roles.
