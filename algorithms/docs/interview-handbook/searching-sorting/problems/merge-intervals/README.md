# Merge Intervals

## 1. Problem Title
Merge Intervals

## 2. Difficulty Level (Easy / Medium / Hard)
Medium

## 3. Problem Statement
Given an array of intervals where intervals[i] = [start_i, end_i], merge all overlapping intervals and return non-overlapping intervals covering all input intervals.

## 4. Input Format
intervals: 2D integer array.

## 5. Output Format
Return merged 2D integer array.

## 6. Constraints (very important for interviews)
- 1 <= intervals.length <= 10^4
- 0 <= start_i <= end_i <= 10^4

## 7. Example Inputs and Outputs
Example 1
```text
Input: intervals=[[1,3],[2,6],[8,10],[15,18]]
Output: [[1,6],[8,10],[15,18]]
Explanation: [1,3] and [2,6] overlap and merge.
```

## 8. Edge Cases
- Already non-overlapping intervals.
- One interval fully contains others.
- Single interval input.

## 9. Brute Force Approach Explanation
Repeatedly compare every pair and merge until stable; can degrade to O(n^2).

## 10. Optimal Approach Explanation
Sort intervals by start, then scan and merge into output list by comparing current interval with last merged interval.

## 11. Step-by-Step Logic
1. Sort by start time.
2. Initialize merged list with first interval.
3. For each interval, either extend last merged end or append as new interval.

## 12. Time Complexity Analysis
O(n log n) due to sorting.

## 13. Space Complexity Analysis
O(n) for output list.

## 14. Clean and Production-Quality Java Code
```java
import java.util.*;

class Solution {
    public int[][] merge(int[][] intervals) {
        Arrays.sort(intervals, Comparator.comparingInt(a -> a[0]));
        List<int[]> merged = new ArrayList<>();

        for (int[] interval : intervals) {
            if (merged.isEmpty() || merged.get(merged.size() - 1)[1] < interval[0]) {
                merged.add(new int[] {interval[0], interval[1]});
            } else {
                merged.get(merged.size() - 1)[1] = Math.max(
                    merged.get(merged.size() - 1)[1],
                    interval[1]
                );
            }
        }

        return merged.toArray(new int[merged.size()][]);
    }
}
```

## 15. Dry Run Example
Sorted intervals: [1,3],[2,6],[8,10]. Start [1,3], merge with [2,6] -> [1,6], then append [8,10].

## 16. Common Interview Follow-Up Questions
- How would you insert one new interval then merge?
- How to count minimum arrows to burst balloons (interval variant)?

## 17. Alternative Solutions if available
- Line sweep with event points.

## 18. Real Interview Context (why companies ask this problem)
This validates sorting transformation and greedy merge reasoning.
