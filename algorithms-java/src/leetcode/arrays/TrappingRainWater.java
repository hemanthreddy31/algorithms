package leetcode.arrays;

import java.util.*;

/**
 * LeetCode 42: Trapping Rain Water
 * 
 * Given n non-negative integers representing an elevation map where the width of each bar is 1,
 * compute how much water it can trap after raining.
 * 
 * Example:
 * Input: height = [0,1,0,2,1,0,1,3,2,1,2,1]
 * Output: 6
 * Explanation: The elevation map is represented by the array.
 * In this case, 6 units of rain water are being trapped.
 */
public class TrappingRainWater {
    
    /**
     * Approach 1: Brute Force
     * Time Complexity: O(n²) - For each element, find max left and right
     * Space Complexity: O(1)
     * 
     * For each position, find water level = min(maxLeft, maxRight) - height
     */
    public int trapBruteForce(int[] height) {
        int n = height.length;
        int water = 0;
        
        for (int i = 0; i < n; i++) {
            int maxLeft = 0, maxRight = 0;
            
            // Find max height to the left
            for (int j = i; j >= 0; j--) {
                maxLeft = Math.max(maxLeft, height[j]);
            }
            
            // Find max height to the right
            for (int j = i; j < n; j++) {
                maxRight = Math.max(maxRight, height[j]);
            }
            
            // Water at current position
            water += Math.min(maxLeft, maxRight) - height[i];
        }
        
        return water;
    }
    
    /**
     * Approach 2: Dynamic Programming
     * Time Complexity: O(n) - Three passes
     * Space Complexity: O(n) - Two arrays
     * 
     * Pre-compute max heights to left and right
     */
    public int trapDP(int[] height) {
        if (height.length == 0) return 0;
        
        int n = height.length;
        int[] leftMax = new int[n];
        int[] rightMax = new int[n];
        
        // Fill leftMax array
        leftMax[0] = height[0];
        for (int i = 1; i < n; i++) {
            leftMax[i] = Math.max(leftMax[i - 1], height[i]);
        }
        
        // Fill rightMax array
        rightMax[n - 1] = height[n - 1];
        for (int i = n - 2; i >= 0; i--) {
            rightMax[i] = Math.max(rightMax[i + 1], height[i]);
        }
        
        // Calculate water
        int water = 0;
        for (int i = 0; i < n; i++) {
            water += Math.min(leftMax[i], rightMax[i]) - height[i];
        }
        
        return water;
    }
    
    /**
     * Approach 3: Stack
     * Time Complexity: O(n) - Single pass
     * Space Complexity: O(n) - Stack space
     * 
     * Use stack to track potential water containers
     */
    public int trapStack(int[] height) {
        Stack<Integer> stack = new Stack<>();
        int water = 0;
        
        for (int i = 0; i < height.length; i++) {
            // Process all bars that can trap water with current bar
            while (!stack.isEmpty() && height[i] > height[stack.peek()]) {
                int bottom = stack.pop();
                
                if (stack.isEmpty()) {
                    break;
                }
                
                int left = stack.peek();
                int width = i - left - 1;
                int minHeight = Math.min(height[left], height[i]);
                int waterHeight = minHeight - height[bottom];
                
                water += width * waterHeight;
            }
            
            stack.push(i);
        }
        
        return water;
    }
    
    /**
     * Approach 4: Two Pointers (Optimal)
     * Time Complexity: O(n) - Single pass
     * Space Complexity: O(1) - Constant space
     * 
     * Use two pointers to track water level from both ends
     */
    public int trap(int[] height) {
        if (height.length == 0) return 0;
        
        int left = 0, right = height.length - 1;
        int leftMax = 0, rightMax = 0;
        int water = 0;
        
        while (left < right) {
            if (height[left] < height[right]) {
                if (height[left] >= leftMax) {
                    leftMax = height[left];
                } else {
                    water += leftMax - height[left];
                }
                left++;
            } else {
                if (height[right] >= rightMax) {
                    rightMax = height[right];
                } else {
                    water += rightMax - height[right];
                }
                right--;
            }
        }
        
        return water;
    }
    
    /**
     * Approach 5: Horizontal Scanning
     * Time Complexity: O(n * h) - h is max height
     * Space Complexity: O(1)
     * 
     * Scan horizontally level by level
     */
    public int trapHorizontal(int[] height) {
        if (height.length == 0) return 0;
        
        int maxHeight = 0;
        for (int h : height) {
            maxHeight = Math.max(maxHeight, h);
        }
        
        int water = 0;
        
        // For each water level
        for (int level = 1; level <= maxHeight; level++) {
            int left = -1;
            
            for (int i = 0; i < height.length; i++) {
                if (height[i] >= level) {
                    if (left != -1) {
                        // Add water between left and current position
                        water += i - left - 1;
                    }
                    left = i;
                }
            }
        }
        
        return water;
    }
    
