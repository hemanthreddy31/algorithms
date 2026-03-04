# Pair Sum Complexity Drill

Topic: Foundations

## 1. Problem Title
Pair Sum Complexity Drill

## 2. Difficulty Level (Easy / Medium / Hard)
Easy

## 3. Problem Statement
Find pair indices summing to target and discuss complexity.

## 4. Input Format
Function arguments as described in the statement.

## 5. Output Format
Return the required result.

## 6. Constraints (very important for interviews)
- 2 <= n <= 1e5

## 7. Example Inputs and Outputs
**Input**
```text
nums=[2,7,11,15], target=9
```
**Output**
```text
[0,1]
```

## 8. Edge Cases
- Empty/minimum input
- Duplicates/repeated values
- Boundary constraint values

## 9. Brute Force Approach Explanation
Enumerate all candidate states/combinations and validate directly; this usually fails upper constraints.

## 10. Optimal Approach Explanation
Apply the `array_hash` pattern to avoid repeated work and reduce asymptotic cost.

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
import java.util.*;
class Solution { public int[] solve(int[] nums, int target){ Map<Integer,Integer> m=new HashMap<>(); for(int i=0;i<nums.length;i++){ int need=target-nums[i]; if(m.containsKey(need)) return new int[]{m.get(need),i}; m.put(nums[i],i);} return new int[]{-1,-1}; } }
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
Tests transition from brute force to hashmap.
