# LRU Cache

Topic: Linked Lists

## 1. Problem Title
LRU Cache

## 2. Difficulty Level (Easy / Medium / Hard)
Medium

## 3. Problem Statement
Design O(1) get/put with LRU eviction.

## 4. Input Format
Function arguments as described in the statement.

## 5. Output Format
Return the required result.

## 6. Constraints (very important for interviews)
- 1 <= capacity <= 3000

## 7. Example Inputs and Outputs
**Input**
```text
LRUCache ops
```
**Output**
```text
expected outputs
```

## 8. Edge Cases
- Empty/minimum input
- Duplicates/repeated values
- Boundary constraint values

## 9. Brute Force Approach Explanation
Enumerate all candidate states/combinations and validate directly; this usually fails upper constraints.

## 10. Optimal Approach Explanation
Apply the `cache` pattern to avoid repeated work and reduce asymptotic cost.

## 11. Step-by-Step Logic
1. Understand brute-force baseline.
1. Identify the key pattern from constraints.
1. Build the optimal data structure/state transition.
1. Iterate/DFS/BFS with invariant maintenance.
1. Return and verify against sample.

## 12. Time Complexity Analysis
O(1) amortized

## 13. Space Complexity Analysis
O(capacity)

## 14. Clean and Production-Quality Java Code
```java
import java.util.*; class LRUCache { static class N{int k,v;N p,n;N(int k,int v){this.k=k;this.v=v;}} int cap; Map<Integer,N> m=new HashMap<>(); N h=new N(-1,-1), t=new N(-1,-1); public LRUCache(int c){cap=c; h.n=t; t.p=h;} public int get(int k){ N x=m.get(k); if(x==null) return -1; mv(x); return x.v;} public void put(int k,int v){ N x=m.get(k); if(x!=null){x.v=v; mv(x); return;} if(m.size()==cap){ N d=t.p; rm(d); m.remove(d.k);} N n=new N(k,v); add(n); m.put(k,n);} void mv(N x){rm(x); add(x);} void add(N x){x.n=h.n; x.p=h; h.n.p=x; h.n=x;} void rm(N x){x.p.n=x.n; x.n.p=x.p;} }
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
HashMap + DLL design.
