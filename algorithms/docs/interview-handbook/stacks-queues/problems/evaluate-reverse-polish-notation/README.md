# Evaluate Reverse Polish Notation

## 1. Problem Title
Evaluate Reverse Polish Notation

## 2. Difficulty Level (Easy / Medium / Hard)
Medium

## 3. Problem Statement
Evaluate arithmetic expression in Reverse Polish Notation. Valid operators are +, -, *, /.

## 4. Input Format
tokens: string array representing RPN expression.

## 5. Output Format
Return integer evaluation result with truncation toward zero for division.

## 6. Constraints (very important for interviews)
- 1 <= tokens.length <= 10^4
- Each token is integer or one of + - * /.
- Expression is always valid.

## 7. Example Inputs and Outputs
Example 1
```text
Input: tokens=['2','1','+','3','*']
Output: 9
Explanation: (2+1)*3 = 9.
```

## 8. Edge Cases
- Negative values.
- Division truncation toward zero.
- Single token expression.

## 9. Brute Force Approach Explanation
Convert RPN to infix and then evaluate with parser. Overkill and error-prone.

## 10. Optimal Approach Explanation
Use stack. Push numbers; on operator, pop two operands, apply op, push result.

## 11. Step-by-Step Logic
1. Traverse tokens left to right.
2. If token is number, push integer.
3. If operator, pop b then a, compute a op b, and push result.

## 12. Time Complexity Analysis
O(n).

## 13. Space Complexity Analysis
O(n).

## 14. Clean and Production-Quality Java Code
```java
import java.util.*;

class Solution {
    public int evalRPN(String[] tokens) {
        Deque<Integer> stack = new ArrayDeque<>();

        for (String token : tokens) {
            switch (token) {
                case "+": {
                    int b = stack.pop();
                    int a = stack.pop();
                    stack.push(a + b);
                    break;
                }
                case "-": {
                    int b = stack.pop();
                    int a = stack.pop();
                    stack.push(a - b);
                    break;
                }
                case "*": {
                    int b = stack.pop();
                    int a = stack.pop();
                    stack.push(a * b);
                    break;
                }
                case "/": {
                    int b = stack.pop();
                    int a = stack.pop();
                    stack.push(a / b);
                    break;
                }
                default:
                    stack.push(Integer.parseInt(token));
            }
        }

        return stack.pop();
    }
}
```

## 15. Dry Run Example
2 1 + 3 * -> push2,push1, + =>3, push3, * =>9.

## 16. Common Interview Follow-Up Questions
- How to support exponent operator?
- How to convert infix to postfix first?

## 17. Alternative Solutions if available
- Recursive evaluation from end using call stack.

## 18. Real Interview Context (why companies ask this problem)
Tests stack simulation and operator operand order correctness.
