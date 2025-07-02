package leetcode.arrays;

import java.util.Arrays;

/**
 * LeetCode 238: Product of Array Except Self
 * 
 * Given an integer array nums, return an array answer such that answer[i] is equal to 
 * the product of all the elements of nums except nums[i].
 * 
 * The product of any prefix or suffix of nums is guaranteed to fit in a 32-bit integer.
 * You must write an algorithm that runs in O(n) time and without using the division operation.
 * 
 * Example:
 * Input: nums = [1,2,3,4]
 * Output: [24,12,8,6]
 * Explanation: [2*3*4, 1*3*4, 1*2*4, 1*2*3]
 */
public class ProductOfArrayExceptSelf {
    
    /**
     * Approach 1: Brute Force (Not optimal)
     * Time Complexity: O(n²) - For each element, multiply all others
     * Space Complexity: O(1) - Only output array
     * 
     * Simple but inefficient
     */
    public int[] productExceptSelfBruteForce(int[] nums) {
        int n = nums.length;
        int[] result = new int[n];
        
        for (int i = 0; i < n; i++) {
            int product = 1;
            for (int j = 0; j < n; j++) {
                if (i != j) {
                    product *= nums[j];
                }
            }
            result[i] = product;
        }
        
        return result;
    }
    
    /**
     * Approach 2: Left and Right Product Arrays
     * Time Complexity: O(n) - Three passes through the array
     * Space Complexity: O(n) - Two extra arrays
     * 
     * Algorithm:
     * 1. left[i] = product of all elements to the left of i
     * 2. right[i] = product of all elements to the right of i
     * 3. result[i] = left[i] * right[i]
     */
    public int[] productExceptSelfTwoArrays(int[] nums) {
        int n = nums.length;
        int[] left = new int[n];
        int[] right = new int[n];
        int[] result = new int[n];
        
        // Build left products array
        left[0] = 1;
        for (int i = 1; i < n; i++) {
            left[i] = left[i - 1] * nums[i - 1];
        }
        
        // Build right products array
        right[n - 1] = 1;
        for (int i = n - 2; i >= 0; i--) {
            right[i] = right[i + 1] * nums[i + 1];
        }
        
        // Build result
        for (int i = 0; i < n; i++) {
            result[i] = left[i] * right[i];
        }
        
        return result;
    }
    
    /**
     * Approach 3: O(1) Space (Optimal)
     * Time Complexity: O(n) - Two passes
     * Space Complexity: O(1) - Only output array (not counted as extra space)
     * 
     * Algorithm:
     * 1. First pass: Store left products in result array
     * 2. Second pass: Multiply by right products on the fly
     */
    public int[] productExceptSelf(int[] nums) {
        int n = nums.length;
        int[] result = new int[n];
        
        // First pass: Calculate left products
        result[0] = 1;
        for (int i = 1; i < n; i++) {
            result[i] = result[i - 1] * nums[i - 1];
        }
        
        // Second pass: Multiply by right products
        int rightProduct = 1;
        for (int i = n - 1; i >= 0; i--) {
            result[i] *= rightProduct;
            rightProduct *= nums[i];
        }
        
        return result;
    }
    
    /**
     * Approach 4: Handle zeros separately
     * Time Complexity: O(n)
     * Space Complexity: O(1)
     * 
     * Special handling for arrays containing zeros
     */
    public int[] productExceptSelfWithZeros(int[] nums) {
        int n = nums.length;
        int[] result = new int[n];
        
        int zeroCount = 0;
        int productWithoutZeros = 1;
        int zeroIndex = -1;
        
        // Count zeros and calculate product without zeros
        for (int i = 0; i < n; i++) {
            if (nums[i] == 0) {
                zeroCount++;
                zeroIndex = i;
                if (zeroCount > 1) {
                    // More than one zero means all products are zero
                    return result;
                }
            } else {
                productWithoutZeros *= nums[i];
            }
        }
        
        // Fill result based on zero count
        if (zeroCount == 0) {
            // No zeros - use standard approach
            return productExceptSelf(nums);
        } else {
            // Exactly one zero - only that position has non-zero product
            result[zeroIndex] = productWithoutZeros;
            return result;
        }
    }
    
