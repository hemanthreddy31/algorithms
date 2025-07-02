package leetcode.greedy;

import java.util.*;

/**
 * LeetCode 55: Jump Game
 * 
 * You are given an integer array nums. You are initially positioned at the array's 
 * first index, and each element in the array represents your maximum jump length at that position.
 * 
 * Return true if you can reach the last index, or false otherwise.
 * 
 * Example:
 * Input: nums = [2,3,1,1,4]
 * Output: true
 * Explanation: Jump 1 step from index 0 to 1, then 3 steps to the last index.
 */
public class JumpGame {
    
    /**
     * Approach 1: Greedy (Optimal)
     * Time Complexity: O(n) - Single pass
     * Space Complexity: O(1) - Only one variable
     * 
     * Algorithm:
     * 1. Track the farthest position we can reach
     * 2. If current position is beyond farthest, return false
     * 3. Update farthest at each step
     */
    public boolean canJumpGreedy(int[] nums) {
        int farthest = 0;
        
        for (int i = 0; i < nums.length; i++) {
            // If current position is unreachable
            if (i > farthest) {
                return false;
            }
            
            // Update farthest position we can reach
            farthest = Math.max(farthest, i + nums[i]);
            
            // Early termination: can reach the end
            if (farthest >= nums.length - 1) {
                return true;
            }
        }
        
        return true;
    }
    
    /**
     * Approach 2: Greedy (Working Backwards)
     * Time Complexity: O(n) - Single pass
     * Space Complexity: O(1)
     * 
     * Algorithm:
     * 1. Start from the last position
     * 2. Check if we can reach current goal from earlier positions
     * 3. Update goal if reachable
     */
    public boolean canJumpBackwards(int[] nums) {
        int lastPos = nums.length - 1;
        
        for (int i = nums.length - 2; i >= 0; i--) {
            if (i + nums[i] >= lastPos) {
                lastPos = i;
            }
        }
        
        return lastPos == 0;
    }
    
    /**
     * Approach 3: Dynamic Programming
     * Time Complexity: O(n²) - Check all previous positions
     * Space Complexity: O(n) - DP array
     * 
     * dp[i] = true if position i is reachable
     */
    public boolean canJumpDP(int[] nums) {
        boolean[] dp = new boolean[nums.length];
        dp[0] = true; // Starting position is reachable
        
        for (int i = 1; i < nums.length; i++) {
            for (int j = 0; j < i; j++) {
                // If j is reachable and can jump to i
                if (dp[j] && j + nums[j] >= i) {
                    dp[i] = true;
                    break;
                }
            }
        }
        
        return dp[nums.length - 1];
    }
    
    /**
     * Approach 4: BFS
     * Time Complexity: O(n) - Visit each position once
     * Space Complexity: O(n) - Queue and visited set
     * 
     * Treat as graph problem
     */
    public boolean canJumpBFS(int[] nums) {
        if (nums.length == 1) return true;
        
        Queue<Integer> queue = new LinkedList<>();
        boolean[] visited = new boolean[nums.length];
        queue.offer(0);
        visited[0] = true;
        
        while (!queue.isEmpty()) {
            int pos = queue.poll();
            
            // Try all possible jumps from current position
            for (int i = 1; i <= nums[pos] && pos + i < nums.length; i++) {
                int nextPos = pos + i;
                
                if (nextPos == nums.length - 1) {
                    return true;
                }
                
                if (!visited[nextPos]) {
                    queue.offer(nextPos);
                    visited[nextPos] = true;
                }
            }
        }
        
        return false;
    }
    
    /**
     * LeetCode 45: Jump Game II
     * Return the minimum number of jumps to reach the last index
     * 
     * Time Complexity: O(n)
     * Space Complexity: O(1)
     */
    public int jump(int[] nums) {
        if (nums.length <= 1) return 0;
        
        int jumps = 0;
        int currentEnd = 0;
        int farthest = 0;
        
        for (int i = 0; i < nums.length - 1; i++) {
            farthest = Math.max(farthest, i + nums[i]);
            
            // Must jump from current range
            if (i == currentEnd) {
                jumps++;
                currentEnd = farthest;
                
                // Can reach the end
                if (currentEnd >= nums.length - 1) {
                    break;
                }
            }
        }
        
        return jumps;
    }
    
    /**
     * Jump Game II - DP Approach
     * Time Complexity: O(n²)
     * Space Complexity: O(n)
     */
    public int jumpDP(int[] nums) {
        int n = nums.length;
        int[] dp = new int[n];
        Arrays.fill(dp, Integer.MAX_VALUE);
        dp[0] = 0;
        
        for (int i = 0; i < n; i++) {
            if (dp[i] == Integer.MAX_VALUE) continue;
            
            for (int j = 1; j <= nums[i] && i + j < n; j++) {
                dp[i + j] = Math.min(dp[i + j], dp[i] + 1);
            }
        }
        
        return dp[n - 1];
    }
    
