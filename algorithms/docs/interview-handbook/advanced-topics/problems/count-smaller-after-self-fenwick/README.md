# Count of Smaller Numbers After Self (Fenwick Tree)

## 1. Problem Title
Count of Smaller Numbers After Self (Fenwick Tree)

## 2. Difficulty Level (Easy / Medium / Hard)
Hard

## 3. Problem Statement
Given integer array nums, return array counts where counts[i] is number of smaller elements to the right of nums[i].

## 4. Input Format
nums: integer array.

## 5. Output Format
Return integer list counts.

## 6. Constraints (very important for interviews)
- 1 <= nums.length <= 10^5
- -10^4 <= nums[i] <= 10^4

## 7. Example Inputs and Outputs
Example 1
```text
Input: nums=[5,2,6,1]
Output: [2,1,1,0]
Explanation: To right of 5, two numbers are smaller: 2 and 1.
```

## 8. Edge Cases
- All equal elements.
- Strictly increasing or decreasing arrays.
- Negative values require coordinate compression.

## 9. Brute Force Approach Explanation
For each i, scan j>i and count nums[j] < nums[i]. O(n^2).

## 10. Optimal Approach Explanation
Process from right to left. Fenwick tree tracks frequencies of seen values with coordinate compression.

## 11. Step-by-Step Logic
1. Coordinate-compress values to ranks 1..m.
2. Traverse nums from right.
3. Query Fenwick prefix(rank-1) for smaller count, then add current rank.

## 12. Time Complexity Analysis
O(n log n).

## 13. Space Complexity Analysis
O(n).

## 14. Clean and Production-Quality Java Code
```java
import java.util.*;

class Solution {
    public List<Integer> countSmaller(int[] nums) {
        int[] sorted = nums.clone();
        Arrays.sort(sorted);

        Map<Integer, Integer> rank = new HashMap<>();
        int r = 1;
        for (int val : sorted) {
            if (!rank.containsKey(val)) {
                rank.put(val, r++);
            }
        }

        Fenwick bit = new Fenwick(r + 2);
        Integer[] ans = new Integer[nums.length];

        for (int i = nums.length - 1; i >= 0; i--) {
            int idx = rank.get(nums[i]);
            ans[i] = bit.query(idx - 1);
            bit.add(idx, 1);
        }

        return Arrays.asList(ans);
    }

    private static class Fenwick {
        int[] bit;

        Fenwick(int n) {
            bit = new int[n + 1];
        }

        void add(int i, int delta) {
            while (i < bit.length) {
                bit[i] += delta;
                i += i & -i;
            }
        }

        int query(int i) {
            int sum = 0;
            while (i > 0) {
                sum += bit[i];
                i -= i & -i;
            }
            return sum;
        }
    }
}
```

## 15. Dry Run Example
From right: 1->count0, add1; 6->count1, add6; 2->count1; 5->count2.

## 16. Common Interview Follow-Up Questions
- How to solve using merge sort counting inversion style?
- How to handle online updates?

## 17. Alternative Solutions if available
- Merge sort based counting O(n log n).
- Balanced BST with order statistics.

## 18. Real Interview Context (why companies ask this problem)
Advanced frequency-query pattern with Fenwick and compression.
