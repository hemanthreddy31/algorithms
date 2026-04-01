# Find the Index of the First Occurrence in a String (KMP)

## 1. Problem Title
Find the Index of the First Occurrence in a String (KMP)

## 2. Difficulty Level (Easy / Medium / Hard)
Medium

## 3. Problem Statement
Given haystack and needle strings, return the index of the first occurrence of needle in haystack, or -1 if needle is not part of haystack.

## 4. Input Format
haystack: string, needle: string.

## 5. Output Format
Return zero-based index or -1.

## 6. Constraints (very important for interviews)
- 1 <= haystack.length, needle.length <= 10^4
- Strings contain lowercase English letters.

## 7. Example Inputs and Outputs
Example 1
```text
Input: haystack="sadbutsad", needle="sad"
Output: 0
Explanation: First match starts at index 0.
```
Example 2
```text
Input: haystack="leetcode", needle="leeto"
Output: -1
Explanation: No full match.
```

## 8. Edge Cases
- Needle longer than haystack.
- Repeated prefix patterns in needle.
- Match occurs at the end.

## 9. Brute Force Approach Explanation
Try each start index and compare characters one by one. Worst-case O(n*m).

## 10. Optimal Approach Explanation
KMP builds LPS array (longest proper prefix suffix) to avoid rechecking characters and achieves O(n+m).

## 11. Step-by-Step Logic
1. Build LPS for needle.
2. Scan haystack with two pointers i (text) and j (pattern).
3. On mismatch, jump j to LPS[j-1] instead of restarting from zero.

## 12. Time Complexity Analysis
O(n + m).

## 13. Space Complexity Analysis
O(m) for LPS.

## 14. Clean and Production-Quality Java Code
```java
class Solution {
    public int strStr(String haystack, String needle) {
        if (needle.isEmpty()) {
            return 0;
        }

        int[] lps = buildLps(needle);
        int i = 0;
        int j = 0;

        while (i < haystack.length()) {
            if (haystack.charAt(i) == needle.charAt(j)) {
                i++;
                j++;
                if (j == needle.length()) {
                    return i - j;
                }
            } else if (j > 0) {
                j = lps[j - 1];
            } else {
                i++;
            }
        }

        return -1;
    }

    private int[] buildLps(String p) {
        int[] lps = new int[p.length()];
        int len = 0;
        for (int i = 1; i < p.length(); ) {
            if (p.charAt(i) == p.charAt(len)) {
                lps[i++] = ++len;
            } else if (len > 0) {
                len = lps[len - 1];
            } else {
                lps[i++] = 0;
            }
        }
        return lps;
    }
}
```

## 15. Dry Run Example
haystack='aaaaab', needle='aaab'. LPS enables jump from j=3 to j=2, avoiding full restart and finding index 2.

## 16. Common Interview Follow-Up Questions
- How would Rabin-Karp compare here?
- Can you find all occurrences instead of first?

## 17. Alternative Solutions if available
- Rabin-Karp rolling hash average O(n+m).
- Naive scan O(n*m).

## 18. Real Interview Context (why companies ask this problem)
This tests deeper string-pattern matching fundamentals beyond standard library calls.
