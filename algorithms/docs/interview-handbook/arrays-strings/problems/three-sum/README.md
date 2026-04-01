# 3Sum

## 1. Problem Title
3Sum

## 2. Difficulty Level (Easy / Medium / Hard)
Medium

## 3. Problem Statement
Given an integer array nums, return all unique triplets [nums[i], nums[j], nums[k]] such that i != j != k and nums[i] + nums[j] + nums[k] == 0.

## 4. Input Format
nums: integer array.

## 5. Output Format
Return list of unique triplets with sum zero.

## 6. Constraints (very important for interviews)
- 3 <= nums.length <= 3000
- -10^5 <= nums[i] <= 10^5

## 7. Example Inputs and Outputs
Example 1
```text
Input: nums=[-1,0,1,2,-1,-4]
Output: [[-1,-1,2],[-1,0,1]]
Explanation: Sort and use two pointers while skipping duplicates.
```

## 8. Edge Cases
- All positives or all negatives => no triplet.
- Many duplicates.
- Exactly one valid triplet.

## 9. Brute Force Approach Explanation
Check all i,j,k combinations and use set for deduplication. Complexity O(n^3).

## 10. Optimal Approach Explanation
Sort array. Fix i, then solve two-sum with left/right pointers for target -nums[i], skipping duplicates.

## 11. Step-by-Step Logic
1. Sort nums.
2. Iterate i from 0 to n-3 and skip duplicate anchors.
3. Use left=i+1 and right=n-1, move pointers based on sum and dedupe.

## 12. Time Complexity Analysis
O(n^2) after sorting.

## 13. Space Complexity Analysis
O(1) extra excluding output.

## 14. Clean and Production-Quality Java Code
```java
import java.util.*;

class Solution {
    public List<List<Integer>> threeSum(int[] nums) {
        Arrays.sort(nums);
        List<List<Integer>> ans = new ArrayList<>();

        for (int i = 0; i < nums.length - 2; i++) {
            if (i > 0 && nums[i] == nums[i - 1]) {
                continue;
            }

            int left = i + 1;
            int right = nums.length - 1;
            while (left < right) {
                int sum = nums[i] + nums[left] + nums[right];
                if (sum == 0) {
                    ans.add(Arrays.asList(nums[i], nums[left], nums[right]));
                    left++;
                    right--;
                    while (left < right && nums[left] == nums[left - 1]) {
                        left++;
                    }
                    while (left < right && nums[right] == nums[right + 1]) {
                        right--;
                    }
                } else if (sum < 0) {
                    left++;
                } else {
                    right--;
                }
            }
        }
        return ans;
    }
}
```

## 15. Dry Run Example
After sorting [-4,-1,-1,0,1,2], anchor -1 (index1): left=-1,right=2 -> triplet [-1,-1,2], then [-1,0,1].

## 16. Common Interview Follow-Up Questions
- How do you solve 3Sum Closest?
- How does k-Sum generalization work?

## 17. Alternative Solutions if available
- Hashing two-sum per anchor (still O(n^2), harder dedupe).

## 18. Real Interview Context (why companies ask this problem)
This problem checks sorting + two-pointer control and duplicate handling discipline.
