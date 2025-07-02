package leetcode.dp;

import java.util.HashMap;
import java.util.Map;

/**
 * LeetCode 70: Climbing Stairs
 * 
 * You are climbing a staircase. It takes n steps to reach the top.
 * Each time you can either climb 1 or 2 steps. In how many distinct ways can you climb to the top?
 * 
 * Example:
 * Input: n = 2
 * Output: 2
 * Explanation: There are two ways to climb to the top.
 * 1. 1 step + 1 step
 * 2. 2 steps
 * 
 * Input: n = 3
 * Output: 3
 * Explanation: There are three ways to climb to the top.
 * 1. 1 step + 1 step + 1 step
 * 2. 1 step + 2 steps
 * 3. 2 steps + 1 step
 */
public class ClimbingStairs {
    
    /**
     * Approach 1: Brute Force Recursion
     * Time Complexity: O(2^n) - Each step branches into two recursive calls
     * Space Complexity: O(n) - Recursion stack depth
     * 
     * Algorithm:
     * At each step, we have two choices:
     * 1. Take 1 step
     * 2. Take 2 steps
     * Total ways = ways(n-1) + ways(n-2)
     * 
     * This is essentially calculating Fibonacci numbers!
     */
    public int climbStairsBruteForce(int n) {
        // Base cases
        if (n <= 0) return 0;
        if (n == 1) return 1;
        if (n == 2) return 2;
        
        // Recursive case: sum of ways to reach (n-1) and (n-2)
        return climbStairsBruteForce(n - 1) + climbStairsBruteForce(n - 2);
    }
    
    /**
     * Approach 2: Recursion with Memoization (Top-Down DP)
     * Time Complexity: O(n) - Each subproblem solved only once
     * Space Complexity: O(n) - Memoization map and recursion stack
     * 
     * Algorithm:
     * Same as brute force but cache results to avoid recalculation
     */
    public int climbStairsMemoization(int n) {
        Map<Integer, Integer> memo = new HashMap<>();
        return climbStairsHelper(n, memo);
    }
    
    private int climbStairsHelper(int n, Map<Integer, Integer> memo) {
        // Check if already calculated
        if (memo.containsKey(n)) {
            return memo.get(n);
        }
        
        // Base cases
        if (n <= 0) return 0;
        if (n == 1) return 1;
        if (n == 2) return 2;
        
        // Calculate and store result
        int result = climbStairsHelper(n - 1, memo) + climbStairsHelper(n - 2, memo);
        memo.put(n, result);
        
        return result;
    }
    
    /**
     * Approach 3: Dynamic Programming with Array (Bottom-Up)
     * Time Complexity: O(n) - Single pass to fill DP array
     * Space Complexity: O(n) - DP array of size n+1
     * 
     * Algorithm:
     * Build solution from bottom up using array to store intermediate results
     */
    public int climbStairsDP(int n) {
        if (n <= 0) return 0;
        if (n == 1) return 1;
        
        // dp[i] represents number of ways to reach step i
        int[] dp = new int[n + 1];
        dp[0] = 1; // One way to stay at ground (do nothing)
        dp[1] = 1; // One way to reach step 1
        
        // Fill the dp array
        for (int i = 2; i <= n; i++) {
            dp[i] = dp[i - 1] + dp[i - 2];
        }
        
        return dp[n];
    }
    
    /**
     * Approach 4: Space Optimized DP (Optimal)
     * Time Complexity: O(n) - Single pass
     * Space Complexity: O(1) - Only two variables needed
     * 
     * Algorithm:
     * Since we only need previous two values, use two variables instead of array
     */
    public int climbStairsOptimal(int n) {
        if (n <= 0) return 0;
        if (n == 1) return 1;
        
        int prev2 = 1; // Ways to reach step (i-2)
        int prev1 = 1; // Ways to reach step (i-1)
        
        for (int i = 2; i <= n; i++) {
            int current = prev1 + prev2;
            prev2 = prev1;
            prev1 = current;
        }
        
        return prev1;
    }
    
    /**
     * Approach 5: Matrix Exponentiation
     * Time Complexity: O(log n) - Fast matrix exponentiation
     * Space Complexity: O(1) - Constant space for matrices
     * 
     * Algorithm:
     * [F(n+1)]   [1 1]^n   [F(1)]
     * [F(n)  ] = [1 0]   * [F(0)]
     * 
     * This is the most efficient for very large n
     */
    public int climbStairsMatrix(int n) {
        if (n <= 0) return 0;
        if (n == 1) return 1;
        
        int[][] base = {{1, 1}, {1, 0}};
        int[][] result = matrixPower(base, n);
        
        return result[0][0];
    }
    
