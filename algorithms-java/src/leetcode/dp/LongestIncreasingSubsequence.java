package leetcode.dp;

import java.util.*;

/**
 * LeetCode 300: Longest Increasing Subsequence
 * 
 * Given an integer array nums, return the length of the longest strictly increasing subsequence.
 * 
 * Example:
 * Input: nums = [10,9,2,5,3,7,101,18]
 * Output: 4
 * Explanation: The longest increasing subsequence is [2,3,7,18]
 */
public class LongestIncreasingSubsequence {
    
    /**
     * Approach 1: Dynamic Programming
     * Time: O(n²), Space: O(n)
     * dp[i] = length of LIS ending at index i
     */
    public int lengthOfLISDP(int[] nums) {
        if (nums.length == 0) return 0;
        
        int[] dp = new int[nums.length];
        Arrays.fill(dp, 1); // Each element forms LIS of length 1
        
        int maxLength = 1;
        
        for (int i = 1; i < nums.length; i++) {
            for (int j = 0; j < i; j++) {
                if (nums[j] < nums[i]) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }
            maxLength = Math.max(maxLength, dp[i]);
        }
        
        return maxLength;
    }
    
    /**
     * Approach 2: Binary Search (Patience Sorting)
     * Time: O(n log n), Space: O(n)
     * Maintain array of smallest tail elements for each length
     */
    public int lengthOfLISBinarySearch(int[] nums) {
        if (nums.length == 0) return 0;
        
        List<Integer> tails = new ArrayList<>();
        
        for (int num : nums) {
            // Find position to insert/replace
            int pos = binarySearch(tails, num);
            
            if (pos == tails.size()) {
                tails.add(num); // Extend sequence
            } else {
                tails.set(pos, num); // Replace with smaller value
            }
        }
        
        return tails.size();
    }
    
    private int binarySearch(List<Integer> tails, int target) {
        int left = 0, right = tails.size();
        
        while (left < right) {
            int mid = left + (right - left) / 2;
            if (tails.get(mid) < target) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }
        
        return left;
    }
    
    /**
     * Approach 3: DP with Path Reconstruction
     * Return the actual LIS, not just length
     */
    public List<Integer> longestIncreasingSubsequence(int[] nums) {
        if (nums.length == 0) return new ArrayList<>();
        
        int[] dp = new int[nums.length];
        int[] parent = new int[nums.length];
        Arrays.fill(dp, 1);
        Arrays.fill(parent, -1);
        
        int maxLength = 1;
        int maxIndex = 0;
        
        for (int i = 1; i < nums.length; i++) {
            for (int j = 0; j < i; j++) {
                if (nums[j] < nums[i] && dp[j] + 1 > dp[i]) {
                    dp[i] = dp[j] + 1;
                    parent[i] = j;
                }
            }
            
            if (dp[i] > maxLength) {
                maxLength = dp[i];
                maxIndex = i;
            }
        }
        
        // Reconstruct LIS
        List<Integer> result = new ArrayList<>();
        int current = maxIndex;
        
        while (current != -1) {
            result.add(nums[current]);
            current = parent[current];
        }
        
        Collections.reverse(result);
        return result;
    }
    
    /**
     * Extended Problem: Number of LIS (LC 673)
     * Count how many LIS exist
     */
    public int findNumberOfLIS(int[] nums) {
        if (nums.length == 0) return 0;
        
        int[] lengths = new int[nums.length];
        int[] counts = new int[nums.length];
        Arrays.fill(lengths, 1);
        Arrays.fill(counts, 1);
        
        int maxLength = 1;
        
        for (int i = 1; i < nums.length; i++) {
            for (int j = 0; j < i; j++) {
                if (nums[j] < nums[i]) {
                    if (lengths[j] + 1 > lengths[i]) {
                        lengths[i] = lengths[j] + 1;
                        counts[i] = counts[j];
                    } else if (lengths[j] + 1 == lengths[i]) {
                        counts[i] += counts[j];
                    }
                }
            }
            maxLength = Math.max(maxLength, lengths[i]);
        }
        
        int result = 0;
        for (int i = 0; i < nums.length; i++) {
            if (lengths[i] == maxLength) {
                result += counts[i];
            }
        }
        
        return result;
    }
    
