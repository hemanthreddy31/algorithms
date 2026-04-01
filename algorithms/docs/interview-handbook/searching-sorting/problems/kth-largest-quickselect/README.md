# Kth Largest Element in an Array (Quickselect)

## 1. Problem Title
Kth Largest Element in an Array (Quickselect)

## 2. Difficulty Level (Easy / Medium / Hard)
Medium

## 3. Problem Statement
Given an integer array nums and an integer k, return the kth largest element in the array.

## 4. Input Format
nums: integer array, k: integer (1-indexed rank from largest).

## 5. Output Format
Return kth largest element value.

## 6. Constraints (very important for interviews)
- 1 <= k <= nums.length <= 10^5
- -10^4 <= nums[i] <= 10^4

## 7. Example Inputs and Outputs
Example 1
```text
Input: nums=[3,2,1,5,6,4], k=2
Output: 5
Explanation: 2nd largest element is 5.
```

## 8. Edge Cases
- k = 1 (max element).
- k = n (min element).
- Duplicate values.

## 9. Brute Force Approach Explanation
Sort descending and return index k-1. O(n log n).

## 10. Optimal Approach Explanation
Quickselect partitions around pivot and recurses only into one side, giving average O(n).

## 11. Step-by-Step Logic
1. Convert kth largest to target index n-k in ascending order.
2. Partition array around pivot.
3. Narrow search range based on pivot index.

## 12. Time Complexity Analysis
Average O(n), worst O(n^2) without randomization.

## 13. Space Complexity Analysis
O(1) iterative partitioning.

## 14. Clean and Production-Quality Java Code
```java
import java.util.*;

class Solution {
    private final Random random = new Random();

    public int findKthLargest(int[] nums, int k) {
        int target = nums.length - k;
        int lo = 0;
        int hi = nums.length - 1;

        while (lo <= hi) {
            int pivotIndex = lo + random.nextInt(hi - lo + 1);
            int p = partition(nums, lo, hi, pivotIndex);
            if (p == target) {
                return nums[p];
            } else if (p < target) {
                lo = p + 1;
            } else {
                hi = p - 1;
            }
        }

        return -1;
    }

    private int partition(int[] nums, int lo, int hi, int pivotIndex) {
        int pivot = nums[pivotIndex];
        swap(nums, pivotIndex, hi);
        int store = lo;
        for (int i = lo; i < hi; i++) {
            if (nums[i] < pivot) {
                swap(nums, store++, i);
            }
        }
        swap(nums, store, hi);
        return store;
    }

    private void swap(int[] nums, int i, int j) {
        int t = nums[i];
        nums[i] = nums[j];
        nums[j] = t;
    }
}
```

## 15. Dry Run Example
nums=[3,2,1,5,6,4], k=2 => target index 4. Partition narrows until pivot index 4 with value 5.

## 16. Common Interview Follow-Up Questions
- How to guarantee O(n) worst-case?
- How to find kth smallest in stream?

## 17. Alternative Solutions if available
- Min-heap of size k: O(n log k).
- Full sort O(n log n).

## 18. Real Interview Context (why companies ask this problem)
This checks order-statistics thinking and in-place partition mechanics.
