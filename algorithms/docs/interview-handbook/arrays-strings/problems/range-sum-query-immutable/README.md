# Range Sum Query - Immutable

## 1. Problem Title
Range Sum Query - Immutable

## 2. Difficulty Level (Easy / Medium / Hard)
Easy

## 3. Problem Statement
Design a data structure that supports sumRange(left, right): return the sum of elements between indices left and right inclusive in an immutable array.

## 4. Input Format
Constructor input: nums array. Query input: left and right indices.

## 5. Output Format
For each query, return integer sum of nums[left..right].

## 6. Constraints (very important for interviews)
- 1 <= nums.length <= 10^4
- -10^5 <= nums[i] <= 10^5
- 0 <= left <= right < nums.length
- Up to 10^4 queries

## 7. Example Inputs and Outputs
Example 1
```text
Input: nums=[-2,0,3,-5,2,-1], sumRange(0,2), sumRange(2,5)
Output: 1, -1
Explanation: Prefix sums answer each query in O(1).
```

## 8. Edge Cases
- Single element range.
- All negative values.
- Query covers full array.

## 9. Brute Force Approach Explanation
For each query, iterate from left to right and sum values. This is O(n) per query.

## 10. Optimal Approach Explanation
Precompute prefix sums where prefix[i] stores sum of first i elements. Then sumRange(l,r)=prefix[r+1]-prefix[l].

## 11. Step-by-Step Logic
1. Build prefix array of size n+1 with prefix[0]=0.
2. For each index i, set prefix[i+1] = prefix[i] + nums[i].
3. Answer query using subtraction.

## 12. Time Complexity Analysis
Build O(n), each query O(1).

## 13. Space Complexity Analysis
O(n) for prefix array.

## 14. Clean and Production-Quality Java Code
```java
class NumArray {
    private final int[] prefix;

    public NumArray(int[] nums) {
        prefix = new int[nums.length + 1];
        for (int i = 0; i < nums.length; i++) {
            prefix[i + 1] = prefix[i] + nums[i];
        }
    }

    public int sumRange(int left, int right) {
        return prefix[right + 1] - prefix[left];
    }
}
```

## 15. Dry Run Example
nums=[1,3,5], prefix=[0,1,4,9]. sumRange(1,2)=prefix[3]-prefix[1]=8.

## 16. Common Interview Follow-Up Questions
- How would you support updates too?
- How do you extend to 2D matrix range sums?

## 17. Alternative Solutions if available
- Fenwick Tree for mutable array.
- Segment Tree for mutable range queries.

## 18. Real Interview Context (why companies ask this problem)
This tests prefix-sum recognition and precomputation tradeoffs.
