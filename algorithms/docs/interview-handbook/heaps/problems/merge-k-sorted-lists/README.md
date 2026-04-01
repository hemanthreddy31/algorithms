# Merge k Sorted Lists

## 1. Problem Title
Merge k Sorted Lists

## 2. Difficulty Level (Easy / Medium / Hard)
Hard

## 3. Problem Statement
Given an array of k sorted linked-lists, merge all the linked-lists into one sorted linked-list and return it.

## 4. Input Format
lists: array of ListNode heads.

## 5. Output Format
Return merged sorted list head.

## 6. Constraints (very important for interviews)
- k == lists.length
- 0 <= k <= 10^4
- 0 <= total nodes <= 10^4
- -10^4 <= Node.val <= 10^4

## 7. Example Inputs and Outputs
Example 1
```text
Input: lists=[[1,4,5],[1,3,4],[2,6]]
Output: [1,1,2,3,4,4,5,6]
Explanation: Heap always extracts smallest current node among k heads.
```

## 8. Edge Cases
- Empty list array.
- Some lists empty.
- All values identical.

## 9. Brute Force Approach Explanation
Collect all values, sort them, create new list. O(N log N).

## 10. Optimal Approach Explanation
Use min-heap of current list heads. Pop smallest node and push its next.

## 11. Step-by-Step Logic
1. Initialize min-heap with non-null heads.
2. Pop smallest node, append to output tail.
3. If popped node has next, push next to heap.

## 12. Time Complexity Analysis
O(N log k), N total nodes.

## 13. Space Complexity Analysis
O(k).

## 14. Clean and Production-Quality Java Code
```java
import java.util.*;

class Solution {
    public ListNode mergeKLists(ListNode[] lists) {
        PriorityQueue<ListNode> minHeap = new PriorityQueue<>(Comparator.comparingInt(a -> a.val));
        for (ListNode head : lists) {
            if (head != null) {
                minHeap.offer(head);
            }
        }

        ListNode dummy = new ListNode(0);
        ListNode tail = dummy;

        while (!minHeap.isEmpty()) {
            ListNode node = minHeap.poll();
            tail.next = node;
            tail = tail.next;
            if (node.next != null) {
                minHeap.offer(node.next);
            }
        }

        return dummy.next;
    }
}
```

## 15. Dry Run Example
Heap starts with 1,1,2. Repeatedly pop smallest and push next nodes to build sorted chain.

## 16. Common Interview Follow-Up Questions
- Can you solve using divide-and-conquer merging?
- Which is better when k is very large but each list tiny?

## 17. Alternative Solutions if available
- Pairwise merge lists divide-and-conquer O(N log k).

## 18. Real Interview Context (why companies ask this problem)
High-frequency hard problem on heap-based multiway merge.
