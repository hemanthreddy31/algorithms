package leetcode.heap;

import java.util.*;

/**
 * LeetCode 295: Find Median from Data Stream
 * 
 * The median is the middle value in an ordered list. If the size of the list is even,
 * there is no middle value and the median is the mean of the two middle values.
 * 
 * Implement the MedianFinder class:
 * - MedianFinder() initializes the MedianFinder object.
 * - void addNum(int num) adds the integer num from the data stream to the data structure.
 * - double findMedian() returns the median of all elements so far.
 */
public class FindMedianDataStream {
    
    /**
     * Approach 1: Two Heaps (Optimal)
     * Time: O(log n) for add, O(1) for find median
     * Space: O(n)
     * 
     * Use max heap for smaller half and min heap for larger half
     */
    static class MedianFinder {
        private PriorityQueue<Integer> maxHeap; // For smaller half (max at top)
        private PriorityQueue<Integer> minHeap; // For larger half (min at top)
        
        public MedianFinder() {
            maxHeap = new PriorityQueue<>((a, b) -> b - a); // Max heap
            minHeap = new PriorityQueue<>(); // Min heap
        }
        
        public void addNum(int num) {
            // Always add to maxHeap first
            maxHeap.offer(num);
            
            // Move the largest element from maxHeap to minHeap
            minHeap.offer(maxHeap.poll());
            
            // Balance the heaps: maxHeap should have at most 1 more element than minHeap
            if (minHeap.size() > maxHeap.size()) {
                maxHeap.offer(minHeap.poll());
            }
        }
        
        public double findMedian() {
            if (maxHeap.size() > minHeap.size()) {
                return maxHeap.peek();
            } else {
                return (maxHeap.peek() + minHeap.peek()) / 2.0;
            }
        }
    }
    
    /**
     * Approach 2: Optimized Two Heaps (Direct insertion)
     * Slightly more efficient by avoiding unnecessary moves
     */
    static class MedianFinderOptimized {
        private PriorityQueue<Integer> maxHeap;
        private PriorityQueue<Integer> minHeap;
        
        public MedianFinderOptimized() {
            maxHeap = new PriorityQueue<>((a, b) -> b - a);
            minHeap = new PriorityQueue<>();
        }
        
        public void addNum(int num) {
            if (maxHeap.isEmpty() || num <= maxHeap.peek()) {
                maxHeap.offer(num);
            } else {
                minHeap.offer(num);
            }
            
            // Balance heaps
            if (maxHeap.size() > minHeap.size() + 1) {
                minHeap.offer(maxHeap.poll());
            } else if (minHeap.size() > maxHeap.size()) {
                maxHeap.offer(minHeap.poll());
            }
        }
        
        public double findMedian() {
            if (maxHeap.size() > minHeap.size()) {
                return maxHeap.peek();
            } else {
                return (maxHeap.peek() + minHeap.peek()) / 2.0;
            }
        }
    }
    
    /**
     * Approach 3: Sorted List (For comparison)
     * Time: O(n) for add, O(1) for find median
     * Space: O(n)
     */
    static class MedianFinderSortedList {
        private List<Integer> list;
        
        public MedianFinderSortedList() {
            list = new ArrayList<>();
        }
        
        public void addNum(int num) {
            int left = 0, right = list.size();
            
            // Binary search for insertion position
            while (left < right) {
                int mid = left + (right - left) / 2;
                if (list.get(mid) < num) {
                    left = mid + 1;
                } else {
                    right = mid;
                }
            }
            
            list.add(left, num);
        }
        
        public double findMedian() {
            int n = list.size();
            if (n % 2 == 1) {
                return list.get(n / 2);
            } else {
                return (list.get(n / 2 - 1) + list.get(n / 2)) / 2.0;
            }
        }
    }
    
    /**
     * Extended Problem: Sliding Window Median (LC 480)
     * Find median in a sliding window
     */
    static class SlidingWindowMedian {
        private PriorityQueue<Integer> maxHeap;
        private PriorityQueue<Integer> minHeap;
        private Map<Integer, Integer> delayed; // For lazy deletion
        private int maxHeapSize, minHeapSize;
        
        public double[] medianSlidingWindow(int[] nums, int k) {
            maxHeap = new PriorityQueue<>((a, b) -> b.compareTo(a));
            minHeap = new PriorityQueue<>();
            delayed = new HashMap<>();
            maxHeapSize = minHeapSize = 0;
            
            double[] result = new double[nums.length - k + 1];
            
            // Initialize first window
            for (int i = 0; i < k; i++) {
                addNum(nums[i]);
            }
            result[0] = findMedian();
            
            // Slide the window
            for (int i = k; i < nums.length; i++) {
                addNum(nums[i]);
                removeNum(nums[i - k]);
                result[i - k + 1] = findMedian();
            }
            
            return result;
        }
        
        private void addNum(int num) {
            if (maxHeap.isEmpty() || num <= maxHeap.peek()) {
                maxHeap.offer(num);
                maxHeapSize++;
            } else {
                minHeap.offer(num);
                minHeapSize++;
            }
            
            rebalance();
        }
        
