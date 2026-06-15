# Data Structures & Algorithms — Complete Concepts Guide

> A complete, interview-focused map of every Data Structures & Algorithms (DSA) concept you need to crack the technical coding rounds at **Google**, **Amazon**, **Salesforce**, and other top-tier (FAANG/MAANG) companies.

This guide is compiled and cross-referenced from the most trusted interview-prep sources — **Blind 75**, **Grind 75 / Tech Interview Handbook**, **NeetCode 150/250 & Roadmap**, **GeeksforGeeks**, **LeetCode Top Interview** lists, and **CLRS / Cracking the Coding Interview**.

---

## Table of Contents

1. [How to Use This Guide](#1-how-to-use-this-guide)
2. [Complexity Analysis (Big-O) — The Foundation](#2-complexity-analysis-big-o--the-foundation)
3. [Core Data Structures](#3-core-data-structures)
4. [Core Algorithms](#4-core-algorithms)
5. [Algorithmic Patterns & Techniques](#5-algorithmic-patterns--techniques)
6. [Topic-by-Topic Concept Checklist (Roadmap Order)](#6-topic-by-topic-concept-checklist-roadmap-order)
7. [Company-Specific Focus Areas](#7-company-specific-focus-areas)
8. [Must-Know Problem Patterns (with Signals)](#8-must-know-problem-patterns-with-signals)
9. [Curated Problem Lists](#9-curated-problem-lists)
10. [Suggested Study Plan](#10-suggested-study-plan)
11. [Resources](#11-resources)

---

## 1. How to Use This Guide

- **Master the fundamentals first** — Big-O, arrays, hashing, recursion. Everything builds on these.
- **Learn patterns, not problems.** Most interview questions are variations of ~15 core patterns (Section 8).
- **Follow the roadmap order** in Section 6 — each topic builds on the previous one.
- **Target volume:** ~150–250 quality problems with spaced repetition beats 600 rushed ones.
- **Time yourself:** Easy ≤ 15 min, Medium ≤ 30 min, Hard ≤ 45 min.
- **Always state complexity** (time + space) and discuss trade-offs out loud — this is graded.

---

## 2. Complexity Analysis (Big-O) — The Foundation

| Concept | What to Know |
|---|---|
| **Asymptotic notation** | Big-O (upper bound), Big-Ω (lower bound), Big-Θ (tight bound) |
| **Time complexity** | Counting operations as input grows |
| **Space complexity** | Auxiliary space vs. total space; recursion stack cost |
| **Amortized analysis** | e.g. dynamic array push, hash table resize |
| **Best / Average / Worst case** | Especially for quicksort, hashing |

**Complexity hierarchy (fastest → slowest):**

$$O(1) < O(\log n) < O(\sqrt{n}) < O(n) < O(n \log n) < O(n^2) < O(n^3) < O(2^n) < O(n!)$$

**Common complexities to recognize on sight:**
- `O(1)` — hash lookup, array index, stack push/pop
- `O(log n)` — binary search, balanced BST ops, heap push/pop
- `O(n)` — single pass, two pointers, BFS/DFS over array
- `O(n log n)` — efficient sorting, heap-based, divide & conquer
- `O(n²)` — nested loops, naive DP, bubble/insertion sort
- `O(2ⁿ)` / `O(n!)` — subsets, permutations, brute-force backtracking

---

## 3. Core Data Structures

### 3.1 Linear Structures
- **Arrays / Dynamic Arrays** — indexing, traversal, in-place modification, resizing, amortized append
- **Strings** — immutability, character arrays, `StringBuilder`/`StringBuffer`, ASCII/Unicode, string interning
- **Linked Lists** — singly, doubly, circular; insertion/deletion, pointer manipulation, dummy nodes
- **Stacks** — LIFO, monotonic stack, function call stack, array vs. linked implementation
- **Queues** — FIFO, circular queue, **deque** (double-ended), monotonic deque
- **Hash Tables / Hash Maps / Hash Sets** — hashing, collision resolution (chaining, open addressing), load factor, rehashing

### 3.2 Tree Structures
- **Binary Trees** — traversals (pre/in/post-order, level-order), height/depth, diameter
- **Binary Search Trees (BST)** — ordered property, insert/delete/search, validation, successor/predecessor
- **Self-Balancing Trees** — AVL trees, Red-Black trees (concepts + when they matter)
- **Heaps / Priority Queues** — min-heap, max-heap, heapify, sift up/down, `k`-th element problems
- **Tries (Prefix Trees)** — insert/search/prefix, autocomplete, word dictionaries
- **N-ary Trees** — generic tree traversal
- **Segment Trees & Fenwick (Binary Indexed) Trees** — range queries/updates (advanced; Google-heavy)
- **B-Trees / B+ Trees** — conceptual (database/indexing context)

### 3.3 Graph Structures
- **Representations** — adjacency list, adjacency matrix, edge list
- **Types** — directed/undirected, weighted/unweighted, cyclic/acyclic (DAG), connected components
- **Disjoint Set Union (Union-Find)** — union by rank, path compression

### 3.4 Specialized / Probabilistic (mention-level, mostly Google)
- **LRU / LFU Cache** — hashmap + doubly linked list
- **Bloom Filters**, **Skip Lists**, **Suffix Trees/Arrays** — know what they are and when used

---

## 4. Core Algorithms

### 4.1 Searching
- **Linear Search** — `O(n)`
- **Binary Search** — on sorted arrays, **on the answer space**, rotated arrays, first/last occurrence, boundary conditions
- **Ternary Search** — unimodal functions (rare)

### 4.2 Sorting
- **Comparison sorts** — Bubble, Selection, Insertion, **Merge Sort**, **Quick Sort** (+ partition schemes), **Heap Sort**
- **Non-comparison sorts** — Counting Sort, Radix Sort, Bucket Sort
- **Properties** — stability, in-place, time/space trade-offs
- **Custom comparators** — sorting by multiple keys

### 4.3 Recursion & Divide and Conquer
- Base case + recursive case, recursion tree, **call stack** cost
- Master Theorem (conceptual), merge sort / quick sort framing
- Tail recursion, recursion → iteration conversion

### 4.4 Graph Algorithms
- **Traversals** — BFS, DFS (recursive + iterative)
- **Shortest Path** — Dijkstra, Bellman-Ford, Floyd-Warshall, BFS for unweighted, **0-1 BFS**, A* (mention)
- **Minimum Spanning Tree** — Prim's, Kruskal's
- **Topological Sort** — Kahn's (BFS) & DFS-based; cycle detection in DAG
- **Connectivity** — connected components, **strongly connected components** (Tarjan/Kosaraju — advanced), bridges/articulation points (advanced)
- **Cycle detection** — directed (colors/recursion stack) & undirected (union-find/DFS)

### 4.5 Dynamic Programming
- **Foundations** — overlapping subproblems, optimal substructure, memoization (top-down) vs. tabulation (bottom-up), state definition, transition, space optimization
- **Classic 1-D DP** — Fibonacci, climbing stairs, house robber, max subarray (Kadane), decode ways, word break
- **Knapsack family** — 0/1 knapsack, unbounded knapsack, subset sum, partition equal subset
- **Sequence DP** — Longest Increasing Subsequence (LIS), Longest Common Subsequence (LCS), edit distance
- **String DP** — palindromic substrings/subsequences, regex/wildcard matching, distinct subsequences
- **2-D / Grid DP** — unique paths, minimum path sum, dungeon game
- **Interval DP** — matrix chain, burst balloons
- **DP on Trees / Graphs** — tree DP, DP on DAG
- **Bitmask DP** — TSP-style, state compression (advanced; Google)

### 4.6 Greedy Algorithms
- Exchange argument / proof of correctness intuition
- Interval scheduling, activity selection, Huffman coding
- Jump game, gas station, task scheduler

### 4.7 Backtracking
- Decision tree, choose–explore–unchoose, pruning
- Subsets, permutations, combinations, combination sum
- N-Queens, Sudoku solver, word search, palindrome partitioning

### 4.8 Mathematical & Bit Algorithms
- **Number theory** — GCD/LCM (Euclid), modular arithmetic, prime sieve (Eratosthenes), fast exponentiation
- **Bit manipulation** — AND/OR/XOR/NOT, shifts, masks, set/clear/toggle bit, count set bits, power-of-two checks, XOR tricks (single number)
- **Geometry** — points/lines, convex hull (mention), overlap/intersection
- **Combinatorics** — permutations/combinations counting, Pascal's triangle, Catalan numbers (mention)

### 4.9 String Algorithms
- Pattern matching — naive, **KMP**, Rabin-Karp (rolling hash), Z-algorithm (advanced)
- Anagrams, palindromes, substring search, string parsing/tokenizing

---

## 5. Algorithmic Patterns & Techniques

These reusable **patterns** unlock the majority of medium/hard problems:

- **Two Pointers** — opposite ends, same direction, fast & slow (Floyd's cycle detection)
- **Sliding Window** — fixed & variable size, longest/shortest substring, window with constraints
- **Prefix Sum / Difference Array** — range sums, subarray sum equals K, running totals
- **Fast & Slow Pointers** — cycle detection, middle of list, happy number
- **Merge Intervals** — overlapping intervals, insert/merge, meeting rooms
- **Cyclic Sort** — numbers in range `[1..n]`, find missing/duplicate
- **In-place Linked List Reversal** — reverse list / sublist, swap pairs
- **Top-K Elements** — heap-based selection, quickselect
- **K-way Merge** — merge k sorted lists/arrays with a heap
- **Monotonic Stack/Deque** — next greater element, daily temperatures, sliding window max, histogram
- **Binary Search on Answer** — minimize/maximize feasibility (Koko bananas, ship packages, split array)
- **Backtracking** — generate all combinations/permutations/subsets
- **BFS/DFS templates** — grids (islands, flood fill), trees, graphs
- **Topological Ordering** — dependency resolution, course schedule
- **Union-Find** — connectivity, accounts merge, number of provinces
- **Dynamic Programming** — memoization → tabulation → space-optimized
- **Greedy choice** — local optimum → global optimum

---

## 6. Topic-by-Topic Concept Checklist (Roadmap Order)

Follow this order (based on the NeetCode roadmap). Check off each as you master it.

| # | Topic | Key Concepts |
|---|---|---|
| 1 | **Arrays & Hashing** | traversal, hashing, frequency counts, prefix sums, duplicates, anagrams |
| 2 | **Two Pointers** | sorted-pair search, palindrome check, container/water, 3Sum |
| 3 | **Sliding Window** | fixed/variable window, longest/shortest substring, max/min in window |
| 4 | **Stack** | valid parentheses, monotonic stack, min-stack, expression evaluation |
| 5 | **Binary Search** | classic, on rotated array, on answer space, first/last position |
| 6 | **Linked List** | reversal, cycle detection, merge, reorder, LRU cache |
| 7 | **Trees** | DFS/BFS traversals, BST ops, depth/diameter, LCA, level order |
| 8 | **Tries** | insert/search/prefix, word dictionary, autocomplete |
| 9 | **Heap / Priority Queue** | top-k, k-closest, median from stream, merge k lists, scheduling |
| 10 | **Backtracking** | subsets, permutations, combinations, N-Queens, word search |
| 11 | **Graphs** | BFS/DFS, islands, clone graph, course schedule (topo sort), union-find |
| 12 | **1-D Dynamic Programming** | climbing stairs, house robber, coin change, word break, LIS |
| 13 | **2-D Dynamic Programming** | unique paths, LCS, edit distance, knapsack, grid DP |
| 14 | **Intervals** | merge/insert intervals, meeting rooms, non-overlapping |
| 15 | **Greedy** | jump game, gas station, task scheduler, interval scheduling |
| 16 | **Advanced Graphs** | Dijkstra, Prim's, Kruskal's, Bellman-Ford, topological sort, SCC |
| 17 | **Math & Geometry** | rotate matrix, spiral order, GCD/LCM, primes, pow(x,n) |
| 18 | **Bit Manipulation** | XOR tricks, count bits, single number, masks, power of two |

---

## 7. Company-Specific Focus Areas

> Patterns recur across all three, but each company has a characteristic flavor.

### 🔵 Google
- **Hardest bar.** Strong emphasis on **algorithmic depth** and clean, optimal solutions.
- **Heavy hitters:** Dynamic Programming (2-D, interval, bitmask), Graphs (advanced — Dijkstra, topo sort, union-find), Backtracking, Tries.
- **Trees & recursion** — complex tree problems, segment/Fenwick trees occasionally.
- **Math & combinatorics**, careful complexity analysis, and **edge-case rigor**.
- Expect **follow-ups** that push you to optimize time/space further.

### 🟠 Amazon
- **Practical & high-volume.** Tons of **Medium** problems; speed and correctness matter.
- **Heavy hitters:** Arrays & Hashing, Two Pointers, Sliding Window, **BFS/DFS on grids & graphs**, Heaps (Top-K), Trees.
- **Frequent themes:** LRU Cache, Number of Islands, K-closest points, merge intervals, trees/BST.
- Strongly tied to **Leadership Principles** — explain trade-offs and reasoning clearly.
- Some **Object-Oriented Design / Low-Level Design** alongside DSA.

### 🟢 Salesforce
- **Fundamentals-focused**, slightly less brutal than Google/Amazon.
- **Heavy hitters:** Arrays, Strings (parsing, manipulation), Hash Maps, Linked Lists, Stacks/Queues, Recursion.
- **Trees & basic Graphs (BFS/DFS)**, easy/medium DP.
- Emphasis on **clean code, OOP concepts**, and solving classic, well-known problems correctly.
- Often **language-specific** depth (e.g., Java/Apex) and practical problem-solving.

### Common to All Three
Arrays • Strings • Hash Maps • Two Pointers • Sliding Window • Binary Search • Linked Lists • Stacks/Queues • Trees/BST • BFS/DFS • Heaps • Recursion/Backtracking • Dynamic Programming

---

## 8. Must-Know Problem Patterns (with Signals)

| Pattern | Recognize When… | Example Problems |
|---|---|---|
| **Two Pointers** | sorted array, pair/triplet, palindrome | Two Sum II, 3Sum, Valid Palindrome |
| **Sliding Window** | contiguous subarray/substring, "longest/shortest" | Longest Substring w/o Repeat, Min Window Substring |
| **Fast & Slow Pointers** | cycle, middle, linked list | Linked List Cycle, Find Duplicate |
| **Merge Intervals** | overlapping ranges | Merge Intervals, Insert Interval, Meeting Rooms |
| **Prefix Sum** | range sum, subarray sum = K | Subarray Sum Equals K, Product Except Self |
| **Binary Search on Answer** | "minimum/maximum value such that…" | Koko Eating Bananas, Split Array Largest Sum |
| **Top-K / Heap** | "k largest/smallest/most frequent" | Top K Frequent, K Closest Points, Merge K Lists |
| **Monotonic Stack** | "next greater/smaller", spans | Daily Temperatures, Largest Rectangle in Histogram |
| **BFS (level order)** | shortest path unweighted, level-by-level | Rotting Oranges, Word Ladder, Binary Tree Level Order |
| **DFS / Backtracking** | all paths/combinations, grid fill | Number of Islands, Subsets, N-Queens, Word Search |
| **Topological Sort** | dependencies, ordering, prerequisites | Course Schedule I/II, Alien Dictionary |
| **Union-Find** | connectivity, grouping, components | Accounts Merge, Number of Provinces, Redundant Connection |
| **Trie** | prefix search, dictionary of words | Implement Trie, Word Search II, Design Add/Search Words |
| **Dynamic Programming** | optimal value, count ways, overlapping subproblems | Coin Change, LIS, Edit Distance, House Robber |
| **Greedy** | local optimal choice, intervals, scheduling | Jump Game, Gas Station, Task Scheduler |

---

## 9. Curated Problem Lists

Work through these well-known, battle-tested lists (in order of growing scope):

- **Blind 75** — the classic 75-problem starter set covering every core pattern.
- **Grind 75** — Blind 75's successor; schedules problems by week and difficulty.
- **NeetCode 150** — Blind 75 + 75 more, grouped by the roadmap topics in Section 6.
- **NeetCode 250** — extended coverage for thorough preparation.
- **LeetCode "Top Interview 150"** & **company-tagged** lists (Google / Amazon / Salesforce).
- **SDE Sheet (striver / GfG)** — structured topic-wise problem sheets.

> Tip: Do **Blind 75 → Grind 75 → NeetCode 150**, then drill **company-tagged** problems for your target company.

---

## 10. Suggested Study Plan

**Phase 1 — Foundations (Week 1–2)**
- Big-O, Arrays & Hashing, Two Pointers, Sliding Window, Stack, Binary Search.

**Phase 2 — Core Structures (Week 3–4)**
- Linked Lists, Trees & BST, Tries, Heaps / Priority Queues.

**Phase 3 — Recursion & Graphs (Week 5–6)**
- Backtracking, BFS/DFS, Graphs, Topological Sort, Union-Find.

**Phase 4 — Dynamic Programming & Greedy (Week 7–8)**
- 1-D DP → 2-D DP, Intervals, Greedy.

**Phase 5 — Advanced & Polish (Week 9+)**
- Advanced Graphs (Dijkstra/MST), Math & Geometry, Bit Manipulation.
- Timed mock interviews, revisit weak patterns, company-tagged problems.

**Daily habit:** 2–4 problems · revisit missed ones after 1 day, 1 week, 1 month (spaced repetition) · always write/articulate the complexity.

---

## 11. Resources

- **Tech Interview Handbook (Grind 75)** — techinterviewhandbook.org/grind75
- **NeetCode (Roadmap, 150/250, video solutions)** — neetcode.io
- **LeetCode** — leetcode.com (Top Interview 150, company tags, contests)
- **GeeksforGeeks** — geeksforgeeks.org (concepts + company prep)
- **Cracking the Coding Interview (CtCI)** — Gayle Laakmann McDowell
- **Introduction to Algorithms (CLRS)** — deep theory reference
- **AlgoMonster / Educative (Grokking the Coding Interview)** — pattern-based learning
- **Visualizations** — visualgo.net, cs.usfca.edu/~galles/visualization

---

### Concept Coverage at a Glance

```
FOUNDATIONS      → Big-O, Recursion, Sorting, Searching
LINEAR           → Arrays, Strings, Linked Lists, Stacks, Queues, Hash Tables
TREES            → Binary Trees, BST, Heaps, Tries, Balanced/Segment Trees
GRAPHS           → BFS, DFS, Dijkstra, MST, Topo Sort, Union-Find, SCC
PARADIGMS        → Two Pointers, Sliding Window, Greedy, Backtracking, D&C, DP
ADVANCED         → Bit Manipulation, Math/Number Theory, String Algos, Geometry
```

> **Remember:** Interviews test *problem-solving patterns*, not memorization. Understand the *why*, communicate clearly, analyze complexity, and handle edge cases. Good luck! 🚀