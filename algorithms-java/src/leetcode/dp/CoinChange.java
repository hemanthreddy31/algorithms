package leetcode.dp;

import java.util.*;

/**
 * LeetCode 322: Coin Change
 * 
 * You are given an integer array coins representing coins of different denominations 
 * and an integer amount representing a total amount of money.
 * 
 * Return the fewest number of coins that you need to make up that amount. 
 * If that amount of money cannot be made up by any combination of the coins, return -1.
 * 
 * You may assume that you have an infinite number of each kind of coin.
 * 
 * Example:
 * Input: coins = [1,2,5], amount = 11
 * Output: 3
 * Explanation: 11 = 5 + 5 + 1
 */
public class CoinChange {
    
    /**
     * Approach 1: Brute Force Recursion
     * Time Complexity: O(S^n) - S is amount, n is number of coins
     * Space Complexity: O(n) - Recursion stack
     * 
     * Try all possible combinations
     */
    public int coinChangeBruteForce(int[] coins, int amount) {
        if (amount == 0) return 0;
        return coinChangeHelper(coins, amount);
    }
    
    private int coinChangeHelper(int[] coins, int remaining) {
        if (remaining < 0) return -1;
        if (remaining == 0) return 0;
        
        int minCoins = Integer.MAX_VALUE;
        
        for (int coin : coins) {
            int result = coinChangeHelper(coins, remaining - coin);
            if (result >= 0 && result < minCoins) {
                minCoins = result + 1;
            }
        }
        
        return minCoins == Integer.MAX_VALUE ? -1 : minCoins;
    }
    
    /**
     * Approach 2: Top-Down DP (Memoization)
     * Time Complexity: O(S * n) - S is amount, n is number of coins
     * Space Complexity: O(S) - Memoization array
     * 
     * Cache results to avoid recomputation
     */
    public int coinChangeMemoization(int[] coins, int amount) {
        if (amount < 0) return -1;
        return coinChangeHelper(coins, amount, new int[amount + 1]);
    }
    
    private int coinChangeHelper(int[] coins, int remaining, int[] memo) {
        if (remaining < 0) return -1;
        if (remaining == 0) return 0;
        if (memo[remaining] != 0) return memo[remaining];
        
        int minCoins = Integer.MAX_VALUE;
        
        for (int coin : coins) {
            int result = coinChangeHelper(coins, remaining - coin, memo);
            if (result >= 0 && result < minCoins) {
                minCoins = result + 1;
            }
        }
        
        memo[remaining] = minCoins == Integer.MAX_VALUE ? -1 : minCoins;
        return memo[remaining];
    }
    
    /**
     * Approach 3: Bottom-Up DP (Optimal)
     * Time Complexity: O(S * n) - S is amount, n is number of coins
     * Space Complexity: O(S) - DP array
     * 
     * Algorithm:
     * 1. dp[i] = minimum coins needed for amount i
     * 2. For each amount, try using each coin
     * 3. dp[i] = min(dp[i], dp[i-coin] + 1)
     */
    public int coinChangeDP(int[] coins, int amount) {
        int[] dp = new int[amount + 1];
        Arrays.fill(dp, amount + 1); // Initialize with impossible value
        dp[0] = 0; // Base case: 0 coins for amount 0
        
        // Build up solution for each amount
        for (int i = 1; i <= amount; i++) {
            for (int coin : coins) {
                if (coin <= i) {
                    dp[i] = Math.min(dp[i], dp[i - coin] + 1);
                }
            }
        }
        
        return dp[amount] > amount ? -1 : dp[amount];
    }
    
    /**
     * Approach 4: BFS (Find shortest path)
     * Time Complexity: O(S * n) - S is amount, n is number of coins
     * Space Complexity: O(S) - Queue and visited set
     * 
     * Treat as graph problem where each amount is a node
     */
    public int coinChangeBFS(int[] coins, int amount) {
        if (amount == 0) return 0;
        
        Queue<Integer> queue = new LinkedList<>();
        Set<Integer> visited = new HashSet<>();
        queue.offer(amount);
        visited.add(amount);
        
        int level = 0;
        
        while (!queue.isEmpty()) {
            int size = queue.size();
            level++;
            
            for (int i = 0; i < size; i++) {
                int current = queue.poll();
                
                for (int coin : coins) {
                    int next = current - coin;
                    
                    if (next == 0) return level;
                    
                    if (next > 0 && !visited.contains(next)) {
                        queue.offer(next);
                        visited.add(next);
                    }
                }
            }
        }
        
        return -1;
    }
    
