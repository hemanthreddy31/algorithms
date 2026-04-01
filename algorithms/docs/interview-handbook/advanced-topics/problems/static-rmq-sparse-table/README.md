# Static Range Minimum Query (Sparse Table)

## 1. Problem Title
Static Range Minimum Query (Sparse Table)

## 2. Difficulty Level (Easy / Medium / Hard)
Medium

## 3. Problem Statement
Given immutable array nums and multiple queries [l, r], return minimum value in each range.

## 4. Input Format
nums: integer array; queries: pairs of 0-based indices l, r.

## 5. Output Format
For each query return minimum in nums[l..r].

## 6. Constraints (very important for interviews)
- 1 <= nums.length <= 2 * 10^5
- 1 <= queries.length <= 2 * 10^5
- 0 <= l <= r < nums.length

## 7. Example Inputs and Outputs
Example 1
```text
Input: nums=[4,6,1,5,7,3], query(1,4)
Output: 1
Explanation: Minimum in [6,1,5,7] is 1.
```

## 8. Edge Cases
- Query length 1.
- Full array query.
- Many repeated queries.

## 9. Brute Force Approach Explanation
Scan each range for every query. O(n) per query.

## 10. Optimal Approach Explanation
Sparse table precomputes range minima for power-of-two lengths; RMQ answered in O(1).

## 11. Step-by-Step Logic
1. Build log table for lengths.
2. Build st[k][i] = min for interval length 2^k starting at i.
3. Query uses two overlapping blocks of length 2^k covering [l,r].

## 12. Time Complexity Analysis
Build O(n log n), each query O(1).

## 13. Space Complexity Analysis
O(n log n).

## 14. Clean and Production-Quality Java Code
```java
import java.util.*;

class SparseTableRMQ {
    private final int[][] st;
    private final int[] log2;

    public SparseTableRMQ(int[] nums) {
        int n = nums.length;
        log2 = new int[n + 1];
        for (int i = 2; i <= n; i++) {
            log2[i] = log2[i / 2] + 1;
        }

        int maxK = log2[n] + 1;
        st = new int[maxK][n];
        System.arraycopy(nums, 0, st[0], 0, n);

        for (int k = 1; k < maxK; k++) {
            int len = 1 << k;
            for (int i = 0; i + len <= n; i++) {
                st[k][i] = Math.min(st[k - 1][i], st[k - 1][i + (len >> 1)]);
            }
        }
    }

    public int queryMin(int l, int r) {
        int len = r - l + 1;
        int k = log2[len];
        return Math.min(st[k][l], st[k][r - (1 << k) + 1]);
    }
}
```

## 15. Dry Run Example
For [1,4], len=4 => k=2, compare st[2][1] and st[2][1], result 1.

## 16. Common Interview Follow-Up Questions
- Why does sparse table require idempotent operations for O(1) query?
- How to support updates?

## 17. Alternative Solutions if available
- Segment tree for mutable queries.

## 18. Real Interview Context (why companies ask this problem)
Senior-level preprocessing vs query-time tradeoff question.
