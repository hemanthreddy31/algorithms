# Unique Paths

## 1. Problem Title
Unique Paths

## 2. Difficulty Level (Easy / Medium / Hard)
Medium

## 3. Problem Statement
A robot is at top-left of m x n grid and can only move down or right. Return number of unique paths to bottom-right.

## 4. Input Format
m, n integers for grid dimensions.

## 5. Output Format
Return integer count of distinct paths.

## 6. Constraints (very important for interviews)
- 1 <= m, n <= 100

## 7. Example Inputs and Outputs
Example 1
```text
Input: m=3, n=7
Output: 28
Explanation: DP counts paths from top and left neighbors.
```

## 8. Edge Cases
- Single row or single column -> only one path.
- Square vs rectangular grids.

## 9. Brute Force Approach Explanation
Recursively branch right/down until destination. Exponential.

## 10. Optimal Approach Explanation
2D DP: dp[r][c] = dp[r-1][c] + dp[r][c-1].

## 11. Step-by-Step Logic
1. Initialize first row and first column with 1.
2. Fill remaining cells using top+left transitions.
3. Return dp[m-1][n-1].

## 12. Time Complexity Analysis
O(m*n).

## 13. Space Complexity Analysis
O(n) with rolling array optimization.

## 14. Clean and Production-Quality Java Code
```java
class Solution {
    public int uniquePaths(int m, int n) {
        int[] dp = new int[n];
        for (int c = 0; c < n; c++) {
            dp[c] = 1;
        }

        for (int r = 1; r < m; r++) {
            for (int c = 1; c < n; c++) {
                dp[c] += dp[c - 1];
            }
        }

        return dp[n - 1];
    }
}
```

## 15. Dry Run Example
For 3x3 grid dp rows evolve: [1,1,1] -> [1,2,3] -> [1,3,6].

## 16. Common Interview Follow-Up Questions
- How do obstacles change state transitions?
- Can you derive combinatorial formula directly?

## 17. Alternative Solutions if available
- Combinatorics C(m+n-2, m-1).

## 18. Real Interview Context (why companies ask this problem)
Standard 2D DP transition problem.
