# Binary Search

## 1. Problem Title
Binary Search

## 2. Difficulty Level (Easy / Medium / Hard)
Easy

## 3. Problem Statement
Given a sorted integer array nums and an integer target, return the index of target if it exists, otherwise return -1.

## 4. Input Format
nums: sorted integer array in ascending order, target: integer.

## 5. Output Format
Return target index or -1.

## 6. Constraints (very important for interviews)
- 1 <= nums.length <= 10^4
- -10^4 <= nums[i], target <= 10^4
- All nums values are unique.

## 7. Example Inputs and Outputs
Example 1
```text
Input: nums=[-1,0,3,5,9,12], target=9
Output: 4
Explanation: Target found at index 4.
```

## 8. Edge Cases
- Target smaller than first element.
- Target greater than last element.
- Single element array.

## 9. Brute Force Approach Explanation
Linearly scan the array and compare each element. O(n).

## 10. Optimal Approach Explanation
Use binary search on sorted array and halve the search space each step.

## 11. Step-by-Step Logic
1. Set lo=0 and hi=n-1.
2. Compute mid safely as lo + (hi-lo)/2.
3. Adjust boundaries based on comparison until found or interval empty.

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
            if (nums[mid] < target) {
                lo = mid + 1;
            } else {
                hi = mid - 1;
            }
        }
        return -1;
    }
}
```

## 15. Dry Run Example
nums=[1,3,5,7], target=5 -> mid=1 val=3, move right; mid=2 val=5, return 2.

## 16. Common Interview Follow-Up Questions
- How do you return insertion position instead?
- How do you find first or last occurrence with duplicates?

## 17. Alternative Solutions if available
- Recursive binary search.

## 18. Real Interview Context (why companies ask this problem)
This is a baseline correctness test for loop invariants and boundary control.
