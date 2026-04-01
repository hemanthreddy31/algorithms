# Daily Temperatures

## 1. Problem Title
Daily Temperatures

## 2. Difficulty Level (Easy / Medium / Hard)
Medium

## 3. Problem Statement
Given daily temperatures, return an array where answer[i] is number of days to wait for a warmer temperature. If none, answer[i]=0.

## 4. Input Format
temperatures: integer array.

## 5. Output Format
Return integer array answer of same length.

## 6. Constraints (very important for interviews)
- 1 <= temperatures.length <= 10^5
- 30 <= temperatures[i] <= 100

## 7. Example Inputs and Outputs
Example 1
```text
Input: temperatures=[73,74,75,71,69,72,76,73]
Output: [1,1,4,2,1,1,0,0]
Explanation: Monotonic stack stores unresolved indices.
```

## 8. Edge Cases
- Strictly decreasing temperatures -> all zeros.
- Strictly increasing temperatures.
- Single day input.

## 9. Brute Force Approach Explanation
For each day i, scan forward j>i until warmer day found. O(n^2).

## 10. Optimal Approach Explanation
Use decreasing monotonic stack of indices. When current temperature is warmer than stack top, resolve waits.

## 11. Step-by-Step Logic
1. Initialize answer array and empty stack of indices.
2. For each index i, pop while temp[i] > temp[stackTop] and fill answer for popped indices.
3. Push i onto stack.

## 12. Time Complexity Analysis
O(n).

## 13. Space Complexity Analysis
O(n).

## 14. Clean and Production-Quality Java Code
```java
import java.util.*;

class Solution {
    public int[] dailyTemperatures(int[] temperatures) {
        int n = temperatures.length;
        int[] answer = new int[n];
        Deque<Integer> stack = new ArrayDeque<>();

        for (int i = 0; i < n; i++) {
            while (!stack.isEmpty() && temperatures[i] > temperatures[stack.peek()]) {
                int prev = stack.pop();
                answer[prev] = i - prev;
            }
            stack.push(i);
        }

        return answer;
    }
}
```

## 15. Dry Run Example
At temp 76 (index 6), pop unresolved 72,75 etc and fill waits.

## 16. Common Interview Follow-Up Questions
- How to find previous warmer day instead of next?
- How to adapt for stock span problem?

## 17. Alternative Solutions if available
- Backward DP jump pointers.

## 18. Real Interview Context (why companies ask this problem)
Canonical monotonic-stack interview pattern.
