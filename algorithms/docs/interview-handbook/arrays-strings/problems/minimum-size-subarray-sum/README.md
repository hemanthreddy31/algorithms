# Minimum Size Subarray Sum

## 1. Problem Title
Minimum Size Subarray Sum

## 2. Difficulty Level (Easy / Medium / Hard)
Medium

## 3. Problem Statement
Given an array of positive integers nums and a positive integer target, return the minimal length of a contiguous subarray of which the sum is at least target. Return 0 if no such subarray exists.

## 4. Input Format
target: integer, nums: positive integer array.

## 5. Output Format
Return minimum valid window length as integer.

## 6. Constraints (very important for interviews)
- 1 <= target <= 10^9
- 1 <= nums.length <= 10^5
- 1 <= nums[i] <= 10^4

## 7. Example Inputs and Outputs
Example 1
```text
Input: target=7, nums=[2,3,1,2,4,3]
Output: 2
Explanation: [4,3] has sum 7 with minimum length 2.
```

## 8. Edge Cases
- No valid subarray => return 0.
- Single element already >= target.
- Entire array required.

## 9. Brute Force Approach Explanation
Try every start index and expand until sum>=target, track minimum length. This is O(n^2).

## 10. Optimal Approach Explanation
Because all numbers are positive, use sliding window with two pointers and shrink while sum >= target.

## 11. Step-by-Step Logic
1. Expand right pointer and add nums[right].
2. While window sum >= target, update answer and shrink from left.
3. If answer not updated, return 0.

## 12. Time Complexity Analysis
O(n), each pointer moves at most n times.

## 13. Space Complexity Analysis
O(1).

## 14. Clean and Production-Quality Java Code
```java
class Solution {
    public int minSubArrayLen(int target, int[] nums) {
        int left = 0;
        int sum = 0;
        int best = Integer.MAX_VALUE;

        for (int right = 0; right < nums.length; right++) {
            sum += nums[right];
            while (sum >= target) {
                best = Math.min(best, right - left + 1);
                sum -= nums[left++];
            }
        }

        return best == Integer.MAX_VALUE ? 0 : best;
    }
}
```

## 15. Dry Run Example
target=7: window grows to [2,3,1,2] sum=8, shrink to [3,1,2] len=3 then [1,2] sum=3; later [4,3] gives len=2.

## 16. Common Interview Follow-Up Questions
- How would this change if numbers could be negative?
- Can you return the subarray indices too?

## 17. Alternative Solutions if available
- Prefix sum + binary search on each start (O(n log n)).

## 18. Real Interview Context (why companies ask this problem)
Sliding-window fundamentals are heavily tested in array/string rounds.
