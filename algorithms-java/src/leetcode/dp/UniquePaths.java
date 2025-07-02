package leetcode.dp;

import java.util.*;

/**
 * LeetCode 62: Unique Paths
 * 
 * There is a robot on an m x n grid. The robot is initially located at the top-left corner.
 * The robot tries to move to the bottom-right corner. The robot can only move either down or right.
 * 
 * Given the two integers m and n, return the number of possible unique paths.
 */
public class UniquePaths {
    
    /**
     * Approach 1: 2D DP
     * Time: O(m*n), Space: O(m*n)
     * Classic grid DP problem
     */
    public int uniquePaths(int m, int n) {
        int[][] dp = new int[m][n];
        
        // Initialize first row and column
        for (int i = 0; i < m; i++) dp[i][0] = 1;
        for (int j = 0; j < n; j++) dp[0][j] = 1;
        
        // Fill the DP table
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                dp[i][j] = dp[i-1][j] + dp[i][j-1];
            }
        }
        
        return dp[m-1][n-1];
    }
    
    /**
     * Approach 2: Space Optimized 1D DP
     * Time: O(m*n), Space: O(min(m,n))
     * Only need previous row
     */
    public int uniquePathsOptimized(int m, int n) {
        // Use smaller dimension for space optimization
        if (m > n) return uniquePathsOptimized(n, m);
        
        int[] dp = new int[m];
        Arrays.fill(dp, 1);
        
        for (int j = 1; j < n; j++) {
            for (int i = 1; i < m; i++) {
                dp[i] += dp[i-1];
            }
        }
        
        return dp[m-1];
    }
    
    /**
     * Approach 3: Mathematical (Combinatorial)
     * Time: O(min(m,n)), Space: O(1)
     * Total moves = (m-1) + (n-1), choose (m-1) down moves
     * Answer = C(m+n-2, m-1)
     */
    public int uniquePathsMath(int m, int n) {
        int totalMoves = m + n - 2;
        int downMoves = m - 1;
        
        long result = 1;
        
        // Calculate C(totalMoves, downMoves) = totalMoves! / (downMoves! * (totalMoves-downMoves)!)
        for (int i = 0; i < downMoves; i++) {
            result = result * (totalMoves - i) / (i + 1);
        }
        
        return (int) result;
    }
    
    /**
     * Extended Problem: Unique Paths II (LC 63)
     * Grid with obstacles
     */
    public int uniquePathsWithObstacles(int[][] obstacleGrid) {
        int m = obstacleGrid.length;
        int n = obstacleGrid[0].length;
        
        if (obstacleGrid[0][0] == 1) return 0;
        
        int[][] dp = new int[m][n];
        dp[0][0] = 1;
        
        // Initialize first column
        for (int i = 1; i < m; i++) {
            dp[i][0] = (obstacleGrid[i][0] == 0 && dp[i-1][0] == 1) ? 1 : 0;
        }
        
        // Initialize first row
        for (int j = 1; j < n; j++) {
            dp[0][j] = (obstacleGrid[0][j] == 0 && dp[0][j-1] == 1) ? 1 : 0;
        }
        
        // Fill DP table
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                if (obstacleGrid[i][j] == 0) {
                    dp[i][j] = dp[i-1][j] + dp[i][j-1];
                }
            }
        }
        
        return dp[m-1][n-1];
    }
    
    /**
     * Extended Problem: Minimum Path Sum (LC 64)
     * Find path with minimum sum
     */
    public int minPathSum(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        
        int[][] dp = new int[m][n];
        dp[0][0] = grid[0][0];
        
        // Initialize first row
        for (int j = 1; j < n; j++) {
            dp[0][j] = dp[0][j-1] + grid[0][j];
        }
        
        // Initialize first column
        for (int i = 1; i < m; i++) {
            dp[i][0] = dp[i-1][0] + grid[i][0];
        }
        
        // Fill DP table
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                dp[i][j] = Math.min(dp[i-1][j], dp[i][j-1]) + grid[i][j];
            }
        }
        
        return dp[m-1][n-1];
    }
    
    /**
     * Extended Problem: Unique Paths with Path Reconstruction
     * Return one possible path
     */
    public List<String> uniquePathWithPath(int m, int n) {
        List<String> result = new ArrayList<>();
        int totalDown = m - 1;
        int totalRight = n - 1;
        
        int currentRow = 0, currentCol = 0;
        
        while (currentRow < m - 1 || currentCol < n - 1) {
            if (currentRow == m - 1) {
                // Can only go right
                result.add("Right");
                currentCol++;
            } else if (currentCol == n - 1) {
                // Can only go down
                result.add("Down");
                currentRow++;
            } else {
                // Can go either way - choose based on remaining paths
                int pathsIfDown = uniquePathsMath(m - currentRow - 1, n - currentCol);
                int pathsIfRight = uniquePathsMath(m - currentRow, n - currentCol - 1);
                
                if (pathsIfDown >= pathsIfRight) {
                    result.add("Down");
                    currentRow++;
                } else {
                    result.add("Right");
                    currentCol++;
                }
            }
        }
        
        return result;
    }
    
    /**
     * Extended Problem: Paths with Exactly K Steps
     * Find number of paths with exactly k steps (allowing backtracking)
     */
    public int uniquePathsKSteps(int m, int n, int k, int startRow, int startCol, int endRow, int endCol) {
        int[][][] dp = new int[m][n][k + 1];
        dp[startRow][startCol][0] = 1;
        
        int[][] dirs = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}}; // right, left, down, up
        
        for (int step = 1; step <= k; step++) {
            for (int i = 0; i < m; i++) {
                for (int j = 0; j < n; j++) {
                    for (int[] dir : dirs) {
                        int prevI = i - dir[0];
                        int prevJ = j - dir[1];
                        
                        if (prevI >= 0 && prevI < m && prevJ >= 0 && prevJ < n) {
                            dp[i][j][step] += dp[prevI][prevJ][step - 1];
                        }
                    }
                }
            }
        }
        
        return dp[endRow][endCol][k];
    }
    
    /**
     * Extended Problem: Maximum Path Sum in Grid
     * Two robots moving simultaneously from top-left to bottom-right
     */
    public int cherryPickup(int[][] grid) {
        int n = grid.length;
        int[][][] dp = new int[n][n][n];
        
        for (int[][] layer : dp) {
            for (int[] row : layer) {
                Arrays.fill(row, Integer.MIN_VALUE);
            }
        }
        
        dp[0][0][0] = grid[0][0];
        
        for (int x1 = 0; x1 < n; x1++) {
            for (int y1 = 0; y1 < n; y1++) {
                for (int x2 = 0; x2 < n; x2++) {
                    int y2 = x1 + y1 - x2; // Constraint: x1+y1 = x2+y2 (same number of steps)
                    
                    if (y2 < 0 || y2 >= n || dp[x1][y1][x2] == Integer.MIN_VALUE) continue;
                    
                    int cherries = grid[x1][y1];
                    if (x1 != x2) cherries += grid[x2][y2]; // Don't double count if same cell
                    
                    // Try all possible previous positions
                    for (int dx1 = -1; dx1 <= 0; dx1++) {
                        for (int dy1 = -1; dy1 <= 0; dy1++) {
                            if (dx1 == 0 && dy1 == 0) continue;
                            
                            for (int dx2 = -1; dx2 <= 0; dx2++) {
                                for (int dy2 = -1; dy2 <= 0; dy2++) {
                                    if (dx2 == 0 && dy2 == 0) continue;
                                    
                                    int px1 = x1 + dx1, py1 = y1 + dy1;
                                    int px2 = x2 + dx2, py2 = y2 + dy2;
                                    
                                    if (px1 >= 0 && py1 >= 0 && px2 >= 0 && py2 >= 0) {
                                        dp[x1][y1][x2] = Math.max(dp[x1][y1][x2], 
                                                                 dp[px1][py1][px2] + cherries);
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        
        return Math.max(0, dp[n-1][n-1][n-1]);
    }
    
    // Test
    public static void main(String[] args) {
        UniquePaths solution = new UniquePaths();
        
        // Test unique paths
        int m = 3, n = 7;
        System.out.println("Unique Paths (" + m + "x" + n + "):");
        System.out.println("2D DP: " + solution.uniquePaths(m, n));
        System.out.println("Optimized: " + solution.uniquePathsOptimized(m, n));
        System.out.println("Mathematical: " + solution.uniquePathsMath(m, n));
        System.out.println("Sample path: " + solution.uniquePathWithPath(m, n));
        
        // Test with obstacles
        int[][] obstacleGrid = {{0,0,0},{0,1,0},{0,0,0}};
        System.out.println("\nWith obstacles: " + solution.uniquePathsWithObstacles(obstacleGrid));
        
        // Test minimum path sum
        int[][] grid = {{1,3,1},{1,5,1},{4,2,1}};
        System.out.println("Min path sum: " + solution.minPathSum(grid));
        
        // Test k steps
        System.out.println("Paths with exactly 4 steps (3x3 grid, (0,0) to (2,2)): " + 
                          solution.uniquePathsKSteps(3, 3, 4, 0, 0, 2, 2));
    }
} 