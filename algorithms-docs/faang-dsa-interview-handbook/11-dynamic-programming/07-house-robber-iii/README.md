# House Robber III

Topic: Dynamic Programming

## 1. Problem Title
House Robber III

## 2. Difficulty Level (Easy / Medium / Hard)
Medium

## 3. Problem Statement
Max rob value in tree without adjacent robbery.

## 4. Input Format
Function arguments as described in the statement.

## 5. Output Format
Return the required result.

## 6. Constraints (very important for interviews)
- 1 <= nodes <= 1e4

## 7. Example Inputs and Outputs
**Input**
```text
tree root
```
**Output**
```text
max value
```

## 8. Edge Cases
- Empty/minimum input
- Duplicates/repeated values
- Boundary constraint values

## 9. Brute Force Approach Explanation
Enumerate all candidate states/combinations and validate directly; this usually fails upper constraints.

## 10. Optimal Approach Explanation
Apply the `tree_dp` pattern to avoid repeated work and reduce asymptotic cost.

## 11. Step-by-Step Logic
1. Understand brute-force baseline.
1. Identify the key pattern from constraints.
1. Build the optimal data structure/state transition.
1. Iterate/DFS/BFS with invariant maintenance.
1. Return and verify against sample.

## 12. Time Complexity Analysis
O(n)

## 13. Space Complexity Analysis
O(h)

## 14. Clean and Production-Quality Java Code
```java
class Solution { static class TreeNode{int val;TreeNode left,right;TreeNode(int v){val=v;}} int best=Integer.MIN_VALUE; public int solve(TreeNode root){ gain(root); return best;} int gain(TreeNode n){ if(n==null) return 0; int l=Math.max(0,gain(n.left)), r=Math.max(0,gain(n.right)); best=Math.max(best,n.val+l+r); return n.val+Math.max(l,r);} }
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
Tree DP states.
