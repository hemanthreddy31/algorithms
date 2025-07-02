package leetcode.linkedlist;

import java.util.*;

/**
 * LeetCode 142: Linked List Cycle II
 * 
 * Given the head of a linked list, return the node where the cycle begins.
 * If there is no cycle, return null.
 * 
 * There is a cycle in a linked list if there is some node in the list that can be reached again
 * by continuously following the next pointer.
 * 
 * Do not modify the linked list.
 * 
 * Example:
 * Input: head = [3,2,0,-4], pos = 1
 * Output: tail connects to node index 1
 */
public class LinkedListCycleII {
    
    /**
     * Definition for singly-linked list node
     */
    static class ListNode {
        int val;
        ListNode next;
        
        ListNode(int x) {
            val = x;
            next = null;
        }
    }
    
    /**
     * Approach 1: Floyd's Cycle Detection Algorithm (Optimal)
     * Time Complexity: O(n) - Two passes through the list
     * Space Complexity: O(1) - Only using two pointers
     * 
     * Algorithm:
     * 1. Phase 1: Use fast/slow pointers to detect if cycle exists
     * 2. Phase 2: Find the start of the cycle
     * 3. Mathematical proof: When pointers meet, start from head and meeting point
     */
    public ListNode detectCycle(ListNode head) {
        if (head == null || head.next == null) {
            return null;
        }
        
        // Phase 1: Detect cycle using Floyd's algorithm
        ListNode slow = head;
        ListNode fast = head;
        
        // Find meeting point
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
            
            if (slow == fast) {
                break; // Cycle detected
            }
        }
        
        // No cycle found
        if (fast == null || fast.next == null) {
            return null;
        }
        
        // Phase 2: Find cycle start
        // Move one pointer to head, keep other at meeting point
        // Move both at same speed until they meet
        ListNode start = head;
        while (start != slow) {
            start = start.next;
            slow = slow.next;
        }
        
