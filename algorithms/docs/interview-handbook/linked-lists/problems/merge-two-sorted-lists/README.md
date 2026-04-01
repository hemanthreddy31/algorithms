# Merge Two Sorted Lists

## 1. Problem Title
Merge Two Sorted Lists

## 2. Difficulty Level (Easy / Medium / Hard)
Easy

## 3. Problem Statement
Merge two sorted linked lists and return it as one sorted list.

## 4. Input Format
list1 and list2 heads of sorted linked lists.

## 5. Output Format
Return merged sorted list head.

## 6. Constraints (very important for interviews)
- 0 <= number of nodes in each list <= 50
- -100 <= Node.val <= 100

## 7. Example Inputs and Outputs
Example 1
```text
Input: list1=[1,2,4], list2=[1,3,4]
Output: [1,1,2,3,4,4]
Explanation: Repeatedly choose smaller current node.
```

## 8. Edge Cases
- One list empty.
- Both lists empty.
- All values in one list smaller.

## 9. Brute Force Approach Explanation
Copy values to array, sort, build new list.

## 10. Optimal Approach Explanation
Use iterative merge with dummy head, reusing original nodes.

## 11. Step-by-Step Logic
1. Create dummy and tail pointers.
2. Compare current nodes from both lists and append smaller.
3. Attach remaining list once one pointer is null.

## 12. Time Complexity Analysis
O(n + m).

## 13. Space Complexity Analysis
O(1) extra.

## 14. Clean and Production-Quality Java Code
```java
class Solution {
    public ListNode mergeTwoLists(ListNode list1, ListNode list2) {
        ListNode dummy = new ListNode(0);
        ListNode tail = dummy;

        while (list1 != null && list2 != null) {
            if (list1.val <= list2.val) {
                tail.next = list1;
                list1 = list1.next;
            } else {
                tail.next = list2;
                list2 = list2.next;
            }
            tail = tail.next;
        }

        tail.next = (list1 != null) ? list1 : list2;
        return dummy.next;
    }
}
```

## 15. Dry Run Example
1->2 and 1->3: pick first 1, then second 1, then 2, then 3.

## 16. Common Interview Follow-Up Questions
- How do you merge k sorted lists efficiently?
- Can you make it recursive?

## 17. Alternative Solutions if available
- Recursive merge O(n+m) with call stack.

## 18. Real Interview Context (why companies ask this problem)
Foundational linked-list merge used in merge sort and divide-and-conquer list problems.
