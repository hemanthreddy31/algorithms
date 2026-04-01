# Network Delay Time (Dijkstra)

## 1. Problem Title
Network Delay Time (Dijkstra)

## 2. Difficulty Level (Easy / Medium / Hard)
Medium

## 3. Problem Statement
Given directed weighted edges times[u,v,w], and source k, return time it takes for all nodes to receive signal. Return -1 if unreachable.

## 4. Input Format
times: 2D array [u,v,w], n: number of nodes (1-indexed), k: source node.

## 5. Output Format
Return minimum time for all nodes to be reached, or -1.

## 6. Constraints (very important for interviews)
- 1 <= n <= 100
- 1 <= times.length <= 6000
- 1 <= w <= 100

## 7. Example Inputs and Outputs
Example 1
```text
Input: times=[[2,1,1],[2,3,1],[3,4,1]], n=4, k=2
Output: 2
Explanation: Farthest node receives at time 2.
```

## 8. Edge Cases
- Graph disconnected from source.
- Single node graph.
- Multiple edges between same nodes.

## 9. Brute Force Approach Explanation
Run repeated relaxations until convergence; slower than needed for non-negative weights.

## 10. Optimal Approach Explanation
Use Dijkstra with min-heap since all weights are non-negative.

## 11. Step-by-Step Logic
1. Build adjacency list.
2. Initialize dist array with infinity except source 0.
3. Pop smallest distance node from heap; relax outgoing edges.
4. Answer is maximum finite dist if all reachable.

## 12. Time Complexity Analysis
O((V + E) log V).

## 13. Space Complexity Analysis
O(V + E).

## 14. Clean and Production-Quality Java Code
```java
import java.util.*;

class Solution {
    public int networkDelayTime(int[][] times, int n, int k) {
        List<List<int[]>> graph = new ArrayList<>();
        for (int i = 0; i <= n; i++) {
            graph.add(new ArrayList<>());
        }
        for (int[] e : times) {
            graph.get(e[0]).add(new int[] {e[1], e[2]});
        }

        int[] dist = new int[n + 1];
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[k] = 0;

        PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparingInt(a -> a[1]));
        pq.offer(new int[] {k, 0});

        while (!pq.isEmpty()) {
            int[] top = pq.poll();
            int node = top[0];
            int d = top[1];
            if (d > dist[node]) {
                continue;
            }
            for (int[] edge : graph.get(node)) {
                int next = edge[0];
                int w = edge[1];
                if (dist[next] > d + w) {
                    dist[next] = d + w;
                    pq.offer(new int[] {next, dist[next]});
                }
            }
        }

        int ans = 0;
        for (int i = 1; i <= n; i++) {
            if (dist[i] == Integer.MAX_VALUE) {
                return -1;
            }
            ans = Math.max(ans, dist[i]);
        }
        return ans;
    }
}
```

## 15. Dry Run Example
From 2: dist(1)=1, dist(3)=1, then from 3 dist(4)=2. Max dist=2.

## 16. Common Interview Follow-Up Questions
- How to solve with negative edges?
- How to retrieve shortest path tree?

## 17. Alternative Solutions if available
- Bellman-Ford O(VE).

## 18. Real Interview Context (why companies ask this problem)
Tests weighted shortest-path modeling and stale-state handling in priority queues.
