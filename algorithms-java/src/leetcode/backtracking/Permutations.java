package leetcode.backtracking;

import java.util.*;

/**
 * LeetCode 46: Permutations
 * 
 * Given an array nums of distinct integers, return all the possible permutations.
 * You can return the answer in any order.
 * 
 * Example:
 * Input: nums = [1,2,3]
 * Output: [[1,2,3],[1,3,2],[2,1,3],[2,3,1],[3,1,2],[3,2,1]]
 */
public class Permutations {
    
    /**
     * Approach 1: Backtracking with Used Array
     * Time: O(n! * n), Space: O(n)
     * Classic backtracking approach
     */
    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        List<Integer> current = new ArrayList<>();
        boolean[] used = new boolean[nums.length];
        backtrack(nums, current, used, result);
        return result;
    }
    
    private void backtrack(int[] nums, List<Integer> current, 
                          boolean[] used, List<List<Integer>> result) {
        // Base case: permutation is complete
        if (current.size() == nums.length) {
            result.add(new ArrayList<>(current));
            return;
        }
        
        // Try each unused number
        for (int i = 0; i < nums.length; i++) {
            if (!used[i]) {
                // Choose
                current.add(nums[i]);
                used[i] = true;
                
                // Explore
                backtrack(nums, current, used, result);
                
                // Unchoose (backtrack)
                current.remove(current.size() - 1);
                used[i] = false;
            }
        }
    }
    
    /**
     * Approach 2: Backtracking with Swapping
     * Time: O(n! * n), Space: O(n)
     * Swap elements to generate permutations
     */
    public List<List<Integer>> permuteSwap(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        backtrackSwap(nums, 0, result);
        return result;
    }
    
    private void backtrackSwap(int[] nums, int start, List<List<Integer>> result) {
        // Base case: reached end of array
        if (start == nums.length) {
            List<Integer> permutation = new ArrayList<>();
            for (int num : nums) {
                permutation.add(num);
            }
            result.add(permutation);
            return;
        }
        
        // Try each element as the current position
        for (int i = start; i < nums.length; i++) {
            // Swap current element to start position
            swap(nums, start, i);
            
            // Recurse for remaining positions
            backtrackSwap(nums, start + 1, result);
            
            // Backtrack: restore original order
            swap(nums, start, i);
        }
    }
    
    private void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
    
    /**
     * Approach 3: Iterative (Building permutations)
     * Time: O(n! * n), Space: O(n!)
     */
    public List<List<Integer>> permuteIterative(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        result.add(new ArrayList<>()); // Start with empty permutation
        
        for (int num : nums) {
            List<List<Integer>> newResult = new ArrayList<>();
            
            for (List<Integer> perm : result) {
                // Insert current number at each possible position
                for (int i = 0; i <= perm.size(); i++) {
                    List<Integer> newPerm = new ArrayList<>(perm);
                    newPerm.add(i, num);
                    newResult.add(newPerm);
                }
            }
            
            result = newResult;
        }
        
        return result;
    }
    
    /**
     * Approach 4: Heap's Algorithm
     * Time: O(n!), Space: O(n)
     * Efficient algorithm for generating all permutations
     */
    public List<List<Integer>> permuteHeaps(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        heapsAlgorithm(nums, nums.length, result);
        return result;
    }
    
    private void heapsAlgorithm(int[] nums, int k, List<List<Integer>> result) {
        if (k == 1) {
            List<Integer> permutation = new ArrayList<>();
            for (int num : nums) {
                permutation.add(num);
            }
            result.add(permutation);
            return;
        }
        
        for (int i = 0; i < k; i++) {
            heapsAlgorithm(nums, k - 1, result);
            
            // If k is odd, swap first and last element
            // If k is even, swap ith and last element
            if (k % 2 == 1) {
                swap(nums, 0, k - 1);
            } else {
                swap(nums, i, k - 1);
            }
        }
    }
    
    /**
     * Extended Problem: Permutations II (LC 47)
     * Handle duplicate elements
     */
    public List<List<Integer>> permuteUnique(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        Arrays.sort(nums); // Sort to handle duplicates
        boolean[] used = new boolean[nums.length];
        backtrackUnique(nums, new ArrayList<>(), used, result);
        return result;
    }
    
    private void backtrackUnique(int[] nums, List<Integer> current, 
                                boolean[] used, List<List<Integer>> result) {
        if (current.size() == nums.length) {
            result.add(new ArrayList<>(current));
            return;
        }
        
        for (int i = 0; i < nums.length; i++) {
            if (used[i]) continue;
            
            // Skip duplicates: if current element is same as previous and 
            // previous is not used, skip current element
            if (i > 0 && nums[i] == nums[i-1] && !used[i-1]) {
                continue;
            }
            
            current.add(nums[i]);
            used[i] = true;
            
            backtrackUnique(nums, current, used, result);
            
            current.remove(current.size() - 1);
            used[i] = false;
        }
    }
    
    /**
     * Extended Problem: Next Permutation (LC 31)
     * Find lexicographically next permutation
     */
    public void nextPermutation(int[] nums) {
        int i = nums.length - 2;
        
        // Find first decreasing element from right
        while (i >= 0 && nums[i] >= nums[i + 1]) {
            i--;
        }
        
        if (i >= 0) {
            // Find element just larger than nums[i]
            int j = nums.length - 1;
            while (nums[j] <= nums[i]) {
                j--;
            }
            
            // Swap them
            swap(nums, i, j);
        }
        
        // Reverse the suffix
        reverse(nums, i + 1);
    }
    
    private void reverse(int[] nums, int start) {
        int end = nums.length - 1;
        while (start < end) {
            swap(nums, start, end);
            start++;
            end--;
        }
    }
    
    /**
     * Extended Problem: Permutation Sequence (LC 60)
     * Find kth permutation sequence
     */
    public String getPermutation(int n, int k) {
        List<Integer> numbers = new ArrayList<>();
        int[] factorial = new int[n + 1];
        
        // Build factorial array and numbers list
        factorial[0] = 1;
        for (int i = 1; i <= n; i++) {
            factorial[i] = factorial[i - 1] * i;
            numbers.add(i);
        }
        
        k--; // Convert to 0-indexed
        StringBuilder result = new StringBuilder();
        
        for (int i = 1; i <= n; i++) {
            int index = k / factorial[n - i];
            result.append(numbers.remove(index));
            k %= factorial[n - i];
        }
        
        return result.toString();
    }
    
    /**
     * Extended Problem: Count Permutations with specific property
     * Count permutations where no element is at its original position (Derangements)
     */
    public int countDerangements(int n) {
        if (n == 0) return 1;
        if (n == 1) return 0;
        
        // dp[i] = number of derangements of i elements
        int[] dp = new int[n + 1];
        dp[0] = 1;
        dp[1] = 0;
        
        for (int i = 2; i <= n; i++) {
            dp[i] = (i - 1) * (dp[i - 1] + dp[i - 2]);
        }
        
        return dp[n];
    }
    
    // Test
    public static void main(String[] args) {
        Permutations solution = new Permutations();
        
        // Test case 1
        int[] nums1 = {1, 2, 3};
        System.out.println("Test 1 [1,2,3]:");
        System.out.println("Used array: " + solution.permute(nums1));
        System.out.println("Swapping: " + solution.permuteSwap(nums1));
        System.out.println("Iterative: " + solution.permuteIterative(nums1));
        
        // Test case 2: With duplicates
        int[] nums2 = {1, 1, 2};
        System.out.println("\nTest 2 [1,1,2] (with duplicates):");
        System.out.println("Unique permutations: " + solution.permuteUnique(nums2));
        
        // Test next permutation
        int[] nums3 = {1, 2, 3};
        solution.nextPermutation(nums3);
        System.out.println("\nNext permutation of [1,2,3]: " + Arrays.toString(nums3));
        
        // Test kth permutation
        System.out.println("3rd permutation of n=4: " + solution.getPermutation(4, 3));
        
        // Test derangements
        System.out.println("Derangements of 4 elements: " + solution.countDerangements(4));
    }
} 