package leetcode.graph;

import java.util.*;

/**
 * LeetCode 417: Pacific Atlantic Water Flow
 * 
 * Given an m x n rectangular island that borders both the Pacific Ocean and Atlantic Ocean.
 * The Pacific Ocean touches the island's left and top edges, and the Atlantic Ocean touches 
 * the island's right and bottom edges.
 * 
 * The island is partitioned into a grid of square cells. You are given an m x n integer 
 * matrix heights where heights[r][c] represents the height above sea level of the cell at (r, c).
 * 
 * Water can flow in four directions. Water flows from a cell to adjacent cells only if the 
 * height of the adjacent cell is less than or equal to the current cell's height.
 * 
 * Return a 2D list of grid coordinates result where result[i] = [ri, ci] denotes that 
 * rain water can flow from cell (ri, ci) to both the Pacific and Atlantic oceans.
 */
public class PacificAtlanticWaterFlow {
    
    /**
     * Approach: Multi-source DFS from Ocean Boundaries
     * Time: O(m*n), Space: O(m*n)
     * 
     * Start DFS from ocean boundaries and find reachable cells
     */
    public List<List<Integer>> pacificAtlantic(int[][] heights) {
        List<List<Integer>> result = new ArrayList<>();
        if (heights == null || heights.length == 0) return result;
        
        int rows = heights.length, cols = heights[0].length;
        boolean[][] pacific = new boolean[rows][cols];
        boolean[][] atlantic = new boolean[rows][cols];
        
        // DFS from Pacific boundaries (top and left)
        for (int i = 0; i < rows; i++) {
            dfs(heights, pacific, i, 0, heights[i][0]);
        }
        for (int j = 0; j < cols; j++) {
            dfs(heights, pacific, 0, j, heights[0][j]);
        }
        
        // DFS from Atlantic boundaries (bottom and right)
        for (int i = 0; i < rows; i++) {
            dfs(heights, atlantic, i, cols - 1, heights[i][cols - 1]);
        }
        for (int j = 0; j < cols; j++) {
            dfs(heights, atlantic, rows - 1, j, heights[rows - 1][j]);
        }
        
        // Find intersection
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (pacific[i][j] && atlantic[i][j]) {
                    result.add(Arrays.asList(i, j));
                }
            }
        }
        
        return result;
    }
    
    private void dfs(int[][] heights, boolean[][] reachable, int row, int col, int prevHeight) {
        int rows = heights.length, cols = heights[0].length;
        
        if (row < 0 || row >= rows || col < 0 || col >= cols ||
            reachable[row][col] || heights[row][col] < prevHeight) {
            return;
        }
        
        reachable[row][col] = true;
        
        int[][] dirs = {{-1,0}, {1,0}, {0,-1}, {0,1}};
        for (int[] dir : dirs) {
            dfs(heights, reachable, row + dir[0], col + dir[1], heights[row][col]);
        }
    }
    
    /**
     * Approach 2: Multi-source BFS from Ocean Boundaries
     * Time Complexity: O(m*n) - Visit each cell at most twice
     * Space Complexity: O(m*n) - Two boolean arrays and queues
     * 
     * Same concept as DFS but using BFS for traversal
     */
    public List<List<Integer>> pacificAtlanticBFS(int[][] heights) {
        List<List<Integer>> result = new ArrayList<>();
        
        if (heights == null || heights.length == 0) {
            return result;
        }
        
        int rows = heights.length;
        int cols = heights[0].length;
        
        boolean[][] pacificReachable = new boolean[rows][cols];
        boolean[][] atlanticReachable = new boolean[rows][cols];
        
        Queue<int[]> pacificQueue = new LinkedList<>();
        Queue<int[]> atlanticQueue = new LinkedList<>();
        
        // Add Pacific boundary cells to queue
        for (int i = 0; i < rows; i++) {
            pacificQueue.offer(new int[]{i, 0});
            pacificReachable[i][0] = true;
        }
        for (int j = 1; j < cols; j++) {
            pacificQueue.offer(new int[]{0, j});
            pacificReachable[0][j] = true;
        }
        
        // Add Atlantic boundary cells to queue
        for (int i = 0; i < rows; i++) {
            atlanticQueue.offer(new int[]{i, cols - 1});
            atlanticReachable[i][cols - 1] = true;
        }
        for (int j = 0; j < cols - 1; j++) {
            atlanticQueue.offer(new int[]{rows - 1, j});
            atlanticReachable[rows - 1][j] = true;
        }
        
        // BFS from Pacific
        bfs(heights, pacificQueue, pacificReachable);
        
        // BFS from Atlantic
        bfs(heights, atlanticQueue, atlanticReachable);
        
        // Find intersection
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (pacificReachable[i][j] && atlanticReachable[i][j]) {
                    result.add(Arrays.asList(i, j));
                }
            }
        }
        
        return result;
    }
    
    private void bfs(int[][] heights, Queue<int[]> queue, boolean[][] reachable) {
        int rows = heights.length;
        int cols = heights[0].length;
        int[][] directions = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
        
        while (!queue.isEmpty()) {
            int[] current = queue.poll();
            int row = current[0];
            int col = current[1];
            
            for (int[] dir : directions) {
                int newRow = row + dir[0];
                int newCol = col + dir[1];
                
                if (newRow >= 0 && newRow < rows && newCol >= 0 && newCol < cols &&
                    !reachable[newRow][newCol] && heights[newRow][newCol] >= heights[row][col]) {
                    reachable[newRow][newCol] = true;
                    queue.offer(new int[]{newRow, newCol});
                }
            }
        }
    }
    
    /**
     * Approach 3: Brute Force (For comparison - inefficient)
     * Time Complexity: O(m*n*(m+n)) - For each cell, potentially traverse entire grid
     * Space Complexity: O(m*n) - Recursion stack and visited array
     * 
     * Check each cell individually if it can reach both oceans
     */
    public List<List<Integer>> pacificAtlanticBruteForce(int[][] heights) {
        List<List<Integer>> result = new ArrayList<>();
        
        if (heights == null || heights.length == 0) {
            return result;
        }
        
        int rows = heights.length;
        int cols = heights[0].length;
        
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (canReachPacific(heights, i, j, new boolean[rows][cols]) &&
                    canReachAtlantic(heights, i, j, new boolean[rows][cols])) {
                    result.add(Arrays.asList(i, j));
                }
            }
        }
        
        return result;
    }
    
    private boolean canReachPacific(int[][] heights, int row, int col, boolean[][] visited) {
        int rows = heights.length;
        int cols = heights[0].length;
        
        // Reached Pacific boundary
        if (row == 0 || col == 0) {
            return true;
        }
        
        if (visited[row][col]) {
            return false;
        }
        
        visited[row][col] = true;
        
        int[][] directions = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
        for (int[] dir : directions) {
            int newRow = row + dir[0];
            int newCol = col + dir[1];
            
            if (newRow >= 0 && newRow < rows && newCol >= 0 && newCol < cols &&
                heights[newRow][newCol] <= heights[row][col] &&
                canReachPacific(heights, newRow, newCol, visited)) {
                return true;
            }
        }
        
        return false;
    }
    
    private boolean canReachAtlantic(int[][] heights, int row, int col, boolean[][] visited) {
        int rows = heights.length;
        int cols = heights[0].length;
        
        // Reached Atlantic boundary
        if (row == rows - 1 || col == cols - 1) {
            return true;
        }
        
        if (visited[row][col]) {
            return false;
        }
        
        visited[row][col] = true;
        
        int[][] directions = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
        for (int[] dir : directions) {
            int newRow = row + dir[0];
            int newCol = col + dir[1];
            
            if (newRow >= 0 && newRow < rows && newCol >= 0 && newCol < cols &&
                heights[newRow][newCol] <= heights[row][col] &&
                canReachAtlantic(heights, newRow, newCol, visited)) {
                return true;
            }
        }
        
        return false;
    }
    
    /**
     * Extended Problem: Water Flow with K Oceans
     * Generalized version where water can flow to K different oceans at different boundaries
     */
    public List<List<Integer>> waterFlowKOceans(int[][] heights, int k) {
        List<List<Integer>> result = new ArrayList<>();
        
        if (heights == null || heights.length == 0 || k <= 0) {
            return result;
        }
        
        int rows = heights.length;
        int cols = heights[0].length;
        
        // For simplicity, assume k <= 4 (top, right, bottom, left)
        boolean[][][] reachable = new boolean[k][rows][cols];
        
        // Define boundaries for each ocean
        int[][][] boundaries = {
            {{0, 0, 0, cols - 1}}, // Top boundary
            {{0, rows - 1, cols - 1, cols - 1}}, // Right boundary
            {{rows - 1, rows - 1, 0, cols - 1}}, // Bottom boundary
            {{0, rows - 1, 0, 0}} // Left boundary
        };
        
        // Run DFS from each ocean's boundary
        for (int ocean = 0; ocean < Math.min(k, 4); ocean++) {
            for (int[] boundary : boundaries[ocean]) {
                for (int i = boundary[0]; i <= boundary[1]; i++) {
                    for (int j = boundary[2]; j <= boundary[3]; j++) {
                        dfs(heights, reachable[ocean], i, j, heights[i][j]);
                    }
                }
            }
        }
        
        // Find cells reachable from all k oceans
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                boolean reachableFromAll = true;
                for (int ocean = 0; ocean < Math.min(k, 4); ocean++) {
                    if (!reachable[ocean][i][j]) {
                        reachableFromAll = false;
                        break;
                    }
                }
                if (reachableFromAll) {
                    result.add(Arrays.asList(i, j));
                }
            }
        }
        
        return result;
    }
    
    /**
     * Helper method to print the grid with results highlighted
     */
    private static void printGridWithResults(int[][] heights, List<List<Integer>> results) {
        int rows = heights.length;
        int cols = heights[0].length;
        boolean[][] isResult = new boolean[rows][cols];
        
        for (List<Integer> coord : results) {
            isResult[coord.get(0)][coord.get(1)] = true;
        }
        
        System.out.println("Grid (results marked with *):");
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                System.out.printf("%2d%s ", heights[i][j], isResult[i][j] ? "*" : " ");
            }
            System.out.println();
        }
    }
    
    // Test the solutions
    public static void main(String[] args) {
        PacificAtlanticWaterFlow solution = new PacificAtlanticWaterFlow();
        
        // Test case 1: Standard example
        int[][] heights1 = {
            {1, 2, 2, 3, 5},
            {3, 2, 3, 4, 4},
            {2, 4, 5, 3, 1},
            {6, 7, 1, 4, 5},
            {5, 1, 1, 2, 4}
        };
        
        System.out.println("Test Case 1:");
        List<List<Integer>> result1DFS = solution.pacificAtlantic(heights1);
        System.out.println("DFS Result: " + result1DFS);
        printGridWithResults(heights1, result1DFS);
        
        List<List<Integer>> result1BFS = solution.pacificAtlanticBFS(heights1);
        System.out.println("BFS Result: " + result1BFS);
        System.out.println("Results match: " + result1DFS.equals(result1BFS));
        
        // Test case 2: Small grid
        int[][] heights2 = {
            {2, 1},
            {1, 2}
        };
        
        System.out.println("\nTest Case 2:");
        List<List<Integer>> result2 = solution.pacificAtlantic(heights2);
        System.out.println("Result: " + result2);
        printGridWithResults(heights2, result2);
        
        // Test case 3: Single cell
        int[][] heights3 = {{1}};
        
        System.out.println("\nTest Case 3 (Single cell):");
        List<List<Integer>> result3 = solution.pacificAtlantic(heights3);
        System.out.println("Result: " + result3);
        
        // Test case 4: All same height
        int[][] heights4 = {
            {1, 1, 1},
            {1, 1, 1},
            {1, 1, 1}
        };
        
        System.out.println("\nTest Case 4 (All same height):");
        List<List<Integer>> result4 = solution.pacificAtlantic(heights4);
        System.out.println("Result: " + result4);
        printGridWithResults(heights4, result4);
        
        // Performance comparison
        System.out.println("\nPerformance Comparison:");
        long startTime = System.nanoTime();
        solution.pacificAtlantic(heights1);
        long dfsTime = System.nanoTime() - startTime;
        
        startTime = System.nanoTime();
        solution.pacificAtlanticBFS(heights1);
        long bfsTime = System.nanoTime() - startTime;
        
        System.out.println("DFS time: " + dfsTime + " ns");
        System.out.println("BFS time: " + bfsTime + " ns");
        
        // Test extended problem
        System.out.println("\nExtended Problem (K=3 oceans):");
        List<List<Integer>> resultK = solution.waterFlowKOceans(heights1, 3);
        System.out.println("K=3 Result: " + resultK);
    }
} 