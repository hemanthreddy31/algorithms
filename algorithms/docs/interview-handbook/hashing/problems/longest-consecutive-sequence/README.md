# Longest Consecutive Sequence

## 1. Problem Title
Longest Consecutive Sequence

## 2. Difficulty Level (Easy / Medium / Hard)
Medium

## 3. Problem Statement
Given an unsorted array of integers nums, return the length of the longest consecutive elements sequence.

## 4. Input Format
nums: integer array.

## 5. Output Format
Return integer length.

## 6. Constraints (very important for interviews)
- 0 <= nums.length <= 10^5
- -10^9 <= nums[i] <= 10^9

## 7. Example Inputs and Outputs
Example 1
```text
Input: nums=[100,4,200,1,3,2]
Output: 4
Explanation: Sequence 1,2,3,4 has length 4.
```

## 8. Edge Cases
- Empty array.
- All duplicates.
- Negative ranges.

## 9. Brute Force Approach Explanation
Sort and scan consecutive run lengths. O(n log n).

## 10. Optimal Approach Explanation
Store values in HashSet. Start counting only from numbers with no predecessor (x-1 not in set).

## 11. Step-by-Step Logic
1. Insert all numbers into set.
2. For each x, if x-1 exists skip because not sequence start.
3. Grow x+1, x+2... and track max length.

## 12. Time Complexity Analysis
O(n) expected.

## 13. Space Complexity Analysis
O(n).

## 14. Clean and Production-Quality Java Code
```java
import java.util.*;

class Solution {
    public int longestConsecutive(int[] nums) {
        Set<Integer> set = new HashSet<>();
        for (int num : nums) {
            set.add(num);
        }

        int best = 0;
        for (int num : set) {
            if (set.contains(num - 1)) {
                continue;
            }
            int current = num;
            int len = 1;
            while (set.contains(current + 1)) {
                current++;
                len++;
            }
            best = Math.max(best, len);
        }
        return best;
    }
}
```

## 15. Dry Run Example
Starts at 1 (since 0 absent), extends to 2,3,4 => length 4.

## 16. Common Interview Follow-Up Questions
- Can you return the actual sequence too?
- How would this work in streaming data?

## 17. Alternative Solutions if available
- Sort then linear scan.
- Union-Find on values (heavier).

## 18. Real Interview Context (why companies ask this problem)
Interviewers check if candidate can use hashing to break sorting lower bound for this objective.
