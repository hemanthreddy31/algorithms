package leetcode.stack;

import java.util.*;

/**
 * LeetCode 739: Daily Temperatures
 * 
 * Given an array of integers temperatures represents the daily temperatures,
 * return an array answer such that answer[i] is the number of days you have to wait
 * after the ith day to get a warmer temperature. If there is no future day for which
 * this is possible, keep answer[i] == 0.
 * 
 * Example:
 * Input: temperatures = [73,74,75,71,69,72,76,73]
 * Output: [1,1,4,2,1,1,0,0]
 */
public class DailyTemperatures {
    
    /**
     * Approach 1: Monotonic Stack (Optimal)
     * Time Complexity: O(n) - Each element pushed and popped at most once
     * Space Complexity: O(n) - Stack can contain all indices
     * 
     * Key Insight: Use stack to maintain decreasing sequence of temperatures
     * When we find a warmer day, it resolves all previous cooler days
     * 
     * Algorithm:
     * 1. Iterate through temperatures
     * 2. While stack not empty and current temp > temp at stack top:
     *    - Pop index and calculate days difference
     * 3. Push current index to stack
     */
    public int[] dailyTemperatures(int[] temperatures) {
        int n = temperatures.length;
        int[] result = new int[n];
        Stack<Integer> stack = new Stack<>(); // Store indices
        
        for (int i = 0; i < n; i++) {
            // While current temperature is warmer than temperature at stack top
            while (!stack.isEmpty() && temperatures[i] > temperatures[stack.peek()]) {
                int prevIndex = stack.pop();
                result[prevIndex] = i - prevIndex; // Days to wait
            }
            
            // Push current index
            stack.push(i);
        }
        
        // Remaining indices in stack have no warmer day (result[i] = 0 by default)
        return result;
    }
    
