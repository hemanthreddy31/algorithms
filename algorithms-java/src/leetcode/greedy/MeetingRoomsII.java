package leetcode.greedy;

import java.util.*;

/**
 * LeetCode 253: Meeting Rooms II
 * 
 * Given an array of meeting time intervals where intervals[i] = [starti, endi],
 * return the minimum number of conference rooms required.
 * 
 * Example:
 * Input: intervals = [[0,30],[5,10],[15,20]]
 * Output: 2
 */
public class MeetingRoomsII {
    
    /**
     * Approach 1: Priority Queue (Min Heap)
     * Time: O(n log n), Space: O(n)
     * 
     * Algorithm:
     * 1. Sort meetings by start time
     * 2. Use min heap to track end times of ongoing meetings
     * 3. For each meeting, remove finished meetings from heap
     * 4. Add current meeting's end time to heap
     * 5. Heap size represents rooms needed at any point
     */
    public int minMeetingRooms(int[][] intervals) {
        if (intervals == null || intervals.length == 0) {
            return 0;
        }
        
        // Sort by start time
        Arrays.sort(intervals, (a, b) -> Integer.compare(a[0], b[0]));
        
        // Min heap to store end times of ongoing meetings
        PriorityQueue<Integer> heap = new PriorityQueue<>();
        
        for (int[] interval : intervals) {
            int start = interval[0];
            int end = interval[1];
            
            // Remove all meetings that have ended before current meeting starts
            while (!heap.isEmpty() && heap.peek() <= start) {
                heap.poll();
            }
            
            // Add current meeting's end time
            heap.offer(end);
        }
        
        return heap.size();
    }
    
    /**
     * Approach 2: Two Pointers (Sweep Line)
     * Time: O(n log n), Space: O(n)
     * 
     * Algorithm:
     * 1. Create separate arrays for start and end times
     * 2. Sort both arrays
     * 3. Use two pointers to simulate timeline
     * 4. Count rooms needed at each time point
     */
    public int minMeetingRoomsSweepLine(int[][] intervals) {
        if (intervals == null || intervals.length == 0) {
            return 0;
        }
        
        int n = intervals.length;
        int[] starts = new int[n];
        int[] ends = new int[n];
        
        // Extract start and end times
        for (int i = 0; i < n; i++) {
            starts[i] = intervals[i][0];
            ends[i] = intervals[i][1];
        }
        
        // Sort both arrays
        Arrays.sort(starts);
        Arrays.sort(ends);
        
        int rooms = 0;
        int maxRooms = 0;
        int startPtr = 0, endPtr = 0;
        
        // Sweep through timeline
        while (startPtr < n) {
            if (starts[startPtr] < ends[endPtr]) {
                // Meeting starts, need a room
                rooms++;
                startPtr++;
            } else {
                // Meeting ends, free a room
                rooms--;
                endPtr++;
            }
            
            maxRooms = Math.max(maxRooms, rooms);
        }
        
        return maxRooms;
    }
    
    /**
     * Approach 3: Event-based Sorting
     * Time: O(n log n), Space: O(n)
     */
    public int minMeetingRoomsEvents(int[][] intervals) {
        List<int[]> events = new ArrayList<>();
        
        // Create events: [time, type] where type: 1 = start, -1 = end
        for (int[] interval : intervals) {
            events.add(new int[]{interval[0], 1});  // Meeting starts
            events.add(new int[]{interval[1], -1}); // Meeting ends
        }
        
        // Sort events by time, if same time then end events first
        events.sort((a, b) -> {
            if (a[0] == b[0]) {
                return Integer.compare(a[1], b[1]); // End events (-1) before start events (1)
            }
            return Integer.compare(a[0], b[0]);
        });
        
        int rooms = 0;
        int maxRooms = 0;
        
        for (int[] event : events) {
            rooms += event[1];
            maxRooms = Math.max(maxRooms, rooms);
        }
        
        return maxRooms;
    }
    
    /**
     * Extended Problem: Meeting Rooms I (LC 252)
     * Check if a person can attend all meetings
     */
    public boolean canAttendMeetings(int[][] intervals) {
        if (intervals == null || intervals.length <= 1) {
            return true;
        }
        
        Arrays.sort(intervals, (a, b) -> Integer.compare(a[0], b[0]));
        
        for (int i = 1; i < intervals.length; i++) {
            if (intervals[i][0] < intervals[i-1][1]) {
                return false; // Overlap found
            }
        }
        
        return true;
    }
    
