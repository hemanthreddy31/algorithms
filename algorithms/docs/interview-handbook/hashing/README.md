# Hashing Interview Track

Hash-based lookups convert many O(n^2) scans into linear passes and are a staple in screening rounds.

## Pattern Recognition Cues
- Need fast membership check -> HashSet.
- Need counts/frequencies -> HashMap.
- Canonical representation grouping -> hash by normalized key.
- Prefix sum + target relation often needs map of seen prefixes.

## Common Interview Traps
- Assuming hash iteration order is stable.
- Ignoring collisions/load-factor tradeoffs in design rounds.
- Using mutable objects as map keys incorrectly.
- Overwriting earliest index needed for longest-range answers.

## Solved Problem Ladder
| # | Problem | Difficulty |
|---|---|---|
| 1 | [Two Sum](problems/two-sum/README.md) | Easy |
| 2 | [Valid Anagram](problems/valid-anagram/README.md) | Easy |
| 3 | [Group Anagrams](problems/group-anagrams/README.md) | Medium |
| 4 | [Longest Consecutive Sequence](problems/longest-consecutive-sequence/README.md) | Medium |
| 5 | [Isomorphic Strings](problems/isomorphic-strings/README.md) | Easy |

## Additional High-Value Variations
- Happy Number
- Contains Duplicate II
- Longest Substring with At Most K Distinct Characters
- First Unique Character in a String
- Design HashMap
- Randomized Set
- Encode and Decode TinyURL
- Find Duplicate Files in System

## How to Use This Topic
- Solve the ladder in order from Easy to Hard.
- For each problem, first speak brute force, then optimal, then code.
- Practice writing complexity and edge cases before coding.
