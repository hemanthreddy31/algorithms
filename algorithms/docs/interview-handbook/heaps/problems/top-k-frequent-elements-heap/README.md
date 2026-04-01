# Top K Frequent Elements

## 1. Problem Title
Top K Frequent Elements

## 2. Difficulty Level (Easy / Medium / Hard)
Medium

## 3. Problem Statement
Given an integer array nums and integer k, return the k most frequent elements.

## 4. Input Format
nums: integer array, k: integer.

## 5. Output Format
Return integer array/list containing k frequent elements.

## 6. Constraints (very important for interviews)
- 1 <= nums.length <= 10^5
- -10^4 <= nums[i] <= 10^4
- k is in [1, number of unique elements]

## 7. Example Inputs and Outputs
Example 1
```text
Input: nums=[1,1,1,2,2,3], k=2
Output: [1,2]
Explanation: 1 appears 3 times, 2 appears 2 times.
```

## 8. Edge Cases
- k equals number of unique elements.
- Single unique value.
- Frequency ties.

## 9. Brute Force Approach Explanation
Count frequencies, sort all unique elements by frequency descending.

## 10. Optimal Approach Explanation
Use frequency map + min-heap of size k over entries.

## 11. Step-by-Step Logic
1. Build frequency map.
2. Push each (value,freq) into min-heap by freq and trim to size k.
3. Extract heap elements as answer.

## 12. Time Complexity Analysis
O(n + u log k), u = unique values.

## 13. Space Complexity Analysis
O(u + k).

## 14. Clean and Production-Quality Java Code
```java
import java.util.*;

class Solution {
    public int[] topKFrequent(int[] nums, int k) {
        Map<Integer, Integer> freq = new HashMap<>();
        for (int num : nums) {
            freq.put(num, freq.getOrDefault(num, 0) + 1);
        }

        PriorityQueue<int[]> minHeap = new PriorityQueue<>(Comparator.comparingInt(a -> a[1]));
        for (Map.Entry<Integer, Integer> entry : freq.entrySet()) {
            minHeap.offer(new int[] {entry.getKey(), entry.getValue()});
            if (minHeap.size() > k) {
                minHeap.poll();
            }
        }

        int[] ans = new int[k];
        for (int i = k - 1; i >= 0; i--) {
            ans[i] = minHeap.poll()[0];
        }
        return ans;
    }
}
```

## 15. Dry Run Example
freq: {1:3,2:2,3:1}, heap keeps top 2 by freq -> [2,1].

## 16. Common Interview Follow-Up Questions
- How to solve in O(n) using bucket sort?
- How to handle streaming updates?

## 17. Alternative Solutions if available
- Bucket sort by frequency O(n).

## 18. Real Interview Context (why companies ask this problem)
Combines hashing and bounded heap optimization, common in data-platform interviews.
