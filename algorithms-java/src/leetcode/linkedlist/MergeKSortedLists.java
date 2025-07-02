package leetcode.linkedlist;

import java.util.*;

/**
 * LeetCode 23: Merge k Sorted Lists
 * 
 * You are given an array of k linked-lists lists, each linked-list is sorted in ascending order.
 * Merge all the linked-lists into one sorted linked-list and return it.
 * 
 * Example:
 * Input: lists = [[1,4,5],[1,3,4],[2,6]]
 * Output: [1,1,2,3,4,4,5,6]
 */
public class MergeKSortedLists {
    
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
     * Approach 1: Priority Queue (Min Heap) - Optimal for Space
     * Time Complexity: O(N log k) - N total nodes, k lists
     * Space Complexity: O(k) - Priority queue stores at most k nodes
     * 
     * Algorithm:
     * 1. Add first node of each list to priority queue
     * 2. Extract minimum and add its next node to queue
     * 3. Continue until queue is empty
     */
    public ListNode mergeKLists(ListNode[] lists) {
        if (lists == null || lists.length == 0) {
            return null;
        }
        
        // Priority queue to store nodes by value
        PriorityQueue<ListNode> pq = new PriorityQueue<>((a, b) -> a.val - b.val);
        
        // Add first node of each list to queue
        for (ListNode list : lists) {
            if (list != null) {
                pq.offer(list);
            }
        }
        
        ListNode dummy = new ListNode(0);
        ListNode current = dummy;
        
        // Process nodes in sorted order
        while (!pq.isEmpty()) {
            ListNode node = pq.poll();
            current.next = node;
            current = current.next;
            
            // Add next node from same list
            if (node.next != null) {
                pq.offer(node.next);
            }
        }
        
        return dummy.next;
    }
    
    /**
     * Approach 2: Divide and Conquer - Optimal Overall
     * Time Complexity: O(N log k) - N total nodes, k lists
     * Space Complexity: O(log k) - Recursion stack
     * 
     * Algorithm:
     * 1. Recursively divide lists into pairs
     * 2. Merge pairs using two-list merge
     * 3. Continue until single list remains
     */
    public ListNode mergeKListsDivideConquer(ListNode[] lists) {
        if (lists == null || lists.length == 0) {
            return null;
        }
        
        return mergeKListsHelper(lists, 0, lists.length - 1);
    }
    
    private ListNode mergeKListsHelper(ListNode[] lists, int start, int end) {
        // Base case: single list
        if (start == end) {
            return lists[start];
        }
        
        // Base case: two lists
        if (start + 1 == end) {
            return mergeTwoLists(lists[start], lists[end]);
        }
        
        // Divide and conquer
        int mid = start + (end - start) / 2;
        ListNode left = mergeKListsHelper(lists, start, mid);
        ListNode right = mergeKListsHelper(lists, mid + 1, end);
        
        return mergeTwoLists(left, right);
    }
    
    /**
     * Approach 3: Sequential Merge (Brute Force)
     * Time Complexity: O(N * k) - N total nodes, k lists
     * Space Complexity: O(1) - Only pointers
     * 
     * Algorithm:
     * 1. Start with first list
     * 2. Merge with each subsequent list one by one
     */
    public ListNode mergeKListsSequential(ListNode[] lists) {
        if (lists == null || lists.length == 0) {
            return null;
        }
        
        ListNode result = lists[0];
        
        for (int i = 1; i < lists.length; i++) {
            result = mergeTwoLists(result, lists[i]);
        }
        
        return result;
    }
    
    /**
     * Approach 4: Iterative Divide and Conquer
     * Time Complexity: O(N log k)
     * Space Complexity: O(1) - No recursion
     * 
     * Bottom-up approach without recursion
     */
    public ListNode mergeKListsIterative(ListNode[] lists) {
        if (lists == null || lists.length == 0) {
            return null;
        }
        
        // Create a list to work with
        List<ListNode> listsList = new ArrayList<>(Arrays.asList(lists));
        
        while (listsList.size() > 1) {
            List<ListNode> nextLevel = new ArrayList<>();
            
            // Merge pairs
            for (int i = 0; i < listsList.size(); i += 2) {
                ListNode list1 = listsList.get(i);
                ListNode list2 = (i + 1 < listsList.size()) ? listsList.get(i + 1) : null;
                nextLevel.add(mergeTwoLists(list1, list2));
            }
            
            listsList = nextLevel;
        }
        
        return listsList.get(0);
    }
    
    /**
     * Approach 5: Convert to Array and Sort
     * Time Complexity: O(N log N) - N total nodes
     * Space Complexity: O(N) - Array storage
     * 
     * Algorithm:
     * 1. Extract all values into array
     * 2. Sort the array
     * 3. Create new linked list from sorted array
     */
    public ListNode mergeKListsArray(ListNode[] lists) {
        List<Integer> values = new ArrayList<>();
        
        // Collect all values
        for (ListNode list : lists) {
            ListNode current = list;
            while (current != null) {
                values.add(current.val);
                current = current.next;
            }
        }
        
        // Sort values
        Collections.sort(values);
        
        // Create new linked list
        ListNode dummy = new ListNode(0);
        ListNode current = dummy;
        
        for (int val : values) {
            current.next = new ListNode(val);
            current = current.next;
        }
        
        return dummy.next;
    }
    
    /**
     * Helper: Merge Two Sorted Lists
     * Used by multiple approaches above
     */
    private ListNode mergeTwoLists(ListNode list1, ListNode list2) {
        ListNode dummy = new ListNode(0);
        ListNode current = dummy;
        
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
        
        return dummy.next;
    }
    
