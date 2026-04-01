# Binary Tree Maximum Path Sum

## 1. Problem Title
Binary Tree Maximum Path Sum

## 2. Difficulty Level (Easy / Medium / Hard)
Hard

## 3. Problem Statement
Given a non-empty binary tree, return the maximum path sum. A path may start and end at any nodes, but must go along parent-child edges.

## 4. Input Format
root: binary tree root.

## 5. Output Format
Return maximum path sum as integer.

## 6. Constraints (very important for interviews)
- 1 <= number of nodes <= 3 * 10^4
- -1000 <= Node.val <= 1000

## 7. Example Inputs and Outputs
Example 1
```text
Input: root=[-10,9,20,null,null,15,7]
Output: 42
Explanation: Best path is 15 -> 20 -> 7.
```

## 8. Edge Cases
- All negative nodes => best single node.
- Best path passes through root or not.
- Skewed tree.

## 9. Brute Force Approach Explanation
Enumerate all possible paths between node pairs and compute sum, which is expensive.

## 10. Optimal Approach Explanation
Tree DP: each node contributes max one-side gain upward; update global answer with leftGain + node + rightGain.

## 11. Step-by-Step Logic
1. DFS returns max gain from node to parent (single branch).
2. Clamp negative child gains to 0.
3. Update global max with split path through current node.

## 12. Time Complexity Analysis
O(n).

## 13. Space Complexity Analysis
O(h).

## 14. Clean and Production-Quality Java Code
```java
class Solution {
    private int best = Integer.MIN_VALUE;

    public int maxPathSum(TreeNode root) {
        gain(root);
        return best;
    }

    private int gain(TreeNode node) {
        if (node == null) {
            return 0;
        }

        int left = Math.max(0, gain(node.left));
        int right = Math.max(0, gain(node.right));

        best = Math.max(best, node.val + left + right);

        return node.val + Math.max(left, right);
    }
}
```

## 15. Dry Run Example
At node 20: left gain=15, right gain=7, path sum=42 updates global max.

## 16. Common Interview Follow-Up Questions
- How to also return the path nodes?
- How does this compare with tree diameter logic?

## 17. Alternative Solutions if available
- Postorder with explicit stack simulating recursion.

## 18. Real Interview Context (why companies ask this problem)
Hard tree DP question frequently used to test local/global state separation.
