package leetcode.bitmanipulation;

import java.util.*;

/**
 * LeetCode 191: Number of 1 Bits (Hamming Weight)
 * 
 * Write a function that takes an unsigned integer and returns the number of '1' bits 
 * it has (also known as the Hamming weight).
 * 
 * Example:
 * Input: n = 00000000000000000000000000001011
 * Output: 3 (binary has three '1' bits)
 */
public class NumberOf1Bits {
    
    /**
     * Approach 1: Bit by Bit Check
     * Time: O(32) = O(1), Space: O(1)
     * Check each bit position
     */
    public int hammingWeight(int n) {
        int count = 0;
        for (int i = 0; i < 32; i++) {
            if ((n & (1 << i)) != 0) {
                count++;
            }
        }
        return count;
    }
    
    /**
     * Approach 2: Right Shift and Check LSB
     * Time: O(32) = O(1), Space: O(1)
     * Check least significant bit and right shift
     */
    public int hammingWeightRightShift(int n) {
        int count = 0;
        while (n != 0) {
            count += n & 1;
            n >>>= 1; // Unsigned right shift to handle negative numbers
        }
        return count;
    }
    
    /**
     * Approach 3: Brian Kernighan's Algorithm
     * Time: O(number of 1 bits), Space: O(1)
     * 
     * Key insight: n & (n-1) removes the rightmost set bit
     */
    public int hammingWeightBrianKernighan(int n) {
        int count = 0;
        while (n != 0) {
            n &= (n - 1); // Remove rightmost set bit
            count++;
        }
        return count;
    }
    
    /**
     * Approach 4: Built-in Function
     * Time: O(1), Space: O(1)
     * Use Integer.bitCount() for comparison
     */
    public int hammingWeightBuiltIn(int n) {
        return Integer.bitCount(n);
    }
    
    /**
     * Approach 5: Lookup Table
     * Time: O(1), Space: O(256)
     * Pre-compute bit counts for all possible byte values
     */
    private static final int[] lookupTable = new int[256];
    
    static {
        for (int i = 0; i < 256; i++) {
            lookupTable[i] = (i & 1) + lookupTable[i / 2];
        }
    }
    
    public int hammingWeightLookup(int n) {
        return lookupTable[n & 0xFF] +
               lookupTable[(n >> 8) & 0xFF] +
               lookupTable[(n >> 16) & 0xFF] +
               lookupTable[(n >> 24) & 0xFF];
    }
    
    /**
     * Approach 6: Parallel Bit Counting (SWAR)
     * Time: O(1), Space: O(1)
     * SWAR = SIMD Within A Register
     */
    public int hammingWeightSWAR(int n) {
        // Count bits in pairs
        n = n - ((n >>> 1) & 0x55555555);
        // Count bits in groups of 4
        n = (n & 0x33333333) + ((n >>> 2) & 0x33333333);
        // Count bits in groups of 8
        n = (n + (n >>> 4)) & 0x0F0F0F0F;
        // Count bits in groups of 16
        n = n + (n >>> 8);
        // Count bits in groups of 32
        n = n + (n >>> 16);
        return n & 0x3F;
    }
    
    /**
     * Extended Problem: Hamming Distance (LC 461)
     * Find hamming distance between two integers
     */
    public int hammingDistance(int x, int y) {
        return hammingWeightBrianKernighan(x ^ y);
    }
    
    /**
     * Extended Problem: Total Hamming Distance (LC 477)
     * Find total hamming distance between all pairs in array
     */
    public int totalHammingDistance(int[] nums) {
        int totalDistance = 0;
        int n = nums.length;
        
        // For each bit position
        for (int i = 0; i < 32; i++) {
            int onesCount = 0;
            
            // Count 1s at position i
            for (int num : nums) {
                onesCount += (num >> i) & 1;
            }
            
            // Each 1 contributes to distance with each 0
            totalDistance += onesCount * (n - onesCount);
        }
        
        return totalDistance;
    }
    
    /**
     * Extended Problem: Count Bits (LC 338)
     * Count bits for numbers 0 to n
     */
    public int[] countBits(int n) {
        int[] result = new int[n + 1];
        
        for (int i = 1; i <= n; i++) {
            // DP: result[i] = result[i >> 1] + (i & 1)
            result[i] = result[i >> 1] + (i & 1);
        }
        
        return result;
    }
    
    /**
     * Extended Problem: Reverse Bits (LC 190)
     * Reverse bits of a 32-bit unsigned integer
     */
    public int reverseBits(int n) {
        int result = 0;
        for (int i = 0; i < 32; i++) {
            result = (result << 1) | (n & 1);
            n >>= 1;
        }
        return result;
    }
    
    /**
     * Extended Problem: Power of Two (LC 231)
     * Check if number is power of 2
     */
    public boolean isPowerOfTwo(int n) {
        return n > 0 && (n & (n - 1)) == 0;
    }
    
    /**
     * Extended Problem: Power of Four (LC 342)
     * Check if number is power of 4
     */
    public boolean isPowerOfFour(int n) {
        // Must be power of 2 and bit must be at even position
        return n > 0 && (n & (n - 1)) == 0 && (n & 0x55555555) != 0;
    }
    
