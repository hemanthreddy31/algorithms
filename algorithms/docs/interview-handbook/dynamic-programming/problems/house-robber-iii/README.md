# House Robber III (Tree DP)

## 1. Problem Title
House Robber III (Tree DP)

## 2. Difficulty Level (Easy / Medium / Hard)
Medium

## 3. Problem Statement
Given root of binary tree where each node value is money, maximize amount robbed without robbing directly-linked parent-child houses.

## 4. Input Format
root: binary tree root.

## 5. Output Format
Return maximum robbed amount.

## 6. Constraints (very important for interviews)
- 1 <= number of nodes <= 10^4
- 0 <= Node.val <= 10^4

## 7. Example Inputs and Outputs
Example 1
```text
Input: root=[3,2,3,null,3,null,1]
Output: 7
Explanation: Rob nodes 3 + 3 + 1.
```

## 8. Edge Cases
- Single node tree.
- Skewed tree.
- All zero values.

## 9. Brute Force Approach Explanation
At each node choose rob/skip recursively without memo -> exponential.

## 10. Optimal Approach Explanation
Tree DP returns two values for each node: [robThis, skipThis].

## 11. Step-by-Step Logic
1. DFS postorder to compute child states.
2. rob = node.val + left.skip + right.skip.
3. skip = max(left.rob,left.skip) + max(right.rob,right.skip).
4. Answer is max(root.rob, root.skip).

## 12. Time Complexity Analysis
O(n).

## 13. Space Complexity Analysis
O(h).

## 14. Clean and Production-Quality Java Code
```java
class Solution {
    public int rob(TreeNode root) {
        int[] state = dfs(root);
        return Math.max(state[0], state[1]);
    }

    // state[0] = rob this node, state[1] = skip this node
    private int[] dfs(TreeNode node) {
        if (node == null) {
            return new int[] {0, 0};
        }

        int[] left = dfs(node.left);
        int[] right = dfs(node.right);

        int rob = node.val + left[1] + right[1];
        int skip = Math.max(left[0], left[1]) + Math.max(right[0], right[1]);

        return new int[] {rob, skip};
    }
}
```

## 15. Dry Run Example
For leaf 3 => [3,0]. Parent 2 with right 3 => rob=2, skip=3.

## 16. Common Interview Follow-Up Questions
- How to return robbed node set as well?
- What if graph is not a tree?

## 17. Alternative Solutions if available
- Memoization by node with rob/notRob flag.

## 18. Real Interview Context (why companies ask this problem)
Important tree-DP pattern with include/exclude state.
