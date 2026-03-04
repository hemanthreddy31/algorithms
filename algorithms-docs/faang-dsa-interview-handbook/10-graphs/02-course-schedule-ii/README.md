# Course Schedule II

Topic: Graphs

## 1. Problem Title
Course Schedule II

## 2. Difficulty Level (Easy / Medium / Hard)
Medium

## 3. Problem Statement
Return valid course order or empty.

## 4. Input Format
Function arguments as described in the statement.

## 5. Output Format
Return the required result.

## 6. Constraints (very important for interviews)
- 1 <= n <= 2000

## 7. Example Inputs and Outputs
**Input**
```text
numCourses + prereqs
```
**Output**
```text
order or []
```

## 8. Edge Cases
- Empty/minimum input
- Duplicates/repeated values
- Boundary constraint values

## 9. Brute Force Approach Explanation
Enumerate all candidate states/combinations and validate directly; this usually fails upper constraints.

## 10. Optimal Approach Explanation
Apply the `graph` pattern to avoid repeated work and reduce asymptotic cost.

## 11. Step-by-Step Logic
1. Understand brute-force baseline.
1. Identify the key pattern from constraints.
1. Build the optimal data structure/state transition.
1. Iterate/DFS/BFS with invariant maintenance.
1. Return and verify against sample.

## 12. Time Complexity Analysis
O(V+E)

## 13. Space Complexity Analysis
O(V+E)

## 14. Clean and Production-Quality Java Code
```java
import java.util.*; class Solution { public int solve(char[][] g){ int m=g.length,n=g[0].length,c=0; for(int r=0;r<m;r++) for(int col=0;col<n;col++) if(g[r][col]=='1'){ c++; dfs(g,r,col);} return c;} void dfs(char[][] g,int r,int c){ if(r<0||c<0||r>=g.length||c>=g[0].length||g[r][c]!='1') return; g[r][c]='0'; dfs(g,r+1,c); dfs(g,r-1,c); dfs(g,r,c+1); dfs(g,r,c-1);} }
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
Topological ordering.