        return start; // This is the cycle start node
    }
    
    /**
     * Approach 2: HashSet Solution
     * Time Complexity: O(n) - Single pass
     * Space Complexity: O(n) - HashSet storage
     * 
     * Algorithm:
     * 1. Track all visited nodes in a HashSet
     * 2. First node that appears twice is the cycle start
     */
    public ListNode detectCycleHashSet(ListNode head) {
        Set<ListNode> visited = new HashSet<>();
        
        ListNode current = head;
        while (current != null) {
            if (visited.contains(current)) {
                return current; // Cycle start found
            }
            visited.add(current);
            current = current.next;
        }
        
        return null; // No cycle
    }
    
    /**
     * Extended Problem: Linked List Cycle I
     * LeetCode 141: Just detect if cycle exists (boolean)
     */
    public boolean hasCycle(ListNode head) {
        if (head == null || head.next == null) {
            return false;
        }
        
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
    
    /**
     * Extended Problem: Find Cycle Length
     * Return the length of the cycle (0 if no cycle)
     */
    public int getCycleLength(ListNode head) {
        if (head == null || head.next == null) {
            return 0;
        }
        
        ListNode slow = head;
        ListNode fast = head;
        
        // Detect cycle
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
            
            if (slow == fast) {
                // Calculate cycle length
                int length = 1;
                ListNode current = slow.next;
                while (current != slow) {
                    current = current.next;
                    length++;
                }
                return length;
            }
        }
        
        return 0; // No cycle
    }
    
    /**
     * Extended Problem: Remove Cycle
     * Remove the cycle if it exists, return head of modified list
     */
    public ListNode removeCycle(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        
        ListNode slow = head;
        ListNode fast = head;
        
        // Detect cycle
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
            
            if (slow == fast) {
                break;
            }
        }
        
        // No cycle
        if (fast == null || fast.next == null) {
            return head;
        }
        
        // Find cycle start
        ListNode start = head;
        ListNode prev = null;
        
        while (start != slow) {
            prev = slow;
            start = start.next;
            slow = slow.next;
        }
        
        // Find the node just before cycle start
        ListNode cyclePrev = slow;
        while (cyclePrev.next != start) {
            cyclePrev = cyclePrev.next;
        }
        
        // Break the cycle
        cyclePrev.next = null;
        
        return head;
    }
    
    /**
     * Extended Problem: Find Intersection of Two Linked Lists
     * LeetCode 160: Find where two linked lists intersect
     */
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        if (headA == null || headB == null) {
            return null;
        }
        
        ListNode pA = headA;
        ListNode pB = headB;
        
        // When one pointer reaches end, switch to other list's head
        // This ensures both pointers travel the same distance
        while (pA != pB) {
            pA = (pA == null) ? headB : pA.next;
            pB = (pB == null) ? headA : pB.next;
        }
        
        return pA; // Either intersection node or null
    }
    
    /**
     * Extended Problem: Find Middle Node
     * Use fast/slow pointers to find middle node
     */
    public ListNode findMiddle(ListNode head) {
        if (head == null) {
            return null;
        }
        
        ListNode slow = head;
        ListNode fast = head;
        
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        
        return slow;
    }
    
    /**
     * Extended Problem: Check if Linked List is Palindrome
     * LeetCode 234: Use fast/slow pointers + reverse
     */
    public boolean isPalindrome(ListNode head) {
        if (head == null || head.next == null) {
            return true;
        }
        
        // Find middle
        ListNode slow = head;
        ListNode fast = head;
        
        while (fast.next != null && fast.next.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        
        // Reverse second half
        ListNode secondHalf = reverseList(slow.next);
        
        // Compare first and second half
        ListNode firstHalf = head;
        while (secondHalf != null) {
            if (firstHalf.val != secondHalf.val) {
                return false;
            }
            firstHalf = firstHalf.next;
            secondHalf = secondHalf.next;
        }
        
        return true;
    }
    
    private ListNode reverseList(ListNode head) {
        ListNode prev = null;
        ListNode current = head;
        
        while (current != null) {
            ListNode next = current.next;
            current.next = prev;
            prev = current;
            current = next;
        }
        
        return prev;
    }
    
    // Helper method to create a cycle for testing
    private static ListNode createListWithCycle(int[] values, int pos) {
        if (values.length == 0) return null;
        
        ListNode head = new ListNode(values[0]);
        ListNode current = head;
        ListNode cycleNode = (pos == 0) ? head : null;
        
        for (int i = 1; i < values.length; i++) {
            current.next = new ListNode(values[i]);
            current = current.next;
            
            if (i == pos) {
                cycleNode = current;
            }
        }
        
        // Create cycle
        if (pos >= 0 && pos < values.length) {
            current.next = cycleNode;
        }
        
        return head;
    }
    
    // Helper method to create normal list
    private static ListNode createList(int[] values) {
        if (values.length == 0) return null;
        
        ListNode head = new ListNode(values[0]);
        ListNode current = head;
        
        for (int i = 1; i < values.length; i++) {
            current.next = new ListNode(values[i]);
            current = current.next;
        }
        
        return head;
    }
    
    // Helper method to print list (careful with cycles!)
    private static void printList(ListNode head, int maxNodes) {
        if (head == null) {
            System.out.println("[]");
            return;
        }
        
        System.out.print("[");
        ListNode current = head;
        int count = 0;
        
        while (current != null && count < maxNodes) {
            System.out.print(current.val);
            if (current.next != null && count < maxNodes - 1) {
                System.out.print(",");
            }
            current = current.next;
            count++;
        }
        
        if (count == maxNodes && current != null) {
            System.out.print("...");
        }
        
        System.out.println("]");
    }
    
    // Test the solutions
    public static void main(String[] args) {
        LinkedListCycleII solution = new LinkedListCycleII();
        
        // Test case 1: List with cycle
        System.out.println("Test Case 1: List with cycle at position 1");
        int[] values1 = {3, 2, 0, -4};
        ListNode cycleList1 = createListWithCycle(values1, 1);
        
        System.out.print("List (first 10 nodes): ");
        printList(cycleList1, 10);
        
        ListNode cycleStart1 = solution.detectCycle(cycleList1);
        System.out.println("Cycle start value (Floyd's): " + 
                          (cycleStart1 != null ? cycleStart1.val : "null"));
        
        ListNode cycleStart2 = solution.detectCycleHashSet(createListWithCycle(values1, 1));
        System.out.println("Cycle start value (HashSet): " + 
                          (cycleStart2 != null ? cycleStart2.val : "null"));
        
        // Test case 2: List without cycle
        System.out.println("\nTest Case 2: List without cycle");
        int[] values2 = {1, 2, 3, 4, 5};
        ListNode noCycleList = createList(values2);
        
        System.out.print("List: ");
        printList(noCycleList, 10);
        
        ListNode cycleStart3 = solution.detectCycle(noCycleList);
        System.out.println("Cycle start: " + (cycleStart3 != null ? cycleStart3.val : "null"));
        
        // Test case 3: Single node with self-cycle
        System.out.println("\nTest Case 3: Single node with self-cycle");
        ListNode singleNode = new ListNode(1);
        singleNode.next = singleNode;
        
        ListNode cycleStart4 = solution.detectCycle(singleNode);
        System.out.println("Cycle start value: " + 
                          (cycleStart4 != null ? cycleStart4.val : "null"));
        
        // Test extended problems
        System.out.println("\nExtended Problems:");
        
        // Has cycle
        ListNode testList1 = createListWithCycle(new int[]{1, 2, 3, 4}, 1);
        System.out.println("Has cycle: " + solution.hasCycle(testList1));
        
        ListNode testList2 = createList(new int[]{1, 2, 3, 4});
        System.out.println("Has cycle: " + solution.hasCycle(testList2));
        
        // Cycle length
        ListNode testList3 = createListWithCycle(new int[]{1, 2, 3, 4, 5}, 2);
        System.out.println("Cycle length: " + solution.getCycleLength(testList3));
        
        // Find middle
        ListNode testList4 = createList(new int[]{1, 2, 3, 4, 5});
        ListNode middle = solution.findMiddle(testList4);
        System.out.println("Middle node value: " + (middle != null ? middle.val : "null"));
        
        // Is palindrome
        ListNode palindrome1 = createList(new int[]{1, 2, 2, 1});
        System.out.println("Is palindrome [1,2,2,1]: " + solution.isPalindrome(palindrome1));
        
        ListNode palindrome2 = createList(new int[]{1, 2, 3, 2, 1});
        System.out.println("Is palindrome [1,2,3,2,1]: " + solution.isPalindrome(palindrome2));
    }
} 