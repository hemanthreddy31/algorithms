package leetcode.heap;

import java.util.*;

/**
 * LeetCode 215: Kth Largest Element in an Array
 * 
 * Given an integer array nums and an integer k, return the kth largest element in the array.
 * Note that it is the kth largest element in the sorted order, not the kth distinct element.
 * 
 * Example:
 * Input: nums = [3,2,1,5,6,4], k = 2
 * Output: 5
 * 
 * Input: nums = [3,2,3,1,2,4,5,5,6], k = 4
 * Output: 4
 */
public class KthLargestElement {
    
    /**
     * Approach 1: Sorting
     * Time Complexity: O(n log n) - Sorting dominates
     * Space Complexity: O(1) - In-place sorting
     * 
     * Simple but not optimal for large arrays
     */
    public int findKthLargestSorting(int[] nums, int k) {
        Arrays.sort(nums);
        return nums[nums.length - k];
    }
    
    /**
     * Approach 2: Min Heap of Size K (Optimal for small k)
     * Time Complexity: O(n log k) - Heap operations
     * Space Complexity: O(k) - Heap size
     * 
     * Algorithm:
     * 1. Maintain a min heap of size k
     * 2. The root of heap is the kth largest element
     * 3. If incoming element is larger than root, replace root
     */
    public int findKthLargestMinHeap(int[] nums, int k) {
        // Min heap with custom comparator (or use default)
        PriorityQueue<Integer> minHeap = new PriorityQueue<>();
        
        for (int num : nums) {
            minHeap.offer(num);
            
            // Keep heap size at most k
            if (minHeap.size() > k) {
                minHeap.poll(); // Remove smallest
            }
        }
        
        // The root is the kth largest
        return minHeap.peek();
    }
    
    /**
     * Approach 3: Max Heap (Optimal for large k)
     * Time Complexity: O(n + k log n) - Build heap + k extractions
     * Space Complexity: O(n) - Heap size
     * 
     * Algorithm:
     * 1. Build a max heap with all elements
     * 2. Extract max k-1 times
     * 3. The root is now the kth largest
     */
    public int findKthLargestMaxHeap(int[] nums, int k) {
        // Max heap
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>(Collections.reverseOrder());
        
        // Add all elements
        for (int num : nums) {
            maxHeap.offer(num);
        }
        
        // Extract k-1 elements
        for (int i = 0; i < k - 1; i++) {
            maxHeap.poll();
        }
        
        return maxHeap.peek();
    }
    
    /**
     * Approach 4: QuickSelect (Optimal Average Case)
     * Time Complexity: O(n) average, O(n²) worst case
     * Space Complexity: O(1) - In-place partitioning
     * 
     * Algorithm:
     * 1. Based on QuickSort's partition
     * 2. Only recurse on the side containing kth element
     * 3. Stop when pivot is at position n-k
     */
    public int findKthLargestQuickSelect(int[] nums, int k) {
        int n = nums.length;
        int targetIndex = n - k; // Convert to kth smallest
        
        int left = 0, right = n - 1;
        
        while (left <= right) {
            int pivotIndex = partition(nums, left, right);
            
            if (pivotIndex == targetIndex) {
                return nums[pivotIndex];
            } else if (pivotIndex < targetIndex) {
                left = pivotIndex + 1;
            } else {
                right = pivotIndex - 1;
            }
        }
        
        return -1; // Should never reach here
    }
    
    private int partition(int[] nums, int left, int right) {
        // Choose random pivot for better average case
        int randomIndex = left + new Random().nextInt(right - left + 1);
        swap(nums, randomIndex, right);
        
        int pivot = nums[right];
        int i = left - 1; // Pointer for smaller elements
        
        for (int j = left; j < right; j++) {
            if (nums[j] < pivot) {
                i++;
                swap(nums, i, j);
            }
        }
        
        swap(nums, i + 1, right);
        return i + 1;
    }
    
    private void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
    
    /**
     * Approach 5: Counting Sort (For limited range)
     * Time Complexity: O(n + range) - Where range is max - min
     * Space Complexity: O(range)
     * 
     * Efficient when the range of values is small
     */
    public int findKthLargestCounting(int[] nums, int k) {
        int min = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;
        
        // Find range
        for (int num : nums) {
            min = Math.min(min, num);
            max = Math.max(max, num);
        }
        
        // Count occurrences
        int range = max - min + 1;
        int[] count = new int[range];
        
        for (int num : nums) {
            count[num - min]++;
        }
        
        // Find kth largest
        int remaining = k;
        for (int i = range - 1; i >= 0; i--) {
            remaining -= count[i];
            if (remaining <= 0) {
                return i + min;
            }
        }
        
        return -1;
    }
    
