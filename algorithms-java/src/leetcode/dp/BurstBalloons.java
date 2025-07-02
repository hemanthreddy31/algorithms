package leetcode.dp;

import java.util.*;

/**
 * LeetCode 312: Burst Balloons
 * 
 * You have n balloons, indexed from 0 to n - 1. Each balloon is painted with
 * a number on it represented by array nums. You are asked to burst all the balloons.
 * 
 * If you burst balloon i you will get nums[i - 1] * nums[i] * nums[i + 1] coins.
 * If i - 1 or i + 1 goes out of bounds, then treat it as if there is a balloon with a 1 painted on it.
 * 
 * Return the maximum coins you can collect by bursting the balloons wisely.
 */
public class BurstBalloons {
    
    /**
     * Approach 1: Interval DP (Bottom-up)
     * Time: O(n³), Space: O(n²)
     * 
     * Key insight: Instead of thinking about which balloon to burst first,
     * think about which balloon to burst LAST in each interval
     */
    public int maxCoins(int[] nums) {
        int n = nums.length;
        
        // Add boundary balloons with value 1
        int[] balloons = new int[n + 2];
        balloons[0] = balloons[n + 1] = 1;
        for (int i = 0; i < n; i++) {
            balloons[i + 1] = nums[i];
        }
        
        // dp[left][right] = max coins from interval (left, right) exclusive
        int[][] dp = new int[n + 2][n + 2];
        
        // Length of interval
        for (int len = 2; len <= n + 1; len++) {
            // Start of interval
            for (int left = 0; left <= n + 1 - len; left++) {
                int right = left + len;
                
                // Try bursting each balloon k as the LAST one in interval (left, right)
                for (int k = left + 1; k < right; k++) {
                    int coins = balloons[left] * balloons[k] * balloons[right];
                    coins += dp[left][k] + dp[k][right];
                    dp[left][right] = Math.max(dp[left][right], coins);
                }
            }
        }
        
        return dp[0][n + 1];
    }
    
    /**
     * Approach 2: Memoization (Top-down)
     * Time: O(n³), Space: O(n²)
     */
    public int maxCoinsMemo(int[] nums) {
        int n = nums.length;
        int[] balloons = new int[n + 2];
        balloons[0] = balloons[n + 1] = 1;
        for (int i = 0; i < n; i++) {
            balloons[i + 1] = nums[i];
        }
        
        int[][] memo = new int[n + 2][n + 2];
        for (int[] row : memo) Arrays.fill(row, -1);
        
        return helper(balloons, 0, n + 1, memo);
    }
    
    private int helper(int[] balloons, int left, int right, int[][] memo) {
        if (left + 1 == right) return 0; // No balloons in between
        
        if (memo[left][right] != -1) return memo[left][right];
        
        int maxCoins = 0;
        
        // Try bursting each balloon k as the last one
        for (int k = left + 1; k < right; k++) {
            int coins = balloons[left] * balloons[k] * balloons[right];
            coins += helper(balloons, left, k, memo) + helper(balloons, k, right, memo);
            maxCoins = Math.max(maxCoins, coins);
        }
        
        memo[left][right] = maxCoins;
        return maxCoins;
    }
    
    /**
     * Extended Problem: Matrix Chain Multiplication
     * Similar interval DP pattern
     */
    public int matrixChainOrder(int[] p) {
        int n = p.length - 1; // Number of matrices
        int[][] dp = new int[n][n];
        
        // Length of chain
        for (int len = 2; len <= n; len++) {
            for (int i = 0; i <= n - len; i++) {
                int j = i + len - 1;
                dp[i][j] = Integer.MAX_VALUE;
                
                // Try all possible split points
                for (int k = i; k < j; k++) {
                    int cost = dp[i][k] + dp[k+1][j] + p[i] * p[k+1] * p[j+1];
                    dp[i][j] = Math.min(dp[i][j], cost);
                }
            }
        }
        
        return dp[0][n-1];
    }
    
    /**
     * Extended Problem: Minimum Cost to Cut a Stick
     * LeetCode 1547: Similar interval DP
     */
    public int minCost(int n, int[] cuts) {
        List<Integer> cutsList = new ArrayList<>();
        cutsList.add(0);
        for (int cut : cuts) cutsList.add(cut);
        cutsList.add(n);
        Collections.sort(cutsList);
        
        int m = cutsList.size();
        int[][] dp = new int[m][m];
        
        for (int len = 2; len < m; len++) {
            for (int i = 0; i <= m - len - 1; i++) {
                int j = i + len;
                dp[i][j] = Integer.MAX_VALUE;
                
                for (int k = i + 1; k < j; k++) {
                    int cost = cutsList.get(j) - cutsList.get(i) + dp[i][k] + dp[k][j];
                    dp[i][j] = Math.min(dp[i][j], cost);
                }
            }
        }
        
        return dp[0][m-1];
    }
    
    /**
     * Extended Problem: Remove Boxes
     * LeetCode 546: 3D interval DP
     */
    public int removeBoxes(int[] boxes) {
        int n = boxes.length;
        int[][][] dp = new int[n][n][n];
        return removeHelper(boxes, 0, n - 1, 0, dp);
    }
    
    private int removeHelper(int[] boxes, int left, int right, int k, int[][][] dp) {
        if (left > right) return 0;
        if (dp[left][right][k] != 0) return dp[left][right][k];
        
        // Find consecutive boxes with same color as boxes[left]
        int originalLeft = left;
        int originalK = k;
        
        while (left + 1 <= right && boxes[left] == boxes[left + 1]) {
            left++;
            k++;
        }
        
        // Option 1: Remove all boxes with same color at the beginning
        int result = (k + 1) * (k + 1) + removeHelper(boxes, left + 1, right, 0, dp);
        
        // Option 2: Keep some boxes and merge with later boxes of same color
        for (int m = left + 1; m <= right; m++) {
            if (boxes[m] == boxes[left]) {
                result = Math.max(result,
                    removeHelper(boxes, left + 1, m - 1, 0, dp) +
                    removeHelper(boxes, m, right, k + 1, dp));
            }
        }
        
        dp[originalLeft][right][originalK] = result;
        return result;
    }
    
    // Test
    public static void main(String[] args) {
        BurstBalloons solution = new BurstBalloons();
        
        // Test burst balloons
        int[] nums1 = {3, 1, 5, 8};
        System.out.println("Max coins from [3,1,5,8]: " + solution.maxCoins(nums1));
        System.out.println("Max coins (memo): " + solution.maxCoinsMemo(nums1));
        
        // Test matrix chain multiplication
        int[] matrices = {1, 2, 3, 4};
        System.out.println("Matrix chain cost: " + solution.matrixChainOrder(matrices));
        
        // Test stick cutting
        int[] cuts = {1, 3, 4, 5};
        System.out.println("Min cost to cut stick of length 7: " + solution.minCost(7, cuts));
        
        // Test remove boxes
        int[] boxes = {1, 3, 2, 2, 2, 3, 4, 3, 1};
        System.out.println("Remove boxes score: " + solution.removeBoxes(boxes));
    }
} 