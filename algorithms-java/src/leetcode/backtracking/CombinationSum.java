package leetcode.backtracking;

import java.util.*;

/**
 * LeetCode 39: Combination Sum
 * 
 * Given an array of distinct integers candidates and a target integer target,
 * return a list of all unique combinations of candidates where the chosen numbers sum to target.
 * You may choose the same number from candidates an unlimited number of times.
 * 
 * Example:
 * Input: candidates = [2,3,6,7], target = 7
 * Output: [[2,2,3],[7]]
 */
public class CombinationSum {
    
    /**
     * Approach 1: Backtracking with Repetition
     * Time: O(N^(T/M)) where N = candidates.length, T = target, M = minimal candidate
     * Space: O(T/M) for recursion depth
     */
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>> result = new ArrayList<>();
        List<Integer> current = new ArrayList<>();
        backtrack(candidates, target, 0, current, result);
        return result;
    }
    
    private void backtrack(int[] candidates, int remaining, int start,
                          List<Integer> current, List<List<Integer>> result) {
        // Base cases
        if (remaining == 0) {
            result.add(new ArrayList<>(current));
            return;
        }
        if (remaining < 0) {
            return;
        }
        
        // Try each candidate starting from 'start' to avoid duplicates
        for (int i = start; i < candidates.length; i++) {
            // Choose current candidate
            current.add(candidates[i]);
            
            // Recurse with same start index (allow repetition)
            backtrack(candidates, remaining - candidates[i], i, current, result);
            
            // Backtrack
            current.remove(current.size() - 1);
        }
    }
    
    /**
     * Approach 2: Optimized with Sorting
     * Time: O(N^(T/M)), Space: O(T/M)
     * Sort candidates for early termination
     */
    public List<List<Integer>> combinationSumOptimized(int[] candidates, int target) {
        List<List<Integer>> result = new ArrayList<>();
        Arrays.sort(candidates); // Sort for optimization
        backtrackOptimized(candidates, target, 0, new ArrayList<>(), result);
        return result;
    }
    
    private void backtrackOptimized(int[] candidates, int remaining, int start,
                                   List<Integer> current, List<List<Integer>> result) {
        if (remaining == 0) {
            result.add(new ArrayList<>(current));
            return;
        }
        
        for (int i = start; i < candidates.length; i++) {
            // Early termination: if current candidate > remaining, skip rest
            if (candidates[i] > remaining) {
                break;
            }
            
            current.add(candidates[i]);
            backtrackOptimized(candidates, remaining - candidates[i], i, current, result);
            current.remove(current.size() - 1);
        }
    }
    
    /**
     * Extended Problem: Combination Sum II (LC 40)
     * Each number can be used only once, array may contain duplicates
     */
    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        List<List<Integer>> result = new ArrayList<>();
        Arrays.sort(candidates); // Sort to handle duplicates
        backtrackSum2(candidates, target, 0, new ArrayList<>(), result);
        return result;
    }
    
    private void backtrackSum2(int[] candidates, int remaining, int start,
                              List<Integer> current, List<List<Integer>> result) {
        if (remaining == 0) {
            result.add(new ArrayList<>(current));
            return;
        }
        
        for (int i = start; i < candidates.length; i++) {
            if (candidates[i] > remaining) break;
            
            // Skip duplicates: only use first occurrence at each level
            if (i > start && candidates[i] == candidates[i - 1]) {
                continue;
            }
            
            current.add(candidates[i]);
            // Use i + 1 (not i) since each number can be used only once
            backtrackSum2(candidates, remaining - candidates[i], i + 1, current, result);
            current.remove(current.size() - 1);
        }
    }
    
    /**
     * Extended Problem: Combination Sum III (LC 216)
     * Find k numbers that add up to n, only use numbers 1-9, each number used at most once
     */
    public List<List<Integer>> combinationSum3(int k, int n) {
        List<List<Integer>> result = new ArrayList<>();
        backtrackSum3(k, n, 1, new ArrayList<>(), result);
        return result;
    }
    
    private void backtrackSum3(int k, int remaining, int start,
                              List<Integer> current, List<List<Integer>> result) {
        if (current.size() == k) {
            if (remaining == 0) {
                result.add(new ArrayList<>(current));
            }
            return;
        }
        
        // Early termination optimizations
        if (remaining < 0 || current.size() > k) return;
        if (remaining < start || remaining > (19 - start) * (k - current.size()) / 2) return;
        
        for (int i = start; i <= 9; i++) {
            current.add(i);
            backtrackSum3(k, remaining - i, i + 1, current, result);
            current.remove(current.size() - 1);
        }
    }
    
    /**
     * Extended Problem: Combination Sum IV (LC 377)
     * Count number of combinations (order matters - this is actually permutations)
     * Uses DP instead of backtracking for efficiency
     */
    public int combinationSum4(int[] nums, int target) {
        int[] dp = new int[target + 1];
        dp[0] = 1; // One way to make 0: use no numbers
        
        for (int i = 1; i <= target; i++) {
            for (int num : nums) {
                if (i >= num) {
                    dp[i] += dp[i - num];
                }
            }
        }
        
        return dp[target];
    }
    
    /**
     * Extended Problem: Factor Combinations (LC 254)
     * Find all combinations of factors that multiply to n
     */
    public List<List<Integer>> getFactors(int n) {
        List<List<Integer>> result = new ArrayList<>();
        backtrackFactors(n, 2, new ArrayList<>(), result);
        return result;
    }
    
    private void backtrackFactors(int n, int start, List<Integer> current,
                                 List<List<Integer>> result) {
        if (n == 1) {
            if (current.size() > 1) { // Need at least 2 factors
                result.add(new ArrayList<>(current));
            }
            return;
        }
        
        for (int i = start; i * i <= n; i++) {
            if (n % i == 0) {
                current.add(i);
                backtrackFactors(n / i, i, current, result);
                current.remove(current.size() - 1);
            }
        }
        
        // Consider n itself as a factor (if it's >= start)
        if (n >= start) {
            current.add(n);
            backtrackFactors(1, n, current, result);
            current.remove(current.size() - 1);
        }
    }
    
    /**
     * Extended Problem: Coin Change with all combinations
     * Find all ways to make change for amount using given coins
     */
    public List<List<Integer>> coinCombinations(int[] coins, int amount) {
        List<List<Integer>> result = new ArrayList<>();
        Arrays.sort(coins);
        backtrackCoins(coins, amount, 0, new ArrayList<>(), result);
        return result;
    }
    
    private void backtrackCoins(int[] coins, int remaining, int start,
                               List<Integer> current, List<List<Integer>> result) {
        if (remaining == 0) {
            result.add(new ArrayList<>(current));
            return;
        }
        
        for (int i = start; i < coins.length; i++) {
            if (coins[i] > remaining) break;
            
            current.add(coins[i]);
            backtrackCoins(coins, remaining - coins[i], i, current, result);
            current.remove(current.size() - 1);
        }
    }
    
    /**
     * Extended Problem: Target Sum with Limited Operations
     * Use +, -, *, / operations to reach target
     */
    public List<String> targetSum(int[] nums, int target) {
        List<String> result = new ArrayList<>();
        if (nums.length == 0) return result;
        
        backtrackTarget(nums, target, 1, nums[0], String.valueOf(nums[0]), result);
        return result;
    }
    
    private void backtrackTarget(int[] nums, int target, int index, 
                                long currentSum, String expression, List<String> result) {
        if (index == nums.length) {
            if (currentSum == target) {
                result.add(expression);
            }
            return;
        }
        
        int num = nums[index];
        
        // Try addition
        backtrackTarget(nums, target, index + 1, currentSum + num,
                       expression + "+" + num, result);
        
        // Try subtraction
        backtrackTarget(nums, target, index + 1, currentSum - num,
                       expression + "-" + num, result);
        
        // Try multiplication (need to handle precedence)
        // For simplicity, we'll skip multiplication in this basic version
    }
    
    // Test
    public static void main(String[] args) {
        CombinationSum solution = new CombinationSum();
        
        // Test combination sum
        int[] candidates1 = {2, 3, 6, 7};
        int target1 = 7;
        System.out.println("Combination Sum [2,3,6,7], target=7:");
        System.out.println(solution.combinationSum(candidates1, target1));
        
        // Test combination sum II
        int[] candidates2 = {10, 1, 2, 7, 6, 1, 5};
        int target2 = 8;
        System.out.println("\nCombination Sum II [10,1,2,7,6,1,5], target=8:");
        System.out.println(solution.combinationSum2(candidates2, target2));
        
        // Test combination sum III
        System.out.println("\nCombination Sum III k=3, n=7:");
        System.out.println(solution.combinationSum3(3, 7));
        
        // Test combination sum IV (count)
        int[] nums4 = {1, 2, 3};
        int target4 = 4;
        System.out.println("\nCombination Sum IV [1,2,3], target=4:");
        System.out.println("Count: " + solution.combinationSum4(nums4, target4));
        
        // Test factor combinations
        System.out.println("\nFactor combinations of 12:");
        System.out.println(solution.getFactors(12));
        
        // Test coin combinations
        int[] coins = {1, 2, 5};
        int amount = 5;
        System.out.println("\nCoin combinations [1,2,5], amount=5:");
        System.out.println(solution.coinCombinations(coins, amount));
    }
} 