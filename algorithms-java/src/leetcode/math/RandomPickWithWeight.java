package leetcode.math;

import java.util.*;

/**
 * LeetCode 528: Random Pick with Weight
 * 
 * You are given an array of positive integers w where w[i] describes the weight of ith index (0-indexed).
 * We need to call the function pickIndex() which randomly returns an integer in the range [0, w.length - 1].
 * pickIndex() should return the integer proportional to its weight.
 * 
 * Example:
 * Input: ["Solution", "pickIndex", "pickIndex", "pickIndex", "pickIndex", "pickIndex"]
 *        [[[1]], [], [], [], [], []]
 * Output: [null, 0, 0, 0, 0, 0]
 */
public class RandomPickWithWeight {
    
    /**
     * Approach 1: Prefix Sum + Binary Search
     * Time: O(n) for constructor, O(log n) for pickIndex
     * Space: O(n)
     */
    static class Solution {
        private int[] prefixSums;
        private Random random;
        
        public Solution(int[] w) {
            this.random = new Random();
            this.prefixSums = new int[w.length];
            
            // Build prefix sum array
            prefixSums[0] = w[0];
            for (int i = 1; i < w.length; i++) {
                prefixSums[i] = prefixSums[i - 1] + w[i];
            }
        }
        
        public int pickIndex() {
            int totalSum = prefixSums[prefixSums.length - 1];
            int target = random.nextInt(totalSum) + 1; // +1 because we want [1, totalSum]
            
            // Binary search for the target
            int left = 0, right = prefixSums.length - 1;
            
            while (left < right) {
                int mid = left + (right - left) / 2;
                
                if (prefixSums[mid] < target) {
                    left = mid + 1;
                } else {
                    right = mid;
                }
            }
            
            return left;
        }
    }
    
    /**
     * Approach 2: Optimized with Linear Search
     * Time: O(n) for constructor, O(n) for pickIndex (worst case)
     * Space: O(n)
     * Faster for small arrays due to cache locality
     */
    static class SolutionLinear {
        private int[] prefixSums;
        private Random random;
        
        public SolutionLinear(int[] w) {
            this.random = new Random();
            this.prefixSums = new int[w.length];
            
            prefixSums[0] = w[0];
            for (int i = 1; i < w.length; i++) {
                prefixSums[i] = prefixSums[i - 1] + w[i];
            }
        }
        
        public int pickIndex() {
            int totalSum = prefixSums[prefixSums.length - 1];
            int target = random.nextInt(totalSum) + 1;
            
            // Linear search
            for (int i = 0; i < prefixSums.length; i++) {
                if (prefixSums[i] >= target) {
                    return i;
                }
            }
            
            return prefixSums.length - 1; // Should never reach here
        }
    }
    
    /**
     * Approach 3: Reservoir Sampling (Space Efficient)
     * Time: O(n) for constructor, O(n) for pickIndex
     * Space: O(1) additional space (excluding input)
     */
    static class SolutionReservoir {
        private int[] weights;
        private Random random;
        
        public SolutionReservoir(int[] w) {
            this.weights = w.clone();
            this.random = new Random();
        }
        
        public int pickIndex() {
            int totalSum = 0;
            int result = 0;
            
            for (int i = 0; i < weights.length; i++) {
                totalSum += weights[i];
                
                // Probability of selecting current index = weights[i] / totalSum
                if (random.nextInt(totalSum) < weights[i]) {
                    result = i;
                }
            }
            
            return result;
        }
    }
    
    /**
     * Extended Problem: Random Pick with Blacklist (LC 710)
     * Pick random number from [0, N) excluding blacklisted numbers
     */
    static class RandomPickBlacklist {
        private Map<Integer, Integer> blacklistMap;
        private Random random;
        private int validRange;
        
        public RandomPickBlacklist(int N, int[] blacklist) {
            this.random = new Random();
            this.blacklistMap = new HashMap<>();
            this.validRange = N - blacklist.length;
            
            Set<Integer> blacklistSet = new HashSet<>();
            for (int b : blacklist) {
                blacklistSet.add(b);
            }
            
            // Map blacklisted numbers in [0, validRange) to non-blacklisted numbers in [validRange, N)
            int nextValid = validRange;
            for (int b : blacklist) {
                if (b < validRange) {
                    while (blacklistSet.contains(nextValid)) {
                        nextValid++;
                    }
                    blacklistMap.put(b, nextValid++);
                }
            }
        }
        
        public int pick() {
            int randomIndex = random.nextInt(validRange);
            return blacklistMap.getOrDefault(randomIndex, randomIndex);
        }
    }
    
    /**
     * Extended Problem: Random Point in Non-overlapping Rectangles (LC 497)
     */
    static class RandomPointInRectangles {
        private int[][] rectangles;
        private int[] prefixSums;
        private Random random;
        