    /**
     * Extended Problem: Longest Decreasing Subsequence
     */
    public int lengthOfLDS(int[] nums) {
        if (nums.length == 0) return 0;
        
        int[] dp = new int[nums.length];
        Arrays.fill(dp, 1);
        
        int maxLength = 1;
        
        for (int i = 1; i < nums.length; i++) {
            for (int j = 0; j < i; j++) {
                if (nums[j] > nums[i]) { // Changed condition for decreasing
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }
            maxLength = Math.max(maxLength, dp[i]);
        }
        
        return maxLength;
    }
    
    /**
     * Extended Problem: Longest Bitonic Subsequence
     * Increasing then decreasing
     */
    public int longestBitonicSubsequence(int[] nums) {
        if (nums.length == 0) return 0;
        
        int n = nums.length;
        int[] increasing = new int[n];
        int[] decreasing = new int[n];
        Arrays.fill(increasing, 1);
        Arrays.fill(decreasing, 1);
        
        // Calculate LIS ending at each position
        for (int i = 1; i < n; i++) {
            for (int j = 0; j < i; j++) {
                if (nums[j] < nums[i]) {
                    increasing[i] = Math.max(increasing[i], increasing[j] + 1);
                }
            }
        }
        
        // Calculate LDS starting at each position
        for (int i = n - 2; i >= 0; i--) {
            for (int j = i + 1; j < n; j++) {
                if (nums[i] > nums[j]) {
                    decreasing[i] = Math.max(decreasing[i], decreasing[j] + 1);
                }
            }
        }
        
        int maxLength = 0;
        for (int i = 0; i < n; i++) {
            maxLength = Math.max(maxLength, increasing[i] + decreasing[i] - 1);
        }
        
        return maxLength;
    }
    
    /**
     * Extended Problem: Russian Doll Envelopes (LC 354)
     * 2D version of LIS
     */
    public int maxEnvelopes(int[][] envelopes) {
        Arrays.sort(envelopes, (a, b) -> {
            if (a[0] == b[0]) {
                return b[1] - a[1]; // Decreasing height for same width
            }
            return a[0] - b[0]; // Increasing width
        });
        
        // Extract heights and find LIS
        int[] heights = new int[envelopes.length];
        for (int i = 0; i < envelopes.length; i++) {
            heights[i] = envelopes[i][1];
        }
        
        return lengthOfLISBinarySearch(heights);
    }
    
    /**
     * Extended Problem: Minimum number of operations to make array strictly increasing
     */
    public int minOperations(int[] nums) {
        List<Integer> tails = new ArrayList<>();
        
        for (int num : nums) {
            int pos = binarySearchGreaterEqual(tails, num);
            
            if (pos == tails.size()) {
                tails.add(num);
            } else {
                tails.set(pos, num);
            }
        }
        
        return nums.length - tails.size(); // Elements to remove
    }
    
    private int binarySearchGreaterEqual(List<Integer> tails, int target) {
        int left = 0, right = tails.size();
        
        while (left < right) {
            int mid = left + (right - left) / 2;
            if (tails.get(mid) < target) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }
        
        return left;
    }
    
    // Test
    public static void main(String[] args) {
        LongestIncreasingSubsequence solution = new LongestIncreasingSubsequence();
        
        // Test case 1
        int[] nums1 = {10, 9, 2, 5, 3, 7, 101, 18};
        System.out.println("Test 1 [10,9,2,5,3,7,101,18]:");
        System.out.println("DP length: " + solution.lengthOfLISDP(nums1));
        System.out.println("Binary Search length: " + solution.lengthOfLISBinarySearch(nums1));
        System.out.println("Actual LIS: " + solution.longestIncreasingSubsequence(nums1));
        System.out.println("Number of LIS: " + solution.findNumberOfLIS(nums1));
        
        // Test case 2
        int[] nums2 = {0, 1, 0, 3, 2, 3};
        System.out.println("\nTest 2 [0,1,0,3,2,3]:");
        System.out.println("LIS length: " + solution.lengthOfLISDP(nums2));
        System.out.println("Number of LIS: " + solution.findNumberOfLIS(nums2));
        
        // Test extended problems
        int[] nums3 = {1, 11, 2, 10, 4, 5, 2, 1};
        System.out.println("\nBitonic subsequence [1,11,2,10,4,5,2,1]: " + 
                          solution.longestBitonicSubsequence(nums3));
        
        // Test envelopes
        int[][] envelopes = {{5,4},{6,4},{6,7},{2,3}};
        System.out.println("Russian Dolls: " + solution.maxEnvelopes(envelopes));
    }
} 