    /**
     * Approach 5: DP with Path Reconstruction
     * Returns the actual coins used, not just the count
     */
    public List<Integer> coinChangeWithPath(int[] coins, int amount) {
        int[] dp = new int[amount + 1];
        int[] parent = new int[amount + 1]; // Track which coin was used
        Arrays.fill(dp, amount + 1);
        Arrays.fill(parent, -1);
        dp[0] = 0;
        
        for (int i = 1; i <= amount; i++) {
            for (int j = 0; j < coins.length; j++) {
                if (coins[j] <= i && dp[i - coins[j]] + 1 < dp[i]) {
                    dp[i] = dp[i - coins[j]] + 1;
                    parent[i] = j; // Remember which coin index was used
                }
            }
        }
        
        if (dp[amount] > amount) {
            return new ArrayList<>(); // No solution
        }
        
        // Reconstruct path
        List<Integer> result = new ArrayList<>();
        int current = amount;
        while (current > 0) {
            int coinIndex = parent[current];
            result.add(coins[coinIndex]);
            current -= coins[coinIndex];
        }
        
        return result;
    }
    
    /**
     * Extended Problem: Coin Change 2 (LeetCode 518)
     * Return the number of combinations that make up the amount
     */
    public int coinChange2(int[] coins, int amount) {
        int[] dp = new int[amount + 1];
        dp[0] = 1; // One way to make 0: use no coins
        
        // Process each coin type
        for (int coin : coins) {
            for (int i = coin; i <= amount; i++) {
                dp[i] += dp[i - coin];
            }
        }
        
        return dp[amount];
    }
    
    /**
     * Extended Problem: Minimum coins with limited supply
     * Each coin can be used at most once
     */
    public int coinChangeLimited(int[] coins, int amount) {
        boolean[] dp = new boolean[amount + 1];
        int[] minCoins = new int[amount + 1];
        Arrays.fill(minCoins, amount + 1);
        
        dp[0] = true;
        minCoins[0] = 0;
        
        // Process each coin
        for (int coin : coins) {
            // Traverse backwards to avoid using same coin twice
            for (int i = amount; i >= coin; i--) {
                if (dp[i - coin]) {
                    dp[i] = true;
                    minCoins[i] = Math.min(minCoins[i], minCoins[i - coin] + 1);
                }
            }
        }
        
        return dp[amount] ? minCoins[amount] : -1;
    }
    
    /**
     * Greedy Approach (Works only for certain coin systems)
     * Time Complexity: O(n log n + amount/largest_coin)
     * Space Complexity: O(1)
     * 
     * Note: This doesn't work for all coin systems!
     * Works for US coins but not for arbitrary denominations
     */
    public int coinChangeGreedy(int[] coins, int amount) {
        Arrays.sort(coins);
        int count = 0;
        
        for (int i = coins.length - 1; i >= 0; i--) {
            count += amount / coins[i];
            amount %= coins[i];
        }
        
        return amount == 0 ? count : -1;
    }
    
    // Test the solutions
    public static void main(String[] args) {
        CoinChange solution = new CoinChange();
        
        // Test case 1
        int[] coins1 = {1, 2, 5};
        int amount1 = 11;
        System.out.println("Test Case 1: coins = [1,2,5], amount = 11");
        System.out.println("Brute Force: " + solution.coinChangeBruteForce(coins1, amount1));
        System.out.println("Memoization: " + solution.coinChangeMemoization(coins1, amount1));
        System.out.println("DP: " + solution.coinChangeDP(coins1, amount1));
        System.out.println("BFS: " + solution.coinChangeBFS(coins1, amount1));
        System.out.println("Coins used: " + solution.coinChangeWithPath(coins1, amount1));
        
        // Test case 2: No solution
        int[] coins2 = {2};
        int amount2 = 3;
        System.out.println("\nTest Case 2: coins = [2], amount = 3");
        System.out.println("DP: " + solution.coinChangeDP(coins2, amount2));
        
        // Test case 3: Amount is 0
        int[] coins3 = {1};
        int amount3 = 0;
        System.out.println("\nTest Case 3: coins = [1], amount = 0");
        System.out.println("DP: " + solution.coinChangeDP(coins3, amount3));
        
        // Test Coin Change 2
        int[] coins4 = {1, 2, 5};
        int amount4 = 5;
        System.out.println("\nCoin Change 2: coins = [1,2,5], amount = 5");
        System.out.println("Number of combinations: " + solution.coinChange2(coins4, amount4));
        
        // Test limited coins
        int[] coins5 = {1, 2, 5};
        int amount5 = 8;
        System.out.println("\nLimited coins: coins = [1,2,5], amount = 8");
        System.out.println("Min coins (each used once): " + solution.coinChangeLimited(coins5, amount5));
        
        // Test greedy (works for US coins)
        int[] coins6 = {1, 5, 10, 25};
        int amount6 = 41;
        System.out.println("\nGreedy approach: coins = [1,5,10,25], amount = 41");
        System.out.println("Greedy result: " + solution.coinChangeGreedy(coins6, amount6));
    }
} 