# Number of Provinces (DSU)

## 1. Problem Title
Number of Provinces (DSU)

## 2. Difficulty Level (Easy / Medium / Hard)
Medium

## 3. Problem Statement
Given n x n matrix isConnected representing direct connections between cities, return number of provinces (connected components).

## 4. Input Format
isConnected: adjacency matrix of size n.

## 5. Output Format
Return count of provinces.

## 6. Constraints (very important for interviews)
- 1 <= n <= 200
- isConnected[i][i] = 1
- isConnected[i][j] = isConnected[j][i]

## 7. Example Inputs and Outputs
Example 1
```text
Input: isConnected=[[1,1,0],[1,1,0],[0,0,1]]
Output: 2
Explanation: Cities {0,1} and {2} form two components.
```

## 8. Edge Cases
- All disconnected except self loops.
- Fully connected graph.
- Single city.

## 9. Brute Force Approach Explanation
Run DFS from every unvisited node (valid O(n^2), but DSU asked in advanced topic).

## 10. Optimal Approach Explanation
Union connected city pairs and count distinct DSU roots.

## 11. Step-by-Step Logic
1. Initialize DSU for n cities.
2. For each pair (i,j) where isConnected[i][j]=1, union(i,j).
3. Count unique roots after all unions.

## 12. Time Complexity Analysis
O(n^2 * alpha(n)).

## 13. Space Complexity Analysis
O(n).

## 14. Clean and Production-Quality Java Code
```java
import java.util.*;

class Solution {
    public int findCircleNum(int[][] isConnected) {
        int n = isConnected.length;
        DSU dsu = new DSU(n);

        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                if (isConnected[i][j] == 1) {
                    dsu.union(i, j);
                }
            }
        }

        Set<Integer> roots = new HashSet<>();
        for (int i = 0; i < n; i++) {
            roots.add(dsu.find(i));
        }
        return roots.size();
    }

    private static class DSU {
        int[] parent;
        int[] rank;

        DSU(int n) {
            parent = new int[n];
            rank = new int[n];
            for (int i = 0; i < n; i++) {
                parent[i] = i;
            }
        }

        int find(int x) {
            if (parent[x] != x) {
                parent[x] = find(parent[x]);
            }
            return parent[x];
        }

        void union(int a, int b) {
            int pa = find(a);
            int pb = find(b);
            if (pa == pb) {
                return;
            }
            if (rank[pa] < rank[pb]) {
                parent[pa] = pb;
            } else if (rank[pa] > rank[pb]) {
                parent[pb] = pa;
            } else {
                parent[pb] = pa;
                rank[pa]++;
            }
        }
    }
}
```

## 15. Dry Run Example
Union(0,1) merges first two cities; city2 remains separate => 2 roots.

## 16. Common Interview Follow-Up Questions
- How does DSU compare with BFS/DFS here?
- How to support dynamic edge additions online?

## 17. Alternative Solutions if available
- DFS/BFS connected components on adjacency matrix.

## 18. Real Interview Context (why companies ask this problem)
DSU is essential for dynamic connectivity and MST-related problems.
