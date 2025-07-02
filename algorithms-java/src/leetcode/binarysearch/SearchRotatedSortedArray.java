package leetcode.binarysearch;

import java.util.*;

/**
 * LeetCode 33: Search in Rotated Sorted Array
 * 
 * There is an integer array nums sorted in ascending order (with distinct values).
 * Prior to being passed to your function, nums is possibly rotated at an unknown pivot index k.
 * Given the array nums after the possible rotation and an integer target, return the index of target 
 * if it is in nums, or -1 if it is not in nums.
 * 
 * You must write an algorithm with O(log n) runtime complexity.
 * 
 * Example:
 * Input: nums = [4,5,6,7,0,1,2], target = 0
 * Output: 4
 */
public class SearchRotatedSortedArray {
    
    /**
     * Approach 1: One-Pass Binary Search
     * Time: O(log n), Space: O(1)
     * 
     * Key insight: At least one half is always sorted in rotated array
     */
    public int search(int[] nums, int target) {
        if (nums == null || nums.length == 0) {
            return -1;
        }
        
        int left = 0, right = nums.length - 1;
        
        while (left <= right) {
            int mid = left + (right - left) / 2;
            
            if (nums[mid] == target) {
                return mid;
            }
            
            // Check which half is sorted
            if (nums[left] <= nums[mid]) {
                // Left half is sorted
                if (target >= nums[left] && target < nums[mid]) {
                    right = mid - 1;
                } else {
                    left = mid + 1;
                }
            } else {
                // Right half is sorted
                if (target > nums[mid] && target <= nums[right]) {
                    left = mid + 1;
                } else {
                    right = mid - 1;
                }
            }
        }
        
        return -1;
    }
    
    /**
     * Approach 2: Two-Pass Binary Search
     * Time: O(log n), Space: O(1)
     * First find pivot, then search in appropriate half
     */
    public int searchTwoPass(int[] nums, int target) {
        if (nums == null || nums.length == 0) {
            return -1;
        }
        
        // Find pivot (smallest element)
        int pivot = findPivot(nums);
        
        // Determine which half to search
        if (target >= nums[pivot] && target <= nums[nums.length - 1]) {
            return binarySearch(nums, pivot, nums.length - 1, target);
        } else {
            return binarySearch(nums, 0, pivot - 1, target);
        }
    }
    
    private int findPivot(int[] nums) {
        int left = 0, right = nums.length - 1;
        
        while (left < right) {
            int mid = left + (right - left) / 2;
            
            if (nums[mid] > nums[right]) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }
        
        return left;
    }
    
