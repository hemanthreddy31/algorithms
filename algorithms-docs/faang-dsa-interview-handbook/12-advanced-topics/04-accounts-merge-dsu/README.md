# Accounts Merge (DSU)

Topic: Advanced Topics

## 1. Problem Title
Accounts Merge (DSU)

## 2. Difficulty Level (Easy / Medium / Hard)
Medium

## 3. Problem Statement
Merge accounts sharing emails.

## 4. Input Format
Function arguments as described in the statement.

## 5. Output Format
Return the required result.

## 6. Constraints (very important for interviews)
- 1 <= accounts <= 1000

## 7. Example Inputs and Outputs
**Input**
```text
accounts matrix
```
**Output**
```text
merged accounts
```

## 8. Edge Cases
- Empty/minimum input
- Duplicates/repeated values
- Boundary constraint values

## 9. Brute Force Approach Explanation
Enumerate all candidate states/combinations and validate directly; this usually fails upper constraints.

## 10. Optimal Approach Explanation
Apply the `uf` pattern to avoid repeated work and reduce asymptotic cost.

## 11. Step-by-Step Logic
1. Understand brute-force baseline.
1. Identify the key pattern from constraints.
1. Build the optimal data structure/state transition.
1. Iterate/DFS/BFS with invariant maintenance.
1. Return and verify against sample.

## 12. Time Complexity Analysis
Near linear

## 13. Space Complexity Analysis
O(total emails)

## 14. Clean and Production-Quality Java Code
```java
class Solution { static class DSU{ int[] p,r; int c; DSU(int n){ c=n; p=new int[n]; r=new int[n]; for(int i=0;i<n;i++) p[i]=i;} int f(int x){ if(p[x]!=x) p[x]=f(p[x]); return p[x]; } void u(int a,int b){ int ra=f(a),rb=f(b); if(ra==rb) return; if(r[ra]<r[rb]) p[ra]=rb; else if(r[rb]<r[ra]) p[rb]=ra; else { p[rb]=ra; r[ra]++; } c--; }} public int solve(int[][] g){ int n=g.length; DSU d=new DSU(n); for(int i=0;i<n;i++) for(int j=i+1;j<n;j++) if(g[i][j]==1) d.u(i,j); return d.c; } }
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
DSU real use-case.
