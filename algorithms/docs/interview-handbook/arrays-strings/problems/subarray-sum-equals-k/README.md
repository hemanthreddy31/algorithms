# Subarray Sum Equals K

## 1. Problem Title
Subarray Sum Equals K

## 2. Difficulty Level (Easy / Medium / Hard)
Medium

## 3. Problem Statement
Given an integer array nums and an integer k, return the total number of continuous subarrays whose sum equals k.

## 4. Input Format
nums: integer array (can include negatives), k: integer target.

## 5. Output Format
Return count of subarrays with sum exactly k.

## 6. Constraints (very important for interviews)
- 1 <= nums.length <= 2 * 10^4
- -1000 <= nums[i] <= 1000
- -10^7 <= k <= 10^7

## 7. Example Inputs and Outputs
Example 1
```text
Input: nums=[1,1,1], k=2
Output: 2
Explanation: Subarrays [1,1] at indices (0,1) and (1,2).
```

## 8. Edge Cases
- Negative numbers break sliding-window monotonicity.
- k = 0 with multiple zero sums.
- Single element equals k.

## 9. Brute Force Approach Explanation
Compute all subarray sums using nested loops and count matches; O(n^2).

## 10. Optimal Approach Explanation
Use prefix sum + HashMap frequency. If current prefix is p, previous prefix p-k indicates a subarray ending here with sum k.

## 11. Step-by-Step Logic
1. Initialize map with {0:1} to count subarrays starting at index 0.
2. Accumulate running prefix sum.
3. Add map[prefix-k] to answer, then increment map[prefix].

## 12. Time Complexity Analysis
O(n).

## 13. Space Complexity Analysis
O(n) in worst case for prefix map.

## 14. Clean and Production-Quality Java Code
```java
import java.util.*;

class Solution {
    public int subarraySum(int[] nums, int k) {
        Map<Integer, Integer> freq = new HashMap<>();
        freq.put(0, 1);

        int prefix = 0;
        int count = 0;
        for (int num : nums) {
            prefix += num;
            count += freq.getOrDefault(prefix - k, 0);
            freq.put(prefix, freq.getOrDefault(prefix, 0) + 1);
        }
        return count;
    }
}
```

## 15. Dry Run Example
nums=[1,2,3],k=3: prefix 1->map[ -2]=0, prefix 3->map[0]=1 count=1, prefix 6->map[3]=1 count=2.

## 16. Common Interview Follow-Up Questions
- How do you return longest subarray with sum k?
- How does this differ from subarray sum divisible by k?

## 17. Alternative Solutions if available
- Nested loops with prefix array O(n^2).

## 18. Real Interview Context (why companies ask this problem)
It tests whether candidates can apply prefix-hash counting when negatives invalidate sliding window.