        public RandomPointInRectangles(int[][] rects) {
            this.rectangles = rects;
            this.random = new Random();
            this.prefixSums = new int[rects.length];
            
            // Calculate areas and build prefix sum
            for (int i = 0; i < rects.length; i++) {
                int area = (rects[i][2] - rects[i][0] + 1) * (rects[i][3] - rects[i][1] + 1);
                prefixSums[i] = (i == 0 ? 0 : prefixSums[i - 1]) + area;
            }
        }
        
        public int[] pick() {
            int totalArea = prefixSums[prefixSums.length - 1];
            int target = random.nextInt(totalArea) + 1;
            
            // Binary search for rectangle
            int left = 0, right = prefixSums.length - 1;
            while (left < right) {
                int mid = left + (right - left) / 2;
                if (prefixSums[mid] < target) {
                    left = mid + 1;
                } else {
                    right = mid;
                }
            }
            
            // Pick random point in selected rectangle
            int[] rect = rectangles[left];
            int x = random.nextInt(rect[2] - rect[0] + 1) + rect[0];
            int y = random.nextInt(rect[3] - rect[1] + 1) + rect[1];
            
            return new int[]{x, y};
        }
    }
    
    /**
     * Extended Problem: Weighted Random Selection with Updates
     * Support dynamic weight updates
     */
    static class WeightedRandomWithUpdates {
        private TreeMap<Integer, Integer> cumulativeWeights; // cumulative weight -> index
        private Map<Integer, Integer> weights; // index -> weight
        private Random random;
        private int totalWeight;
        
        public WeightedRandomWithUpdates() {
            this.cumulativeWeights = new TreeMap<>();
            this.weights = new HashMap<>();
            this.random = new Random();
            this.totalWeight = 0;
        }
        
        public void addElement(int index, int weight) {
            if (weights.containsKey(index)) {
                updateWeight(index, weight);
                return;
            }
            
            weights.put(index, weight);
            totalWeight += weight;
            cumulativeWeights.put(totalWeight, index);
        }
        
        public void updateWeight(int index, int newWeight) {
            if (!weights.containsKey(index)) {
                addElement(index, newWeight);
                return;
            }
            
            int oldWeight = weights.get(index);
            weights.put(index, newWeight);
            
            // Rebuild cumulative weights (inefficient but simple)
            rebuildCumulativeWeights();
        }
        
        public void removeElement(int index) {
            if (!weights.containsKey(index)) return;
            
            weights.remove(index);
            rebuildCumulativeWeights();
        }
        
        public int pickIndex() {
            if (totalWeight == 0) return -1;
            
            int target = random.nextInt(totalWeight) + 1;
            return cumulativeWeights.ceilingEntry(target).getValue();
        }
        
        private void rebuildCumulativeWeights() {
            cumulativeWeights.clear();
            totalWeight = 0;
            
            for (Map.Entry<Integer, Integer> entry : weights.entrySet()) {
                totalWeight += entry.getValue();
                cumulativeWeights.put(totalWeight, entry.getKey());
            }
        }
    }
    
    /**
     * Extended Problem: Alias Method (O(1) pickIndex after O(n) preprocessing)
     * Most efficient for frequent picks with static weights
     */
    static class AliasMethod {
        private int[] alias;
        private double[] probability;
        private Random random;
        
        public AliasMethod(int[] weights) {
            this.random = new Random();
            int n = weights.length;
            this.alias = new int[n];
            this.probability = new double[n];
            
            // Calculate total weight and normalize
            long totalWeight = 0;
            for (int w : weights) totalWeight += w;
            
            double[] scaledWeights = new double[n];
            for (int i = 0; i < n; i++) {
                scaledWeights[i] = (double) weights[i] * n / totalWeight;
            }
            
            // Separate into small and large buckets
            Queue<Integer> small = new LinkedList<>();
            Queue<Integer> large = new LinkedList<>();
            
            for (int i = 0; i < n; i++) {
                if (scaledWeights[i] < 1.0) {
                    small.offer(i);
                } else {
                    large.offer(i);
                }
            }
            
            // Build alias table
            while (!small.isEmpty() && !large.isEmpty()) {
                int smallIndex = small.poll();
                int largeIndex = large.poll();
                
                probability[smallIndex] = scaledWeights[smallIndex];
                alias[smallIndex] = largeIndex;
                
                scaledWeights[largeIndex] = scaledWeights[largeIndex] + scaledWeights[smallIndex] - 1.0;
                
                if (scaledWeights[largeIndex] < 1.0) {
                    small.offer(largeIndex);
                } else {
                    large.offer(largeIndex);
                }
            }
            
            while (!large.isEmpty()) {
                probability[large.poll()] = 1.0;
            }
            
            while (!small.isEmpty()) {
                probability[small.poll()] = 1.0;
            }
        }
        
