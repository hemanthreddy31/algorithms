package leetcode.bitmanipulation;

import java.util.*;

/**
 * LeetCode 260: Single Number III
 * 
 * Given an integer array nums, in which exactly two elements appear only once 
 * and all the other elements appear exactly twice. Find the two elements that appear only once.
 * You can return the answer in any order.
 * 
 * You must write an algorithm that runs in linear runtime complexity and uses only constant extra space.
 * 
 * Example:
 * Input: nums = [1,2,1,3,2,5]
 * Output: [3,5]
 */
public class SingleNumberIII {
    
    /**
     * Approach 1: XOR with Bit Grouping
     * Time: O(n), Space: O(1)
     * 
     * Key insights:
     * 1. XOR all numbers gives us a^b (the two single numbers)
     * 2. Find any set bit in a^b to distinguish a and b
     * 3. Group numbers by this bit and XOR within groups
     */
    public int[] singleNumber(int[] nums) {
        // Step 1: XOR all numbers to get a^b
        int xorAll = 0;
        for (int num : nums) {
            xorAll ^= num;
        }
        
        // Step 2: Find rightmost set bit (where a and b differ)
        int rightmostSetBit = xorAll & (-xorAll);
        
        // Step 3: Group numbers and XOR within groups
        int a = 0, b = 0;
        for (int num : nums) {
            if ((num & rightmostSetBit) == 0) {
                a ^= num; // Group 1
            } else {
                b ^= num; // Group 2
            }
        }
        
        return new int[]{a, b};
    }
    
    /**
     * Approach 2: Using HashMap (for comparison)
     * Time: O(n), Space: O(n)
     * Count frequencies and find elements with count 1
     */
    public int[] singleNumberHashMap(int[] nums) {
        Map<Integer, Integer> countMap = new HashMap<>();
        
        // Count frequencies
        for (int num : nums) {
            countMap.put(num, countMap.getOrDefault(num, 0) + 1);
        }
        
        // Find elements with count 1
        List<Integer> result = new ArrayList<>();
        for (Map.Entry<Integer, Integer> entry : countMap.entrySet()) {
            if (entry.getValue() == 1) {
                result.add(entry.getKey());
            }
        }
        
        return result.stream().mapToInt(i -> i).toArray();
    }
    
    /**
     * Approach 3: XOR with Any Set Bit
     * Time: O(n), Space: O(1)
     * Use any set bit position instead of rightmost
     */
    public int[] singleNumberAnyBit(int[] nums) {
        int xorAll = 0;
        for (int num : nums) {
            xorAll ^= num;
        }
        
        // Find any set bit (not necessarily rightmost)
        int setBit = 1;
        while ((xorAll & setBit) == 0) {
            setBit <<= 1;
        }
        
        int a = 0, b = 0;
        for (int num : nums) {
            if ((num & setBit) == 0) {
                a ^= num;
            } else {
                b ^= num;
            }
        }
        
        return new int[]{a, b};
    }
    
    /**
     * Extended Problem: Single Number I (LC 136)
     * Find the single number when others appear twice
     */
    public int singleNumberI(int[] nums) {
        int result = 0;
        for (int num : nums) {
            result ^= num;
        }
        return result;
    }
    
    /**
     * Extended Problem: Single Number II (LC 137)
     * Find single number when others appear thrice
     */
    public int singleNumberII(int[] nums) {
        int ones = 0, twos = 0;
        
        for (int num : nums) {
            // Update twos first
            twos |= ones & num;
            // Update ones
            ones ^= num;
            // Clear bits that appear 3 times
            int threes = ones & twos;
            ones &= ~threes;
            twos &= ~threes;
        }
        
        return ones;
    }
    
    /**
     * Extended Problem: Single Number II (Alternative)
     * Using bit counting approach
     */
    public int singleNumberIIBitCount(int[] nums) {
        int result = 0;
        
        // Check each bit position
        for (int i = 0; i < 32; i++) {
            int bitCount = 0;
            
            // Count 1s at position i
            for (int num : nums) {
                if ((num >> i & 1) == 1) {
                    bitCount++;
                }
            }
            
            // If not divisible by 3, single number has 1 at this position
            if (bitCount % 3 != 0) {
                result |= (1 << i);
            }
        }
        
        return result;
    }
    
    /**
     * Extended Problem: Single Number IV
     * General case: find single number when others appear k times
     */
    public int singleNumberK(int[] nums, int k) {
        int result = 0;
        
        for (int i = 0; i < 32; i++) {
            int bitCount = 0;
            
            for (int num : nums) {
                if ((num >> i & 1) == 1) {
                    bitCount++;
                }
            }
            
            if (bitCount % k != 0) {
                result |= (1 << i);
            }
        }
        
        return result;
    }
    
    /**
     * Extended Problem: Find Two Non-Repeating Elements
     * When array has duplicates but exactly two elements appear once
     */
    public int[] findTwoNonRepeating(int[] nums) {
        return singleNumber(nums); // Same as main problem
    }
    
    /**
     * Extended Problem: Find Three Single Numbers
     * When exactly three elements appear once, others appear twice
     */
    public int[] findThreeSingleNumbers(int[] nums) {
        // This requires different approach since XOR won't work directly
        Map<Integer, Integer> countMap = new HashMap<>();
        
        for (int num : nums) {
            countMap.put(num, countMap.getOrDefault(num, 0) + 1);
        }
        
        List<Integer> result = new ArrayList<>();
        for (Map.Entry<Integer, Integer> entry : countMap.entrySet()) {
            if (entry.getValue() == 1) {
                result.add(entry.getKey());
            }
        }
        
        return result.stream().mapToInt(i -> i).toArray();
    }
    
