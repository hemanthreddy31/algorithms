# Min Cost to Connect All Points

Topic: Graphs

## 1. Problem Title
Min Cost to Connect All Points

## 2. Difficulty Level (Easy / Medium / Hard)
Medium

## 3. Problem Statement
Connect all points with minimum total cost.

## 4. Input Format
Function arguments as described in the statement.

## 5. Output Format
Return the required result.

## 6. Constraints (very important for interviews)
- 1 <= n <= 1000

## 7. Example Inputs and Outputs
**Input**
```text
points array
```
**Output**
```text
min cost
```

## 8. Edge Cases
- Empty/minimum input
- Duplicates/repeated values
- Boundary constraint values

## 9. Brute Force Approach Explanation
Enumerate all candidate states/combinations and validate directly; this usually fails upper constraints.

## 10. Optimal Approach Explanation
Apply the `mst` pattern to avoid repeated work and reduce asymptotic cost.

## 11. Step-by-Step Logic
1. Understand brute-force baseline.
1. Identify the key pattern from constraints.
1. Build the optimal data structure/state transition.
1. Iterate/DFS/BFS with invariant maintenance.
1. Return and verify against sample.

## 12. Time Complexity Analysis
O(n^2)

## 13. Space Complexity Analysis
O(n)

## 14. Clean and Production-Quality Java Code
```java
import java.util.*; class Solution { public int solve(int[][] p){ int n=p.length,ans=0; boolean[] used=new boolean[n]; int[] d=new int[n]; Arrays.fill(d,Integer.MAX_VALUE); d[0]=0; for(int i=0;i<n;i++){ int u=-1; for(int v=0;v<n;v++) if(!used[v]&&(u==-1||d[v]<d[u])) u=v; used[u]=true; ans+=d[u]; for(int v=0;v<n;v++) if(!used[v]){ int w=Math.abs(p[u][0]-p[v][0])+Math.abs(p[u][1]-p[v][1]); if(w<d[v]) d[v]=w; } } return ans; } }
```

## 15. Dry Run Example
Track the sample input through each core state update and confirm it produces the sample output.

## 16. Common Interview Follow-Up Questions
- Can this be done in-place or with less memory?
- How would you adapt this for streaming/online input?
- Which constraints break this solution?

## 17. Alternative Solutions if available
- A simpler implementation often exists with slightly worse complexity.
- Mention tradeoffs explicitly during interview discussion.

## 18. Real Interview Context (why companies ask this problem)
MST (Prim/Kruskal).
