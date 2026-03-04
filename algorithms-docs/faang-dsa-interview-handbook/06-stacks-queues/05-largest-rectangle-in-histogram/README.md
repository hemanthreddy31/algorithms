# Largest Rectangle in Histogram

Topic: Stacks and Queues

## 1. Problem Title
Largest Rectangle in Histogram

## 2. Difficulty Level (Easy / Medium / Hard)
Hard

## 3. Problem Statement
Largest rectangle area.

## 4. Input Format
Function arguments as described in the statement.

## 5. Output Format
Return the required result.

## 6. Constraints (very important for interviews)
- 1 <= n <= 1e5

## 7. Example Inputs and Outputs
**Input**
```text
heights=[2,1,5,6,2,3]
```
**Output**
```text
10
```

## 8. Edge Cases
- Empty/minimum input
- Duplicates/repeated values
- Boundary constraint values

## 9. Brute Force Approach Explanation
Enumerate all candidate states/combinations and validate directly; this usually fails upper constraints.

## 10. Optimal Approach Explanation
Apply the `mono_stack` pattern to avoid repeated work and reduce asymptotic cost.

## 11. Step-by-Step Logic
1. Understand brute-force baseline.
1. Identify the key pattern from constraints.
1. Build the optimal data structure/state transition.
1. Iterate/DFS/BFS with invariant maintenance.
1. Return and verify against sample.

## 12. Time Complexity Analysis
O(n)

## 13. Space Complexity Analysis
O(n)

## 14. Clean and Production-Quality Java Code
```java
import java.util.*; class Solution { public int[] solve(int[] a){ int n=a.length; int[] ans=new int[n]; Deque<Integer> st=new ArrayDeque<>(); for(int i=0;i<n;i++){ while(!st.isEmpty()&&a[i]>a[st.peek()]){ int idx=st.pop(); ans[idx]=i-idx;} st.push(i);} return ans;} }
```

## 15. Dry Run Example
Track the sample input through each core state update and confirm it produces the sample output.

## 16. Common Interview Follow-Up Questions
- Can this be done in-place or with less memory?
- How would you adapt this for streaming/online input?
- Which constraints break this solution?

## 17. Alternative Solutions if available
- A simpler implementation often exists with slightly worse complexity.
- Mention tradeoffs explicitly during interview discussion.

## 18. Real Interview Context (why companies ask this problem)
Monotonic stack boundaries.
