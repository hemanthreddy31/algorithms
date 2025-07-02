package leetcode.math;

import java.util.*;

/**
 * LeetCode 69: Sqrt(x)
 * 
 * Given a non-negative integer x, compute and return the square root of x.
 * Since the return type is an integer, the decimal digits are truncated, 
 * and only the integer part of the result is returned.
 * 
 * Note: You are not allowed to use any built-in exponent function or operator.
 * 
 * Example:
 * Input: x = 8
 * Output: 2 (sqrt of 8 is 2.828..., and we return the integer part, which is 2)
 */
public class SqrtX {
    
    /**
     * Approach 1: Binary Search
     * Time: O(log x), Space: O(1)
     * 
     * Search for the largest number whose square <= x
     */
    public int mySqrt(int x) {
        if (x == 0) return 0;
        
        int left = 1, right = x;
        int result = 1;
        
        while (left <= right) {
            int mid = left + (right - left) / 2;
            
            if (mid <= x / mid) { // Avoid overflow: mid * mid <= x
                result = mid;
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        
        return result;
    }
    
    /**
     * Approach 2: Optimized Binary Search
     * Time: O(log x), Space: O(1)
     * Use tighter bounds for binary search
     */
    public int mySqrtOptimized(int x) {
        if (x < 2) return x;
        
        // Tighter bounds: sqrt(x) is between 2 and x/2 for x >= 4
        int left = 2, right = x / 2;
        
        while (left <= right) {
            int mid = left + (right - left) / 2;
            long square = (long) mid * mid;
            
            if (square == x) {
                return mid;
            } else if (square < x) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        
        return right; // right is the largest number whose square <= x
    }
    
    /**
     * Approach 3: Newton's Method (Newton-Raphson)
     * Time: O(log x), Space: O(1)
     * 
     * Formula: x_{n+1} = (x_n + a/x_n) / 2
     * where a is the number we want to find square root of
     */
    public int mySqrtNewton(int x) {
        if (x < 2) return x;
        
        long guess = x;
        while (guess * guess > x) {
            guess = (guess + x / guess) / 2;
        }
        
        return (int) guess;
    }
    
    /**
     * Approach 4: Bit Manipulation
     * Time: O(log x), Space: O(1)
     * Build the answer bit by bit
     */
    public int mySqrtBitManipulation(int x) {
        if (x < 2) return x;
        
        int result = 0;
        
        // Start from the highest possible bit
        for (int bit = 15; bit >= 0; bit--) { // 2^15 = 32768, covers int range
            int candidate = result | (1 << bit);
            if ((long) candidate * candidate <= x) {
                result = candidate;
            }
        }
        
        return result;
    }
    
    /**
     * Approach 5: Mathematical (Using logarithms concept)
     * Time: O(1), Space: O(1)
     * Uses the fact that sqrt(x) = e^(ln(x)/2)
     */
    public int mySqrtMath(int x) {
        if (x < 2) return x;
        
        // Using built-in functions for demonstration
        // In practice, this violates the constraint
        int result = (int) Math.exp(0.5 * Math.log(x));
        
        // Adjust for precision errors
        return (long) (result + 1) * (result + 1) <= x ? result + 1 : result;
    }
    
    /**
     * Extended Problem: Perfect Square (LC 367)
     * Check if a number is a perfect square
     */
    public boolean isPerfectSquare(int num) {
        if (num < 2) return true;
        
        long left = 2, right = num / 2;
        
        while (left <= right) {
            long mid = left + (right - left) / 2;
            long square = mid * mid;
            
            if (square == num) {
                return true;
            } else if (square < num) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        
        return false;
    }
    
    /**
     * Extended Problem: Find Square Root with Precision
     * Return floating-point square root with given precision
     */
    public double sqrtWithPrecision(double x, double precision) {
        if (x < 0) throw new IllegalArgumentException("Cannot compute sqrt of negative number");
        if (x == 0) return 0;
        
        double guess = x;
        while (Math.abs(guess * guess - x) > precision) {
            guess = (guess + x / guess) / 2.0;
        }
        
        return guess;
    }
    
    /**
     * Extended Problem: Nth Root
     * Find nth root of a number
     */
    public double nthRoot(double x, int n) {
        if (n == 0) throw new IllegalArgumentException("n cannot be 0");
        if (n == 1) return x;
        
        double guess = x / n;
        double precision = 1e-10;
        
        while (true) {
            double newGuess = ((n - 1) * guess + x / Math.pow(guess, n - 1)) / n;
            if (Math.abs(newGuess - guess) < precision) {
                break;
            }
            guess = newGuess;
        }
        
        return guess;
    }
    
    /**
     * Extended Problem: Square Root Decomposition
     * Useful for range queries
     */
    public static class SqrtDecomposition {
        private int[] arr;
        private int[] blocks;
        private int blockSize;
        private int numBlocks;
        
        public SqrtDecomposition(int[] array) {
            this.arr = array.clone();
            this.blockSize = (int) Math.sqrt(array.length);
            this.numBlocks = (array.length + blockSize - 1) / blockSize;
            this.blocks = new int[numBlocks];
            
            // Precompute block sums
            for (int i = 0; i < array.length; i++) {
                blocks[i / blockSize] += array[i];
            }
        }
        
        public void update(int index, int value) {
            blocks[index / blockSize] += value - arr[index];
            arr[index] = value;
        }
        
        public int rangeSum(int left, int right) {
            int sum = 0;
            int leftBlock = left / blockSize;
            int rightBlock = right / blockSize;
            
            if (leftBlock == rightBlock) {
                // Same block
                for (int i = left; i <= right; i++) {
                    sum += arr[i];
                }
            } else {
                // Different blocks
                // Left partial block
                for (int i = left; i < (leftBlock + 1) * blockSize; i++) {
                    sum += arr[i];
                }
                
                // Complete middle blocks
                for (int i = leftBlock + 1; i < rightBlock; i++) {
                    sum += blocks[i];
                }
                
                // Right partial block
                for (int i = rightBlock * blockSize; i <= right; i++) {
                    sum += arr[i];
                }
            }
            
            return sum;
        }
    }
    
    /**
     * Extended Problem: Integer to Roman (related to mathematical conversions)
     */
    public String intToRoman(int num) {
        int[] values = {1000, 900, 500, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1};
        String[] symbols = {"M", "CM", "D", "CD", "C", "XC", "L", "XL", "X", "IX", "V", "IV", "I"};
        
        StringBuilder result = new StringBuilder();
        
        for (int i = 0; i < values.length; i++) {
            while (num >= values[i]) {
                result.append(symbols[i]);
                num -= values[i];
            }
        }
        
        return result.toString();
    }
    
    /**
     * Extended Problem: Count Primes (LC 204)
     * Count primes up to n using Sieve of Eratosthenes
     */
    public int countPrimes(int n) {
        if (n <= 2) return 0;
        
        boolean[] isPrime = new boolean[n];
        Arrays.fill(isPrime, true);
        isPrime[0] = isPrime[1] = false;
        
        // Sieve of Eratosthenes
        for (int i = 2; i * i < n; i++) {
            if (isPrime[i]) {
                for (int j = i * i; j < n; j += i) {
                    isPrime[j] = false;
                }
            }
        }
        
        int count = 0;
        for (boolean prime : isPrime) {
            if (prime) count++;
        }
        
        return count;
    }
    
    /**
     * Utility: Fast Integer Square Root Check
     */
    public boolean isIntegerSquareRoot(int x) {
        if (x < 0) return false;
        int sqrt = mySqrt(x);
        return sqrt * sqrt == x;
    }
    
    /**
     * Utility: Babylonian Method (another name for Newton's method)
     */
    public double babylonianSqrt(double x) {
        if (x == 0) return 0;
        
        double guess = x;
        double precision = 1e-10;
        
        while (true) {
            double newGuess = (guess + x / guess) / 2.0;
            if (Math.abs(newGuess - guess) < precision) {
                return newGuess;
            }
            guess = newGuess;
        }
    }
    
    // Test cases
    public static void main(String[] args) {
        SqrtX solution = new SqrtX();
        
        // Test basic square root
        int[] testCases = {0, 1, 4, 8, 9, 16, 24, 25, 2147395599};
        
        System.out.println("Testing different approaches:");
        for (int x : testCases) {
            System.out.println("x = " + x);
            System.out.println("  Binary Search: " + solution.mySqrt(x));
            System.out.println("  Optimized: " + solution.mySqrtOptimized(x));
            System.out.println("  Newton's: " + solution.mySqrtNewton(x));
            System.out.println("  Bit Manipulation: " + solution.mySqrtBitManipulation(x));
            System.out.println("  Built-in check: " + (int)Math.sqrt(x));
            System.out.println();
        }
        
        // Test perfect squares
        System.out.println("Perfect Square Tests:");
        int[] perfectTests = {16, 14, 25, 26};
        for (int num : perfectTests) {
            System.out.println(num + " is perfect square: " + solution.isPerfectSquare(num));
        }
        
        // Test precision square root
        System.out.println("\nPrecision Square Root:");
        double[] precisionTests = {2.0, 10.0, 0.25};
        for (double x : precisionTests) {
            double result = solution.sqrtWithPrecision(x, 1e-6);
            System.out.println("sqrt(" + x + ") = " + result + " (built-in: " + Math.sqrt(x) + ")");
        }
        
        // Test nth root
        System.out.println("\nNth Root Tests:");
        System.out.println("3rd root of 27: " + solution.nthRoot(27, 3));
        System.out.println("4th root of 16: " + solution.nthRoot(16, 4));
        
        // Test integer square root check
        System.out.println("\nInteger Square Root Check:");
        int[] checkTests = {9, 10, 16, 17};
        for (int x : checkTests) {
            System.out.println(x + " has integer sqrt: " + solution.isIntegerSquareRoot(x));
        }
        
        // Test sqrt decomposition
        System.out.println("\nSquare Root Decomposition:");
        int[] arr = {1, 3, 5, 7, 9, 11};
        SqrtDecomposition sqrtDecomp = new SqrtDecomposition(arr);
        System.out.println("Range sum [1, 4]: " + sqrtDecomp.rangeSum(1, 4));
        sqrtDecomp.update(2, 10);
        System.out.println("After update index 2 to 10, range sum [1, 4]: " + sqrtDecomp.rangeSum(1, 4));
        
        // Test count primes
        System.out.println("\nCount Primes:");
        int[] primeTests = {10, 20, 100};
        for (int n : primeTests) {
            System.out.println("Primes less than " + n + ": " + solution.countPrimes(n));
        }
        
        // Test Babylonian method
        System.out.println("\nBabylonian Method:");
        double babylonian = solution.babylonianSqrt(50.0);
        System.out.println("Babylonian sqrt(50): " + babylonian + " (built-in: " + Math.sqrt(50) + ")");
        
        // Test Roman numeral conversion
        System.out.println("\nRoman Numeral:");
        int[] romanTests = {3, 4, 9, 58, 1994};
        for (int num : romanTests) {
            System.out.println(num + " -> " + solution.intToRoman(num));
        }
    }
} 