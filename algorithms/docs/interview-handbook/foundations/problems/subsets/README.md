# Subsets

## 1. Problem Title
Subsets

## 2. Difficulty Level (Easy / Medium / Hard)
Medium

## 3. Problem Statement
Given an integer array of unique elements, return all possible subsets (the power set).

## 4. Input Format
nums: integer array with unique values.

## 5. Output Format
Return a list of all subsets in any order.

## 6. Constraints (very important for interviews)
- 1 <= nums.length <= 10
- -10 <= nums[i] <= 10
- All elements are unique.

## 7. Example Inputs and Outputs
Example 1
```text
Input: nums = [1,2,3]
Output: [[],[1],[2],[3],[1,2],[1,3],[2,3],[1,2,3]]
Explanation: Every element is either picked or skipped.
```

## 8. Edge Cases
- Single element input.
- Negative numbers.
- Ordering of subsets can differ.

## 9. Brute Force Approach Explanation
Iterate all bitmasks from 0 to 2^n - 1 and build a subset from set bits.

## 10. Optimal Approach Explanation
Backtracking explores inclusion/exclusion decisions recursively and naturally builds subsets.

## 11. Step-by-Step Logic
1. Start DFS from index 0 with empty path.
2. Add current path snapshot to answer.
3. Try each next index, include value, recurse, and backtrack.

## 12. Time Complexity Analysis
O(n * 2^n), because there are 2^n subsets and each copy can take O(n).

## 13. Space Complexity Analysis
O(n) recursion stack excluding output.

## 14. Clean and Production-Quality Java Code
```java
import java.util.*;

class Solution {
    public List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        backtrack(0, nums, new ArrayList<>(), result);
        return result;
    }

    private void backtrack(int start, int[] nums, List<Integer> path, List<List<Integer>> result) {
        result.add(new ArrayList<>(path));
        for (int i = start; i < nums.length; i++) {
            path.add(nums[i]);
            backtrack(i + 1, nums, path, result);
            path.remove(path.size() - 1);
        }
    }
}
```

## 15. Dry Run Example
nums=[1,2]: [] -> [1] -> [1,2] backtrack -> [2] -> done.

## 16. Common Interview Follow-Up Questions
- How does solution change if duplicates are allowed?
- Can you generate subsets in lexicographic order?

## 17. Alternative Solutions if available
- Bitmask generation.
- Iterative expansion: for each num, clone existing subsets and append num.

## 18. Real Interview Context (why companies ask this problem)
This tests recursion tree control and backtracking hygiene (push/pop state).
