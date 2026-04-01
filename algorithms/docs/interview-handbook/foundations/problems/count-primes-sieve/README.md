# Count Primes (Sieve of Eratosthenes)

## 1. Problem Title
Count Primes (Sieve of Eratosthenes)

## 2. Difficulty Level (Easy / Medium / Hard)
Medium

## 3. Problem Statement
Given an integer n, return the number of prime numbers strictly less than n.

## 4. Input Format
n: integer upper bound.

## 5. Output Format
Return count of primes in range [0, n).

## 6. Constraints (very important for interviews)
- 0 <= n <= 5 * 10^6

## 7. Example Inputs and Outputs
Example 1
```text
Input: n = 10
Output: 4
Explanation: Primes are 2,3,5,7.
```

## 8. Edge Cases
- n <= 2 gives 0.
- Large n requires optimized marking start i*i.
- Need to avoid overflow in i*i for large i.

## 9. Brute Force Approach Explanation
Check primality for each number from 2 to n-1 by trial division up to sqrt(k).

## 10. Optimal Approach Explanation
Use sieve: mark multiples of each unmarked number starting from i*i. Unmarked numbers are primes.

## 11. Step-by-Step Logic
1. Create boolean array isPrime initialized true for [2..n-1].
2. For each i up to sqrt(n), if prime, mark multiples i*i, i*i+i, ... as non-prime.
3. Count remaining true values.

## 12. Time Complexity Analysis
O(n log log n).

## 13. Space Complexity Analysis
O(n).

## 14. Clean and Production-Quality Java Code
```java
class Solution {
    public int countPrimes(int n) {
        if (n <= 2) {
            return 0;
        }

        boolean[] isPrime = new boolean[n];
        for (int i = 2; i < n; i++) {
            isPrime[i] = true;
        }

        for (int i = 2; (long) i * i < n; i++) {
            if (!isPrime[i]) {
                continue;
            }
            for (int j = i * i; j < n; j += i) {
                isPrime[j] = false;
            }
        }

        int count = 0;
        for (int i = 2; i < n; i++) {
            if (isPrime[i]) {
                count++;
            }
        }
        return count;
    }
}
```

## 15. Dry Run Example
n=10: mark multiples of 2 -> 4,6,8; of 3 -> 9. Remaining primes: 2,3,5,7.

## 16. Common Interview Follow-Up Questions
- How can you reduce memory for very large n?
- Can you handle multiple range prime queries?

## 17. Alternative Solutions if available
- Segmented sieve for large intervals.
- Trial division baseline for small n.

## 18. Real Interview Context (why companies ask this problem)
This checks mathematical optimization and ability to move from naive primality checks to precomputation.
