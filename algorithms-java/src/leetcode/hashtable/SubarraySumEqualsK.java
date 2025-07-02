package leetcode.hashtable;

import java.util.*;

/**
 * LeetCode 560: Subarray Sum Equals K
 * 
 * Given an array of integers nums and an integer k, return the total number of 
 * continuous subarrays whose sum equals to k.
 * 
 * Example:
 * Input: nums = [1,1,1], k = 2
 * Output: 2
 * 
 * Input: nums = [1,2,3], k = 3
 * Output: 2
 */
public class SubarraySumEqualsK {
    
    /**
     * Approach 1: Brute Force
     * Time Complexity: O(n²) - Two nested loops
     * Space Complexity: O(1) - No extra space
     * 
     * Check all possible subarrays
     */
    public int subarraySumBruteForce(int[] nums, int k) {
        int count = 0;
        
        for (int i = 0; i < nums.length; i++) {
            int sum = 0;
            for (int j = i; j < nums.length; j++) {
                sum += nums[j];
                if (sum == k) {
                    count++;
                }
            }
        }
        
        return count;
    }
    
    /**
     * Approach 2: Prefix Sum with HashMap (Optimal)
     * Time Complexity: O(n) - Single pass
     * Space Complexity: O(n) - HashMap storage
     * 
     * Key insight: If prefixSum[j] - prefixSum[i] = k, then subarray from i+1 to j has sum k
     * So we need to find prefixSum[j] - k in our map
     */
    public int subarraySum(int[] nums, int k) {
        Map<Integer, Integer> prefixSumCount = new HashMap<>();
        prefixSumCount.put(0, 1); // Handle case where subarray starts from index 0
        
        int count = 0;
        int prefixSum = 0;
        
        for (int num : nums) {
            prefixSum += num;
            
            // Check if (prefixSum - k) exists
            // This means there's a subarray ending at current position with sum k
            if (prefixSumCount.containsKey(prefixSum - k)) {
                count += prefixSumCount.get(prefixSum - k);
            }
            
            // Add current prefix sum to map
            prefixSumCount.put(prefixSum, prefixSumCount.getOrDefault(prefixSum, 0) + 1);
        }
        
        return count;
    }
    
    /**
     * Extended Problem: Maximum Size Subarray Sum Equals K
     * LeetCode 325: Find longest subarray with sum k
     */
    public int maxSubArrayLen(int[] nums, int k) {
        Map<Integer, Integer> prefixSumIndex = new HashMap<>();
        prefixSumIndex.put(0, -1); // Handle case where subarray starts from index 0
        
        int maxLength = 0;
        int prefixSum = 0;
        
        for (int i = 0; i < nums.length; i++) {
            prefixSum += nums[i];
            
            if (prefixSumIndex.containsKey(prefixSum - k)) {
                maxLength = Math.max(maxLength, i - prefixSumIndex.get(prefixSum - k));
            }
            
            // Only add if not present (we want the leftmost occurrence)
            if (!prefixSumIndex.containsKey(prefixSum)) {
                prefixSumIndex.put(prefixSum, i);
            }
        }
        
        return maxLength;
    }
    
    /**
     * Extended Problem: Minimum Size Subarray Sum >= K
     * LeetCode 209: Find shortest subarray with sum >= k
     */
    public int minSubArrayLen(int target, int[] nums) {
        int left = 0;
        int sum = 0;
        int minLength = Integer.MAX_VALUE;
        
        for (int right = 0; right < nums.length; right++) {
            sum += nums[right];
            
            while (sum >= target) {
                minLength = Math.min(minLength, right - left + 1);
                sum -= nums[left];
                left++;
            }
        }
        
        return minLength == Integer.MAX_VALUE ? 0 : minLength;
    }
    
    /**
     * Extended Problem: Subarray Sum Divisible by K
     * LeetCode 974: Count subarrays with sum divisible by k
     */
    public int subarraysDivByK(int[] nums, int k) {
        Map<Integer, Integer> remainderCount = new HashMap<>();
        remainderCount.put(0, 1);
        
        int count = 0;
        int prefixSum = 0;
        
        for (int num : nums) {
            prefixSum += num;
            int remainder = ((prefixSum % k) + k) % k; // Handle negative remainders
            
            if (remainderCount.containsKey(remainder)) {
                count += remainderCount.get(remainder);
            }
            
            remainderCount.put(remainder, remainderCount.getOrDefault(remainder, 0) + 1);
        }
        
        return count;
    }
    