    /**
     * Extended Problem: Merge K Sorted Arrays
     * Merge k sorted arrays into one sorted array
     */
    public int[] mergeKSortedArrays(int[][] arrays) {
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> a[0] - b[0]);
        List<Integer> result = new ArrayList<>();
        
        // Add first element of each array to heap
        // Format: [value, arrayIndex, elementIndex]
        for (int i = 0; i < arrays.length; i++) {
            if (arrays[i].length > 0) {
                pq.offer(new int[]{arrays[i][0], i, 0});
            }
        }
        
        while (!pq.isEmpty()) {
            int[] current = pq.poll();
            int value = current[0];
            int arrayIndex = current[1];
            int elementIndex = current[2];
            
            result.add(value);
            
            // Add next element from same array
            if (elementIndex + 1 < arrays[arrayIndex].length) {
                pq.offer(new int[]{
                    arrays[arrayIndex][elementIndex + 1], 
                    arrayIndex, 
                    elementIndex + 1
                });
            }
        }
        
        return result.stream().mapToInt(i -> i).toArray();
    }
    
    /**
     * Extended Problem: Find Smallest Range Covering Elements from K Lists
     * LeetCode 632: Find smallest range that includes at least one number from each list
     */
    public int[] smallestRange(List<List<Integer>> nums) {
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> a[0] - b[0]);
        int max = Integer.MIN_VALUE;
        
        // Initialize heap with first element from each list
        for (int i = 0; i < nums.size(); i++) {
            int val = nums.get(i).get(0);
            pq.offer(new int[]{val, i, 0});
            max = Math.max(max, val);
        }
        
        int[] result = {0, Integer.MAX_VALUE};
        
        while (pq.size() == nums.size()) {
            int[] current = pq.poll();
            int val = current[0];
            int listIndex = current[1];
            int elementIndex = current[2];
            
            // Update result if current range is smaller
            if (max - val < result[1] - result[0]) {
                result[0] = val;
                result[1] = max;
            }
            
            // Add next element from same list
            if (elementIndex + 1 < nums.get(listIndex).size()) {
                int nextVal = nums.get(listIndex).get(elementIndex + 1);
                pq.offer(new int[]{nextVal, listIndex, elementIndex + 1});
                max = Math.max(max, nextVal);
            }
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
        MergeKSortedLists solution = new MergeKSortedLists();
        
        // Test case 1: Standard case
        System.out.println("Test Case 1: Merge k sorted lists");
        ListNode[] lists1 = {
            createList(new int[]{1, 4, 5}),
            createList(new int[]{1, 3, 4}),
            createList(new int[]{2, 6})
        };
        
        System.out.println("Input lists:");
        for (int i = 0; i < lists1.length; i++) {
            System.out.print("List " + i + ": ");
            printList(lists1[i]);
        }
        
        // Test different approaches
        ListNode result1 = solution.mergeKLists(copyLists(lists1));
        System.out.print("Priority Queue: ");
        printList(result1);
        
        ListNode result2 = solution.mergeKListsDivideConquer(copyLists(lists1));
        System.out.print("Divide & Conquer: ");
        printList(result2);
        
        ListNode result3 = solution.mergeKListsSequential(copyLists(lists1));
        System.out.print("Sequential: ");
        printList(result3);
        
        ListNode result4 = solution.mergeKListsIterative(copyLists(lists1));
        System.out.print("Iterative: ");
        printList(result4);
        
        // Test case 2: Empty lists
        System.out.println("\nTest Case 2: Empty input");
        ListNode[] lists2 = {};
        ListNode result5 = solution.mergeKLists(lists2);
        System.out.print("Result: ");
        printList(result5);
        
        // Test case 3: Lists with nulls
        System.out.println("\nTest Case 3: Lists with nulls");
        ListNode[] lists3 = {
            null,
            createList(new int[]{1, 2, 3}),
            null,
            createList(new int[]{4, 5, 6})
        };
        
        ListNode result6 = solution.mergeKLists(lists3);
        System.out.print("Result: ");
        printList(result6);
        
        // Test extended problems
        System.out.println("\nExtended Problems:");
        
        // Merge k sorted arrays
        int[][] arrays = {
            {1, 4, 7},
            {2, 5, 8},
            {3, 6, 9}
        };
        System.out.println("Merge k sorted arrays:");
        System.out.println("Input: " + Arrays.deepToString(arrays));
        int[] mergedArray = solution.mergeKSortedArrays(arrays);
        System.out.println("Output: " + Arrays.toString(mergedArray));
        
        // Smallest range
        List<List<Integer>> nums = Arrays.asList(
            Arrays.asList(4, 10, 15, 24, 26),
            Arrays.asList(0, 9, 12, 20),
            Arrays.asList(5, 18, 22, 30)
        );
        System.out.println("Smallest range covering all lists:");
        System.out.println("Input: " + nums);
        int[] range = solution.smallestRange(nums);
        System.out.println("Smallest range: [" + range[0] + ", " + range[1] + "]");
    }
    
    // Helper method to copy lists for testing multiple approaches
    private static ListNode[] copyLists(ListNode[] original) {
        ListNode[] copy = new ListNode[original.length];
        for (int i = 0; i < original.length; i++) {
            copy[i] = copyList(original[i]);
        }
        return copy;
    }
    
    private static ListNode copyList(ListNode head) {
        if (head == null) return null;
        
        ListNode newHead = new ListNode(head.val);
        ListNode current = newHead;
        ListNode original = head.next;
        
        while (original != null) {
            current.next = new ListNode(original.val);
            current = current.next;
            original = original.next;
        }
        
        return newHead;
    }
} 