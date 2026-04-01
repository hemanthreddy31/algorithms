# Find First and Last Position of Element in Sorted Array

## 1. Problem Title
Find First and Last Position of Element in Sorted Array

## 2. Difficulty Level (Easy / Medium / Hard)
Medium

## 3. Problem Statement
Given a sorted array nums and target, return the starting and ending position of target. If not found, return [-1, -1].

## 4. Input Format
nums: sorted integer array (may contain duplicates), target: integer.

## 5. Output Format
Return int array of size 2: [firstIndex, lastIndex].

## 6. Constraints (very important for interviews)
- 0 <= nums.length <= 10^5
- -10^9 <= nums[i], target <= 10^9

## 7. Example Inputs and Outputs
Example 1
```text
Input: nums=[5,7,7,8,8,10], target=8
Output: [3,4]
Explanation: 8 appears from index 3 to 4.
```

## 8. Edge Cases
- Empty array.
- Single occurrence.
- All elements equal target.

## 9. Brute Force Approach Explanation
Scan from left to right and track first/last. O(n).

## 10. Optimal Approach Explanation
Use two binary searches: lower bound for first index, upper bound for last index.

## 11. Step-by-Step Logic
1. Binary search leftmost index where value >= target.
2. Binary search leftmost index where value > target.
3. Derive last as upperBound-1 and validate existence.

## 12. Time Complexity Analysis
O(log n).

## 13. Space Complexity Analysis
O(1).

## 14. Clean and Production-Quality Java Code
```java
class Solution {
    public int[] searchRange(int[] nums, int target) {
        int first = lowerBound(nums, target);
        if (first == nums.length || nums[first] != target) {
            return new int[] {-1, -1};
        }
        int last = lowerBound(nums, target + 1) - 1;
        return new int[] {first, last};
    }

    private int lowerBound(int[] nums, int target) {
        int lo = 0;
        int hi = nums.length;
        while (lo < hi) {
            int mid = lo + (hi - lo) / 2;
            if (nums[mid] < target) {
                lo = mid + 1;
            } else {
                hi = mid;
            }
        }
        return lo;
    }
}
```

## 15. Dry Run Example
target=8 => lowerBound(8)=3, lowerBound(9)=5 => last=4.

## 16. Common Interview Follow-Up Questions
- Can you do it recursively?
- How would you solve for floating-point sorted arrays?

## 17. Alternative Solutions if available
- One binary search for match then expand both sides (O(n) worst case).

## 18. Real Interview Context (why companies ask this problem)
This evaluates mastery of lower/upper bound templates with duplicates.
