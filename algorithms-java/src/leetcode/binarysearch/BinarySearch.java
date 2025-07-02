package leetcode.binarysearch;

/**
 * LeetCode 704: Binary Search
 * 
 * Given an array of integers nums which is sorted in ascending order, and an integer target,
 * write a function to search target in nums. If target exists, then return its index. 
 * Otherwise, return -1.
 * 
 * You must write an algorithm with O(log n) runtime complexity.
 * 
 * Example:
 * Input: nums = [-1,0,3,5,9,12], target = 9
 * Output: 4
 * Explanation: 9 exists in nums and its index is 4
 */
public class BinarySearch {
    
    /**
     * Approach 1: Classic Binary Search (Iterative)
     * Time Complexity: O(log n) - Search space halved each iteration
     * Space Complexity: O(1) - Only using a few variables
     * 
     * Algorithm:
     * 1. Maintain left and right pointers
     * 2. Calculate mid point
     * 3. Compare target with mid element
     * 4. Adjust search space based on comparison
     */
    public int searchIterative(int[] nums, int target) {
        int left = 0;
        int right = nums.length - 1;
        
        while (left <= right) {
            // Prevent integer overflow
            int mid = left + (right - left) / 2;
            
            if (nums[mid] == target) {
                return mid;
            } else if (nums[mid] < target) {
                // Target is in right half
                left = mid + 1;
            } else {
                // Target is in left half
                right = mid - 1;
            }
        }
        
        return -1; // Target not found
    }
    
    /**
     * Approach 2: Binary Search (Recursive)
     * Time Complexity: O(log n) - Same as iterative
     * Space Complexity: O(log n) - Recursion stack
     * 
     * Less efficient than iterative due to function call overhead
     */
    public int searchRecursive(int[] nums, int target) {
        return binarySearchHelper(nums, target, 0, nums.length - 1);
    }
    
    private int binarySearchHelper(int[] nums, int target, int left, int right) {
        // Base case: search space exhausted
        if (left > right) {
            return -1;
        }
        
        int mid = left + (right - left) / 2;
        
        if (nums[mid] == target) {
            return mid;
        } else if (nums[mid] < target) {
            // Search right half
            return binarySearchHelper(nums, target, mid + 1, right);
        } else {
            // Search left half
            return binarySearchHelper(nums, target, left, mid - 1);
        }
    }
    
