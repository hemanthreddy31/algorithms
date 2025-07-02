package leetcode.twopointers;

import java.util.*;

/**
 * LeetCode 283: Move Zeroes
 * 
 * Given an integer array nums, move all 0's to the end of it while maintaining 
 * the relative order of the non-zero elements.
 * 
 * Note that you must do this in-place without making a copy of the array.
 * 
 * Example:
 * Input: nums = [0,1,0,3,12]
 * Output: [1,3,12,0,0]
 */
public class MoveZeroes {
    
    /**
     * Approach 1: Extra Array (Not optimal - for comparison)
     * Time Complexity: O(n) - Two passes
     * Space Complexity: O(n) - Extra array
     * 
     * Uses extra space, doesn't meet in-place requirement
     */
    public void moveZeroesExtraSpace(int[] nums) {
        int[] result = new int[nums.length];
        int insertPos = 0;
        
        // Copy non-zero elements first
        for (int num : nums) {
            if (num != 0) {
                result[insertPos++] = num;
            }
        }
        
        // Fill remaining positions with zeros (already initialized to 0)
        // Copy back to original array
        System.arraycopy(result, 0, nums, 0, nums.length);
    }
    
    /**
     * Approach 2: Two Pointers - Two Pass (Sub-optimal)
     * Time Complexity: O(n) - Two passes
     * Space Complexity: O(1) - In-place
     * 
     * Algorithm:
     * 1. First pass: Move all non-zero elements to the front
     * 2. Second pass: Fill remaining positions with zeros
     */
    public void moveZeroesTwoPass(int[] nums) {
        int insertPos = 0;
        
        // First pass: move non-zero elements to front
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != 0) {
                nums[insertPos++] = nums[i];
            }
        }
        
        // Second pass: fill remaining with zeros
        while (insertPos < nums.length) {
            nums[insertPos++] = 0;
        }
    }
    
    /**
     * Approach 3: Two Pointers - One Pass (Optimal)
     * Time Complexity: O(n) - Single pass
     * Space Complexity: O(1) - In-place
     * 
     * Algorithm:
     * 1. Use two pointers: one for iteration, one for next non-zero position
     * 2. When we find a non-zero element, swap it with the element at insertPos
     * 3. This maintains relative order and moves zeros to the end
     */
    public void moveZeroes(int[] nums) {
        int insertPos = 0; // Position where next non-zero element should go
        
        // Move all non-zero elements to the front
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != 0) {
                // Swap current element with element at insertPos
                int temp = nums[insertPos];
                nums[insertPos] = nums[i];
                nums[i] = temp;
                insertPos++;
            }
        }
    }
    
    /**
     * Approach 4: Optimized Single Pass
     * Time Complexity: O(n) - Single pass
     * Space Complexity: O(1) - In-place
     * 
     * Optimization: Only swap when necessary (when i != insertPos)
     */
    public void moveZeroesOptimized(int[] nums) {
        int insertPos = 0;
        
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != 0) {
                // Only swap if needed
                if (i != insertPos) {
                    nums[insertPos] = nums[i];
                    nums[i] = 0;
                }
                insertPos++;
            }
        }
    }
    
    /**
     * Approach 5: Counting Zeros
     * Time Complexity: O(n) - Two passes
     * Space Complexity: O(1) - In-place
     * 
     * Count zeros first, then rearrange
     */
    public void moveZeroesCounting(int[] nums) {
        int zeroCount = 0;
        
        // Count zeros
        for (int num : nums) {
            if (num == 0) {
                zeroCount++;
            }
        }
        
        // Move non-zeros to front
        int insertPos = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != 0) {
                nums[insertPos++] = nums[i];
            }
        }
        
        // Fill end with zeros
        for (int i = nums.length - zeroCount; i < nums.length; i++) {
            nums[i] = 0;
        }
    }
    
    /**
     * Extended Problem: Remove Element
     * LeetCode 27: Remove all instances of a specific value
     */
    public int removeElement(int[] nums, int val) {
        int insertPos = 0;
        
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != val) {
                nums[insertPos++] = nums[i];
            }
        }
        
        return insertPos; // New length
    }
    
    /**
     * Extended Problem: Remove Duplicates from Sorted Array
     * LeetCode 26: Remove duplicates in-place
     */
    public int removeDuplicates(int[] nums) {
        if (nums.length <= 1) return nums.length;
        
        int insertPos = 1; // Start from second position
        
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] != nums[i - 1]) {
                nums[insertPos++] = nums[i];
            }
        }
        
        return insertPos;
    }
    
    /**
     * Extended Problem: Sort Colors (Dutch National Flag)
     * LeetCode 75: Sort array containing only 0s, 1s, and 2s
     */
    public void sortColors(int[] nums) {
        int left = 0;      // Boundary for 0s
        int right = nums.length - 1; // Boundary for 2s
        int current = 0;   // Current position
        
        while (current <= right) {
            if (nums[current] == 0) {
                // Swap with left boundary
                swap(nums, left, current);
                left++;
                current++;
            } else if (nums[current] == 2) {
                // Swap with right boundary
                swap(nums, current, right);
                right--;
                // Don't increment current as we need to check swapped element
            } else {
                // nums[current] == 1, just move forward
                current++;
            }
        }
    }
    
    private void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
    
    /**
     * Extended Problem: Partition Array
     * Partition array such that elements < pivot come before elements >= pivot
     */
    public int partition(int[] nums, int pivot) {
        int left = 0;
        int right = nums.length - 1;
        
        while (left <= right) {
            while (left <= right && nums[left] < pivot) {
                left++;
            }
            while (left <= right && nums[right] >= pivot) {
                right--;
            }
            
            if (left < right) {
                swap(nums, left, right);
                left++;
                right--;
            }
        }
        
        return left; // Partition index
    }
    
    // Helper method to print array
    private static void printArray(int[] nums) {
        System.out.println(Arrays.toString(nums));
    }
    
    // Test the solutions
    public static void main(String[] args) {
        MoveZeroes solution = new MoveZeroes();
        
        // Test case 1: Standard case
        int[] nums1 = {0, 1, 0, 3, 12};
        System.out.println("Test Case 1: Move Zeroes");
        System.out.print("Original: ");
        printArray(nums1);
        
        solution.moveZeroes(nums1.clone());
        System.out.print("Optimal: ");
        printArray(nums1);
        
        // Compare different approaches
        int[] test = {0, 1, 0, 3, 12};
        solution.moveZeroesExtraSpace(test.clone());
        System.out.print("Extra Space: ");
        printArray(test);
        
        test = new int[]{0, 1, 0, 3, 12};
        solution.moveZeroesTwoPass(test);
        System.out.print("Two Pass: ");
        printArray(test);
        
        test = new int[]{0, 1, 0, 3, 12};
        solution.moveZeroesOptimized(test);
        System.out.print("Optimized: ");
        printArray(test);
        
        // Test case 2: All zeros
        int[] nums2 = {0, 0, 0};
        System.out.println("\nTest Case 2: All zeros");
        System.out.print("Original: ");
        printArray(nums2);
        solution.moveZeroes(nums2);
        System.out.print("Result: ");
        printArray(nums2);
        
        // Test case 3: No zeros
        int[] nums3 = {1, 2, 3};
        System.out.println("\nTest Case 3: No zeros");
        System.out.print("Original: ");
        printArray(nums3);
        solution.moveZeroes(nums3);
        System.out.print("Result: ");
        printArray(nums3);
        
        // Test extended problems
        System.out.println("\nExtended Problems:");
        
        // Remove element
        int[] nums4 = {3, 2, 2, 3};
        int val = 3;
        System.out.println("Remove Element " + val + " from " + Arrays.toString(nums4));
        int newLength = solution.removeElement(nums4, val);
        System.out.println("New length: " + newLength + ", Array: " + Arrays.toString(Arrays.copyOf(nums4, newLength)));
        
        // Remove duplicates
        int[] nums5 = {1, 1, 2, 2, 3};
        System.out.println("Remove Duplicates from " + Arrays.toString(nums5));
        newLength = solution.removeDuplicates(nums5);
        System.out.println("New length: " + newLength + ", Array: " + Arrays.toString(Arrays.copyOf(nums5, newLength)));
        
        // Sort colors
        int[] nums6 = {2, 0, 2, 1, 1, 0};
        System.out.println("Sort Colors: " + Arrays.toString(nums6));
        solution.sortColors(nums6);
        System.out.println("Sorted: " + Arrays.toString(nums6));
        
        // Partition array
        int[] nums7 = {3, 1, 4, 1, 5, 9, 2, 6};
        int pivot = 4;
        System.out.println("Partition " + Arrays.toString(nums7) + " around pivot " + pivot);
        int partitionIndex = solution.partition(nums7, pivot);
        System.out.println("Partitioned: " + Arrays.toString(nums7) + ", partition index: " + partitionIndex);
    }
} 