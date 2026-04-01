# Reverse Linked List

## 1. Problem Title
Reverse Linked List

## 2. Difficulty Level (Easy / Medium / Hard)
Easy

## 3. Problem Statement
Given the head of a singly linked list, reverse the list and return the new head.

## 4. Input Format
head: head node of singly linked list.

## 5. Output Format
Return head of reversed linked list.

## 6. Constraints (very important for interviews)
- 0 <= number of nodes <= 5000
- -5000 <= Node.val <= 5000

## 7. Example Inputs and Outputs
Example 1
```text
Input: head = [1,2,3,4,5]
Output: [5,4,3,2,1]
Explanation: Pointers are reversed in-place.
```

## 8. Edge Cases
- Empty list.
- Single node.
- Two nodes.

## 9. Brute Force Approach Explanation
Copy node values into array and rewrite nodes in reverse order.

## 10. Optimal Approach Explanation
Iteratively reverse next pointers using prev, curr, and next references.

## 11. Step-by-Step Logic
1. Initialize prev = null and curr = head.
2. Save curr.next, point curr.next to prev.
3. Advance prev and curr until curr becomes null.

## 12. Time Complexity Analysis
O(n).

## 13. Space Complexity Analysis
O(1).

## 14. Clean and Production-Quality Java Code
```java
class Solution {
    public ListNode reverseList(ListNode head) {
        ListNode prev = null;
        ListNode curr = head;

        while (curr != null) {
            ListNode next = curr.next;
            curr.next = prev;
            prev = curr;
            curr = next;
        }

        return prev;
    }
}
```

## 15. Dry Run Example
1->2->3: after first step 1->null, then 2->1->null, then 3->2->1->null.

## 16. Common Interview Follow-Up Questions
- Can you do this recursively?
- How do you reverse nodes in groups of k?

## 17. Alternative Solutions if available
- Recursive pointer reversal.
- Value swap using stack/array (not in-place).

## 18. Real Interview Context (why companies ask this problem)
Tests pointer manipulation safety and in-place mutation ability.