    /**
     * Extended Problem: Meeting Scheduler (LC 1229)
     * Find common free time slots
     */
    public List<Integer> minAvailableDuration(int[][] slots1, int[][] slots2, int duration) {
        Arrays.sort(slots1, (a, b) -> Integer.compare(a[0], b[0]));
        Arrays.sort(slots2, (a, b) -> Integer.compare(a[0], b[0]));
        
        int i = 0, j = 0;
        
        while (i < slots1.length && j < slots2.length) {
            int start = Math.max(slots1[i][0], slots2[j][0]);
            int end = Math.min(slots1[i][1], slots2[j][1]);
            
            if (start + duration <= end) {
                return Arrays.asList(start, start + duration);
            }
            
            // Move pointer of slot that ends earlier
            if (slots1[i][1] < slots2[j][1]) {
                i++;
            } else {
                j++;
            }
        }
        
        return new ArrayList<>();
    }
    
    /**
     * Extended Problem: Employee Free Time (LC 759)
     * Find common free time for all employees
     */
    public List<int[]> employeeFreeTime(List<List<int[]>> schedule) {
        List<int[]> intervals = new ArrayList<>();
        
        // Flatten all intervals
        for (List<int[]> employee : schedule) {
            intervals.addAll(employee);
        }
        
        // Sort by start time
        intervals.sort((a, b) -> Integer.compare(a[0], b[0]));
        
        List<int[]> merged = new ArrayList<>();
        merged.add(intervals.get(0));
        
        // Merge overlapping intervals
        for (int i = 1; i < intervals.size(); i++) {
            int[] current = intervals.get(i);
            int[] last = merged.get(merged.size() - 1);
            
            if (current[0] <= last[1]) {
                last[1] = Math.max(last[1], current[1]);
            } else {
                merged.add(current);
            }
        }
        
        // Find gaps between merged intervals
        List<int[]> freeTime = new ArrayList<>();
        for (int i = 1; i < merged.size(); i++) {
            freeTime.add(new int[]{merged.get(i-1)[1], merged.get(i)[0]});
        }
        
        return freeTime;
    }
    
    /**
     * Extended Problem: Car Pooling (LC 1094)
     * Similar interval problem with capacity constraints
     */
    public boolean carPooling(int[][] trips, int capacity) {
        // Use sweep line algorithm
        Map<Integer, Integer> timeline = new TreeMap<>();
        
        for (int[] trip : trips) {
            int passengers = trip[0];
            int start = trip[1];
            int end = trip[2];
            
            timeline.put(start, timeline.getOrDefault(start, 0) + passengers);
            timeline.put(end, timeline.getOrDefault(end, 0) - passengers);
        }
        
        int currentPassengers = 0;
        for (int change : timeline.values()) {
            currentPassengers += change;
            if (currentPassengers > capacity) {
                return false;
            }
        }
        
        return true;
    }
    
    // Test cases
    public static void main(String[] args) {
        MeetingRoomsII solution = new MeetingRoomsII();
        
        // Test case 1: Basic example
        int[][] intervals1 = {{0, 30}, {5, 10}, {15, 20}};
        System.out.println("Test 1 - Min meeting rooms: " + solution.minMeetingRooms(intervals1));
        System.out.println("Sweep line approach: " + solution.minMeetingRoomsSweepLine(intervals1));
        System.out.println("Events approach: " + solution.minMeetingRoomsEvents(intervals1));
        
        // Test case 2: No overlaps
        int[][] intervals2 = {{7, 10}, {2, 4}};
        System.out.println("\nTest 2 - Min meeting rooms: " + solution.minMeetingRooms(intervals2));
        
        // Test case 3: All overlap
        int[][] intervals3 = {{1, 5}, {2, 6}, {3, 7}};
        System.out.println("\nTest 3 - Min meeting rooms: " + solution.minMeetingRooms(intervals3));
        
        // Test Meeting Rooms I
        System.out.println("\nCan attend all meetings (intervals1): " + solution.canAttendMeetings(intervals1));
        System.out.println("Can attend all meetings (intervals2): " + solution.canAttendMeetings(intervals2));
        
        // Test car pooling
        int[][] trips = {{2, 1, 5}, {3, 3, 7}};
        System.out.println("\nCar pooling possible (capacity 4): " + solution.carPooling(trips, 4));
        System.out.println("Car pooling possible (capacity 5): " + solution.carPooling(trips, 5));
    }
} 