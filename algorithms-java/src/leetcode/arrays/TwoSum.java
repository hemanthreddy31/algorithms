package leetcode.arrays;

import java.util.HashMap;
import java.util.Map;

/**
 * LeetCode 1: Two Sum
 * 
 * Given an array of integers nums and an integer target, return indices of the two numbers 
 * such that they add up to target.
 * 
 * You may assume that each input would have exactly one solution, and you may not use the 
 * same element twice.
 * 
 * Example:
 * Input: nums = [2,7,11,15], target = 9
 * Output: [0,1]
 * Explanation: Because nums[0] + nums[1] == 9, we return [0, 1].
 */
public class TwoSum {
    
    /**
     * Approach 1: Brute Force
     * Time Complexity: O(n²) - We check every pair of numbers
     * Space Complexity: O(1) - Only using constant extra space
     * 
     * Algorithm:
     * 1. Use two nested loops to check every pair of numbers
     * 2. For each element nums[i], check if any element nums[j] where j > i
     *    makes nums[i] + nums[j] = target
     * 3. Return the indices when found
     */
    public int[] twoSumBruteForce(int[] nums, int target) {
        // Iterate through each element
        for (int i = 0; i < nums.length; i++) {
            // For each element, check all elements after it
            for (int j = i + 1; j < nums.length; j++) {
                // Check if the sum equals target
                if (nums[i] + nums[j] == target) {
                    return new int[] {i, j};
                }
            }
        }
        // No solution found (though problem guarantees one exists)
        return new int[] {};
    }
    
    /**
     * Approach 2: Two-Pass Hash Table
     * Time Complexity: O(n) - We traverse the list twice
     * Space Complexity: O(n) - Hash table stores at most n elements
     * 
     * Algorithm:
     * 1. First pass: Build a hash table mapping each value to its index
     * 2. Second pass: For each element, check if (target - element) exists in the hash table
     * 3. Make sure we don't use the same element twice
     */
    public int[] twoSumTwoPass(int[] nums, int target) {
        // Create hash map to store value -> index mapping
        Map<Integer, Integer> map = new HashMap<>();
        
        // First pass: populate the hash map
        for (int i = 0; i < nums.length; i++) {
            map.put(nums[i], i);
        }
        
        // Second pass: look for complement
        for (int i = 0; i < nums.length; i++) {
            int complement = target - nums[i];
            // Check if complement exists and it's not the same element
            if (map.containsKey(complement) && map.get(complement) != i) {
                return new int[] {i, map.get(complement)};
            }
        }
        
        return new int[] {};
    }
    
    /**
     * Approach 3: One-Pass Hash Table (Optimal)
     * Time Complexity: O(n) - We traverse the list only once
     * Space Complexity: O(n) - Hash table stores at most n elements
     * 
     * Algorithm:
     * 1. While iterating through the array, check if the complement exists in the hash table
     * 2. If it exists, we found our answer
     * 3. If not, add the current element to the hash table
     * 4. This works because when we reach the second number, the first number is already in the map
     */
    public int[] twoSumOptimal(int[] nums, int target) {
        // Create hash map to store value -> index mapping
        Map<Integer, Integer> map = new HashMap<>();
        
        // Single pass through the array
        for (int i = 0; i < nums.length; i++) {
            int complement = target - nums[i];
            
            // Check if we've seen the complement before
            if (map.containsKey(complement)) {
                // Found the pair! Return indices
                return new int[] {map.get(complement), i};
            }
            
            // Add current number and its index to the map
            map.put(nums[i], i);
        }
        
        return new int[] {};
    }
    
    // Test the solutions
    public static void main(String[] args) {
        TwoSum solution = new TwoSum();
        
        // Test case 1
        int[] nums1 = {2, 7, 11, 15};
        int target1 = 9;
        
        System.out.println("Test Case 1: nums = [2,7,11,15], target = 9");
        
        int[] result1 = solution.twoSumBruteForce(nums1, target1);
        System.out.println("Brute Force: [" + result1[0] + ", " + result1[1] + "]");
        
        int[] result2 = solution.twoSumTwoPass(nums1, target1);
        System.out.println("Two Pass: [" + result2[0] + ", " + result2[1] + "]");
        
        int[] result3 = solution.twoSumOptimal(nums1, target1);
        System.out.println("Optimal: [" + result3[0] + ", " + result3[1] + "]");
        
        // Test case 2
        int[] nums2 = {3, 2, 4};
        int target2 = 6;
        
        System.out.println("\nTest Case 2: nums = [3,2,4], target = 6");
        
        result1 = solution.twoSumBruteForce(nums2, target2);
        System.out.println("Brute Force: [" + result1[0] + ", " + result1[1] + "]");
        
        result2 = solution.twoSumTwoPass(nums2, target2);
        System.out.println("Two Pass: [" + result2[0] + ", " + result2[1] + "]");
        
        result3 = solution.twoSumOptimal(nums2, target2);
        System.out.println("Optimal: [" + result3[0] + ", " + result3[1] + "]");
    }
} 