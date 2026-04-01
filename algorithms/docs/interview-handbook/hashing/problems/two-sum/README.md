# Two Sum

## 1. Problem Title
Two Sum

## 2. Difficulty Level (Easy / Medium / Hard)
Easy

## 3. Problem Statement
Given an array of integers nums and an integer target, return indices of the two numbers such that they add up to target.

## 4. Input Format
nums: integer array, target: integer.

## 5. Output Format
Return int array of size 2 containing valid indices.

## 6. Constraints (very important for interviews)
- 2 <= nums.length <= 10^4
- -10^9 <= nums[i], target <= 10^9
- Exactly one valid answer exists.

## 7. Example Inputs and Outputs
Example 1
```text
Input: nums=[2,7,11,15], target=9
Output: [0,1]
Explanation: nums[0] + nums[1] = 9.
```

## 8. Edge Cases
- Negative numbers.
- Duplicate values used as different indices.
- Answer appears near end.

## 9. Brute Force Approach Explanation
Check every pair i<j and return when nums[i]+nums[j]==target. O(n^2).

## 10. Optimal Approach Explanation
Store seen value->index in HashMap. For each num, check whether target-num already exists.

## 11. Step-by-Step Logic
1. Initialize empty map.
2. For each index i, compute complement=target-nums[i].
3. If complement exists in map, return [map[complement], i], otherwise store nums[i].

## 12. Time Complexity Analysis
O(n).

## 13. Space Complexity Analysis
O(n).

## 14. Clean and Production-Quality Java Code
```java
import java.util.*;

class Solution {
    public int[] twoSum(int[] nums, int target) {
        Map<Integer, Integer> seen = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            int need = target - nums[i];
            if (seen.containsKey(need)) {
                return new int[] {seen.get(need), i};
            }
            seen.put(nums[i], i);
        }
        return new int[0];
    }
}
```

## 15. Dry Run Example
i=0 val=2 -> store {2:0}; i=1 val=7, need=2 found -> return [0,1].

## 16. Common Interview Follow-Up Questions
- How would you solve if array is already sorted?
- How do you return all unique pairs?

## 17. Alternative Solutions if available
- Sort + two pointers with original indices.

## 18. Real Interview Context (why companies ask this problem)
Classic map lookup pattern question used in almost all screening loops.
