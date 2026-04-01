# Longest Increasing Subsequence

## 1. Problem Title
Longest Increasing Subsequence

## 2. Difficulty Level (Easy / Medium / Hard)
Medium

## 3. Problem Statement
Given integer array nums, return length of the longest strictly increasing subsequence.

## 4. Input Format
nums: integer array.

## 5. Output Format
Return LIS length as integer.

## 6. Constraints (very important for interviews)
- 1 <= nums.length <= 2500
- -10^4 <= nums[i] <= 10^4

## 7. Example Inputs and Outputs
Example 1
```text
Input: nums=[10,9,2,5,3,7,101,18]
Output: 4
Explanation: One LIS is [2,3,7,101].
```

## 8. Edge Cases
- Strictly decreasing array -> 1.
- All equal values -> 1.
- Long increasing run.

## 9. Brute Force Approach Explanation
Generate all subsequences and check increasing property. Exponential.

## 10. Optimal Approach Explanation
Patience sorting idea: maintain tails array; binary search replace first >= current.

## 11. Step-by-Step Logic
1. Initialize empty tails list.
2. For each num, binary search insertion index in tails.
3. Replace tails[idx] or append if idx at end.

## 12. Time Complexity Analysis
O(n log n).

## 13. Space Complexity Analysis
O(n).

## 14. Clean and Production-Quality Java Code
```java
import java.util.*;

class Solution {
    public int lengthOfLIS(int[] nums) {
        int[] tails = new int[nums.length];
        int size = 0;

        for (int num : nums) {
            int lo = 0, hi = size;
            while (lo < hi) {
                int mid = lo + (hi - lo) / 2;
                if (tails[mid] < num) {
                    lo = mid + 1;
                } else {
                    hi = mid;
                }
            }
            tails[lo] = num;
            if (lo == size) {
                size++;
            }
        }

        return size;
    }
}
```

## 15. Dry Run Example
nums 10,9,2,5,3,7 -> tails evolves [10],[9],[2],[2,5],[2,3],[2,3,7].

## 16. Common Interview Follow-Up Questions
- Can you reconstruct one LIS sequence?
- How to handle non-decreasing subsequence variant?

## 17. Alternative Solutions if available
- O(n^2) DP: dp[i]=max LIS ending at i.

## 18. Real Interview Context (why companies ask this problem)
Frequent medium-hard DP question emphasizing optimization from O(n^2) to O(n log n).
