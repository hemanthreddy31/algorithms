# Heaps Interview Track

Priority queues are core for top-k, scheduling, streaming medians, and multi-source merge workflows.

## Pattern Recognition Cues
- Need repeated smallest/largest extraction -> heap.
- Top k among large n -> min-heap of size k.
- Online median -> two heaps.
- Merge many sorted streams -> heap of heads.

## Common Interview Traps
- Using max-heap when min-heap is needed (or vice versa).
- Comparator overflow via subtraction.
- Forgetting heap rebalance in two-heap median.
- Pushing all elements when bounded heap is enough.

## Solved Problem Ladder
| # | Problem | Difficulty |
|---|---|---|
| 1 | [Kth Largest Element in a Stream](problems/kth-largest-stream/README.md) | Easy |
| 2 | [Top K Frequent Elements](problems/top-k-frequent-elements-heap/README.md) | Medium |
| 3 | [Find Median from Data Stream](problems/find-median-from-data-stream/README.md) | Hard |
| 4 | [Merge k Sorted Lists](problems/merge-k-sorted-lists/README.md) | Hard |
| 5 | [Meeting Rooms II](problems/meeting-rooms-ii/README.md) | Medium |

## Additional High-Value Variations
- IPO
- Reorganize String
- K Closest Points to Origin
- Ugly Number II
- Smallest Range Covering Elements from K Lists
- Find K Pairs with Smallest Sums
- The Skyline Problem
- Furthest Building You Can Reach

## How to Use This Topic
- Solve the ladder in order from Easy to Hard.
- For each problem, first speak brute force, then optimal, then code.
- Practice writing complexity and edge cases before coding.
