# Redundant Connection (Union-Find)

## 1. Problem Title
Redundant Connection (Union-Find)

## 2. Difficulty Level (Easy / Medium / Hard)
Medium

## 3. Problem Statement
Given a tree with one extra edge added, return the edge that can be removed so that graph becomes a tree again.

## 4. Input Format
edges: 2D array where edges[i]=[u,v].

## 5. Output Format
Return redundant edge [u,v].

## 6. Constraints (very important for interviews)
- n == edges.length
- 3 <= n <= 1000
- 1 <= u < v <= n

## 7. Example Inputs and Outputs
Example 1
```text
Input: edges=[[1,2],[1,3],[2,3]]
Output: [2,3]
Explanation: Adding [2,3] closes first cycle.
```

## 8. Edge Cases
- Cycle forms late in input order.
- Graph size at upper bound.

## 9. Brute Force Approach Explanation
For each edge, remove it and test if graph is tree via DFS/BFS.

## 10. Optimal Approach Explanation
Union-Find: if endpoints of an edge already share root, that edge is redundant.

## 11. Step-by-Step Logic
1. Initialize DSU parents and ranks for 1..n.
2. For each edge, find roots of endpoints.
3. If roots same, return edge; else union roots.

## 12. Time Complexity Analysis
O(n * alpha(n)).

## 13. Space Complexity Analysis
O(n).

## 14. Clean and Production-Quality Java Code
```java
class Solution {
    public int[] findRedundantConnection(int[][] edges) {
        int n = edges.length;
        int[] parent = new int[n + 1];
        int[] rank = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            parent[i] = i;
        }

        for (int[] edge : edges) {
            int u = edge[0], v = edge[1];
            int pu = find(parent, u);
            int pv = find(parent, v);
            if (pu == pv) {
                return edge;
            }
            if (rank[pu] < rank[pv]) {
                parent[pu] = pv;
            } else if (rank[pu] > rank[pv]) {
                parent[pv] = pu;
            } else {
                parent[pv] = pu;
                rank[pu]++;
            }
        }

        return new int[0];
    }

    private int find(int[] parent, int x) {
        if (parent[x] != x) {
            parent[x] = find(parent, parent[x]);
        }
        return parent[x];
    }
}
```

## 15. Dry Run Example
Process [1,2], [1,3] unions; [2,3] endpoints already connected -> redundant.

## 16. Common Interview Follow-Up Questions
- How to detect redundant edge in directed graph variant?
- Can you return all cycle-forming edges in stream?

## 17. Alternative Solutions if available
- DFS connectivity checks after edge removals (slow).

## 18. Real Interview Context (why companies ask this problem)
Direct DSU pattern test for cycle detection in dynamic connectivity.
