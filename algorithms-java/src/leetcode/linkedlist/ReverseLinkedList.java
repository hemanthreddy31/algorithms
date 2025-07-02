package leetcode.linkedlist;

/**
 * LeetCode 206: Reverse Linked List
 * 
 * Given the head of a singly linked list, reverse the list, and return the reversed list.
 * 
 * Example:
 * Input: head = [1,2,3,4,5]
 * Output: [5,4,3,2,1]
 */
public class ReverseLinkedList {
    
    /**
     * Definition for singly-linked list node
     */
    static class ListNode {
        int val;
        ListNode next;
        
        ListNode() {}
        
        ListNode(int val) { 
            this.val = val; 
        }
        
        ListNode(int val, ListNode next) { 
            this.val = val; 
            this.next = next; 
        }
    }
    
    /**
     * Approach 1: Iterative Solution
     * Time Complexity: O(n) - Visit each node exactly once
     * Space Complexity: O(1) - Only using three pointers
     * 
     * Algorithm:
     * 1. Use three pointers: prev, current, and next
     * 2. Traverse the list and reverse the links
     * 3. Move the pointers forward
     * 4. Return prev which becomes the new head
     * 
     * Visual representation:
     * Initial: null <- 1 -> 2 -> 3 -> 4 -> 5
     * Step 1:  null <- 1 <- 2 -> 3 -> 4 -> 5
     * Step 2:  null <- 1 <- 2 <- 3 -> 4 -> 5
     * Final:   null <- 1 <- 2 <- 3 <- 4 <- 5
     */
    public ListNode reverseListIterative(ListNode head) {
        // Initialize pointers
        ListNode prev = null;      // Previous node
        ListNode current = head;   // Current node
        
        // Traverse and reverse links
        while (current != null) {
            // Store next node before breaking the link
            ListNode next = current.next;
            
            // Reverse the link
            current.next = prev;
            
            // Move pointers forward
            prev = current;
            current = next;
        }
        
        // prev is now the new head
        return prev;
    }
    
    /**
     * Approach 2: Recursive Solution
     * Time Complexity: O(n) - Visit each node exactly once
     * Space Complexity: O(n) - Recursion stack uses O(n) space
     * 
     * Algorithm:
     * 1. Base case: if head is null or single node, return head
     * 2. Recursively reverse the rest of the list
     * 3. Put the current node at the end
     * 4. Return the new head
     * 
     * The key insight: Assume the rest of the list is already reversed
     */
    public ListNode reverseListRecursive(ListNode head) {
        // Base case: empty list or single node
        if (head == null || head.next == null) {
            return head;
        }
        
        // Recursively reverse the rest of the list
        ListNode newHead = reverseListRecursive(head.next);
        
        // Put current node at the end
        // head.next is the last node of the reversed sublist
        head.next.next = head;
        head.next = null;
        
        return newHead;
    }
    
    /**
     * Approach 3: Recursive with Helper (Tail Recursion Style)
     * Time Complexity: O(n) - Visit each node exactly once
     * Space Complexity: O(n) - Recursion stack
     * 
     * This approach mimics the iterative solution using recursion
     */
    public ListNode reverseListTailRecursive(ListNode head) {
        return reverseHelper(null, head);
    }
    
    private ListNode reverseHelper(ListNode prev, ListNode current) {
        // Base case: reached end of list
        if (current == null) {
            return prev;
        }
        
        // Save next node
        ListNode next = current.next;
        
        // Reverse the link
        current.next = prev;
        
        // Recursive call with updated pointers
        return reverseHelper(current, next);
    }
    
    /**
     * Approach 4: Using Stack
     * Time Complexity: O(n) - Two passes through the list
     * Space Complexity: O(n) - Stack stores all nodes
     * 
     * This approach is less efficient but demonstrates stack usage
     */
    public ListNode reverseListStack(ListNode head) {
        if (head == null) return null;
        
        // Push all nodes onto stack
        java.util.Stack<ListNode> stack = new java.util.Stack<>();
        ListNode current = head;
        
        while (current != null) {
            stack.push(current);
            current = current.next;
        }
        
        // Pop nodes and rebuild list
        ListNode newHead = stack.pop();
        current = newHead;
        
        while (!stack.isEmpty()) {
            current.next = stack.pop();
            current = current.next;
        }
        
        // Important: set last node's next to null
        current.next = null;
        
        return newHead;
    }
    
    /**
     * Approach 5: In-place with Dummy Node
     * Time Complexity: O(n) - Single pass
     * Space Complexity: O(1) - Only extra node for dummy
     * 
     * Uses a dummy node to simplify edge cases
     */
    public ListNode reverseListDummy(ListNode head) {
        ListNode dummy = new ListNode(0);
        dummy.next = null;
        
        while (head != null) {
            ListNode next = head.next;
            head.next = dummy.next;
            dummy.next = head;
            head = next;
        }
        
        return dummy.next;
    }
    
    // Helper method to create linked list from array
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
    
    // Helper method to print linked list
    private static void printList(ListNode head) {
        ListNode current = head;
        while (current != null) {
            System.out.print(current.val);
            if (current.next != null) {
                System.out.print(" -> ");
            }
            current = current.next;
        }
        System.out.println();
    }
    
    // Test the solutions
    public static void main(String[] args) {
        ReverseLinkedList solution = new ReverseLinkedList();
        
        // Test case 1: Normal list
        int[] values1 = {1, 2, 3, 4, 5};
        System.out.println("Test Case 1: [1,2,3,4,5]");
        
        ListNode list1 = createList(values1);
        System.out.print("Original: ");
        printList(list1);
        
        ListNode reversed1 = solution.reverseListIterative(createList(values1));
        System.out.print("Iterative: ");
        printList(reversed1);
        
        ListNode reversed2 = solution.reverseListRecursive(createList(values1));
        System.out.print("Recursive: ");
        printList(reversed2);
        
        ListNode reversed3 = solution.reverseListTailRecursive(createList(values1));
        System.out.print("Tail Recursive: ");
        printList(reversed3);
        
        // Test case 2: Single node
        int[] values2 = {1};
        System.out.println("\nTest Case 2: [1]");
        ListNode list2 = createList(values2);
        System.out.print("Original: ");
        printList(list2);
        
        ListNode reversed4 = solution.reverseListIterative(createList(values2));
        System.out.print("Reversed: ");
        printList(reversed4);
        
        // Test case 3: Empty list
        System.out.println("\nTest Case 3: []");
        ListNode list3 = null;
        ListNode reversed5 = solution.reverseListIterative(list3);
        System.out.print("Reversed: ");
        printList(reversed5);
    }
} 