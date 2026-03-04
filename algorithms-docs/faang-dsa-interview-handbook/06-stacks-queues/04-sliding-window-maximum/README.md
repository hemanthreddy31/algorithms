# Sliding Window Maximum

Topic: Stacks and Queues

## 1. Problem Title
Sliding Window Maximum

## 2. Difficulty Level (Easy / Medium / Hard)
Hard

## 3. Problem Statement
Max per window of size k.

## 4. Input Format
Function arguments as described in the statement.

## 5. Output Format
Return the required result.

## 6. Constraints (very important for interviews)
- 1 <= n <= 1e5

## 7. Example Inputs and Outputs
**Input**
```text
nums=[1,3,-1,-3,5,3,6,7], k=3
```
**Output**
```text
[3,3,5,5,6,7]
```

## 8. Edge Cases
- Empty/minimum input
- Duplicates/repeated values
- Boundary constraint values

## 9. Brute Force Approach Explanation
Enumerate all candidate states/combinations and validate directly; this usually fails upper constraints.

## 10. Optimal Approach Explanation
Apply the `deque` pattern to avoid repeated work and reduce asymptotic cost.

## 11. Step-by-Step Logic
1. Understand brute-force baseline.
1. Identify the key pattern from constraints.
1. Build the optimal data structure/state transition.
1. Iterate/DFS/BFS with invariant maintenance.
1. Return and verify against sample.

## 12. Time Complexity Analysis
O(n)

## 13. Space Complexity Analysis
O(k)

## 14. Clean and Production-Quality Java Code
```java
import java.util.*; class Solution { public int[] solve(int[] nums,int k){ Deque<Integer> dq=new ArrayDeque<>(); int[] ans=new int[nums.length-k+1]; for(int i=0,idx=0;i<nums.length;i++){ while(!dq.isEmpty()&&dq.peekFirst()<=i-k) dq.pollFirst(); while(!dq.isEmpty()&&nums[dq.peekLast()]<=nums[i]) dq.pollLast(); dq.offerLast(i); if(i>=k-1) ans[idx++]=nums[dq.peekFirst()]; } return ans; } }
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
Deque pattern.
