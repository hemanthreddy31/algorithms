# Validate Binary Search Tree

## 1. Problem Title
Validate Binary Search Tree

## 2. Difficulty Level (Easy / Medium / Hard)
Medium

## 3. Problem Statement
Given root of a binary tree, determine if it is a valid BST.

## 4. Input Format
root: binary tree root node.

## 5. Output Format
Return true if tree satisfies BST property strictly.

## 6. Constraints (very important for interviews)
- 1 <= number of nodes <= 10^4
- -2^31 <= Node.val <= 2^31 - 1

## 7. Example Inputs and Outputs
Example 1
```text
Input: root=[2,1,3]
Output: true
Explanation: Left < root < right holds for all nodes.
```
Example 2
```text
Input: root=[5,1,4,null,null,3,6]
Output: false
Explanation: Node 3 in right subtree violates global bound.
```

## 8. Edge Cases
- Duplicate values should fail for strict BST.
- Deep violation not near root.
- Integer boundary values.

## 9. Brute Force Approach Explanation
For each node, scan entire left and right subtree for violations. O(n^2).

## 10. Optimal Approach Explanation
Carry valid min/max bounds during DFS; each node must be in (low, high).

## 11. Step-by-Step Logic
1. Start with bounds (-inf, +inf).
2. At node, if value <= low or >= high return false.
3. Recurse left with high=node.val and right with low=node.val.

## 12. Time Complexity Analysis
O(n).

## 13. Space Complexity Analysis
O(h).

## 14. Clean and Production-Quality Java Code
```java
class Solution {
    public boolean isValidBST(TreeNode root) {
        return dfs(root, Long.MIN_VALUE, Long.MAX_VALUE);
    }

    private boolean dfs(TreeNode node, long low, long high) {
        if (node == null) {
            return true;
        }
        if (node.val <= low || node.val >= high) {
            return false;
        }
        return dfs(node.left, low, node.val) && dfs(node.right, node.val, high);
    }
}
```

## 15. Dry Run Example
Node 5 right child 4 violates low bound 5 -> false.

## 16. Common Interview Follow-Up Questions
- Can you validate using inorder traversal?
- How to fix a BST with two swapped nodes?

## 17. Alternative Solutions if available
- Inorder traversal must be strictly increasing.

## 18. Real Interview Context (why companies ask this problem)
Tests understanding of global constraints vs local parent-child checks.
