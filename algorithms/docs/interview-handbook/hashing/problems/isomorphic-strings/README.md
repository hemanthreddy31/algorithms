# Isomorphic Strings

## 1. Problem Title
Isomorphic Strings

## 2. Difficulty Level (Easy / Medium / Hard)
Easy

## 3. Problem Statement
Given strings s and t, determine if they are isomorphic. Characters in s can be replaced to get t with a one-to-one mapping.

## 4. Input Format
s and t: strings of equal length.

## 5. Output Format
Return true if one-to-one mapping exists, else false.

## 6. Constraints (very important for interviews)
- 1 <= s.length <= 5 * 10^4
- s.length == t.length
- s and t consist of valid ASCII chars.

## 7. Example Inputs and Outputs
Example 1
```text
Input: s='egg', t='add'
Output: true
Explanation: e->a and g->d works consistently.
```
Example 2
```text
Input: s='foo', t='bar'
Output: false
Explanation: o maps to two different chars.
```

## 8. Edge Cases
- Repeated characters requiring same mapping.
- Two chars in s mapping to same char in t (invalid).
- Single-character strings.

## 9. Brute Force Approach Explanation
Build mapping and validate in two passes with nested checks.

## 10. Optimal Approach Explanation
Track both forward and reverse maps simultaneously to enforce bijection in one pass.

## 11. Step-by-Step Logic
1. Iterate index i.
2. If existing mapping conflicts, return false.
3. Insert new forward/reverse mapping otherwise.

## 12. Time Complexity Analysis
O(n).

## 13. Space Complexity Analysis
O(1) for bounded charset, else O(k).

## 14. Clean and Production-Quality Java Code
```java
import java.util.*;

class Solution {
    public boolean isIsomorphic(String s, String t) {
        Map<Character, Character> st = new HashMap<>();
        Map<Character, Character> ts = new HashMap<>();

        for (int i = 0; i < s.length(); i++) {
            char a = s.charAt(i);
            char b = t.charAt(i);

            if (st.containsKey(a) && st.get(a) != b) {
                return false;
            }
            if (ts.containsKey(b) && ts.get(b) != a) {
                return false;
            }
            st.put(a, b);
            ts.put(b, a);
        }

        return true;
    }
}
```

## 15. Dry Run Example
s='paper', t='title': p->t, a->i, p->t valid, e->l, r->e => true.

## 16. Common Interview Follow-Up Questions
- Can you do this with arrays for ASCII only?
- How does this differ from anagram checks?

## 17. Alternative Solutions if available
- Store first-seen indices arrays and compare patterns.

## 18. Real Interview Context (why companies ask this problem)
Useful to test bijection constraints and map consistency.
