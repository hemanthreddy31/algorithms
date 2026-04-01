# Remove Nth Node From End of List

## 1. Problem Title
Remove Nth Node From End of List

## 2. Difficulty Level (Easy / Medium / Hard)
Medium

## 3. Problem Statement
Given head of a linked list, remove the nth node from the end and return its head.

## 4. Input Format
head: list head, n: positive integer.

## 5. Output Format
Return new head after deletion.

## 6. Constraints (very important for interviews)
- The number of nodes is sz.
- 1 <= sz <= 30
- 1 <= n <= sz

## 7. Example Inputs and Outputs
Example 1
```text
Input: head=[1,2,3,4,5], n=2
Output: [1,2,3,5]
Explanation: Node with value 4 is removed.
```

## 8. Edge Cases
- Removing head node.
- Single-node list.
- n equals list length.

## 9. Brute Force Approach Explanation
Compute length, then remove (len-n+1)-th from start in second pass.

## 10. Optimal Approach Explanation
Use dummy + two pointers. Move fast n steps first, then move both until fast reaches end.

## 11. Step-by-Step Logic
1. Create dummy before head.
2. Advance fast by n nodes.
3. Move fast and slow together until fast.next is null.
4. Delete slow.next and return dummy.next.

## 12. Time Complexity Analysis
O(n).

## 13. Space Complexity Analysis
O(1).

## 14. Clean and Production-Quality Java Code
```java
class Solution {
    public ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode dummy = new ListNode(0, head);
        ListNode fast = dummy;
        ListNode slow = dummy;

        for (int i = 0; i < n; i++) {
            fast = fast.next;
        }

        while (fast.next != null) {
            fast = fast.next;
            slow = slow.next;
        }

        slow.next = slow.next.next;
        return dummy.next;
    }
}
```

## 15. Dry Run Example
head=[1,2,3], n=3: fast advances to 3rd node, loop skips, slow at dummy -> remove head.

## 16. Common Interview Follow-Up Questions
- Can this be solved without dummy node?
- How do you remove multiple nodes from end list of k values?

## 17. Alternative Solutions if available
- Two-pass length calculation approach.

## 18. Real Interview Context (why companies ask this problem)
This checks off-by-one handling and pointer gap invariants.
