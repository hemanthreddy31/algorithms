package leetcode.math;

import java.util.*;

/**
 * LeetCode 50: Pow(x, n)
 * 
 * Implement pow(x, n), which calculates x raised to the power n (i.e., x^n).
 * 
 * Example:
 * Input: x = 2.00000, n = 10
 * Output: 1024.00000
 * 
 * Input: x = 2.00000, n = -2
 * Output: 0.25000
 * Explanation: 2^-2 = 1/2^2 = 1/4 = 0.25
 */
public class PowerOfX {
    
    /**
     * Approach 1: Brute Force (Time Limit Exceeded for large n)
     * Time Complexity: O(n) - Multiply n times
     * Space Complexity: O(1)
     * 
     * Simple but inefficient for large values of n
     */
    public double myPowBruteForce(double x, int n) {
        if (n == 0) return 1.0;
        
        long absN = Math.abs((long)n);
        double result = 1.0;
        
        for (long i = 0; i < absN; i++) {
            result *= x;
        }
        
        return n < 0 ? 1.0 / result : result;
    }
    
    /**
     * Approach 2: Fast Exponentiation - Recursive (Optimal)
     * Time Complexity: O(log n) - Divide n by 2 each time
     * Space Complexity: O(log n) - Recursion stack
     * 
     * Algorithm:
     * x^n = x^(n/2) * x^(n/2) if n is even
     * x^n = x * x^(n/2) * x^(n/2) if n is odd
     */
    public double myPowRecursive(double x, int n) {
        if (n == 0) return 1.0;
        if (n == Integer.MIN_VALUE) {
            // Handle overflow: -2^31 cannot be converted to positive
            return 1.0 / (myPowRecursive(x, Integer.MAX_VALUE) * x);
        }
        
        if (n < 0) {
            return 1.0 / myPowRecursive(x, -n);
        }
        
        // Recursive case
        if (n % 2 == 0) {
            double half = myPowRecursive(x, n / 2);
            return half * half;
        } else {
            double half = myPowRecursive(x, n / 2);
            return x * half * half;
        }
    }
    
    /**
     * Approach 3: Fast Exponentiation - Iterative (Most Optimal)
     * Time Complexity: O(log n)
     * Space Complexity: O(1) - No recursion
     * 
     * Uses binary representation of n
     */
    public double myPowIterative(double x, int n) {
        if (n == 0) return 1.0;
        
        long absN = Math.abs((long)n);
        double result = 1.0;
        double currentProduct = x;
        
        // Process each bit of n
        while (absN > 0) {
            if (absN % 2 == 1) {
                result *= currentProduct;
            }
            currentProduct *= currentProduct;
            absN /= 2;
        }
        
        return n < 0 ? 1.0 / result : result;
    }
    
    /**
     * Approach 4: Binary Exponentiation with Bit Manipulation
     * Time Complexity: O(log n)
     * Space Complexity: O(1)
     * 
     * Uses bit operations for efficiency
     */
    public double myPowBitwise(double x, int n) {
        if (n == 0) return 1.0;
        
        long absN = Math.abs((long)n);
        double result = 1.0;
        
        while (absN > 0) {
            // Check if least significant bit is 1
            if ((absN & 1) == 1) {
                result *= x;
            }
            x *= x;
            absN >>= 1; // Right shift by 1 (divide by 2)
        }
        
        return n < 0 ? 1.0 / result : result;
    }
    
    /**
     * Extended Problem: Check if number is power of 2
     * LeetCode 231
     */
    public boolean isPowerOfTwo(int n) {
        // A power of 2 has only one bit set
        // n & (n-1) removes the rightmost set bit
        return n > 0 && (n & (n - 1)) == 0;
    }
    
    /**
     * Extended Problem: Check if number is power of 3
     * LeetCode 326
     */
    public boolean isPowerOfThree(int n) {
        // Method 1: Loop
        if (n <= 0) return false;
        while (n % 3 == 0) {
            n /= 3;
        }
        return n == 1;
    }
    
    /**
     * Check if number is power of 3 - Mathematical approach
     */
    public boolean isPowerOfThreeMath(int n) {
        // 3^19 = 1162261467 is the largest power of 3 that fits in int
        // If n is power of 3, it must divide 3^19
        return n > 0 && 1162261467 % n == 0;
    }
    
    /**
     * Extended Problem: Check if number is power of 4
     * LeetCode 342
     */
    public boolean isPowerOfFour(int n) {
        // Must be positive, power of 2, and have 1 in odd bit position
        // 0x55555555 = 01010101010101010101010101010101 (bits at even positions)
        return n > 0 && (n & (n - 1)) == 0 && (n & 0x55555555) != 0;
    }
    