    private int[][] matrixPower(int[][] matrix, int n) {
        if (n == 1) return matrix;
        
        if (n % 2 == 0) {
            int[][] half = matrixPower(matrix, n / 2);
            return multiplyMatrix(half, half);
        } else {
            return multiplyMatrix(matrix, matrixPower(matrix, n - 1));
        }
    }
    
    private int[][] multiplyMatrix(int[][] a, int[][] b) {
        int[][] result = new int[2][2];
        result[0][0] = a[0][0] * b[0][0] + a[0][1] * b[1][0];
        result[0][1] = a[0][0] * b[0][1] + a[0][1] * b[1][1];
        result[1][0] = a[1][0] * b[0][0] + a[1][1] * b[1][0];
        result[1][1] = a[1][0] * b[0][1] + a[1][1] * b[1][1];
        return result;
    }
    
    /**
     * Approach 6: Mathematical Formula (Binet's Formula)
     * Time Complexity: O(1) - Direct calculation
     * Space Complexity: O(1) - No extra space
     * 
     * Uses the golden ratio formula for Fibonacci numbers
     * Note: May have precision issues for large n
     */
    public int climbStairsFormula(int n) {
        if (n <= 0) return 0;
        if (n == 1) return 1;
        
        double sqrt5 = Math.sqrt(5);
        double phi = (1 + sqrt5) / 2;
        double psi = (1 - sqrt5) / 2;
        
        // Fibonacci formula adjusted for our indexing
        return (int) Math.round((Math.pow(phi, n + 1) - Math.pow(psi, n + 1)) / sqrt5);
    }
    
    /**
     * Extended Problem: Climb stairs with k steps at a time
     * You can climb 1, 2, ..., k steps at a time
     */
    public int climbStairsKSteps(int n, int k) {
        if (n <= 0) return 0;
        if (n == 1) return 1;
        
        int[] dp = new int[n + 1];
        dp[0] = 1;
        
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= k && j <= i; j++) {
                dp[i] += dp[i - j];
            }
        }
        
        return dp[n];
    }
    
    /**
     * Extended Problem: Climb stairs with variable cost
     * Each step has a cost, find minimum cost to reach top
     */
    public int minCostClimbingStairs(int[] cost) {
        int n = cost.length;
        if (n == 0) return 0;
        if (n == 1) return cost[0];
        
        int prev2 = cost[0];
        int prev1 = cost[1];
        
        for (int i = 2; i < n; i++) {
            int current = cost[i] + Math.min(prev1, prev2);
            prev2 = prev1;
            prev1 = current;
        }
        
        // Can reach top from either last or second last step
        return Math.min(prev1, prev2);
    }
    
    // Test the solutions
    public static void main(String[] args) {
        ClimbingStairs solution = new ClimbingStairs();
        
        // Test different values of n
        int[] testCases = {2, 3, 5, 10, 20};
        
        System.out.println("Climbing Stairs - Different Approaches:");
        System.out.println("n\tBrute\tMemo\tDP\tOptimal\tMatrix\tFormula");
        
        for (int n : testCases) {
            System.out.printf("%d\t", n);
            
            // Skip brute force for large n (too slow)
            if (n <= 20) {
                System.out.printf("%d\t", solution.climbStairsBruteForce(n));
            } else {
                System.out.print("-\t");
            }
            
            System.out.printf("%d\t", solution.climbStairsMemoization(n));
            System.out.printf("%d\t", solution.climbStairsDP(n));
            System.out.printf("%d\t", solution.climbStairsOptimal(n));
            System.out.printf("%d\t", solution.climbStairsMatrix(n));
            System.out.printf("%d%n", solution.climbStairsFormula(n));
        }
        
        // Test extended problems
        System.out.println("\nClimbing with k=3 steps:");
        System.out.println("n=4, k=3: " + solution.climbStairsKSteps(4, 3));
        
        System.out.println("\nMin cost climbing:");
        int[] costs = {10, 15, 20};
        System.out.println("Costs [10,15,20]: " + solution.minCostClimbingStairs(costs));
    }
} 