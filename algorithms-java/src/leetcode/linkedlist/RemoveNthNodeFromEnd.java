package leetcode.linkedlist;

/**
 * LeetCode 19: Remove Nth Node From End of List
 * 
 * Given the head of a linked list, remove the nth node from the end of the list and return its head.
 * 
 * Example:
 * Input: head = [1,2,3,4,5], n = 2
 * Output: [1,2,3,5]
 * 
 * Follow up: Could you do this in one pass?
 */
public class RemoveNthNodeFromEnd {
    
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
     * Approach 1: Two Pass Solution
     * Time Complexity: O(L) - Two passes through the list
     * Space Complexity: O(1) - Only using pointers
     * 
     * Algorithm:
     * 1. First pass: Calculate the length of the list
     * 2. Second pass: Remove the (L-n+1)th node from the beginning
     */
    public ListNode removeNthFromEndTwoPass(ListNode head, int n) {
        // First pass: get length
        int length = 0;
        ListNode current = head;
        while (current != null) {
            length++;
            current = current.next;
        }
        
        // Edge case: remove head
        if (n == length) {
            return head.next;
        }
        
        // Second pass: find node to remove
        current = head;
        for (int i = 0; i < length - n - 1; i++) {
            current = current.next;
        }
        
        // Remove the nth node from end
        current.next = current.next.next;
        
        return head;
    }
    
    /**
     * Approach 2: Two Pointers (One Pass) - Optimal
     * Time Complexity: O(L) - Single pass through the list
     * Space Complexity: O(1) - Only using two pointers
     * 
     * Algorithm:
     * 1. Use two pointers with a gap of n nodes
     * 2. When fast pointer reaches the end, slow pointer is at the node before target
     * 3. Remove the target node
     */
    public ListNode removeNthFromEnd(ListNode head, int n) {
        // Create dummy node to handle edge cases
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        
        ListNode fast = dummy;
        ListNode slow = dummy;
        
        // Move fast pointer n+1 steps ahead
        for (int i = 0; i <= n; i++) {
            fast = fast.next;
        }
        
        // Move both pointers until fast reaches the end
        while (fast != null) {
            fast = fast.next;
            slow = slow.next;
        }
        
        // Remove the nth node from end
        slow.next = slow.next.next;
        
        return dummy.next;
    }
    
    /**
     * Approach 3: Recursive Solution
     * Time Complexity: O(L) - Visit each node once
     * Space Complexity: O(L) - Recursion stack
     * 
     * Algorithm:
     * 1. Recursively traverse to the end
     * 2. Count nodes on the way back
     * 3. Remove when count equals n
     */
    public ListNode removeNthFromEndRecursive(ListNode head, int n) {
        int[] count = {0}; // Use array to pass by reference
        return removeHelper(head, n, count);
    }
    
    private ListNode removeHelper(ListNode node, int n, int[] count) {
        if (node == null) {
            return null;
        }
        
        node.next = removeHelper(node.next, n, count);
        count[0]++;
        
        // If this is the nth node from end, remove it
        if (count[0] == n) {
            return node.next;
        }
        
        return node;
    }
    
    /**
     * Approach 4: Using Stack
     * Time Complexity: O(L) - Two passes
     * Space Complexity: O(L) - Stack storage
     * 
     * Algorithm:
     * 1. Push all nodes onto stack
     * 2. Pop n nodes to find the target
     * 3. Remove the target node
     */
    public ListNode removeNthFromEndStack(ListNode head, int n) {
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        
        java.util.Stack<ListNode> stack = new java.util.Stack<>();
        ListNode current = dummy;
        
        // Push all nodes onto stack
        while (current != null) {
            stack.push(current);
            current = current.next;
        }
        
        // Pop n nodes to get to the node before target
        for (int i = 0; i < n; i++) {
            stack.pop();
        }
        
        // Remove the target node
        ListNode prev = stack.peek();
        prev.next = prev.next.next;
        
        return dummy.next;
    }
    
    /**
     * Approach 5: Convert to Array
     * Time Complexity: O(L) - Two passes
     * Space Complexity: O(L) - Array storage
     * 
     * Algorithm:
     * 1. Convert linked list to array
     * 2. Remove the nth element from end
     * 3. Reconstruct the linked list
     */
    public ListNode removeNthFromEndArray(ListNode head, int n) {
        // Convert to array
        java.util.List<ListNode> nodes = new java.util.ArrayList<>();
        ListNode current = head;
        while (current != null) {
            nodes.add(current);
            current = current.next;
        }
        
        int size = nodes.size();
        
        // Edge case: remove head
        if (n == size) {
            return head.next;
        }
        
        // Remove the nth node from end
        int indexToRemove = size - n;
        nodes.get(indexToRemove - 1).next = nodes.get(indexToRemove).next;
        
        return head;
    }
    