        public int pickIndex() {
            int randomBucket = random.nextInt(probability.length);
            double randomProb = random.nextDouble();
            
            if (randomProb < probability[randomBucket]) {
                return randomBucket;
            } else {
                return alias[randomBucket];
            }
        }
    }
    
    // Test and demonstration
    public static void main(String[] args) {
        System.out.println("=== Testing Random Pick with Weight ===");
        
        // Test case 1: Basic functionality
        int[] weights1 = {1, 3, 2};
        Solution solution1 = new Solution(weights1);
        
        System.out.println("Weights: " + Arrays.toString(weights1));
        Map<Integer, Integer> counts1 = new HashMap<>();
        int trials = 10000;
        
        for (int i = 0; i < trials; i++) {
            int picked = solution1.pickIndex();
            counts1.put(picked, counts1.getOrDefault(picked, 0) + 1);
        }
        
        System.out.println("Results after " + trials + " trials:");
        for (int i = 0; i < weights1.length; i++) {
            double expectedRatio = (double) weights1[i] / Arrays.stream(weights1).sum();
            double actualRatio = (double) counts1.getOrDefault(i, 0) / trials;
            System.out.printf("Index %d: Expected %.3f, Actual %.3f (count: %d)\n", 
                            i, expectedRatio, actualRatio, counts1.getOrDefault(i, 0));
        }
        
        // Test case 2: Compare different approaches
        System.out.println("\n=== Comparing Different Approaches ===");
        int[] weights2 = {5, 1, 1, 1, 1, 1};
        
        Solution binarySearch = new Solution(weights2);
        SolutionLinear linearSearch = new SolutionLinear(weights2);
        SolutionReservoir reservoir = new SolutionReservoir(weights2);
        AliasMethod alias = new AliasMethod(weights2);
        
        Map<String, Map<Integer, Integer>> results = new HashMap<>();
        results.put("Binary Search", new HashMap<>());
        results.put("Linear Search", new HashMap<>());
        results.put("Reservoir", new HashMap<>());
        results.put("Alias Method", new HashMap<>());
        
        int testTrials = 1000;
        for (int i = 0; i < testTrials; i++) {
            incrementCount(results.get("Binary Search"), binarySearch.pickIndex());
            incrementCount(results.get("Linear Search"), linearSearch.pickIndex());
            incrementCount(results.get("Reservoir"), reservoir.pickIndex());
            incrementCount(results.get("Alias Method"), alias.pickIndex());
        }
        
        System.out.println("Weights: " + Arrays.toString(weights2));
        for (String method : results.keySet()) {
            System.out.println(method + ": " + results.get(method));
        }
        
        // Test case 3: Weighted Random with Updates
        System.out.println("\n=== Testing Weighted Random with Updates ===");
        WeightedRandomWithUpdates dynamicWeighted = new WeightedRandomWithUpdates();
        
        dynamicWeighted.addElement(0, 10);
        dynamicWeighted.addElement(1, 20);
        dynamicWeighted.addElement(2, 30);
        
        System.out.println("Initial picks:");
        for (int i = 0; i < 10; i++) {
            System.out.print(dynamicWeighted.pickIndex() + " ");
        }
        System.out.println();
        
        // Update weights
        dynamicWeighted.updateWeight(0, 50);
        System.out.println("After increasing weight of index 0:");
        for (int i = 0; i < 10; i++) {
            System.out.print(dynamicWeighted.pickIndex() + " ");
        }
        System.out.println();
        
        // Test case 4: Random Pick with Blacklist
        System.out.println("\n=== Testing Random Pick with Blacklist ===");
        int[] blacklist = {2, 3, 5};
        RandomPickBlacklist blacklistPicker = new RandomPickBlacklist(7, blacklist);
        
        System.out.println("Blacklist: " + Arrays.toString(blacklist));
        System.out.println("Valid range: [0, 7), excluding blacklisted");
        System.out.print("Picks: ");
        for (int i = 0; i < 20; i++) {
            System.out.print(blacklistPicker.pick() + " ");
        }
        System.out.println();
        
        // Test case 5: Random Point in Rectangles
        System.out.println("\n=== Testing Random Point in Rectangles ===");
        int[][] rectangles = {{1, 1, 5, 5}, {10, 10, 15, 15}};
        RandomPointInRectangles rectPicker = new RandomPointInRectangles(rectangles);
        
        System.out.println("Rectangles: " + Arrays.deepToString(rectangles));
        System.out.print("Random points: ");
        for (int i = 0; i < 10; i++) {
            System.out.print(Arrays.toString(rectPicker.pick()) + " ");
        }
        System.out.println();
    }
    
    private static void incrementCount(Map<Integer, Integer> counts, int key) {
        counts.put(key, counts.getOrDefault(key, 0) + 1);
    }
} 