    /**
     * Extended Problem: Container With Most Water (Different from trapping)
     * LeetCode 11: Find two lines that form container with most water
     */
    public int maxArea(int[] height) {
        int left = 0, right = height.length - 1;
        int maxArea = 0;
        
        while (left < right) {
            int width = right - left;
            int minHeight = Math.min(height[left], height[right]);
            maxArea = Math.max(maxArea, width * minHeight);
            
            if (height[left] < height[right]) {
                left++;
            } else {
                right--;
            }
        }
        
        return maxArea;
    }
    
    /**
     * Extended Problem: Trapping Rain Water II (3D)
     * LeetCode 407: Given m x n matrix of heights, calculate trapped water
     */
    public int trapRainWater(int[][] heightMap) {
        if (heightMap.length == 0 || heightMap[0].length == 0) return 0;
        
        int m = heightMap.length;
        int n = heightMap[0].length;
        boolean[][] visited = new boolean[m][n];
        
        // Min heap to process cells by height
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> a[2] - b[2]);
        
        // Add all border cells to heap
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (i == 0 || i == m - 1 || j == 0 || j == n - 1) {
                    pq.offer(new int[]{i, j, heightMap[i][j]});
                    visited[i][j] = true;
                }
            }
        }
        
        int water = 0;
        int[][] dirs = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
        
        while (!pq.isEmpty()) {
            int[] cell = pq.poll();
            int row = cell[0], col = cell[1], height = cell[2];
            
            // Check all neighbors
            for (int[] dir : dirs) {
                int newRow = row + dir[0];
                int newCol = col + dir[1];
                
                if (newRow >= 0 && newRow < m && newCol >= 0 && newCol < n 
                    && !visited[newRow][newCol]) {
                    
                    visited[newRow][newCol] = true;
                    int newHeight = heightMap[newRow][newCol];
                    
                    // Water trapped = difference between boundary and cell height
                    water += Math.max(0, height - newHeight);
                    
                    // Add to heap with max of boundary and cell height
                    pq.offer(new int[]{newRow, newCol, Math.max(height, newHeight)});
                }
            }
        }
        
        return water;
    }
    
    /**
     * Helper: Visualize the water trapping
     */
    private void visualizeWater(int[] height) {
        int maxHeight = 0;
        for (int h : height) {
            maxHeight = Math.max(maxHeight, h);
        }
        
        System.out.println("Water Visualization:");
        
        // Print from top to bottom
        for (int level = maxHeight; level > 0; level--) {
            for (int i = 0; i < height.length; i++) {
                if (height[i] >= level) {
                    System.out.print("█");
                } else {
                    // Check if water exists at this level
                    int leftMax = 0, rightMax = 0;
                    for (int j = 0; j < i; j++) {
                        leftMax = Math.max(leftMax, height[j]);
                    }
                    for (int j = i + 1; j < height.length; j++) {
                        rightMax = Math.max(rightMax, height[j]);
                    }
                    
                    if (leftMax >= level && rightMax >= level) {
                        System.out.print("~");
                    } else {
                        System.out.print(" ");
                    }
                }
            }
            System.out.println();
        }
        
        // Print ground
        for (int i = 0; i < height.length; i++) {
            System.out.print("─");
        }
        System.out.println();
    }
    
    // Test the solutions
    public static void main(String[] args) {
        TrappingRainWater solution = new TrappingRainWater();
        
        // Test case 1: Standard example
        int[] height1 = {0, 1, 0, 2, 1, 0, 1, 3, 2, 1, 2, 1};
        System.out.println("Test Case 1: " + Arrays.toString(height1));
        solution.visualizeWater(height1);
        System.out.println("Brute Force: " + solution.trapBruteForce(height1));
        System.out.println("DP: " + solution.trapDP(height1));
        System.out.println("Stack: " + solution.trapStack(height1));
        System.out.println("Two Pointers: " + solution.trap(height1));
        System.out.println("Horizontal: " + solution.trapHorizontal(height1));
        
        // Test case 2: Simple case
        int[] height2 = {4, 2, 0, 3, 2, 5};
        System.out.println("\nTest Case 2: " + Arrays.toString(height2));
        solution.visualizeWater(height2);
        System.out.println("Water trapped: " + solution.trap(height2));
        
        // Test case 3: No water
        int[] height3 = {1, 2, 3, 4, 5};
        System.out.println("\nTest Case 3: " + Arrays.toString(height3));
        System.out.println("Water trapped: " + solution.trap(height3));
        
        // Test container with most water
        int[] height4 = {1, 8, 6, 2, 5, 4, 8, 3, 7};
        System.out.println("\nContainer With Most Water:");
        System.out.println("Heights: " + Arrays.toString(height4));
        System.out.println("Max area: " + solution.maxArea(height4));
        
        // Test 3D rain water
        int[][] heightMap = {
            {1, 4, 3, 1, 3, 2},
            {3, 2, 1, 3, 2, 4},
            {2, 3, 3, 2, 3, 1}
        };
        System.out.println("\n3D Rain Water Trapping:");
        System.out.println("Water trapped: " + solution.trapRainWater(heightMap));
    }
} 