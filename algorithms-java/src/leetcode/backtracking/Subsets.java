package leetcode.backtracking;

import java.util.*;

/**
 * LeetCode 78: Subsets
 * 
 * Given an integer array nums of unique elements, return all possible subsets (the power set).
 * The solution set must not contain duplicate subsets. Return the solution in any order.
 * 
 * Example:
 * Input: nums = [1,2,3]
 * Output: [[],[1],[2],[1,2],[3],[1,3],[2,3],[1,2,3]]
 */
public class Subsets {
    
    /**
     * Approach 1: Backtracking (Classic)
     * Time Complexity: O(n * 2^n) - Generate 2^n subsets, each takes O(n) to copy
     * Space Complexity: O(n) - Recursion stack depth
     * 
     * Algorithm:
     * 1. For each element, we have two choices: include it or exclude it
     * 2. Use backtracking to explore all possibilities
     * 3. Add current subset when we've made decision for all elements
     */
    public List<List<Integer>> subsetsBacktracking(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        List<Integer> currentSubset = new ArrayList<>();
        backtrack(nums, 0, currentSubset, result);
        return result;
    }
    
    private void backtrack(int[] nums, int start, List<Integer> currentSubset, 
                          List<List<Integer>> result) {
        // Add current subset to result (make a copy)
        result.add(new ArrayList<>(currentSubset));
        
        // Try adding each remaining element
        for (int i = start; i < nums.length; i++) {
            // Include nums[i]
            currentSubset.add(nums[i]);
            
            // Recurse with nums[i] included
            backtrack(nums, i + 1, currentSubset, result);
            
            // Backtrack: remove nums[i]
            currentSubset.remove(currentSubset.size() - 1);
        }
    }
    
    /**
     * Approach 2: Iterative (Building subsets incrementally)
     * Time Complexity: O(n * 2^n)
     * Space Complexity: O(1) - No recursion
     * 
     * Algorithm:
     * 1. Start with empty subset
     * 2. For each number, add it to all existing subsets to create new subsets
     * 3. Add new subsets to result
     */
    public List<List<Integer>> subsetsIterative(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        result.add(new ArrayList<>()); // Start with empty subset
        
        for (int num : nums) {
            int size = result.size();
            // For each existing subset, create a new subset by adding current number
            for (int i = 0; i < size; i++) {
                List<Integer> newSubset = new ArrayList<>(result.get(i));
                newSubset.add(num);
                result.add(newSubset);
            }
        }
        
        return result;
    }
    
    /**
     * Approach 3: Bit Manipulation
     * Time Complexity: O(n * 2^n)
     * Space Complexity: O(1)
     * 
     * Algorithm:
     * 1. Each subset corresponds to a binary number from 0 to 2^n - 1
     * 2. If bit i is set in the binary number, include nums[i] in subset
     * 3. Generate all 2^n binary numbers to get all subsets
     */
    public List<List<Integer>> subsetsBitManipulation(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        int n = nums.length;
        int totalSubsets = 1 << n; // 2^n
        
        for (int i = 0; i < totalSubsets; i++) {
            List<Integer> subset = new ArrayList<>();
            
            // Check each bit position
            for (int j = 0; j < n; j++) {
                // If j-th bit is set, include nums[j]
                if ((i & (1 << j)) != 0) {
                    subset.add(nums[j]);
                }
            }
            
            result.add(subset);
        }
        
        return result;
    }
    
    /**
     * Approach 4: Recursive without explicit backtracking
     * Time Complexity: O(n * 2^n)
     * Space Complexity: O(n) - Recursion stack
     * 
     * Algorithm:
     * 1. For each element, generate subsets with and without it
     * 2. Combine results from both recursive calls
     */
    public List<List<Integer>> subsetsRecursive(int[] nums) {
        return subsetsHelper(nums, 0);
    }
    
    private List<List<Integer>> subsetsHelper(int[] nums, int index) {
        List<List<Integer>> result = new ArrayList<>();
        
        // Base case: no more elements to consider
        if (index == nums.length) {
            result.add(new ArrayList<>());
            return result;
        }
        
        // Get all subsets without current element
        List<List<Integer>> subsetsWithout = subsetsHelper(nums, index + 1);
        
        // Add all subsets without current element
        result.addAll(subsetsWithout);
        
        // Add all subsets with current element
        for (List<Integer> subset : subsetsWithout) {
            List<Integer> subsetWith = new ArrayList<>(subset);
            subsetWith.add(0, nums[index]); // Add at beginning to maintain order
            result.add(subsetWith);
        }
        
        return result;
    }
    
