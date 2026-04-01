# Arrays and Strings Interview Track

Most interview rounds begin here: linear scans, windows, prefix structures, two pointers, and string matching.

## Pattern Recognition Cues
- Contiguous segment language usually means subarray/window/prefix-sum.
- Sorted array + pair/triplet target often means two pointers.
- Next greater/smaller element hints monotonic stack.
- Pattern search in text hints KMP or Rabin-Karp.

## Common Interview Traps
- Incorrect window shrink condition.
- Off-by-one in prefix arrays.
- Not deduplicating tuples in 3Sum-like problems.
- Wrong failure table transitions in KMP.

## Solved Problem Ladder
| # | Problem | Difficulty |
|---|---|---|
| 1 | [Range Sum Query - Immutable](problems/range-sum-query-immutable/README.md) | Easy |
| 2 | [Minimum Size Subarray Sum](problems/minimum-size-subarray-sum/README.md) | Medium |
| 3 | [3Sum](problems/three-sum/README.md) | Medium |
| 4 | [Subarray Sum Equals K](problems/subarray-sum-equals-k/README.md) | Medium |
| 5 | [Find the Index of the First Occurrence in a String (KMP)](problems/first-occurrence-kmp/README.md) | Medium |
| 6 | [Rabin-Karp Substring Search (Rolling Hash)](problems/rabin-karp-substring-search/README.md) | Medium |

## Additional High-Value Variations
- Trapping Rain Water
- Product of Array Except Self
- Minimum Window Substring
- Longest Palindromic Substring
- Rabin-Karp Multiple Pattern Search
- Longest Repeating Character Replacement
- Subarray Product Less Than K
- Find All Anagrams in a String

## How to Use This Topic
- Solve the ladder in order from Easy to Hard.
- For each problem, first speak brute force, then optimal, then code.
- Practice writing complexity and edge cases before coding.
