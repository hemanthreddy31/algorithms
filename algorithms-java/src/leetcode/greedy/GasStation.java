package leetcode.greedy;

import java.util.*;

/**
 * LeetCode 134: Gas Station
 * 
 * There are n gas stations along a circular route, where the amount of gas at the ith station is gas[i].
 * You have a car with an unlimited gas tank and it costs cost[i] of gas to travel from the ith station 
 * to its next (i + 1)th station. You begin the journey with an empty tank at one of the gas stations.
 * 
 * Given two integer arrays gas and cost, return the starting gas station's index if you can travel 
 * around the circuit once in the clockwise direction, otherwise return -1.
 * 
 * If there exists a solution, it is guaranteed to be unique.
 */
public class GasStation {
    
    /**
     * Approach 1: Greedy One Pass
     * Time: O(n), Space: O(1)
     * 
     * Key Insight: If we can't reach station j from station i, 
     * then we can't reach j from any station between i and j.
     */
    public int canCompleteCircuit(int[] gas, int[] cost) {
        int totalGas = 0;
        int totalCost = 0;
        int currentGas = 0;
        int startIndex = 0;
        
        for (int i = 0; i < gas.length; i++) {
            totalGas += gas[i];
            totalCost += cost[i];
            currentGas += gas[i] - cost[i];
            
            // If we can't reach next station, reset starting point
            if (currentGas < 0) {
                startIndex = i + 1;
                currentGas = 0;
            }
        }
        
        // If total gas >= total cost, solution exists
        return totalGas >= totalCost ? startIndex : -1;
    }
    
    /**
     * Approach 2: Brute Force (For comparison)
     * Time: O(n²), Space: O(1)
     * Try each starting position
     */
    public int canCompleteCircuitBruteForce(int[] gas, int[] cost) {
        int n = gas.length;
        
        for (int start = 0; start < n; start++) {
            if (canStartFrom(gas, cost, start)) {
                return start;
            }
        }
        
        return -1;
    }
    
    private boolean canStartFrom(int[] gas, int[] cost, int start) {
        int n = gas.length;
        int tank = 0;
        
        for (int i = 0; i < n; i++) {
            int index = (start + i) % n;
            tank += gas[index] - cost[index];
            
            if (tank < 0) {
                return false;
            }
        }
        
        return true;
    }
    
    /**
     * Approach 3: Two Pointers (Circular)
     * Time: O(n), Space: O(1)
     * Find the longest segment we can travel
     */
    public int canCompleteCircuitTwoPointers(int[] gas, int[] cost) {
        int n = gas.length;
        int start = 0;
        int end = 1;
        int currentGas = gas[start] - cost[start];
        
        // Keep trying until we complete the circle or prove impossible
        while (end != start || currentGas < 0) {
            // If current gas is negative, remove starting stations
            while (currentGas < 0 && start != end) {
                currentGas -= (gas[start] - cost[start]);
                start = (start + 1) % n;
                
                if (start == 0) {
                    return -1; // Completed full circle
                }
            }
            
            // Add ending stations
            currentGas += gas[end] - cost[end];
            end = (end + 1) % n;
        }
        
        return start;
    }
    
    /**
     * Approach 4: Difference Array Analysis
     * Time: O(n), Space: O(n)
     * Analyze the difference between gas and cost
     */
    public int canCompleteCircuitDifferenceArray(int[] gas, int[] cost) {
        int n = gas.length;
        int[] diff = new int[n];
        int total = 0;
        
        // Calculate difference array
        for (int i = 0; i < n; i++) {
            diff[i] = gas[i] - cost[i];
            total += diff[i];
        }
        
        // If total is negative, no solution
        if (total < 0) {
            return -1;
        }
        
        // Find starting point where cumulative sum never goes negative
        int cumSum = 0;
        int start = 0;
        
        for (int i = 0; i < n; i++) {
            cumSum += diff[i];
            if (cumSum < 0) {
                start = i + 1;
                cumSum = 0;
            }
        }
        
        return start;
    }
    
    /**
     * Extended Problem: Minimum Refuel Stops
     * Find minimum number of refuel stops to complete journey
     */
    public int minRefuelStops(int target, int startFuel, int[][] stations) {
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>((a, b) -> b - a);
        int fuel = startFuel;
        int position = 0;
        int stops = 0;
        int stationIndex = 0;
        
        while (fuel < target) {
            // Add all reachable stations to heap
            while (stationIndex < stations.length && stations[stationIndex][0] <= fuel) {
                maxHeap.offer(stations[stationIndex][1]);
                stationIndex++;
            }
            
            // If no stations available, impossible
            if (maxHeap.isEmpty()) {
                return -1;
            }
            
            // Refuel at station with most gas
            fuel += maxHeap.poll();
            stops++;
        }
        
        return stops;
    }
    