    /**
     * Approach 5: Using Queue (BFS style)
     * Time Complexity: O(n * 2^n)
     * Space Complexity: O(2^n) - Queue size
     */
    public List<List<Integer>> subsetsQueue(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        Queue<List<Integer>> queue = new LinkedList<>();
        queue.offer(new ArrayList<>()); // Start with empty subset
        
        for (int num : nums) {
            int size = queue.size();
            
            for (int i = 0; i < size; i++) {
                List<Integer> subset = queue.poll();
                
                // Add subset without current number
                result.add(new ArrayList<>(subset));
                
                // Create and enqueue subset with current number
                subset.add(num);
                queue.offer(subset);
            }
        }
        
        // Add remaining subsets in queue
        while (!queue.isEmpty()) {
            result.add(queue.poll());
        }
        
        return result;
    }
    
    /**
     * Extended Problem: Subsets with Duplicates (LeetCode 90)
     * Given an array that may contain duplicates
     */
    public List<List<Integer>> subsetsWithDup(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        Arrays.sort(nums); // Sort to handle duplicates
        backtrackWithDup(nums, 0, new ArrayList<>(), result);
        return result;
    }
    
    private void backtrackWithDup(int[] nums, int start, List<Integer> currentSubset,
                                 List<List<Integer>> result) {
        result.add(new ArrayList<>(currentSubset));
        
        for (int i = start; i < nums.length; i++) {
            // Skip duplicates
            if (i > start && nums[i] == nums[i - 1]) {
                continue;
            }
            
            currentSubset.add(nums[i]);
            backtrackWithDup(nums, i + 1, currentSubset, result);
            currentSubset.remove(currentSubset.size() - 1);
        }
    }
    
    /**
     * Extended Problem: Subsets of size k
     * Return all subsets of exactly size k
     */
    public List<List<Integer>> subsetsOfSizeK(int[] nums, int k) {
        List<List<Integer>> result = new ArrayList<>();
        backtrackSizeK(nums, 0, k, new ArrayList<>(), result);
        return result;
    }
    
    private void backtrackSizeK(int[] nums, int start, int k, 
                               List<Integer> currentSubset, List<List<Integer>> result) {
        if (currentSubset.size() == k) {
            result.add(new ArrayList<>(currentSubset));
            return;
        }
        
        // Optimization: if not enough elements left, return
        if (currentSubset.size() + (nums.length - start) < k) {
            return;
        }
        
        for (int i = start; i < nums.length; i++) {
            currentSubset.add(nums[i]);
            backtrackSizeK(nums, i + 1, k, currentSubset, result);
            currentSubset.remove(currentSubset.size() - 1);
        }
    }
    
    // Helper method to print result
    private static void printResult(List<List<Integer>> result) {
        System.out.print("[");
        for (int i = 0; i < result.size(); i++) {
            System.out.print(result.get(i));
            if (i < result.size() - 1) System.out.print(", ");
        }
        System.out.println("]");
    }
    
    // Test the solutions
    public static void main(String[] args) {
        Subsets solution = new Subsets();
        
        // Test case 1
        int[] nums1 = {1, 2, 3};
        System.out.println("Test Case 1: nums = [1,2,3]");
        
        System.out.print("Backtracking: ");
        printResult(solution.subsetsBacktracking(nums1));
        
        System.out.print("Iterative: ");
        printResult(solution.subsetsIterative(nums1));
        
        System.out.print("Bit Manipulation: ");
        printResult(solution.subsetsBitManipulation(nums1));
        
        System.out.print("Recursive: ");
        printResult(solution.subsetsRecursive(nums1));
        
        // Test case 2: Empty array
        int[] nums2 = {};
        System.out.println("\nTest Case 2: nums = []");
        System.out.print("Backtracking: ");
        printResult(solution.subsetsBacktracking(nums2));
        
        // Test case 3: With duplicates
        int[] nums3 = {1, 2, 2};
        System.out.println("\nTest Case 3: nums = [1,2,2] (with duplicates)");
        System.out.print("Subsets with Dup: ");
        printResult(solution.subsetsWithDup(nums3));
        
        // Test case 4: Subsets of size k
        int[] nums4 = {1, 2, 3, 4};
        System.out.println("\nTest Case 4: nums = [1,2,3,4], k = 2");
        System.out.print("Subsets of size 2: ");
        printResult(solution.subsetsOfSizeK(nums4, 2));
    }
} 