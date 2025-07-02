package leetcode.twopointers;

/**
 * LeetCode 11: Container With Most Water
 * 
 * You are given an integer array height of length n. There are n vertical lines drawn such 
 * that the two endpoints of the ith line are (i, 0) and (i, height[i]).
 * 
 * Find two lines that together with the x-axis form a container, such that the container 
 * contains the most water.
 * 
 * Return the maximum amount of water a container can store.
 * 
 * Example:
 * Input: height = [1,8,6,2,5,4,8,3,7]
 * Output: 49
 * Explanation: The vertical lines are at indices 0,1,2,3,4,5,6,7,8. 
 * The max area is between lines at index 1 and 8: min(8,7) * (8-1) = 49
 */
public class ContainerWithMostWater {
    
    /**
     * Approach 1: Brute Force
     * Time Complexity: O(n²) - Check all possible pairs
     * Space Complexity: O(1) - No extra space needed
     * 
     * Algorithm:
     * 1. Try all possible pairs of lines
     * 2. Calculate area for each pair: min(height[i], height[j]) * (j - i)
     * 3. Keep track of maximum area
     */
    public int maxAreaBruteForce(int[] height) {
        int maxArea = 0;
        
        // Try all possible pairs
        for (int i = 0; i < height.length - 1; i++) {
            for (int j = i + 1; j < height.length; j++) {
                // Calculate area between lines i and j
                int width = j - i;
                int minHeight = Math.min(height[i], height[j]);
                int area = width * minHeight;
                
                // Update max area if current is larger
                maxArea = Math.max(maxArea, area);
            }
        }
        
        return maxArea;
    }
    
    /**
     * Approach 2: Two Pointers (Optimal)
     * Time Complexity: O(n) - Single pass with two pointers
     * Space Complexity: O(1) - Only two pointers used
     * 
     * Key Insight: Start with widest container and gradually make it narrower.
     * Move the pointer pointing to the shorter line inward.
     * 
     * Why this works:
     * - If we move the pointer at the taller line, we can't get more area
     * - Moving the pointer at the shorter line might give us more area
     * 
     * Algorithm:
     * 1. Start with two pointers at the beginning and end
     * 2. Calculate current area
     * 3. Move the pointer pointing to the shorter line
     * 4. Repeat until pointers meet
     */
    public int maxAreaOptimal(int[] height) {
        int maxArea = 0;
        int left = 0;
        int right = height.length - 1;
        
        while (left < right) {
            // Calculate current area
            int width = right - left;
            int minHeight = Math.min(height[left], height[right]);
            int currentArea = width * minHeight;
            
            // Update max area
            maxArea = Math.max(maxArea, currentArea);
            
            // Move the pointer pointing to the shorter line
            if (height[left] < height[right]) {
                left++;
            } else {
                right--;
            }
        }
        
        return maxArea;
    }
    
    /**
     * Approach 3: Two Pointers with Optimization
     * Time Complexity: O(n) - Single pass
     * Space Complexity: O(1) - Only extra variables
     * 
     * Optimization: Skip lines that are shorter than the current boundary
     */
    public int maxAreaOptimized(int[] height) {
        int maxArea = 0;
        int left = 0;
        int right = height.length - 1;
        
        while (left < right) {
            // Calculate current area
            int width = right - left;
            int leftHeight = height[left];
            int rightHeight = height[right];
            int minHeight = Math.min(leftHeight, rightHeight);
            int currentArea = width * minHeight;
            
            maxArea = Math.max(maxArea, currentArea);
            
            // Move pointer and skip shorter lines
            if (leftHeight < rightHeight) {
                // Skip all lines shorter than current left
                int currentLeft = leftHeight;
                while (left < right && height[left] <= currentLeft) {
                    left++;
                }
            } else {
                // Skip all lines shorter than current right
                int currentRight = rightHeight;
                while (left < right && height[right] <= currentRight) {
                    right--;
                }
            }
        }
        
        return maxArea;
    }
    
    /**
     * Approach 4: Two Pointers with Early Termination
     * Time Complexity: O(n) - Single pass
     * Space Complexity: O(1) - Only extra variables
     * 
     * Early termination: If remaining width * max possible height < current max area
     */
    public int maxAreaEarlyTermination(int[] height) {
        int maxArea = 0;
        int left = 0;
        int right = height.length - 1;
        int maxHeight = 0;
        
        // Find maximum height for early termination
        for (int h : height) {
            maxHeight = Math.max(maxHeight, h);
        }
        
        while (left < right) {
            // Early termination check
            int remainingWidth = right - left;
            if (remainingWidth * maxHeight <= maxArea) {
                break;
            }
            
            // Calculate current area
            int minHeight = Math.min(height[left], height[right]);
            int currentArea = remainingWidth * minHeight;
            maxArea = Math.max(maxArea, currentArea);
            
            // Move the shorter pointer
            if (height[left] < height[right]) {
                left++;
            } else {
                right--;
            }
        }
        
        return maxArea;
    }
    
    /**
     * Approach 5: Detailed Two Pointers with Explanation
     * This version includes detailed tracking for understanding
     */
    public int maxAreaDetailed(int[] height) {
        int maxArea = 0;
        int left = 0;
        int right = height.length - 1;
        int bestLeft = 0, bestRight = 0;
        
        System.out.println("Starting two-pointer search...");
        
        while (left < right) {
            int width = right - left;
            int leftHeight = height[left];
            int rightHeight = height[right];
            int minHeight = Math.min(leftHeight, rightHeight);
            int currentArea = width * minHeight;
            
            System.out.printf("Left=%d (h=%d), Right=%d (h=%d), Width=%d, Area=%d%n",
                    left, leftHeight, right, rightHeight, width, currentArea);
            
            if (currentArea > maxArea) {
                maxArea = currentArea;
                bestLeft = left;
                bestRight = right;
            }
            
            // Move the pointer at the shorter line
            if (leftHeight < rightHeight) {
                left++;
                System.out.println("  Moving left pointer (shorter line)");
            } else {
                right--;
                System.out.println("  Moving right pointer (shorter line)");
            }
        }
        
        System.out.printf("Best container: lines at %d and %d with area %d%n",
                bestLeft, bestRight, maxArea);
        
        return maxArea;
    }
    
    // Test the solutions
    public static void main(String[] args) {
        ContainerWithMostWater solution = new ContainerWithMostWater();
        
        // Test case 1
        int[] height1 = {1, 8, 6, 2, 5, 4, 8, 3, 7};
        System.out.println("Test Case 1: [1,8,6,2,5,4,8,3,7]");
        System.out.println("Brute Force: " + solution.maxAreaBruteForce(height1));
        System.out.println("Optimal: " + solution.maxAreaOptimal(height1));
        System.out.println("Optimized: " + solution.maxAreaOptimized(height1));
        System.out.println();
        
        // Test case 2: Two elements
        int[] height2 = {1, 1};
        System.out.println("Test Case 2: [1,1]");
        System.out.println("Optimal: " + solution.maxAreaOptimal(height2));
        System.out.println();
        
        // Test case 3: Increasing heights
        int[] height3 = {1, 2, 3, 4, 5};
        System.out.println("Test Case 3: [1,2,3,4,5]");
        System.out.println("Optimal: " + solution.maxAreaOptimal(height3));
        System.out.println();
        
        // Test case 4: With detailed explanation
        int[] height4 = {1, 3, 2, 5, 25, 24, 5};
        System.out.println("Test Case 4: [1,3,2,5,25,24,5] - Detailed");
        solution.maxAreaDetailed(height4);
    }
} 