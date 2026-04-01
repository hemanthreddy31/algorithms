# Pow(x, n)

## 1. Problem Title
Pow(x, n)

## 2. Difficulty Level (Easy / Medium / Hard)
Medium

## 3. Problem Statement
Implement x raised to power n (x^n), where n can be negative.

## 4. Input Format
x: double, n: signed 32-bit integer.

## 5. Output Format
Return x^n as a double.

## 6. Constraints (very important for interviews)
- -100.0 < x < 100.0
- -2^31 <= n <= 2^31 - 1
- Either x != 0 or n > 0

## 7. Example Inputs and Outputs
Example 1
```text
Input: x = 2.0, n = 10
Output: 1024.0
Explanation: 2 multiplied 10 times.
```
Example 2
```text
Input: x = 2.0, n = -2
Output: 0.25
Explanation: 1 / (2^2).
```

## 8. Edge Cases
- n is Integer.MIN_VALUE (overflow risk for abs).
- x = 1 or -1.
- Negative exponent.

## 9. Brute Force Approach Explanation
Multiply x by itself |n| times, then invert if n is negative. This is O(|n|).

## 10. Optimal Approach Explanation
Use fast exponentiation: x^n = (x^(n/2))^2 when n is even, and x * x^(n-1) when odd. Process bits of exponent in O(log n).

## 11. Step-by-Step Logic
1. Promote n to long to safely handle Integer.MIN_VALUE.
2. If exponent is negative, invert base and make exponent positive.
3. Binary exponentiation: square base every step and multiply answer when bit is set.

## 12. Time Complexity Analysis
O(log |n|).

## 13. Space Complexity Analysis
O(1) iterative implementation.

## 14. Clean and Production-Quality Java Code
```java
class Solution {
    public double myPow(double x, int n) {
        long exp = n;
        if (exp < 0) {
            x = 1.0 / x;
            exp = -exp;
        }

        double result = 1.0;
        while (exp > 0) {
            if ((exp & 1L) == 1L) {
                result *= x;
            }
            x *= x;
            exp >>= 1;
        }
        return result;
    }
}
```

## 15. Dry Run Example
x=2,n=10: result=1, exp bits 1010 -> multiply at set bits after squaring chain -> 1024.

## 16. Common Interview Follow-Up Questions
- Can you write the recursive version?
- How do you avoid precision issues for very large exponents?

## 17. Alternative Solutions if available
- Recursive divide-and-conquer with O(log n) stack depth.
- Math library call (not expected in interview).

## 18. Real Interview Context (why companies ask this problem)
Interviewers test divide-and-conquer reasoning and careful handling of integer edge cases.