    /**
     * Approach 6: Median of Medians (Guaranteed O(n))
     * Time Complexity: O(n) - Worst case guaranteed
     * Space Complexity: O(log n) - Recursion
     * 
     * More complex but guarantees linear time
     */
    public int findKthLargestMedianOfMedians(int[] nums, int k) {
        return selectKth(nums, 0, nums.length - 1, nums.length - k);
    }
    
    private int selectKth(int[] nums, int left, int right, int k) {
        if (left == right) {
            return nums[left];
        }
        
        int pivotIndex = medianOfMediansPivot(nums, left, right);
        pivotIndex = partitionAroundPivot(nums, left, right, pivotIndex);
        
        if (k == pivotIndex) {
            return nums[k];
        } else if (k < pivotIndex) {
            return selectKth(nums, left, pivotIndex - 1, k);
        } else {
            return selectKth(nums, pivotIndex + 1, right, k);
        }
    }
    
    private int medianOfMediansPivot(int[] nums, int left, int right) {
        if (right - left < 5) {
            return partitionAndGetMedian(nums, left, right);
        }
        
        // Divide into groups of 5
        for (int i = left; i <= right; i += 5) {
            int subRight = Math.min(i + 4, right);
            int median5 = partitionAndGetMedian(nums, i, subRight);
            swap(nums, median5, left + (i - left) / 5);
        }
        
        int mid = (right - left) / 10 + left + 1;
        return selectKth(nums, left, left + (right - left) / 5, mid);
    }
    
    private int partitionAndGetMedian(int[] nums, int left, int right) {
        // Simple insertion sort for small arrays
        for (int i = left + 1; i <= right; i++) {
            for (int j = i; j > left && nums[j - 1] > nums[j]; j--) {
                swap(nums, j, j - 1);
            }
        }
        return (left + right) / 2;
    }
    
    private int partitionAroundPivot(int[] nums, int left, int right, int pivotIndex) {
        int pivotValue = nums[pivotIndex];
        swap(nums, pivotIndex, right);
        
        int storeIndex = left;
        for (int i = left; i < right; i++) {
            if (nums[i] < pivotValue) {
                swap(nums, storeIndex, i);
                storeIndex++;
            }
        }
        
        swap(nums, storeIndex, right);
        return storeIndex;
    }
    
    /**
     * Extended Problem: Find K Largest Elements
     * Return the k largest elements (in any order)
     */
    public int[] findKLargest(int[] nums, int k) {
        PriorityQueue<Integer> minHeap = new PriorityQueue<>();
        
        for (int num : nums) {
            minHeap.offer(num);
            if (minHeap.size() > k) {
                minHeap.poll();
            }
        }
        
        int[] result = new int[k];
        for (int i = 0; i < k; i++) {
            result[i] = minHeap.poll();
        }
        
        return result;
    }
    
    // Test the solutions
    public static void main(String[] args) {
        KthLargestElement solution = new KthLargestElement();
        
        // Test case 1
        int[] nums1 = {3, 2, 1, 5, 6, 4};
        int k1 = 2;
        System.out.println("Test Case 1: nums = [3,2,1,5,6,4], k = 2");
        System.out.println("Sorting: " + solution.findKthLargestSorting(nums1.clone(), k1));
        System.out.println("Min Heap: " + solution.findKthLargestMinHeap(nums1.clone(), k1));
        System.out.println("Max Heap: " + solution.findKthLargestMaxHeap(nums1.clone(), k1));
        System.out.println("QuickSelect: " + solution.findKthLargestQuickSelect(nums1.clone(), k1));
        
        // Test case 2: With duplicates
        int[] nums2 = {3, 2, 3, 1, 2, 4, 5, 5, 6};
        int k2 = 4;
        System.out.println("\nTest Case 2: nums = [3,2,3,1,2,4,5,5,6], k = 4");
        System.out.println("Min Heap: " + solution.findKthLargestMinHeap(nums2.clone(), k2));
        System.out.println("QuickSelect: " + solution.findKthLargestQuickSelect(nums2.clone(), k2));
        
        // Test case 3: Small array
        int[] nums3 = {1};
        int k3 = 1;
        System.out.println("\nTest Case 3: nums = [1], k = 1");
        System.out.println("Min Heap: " + solution.findKthLargestMinHeap(nums3.clone(), k3));
        
        // Test k largest elements
        int[] nums4 = {3, 2, 1, 5, 6, 4};
        int k4 = 3;
        System.out.println("\nTest Case 4: Find 3 largest elements");
        System.out.println("K Largest: " + Arrays.toString(solution.findKLargest(nums4, k4)));
    }
} 