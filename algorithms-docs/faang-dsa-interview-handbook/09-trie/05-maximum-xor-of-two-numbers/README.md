# Maximum XOR of Two Numbers

Topic: Trie

## 1. Problem Title
Maximum XOR of Two Numbers

## 2. Difficulty Level (Easy / Medium / Hard)
Medium

## 3. Problem Statement
Maximum xor pair in array.

## 4. Input Format
Function arguments as described in the statement.

## 5. Output Format
Return the required result.

## 6. Constraints (very important for interviews)
- 1 <= n <= 2e5

## 7. Example Inputs and Outputs
**Input**
```text
nums=[3,10,5,25,2,8]
```
**Output**
```text
28
```

## 8. Edge Cases
- Empty/minimum input
- Duplicates/repeated values
- Boundary constraint values

## 9. Brute Force Approach Explanation
Enumerate all candidate states/combinations and validate directly; this usually fails upper constraints.

## 10. Optimal Approach Explanation
Apply the `trie` pattern to avoid repeated work and reduce asymptotic cost.

## 11. Step-by-Step Logic
1. Understand brute-force baseline.
1. Identify the key pattern from constraints.
1. Build the optimal data structure/state transition.
1. Iterate/DFS/BFS with invariant maintenance.
1. Return and verify against sample.

## 12. Time Complexity Analysis
O(32n)

## 13. Space Complexity Analysis
O(32n)

## 14. Clean and Production-Quality Java Code
```java
class Trie { static class Node{Node[] n=new Node[26]; boolean end;} Node r=new Node(); public void insert(String w){ Node c=r; for(char ch:w.toCharArray()){ int i=ch-'a'; if(c.n[i]==null) c.n[i]=new Node(); c=c.n[i]; } c.end=true; } public boolean search(String w){ Node c=r; for(char ch:w.toCharArray()){ int i=ch-'a'; if(c.n[i]==null) return false; c=c.n[i]; } return c.end; } }
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
Bitwise trie.