    /**
     * Extended Problem: Continuous Subarray Sum
     * LeetCode 523: Check if array has continuous subarray of size >= 2 with sum multiple of k
     */
    public boolean checkSubarraySum(int[] nums, int k) {
        Map<Integer, Integer> remainderIndex = new HashMap<>();
        remainderIndex.put(0, -1);
        
        int prefixSum = 0;
        
        for (int i = 0; i < nums.length; i++) {
            prefixSum += nums[i];
            int remainder = prefixSum % k;
            
            if (remainderIndex.containsKey(remainder)) {
                if (i - remainderIndex.get(remainder) > 1) {
                    return true;
                }
            } else {
                remainderIndex.put(remainder, i);
            }
        }
        
        return false;
    }
    
    /**
     * Extended Problem: Binary Subarray with Sum
     * LeetCode 930: Count binary subarrays with sum k
     */
    public int numSubarraysWithSum(int[] nums, int goal) {
        return subarraySum(nums, goal); // Same as original problem
    }
    
    /**
     * Helper: Print all subarrays with sum k
     */
    public void printSubarraysWithSum(int[] nums, int k) {
        System.out.println("Subarrays with sum " + k + ":");
        
        for (int i = 0; i < nums.length; i++) {
            int sum = 0;
            for (int j = i; j < nums.length; j++) {
                sum += nums[j];
                if (sum == k) {
                    System.out.print("  [" + i + ", " + j + "]: ");
                    for (int l = i; l <= j; l++) {
                        System.out.print(nums[l] + " ");
                    }
                    System.out.println();
                }
            }
        }
    }
    
    /**
     * Helper: Print all subarrays with sum k
     */
    public void printSubarraysWithSum(int[] nums, int k) {
        System.out.println("Subarrays with sum " + k + ":");
        
        for (int i = 0; i < nums.length; i++) {
            int sum = 0;
            for (int j = i; j < nums.length; j++) {
                sum += nums[j];
                if (sum == k) {
                    System.out.print("  [" + i + ", " + j + "]: ");
                    for (int l = i; l <= j; l++) {
                        System.out.print(nums[l] + " ");
                    }
                    System.out.println();
                }
            }
        }
    }
    
    // Test the solutions
    public static void main(String[] args) {
        SubarraySumEqualsK solution = new SubarraySumEqualsK();
        
        // Test case 1: Standard case
        int[] nums1 = {1, 1, 1};
        int k1 = 2;
        System.out.println("Test Case 1: nums = " + Arrays.toString(nums1) + ", k = " + k1);
        System.out.println("Brute Force: " + solution.subarraySumBruteForce(nums1, k1));
        System.out.println("Optimal: " + solution.subarraySum(nums1, k1));
        solution.printSubarraysWithSum(nums1, k1);
        
        // Test case 2: Another example
        int[] nums2 = {1, 2, 3};
        int k2 = 3;
        System.out.println("\nTest Case 2: nums = " + Arrays.toString(nums2) + ", k = " + k2);
        System.out.println("Optimal: " + solution.subarraySum(nums2, k2));
        solution.printSubarraysWithSum(nums2, k2);
        
        // Test case 3: With negative numbers
        int[] nums3 = {1, -1, 0};
        int k3 = 0;
        System.out.println("\nTest Case 3: nums = " + Arrays.toString(nums3) + ", k = " + k3);
        System.out.println("Optimal: " + solution.subarraySum(nums3, k3));
        solution.printSubarraysWithSum(nums3, k3);
        
        // Test case 4: No valid subarrays
        int[] nums4 = {1, 2, 3};
        int k4 = 7;
        System.out.println("\nTest Case 4: nums = " + Arrays.toString(nums4) + ", k = " + k4);
        System.out.println("Optimal: " + solution.subarraySum(nums4, k4));
        
        // Test extended problems
        System.out.println("\nExtended Problems:");
        
        // Maximum length subarray
        int[] nums6 = {1, -1, 5, -2, 3};
        int k6 = 3;
        System.out.println("Max length subarray with sum " + k6 + ": " + 
                          solution.maxSubArrayLen(nums6, k6));
        
        // Minimum length subarray
        int[] nums7 = {2, 3, 1, 2, 4, 3};
        int target = 7;
        System.out.println("Min length subarray with sum >= " + target + ": " + 
                          solution.minSubArrayLen(target, nums7));
        
        // Divisible by k
        int[] nums8 = {4, 5, 0, -2, -3, 1};
        int k8 = 5;
        System.out.println("Subarrays divisible by " + k8 + ": " + 
                          solution.subarraysDivByK(nums8, k8));
        
        // Continuous subarray sum
        int[] nums9 = {23, 2, 4, 6, 7};
        int k9 = 6;
        System.out.println("Has continuous subarray sum multiple of " + k9 + ": " + 
                          solution.checkSubarraySum(nums9, k9));
    }
} 