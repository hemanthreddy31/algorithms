package leetcode.arrays;

import java.util.*;

/**
 * LeetCode 15: 3Sum
 * 
 * Given an integer array nums, return all the triplets [nums[i], nums[j], nums[k]] 
 * such that i != j, i != k, and j != k, and nums[i] + nums[j] + nums[k] == 0.
 * 
 * Notice that the solution set must not contain duplicate triplets.
 * 
 * Example:
 * Input: nums = [-1,0,1,2,-1,-4]
 * Output: [[-1,-1,2],[-1,0,1]]
 * Explanation: 
 * nums[0] + nums[1] + nums[2] = (-1) + 0 + 1 = 0.
 * nums[1] + nums[2] + nums[4] = 0 + 1 + (-1) = 0.
 * nums[0] + nums[3] + nums[4] = (-1) + 2 + (-1) = 0.
 * The distinct triplets are [-1,0,1] and [-1,-1,2].
 */
public class ThreeSum {
    
    /**
     * Approach 1: Brute Force with HashSet
     * Time Complexity: O(n³) - Three nested loops
     * Space Complexity: O(n) - HashSet to store unique triplets
     * 
     * Algorithm:
     * 1. Use three nested loops to check all possible triplets
     * 2. Use a HashSet to avoid duplicate triplets
     * 3. Sort each triplet before adding to ensure uniqueness
     */
    public List<List<Integer>> threeSumBruteForce(int[] nums) {
        Set<List<Integer>> resultSet = new HashSet<>();
        
        // Check all possible triplets
        for (int i = 0; i < nums.length - 2; i++) {
            for (int j = i + 1; j < nums.length - 1; j++) {
                for (int k = j + 1; k < nums.length; k++) {
                    if (nums[i] + nums[j] + nums[k] == 0) {
                        // Create a sorted list to ensure uniqueness
                        List<Integer> triplet = Arrays.asList(nums[i], nums[j], nums[k]);
                        Collections.sort(triplet);
                        resultSet.add(triplet);
                    }
                }
            }
        }
        
        return new ArrayList<>(resultSet);
    }
    
    /**
     * Approach 2: Sorting + Two Pointers (Optimal)
     * Time Complexity: O(n²) - One loop with two-pointer search inside
     * Space Complexity: O(1) - Only for output, no extra space used
     * 
     * Algorithm:
     * 1. Sort the array first
     * 2. For each element nums[i], find two other elements that sum to -nums[i]
     * 3. Use two pointers (left and right) to find the pair
     * 4. Skip duplicates to avoid duplicate triplets
     */
    public List<List<Integer>> threeSumOptimal(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        
        // Sort the array to enable two-pointer technique
        Arrays.sort(nums);
        
        // Fix the first element and find the other two
        for (int i = 0; i < nums.length - 2; i++) {
            // Skip duplicate values for the first element
            if (i > 0 && nums[i] == nums[i - 1]) {
                continue;
            }
            
            // Early termination: if current number is positive, sum can't be zero
            if (nums[i] > 0) {
                break;
            }
            
            // Two pointers to find the pair that sums to -nums[i]
            int left = i + 1;
            int right = nums.length - 1;
            int target = -nums[i];
            
            while (left < right) {
                int sum = nums[left] + nums[right];
                
                if (sum == target) {
                    // Found a valid triplet
                    result.add(Arrays.asList(nums[i], nums[left], nums[right]));
                    
                    // Skip duplicates for the second element
                    while (left < right && nums[left] == nums[left + 1]) {
                        left++;
                    }
                    // Skip duplicates for the third element
                    while (left < right && nums[right] == nums[right - 1]) {
                        right--;
                    }
                    
                    // Move both pointers
                    left++;
                    right--;
                } else if (sum < target) {
                    // Need a larger sum, move left pointer
                    left++;
                } else {
                    // Need a smaller sum, move right pointer
                    right--;
                }
            }
        }
        
        return result;
    }
    
