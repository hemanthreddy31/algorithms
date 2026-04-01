# Lowest Common Ancestor of a Binary Tree

## 1. Problem Title
Lowest Common Ancestor of a Binary Tree

## 2. Difficulty Level (Easy / Medium / Hard)
Medium

## 3. Problem Statement
Given a binary tree and two nodes p and q, return their lowest common ancestor.

## 4. Input Format
root: binary tree root, p and q: references to target nodes.

## 5. Output Format
Return TreeNode pointer representing LCA.

## 6. Constraints (very important for interviews)
- 2 <= number of nodes <= 10^5
- All Node.val are unique
- p and q exist in tree

## 7. Example Inputs and Outputs
Example 1
```text
Input: root=[3,5,1,6,2,0,8,null,null,7,4], p=5, q=1
Output: 3
Explanation: 3 is the first node containing both in different subtrees.
```

## 8. Edge Cases
- One node is ancestor of the other.
- p and q in same subtree.
- Highly unbalanced tree.

## 9. Brute Force Approach Explanation
Store parent pointers then build ancestor set for one node and walk other upward.

## 10. Optimal Approach Explanation
DFS returns non-null when subtree contains p or q. If both sides return non-null, current node is LCA.

## 11. Step-by-Step Logic
1. Base: null/p/q -> return node itself.
2. Recurse left and right.
3. If both non-null, return current; otherwise return non-null child.

## 12. Time Complexity Analysis
O(n).

## 13. Space Complexity Analysis
O(h).

## 14. Clean and Production-Quality Java Code
```java
class Solution {
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null || root == p || root == q) {
            return root;
        }

        TreeNode left = lowestCommonAncestor(root.left, p, q);
        TreeNode right = lowestCommonAncestor(root.right, p, q);

        if (left != null && right != null) {
            return root;
        }
        return left != null ? left : right;
    }
}
```

## 15. Dry Run Example
At node 3, left returns 5 and right returns 1, so LCA is 3.

## 16. Common Interview Follow-Up Questions
- How to solve when tree has parent pointers?
- How does solution change for BST?

## 17. Alternative Solutions if available
- Parent map + visited ancestor set.

## 18. Real Interview Context (why companies ask this problem)
Evaluates recursive decomposition and merge logic on trees.
