package leetcode.binarysearch;

import java.util.*;

/**
 * LeetCode 34: Find First and Last Position of Element in Sorted Array
 * 
 * Given an array of integers nums sorted in non-decreasing order, find the starting 
 * and ending position of a given target value.
 * If target is not found in the array, return [-1, -1].
 * 
 * You must write an algorithm with O(log n) runtime complexity.
 * 
 * Example:
 * Input: nums = [5,7,7,8,8,8,10], target = 8
 * Output: [3,5]
 */
public class FindFirstLastPosition {
    
    /**
     * Approach 1: Two Binary Searches
     * Time: O(log n), Space: O(1)
     * 
     * Find leftmost and rightmost positions separately
     */
    public int[] searchRange(int[] nums, int target) {
        if (nums == null || nums.length == 0) {
            return new int[]{-1, -1};
        }
        
        int first = findFirst(nums, target);
        if (first == -1) {
            return new int[]{-1, -1};
        }
        
        int last = findLast(nums, target);
        return new int[]{first, last};
    }
    
    private int findFirst(int[] nums, int target) {
        int left = 0, right = nums.length - 1;
        int result = -1;
        
        while (left <= right) {
            int mid = left + (right - left) / 2;
            
            if (nums[mid] == target) {
                result = mid;
                right = mid - 1; // Continue searching left
            } else if (nums[mid] < target) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        
        return result;
    }
    
    private int findLast(int[] nums, int target) {
        int left = 0, right = nums.length - 1;
        int result = -1;
        
        while (left <= right) {
            int mid = left + (right - left) / 2;
            
            if (nums[mid] == target) {
                result = mid;
                left = mid + 1; // Continue searching right
            } else if (nums[mid] < target) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        
        return result;
    }
    
    /**
     * Approach 2: Find Boundaries with Lower/Upper Bound
     * Time: O(log n), Space: O(1)
     * Use lower_bound and upper_bound concepts
     */
    public int[] searchRangeBounds(int[] nums, int target) {
        if (nums == null || nums.length == 0) {
            return new int[]{-1, -1};
        }
        
        int left = lowerBound(nums, target);
        int right = upperBound(nums, target) - 1;
        
        if (left < nums.length && nums[left] == target) {
            return new int[]{left, right};
        }
        
        return new int[]{-1, -1};
    }
    
    // Find first position where nums[i] >= target
    private int lowerBound(int[] nums, int target) {
        int left = 0, right = nums.length;
        
        while (left < right) {
            int mid = left + (right - left) / 2;
            
            if (nums[mid] < target) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }
        
        return left;
    }
    
    // Find first position where nums[i] > target
    private int upperBound(int[] nums, int target) {
        int left = 0, right = nums.length;
        
        while (left < right) {
            int mid = left + (right - left) / 2;
            
            if (nums[mid] <= target) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }
        
        return left;
    }
    
    /**
     * Approach 3: Unified Binary Search
     * Time: O(log n), Space: O(1)
     * Single function with flag for left/right boundary
     */
    public int[] searchRangeUnified(int[] nums, int target) {
        if (nums == null || nums.length == 0) {
            return new int[]{-1, -1};
        }
        
        int first = binarySearch(nums, target, true);
        if (first == -1) {
            return new int[]{-1, -1};
        }
        
        int last = binarySearch(nums, target, false);
        return new int[]{first, last};
    }
    
    private int binarySearch(int[] nums, int target, boolean findFirst) {
        int left = 0, right = nums.length - 1;
        int result = -1;
        
        while (left <= right) {
            int mid = left + (right - left) / 2;
            
            if (nums[mid] == target) {
                result = mid;
                if (findFirst) {
                    right = mid - 1;
                } else {
                    left = mid + 1;
                }
            } else if (nums[mid] < target) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        
        return result;
    }
    
    /**
     * Extended Problem: Count Occurrences
     * Count how many times target appears in array
     */
    public int countOccurrences(int[] nums, int target) {
        int[] range = searchRange(nums, target);
        if (range[0] == -1) {
            return 0;
        }
        return range[1] - range[0] + 1;
    }
    
    /**
     * Extended Problem: Find Range of All Elements
     * Find ranges for all unique elements in array
     */
    public List<int[]> findAllRanges(int[] nums) {
        List<int[]> result = new ArrayList<>();
        if (nums == null || nums.length == 0) {
            return result;
        }
        
        int i = 0;
        while (i < nums.length) {
            int start = i;
            while (i < nums.length - 1 && nums[i] == nums[i + 1]) {
                i++;
            }
            result.add(new int[]{start, i});
            i++;
        }
        
        return result;
    }
    
    /**
     * Extended Problem: Find K-th Occurrence
     * Find position of k-th occurrence of target (1-indexed)
     */
    public int findKthOccurrence(int[] nums, int target, int k) {
        int first = findFirst(nums, target);
        if (first == -1) {
            return -1;
        }
        
        int kthPosition = first + k - 1;
        if (kthPosition < nums.length && nums[kthPosition] == target) {
            return kthPosition;
        }
        
        return -1;
    }
    
    /**
     * Extended Problem: Find Element Range in 2D Matrix
     * Find range in row-wise and column-wise sorted matrix
     */
    public int[] searchRangeIn2D(int[][] matrix, int target) {
        if (matrix == null || matrix.length == 0) {
            return new int[]{-1, -1};
        }
        
        int rows = matrix.length;
        int cols = matrix[0].length;
        
        // Find first occurrence
        int first = -1;
        for (int i = 0; i < rows && first == -1; i++) {
            int pos = findFirst(matrix[i], target);
            if (pos != -1) {
                first = i * cols + pos;
            }
        }
        
        if (first == -1) {
            return new int[]{-1, -1};
        }
        
        // Find last occurrence
        int last = -1;
        for (int i = rows - 1; i >= 0 && last == -1; i--) {
            int pos = findLast(matrix[i], target);
            if (pos != -1) {
                last = i * cols + pos;
            }
        }
        
        return new int[]{first, last};
    }
    
    /**
     * Extended Problem: Find Range with Tolerance
     * Find range of elements within tolerance of target
     */
    public int[] searchRangeWithTolerance(int[] nums, int target, int tolerance) {
        int lowerTarget = target - tolerance;
        int upperTarget = target + tolerance;
        
        int first = lowerBound(nums, lowerTarget);
        int last = upperBound(nums, upperTarget) - 1;
        
        if (first <= last && first < nums.length) {
            return new int[]{first, last};
        }
        
        return new int[]{-1, -1};
    }
    
    /**
     * Utility: Validate Result
     */
    public boolean validateRange(int[] nums, int target, int[] range) {
        if (range[0] == -1 && range[1] == -1) {
            // Check target doesn't exist
            for (int num : nums) {
                if (num == target) return false;
            }
            return true;
        }
        
        // Check boundaries
        if (range[0] < 0 || range[1] >= nums.length || range[0] > range[1]) {
            return false;
        }
        
        // Check all elements in range are target
        for (int i = range[0]; i <= range[1]; i++) {
            if (nums[i] != target) return false;
        }
        
        // Check elements outside range are not target
        if (range[0] > 0 && nums[range[0] - 1] == target) return false;
        if (range[1] < nums.length - 1 && nums[range[1] + 1] == target) return false;
        
        return true;
    }
    
    // Test cases
    public static void main(String[] args) {
        FindFirstLastPosition solution = new FindFirstLastPosition();
        
        // Test case 1: Multiple occurrences
        int[] nums1 = {5, 7, 7, 8, 8, 8, 10};
        int target1 = 8;
        System.out.println("Test 1 - Array: " + Arrays.toString(nums1) + ", Target: " + target1);
        int[] result1 = solution.searchRange(nums1, target1);
        System.out.println("Range: " + Arrays.toString(result1)); // [3, 5]
        System.out.println("Bounds approach: " + Arrays.toString(solution.searchRangeBounds(nums1, target1)));
        System.out.println("Unified approach: " + Arrays.toString(solution.searchRangeUnified(nums1, target1)));
        System.out.println("Validate: " + solution.validateRange(nums1, target1, result1));
        
        // Test case 2: Single occurrence
        int[] nums2 = {5, 7, 7, 8, 8, 8, 10};
        int target2 = 5;
        System.out.println("\nTest 2 - Target: " + target2);
        int[] result2 = solution.searchRange(nums2, target2);
        System.out.println("Range: " + Arrays.toString(result2)); // [0, 0]
        
        // Test case 3: Not found
        int[] nums3 = {5, 7, 7, 8, 8, 8, 10};
        int target3 = 6;
        System.out.println("\nTest 3 - Target: " + target3);
        int[] result3 = solution.searchRange(nums3, target3);
        System.out.println("Range: " + Arrays.toString(result3)); // [-1, -1]
        
        // Test case 4: Empty array
        int[] nums4 = {};
        System.out.println("\nTest 4 - Empty array:");
        System.out.println("Range: " + Arrays.toString(solution.searchRange(nums4, 1))); // [-1, -1]
        
        // Test extended problems
        System.out.println("\nExtended Problems:");
        System.out.println("Count occurrences of 8: " + solution.countOccurrences(nums1, 8)); // 3
        System.out.println("2nd occurrence of 8: " + solution.findKthOccurrence(nums1, 8, 2)); // 4
        
        System.out.println("All ranges:");
        List<int[]> allRanges = solution.findAllRanges(nums1);
        for (int[] range : allRanges) {
            System.out.println("  " + Arrays.toString(range));
        }
        
        // Test with tolerance
        int[] range = solution.searchRangeWithTolerance(nums1, 8, 1);
        System.out.println("Range with tolerance ±1 around 8: " + Arrays.toString(range));
    }
} 