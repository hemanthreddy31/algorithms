# Kth Largest Element in a Stream

## 1. Problem Title
Kth Largest Element in a Stream

## 2. Difficulty Level (Easy / Medium / Hard)
Easy

## 3. Problem Statement
Design a class to find the kth largest element in a stream of numbers.

## 4. Input Format
Constructor input: k and initial nums. Method add(val) inserts value.

## 5. Output Format
add returns current kth largest element.

## 6. Constraints (very important for interviews)
- 1 <= k <= 10^4
- -10^4 <= nums[i], val <= 10^4
- At most 10^4 calls to add

## 7. Example Inputs and Outputs
Example 1
```text
Input: k=3, nums=[4,5,8,2], add(3), add(5), add(10)
Output: 4,5,5
Explanation: Maintain min-heap of k largest seen values.
```

## 8. Edge Cases
- Initial nums size smaller than k.
- Duplicate values.
- Negative values.

## 9. Brute Force Approach Explanation
After each add, sort all numbers and pick kth largest. Too slow for stream.

## 10. Optimal Approach Explanation
Maintain min-heap of size k. Heap top is kth largest.

## 11. Step-by-Step Logic
1. Push initial numbers while trimming heap to size k.
2. On add, push val and pop if heap size > k.
3. Return heap.peek().

## 12. Time Complexity Analysis
O(log k) per add.

## 13. Space Complexity Analysis
O(k).

## 14. Clean and Production-Quality Java Code
```java
import java.util.*;

class KthLargest {
    private final int k;
    private final PriorityQueue<Integer> minHeap = new PriorityQueue<>();

    public KthLargest(int k, int[] nums) {
        this.k = k;
        for (int num : nums) {
            add(num);
        }
    }

    public int add(int val) {
        minHeap.offer(val);
        if (minHeap.size() > k) {
            minHeap.poll();
        }
        return minHeap.peek();
    }
}
```

## 15. Dry Run Example
k=3, heap keeps [4,5,8]. add(10) -> heap [5,8,10], kth largest=5.

## 16. Common Interview Follow-Up Questions
- How to get median in a stream instead of kth largest?
- How to support dynamic k changes?

## 17. Alternative Solutions if available
- Balanced BST / TreeMap with counts.

## 18. Real Interview Context (why companies ask this problem)
Common streaming heap design pattern for real-time ranking queries.
