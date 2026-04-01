# Min Cost to Connect All Points (MST)

## 1. Problem Title
Min Cost to Connect All Points (MST)

## 2. Difficulty Level (Easy / Medium / Hard)
Medium

## 3. Problem Statement
Given points in 2D, return minimum cost to connect all points where cost between points i,j is Manhattan distance.

## 4. Input Format
points: 2D integer array [x,y].

## 5. Output Format
Return integer minimum spanning tree total cost.

## 6. Constraints (very important for interviews)
- 1 <= points.length <= 1000
- -10^6 <= xi, yi <= 10^6

## 7. Example Inputs and Outputs
Example 1
```text
Input: points=[[0,0],[2,2],[3,10],[5,2],[7,0]]
Output: 20
Explanation: MST picks cheapest edges without cycles.
```

## 8. Edge Cases
- Single point => cost 0.
- Duplicate coordinates.
- Dense complete graph (implicit edges).

## 9. Brute Force Approach Explanation
Generate all spanning trees and choose minimum; combinatorially impossible.

## 10. Optimal Approach Explanation
Prim's algorithm on implicit complete graph using min distance to current MST.

## 11. Step-by-Step Logic
1. Maintain inMST array and best known connection cost for each point.
2. Repeatedly pick non-MST point with smallest cost.
3. Update neighbor costs via Manhattan distance.

## 12. Time Complexity Analysis
O(n^2) with array-based Prim (no explicit edges).

## 13. Space Complexity Analysis
O(n).

## 14. Clean and Production-Quality Java Code
```java
import java.util.*;

class Solution {
    public int minCostConnectPoints(int[][] points) {
        int n = points.length;
        int[] minDist = new int[n];
        Arrays.fill(minDist, Integer.MAX_VALUE);
        boolean[] inMST = new boolean[n];
        minDist[0] = 0;

        int total = 0;
        for (int i = 0; i < n; i++) {
            int u = -1;
            for (int v = 0; v < n; v++) {
                if (!inMST[v] && (u == -1 || minDist[v] < minDist[u])) {
                    u = v;
                }
            }

            inMST[u] = true;
            total += minDist[u];

            for (int v = 0; v < n; v++) {
                if (!inMST[v]) {
                    int cost = Math.abs(points[u][0] - points[v][0]) + Math.abs(points[u][1] - points[v][1]);
                    if (cost < minDist[v]) {
                        minDist[v] = cost;
                    }
                }
            }
        }

        return total;
    }
}
```

## 15. Dry Run Example
Start with point0, nearest point1 cost4, then point3 cost3, etc; sum selected edges = 20.

## 16. Common Interview Follow-Up Questions
- How to solve with Kruskal + DSU when edges are explicit?
- How would Euclidean distance change complexity?

## 17. Alternative Solutions if available
- Kruskal with all O(n^2) edges and DSU.

## 18. Real Interview Context (why companies ask this problem)
MST fundamentals are frequently tested for graph optimization reasoning.