        private void removeNum(int num) {
            delayed.put(num, delayed.getOrDefault(num, 0) + 1);
            
            if (num <= maxHeap.peek()) {
                maxHeapSize--;
            } else {
                minHeapSize--;
            }
            
            rebalance();
        }
        
        private void rebalance() {
            if (maxHeapSize > minHeapSize + 1) {
                minHeap.offer(maxHeap.poll());
                maxHeapSize--;
                minHeapSize++;
            } else if (minHeapSize > maxHeapSize) {
                maxHeap.offer(minHeap.poll());
                minHeapSize--;
                maxHeapSize++;
            }
            
            // Clean up delayed elements
            while (!maxHeap.isEmpty() && delayed.getOrDefault(maxHeap.peek(), 0) > 0) {
                int num = maxHeap.poll();
                delayed.put(num, delayed.get(num) - 1);
                if (delayed.get(num) == 0) {
                    delayed.remove(num);
                }
            }
            
            while (!minHeap.isEmpty() && delayed.getOrDefault(minHeap.peek(), 0) > 0) {
                int num = minHeap.poll();
                delayed.put(num, delayed.get(num) - 1);
                if (delayed.get(num) == 0) {
                    delayed.remove(num);
                }
            }
        }
        
        private double findMedian() {
            if (maxHeapSize > minHeapSize) {
                return maxHeap.peek();
            } else {
                return ((long)maxHeap.peek() + minHeap.peek()) / 2.0;
            }
        }
    }
    
    /**
     * Extended Problem: Running Median with Capacity
     * MedianFinder with maximum capacity
     */
    static class MedianFinderWithCapacity {
        private PriorityQueue<Integer> maxHeap;
        private PriorityQueue<Integer> minHeap;
        private int capacity;
        private Queue<Integer> window;
        
        public MedianFinderWithCapacity(int capacity) {
            this.capacity = capacity;
            maxHeap = new PriorityQueue<>((a, b) -> b - a);
            minHeap = new PriorityQueue<>();
            window = new LinkedList<>();
        }
        
        public void addNum(int num) {
            if (window.size() == capacity) {
                int removed = window.poll();
                removeFromHeaps(removed);
            }
            
            window.offer(num);
            
            if (maxHeap.isEmpty() || num <= maxHeap.peek()) {
                maxHeap.offer(num);
            } else {
                minHeap.offer(num);
            }
            
            balance();
        }
        
        private void removeFromHeaps(int num) {
            if (maxHeap.contains(num)) {
                maxHeap.remove(num);
            } else {
                minHeap.remove(num);
            }
        }
        
        private void balance() {
            if (maxHeap.size() > minHeap.size() + 1) {
                minHeap.offer(maxHeap.poll());
            } else if (minHeap.size() > maxHeap.size()) {
                maxHeap.offer(minHeap.poll());
            }
        }
        
        public double findMedian() {
            if (window.isEmpty()) return 0.0;
            
            if (maxHeap.size() > minHeap.size()) {
                return maxHeap.peek();
            } else {
                return (maxHeap.peek() + minHeap.peek()) / 2.0;
            }
        }
    }
    
    // Test cases
    public static void main(String[] args) {
        System.out.println("=== Testing MedianFinder ===");
        
        // Test basic functionality
        MedianFinder mf = new MedianFinder();
        mf.addNum(1);
        mf.addNum(2);
        System.out.println("Median after adding 1, 2: " + mf.findMedian()); // 1.5
        
        mf.addNum(3);
        System.out.println("Median after adding 3: " + mf.findMedian()); // 2.0
        
        // Test optimized version
        System.out.println("\n=== Testing Optimized MedianFinder ===");
        MedianFinderOptimized mfo = new MedianFinderOptimized();
        mfo.addNum(6);
        System.out.println("Median after adding 6: " + mfo.findMedian()); // 6.0
        
        mfo.addNum(10);
        System.out.println("Median after adding 10: " + mfo.findMedian()); // 8.0
        
        mfo.addNum(2);
        System.out.println("Median after adding 2: " + mfo.findMedian()); // 6.0
        
        mfo.addNum(6);
        System.out.println("Median after adding 6: " + mfo.findMedian()); // 6.0
        
        mfo.addNum(5);
        System.out.println("Median after adding 5: " + mfo.findMedian()); // 6.0
        
        // Test sliding window median
        System.out.println("\n=== Testing Sliding Window Median ===");
        SlidingWindowMedian swm = new SlidingWindowMedian();
        int[] nums = {1, 3, -1, -3, 5, 3, 6, 7};
        double[] medians = swm.medianSlidingWindow(nums, 3);
        System.out.println("Sliding window medians: " + Arrays.toString(medians));
        
        // Test capacity-limited median finder
        System.out.println("\n=== Testing MedianFinder with Capacity ===");
        MedianFinderWithCapacity mfc = new MedianFinderWithCapacity(3);
        mfc.addNum(1);
        System.out.println("Median: " + mfc.findMedian()); // 1.0
        
        mfc.addNum(2);
        System.out.println("Median: " + mfc.findMedian()); // 1.5
        
        mfc.addNum(3);
        System.out.println("Median: " + mfc.findMedian()); // 2.0
        
        mfc.addNum(4); // Should remove 1
        System.out.println("Median: " + mfc.findMedian()); // 2.5
    }
} 