    /**
     * Extended Problem: XOR Queries of Subarray (LC 1310)
     * Answer XOR queries on subarrays
     */
    public int[] xorQueries(int[] arr, int[][] queries) {
        int n = arr.length;
        int[] prefixXor = new int[n + 1];
        
        // Build prefix XOR array
        for (int i = 0; i < n; i++) {
            prefixXor[i + 1] = prefixXor[i] ^ arr[i];
        }
        
        int[] result = new int[queries.length];
        for (int i = 0; i < queries.length; i++) {
            int left = queries[i][0];
            int right = queries[i][1];
            result[i] = prefixXor[right + 1] ^ prefixXor[left];
        }
        
        return result;
    }
    
    /**
     * Extended Problem: Find Unique Binary String (LC 1980)
     * Find binary string not in given array using XOR
     */
    public String findDifferentBinaryString(String[] nums) {
        int n = nums.length;
        StringBuilder result = new StringBuilder();
        
        // Cantor's diagonal argument
        for (int i = 0; i < n; i++) {
            char currentBit = nums[i].charAt(i);
            // Flip the bit
            result.append(currentBit == '0' ? '1' : '0');
        }
        
        return result.toString();
    }
    
    /**
     * Utility: Print XOR Operations
     */
    public void demonstrateXOR(int[] nums) {
        System.out.println("XOR demonstration for array: " + Arrays.toString(nums));
        
        int runningXor = 0;
        for (int i = 0; i < nums.length; i++) {
            runningXor ^= nums[i];
            System.out.println("After XOR with " + nums[i] + ": " + runningXor + 
                             " (binary: " + Integer.toBinaryString(runningXor) + ")");
        }
    }
    
    /**
     * Utility: Find Rightmost Set Bit
     */
    public int findRightmostSetBit(int n) {
        return n & (-n);
    }
    
    /**
     * Utility: Count Set Bits
     */
    public int countSetBits(int n) {
        int count = 0;
        while (n != 0) {
            count++;
            n &= (n - 1); // Brian Kernighan's algorithm
        }
        return count;
    }
    
    // Test cases
    public static void main(String[] args) {
        SingleNumberIII solution = new SingleNumberIII();
        
        // Test case 1: Basic example
        int[] nums1 = {1, 2, 1, 3, 2, 5};
        System.out.println("Test 1 - Array: " + Arrays.toString(nums1));
        int[] result1 = solution.singleNumber(nums1);
        System.out.println("Two single numbers: " + Arrays.toString(result1)); // [3, 5]
        System.out.println("HashMap approach: " + Arrays.toString(solution.singleNumberHashMap(nums1)));
        System.out.println("Any bit approach: " + Arrays.toString(solution.singleNumberAnyBit(nums1)));
        
        // Demonstrate XOR process
        solution.demonstrateXOR(nums1);
        
        // Test case 2: Negative numbers
        int[] nums2 = {-1, 0, -1, 0, 1, 2};
        System.out.println("\nTest 2 - With negatives: " + Arrays.toString(nums2));
        int[] result2 = solution.singleNumber(nums2);
        System.out.println("Two single numbers: " + Arrays.toString(result2)); // [1, 2]
        
        // Test case 3: Large numbers
        int[] nums3 = {1000000, 999999, 1000000, 999999, 123456, 654321};
        System.out.println("\nTest 3 - Large numbers: " + Arrays.toString(nums3));
        int[] result3 = solution.singleNumber(nums3);
        System.out.println("Two single numbers: " + Arrays.toString(result3)); // [123456, 654321]
        
        // Test extended problems
        System.out.println("\nExtended Problems:");
        
        // Single Number I
        int[] single1 = {2, 2, 1};
        System.out.println("Single Number I [2,2,1]: " + solution.singleNumberI(single1)); // 1
        
        // Single Number II
        int[] single2 = {2, 2, 3, 2};
        System.out.println("Single Number II [2,2,3,2]: " + solution.singleNumberII(single2)); // 3
        System.out.println("Single Number II (bit count): " + solution.singleNumberIIBitCount(single2)); // 3
        
        // Single Number K (k=3)
        int[] singleK = {0, 1, 0, 1, 0, 1, 99};
        System.out.println("Single Number K=3 [0,1,0,1,0,1,99]: " + solution.singleNumberK(singleK, 3)); // 99
        
        // XOR queries
        int[] arr = {1, 3, 4, 8};
        int[][] queries = {{0, 1}, {1, 2}, {0, 3}, {3, 3}};
        System.out.println("XOR queries result: " + Arrays.toString(solution.xorQueries(arr, queries)));
        
        // Utility demonstrations
        System.out.println("\nUtility Functions:");
        int testNum = 12; // 1100 in binary
        System.out.println("Rightmost set bit of 12: " + solution.findRightmostSetBit(testNum)); // 4
        System.out.println("Count set bits in 12: " + solution.countSetBits(testNum)); // 2
        
        // Find different binary string
        String[] binaryStrings = {"01", "10"};
        System.out.println("Different binary string: " + solution.findDifferentBinaryString(binaryStrings)); // "00" or "11"
    }
} 