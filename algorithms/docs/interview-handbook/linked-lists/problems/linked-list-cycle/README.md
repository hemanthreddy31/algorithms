# Linked List Cycle

## 1. Problem Title
Linked List Cycle

## 2. Difficulty Level (Easy / Medium / Hard)
Easy

## 3. Problem Statement
Given head of a linked list, determine if the linked list has a cycle.

## 4. Input Format
head: list head pointer.

## 5. Output Format
Return true if cycle exists, else false.

## 6. Constraints (very important for interviews)
- 0 <= number of nodes <= 10^4
- -10^5 <= Node.val <= 10^5

## 7. Example Inputs and Outputs
Example 1
```text
Input: head=[3,2,0,-4], tail connects to index 1
Output: true
Explanation: Fast pointer eventually meets slow pointer.
```

## 8. Edge Cases
- Empty list.
- Single node without self-loop.
- Single node with self-loop.

## 9. Brute Force Approach Explanation
Track visited nodes in HashSet and detect repeats.

## 10. Optimal Approach Explanation
Floyd cycle detection uses slow (1x) and fast (2x) pointers; meeting implies cycle.

## 11. Step-by-Step Logic
1. Initialize slow and fast at head.
2. Move slow by 1 and fast by 2 while fast and fast.next are not null.
3. If slow == fast, cycle exists; otherwise no cycle.

## 12. Time Complexity Analysis
O(n).

## 13. Space Complexity Analysis
O(1).

## 14. Clean and Production-Quality Java Code
```java
class Solution {
    public boolean hasCycle(ListNode head) {
        ListNode slow = head;
        ListNode fast = head;

        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
            if (slow == fast) {
                return true;
            }
        }
        return false;
    }
}
```

## 15. Dry Run Example
In cycle list, fast laps slow and both meet inside cycle.

## 16. Common Interview Follow-Up Questions
- How do you find the cycle start node?
- How do you compute cycle length?

## 17. Alternative Solutions if available
- HashSet of visited nodes (O(n) space).

## 18. Real Interview Context (why companies ask this problem)
Classic fast-slow pointer test for reasoning about pointer speeds.
