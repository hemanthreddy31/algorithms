# Single Number

## 1. Problem Title
Single Number

## 2. Difficulty Level (Easy / Medium / Hard)
Easy

## 3. Problem Statement
Given a non-empty integer array where every element appears exactly twice except one element that appears once, return the element that appears once.

## 4. Input Format
nums: integer array of length n.

## 5. Output Format
Return a single integer that appears exactly once.

## 6. Constraints (very important for interviews)
- 1 <= n <= 3 * 10^4
- -3 * 10^4 <= nums[i] <= 3 * 10^4
- Each element appears twice except one element.

## 7. Example Inputs and Outputs
Example 1
```text
Input: nums = [4,1,2,1,2]
Output: 4
Explanation: Pairs cancel out under XOR.
```

## 8. Edge Cases
- Array size is 1.
- Single element is at start or end.
- Contains negative values.

## 9. Brute Force Approach Explanation
Count frequency of each number using a map and return the number with frequency 1. This takes linear extra memory.

## 10. Optimal Approach Explanation
Use XOR. Since x ^ x = 0 and x ^ 0 = x, all duplicate pairs cancel and only the unique number remains.

## 11. Step-by-Step Logic
1. Initialize result = 0.
2. XOR each number into result.
3. Return result.

## 12. Time Complexity Analysis
O(n), one pass through the array.

## 13. Space Complexity Analysis
O(1), constant extra memory.

## 14. Clean and Production-Quality Java Code
```java
class Solution {
    public int singleNumber(int[] nums) {
        int result = 0;
        for (int num : nums) {
            result ^= num;
        }
        return result;
    }
}
```

## 15. Dry Run Example
For [4,1,2,1,2]: (((((0^4)^1)^2)^1)^2) = 4.

## 16. Common Interview Follow-Up Questions
- What if every element appears three times except one?
- Can you solve when two unique numbers exist?

## 17. Alternative Solutions if available
- HashMap frequency count (O(n) space).
- Sorting and scanning adjacent pairs (O(n log n)).

## 18. Real Interview Context (why companies ask this problem)
Interviewers use this to test bit manipulation fluency and ability to move from counting to a constant-space trick.
