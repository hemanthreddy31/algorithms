# Complete DSA Interview Guide for MAANG/FAANG Companies

## Table of Contents
1. [Arrays & Strings](#1-arrays--strings)
2. [Two Pointers & Sliding Window](#2-two-pointers--sliding-window)
3. [Hash Tables](#3-hash-tables)
4. [Linked Lists](#4-linked-lists)
5. [Stacks & Queues](#5-stacks--queues)
6. [Trees & Binary Search Trees](#6-trees--binary-search-trees)
7. [Graphs](#7-graphs)
8. [Dynamic Programming](#8-dynamic-programming)
9. [Backtracking](#9-backtracking)
10.[Greedy Algorithms](#10-greedy-algorithms)
11.[Heaps/Priority Queues](#11-heapspriority-queues)
12.[Tries](#12-tries)
13.[Binary Search](#13-binary-search)
14.[Bit Manipulation](#14-bit-manipulation)
15.[Math & Geometry](#15-math--geometry)

---

## 1. Arrays & Strings

### Key Concepts:
- Array manipulation, in-place operations
- String parsing and pattern matching
- Subarray/substring problems

### Must-Know Patterns:
1. **Prefix Sum**: Running sum for range queries
2. **Kadane's Algorithm**: Maximum subarray sum
3. **Dutch National Flag**: Three-way partitioning

### Essential LeetCode Problems:

#### Easy:
- **Two Sum (LC #1)**: Hash table for O(n) solution
- **Best Time to Buy and Sell Stock (LC #121)**: Track minimum and maximum profit
- **Valid Anagram (LC #242)**: Character frequency counting

#### Medium:
- **3Sum (LC #15)**: Two pointers with sorting
- **Product of Array Except Self (LC #238)**: Prefix and suffix products
- **Longest Substring Without Repeating Characters (LC #3)**: Sliding window with hash set

#### Hard:
- **Median of Two Sorted Arrays (LC #4)**: Binary search approach
- **Trapping Rain Water (LC #42)**: Two pointers or stack approach

---

## 2. Two Pointers & Sliding Window

### Key Concepts:
- Fixed/variable window sizes
- Two pointers from start/end
- Fast and slow pointers

### Must-Know Patterns:
1. **Fixed Window**: Window of size k
2. **Variable Window**: Expand and contract based on condition
3. **Two Pointers**: Start and end moving towards each other

### Essential LeetCode Problems:

#### Easy:
- **Valid Palindrome (LC #125)**: Two pointers from both ends
- **Move Zeroes (LC #283)**: Two pointers for partitioning

#### Medium:
- **Container With Most Water (LC #11)**: Two pointers optimization
- **Longest Repeating Character Replacement (LC #424)**: Sliding window with frequency
- **Minimum Window Substring (LC #76)**: Variable sliding window

---

## 3. Hash Tables

### Key Concepts:
- Frequency counting
- Grouping and bucketing
- Set operations

### Must-Know Patterns:
1. **Frequency Map**: Count occurrences
2. **Two Sum Pattern**: Find pairs with target sum
3. **Group Anagrams**: Use sorted string as key

### Essential LeetCode Problems:

#### Easy:
- **Contains Duplicate (LC #217)**: Set for uniqueness
- **Intersection of Two Arrays (LC #349)**: Set operations

#### Medium:
- **Group Anagrams (LC #49)**: Hash map with sorted keys
- **Subarray Sum Equals K (LC #560)**: Prefix sum with hash map
- **LRU Cache (LC #146)**: Hash map + doubly linked list

---

## 4. Linked Lists

### Key Concepts:
- Pointer manipulation
- Fast and slow pointers (Floyd's algorithm)
- Reversing linked lists

### Must-Know Patterns:
1. **Dummy Node**: Simplify edge cases
2. **Fast/Slow Pointers**: Detect cycles, find middle
3. **In-place Reversal**: Reverse entire list or parts

### Essential LeetCode Problems:

#### Easy:
- **Reverse Linked List (LC #206)**: Iterative and recursive approaches
- **Merge Two Sorted Lists (LC #21)**: Two pointer merge

#### Medium:
- **Add Two Numbers (LC #2)**: Digit-by-digit addition
- **Remove Nth Node From End (LC #19)**: Two pointers with gap
- **Linked List Cycle II (LC #142)**: Floyd's cycle detection

#### Hard:
- **Merge k Sorted Lists (LC #23)**: Heap or divide-and-conquer

---

## 5. Stacks & Queues

### Key Concepts:
- LIFO (Stack) vs FIFO (Queue)
- Monotonic stack/queue
- Expression evaluation

### Must-Know Patterns:
1. **Matching Brackets**: Stack for parentheses
2. **Monotonic Stack**: Next greater/smaller element
3. **Queue with Stack**: Implement queue using stacks

### Essential LeetCode Problems:

#### Easy:
- **Valid Parentheses (LC #20)**: Stack for bracket matching
- **Implement Queue using Stacks (LC #232)**: Two stack approach

#### Medium:
- **Daily Temperatures (LC #739)**: Monotonic stack
- **Evaluate Reverse Polish Notation (LC #150)**: Stack for expression evaluation
- **Min Stack (LC #155)**: Stack with O(1) min operation

---

## 6. Trees & Binary Search Trees

### Key Concepts:
- Tree traversals (inorder, preorder, postorder, level-order)
- BST properties
- Tree construction and serialization

### Must-Know Patterns:
1. **DFS Traversal**: Recursive and iterative
2. **Level Order**: BFS with queue
3. **Path Problems**: Root to leaf paths

### Essential LeetCode Problems:

#### Easy:
- **Maximum Depth of Binary Tree (LC #104)**: Simple recursion
- **Invert Binary Tree (LC #226)**: Recursive swap

#### Medium:
- **Validate Binary Search Tree (LC #98)**: Inorder traversal or bounds checking
- **Lowest Common Ancestor of BST (LC #235)**: Use BST properties
- **Binary Tree Level Order Traversal (LC #102)**: BFS with queue

#### Hard:
- **Serialize and Deserialize Binary Tree (LC #297)**: Preorder with markers
- **Binary Tree Maximum Path Sum (LC #124)**: DFS with global max

---

## 7. Graphs

### Key Concepts:
- Graph representations (adjacency list/matrix)
- DFS and BFS traversals
- Topological sort, cycle detection

### Must-Know Patterns:
1. **DFS/BFS**: Connected components, shortest path
2. **Union Find**: Disjoint sets, cycle detection
3. **Topological Sort**: Kahn's algorithm or DFS

### Essential LeetCode Problems:

#### Medium:
- **Number of Islands (LC #200)**: DFS/BFS on grid
- **Clone Graph (LC #133)**: DFS with hash map
- **Course Schedule (LC #207)**: Topological sort for cycle detection
- **Pacific Atlantic Water Flow (LC #417)**: Multi-source BFS/DFS

#### Hard:
- **Word Ladder (LC #127)**: BFS for shortest transformation
- **Alien Dictionary (LC #269)**: Topological sort

---

## 8. Dynamic Programming

### Key Concepts:
- Overlapping subproblems
- Optimal substructure
- Memoization vs tabulation

### Must-Know Patterns:
1. **1D DP**: Fibonacci-like problems
2. **2D DP**: Grid traversal, string matching
3. **State Machine DP**: Buy/sell stocks

### Essential LeetCode Problems:

#### Easy:
- **Climbing Stairs (LC #70)**: Basic 1D DP
- **House Robber (LC #198)**: DP with constraints

#### Medium:
- **Longest Increasing Subsequence (LC #300)**: Classic DP
- **Coin Change (LC #322)**: Unbounded knapsack
- **Unique Paths (LC #62)**: 2D grid DP
- **Word Break (LC #139)**: DP with dictionary

#### Hard:
- **Edit Distance (LC #72)**: Classic 2D DP
- **Burst Balloons (LC #312)**: Interval DP

---

## 9. Backtracking

### Key Concepts:
- Explore all possibilities
- Pruning invalid paths
- State space tree

### Must-Know Patterns:
1. **Subsets/Permutations**: Generate all combinations
2. **N-Queens Pattern**: Place with constraints
3. **Path Finding**: Maze solving

### Essential LeetCode Problems:

#### Medium:
- **Subsets (LC #78)**: Include/exclude pattern
- **Permutations (LC #46)**: Swap or used array
- **Combination Sum (LC #39)**: Backtrack with repetition
- **Word Search (LC #79)**: Grid backtracking

#### Hard:
- **N-Queens (LC #51)**: Classic backtracking
- **Sudoku Solver (LC #37)**: Constraint satisfaction

---

## 10. Greedy Algorithms

### Key Concepts:
- Local optimal leads to global optimal
- Proof of correctness
- Activity selection

### Must-Know Patterns:
1. **Interval Scheduling**: Sort by end time
2. **Huffman Coding**: Build optimal tree
3. **Jump Game**: Greedy choice at each step

### Essential LeetCode Problems:

#### Medium:
- **Jump Game (LC #55)**: Greedy reachability
- **Meeting Rooms II (LC #253)**: Interval scheduling
- **Gas Station (LC #134)**: Circular array greedy

---

## 11. Heaps/Priority Queues

### Key Concepts:
- Min heap vs max heap
- Heap operations: O(log n) insert/delete
- K-way merge

### Must-Know Patterns:
1. **Top K Elements**: Use heap of size k
2. **Median Finding**: Two heaps
3. **Merge K Lists**: Min heap

### Essential LeetCode Problems:

#### Medium:
- **Kth Largest Element (LC #215)**: Quick select or heap
- **Top K Frequent Elements (LC #347)**: Heap with frequency
- **Find Median from Data Stream (LC #295)**: Two heaps

---

## 12. Tries

### Key Concepts:
- Prefix tree structure
- Word search optimization
- Auto-complete functionality

### Must-Know Patterns:
1. **Word Dictionary**: Insert and search
2. **Prefix Matching**: All words with prefix
3. **Word Search II**: Trie + backtracking

### Essential LeetCode Problems:

#### Medium:
- **Implement Trie (LC #208)**: Basic trie operations
- **Word Search II (LC #212)**: Trie + DFS on grid
- **Design Add and Search Words (LC #211)**: Trie with wildcard

---

## 13. Binary Search

### Key Concepts:
- Search space reduction
- Finding boundaries
- Search on answer

### Must-Know Patterns:
1. **Classic Binary Search**: Find target
2. **First/Last Position**: Modified binary search
3. **Search in Rotated Array**: Modified condition

### Essential LeetCode Problems:

#### Easy:
- **Binary Search (LC #704)**: Classic implementation

#### Medium:
- **Search in Rotated Sorted Array (LC #33)**: Modified binary search
- **Find First and Last Position (LC #34)**: Two binary searches
- **Search a 2D Matrix (LC #74)**: 2D to 1D mapping

---

## 14. Bit Manipulation

### Key Concepts:
- Bitwise operations: AND, OR, XOR, NOT
- Bit shifting
- Common tricks

### Must-Know Patterns:
1. **XOR Properties**: a^a=0, a^0=a
2. **Power of 2**: n & (n-1)
3. **Bit Counting**: Brian Kernighan's algorithm

### Essential LeetCode Problems:

#### Easy:
- **Single Number (LC #136)**: XOR to find unique
- **Number of 1 Bits (LC #191)**: Bit counting

#### Medium:
- **Subsets (LC #78)**: Bit manipulation approach
- **Single Number III (LC #260)**: XOR with grouping

---

## 15. Math & Geometry

### Key Concepts:
- Prime numbers, GCD/LCM
- Modular arithmetic
- Computational geometry

### Must-Know Patterns:
1. **Sieve of Eratosthenes**: Find all primes
2. **Fast Power**: Exponentiation by squaring
3. **Reservoir Sampling**: Random selection

### Essential LeetCode Problems:

#### Medium:
- **Pow(x, n) (LC #50)**: Fast exponentiation
- **Sqrt(x) (LC #69)**: Binary search approach
- **Random Pick with Weight (LC #528)**: Prefix sum + binary search

---

## Interview Preparation Strategy

### Week 1-2: Foundation
- Arrays, Strings, Hash Tables
- Two Pointers, Sliding Window
- Practice 3-4 problems daily

### Week 3-4: Data Structures
- Linked Lists, Stacks, Queues
- Trees, Graphs basics
- Focus on implementation

### Week 5-6: Advanced Topics
- Dynamic Programming patterns
- Backtracking problems
- Graph algorithms

### Week 7-8: Optimization
- Heaps, Tries
- Binary Search variations
- Bit manipulation tricks

### Final Weeks: Practice
- Mock interviews
- Time-bound problem solving
- Review weak areas

## Tips for Success

1. **Understand, Don't Memorize**: Focus on patterns, not solutions
2. **Time Complexity**: Always analyze and optimize
3. **Edge Cases**: Empty input, single element, duplicates
4. **Clean Code**: Variable names, modularity
5. **Communication**: Explain your approach clearly

Remember: Consistency is key. Solve 2-3 problems daily and review solutions to understand different approaches. 