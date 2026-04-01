# Meeting Rooms II

## 1. Problem Title
Meeting Rooms II

## 2. Difficulty Level (Easy / Medium / Hard)
Medium

## 3. Problem Statement
Given intervals of meeting time, find minimum number of conference rooms required.

## 4. Input Format
intervals: 2D array where intervals[i] = [start, end].

## 5. Output Format
Return minimum number of rooms as integer.

## 6. Constraints (very important for interviews)
- 1 <= intervals.length <= 10^4
- 0 <= start < end <= 10^6

## 7. Example Inputs and Outputs
Example 1
```text
Input: intervals=[[0,30],[5,10],[15,20]]
Output: 2
Explanation: Two overlapping meetings require two rooms.
```

## 8. Edge Cases
- Non-overlapping meetings -> 1 room.
- Fully overlapping meetings -> n rooms.
- Meetings touching at boundaries.

## 9. Brute Force Approach Explanation
Track overlaps by checking every pair or timeline simulation with large arrays.

## 10. Optimal Approach Explanation
Sort by start time and use min-heap of end times. Reuse room if earliest ending meeting finishes before next starts.

## 11. Step-by-Step Logic
1. Sort meetings by start.
2. For each meeting, if min end <= start, pop heap (reuse room).
3. Push current end; heap size is current rooms used.

## 12. Time Complexity Analysis
O(n log n).

## 13. Space Complexity Analysis
O(n).

## 14. Clean and Production-Quality Java Code
```java
import java.util.*;

class Solution {
    public int minMeetingRooms(int[][] intervals) {
        Arrays.sort(intervals, Comparator.comparingInt(a -> a[0]));
        PriorityQueue<Integer> minEnd = new PriorityQueue<>();

        for (int[] interval : intervals) {
            if (!minEnd.isEmpty() && minEnd.peek() <= interval[0]) {
                minEnd.poll();
            }
            minEnd.offer(interval[1]);
        }

        return minEnd.size();
    }
}
```

## 15. Dry Run Example
Sorted: [0,30],[5,10],[15,20]. Heap after first two => [10,30], third reuses 10-room.

## 16. Common Interview Follow-Up Questions
- Can you output actual room assignment?
- How does sweep-line with starts/ends compare?

## 17. Alternative Solutions if available
- Two sorted arrays starts/ends with two pointers.

## 18. Real Interview Context (why companies ask this problem)
Scheduling + heap reasoning appears in backend and infra interview scenarios.
