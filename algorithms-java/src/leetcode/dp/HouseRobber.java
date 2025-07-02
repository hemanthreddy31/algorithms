package leetcode.dp;

import java.util.*;

/**
 * LeetCode 198: House Robber
 * 
 * You are a professional robber planning to rob houses along a street.
 * Each house has a certain amount of money stashed, the only constraint stopping you
 * from robbing each of them is that adjacent houses have security systems connected
 * and it will automatically contact the police if two adjacent houses were broken
 * into on the same night.
 * 
 * Given an integer array nums representing the amount of money of each house,
 * return the maximum amount of money you can rob tonight without alerting the police.
 */
public class HouseRobber {
    
    /**
     * Approach 1: Recursion (Brute Force)
     * Time: O(2^n), Space: O(n)
     * Try all possibilities: rob or skip each house
     */
    public int robRecursive(int[] nums) {
        return robHelper(nums, 0);
    }
    
    private int robHelper(int[] nums, int index) {
        if (index >= nums.length) return 0;
        
        // Choice 1: Rob current house and skip next
        int robCurrent = nums[index] + robHelper(nums, index + 2);
        
        // Choice 2: Skip current house
        int skipCurrent = robHelper(nums, index + 1);
        
        return Math.max(robCurrent, skipCurrent);
    }
    
    /**
     * Approach 2: Memoization (Top-Down DP)
     * Time: O(n), Space: O(n)
     * Cache results to avoid recomputation
     */
    public int robMemoization(int[] nums) {
        int[] memo = new int[nums.length];
        Arrays.fill(memo, -1);
        return robMemo(nums, 0, memo);
    }
    
    private int robMemo(int[] nums, int index, int[] memo) {
        if (index >= nums.length) return 0;
        if (memo[index] != -1) return memo[index];
        
        int robCurrent = nums[index] + robMemo(nums, index + 2, memo);
        int skipCurrent = robMemo(nums, index + 1, memo);
        
        memo[index] = Math.max(robCurrent, skipCurrent);
        return memo[index];
    }
    
    /**
     * Approach 3: Bottom-Up DP with Array
     * Time: O(n), Space: O(n)
     * Build solution from smaller subproblems
     */
    public int robDP(int[] nums) {
        if (nums.length == 0) return 0;
        if (nums.length == 1) return nums[0];
        
        int[] dp = new int[nums.length];
        dp[0] = nums[0];
        dp[1] = Math.max(nums[0], nums[1]);
        
        for (int i = 2; i < nums.length; i++) {
            dp[i] = Math.max(dp[i-1], nums[i] + dp[i-2]);
        }
        
        return dp[nums.length - 1];
    }
    
    /**
     * Approach 4: Space Optimized DP (Optimal)
     * Time: O(n), Space: O(1)
     * Only need previous two values
     */
    public int robOptimal(int[] nums) {
        if (nums.length == 0) return 0;
        if (nums.length == 1) return nums[0];
        
        int prev2 = nums[0];
        int prev1 = Math.max(nums[0], nums[1]);
        
        for (int i = 2; i < nums.length; i++) {
            int current = Math.max(prev1, nums[i] + prev2);
            prev2 = prev1;
            prev1 = current;
        }
        
        return prev1;
    }
    
    /**
     * Extended Problem: House Robber II (LC 213)
     * Houses arranged in a circle - first and last are adjacent
     */
    public int robCircular(int[] nums) {
        if (nums.length == 0) return 0;
        if (nums.length == 1) return nums[0];
        
        // Case 1: Rob houses 0 to n-2 (exclude last)
        int maxExcludeLast = robRange(nums, 0, nums.length - 2);
        
        // Case 2: Rob houses 1 to n-1 (exclude first)
        int maxExcludeFirst = robRange(nums, 1, nums.length - 1);
        
        return Math.max(maxExcludeLast, maxExcludeFirst);
    }
    
