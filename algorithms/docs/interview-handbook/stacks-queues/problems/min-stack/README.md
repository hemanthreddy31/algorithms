# Min Stack

## 1. Problem Title
Min Stack

## 2. Difficulty Level (Easy / Medium / Hard)
Medium

## 3. Problem Statement
Design a stack that supports push, pop, top, and retrieving minimum element in constant time.

## 4. Input Format
Operations sequence: push(x), pop(), top(), getMin().

## 5. Output Format
top returns latest value, getMin returns minimum so far.

## 6. Constraints (very important for interviews)
- Values fit in 32-bit signed integer.
- At most 3 * 10^4 operations.

## 7. Example Inputs and Outputs
Example 1
```text
Input: push(-2), push(0), push(-3), getMin(), pop(), top(), getMin()
Output: -3, 0, -2
Explanation: Auxiliary min tracking keeps O(1) min query.
```

## 8. Edge Cases
- Duplicate minimum values.
- Push increasing or decreasing only.
- Operations near empty stack boundaries.

## 9. Brute Force Approach Explanation
Store values in list and scan list for each getMin in O(n).

## 10. Optimal Approach Explanation
Store pairs (value, currentMin) on stack so each node knows minimum up to that point.

## 11. Step-by-Step Logic
1. On push, minSoFar = min(x, currentMin).
2. Store both x and minSoFar.
3. top/pop/getMin operate on stack top in O(1).

## 12. Time Complexity Analysis
All operations O(1).

## 13. Space Complexity Analysis
O(n).

## 14. Clean and Production-Quality Java Code
```java
import java.util.*;

class MinStack {
    private static class Node {
        int value;
        int minSoFar;

        Node(int value, int minSoFar) {
            this.value = value;
            this.minSoFar = minSoFar;
        }
    }

    private final Deque<Node> stack = new ArrayDeque<>();

    public void push(int val) {
        int minSoFar = stack.isEmpty() ? val : Math.min(val, stack.peek().minSoFar);
        stack.push(new Node(val, minSoFar));
    }

    public void pop() {
        stack.pop();
    }

    public int top() {
        return stack.peek().value;
    }

    public int getMin() {
        return stack.peek().minSoFar;
    }
}
```

## 15. Dry Run Example
push 3(min3), push 1(min1), push 2(min1). getMin=1, pop 2, getMin still 1.

## 16. Common Interview Follow-Up Questions
- How to support getMax as well?
- Can this be done without pair objects?

## 17. Alternative Solutions if available
- Two stacks: one main, one min stack.

## 18. Real Interview Context (why companies ask this problem)
Design question for data-structure augmentation under strict complexity constraints.