    /**
     * Extended Problem: Maximum Product Subarray
     * LeetCode 152: Find contiguous subarray with largest product
     */
    public int maxProduct(int[] nums) {
        if (nums.length == 0) return 0;
        
        int maxSoFar = nums[0];
        int minSoFar = nums[0];
        int result = nums[0];
        
        for (int i = 1; i < nums.length; i++) {
            int current = nums[i];
            int tempMax = Math.max(current, 
                          Math.max(maxSoFar * current, minSoFar * current));
            minSoFar = Math.min(current, 
                       Math.min(maxSoFar * current, minSoFar * current));
            
            maxSoFar = tempMax;
            result = Math.max(result, maxSoFar);
        }
        
        return result;
    }
    
    /**
     * Extended Problem: Product of Last K Numbers
     * Design a class that calculates product of last k numbers in stream
     */
    static class ProductOfNumbers {
        private List<Integer> products;
        
        public ProductOfNumbers() {
            products = new ArrayList<>();
            products.add(1); // Base case
        }
        
        public void add(int num) {
            if (num == 0) {
                // Reset products when encountering zero
                products = new ArrayList<>();
                products.add(1);
            } else {
                products.add(products.get(products.size() - 1) * num);
            }
        }
        
        public int getProduct(int k) {
            int n = products.size();
            // If k is larger than available products, there was a zero
            if (k >= n) return 0;
            
            return products.get(n - 1) / products.get(n - k - 1);
        }
    }
    
    // Helper method to print array
    private static void printArray(int[] arr) {
        System.out.println(Arrays.toString(arr));
    }
    
    // Test the solutions
    public static void main(String[] args) {
        ProductOfArrayExceptSelf solution = new ProductOfArrayExceptSelf();
        
        // Test case 1: Normal case
        int[] nums1 = {1, 2, 3, 4};
        System.out.println("Test Case 1: nums = [1,2,3,4]");
        System.out.print("Brute Force: ");
        printArray(solution.productExceptSelfBruteForce(nums1));
        System.out.print("Two Arrays: ");
        printArray(solution.productExceptSelfTwoArrays(nums1));
        System.out.print("Optimal: ");
        printArray(solution.productExceptSelf(nums1));
        
        // Test case 2: With zero
        int[] nums2 = {1, 2, 0, 4};
        System.out.println("\nTest Case 2: nums = [1,2,0,4]");
        System.out.print("With Zeros: ");
        printArray(solution.productExceptSelfWithZeros(nums2));
        
        // Test case 3: Multiple zeros
        int[] nums3 = {0, 0, 2, 3};
        System.out.println("\nTest Case 3: nums = [0,0,2,3]");
        System.out.print("Result: ");
        printArray(solution.productExceptSelfWithZeros(nums3));
        
        // Test maximum product subarray
        int[] nums4 = {2, 3, -2, 4};
        System.out.println("\nMaximum Product Subarray:");
        System.out.println("nums = [2,3,-2,4]");
        System.out.println("Max product: " + solution.maxProduct(nums4));
        
        // Test ProductOfNumbers
        System.out.println("\nProduct of Last K Numbers:");
        ProductOfNumbers productOfNumbers = new ProductOfNumbers();
        productOfNumbers.add(3);
        productOfNumbers.add(0);
        productOfNumbers.add(2);
        productOfNumbers.add(5);
        productOfNumbers.add(4);
        System.out.println("After adding [3,0,2,5,4]:");
        System.out.println("Product of last 2: " + productOfNumbers.getProduct(2));
        System.out.println("Product of last 3: " + productOfNumbers.getProduct(3));
        System.out.println("Product of last 4: " + productOfNumbers.getProduct(4));
    }
} 