package leetcode.heap;

import java.util.*;

/**
 * LeetCode 347: Top K Frequent Elements
 * 
 * Given an integer array nums and an integer k, return the k most frequent elements.
 * You may return the answer in any order.
 * 
 * Example:
 * Input: nums = [1,1,1,2,2,3], k = 2
 * Output: [1,2]
 */
public class TopKFrequentElements {
    
    /**
     * Approach 1: Min Heap of Size K
     * Time: O(n log k), Space: O(n + k)
     * 
     * Algorithm:
     * 1. Count frequency of each element
     * 2. Use min heap of size k to keep top k frequent elements
     * 3. If heap size > k, remove minimum frequency element
     */
    public int[] topKFrequent(int[] nums, int k) {
        // Count frequencies
        Map<Integer, Integer> freqMap = new HashMap<>();
        for (int num : nums) {
            freqMap.put(num, freqMap.getOrDefault(num, 0) + 1);
        }
        
        // Min heap to store k most frequent elements
        PriorityQueue<int[]> minHeap = new PriorityQueue<>((a, b) -> a[1] - b[1]);
        
        for (Map.Entry<Integer, Integer> entry : freqMap.entrySet()) {
            minHeap.offer(new int[]{entry.getKey(), entry.getValue()});
            
            if (minHeap.size() > k) {
                minHeap.poll();
            }
        }
        
        // Extract result
        int[] result = new int[k];
        for (int i = 0; i < k; i++) {
            result[i] = minHeap.poll()[0];
        }
        
        return result;
    }
    
    /**
     * Approach 2: Max Heap (Simple)
     * Time: O(n log n), Space: O(n)
     * Use max heap to get top k elements directly
     */
    public int[] topKFrequentMaxHeap(int[] nums, int k) {
        Map<Integer, Integer> freqMap = new HashMap<>();
        for (int num : nums) {
            freqMap.put(num, freqMap.getOrDefault(num, 0) + 1);
        }
        
        // Max heap based on frequency
        PriorityQueue<int[]> maxHeap = new PriorityQueue<>((a, b) -> b[1] - a[1]);
        
        for (Map.Entry<Integer, Integer> entry : freqMap.entrySet()) {
            maxHeap.offer(new int[]{entry.getKey(), entry.getValue()});
        }
        
        int[] result = new int[k];
        for (int i = 0; i < k; i++) {
            result[i] = maxHeap.poll()[0];
        }
        
        return result;
    }
    
    /**
     * Approach 3: Bucket Sort
     * Time: O(n), Space: O(n)
     * 
     * Algorithm:
     * 1. Count frequencies
     * 2. Create buckets where index = frequency
     * 3. Fill buckets with elements having that frequency
     * 4. Traverse from highest frequency to get top k
     */
    public int[] topKFrequentBucketSort(int[] nums, int k) {
        Map<Integer, Integer> freqMap = new HashMap<>();
        for (int num : nums) {
            freqMap.put(num, freqMap.getOrDefault(num, 0) + 1);
        }
        
        // Create buckets for each frequency
        List<Integer>[] buckets = new List[nums.length + 1];
        for (int i = 0; i <= nums.length; i++) {
            buckets[i] = new ArrayList<>();
        }
        
        // Fill buckets
        for (Map.Entry<Integer, Integer> entry : freqMap.entrySet()) {
            int freq = entry.getValue();
            buckets[freq].add(entry.getKey());
        }
        
        // Collect top k elements
        List<Integer> result = new ArrayList<>();
        for (int i = buckets.length - 1; i >= 0 && result.size() < k; i--) {
            if (!buckets[i].isEmpty()) {
                result.addAll(buckets[i]);
            }
        }
        
        return result.stream().limit(k).mapToInt(i -> i).toArray();
    }
    
    /**
     * Approach 4: Quick Select
     * Time: O(n) average, O(n²) worst, Space: O(n)
     * Use quickselect to find kth most frequent element
     */
    public int[] topKFrequentQuickSelect(int[] nums, int k) {
        Map<Integer, Integer> freqMap = new HashMap<>();
        for (int num : nums) {
            freqMap.put(num, freqMap.getOrDefault(num, 0) + 1);
        }
        
        int[][] freqArray = new int[freqMap.size()][2];
        int idx = 0;
        for (Map.Entry<Integer, Integer> entry : freqMap.entrySet()) {
            freqArray[idx][0] = entry.getKey();
            freqArray[idx][1] = entry.getValue();
            idx++;
        }
        
        // Use quickselect to find top k elements
        quickSelect(freqArray, 0, freqArray.length - 1, freqArray.length - k);
        
        int[] result = new int[k];
        for (int i = freqArray.length - k; i < freqArray.length; i++) {
            result[i - (freqArray.length - k)] = freqArray[i][0];
        }
        
        return result;
    }
    
    private void quickSelect(int[][] arr, int left, int right, int kIndex) {
        if (left == right) return;
        
        int pivotIndex = partition(arr, left, right);
        
        if (pivotIndex == kIndex) {
            return;
        } else if (pivotIndex < kIndex) {
            quickSelect(arr, pivotIndex + 1, right, kIndex);
        } else {
            quickSelect(arr, left, pivotIndex - 1, kIndex);
        }
    }
    
    private int partition(int[][] arr, int left, int right) {
        int pivot = arr[right][1];
        int i = left;
        
        for (int j = left; j < right; j++) {
            if (arr[j][1] <= pivot) {
                swap(arr, i, j);
                i++;
            }
        }
        
        swap(arr, i, right);
        return i;
    }
    
