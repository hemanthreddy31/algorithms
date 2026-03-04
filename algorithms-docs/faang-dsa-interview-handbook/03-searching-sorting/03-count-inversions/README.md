# Count Inversions

Topic: Searching and Sorting

## 1. Problem Title
Count Inversions

## 2. Difficulty Level (Easy / Medium / Hard)
Hard

## 3. Problem Statement
Count pairs i<j and a[i]>a[j].

## 4. Input Format
Function arguments as described in the statement.

## 5. Output Format
Return the required result.

## 6. Constraints (very important for interviews)
- 1 <= n <= 2e5

## 7. Example Inputs and Outputs
**Input**
```text
a=[2,4,1,3,5]
```
**Output**
```text
3
```

## 8. Edge Cases
- Empty/minimum input
- Duplicates/repeated values
- Boundary constraint values

## 9. Brute Force Approach Explanation
Enumerate all candidate states/combinations and validate directly; this usually fails upper constraints.

## 10. Optimal Approach Explanation
Apply the `search_sort` pattern to avoid repeated work and reduce asymptotic cost.

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
class Solution { public int solve(int[] nums,int target){ int l=0,r=nums.length-1; while(l<=r){ int m=l+(r-l)/2; if(nums[m]==target) return m; if(nums[l]<=nums[m]){ if(nums[l]<=target&&target<nums[m]) r=m-1; else l=m+1; } else { if(nums[m]<target&&target<=nums[r]) l=m+1; else r=m-1; } } return -1; } }
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
Merge-sort augmentation.
