# Cheapest Flights Within K Stops (Bellman-Ford Style)

## 1. Problem Title
Cheapest Flights Within K Stops (Bellman-Ford Style)

## 2. Difficulty Level (Easy / Medium / Hard)
Medium

## 3. Problem Statement
Given flights[u,v,price], src, dst, and k, return cheapest price from src to dst with at most k stops. Return -1 if not possible.

## 4. Input Format
n: node count, flights: 2D edge list, src, dst, k integers.

## 5. Output Format
Return minimum cost or -1.

## 6. Constraints (very important for interviews)
- 1 <= n <= 100
- 0 <= flights.length <= n*(n-1)/2
- 0 <= price <= 10^4
- 0 <= k < n

## 7. Example Inputs and Outputs
Example 1
```text
Input: n=3, flights=[[0,1,100],[1,2,100],[0,2,500]], src=0, dst=2, k=1
Output: 200
Explanation: 0->1->2 within one stop is cheapest.
```

## 8. Edge Cases
- No route exists.
- k = 0 allows direct flights only.
- Cheaper path may have more edges but within stop limit.

## 9. Brute Force Approach Explanation
DFS all paths up to k+1 edges and track minimum cost. Exponential in branching.

## 10. Optimal Approach Explanation
Bellman-Ford limited to k+1 relaxations. Each round uses previous distances to avoid chaining within same round.

## 11. Step-by-Step Logic
1. Initialize dist[src]=0 and others INF.
2. Repeat k+1 times: copy dist to next, relax each flight using dist, write into next.
3. Set dist=next after each round.

## 12. Time Complexity Analysis
O((k+1) * E).

## 13. Space Complexity Analysis
O(V).

## 14. Clean and Production-Quality Java Code
```java
import java.util.*;

class Solution {
    public int findCheapestPrice(int n, int[][] flights, int src, int dst, int k) {
        int inf = 1_000_000_000;
        int[] dist = new int[n];
        Arrays.fill(dist, inf);
        dist[src] = 0;

        for (int i = 0; i <= k; i++) {
            int[] next = Arrays.copyOf(dist, n);
            for (int[] flight : flights) {
                int u = flight[0], v = flight[1], w = flight[2];
                if (dist[u] == inf) {
                    continue;
                }
                next[v] = Math.min(next[v], dist[u] + w);
            }
            dist = next;
        }

        return dist[dst] == inf ? -1 : dist[dst];
    }
}
```

## 15. Dry Run Example
Round0 direct costs; round1 allows one intermediate stop and updates cheaper route 0->1->2.

## 16. Common Interview Follow-Up Questions
- Can Dijkstra be adapted with stop count state?
- How to return actual route path?

## 17. Alternative Solutions if available
- Modified Dijkstra with state (node,stops).
- BFS by levels with pruning.

## 18. Real Interview Context (why companies ask this problem)
Great for testing understanding of constrained shortest path and Bellman-Ford relaxation logic.
