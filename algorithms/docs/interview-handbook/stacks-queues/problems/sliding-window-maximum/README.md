# Sliding Window Maximum

## 1. Problem Title
Sliding Window Maximum

## 2. Difficulty Level (Easy / Medium / Hard)
Hard

## 3. Problem Statement
Given an array nums and window size k, return max value in each sliding window of size k.

## 4. Input Format
nums: integer array, k: positive integer.

## 5. Output Format
Return integer array of window maxima.

## 6. Constraints (very important for interviews)
- 1 <= nums.length <= 10^5
- -10^4 <= nums[i] <= 10^4
- 1 <= k <= nums.length

## 7. Example Inputs and Outputs
Example 1
```text
Input: nums=[1,3,-1,-3,5,3,6,7], k=3
Output: [3,3,5,5,6,7]
Explanation: Deque keeps candidate indices in decreasing value order.
```

## 8. Edge Cases
- k = 1 returns original array.
- k = n returns single maximum.
- Duplicate values.

## 9. Brute Force Approach Explanation
Compute max for each window by scanning k elements => O(nk).

## 10. Optimal Approach Explanation
Use deque of indices maintaining decreasing values. Front is current window maximum.

## 11. Step-by-Step Logic
1. Remove indices outside current window from deque front.
2. Pop from back while current value >= deque back value.
3. Push current index; once i >= k-1, record nums[deque front].

## 12. Time Complexity Analysis
O(n).

## 13. Space Complexity Analysis
O(k).

## 14. Clean and Production-Quality Java Code
```java
import java.util.*;

class Solution {
    public int[] maxSlidingWindow(int[] nums, int k) {
        int n = nums.length;
        int[] ans = new int[n - k + 1];
        Deque<Integer> dq = new ArrayDeque<>();

        for (int i = 0; i < n; i++) {
            while (!dq.isEmpty() && dq.peekFirst() <= i - k) {
                dq.pollFirst();
            }
            while (!dq.isEmpty() && nums[dq.peekLast()] <= nums[i]) {
                dq.pollLast();
            }
            dq.offerLast(i);

            if (i >= k - 1) {
                ans[i - k + 1] = nums[dq.peekFirst()];
            }
        }

        return ans;
    }
}
```

## 15. Dry Run Example
For first window [1,3,-1], deque stores indices [1,2], max=3. As window shifts, outdated indices are removed.

## 16. Common Interview Follow-Up Questions
- How to support dynamic window sizes?
- Can you do this with priority queue and lazy deletion?

## 17. Alternative Solutions if available
- Max-heap with stale index removal O(n log k).
- Block decomposition (left/right maxima arrays).

## 18. Real Interview Context (why companies ask this problem)
Advanced queue problem that distinguishes strong candidates in linear-time optimization.
