# 🚀 DSA Interview Preparation Guide — Java Edition

> **A one-stop, pattern-based resource for mastering Data Structures & Algorithms for top tech company interviews (Google, Meta, Apple, Microsoft, Amazon, Netflix, Uber, and more).**

---

## 📖 Table of Contents

| # | Pattern | Difficulty | Key Companies |
|---|---------|-----------|---------------|
| 1 | [Arrays & Hashing](#1-arrays--hashing) | Easy–Medium | Google, Amazon, Meta, Microsoft |
| 2 | [Two Pointers](#2-two-pointers) | Easy–Medium | Google, Apple, Meta, Uber |
| 3 | [Sliding Window](#3-sliding-window) | Medium | Google, Amazon, Microsoft, Netflix |
| 4 | [Strings](#4-strings) | Easy–Medium | Amazon, Google, Microsoft, Apple |
| 5 | [Binary Search](#5-binary-search) | Medium | Google, Meta, Amazon, Uber |
| 6 | [Stack & Queue](#6-stack--queue) | Easy–Medium | Amazon, Microsoft, Google, Bloomberg |
| 7 | [Linked List](#7-linked-list) | Easy–Medium | Microsoft, Amazon, Meta, Apple |
| 8 | [Recursion & Backtracking](#8-recursion--backtracking) | Medium–Hard | Google, Meta, Amazon, Apple |
| 9 | [Trees (Binary Tree & BST)](#9-trees) | Medium–Hard | Google, Microsoft, Amazon, Meta |
| 10 | [Graphs](#10-graphs) | Medium–Hard | Google, Meta, Uber, Netflix, Apple |
| 11 | [Heap / Priority Queue](#11-heap--priority-queue) | Medium | Amazon, Google, Meta, Uber |
| 12 | [Trie](#12-trie) | Medium–Hard | Google, Amazon, Microsoft |
| 13 | [Dynamic Programming](#13-dynamic-programming) | Medium–Hard | Google, Amazon, Meta, Apple, Uber |
| 14 | [Greedy Algorithms](#14-greedy-algorithms) | Medium | Google, Amazon, Uber |
| 15 | [Intervals](#15-intervals) | Medium | Google, Meta, Amazon, Uber |
| 16 | [Bit Manipulation](#16-bit-manipulation) | Easy–Medium | Apple, Google, Microsoft |
| 17 | [Math & Geometry](#17-math--geometry) | Easy–Hard | Google, Apple, Goldman Sachs |
|   | [Study Plan & Timeline](#-study-plan--timeline) | — | — |
|   | [Company-wise Pattern Frequency](#-company-wise-pattern-frequency) | — | — |
|   | [General Interview Tips](#-general-interview-tips) | — | — |

---

## How to Use This Guide

1. **Study pattern by pattern**, not problem by problem.
2. For each pattern, read the **explanation** first to understand *when* and *why* to apply it.
3. Attempt each problem on your own before reading the solution.
4. Start with the **brute force**, then understand the **optimized** approach.
5. Pay special attention to **edge cases** and **pitfalls** — interviewers test these.
6. Use the **company tags** to prioritize patterns for your target company.

---

## Complexity Cheat Sheet

| Notation | Name | Example |
|----------|------|---------|
| O(1) | Constant | HashMap lookup |
| O(log n) | Logarithmic | Binary Search |
| O(n) | Linear | Single pass array scan |
| O(n log n) | Linearithmic | Merge Sort |
| O(n²) | Quadratic | Nested loops |
| O(2ⁿ) | Exponential | Recursive subsets |
| O(n!) | Factorial | Permutations |

---

# 1. Arrays & Hashing

## Pattern Overview

Arrays are the most fundamental data structure. Hashing (via `HashMap`/`HashSet`) provides O(1) average lookups and is the single most useful technique for optimizing brute-force array problems.

**When to use:**
- Need to find pairs/groups satisfying a condition
- Frequency counting
- Detecting duplicates
- Mapping relationships between elements

**How to recognize:**
- "Find if there exist two elements that …"
- "Count the frequency of …"
- "Return indices of …"
- "Group elements by …"

**Companies:** Google, Amazon, Meta, Microsoft, Apple, Uber, Netflix

---

### 1.1 Two Sum

**Problem:** Given an array of integers `nums` and an integer `target`, return the indices of the two numbers such that they add up to `target`. Each input has exactly one solution, and you may not use the same element twice.

#### Brute Force

**Explanation:** Check every possible pair `(i, j)` where `i < j`.

| Complexity | Value |
|-----------|-------|
| Time | O(n²) |
| Space | O(1) |

```java
class Solution {
    public int[] twoSum(int[] nums, int target) {
        // Check every pair
        for (int i = 0; i < nums.length; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                if (nums[i] + nums[j] == target) {
                    return new int[]{i, j};
                }
            }
        }
        return new int[]{};
    }
}
```

#### Optimized Approach — HashMap

**Intuition:** For each element `nums[i]`, we need `target - nums[i]` to exist somewhere else. A HashMap lets us check this in O(1).

**Steps:**
1. Iterate through the array once.
2. For each element, compute `complement = target - nums[i]`.
3. If `complement` is already in the map, we found our answer.
4. Otherwise, store `nums[i] -> i` in the map for future lookups.

| Complexity | Value |
|-----------|-------|
| Time | O(n) |
| Space | O(n) |

```java
class Solution {
    public int[] twoSum(int[] nums, int target) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            int complement = target - nums[i];
            // Check if complement was seen before
            if (map.containsKey(complement)) {
                return new int[]{map.get(complement), i};
            }
            // Store current value and its index
            map.put(nums[i], i);
        }
        return new int[]{};
    }
}
```

**Common Pitfalls:**
- Don't use the same element twice — store *after* checking.
- Handle negative numbers and zeros properly.

---

### 1.2 Contains Duplicate

**Problem:** Given an integer array `nums`, return `true` if any value appears at least twice in the array, and `false` if every element is distinct.

#### Brute Force

**Explanation:** Compare every pair of elements.

| Complexity | Value |
|-----------|-------|
| Time | O(n²) |
| Space | O(1) |

```java
class Solution {
    public boolean containsDuplicate(int[] nums) {
        for (int i = 0; i < nums.length; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                if (nums[i] == nums[j]) {
                    return true;
                }
            }
        }
        return false;
    }
}
```

#### Optimized Approach — HashSet

**Intuition:** A `HashSet` only stores unique elements. If `add()` returns `false`, the element already exists.

| Complexity | Value |
|-----------|-------|
| Time | O(n) |
| Space | O(n) |

```java
class Solution {
    public boolean containsDuplicate(int[] nums) {
        Set<Integer> seen = new HashSet<>();
        for (int num : nums) {
            // add() returns false if element already exists
            if (!seen.add(num)) {
                return true;
            }
        }
        return false;
    }
}
```

---

### 1.3 Group Anagrams

**Problem:** Given an array of strings `strs`, group the anagrams together. An anagram is a word formed by rearranging the letters of another word.

#### Brute Force

**Explanation:** Sort each string and use it as a key to group.

| Complexity | Value |
|-----------|-------|
| Time | O(n · k log k) where k = max string length |
| Space | O(n · k) |

```java
class Solution {
    public List<List<String>> groupAnagrams(String[] strs) {
        Map<String, List<String>> map = new HashMap<>();
        for (String s : strs) {
            // Sort characters to create a canonical key
            char[] chars = s.toCharArray();
            Arrays.sort(chars);
            String key = new String(chars);
            map.computeIfAbsent(key, k -> new ArrayList<>()).add(s);
        }
        return new ArrayList<>(map.values());
    }
}
```

#### Optimized Approach — Character Count Key

**Intuition:** Instead of sorting, use a character frequency array as the key. This reduces the per-string cost from O(k log k) to O(k).

| Complexity | Value |
|-----------|-------|
| Time | O(n · k) |
| Space | O(n · k) |

```java
class Solution {
    public List<List<String>> groupAnagrams(String[] strs) {
        Map<String, List<String>> map = new HashMap<>();
        for (String s : strs) {
            // Count frequency of each character
            int[] count = new int[26];
            for (char c : s.toCharArray()) {
                count[c - 'a']++;
            }
            // Build key from counts: e.g., "1#0#0#...#0"
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < 26; i++) {
                sb.append(count[i]).append('#');
            }
            String key = sb.toString();
            map.computeIfAbsent(key, k -> new ArrayList<>()).add(s);
        }
        return new ArrayList<>(map.values());
    }
}
```

**Common Pitfalls:**
- Empty strings are valid anagrams of each other.
- Ensure consistent key generation (e.g., always use `#` delimiter to avoid collisions like `[1,2,3]` vs `[12,3]`).

---

### 1.4 Top K Frequent Elements

**Problem:** Given an integer array `nums` and an integer `k`, return the `k` most frequent elements.

#### Brute Force

**Explanation:** Count frequencies, sort by frequency, pick top k.

| Complexity | Value |
|-----------|-------|
| Time | O(n log n) |
| Space | O(n) |

```java
class Solution {
    public int[] topKFrequent(int[] nums, int k) {
        // Count frequencies
        Map<Integer, Integer> freq = new HashMap<>();
        for (int num : nums) {
            freq.put(num, freq.getOrDefault(num, 0) + 1);
        }
        // Sort entries by frequency (descending)
        List<Map.Entry<Integer, Integer>> entries = new ArrayList<>(freq.entrySet());
        entries.sort((a, b) -> b.getValue() - a.getValue());
        // Pick top k
        int[] result = new int[k];
        for (int i = 0; i < k; i++) {
            result[i] = entries.get(i).getKey();
        }
        return result;
    }
}
```

#### Optimized Approach — Bucket Sort

**Intuition:** Frequencies range from 1 to n. Create buckets indexed by frequency. This gives O(n) time.

| Complexity | Value |
|-----------|-------|
| Time | O(n) |
| Space | O(n) |

```java
class Solution {
    public int[] topKFrequent(int[] nums, int k) {
        Map<Integer, Integer> freq = new HashMap<>();
        for (int num : nums) {
            freq.put(num, freq.getOrDefault(num, 0) + 1);
        }
        // Bucket sort: index = frequency, value = list of numbers with that frequency
        @SuppressWarnings("unchecked")
        List<Integer>[] buckets = new List[nums.length + 1];
        for (int i = 0; i <= nums.length; i++) {
            buckets[i] = new ArrayList<>();
        }
        for (Map.Entry<Integer, Integer> entry : freq.entrySet()) {
            buckets[entry.getValue()].add(entry.getKey());
        }
        // Collect top k from highest frequency bucket downward
        int[] result = new int[k];
        int idx = 0;
        for (int i = buckets.length - 1; i >= 0 && idx < k; i--) {
            for (int num : buckets[i]) {
                result[idx++] = num;
                if (idx == k) break;
            }
        }
        return result;
    }
}
```

**Edge Cases:**
- All elements are the same (k = 1).
- All elements are unique (k = n).

---

### 1.5 Product of Array Except Self

**Problem:** Given an integer array `nums`, return an array `answer` such that `answer[i]` equals the product of all elements of `nums` except `nums[i]`. You must solve it in O(n) time and **without using division**.

#### Brute Force

| Complexity | Value |
|-----------|-------|
| Time | O(n²) |
| Space | O(1) extra |

```java
class Solution {
    public int[] productExceptSelf(int[] nums) {
        int n = nums.length;
        int[] answer = new int[n];
        for (int i = 0; i < n; i++) {
            int product = 1;
            for (int j = 0; j < n; j++) {
                if (i != j) {
                    product *= nums[j];
                }
            }
            answer[i] = product;
        }
        return answer;
    }
}
```

#### Optimized Approach — Prefix & Suffix Products

**Intuition:** `answer[i] = (product of all elements to the left of i) × (product of all elements to the right of i)`. Compute prefix products left-to-right, then suffix products right-to-left.

| Complexity | Value |
|-----------|-------|
| Time | O(n) |
| Space | O(1) extra (output array not counted) |

```java
class Solution {
    public int[] productExceptSelf(int[] nums) {
        int n = nums.length;
        int[] answer = new int[n];
        // Pass 1: prefix products (left to right)
        answer[0] = 1;
        for (int i = 1; i < n; i++) {
            answer[i] = answer[i - 1] * nums[i - 1];
        }
        // Pass 2: multiply by suffix products (right to left)
        int suffix = 1;
        for (int i = n - 1; i >= 0; i--) {
            answer[i] *= suffix;
            suffix *= nums[i];
        }
        return answer;
    }
}
```

**Pitfalls:**
- Zeros in the array — approach handles them naturally since no division is used.

---

# 2. Two Pointers

## Pattern Overview

Two pointers is a technique where two indices traverse the data structure (usually an array or string) from different positions or at different speeds. It reduces nested-loop brute forces from O(n²) to O(n).

**When to use:**
- Sorted arrays: find pairs, triplets with a target sum
- Partitioning: move elements satisfying a condition
- Palindrome checking
- Merging two sorted arrays

**How to recognize:**
- "Find pair/triplet that sums to …"
- "Is the string a palindrome?"
- "Remove duplicates from sorted array"
- Input is **sorted** or answer involves **comparing from both ends**

**Companies:** Google, Apple, Meta, Uber, Amazon, Microsoft

---

### 2.1 Valid Palindrome

**Problem:** Given a string `s`, return `true` if it is a palindrome considering only alphanumeric characters and ignoring cases.

#### Brute Force

**Explanation:** Filter out non-alphanumeric chars, convert to lowercase, reverse, and compare.

| Complexity | Value |
|-----------|-------|
| Time | O(n) |
| Space | O(n) |

```java
class Solution {
    public boolean isPalindrome(String s) {
        StringBuilder filtered = new StringBuilder();
        for (char c : s.toCharArray()) {
            if (Character.isLetterOrDigit(c)) {
                filtered.append(Character.toLowerCase(c));
            }
        }
        String clean = filtered.toString();
        String reversed = filtered.reverse().toString();
        return clean.equals(reversed);
    }
}
```

#### Optimized Approach — Two Pointers (In-Place)

**Intuition:** Use two pointers from both ends, skip non-alphanumeric characters, and compare.

| Complexity | Value |
|-----------|-------|
| Time | O(n) |
| Space | O(1) |

```java
class Solution {
    public boolean isPalindrome(String s) {
        int left = 0, right = s.length() - 1;
        while (left < right) {
            // Skip non-alphanumeric from left
            while (left < right && !Character.isLetterOrDigit(s.charAt(left))) {
                left++;
            }
            // Skip non-alphanumeric from right
            while (left < right && !Character.isLetterOrDigit(s.charAt(right))) {
                right--;
            }
            // Compare characters (case-insensitive)
            if (Character.toLowerCase(s.charAt(left)) != Character.toLowerCase(s.charAt(right))) {
                return false;
            }
            left++;
            right--;
        }
        return true;
    }
}
```

---

### 2.2 Three Sum (3Sum)

**Problem:** Given an integer array `nums`, return all triplets `[nums[i], nums[j], nums[k]]` such that `i ≠ j ≠ k` and `nums[i] + nums[j] + nums[k] == 0`. The solution set must not contain duplicate triplets.

#### Brute Force

| Complexity | Value |
|-----------|-------|
| Time | O(n³) |
| Space | O(n) for deduplication |

```java
class Solution {
    public List<List<Integer>> threeSum(int[] nums) {
        Set<List<Integer>> resultSet = new HashSet<>();
        Arrays.sort(nums);
        for (int i = 0; i < nums.length; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                for (int k = j + 1; k < nums.length; k++) {
                    if (nums[i] + nums[j] + nums[k] == 0) {
                        resultSet.add(Arrays.asList(nums[i], nums[j], nums[k]));
                    }
                }
            }
        }
        return new ArrayList<>(resultSet);
    }
}
```

#### Optimized Approach — Sort + Two Pointers

**Intuition:** Sort the array. Fix one element, then use two pointers on the remainder to find pairs summing to its negation. Skip duplicates to avoid repeats.

| Complexity | Value |
|-----------|-------|
| Time | O(n²) |
| Space | O(1) extra (ignoring output) |

```java
class Solution {
    public List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        Arrays.sort(nums);
        for (int i = 0; i < nums.length - 2; i++) {
            // Skip duplicate for first element
            if (i > 0 && nums[i] == nums[i - 1]) continue;
            // Early termination: if smallest is > 0, no valid triplet
            if (nums[i] > 0) break;
            int left = i + 1, right = nums.length - 1;
            while (left < right) {
                int sum = nums[i] + nums[left] + nums[right];
                if (sum == 0) {
                    result.add(Arrays.asList(nums[i], nums[left], nums[right]));
                    // Skip duplicates for second element
                    while (left < right && nums[left] == nums[left + 1]) left++;
                    // Skip duplicates for third element
                    while (left < right && nums[right] == nums[right - 1]) right--;
                    left++;
                    right--;
                } else if (sum < 0) {
                    left++;
                } else {
                    right--;
                }
            }
        }
        return result;
    }
}
```

**Common Pitfalls:**
- Forgetting to skip duplicates → produces duplicate triplets.
- Not sorting first → two-pointer logic breaks.

---

### 2.3 Container With Most Water

**Problem:** Given `n` non-negative integers `height[0..n-1]` where each represents a point `(i, height[i])`, find two lines that together with the x-axis form a container that holds the most water.

#### Brute Force

| Complexity | Value |
|-----------|-------|
| Time | O(n²) |
| Space | O(1) |

```java
class Solution {
    public int maxArea(int[] height) {
        int maxWater = 0;
        for (int i = 0; i < height.length; i++) {
            for (int j = i + 1; j < height.length; j++) {
                int water = Math.min(height[i], height[j]) * (j - i);
                maxWater = Math.max(maxWater, water);
            }
        }
        return maxWater;
    }
}
```

#### Optimized Approach — Two Pointers

**Intuition:** Start with the widest container (left = 0, right = n-1). The only way to potentially find a larger area is to move the pointer pointing to the shorter line inward — a taller line might compensate for the reduced width.

| Complexity | Value |
|-----------|-------|
| Time | O(n) |
| Space | O(1) |

```java
class Solution {
    public int maxArea(int[] height) {
        int left = 0, right = height.length - 1;
        int maxWater = 0;
        while (left < right) {
            // Calculate area with current pointers
            int water = Math.min(height[left], height[right]) * (right - left);
            maxWater = Math.max(maxWater, water);
            // Move the shorter side inward
            if (height[left] < height[right]) {
                left++;
            } else {
                right--;
            }
        }
        return maxWater;
    }
}
```

**Edge Cases:**
- All heights are equal → area = height × (n - 1).
- Heights in strictly ascending or descending order.

---

### 2.4 Trapping Rain Water

**Problem:** Given `n` non-negative integers representing an elevation map where the width of each bar is 1, compute how much water it can trap after raining.

#### Brute Force

**Explanation:** For each position, water trapped = `min(maxLeft, maxRight) - height[i]`.

| Complexity | Value |
|-----------|-------|
| Time | O(n²) |
| Space | O(1) |

```java
class Solution {
    public int trap(int[] height) {
        int total = 0;
        for (int i = 0; i < height.length; i++) {
            int leftMax = 0, rightMax = 0;
            // Find max height to the left
            for (int j = 0; j <= i; j++) {
                leftMax = Math.max(leftMax, height[j]);
            }
            // Find max height to the right
            for (int j = i; j < height.length; j++) {
                rightMax = Math.max(rightMax, height[j]);
            }
            total += Math.min(leftMax, rightMax) - height[i];
        }
        return total;
    }
}
```

#### Optimized Approach — Two Pointers

**Intuition:** Maintain `leftMax` and `rightMax` while moving pointers inward. If `leftMax < rightMax`, we know water at `left` is bounded by `leftMax`. Vice versa for right.

| Complexity | Value |
|-----------|-------|
| Time | O(n) |
| Space | O(1) |

```java
class Solution {
    public int trap(int[] height) {
        int left = 0, right = height.length - 1;
        int leftMax = 0, rightMax = 0;
        int total = 0;
        while (left < right) {
            if (height[left] < height[right]) {
                // Water at left depends on leftMax
                leftMax = Math.max(leftMax, height[left]);
                total += leftMax - height[left];
                left++;
            } else {
                // Water at right depends on rightMax
                rightMax = Math.max(rightMax, height[right]);
                total += rightMax - height[right];
                right--;
            }
        }
        return total;
    }
}
```

---

# 3. Sliding Window

## Pattern Overview

Sliding window maintains a **window** (subarray/substring) that expands or contracts to solve problems involving contiguous sequences. It converts O(n²) or O(n³) brute forces into O(n).

**Two variants:**
- **Fixed-size window:** Window size is given (e.g., max sum of subarray of size k).
- **Variable-size window:** Window grows/shrinks based on a condition (e.g., smallest subarray with sum ≥ target).

**How to recognize:**
- "Find longest/shortest subarray/substring with property X"
- "Maximum sum subarray of size k"
- "Substring with at most k distinct characters"

**Companies:** Google, Amazon, Microsoft, Netflix, Uber, Meta

---

### 3.1 Best Time to Buy and Sell Stock

**Problem:** Given an array `prices` where `prices[i]` is the price on day `i`, find the maximum profit from one buy-sell transaction.

#### Brute Force

| Complexity | Value |
|-----------|-------|
| Time | O(n²) |
| Space | O(1) |

```java
class Solution {
    public int maxProfit(int[] prices) {
        int maxProfit = 0;
        for (int i = 0; i < prices.length; i++) {
            for (int j = i + 1; j < prices.length; j++) {
                maxProfit = Math.max(maxProfit, prices[j] - prices[i]);
            }
        }
        return maxProfit;
    }
}
```

#### Optimized Approach — Sliding Window / Single Pass

**Intuition:** Track the minimum price seen so far. At each day, the max profit is `currentPrice - minPrice`.

| Complexity | Value |
|-----------|-------|
| Time | O(n) |
| Space | O(1) |

```java
class Solution {
    public int maxProfit(int[] prices) {
        int minPrice = Integer.MAX_VALUE;
        int maxProfit = 0;
        for (int price : prices) {
            // Update minimum price seen so far (buy point)
            minPrice = Math.min(minPrice, price);
            // Calculate profit if we sell today
            maxProfit = Math.max(maxProfit, price - minPrice);
        }
        return maxProfit;
    }
}
```

---

### 3.2 Longest Substring Without Repeating Characters

**Problem:** Given a string `s`, find the length of the longest substring without repeating characters.

#### Brute Force

**Explanation:** Check every possible substring; use a set to verify uniqueness.

| Complexity | Value |
|-----------|-------|
| Time | O(n³) |
| Space | O(min(n, m)) where m = character set size |

```java
class Solution {
    public int lengthOfLongestSubstring(String s) {
        int maxLen = 0;
        for (int i = 0; i < s.length(); i++) {
            for (int j = i; j < s.length(); j++) {
                if (allUnique(s, i, j)) {
                    maxLen = Math.max(maxLen, j - i + 1);
                }
            }
        }
        return maxLen;
    }

    private boolean allUnique(String s, int start, int end) {
        Set<Character> set = new HashSet<>();
        for (int i = start; i <= end; i++) {
            if (!set.add(s.charAt(i))) return false;
        }
        return true;
    }
}
```

#### Optimized Approach — Sliding Window with HashMap

**Intuition:** Expand the window by moving `right`. When a duplicate is found, shrink from `left` until the window is valid again. Store each character's latest index for efficient shrinking.

| Complexity | Value |
|-----------|-------|
| Time | O(n) |
| Space | O(min(n, m)) |

```java
class Solution {
    public int lengthOfLongestSubstring(String s) {
        Map<Character, Integer> lastSeen = new HashMap<>();
        int maxLen = 0;
        int left = 0;
        for (int right = 0; right < s.length(); right++) {
            char c = s.charAt(right);
            // If char was seen and is within current window, move left past it
            if (lastSeen.containsKey(c) && lastSeen.get(c) >= left) {
                left = lastSeen.get(c) + 1;
            }
            lastSeen.put(c, right);
            maxLen = Math.max(maxLen, right - left + 1);
        }
        return maxLen;
    }
}
```

---

### 3.3 Minimum Window Substring

**Problem:** Given strings `s` and `t`, return the minimum window substring of `s` that contains all characters of `t` (including duplicates). If no such window exists, return `""`.

#### Brute Force

| Complexity | Value |
|-----------|-------|
| Time | O(n² · m) |
| Space | O(m) |

```java
class Solution {
    public String minWindow(String s, String t) {
        int minLen = Integer.MAX_VALUE;
        String result = "";
        for (int i = 0; i < s.length(); i++) {
            for (int j = i; j < s.length(); j++) {
                String sub = s.substring(i, j + 1);
                if (containsAll(sub, t) && sub.length() < minLen) {
                    minLen = sub.length();
                    result = sub;
                }
            }
        }
        return result;
    }

    private boolean containsAll(String window, String t) {
        int[] counts = new int[128];
        for (char c : t.toCharArray()) counts[c]++;
        for (char c : window.toCharArray()) counts[c]--;
        for (int count : counts) {
            if (count > 0) return false;
        }
        return true;
    }
}
```

#### Optimized Approach — Sliding Window with Frequency Map

**Intuition:** Expand `right` to include characters. Track how many required characters are satisfied. Once all are satisfied, shrink `left` to find the minimum valid window.

| Complexity | Value |
|-----------|-------|
| Time | O(n + m) |
| Space | O(m) |

```java
class Solution {
    public String minWindow(String s, String t) {
        if (s.length() < t.length()) return "";
        // Frequency map for characters in t
        Map<Character, Integer> need = new HashMap<>();
        for (char c : t.toCharArray()) {
            need.put(c, need.getOrDefault(c, 0) + 1);
        }
        int required = need.size(); // Number of unique chars needed
        int formed = 0;             // Number of unique chars with desired frequency
        Map<Character, Integer> windowCounts = new HashMap<>();
        int left = 0;
        int minLen = Integer.MAX_VALUE;
        int minLeft = 0;

        for (int right = 0; right < s.length(); right++) {
            char c = s.charAt(right);
            windowCounts.put(c, windowCounts.getOrDefault(c, 0) + 1);
            // Check if current char's frequency matches requirement
            if (need.containsKey(c) && windowCounts.get(c).intValue() == need.get(c).intValue()) {
                formed++;
            }
            // Try to shrink window from left
            while (formed == required) {
                if (right - left + 1 < minLen) {
                    minLen = right - left + 1;
                    minLeft = left;
                }
                char leftChar = s.charAt(left);
                windowCounts.put(leftChar, windowCounts.get(leftChar) - 1);
                if (need.containsKey(leftChar) && windowCounts.get(leftChar) < need.get(leftChar)) {
                    formed--;
                }
                left++;
            }
        }
        return minLen == Integer.MAX_VALUE ? "" : s.substring(minLeft, minLeft + minLen);
    }
}
```

**Pitfalls:**
- Use `.intValue()` when comparing `Integer` objects in Java to avoid reference comparison.
- Empty `t` → return `""`.

---

# 4. Strings

## Pattern Overview

String problems test manipulation, pattern matching, and often overlap with arrays, hashing, and sliding window.

**Key techniques:**
- Two pointers (palindromes)
- HashMap/frequency counting (anagrams)
- StringBuilder for efficient concatenation
- Character arithmetic (`c - 'a'`)

**Companies:** Amazon, Google, Microsoft, Apple, Meta

---

### 4.1 Valid Anagram

**Problem:** Given two strings `s` and `t`, return `true` if `t` is an anagram of `s`.

#### Brute Force — Sorting

| Complexity | Value |
|-----------|-------|
| Time | O(n log n) |
| Space | O(n) |

```java
class Solution {
    public boolean isAnagram(String s, String t) {
        if (s.length() != t.length()) return false;
        char[] sArr = s.toCharArray();
        char[] tArr = t.toCharArray();
        Arrays.sort(sArr);
        Arrays.sort(tArr);
        return Arrays.equals(sArr, tArr);
    }
}
```

#### Optimized Approach — Frequency Array

| Complexity | Value |
|-----------|-------|
| Time | O(n) |
| Space | O(1) — fixed 26-size array |

```java
class Solution {
    public boolean isAnagram(String s, String t) {
        if (s.length() != t.length()) return false;
        int[] count = new int[26];
        for (int i = 0; i < s.length(); i++) {
            count[s.charAt(i) - 'a']++;
            count[t.charAt(i) - 'a']--;
        }
        for (int c : count) {
            if (c != 0) return false;
        }
        return true;
    }
}
```

---

### 4.2 Longest Palindromic Substring

**Problem:** Given a string `s`, return the longest palindromic substring.

#### Brute Force

| Complexity | Value |
|-----------|-------|
| Time | O(n³) |
| Space | O(1) |

```java
class Solution {
    public String longestPalindrome(String s) {
        String result = "";
        for (int i = 0; i < s.length(); i++) {
            for (int j = i; j < s.length(); j++) {
                if (isPalindrome(s, i, j) && (j - i + 1) > result.length()) {
                    result = s.substring(i, j + 1);
                }
            }
        }
        return result;
    }

    private boolean isPalindrome(String s, int l, int r) {
        while (l < r) {
            if (s.charAt(l++) != s.charAt(r--)) return false;
        }
        return true;
    }
}
```

#### Optimized Approach — Expand Around Center

**Intuition:** Every palindrome has a center. For each possible center (2n - 1 centers: n single chars + n-1 gaps), expand outward while characters match.

| Complexity | Value |
|-----------|-------|
| Time | O(n²) |
| Space | O(1) |

```java
class Solution {
    private int start = 0, maxLen = 0;

    public String longestPalindrome(String s) {
        if (s.length() < 2) return s;
        for (int i = 0; i < s.length(); i++) {
            // Odd-length palindromes
            expand(s, i, i);
            // Even-length palindromes
            expand(s, i, i + 1);
        }
        return s.substring(start, start + maxLen);
    }

    private void expand(String s, int left, int right) {
        while (left >= 0 && right < s.length() && s.charAt(left) == s.charAt(right)) {
            if (right - left + 1 > maxLen) {
                start = left;
                maxLen = right - left + 1;
            }
            left--;
            right++;
        }
    }
}
```

---

### 4.3 Longest Common Prefix

**Problem:** Find the longest common prefix string amongst an array of strings.

#### Optimized Approach — Vertical Scanning

| Complexity | Value |
|-----------|-------|
| Time | O(S) where S = sum of all characters |
| Space | O(1) |

```java
class Solution {
    public String longestCommonPrefix(String[] strs) {
        if (strs == null || strs.length == 0) return "";
        // Compare characters column by column
        for (int i = 0; i < strs[0].length(); i++) {
            char c = strs[0].charAt(i);
            for (int j = 1; j < strs.length; j++) {
                // If we've exhausted a string or found a mismatch
                if (i >= strs[j].length() || strs[j].charAt(i) != c) {
                    return strs[0].substring(0, i);
                }
            }
        }
        return strs[0];
    }
}
```

---

# 5. Binary Search

## Pattern Overview

Binary search eliminates half the search space in each step, achieving O(log n) time on **sorted** or **monotonic** data.

**Variants:**
- **Standard:** Find exact target.
- **Lower bound:** Find first element ≥ target.
- **Upper bound:** Find first element > target.
- **Search on answer:** Binary search on the answer space (e.g., "minimum capacity to ship packages").

**How to recognize:**
- Sorted array + search → binary search
- "Find minimum/maximum that satisfies …"
- "Kth smallest/largest"
- Monotonic predicate function

**Companies:** Google, Meta, Amazon, Uber, Apple, Microsoft

---

### 5.1 Binary Search (Classic)

**Problem:** Given a sorted array of integers `nums` and a target, return its index. If not found, return `-1`.

| Complexity | Value |
|-----------|-------|
| Time | O(log n) |
| Space | O(1) |

```java
class Solution {
    public int search(int[] nums, int target) {
        int left = 0, right = nums.length - 1;
        while (left <= right) {
            int mid = left + (right - left) / 2; // Avoids integer overflow
            if (nums[mid] == target) {
                return mid;
            } else if (nums[mid] < target) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return -1;
    }
}
```

**Pitfalls:**
- Integer overflow: Use `left + (right - left) / 2` instead of `(left + right) / 2`.
- Off-by-one errors: Be precise about `left <= right` vs `left < right`.

---

### 5.2 Search in Rotated Sorted Array

**Problem:** Search for a `target` in a sorted array that has been rotated at some pivot. Return its index or `-1`.

#### Brute Force — Linear Scan

| Complexity | Value |
|-----------|-------|
| Time | O(n) |
| Space | O(1) |

```java
class Solution {
    public int search(int[] nums, int target) {
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == target) return i;
        }
        return -1;
    }
}
```

#### Optimized Approach — Modified Binary Search

**Intuition:** At least one half of the array around `mid` is always sorted. Determine which half is sorted and check if `target` lies within it.

| Complexity | Value |
|-----------|-------|
| Time | O(log n) |
| Space | O(1) |

```java
class Solution {
    public int search(int[] nums, int target) {
        int left = 0, right = nums.length - 1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] == target) return mid;

            // Left half is sorted
            if (nums[left] <= nums[mid]) {
                if (target >= nums[left] && target < nums[mid]) {
                    right = mid - 1; // Target is in sorted left half
                } else {
                    left = mid + 1;
                }
            }
            // Right half is sorted
            else {
                if (target > nums[mid] && target <= nums[right]) {
                    left = mid + 1; // Target is in sorted right half
                } else {
                    right = mid - 1;
                }
            }
        }
        return -1;
    }
}
```

---

### 5.3 Find Minimum in Rotated Sorted Array

**Problem:** Find the minimum element in a rotated sorted array (no duplicates).

| Complexity | Value |
|-----------|-------|
| Time | O(log n) |
| Space | O(1) |

```java
class Solution {
    public int findMin(int[] nums) {
        int left = 0, right = nums.length - 1;
        while (left < right) {
            int mid = left + (right - left) / 2;
            // If mid element is greater than right, minimum is in right half
            if (nums[mid] > nums[right]) {
                left = mid + 1;
            } else {
                right = mid; // mid could be the minimum
            }
        }
        return nums[left];
    }
}
```

---

### 5.4 Koko Eating Bananas (Search on Answer)

**Problem:** Koko has `n` piles of bananas. She can eat at speed `k` bananas/hour, one pile at a time. If a pile has fewer than `k`, she finishes it in 1 hour. She has `h` hours. Find the minimum integer `k` to eat all bananas within `h` hours.

#### Brute Force

| Complexity | Value |
|-----------|-------|
| Time | O(max(piles) × n) |
| Space | O(1) |

```java
class Solution {
    public int minEatingSpeed(int[] piles, int h) {
        int maxPile = 0;
        for (int p : piles) maxPile = Math.max(maxPile, p);
        for (int k = 1; k <= maxPile; k++) {
            if (canFinish(piles, k, h)) return k;
        }
        return maxPile;
    }

    private boolean canFinish(int[] piles, int k, int h) {
        int hours = 0;
        for (int p : piles) {
            hours += (p + k - 1) / k; // Ceiling division
        }
        return hours <= h;
    }
}
```

#### Optimized Approach — Binary Search on Answer

**Intuition:** The answer `k` lies in `[1, max(piles)]`. For a given `k`, we can check in O(n) if Koko finishes in time. This check is **monotonic**: if `k` works, any larger `k` also works. Binary search on `k`.

| Complexity | Value |
|-----------|-------|
| Time | O(n log(max(piles))) |
| Space | O(1) |

```java
class Solution {
    public int minEatingSpeed(int[] piles, int h) {
        int left = 1, right = 0;
        for (int p : piles) right = Math.max(right, p);

        while (left < right) {
            int mid = left + (right - left) / 2;
            if (canFinish(piles, mid, h)) {
                right = mid; // mid might be the answer, try smaller
            } else {
                left = mid + 1;
            }
        }
        return left;
    }

    private boolean canFinish(int[] piles, int k, int h) {
        int hours = 0;
        for (int p : piles) {
            hours += (p + k - 1) / k; // Ceiling division without floating point
        }
        return hours <= h;
    }
}
```

---

# 6. Stack & Queue

## Pattern Overview

**Stack (LIFO):** Used for problems involving nested structures, matching, and maintaining a decreasing/increasing order.

**Queue (FIFO):** Used for BFS, level-order processing, and sliding window max/min (monotonic deque).

**How to recognize:**
- "Next greater element" → Monotonic Stack
- "Valid parentheses" → Stack
- "Evaluate expression" → Stack
- "Sliding window maximum" → Monotonic Deque

**Companies:** Amazon, Microsoft, Google, Bloomberg, Meta

---

### 6.1 Valid Parentheses

**Problem:** Given a string `s` containing `()[]{}`, determine if the input string is valid.

| Complexity | Value |
|-----------|-------|
| Time | O(n) |
| Space | O(n) |

```java
class Solution {
    public boolean isValid(String s) {
        Deque<Character> stack = new ArrayDeque<>();
        Map<Character, Character> map = Map.of(')', '(', ']', '[', '}', '{');
        for (char c : s.toCharArray()) {
            if (map.containsKey(c)) {
                // Closing bracket: check if stack top matches
                if (stack.isEmpty() || !stack.pop().equals(map.get(c))) {
                    return false;
                }
            } else {
                // Opening bracket: push to stack
                stack.push(c);
            }
        }
        return stack.isEmpty();
    }
}
```

---

### 6.2 Daily Temperatures (Monotonic Stack)

**Problem:** Given daily temperatures, return an array where `answer[i]` is the number of days you have to wait after day `i` to get a warmer temperature. If no future day is warmer, `answer[i] = 0`.

#### Brute Force

| Complexity | Value |
|-----------|-------|
| Time | O(n²) |
| Space | O(1) |

```java
class Solution {
    public int[] dailyTemperatures(int[] temperatures) {
        int n = temperatures.length;
        int[] answer = new int[n];
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                if (temperatures[j] > temperatures[i]) {
                    answer[i] = j - i;
                    break;
                }
            }
        }
        return answer;
    }
}
```

#### Optimized Approach — Monotonic Stack

**Intuition:** Maintain a stack of indices with decreasing temperatures. When a warmer day is found, pop indices and compute the difference.

| Complexity | Value |
|-----------|-------|
| Time | O(n) |
| Space | O(n) |

```java
class Solution {
    public int[] dailyTemperatures(int[] temperatures) {
        int n = temperatures.length;
        int[] answer = new int[n];
        Deque<Integer> stack = new ArrayDeque<>(); // Stack of indices
        for (int i = 0; i < n; i++) {
            // Pop all indices whose temperature is less than current
            while (!stack.isEmpty() && temperatures[i] > temperatures[stack.peek()]) {
                int prevIdx = stack.pop();
                answer[prevIdx] = i - prevIdx;
            }
            stack.push(i);
        }
        return answer;
    }
}
```

---

### 6.3 Min Stack

**Problem:** Design a stack that supports `push`, `pop`, `top`, and `getMin` — all in O(1) time.

```java
class MinStack {
    private Deque<Integer> stack;
    private Deque<Integer> minStack; // Tracks minimums

    public MinStack() {
        stack = new ArrayDeque<>();
        minStack = new ArrayDeque<>();
    }

    public void push(int val) {
        stack.push(val);
        // Push to minStack if it's empty or val <= current min
        if (minStack.isEmpty() || val <= minStack.peek()) {
            minStack.push(val);
        }
    }

    public void pop() {
        int val = stack.pop();
        // If popped element is the current min, pop from minStack too
        if (val == minStack.peek()) {
            minStack.pop();
        }
    }

    public int top() {
        return stack.peek();
    }

    public int getMin() {
        return minStack.peek();
    }
}
```

| Operation | Time | Space |
|-----------|------|-------|
| push | O(1) | O(n) |
| pop | O(1) | — |
| top | O(1) | — |
| getMin | O(1) | — |

---

# 7. Linked List

## Pattern Overview

Linked list problems test pointer manipulation and in-place operations. The key techniques are **fast-slow pointers**, **dummy nodes**, and **reversal**.

**Key techniques:**
- **Fast & Slow Pointers:** Detect cycles, find middle, find nth-from-end.
- **Dummy Head:** Simplifies edge cases (e.g., deleting head node).
- **Reversal:** In-place reversal of a linked list or a portion of it.

**How to recognize:**
- "Detect cycle" → Fast/Slow pointers
- "Merge two sorted lists" → Dummy head + iteration
- "Reverse a linked list" → Pointer manipulation
- "Find the middle" → Fast/Slow pointers

**Companies:** Microsoft, Amazon, Meta, Apple, Google

---

### 7.1 Reverse Linked List

**Problem:** Given the head of a singly linked list, reverse the list and return the reversed list.

#### Iterative Approach

| Complexity | Value |
|-----------|-------|
| Time | O(n) |
| Space | O(1) |

```java
class Solution {
    public ListNode reverseList(ListNode head) {
        ListNode prev = null;
        ListNode curr = head;
        while (curr != null) {
            ListNode next = curr.next; // Save next node
            curr.next = prev;          // Reverse pointer
            prev = curr;               // Advance prev
            curr = next;               // Advance curr
        }
        return prev; // prev is the new head
    }
}
```

#### Recursive Approach

| Complexity | Value |
|-----------|-------|
| Time | O(n) |
| Space | O(n) — call stack |

```java
class Solution {
    public ListNode reverseList(ListNode head) {
        // Base case: empty list or single node
        if (head == null || head.next == null) return head;
        // Recursively reverse the rest
        ListNode newHead = reverseList(head.next);
        // Make the next node point back to current
        head.next.next = head;
        head.next = null;
        return newHead;
    }
}
```

---

### 7.2 Linked List Cycle

**Problem:** Given head of a linked list, determine if the linked list has a cycle.

#### Brute Force — HashSet

| Complexity | Value |
|-----------|-------|
| Time | O(n) |
| Space | O(n) |

```java
class Solution {
    public boolean hasCycle(ListNode head) {
        Set<ListNode> visited = new HashSet<>();
        ListNode curr = head;
        while (curr != null) {
            if (!visited.add(curr)) return true; // Already seen
            curr = curr.next;
        }
        return false;
    }
}
```

#### Optimized Approach — Floyd's Cycle Detection (Tortoise & Hare)

**Intuition:** Slow pointer moves 1 step, fast pointer moves 2 steps. If a cycle exists, they will eventually meet.

| Complexity | Value |
|-----------|-------|
| Time | O(n) |
| Space | O(1) |

```java
class Solution {
    public boolean hasCycle(ListNode head) {
        ListNode slow = head, fast = head;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
            if (slow == fast) return true; // Cycle detected
        }
        return false;
    }
}
```

---

### 7.3 Merge Two Sorted Lists

**Problem:** Merge two sorted linked lists and return it as a sorted list.

| Complexity | Value |
|-----------|-------|
| Time | O(n + m) |
| Space | O(1) |

```java
class Solution {
    public ListNode mergeTwoLists(ListNode list1, ListNode list2) {
        ListNode dummy = new ListNode(0); // Dummy head simplifies logic
        ListNode curr = dummy;
        while (list1 != null && list2 != null) {
            if (list1.val <= list2.val) {
                curr.next = list1;
                list1 = list1.next;
            } else {
                curr.next = list2;
                list2 = list2.next;
            }
            curr = curr.next;
        }
        // Attach remaining nodes
        curr.next = (list1 != null) ? list1 : list2;
        return dummy.next;
    }
}
```

---

### 7.4 Remove Nth Node From End of List

**Problem:** Given the head of a linked list, remove the `n`th node from the end and return its head.

#### Optimized Approach — Two Pointers (One Pass)

**Intuition:** Advance `fast` pointer by `n` steps. Then move both `fast` and `slow` together. When `fast` reaches the end, `slow` is just before the node to remove.

| Complexity | Value |
|-----------|-------|
| Time | O(n) |
| Space | O(1) |

```java
class Solution {
    public ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode dummy = new ListNode(0, head);
        ListNode fast = dummy, slow = dummy;
        // Advance fast by n + 1 steps
        for (int i = 0; i <= n; i++) {
            fast = fast.next;
        }
        // Move both until fast reaches end
        while (fast != null) {
            fast = fast.next;
            slow = slow.next;
        }
        // Skip the target node
        slow.next = slow.next.next;
        return dummy.next;
    }
}
```

**Pitfalls:**
- Use a dummy node to handle deletion of the head.
- Off-by-one: advance fast by `n + 1` steps so `slow` lands *before* the target.

---

### 7.5 LRU Cache

**Problem:** Design a data structure that follows the constraints of a Least Recently Used (LRU) cache. Implement `get(key)` and `put(key, value)` in O(1).

**Approach:** Use a `HashMap` for O(1) lookups and a **doubly linked list** for O(1) insertion/removal to track recency.

```java
class LRUCache {
    private class Node {
        int key, value;
        Node prev, next;
        Node(int k, int v) { key = k; value = v; }
    }

    private int capacity;
    private Map<Integer, Node> map;
    private Node head, tail; // Sentinel nodes

    public LRUCache(int capacity) {
        this.capacity = capacity;
        map = new HashMap<>();
        head = new Node(0, 0);
        tail = new Node(0, 0);
        head.next = tail;
        tail.prev = head;
    }

    public int get(int key) {
        if (!map.containsKey(key)) return -1;
        Node node = map.get(key);
        // Move to front (most recently used)
        remove(node);
        insertAtFront(node);
        return node.value;
    }

    public void put(int key, int value) {
        if (map.containsKey(key)) {
            remove(map.get(key));
        }
        Node node = new Node(key, value);
        insertAtFront(node);
        map.put(key, node);
        // Evict LRU if over capacity
        if (map.size() > capacity) {
            Node lru = tail.prev;
            remove(lru);
            map.remove(lru.key);
        }
    }

    private void remove(Node node) {
        node.prev.next = node.next;
        node.next.prev = node.prev;
    }

    private void insertAtFront(Node node) {
        node.next = head.next;
        node.prev = head;
        head.next.prev = node;
        head.next = node;
    }
}
```

| Operation | Time |
|-----------|------|
| get | O(1) |
| put | O(1) |

---

# 8. Recursion & Backtracking

## Pattern Overview

**Recursion** solves a problem by breaking it into smaller subproblems. **Backtracking** is a systematic way to explore all possible solutions by building candidates incrementally and abandoning ("backtracking") those that fail constraints.

**When to use:**
- Generate all permutations / combinations / subsets
- Solve constraint satisfaction problems (N-Queens, Sudoku)
- Tree/graph traversal (DFS is inherently recursive)

**Template for Backtracking:**
```
backtrack(candidates, current_state):
    if goal_reached:
        add current_state to results
        return
    for each choice in candidates:
        if choice is valid:
            make choice
            backtrack(remaining_candidates, updated_state)
            undo choice (backtrack)
```

**How to recognize:**
- "Generate all …" → Backtracking
- "Find all possible …" → Backtracking
- "Return all combinations/permutations" → Backtracking
- Problem has constraints that prune the search space

**Companies:** Google, Meta, Amazon, Apple, Microsoft

---

### 8.1 Subsets

**Problem:** Given an integer array `nums` of unique elements, return all possible subsets (the power set).

#### Approach — Backtracking

| Complexity | Value |
|-----------|-------|
| Time | O(n × 2ⁿ) |
| Space | O(n) recursion depth |

```java
class Solution {
    public List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        backtrack(nums, 0, new ArrayList<>(), result);
        return result;
    }

    private void backtrack(int[] nums, int start, List<Integer> current, List<List<Integer>> result) {
        // Every state is a valid subset
        result.add(new ArrayList<>(current));
        for (int i = start; i < nums.length; i++) {
            current.add(nums[i]);             // Choose
            backtrack(nums, i + 1, current, result); // Explore
            current.remove(current.size() - 1);      // Un-choose (backtrack)
        }
    }
}
```

#### Alternative — Iterative (Bit Manipulation)

```java
class Solution {
    public List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        int n = nums.length;
        // Each number from 0 to 2^n - 1 represents a subset
        for (int mask = 0; mask < (1 << n); mask++) {
            List<Integer> subset = new ArrayList<>();
            for (int i = 0; i < n; i++) {
                if ((mask & (1 << i)) != 0) {
                    subset.add(nums[i]);
                }
            }
            result.add(subset);
        }
        return result;
    }
}
```

---

### 8.2 Permutations

**Problem:** Given an array `nums` of distinct integers, return all possible permutations.

| Complexity | Value |
|-----------|-------|
| Time | O(n × n!) |
| Space | O(n) |

```java
class Solution {
    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        backtrack(nums, new ArrayList<>(), new boolean[nums.length], result);
        return result;
    }

    private void backtrack(int[] nums, List<Integer> current, boolean[] used, List<List<Integer>> result) {
        if (current.size() == nums.length) {
            result.add(new ArrayList<>(current));
            return;
        }
        for (int i = 0; i < nums.length; i++) {
            if (used[i]) continue; // Skip already used elements
            used[i] = true;
            current.add(nums[i]);
            backtrack(nums, current, used, result);
            current.remove(current.size() - 1); // Backtrack
            used[i] = false;
        }
    }
}
```

---

### 8.3 Combination Sum

**Problem:** Given an array of distinct integers `candidates` and a `target`, return all unique combinations where the chosen numbers sum to `target`. The same number may be used **unlimited** times.

| Complexity | Value |
|-----------|-------|
| Time | O(n^(T/M)) where T = target, M = min candidate |
| Space | O(T/M) recursion depth |

```java
class Solution {
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>> result = new ArrayList<>();
        Arrays.sort(candidates); // Sort for early termination
        backtrack(candidates, target, 0, new ArrayList<>(), result);
        return result;
    }

    private void backtrack(int[] candidates, int remaining, int start,
                           List<Integer> current, List<List<Integer>> result) {
        if (remaining == 0) {
            result.add(new ArrayList<>(current));
            return;
        }
        for (int i = start; i < candidates.length; i++) {
            if (candidates[i] > remaining) break; // Pruning: no point continuing
            current.add(candidates[i]);
            // Pass i (not i+1) because we can reuse same element
            backtrack(candidates, remaining - candidates[i], i, current, result);
            current.remove(current.size() - 1);
        }
    }
}
```

---

### 8.4 Word Search

**Problem:** Given an `m × n` grid of characters and a string `word`, return `true` if `word` exists in the grid. The word can be formed from sequentially adjacent cells (horizontal or vertical), and each cell may only be used once.

| Complexity | Value |
|-----------|-------|
| Time | O(m × n × 4^L) where L = word length |
| Space | O(L) recursion depth |

```java
class Solution {
    public boolean exist(char[][] board, String word) {
        int m = board.length, n = board[0].length;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (backtrack(board, word, i, j, 0)) {
                    return true;
                }
            }
        }
        return false;
    }

    private boolean backtrack(char[][] board, String word, int i, int j, int idx) {
        if (idx == word.length()) return true; // All characters matched
        if (i < 0 || i >= board.length || j < 0 || j >= board[0].length) return false;
        if (board[i][j] != word.charAt(idx)) return false;

        char temp = board[i][j];
        board[i][j] = '#'; // Mark as visited

        // Explore 4 directions
        boolean found = backtrack(board, word, i + 1, j, idx + 1)
                      || backtrack(board, word, i - 1, j, idx + 1)
                      || backtrack(board, word, i, j + 1, idx + 1)
                      || backtrack(board, word, i, j - 1, idx + 1);

        board[i][j] = temp; // Restore (backtrack)
        return found;
    }
}
```

**Pitfalls:**
- Forgetting to restore the cell after backtracking.
- Not handling all 4 directions.

---

### 8.5 N-Queens

**Problem:** Place `n` queens on an `n × n` chessboard so that no two queens attack each other. Return all distinct solutions.

| Complexity | Value |
|-----------|-------|
| Time | O(n!) |
| Space | O(n²) |

```java
class Solution {
    public List<List<String>> solveNQueens(int n) {
        List<List<String>> result = new ArrayList<>();
        char[][] board = new char[n][n];
        for (char[] row : board) Arrays.fill(row, '.');
        backtrack(board, 0, result);
        return result;
    }

    private void backtrack(char[][] board, int row, List<List<String>> result) {
        if (row == board.length) {
            // Convert board to list of strings
            List<String> solution = new ArrayList<>();
            for (char[] r : board) solution.add(new String(r));
            result.add(solution);
            return;
        }
        for (int col = 0; col < board.length; col++) {
            if (isValid(board, row, col)) {
                board[row][col] = 'Q';
                backtrack(board, row + 1, result);
                board[row][col] = '.'; // Backtrack
            }
        }
    }

    private boolean isValid(char[][] board, int row, int col) {
        // Check column above
        for (int i = 0; i < row; i++) {
            if (board[i][col] == 'Q') return false;
        }
        // Check upper-left diagonal
        for (int i = row - 1, j = col - 1; i >= 0 && j >= 0; i--, j--) {
            if (board[i][j] == 'Q') return false;
        }
        // Check upper-right diagonal
        for (int i = row - 1, j = col + 1; i >= 0 && j < board.length; i--, j++) {
            if (board[i][j] == 'Q') return false;
        }
        return true;
    }
}
```

---

# 9. Trees

## Pattern Overview

Trees are hierarchical data structures. Most tree problems use **DFS** (preorder, inorder, postorder) or **BFS** (level order).

**Key concepts:**
- **Binary Tree Traversals:** Preorder (root-left-right), Inorder (left-root-right), Postorder (left-right-root)
- **BST Property:** Left subtree < root < right subtree (inorder gives sorted sequence)
- **Height/Depth:** Max path from root to leaf
- **Balanced Tree:** Height difference between left and right subtree ≤ 1

**How to recognize:**
- "Given a binary tree …" → Usually DFS
- "Level by level" → BFS
- "Validate BST" → Inorder or range checking
- "Lowest Common Ancestor" → DFS with return values

**Companies:** Google, Microsoft, Amazon, Meta, Apple

---

### 9.1 Maximum Depth of Binary Tree

**Problem:** Given the root of a binary tree, return its maximum depth.

#### DFS Approach

| Complexity | Value |
|-----------|-------|
| Time | O(n) |
| Space | O(h) where h = height |

```java
class Solution {
    public int maxDepth(TreeNode root) {
        if (root == null) return 0;
        return 1 + Math.max(maxDepth(root.left), maxDepth(root.right));
    }
}
```

#### BFS Approach

```java
class Solution {
    public int maxDepth(TreeNode root) {
        if (root == null) return 0;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        int depth = 0;
        while (!queue.isEmpty()) {
            int size = queue.size();
            depth++;
            for (int i = 0; i < size; i++) {
                TreeNode node = queue.poll();
                if (node.left != null) queue.offer(node.left);
                if (node.right != null) queue.offer(node.right);
            }
        }
        return depth;
    }
}
```

---

### 9.2 Invert Binary Tree

**Problem:** Given the root of a binary tree, invert the tree (mirror it) and return its root.

| Complexity | Value |
|-----------|-------|
| Time | O(n) |
| Space | O(h) |

```java
class Solution {
    public TreeNode invertTree(TreeNode root) {
        if (root == null) return null;
        // Swap left and right children
        TreeNode temp = root.left;
        root.left = invertTree(root.right);
        root.right = invertTree(temp);
        return root;
    }
}
```

---

### 9.3 Binary Tree Level Order Traversal

**Problem:** Return the level order traversal of a binary tree (i.e., from left to right, level by level).

| Complexity | Value |
|-----------|-------|
| Time | O(n) |
| Space | O(n) |

```java
class Solution {
    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> result = new ArrayList<>();
        if (root == null) return result;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            int size = queue.size();
            List<Integer> level = new ArrayList<>();
            for (int i = 0; i < size; i++) {
                TreeNode node = queue.poll();
                level.add(node.val);
                if (node.left != null) queue.offer(node.left);
                if (node.right != null) queue.offer(node.right);
            }
            result.add(level);
        }
        return result;
    }
}
```

---

### 9.4 Validate Binary Search Tree

**Problem:** Given the root of a binary tree, determine if it is a valid BST.

#### Approach — Range Validation (DFS)

**Intuition:** Each node must be within a valid range `(min, max)`. As we go left, the max shrinks. As we go right, the min grows.

| Complexity | Value |
|-----------|-------|
| Time | O(n) |
| Space | O(h) |

```java
class Solution {
    public boolean isValidBST(TreeNode root) {
        return validate(root, Long.MIN_VALUE, Long.MAX_VALUE);
    }

    private boolean validate(TreeNode node, long min, long max) {
        if (node == null) return true;
        if (node.val <= min || node.val >= max) return false;
        return validate(node.left, min, node.val)
            && validate(node.right, node.val, max);
    }
}
```

**Pitfalls:**
- Using `int` for bounds fails when node values are `Integer.MIN_VALUE` or `Integer.MAX_VALUE`. Use `long`.
- Common mistake: only comparing with parent, not the full range.

---

### 9.5 Lowest Common Ancestor of a Binary Tree

**Problem:** Given a binary tree and two nodes `p` and `q`, find their lowest common ancestor (LCA).

| Complexity | Value |
|-----------|-------|
| Time | O(n) |
| Space | O(h) |

```java
class Solution {
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null || root == p || root == q) return root;
        TreeNode left = lowestCommonAncestor(root.left, p, q);
        TreeNode right = lowestCommonAncestor(root.right, p, q);
        // If both sides return non-null, root is the LCA
        if (left != null && right != null) return root;
        // Otherwise, LCA is in the non-null side
        return left != null ? left : right;
    }
}
```

---

### 9.6 Binary Tree Maximum Path Sum

**Problem:** Given a binary tree, find the maximum path sum. A path can start and end at any node.

| Complexity | Value |
|-----------|-------|
| Time | O(n) |
| Space | O(h) |

```java
class Solution {
    private int maxSum = Integer.MIN_VALUE;

    public int maxPathSum(TreeNode root) {
        dfs(root);
        return maxSum;
    }

    private int dfs(TreeNode node) {
        if (node == null) return 0;
        // Max gain from left and right subtrees (ignore negative paths)
        int leftGain = Math.max(0, dfs(node.left));
        int rightGain = Math.max(0, dfs(node.right));
        // Path through current node as highest point
        int currentPathSum = node.val + leftGain + rightGain;
        maxSum = Math.max(maxSum, currentPathSum);
        // Return max gain when continuing the path upward (can only pick one child)
        return node.val + Math.max(leftGain, rightGain);
    }
}
```

**Key insight:** The function returns the max gain when the path *continues upward* (can only go through one child), but updates the global max considering the path *through* the node (both children).

---

### 9.7 Serialize and Deserialize Binary Tree

**Problem:** Design an algorithm to serialize a binary tree to a string and deserialize it back.

```java
public class Codec {
    // Serialize using preorder traversal
    public String serialize(TreeNode root) {
        StringBuilder sb = new StringBuilder();
        serializeHelper(root, sb);
        return sb.toString();
    }

    private void serializeHelper(TreeNode node, StringBuilder sb) {
        if (node == null) {
            sb.append("null,");
            return;
        }
        sb.append(node.val).append(",");
        serializeHelper(node.left, sb);
        serializeHelper(node.right, sb);
    }

    // Deserialize from preorder string
    public TreeNode deserialize(String data) {
        Queue<String> nodes = new LinkedList<>(Arrays.asList(data.split(",")));
        return deserializeHelper(nodes);
    }

    private TreeNode deserializeHelper(Queue<String> nodes) {
        String val = nodes.poll();
        if ("null".equals(val)) return null;
        TreeNode node = new TreeNode(Integer.parseInt(val));
        node.left = deserializeHelper(nodes);
        node.right = deserializeHelper(nodes);
        return node;
    }
}
```

---

# 10. Graphs

## Pattern Overview

Graphs model relationships between objects. They consist of **vertices** (nodes) and **edges**. Most graph problems are solved with **BFS**, **DFS**, or specialized algorithms (Dijkstra, Union-Find, Topological Sort).

**Representations:**
- **Adjacency List:** `Map<Integer, List<Integer>>` — efficient for sparse graphs.
- **Adjacency Matrix:** `boolean[][]` — efficient for dense graphs.
- **Implicit graph:** Grid-based problems where neighbors are adjacent cells.

**Key algorithms:**
- **BFS:** Shortest path in unweighted graphs, level-order exploration.
- **DFS:** Connected components, cycle detection, path finding.
- **Topological Sort:** Ordering tasks with prerequisites (DAG only).
- **Union-Find (Disjoint Set):** Dynamic connectivity, cycle detection in undirected graphs.
- **Dijkstra's:** Shortest path in weighted graphs (non-negative weights).

**How to recognize:**
- "Number of islands / connected components" → BFS/DFS on grid
- "Course schedule / prerequisites" → Topological Sort
- "Shortest path" → BFS (unweighted) or Dijkstra (weighted)
- "Is the graph connected?" → Union-Find or DFS

**Companies:** Google, Meta, Uber, Netflix, Apple, Amazon, Microsoft

---

### 10.1 Number of Islands

**Problem:** Given an `m × n` 2D grid of `'1'`s (land) and `'0'`s (water), return the number of islands. An island is surrounded by water and is formed by connecting adjacent lands horizontally or vertically.

#### BFS Approach

| Complexity | Value |
|-----------|-------|
| Time | O(m × n) |
| Space | O(min(m, n)) — queue size |

```java
class Solution {
    public int numIslands(char[][] grid) {
        int count = 0;
        int m = grid.length, n = grid[0].length;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == '1') {
                    count++;
                    bfs(grid, i, j);
                }
            }
        }
        return count;
    }

    private void bfs(char[][] grid, int r, int c) {
        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[]{r, c});
        grid[r][c] = '0'; // Mark visited
        int[][] dirs = {{1,0},{-1,0},{0,1},{0,-1}};
        while (!queue.isEmpty()) {
            int[] cell = queue.poll();
            for (int[] d : dirs) {
                int nr = cell[0] + d[0], nc = cell[1] + d[1];
                if (nr >= 0 && nr < grid.length && nc >= 0 && nc < grid[0].length
                        && grid[nr][nc] == '1') {
                    grid[nr][nc] = '0';
                    queue.offer(new int[]{nr, nc});
                }
            }
        }
    }
}
```

#### DFS Approach

| Complexity | Value |
|-----------|-------|
| Time | O(m × n) |
| Space | O(m × n) worst case — recursion stack |

```java
class Solution {
    public int numIslands(char[][] grid) {
        int count = 0;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] == '1') {
                    count++;
                    dfs(grid, i, j);
                }
            }
        }
        return count;
    }

    private void dfs(char[][] grid, int i, int j) {
        if (i < 0 || i >= grid.length || j < 0 || j >= grid[0].length || grid[i][j] == '0') {
            return;
        }
        grid[i][j] = '0'; // Mark visited
        dfs(grid, i + 1, j);
        dfs(grid, i - 1, j);
        dfs(grid, i, j + 1);
        dfs(grid, i, j - 1);
    }
}
```

---

### 10.2 Clone Graph

**Problem:** Given a reference of a node in a connected undirected graph, return a deep copy of the graph. Each node has a value and a list of neighbors.

| Complexity | Value |
|-----------|-------|
| Time | O(V + E) |
| Space | O(V) |

```java
class Solution {
    private Map<Node, Node> visited = new HashMap<>();

    public Node cloneGraph(Node node) {
        if (node == null) return null;
        // If already cloned, return the clone
        if (visited.containsKey(node)) return visited.get(node);
        // Create clone
        Node clone = new Node(node.val, new ArrayList<>());
        visited.put(node, clone);
        // Recursively clone all neighbors
        for (Node neighbor : node.neighbors) {
            clone.neighbors.add(cloneGraph(neighbor));
        }
        return clone;
    }
}
```

---

### 10.3 Course Schedule (Cycle Detection / Topological Sort)

**Problem:** There are `numCourses` courses labeled `0` to `numCourses - 1`. You are given `prerequisites` where `prerequisites[i] = [ai, bi]` means you must take course `bi` before `ai`. Return `true` if you can finish all courses (i.e., no cycle in the prerequisite graph).

#### Approach — BFS (Kahn's Algorithm for Topological Sort)

| Complexity | Value |
|-----------|-------|
| Time | O(V + E) |
| Space | O(V + E) |

```java
class Solution {
    public boolean canFinish(int numCourses, int[][] prerequisites) {
        // Build adjacency list and in-degree array
        List<List<Integer>> graph = new ArrayList<>();
        int[] inDegree = new int[numCourses];
        for (int i = 0; i < numCourses; i++) graph.add(new ArrayList<>());
        for (int[] pre : prerequisites) {
            graph.get(pre[1]).add(pre[0]);
            inDegree[pre[0]]++;
        }
        // Start with courses that have no prerequisites
        Queue<Integer> queue = new LinkedList<>();
        for (int i = 0; i < numCourses; i++) {
            if (inDegree[i] == 0) queue.offer(i);
        }
        int completed = 0;
        while (!queue.isEmpty()) {
            int course = queue.poll();
            completed++;
            for (int next : graph.get(course)) {
                inDegree[next]--;
                if (inDegree[next] == 0) queue.offer(next);
            }
        }
        return completed == numCourses; // If all completed, no cycle
    }
}
```

#### Approach — DFS (Cycle Detection with Colors)

```java
class Solution {
    public boolean canFinish(int numCourses, int[][] prerequisites) {
        List<List<Integer>> graph = new ArrayList<>();
        for (int i = 0; i < numCourses; i++) graph.add(new ArrayList<>());
        for (int[] pre : prerequisites) {
            graph.get(pre[1]).add(pre[0]);
        }
        // 0 = unvisited, 1 = in current path, 2 = fully processed
        int[] state = new int[numCourses];
        for (int i = 0; i < numCourses; i++) {
            if (hasCycle(graph, i, state)) return false;
        }
        return true;
    }

    private boolean hasCycle(List<List<Integer>> graph, int node, int[] state) {
        if (state[node] == 1) return true;  // Cycle detected
        if (state[node] == 2) return false; // Already processed
        state[node] = 1; // Mark as in-progress
        for (int neighbor : graph.get(node)) {
            if (hasCycle(graph, neighbor, state)) return true;
        }
        state[node] = 2; // Mark as completed
        return false;
    }
}
```

---

### 10.4 Pacific Atlantic Water Flow

**Problem:** Given an `m × n` island grid with heights, water can flow to neighboring cells with height ≤ current cell. Find all cells from which water can flow to **both** the Pacific and Atlantic oceans.

**Intuition:** Instead of checking from each cell, do **reverse BFS/DFS** from each ocean border. Find cells reachable from Pacific and cells reachable from Atlantic; the intersection is the answer.

| Complexity | Value |
|-----------|-------|
| Time | O(m × n) |
| Space | O(m × n) |

```java
class Solution {
    private int[][] dirs = {{1,0},{-1,0},{0,1},{0,-1}};

    public List<List<Integer>> pacificAtlantic(int[][] heights) {
        int m = heights.length, n = heights[0].length;
        boolean[][] pacific = new boolean[m][n];
        boolean[][] atlantic = new boolean[m][n];
        Queue<int[]> pacQueue = new LinkedList<>(), atlQueue = new LinkedList<>();
        for (int i = 0; i < m; i++) {
            pacQueue.offer(new int[]{i, 0});     pacific[i][0] = true;
            atlQueue.offer(new int[]{i, n - 1}); atlantic[i][n - 1] = true;
        }
        for (int j = 0; j < n; j++) {
            pacQueue.offer(new int[]{0, j});     pacific[0][j] = true;
            atlQueue.offer(new int[]{m - 1, j}); atlantic[m - 1][j] = true;
        }
        bfs(heights, pacQueue, pacific);
        bfs(heights, atlQueue, atlantic);
        // Intersection
        List<List<Integer>> result = new ArrayList<>();
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (pacific[i][j] && atlantic[i][j]) {
                    result.add(Arrays.asList(i, j));
                }
            }
        }
        return result;
    }

    private void bfs(int[][] heights, Queue<int[]> queue, boolean[][] reachable) {
        while (!queue.isEmpty()) {
            int[] cell = queue.poll();
            for (int[] d : dirs) {
                int nr = cell[0] + d[0], nc = cell[1] + d[1];
                if (nr >= 0 && nr < heights.length && nc >= 0 && nc < heights[0].length
                        && !reachable[nr][nc]
                        && heights[nr][nc] >= heights[cell[0]][cell[1]]) {
                    reachable[nr][nc] = true;
                    queue.offer(new int[]{nr, nc});
                }
            }
        }
    }
}
```

---

### 10.5 Graph Valid Tree

**Problem:** Given `n` nodes labeled `0` to `n - 1` and a list of undirected edges, check if these edges form a valid tree (connected and acyclic).

**Key insight:** A valid tree has exactly `n - 1` edges and is connected.

#### Approach — Union-Find

| Complexity | Value |
|-----------|-------|
| Time | O(n · α(n)) ≈ O(n) |
| Space | O(n) |

```java
class Solution {
    private int[] parent, rank;

    public boolean validTree(int n, int[][] edges) {
        if (edges.length != n - 1) return false;
        parent = new int[n];
        rank = new int[n];
        for (int i = 0; i < n; i++) parent[i] = i;

        for (int[] edge : edges) {
            if (!union(edge[0], edge[1])) return false;
        }
        return true;
    }

    private int find(int x) {
        if (parent[x] != x) parent[x] = find(parent[x]); // Path compression
        return parent[x];
    }

    private boolean union(int x, int y) {
        int px = find(x), py = find(y);
        if (px == py) return false; // Cycle detected
        if (rank[px] < rank[py]) { int tmp = px; px = py; py = tmp; }
        parent[py] = px;
        if (rank[px] == rank[py]) rank[px]++;
        return true;
    }
}
```

---

### 10.6 Word Ladder (BFS Shortest Path)

**Problem:** Given `beginWord`, `endWord`, and a dictionary `wordList`, find the length of the shortest transformation sequence from `beginWord` to `endWord`, changing one letter at a time.

| Complexity | Value |
|-----------|-------|
| Time | O(M² × N) where M = word length, N = word list size |
| Space | O(M² × N) |

```java
class Solution {
    public int ladderLength(String beginWord, String endWord, List<String> wordList) {
        Set<String> wordSet = new HashSet<>(wordList);
        if (!wordSet.contains(endWord)) return 0;
        Queue<String> queue = new LinkedList<>();
        queue.offer(beginWord);
        Set<String> visited = new HashSet<>();
        visited.add(beginWord);
        int level = 1;
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                String word = queue.poll();
                char[] chars = word.toCharArray();
                for (int j = 0; j < chars.length; j++) {
                    char original = chars[j];
                    for (char c = 'a'; c <= 'z'; c++) {
                        if (c == original) continue;
                        chars[j] = c;
                        String next = new String(chars);
                        if (next.equals(endWord)) return level + 1;
                        if (wordSet.contains(next) && !visited.contains(next)) {
                            visited.add(next);
                            queue.offer(next);
                        }
                    }
                    chars[j] = original; // Restore
                }
            }
            level++;
        }
        return 0;
    }
}
```

---

# 11. Heap / Priority Queue

## Pattern Overview

A **Heap** (Priority Queue) efficiently gives you the min or max element. Java's `PriorityQueue` is a min-heap by default.

**When to use:**
- "Find the k-th largest/smallest"
- "Merge k sorted lists"
- "Top k frequent elements" (alternative to bucket sort)
- "Median from data stream"
- Scheduling / greedy where you always process the best option

**How to recognize:**
- Need repeated access to the extreme (min/max) element
- "K largest", "K closest", "K most frequent"

**Companies:** Amazon, Google, Meta, Uber, Microsoft

---

### 11.1 Kth Largest Element in an Array

**Problem:** Given an integer array `nums` and an integer `k`, return the `k`th largest element.

#### Brute Force — Sort

| Complexity | Value |
|-----------|-------|
| Time | O(n log n) |
| Space | O(1) |

```java
class Solution {
    public int findKthLargest(int[] nums, int k) {
        Arrays.sort(nums);
        return nums[nums.length - k];
    }
}
```

#### Optimized Approach — Min-Heap of Size k

**Intuition:** Maintain a min-heap of size k. After processing all elements, the root is the k-th largest.

| Complexity | Value |
|-----------|-------|
| Time | O(n log k) |
| Space | O(k) |

```java
class Solution {
    public int findKthLargest(int[] nums, int k) {
        PriorityQueue<Integer> minHeap = new PriorityQueue<>();
        for (int num : nums) {
            minHeap.offer(num);
            // Keep only k largest elements
            if (minHeap.size() > k) {
                minHeap.poll();
            }
        }
        return minHeap.peek();
    }
}
```

#### Optimal — QuickSelect (Average O(n))

```java
class Solution {
    public int findKthLargest(int[] nums, int k) {
        int target = nums.length - k; // Convert to index in sorted order
        return quickSelect(nums, 0, nums.length - 1, target);
    }

    private int quickSelect(int[] nums, int left, int right, int target) {
        int pivot = nums[right];
        int storeIdx = left;
        for (int i = left; i < right; i++) {
            if (nums[i] <= pivot) {
                swap(nums, i, storeIdx);
                storeIdx++;
            }
        }
        swap(nums, storeIdx, right);
        if (storeIdx == target) return nums[storeIdx];
        else if (storeIdx < target) return quickSelect(nums, storeIdx + 1, right, target);
        else return quickSelect(nums, left, storeIdx - 1, target);
    }

    private void swap(int[] nums, int i, int j) {
        int tmp = nums[i]; nums[i] = nums[j]; nums[j] = tmp;
    }
}
```

---

### 11.2 Merge K Sorted Lists

**Problem:** Merge `k` sorted linked lists into one sorted linked list.

#### Brute Force — Collect All, Sort

| Complexity | Value |
|-----------|-------|
| Time | O(N log N) where N = total nodes |
| Space | O(N) |

```java
class Solution {
    public ListNode mergeKLists(ListNode[] lists) {
        List<Integer> values = new ArrayList<>();
        for (ListNode node : lists) {
            while (node != null) {
                values.add(node.val);
                node = node.next;
            }
        }
        Collections.sort(values);
        ListNode dummy = new ListNode(0);
        ListNode curr = dummy;
        for (int val : values) {
            curr.next = new ListNode(val);
            curr = curr.next;
        }
        return dummy.next;
    }
}
```

#### Optimized Approach — Min-Heap

**Intuition:** Use a min-heap to always pick the smallest head across all k lists.

| Complexity | Value |
|-----------|-------|
| Time | O(N log k) |
| Space | O(k) |

```java
class Solution {
    public ListNode mergeKLists(ListNode[] lists) {
        PriorityQueue<ListNode> minHeap = new PriorityQueue<>((a, b) -> a.val - b.val);
        // Add head of each list to the heap
        for (ListNode node : lists) {
            if (node != null) minHeap.offer(node);
        }
        ListNode dummy = new ListNode(0);
        ListNode curr = dummy;
        while (!minHeap.isEmpty()) {
            ListNode smallest = minHeap.poll();
            curr.next = smallest;
            curr = curr.next;
            // Add next node from the same list
            if (smallest.next != null) {
                minHeap.offer(smallest.next);
            }
        }
        return dummy.next;
    }
}
```

---

### 11.3 Find Median from Data Stream

**Problem:** Design a data structure that supports `addNum(int num)` and `findMedian()` efficiently.

**Approach:** Use two heaps — a **max-heap** for the lower half and a **min-heap** for the upper half. The median is derived from the tops of these heaps.

```java
class MedianFinder {
    private PriorityQueue<Integer> maxHeap; // Lower half (max at top)
    private PriorityQueue<Integer> minHeap; // Upper half (min at top)

    public MedianFinder() {
        maxHeap = new PriorityQueue<>(Collections.reverseOrder());
        minHeap = new PriorityQueue<>();
    }

    public void addNum(int num) {
        maxHeap.offer(num);
        // Ensure max of lower half <= min of upper half
        minHeap.offer(maxHeap.poll());
        // Balance sizes: maxHeap can have at most 1 more element
        if (minHeap.size() > maxHeap.size()) {
            maxHeap.offer(minHeap.poll());
        }
    }

    public double findMedian() {
        if (maxHeap.size() > minHeap.size()) {
            return maxHeap.peek();
        }
        return (maxHeap.peek() + minHeap.peek()) / 2.0;
    }
}
```

| Operation | Time |
|-----------|------|
| addNum | O(log n) |
| findMedian | O(1) |

---

### 11.4 Task Scheduler

**Problem:** Given tasks represented by characters and a cooldown period `n`, find the minimum number of intervals the CPU needs to execute all tasks.

**Intuition:** Greedily schedule the most frequent task first. The answer is bounded by the task with highest frequency.

| Complexity | Value |
|-----------|-------|
| Time | O(N) where N = total tasks |
| Space | O(1) — 26 letters |

```java
class Solution {
    public int leastInterval(char[] tasks, int n) {
        int[] freq = new int[26];
        for (char t : tasks) freq[t - 'A']++;
        Arrays.sort(freq);
        int maxFreq = freq[25];
        // Count how many tasks have the max frequency
        int maxCount = 0;
        for (int f : freq) {
            if (f == maxFreq) maxCount++;
        }
        // Formula: (maxFreq - 1) * (n + 1) + maxCount
        // But answer is at least tasks.length (no idle needed if enough variety)
        int result = (maxFreq - 1) * (n + 1) + maxCount;
        return Math.max(result, tasks.length);
    }
}
```

---

# 12. Trie

## Pattern Overview

A **Trie** (prefix tree) is a tree-like data structure for efficient string prefix operations. Each node represents a character, and paths from root to nodes represent prefixes.

**When to use:**
- Autocomplete / prefix matching
- Word search in a dictionary
- Counting words with a given prefix
- Spell checking

**How to recognize:**
- "Search for words with prefix …"
- "Implement autocomplete"
- "Word search II" (multiple words in a grid)

**Companies:** Google, Amazon, Microsoft, Meta

---

### 12.1 Implement Trie (Prefix Tree)

**Problem:** Implement a trie with `insert`, `search`, and `startsWith` methods.

```java
class Trie {
    private class TrieNode {
        TrieNode[] children = new TrieNode[26];
        boolean isEnd = false;
    }

    private TrieNode root;

    public Trie() {
        root = new TrieNode();
    }

    public void insert(String word) {
        TrieNode node = root;
        for (char c : word.toCharArray()) {
            int idx = c - 'a';
            if (node.children[idx] == null) {
                node.children[idx] = new TrieNode();
            }
            node = node.children[idx];
        }
        node.isEnd = true;
    }

    public boolean search(String word) {
        TrieNode node = searchPrefix(word);
        return node != null && node.isEnd;
    }

    public boolean startsWith(String prefix) {
        return searchPrefix(prefix) != null;
    }

    private TrieNode searchPrefix(String word) {
        TrieNode node = root;
        for (char c : word.toCharArray()) {
            int idx = c - 'a';
            if (node.children[idx] == null) return null;
            node = node.children[idx];
        }
        return node;
    }
}
```

| Operation | Time | Space |
|-----------|------|-------|
| insert | O(m) | O(m) per word |
| search | O(m) | O(1) |
| startsWith | O(m) | O(1) |

*m = length of the word/prefix*

---

### 12.2 Word Search II

**Problem:** Given an `m × n` board of characters and a list of words, find all words that can be formed by sequentially adjacent cells (each cell used at most once per word).

**Approach:** Build a Trie from the word list. Then DFS on the board, walking the Trie simultaneously to prune invalid paths.

| Complexity | Value |
|-----------|-------|
| Time | O(m × n × 4^L) pruned by Trie |
| Space | O(W × L) for Trie where W = words, L = avg length |

```java
class Solution {
    private class TrieNode {
        TrieNode[] children = new TrieNode[26];
        String word = null; // Store complete word at terminal node
    }

    public List<String> findWords(char[][] board, String[] words) {
        // Build Trie
        TrieNode root = new TrieNode();
        for (String word : words) {
            TrieNode node = root;
            for (char c : word.toCharArray()) {
                int idx = c - 'a';
                if (node.children[idx] == null) node.children[idx] = new TrieNode();
                node = node.children[idx];
            }
            node.word = word;
        }
        List<String> result = new ArrayList<>();
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                dfs(board, i, j, root, result);
            }
        }
        return result;
    }

    private void dfs(char[][] board, int i, int j, TrieNode node, List<String> result) {
        if (i < 0 || i >= board.length || j < 0 || j >= board[0].length) return;
        char c = board[i][j];
        if (c == '#' || node.children[c - 'a'] == null) return;

        node = node.children[c - 'a'];
        if (node.word != null) {
            result.add(node.word);
            node.word = null; // Avoid duplicates
        }
        board[i][j] = '#'; // Mark visited
        dfs(board, i + 1, j, node, result);
        dfs(board, i - 1, j, node, result);
        dfs(board, i, j + 1, node, result);
        dfs(board, i, j - 1, node, result);
        board[i][j] = c; // Restore
    }
}
```

---

# 13. Dynamic Programming

## Pattern Overview

**Dynamic Programming (DP)** solves problems by breaking them into overlapping subproblems and storing results to avoid redundant computation. It is the most feared yet most rewarding pattern.

**Two approaches:**
- **Top-Down (Memoization):** Recursive with caching. Easier to write; follows the recurrence naturally.
- **Bottom-Up (Tabulation):** Iterative; builds the solution from base cases. Often more space-efficient.

**DP Categories:**
| Category | Examples |
|----------|---------|
| 1D DP | Climbing Stairs, House Robber, Coin Change |
| 2D DP | Longest Common Subsequence, Edit Distance, Unique Paths |
| Knapsack | 0/1 Knapsack, Subset Sum, Partition Equal Subset Sum |
| String DP | Palindrome Substring, Edit Distance, Regex Matching |
| Interval DP | Matrix Chain Multiplication, Burst Balloons |
| State Machine DP | Buy/Sell Stock with cooldown, with at most k transactions |

**How to recognize:**
- "Count the number of ways …"
- "What is the minimum/maximum cost to …"
- "Can you reach / partition / form …?"
- Optimal substructure + overlapping subproblems

**Framework for solving DP:**
1. **Define the state:** What does `dp[i]` (or `dp[i][j]`) represent?
2. **Find the recurrence:** How does `dp[i]` relate to previous states?
3. **Identify base cases:** What are the trivially known values?
4. **Determine iteration order:** Ensure dependencies are computed first.
5. **Optimize space** if possible (often only previous row/column needed).

**Companies:** Google, Amazon, Meta, Apple, Uber, Microsoft, Netflix

---

### 13.1 Climbing Stairs

**Problem:** You are climbing a staircase with `n` steps. Each time you can climb 1 or 2 steps. In how many distinct ways can you reach the top?

#### Brute Force — Pure Recursion

| Complexity | Value |
|-----------|-------|
| Time | O(2ⁿ) |
| Space | O(n) |

```java
class Solution {
    public int climbStairs(int n) {
        if (n <= 2) return n;
        return climbStairs(n - 1) + climbStairs(n - 2);
    }
}
```

#### Optimized — Bottom-Up DP (O(1) space)

**State:** `dp[i]` = number of ways to reach step `i`.
**Recurrence:** `dp[i] = dp[i-1] + dp[i-2]` (Fibonacci).

| Complexity | Value |
|-----------|-------|
| Time | O(n) |
| Space | O(1) |

```java
class Solution {
    public int climbStairs(int n) {
        if (n <= 2) return n;
        int prev2 = 1, prev1 = 2;
        for (int i = 3; i <= n; i++) {
            int curr = prev1 + prev2;
            prev2 = prev1;
            prev1 = curr;
        }
        return prev1;
    }
}
```

---

### 13.2 House Robber

**Problem:** Given an array `nums` representing money in each house along a street, return the max amount you can rob without robbing two adjacent houses.

#### Brute Force — Recursion

| Complexity | Value |
|-----------|-------|
| Time | O(2ⁿ) |
| Space | O(n) |

```java
class Solution {
    public int rob(int[] nums) {
        return robHelper(nums, nums.length - 1);
    }

    private int robHelper(int[] nums, int i) {
        if (i < 0) return 0;
        // Either rob house i (skip i-1) or skip house i
        return Math.max(robHelper(nums, i - 2) + nums[i], robHelper(nums, i - 1));
    }
}
```

#### Optimized — Bottom-Up DP

**State:** `dp[i]` = max money robbing from house `0` to `i`.
**Recurrence:** `dp[i] = max(dp[i-1], dp[i-2] + nums[i])`

| Complexity | Value |
|-----------|-------|
| Time | O(n) |
| Space | O(1) |

```java
class Solution {
    public int rob(int[] nums) {
        if (nums.length == 1) return nums[0];
        int prev2 = 0, prev1 = 0;
        for (int num : nums) {
            int curr = Math.max(prev1, prev2 + num);
            prev2 = prev1;
            prev1 = curr;
        }
        return prev1;
    }
}
```

---

### 13.3 Coin Change

**Problem:** Given coins of different denominations and a total `amount`, return the fewest number of coins needed. Return `-1` if not possible.

#### Brute Force — Recursion

| Complexity | Value |
|-----------|-------|
| Time | O(S^n) where S = amount, n = coin types |
| Space | O(S) |

```java
class Solution {
    public int coinChange(int[] coins, int amount) {
        if (amount == 0) return 0;
        if (amount < 0) return -1;
        int minCoins = Integer.MAX_VALUE;
        for (int coin : coins) {
            int sub = coinChange(coins, amount - coin);
            if (sub >= 0) {
                minCoins = Math.min(minCoins, sub + 1);
            }
        }
        return minCoins == Integer.MAX_VALUE ? -1 : minCoins;
    }
}
```

#### Optimized — Bottom-Up DP

**State:** `dp[i]` = min coins to make amount `i`.
**Recurrence:** `dp[i] = min(dp[i - coin] + 1)` for each coin.

| Complexity | Value |
|-----------|-------|
| Time | O(amount × n) |
| Space | O(amount) |

```java
class Solution {
    public int coinChange(int[] coins, int amount) {
        int[] dp = new int[amount + 1];
        Arrays.fill(dp, amount + 1); // Fill with "impossible" value
        dp[0] = 0;
        for (int i = 1; i <= amount; i++) {
            for (int coin : coins) {
                if (coin <= i) {
                    dp[i] = Math.min(dp[i], dp[i - coin] + 1);
                }
            }
        }
        return dp[amount] > amount ? -1 : dp[amount];
    }
}
```

---

### 13.4 Longest Increasing Subsequence (LIS)

**Problem:** Given an integer array `nums`, return the length of the longest strictly increasing subsequence.

#### Brute Force — DP O(n²)

**State:** `dp[i]` = length of LIS ending at index `i`.

| Complexity | Value |
|-----------|-------|
| Time | O(n²) |
| Space | O(n) |

```java
class Solution {
    public int lengthOfLIS(int[] nums) {
        int n = nums.length;
        int[] dp = new int[n];
        Arrays.fill(dp, 1); // Each element is an LIS of length 1
        int maxLen = 1;
        for (int i = 1; i < n; i++) {
            for (int j = 0; j < i; j++) {
                if (nums[j] < nums[i]) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }
            maxLen = Math.max(maxLen, dp[i]);
        }
        return maxLen;
    }
}
```

#### Optimized — Binary Search (Patience Sorting)

**Intuition:** Maintain a list `tails` where `tails[i]` is the smallest tail element for an increasing subsequence of length `i+1`. Use binary search to find the position.

| Complexity | Value |
|-----------|-------|
| Time | O(n log n) |
| Space | O(n) |

```java
class Solution {
    public int lengthOfLIS(int[] nums) {
        List<Integer> tails = new ArrayList<>();
        for (int num : nums) {
            int pos = Collections.binarySearch(tails, num);
            if (pos < 0) pos = -(pos + 1); // Insertion point
            if (pos == tails.size()) {
                tails.add(num); // Extend the longest subsequence
            } else {
                tails.set(pos, num); // Replace with smaller tail
            }
        }
        return tails.size();
    }
}
```

---

### 13.5 Longest Common Subsequence

**Problem:** Given two strings `text1` and `text2`, return the length of their longest common subsequence.

#### Optimized — 2D DP

**State:** `dp[i][j]` = LCS of `text1[0..i-1]` and `text2[0..j-1]`.
**Recurrence:**
- If `text1[i-1] == text2[j-1]`: `dp[i][j] = dp[i-1][j-1] + 1`
- Else: `dp[i][j] = max(dp[i-1][j], dp[i][j-1])`

| Complexity | Value |
|-----------|-------|
| Time | O(m × n) |
| Space | O(m × n), optimizable to O(min(m, n)) |

```java
class Solution {
    public int longestCommonSubsequence(String text1, String text2) {
        int m = text1.length(), n = text2.length();
        int[][] dp = new int[m + 1][n + 1];
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if (text1.charAt(i - 1) == text2.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                } else {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                }
            }
        }
        return dp[m][n];
    }
}
```

---

### 13.6 Word Break

**Problem:** Given a string `s` and a dictionary of words `wordDict`, return `true` if `s` can be segmented into a space-separated sequence of dictionary words.

#### Brute Force — Recursion

| Complexity | Value |
|-----------|-------|
| Time | O(2ⁿ) |
| Space | O(n) |

```java
class Solution {
    public boolean wordBreak(String s, List<String> wordDict) {
        return canBreak(s, new HashSet<>(wordDict), 0);
    }

    private boolean canBreak(String s, Set<String> dict, int start) {
        if (start == s.length()) return true;
        for (int end = start + 1; end <= s.length(); end++) {
            if (dict.contains(s.substring(start, end)) && canBreak(s, dict, end)) {
                return true;
            }
        }
        return false;
    }
}
```

#### Optimized — Bottom-Up DP

**State:** `dp[i]` = can `s[0..i-1]` be segmented?

| Complexity | Value |
|-----------|-------|
| Time | O(n² × m) where m = avg word length for substring |
| Space | O(n) |

```java
class Solution {
    public boolean wordBreak(String s, List<String> wordDict) {
        Set<String> dict = new HashSet<>(wordDict);
        int n = s.length();
        boolean[] dp = new boolean[n + 1];
        dp[0] = true; // Empty string
        for (int i = 1; i <= n; i++) {
            for (int j = 0; j < i; j++) {
                // If s[0..j-1] can be segmented AND s[j..i-1] is in dict
                if (dp[j] && dict.contains(s.substring(j, i))) {
                    dp[i] = true;
                    break;
                }
            }
        }
        return dp[n];
    }
}
```

---

### 13.7 Partition Equal Subset Sum (0/1 Knapsack)

**Problem:** Given a non-empty array `nums` containing only positive integers, determine if the array can be partitioned into two subsets with equal sum.

**Reduction:** This is equivalent to: can we find a subset with sum = `totalSum / 2`? (0/1 Knapsack variant)

| Complexity | Value |
|-----------|-------|
| Time | O(n × sum) |
| Space | O(sum) |

```java
class Solution {
    public boolean canPartition(int[] nums) {
        int total = 0;
        for (int num : nums) total += num;
        if (total % 2 != 0) return false; // Odd sum can't be split equally
        int target = total / 2;
        boolean[] dp = new boolean[target + 1];
        dp[0] = true;
        for (int num : nums) {
            // Traverse right to left to avoid using same element twice
            for (int j = target; j >= num; j--) {
                dp[j] = dp[j] || dp[j - num];
            }
        }
        return dp[target];
    }
}
```

**Key insight:** Iterating **right to left** ensures each number is used at most once (0/1 knapsack). Left to right would allow unlimited reuse (unbounded knapsack).

---

### 13.8 Edit Distance

**Problem:** Given two strings `word1` and `word2`, return the minimum number of operations (insert, delete, replace) to convert `word1` to `word2`.

**State:** `dp[i][j]` = edit distance between `word1[0..i-1]` and `word2[0..j-1]`.

| Complexity | Value |
|-----------|-------|
| Time | O(m × n) |
| Space | O(m × n) |

```java
class Solution {
    public int minDistance(String word1, String word2) {
        int m = word1.length(), n = word2.length();
        int[][] dp = new int[m + 1][n + 1];
        // Base cases
        for (int i = 0; i <= m; i++) dp[i][0] = i; // Delete all chars
        for (int j = 0; j <= n; j++) dp[0][j] = j; // Insert all chars

        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if (word1.charAt(i - 1) == word2.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1]; // No operation needed
                } else {
                    dp[i][j] = 1 + Math.min(
                        dp[i - 1][j - 1], // Replace
                        Math.min(dp[i - 1][j],  // Delete
                                 dp[i][j - 1])   // Insert
                    );
                }
            }
        }
        return dp[m][n];
    }
}
```

---

### 13.9 Unique Paths

**Problem:** A robot is at the top-left corner of an `m × n` grid. It can only move right or down. How many unique paths exist to the bottom-right corner?

| Complexity | Value |
|-----------|-------|
| Time | O(m × n) |
| Space | O(n) — space optimized |

```java
class Solution {
    public int uniquePaths(int m, int n) {
        int[] dp = new int[n];
        Arrays.fill(dp, 1); // First row: all 1s
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                dp[j] += dp[j - 1]; // dp[j] = from above + from left
            }
        }
        return dp[n - 1];
    }
}
```

---

### 13.10 Decode Ways

**Problem:** Given a string `s` containing only digits, return the number of ways to decode it (where 'A' = 1, 'B' = 2, ..., 'Z' = 26).

| Complexity | Value |
|-----------|-------|
| Time | O(n) |
| Space | O(1) |

```java
class Solution {
    public int numDecodings(String s) {
        if (s.charAt(0) == '0') return 0;
        int n = s.length();
        int prev2 = 1, prev1 = 1; // dp[0] = 1, dp[1] = 1 (if s[0] != '0')
        for (int i = 2; i <= n; i++) {
            int curr = 0;
            int oneDigit = s.charAt(i - 1) - '0';
            int twoDigit = Integer.parseInt(s.substring(i - 2, i));
            // Single digit decode (1-9)
            if (oneDigit >= 1) curr += prev1;
            // Two digit decode (10-26)
            if (twoDigit >= 10 && twoDigit <= 26) curr += prev2;
            prev2 = prev1;
            prev1 = curr;
        }
        return prev1;
    }
}
```

**Pitfalls:**
- Leading zeros: `"06"` has 0 ways.
- `"0"` → 0 ways, `"10"` → 1 way, `"27"` → 1 way.

---

# 14. Greedy Algorithms

## Pattern Overview

**Greedy** algorithms make the locally optimal choice at each step, hoping it leads to a globally optimal solution. Unlike DP, greedy doesn't reconsider past choices.

**When greedy works:**
- The problem has the **greedy choice property** (local optimum leads to global optimum).
- The problem has **optimal substructure**.

**How to recognize:**
- "Maximum/minimum number of …" with a sorting step
- Interval scheduling, activity selection
- "Jump game" style problems
- Often involves sorting by some criterion first

**Companies:** Google, Amazon, Uber, Meta, Apple

---

### 14.1 Jump Game

**Problem:** Given an array `nums` where `nums[i]` is the max jump length from position `i`, determine if you can reach the last index.

#### Greedy — Track Farthest Reachable

**Intuition:** Track the farthest position reachable. If at any index `i` we find `i > farthest`, we're stuck.

| Complexity | Value |
|-----------|-------|
| Time | O(n) |
| Space | O(1) |

```java
class Solution {
    public boolean canJump(int[] nums) {
        int farthest = 0;
        for (int i = 0; i < nums.length; i++) {
            if (i > farthest) return false; // Can't reach this index
            farthest = Math.max(farthest, i + nums[i]);
        }
        return true;
    }
}
```

---

### 14.2 Jump Game II

**Problem:** Given the same setup, return the **minimum** number of jumps to reach the last index. You can always reach the last index.

**Intuition:** BFS-like approach. Track the current range `[start, end]` we can reach with the current number of jumps. Find the farthest position reachable from this range, then jump.

| Complexity | Value |
|-----------|-------|
| Time | O(n) |
| Space | O(1) |

```java
class Solution {
    public int jump(int[] nums) {
        int jumps = 0, currentEnd = 0, farthest = 0;
        for (int i = 0; i < nums.length - 1; i++) {
            farthest = Math.max(farthest, i + nums[i]);
            // When we've explored all positions in the current jump range
            if (i == currentEnd) {
                jumps++;
                currentEnd = farthest;
            }
        }
        return jumps;
    }
}
```

---

### 14.3 Gas Station

**Problem:** There are `n` gas stations in a circle. You start with an empty tank. `gas[i]` is the gas at station `i`, `cost[i]` is the cost to travel to the next station. Return the starting station index if you can complete the circuit, else return `-1`.

**Intuition:** If `totalGas >= totalCost`, a solution exists. The starting point is where the running surplus is the most negative (reset point).

| Complexity | Value |
|-----------|-------|
| Time | O(n) |
| Space | O(1) |

```java
class Solution {
    public int canCompleteCircuit(int[] gas, int[] cost) {
        int totalSurplus = 0, currentSurplus = 0, start = 0;
        for (int i = 0; i < gas.length; i++) {
            int diff = gas[i] - cost[i];
            totalSurplus += diff;
            currentSurplus += diff;
            // If we run out of gas, reset start to next station
            if (currentSurplus < 0) {
                start = i + 1;
                currentSurplus = 0;
            }
        }
        return totalSurplus >= 0 ? start : -1;
    }
}
```

---

### 14.4 Hand of Straights / Group Cards

**Problem:** Given an array of integers `hand` and an integer `groupSize`, return `true` if the array can be rearranged into groups of `groupSize` consecutive cards.

| Complexity | Value |
|-----------|-------|
| Time | O(n log n) |
| Space | O(n) |

```java
class Solution {
    public boolean isNStraightHand(int[] hand, int groupSize) {
        if (hand.length % groupSize != 0) return false;
        TreeMap<Integer, Integer> countMap = new TreeMap<>();
        for (int card : hand) {
            countMap.put(card, countMap.getOrDefault(card, 0) + 1);
        }
        while (!countMap.isEmpty()) {
            int first = countMap.firstKey(); // Smallest available card
            for (int i = first; i < first + groupSize; i++) {
                if (!countMap.containsKey(i)) return false;
                int count = countMap.get(i);
                if (count == 1) {
                    countMap.remove(i);
                } else {
                    countMap.put(i, count - 1);
                }
            }
        }
        return true;
    }
}
```

---

# 15. Intervals

## Pattern Overview

Interval problems involve ranges `[start, end]` and require merging, inserting, or finding overlaps. The key technique is **sorting by start time** and then processing linearly.

**When to use:**
- "Merge overlapping intervals"
- "Insert a new interval"
- "Meeting rooms" problems
- "Minimum number of platforms/rooms"

**How to recognize:**
- Input is a list of `[start, end]` pairs
- Need to find overlaps, gaps, or merge ranges

**Companies:** Google, Meta, Amazon, Uber, Microsoft, Bloomberg

---

### 15.1 Merge Intervals

**Problem:** Given an array of intervals `[start, end]`, merge all overlapping intervals.

| Complexity | Value |
|-----------|-------|
| Time | O(n log n) |
| Space | O(n) |

```java
class Solution {
    public int[][] merge(int[][] intervals) {
        // Sort by start time
        Arrays.sort(intervals, (a, b) -> a[0] - b[0]);
        List<int[]> merged = new ArrayList<>();
        for (int[] interval : intervals) {
            // If merged list is empty or no overlap
            if (merged.isEmpty() || merged.get(merged.size() - 1)[1] < interval[0]) {
                merged.add(interval);
            } else {
                // Overlap: extend the end of the last merged interval
                merged.get(merged.size() - 1)[1] =
                    Math.max(merged.get(merged.size() - 1)[1], interval[1]);
            }
        }
        return merged.toArray(new int[merged.size()][]);
    }
}
```

---

### 15.2 Insert Interval

**Problem:** Given a set of non-overlapping intervals sorted by start time and a new interval, insert and merge if necessary.

| Complexity | Value |
|-----------|-------|
| Time | O(n) |
| Space | O(n) |

```java
class Solution {
    public int[][] insert(int[][] intervals, int[] newInterval) {
        List<int[]> result = new ArrayList<>();
        int i = 0, n = intervals.length;
        // Add all intervals that come before newInterval
        while (i < n && intervals[i][1] < newInterval[0]) {
            result.add(intervals[i++]);
        }
        // Merge overlapping intervals with newInterval
        while (i < n && intervals[i][0] <= newInterval[1]) {
            newInterval[0] = Math.min(newInterval[0], intervals[i][0]);
            newInterval[1] = Math.max(newInterval[1], intervals[i][1]);
            i++;
        }
        result.add(newInterval);
        // Add remaining intervals
        while (i < n) {
            result.add(intervals[i++]);
        }
        return result.toArray(new int[result.size()][]);
    }
}
```

---

### 15.3 Non-Overlapping Intervals

**Problem:** Given an array of intervals, return the minimum number of intervals you need to remove to make the rest non-overlapping.

**Intuition:** Sort by end time. Greedily keep intervals that end earliest (activity selection problem).

| Complexity | Value |
|-----------|-------|
| Time | O(n log n) |
| Space | O(1) |

```java
class Solution {
    public int eraseOverlapIntervals(int[][] intervals) {
        // Sort by end time
        Arrays.sort(intervals, (a, b) -> a[1] - b[1]);
        int removals = 0;
        int prevEnd = Integer.MIN_VALUE;
        for (int[] interval : intervals) {
            if (interval[0] >= prevEnd) {
                // No overlap — keep this interval
                prevEnd = interval[1];
            } else {
                // Overlap — remove this interval (increment count)
                removals++;
            }
        }
        return removals;
    }
}
```

---

### 15.4 Meeting Rooms II

**Problem:** Given an array of meeting time intervals, find the minimum number of conference rooms required.

**Approach:** Use a min-heap to track the earliest ending meeting. For each new meeting, if it starts after the earliest ending, reuse that room; otherwise, add a new room.

| Complexity | Value |
|-----------|-------|
| Time | O(n log n) |
| Space | O(n) |

```java
class Solution {
    public int minMeetingRooms(int[][] intervals) {
        Arrays.sort(intervals, (a, b) -> a[0] - b[0]); // Sort by start time
        PriorityQueue<Integer> minHeap = new PriorityQueue<>(); // Tracks end times
        for (int[] interval : intervals) {
            // If earliest ending meeting is over before this one starts, reuse room
            if (!minHeap.isEmpty() && minHeap.peek() <= interval[0]) {
                minHeap.poll();
            }
            minHeap.offer(interval[1]); // Add current meeting's end time
        }
        return minHeap.size(); // Size = number of rooms needed
    }
}
```

---

# 16. Bit Manipulation

## Pattern Overview

Bit manipulation uses bitwise operators to solve problems efficiently. Common operators: `&` (AND), `|` (OR), `^` (XOR), `~` (NOT), `<<` (left shift), `>>` (right shift).

**Key properties:**
- `x ^ x = 0` — XOR of a number with itself is 0
- `x ^ 0 = x` — XOR with 0 is identity
- `x & (x - 1)` — clears the lowest set bit
- `x & (-x)` — isolates the lowest set bit

**How to recognize:**
- "Single number" (find unique element)
- "Count set bits"
- "Power of two"
- Problems where O(1) space is required and values are integers

**Companies:** Apple, Google, Microsoft, Amazon

---

### 16.1 Single Number

**Problem:** Given a non-empty array where every element appears twice except for one, find that single one.

| Complexity | Value |
|-----------|-------|
| Time | O(n) |
| Space | O(1) |

```java
class Solution {
    public int singleNumber(int[] nums) {
        int result = 0;
        for (int num : nums) {
            result ^= num; // XOR: pairs cancel out, leaving the single number
        }
        return result;
    }
}
```

---

### 16.2 Number of 1 Bits (Hamming Weight)

**Problem:** Return the number of `1` bits in the binary representation of an integer.

| Complexity | Value |
|-----------|-------|
| Time | O(k) where k = number of set bits |
| Space | O(1) |

```java
class Solution {
    public int hammingWeight(int n) {
        int count = 0;
        while (n != 0) {
            n &= (n - 1); // Clear the lowest set bit
            count++;
        }
        return count;
    }
}
```

---

### 16.3 Counting Bits

**Problem:** Given an integer `n`, return an array `ans` of length `n + 1` where `ans[i]` is the number of 1's in the binary representation of `i`.

**Intuition:** `countBits(i) = countBits(i >> 1) + (i & 1)`. The number of set bits of `i` equals the bits of `i/2` plus whether `i` is odd.

| Complexity | Value |
|-----------|-------|
| Time | O(n) |
| Space | O(n) |

```java
class Solution {
    public int[] countBits(int n) {
        int[] ans = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            ans[i] = ans[i >> 1] + (i & 1);
        }
        return ans;
    }
}
```

---

### 16.4 Reverse Bits

**Problem:** Reverse bits of a given 32 bits unsigned integer.

| Complexity | Value |
|-----------|-------|
| Time | O(1) — always 32 iterations |
| Space | O(1) |

```java
public class Solution {
    public int reverseBits(int n) {
        int result = 0;
        for (int i = 0; i < 32; i++) {
            result <<= 1;          // Make room for next bit
            result |= (n & 1);     // Add the last bit of n
            n >>= 1;               // Move to next bit of n
        }
        return result;
    }
}
```

---

### 16.5 Missing Number

**Problem:** Given an array `nums` containing `n` distinct numbers in the range `[0, n]`, find the missing number.

#### Approach — XOR

**Intuition:** XOR all indices `0..n` with all array values. Everything cancels except the missing number.

| Complexity | Value |
|-----------|-------|
| Time | O(n) |
| Space | O(1) |

```java
class Solution {
    public int missingNumber(int[] nums) {
        int xor = nums.length; // Start with n
        for (int i = 0; i < nums.length; i++) {
            xor ^= i ^ nums[i]; // XOR index and value
        }
        return xor;
    }
}
```

---

# 17. Math & Geometry

## Pattern Overview

Math problems test number theory, modular arithmetic, and geometric reasoning. They often have elegant O(1) or O(log n) solutions.

**Key concepts:**
- Modular arithmetic
- GCD / LCM (Euclidean algorithm)
- Sieve of Eratosthenes (primes)
- Matrix operations (rotation, spiral)
- Power / exponentiation

**Companies:** Google, Apple, Goldman Sachs, Amazon, Microsoft

---

### 17.1 Rotate Image

**Problem:** Rotate an `n × n` 2D matrix 90 degrees clockwise **in-place**.

**Approach:** Transpose the matrix, then reverse each row.

| Complexity | Value |
|-----------|-------|
| Time | O(n²) |
| Space | O(1) |

```java
class Solution {
    public void rotate(int[][] matrix) {
        int n = matrix.length;
        // Step 1: Transpose (swap matrix[i][j] with matrix[j][i])
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                int temp = matrix[i][j];
                matrix[i][j] = matrix[j][i];
                matrix[j][i] = temp;
            }
        }
        // Step 2: Reverse each row
        for (int i = 0; i < n; i++) {
            int left = 0, right = n - 1;
            while (left < right) {
                int temp = matrix[i][left];
                matrix[i][left] = matrix[i][right];
                matrix[i][right] = temp;
                left++;
                right--;
            }
        }
    }
}
```

---

### 17.2 Spiral Matrix

**Problem:** Given an `m × n` matrix, return all elements in spiral order.

| Complexity | Value |
|-----------|-------|
| Time | O(m × n) |
| Space | O(1) extra |

```java
class Solution {
    public List<Integer> spiralOrder(int[][] matrix) {
        List<Integer> result = new ArrayList<>();
        int top = 0, bottom = matrix.length - 1;
        int left = 0, right = matrix[0].length - 1;
        while (top <= bottom && left <= right) {
            // Traverse right
            for (int j = left; j <= right; j++) result.add(matrix[top][j]);
            top++;
            // Traverse down
            for (int i = top; i <= bottom; i++) result.add(matrix[i][right]);
            right--;
            // Traverse left (if rows remain)
            if (top <= bottom) {
                for (int j = right; j >= left; j--) result.add(matrix[bottom][j]);
                bottom--;
            }
            // Traverse up (if columns remain)
            if (left <= right) {
                for (int i = bottom; i >= top; i--) result.add(matrix[i][left]);
                left++;
            }
        }
        return result;
    }
}
```

---

### 17.3 Set Matrix Zeroes

**Problem:** Given an `m × n` matrix, if an element is 0, set its entire row and column to 0. Do it **in-place**.

**Approach:** Use the first row and first column as markers.

| Complexity | Value |
|-----------|-------|
| Time | O(m × n) |
| Space | O(1) |

```java
class Solution {
    public void setZeroes(int[][] matrix) {
        int m = matrix.length, n = matrix[0].length;
        boolean firstRowZero = false, firstColZero = false;
        // Check if first row/col need to be zeroed
        for (int j = 0; j < n; j++) if (matrix[0][j] == 0) firstRowZero = true;
        for (int i = 0; i < m; i++) if (matrix[i][0] == 0) firstColZero = true;
        // Use first row/col as markers for the rest
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                if (matrix[i][j] == 0) {
                    matrix[i][0] = 0;
                    matrix[0][j] = 0;
                }
            }
        }
        // Zero out cells based on markers
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                if (matrix[i][0] == 0 || matrix[0][j] == 0) {
                    matrix[i][j] = 0;
                }
            }
        }
        // Handle first row and column
        if (firstRowZero) for (int j = 0; j < n; j++) matrix[0][j] = 0;
        if (firstColZero) for (int i = 0; i < m; i++) matrix[i][0] = 0;
    }
}
```

---

### 17.4 Pow(x, n) — Fast Exponentiation

**Problem:** Implement `pow(x, n)`, calculating x raised to the power n.

#### Brute Force

| Complexity | Value |
|-----------|-------|
| Time | O(n) |
| Space | O(1) |

```java
class Solution {
    public double myPow(double x, int n) {
        long power = n;
        if (power < 0) { x = 1 / x; power = -power; }
        double result = 1.0;
        for (long i = 0; i < power; i++) {
            result *= x;
        }
        return result;
    }
}
```

#### Optimized — Binary Exponentiation

**Intuition:** `x^n = (x^(n/2))^2` if n is even, `x * (x^(n/2))^2` if n is odd.

| Complexity | Value |
|-----------|-------|
| Time | O(log n) |
| Space | O(1) |

```java
class Solution {
    public double myPow(double x, int n) {
        long power = n;
        if (power < 0) { x = 1 / x; power = -power; }
        double result = 1.0;
        while (power > 0) {
            if ((power & 1) == 1) { // Odd power
                result *= x;
            }
            x *= x;       // Square the base
            power >>= 1;  // Halve the exponent
        }
        return result;
    }
}
```

**Pitfalls:**
- Use `long` for power to handle `n = Integer.MIN_VALUE` (negating it overflows `int`).

---

# 📅 Study Plan & Timeline

## 8-Week Plan (Recommended)

| Week | Focus | Key Problems |
|------|-------|-------------|
| **1** | Arrays, Hashing, Two Pointers | Two Sum, Contains Duplicate, 3Sum, Valid Palindrome |
| **2** | Sliding Window, Binary Search | Best Time Buy/Sell Stock, Longest Substring, Search in Rotated Array |
| **3** | Strings, Stack & Queue | Valid Anagram, Valid Parentheses, Daily Temperatures, Min Stack |
| **4** | Linked List, Trees (basics) | Reverse LL, Merge Two Lists, Max Depth, Invert Tree, Level Order |
| **5** | Trees (advanced), Tries | Validate BST, LCA, Max Path Sum, Implement Trie, Word Search II |
| **6** | Graphs | Number of Islands, Course Schedule, Word Ladder, Pacific Atlantic |
| **7** | Dynamic Programming | Climbing Stairs, House Robber, Coin Change, LIS, LCS, Edit Distance |
| **8** | Greedy, Intervals, Heap, Bit Manipulation, Review | Jump Game, Merge Intervals, Kth Largest, Single Number |

## 4-Week Crash Plan

| Week | Focus |
|------|-------|
| **1** | Arrays, Hashing, Two Pointers, Sliding Window, Binary Search |
| **2** | Stack, Linked List, Trees, Graphs |
| **3** | Dynamic Programming, Backtracking |
| **4** | Greedy, Intervals, Heap, Trie, Bit Manipulation, Mock Interviews |

## Daily Practice Routine

1. **Warm-up (15 min):** Solve 1 easy problem from a familiar pattern.
2. **Deep practice (60-90 min):** Solve 1-2 medium problems from the week's focus pattern.
3. **Review (15 min):** Review one previously solved problem; optimize or rewrite from memory.
4. **Weekend:** Attempt 1 hard problem. Do a timed mock interview (45 min, 2 problems).

---

# 📊 Company-wise Pattern Frequency

| Pattern | Google | Meta | Amazon | Microsoft | Apple | Uber | Netflix |
|---------|--------|------|--------|-----------|-------|------|---------|
| Arrays & Hashing | ⭐⭐⭐ | ⭐⭐⭐ | ⭐⭐⭐ | ⭐⭐⭐ | ⭐⭐ | ⭐⭐ | ⭐⭐ |
| Two Pointers | ⭐⭐⭐ | ⭐⭐ | ⭐⭐ | ⭐⭐ | ⭐⭐⭐ | ⭐⭐ | ⭐ |
| Sliding Window | ⭐⭐⭐ | ⭐⭐ | ⭐⭐⭐ | ⭐⭐ | ⭐⭐ | ⭐⭐ | ⭐⭐⭐ |
| Binary Search | ⭐⭐⭐ | ⭐⭐⭐ | ⭐⭐ | ⭐⭐ | ⭐⭐ | ⭐⭐⭐ | ⭐⭐ |
| Stack & Queue | ⭐⭐ | ⭐⭐ | ⭐⭐⭐ | ⭐⭐⭐ | ⭐⭐ | ⭐ | ⭐ |
| Linked List | ⭐⭐ | ⭐⭐⭐ | ⭐⭐⭐ | ⭐⭐⭐ | ⭐⭐⭐ | ⭐ | ⭐ |
| Trees | ⭐⭐⭐ | ⭐⭐⭐ | ⭐⭐⭐ | ⭐⭐⭐ | ⭐⭐⭐ | ⭐⭐ | ⭐⭐ |
| Graphs | ⭐⭐⭐ | ⭐⭐⭐ | ⭐⭐ | ⭐⭐ | ⭐⭐ | ⭐⭐⭐ | ⭐⭐⭐ |
| Backtracking | ⭐⭐⭐ | ⭐⭐⭐ | ⭐⭐ | ⭐⭐ | ⭐⭐⭐ | ⭐⭐ | ⭐ |
| Dynamic Programming | ⭐⭐⭐ | ⭐⭐⭐ | ⭐⭐⭐ | ⭐⭐ | ⭐⭐⭐ | ⭐⭐⭐ | ⭐⭐ |
| Greedy | ⭐⭐⭐ | ⭐⭐ | ⭐⭐⭐ | ⭐⭐ | ⭐⭐ | ⭐⭐⭐ | ⭐⭐ |
| Heap / PQ | ⭐⭐ | ⭐⭐⭐ | ⭐⭐⭐ | ⭐⭐ | ⭐⭐ | ⭐⭐⭐ | ⭐⭐ |
| Trie | ⭐⭐⭐ | ⭐⭐ | ⭐⭐⭐ | ⭐⭐⭐ | ⭐ | ⭐ | ⭐ |
| Intervals | ⭐⭐⭐ | ⭐⭐⭐ | ⭐⭐ | ⭐⭐ | ⭐⭐ | ⭐⭐⭐ | ⭐⭐ |
| Bit Manipulation | ⭐⭐ | ⭐ | ⭐⭐ | ⭐⭐⭐ | ⭐⭐⭐ | ⭐ | ⭐ |

*⭐ = Occasionally asked, ⭐⭐ = Frequently asked, ⭐⭐⭐ = Very frequently asked*

---

# 🏆 General Interview Tips

## Before the Interview

- **Clarify constraints:** Array size, value range, sorted/unsorted, duplicates allowed?
- **Ask about edge cases:** Empty input, single element, all same elements, negative numbers.
- **Confirm the expected output format:** Return value, print, modify in-place?
- **Discuss time/space complexity expectations** before coding.

## During the Interview

### Step-by-Step Framework (UMPIRE)

1. **U**nderstand: Restate the problem. Ask clarifying questions.
2. **M**atch: Identify which pattern(s) apply.
3. **P**lan: Describe your approach before coding. Start with brute force, then optimize.
4. **I**mplement: Write clean, modular code. Name variables descriptively.
5. **R**eview: Walk through your code with a test case. Check edge cases.
6. **E**valuate: State time and space complexity.

### Communication Tips

- **Think out loud.** Interviewers want to see your thought process.
- **Start with the brute force.** Even if you know the optimal, mentioning brute force shows completeness.
- **Trade-off discussion.** "We could use more space to reduce time from O(n²) to O(n)."
- **Don't stay silent.** If stuck, explain what you're thinking and where you're stuck.

## Common Pitfalls to Avoid

| Pitfall | How to Avoid |
|---------|-------------|
| Integer overflow | Use `long` for products/sums; use `left + (right - left) / 2` for midpoint |
| Off-by-one errors | Carefully decide `<=` vs `<`, `i + 1` vs `i` |
| Null pointer exceptions | Always check `null` before accessing `.next`, `.left`, `.right` |
| Modifying collection while iterating | Use an index-based loop or create a copy |
| Forgetting to handle empty input | Add explicit checks at the start |
| Not restoring state in backtracking | Always undo changes after recursive call |
| Comparing `Integer` objects with `==` | Use `.equals()` or `.intValue()` in Java |
| Hardcoding array size | Use `.length` or `.size()` dynamically |

## Pattern Recognition Cheat Sheet

| If the problem says... | Think about... |
|------------------------|---------------|
| "Sorted array" | Binary Search, Two Pointers |
| "All permutations/combinations/subsets" | Backtracking |
| "Find minimum/maximum path/cost" | DP or Greedy |
| "Top/bottom K elements" | Heap |
| "Common prefix/search words" | Trie |
| "Connected components, shortest path" | BFS / DFS / Union-Find |
| "Intervals, schedules" | Sort + Greedy or Merge |
| "Subarray/Substring with property" | Sliding Window |
| "In-place, O(1) space on integers" | Bit Manipulation |
| "Tree traversal" | DFS (recursive) or BFS (iterative) |
| "Design a data structure" | Combine HashMap + LinkedList, or Heap, etc. |
| "Optimal substructure + overlapping subproblems" | Dynamic Programming |

## Java-Specific Tips for Interviews

- **Use `Deque<>` (ArrayDeque) instead of `Stack<>`** — the legacy `Stack` class is synchronized and slow.
- **`PriorityQueue` is min-heap by default.** For max-heap: `new PriorityQueue<>(Collections.reverseOrder())`.
- **`HashMap.getOrDefault(key, defaultValue)`** saves null checks.
- **`Arrays.sort()` uses dual-pivot quicksort for primitives (O(n log n) average), TimSort for objects (O(n log n) guaranteed).**
- **`StringBuilder`** for string concatenation in loops — never use `+=` on strings in a loop.
- **Integer cache:** Java caches `Integer` values `-128` to `127`. Use `.equals()` for comparison, not `==`.
- **`Map.computeIfAbsent(key, k -> new ArrayList<>())`** is cleaner than manual null checks.
- **`int mid = left + (right - left) / 2`** avoids integer overflow (vs `(left + right) / 2`).

---

## 📚 Recommended Practice Platforms

| Platform | Best For |
|----------|---------|
| **LeetCode** | Comprehensive problem bank, company tags, contests |
| **NeetCode.io** | Curated lists organized by pattern (NeetCode 150) |
| **AlgoExpert** | Video explanations, structured curriculum |
| **HackerRank** | Timed contests, company-specific challenges |
| **Codeforces** | Competitive programming, advanced algorithms |

---

## Quick Reference — All Problems in This Guide

| # | Problem | Pattern | Difficulty |
|---|---------|---------|-----------|
| 1.1 | Two Sum | Arrays & Hashing | Easy |
| 1.2 | Contains Duplicate | Arrays & Hashing | Easy |
| 1.3 | Group Anagrams | Arrays & Hashing | Medium |
| 1.4 | Top K Frequent Elements | Arrays & Hashing | Medium |
| 1.5 | Product of Array Except Self | Arrays & Hashing | Medium |
| 2.1 | Valid Palindrome | Two Pointers | Easy |
| 2.2 | Three Sum | Two Pointers | Medium |
| 2.3 | Container With Most Water | Two Pointers | Medium |
| 2.4 | Trapping Rain Water | Two Pointers | Hard |
| 3.1 | Best Time to Buy and Sell Stock | Sliding Window | Easy |
| 3.2 | Longest Substring Without Repeating Characters | Sliding Window | Medium |
| 3.3 | Minimum Window Substring | Sliding Window | Hard |
| 4.1 | Valid Anagram | Strings | Easy |
| 4.2 | Longest Palindromic Substring | Strings | Medium |
| 4.3 | Longest Common Prefix | Strings | Easy |
| 5.1 | Binary Search | Binary Search | Easy |
| 5.2 | Search in Rotated Sorted Array | Binary Search | Medium |
| 5.3 | Find Minimum in Rotated Sorted Array | Binary Search | Medium |
| 5.4 | Koko Eating Bananas | Binary Search | Medium |
| 6.1 | Valid Parentheses | Stack | Easy |
| 6.2 | Daily Temperatures | Stack | Medium |
| 6.3 | Min Stack | Stack | Medium |
| 7.1 | Reverse Linked List | Linked List | Easy |
| 7.2 | Linked List Cycle | Linked List | Easy |
| 7.3 | Merge Two Sorted Lists | Linked List | Easy |
| 7.4 | Remove Nth Node From End | Linked List | Medium |
| 7.5 | LRU Cache | Linked List | Medium |
| 8.1 | Subsets | Backtracking | Medium |
| 8.2 | Permutations | Backtracking | Medium |
| 8.3 | Combination Sum | Backtracking | Medium |
| 8.4 | Word Search | Backtracking | Medium |
| 8.5 | N-Queens | Backtracking | Hard |
| 9.1 | Maximum Depth of Binary Tree | Trees | Easy |
| 9.2 | Invert Binary Tree | Trees | Easy |
| 9.3 | Binary Tree Level Order Traversal | Trees | Medium |
| 9.4 | Validate Binary Search Tree | Trees | Medium |
| 9.5 | Lowest Common Ancestor | Trees | Medium |
| 9.6 | Binary Tree Maximum Path Sum | Trees | Hard |
| 9.7 | Serialize and Deserialize Binary Tree | Trees | Hard |
| 10.1 | Number of Islands | Graphs | Medium |
| 10.2 | Clone Graph | Graphs | Medium |
| 10.3 | Course Schedule | Graphs | Medium |
| 10.4 | Pacific Atlantic Water Flow | Graphs | Medium |
| 10.5 | Graph Valid Tree | Graphs | Medium |
| 10.6 | Word Ladder | Graphs | Hard |
| 11.1 | Kth Largest Element | Heap | Medium |
| 11.2 | Merge K Sorted Lists | Heap | Hard |
| 11.3 | Find Median from Data Stream | Heap | Hard |
| 11.4 | Task Scheduler | Heap | Medium |
| 12.1 | Implement Trie | Trie | Medium |
| 12.2 | Word Search II | Trie | Hard |
| 13.1 | Climbing Stairs | DP | Easy |
| 13.2 | House Robber | DP | Medium |
| 13.3 | Coin Change | DP | Medium |
| 13.4 | Longest Increasing Subsequence | DP | Medium |
| 13.5 | Longest Common Subsequence | DP | Medium |
| 13.6 | Word Break | DP | Medium |
| 13.7 | Partition Equal Subset Sum | DP | Medium |
| 13.8 | Edit Distance | DP | Medium |
| 13.9 | Unique Paths | DP | Medium |
| 13.10 | Decode Ways | DP | Medium |
| 14.1 | Jump Game | Greedy | Medium |
| 14.2 | Jump Game II | Greedy | Medium |
| 14.3 | Gas Station | Greedy | Medium |
| 14.4 | Hand of Straights | Greedy | Medium |
| 15.1 | Merge Intervals | Intervals | Medium |
| 15.2 | Insert Interval | Intervals | Medium |
| 15.3 | Non-Overlapping Intervals | Intervals | Medium |
| 15.4 | Meeting Rooms II | Intervals | Medium |
| 16.1 | Single Number | Bit Manipulation | Easy |
| 16.2 | Number of 1 Bits | Bit Manipulation | Easy |
| 16.3 | Counting Bits | Bit Manipulation | Easy |
| 16.4 | Reverse Bits | Bit Manipulation | Easy |
| 16.5 | Missing Number | Bit Manipulation | Easy |
| 17.1 | Rotate Image | Math & Geometry | Medium |
| 17.2 | Spiral Matrix | Math & Geometry | Medium |
| 17.3 | Set Matrix Zeroes | Math & Geometry | Medium |
| 17.4 | Pow(x, n) | Math & Geometry | Medium |

---

> **Total: 70+ problems across 17 patterns — covering the most frequently asked DSA interview questions at top tech companies.**
>
> *Good luck with your interviews! Consistency beats intensity. Solve 2-3 problems daily, understand the patterns, and you will be well-prepared.*