    /**
     * Extended Problem: Find Peak Element in Circular Array
     * Related circular array problem
     */
    public int findPeakInCircular(int[] nums) {
        int n = nums.length;
        
        for (int i = 0; i < n; i++) {
            int prev = nums[(i - 1 + n) % n];
            int next = nums[(i + 1) % n];
            
            if (nums[i] > prev && nums[i] > next) {
                return i;
            }
        }
        
        return -1; // No peak found
    }
    
    /**
     * Extended Problem: Circular Array Loop (LC 457)
     * Check if circular array has a valid loop
     */
    public boolean circularArrayLoop(int[] nums) {
        int n = nums.length;
        
        for (int i = 0; i < n; i++) {
            if (nums[i] == 0) continue;
            
            int slow = i, fast = i;
            
            // Check if all moves are in same direction
            while (nums[slow] * nums[getNext(nums, slow)] > 0 &&
                   nums[fast] * nums[getNext(nums, fast)] > 0 &&
                   nums[fast] * nums[getNext(nums, getNext(nums, fast))] > 0) {
                
                slow = getNext(nums, slow);
                fast = getNext(nums, getNext(nums, fast));
                
                if (slow == fast) {
                    // Check if loop length > 1
                    if (slow == getNext(nums, slow)) {
                        break; // Self loop
                    }
                    return true;
                }
            }
            
            // Mark all elements in this path as visited
            slow = i;
            int direction = nums[i];
            while (nums[slow] * direction > 0) {
                int next = getNext(nums, slow);
                nums[slow] = 0;
                slow = next;
            }
        }
        
        return false;
    }
    
    private int getNext(int[] nums, int i) {
        int n = nums.length;
        return ((i + nums[i]) % n + n) % n;
    }
    
    /**
     * Utility: Validate solution
     * Check if a starting position actually works
     */
    public boolean validateSolution(int[] gas, int[] cost, int start) {
        int n = gas.length;
        int tank = 0;
        
        for (int i = 0; i < n; i++) {
            int index = (start + i) % n;
            tank += gas[index] - cost[index];
            
            if (tank < 0) {
                return false;
            }
        }
        
        return true;
    }
    
    // Test cases
    public static void main(String[] args) {
        GasStation solution = new GasStation();
        
        // Test case 1: Valid solution
        int[] gas1 = {1, 2, 3, 4, 5};
        int[] cost1 = {3, 4, 5, 1, 2};
        int result1 = solution.canCompleteCircuit(gas1, cost1);
        System.out.println("Test 1 - Starting index: " + result1);
        System.out.println("Validation: " + solution.validateSolution(gas1, cost1, result1));
        
        // Test case 2: No solution
        int[] gas2 = {2, 3, 4};
        int[] cost2 = {3, 4, 3};
        int result2 = solution.canCompleteCircuit(gas2, cost2);
        System.out.println("\nTest 2 - Starting index: " + result2);
        
        // Test case 3: Single station
        int[] gas3 = {5};
        int[] cost3 = {4};
        int result3 = solution.canCompleteCircuit(gas3, cost3);
        System.out.println("\nTest 3 - Starting index: " + result3);
        
        // Compare approaches
        System.out.println("\nComparing approaches for test 1:");
        System.out.println("Greedy: " + solution.canCompleteCircuit(gas1, cost1));
        System.out.println("Brute Force: " + solution.canCompleteCircuitBruteForce(gas1, cost1));
        System.out.println("Two Pointers: " + solution.canCompleteCircuitTwoPointers(gas1, cost1));
        System.out.println("Difference Array: " + solution.canCompleteCircuitDifferenceArray(gas1, cost1));
        
        // Test circular array loop
        int[] circularArray = {2, -1, 1, 2, 2};
        System.out.println("\nCircular array loop exists: " + solution.circularArrayLoop(circularArray));
        
        // Test refuel stops
        int[][] stations = {{10, 60}, {20, 30}, {30, 30}, {60, 40}};
        System.out.println("Min refuel stops: " + solution.minRefuelStops(100, 50, stations));
    }
} 