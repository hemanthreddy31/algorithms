# Climbing Stairs

## 1. Problem Title
Climbing Stairs

## 2. Difficulty Level (Easy / Medium / Hard)
Easy

## 3. Problem Statement
You are climbing a staircase. It takes n steps to reach the top. Each time you can climb 1 or 2 steps. Return number of distinct ways.

## 4. Input Format
n: integer number of steps.

## 5. Output Format
Return count of distinct ways as integer.

## 6. Constraints (very important for interviews)
- 1 <= n <= 45

## 7. Example Inputs and Outputs
Example 1
```text
Input: n=3
Output: 3
Explanation: Ways: [1+1+1, 1+2, 2+1].
```

## 8. Edge Cases
- n = 1.
- n = 2.
- Upper bound near int limit for this constraint.

## 9. Brute Force Approach Explanation
Recursive choose 1 or 2 steps from each state -> exponential O(2^n).

## 10. Optimal Approach Explanation
DP relation ways[i] = ways[i-1] + ways[i-2] (Fibonacci pattern).

## 11. Step-by-Step Logic
1. Handle n <= 2 directly.
2. Iteratively build from step 3 to n using two previous values.
3. Return current value.

## 12. Time Complexity Analysis
O(n).

## 13. Space Complexity Analysis
O(1).

## 14. Clean and Production-Quality Java Code
```java
class Solution {
    public int climbStairs(int n) {
        if (n <= 2) {
            return n;
        }

        int prev2 = 1;
        int prev1 = 2;
        for (int i = 3; i <= n; i++) {
            int curr = prev1 + prev2;
            prev2 = prev1;
            prev1 = curr;
        }
        return prev1;
    }
}
```

## 15. Dry Run Example
n=5: 1,2,3,5,8 -> answer 8.

## 16. Common Interview Follow-Up Questions
- How if you can take up to k steps each time?
- How if some steps are blocked?

## 17. Alternative Solutions if available
- Top-down memoization.
- Matrix exponentiation for Fibonacci.

## 18. Real Interview Context (why companies ask this problem)
Classic 1D DP state-transition warmup.