    /**
     * Variation 1: Find First Occurrence (Lower Bound)
     * If target appears multiple times, find the first occurrence
     * 
     * Time Complexity: O(log n)
     * Space Complexity: O(1)
     */
    public int findFirstOccurrence(int[] nums, int target) {
        int left = 0;
        int right = nums.length - 1;
        int result = -1;
        
        while (left <= right) {
            int mid = left + (right - left) / 2;
            
            if (nums[mid] == target) {
                result = mid; // Record position
                right = mid - 1; // Continue searching left
            } else if (nums[mid] < target) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        
        return result;
    }
    
    /**
     * Variation 2: Find Last Occurrence (Upper Bound)
     * If target appears multiple times, find the last occurrence
     * 
     * Time Complexity: O(log n)
     * Space Complexity: O(1)
     */
    public int findLastOccurrence(int[] nums, int target) {
        int left = 0;
        int right = nums.length - 1;
        int result = -1;
        
        while (left <= right) {
            int mid = left + (right - left) / 2;
            
            if (nums[mid] == target) {
                result = mid; // Record position
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
     * Variation 3: Find Insert Position
     * Find the position where target should be inserted to maintain sorted order
     * 
     * Time Complexity: O(log n)
     * Space Complexity: O(1)
     */
    public int searchInsert(int[] nums, int target) {
        int left = 0;
        int right = nums.length - 1;
        
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
        
        // left is the insert position
        return left;
    }
    
    /**
     * Variation 4: Find Closest Element
     * Find the element closest to target
     * 
     * Time Complexity: O(log n)
     * Space Complexity: O(1)
     */
    public int findClosest(int[] nums, int target) {
        if (nums.length == 0) return -1;
        
        int left = 0;
        int right = nums.length - 1;
        
        // Edge cases
        if (target <= nums[left]) return left;
        if (target >= nums[right]) return right;
        
        while (left < right - 1) {
            int mid = left + (right - left) / 2;
            
            if (nums[mid] == target) {
                return mid;
            } else if (nums[mid] < target) {
                left = mid;
            } else {
                right = mid;
            }
        }
        
        // Compare distances
        return Math.abs(nums[left] - target) <= Math.abs(nums[right] - target) ? left : right;
    }
    
    /**
     * Variation 5: Search in Rotated Sorted Array
     * LeetCode 33: Array is rotated at some pivot
     * 
     * Time Complexity: O(log n)
     * Space Complexity: O(1)
     */
    public int searchRotated(int[] nums, int target) {
        int left = 0;
        int right = nums.length - 1;
        
        while (left <= right) {
            int mid = left + (right - left) / 2;
            
            if (nums[mid] == target) {
                return mid;
            }
            
            // Determine which half is sorted
            if (nums[left] <= nums[mid]) {
                // Left half is sorted
                if (target >= nums[left] && target < nums[mid]) {
                    right = mid - 1; // Target in left half
                } else {
                    left = mid + 1; // Target in right half
                }
            } else {
                // Right half is sorted
                if (target > nums[mid] && target <= nums[right]) {
                    left = mid + 1; // Target in right half
                } else {
                    right = mid - 1; // Target in left half
                }
            }
        }
        
        return -1;
    }
    
    /**
     * Variation 6: Find Peak Element
     * LeetCode 162: Find a peak element (greater than neighbors)
     * 
     * Time Complexity: O(log n)
     * Space Complexity: O(1)
     */
    public int findPeakElement(int[] nums) {
        int left = 0;
        int right = nums.length - 1;
        
        while (left < right) {
            int mid = left + (right - left) / 2;
            
            if (nums[mid] > nums[mid + 1]) {
                // Peak is in left half (including mid)
                right = mid;
            } else {
                // Peak is in right half
                left = mid + 1;
            }
        }
        
        return left;
    }
    
    /**
     * Variation 7: Binary Search on Answer
     * Example: Find square root of x (LeetCode 69)
     * 
     * Time Complexity: O(log n)
     * Space Complexity: O(1)
     */
    public int mySqrt(int x) {
        if (x < 2) return x;
        
        long left = 2;
        long right = x / 2;
        
        while (left <= right) {
            long mid = left + (right - left) / 2;
            long square = mid * mid;
            
            if (square == x) {
                return (int) mid;
            } else if (square < x) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        
        // Return floor of square root
        return (int) right;
    }
    
    // Test the solutions
    public static void main(String[] args) {
        BinarySearch solution = new BinarySearch();
        
        // Test classic binary search
        int[] nums1 = {-1, 0, 3, 5, 9, 12};
        System.out.println("Classic Binary Search:");
        System.out.println("Array: [-1,0,3,5,9,12], Target: 9");
        System.out.println("Iterative: " + solution.searchIterative(nums1, 9));
        System.out.println("Recursive: " + solution.searchRecursive(nums1, 9));
        
        // Test with duplicates
        int[] nums2 = {1, 2, 2, 2, 3, 4, 5};
        System.out.println("\nWith Duplicates:");
        System.out.println("Array: [1,2,2,2,3,4,5], Target: 2");
        System.out.println("First occurrence: " + solution.findFirstOccurrence(nums2, 2));
        System.out.println("Last occurrence: " + solution.findLastOccurrence(nums2, 2));
        
        // Test insert position
        int[] nums3 = {1, 3, 5, 6};
        System.out.println("\nInsert Position:");
        System.out.println("Array: [1,3,5,6], Target: 5");
        System.out.println("Insert position: " + solution.searchInsert(nums3, 5));
        System.out.println("Array: [1,3,5,6], Target: 2");
        System.out.println("Insert position: " + solution.searchInsert(nums3, 2));
        
        // Test rotated array
        int[] nums4 = {4, 5, 6, 7, 0, 1, 2};
        System.out.println("\nRotated Array:");
        System.out.println("Array: [4,5,6,7,0,1,2], Target: 0");
        System.out.println("Index: " + solution.searchRotated(nums4, 0));
        
        // Test peak element
        int[] nums5 = {1, 2, 3, 1};
        System.out.println("\nPeak Element:");
        System.out.println("Array: [1,2,3,1]");
        System.out.println("Peak index: " + solution.findPeakElement(nums5));
        
        // Test square root
        System.out.println("\nSquare Root:");
        System.out.println("sqrt(8): " + solution.mySqrt(8));
        System.out.println("sqrt(16): " + solution.mySqrt(16));
    }
} 