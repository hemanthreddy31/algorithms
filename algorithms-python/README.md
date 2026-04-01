# 🎯 DSA Mastery Guide — Crack FAANG & Top Tech Interviews

> **A one-stop, pattern-driven resource for mastering Data Structures and Algorithms using Python.**
> Covers Google, Microsoft, Apple, Meta, Amazon, Uber, Netflix, and more.

---

## 📑 Table of Contents

| # | Pattern | Difficulty | Frequently Asked By |
|---|---------|-----------|---------------------|
| 1 | [Arrays](#1-arrays) | Easy–Medium | All Companies |
| 2 | [Strings](#2-strings) | Easy–Medium | Google, Microsoft, Amazon |
| 3 | [Two Pointers](#3-two-pointers) | Easy–Medium | Meta, Google, Apple |
| 4 | [Sliding Window](#4-sliding-window) | Medium | Google, Amazon, Uber |
| 5 | [Binary Search](#5-binary-search) | Medium | Google, Meta, Microsoft |
| 6 | [Linked Lists](#6-linked-lists) | Easy–Medium | Microsoft, Amazon, Apple |
| 7 | [Stacks & Queues](#7-stacks--queues) | Medium | Amazon, Bloomberg, Uber |
| 8 | [Recursion & Backtracking](#8-recursion--backtracking) | Medium–Hard | Google, Meta, Apple |
| 9 | [Trees (Binary Tree & BST)](#9-trees-binary-tree--bst) | Medium | All Companies |
| 10 | [Heaps / Priority Queues](#10-heaps--priority-queues) | Medium | Google, Amazon, Netflix |
| 11 | [Tries](#11-tries) | Medium–Hard | Google, Microsoft, Amazon |
| 12 | [Graphs](#12-graphs) | Medium–Hard | Google, Meta, Uber, Apple |
| 13 | [Dynamic Programming](#13-dynamic-programming) | Medium–Hard | Google, Amazon, Meta |
| 14 | [Greedy Algorithms](#14-greedy-algorithms) | Medium | Amazon, Google, Uber |
| 15 | [Intervals](#15-intervals) | Medium | Google, Meta, Uber |
| 16 | [Bit Manipulation](#16-bit-manipulation) | Easy–Medium | Apple, Google, Microsoft |
| 17 | [Math & Geometry](#17-math--geometry) | Easy–Hard | Google, Apple, Microsoft |
| 18 | [Tips, Pattern Recognition & Company Guide](#18-tips-pattern-recognition--company-guide) | — | — |

---

## How to Use This Guide

1. **Go pattern by pattern** — don't jump around randomly.
2. **Read the pattern explanation** before touching problems.
3. **Try brute force first**, then study the optimized approach.
4. **Code every solution yourself** before reading the implementation.
5. **Track your progress** — revisit problems after 3 and 7 days (spaced repetition).

### Complexity Cheat Sheet

| Notation | Name | Example |
|----------|------|---------|
| O(1) | Constant | Hash map lookup |
| O(log n) | Logarithmic | Binary search |
| O(n) | Linear | Single pass |
| O(n log n) | Linearithmic | Merge sort |
| O(n²) | Quadratic | Nested loops |
| O(2ⁿ) | Exponential | Subsets |
| O(n!) | Factorial | Permutations |

---

# 1. Arrays

## Pattern Overview

Arrays are the most fundamental data structure. Almost every interview starts with array problems. The key is recognizing sub-patterns: **hashing**, **sorting**, **prefix sums**, **in-place manipulation**, and **matrix traversal**.

### When to Use
- You need O(1) index-based access.
- Problems involve contiguous sequences of elements.
- You need to aggregate, transform, or search through a collection.

### Tips to Recognize
- "Given an array/list of integers…"
- "Find a subarray / contiguous segment…"
- "In-place" hints at swapping / partitioning.

### Common Pitfalls
- Off-by-one errors in indexing.
- Forgetting to handle empty arrays.
- Mutating the array while iterating.

---

## 1.1 Two Sum

**Companies:** Google, Amazon, Meta, Apple, Microsoft, Uber — *the most asked interview question of all time.*

### Problem Statement
Given an array of integers `nums` and an integer `target`, return the **indices** of the two numbers that add up to `target`. Each input has exactly one solution. You may not use the same element twice.

### Brute Force Approach

**Idea:** Check every pair of elements.

- **Time Complexity:** O(n²)
- **Space Complexity:** O(1)

```python
def two_sum_brute(nums, target):
    n = len(nums)
    for i in range(n):
        for j in range(i + 1, n):
            if nums[i] + nums[j] == target:
                return [i, j]
    return []
```

### Optimized Approach — Hash Map

**Intuition:** For each element `x`, we need `target - x`. A hash map gives O(1) lookup.

**Steps:**
1. Iterate through the array.
2. For each number, check if `target - num` exists in the map.
3. If yes, return both indices. If no, store `num: index`.

- **Time Complexity:** O(n)
- **Space Complexity:** O(n)

```python
def two_sum(nums, target):
    seen = {}  # value -> index
    for i, num in enumerate(nums):
        complement = target - num
        if complement in seen:
            return [seen[complement], i]
        seen[num] = i
    return []
```

---

## 1.2 Best Time to Buy and Sell Stock

**Companies:** Amazon, Meta, Google, Microsoft, Goldman Sachs

### Problem Statement
Given an array `prices` where `prices[i]` is the price on day `i`, find the maximum profit from one buy and one sell. You must buy before you sell.

### Brute Force Approach

**Idea:** Try every (buy, sell) pair.

- **Time Complexity:** O(n²)
- **Space Complexity:** O(1)

```python
def max_profit_brute(prices):
    max_p = 0
    for i in range(len(prices)):
        for j in range(i + 1, len(prices)):
            max_p = max(max_p, prices[j] - prices[i])
    return max_p
```

### Optimized Approach — Track Minimum

**Intuition:** As we scan left to right, keep the minimum price seen so far. At each step, the best profit we can get is `current_price - min_price`.

- **Time Complexity:** O(n)
- **Space Complexity:** O(1)

```python
def max_profit(prices):
    min_price = float('inf')
    max_profit = 0
    for price in prices:
        min_price = min(min_price, price)         # update cheapest buy
        max_profit = max(max_profit, price - min_price)  # best sell today
    return max_profit
```

---

## 1.3 Product of Array Except Self

**Companies:** Meta, Amazon, Google, Apple

### Problem Statement
Given an array `nums`, return an array `answer` where `answer[i]` equals the product of all elements except `nums[i]`. Solve without division and in O(n) time.

### Brute Force Approach

- **Time Complexity:** O(n²)
- **Space Complexity:** O(n)

```python
def product_except_self_brute(nums):
    n = len(nums)
    result = [1] * n
    for i in range(n):
        for j in range(n):
            if i != j:
                result[i] *= nums[j]
    return result
```

### Optimized Approach — Prefix & Suffix Products

**Intuition:** `answer[i] = (product of all elements to the left) * (product of all elements to the right)`.

**Steps:**
1. Build prefix products in a left pass.
2. Multiply with suffix products in a right pass.

- **Time Complexity:** O(n)
- **Space Complexity:** O(1) extra (output array not counted)

```python
def product_except_self(nums):
    n = len(nums)
    result = [1] * n

    # Left pass: result[i] = product of nums[0..i-1]
    prefix = 1
    for i in range(n):
        result[i] = prefix
        prefix *= nums[i]

    # Right pass: multiply by product of nums[i+1..n-1]
    suffix = 1
    for i in range(n - 1, -1, -1):
        result[i] *= suffix
        suffix *= nums[i]

    return result
```

---

## 1.4 Contains Duplicate

**Companies:** Amazon, Apple, Microsoft

### Problem Statement
Given an integer array `nums`, return `True` if any value appears at least twice.

### Brute Force — O(n²)
```python
def contains_duplicate_brute(nums):
    for i in range(len(nums)):
        for j in range(i + 1, len(nums)):
            if nums[i] == nums[j]:
                return True
    return False
```

### Optimized — Hash Set O(n)

```python
def contains_duplicate(nums):
    seen = set()
    for num in nums:
        if num in seen:
            return True
        seen.add(num)
    return False
```

---

## 1.5 Maximum Subarray (Kadane's Algorithm)

**Companies:** Google, Amazon, Microsoft, LinkedIn

### Problem Statement
Find the contiguous subarray with the largest sum.

### Brute Force — O(n²)
```python
def max_subarray_brute(nums):
    max_sum = float('-inf')
    for i in range(len(nums)):
        current = 0
        for j in range(i, len(nums)):
            current += nums[j]
            max_sum = max(max_sum, current)
    return max_sum
```

### Optimized — Kadane's Algorithm O(n)

**Intuition:** At each index, decide: extend the previous subarray or start fresh.

```python
def max_subarray(nums):
    current_sum = max_sum = nums[0]
    for num in nums[1:]:
        current_sum = max(num, current_sum + num)  # extend or restart
        max_sum = max(max_sum, current_sum)
    return max_sum
```

- **Time Complexity:** O(n)
- **Space Complexity:** O(1)

---

## 1.6 Merge Sorted Array (In-Place)

**Companies:** Meta, Microsoft, Apple

### Problem Statement
Merge `nums2` into `nums1` (which has enough trailing zeros). Both are sorted.

### Optimized — Three Pointers from End O(n + m)

**Intuition:** Fill from the back to avoid shifting elements.

```python
def merge(nums1, m, nums2, n):
    p1, p2, p = m - 1, n - 1, m + n - 1
    while p1 >= 0 and p2 >= 0:
        if nums1[p1] >= nums2[p2]:
            nums1[p] = nums1[p1]
            p1 -= 1
        else:
            nums1[p] = nums2[p2]
            p2 -= 1
        p -= 1
    # Copy remaining elements from nums2
    nums1[:p2 + 1] = nums2[:p2 + 1]
```

---

# 2. Strings

## Pattern Overview

String problems test your ability to manipulate sequences of characters efficiently. Key techniques: **hashing/frequency counting**, **two pointers**, **sliding window**, and **string matching**.

### When to Use
- Input is a string or list of strings.
- Anagram, palindrome, substring, or pattern matching tasks.

### Common Pitfalls
- Strings are **immutable** in Python — concatenation in a loop is O(n²). Use `list` and `''.join()`.
- Case sensitivity and whitespace handling.
- Unicode / special character edge cases.

---

## 2.1 Valid Anagram

**Companies:** Amazon, Google, Microsoft

### Problem Statement
Given two strings `s` and `t`, return `True` if `t` is an anagram of `s`.

### Brute Force — Sort O(n log n)
```python
def is_anagram_brute(s, t):
    return sorted(s) == sorted(t)
```

### Optimized — Frequency Count O(n)

```python
from collections import Counter

def is_anagram(s, t):
    if len(s) != len(t):
        return False
    return Counter(s) == Counter(t)
```

---

## 2.2 Valid Palindrome

**Companies:** Meta, Microsoft, Apple

### Problem Statement
Given a string `s`, determine if it is a palindrome considering only alphanumeric characters and ignoring case.

### Brute Force — Filter and Reverse O(n)
```python
def is_palindrome_brute(s):
    filtered = ''.join(c.lower() for c in s if c.isalnum())
    return filtered == filtered[::-1]
```

### Optimized — Two Pointers O(n) with O(1) Space

```python
def is_palindrome(s):
    left, right = 0, len(s) - 1
    while left < right:
        while left < right and not s[left].isalnum():
            left += 1
        while left < right and not s[right].isalnum():
            right -= 1
        if s[left].lower() != s[right].lower():
            return False
        left += 1
        right -= 1
    return True
```

---

## 2.3 Longest Substring Without Repeating Characters

**Companies:** Amazon, Google, Meta, Bloomberg, Uber

### Problem Statement
Given a string `s`, find the length of the **longest substring** without repeating characters.

### Brute Force — O(n³)
```python
def length_of_longest_brute(s):
    max_len = 0
    for i in range(len(s)):
        for j in range(i, len(s)):
            substring = s[i:j + 1]
            if len(set(substring)) == len(substring):
                max_len = max(max_len, len(substring))
    return max_len
```

### Optimized — Sliding Window + Hash Set O(n)

**Intuition:** Expand right pointer. When a duplicate is found, shrink from the left until the window is valid again.

```python
def length_of_longest(s):
    char_set = set()
    left = max_len = 0
    for right in range(len(s)):
        while s[right] in char_set:
            char_set.remove(s[left])
            left += 1
        char_set.add(s[right])
        max_len = max(max_len, right - left + 1)
    return max_len
```

---

## 2.4 Longest Palindromic Substring

**Companies:** Google, Amazon, Microsoft

### Problem Statement
Given string `s`, return the longest palindromic substring.

### Brute Force — O(n³)
```python
def longest_palindrome_brute(s):
    def is_pal(sub):
        return sub == sub[::-1]
    
    best = ""
    for i in range(len(s)):
        for j in range(i, len(s)):
            if is_pal(s[i:j + 1]) and j - i + 1 > len(best):
                best = s[i:j + 1]
    return best
```

### Optimized — Expand Around Center O(n²)

**Intuition:** Every palindrome has a center. Expand outward from each possible center (single char and between two chars).

```python
def longest_palindrome(s):
    def expand(left, right):
        while left >= 0 and right < len(s) and s[left] == s[right]:
            left -= 1
            right += 1
        return s[left + 1:right]

    result = ""
    for i in range(len(s)):
        # Odd-length palindromes
        odd = expand(i, i)
        # Even-length palindromes
        even = expand(i, i + 1)
        result = max(result, odd, even, key=len)
    return result
```

- **Time:** O(n²) | **Space:** O(1)

---

## 2.5 Group Anagrams

**Companies:** Meta, Amazon, Google, Uber

### Problem Statement
Given an array of strings, group the anagrams together.

### Optimized — Sorted Key Hash Map O(n · k log k)

```python
from collections import defaultdict

def group_anagrams(strs):
    groups = defaultdict(list)
    for s in strs:
        key = tuple(sorted(s))  # anagrams share the same sorted form
        groups[key].append(s)
    return list(groups.values())
```

### Further Optimized — Frequency Tuple Key O(n · k)

```python
from collections import defaultdict

def group_anagrams_optimal(strs):
    groups = defaultdict(list)
    for s in strs:
        count = [0] * 26
        for c in s:
            count[ord(c) - ord('a')] += 1
        groups[tuple(count)].append(s)
    return list(groups.values())
```

---

# 3. Two Pointers

## Pattern Overview

Two pointers is a technique where two indices traverse the data structure (usually from opposite ends or at different speeds). It reduces nested iterations from O(n²) to O(n).

### When to Use
- **Sorted arrays**: searching for pairs, triplets.
- **Palindrome** checks.
- **Partitioning** / removing duplicates in-place.

### Tips to Recognize
- "Sorted array" + "find pair with given sum."
- "In-place" + "remove / move elements."
- "Palindrome" or "reverse."

### Companies: Meta, Google, Apple, Amazon

---

## 3.1 Three Sum

**Companies:** Meta, Google, Amazon, Apple, Bloomberg

### Problem Statement
Given an array `nums`, return all unique triplets `[a, b, c]` such that `a + b + c = 0`.

### Brute Force — O(n³)
```python
def three_sum_brute(nums):
    nums.sort()
    result = set()
    n = len(nums)
    for i in range(n):
        for j in range(i + 1, n):
            for k in range(j + 1, n):
                if nums[i] + nums[j] + nums[k] == 0:
                    result.add((nums[i], nums[j], nums[k]))
    return [list(t) for t in result]
```

### Optimized — Sort + Two Pointers O(n²)

**Intuition:** Fix one element, then use two pointers on the remaining sorted subarray to find pairs.

```python
def three_sum(nums):
    nums.sort()
    result = []
    for i in range(len(nums) - 2):
        if i > 0 and nums[i] == nums[i - 1]:  # skip duplicate anchors
            continue
        left, right = i + 1, len(nums) - 1
        while left < right:
            total = nums[i] + nums[left] + nums[right]
            if total < 0:
                left += 1
            elif total > 0:
                right -= 1
            else:
                result.append([nums[i], nums[left], nums[right]])
                while left < right and nums[left] == nums[left + 1]:
                    left += 1   # skip duplicate left
                while left < right and nums[right] == nums[right - 1]:
                    right -= 1  # skip duplicate right
                left += 1
                right -= 1
    return result
```

---

## 3.2 Container With Most Water

**Companies:** Google, Amazon, Meta, Goldman Sachs

### Problem Statement
Given `n` non-negative integers representing heights of vertical lines, find two lines that together with the x-axis form a container with the most water.

### Brute Force — O(n²)
```python
def max_area_brute(height):
    max_water = 0
    for i in range(len(height)):
        for j in range(i + 1, len(height)):
            max_water = max(max_water, min(height[i], height[j]) * (j - i))
    return max_water
```

### Optimized — Two Pointers O(n)

**Intuition:** Start with widest container. Move the shorter line inward (only way to potentially increase height).

```python
def max_area(height):
    left, right = 0, len(height) - 1
    max_water = 0
    while left < right:
        w = right - left
        h = min(height[left], height[right])
        max_water = max(max_water, w * h)
        if height[left] < height[right]:
            left += 1
        else:
            right -= 1
    return max_water
```

---

## 3.3 Trapping Rain Water

**Companies:** Google, Amazon, Meta, Goldman Sachs, Apple

### Problem Statement
Given `n` non-negative integers representing an elevation map, compute how much water it can trap after raining.

### Brute Force — O(n²)
```python
def trap_brute(height):
    n = len(height)
    water = 0
    for i in range(n):
        left_max = max(height[:i + 1])
        right_max = max(height[i:])
        water += min(left_max, right_max) - height[i]
    return water
```

### Optimized — Two Pointers O(n)

**Intuition:** Water at any position depends on `min(max_left, max_right) - height[i]`. Use two pointers tracking running maxes from each side.

```python
def trap(height):
    left, right = 0, len(height) - 1
    left_max = right_max = 0
    water = 0
    while left < right:
        if height[left] < height[right]:
            left_max = max(left_max, height[left])
            water += left_max - height[left]
            left += 1
        else:
            right_max = max(right_max, height[right])
            water += right_max - height[right]
            right -= 1
    return water
```

- **Time:** O(n) | **Space:** O(1)

---

## 3.4 Move Zeroes

**Companies:** Meta, Apple, Microsoft

### Problem Statement
Move all 0s to the end of the array while maintaining the relative order of non-zero elements. Must be in-place.

### Optimized — Two Pointers O(n)

```python
def move_zeroes(nums):
    write = 0  # position to place next non-zero
    for read in range(len(nums)):
        if nums[read] != 0:
            nums[write], nums[read] = nums[read], nums[write]
            write += 1
```

---

# 4. Sliding Window

## Pattern Overview

Sliding window maintains a window (subarray/substring) that expands or shrinks as it slides over the data. It turns O(n²) brute-force substring/subarray scans into O(n).

### When to Use
- Finding **longest / shortest subarray or substring** satisfying a condition.
- Problems involving **contiguous sequences** with constraints.
- Keywords: "subarray," "substring," "window," "consecutive."

### Two Variants
| Variant | Description |
|---------|-------------|
| **Fixed-size window** | Window length is given; slide one step at a time. |
| **Variable-size window** | Expand right, shrink left when condition is violated. |

### Companies: Google, Amazon, Uber, Netflix, Meta

---

## 4.1 Maximum Sum Subarray of Size K (Fixed Window)

### Problem Statement
Given an array and integer `k`, find the maximum sum of any contiguous subarray of size `k`.

### Brute Force — O(n · k)
```python
def max_sum_brute(nums, k):
    max_sum = float('-inf')
    for i in range(len(nums) - k + 1):
        max_sum = max(max_sum, sum(nums[i:i + k]))
    return max_sum
```

### Optimized — Sliding Window O(n)

```python
def max_sum_subarray(nums, k):
    window_sum = sum(nums[:k])
    max_sum = window_sum
    for i in range(k, len(nums)):
        window_sum += nums[i] - nums[i - k]  # slide: add right, remove left
        max_sum = max(max_sum, window_sum)
    return max_sum
```

---

## 4.2 Minimum Window Substring

**Companies:** Meta, Google, Amazon, Uber — *very frequently asked.*

### Problem Statement
Given strings `s` and `t`, return the minimum window in `s` that contains all characters of `t`. If no such window exists, return `""`.

### Brute Force — O(n² · m)
```python
from collections import Counter

def min_window_brute(s, t):
    if not t:
        return ""
    best = ""
    for i in range(len(s)):
        for j in range(i + len(t), len(s) + 1):
            window = s[i:j]
            t_count = Counter(t)
            for c in window:
                if c in t_count and t_count[c] > 0:
                    t_count[c] -= 1
            if all(v <= 0 for v in t_count.values()):
                if not best or len(window) < len(best):
                    best = window
    return best
```

### Optimized — Sliding Window + Hash Map O(n)

**Intuition:** Expand window right until all chars are covered, then shrink from left to minimize.

```python
from collections import Counter

def min_window(s, t):
    if not t or not s:
        return ""

    need = Counter(t)          # characters we need
    have = 0                   # how many unique chars are satisfied
    required = len(need)       # total unique chars needed
    best = (float('inf'), 0, 0)  # (length, left, right)
    window = {}

    left = 0
    for right in range(len(s)):
        char = s[right]
        window[char] = window.get(char, 0) + 1

        # Check if this char's frequency requirement is met
        if char in need and window[char] == need[char]:
            have += 1

        # Try to shrink from the left
        while have == required:
            size = right - left + 1
            if size < best[0]:
                best = (size, left, right)

            # Remove left char from window
            window[s[left]] -= 1
            if s[left] in need and window[s[left]] < need[s[left]]:
                have -= 1
            left += 1

    return "" if best[0] == float('inf') else s[best[1]:best[2] + 1]
```

- **Time:** O(n) | **Space:** O(n)

---

## 4.3 Longest Repeating Character Replacement

**Companies:** Google, Amazon, Microsoft

### Problem Statement
Given string `s` and integer `k`, find the length of the longest substring containing the same letter after performing at most `k` replacements.

### Optimized — Sliding Window O(n)

**Intuition:** In a window, we need to replace `window_length - max_freq` characters. If that exceeds `k`, shrink from left.

```python
def character_replacement(s, k):
    count = {}
    left = max_len = max_freq = 0
    for right in range(len(s)):
        count[s[right]] = count.get(s[right], 0) + 1
        max_freq = max(max_freq, count[s[right]])

        # If replacements needed > k, shrink window
        if (right - left + 1) - max_freq > k:
            count[s[left]] -= 1
            left += 1

        max_len = max(max_len, right - left + 1)
    return max_len
```

---

## 4.4 Permutation in String

**Companies:** Microsoft, Amazon

### Problem Statement
Given strings `s1` and `s2`, return `True` if `s2` contains a permutation of `s1`.

### Optimized — Fixed Sliding Window O(n)

```python
from collections import Counter

def check_inclusion(s1, s2):
    if len(s1) > len(s2):
        return False
    s1_count = Counter(s1)
    window = Counter(s2[:len(s1)])
    if window == s1_count:
        return True
    for i in range(len(s1), len(s2)):
        # Add new char
        window[s2[i]] += 1
        # Remove old char
        old = s2[i - len(s1)]
        window[old] -= 1
        if window[old] == 0:
            del window[old]
        if window == s1_count:
            return True
    return False
```

---

# 5. Binary Search

## Pattern Overview

Binary search halves the search space each step — O(log n). It applies not just to sorted arrays but to any **monotonic** condition.

### When to Use
- Sorted array / rotated sorted array.
- "Find minimum/maximum that satisfies a condition" — **binary search on answer**.
- Searching in a matrix with sorted rows/columns.

### Template

```python
def binary_search(nums, target):
    lo, hi = 0, len(nums) - 1
    while lo <= hi:
        mid = lo + (hi - lo) // 2
        if nums[mid] == target:
            return mid
        elif nums[mid] < target:
            lo = mid + 1
        else:
            hi = mid - 1
    return -1
```

### Common Pitfalls
- `lo + (hi - lo) // 2` prevents overflow (matters in other languages, good habit in Python too).
- `while lo <= hi` vs `while lo < hi` — the boundary condition depends on the variant.
- Not handling duplicates correctly.

### Companies: Google, Meta, Microsoft, Amazon, Apple

---

## 5.1 Search in Rotated Sorted Array

**Companies:** Meta, Google, Amazon, Microsoft

### Problem Statement
Search for `target` in a rotated sorted array. Return its index or `-1`.

### Brute Force — O(n)
```python
def search_brute(nums, target):
    for i, num in enumerate(nums):
        if num == target:
            return i
    return -1
```

### Optimized — Modified Binary Search O(log n)

**Intuition:** At any `mid`, one half is always sorted. Determine which half, then decide which side `target` falls in.

```python
def search(nums, target):
    lo, hi = 0, len(nums) - 1
    while lo <= hi:
        mid = lo + (hi - lo) // 2
        if nums[mid] == target:
            return mid

        # Left half is sorted
        if nums[lo] <= nums[mid]:
            if nums[lo] <= target < nums[mid]:
                hi = mid - 1
            else:
                lo = mid + 1
        # Right half is sorted
        else:
            if nums[mid] < target <= nums[hi]:
                lo = mid + 1
            else:
                hi = mid - 1
    return -1
```

---

## 5.2 Find Minimum in Rotated Sorted Array

**Companies:** Meta, Google, Amazon

### Problem Statement
Find the minimum element in a rotated sorted array (no duplicates).

### Optimized — Binary Search O(log n)

```python
def find_min(nums):
    lo, hi = 0, len(nums) - 1
    while lo < hi:
        mid = lo + (hi - lo) // 2
        if nums[mid] > nums[hi]:
            lo = mid + 1    # min is in right half
        else:
            hi = mid        # mid could be the min
    return nums[lo]
```

---

## 5.3 Koko Eating Bananas (Binary Search on Answer)

**Companies:** Google, Amazon, Uber

### Problem Statement
Koko has `n` piles of bananas. She can eat `k` bananas/hour. Guards return in `h` hours. Find the minimum `k` so she can eat all bananas in time.

### Optimized — Binary Search on Speed O(n · log(max_pile))

**Intuition:** If speed `k` works, any speed > `k` also works → monotonic → binary search.

```python
import math

def min_eating_speed(piles, h):
    lo, hi = 1, max(piles)
    while lo < hi:
        mid = lo + (hi - lo) // 2
        # Hours needed at speed mid
        hours = sum(math.ceil(p / mid) for p in piles)
        if hours <= h:
            hi = mid       # try slower
        else:
            lo = mid + 1   # need faster
    return lo
```

---

## 5.4 Search a 2D Matrix

**Companies:** Microsoft, Amazon, Google

### Problem Statement
Search for a value in an `m x n` matrix where each row is sorted left-to-right and the first element of each row is greater than the last element of the previous row.

### Optimized — Binary Search O(log(m·n))

**Intuition:** Treat the matrix as a single sorted array of length `m * n`.

```python
def search_matrix(matrix, target):
    if not matrix:
        return False
    m, n = len(matrix), len(matrix[0])
    lo, hi = 0, m * n - 1
    while lo <= hi:
        mid = lo + (hi - lo) // 2
        val = matrix[mid // n][mid % n]  # convert 1D index to 2D
        if val == target:
            return True
        elif val < target:
            lo = mid + 1
        else:
            hi = mid - 1
    return False
```

---

# 6. Linked Lists

## Pattern Overview

Linked list problems test pointer manipulation. Key techniques: **fast & slow pointers**, **dummy head**, **reversal**, and **merge**.

### When to Use
- Dynamic data where frequent insertions/deletions are needed.
- Problems involving cycles, intersections, or merging.

### Tips to Recognize
- "Given head of a linked list…"
- Cycle detection → Floyd's tortoise and hare.
- "Reverse" → iterative pointer swap.

### Common Node Definition
```python
class ListNode:
    def __init__(self, val=0, next=None):
        self.val = val
        self.next = next
```

### Companies: Microsoft, Amazon, Apple, Meta

---

## 6.1 Reverse Linked List

**Companies:** Amazon, Microsoft, Apple, Meta — *the most fundamental linked list problem.*

### Problem Statement
Reverse a singly linked list.

### Brute Force — Using Stack O(n) space
```python
def reverse_list_brute(head):
    stack = []
    curr = head
    while curr:
        stack.append(curr.val)
        curr = curr.next
    curr = head
    while curr:
        curr.val = stack.pop()
        curr = curr.next
    return head
```

### Optimized — Iterative O(n) time, O(1) space

```python
def reverse_list(head):
    prev, curr = None, head
    while curr:
        nxt = curr.next   # save next
        curr.next = prev  # reverse pointer
        prev = curr       # advance prev
        curr = nxt        # advance curr
    return prev
```

---

## 6.2 Linked List Cycle

**Companies:** Amazon, Microsoft, Apple

### Problem Statement
Given `head`, determine if the linked list has a cycle.

### Optimized — Floyd's Slow & Fast Pointers O(n)

```python
def has_cycle(head):
    slow = fast = head
    while fast and fast.next:
        slow = slow.next
        fast = fast.next.next
        if slow == fast:
            return True
    return False
```

---

## 6.3 Merge Two Sorted Lists

**Companies:** Amazon, Microsoft, Apple, Google

### Problem Statement
Merge two sorted linked lists into one sorted list.

### Optimized — Iterative with Dummy Node O(n + m)

```python
def merge_two_lists(l1, l2):
    dummy = ListNode(0)
    tail = dummy
    while l1 and l2:
        if l1.val <= l2.val:
            tail.next = l1
            l1 = l1.next
        else:
            tail.next = l2
            l2 = l2.next
        tail = tail.next
    tail.next = l1 or l2  # attach remaining
    return dummy.next
```

---

## 6.4 Remove Nth Node From End

**Companies:** Meta, Amazon, Google

### Problem Statement
Remove the nth node from the end of a linked list in one pass.

### Optimized — Two Pointers with Gap O(n)

**Intuition:** Move `fast` pointer `n` steps ahead. Then move both until `fast` reaches end. `slow` will be just before the target.

```python
def remove_nth_from_end(head, n):
    dummy = ListNode(0, head)
    fast = slow = dummy
    for _ in range(n + 1):   # create gap of n+1
        fast = fast.next
    while fast:
        fast = fast.next
        slow = slow.next
    slow.next = slow.next.next  # skip the target node
    return dummy.next
```

---

## 6.5 LRU Cache

**Companies:** Amazon, Meta, Google, Microsoft, Netflix — *extremely popular design question.*

### Problem Statement
Design a data structure that follows the LRU (Least Recently Used) cache constraint with `get` and `put` in O(1).

### Optimized — Doubly Linked List + Hash Map

```python
class DLLNode:
    def __init__(self, key=0, val=0):
        self.key = key
        self.val = val
        self.prev = None
        self.next = None

class LRUCache:
    def __init__(self, capacity):
        self.cap = capacity
        self.cache = {}           # key -> DLLNode
        self.head = DLLNode()     # dummy head (MRU side)
        self.tail = DLLNode()     # dummy tail (LRU side)
        self.head.next = self.tail
        self.tail.prev = self.head

    def _remove(self, node):
        node.prev.next = node.next
        node.next.prev = node.prev

    def _add_to_front(self, node):
        node.next = self.head.next
        node.prev = self.head
        self.head.next.prev = node
        self.head.next = node

    def get(self, key):
        if key not in self.cache:
            return -1
        node = self.cache[key]
        self._remove(node)
        self._add_to_front(node)  # mark as recently used
        return node.val

    def put(self, key, value):
        if key in self.cache:
            self._remove(self.cache[key])
        node = DLLNode(key, value)
        self.cache[key] = node
        self._add_to_front(node)
        if len(self.cache) > self.cap:
            lru = self.tail.prev   # least recently used
            self._remove(lru)
            del self.cache[lru.key]
```

- **Time:** O(1) for both `get` and `put`
- **Space:** O(capacity)

---

# 7. Stacks & Queues

## Pattern Overview

Stacks (LIFO) and queues (FIFO) are used for **order-dependent processing**. Key patterns: **monotonic stack**, **next greater element**, **valid parentheses**, **BFS with queue**.

### When to Use
- **Stacks:** Matching brackets, expression evaluation, undo operations, monotonic sequences.
- **Queues:** BFS, level-order processing, sliding window maximum.

### Companies: Amazon, Bloomberg, Uber, Google

---

## 7.1 Valid Parentheses

**Companies:** Amazon, Meta, Google, Bloomberg

### Problem Statement
Given a string containing `()[]{}`, determine if the input is valid.

### Optimized — Stack O(n)

```python
def is_valid(s):
    stack = []
    mapping = {')': '(', ']': '[', '}': '{'}
    for char in s:
        if char in mapping:
            top = stack.pop() if stack else '#'
            if mapping[char] != top:
                return False
        else:
            stack.append(char)
    return len(stack) == 0
```

---

## 7.2 Min Stack

**Companies:** Amazon, Google, Microsoft

### Problem Statement
Design a stack that supports `push`, `pop`, `top`, and retrieving the minimum element, all in O(1).

### Optimized — Auxiliary Min Stack

```python
class MinStack:
    def __init__(self):
        self.stack = []
        self.min_stack = []  # tracks minimums

    def push(self, val):
        self.stack.append(val)
        # Push to min_stack if val <= current min
        if not self.min_stack or val <= self.min_stack[-1]:
            self.min_stack.append(val)

    def pop(self):
        val = self.stack.pop()
        if val == self.min_stack[-1]:
            self.min_stack.pop()

    def top(self):
        return self.stack[-1]

    def getMin(self):
        return self.min_stack[-1]
```

---

## 7.3 Daily Temperatures (Monotonic Stack)

**Companies:** Google, Amazon, Meta

### Problem Statement
Given array `temperatures`, return array where `answer[i]` is the number of days until a warmer temperature. If none, `answer[i] = 0`.

### Brute Force — O(n²)
```python
def daily_temperatures_brute(temperatures):
    n = len(temperatures)
    result = [0] * n
    for i in range(n):
        for j in range(i + 1, n):
            if temperatures[j] > temperatures[i]:
                result[i] = j - i
                break
    return result
```

### Optimized — Monotonic Decreasing Stack O(n)

**Intuition:** Maintain a stack of indices with decreasing temperatures. When we find a warmer day, pop and calculate the gap.

```python
def daily_temperatures(temperatures):
    n = len(temperatures)
    result = [0] * n
    stack = []  # stores indices
    for i in range(n):
        while stack and temperatures[i] > temperatures[stack[-1]]:
            prev = stack.pop()
            result[prev] = i - prev
        stack.append(i)
    return result
```

---

## 7.4 Largest Rectangle in Histogram

**Companies:** Google, Amazon, Microsoft — *classic hard problem.*

### Problem Statement
Given array `heights` representing a histogram, find the area of the largest rectangle.

### Optimized — Monotonic Stack O(n)

```python
def largest_rectangle_area(heights):
    stack = []  # stores indices
    max_area = 0
    heights.append(0)  # sentinel to flush remaining bars
    for i, h in enumerate(heights):
        while stack and heights[stack[-1]] > h:
            height = heights[stack.pop()]
            width = i if not stack else i - stack[-1] - 1
            max_area = max(max_area, height * width)
        stack.append(i)
    heights.pop()  # restore original list
    return max_area
```

---

# 8. Recursion & Backtracking

## Pattern Overview

Recursion solves problems by breaking them into smaller sub-problems. **Backtracking** is recursion + pruning: explore a path, if it doesn't lead to a valid solution, undo the last step and try another.

### When to Use
- Generate all combinations / permutations / subsets.
- Solve constraint satisfaction (Sudoku, N-Queens).
- Explore all possible paths (word search, maze).

### Backtracking Template
```python
def backtrack(state, choices):
    if is_solution(state):
        result.append(state[:])  # record solution
        return
    for choice in choices:
        if is_valid(choice):
            state.append(choice)     # make choice
            backtrack(state, ...)    # recurse
            state.pop()             # undo choice (backtrack)
```

### Companies: Google, Meta, Apple, Amazon

---

## 8.1 Subsets

**Companies:** Meta, Google, Amazon

### Problem Statement
Given an integer array `nums` of unique elements, return all possible subsets (the power set).

### Optimized — Backtracking O(n · 2ⁿ)

```python
def subsets(nums):
    result = []

    def backtrack(start, current):
        result.append(current[:])  # add a copy
        for i in range(start, len(nums)):
            current.append(nums[i])
            backtrack(i + 1, current)
            current.pop()           # backtrack

    backtrack(0, [])
    return result
```

### Iterative Approach

```python
def subsets_iterative(nums):
    result = [[]]
    for num in nums:
        result += [subset + [num] for subset in result]
    return result
```

---

## 8.2 Permutations

**Companies:** Meta, Google, Microsoft

### Problem Statement
Given an array `nums` of distinct integers, return all possible permutations.

### Optimized — Backtracking O(n · n!)

```python
def permute(nums):
    result = []

    def backtrack(current, remaining):
        if not remaining:
            result.append(current[:])
            return
        for i in range(len(remaining)):
            current.append(remaining[i])
            backtrack(current, remaining[:i] + remaining[i + 1:])
            current.pop()

    backtrack([], nums)
    return result
```

---

## 8.3 Combination Sum

**Companies:** Google, Amazon, Airbnb

### Problem Statement
Given array `candidates` (no duplicates) and `target`, find all unique combinations that sum to `target`. Each number may be used unlimited times.

### Optimized — Backtracking with Pruning

```python
def combination_sum(candidates, target):
    result = []

    def backtrack(start, current, remaining):
        if remaining == 0:
            result.append(current[:])
            return
        for i in range(start, len(candidates)):
            if candidates[i] > remaining:
                break  # prune: remaining candidates too large
            current.append(candidates[i])
            backtrack(i, current, remaining - candidates[i])  # i, not i+1 (reuse)
            current.pop()

    candidates.sort()  # sort to enable pruning
    backtrack(0, [], target)
    return result
```

---

## 8.4 Word Search

**Companies:** Amazon, Google, Meta, Microsoft

### Problem Statement
Given an `m x n` board of characters and a string `word`, return `True` if `word` exists in the grid. The word must be constructed from adjacent cells (horizontal/vertical), each cell used at most once.

### Optimized — DFS Backtracking O(m · n · 4^L)

```python
def exist(board, word):
    rows, cols = len(board), len(board[0])

    def dfs(r, c, idx):
        if idx == len(word):
            return True
        if r < 0 or r >= rows or c < 0 or c >= cols:
            return False
        if board[r][c] != word[idx]:
            return False

        temp = board[r][c]
        board[r][c] = '#'  # mark visited

        found = (dfs(r + 1, c, idx + 1) or
                 dfs(r - 1, c, idx + 1) or
                 dfs(r, c + 1, idx + 1) or
                 dfs(r, c - 1, idx + 1))

        board[r][c] = temp  # restore (backtrack)
        return found

    for r in range(rows):
        for c in range(cols):
            if dfs(r, c, 0):
                return True
    return False
```

---

## 8.5 N-Queens

**Companies:** Google, Amazon, Meta — *classic hard backtracking.*

### Problem Statement
Place `n` queens on an `n x n` chessboard so that no two queens threaten each other.

### Optimized — Backtracking with Column & Diagonal Sets

```python
def solve_n_queens(n):
    result = []
    board = [['.' ] * n for _ in range(n)]
    cols = set()
    pos_diag = set()   # (row + col) constant on positive diagonals
    neg_diag = set()   # (row - col) constant on negative diagonals

    def backtrack(row):
        if row == n:
            result.append([''.join(r) for r in board])
            return
        for col in range(n):
            if col in cols or (row + col) in pos_diag or (row - col) in neg_diag:
                continue
            # Place queen
            board[row][col] = 'Q'
            cols.add(col)
            pos_diag.add(row + col)
            neg_diag.add(row - col)

            backtrack(row + 1)

            # Remove queen (backtrack)
            board[row][col] = '.'
            cols.remove(col)
            pos_diag.remove(row + col)
            neg_diag.remove(row - col)

    backtrack(0)
    return result
```

---

# 9. Trees (Binary Tree & BST)

## Pattern Overview

Trees are hierarchical data structures. Most problems use **DFS** (preorder, inorder, postorder) or **BFS** (level-order). BSTs add the property: `left < root < right`.

### When to Use
- Hierarchical data.
- Problems mentioning "root," "left/right child," "depth," "height."
- BST problems: search, insert, validate, find kth element.

### Common Node Definition
```python
class TreeNode:
    def __init__(self, val=0, left=None, right=None):
        self.val = val
        self.left = left
        self.right = right
```

### Companies: All — trees are universal in interviews.

---

## 9.1 Maximum Depth of Binary Tree

**Companies:** Amazon, Google, Microsoft

### Optimized — DFS O(n)

```python
def max_depth(root):
    if not root:
        return 0
    return 1 + max(max_depth(root.left), max_depth(root.right))
```

### Iterative BFS

```python
from collections import deque

def max_depth_bfs(root):
    if not root:
        return 0
    queue = deque([root])
    depth = 0
    while queue:
        depth += 1
        for _ in range(len(queue)):
            node = queue.popleft()
            if node.left:
                queue.append(node.left)
            if node.right:
                queue.append(node.right)
    return depth
```

---

## 9.2 Invert Binary Tree

**Companies:** Google, Amazon — *the famous "Homebrew" interview question.*

### Optimized — DFS O(n)

```python
def invert_tree(root):
    if not root:
        return None
    root.left, root.right = invert_tree(root.right), invert_tree(root.left)
    return root
```

---

## 9.3 Binary Tree Level Order Traversal

**Companies:** Meta, Amazon, Microsoft

### Optimized — BFS O(n)

```python
from collections import deque

def level_order(root):
    if not root:
        return []
    result = []
    queue = deque([root])
    while queue:
        level = []
        for _ in range(len(queue)):
            node = queue.popleft()
            level.append(node.val)
            if node.left:
                queue.append(node.left)
            if node.right:
                queue.append(node.right)
        result.append(level)
    return result
```

---

## 9.4 Validate Binary Search Tree

**Companies:** Meta, Amazon, Google, Microsoft

### Problem Statement
Determine if a binary tree is a valid BST.

### Optimized — DFS with Range O(n)

**Intuition:** Each node must be within a valid range `(low, high)`. As we go left the upper bound tightens; as we go right the lower bound tightens.

```python
def is_valid_bst(root):
    def validate(node, low, high):
        if not node:
            return True
        if not (low < node.val < high):
            return False
        return (validate(node.left, low, node.val) and
                validate(node.right, node.val, high))

    return validate(root, float('-inf'), float('inf'))
```

---

## 9.5 Lowest Common Ancestor of a Binary Tree

**Companies:** Meta, Google, Amazon, Microsoft — *very frequently asked.*

### Problem Statement
Given a binary tree and two nodes `p` and `q`, find their lowest common ancestor (LCA).

### Optimized — Recursive DFS O(n)

**Intuition:** If both `p` and `q` are found in different subtrees, current node is LCA. If both are in one subtree, LCA is deeper.

```python
def lowest_common_ancestor(root, p, q):
    if not root or root == p or root == q:
        return root
    left = lowest_common_ancestor(root.left, p, q)
    right = lowest_common_ancestor(root.right, p, q)
    if left and right:
        return root        # p and q are in different subtrees
    return left or right   # both are in the same subtree
```

---

## 9.6 Binary Tree Maximum Path Sum

**Companies:** Google, Meta, Microsoft — *classic hard.*

### Problem Statement
Find the maximum path sum in a binary tree. A path can start and end at any node.

### Optimized — DFS O(n)

```python
def max_path_sum(root):
    max_sum = float('-inf')

    def dfs(node):
        nonlocal max_sum
        if not node:
            return 0
        left_gain = max(dfs(node.left), 0)    # ignore negative paths
        right_gain = max(dfs(node.right), 0)

        # Path through this node as the "turn" (left -> node -> right)
        path_price = node.val + left_gain + right_gain
        max_sum = max(max_sum, path_price)

        # Return max gain if we continue upward (can only pick one branch)
        return node.val + max(left_gain, right_gain)

    dfs(root)
    return max_sum
```

---

## 9.7 Serialize and Deserialize Binary Tree

**Companies:** Meta, Google, Amazon, Microsoft

### Problem Statement
Design an algorithm to serialize and deserialize a binary tree.

### Optimized — Preorder DFS

```python
class Codec:
    def serialize(self, root):
        result = []

        def dfs(node):
            if not node:
                result.append("N")
                return
            result.append(str(node.val))
            dfs(node.left)
            dfs(node.right)

        dfs(root)
        return ",".join(result)

    def deserialize(self, data):
        values = iter(data.split(","))

        def dfs():
            val = next(values)
            if val == "N":
                return None
            node = TreeNode(int(val))
            node.left = dfs()
            node.right = dfs()
            return node

        return dfs()
```

---

## 9.8 Kth Smallest Element in a BST

**Companies:** Amazon, Google, Meta

### Optimized — Inorder Traversal O(H + k)

**Intuition:** Inorder traversal of a BST yields elements in sorted order. The kth visited node is the answer.

```python
def kth_smallest(root, k):
    stack = []
    curr = root
    count = 0
    while curr or stack:
        while curr:
            stack.append(curr)
            curr = curr.left
        curr = stack.pop()
        count += 1
        if count == k:
            return curr.val
        curr = curr.right
```

---

# 10. Heaps / Priority Queues

## Pattern Overview

A heap gives O(log n) insert and O(1) access to the min (or max) element. Use it when you need to repeatedly find the **k-th largest/smallest**, **merge sorted lists**, or **schedule tasks**.

### When to Use
- "Find the k largest / smallest elements."
- "Median of a stream."
- Merge k sorted structures.
- Any problem involving a running minimum/maximum.

### Python's `heapq` — min-heap by default.
```python
import heapq
# Max-heap trick: negate values
```

### Companies: Google, Amazon, Netflix, Meta

---

## 10.1 Kth Largest Element in an Array

**Companies:** Meta, Amazon, Google

### Problem Statement
Find the kth largest element (not kth distinct).

### Brute Force — Sort O(n log n)
```python
def find_kth_largest_brute(nums, k):
    return sorted(nums, reverse=True)[k - 1]
```

### Optimized — Min-Heap of Size k O(n log k)

**Intuition:** Maintain a min-heap of size `k`. After processing all elements, the root is the kth largest.

```python
import heapq

def find_kth_largest(nums, k):
    heap = nums[:k]
    heapq.heapify(heap)        # O(k)
    for num in nums[k:]:
        if num > heap[0]:
            heapq.heapreplace(heap, num)  # pop smallest, push num
    return heap[0]
```

---

## 10.2 Merge K Sorted Lists

**Companies:** Amazon, Google, Meta, Microsoft — *classic.*

### Problem Statement
Merge `k` sorted linked lists into one sorted list.

### Optimized — Min-Heap O(N log k), N = total elements

```python
import heapq

def merge_k_lists(lists):
    heap = []
    for i, l in enumerate(lists):
        if l:
            heapq.heappush(heap, (l.val, i, l))

    dummy = ListNode(0)
    tail = dummy
    while heap:
        val, i, node = heapq.heappop(heap)
        tail.next = node
        tail = tail.next
        if node.next:
            heapq.heappush(heap, (node.next.val, i, node.next))
    return dummy.next
```

---

## 10.3 Find Median from Data Stream

**Companies:** Amazon, Google, Meta, Netflix — *very popular.*

### Problem Statement
Design a data structure that supports `addNum(num)` and `findMedian()` efficiently.

### Optimized — Two Heaps O(log n) add, O(1) find

**Intuition:** Use a max-heap for the smaller half and a min-heap for the larger half. Balance their sizes.

```python
import heapq

class MedianFinder:
    def __init__(self):
        self.small = []  # max-heap (negate values)
        self.large = []  # min-heap

    def addNum(self, num):
        heapq.heappush(self.small, -num)

        # Ensure max of small <= min of large
        if self.small and self.large and (-self.small[0]) > self.large[0]:
            val = -heapq.heappop(self.small)
            heapq.heappush(self.large, val)

        # Balance sizes: small can have at most 1 more element
        if len(self.small) > len(self.large) + 1:
            val = -heapq.heappop(self.small)
            heapq.heappush(self.large, val)
        if len(self.large) > len(self.small):
            val = heapq.heappop(self.large)
            heapq.heappush(self.small, -val)

    def findMedian(self):
        if len(self.small) > len(self.large):
            return -self.small[0]
        return (-self.small[0] + self.large[0]) / 2.0
```

---

## 10.4 Top K Frequent Elements

**Companies:** Amazon, Google, Meta

### Problem Statement
Given an integer array `nums` and integer `k`, return the `k` most frequent elements.

### Optimized — Bucket Sort O(n)

```python
from collections import Counter

def top_k_frequent(nums, k):
    count = Counter(nums)
    # Bucket: index = frequency, value = list of numbers with that frequency
    buckets = [[] for _ in range(len(nums) + 1)]
    for num, freq in count.items():
        buckets[freq].append(num)

    result = []
    for i in range(len(buckets) - 1, 0, -1):
        for num in buckets[i]:
            result.append(num)
            if len(result) == k:
                return result
    return result
```

---

# 11. Tries

## Pattern Overview

A Trie (prefix tree) is a tree-like data structure for efficiently storing and retrieving strings. Each node represents a character, and paths from root to nodes represent prefixes.

### When to Use
- Autocomplete / typeahead.
- Spell checking.
- Word search in a grid.
- Prefix matching.

### Companies: Google, Microsoft, Amazon

---

## 11.1 Implement Trie (Prefix Tree)

**Companies:** Google, Amazon, Microsoft

### Problem Statement
Implement a trie with `insert`, `search`, and `startsWith` methods.

### Optimized Implementation

```python
class TrieNode:
    def __init__(self):
        self.children = {}     # char -> TrieNode
        self.is_end = False    # marks end of a word

class Trie:
    def __init__(self):
        self.root = TrieNode()

    def insert(self, word):
        node = self.root
        for char in word:
            if char not in node.children:
                node.children[char] = TrieNode()
            node = node.children[char]
        node.is_end = True

    def search(self, word):
        node = self._find_node(word)
        return node is not None and node.is_end

    def startsWith(self, prefix):
        return self._find_node(prefix) is not None

    def _find_node(self, prefix):
        node = self.root
        for char in prefix:
            if char not in node.children:
                return None
            node = node.children[char]
        return node
```

---

## 11.2 Word Search II

**Companies:** Google, Amazon, Meta — *hard.*

### Problem Statement
Given an `m x n` board and a list of words, find all words that can be formed by adjacent cells.

### Optimized — Trie + DFS Backtracking

```python
class TrieNode:
    def __init__(self):
        self.children = {}
        self.word = None  # stores the full word when it ends here

def find_words(board, words):
    root = TrieNode()
    for word in words:
        node = root
        for char in word:
            if char not in node.children:
                node.children[char] = TrieNode()
            node = node.children[char]
        node.word = word

    rows, cols = len(board), len(board[0])
    result = []

    def dfs(r, c, node):
        if r < 0 or r >= rows or c < 0 or c >= cols:
            return
        char = board[r][c]
        if char not in node.children:
            return

        child = node.children[char]
        if child.word:
            result.append(child.word)
            child.word = None  # avoid duplicates

        board[r][c] = '#'  # mark visited
        for dr, dc in [(1, 0), (-1, 0), (0, 1), (0, -1)]:
            dfs(r + dr, c + dc, child)
        board[r][c] = char  # restore

        # Prune: remove leaf nodes to speed up future searches
        if not child.children:
            del node.children[char]

    for r in range(rows):
        for c in range(cols):
            dfs(r, c, root)

    return result
```

---

# 12. Graphs

## Pattern Overview

Graphs model relationships. Key algorithms: **BFS**, **DFS**, **Topological Sort**, **Union-Find**, **Dijkstra**, and **Bellman-Ford**.

### Representations
```python
# Adjacency List (most common)
graph = {node: [neighbors]}

# Edge List
edges = [(u, v, weight)]

# Adjacency Matrix
matrix[i][j] = 1  # edge from i to j
```

### When to Use
- Social networks, maps, dependencies.
- "Connected components," "shortest path," "cycle detection."
- Grid problems (grid = implicit graph).

### Companies: Google, Meta, Uber, Apple, Amazon

---

## 12.1 Number of Islands

**Companies:** Amazon, Google, Meta, Microsoft — *most asked graph question.*

### Problem Statement
Given a 2D grid of `'1'`s (land) and `'0'`s (water), count the number of islands.

### Optimized — DFS O(m · n)

```python
def num_islands(grid):
    if not grid:
        return 0
    rows, cols = len(grid), len(grid[0])
    count = 0

    def dfs(r, c):
        if r < 0 or r >= rows or c < 0 or c >= cols or grid[r][c] != '1':
            return
        grid[r][c] = '0'  # mark visited
        dfs(r + 1, c)
        dfs(r - 1, c)
        dfs(r, c + 1)
        dfs(r, c - 1)

    for r in range(rows):
        for c in range(cols):
            if grid[r][c] == '1':
                count += 1
                dfs(r, c)
    return count
```

---

## 12.2 Clone Graph

**Companies:** Meta, Google, Amazon

### Problem Statement
Given a reference of a node in a connected undirected graph, return a deep copy.

```python
class Node:
    def __init__(self, val=0, neighbors=None):
        self.val = val
        self.neighbors = neighbors if neighbors else []
```

### Optimized — BFS with Hash Map O(V + E)

```python
from collections import deque

def clone_graph(node):
    if not node:
        return None
    cloned = {node: Node(node.val)}
    queue = deque([node])
    while queue:
        curr = queue.popleft()
        for neighbor in curr.neighbors:
            if neighbor not in cloned:
                cloned[neighbor] = Node(neighbor.val)
                queue.append(neighbor)
            cloned[curr].neighbors.append(cloned[neighbor])
    return cloned[node]
```

---

## 12.3 Course Schedule (Cycle Detection)

**Companies:** Amazon, Google, Meta, Microsoft

### Problem Statement
There are `numCourses` courses (0 to n-1). Given prerequisite pairs, determine if you can finish all courses. (Detect if the dependency graph has a cycle.)

### Optimized — DFS Cycle Detection O(V + E)

```python
def can_finish(numCourses, prerequisites):
    graph = {i: [] for i in range(numCourses)}
    for course, pre in prerequisites:
        graph[course].append(pre)

    # 0 = unvisited, 1 = visiting (in current path), 2 = visited
    state = [0] * numCourses

    def has_cycle(course):
        if state[course] == 1:
            return True   # cycle detected
        if state[course] == 2:
            return False  # already fully processed
        state[course] = 1
        for pre in graph[course]:
            if has_cycle(pre):
                return True
        state[course] = 2
        return False

    for c in range(numCourses):
        if has_cycle(c):
            return False
    return True
```

---

## 12.4 Course Schedule II (Topological Sort)

**Companies:** Amazon, Google, Meta

### Problem Statement
Return the ordering of courses you should take (topological order). If impossible, return `[]`.

### Optimized — Kahn's Algorithm (BFS) O(V + E)

```python
from collections import deque

def find_order(numCourses, prerequisites):
    graph = {i: [] for i in range(numCourses)}
    in_degree = [0] * numCourses
    for course, pre in prerequisites:
        graph[pre].append(course)
        in_degree[course] += 1

    queue = deque([i for i in range(numCourses) if in_degree[i] == 0])
    order = []
    while queue:
        node = queue.popleft()
        order.append(node)
        for neighbor in graph[node]:
            in_degree[neighbor] -= 1
            if in_degree[neighbor] == 0:
                queue.append(neighbor)

    return order if len(order) == numCourses else []
```

---

## 12.5 Word Ladder

**Companies:** Google, Amazon, Meta

### Problem Statement
Given `beginWord`, `endWord`, and a dictionary `wordList`, find the length of the shortest transformation sequence (change one letter at a time, each intermediate word must be in `wordList`).

### Optimized — BFS O(M² · N) where M = word length, N = word count

```python
from collections import deque

def ladder_length(beginWord, endWord, wordList):
    word_set = set(wordList)
    if endWord not in word_set:
        return 0

    queue = deque([(beginWord, 1)])
    visited = {beginWord}

    while queue:
        word, length = queue.popleft()
        for i in range(len(word)):
            for c in 'abcdefghijklmnopqrstuvwxyz':
                next_word = word[:i] + c + word[i + 1:]
                if next_word == endWord:
                    return length + 1
                if next_word in word_set and next_word not in visited:
                    visited.add(next_word)
                    queue.append((next_word, length + 1))
    return 0
```

---

## 12.6 Pacific Atlantic Water Flow

**Companies:** Google, Amazon

### Problem Statement
Given an `m x n` matrix of heights, find all cells where water can flow to both the Pacific and Atlantic oceans.

### Optimized — Reverse BFS/DFS from Oceans O(m · n)

**Intuition:** Instead of flowing from each cell to the ocean, start from the ocean borders and flow uphill.

```python
def pacific_atlantic(heights):
    if not heights:
        return []
    rows, cols = len(heights), len(heights[0])
    pacific = set()
    atlantic = set()

    def dfs(r, c, visited, prev_height):
        if (r, c) in visited or r < 0 or r >= rows or c < 0 or c >= cols:
            return
        if heights[r][c] < prev_height:
            return
        visited.add((r, c))
        for dr, dc in [(1, 0), (-1, 0), (0, 1), (0, -1)]:
            dfs(r + dr, c + dc, visited, heights[r][c])

    for c in range(cols):
        dfs(0, c, pacific, heights[0][c])            # top row -> Pacific
        dfs(rows - 1, c, atlantic, heights[rows - 1][c])  # bottom row -> Atlantic
    for r in range(rows):
        dfs(r, 0, pacific, heights[r][0])             # left col -> Pacific
        dfs(r, cols - 1, atlantic, heights[r][cols - 1])  # right col -> Atlantic

    return list(pacific & atlantic)
```

---

## 12.7 Graph Valid Tree

**Companies:** Google, Meta

### Problem Statement
Given `n` nodes and a list of undirected edges, check if these edges form a valid tree. (A valid tree is connected and has no cycles.)

### Optimized — Union-Find O(n · α(n))

```python
def valid_tree(n, edges):
    if len(edges) != n - 1:  # a tree has exactly n-1 edges
        return False

    parent = list(range(n))
    rank = [0] * n

    def find(x):
        while parent[x] != x:
            parent[x] = parent[parent[x]]  # path compression
            x = parent[x]
        return x

    def union(x, y):
        px, py = find(x), find(y)
        if px == py:
            return False  # cycle
        if rank[px] < rank[py]:
            px, py = py, px
        parent[py] = px
        if rank[px] == rank[py]:
            rank[px] += 1
        return True

    return all(union(u, v) for u, v in edges)
```

---

## 12.8 Dijkstra's Algorithm — Network Delay Time

**Companies:** Google, Amazon, Uber

### Problem Statement
Given `n` nodes and weighted directed edges `times`, find the time for a signal sent from node `k` to reach all nodes. Return `-1` if impossible.

### Optimized — Dijkstra O(E log V)

```python
import heapq
from collections import defaultdict

def network_delay_time(times, n, k):
    graph = defaultdict(list)
    for u, v, w in times:
        graph[u].append((v, w))

    dist = {k: 0}
    heap = [(0, k)]  # (distance, node)
    while heap:
        d, u = heapq.heappop(heap)
        if d > dist.get(u, float('inf')):
            continue
        for v, w in graph[u]:
            new_dist = d + w
            if new_dist < dist.get(v, float('inf')):
                dist[v] = new_dist
                heapq.heappush(heap, (new_dist, v))

    if len(dist) == n:
        return max(dist.values())
    return -1
```

---

# 13. Dynamic Programming

## Pattern Overview

DP solves problems by breaking them into overlapping sub-problems and caching results. Two approaches:
- **Top-down (Memoization):** Recursive + cache.
- **Bottom-up (Tabulation):** Iterative, fill a table.

### When to Use
- Optimization problems (min/max/count).
- Problem has **optimal substructure** and **overlapping sub-problems**.
- Keywords: "minimum cost," "number of ways," "longest/shortest."

### DP Categories
| Category | Examples |
|----------|---------|
| **1D DP** | Climbing Stairs, House Robber, Coin Change |
| **2D DP** | Unique Paths, LCS, Edit Distance |
| **Knapsack** | 0/1 Knapsack, Partition Equal Subset Sum |
| **String DP** | Palindromic Substrings, Word Break |
| **Interval DP** | Burst Balloons, Matrix Chain |
| **State Machine** | Buy & Sell Stock with cooldown/fee |

### Companies: Google, Amazon, Meta, Microsoft — *DP is the #1 hardest category in interviews.*

---

## 13.1 Climbing Stairs

**Companies:** Amazon, Google, Apple

### Problem Statement
You can climb 1 or 2 steps at a time. How many distinct ways can you climb `n` steps?

### Brute Force — Recursion O(2ⁿ)
```python
def climb_stairs_brute(n):
    if n <= 2:
        return n
    return climb_stairs_brute(n - 1) + climb_stairs_brute(n - 2)
```

### Optimized — Bottom-Up DP O(n)

```python
def climb_stairs(n):
    if n <= 2:
        return n
    prev2, prev1 = 1, 2
    for _ in range(3, n + 1):
        curr = prev1 + prev2
        prev2, prev1 = prev1, curr
    return prev1
```

- **Time:** O(n) | **Space:** O(1)

---

## 13.2 House Robber

**Companies:** Amazon, Google, Microsoft

### Problem Statement
Rob houses along a street. Can't rob two adjacent houses. Maximize the total amount.

### Brute Force — Recursion O(2ⁿ)
```python
def rob_brute(nums):
    def helper(i):
        if i < 0:
            return 0
        return max(helper(i - 1), helper(i - 2) + nums[i])
    return helper(len(nums) - 1)
```

### Optimized — Bottom-Up DP O(n)

**Intuition:** At each house, choose to rob it (skip previous) or skip it (keep previous best).

```python
def rob(nums):
    if not nums:
        return 0
    if len(nums) == 1:
        return nums[0]
    prev2, prev1 = 0, 0
    for num in nums:
        curr = max(prev1, prev2 + num)
        prev2, prev1 = prev1, curr
    return prev1
```

---

## 13.3 Coin Change

**Companies:** Amazon, Google, Meta, Microsoft

### Problem Statement
Given coins of different denominations and a total `amount`, find the fewest coins needed to make up that amount. Return `-1` if impossible.

### Brute Force — Recursion O(amount^n)
```python
def coin_change_brute(coins, amount):
    if amount == 0:
        return 0
    if amount < 0:
        return float('inf')
    best = float('inf')
    for coin in coins:
        res = coin_change_brute(coins, amount - coin)
        best = min(best, res + 1)
    return best if best != float('inf') else -1
```

### Optimized — Bottom-Up DP O(amount · n)

```python
def coin_change(coins, amount):
    dp = [float('inf')] * (amount + 1)
    dp[0] = 0  # base case: 0 coins needed for amount 0
    for a in range(1, amount + 1):
        for coin in coins:
            if coin <= a:
                dp[a] = min(dp[a], dp[a - coin] + 1)
    return dp[amount] if dp[amount] != float('inf') else -1
```

---

## 13.4 Longest Common Subsequence

**Companies:** Google, Amazon, Microsoft

### Problem Statement
Given two strings `text1` and `text2`, return the length of their longest common subsequence.

### Brute Force — Recursion O(2^(m+n))
```python
def lcs_brute(text1, text2):
    def helper(i, j):
        if i == len(text1) or j == len(text2):
            return 0
        if text1[i] == text2[j]:
            return 1 + helper(i + 1, j + 1)
        return max(helper(i + 1, j), helper(i, j + 1))
    return helper(0, 0)
```

### Optimized — 2D DP O(m · n)

```python
def longest_common_subsequence(text1, text2):
    m, n = len(text1), len(text2)
    dp = [[0] * (n + 1) for _ in range(m + 1)]
    for i in range(m - 1, -1, -1):
        for j in range(n - 1, -1, -1):
            if text1[i] == text2[j]:
                dp[i][j] = 1 + dp[i + 1][j + 1]
            else:
                dp[i][j] = max(dp[i + 1][j], dp[i][j + 1])
    return dp[0][0]
```

---

## 13.5 0/1 Knapsack

**Companies:** Google, Amazon, Microsoft

### Problem Statement
Given `n` items with weights and values, and a knapsack capacity `W`, maximize the total value without exceeding the capacity.

### Brute Force — Recursion O(2ⁿ)
```python
def knapsack_brute(weights, values, W):
    def helper(i, remaining):
        if i < 0 or remaining == 0:
            return 0
        # Skip item i
        result = helper(i - 1, remaining)
        # Take item i (if it fits)
        if weights[i] <= remaining:
            result = max(result, values[i] + helper(i - 1, remaining - weights[i]))
        return result
    return helper(len(weights) - 1, W)
```

### Optimized — 1D DP O(n · W)

```python
def knapsack(weights, values, W):
    n = len(weights)
    dp = [0] * (W + 1)
    for i in range(n):
        for w in range(W, weights[i] - 1, -1):  # reverse to avoid reuse
            dp[w] = max(dp[w], dp[w - weights[i]] + values[i])
    return dp[W]
```

---

## 13.6 Word Break

**Companies:** Meta, Amazon, Google

### Problem Statement
Given string `s` and dictionary `wordDict`, return `True` if `s` can be segmented into space-separated dictionary words.

### Optimized — DP O(n² · m)

```python
def word_break(s, wordDict):
    word_set = set(wordDict)
    n = len(s)
    dp = [False] * (n + 1)
    dp[0] = True  # empty string is always valid
    for i in range(1, n + 1):
        for j in range(i):
            if dp[j] and s[j:i] in word_set:
                dp[i] = True
                break
    return dp[n]
```

---

## 13.7 Longest Increasing Subsequence

**Companies:** Google, Amazon, Microsoft, Apple

### Problem Statement
Given an integer array `nums`, return the length of the longest strictly increasing subsequence.

### Brute Force — DP O(n²)
```python
def length_of_lis_brute(nums):
    n = len(nums)
    dp = [1] * n
    for i in range(1, n):
        for j in range(i):
            if nums[j] < nums[i]:
                dp[i] = max(dp[i], dp[j] + 1)
    return max(dp)
```

### Optimized — Binary Search O(n log n)

**Intuition:** Maintain a list `tails` where `tails[i]` is the smallest tail of all increasing subsequences of length `i + 1`.

```python
import bisect

def length_of_lis(nums):
    tails = []
    for num in nums:
        pos = bisect.bisect_left(tails, num)
        if pos == len(tails):
            tails.append(num)
        else:
            tails[pos] = num  # replace to keep smallest possible tail
    return len(tails)
```

---

## 13.8 Edit Distance

**Companies:** Google, Amazon, Microsoft

### Problem Statement
Given two strings `word1` and `word2`, return the minimum number of operations (insert, delete, replace) to convert `word1` to `word2`.

### Optimized — 2D DP O(m · n)

```python
def min_distance(word1, word2):
    m, n = len(word1), len(word2)
    dp = [[0] * (n + 1) for _ in range(m + 1)]

    # Base cases
    for i in range(m + 1):
        dp[i][0] = i  # delete all chars from word1
    for j in range(n + 1):
        dp[0][j] = j  # insert all chars of word2

    for i in range(1, m + 1):
        for j in range(1, n + 1):
            if word1[i - 1] == word2[j - 1]:
                dp[i][j] = dp[i - 1][j - 1]  # no operation needed
            else:
                dp[i][j] = 1 + min(
                    dp[i - 1][j],      # delete
                    dp[i][j - 1],      # insert
                    dp[i - 1][j - 1]   # replace
                )
    return dp[m][n]
```

---

## 13.9 Unique Paths

**Companies:** Google, Amazon, Meta

### Problem Statement
A robot is at the top-left of an `m x n` grid. It can only move right or down. How many unique paths to the bottom-right?

### Optimized — DP O(m · n)

```python
def unique_paths(m, n):
    dp = [1] * n  # first row: all 1s
    for _ in range(1, m):
        for j in range(1, n):
            dp[j] += dp[j - 1]
    return dp[-1]
```

- **Time:** O(m · n) | **Space:** O(n)

---

## 13.10 Partition Equal Subset Sum

**Companies:** Amazon, Google, Meta

### Problem Statement
Given a non-empty array `nums`, determine if it can be partitioned into two subsets with equal sum.

### Optimized — DP (Subset Sum variant) O(n · sum)

```python
def can_partition(nums):
    total = sum(nums)
    if total % 2 != 0:
        return False
    target = total // 2
    dp = [False] * (target + 1)
    dp[0] = True
    for num in nums:
        for j in range(target, num - 1, -1):  # reverse to avoid reusing
            dp[j] = dp[j] or dp[j - num]
    return dp[target]
```

---

# 14. Greedy Algorithms

## Pattern Overview

Greedy makes the locally optimal choice at each step, hoping to find the global optimum. Unlike DP, greedy never reconsiders choices.

### When to Use
- Problem has **greedy choice property**: a local optimum leads to a global optimum.
- Interval scheduling, activity selection, Huffman coding.
- Often used with sorting.

### Companies: Amazon, Google, Uber, Apple

---

## 14.1 Jump Game

**Companies:** Amazon, Google, Meta

### Problem Statement
Given array `nums`, where `nums[i]` is your max jump from position `i`, determine if you can reach the last index.

### Optimized — Greedy O(n)

**Intuition:** Track the farthest index reachable. If current index exceeds it, we're stuck.

```python
def can_jump(nums):
    farthest = 0
    for i in range(len(nums)):
        if i > farthest:
            return False
        farthest = max(farthest, i + nums[i])
    return True
```

---

## 14.2 Jump Game II

**Companies:** Amazon, Google, Meta

### Problem Statement
Minimum number of jumps to reach the last index.

### Optimized — BFS-style Greedy O(n)

```python
def jump(nums):
    jumps = 0
    current_end = farthest = 0
    for i in range(len(nums) - 1):
        farthest = max(farthest, i + nums[i])
        if i == current_end:  # must jump
            jumps += 1
            current_end = farthest
    return jumps
```

---

## 14.3 Gas Station

**Companies:** Amazon, Google

### Problem Statement
There are `n` gas stations around a circular route. At station `i`, you get `gas[i]` fuel and it costs `cost[i]` to travel to the next station. Find the starting station index, or `-1` if the circuit can't be completed.

### Optimized — Greedy O(n)

```python
def can_complete_circuit(gas, cost):
    if sum(gas) < sum(cost):
        return -1  # not enough total gas
    tank = 0
    start = 0
    for i in range(len(gas)):
        tank += gas[i] - cost[i]
        if tank < 0:  # can't reach next station from current start
            start = i + 1
            tank = 0
    return start
```

---

## 14.4 Task Scheduler

**Companies:** Meta, Amazon, Google

### Problem Statement
Given tasks with a cooldown `n` between same tasks, find the minimum intervals to finish all tasks.

### Optimized — Greedy with Math O(N)

```python
from collections import Counter

def least_interval(tasks, n):
    counts = Counter(tasks)
    max_count = max(counts.values())
    # Number of tasks that have the maximum frequency
    max_count_tasks = sum(1 for v in counts.values() if v == max_count)
    # Minimum slots = (max_count - 1) * (n + 1) + max_count_tasks
    result = (max_count - 1) * (n + 1) + max_count_tasks
    return max(result, len(tasks))  # can't be less than total tasks
```

---

# 15. Intervals

## Pattern Overview

Interval problems involve ranges `[start, end]`. Key operations: **merge**, **insert**, **intersection**, **minimum platforms**.

### When to Use
- Problems with time ranges, schedules, or overlapping segments.
- Keywords: "overlapping," "merge," "interval."

### Key Insight
**Always sort by start time** (or end time, depending on the problem). Then process linearly.

### Companies: Google, Meta, Uber, Amazon

---

## 15.1 Merge Intervals

**Companies:** Google, Meta, Amazon, Uber — *very frequently asked.*

### Problem Statement
Given a collection of intervals, merge all overlapping intervals.

### Optimized — Sort + Linear Scan O(n log n)

```python
def merge_intervals(intervals):
    intervals.sort(key=lambda x: x[0])
    merged = [intervals[0]]
    for start, end in intervals[1:]:
        if start <= merged[-1][1]:  # overlapping
            merged[-1][1] = max(merged[-1][1], end)
        else:
            merged.append([start, end])
    return merged
```

---

## 15.2 Insert Interval

**Companies:** Google, Meta

### Problem Statement
Insert a new interval into a sorted, non-overlapping list of intervals. Merge if necessary.

### Optimized — Linear Scan O(n)

```python
def insert(intervals, newInterval):
    result = []
    for i, (start, end) in enumerate(intervals):
        if newInterval[1] < start:
            # newInterval comes before current, no more overlaps
            result.append(newInterval)
            return result + intervals[i:]
        elif newInterval[0] > end:
            # current comes before newInterval
            result.append([start, end])
        else:
            # overlap, merge
            newInterval = [min(newInterval[0], start), max(newInterval[1], end)]
    result.append(newInterval)
    return result
```

---

## 15.3 Non-Overlapping Intervals

**Companies:** Google, Amazon

### Problem Statement
Given intervals, find the minimum number of intervals to remove to make the rest non-overlapping.

### Optimized — Greedy (Sort by end) O(n log n)

**Intuition:** Keep intervals that end earliest → maximize room for subsequent intervals.

```python
def erase_overlap_intervals(intervals):
    intervals.sort(key=lambda x: x[1])
    prev_end = float('-inf')
    removals = 0
    for start, end in intervals:
        if start >= prev_end:
            prev_end = end   # keep this interval
        else:
            removals += 1   # remove this interval (overlaps)
    return removals
```

---

## 15.4 Meeting Rooms II

**Companies:** Google, Meta, Amazon, Uber

### Problem Statement
Given meeting time intervals, find the minimum number of conference rooms required.

### Optimized — Min-Heap O(n log n)

```python
import heapq

def min_meeting_rooms(intervals):
    if not intervals:
        return 0
    intervals.sort(key=lambda x: x[0])
    heap = [intervals[0][1]]  # end time of first meeting
    for start, end in intervals[1:]:
        if start >= heap[0]:
            heapq.heappop(heap)  # reuse room
        heapq.heappush(heap, end)
    return len(heap)
```

### Alternative — Line Sweep O(n log n)

```python
def min_meeting_rooms_sweep(intervals):
    events = []
    for start, end in intervals:
        events.append((start, 1))   # meeting starts
        events.append((end, -1))    # meeting ends
    events.sort()
    rooms = max_rooms = 0
    for _, delta in events:
        rooms += delta
        max_rooms = max(max_rooms, rooms)
    return max_rooms
```

---

# 16. Bit Manipulation

## Pattern Overview

Bit manipulation uses bitwise operators (`&`, `|`, `^`, `~`, `<<`, `>>`) for efficient computation. Useful for toggling, checking, and counting bits.

### Key Operations
| Operation | Code | Purpose |
|-----------|------|---------|
| Check if bit `i` is set | `n & (1 << i)` | Test specific bit |
| Set bit `i` | `n \| (1 << i)` | Turn bit on |
| Clear bit `i` | `n & ~(1 << i)` | Turn bit off |
| Toggle bit `i` | `n ^ (1 << i)` | Flip bit |
| Remove lowest set bit | `n & (n - 1)` | Count bits / power of 2 check |
| Isolate lowest set bit | `n & (-n)` | Find rightmost 1 |

### Companies: Apple, Google, Microsoft

---

## 16.1 Single Number

**Companies:** Amazon, Google, Apple

### Problem Statement
Every element appears twice except one. Find the single element.

### Optimized — XOR O(n)

**Intuition:** `a ^ a = 0` and `a ^ 0 = a`. XOR all elements; duplicates cancel out.

```python
def single_number(nums):
    result = 0
    for num in nums:
        result ^= num
    return result
```

---

## 16.2 Number of 1 Bits

**Companies:** Apple, Microsoft

### Optimized — Brian Kernighan's Algorithm O(k) where k = number of set bits

```python
def hamming_weight(n):
    count = 0
    while n:
        n &= (n - 1)  # clears the lowest set bit
        count += 1
    return count
```

---

## 16.3 Counting Bits

**Companies:** Google, Amazon

### Problem Statement
Given integer `n`, return array `ans` where `ans[i]` is the number of 1s in binary of `i`, for `0 <= i <= n`.

### Optimized — DP with Bit Trick O(n)

```python
def count_bits(n):
    dp = [0] * (n + 1)
    for i in range(1, n + 1):
        dp[i] = dp[i >> 1] + (i & 1)  # half the number + last bit
    return dp
```

---

## 16.4 Reverse Bits

**Companies:** Apple, Google

### Optimized — Bit by Bit O(32) = O(1)

```python
def reverse_bits(n):
    result = 0
    for _ in range(32):
        result = (result << 1) | (n & 1)
        n >>= 1
    return result
```

---

## 16.5 Missing Number

**Companies:** Microsoft, Amazon

### Problem Statement
Given array `nums` containing `n` distinct numbers in `[0, n]`, find the missing number.

### Optimized — XOR O(n)

```python
def missing_number(nums):
    result = len(nums)
    for i, num in enumerate(nums):
        result ^= i ^ num
    return result
```

---

# 17. Math & Geometry

## Pattern Overview

Math problems test number theory, modular arithmetic, and geometric reasoning. Common topics: **primes**, **GCD**, **matrix operations**, **rotation**, **random sampling**.

### Companies: Google, Apple, Microsoft

---

## 17.1 Rotate Image

**Companies:** Amazon, Google, Microsoft

### Problem Statement
Rotate an `n x n` matrix 90 degrees clockwise **in-place**.

### Optimized — Transpose + Reverse O(n²)

```python
def rotate(matrix):
    n = len(matrix)
    # Step 1: Transpose (swap rows and columns)
    for i in range(n):
        for j in range(i + 1, n):
            matrix[i][j], matrix[j][i] = matrix[j][i], matrix[i][j]
    # Step 2: Reverse each row
    for row in matrix:
        row.reverse()
```

---

## 17.2 Spiral Matrix

**Companies:** Amazon, Google, Microsoft, Apple

### Problem Statement
Given an `m x n` matrix, return all elements in spiral order.

### Optimized — Boundary Simulation O(m · n)

```python
def spiral_order(matrix):
    result = []
    top, bottom = 0, len(matrix) - 1
    left, right = 0, len(matrix[0]) - 1
    while top <= bottom and left <= right:
        for c in range(left, right + 1):      # traverse right
            result.append(matrix[top][c])
        top += 1
        for r in range(top, bottom + 1):      # traverse down
            result.append(matrix[r][right])
        right -= 1
        if top <= bottom:
            for c in range(right, left - 1, -1):  # traverse left
                result.append(matrix[bottom][c])
            bottom -= 1
        if left <= right:
            for r in range(bottom, top - 1, -1):  # traverse up
                result.append(matrix[r][left])
            left += 1
    return result
```

---

## 17.3 Set Matrix Zeroes

**Companies:** Amazon, Microsoft

### Problem Statement
If an element is 0, set its entire row and column to 0. Do it **in-place**.

### Optimized — Use First Row/Column as Markers O(m · n) time, O(1) space

```python
def set_zeroes(matrix):
    m, n = len(matrix), len(matrix[0])
    first_row_zero = any(matrix[0][j] == 0 for j in range(n))
    first_col_zero = any(matrix[i][0] == 0 for i in range(m))

    # Mark zeros in first row/col
    for i in range(1, m):
        for j in range(1, n):
            if matrix[i][j] == 0:
                matrix[i][0] = 0
                matrix[0][j] = 0

    # Zero out marked rows and columns
    for i in range(1, m):
        for j in range(1, n):
            if matrix[i][0] == 0 or matrix[0][j] == 0:
                matrix[i][j] = 0

    # Handle first row and column
    if first_row_zero:
        for j in range(n):
            matrix[0][j] = 0
    if first_col_zero:
        for i in range(m):
            matrix[i][0] = 0
```

---

## 17.4 Pow(x, n) — Fast Exponentiation

**Companies:** Google, Meta, Amazon

### Problem Statement
Implement `pow(x, n)` — compute x raised to the power n.

### Optimized — Binary Exponentiation O(log n)

```python
def my_pow(x, n):
    if n < 0:
        x, n = 1 / x, -n
    result = 1
    while n:
        if n & 1:         # n is odd
            result *= x
        x *= x
        n >>= 1
    return result
```

---

## 17.5 Happy Number

**Companies:** Apple, Google

### Problem Statement
A happy number reaches 1 by repeatedly replacing it with the sum of the squares of its digits. Return `True` if `n` is happy.

### Optimized — Floyd's Cycle Detection O(log n)

```python
def is_happy(n):
    def get_next(num):
        return sum(int(d) ** 2 for d in str(num))

    slow = n
    fast = get_next(n)
    while fast != 1 and slow != fast:
        slow = get_next(slow)
        fast = get_next(get_next(fast))
    return fast == 1
```

---

# 18. Tips, Pattern Recognition & Company Guide

## How to Recognize Patterns in Interviews

| If You See... | Think About... |
|--------------|----------------|
| "Sorted array" | Binary Search, Two Pointers |
| "Subarray/substring" + condition | Sliding Window |
| "All combinations/permutations" | Backtracking |
| "Min/max cost, number of ways" | Dynamic Programming |
| "Connected components, shortest path" | Graph BFS/DFS |
| "Top K / Kth largest" | Heap |
| "Prefix matching, autocomplete" | Trie |
| "Matching brackets, nested" | Stack |
| "Overlapping intervals, schedules" | Intervals (sort + scan) |
| "Stream of data" | Heap / Sliding Window |
| "In-place, O(1) space" | Two Pointers / Bit Manipulation |
| "Tree structure, hierarchical" | DFS / BFS on Tree |
| "Can I reach the end / is it possible" | Greedy / DP / BFS |
| "Grid traversal" | BFS / DFS (Graph) |

---

## Company-Specific Focus Areas

| Company | Top Focus Areas |
|---------|----------------|
| **Google** | Graphs, DP, Binary Search, Tries, Backtracking, Math |
| **Meta (Facebook)** | Trees, Graphs, Sliding Window, Two Pointers, DP |
| **Amazon** | Arrays, Trees, Graphs, DP, Greedy, System Design |
| **Microsoft** | Linked Lists, Trees, Arrays, DP, Stacks |
| **Apple** | Arrays, Bit Manipulation, Trees, Two Pointers, Math |
| **Netflix** | Heaps, Graphs, Sliding Window, System Design |
| **Uber** | Graphs, Intervals, Sliding Window, Greedy, DP |
| **Bloomberg** | Stacks, Queues, Trees, Arrays |
| **Goldman Sachs** | Arrays, Two Pointers, DP, Math |

---

## Common Pitfalls to Avoid

1. **Not clarifying the problem** — Always ask about edge cases, constraints, input size.
2. **Jumping to code** — Explain your approach first. Interviewers want to see your thought process.
3. **Ignoring edge cases:**
   - Empty input
   - Single element
   - All duplicates
   - Negative numbers
   - Integer overflow (less common in Python)
4. **Off-by-one errors** — Especially in binary search and sliding window.
5. **Mutating input** — Ask if it's okay to modify the input array/list.
6. **Not testing** — Walk through your solution with a small example.
7. **Over-engineering** — Start with brute force, then optimize. Don't try to write the perfect solution immediately.

---

## Study Plan — 8-Week Schedule

| Week | Focus | Problems Per Day |
|------|-------|-----------------|
| 1 | Arrays, Strings, Hashing | 3–4 |
| 2 | Two Pointers, Sliding Window | 3–4 |
| 3 | Binary Search, Stacks, Queues | 3 |
| 4 | Linked Lists, Trees | 3 |
| 5 | Graphs (BFS, DFS, Topological Sort) | 3 |
| 6 | Dynamic Programming (1D, 2D) | 2–3 |
| 7 | DP (Knapsack, Strings), Greedy, Intervals | 2–3 |
| 8 | Heaps, Tries, Bit Manipulation, Revision | 3 |

---

## Interview Day Checklist

- [ ] Read the problem **twice**.
- [ ] Identify the **pattern** (use the table above).
- [ ] Discuss **brute force** first with complexity.
- [ ] Optimize step by step — communicate your thought process.
- [ ] Write **clean code** with meaningful variable names.
- [ ] Trace through your solution with a **small example**.
- [ ] Discuss **edge cases** proactively.
- [ ] State **time and space complexity** clearly.

---

## Additional Resources

- **LeetCode** — [leetcode.com](https://leetcode.com) (Primary problem source)
- **NeetCode** — [neetcode.io](https://neetcode.io) (Curated problem lists with video explanations)
- **Blind 75** — The 75 must-do problems for interviews
- **Grind 75** — Customizable study plan
- **Cracking the Coding Interview** — Classic textbook

---

> **Remember:** Consistency beats intensity. Solve 2–3 problems daily with deep understanding rather than rushing through 10 problems superficially. Understand the *why* behind each pattern, not just the *how*.

---

*Built with ❤️ for aspiring FAANG engineers. Good luck with your interviews!*