    private void swap(int[][] arr, int i, int j) {
        int[] temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
    
    /**
     * Extended Problem: Top K Frequent Words (LC 692)
     * Same concept but with lexicographical ordering for ties
     */
    public List<String> topKFrequentWords(String[] words, int k) {
        Map<String, Integer> freqMap = new HashMap<>();
        for (String word : words) {
            freqMap.put(word, freqMap.getOrDefault(word, 0) + 1);
        }
        
        // Min heap with custom comparator
        PriorityQueue<String> minHeap = new PriorityQueue<>((a, b) -> {
            int freqA = freqMap.get(a);
            int freqB = freqMap.get(b);
            
            if (freqA == freqB) {
                return b.compareTo(a); // Reverse lexicographical for min heap
            }
            return freqA - freqB; // Min frequency first
        });
        
        for (String word : freqMap.keySet()) {
            minHeap.offer(word);
            if (minHeap.size() > k) {
                minHeap.poll();
            }
        }
        
        List<String> result = new ArrayList<>();
        while (!minHeap.isEmpty()) {
            result.add(0, minHeap.poll()); // Add to front to maintain order
        }
        
        return result;
    }
    
    /**
     * Extended Problem: Frequency Sort (LC 451)
     * Sort characters by frequency
     */
    public String frequencySort(String s) {
        Map<Character, Integer> freqMap = new HashMap<>();
        for (char c : s.toCharArray()) {
            freqMap.put(c, freqMap.getOrDefault(c, 0) + 1);
        }
        
        // Max heap based on frequency
        PriorityQueue<Map.Entry<Character, Integer>> maxHeap = 
            new PriorityQueue<>((a, b) -> b.getValue() - a.getValue());
        
        maxHeap.addAll(freqMap.entrySet());
        
        StringBuilder result = new StringBuilder();
        while (!maxHeap.isEmpty()) {
            Map.Entry<Character, Integer> entry = maxHeap.poll();
            char c = entry.getKey();
            int freq = entry.getValue();
            
            for (int i = 0; i < freq; i++) {
                result.append(c);
            }
        }
        
        return result.toString();
    }
    
    /**
     * Extended Problem: Find K Closest Elements (LC 658)
     * Find k elements closest to target
     */
    public List<Integer> findClosestElements(int[] arr, int k, int x) {
        // Max heap based on distance from x
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>((a, b) -> {
            int distA = Math.abs(a - x);
            int distB = Math.abs(b - x);
            
            if (distA == distB) {
                return b - a; // If same distance, prefer smaller number
            }
            return distB - distA; // Max distance first
        });
        
        for (int num : arr) {
            maxHeap.offer(num);
            if (maxHeap.size() > k) {
                maxHeap.poll();
            }
        }
        
        List<Integer> result = new ArrayList<>(maxHeap);
        Collections.sort(result);
        return result;
    }
    
    /**
     * Extended Problem: Reorganize String (LC 767)
     * Use heap to reorganize string so no adjacent characters are same
     */
    public String reorganizeString(String s) {
        Map<Character, Integer> freqMap = new HashMap<>();
        for (char c : s.toCharArray()) {
            freqMap.put(c, freqMap.getOrDefault(c, 0) + 1);
        }
        
        // Max heap based on frequency
        PriorityQueue<Map.Entry<Character, Integer>> maxHeap = 
            new PriorityQueue<>((a, b) -> b.getValue() - a.getValue());
        
        maxHeap.addAll(freqMap.entrySet());
        
        StringBuilder result = new StringBuilder();
        Map.Entry<Character, Integer> prev = null;
        
        while (!maxHeap.isEmpty()) {
            Map.Entry<Character, Integer> current = maxHeap.poll();
            
            result.append(current.getKey());
            current.setValue(current.getValue() - 1);
            
            // Add previous back if it still has count
            if (prev != null && prev.getValue() > 0) {
                maxHeap.offer(prev);
            }
            
            prev = current;
        }
        
        return result.length() == s.length() ? result.toString() : "";
    }
    
    // Test cases
    public static void main(String[] args) {
        TopKFrequentElements solution = new TopKFrequentElements();
        
        // Test top k frequent elements
        int[] nums1 = {1, 1, 1, 2, 2, 3};
        int k1 = 2;
        System.out.println("Test 1 - Top " + k1 + " frequent:");
        System.out.println("Min Heap: " + Arrays.toString(solution.topKFrequent(nums1, k1)));
        System.out.println("Max Heap: " + Arrays.toString(solution.topKFrequentMaxHeap(nums1, k1)));
        System.out.println("Bucket Sort: " + Arrays.toString(solution.topKFrequentBucketSort(nums1, k1)));
        System.out.println("Quick Select: " + Arrays.toString(solution.topKFrequentQuickSelect(nums1, k1)));
        
        // Test edge case
        int[] nums2 = {1};
        int k2 = 1;
        System.out.println("\nTest 2 - Single element:");
        System.out.println("Result: " + Arrays.toString(solution.topKFrequent(nums2, k2)));
        
        // Test top k frequent words
        String[] words = {"i", "love", "leetcode", "i", "love", "coding"};
        System.out.println("\nTop 2 frequent words: " + solution.topKFrequentWords(words, 2));
        
        // Test frequency sort
        System.out.println("Frequency sort 'tree': " + solution.frequencySort("tree"));
        
        // Test find closest elements
        int[] arr = {1, 2, 3, 4, 5};
        System.out.println("3 closest to 3: " + solution.findClosestElements(arr, 3, 3));
        
        // Test reorganize string
        System.out.println("Reorganize 'aab': " + solution.reorganizeString("aab"));
        System.out.println("Reorganize 'aaab': " + solution.reorganizeString("aaab"));
    }
} 