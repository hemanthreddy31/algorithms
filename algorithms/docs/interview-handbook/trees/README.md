# Trees Interview Track

Tree rounds test recursion fluency, traversal control, and local-to-global state transitions.

## Pattern Recognition Cues
- Path property from root to leaf -> DFS recursion.
- Level-wise output -> BFS queue.
- Ancestor relation -> LCA patterns.
- Compute best value with child dependencies -> tree DP.

## Common Interview Traps
- Returning wrong null/base values.
- Confusing node count vs edge count (diameter-like).
- Global state not reset between test cases.
- For BST checks, using local parent comparison only instead of range bounds.

## Solved Problem Ladder
| # | Problem | Difficulty |
|---|---|---|
| 1 | [Maximum Depth of Binary Tree](problems/maximum-depth-binary-tree/README.md) | Easy |
| 2 | [Binary Tree Level Order Traversal](problems/binary-tree-level-order/README.md) | Medium |
| 3 | [Validate Binary Search Tree](problems/validate-bst/README.md) | Medium |
| 4 | [Lowest Common Ancestor of a Binary Tree](problems/lowest-common-ancestor-bt/README.md) | Medium |
| 5 | [Binary Tree Maximum Path Sum](problems/binary-tree-maximum-path-sum/README.md) | Hard |

## Additional High-Value Variations
- Serialize and Deserialize Binary Tree
- Construct Binary Tree from Preorder and Inorder Traversal
- Kth Smallest Element in a BST
- Binary Tree Right Side View
- Path Sum III
- Flatten Binary Tree to Linked List
- Count Complete Tree Nodes
- Recover Binary Search Tree

## How to Use This Topic
- Solve the ladder in order from Easy to Hard.
- For each problem, first speak brute force, then optimal, then code.
- Practice writing complexity and edge cases before coding.
