package leetcode.linkedlist;

/**
 * LeetCode 21: Merge Two Sorted Lists
 * 
 * You are given the heads of two sorted linked lists list1 and list2.
 * Merge the two lists in a sorted manner and return the head of the merged list.
 * The merged list should be made by splicing together the nodes of the first two lists.
 * 
 * Example:
 * Input: list1 = [1,2,4], list2 = [1,3,4]
 * Output: [1,1,2,3,4,4]
 */
public class MergeTwoSortedLists {
    
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
     * Approach 1: Iterative with Dummy Node (Optimal)
     * Time Complexity: O(m + n) - Visit each node once
     * Space Complexity: O(1) - Only using pointers
     * 
     * Algorithm:
     * 1. Create a dummy node to simplify edge cases
     * 2. Use two pointers to traverse both lists
     * 3. Always choose the smaller value
     * 4. Append remaining nodes if one list is exhausted
     */
    public ListNode mergeTwoLists(ListNode list1, ListNode list2) {
        // Create dummy node to simplify logic
        ListNode dummy = new ListNode(0);
        ListNode current = dummy;
        
        // Merge while both lists have nodes
        while (list1 != null && list2 != null) {
            if (list1.val <= list2.val) {
                current.next = list1;
                list1 = list1.next;
            } else {
                current.next = list2;
                list2 = list2.next;
            }
            current = current.next;
        }
        
        // Append remaining nodes
        if (list1 != null) {
            current.next = list1;
        } else {
            current.next = list2;
        }
        
        return dummy.next;
    }
    
    /**
     * Approach 2: Recursive Solution
     * Time Complexity: O(m + n) - Visit each node once
     * Space Complexity: O(m + n) - Recursion stack
     * 
     * Algorithm:
     * 1. Base cases: if one list is null, return the other
     * 2. Choose the smaller head and recursively merge the rest
     */
    public ListNode mergeTwoListsRecursive(ListNode list1, ListNode list2) {
        // Base cases
        if (list1 == null) return list2;
        if (list2 == null) return list1;
        
        // Recursive merge
        if (list1.val <= list2.val) {
            list1.next = mergeTwoListsRecursive(list1.next, list2);
            return list1;
        } else {
            list2.next = mergeTwoListsRecursive(list1, list2.next);
            return list2;
        }
    }
    
    /**
     * Approach 3: Iterative without Dummy Node
     * Time Complexity: O(m + n)
     * Space Complexity: O(1)
     * 
     * More complex but avoids dummy node
     */
    public ListNode mergeTwoListsNoDummy(ListNode list1, ListNode list2) {
        if (list1 == null) return list2;
        if (list2 == null) return list1;
        
        // Determine the head
        ListNode head;
        if (list1.val <= list2.val) {
            head = list1;
            list1 = list1.next;
        } else {
            head = list2;
            list2 = list2.next;
        }
        
        ListNode current = head;
        
        // Merge the rest
        while (list1 != null && list2 != null) {
            if (list1.val <= list2.val) {
                current.next = list1;
                list1 = list1.next;
            } else {
                current.next = list2;
                list2 = list2.next;
            }
            current = current.next;
        }
        
        // Append remaining nodes
        current.next = (list1 != null) ? list1 : list2;
        
        return head;
    }
    
    /**
     * Approach 4: In-place Merge (Space Optimized)
     * Time Complexity: O(m + n)
     * Space Complexity: O(1)
     * 
     * Reuse existing nodes without creating new connections
     */
    public ListNode mergeTwoListsInPlace(ListNode list1, ListNode list2) {
        if (list1 == null) return list2;
        if (list2 == null) return list1;
        
        // Ensure list1 starts with smaller value
        if (list1.val > list2.val) {
            ListNode temp = list1;
            list1 = list2;
            list2 = temp;
        }
        
        ListNode head = list1;
        
        while (list1.next != null && list2 != null) {
            if (list1.next.val <= list2.val) {
                list1 = list1.next;
            } else {
                ListNode temp = list2.next;
                list2.next = list1.next;
                list1.next = list2;
                list2 = temp;
                list1 = list1.next;
            }
        }
        
        if (list2 != null) {
            list1.next = list2;
        }
        
        return head;
    }
    
    /**
     * Extended Problem: Merge Multiple Sorted Lists
     * Merge k sorted linked lists
     */
    public ListNode mergeKLists(ListNode[] lists) {
        if (lists == null || lists.length == 0) return null;
        
        // Merge lists one by one
        ListNode result = lists[0];
        for (int i = 1; i < lists.length; i++) {
            result = mergeTwoLists(result, lists[i]);
        }
        
        return result;
    }
    
    /**
     * Extended Problem: Merge Sorted Arrays
     * Given two sorted arrays, merge them into one sorted array
     */
    public int[] mergeSortedArrays(int[] nums1, int[] nums2) {
        int[] result = new int[nums1.length + nums2.length];
        int i = 0, j = 0, k = 0;
        
        while (i < nums1.length && j < nums2.length) {
            if (nums1[i] <= nums2[j]) {
                result[k++] = nums1[i++];
            } else {
                result[k++] = nums2[j++];
            }
        }
        
        while (i < nums1.length) {
            result[k++] = nums1[i++];
        }
        
        while (j < nums2.length) {
            result[k++] = nums2[j++];
        }
        
        return result;
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
        MergeTwoSortedLists solution = new MergeTwoSortedLists();
        
        // Test case 1: Normal merge
        int[] values1 = {1, 2, 4};
        int[] values2 = {1, 3, 4};
        
        ListNode list1 = createList(values1);
        ListNode list2 = createList(values2);
        
        System.out.println("Test Case 1:");
        System.out.print("List1: ");
        printList(list1);
        System.out.print("List2: ");
        printList(list2);
        
        ListNode merged1 = solution.mergeTwoLists(createList(values1), createList(values2));
        System.out.print("Iterative: ");
        printList(merged1);
        
        ListNode merged2 = solution.mergeTwoListsRecursive(createList(values1), createList(values2));
        System.out.print("Recursive: ");
        printList(merged2);
        
        // Test case 2: Empty list merge
        System.out.println("\nTest Case 2: One empty list");
        ListNode list3 = null;
        ListNode list4 = createList(new int[]{0});
        
        System.out.print("List1: ");
        printList(list3);
        System.out.print("List2: ");
        printList(list4);
        
        ListNode merged3 = solution.mergeTwoLists(list3, list4);
        System.out.print("Merged: ");
        printList(merged3);
        
        // Test case 3: Both empty
        System.out.println("\nTest Case 3: Both empty");
        ListNode merged4 = solution.mergeTwoLists(null, null);
        System.out.print("Merged: ");
        printList(merged4);
        
        // Test case 4: Different lengths
        System.out.println("\nTest Case 4: Different lengths");
        int[] values5 = {1, 3, 5, 7, 9};
        int[] values6 = {2, 4};
        
        ListNode list5 = createList(values5);
        ListNode list6 = createList(values6);
        
        System.out.print("List1: ");
        printList(list5);
        System.out.print("List2: ");
        printList(list6);
        
        ListNode merged5 = solution.mergeTwoLists(list5, list6);
        System.out.print("Merged: ");
        printList(merged5);
    }
} 