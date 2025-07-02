package leetcode.hashtable;

import java.util.*;

/**
 * LeetCode 217: Contains Duplicate
 * 
 * Given an integer array nums, return true if any value appears at least twice in the array,
 * and return false if every element is distinct.
 * 
 * Example:
 * Input: nums = [1,2,3,1]
 * Output: true
 * 
 * Input: nums = [1,2,3,4]
 * Output: false
 */
public class ContainsDuplicate {
    
    /**
     * Approach 1: Brute Force
     * Time Complexity: O(n²) - Check every pair
     * Space Complexity: O(1) - No extra space
     * 
     * Compare every element with every other element
     */
    public boolean containsDuplicateBruteForce(int[] nums) {
        for (int i = 0; i < nums.length - 1; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                if (nums[i] == nums[j]) {
                    return true;
                }
            }
        }
        return false;
    }
    
    /**
     * Approach 2: Sorting
     * Time Complexity: O(n log n) - Sorting dominates
     * Space Complexity: O(1) - In-place sorting (or O(n) if not in-place)
     * 
     * Sort array and check adjacent elements
     */
    public boolean containsDuplicateSorting(int[] nums) {
        Arrays.sort(nums);
        
        for (int i = 0; i < nums.length - 1; i++) {
            if (nums[i] == nums[i + 1]) {
                return true;
            }
        }
        return false;
    }
    
    /**
     * Approach 3: HashSet (Optimal)
     * Time Complexity: O(n) - Single pass
     * Space Complexity: O(n) - HashSet storage
     * 
     * Use HashSet to track seen elements
     */
    public boolean containsDuplicate(int[] nums) {
        Set<Integer> seen = new HashSet<>();
        
        for (int num : nums) {
            if (seen.contains(num)) {
                return true;
            }
            seen.add(num);
        }
        return false;
    }
    
    /**
     * Approach 4: HashSet with Early Termination
     * Time Complexity: O(n) - Single pass
     * Space Complexity: O(n) - HashSet storage
     * 
     * Optimized version using HashSet.add() return value
     */
    public boolean containsDuplicateOptimized(int[] nums) {
        Set<Integer> seen = new HashSet<>();
        
        for (int num : nums) {
            if (!seen.add(num)) {
                return true; // add() returns false if element already exists
            }
        }
        return false;
    }
    
    /**
     * Approach 5: HashMap with Frequency Count
     * Time Complexity: O(n) - Single pass
     * Space Complexity: O(n) - HashMap storage
     * 
     * Count frequencies and check if any > 1
     */
    public boolean containsDuplicateHashMap(int[] nums) {
        Map<Integer, Integer> frequency = new HashMap<>();
        
        for (int num : nums) {
            frequency.put(num, frequency.getOrDefault(num, 0) + 1);
            if (frequency.get(num) > 1) {
                return true;
            }
        }
        return false;
    }
    
    /**
     * Extended Problem: Contains Duplicate II
     * LeetCode 219: Check if duplicate exists within k distance
     */
    public boolean containsNearbyDuplicate(int[] nums, int k) {
        Map<Integer, Integer> map = new HashMap<>();
        
        for (int i = 0; i < nums.length; i++) {
            if (map.containsKey(nums[i])) {
                int prevIndex = map.get(nums[i]);
                if (i - prevIndex <= k) {
                    return true;
                }
            }
            map.put(nums[i], i);
        }
        return false;
    }
    
    /**
     * Extended Problem: Contains Duplicate III
     * LeetCode 220: Check if duplicate exists within k distance and value difference <= t
     */
    public boolean containsNearbyAlmostDuplicate(int[] nums, int k, int t) {
        if (k < 1 || t < 0) return false;
        
        TreeSet<Long> set = new TreeSet<>();
        
        for (int i = 0; i < nums.length; i++) {
            long current = nums[i];
            
            // Check if there's a number in [current-t, current+t] range
            Long floor = set.floor(current + t);
            if (floor != null && floor >= current - t) {
                return true;
            }
            
            set.add(current);
            
            // Maintain window size of k
            if (i >= k) {
                set.remove((long) nums[i - k]);
            }
        }
        
        return false;
    }
    
    /**
     * Extended Problem: Find All Duplicates
     * LeetCode 442: Find all numbers that appear twice (nums[i] in [1,n])
     */
    public List<Integer> findDuplicates(int[] nums) {
        List<Integer> result = new ArrayList<>();
        
        // Use array indices as hash (since nums[i] in [1,n])
        for (int i = 0; i < nums.length; i++) {
            int index = Math.abs(nums[i]) - 1;
            
            if (nums[index] < 0) {
                // Already marked, this is a duplicate
                result.add(Math.abs(nums[i]));
            } else {
                // Mark as visited by making negative
                nums[index] = -nums[index];
            }
        }
        
        return result;
    }
    
    /**
     * Extended Problem: Single Number
     * LeetCode 136: Find the single number (all others appear twice)
     */
    public int singleNumber(int[] nums) {
        int result = 0;
        
        // XOR all numbers - duplicates cancel out
        for (int num : nums) {
            result ^= num;
        }
        
        return result;
    }
    
    /**
     * Extended Problem: Find Missing Number
     * LeetCode 268: Find missing number in [0,n] range
     */
    public int missingNumber(int[] nums) {
        Set<Integer> numSet = new HashSet<>();
        
        for (int num : nums) {
            numSet.add(num);
        }
        
        // Find missing number
        for (int i = 0; i <= nums.length; i++) {
            if (!numSet.contains(i)) {
                return i;
            }
        }
        
        return -1; // Should never reach here
    }
    
    /**
     * Extended Problem: First Missing Positive
     * LeetCode 41: Find smallest missing positive integer
     */
    public int firstMissingPositive(int[] nums) {
        int n = nums.length;
        
        // Use array itself as hash table
        // Place each number i at index i-1 if possible
        for (int i = 0; i < n; i++) {
            while (nums[i] > 0 && nums[i] <= n && nums[nums[i] - 1] != nums[i]) {
                // Swap nums[i] to its correct position
                int temp = nums[nums[i] - 1];
                nums[nums[i] - 1] = nums[i];
                nums[i] = temp;
            }
        }
        
        // Find first missing positive
        for (int i = 0; i < n; i++) {
            if (nums[i] != i + 1) {
                return i + 1;
            }
        }
        
        return n + 1;
    }
    
    // Test the solutions
    public static void main(String[] args) {
        ContainsDuplicate solution = new ContainsDuplicate();
        
        // Test case 1: Contains duplicate
        int[] nums1 = {1, 2, 3, 1};
        System.out.println("Test Case 1: " + Arrays.toString(nums1));
        System.out.println("Brute Force: " + solution.containsDuplicateBruteForce(nums1.clone()));
        System.out.println("Sorting: " + solution.containsDuplicateSorting(nums1.clone()));
        System.out.println("HashSet: " + solution.containsDuplicate(nums1.clone()));
        System.out.println("Optimized: " + solution.containsDuplicateOptimized(nums1.clone()));
        System.out.println("HashMap: " + solution.containsDuplicateHashMap(nums1.clone()));
        
        // Test case 2: No duplicates
        int[] nums2 = {1, 2, 3, 4};
        System.out.println("\nTest Case 2: " + Arrays.toString(nums2));
        System.out.println("HashSet: " + solution.containsDuplicate(nums2));
        
        // Test case 3: Empty array
        int[] nums3 = {};
        System.out.println("\nTest Case 3: " + Arrays.toString(nums3));
        System.out.println("HashSet: " + solution.containsDuplicate(nums3));
        
        // Test case 4: Single element
        int[] nums4 = {1};
        System.out.println("\nTest Case 4: " + Arrays.toString(nums4));
        System.out.println("HashSet: " + solution.containsDuplicate(nums4));
        
        // Test extended problems
        System.out.println("\nExtended Problems:");
        
        // Contains Duplicate II
        int[] nums5 = {1, 2, 3, 1};
        int k = 3;
        System.out.println("Contains Nearby Duplicate (k=" + k + "): " + 
                          solution.containsNearbyDuplicate(nums5, k));
        
        // Contains Duplicate III
        int[] nums6 = {1, 2, 3, 1};
        int k2 = 3, t = 0;
        System.out.println("Contains Nearby Almost Duplicate (k=" + k2 + ", t=" + t + "): " + 
                          solution.containsNearbyAlmostDuplicate(nums6, k2, t));
        
        // Find all duplicates
        int[] nums7 = {4, 3, 2, 7, 8, 2, 3, 1};
        System.out.println("Find All Duplicates: " + Arrays.toString(nums7) + 
                          " -> " + solution.findDuplicates(nums7.clone()));
        
        // Single number
        int[] nums8 = {2, 2, 1};
        System.out.println("Single Number: " + Arrays.toString(nums8) + 
                          " -> " + solution.singleNumber(nums8));
        
        // Missing number
        int[] nums9 = {3, 0, 1};
        System.out.println("Missing Number: " + Arrays.toString(nums9) + 
                          " -> " + solution.missingNumber(nums9));
        
        // First missing positive
        int[] nums10 = {1, 2, 0};
        System.out.println("First Missing Positive: " + Arrays.toString(nums10) + 
                          " -> " + solution.firstMissingPositive(nums10.clone()));
    }
} 