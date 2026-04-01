# Advanced Topics Interview Track

These topics appear in senior rounds where data structure design, offline preprocessing, and optimization rigor are evaluated.

## Pattern Recognition Cues
- Frequent range queries + point/range updates -> segment/fenwick trees.
- Static idempotent range queries -> sparse table.
- Dynamic connectivity merging -> DSU.
- Capacity-constrained path allocation -> max flow.
- Planar point-set boundary/area problems -> computational geometry.

## Common Interview Traps
- Lazy propagation bugs due to missed push-down.
- Fenwick off-by-one from zero-based indexing.
- Sparse table misuse on non-idempotent operations.
- Max-flow residual graph not updated both directions.

## Solved Problem Ladder
| # | Problem | Difficulty |
|---|---|---|
| 1 | [Range Sum Query - Mutable (Segment Tree)](problems/range-sum-query-mutable-segment-tree/README.md) | Medium |
| 2 | [Count of Smaller Numbers After Self (Fenwick Tree)](problems/count-smaller-after-self-fenwick/README.md) | Hard |
| 3 | [Static Range Minimum Query (Sparse Table)](problems/static-rmq-sparse-table/README.md) | Medium |
| 4 | [Number of Provinces (DSU)](problems/number-of-provinces-dsu/README.md) | Medium |
| 5 | [Maximum Flow (Edmonds-Karp)](problems/maximum-flow-edmonds-karp/README.md) | Hard |
| 6 | [Erect the Fence (Convex Hull)](problems/erect-the-fence-convex-hull/README.md) | Hard |

## Additional High-Value Variations
- Range Update and Range Query with Lazy Segment Tree
- Offline Queries with Mo's Algorithm
- DSU Rollback
- Minimum Cut
- Half-Plane Intersection
- Line Sweep for Rectangle Union Area
- Persistent Segment Tree
- Heavy-Light Decomposition

## How to Use This Topic
- Solve the ladder in order from Easy to Hard.
- For each problem, first speak brute force, then optimal, then code.
- Practice writing complexity and edge cases before coding.
