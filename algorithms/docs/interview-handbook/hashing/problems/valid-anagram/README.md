# Valid Anagram

## 1. Problem Title
Valid Anagram

## 2. Difficulty Level (Easy / Medium / Hard)
Easy

## 3. Problem Statement
Given two strings s and t, return true if t is an anagram of s and false otherwise.

## 4. Input Format
s and t: lowercase strings.

## 5. Output Format
Return boolean result.

## 6. Constraints (very important for interviews)
- 1 <= s.length, t.length <= 5 * 10^4
- s and t consist of lowercase English letters.

## 7. Example Inputs and Outputs
Example 1
```text
Input: s='anagram', t='nagaram'
Output: true
Explanation: Both contain same letter frequencies.
```

## 8. Edge Cases
- Different lengths.
- Repeated characters.
- Unicode variation in follow-up.

## 9. Brute Force Approach Explanation
Sort both strings and compare. O(n log n).

## 10. Optimal Approach Explanation
Count character frequencies using fixed array of size 26 and compare counts.

## 11. Step-by-Step Logic
1. If lengths differ, return false.
2. Increment count for chars in s and decrement for chars in t.
3. If any count non-zero, return false; else true.

## 12. Time Complexity Analysis
O(n).

## 13. Space Complexity Analysis
O(1) for fixed alphabet.

## 14. Clean and Production-Quality Java Code
```java
class Solution {
    public boolean isAnagram(String s, String t) {
        if (s.length() != t.length()) {
            return false;
        }

        int[] freq = new int[26];
        for (int i = 0; i < s.length(); i++) {
            freq[s.charAt(i) - 'a']++;
            freq[t.charAt(i) - 'a']--;
        }

        for (int count : freq) {
            if (count != 0) {
                return false;
            }
        }
        return true;
    }
}
```

## 15. Dry Run Example
s='ab', t='ba': freq after scan returns all zeros -> true.

## 16. Common Interview Follow-Up Questions
- How would you handle Unicode input efficiently?
- Can this be done for streaming characters?

## 17. Alternative Solutions if available
- Sort and compare.
- HashMap frequency when alphabet is large.

## 18. Real Interview Context (why companies ask this problem)
Tests frequency counting and constant-space reasoning.
