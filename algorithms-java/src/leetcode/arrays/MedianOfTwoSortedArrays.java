package leetcode.arrays;

import java.util.Arrays;

/**
 * LeetCode 4: Median of Two Sorted Arrays
 * 
 * Given two sorted arrays nums1 and nums2 of size m and n respectively,
 * return the median of the two sorted arrays.
 * 
 * The overall run time complexity should be O(log (m+n)).
 * 
 * Example:
 * Input: nums1 = [1,3], nums2 = [2]
 * Output: 2.00000
 * Explanation: merged array = [1,2,3] and median is 2.
 */
public class MedianOfTwoSortedArrays {
    
    /**
     * Approach 1: Merge and Find Median (Brute Force)
     * Time Complexity: O(m + n) - Merge both arrays
     * Space Complexity: O(m + n) - Store merged array
     * 
     * Simple but doesn't meet O(log(m+n)) requirement
     */
    public double findMedianSortedArraysBruteForce(int[] nums1, int[] nums2) {
        int m = nums1.length, n = nums2.length;
        int[] merged = new int[m + n];
        
        // Merge two sorted arrays
        int i = 0, j = 0, k = 0;
        while (i < m && j < n) {
            if (nums1[i] <= nums2[j]) {
                merged[k++] = nums1[i++];
            } else {
                merged[k++] = nums2[j++];
            }
        }
        
        while (i < m) merged[k++] = nums1[i++];
        while (j < n) merged[k++] = nums2[j++];
        
        // Find median
        int totalLength = m + n;
        if (totalLength % 2 == 0) {
            return (merged[totalLength/2 - 1] + merged[totalLength/2]) / 2.0;
        } else {
            return merged[totalLength/2];
        }
    }
    
    /**
     * Approach 2: Two Pointers without Extra Space
     * Time Complexity: O(m + n) - Traverse to median position
     * Space Complexity: O(1) - No extra space
     * 
     * Better space complexity but still O(m+n) time
     */
    public double findMedianSortedArraysTwoPointers(int[] nums1, int[] nums2) {
        int m = nums1.length, n = nums2.length;
        int totalLength = m + n;
        int medianPos1 = (totalLength - 1) / 2;
        int medianPos2 = totalLength / 2;
        
        int i = 0, j = 0, count = 0;
        int median1 = 0, median2 = 0;
        
        while (count <= medianPos2) {
            int current;
            
            if (i >= m) {
                current = nums2[j++];
            } else if (j >= n) {
                current = nums1[i++];
            } else if (nums1[i] <= nums2[j]) {
                current = nums1[i++];
            } else {
                current = nums2[j++];
            }
            
            if (count == medianPos1) median1 = current;
            if (count == medianPos2) median2 = current;
            count++;
        }
        
        return (median1 + median2) / 2.0;
    }
    
    /**
     * Approach 3: Binary Search (Optimal)
     * Time Complexity: O(log(min(m,n))) - Binary search on smaller array
     * Space Complexity: O(1)
     * 
     * Algorithm:
     * 1. Partition both arrays such that left half has same elements as right half
     * 2. Ensure max(left) <= min(right)
     * 3. Binary search for correct partition
     */
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        // Ensure nums1 is the smaller array
        if (nums1.length > nums2.length) {
            return findMedianSortedArrays(nums2, nums1);
        }
        
        int m = nums1.length;
        int n = nums2.length;
        int low = 0, high = m;
        
        while (low <= high) {
            // Partition nums1
            int partitionX = (low + high) / 2;
            // Partition nums2 to balance total elements
            int partitionY = (m + n + 1) / 2 - partitionX;
            
            // Handle edge cases for partition boundaries
            int maxLeftX = (partitionX == 0) ? Integer.MIN_VALUE : nums1[partitionX - 1];
            int minRightX = (partitionX == m) ? Integer.MAX_VALUE : nums1[partitionX];
            
            int maxLeftY = (partitionY == 0) ? Integer.MIN_VALUE : nums2[partitionY - 1];
            int minRightY = (partitionY == n) ? Integer.MAX_VALUE : nums2[partitionY];
            
            // Check if we found correct partition
            if (maxLeftX <= minRightY && maxLeftY <= minRightX) {
                // We have partitioned correctly
                if ((m + n) % 2 == 0) {
                    // Even total length
                    return (Math.max(maxLeftX, maxLeftY) + 
                            Math.min(minRightX, minRightY)) / 2.0;
                } else {
                    // Odd total length
                    return Math.max(maxLeftX, maxLeftY);
                }
            } else if (maxLeftX > minRightY) {
                // We are too far right in nums1
                high = partitionX - 1;
            } else {
                // We are too far left in nums1
                low = partitionX + 1;
            }
        }
        