    /**
     * Extended Problem: Missing Number (LC 268)
     * Find missing number using XOR
     */
    public int missingNumber(int[] nums) {
        int missing = nums.length;
        for (int i = 0; i < nums.length; i++) {
            missing ^= i ^ nums[i];
        }
        return missing;
    }
    
    /**
     * Extended Problem: Find Complement (LC 476)
     * Find complement of a number
     */
    public int findComplement(int num) {
        // Find the highest bit position
        int bitLength = Integer.toBinaryString(num).length();
        int mask = (1 << bitLength) - 1;
        return num ^ mask;
    }
    
    /**
     * Extended Problem: Maximum XOR of Two Numbers (LC 421)
     * Find maximum XOR of any two numbers in array
     */
    public int findMaximumXOR(int[] nums) {
        int max = 0;
        int mask = 0;
        
        for (int i = 30; i >= 0; i--) {
            mask |= (1 << i);
            Set<Integer> prefixes = new HashSet<>();
            
            for (int num : nums) {
                prefixes.add(num & mask);
            }
            
            int temp = max | (1 << i);
            for (int prefix : prefixes) {
                if (prefixes.contains(temp ^ prefix)) {
                    max = temp;
                    break;
                }
            }
        }
        
        return max;
    }
    
    /**
     * Utility: Print Binary Representation
     */
    public void printBinary(int n) {
        System.out.println(Integer.toBinaryString(n) + " (" + n + ")");
    }
    
    /**
     * Utility: Get Bit at Position
     */
    public boolean getBit(int n, int position) {
        return (n & (1 << position)) != 0;
    }
    
    /**
     * Utility: Set Bit at Position
     */
    public int setBit(int n, int position) {
        return n | (1 << position);
    }
    
    /**
     * Utility: Clear Bit at Position
     */
    public int clearBit(int n, int position) {
        return n & ~(1 << position);
    }
    
    /**
     * Utility: Toggle Bit at Position
     */
    public int toggleBit(int n, int position) {
        return n ^ (1 << position);
    }
    
    // Test cases
    public static void main(String[] args) {
        NumberOf1Bits solution = new NumberOf1Bits();
        
        // Test case 1: Basic counting
        int n1 = 0b00000000000000000000000000001011; // 11 in decimal
        System.out.println("Test 1 - n = " + n1);
        solution.printBinary(n1);
        System.out.println("Bit by bit: " + solution.hammingWeight(n1)); // 3
        System.out.println("Right shift: " + solution.hammingWeightRightShift(n1)); // 3
        System.out.println("Brian Kernighan: " + solution.hammingWeightBrianKernighan(n1)); // 3
        System.out.println("Built-in: " + solution.hammingWeightBuiltIn(n1)); // 3
        System.out.println("Lookup: " + solution.hammingWeightLookup(n1)); // 3
        System.out.println("SWAR: " + solution.hammingWeightSWAR(n1)); // 3
        
        // Test case 2: All 1s
        int n2 = 0b11111111111111111111111111111101; // -3 in decimal
        System.out.println("\nTest 2 - n = " + n2);
        solution.printBinary(n2);
        System.out.println("Hamming weight: " + solution.hammingWeight(n2)); // 31
        
        // Test case 3: Power of 2
        int n3 = 16;
        System.out.println("\nTest 3 - n = " + n3);
        solution.printBinary(n3);
        System.out.println("Hamming weight: " + solution.hammingWeight(n3)); // 1
        System.out.println("Is power of 2: " + solution.isPowerOfTwo(n3)); // true
        System.out.println("Is power of 4: " + solution.isPowerOfFour(n3)); // true
        
        // Test extended problems
        System.out.println("\nExtended Problems:");
        System.out.println("Hamming distance between 1 and 4: " + solution.hammingDistance(1, 4)); // 2
        
        int[] nums = {4, 14, 2};
        System.out.println("Total hamming distance [4,14,2]: " + solution.totalHammingDistance(nums)); // 6
        
        System.out.println("Count bits 0-5: " + Arrays.toString(solution.countBits(5)));
        
        System.out.println("Reverse bits of 43261596: " + solution.reverseBits(43261596));
        
        int[] missingArray = {3, 0, 1};
        System.out.println("Missing number in [3,0,1]: " + solution.missingNumber(missingArray)); // 2
        
        System.out.println("Complement of 5: " + solution.findComplement(5)); // 2
        
        int[] xorArray = {3, 10, 5, 25, 2, 8};
        System.out.println("Maximum XOR in array: " + solution.findMaximumXOR(xorArray)); // 28
        
        // Test bit manipulation utilities
        System.out.println("\nBit Manipulation Utilities:");
        int testNum = 10; // 1010 in binary
        solution.printBinary(testNum);
        System.out.println("Get bit 1: " + solution.getBit(testNum, 1)); // true
        System.out.println("Set bit 0: " + solution.setBit(testNum, 0)); // 11
        System.out.println("Clear bit 1: " + solution.clearBit(testNum, 1)); // 8
        System.out.println("Toggle bit 2: " + solution.toggleBit(testNum, 2)); // 14
    }
} 