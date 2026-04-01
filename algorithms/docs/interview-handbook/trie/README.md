# Trie Interview Track

Trie questions combine prefix retrieval with constrained DFS and dictionary compression.

## Pattern Recognition Cues
- Prefix-based lookup/auto-complete -> trie.
- Wildcard dictionary matching -> trie DFS.
- Bulk word matching in board/text -> trie + backtracking.
- Bitwise maximize/minimize XOR -> bit trie.

## Common Interview Traps
- High memory from full 26-array nodes when sparse.
- Not clearing visited state in board DFS.
- Returning duplicates in word-search outputs.
- Failing to terminate paths when prefix mismatch occurs.

## Solved Problem Ladder
| # | Problem | Difficulty |
|---|---|---|
| 1 | [Implement Trie (Prefix Tree)](problems/implement-trie/README.md) | Medium |
| 2 | [Design Add and Search Words Data Structure](problems/add-and-search-word/README.md) | Medium |
| 3 | [Replace Words](problems/replace-words/README.md) | Medium |
| 4 | [Word Search II](problems/word-search-ii/README.md) | Hard |
| 5 | [Maximum XOR of Two Numbers in an Array](problems/maximum-xor-two-numbers/README.md) | Medium |

## Additional High-Value Variations
- Map Sum Pairs
- Longest Word in Dictionary
- Stream of Characters
- Palindrome Pairs
- Prefix and Suffix Search
- Word Squares
- Implement Magic Dictionary
- Search Suggestions System

## How to Use This Topic
- Solve the ladder in order from Easy to Hard.
- For each problem, first speak brute force, then optimal, then code.
- Practice writing complexity and edge cases before coding.
