package leetcode.linkedlist;

/**
 * LeetCode 2: Add Two Numbers
 * 
 * You are given two non-empty linked lists representing two non-negative integers.
 * The digits are stored in reverse order, and each of their nodes contains a single digit.
 * Add the two numbers and return the sum as a linked list.
 * 
 * You may assume the two numbers do not contain any leading zero, except the number 0 itself.
 * 
 * Example:
 * Input: l1 = [2,4,3], l2 = [5,6,4]
 * Output: [7,0,8]
 * Explanation: 342 + 465 = 807.
 */
public class AddTwoNumbers {
    
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
     * Approach 1: Elementary Math (Optimal)
     * Time Complexity: O(max(m, n)) - Process longer list
     * Space Complexity: O(max(m, n)) - Result list length
     * 
     * Algorithm:
     * 1. Traverse both lists simultaneously
     * 2. Add corresponding digits + carry
     * 3. Handle carry propagation
     * 4. Continue until both lists are exhausted and no carry
     */
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode dummy = new ListNode(0);
        ListNode current = dummy;
        int carry = 0;
        
        while (l1 != null || l2 != null || carry != 0) {
            // Get values (0 if node is null)
            int val1 = (l1 != null) ? l1.val : 0;
            int val2 = (l2 != null) ? l2.val : 0;
            
            // Calculate sum
            int sum = val1 + val2 + carry;
            carry = sum / 10;
            int digit = sum % 10;
            
            // Create new node
            current.next = new ListNode(digit);
            current = current.next;
            
            // Move to next nodes
            if (l1 != null) l1 = l1.next;
            if (l2 != null) l2 = l2.next;
        }
        
