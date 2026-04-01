# Rabin-Karp Substring Search (Rolling Hash)

## 1. Problem Title
Rabin-Karp Substring Search (Rolling Hash)

## 2. Difficulty Level (Easy / Medium / Hard)
Medium

## 3. Problem Statement
Given text and pattern strings, return the first index where pattern appears in text using rolling hash, or -1 if not found.

## 4. Input Format
text: string, pattern: string.

## 5. Output Format
Return zero-based first match index or -1.

## 6. Constraints (very important for interviews)
- 1 <= text.length, pattern.length <= 10^5
- Strings contain lowercase English letters.

## 7. Example Inputs and Outputs
Example 1
```text
Input: text='abracadabra', pattern='cada'
Output: 4
Explanation: Rolling hash windows match at index 4, then verified by substring compare.
```

## 8. Edge Cases
- Pattern longer than text.
- Multiple hash collisions possible.
- Repeated character text.

## 9. Brute Force Approach Explanation
Compare pattern against every window character-by-character. O((n-m+1) * m).

## 10. Optimal Approach Explanation
Use polynomial rolling hash for O(1) window hash updates and verify only on hash match.

## 11. Step-by-Step Logic
1. Precompute base powers and initial hashes for first window and pattern.
2. Slide window by removing outgoing char and adding incoming char with modular arithmetic.
3. When hashes match, verify actual substring to avoid collision errors.

## 12. Time Complexity Analysis
Average O(n + m), worst-case O(n*m) with heavy collisions.

## 13. Space Complexity Analysis
O(1) extra (excluding power precompute if done on fly).

## 14. Clean and Production-Quality Java Code
```java
class Solution {
    public int rabinKarp(String text, String pattern) {
        int n = text.length();
        int m = pattern.length();
        if (m > n) {
            return -1;
        }

        long mod = 1_000_000_007L;
        long base = 911382323L;
        long power = 1L;

        long patternHash = 0L;
        long windowHash = 0L;
        for (int i = 0; i < m; i++) {
            patternHash = (patternHash * base + pattern.charAt(i)) % mod;
            windowHash = (windowHash * base + text.charAt(i)) % mod;
            if (i > 0) {
                power = (power * base) % mod;
            }
        }

        for (int i = 0; i <= n - m; i++) {
            if (windowHash == patternHash && text.regionMatches(i, pattern, 0, m)) {
                return i;
            }
            if (i < n - m) {
                long out = (text.charAt(i) * power) % mod;
                windowHash = (windowHash - out + mod) % mod;
                windowHash = (windowHash * base + text.charAt(i + m)) % mod;
            }
        }

        return -1;
    }
}
```

## 15. Dry Run Example
Window 'abra' hash differs, slide until window 'cada' hash equals pattern hash, then verify and return index 4.

## 16. Common Interview Follow-Up Questions
- How to return all match indices instead of first?
- How to reduce collision probability further?

## 17. Alternative Solutions if available
- KMP deterministic O(n+m).
- Z-algorithm pattern matching.

## 18. Real Interview Context (why companies ask this problem)
Interviewers use this to test rolling-hash design and collision-aware implementation.
