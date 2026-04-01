# Dynamic Programming Interview Track

DP rounds test state design, transition correctness, and optimization from exponential recursion to polynomial runtime.

## Pattern Recognition Cues
- Overlapping subproblems and optimal substructure -> DP.
- Choice at each index/item -> 1D or knapsack-style DP.
- Grid movement constraints -> 2D DP.
- Digit constraints across ranges -> digit DP.
- Tree include/exclude choices -> tree DP.

## Common Interview Traps
- State misses one dimension needed for correctness.
- Wrong transition order causing reuse bugs.
- Using recursion without memoization and timing out.
- Confusing subsequence vs substring transitions.

## Solved Problem Ladder
| # | Problem | Difficulty |
|---|---|---|
| 1 | [Climbing Stairs](problems/climbing-stairs/README.md) | Easy |
| 2 | [Unique Paths](problems/unique-paths/README.md) | Medium |
| 3 | [Coin Change](problems/coin-change/README.md) | Medium |
| 4 | [Longest Increasing Subsequence](problems/longest-increasing-subsequence/README.md) | Medium |
| 5 | [Partition Equal Subset Sum](problems/partition-equal-subset-sum/README.md) | Medium |
| 6 | [Burst Balloons (Partition DP)](problems/burst-balloons/README.md) | Hard |
| 7 | [Count Digit One (Digit DP)](problems/count-digit-one/README.md) | Hard |
| 8 | [House Robber III (Tree DP)](problems/house-robber-iii/README.md) | Medium |

## Additional High-Value Variations
- Edit Distance
- Distinct Subsequences
- Longest Common Subsequence
- Regular Expression Matching
- Best Time to Buy and Sell Stock with Cooldown
- Delete and Earn
- Palindrome Partitioning II
- Dungeon Game

## How to Use This Topic
- Solve the ladder in order from Easy to Hard.
- For each problem, first speak brute force, then optimal, then code.
- Practice writing complexity and edge cases before coding.