    /**
     * Approach 3: HashSet for Two Sum
     * Time Complexity: O(n²) - One loop with hash-based two sum inside
     * Space Complexity: O(n) - HashSet for storing elements
     * 
     * Algorithm:
     * 1. Sort the array to handle duplicates easily
     * 2. For each element nums[i], use HashSet to find two sum
     * 3. Skip duplicates to avoid duplicate triplets
     */
    public List<List<Integer>> threeSumHashSet(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        Arrays.sort(nums);
        
        for (int i = 0; i < nums.length - 2; i++) {
            // Skip duplicates
            if (i > 0 && nums[i] == nums[i - 1]) {
                continue;
            }
            
            // Use HashSet to find two numbers that sum to -nums[i]
            Set<Integer> seen = new HashSet<>();
            int target = -nums[i];
            
            for (int j = i + 1; j < nums.length; j++) {
                int complement = target - nums[j];
                
                if (seen.contains(complement)) {
                    result.add(Arrays.asList(nums[i], complement, nums[j]));
                    
                    // Skip duplicates
                    while (j + 1 < nums.length && nums[j] == nums[j + 1]) {
                        j++;
                    }
                }
                
                seen.add(nums[j]);
            }
        }
        
        return result;
    }
    
    /**
     * Approach 4: No-Sort Solution using HashMap
     * Time Complexity: O(n²) - Two loops with constant time operations
     * Space Complexity: O(n) - HashMap and HashSet for storage
     * 
     * This approach doesn't require sorting but is more complex to handle duplicates
     */
    public List<List<Integer>> threeSumNoSort(int[] nums) {
        Set<List<Integer>> result = new HashSet<>();
        Map<Integer, Integer> map = new HashMap<>();
        
        // Count frequency of each number
        for (int num : nums) {
            map.put(num, map.getOrDefault(num, 0) + 1);
        }
        
        // Iterate through unique pairs
        for (int i = 0; i < nums.length - 1; i++) {
            map.put(nums[i], map.get(nums[i]) - 1);
            
            for (int j = i + 1; j < nums.length; j++) {
                map.put(nums[j], map.get(nums[j]) - 1);
                
                int target = -(nums[i] + nums[j]);
                if (map.getOrDefault(target, 0) > 0) {
                    List<Integer> triplet = Arrays.asList(nums[i], nums[j], target);
                    Collections.sort(triplet);
                    result.add(triplet);
                }
                
                map.put(nums[j], map.get(nums[j]) + 1);
            }
            
            map.put(nums[i], map.get(nums[i]) + 1);
        }
        
        return new ArrayList<>(result);
    }
    
    // Helper method to print results
    private static void printResult(List<List<Integer>> result) {
        System.out.print("[");
        for (int i = 0; i < result.size(); i++) {
            System.out.print(result.get(i));
            if (i < result.size() - 1) System.out.print(", ");
        }
        System.out.println("]");
    }
    
    // Test the solutions
    public static void main(String[] args) {
        ThreeSum solution = new ThreeSum();
        
        // Test case 1
        int[] nums1 = {-1, 0, 1, 2, -1, -4};
        System.out.println("Test Case 1: nums = [-1,0,1,2,-1,-4]");
        
        System.out.print("Brute Force: ");
        printResult(solution.threeSumBruteForce(nums1));
        
        System.out.print("Optimal (Two Pointers): ");
        printResult(solution.threeSumOptimal(nums1));
        
        System.out.print("HashSet: ");
        printResult(solution.threeSumHashSet(nums1));
        
        // Test case 2: No valid triplets
        int[] nums2 = {0, 1, 1};
        System.out.println("\nTest Case 2: nums = [0,1,1]");
        System.out.print("Optimal: ");
        printResult(solution.threeSumOptimal(nums2));
        
        // Test case 3: All zeros
        int[] nums3 = {0, 0, 0};
        System.out.println("\nTest Case 3: nums = [0,0,0]");
        System.out.print("Optimal: ");
        printResult(solution.threeSumOptimal(nums3));
    }
} 