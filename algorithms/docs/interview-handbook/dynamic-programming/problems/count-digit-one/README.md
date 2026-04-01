# Count Digit One (Digit DP)

## 1. Problem Title
Count Digit One (Digit DP)

## 2. Difficulty Level (Easy / Medium / Hard)
Hard

## 3. Problem Statement
Given an integer n, count the total number of digit '1' appearing in all non-negative integers less than or equal to n.

## 4. Input Format
n: non-negative integer.

## 5. Output Format
Return total count of digit '1'.

## 6. Constraints (very important for interviews)
- 0 <= n <= 10^9

## 7. Example Inputs and Outputs
Example 1
```text
Input: n=13
Output: 6
Explanation: 1 appears in 1,10,11,12,13 (6 total times).
```

## 8. Edge Cases
- n = 0 => 0.
- Powers of 10 boundaries.
- Large n with many digits.

## 9. Brute Force Approach Explanation
Iterate from 0 to n and count ones in each number. Too slow for large n.

## 10. Optimal Approach Explanation
Digit DP with tight bound and count accumulation by positions.

## 11. Step-by-Step Logic
1. Convert n to digit array.
2. DFS over digit index with states (idx, tight, onesSoFar).
3. Memoize non-tight states to avoid recomputation.

## 12. Time Complexity Analysis
O(d * 10 * d) where d is number of digits (small, <=10).

## 13. Space Complexity Analysis
O(d * d * 2) for memo table.

## 14. Clean and Production-Quality Java Code
```java
import java.util.*;

class Solution {
    private char[] digits;
    private Integer[][][] memo;

    public int countDigitOne(int n) {
        if (n == 0) {
            return 0;
        }
        digits = String.valueOf(n).toCharArray();
        memo = new Integer[digits.length][digits.length + 1][2];
        return dfs(0, 0, 1);
    }

    private int dfs(int idx, int ones, int tight) {
        if (idx == digits.length) {
            return ones;
        }
        if (memo[idx][ones][tight] != null) {
            return memo[idx][ones][tight];
        }

        int limit = tight == 1 ? digits[idx] - '0' : 9;
        int total = 0;
        for (int dig = 0; dig <= limit; dig++) {
            int nextTight = (tight == 1 && dig == limit) ? 1 : 0;
            total += dfs(idx + 1, ones + (dig == 1 ? 1 : 0), nextTight);
        }

        memo[idx][ones][tight] = total;
        return total;
    }
}
```

## 15. Dry Run Example
n=13: digit DP enumerates 00..13 under tight constraint and accumulates count of placed digit 1.

## 16. Common Interview Follow-Up Questions
- How to count occurrences of any digit d?
- How to count numbers with no repeated digits?

## 17. Alternative Solutions if available
- Mathematical place-value counting formula O(log n).

## 18. Real Interview Context (why companies ask this problem)
Advanced DP topic testing state design under numeric bounds.
