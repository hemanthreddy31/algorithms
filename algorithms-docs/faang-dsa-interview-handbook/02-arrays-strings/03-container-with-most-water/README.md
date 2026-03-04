# Container With Most Water

Topic: Arrays and Strings

## 1. Problem Title
Container With Most Water

## 2. Difficulty Level (Easy / Medium / Hard)
Medium

## 3. Problem Statement
Max water between two lines.

## 4. Input Format
Function arguments as described in the statement.

## 5. Output Format
Return the required result.

## 6. Constraints (very important for interviews)
- 2 <= n <= 1e5

## 7. Example Inputs and Outputs
**Input**
```text
height=[1,8,6,2,5,4,8,3,7]
```
**Output**
```text
49
```

## 8. Edge Cases
- Empty/minimum input
- Duplicates/repeated values
- Boundary constraint values

## 9. Brute Force Approach Explanation
Enumerate all candidate states/combinations and validate directly; this usually fails upper constraints.

## 10. Optimal Approach Explanation
Apply the `two_pointers` pattern to avoid repeated work and reduce asymptotic cost.

## 11. Step-by-Step Logic
1. Understand brute-force baseline.
1. Identify the key pattern from constraints.
1. Build the optimal data structure/state transition.
1. Iterate/DFS/BFS with invariant maintenance.
1. Return and verify against sample.

## 12. Time Complexity Analysis
O(n)

## 13. Space Complexity Analysis
O(1)

## 14. Clean and Production-Quality Java Code
```java
class Solution { public int solve(int[] h){ int l=0,r=h.length-1,ans=0; while(l<r){ ans=Math.max(ans,Math.min(h[l],h[r])*(r-l)); if(h[l]<h[r]) l++; else r--; } return ans; } }
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
Two-pointer proof question.
