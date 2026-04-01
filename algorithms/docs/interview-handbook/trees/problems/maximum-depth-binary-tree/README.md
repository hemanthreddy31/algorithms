# Maximum Depth of Binary Tree

## 1. Problem Title
Maximum Depth of Binary Tree

## 2. Difficulty Level (Easy / Medium / Hard)
Easy

## 3. Problem Statement
Given root of a binary tree, return its maximum depth.

## 4. Input Format
root: binary tree root node.

## 5. Output Format
Return integer depth (number of nodes along longest root-to-leaf path).

## 6. Constraints (very important for interviews)
- 0 <= number of nodes <= 10^4
- -100 <= Node.val <= 100

## 7. Example Inputs and Outputs
Example 1
```text
Input: root=[3,9,20,null,null,15,7]
Output: 3
Explanation: Longest path has 3 nodes.
```

## 8. Edge Cases
- Empty tree depth is 0.
- Single node depth is 1.
- Skewed tree.

## 9. Brute Force Approach Explanation
Traverse all root-to-leaf paths and track maximum length.

## 10. Optimal Approach Explanation
DFS recursion: depth(node)=1+max(depth(left), depth(right)).

## 11. Step-by-Step Logic
1. If node is null return 0.
2. Recursively compute left and right depths.
3. Return 1 + max(left, right).

## 12. Time Complexity Analysis
O(n).

## 13. Space Complexity Analysis
O(h) recursion stack, h=tree height.

## 14. Clean and Production-Quality Java Code
```java
class Solution {
    public int maxDepth(TreeNode root) {
        if (root == null) {
            return 0;
        }
        return 1 + Math.max(maxDepth(root.left), maxDepth(root.right));
    }
}
```

## 15. Dry Run Example
For node 3: left depth=1 (node9), right depth=2 (20->15/7), so total=3.

## 16. Common Interview Follow-Up Questions
- Can you solve iteratively with BFS?
- How to compute minimum depth instead?

## 17. Alternative Solutions if available
- Level-order BFS counting levels.

## 18. Real Interview Context (why companies ask this problem)
Simple recursion baseline for tree traversal fluency.
