# Valid Parentheses

## 1. Problem Title
Valid Parentheses

## 2. Difficulty Level (Easy / Medium / Hard)
Easy

## 3. Problem Statement
Given a string s containing only '(', ')', '{', '}', '[' and ']', determine if input string is valid.

## 4. Input Format
s: string of bracket characters.

## 5. Output Format
Return true if all brackets are balanced and correctly nested, else false.

## 6. Constraints (very important for interviews)
- 1 <= s.length <= 10^4
- s consists only of ()[]{}.

## 7. Example Inputs and Outputs
Example 1
```text
Input: s='()[]{}'
Output: true
Explanation: All pairs close correctly in order.
```
Example 2
```text
Input: s='(]'
Output: false
Explanation: Mismatched closing bracket.
```

## 8. Edge Cases
- Odd length string cannot be valid.
- Closing bracket appears before opening.
- Nested and adjacent mixes.

## 9. Brute Force Approach Explanation
Repeatedly remove matching pairs from string until no change. Inefficient due to repeated scans.

## 10. Optimal Approach Explanation
Use stack of opening brackets. For each closing bracket, top must match corresponding opening.

## 11. Step-by-Step Logic
1. Traverse each character.
2. Push opening brackets.
3. For closing bracket, verify stack not empty and pair matches.
4. String is valid only if stack is empty at end.

## 12. Time Complexity Analysis
O(n).

## 13. Space Complexity Analysis
O(n) in worst case.

## 14. Clean and Production-Quality Java Code
```java
import java.util.*;

class Solution {
    public boolean isValid(String s) {
        Deque<Character> stack = new ArrayDeque<>();
        for (char c : s.toCharArray()) {
            if (c == '(' || c == '[' || c == '{') {
                stack.push(c);
            } else {
                if (stack.isEmpty()) {
                    return false;
                }
                char open = stack.pop();
                if ((c == ')' && open != '(')
                    || (c == ']' && open != '[')
                    || (c == '}' && open != '{')) {
                    return false;
                }
            }
        }
        return stack.isEmpty();
    }
}
```

## 15. Dry Run Example
s='([{}])': push (, [, { then pop for }, ], ) in reverse order -> valid.

## 16. Common Interview Follow-Up Questions
- How to validate multiple bracket types with custom mapping?
- How to return index of first invalid char?

## 17. Alternative Solutions if available
- String replacement loop (not efficient).

## 18. Real Interview Context (why companies ask this problem)
Standard stack problem for balanced parsing correctness.
