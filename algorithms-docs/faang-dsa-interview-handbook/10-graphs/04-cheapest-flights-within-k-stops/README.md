# Cheapest Flights Within K Stops

Topic: Graphs

## 1. Problem Title
Cheapest Flights Within K Stops

## 2. Difficulty Level (Easy / Medium / Hard)
Medium

## 3. Problem Statement
Cheapest route with <=k stops.

## 4. Input Format
Function arguments as described in the statement.

## 5. Output Format
Return the required result.

## 6. Constraints (very important for interviews)
- 1 <= n <= 100

## 7. Example Inputs and Outputs
**Input**
```text
n,flights,src,dst,k
```
**Output**
```text
cost or -1
```

## 8. Edge Cases
- Empty/minimum input
- Duplicates/repeated values
- Boundary constraint values

## 9. Brute Force Approach Explanation
Enumerate all candidate states/combinations and validate directly; this usually fails upper constraints.

## 10. Optimal Approach Explanation
Apply the `shortest` pattern to avoid repeated work and reduce asymptotic cost.

## 11. Step-by-Step Logic
1. Understand brute-force baseline.
1. Identify the key pattern from constraints.
1. Build the optimal data structure/state transition.
1. Iterate/DFS/BFS with invariant maintenance.
1. Return and verify against sample.

## 12. Time Complexity Analysis
O(kE)

## 13. Space Complexity Analysis
O(V)

## 14. Clean and Production-Quality Java Code
```java
import java.util.*; class Solution { public int solve(int[][] times,int n,int k){ List<List<int[]>> g=new ArrayList<>(); for(int i=0;i<=n;i++) g.add(new ArrayList<>()); for(int[] t:times) g.get(t[0]).add(new int[]{t[1],t[2]}); int[] d=new int[n+1]; Arrays.fill(d,Integer.MAX_VALUE); d[k]=0; PriorityQueue<int[]> pq=new PriorityQueue<>((a,b)->a[1]-b[1]); pq.offer(new int[]{k,0}); while(!pq.isEmpty()){ int[] cur=pq.poll(); int u=cur[0],du=cur[1]; if(du!=d[u]) continue; for(int[] e:g.get(u)) if(d[e[0]]>du+e[1]){ d[e[0]]=du+e[1]; pq.offer(new int[]{e[0],d[e[0]]}); } } int ans=0; for(int i=1;i<=n;i++){ if(d[i]==Integer.MAX_VALUE) return -1; ans=Math.max(ans,d[i]); } return ans; } }
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
Bellman-Ford variant.
