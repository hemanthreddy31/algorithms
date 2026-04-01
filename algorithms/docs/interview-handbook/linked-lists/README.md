# Linked Lists Interview Track

Pointer discipline problems test precision under pressure: traversal invariants, in-place mutation, and node ownership.

## Pattern Recognition Cues
- Need kth from end -> fast/slow pointers.
- Cycle-related language -> Floyd tortoise-hare.
- In-place reorder often means split + reverse + merge.
- Cache design usually combines hashmap + doubly linked list.

## Common Interview Traps
- Losing next pointer during rewiring.
- Forgetting dummy node for head-edge cases.
- Not handling 1-node or 2-node lists.
- Memory leaks in custom cache node detach/attach.

## Solved Problem Ladder
| # | Problem | Difficulty |
|---|---|---|
| 1 | [Reverse Linked List](problems/reverse-linked-list/README.md) | Easy |
| 2 | [Linked List Cycle](problems/linked-list-cycle/README.md) | Easy |
| 3 | [Remove Nth Node From End of List](problems/remove-nth-node-from-end/README.md) | Medium |
| 4 | [Merge Two Sorted Lists](problems/merge-two-sorted-lists/README.md) | Easy |
| 5 | [LRU Cache](problems/lru-cache/README.md) | Medium |

## Additional High-Value Variations
- Copy List with Random Pointer
- Reverse Nodes in k-Group
- Intersection of Two Linked Lists
- Palindrome Linked List
- Flatten a Multilevel Doubly Linked List
- Sort List
- Swap Nodes in Pairs
- Rotate List

## How to Use This Topic
- Solve the ladder in order from Easy to Hard.
- For each problem, first speak brute force, then optimal, then code.
- Practice writing complexity and edge cases before coding.
