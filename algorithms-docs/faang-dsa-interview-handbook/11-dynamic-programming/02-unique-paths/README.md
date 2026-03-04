# Unique Paths

Topic: Dynamic Programming

## 1. Problem Title
Unique Paths

## 2. Difficulty Level (Easy / Medium / Hard)
Medium

## 3. Problem Statement
Grid paths from top-left to bottom-right.

## 4. Input Format
Function arguments as described in the statement.

## 5. Output Format
Return the required result.

## 6. Constraints (very important for interviews)
- 1 <= m,n <= 100

## 7. Example Inputs and Outputs
**Input**
```text
m=3,n=7
```
**Output**
```text
28
```

## 8. Edge Cases
- Empty/minimum input
- Duplicates/repeated values
- Boundary constraint values

## 9. Brute Force Approach Explanation
Enumerate all candidate states/combinations and validate directly; this usually fails upper constraints.

## 10. Optimal Approach Explanation
Apply the `dp2d` pattern to avoid repeated work and reduce asymptotic cost.

## 11. Step-by-Step Logic
1. Understand brute-force baseline.
1. Identify the key pattern from constraints.
1. Build the optimal data structure/state transition.
1. Iterate/DFS/BFS with invariant maintenance.
1. Return and verify against sample.

## 12. Time Complexity Analysis
O(m*n)

## 13. Space Complexity Analysis
O(n)

## 14. Clean and Production-Quality Java Code
```java
class Solution { public int solve(int m,int n){ int[] dp=new int[n]; for(int i=0;i<n;i++) dp[i]=1; for(int r=1;r<m;r++) for(int c=1;c<n;c++) dp[c]+=dp[c-1]; return dp[n-1]; } }
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
2D DP compression.
