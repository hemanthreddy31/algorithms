# Median from Data Stream

Topic: Heaps

## 1. Problem Title
Median from Data Stream

## 2. Difficulty Level (Easy / Medium / Hard)
Hard

## 3. Problem Statement
Support addNum/findMedian.

## 4. Input Format
Function arguments as described in the statement.

## 5. Output Format
Return the required result.

## 6. Constraints (very important for interviews)
- Up to 5e4 ops

## 7. Example Inputs and Outputs
**Input**
```text
add 1,2,3
```
**Output**
```text
1.5 then 2
```

## 8. Edge Cases
- Empty/minimum input
- Duplicates/repeated values
- Boundary constraint values

## 9. Brute Force Approach Explanation
Enumerate all candidate states/combinations and validate directly; this usually fails upper constraints.

## 10. Optimal Approach Explanation
Apply the `median` pattern to avoid repeated work and reduce asymptotic cost.

## 11. Step-by-Step Logic
1. Understand brute-force baseline.
1. Identify the key pattern from constraints.
1. Build the optimal data structure/state transition.
1. Iterate/DFS/BFS with invariant maintenance.
1. Return and verify against sample.

## 12. Time Complexity Analysis
O(log n)

## 13. Space Complexity Analysis
O(n)

## 14. Clean and Production-Quality Java Code
```java
import java.util.*; class MedianFinder { PriorityQueue<Integer> lo=new PriorityQueue<>(Collections.reverseOrder()); PriorityQueue<Integer> hi=new PriorityQueue<>(); public void addNum(int x){ if(lo.isEmpty()||x<=lo.peek()) lo.offer(x); else hi.offer(x); if(lo.size()>hi.size()+1) hi.offer(lo.poll()); if(hi.size()>lo.size()) lo.offer(hi.poll()); } public double findMedian(){ return lo.size()==hi.size()?(lo.peek()+hi.peek())/2.0:lo.peek(); } }
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
Two-heaps balancing.
