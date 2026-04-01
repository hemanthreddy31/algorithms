# Maximum XOR of Two Numbers in an Array

## 1. Problem Title
Maximum XOR of Two Numbers in an Array

## 2. Difficulty Level (Easy / Medium / Hard)
Medium

## 3. Problem Statement
Given integer array nums, return maximum value of nums[i] XOR nums[j] for 0 <= i <= j < n.

## 4. Input Format
nums: non-negative integer array.

## 5. Output Format
Return maximum XOR integer value.

## 6. Constraints (very important for interviews)
- 1 <= nums.length <= 2 * 10^5
- 0 <= nums[i] <= 2^31 - 1

## 7. Example Inputs and Outputs
Example 1
```text
Input: nums=[3,10,5,25,2,8]
Output: 28
Explanation: 5 XOR 25 = 28.
```

## 8. Edge Cases
- Single element array.
- All equal values.
- Large integer bits.

## 9. Brute Force Approach Explanation
Try all pairs and compute XOR. O(n^2).

## 10. Optimal Approach Explanation
Build bitwise trie of numbers. For each number, greedily choose opposite bit at each level to maximize XOR.

## 11. Step-by-Step Logic
1. Insert each number into binary trie from bit 31 to 0.
2. For each number, traverse trie preferring opposite bits.
3. Compute best XOR and track global max.

## 12. Time Complexity Analysis
O(32*n) = O(n).

## 13. Space Complexity Analysis
O(32*n).

## 14. Clean and Production-Quality Java Code
```java
class Solution {
    private static class Node {
        Node zero;
        Node one;
    }

    public int findMaximumXOR(int[] nums) {
        Node root = new Node();
        for (int num : nums) {
            insert(root, num);
        }

        int best = 0;
        for (int num : nums) {
            best = Math.max(best, query(root, num));
        }
        return best;
    }

    private void insert(Node root, int num) {
        Node curr = root;
        for (int bit = 31; bit >= 0; bit--) {
            int b = (num >> bit) & 1;
            if (b == 0) {
                if (curr.zero == null) {
                    curr.zero = new Node();
                }
                curr = curr.zero;
            } else {
                if (curr.one == null) {
                    curr.one = new Node();
                }
                curr = curr.one;
            }
        }
    }

    private int query(Node root, int num) {
        Node curr = root;
        int value = 0;
        for (int bit = 31; bit >= 0; bit--) {
            int b = (num >> bit) & 1;
            if (b == 0) {
                if (curr.one != null) {
                    value |= (1 << bit);
                    curr = curr.one;
                } else {
                    curr = curr.zero;
                }
            } else {
                if (curr.zero != null) {
                    value |= (1 << bit);
                    curr = curr.zero;
                } else {
                    curr = curr.one;
                }
            }
        }
        return value;
    }
}
```

## 15. Dry Run Example
For num=5 (00101), trie path against 25 (11001) creates XOR bits 11100 => 28.

## 16. Common Interview Follow-Up Questions
- How to answer max XOR for each query with dynamic inserts?
- How does this compare to prefix-hash bitmask approach?

## 17. Alternative Solutions if available
- Bitmask + greedy set approach (also O(32n)).

## 18. Real Interview Context (why companies ask this problem)
Tests advanced trie application over binary representation, common in high-bar rounds.
