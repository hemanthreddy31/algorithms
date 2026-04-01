# Power of Two

## 1. Problem Title
Power of Two

## 2. Difficulty Level (Easy / Medium / Hard)
Easy

## 3. Problem Statement
Given an integer n, return true if n is a power of two. Otherwise return false.

## 4. Input Format
n: signed 32-bit integer.

## 5. Output Format
Return true if n = 2^k for some integer k >= 0; otherwise false.

## 6. Constraints (very important for interviews)
- -2^31 <= n <= 2^31 - 1

## 7. Example Inputs and Outputs
Example 1
```text
Input: n = 16
Output: true
Explanation: 16 = 2^4.
```
Example 2
```text
Input: n = 18
Output: false
Explanation: Not a pure power of two.
```

## 8. Edge Cases
- n <= 0 should return false.
- n = 1 should return true.
- Largest positive int boundary.

## 9. Brute Force Approach Explanation
Repeatedly divide n by 2 while divisible; at the end check if n becomes 1.

## 10. Optimal Approach Explanation
For positive n, power of two numbers have exactly one set bit. So n & (n - 1) equals 0 only for powers of two.

## 11. Step-by-Step Logic
1. If n <= 0, return false.
2. Compute n & (n - 1).
3. Return whether result is 0.

## 12. Time Complexity Analysis
O(1).

## 13. Space Complexity Analysis
O(1).

## 14. Clean and Production-Quality Java Code
```java
class Solution {
    public boolean isPowerOfTwo(int n) {
        return n > 0 && (n & (n - 1)) == 0;
    }
}
```

## 15. Dry Run Example
n=16 (10000b), n-1=01111b, AND=0 -> true.

## 16. Common Interview Follow-Up Questions
- How do you check power of four?
- How would this change for 64-bit numbers?

## 17. Alternative Solutions if available
- Loop division by 2 until odd.
- Use logarithms with precision guard (not preferred).

## 18. Real Interview Context (why companies ask this problem)
This question checks whether the candidate recognizes a canonical bit pattern quickly.
