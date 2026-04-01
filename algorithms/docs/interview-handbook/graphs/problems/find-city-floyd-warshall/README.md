# Find the City With the Smallest Number of Neighbors at a Threshold Distance (Floyd-Warshall)

## 1. Problem Title
Find the City With the Smallest Number of Neighbors at a Threshold Distance (Floyd-Warshall)

## 2. Difficulty Level (Easy / Medium / Hard)
Medium

## 3. Problem Statement
Given weighted undirected graph and distanceThreshold, return city with smallest number of reachable cities within threshold (break ties by largest index).

## 4. Input Format
n: number of cities, edges: [u,v,w], distanceThreshold: integer.

## 5. Output Format
Return city index.

## 6. Constraints (very important for interviews)
- 2 <= n <= 100
- 1 <= edges.length <= n*(n-1)/2
- 1 <= w, distanceThreshold <= 10^4

## 7. Example Inputs and Outputs
Example 1
```text
Input: n=4, edges=[[0,1,3],[1,2,1],[1,3,4],[2,3,1]], threshold=4
Output: 3
Explanation: Cities 0 and 3 have same reachable count; choose larger index 3.
```

## 8. Edge Cases
- Disconnected graph.
- Tie in reachable counts.
- Dense graph near n=100.

## 9. Brute Force Approach Explanation
Run BFS/DFS from each node for weighted graph (incorrect) or Dijkstra from each node O(n * (E log V)).

## 10. Optimal Approach Explanation
Use Floyd-Warshall all-pairs shortest path O(n^3), suitable for n <= 100.

## 11. Step-by-Step Logic
1. Initialize dist matrix with INF and zero diagonal.
2. Fill direct edge weights (min if duplicates).
3. Run triple loop k,i,j to relax via intermediate k.
4. Count reachable cities per i and apply tie-break.

## 12. Time Complexity Analysis
O(n^3).

## 13. Space Complexity Analysis
O(n^2).

## 14. Clean and Production-Quality Java Code
```java
import java.util.*;

class Solution {
    public int findTheCity(int n, int[][] edges, int distanceThreshold) {
        int inf = 1_000_000_000;
        int[][] dist = new int[n][n];

        for (int i = 0; i < n; i++) {
            Arrays.fill(dist[i], inf);
            dist[i][i] = 0;
        }

        for (int[] e : edges) {
            int u = e[0], v = e[1], w = e[2];
            dist[u][v] = Math.min(dist[u][v], w);
            dist[v][u] = Math.min(dist[v][u], w);
        }

        for (int k = 0; k < n; k++) {
            for (int i = 0; i < n; i++) {
                if (dist[i][k] == inf) {
                    continue;
                }
                for (int j = 0; j < n; j++) {
                    if (dist[k][j] == inf) {
                        continue;
                    }
                    dist[i][j] = Math.min(dist[i][j], dist[i][k] + dist[k][j]);
                }
            }
        }

        int bestCity = -1;
        int minReachable = Integer.MAX_VALUE;

        for (int i = 0; i < n; i++) {
            int count = 0;
            for (int j = 0; j < n; j++) {
                if (i != j && dist[i][j] <= distanceThreshold) {
                    count++;
                }
            }
            if (count <= minReachable) {
                minReachable = count;
                bestCity = i;
            }
        }

        return bestCity;
    }
}
```

## 15. Dry Run Example
After APSP matrix computed, count threshold-reachable per row and apply largest-index tie rule.

## 16. Common Interview Follow-Up Questions
- When would n-times Dijkstra be better than Floyd-Warshall?
- How to reconstruct shortest paths between pairs?

## 17. Alternative Solutions if available
- Run Dijkstra from each city for sparse graphs.

## 18. Real Interview Context (why companies ask this problem)
Validates all-pairs shortest path understanding and complexity tradeoff decisions.
