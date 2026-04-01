# Binary Tree Level Order Traversal

## 1. Problem Title
Binary Tree Level Order Traversal

## 2. Difficulty Level (Easy / Medium / Hard)
Medium

## 3. Problem Statement
Given root of binary tree, return level order traversal of node values.

## 4. Input Format
root: binary tree root node.

## 5. Output Format
Return list of levels, each level is list of integers.

## 6. Constraints (very important for interviews)
- 0 <= number of nodes <= 2000
- -1000 <= Node.val <= 1000

## 7. Example Inputs and Outputs
Example 1
```text
Input: root=[3,9,20,null,null,15,7]
Output: [[3],[9,20],[15,7]]
Explanation: BFS processes one level at a time.
```

## 8. Edge Cases
- Empty tree returns empty list.
- Skewed tree gives one value per level.

## 9. Brute Force Approach Explanation
DFS and track depth in map/list; then collect by depth.

## 10. Optimal Approach Explanation
Use BFS queue. For each loop, process current queue size as one level.

## 11. Step-by-Step Logic
1. If root null return empty.
2. Push root to queue.
3. For each level-size batch, pop nodes, collect values, push children.

## 12. Time Complexity Analysis
O(n).

## 13. Space Complexity Analysis
O(w) where w is max width of tree.

## 14. Clean and Production-Quality Java Code
```java
import java.util.*;

class Solution {
    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> result = new ArrayList<>();
        if (root == null) {
            return result;
        }

        Queue<TreeNode> queue = new ArrayDeque<>();
        queue.offer(root);

        while (!queue.isEmpty()) {
            int size = queue.size();
            List<Integer> level = new ArrayList<>(size);
            for (int i = 0; i < size; i++) {
                TreeNode node = queue.poll();
                level.add(node.val);
                if (node.left != null) {
                    queue.offer(node.left);
                }
                if (node.right != null) {
                    queue.offer(node.right);
                }
            }
            result.add(level);
        }

        return result;
    }
}
```

## 15. Dry Run Example
Queue: [3] -> level[3], enqueue 9,20 -> next level [9,20].

## 16. Common Interview Follow-Up Questions
- How to return zigzag level order?
- How to compute right side view?

## 17. Alternative Solutions if available
- DFS preorder with depth-indexed lists.

## 18. Real Interview Context (why companies ask this problem)
Core BFS tree traversal used in many system modeling questions.