    private int robRange(int[] nums, int start, int end) {
        int prev2 = 0, prev1 = 0;
        
        for (int i = start; i <= end; i++) {
            int current = Math.max(prev1, nums[i] + prev2);
            prev2 = prev1;
            prev1 = current;
        }
        
        return prev1;
    }
    
    /**
     * Extended Problem: House Robber III (LC 337)
     * Houses arranged in binary tree
     */
    static class TreeNode {
        int val;
        TreeNode left, right;
        TreeNode(int val) { this.val = val; }
    }
    
    public int robTree(TreeNode root) {
        int[] result = robTreeHelper(root);
        return Math.max(result[0], result[1]);
    }
    
    // Returns [maxWithoutRoot, maxWithRoot]
    private int[] robTreeHelper(TreeNode node) {
        if (node == null) return new int[]{0, 0};
        
        int[] left = robTreeHelper(node.left);
        int[] right = robTreeHelper(node.right);
        
        // Don't rob current node
        int withoutCurrent = Math.max(left[0], left[1]) + Math.max(right[0], right[1]);
        
        // Rob current node
        int withCurrent = node.val + left[0] + right[0];
        
        return new int[]{withoutCurrent, withCurrent};
    }
    
    /**
     * Extended Problem: House Robber with K-day Cooldown
     * After robbing, must wait k days before robbing again
     */
    public int robWithCooldown(int[] nums, int k) {
        if (nums.length == 0) return 0;
        if (k >= nums.length - 1) return Arrays.stream(nums).max().orElse(0);
        
        int[] dp = new int[nums.length];
        dp[0] = nums[0];
        
        for (int i = 1; i < nums.length; i++) {
            dp[i] = Math.max(dp[i-1], nums[i] + (i-k-1 >= 0 ? dp[i-k-1] : 0));
        }
        
        return dp[nums.length - 1];
    }
    
    /**
     * Extended Problem: House Robber with Path Reconstruction
     * Return which houses to rob for maximum money
     */
    public List<Integer> robWithPath(int[] nums) {
        if (nums.length == 0) return new ArrayList<>();
        if (nums.length == 1) return Arrays.asList(0);
        
        int[] dp = new int[nums.length];
        boolean[] robbed = new boolean[nums.length];
        
        dp[0] = nums[0];
        robbed[0] = true;
        
        if (nums[1] > nums[0]) {
            dp[1] = nums[1];
            robbed[1] = true;
        } else {
            dp[1] = nums[0];
            robbed[1] = false;
        }
        
        for (int i = 2; i < nums.length; i++) {
            if (dp[i-1] > nums[i] + dp[i-2]) {
                dp[i] = dp[i-1];
                robbed[i] = false;
            } else {
                dp[i] = nums[i] + dp[i-2];
                robbed[i] = true;
            }
        }
        
        // Reconstruct path
        List<Integer> result = new ArrayList<>();
        for (int i = nums.length - 1; i >= 0; ) {
            if (robbed[i]) {
                result.add(i);
                i -= 2;
            } else {
                i--;
            }
        }
        
        Collections.reverse(result);
        return result;
    }
    
    // Test
    public static void main(String[] args) {
        HouseRobber solution = new HouseRobber();
        
        // Test case 1
        int[] nums1 = {1, 2, 3, 1};
        System.out.println("Test 1 [1,2,3,1]:");
        System.out.println("Recursive: " + solution.robRecursive(nums1));
        System.out.println("Memoization: " + solution.robMemoization(nums1));
        System.out.println("DP: " + solution.robDP(nums1));
        System.out.println("Optimal: " + solution.robOptimal(nums1));
        System.out.println("Houses to rob: " + solution.robWithPath(nums1));
        
        // Test case 2
        int[] nums2 = {2, 7, 9, 3, 1};
        System.out.println("\nTest 2 [2,7,9,3,1]:");
        System.out.println("Optimal: " + solution.robOptimal(nums2));
        System.out.println("Circular: " + solution.robCircular(nums2));
        
        // Test cooldown
        System.out.println("With 2-day cooldown: " + solution.robWithCooldown(nums2, 2));
    }
} 