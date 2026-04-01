# Find Median from Data Stream

## 1. Problem Title
Find Median from Data Stream

## 2. Difficulty Level (Easy / Medium / Hard)
Hard

## 3. Problem Statement
Design a data structure that supports adding numbers and finding median efficiently.

## 4. Input Format
Operations: addNum(num), findMedian().

## 5. Output Format
findMedian returns middle value (or average of two middles).

## 6. Constraints (very important for interviews)
- -10^5 <= num <= 10^5
- At most 5 * 10^4 operations

## 7. Example Inputs and Outputs
Example 1
```text
Input: addNum(1), addNum(2), findMedian(), addNum(3), findMedian()
Output: 1.5, 2.0
Explanation: Two heaps keep lower and upper halves balanced.
```

## 8. Edge Cases
- Even and odd counts alternate.
- Negative and duplicate values.
- Large operation count.

## 9. Brute Force Approach Explanation
Maintain sorted list and insert in sorted position each time; O(n) insertion.

## 10. Optimal Approach Explanation
Use max-heap for lower half and min-heap for upper half, rebalancing sizes.

## 11. Step-by-Step Logic
1. Insert into max-heap first, then move top to min-heap.
2. If min-heap becomes larger, move one back to max-heap.
3. Median is maxHeap top (odd) or average of tops (even).

## 12. Time Complexity Analysis
addNum O(log n), findMedian O(1).

## 13. Space Complexity Analysis
O(n).

## 14. Clean and Production-Quality Java Code
```java
import java.util.*;

class MedianFinder {
    private final PriorityQueue<Integer> lower = new PriorityQueue<>(Collections.reverseOrder());
    private final PriorityQueue<Integer> upper = new PriorityQueue<>();

    public void addNum(int num) {
        lower.offer(num);
        upper.offer(lower.poll());
        if (upper.size() > lower.size()) {
            lower.offer(upper.poll());
        }
    }

    public double findMedian() {
        if (lower.size() > upper.size()) {
            return lower.peek();
        }
        return (lower.peek() + upper.peek()) / 2.0;
    }
}
```

## 15. Dry Run Example
Add 1 => lower[1]. Add 2 => lower[1],upper[2]. Median=(1+2)/2.

## 16. Common Interview Follow-Up Questions
- How to support deletion as well?
- How to compute percentile instead of median?

## 17. Alternative Solutions if available
- Order-statistics tree.

## 18. Real Interview Context (why companies ask this problem)
Streaming statistics problem testing balanced data structure design.