        throw new IllegalArgumentException("Input arrays are not sorted");
    }
    
    /**
     * Approach 4: Recursive Binary Search
     * Time Complexity: O(log(m+n))
     * Space Complexity: O(log(m+n)) - Recursion stack
     * 
     * Find kth smallest element recursively
     */
    public double findMedianSortedArraysRecursive(int[] nums1, int[] nums2) {
        int m = nums1.length, n = nums2.length;
        int total = m + n;
        
        if (total % 2 == 1) {
            return findKth(nums1, 0, nums2, 0, total / 2 + 1);
        } else {
            return (findKth(nums1, 0, nums2, 0, total / 2) + 
                    findKth(nums1, 0, nums2, 0, total / 2 + 1)) / 2.0;
        }
    }
    
    private double findKth(int[] nums1, int start1, int[] nums2, int start2, int k) {
        if (start1 >= nums1.length) return nums2[start2 + k - 1];
        if (start2 >= nums2.length) return nums1[start1 + k - 1];
        if (k == 1) return Math.min(nums1[start1], nums2[start2]);
        
        int mid1 = Integer.MAX_VALUE, mid2 = Integer.MAX_VALUE;
        if (start1 + k/2 - 1 < nums1.length) mid1 = nums1[start1 + k/2 - 1];
        if (start2 + k/2 - 1 < nums2.length) mid2 = nums2[start2 + k/2 - 1];
        
        if (mid1 < mid2) {
            return findKth(nums1, start1 + k/2, nums2, start2, k - k/2);
        } else {
            return findKth(nums1, start1, nums2, start2 + k/2, k - k/2);
        }
    }
    
    /**
     * Extended Problem: Median of K Sorted Arrays
     * Generalization to k sorted arrays
     */
    public double findMedianKSortedArrays(int[][] arrays) {
        int totalLength = 0;
        for (int[] arr : arrays) {
            totalLength += arr.length;
        }
        
        if (totalLength % 2 == 1) {
            return findKthInKArrays(arrays, totalLength / 2 + 1);
        } else {
            return (findKthInKArrays(arrays, totalLength / 2) + 
                    findKthInKArrays(arrays, totalLength / 2 + 1)) / 2.0;
        }
    }
    
    private double findKthInKArrays(int[][] arrays, int k) {
        int[] indices = new int[arrays.length];
        
        for (int count = 0; count < k; count++) {
            int minVal = Integer.MAX_VALUE;
            int minArray = -1;
            
            // Find minimum among current elements
            for (int i = 0; i < arrays.length; i++) {
                if (indices[i] < arrays[i].length && arrays[i][indices[i]] < minVal) {
                    minVal = arrays[i][indices[i]];
                    minArray = i;
                }
            }
            
            indices[minArray]++;
            
            if (count == k - 1) {
                return minVal;
            }
        }
        
        return 0;
    }
    
    /**
     * Helper: Print median calculation details
     */
    private void printMedianDetails(int[] nums1, int[] nums2, double median) {
        System.out.println("nums1: " + Arrays.toString(nums1));
        System.out.println("nums2: " + Arrays.toString(nums2));
        System.out.println("Median: " + median);
        
        // Show merged array for verification
        int[] merged = new int[nums1.length + nums2.length];
        int i = 0, j = 0, k = 0;
        while (i < nums1.length && j < nums2.length) {
            if (nums1[i] <= nums2[j]) {
                merged[k++] = nums1[i++];
            } else {
                merged[k++] = nums2[j++];
            }
        }
        while (i < nums1.length) merged[k++] = nums1[i++];
        while (j < nums2.length) merged[k++] = nums2[j++];
        
        System.out.println("Merged: " + Arrays.toString(merged));
        System.out.println();
    }
    
    // Test the solutions
    public static void main(String[] args) {
        MedianOfTwoSortedArrays solution = new MedianOfTwoSortedArrays();
        
        // Test case 1: Odd total length
        int[] nums1 = {1, 3};
        int[] nums2 = {2};
        System.out.println("Test Case 1:");
        solution.printMedianDetails(nums1, nums2, solution.findMedianSortedArrays(nums1, nums2));
        
        // Test case 2: Even total length
        int[] nums3 = {1, 2};
        int[] nums4 = {3, 4};
        System.out.println("Test Case 2:");
        solution.printMedianDetails(nums3, nums4, solution.findMedianSortedArrays(nums3, nums4));
        
        // Test case 3: Empty array
        int[] nums5 = {};
        int[] nums6 = {1};
        System.out.println("Test Case 3:");
        solution.printMedianDetails(nums5, nums6, solution.findMedianSortedArrays(nums5, nums6));
        
        // Test case 4: Different sizes
        int[] nums7 = {1, 3, 5, 7, 9};
        int[] nums8 = {2, 4, 6, 8, 10, 12};
        System.out.println("Test Case 4:");
        solution.printMedianDetails(nums7, nums8, solution.findMedianSortedArrays(nums7, nums8));
        
        // Compare all approaches
        System.out.println("Comparing approaches on Test Case 4:");
        System.out.println("Brute Force: " + solution.findMedianSortedArraysBruteForce(nums7, nums8));
        System.out.println("Two Pointers: " + solution.findMedianSortedArraysTwoPointers(nums7, nums8));
        System.out.println("Binary Search: " + solution.findMedianSortedArrays(nums7, nums8));
        System.out.println("Recursive: " + solution.findMedianSortedArraysRecursive(nums7, nums8));
        
        // Test k sorted arrays
        int[][] kArrays = {{1, 5, 9}, {2, 6, 10}, {3, 7, 11}, {4, 8, 12}};
        System.out.println("\nMedian of K Sorted Arrays:");
        System.out.println("Arrays: " + Arrays.deepToString(kArrays));
        System.out.println("Median: " + solution.findMedianKSortedArrays(kArrays));
    }
} 