    /**
     * Approach 2: Brute Force
     * Time Complexity: O(n²) - For each day, check all future days
     * Space Complexity: O(1) - Only result array
     * 
     * For comparison - shows why monotonic stack is needed
     */
    public int[] dailyTemperaturesBruteForce(int[] temperatures) {
        int n = temperatures.length;
        int[] result = new int[n];
        
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                if (temperatures[j] > temperatures[i]) {
                    result[i] = j - i;
                    break;
                }
            }
            // If no warmer day found, result[i] remains 0
        }
        
        return result;
    }
    
    /**
     * Approach 3: Optimized with Early Termination
     * Time Complexity: O(n²) worst case, but with optimizations
     * Space Complexity: O(1)
     * 
     * Optimization: Skip days when temperature is at maximum possible
     */
    public int[] dailyTemperaturesOptimized(int[] temperatures) {
        int n = temperatures.length;
        int[] result = new int[n];
        int maxTemp = 0;
        
        // Find maximum temperature for optimization
        for (int temp : temperatures) {
            maxTemp = Math.max(maxTemp, temp);
        }
        
        for (int i = 0; i < n; i++) {
            // If current temperature is maximum, no warmer day exists
            if (temperatures[i] == maxTemp) {
                result[i] = 0;
                continue;
            }
            
            for (int j = i + 1; j < n; j++) {
                if (temperatures[j] > temperatures[i]) {
                    result[i] = j - i;
                    break;
                }
            }
        }
        
        return result;
    }
    
    /**
     * Approach 4: Array-based Stack (Space Optimized)
     * Time Complexity: O(n)
     * Space Complexity: O(n) - Using array instead of Stack object
     * 
     * Uses array to simulate stack for better memory performance
     */
    public int[] dailyTemperaturesArrayStack(int[] temperatures) {
        int n = temperatures.length;
        int[] result = new int[n];
        int[] stack = new int[n]; // Array-based stack
        int top = -1; // Stack pointer
        
        for (int i = 0; i < n; i++) {
            // While stack not empty and current temp > temp at stack top
            while (top >= 0 && temperatures[i] > temperatures[stack[top]]) {
                int prevIndex = stack[top--];
                result[prevIndex] = i - prevIndex;
            }
            
            // Push current index
            stack[++top] = i;
        }
        
        return result;
    }
    
    /**
     * Extended Problem: Next Greater Element I
     * LeetCode 496: Find next greater element for each element in array
     */
    public int[] nextGreaterElement(int[] nums1, int[] nums2) {
        Map<Integer, Integer> nextGreaterMap = new HashMap<>();
        Stack<Integer> stack = new Stack<>();
        
        // Build next greater map for nums2
        for (int num : nums2) {
            while (!stack.isEmpty() && num > stack.peek()) {
                nextGreaterMap.put(stack.pop(), num);
            }
            stack.push(num);
        }
        
        // Build result for nums1
        int[] result = new int[nums1.length];
        for (int i = 0; i < nums1.length; i++) {
            result[i] = nextGreaterMap.getOrDefault(nums1[i], -1);
        }
        
        return result;
    }
    
    /**
     * Extended Problem: Next Greater Element II (Circular Array)
     * LeetCode 503: Find next greater element in circular array
     */
    public int[] nextGreaterElementsCircular(int[] nums) {
        int n = nums.length;
        int[] result = new int[n];
        Arrays.fill(result, -1);
        Stack<Integer> stack = new Stack<>();
        
        // Process array twice to handle circular nature
        for (int i = 0; i < 2 * n; i++) {
            int currentIndex = i % n;
            
            while (!stack.isEmpty() && nums[currentIndex] > nums[stack.peek()]) {
                result[stack.pop()] = nums[currentIndex];
            }
            
            // Only push indices in first pass
            if (i < n) {
                stack.push(currentIndex);
            }
        }
        
        return result;
    }
    
    /**
     * Extended Problem: Previous Greater Element
     * Find previous greater element for each element
     */
    public int[] previousGreaterElement(int[] nums) {
        int n = nums.length;
        int[] result = new int[n];
        Arrays.fill(result, -1);
        Stack<Integer> stack = new Stack<>();
        
        for (int i = 0; i < n; i++) {
            // Remove elements smaller than current
            while (!stack.isEmpty() && nums[stack.peek()] <= nums[i]) {
                stack.pop();
            }
            
            // If stack not empty, top is previous greater
            if (!stack.isEmpty()) {
                result[i] = nums[stack.peek()];
            }
            
            stack.push(i);
        }
        
        return result;
    }
    
    /**
     * Extended Problem: Largest Rectangle in Histogram
     * LeetCode 84: Related monotonic stack problem
     */
    public int largestRectangleArea(int[] heights) {
        Stack<Integer> stack = new Stack<>();
        int maxArea = 0;
        
        for (int i = 0; i <= heights.length; i++) {
            int currentHeight = (i == heights.length) ? 0 : heights[i];
            
            while (!stack.isEmpty() && currentHeight < heights[stack.peek()]) {
                int height = heights[stack.pop()];
                int width = stack.isEmpty() ? i : i - stack.peek() - 1;
                maxArea = Math.max(maxArea, height * width);
            }
            
            stack.push(i);
        }
        
        return maxArea;
    }
    
    // Helper method to print array
    private static void printArray(int[] arr) {
        System.out.println(Arrays.toString(arr));
    }
    
    // Test the solutions
    public static void main(String[] args) {
        DailyTemperatures solution = new DailyTemperatures();
        
        // Test case 1: Standard example
        int[] temperatures1 = {73, 74, 75, 71, 69, 72, 76, 73};
        System.out.println("Test Case 1: Daily Temperatures");
        System.out.println("Input: " + Arrays.toString(temperatures1));
        
        System.out.print("Monotonic Stack: ");
        printArray(solution.dailyTemperatures(temperatures1));
        
        System.out.print("Brute Force: ");
        printArray(solution.dailyTemperaturesBruteForce(temperatures1));
        
        System.out.print("Array Stack: ");
        printArray(solution.dailyTemperaturesArrayStack(temperatures1));
        
        // Test case 2: Increasing temperatures
        int[] temperatures2 = {30, 40, 50, 60};
        System.out.println("\nTest Case 2: Increasing temperatures");
        System.out.println("Input: " + Arrays.toString(temperatures2));
        System.out.print("Result: ");
        printArray(solution.dailyTemperatures(temperatures2));
        
        // Test case 3: Decreasing temperatures
        int[] temperatures3 = {30, 60, 90};
        System.out.println("\nTest Case 3: Mixed temperatures");
        System.out.println("Input: " + Arrays.toString(temperatures3));
        System.out.print("Result: ");
        printArray(solution.dailyTemperatures(temperatures3));
        
        // Test extended problems
        System.out.println("\nExtended Problems:");
        
        // Next Greater Element I
        int[] nums1 = {4, 1, 2};
        int[] nums2 = {1, 3, 4, 2};
        System.out.println("Next Greater Element I:");
        System.out.println("nums1: " + Arrays.toString(nums1));
        System.out.println("nums2: " + Arrays.toString(nums2));
        System.out.print("Result: ");
        printArray(solution.nextGreaterElement(nums1, nums2));
        
        // Next Greater Element II (Circular)
        int[] circular = {1, 2, 1};
        System.out.println("Next Greater Element II (Circular):");
        System.out.println("Input: " + Arrays.toString(circular));
        System.out.print("Result: ");
        printArray(solution.nextGreaterElementsCircular(circular));
        
        // Previous Greater Element
        int[] prev = {1, 3, 2, 4};
        System.out.println("Previous Greater Element:");
        System.out.println("Input: " + Arrays.toString(prev));
        System.out.print("Result: ");
        printArray(solution.previousGreaterElement(prev));
        
        // Largest Rectangle in Histogram
        int[] heights = {2, 1, 5, 6, 2, 3};
        System.out.println("Largest Rectangle in Histogram:");
        System.out.println("Input: " + Arrays.toString(heights));
        System.out.println("Max Area: " + solution.largestRectangleArea(heights));
    }
} 