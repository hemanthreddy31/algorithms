package leetcode.hashtable;

import java.util.*;

/**
 * LeetCode 349: Intersection of Two Arrays
 * 
 * Given two integer arrays nums1 and nums2, return an array of their intersection.
 * Each element in the result must be unique and you may return the result in any order.
 * 
 * Example:
 * Input: nums1 = [1,2,2,1], nums2 = [2,2]
 * Output: [2]
 * 
 * Input: nums1 = [4,9,5], nums2 = [9,4,9,8,4]
 * Output: [9,4] or [4,9]
 */
public class IntersectionOfTwoArrays {
    
    /**
     * Approach 1: HashSet (Optimal)
     * Time Complexity: O(m + n) - Single pass through both arrays
     * Space Complexity: O(m + n) - HashSet storage
     * 
     * Use HashSet to store elements from one array, then check the other
     */
    public int[] intersection(int[] nums1, int[] nums2) {
        Set<Integer> set1 = new HashSet<>();
        Set<Integer> intersection = new HashSet<>();
        
        // Store all elements from nums1
        for (int num : nums1) {
            set1.add(num);
        }
        
        // Check each element in nums2
        for (int num : nums2) {
            if (set1.contains(num)) {
                intersection.add(num);
            }
        }
        
        // Convert to array
        return intersection.stream().mapToInt(i -> i).toArray();
    }
    
    /**
     * Approach 2: Sorting + Two Pointers
     * Time Complexity: O(m log m + n log n) - Sorting dominates
     * Space Complexity: O(1) - Only result array
     * 
     * Sort both arrays and use two pointers
     */
    public int[] intersectionSorting(int[] nums1, int[] nums2) {
        Arrays.sort(nums1);
        Arrays.sort(nums2);
        
        Set<Integer> intersection = new HashSet<>();
        int i = 0, j = 0;
        
        while (i < nums1.length && j < nums2.length) {
            if (nums1[i] == nums2[j]) {
                intersection.add(nums1[i]);
                i++;
                j++;
            } else if (nums1[i] < nums2[j]) {
                i++;
            } else {
                j++;
            }
        }
        
        return intersection.stream().mapToInt(x -> x).toArray();
    }
    
    /**
     * Extended Problem: Intersection with Frequencies
     * LeetCode 350: Return intersection with frequencies
     */
    public int[] intersectII(int[] nums1, int[] nums2) {
        Map<Integer, Integer> count1 = new HashMap<>();
        List<Integer> result = new ArrayList<>();
        
        // Count frequencies in nums1
        for (int num : nums1) {
            count1.put(num, count1.getOrDefault(num, 0) + 1);
        }
        
        // Find intersection with frequencies
        for (int num : nums2) {
            if (count1.containsKey(num) && count1.get(num) > 0) {
                result.add(num);
                count1.put(num, count1.get(num) - 1);
            }
        }
        
        return result.stream().mapToInt(i -> i).toArray();
    }
    
    /**
     * Extended Problem: Intersection of Multiple Arrays
     * Find intersection of k arrays
     */
    public List<Integer> intersectionMultiple(int[][] arrays) {
        if (arrays.length == 0) return new ArrayList<>();
        
        Map<Integer, Integer> count = new HashMap<>();
        
        // Count occurrences across all arrays
        for (int[] array : arrays) {
            Set<Integer> uniqueInArray = new HashSet<>();
            for (int num : array) {
                uniqueInArray.add(num);
            }
            
            for (int num : uniqueInArray) {
                count.put(num, count.getOrDefault(num, 0) + 1);
            }
        }
        
        // Find elements present in all arrays
        List<Integer> result = new ArrayList<>();
        for (Map.Entry<Integer, Integer> entry : count.entrySet()) {
            if (entry.getValue() == arrays.length) {
                result.add(entry.getKey());
            }
        }
        
        Collections.sort(result);
        return result;
    }
    
    /**
     * Extended Problem: Union of Two Arrays
     * Find union of two arrays (all unique elements)
     */
    public int[] union(int[] nums1, int[] nums2) {
        Set<Integer> unionSet = new HashSet<>();
        
        for (int num : nums1) unionSet.add(num);
        for (int num : nums2) unionSet.add(num);
        
        return unionSet.stream().mapToInt(i -> i).toArray();
    }
    
    /**
     * Extended Problem: Symmetric Difference
     * Find elements in either array but not in both
     */
    public int[] symmetricDifference(int[] nums1, int[] nums2) {
        Set<Integer> set1 = new HashSet<>();
        Set<Integer> set2 = new HashSet<>();
        
        for (int num : nums1) set1.add(num);
        for (int num : nums2) set2.add(num);
        
        Set<Integer> result = new HashSet<>(set1);
        result.addAll(set2);
        
        // Remove intersection
        Set<Integer> intersection = new HashSet<>(set1);
        intersection.retainAll(set2);
        result.removeAll(intersection);
        
        return result.stream().mapToInt(i -> i).toArray();
    }
    
    /**
     * Extended Problem: Subset Check
     * Check if nums1 is a subset of nums2
     */
    public boolean isSubset(int[] nums1, int[] nums2) {
        Set<Integer> set2 = new HashSet<>();
        for (int num : nums2) {
            set2.add(num);
        }
        
        for (int num : nums1) {
            if (!set2.contains(num)) {
                return false;
            }
        }
        
        return true;
    }
    
    // Helper method to print arrays
    private static void printArray(int[] arr) {
        System.out.println(Arrays.toString(arr));
    }
    
    // Test the solutions
    public static void main(String[] args) {
        IntersectionOfTwoArrays solution = new IntersectionOfTwoArrays();
        
        // Test case 1: Standard case
        int[] nums1 = {1, 2, 2, 1};
        int[] nums2 = {2, 2};
        System.out.println("Test Case 1:");
        System.out.println("nums1: " + Arrays.toString(nums1));
        System.out.println("nums2: " + Arrays.toString(nums2));
        System.out.println("Intersection: " + Arrays.toString(solution.intersection(nums1, nums2)));
        System.out.println("Intersection II: " + Arrays.toString(solution.intersectII(nums1, nums2)));
        
        // Test case 2: No intersection
        int[] nums3 = {1, 2, 3};
        int[] nums4 = {4, 5, 6};
        System.out.println("\nTest Case 2: No intersection");
        System.out.println("Result: " + Arrays.toString(solution.intersection(nums3, nums4)));
        
        // Test extended problems
        System.out.println("\nExtended Problems:");
        
        // Multiple arrays intersection
        int[][] arrays = {{1, 2, 3}, {2, 3, 4}, {2, 3, 5}};
        System.out.println("Multiple Arrays Intersection:");
        System.out.println("Arrays: " + Arrays.deepToString(arrays));
        System.out.println("Result: " + solution.intersectionMultiple(arrays));
        
        // Union
        int[] nums9 = {1, 2, 3};
        int[] nums10 = {3, 4, 5};
        System.out.println("Union:");
        System.out.print("Result: ");
        printArray(solution.union(nums9, nums10));
        
        // Symmetric difference
        System.out.println("Symmetric Difference:");
        System.out.print("Result: ");
        printArray(solution.symmetricDifference(nums9, nums10));
        
        // Subset check
        int[] nums11 = {1, 2};
        int[] nums12 = {1, 2, 3, 4};
        System.out.println("Subset Check:");
        System.out.println(Arrays.toString(nums11) + " is subset of " + 
                          Arrays.toString(nums12) + ": " + solution.isSubset(nums11, nums12));
    }
} 