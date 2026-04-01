# Burst Balloons (Partition DP)

## 1. Problem Title
Burst Balloons (Partition DP)

## 2. Difficulty Level (Easy / Medium / Hard)
Hard

## 3. Problem Statement
Given n balloons with numbers, each burst gains nums[left]*nums[i]*nums[right]. Return maximum coins collectable by bursting all balloons optimally.

## 4. Input Format
nums: integer array.

## 5. Output Format
Return maximum coins as integer.

## 6. Constraints (very important for interviews)
- 1 <= nums.length <= 300
- 0 <= nums[i] <= 100

## 7. Example Inputs and Outputs
Example 1
```text
Input: nums=[3,1,5,8]
Output: 167
Explanation: Best order yields 167.
```

## 8. Edge Cases
- Zero-valued balloons.
- Single balloon.
- Large n requiring O(n^3) DP.

## 9. Brute Force Approach Explanation
Try all burst orders recursively (n! possibilities).

## 10. Optimal Approach Explanation
Interval DP: choose last balloon to burst in interval (l,r).

## 11. Step-by-Step Logic
1. Pad nums with 1 at both ends.
2. dp[l][r] stores max coins for open interval (l,r).
3. For each interval length, try every k in (l,r) as last burst.

## 12. Time Complexity Analysis
O(n^3).

## 13. Space Complexity Analysis
O(n^2).

## 14. Clean and Production-Quality Java Code
```java
class Solution {
    public int maxCoins(int[] nums) {
        int n = nums.length;
        int[] arr = new int[n + 2];
        arr[0] = 1;
        arr[n + 1] = 1;
        for (int i = 0; i < n; i++) {
            arr[i + 1] = nums[i];
        }

        int[][] dp = new int[n + 2][n + 2];

        for (int len = 2; len < n + 2; len++) {
            for (int left = 0; left + len < n + 2; left++) {
                int right = left + len;
                for (int k = left + 1; k < right; k++) {
                    int gain = arr[left] * arr[k] * arr[right];
                    dp[left][right] = Math.max(dp[left][right], dp[left][k] + gain + dp[k][right]);
                }
            }
        }

        return dp[0][n + 1];
    }
}
```

## 15. Dry Run Example
For interval (0,5) in padded array [1,3,1,5,8,1], try each last burst and keep max 167.

## 16. Common Interview Follow-Up Questions
- How to derive recurrence formally?
- Can this be optimized below O(n^3)?

## 17. Alternative Solutions if available
- Top-down memoized interval recursion.

## 18. Real Interview Context (why companies ask this problem)
Classic partition-DP hard problem in top-tier interviews.