    /**
     * Extended Problem: Super Pow
     * LeetCode 372: Calculate a^b mod 1337 where b is extremely large
     */
    public int superPow(int a, int[] b) {
        int mod = 1337;
        int result = 1;
        a %= mod;
        
        for (int i = b.length - 1; i >= 0; i--) {
            result = (result * pow(a, b[i], mod)) % mod;
            a = pow(a, 10, mod);
        }
        
        return result;
    }
    
    private int pow(int a, int b, int mod) {
        int result = 1;
        a %= mod;
        
        while (b > 0) {
            if (b % 2 == 1) {
                result = (result * a) % mod;
            }
            a = (a * a) % mod;
            b /= 2;
        }
        
        return result;
    }
    
    /**
     * Matrix Exponentiation for Fibonacci
     * Calculate nth Fibonacci number in O(log n) time
     */
    public int fibonacci(int n) {
        if (n <= 1) return n;
        
        int[][] base = {{1, 1}, {1, 0}};
        int[][] result = matrixPower(base, n - 1);
        
        return result[0][0];
    }
    
    private int[][] matrixPower(int[][] matrix, int n) {
        int[][] result = {{1, 0}, {0, 1}}; // Identity matrix
        
        while (n > 0) {
            if (n % 2 == 1) {
                result = multiplyMatrix(result, matrix);
            }
            matrix = multiplyMatrix(matrix, matrix);
            n /= 2;
        }
        
        return result;
    }
    
    private int[][] multiplyMatrix(int[][] a, int[][] b) {
        int[][] result = new int[2][2];
        
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 2; j++) {
                for (int k = 0; k < 2; k++) {
                    result[i][j] += a[i][k] * b[k][j];
                }
            }
        }
        
        return result;
    }
    
    /**
     * Modular Exponentiation
     * Calculate (base^exp) % mod efficiently
     */
    public long modPow(long base, long exp, long mod) {
        long result = 1;
        base %= mod;
        
        while (exp > 0) {
            if (exp % 2 == 1) {
                result = (result * base) % mod;
            }
            base = (base * base) % mod;
            exp /= 2;
        }
        
        return result;
    }
    
    // Test the solutions
    public static void main(String[] args) {
        PowerOfX solution = new PowerOfX();
        
        // Test pow(x, n)
        System.out.println("Testing pow(x, n):");
        double x1 = 2.0;
        int n1 = 10;
        System.out.println("x = " + x1 + ", n = " + n1);
        System.out.println("Recursive: " + solution.myPowRecursive(x1, n1));
        System.out.println("Iterative: " + solution.myPowIterative(x1, n1));
        System.out.println("Bitwise: " + solution.myPowBitwise(x1, n1));
        
        // Test negative exponent
        double x2 = 2.0;
        int n2 = -2;
        System.out.println("\nx = " + x2 + ", n = " + n2);
        System.out.println("Result: " + solution.myPowIterative(x2, n2));
        
        // Test edge case
        double x3 = 2.0;
        int n3 = Integer.MIN_VALUE;
        System.out.println("\nx = " + x3 + ", n = Integer.MIN_VALUE");
        System.out.println("Result: " + solution.myPowRecursive(x3, n3));
        
        // Test power checking
        System.out.println("\nPower Checking:");
        System.out.println("16 is power of 2: " + solution.isPowerOfTwo(16));
        System.out.println("18 is power of 2: " + solution.isPowerOfTwo(18));
        System.out.println("27 is power of 3: " + solution.isPowerOfThree(27));
        System.out.println("28 is power of 3: " + solution.isPowerOfThree(28));
        System.out.println("16 is power of 4: " + solution.isPowerOfFour(16));
        System.out.println("32 is power of 4: " + solution.isPowerOfFour(32));
        
        // Test super pow
        int a = 2;
        int[] b = {1, 0}; // 2^10
        System.out.println("\nSuper Pow: 2^10 mod 1337 = " + solution.superPow(a, b));
        
        // Test Fibonacci
        System.out.println("\nFibonacci using matrix exponentiation:");
        for (int i = 0; i <= 10; i++) {
            System.out.print(solution.fibonacci(i) + " ");
        }
        System.out.println();
        
        // Test modular exponentiation
        System.out.println("\nModular exponentiation:");
        System.out.println("3^10 mod 1000 = " + solution.modPow(3, 10, 1000));
    }
} 