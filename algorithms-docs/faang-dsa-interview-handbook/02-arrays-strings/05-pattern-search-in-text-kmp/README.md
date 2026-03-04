# Pattern Search in Text (KMP)

Topic: Arrays and Strings

## 1. Problem Title
Pattern Search in Text (KMP)

## 2. Difficulty Level (Easy / Medium / Hard)
Hard

## 3. Problem Statement
Return all pattern start indices in text.

## 4. Input Format
Function arguments as described in the statement.

## 5. Output Format
Return the required result.

## 6. Constraints (very important for interviews)
- 1 <= |t|,|p| <= 2e5

## 7. Example Inputs and Outputs
**Input**
```text
text=ababcabcabababd, pat=ababd
```
**Output**
```text
[10]
```

## 8. Edge Cases
- Empty/minimum input
- Duplicates/repeated values
- Boundary constraint values

## 9. Brute Force Approach Explanation
Enumerate all candidate states/combinations and validate directly; this usually fails upper constraints.

## 10. Optimal Approach Explanation
Apply the `string_match` pattern to avoid repeated work and reduce asymptotic cost.

## 11. Step-by-Step Logic
1. Understand brute-force baseline.
1. Identify the key pattern from constraints.
1. Build the optimal data structure/state transition.
1. Iterate/DFS/BFS with invariant maintenance.
1. Return and verify against sample.

## 12. Time Complexity Analysis
O(n+m)

## 13. Space Complexity Analysis
O(m)

## 14. Clean and Production-Quality Java Code
```java
import java.util.*; class Solution { public List<Integer> solve(String t,String p){ List<Integer> out=new ArrayList<>(); if(p.isEmpty()) return out; int[] lps=new int[p.length()]; for(int i=1,len=0;i<p.length();){ if(p.charAt(i)==p.charAt(len)) lps[i++]=++len; else if(len>0) len=lps[len-1]; else lps[i++]=0; } for(int i=0,j=0;i<t.length();){ if(t.charAt(i)==p.charAt(j)){i++;j++;} if(j==p.length()){ out.add(i-j); j=lps[j-1]; } else if(i<t.length()&&t.charAt(i)!=p.charAt(j)){ if(j>0) j=lps[j-1]; else i++; } } return out;} }
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
KMP and Rabin-Karp discussion expected.
