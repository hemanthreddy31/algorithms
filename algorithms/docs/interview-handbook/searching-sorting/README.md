# Searching and Sorting Interview Track

Binary search templates, sorting-driven transformations, and order statistics are common for correctness + performance checks.

## Pattern Recognition Cues
- Monotonic answer space implies binary search on answer.
- Interval overlap/merging implies sort by start/end first.
- Need kth element without full sort implies heap or quickselect.
- Need first/last occurrence means lower/upper-bound style binary search.

## Common Interview Traps
- Infinite loop due to wrong mid movement.
- Unstable custom comparator causing inconsistent order.
- Forgetting randomized pivot in quickselect worst-case.
- Mixing half-open and closed interval boundaries.

## Solved Problem Ladder
| # | Problem | Difficulty |
|---|---|---|
| 1 | [Binary Search](problems/binary-search/README.md) | Easy |
| 2 | [Search in Rotated Sorted Array](problems/search-rotated-sorted-array/README.md) | Medium |
| 3 | [Find First and Last Position of Element in Sorted Array](problems/first-and-last-position/README.md) | Medium |
| 4 | [Kth Largest Element in an Array (Quickselect)](problems/kth-largest-quickselect/README.md) | Medium |
| 5 | [Merge Intervals](problems/merge-intervals/README.md) | Medium |

## Additional High-Value Variations
- Search a 2D Matrix
- Median of Two Sorted Arrays
- Sort Colors
- Largest Number
- Find Peak Element
- Capacity To Ship Packages Within D Days
- Koko Eating Bananas
- Divide Chocolate

## How to Use This Topic
- Solve the ladder in order from Easy to Hard.
- For each problem, first speak brute force, then optimal, then code.
- Practice writing complexity and edge cases before coding.
