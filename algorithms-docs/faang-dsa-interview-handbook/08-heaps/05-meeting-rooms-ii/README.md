# Meeting Rooms II

Topic: Heaps

## 1. Problem Title
Meeting Rooms II

## 2. Difficulty Level (Easy / Medium / Hard)
Medium

## 3. Problem Statement
Minimum rooms for intervals.

## 4. Input Format
Function arguments as described in the statement.

## 5. Output Format
Return the required result.

## 6. Constraints (very important for interviews)
- 1 <= n <= 1e4

## 7. Example Inputs and Outputs
**Input**
```text
intervals=[[0,30],[5,10],[15,20]]
```
**Output**
```text
2
```

## 8. Edge Cases
- Empty/minimum input
- Duplicates/repeated values
- Boundary constraint values

## 9. Brute Force Approach Explanation
Enumerate all candidate states/combinations and validate directly; this usually fails upper constraints.

## 10. Optimal Approach Explanation
Apply the `heap` pattern to avoid repeated work and reduce asymptotic cost.

## 11. Step-by-Step Logic
1. Understand brute-force baseline.
1. Identify the key pattern from constraints.
1. Build the optimal data structure/state transition.
1. Iterate/DFS/BFS with invariant maintenance.
1. Return and verify against sample.

## 12. Time Complexity Analysis
O(n log n)

## 13. Space Complexity Analysis
O(n)

## 14. Clean and Production-Quality Java Code
```java
import java.util.*; class Solution { public int[] solve(int[] nums,int k){ Map<Integer,Integer> f=new HashMap<>(); for(int x:nums) f.put(x,f.getOrDefault(x,0)+1); PriorityQueue<int[]> pq=new PriorityQueue<>((a,b)->a[1]-b[1]); for(Map.Entry<Integer,Integer> e:f.entrySet()){ pq.offer(new int[]{e.getKey(),e.getValue()}); if(pq.size()>k) pq.poll(); } int[] ans=new int[k]; for(int i=k-1;i>=0;i--) ans[i]=pq.poll()[0]; return ans;} }
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
Scheduling with heap.
