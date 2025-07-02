package leetcode.bitmanipulation;

import java.util.*;

/**
 * LeetCode 136: Single Number
 * 
 * Given a non-empty array of integers nums, every element appears twice except for one. 
 * Find that single one.
 * 
 * You must implement a solution with a linear runtime complexity and use only constant extra space.
 * 
 * Example:
 * Input: nums = [2,2,1]
 * Output: 1
 */
public class SingleNumber {
    
    /**
     * Approach 1: XOR (Optimal)
     * Time Complexity: O(n) - Single pass
     * Space Complexity: O(1) - No extra space
     * 
     * Key Properties of XOR:
     * 1. a ^ 0 = a
     * 2. a ^ a = 0
     * 3. a ^ b ^ a = b (XOR is commutative and associative)
     */
    public int singleNumber(int[] nums) {
        int result = 0;
        
        for (int num : nums) {
            result ^= num;
        }
        
        return result;
    }
    
    /**
     * Approach 2: HashSet (Not optimal but intuitive)
     * Time Complexity: O(n)
     * Space Complexity: O(n) - HashSet storage
     */
    public int singleNumberHashSet(int[] nums) {
        Set<Integer> set = new HashSet<>();
        
        for (int num : nums) {
            if (set.contains(num)) {
                set.remove(num);
            } else {
                set.add(num);
            }
        }
        
        return set.iterator().next();
    }
    
    /**
     * Approach 3: Mathematical
     * Time Complexity: O(n)
     * Space Complexity: O(n) - Set storage
     * 
     * Formula: 2 * (sum of unique elements) - (sum of all elements) = single element
     */
    public int singleNumberMath(int[] nums) {
        Set<Integer> set = new HashSet<>();
        int sumOfAll = 0;
        int sumOfUnique = 0;
        
        for (int num : nums) {
            if (set.add(num)) {
                sumOfUnique += num;
            }
            sumOfAll += num;
        }
        
        return 2 * sumOfUnique - sumOfAll;
    }
    
    /**
     * LeetCode 137: Single Number II
     * Every element appears three times except for one
     * 
     * Time Complexity: O(n)
     * Space Complexity: O(1)
     */
    public int singleNumberII(int[] nums) {
        int ones = 0, twos = 0;
        
        for (int num : nums) {
            // Update twos with bits that appeared twice
            twos |= ones & num;
            
            // XOR with ones to get bits that appeared once or three times
            ones ^= num;
            
            // Remove bits that appeared three times
            int threes = ones & twos;
            ones &= ~threes;
            twos &= ~threes;
        }
        
        return ones;
    }
    
    /**
     * Alternative approach for Single Number II using bit counting
     */
    public int singleNumberIIBitCounting(int[] nums) {
        int result = 0;
        
        // Check each bit position
        for (int i = 0; i < 32; i++) {
            int bitSum = 0;
            
            // Count how many numbers have bit i set
            for (int num : nums) {
                bitSum += (num >> i) & 1;
            }
            
            // If count % 3 != 0, then the single number has this bit set
            if (bitSum % 3 != 0) {
                result |= 1 << i;
            }
        }
        
        return result;
    }
    
    /**
     * LeetCode 260: Single Number III
     * Two elements appear only once, all others appear twice
     * 
     * Time Complexity: O(n)
     * Space Complexity: O(1)
     */
    public int[] singleNumberIII(int[] nums) {
        // XOR all numbers to get a ^ b
        int xor = 0;
        for (int num : nums) {
            xor ^= num;
        }
        
        // Find a bit that is different between a and b
        // Get the rightmost set bit
        int diffBit = xor & -xor;
        
        // Partition numbers into two groups based on the diff bit
        int[] result = new int[2];
        for (int num : nums) {
            if ((num & diffBit) == 0) {
                result[0] ^= num;
            } else {
                result[1] ^= num;
            }
        }
        
        return result;
    }
    