    /**
     * LeetCode 1306: Jump Game III
     * Can jump nums[i] steps forward or backward
     * Return true if can reach any index with value 0
     */
    public boolean canReach(int[] arr, int start) {
        if (start < 0 || start >= arr.length || arr[start] < 0) {
            return false;
        }
        
        if (arr[start] == 0) {
            return true;
        }
        
        // Mark as visited by making negative
        arr[start] = -arr[start];
        
        // Try both directions
        boolean canReachZero = canReach(arr, start + arr[start]) || 
                              canReach(arr, start - arr[start]);
        
        // Restore original value
        arr[start] = -arr[start];
        
        return canReachZero;
    }
    
    /**
     * Jump Game III - BFS Approach
     */
    public boolean canReachBFS(int[] arr, int start) {
        Queue<Integer> queue = new LinkedList<>();
        boolean[] visited = new boolean[arr.length];
        queue.offer(start);
        visited[start] = true;
        
        while (!queue.isEmpty()) {
            int pos = queue.poll();
            
            if (arr[pos] == 0) {
                return true;
            }
            
            // Try forward jump
            int forward = pos + arr[pos];
            if (forward < arr.length && !visited[forward]) {
                queue.offer(forward);
                visited[forward] = true;
            }
            
            // Try backward jump
            int backward = pos - arr[pos];
            if (backward >= 0 && !visited[backward]) {
                queue.offer(backward);
                visited[backward] = true;
            }
        }
        
        return false;
    }
    
    /**
     * LeetCode 1345: Jump Game IV
     * Can jump to i+1, i-1, or any j where arr[i] == arr[j]
     * Return minimum jumps to reach last index
     */
    public int minJumps(int[] arr) {
        int n = arr.length;
        if (n == 1) return 0;
        
        // Build graph of indices with same value
        Map<Integer, List<Integer>> graph = new HashMap<>();
        for (int i = 0; i < n; i++) {
            graph.computeIfAbsent(arr[i], k -> new ArrayList<>()).add(i);
        }
        
        Queue<Integer> queue = new LinkedList<>();
        boolean[] visited = new boolean[n];
        queue.offer(0);
        visited[0] = true;
        
        int steps = 0;
        
        while (!queue.isEmpty()) {
            int size = queue.size();
            
            for (int i = 0; i < size; i++) {
                int pos = queue.poll();
                
                if (pos == n - 1) {
                    return steps;
                }
                
                // Jump to same value indices
                if (graph.containsKey(arr[pos])) {
                    for (int next : graph.get(arr[pos])) {
                        if (!visited[next]) {
                            queue.offer(next);
                            visited[next] = true;
                        }
                    }
                    // Clear to avoid revisiting
                    graph.remove(arr[pos]);
                }
                
                // Jump to i+1
                if (pos + 1 < n && !visited[pos + 1]) {
                    queue.offer(pos + 1);
                    visited[pos + 1] = true;
                }
                
                // Jump to i-1
                if (pos - 1 >= 0 && !visited[pos - 1]) {
                    queue.offer(pos - 1);
                    visited[pos - 1] = true;
                }
            }
            
            steps++;
        }
        
        return -1;
    }
    
    // Test the solutions
    public static void main(String[] args) {
        JumpGame solution = new JumpGame();
        
        // Test Jump Game I
        int[] nums1 = {2, 3, 1, 1, 4};
        System.out.println("Jump Game I:");
        System.out.println("Input: [2,3,1,1,4]");
        System.out.println("Greedy: " + solution.canJumpGreedy(nums1));
        System.out.println("Backwards: " + solution.canJumpBackwards(nums1));
        System.out.println("DP: " + solution.canJumpDP(nums1));
        System.out.println("BFS: " + solution.canJumpBFS(nums1));
        
        int[] nums2 = {3, 2, 1, 0, 4};
        System.out.println("\nInput: [3,2,1,0,4]");
        System.out.println("Greedy: " + solution.canJumpGreedy(nums2));
        
        // Test Jump Game II
        int[] nums3 = {2, 3, 1, 1, 4};
        System.out.println("\nJump Game II:");
        System.out.println("Input: [2,3,1,1,4]");
        System.out.println("Min jumps: " + solution.jump(nums3));
        System.out.println("Min jumps (DP): " + solution.jumpDP(nums3));
        
        // Test Jump Game III
        int[] arr1 = {4, 2, 3, 0, 3, 1, 2};
        int start1 = 5;
        System.out.println("\nJump Game III:");
        System.out.println("Input: [4,2,3,0,3,1,2], start = 5");
        System.out.println("Can reach 0: " + solution.canReach(arr1.clone(), start1));
        System.out.println("Can reach 0 (BFS): " + solution.canReachBFS(arr1, start1));
        
        // Test Jump Game IV
        int[] arr2 = {100, -23, -23, 404, 100, 23, 23, 23, 3, 404};
        System.out.println("\nJump Game IV:");
        System.out.println("Input: [100,-23,-23,404,100,23,23,23,3,404]");
        System.out.println("Min jumps: " + solution.minJumps(arr2));
    }
} 