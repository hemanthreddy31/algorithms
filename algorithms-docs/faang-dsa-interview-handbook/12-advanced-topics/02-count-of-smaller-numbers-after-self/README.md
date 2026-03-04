# Count of Smaller Numbers After Self

Topic: Advanced Topics

## 1. Problem Title
Count of Smaller Numbers After Self

## 2. Difficulty Level (Easy / Medium / Hard)
Hard

## 3. Problem Statement
Count right-side smaller elements per index.

## 4. Input Format
Function arguments as described in the statement.

## 5. Output Format
Return the required result.

## 6. Constraints (very important for interviews)
- 1 <= n <= 1e5

## 7. Example Inputs and Outputs
**Input**
```text
nums=[5,2,6,1]
```
**Output**
```text
[2,1,1,0]
```

## 8. Edge Cases
- Empty/minimum input
- Duplicates/repeated values
- Boundary constraint values

## 9. Brute Force Approach Explanation
Enumerate all candidate states/combinations and validate directly; this usually fails upper constraints.

## 10. Optimal Approach Explanation
Apply the `advanced` pattern to avoid repeated work and reduce asymptotic cost.

## 11. Step-by-Step Logic
1. Understand brute-force baseline.
1. Identify the key pattern from constraints.
1. Build the optimal data structure/state transition.
1. Iterate/DFS/BFS with invariant maintenance.
1. Return and verify against sample.

## 12. Time Complexity Analysis
O(n log n)

## 13. Space Complexity Analysis
O(n)

## 14. Clean and Production-Quality Java Code
```java
import java.util.*; class Solution { public int solve(int[] a){ int ans=0; for(int x:a) ans=Math.max(ans,x); return ans; } }
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
Fenwick tree pattern.
