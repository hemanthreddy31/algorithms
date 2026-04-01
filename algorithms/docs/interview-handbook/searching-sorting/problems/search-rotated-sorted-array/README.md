# Search in Rotated Sorted Array

## 1. Problem Title
Search in Rotated Sorted Array

## 2. Difficulty Level (Easy / Medium / Hard)
Medium

## 3. Problem Statement
An integer array nums is sorted in ascending order and then rotated. Given nums and target, return index of target or -1.

## 4. Input Format
nums: rotated sorted integer array with unique values, target: integer.

## 5. Output Format
Return target index or -1.

## 6. Constraints (very important for interviews)
- 1 <= nums.length <= 5000
- -10^4 <= nums[i], target <= 10^4
- All nums values are unique.

## 7. Example Inputs and Outputs
Example 1
```text
Input: nums=[4,5,6,7,0,1,2], target=0
Output: 4
Explanation: Target is in right sorted half.
```

## 8. Edge Cases
- Array not rotated.
- Target in left sorted half.
- Target absent.

## 9. Brute Force Approach Explanation
Linear scan to find target index. O(n).

## 10. Optimal Approach Explanation
Modified binary search: one side is always sorted. Decide whether target lies in sorted side and move accordingly.

## 11. Step-by-Step Logic
1. Pick mid.
2. Check whether left half [lo..mid] is sorted or right half [mid..hi] is sorted.
3. If target lies in sorted half, shrink there; otherwise move to other half.

## 12. Time Complexity Analysis
O(log n).

## 13. Space Complexity Analysis
O(1).

## 14. Clean and Production-Quality Java Code
```java
class Solution {
    public int search(int[] nums, int target) {
        int lo = 0;
        int hi = nums.length - 1;

        while (lo <= hi) {
            int mid = lo + (hi - lo) / 2;
            if (nums[mid] == target) {
                return mid;
            }

            if (nums[lo] <= nums[mid]) {
                if (nums[lo] <= target && target < nums[mid]) {
                    hi = mid - 1;
                } else {
                    lo = mid + 1;
                }
            } else {
                if (nums[mid] < target && target <= nums[hi]) {
                    lo = mid + 1;
                } else {
                    hi = mid - 1;
                }
            }
        }

        return -1;
    }
}
```

## 15. Dry Run Example
nums=[4,5,6,7,0,1,2], target=0: mid=3(7), left sorted but target not there => lo=4, then mid=5(1), target in left => hi=4, mid=4 hit.

## 16. Common Interview Follow-Up Questions
- How do duplicates change the logic?
- How to find rotation pivot first then binary search?

## 17. Alternative Solutions if available
- Find pivot in O(log n), then two binary searches.

## 18. Real Interview Context (why companies ask this problem)
This tests binary search under non-trivial monotonic structure.
