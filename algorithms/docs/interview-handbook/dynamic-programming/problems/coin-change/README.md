# Coin Change

## 1. Problem Title
Coin Change

## 2. Difficulty Level (Easy / Medium / Hard)
Medium

## 3. Problem Statement
Given coin denominations and amount, return fewest coins needed to make amount, or -1 if impossible.

## 4. Input Format
coins: integer array, amount: integer.

## 5. Output Format
Return minimum coin count or -1.

## 6. Constraints (very important for interviews)
- 1 <= coins.length <= 12
- 1 <= coins[i] <= 2^31 - 1
- 0 <= amount <= 10^4

## 7. Example Inputs and Outputs
Example 1
```text
Input: coins=[1,2,5], amount=11
Output: 3
Explanation: 11 = 5 + 5 + 1.
```

## 8. Edge Cases
- amount = 0 => 0.
- No combination possible.
- Large amount with small coins.

## 9. Brute Force Approach Explanation
Try all combinations recursively; exponential.

## 10. Optimal Approach Explanation
Unbounded knapsack DP where dp[a] is min coins for amount a.

## 11. Step-by-Step Logic
1. Initialize dp[0]=0 and others INF.
2. For each amount from 1..target, try each coin if coin<=amount.
3. Transition dp[a]=min(dp[a], dp[a-coin]+1).

## 12. Time Complexity Analysis
O(amount * number_of_coins).

## 13. Space Complexity Analysis
O(amount).

## 14. Clean and Production-Quality Java Code
```java
import java.util.*;

class Solution {
    public int coinChange(int[] coins, int amount) {
        int inf = amount + 1;
        int[] dp = new int[amount + 1];
        Arrays.fill(dp, inf);
        dp[0] = 0;

        for (int a = 1; a <= amount; a++) {
            for (int coin : coins) {
                if (coin <= a) {
                    dp[a] = Math.min(dp[a], dp[a - coin] + 1);
                }
            }
        }

        return dp[amount] == inf ? -1 : dp[amount];
    }
}
```

## 15. Dry Run Example
amount 11 with [1,2,5]: dp[5]=1, dp[10]=2, dp[11]=3.

## 16. Common Interview Follow-Up Questions
- How to count number of combinations instead of minimum coins?
- How to output one optimal combination?

## 17. Alternative Solutions if available
- BFS over amounts.
- Top-down memoization.

## 18. Real Interview Context (why companies ask this problem)
Knapsack-style DP with optimization objective.
