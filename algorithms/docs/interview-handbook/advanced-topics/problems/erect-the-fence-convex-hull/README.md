# Erect the Fence (Convex Hull)

## 1. Problem Title
Erect the Fence (Convex Hull)

## 2. Difficulty Level (Easy / Medium / Hard)
Hard

## 3. Problem Statement
Given coordinates of trees in a garden, return points on the perimeter of the minimum fence enclosing all trees (including boundary collinear points).

## 4. Input Format
trees: 2D integer array [x,y].

## 5. Output Format
Return list of boundary points in any order.

## 6. Constraints (very important for interviews)
- 1 <= trees.length <= 3000
- 0 <= xi, yi <= 100

## 7. Example Inputs and Outputs
Example 1
```text
Input: trees=[[1,1],[2,2],[2,0],[2,4],[3,3],[4,2]]
Output: [[1,1],[2,0],[4,2],[3,3],[2,4]]
Explanation: Monotonic chain convex hull including collinear boundary points.
```

## 8. Edge Cases
- All points collinear.
- Duplicate points.
- Very small point count.

## 9. Brute Force Approach Explanation
Check each point pair as potential edge and test all points on one side. O(n^3).

## 10. Optimal Approach Explanation
Use Andrew monotonic chain to build lower and upper hull with cross-product orientation checks.

## 11. Step-by-Step Logic
1. Sort points lexicographically.
2. Build lower hull: remove last while making right turn.
3. Build upper hull similarly in reverse order.
4. Combine hull points and deduplicate.

## 12. Time Complexity Analysis
O(n log n) due to sorting.

## 13. Space Complexity Analysis
O(n).

## 14. Clean and Production-Quality Java Code
```java
import java.util.*;

class Solution {
    public int[][] outerTrees(int[][] trees) {
        if (trees.length <= 1) {
            return trees;
        }

        Arrays.sort(trees, (a, b) -> a[0] == b[0] ? Integer.compare(a[1], b[1]) : Integer.compare(a[0], b[0]));
        List<int[]> hull = new ArrayList<>();

        for (int[] p : trees) {
            while (hull.size() >= 2 && cross(hull.get(hull.size() - 2), hull.get(hull.size() - 1), p) < 0) {
                hull.remove(hull.size() - 1);
            }
            hull.add(p);
        }

        int lowerSize = hull.size();
        for (int i = trees.length - 2; i >= 0; i--) {
            int[] p = trees[i];
            while (hull.size() > lowerSize && cross(hull.get(hull.size() - 2), hull.get(hull.size() - 1), p) < 0) {
                hull.remove(hull.size() - 1);
            }
            hull.add(p);
        }

        Set<String> seen = new HashSet<>();
        List<int[]> unique = new ArrayList<>();
        for (int[] p : hull) {
            String key = p[0] + "," + p[1];
            if (seen.add(key)) {
                unique.add(p);
            }
        }

        return unique.toArray(new int[unique.size()][]);
    }

    private int cross(int[] a, int[] b, int[] c) {
        return (b[0] - a[0]) * (c[1] - a[1]) - (b[1] - a[1]) * (c[0] - a[0]);
    }
}
```

## 15. Dry Run Example
Sorted points build lower and upper chains; inward right turns are removed, leaving boundary points.

## 16. Common Interview Follow-Up Questions
- How to compute polygon area after hull construction?
- How to exclude collinear interior points if problem demands strict hull vertices only?

## 17. Alternative Solutions if available
- Graham scan.
- Jarvis march for small hull sizes.

## 18. Real Interview Context (why companies ask this problem)
Geometry appears in high-level rounds to test orientation math and algorithmic rigor.