    /**
     * General solution for "k elements appear once, all others appear n times"
     * Using modulo arithmetic on each bit
     */
    public int[] singleNumberGeneral(int[] nums, int k, int n) {
        // This is a simplified version for demonstration
        // Full implementation would require more complex state tracking
        
        if (k == 1 && n == 2) {
            return new int[]{singleNumber(nums)};
        } else if (k == 1 && n == 3) {
            return new int[]{singleNumberII(nums)};
        } else if (k == 2 && n == 2) {
            return singleNumberIII(nums);
        }
        
        // For other cases, would need more complex implementation
        return new int[0];
    }
    
    /**
     * Extended Problem: Find missing number (0 to n)
     * LeetCode 268
     */
    public int missingNumber(int[] nums) {
        int n = nums.length;
        int expectedXor = 0;
        int actualXor = 0;
        
        // XOR all numbers from 0 to n
        for (int i = 0; i <= n; i++) {
            expectedXor ^= i;
        }
        
        // XOR all numbers in array
        for (int num : nums) {
            actualXor ^= num;
        }
        
        // Missing number = expectedXor ^ actualXor
        return expectedXor ^ actualXor;
    }
    
    /**
     * Bit manipulation tricks demonstration
     */
    public void demonstrateBitTricks() {
        System.out.println("\n=== Bit Manipulation Tricks ===");
        
        // 1. Check if number is power of 2
        int n = 16;
        boolean isPowerOf2 = (n & (n - 1)) == 0 && n > 0;
        System.out.println(n + " is power of 2: " + isPowerOf2);
        
        // 2. Count set bits (Brian Kernighan's algorithm)
        int num = 13; // Binary: 1101
        int count = 0;
        int temp = num;
        while (temp > 0) {
            temp &= temp - 1;
            count++;
        }
        System.out.println("Set bits in " + num + ": " + count);
        
        // 3. Get rightmost set bit
        int rightmostBit = num & -num;
        System.out.println("Rightmost set bit of " + num + ": " + rightmostBit);
        
        // 4. Toggle bit at position i
        int i = 2;
        int toggled = num ^ (1 << i);
        System.out.println("Toggle bit " + i + " of " + num + ": " + toggled);
        
        // 5. Check if bit at position i is set
        boolean isSet = (num & (1 << i)) != 0;
        System.out.println("Bit " + i + " of " + num + " is set: " + isSet);
    }
    
    // Test the solutions
    public static void main(String[] args) {
        SingleNumber solution = new SingleNumber();
        
        // Test Single Number I
        int[] nums1 = {2, 2, 1};
        System.out.println("Single Number I:");
        System.out.println("Input: [2,2,1]");
        System.out.println("XOR approach: " + solution.singleNumber(nums1));
        System.out.println("HashSet approach: " + solution.singleNumberHashSet(nums1));
        System.out.println("Math approach: " + solution.singleNumberMath(nums1));
        
        // Test Single Number II
        int[] nums2 = {2, 2, 3, 2};
        System.out.println("\nSingle Number II:");
        System.out.println("Input: [2,2,3,2]");
        System.out.println("State machine: " + solution.singleNumberII(nums2));
        System.out.println("Bit counting: " + solution.singleNumberIIBitCounting(nums2));
        
        // Test Single Number III
        int[] nums3 = {1, 2, 1, 3, 2, 5};
        System.out.println("\nSingle Number III:");
        System.out.println("Input: [1,2,1,3,2,5]");
        System.out.println("Result: " + Arrays.toString(solution.singleNumberIII(nums3)));
        
        // Test Missing Number
        int[] nums4 = {3, 0, 1};
        System.out.println("\nMissing Number:");
        System.out.println("Input: [3,0,1]");
        System.out.println("Missing: " + solution.missingNumber(nums4));
        
        // Demonstrate bit tricks
        solution.demonstrateBitTricks();
    }
} 