    private int binarySearch(int[] nums, int left, int right, int target) {
        while (left <= right) {
            int mid = left + (right - left) / 2;
            
            if (nums[mid] == target) {
                return mid;
            } else if (nums[mid] < target) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        
        return -1;
    }
    
    /**
     * Extended Problem: Search in Rotated Sorted Array II (LC 81)
     * Array may contain duplicates
     */
    public boolean searchWithDuplicates(int[] nums, int target) {
        int left = 0, right = nums.length - 1;
        
        while (left <= right) {
            int mid = left + (right - left) / 2;
            
            if (nums[mid] == target) {
                return true;
            }
            
            // Handle duplicates: when nums[left] == nums[mid] == nums[right]
            if (nums[left] == nums[mid] && nums[mid] == nums[right]) {
                left++;
                right--;
            } else if (nums[left] <= nums[mid]) {
                // Left half is sorted
                if (target >= nums[left] && target < nums[mid]) {
                    right = mid - 1;
                } else {
                    left = mid + 1;
                }
            } else {
                // Right half is sorted
                if (target > nums[mid] && target <= nums[right]) {
                    left = mid + 1;
                } else {
                    right = mid - 1;
                }
            }
        }
        
        return false;
    }
    
    /**
     * Extended Problem: Find Minimum in Rotated Sorted Array (LC 153)
     */
    public int findMin(int[] nums) {
        int left = 0, right = nums.length - 1;
        
        while (left < right) {
            int mid = left + (right - left) / 2;
            
            if (nums[mid] > nums[right]) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }
        
        return nums[left];
    }
    
    /**
     * Extended Problem: Find Minimum with Duplicates (LC 154)
     */
    public int findMinWithDuplicates(int[] nums) {
        int left = 0, right = nums.length - 1;
        
        while (left < right) {
            int mid = left + (right - left) / 2;
            
            if (nums[mid] > nums[right]) {
                left = mid + 1;
            } else if (nums[mid] < nums[right]) {
                right = mid;
            } else {
                // nums[mid] == nums[right], can't determine which side
                right--;
            }
        }
        
        return nums[left];
    }
    
    /**
     * Extended Problem: Find Peak Element in Rotated Array
     */
    public int findPeakInRotated(int[] nums) {
        int left = 0, right = nums.length - 1;
        
        while (left < right) {
            int mid = left + (right - left) / 2;
            
            if (nums[mid] > nums[mid + 1]) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }
        
        return left;
    }
    
    /**
     * Extended Problem: Count Rotations
     * Count how many times array was rotated
     */
    public int countRotations(int[] nums) {
        return findPivot(nums);
    }
    
    /**
     * Extended Problem: Check if Array is Rotated Sorted
     */
    public boolean isRotatedSorted(int[] nums) {
        int count = 0;
        int n = nums.length;
        
        for (int i = 0; i < n; i++) {
            if (nums[i] > nums[(i + 1) % n]) {
                count++;
            }
        }
        
        return count <= 1;
    }
    
    /**
     * Utility: Restore Original Array
     * Given rotated array, restore original sorted array
     */
    public int[] restoreArray(int[] nums) {
        int pivot = findPivot(nums);
        int[] result = new int[nums.length];
        
        for (int i = 0; i < nums.length; i++) {
            result[i] = nums[(pivot + i) % nums.length];
        }
        
        return result;
    }
    
    // Test cases
    public static void main(String[] args) {
        SearchRotatedSortedArray solution = new SearchRotatedSortedArray();
        
        // Test case 1: Basic search
        int[] nums1 = {4, 5, 6, 7, 0, 1, 2};
        System.out.println("Test 1 - Array: " + Arrays.toString(nums1));
        System.out.println("Search for 0: " + solution.search(nums1, 0)); // 4
        System.out.println("Search for 3: " + solution.search(nums1, 3)); // -1
        System.out.println("Two-pass search for 0: " + solution.searchTwoPass(nums1, 0)); // 4
        
        // Test case 2: No rotation
        int[] nums2 = {1, 2, 3, 4, 5};
        System.out.println("\nTest 2 - No rotation: " + Arrays.toString(nums2));
        System.out.println("Search for 3: " + solution.search(nums2, 3)); // 2
        
        // Test case 3: Single element
        int[] nums3 = {1};
        System.out.println("\nTest 3 - Single element: " + Arrays.toString(nums3));
        System.out.println("Search for 1: " + solution.search(nums3, 1)); // 0
        
        // Test case 4: With duplicates
        int[] nums4 = {2, 5, 6, 0, 0, 1, 2};
        System.out.println("\nTest 4 - With duplicates: " + Arrays.toString(nums4));
        System.out.println("Search for 0 (with dups): " + solution.searchWithDuplicates(nums4, 0)); // true
        System.out.println("Search for 3 (with dups): " + solution.searchWithDuplicates(nums4, 3)); // false
        
        // Test minimum finding
        System.out.println("\nMinimum in rotated array: " + solution.findMin(nums1)); // 0
        System.out.println("Minimum with duplicates: " + solution.findMinWithDuplicates(nums4)); // 0
        
        // Test rotation count
        System.out.println("Rotation count: " + solution.countRotations(nums1)); // 4
        System.out.println("Is rotated sorted: " + solution.isRotatedSorted(nums1)); // true
        
        // Test array restoration
        System.out.println("Original array: " + Arrays.toString(solution.restoreArray(nums1))); // [0,1,2,4,5,6,7]
    }
} 