        return dummy.next;
    }
    
    /**
     * Approach 2: Recursive Solution
     * Time Complexity: O(max(m, n))
     * Space Complexity: O(max(m, n)) - Recursion stack + result
     * 
     * Recursive approach for educational purposes
     */
    public ListNode addTwoNumbersRecursive(ListNode l1, ListNode l2) {
        return addTwoNumbersHelper(l1, l2, 0);
    }
    
    private ListNode addTwoNumbersHelper(ListNode l1, ListNode l2, int carry) {
        // Base case
        if (l1 == null && l2 == null && carry == 0) {
            return null;
        }
        
        // Get values
        int val1 = (l1 != null) ? l1.val : 0;
        int val2 = (l2 != null) ? l2.val : 0;
        
        // Calculate sum
        int sum = val1 + val2 + carry;
        int digit = sum % 10;
        int newCarry = sum / 10;
        
        // Create current node
        ListNode current = new ListNode(digit);
        
        // Recursive call for next nodes
        ListNode next1 = (l1 != null) ? l1.next : null;
        ListNode next2 = (l2 != null) ? l2.next : null;
        current.next = addTwoNumbersHelper(next1, next2, newCarry);
        
        return current;
    }
    
    /**
     * Approach 3: Convert to Numbers (Not recommended for large numbers)
     * Time Complexity: O(m + n)
     * Space Complexity: O(max(m, n))
     * 
     * Only works for numbers that fit in long/BigInteger
     */
    public ListNode addTwoNumbersConvert(ListNode l1, ListNode l2) {
        long num1 = listToNumber(l1);
        long num2 = listToNumber(l2);
        long sum = num1 + num2;
        
        return numberToList(sum);
    }
    
    private long listToNumber(ListNode head) {
        long number = 0;
        long multiplier = 1;
        
        while (head != null) {
            number += head.val * multiplier;
            multiplier *= 10;
            head = head.next;
        }
        
        return number;
    }
    
    private ListNode numberToList(long number) {
        if (number == 0) return new ListNode(0);
        
        ListNode dummy = new ListNode(0);
        ListNode current = dummy;
        
        while (number > 0) {
            current.next = new ListNode((int)(number % 10));
            current = current.next;
            number /= 10;
        }
        
        return dummy.next;
    }
    
    /**
     * Extended Problem: Add Two Numbers II
     * LeetCode 445: Numbers stored in normal order (not reversed)
     */
    public ListNode addTwoNumbersII(ListNode l1, ListNode l2) {
        // Get lengths
        int len1 = getLength(l1);
        int len2 = getLength(l2);
        
        // Pad shorter list with zeros
        if (len1 < len2) {
            l1 = padWithZeros(l1, len2 - len1);
        } else if (len2 < len1) {
            l2 = padWithZeros(l2, len1 - len2);
        }
        
        // Add recursively
        ResultWrapper result = addHelper(l1, l2);
        
        // Handle final carry
        if (result.carry == 1) {
            ListNode newHead = new ListNode(1);
            newHead.next = result.node;
            return newHead;
        }
        
        return result.node;
    }
    
    static class ResultWrapper {
        ListNode node;
        int carry;
        
        ResultWrapper(ListNode node, int carry) {
            this.node = node;
            this.carry = carry;
        }
    }
    
    private ResultWrapper addHelper(ListNode l1, ListNode l2) {
        if (l1 == null && l2 == null) {
            return new ResultWrapper(null, 0);
        }
        
        // Recursively add next nodes
        ResultWrapper result = addHelper(l1.next, l2.next);
        
        // Add current digits
        int sum = l1.val + l2.val + result.carry;
        ListNode current = new ListNode(sum % 10);
        current.next = result.node;
        
        return new ResultWrapper(current, sum / 10);
    }
    
    private int getLength(ListNode head) {
        int length = 0;
        while (head != null) {
            length++;
            head = head.next;
        }
        return length;
    }
    
    private ListNode padWithZeros(ListNode head, int count) {
        for (int i = 0; i < count; i++) {
            ListNode zero = new ListNode(0);
            zero.next = head;
            head = zero;
        }
        return head;
    }
    
    /**
     * Extended Problem: Multiply Two Numbers
     * Multiply two numbers represented as linked lists
     */
    public ListNode multiplyTwoNumbers(ListNode l1, ListNode l2) {
        long num1 = listToNumber(l1);
        long num2 = listToNumber(l2);
        long product = num1 * num2;
        
        return numberToList(product);
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
    
    // Helper method to print number representation
    private static void printAsNumber(ListNode head, String label) {
        System.out.print(label + ": ");
        if (head == null) {
            System.out.println("0");
            return;
        }
        
        // Collect digits in reverse order
        java.util.List<Integer> digits = new java.util.ArrayList<>();
        while (head != null) {
            digits.add(head.val);
            head = head.next;
        }
        
        // Print in reverse order
        for (int i = digits.size() - 1; i >= 0; i--) {
            System.out.print(digits.get(i));
        }
        System.out.println();
    }
    
    // Test the solutions
    public static void main(String[] args) {
        AddTwoNumbers solution = new AddTwoNumbers();
        
        // Test case 1: 342 + 465 = 807
        int[] values1 = {2, 4, 3}; // represents 342
        int[] values2 = {5, 6, 4}; // represents 465
        
        ListNode l1 = createList(values1);
        ListNode l2 = createList(values2);
        
        System.out.println("Test Case 1: 342 + 465 = 807");
        System.out.print("L1: ");
        printList(l1);
        printAsNumber(l1, "Number 1");
        
        System.out.print("L2: ");
        printList(l2);
        printAsNumber(l2, "Number 2");
        
        ListNode result1 = solution.addTwoNumbers(createList(values1), createList(values2));
        System.out.print("Sum: ");
        printList(result1);
        printAsNumber(result1, "Result");
        
        // Test case 2: 0 + 0 = 0
        System.out.println("\nTest Case 2: 0 + 0 = 0");
        ListNode l3 = createList(new int[]{0});
        ListNode l4 = createList(new int[]{0});
        
        ListNode result2 = solution.addTwoNumbers(l3, l4);
        System.out.print("Sum: ");
        printList(result2);
        
        // Test case 3: 9999999 + 9999 = 10009998
        System.out.println("\nTest Case 3: 9999999 + 9999 = 10009998");
        int[] values5 = {9,9,9,9,9,9,9}; // represents 9999999
        int[] values6 = {9,9,9,9};       // represents 9999
        
        ListNode l5 = createList(values5);
        ListNode l6 = createList(values6);
        
        System.out.print("L1: ");
        printList(l5);
        printAsNumber(l5, "Number 1");
        
        System.out.print("L2: ");
        printList(l6);
        printAsNumber(l6, "Number 2");
        
        ListNode result3 = solution.addTwoNumbers(l5, l6);
        System.out.print("Sum: ");
        printList(result3);
        printAsNumber(result3, "Result");
        
        // Test recursive approach
        System.out.println("\nTesting Recursive Approach:");
        ListNode result4 = solution.addTwoNumbersRecursive(createList(values1), createList(values2));
        System.out.print("Recursive Sum: ");
        printList(result4);
    }
} 