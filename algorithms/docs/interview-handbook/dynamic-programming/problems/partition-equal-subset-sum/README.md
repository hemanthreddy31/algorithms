# Partition Equal Subset Sum

## 1. Problem Title
Partition Equal Subset Sum

## 2. Difficulty Level (Easy / Medium / Hard)
Medium

## 3. Problem Statement
Given a non-empty array nums containing positive integers, determine if it can be partitioned into two subsets with equal sum.

## 4. Input Format
nums: positive integer array.

## 5. Output Format
Return true if equal partition exists else false.

## 6. Constraints (very important for interviews)
- 1 <= nums.length <= 200
- 1 <= nums[i] <= 100

## 7. Example Inputs and Outputs
Example 1
```text
Input: nums=[1,5,11,5]
Output: true
Explanation: Can split into [1,5,5] and [11].
```

## 8. Edge Cases
- Total sum odd => impossible.
- Single element.
- Many small numbers.

## 9. Brute Force Approach Explanation
Try assigning each number to one of two sets recursively; O(2^n).

## 10. Optimal Approach Explanation
Transform to subset sum target=sum/2 with 0/1 knapsack DP.

## 11. Step-by-Step Logic
1. Compute total sum, return false if odd.
2. Set target = sum/2.
3. Use boolean dp[target+1], iterate nums and update backwards.

## 12. Time Complexity Analysis
O(n * target).

## 13. Space Complexity Analysis
O(target).

## 14. Clean and Production-Quality Java Code
```java
class Solution {
    public boolean canPartition(int[] nums) {
        int sum = 0;
        for (int num : nums) {
            sum += num;
        }
        if ((sum & 1) == 1) {
            return false;
        }

        int target = sum / 2;
        boolean[] dp = new boolean[target + 1];
        dp[0] = true;

        for (int num : nums) {
            for (int s = target; s >= num; s--) {
                dp[s] = dp[s] || dp[s - num];
            }
        }

        return dp[target];
    }
}
```

## 15. Dry Run Example
target=11: after processing 1,5,11,5, dp[11] becomes true.

## 16. Common Interview Follow-Up Questions
- Can you return actual subsets too?
- How does this relate to 0/1 knapsack?

## 17. Alternative Solutions if available
- Top-down memoization over (index, remaining).

## 18. Real Interview Context (why companies ask this problem)
Tests reduction to knapsack and 1D DP space optimization.