    /**
     * Extended Problem: Remove Nth Node From Beginning
     * Remove the nth node from the beginning of the list
     */
    public ListNode removeNthFromBeginning(ListNode head, int n) {
        if (n == 1) {
            return head.next;
        }
        
        ListNode current = head;
        for (int i = 0; i < n - 2; i++) {
            current = current.next;
        }
        
        current.next = current.next.next;
        return head;
    }
    
    /**
     * Extended Problem: Remove All Nodes with Value
     * Remove all nodes that have the given value
     */
    public ListNode removeElements(ListNode head, int val) {
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode current = dummy;
        
        while (current.next != null) {
            if (current.next.val == val) {
                current.next = current.next.next;
            } else {
                current = current.next;
            }
        }
        
        return dummy.next;
    }
    
    /**
     * Extended Problem: Delete Middle Node
     * Delete the middle node of the linked list
     */
    public ListNode deleteMiddle(ListNode head) {
        if (head == null || head.next == null) {
            return null;
        }
        
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode slow = dummy;
        ListNode fast = head;
        
        // Fast moves 2 steps, slow moves 1 step
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        
        // Remove middle node
        slow.next = slow.next.next;
        
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
        if (head == null) {
            System.out.println("[]");
            return;
        }
        
        ListNode current = head;
        System.out.print("[");
        while (current != null) {
            System.out.print(current.val);
            if (current.next != null) {
                System.out.print(",");
            }
            current = current.next;
        }
        System.out.println("]");
    }
    
    // Test the solutions
    public static void main(String[] args) {
        RemoveNthNodeFromEnd solution = new RemoveNthNodeFromEnd();
        
        // Test case 1: Remove 2nd node from end
        int[] values1 = {1, 2, 3, 4, 5};
        int n1 = 2;
        
        System.out.println("Test Case 1: Remove " + n1 + "nd node from end");
        ListNode list1 = createList(values1);
        System.out.print("Original: ");
        printList(list1);
        
        ListNode result1 = solution.removeNthFromEnd(createList(values1), n1);
        System.out.print("Two Pointers: ");
        printList(result1);
        
        ListNode result2 = solution.removeNthFromEndTwoPass(createList(values1), n1);
        System.out.print("Two Pass: ");
        printList(result2);
        
        ListNode result3 = solution.removeNthFromEndRecursive(createList(values1), n1);
        System.out.print("Recursive: ");
        printList(result3);
        
        // Test case 2: Remove head (first node)
        System.out.println("\nTest Case 2: Remove head node");
        int[] values2 = {1, 2};
        int n2 = 2;
        
        ListNode list2 = createList(values2);
        System.out.print("Original: ");
        printList(list2);
        
        ListNode result4 = solution.removeNthFromEnd(createList(values2), n2);
        System.out.print("Result: ");
        printList(result4);
        
        // Test case 3: Single node
        System.out.println("\nTest Case 3: Single node list");
        int[] values3 = {1};
        int n3 = 1;
        
        ListNode list3 = createList(values3);
        System.out.print("Original: ");
        printList(list3);
        
        ListNode result5 = solution.removeNthFromEnd(list3, n3);
        System.out.print("Result: ");
        printList(result5);
        
        // Test case 4: Remove last node
        System.out.println("\nTest Case 4: Remove last node");
        int[] values4 = {1, 2, 3, 4, 5};
        int n4 = 1;
        
        ListNode list4 = createList(values4);
        System.out.print("Original: ");
        printList(list4);
        
        ListNode result6 = solution.removeNthFromEnd(list4, n4);
        System.out.print("Result: ");
        printList(result6);
        
        // Test extended problems
        System.out.println("\nExtended Problems:");
        
        // Remove from beginning
        ListNode list5 = createList(new int[]{1, 2, 3, 4, 5});
        System.out.print("Remove 3rd from beginning: ");
        ListNode result7 = solution.removeNthFromBeginning(list5, 3);
        printList(result7);
        
        // Remove all elements with value
        ListNode list6 = createList(new int[]{1, 2, 6, 3, 4, 5, 6});
        System.out.print("Remove all 6s: ");
        ListNode result8 = solution.removeElements(list6, 6);
        printList(result8);
        
        // Delete middle
        ListNode list7 = createList(new int[]{1, 3, 4, 7, 1, 2, 6});
        System.out.print("Delete middle: ");
        ListNode result9 = solution.deleteMiddle(list7);
        printList(result9);
    }
} 