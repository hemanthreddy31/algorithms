# Course Schedule

## 1. Problem Title
Course Schedule

## 2. Difficulty Level (Easy / Medium / Hard)
Medium

## 3. Problem Statement
There are numCourses courses labeled 0..numCourses-1. Given prerequisites pairs [a,b] (take b before a), determine if all courses can be finished.

## 4. Input Format
numCourses: integer, prerequisites: 2D integer array.

## 5. Output Format
Return true if DAG (no cycle), else false.

## 6. Constraints (very important for interviews)
- 1 <= numCourses <= 2000
- 0 <= prerequisites.length <= 5000

## 7. Example Inputs and Outputs
Example 1
```text
Input: numCourses=2, prerequisites=[[1,0]]
Output: true
Explanation: Order 0->1 is valid.
```
Example 2
```text
Input: numCourses=2, prerequisites=[[1,0],[0,1]]
Output: false
Explanation: Cycle exists.
```

## 8. Edge Cases
- No prerequisites.
- Disconnected graph components.
- Self dependency.

## 9. Brute Force Approach Explanation
Try all permutations/topological orders explicitly (infeasible).

## 10. Optimal Approach Explanation
Use Kahn's algorithm (BFS topological sort). If processed count equals numCourses, no cycle.

## 11. Step-by-Step Logic
1. Build adjacency list and indegree array.
2. Push all indegree-0 nodes into queue.
3. Pop queue, reduce indegree of neighbors, enqueue new zeros.
4. Compare processed count with numCourses.

## 12. Time Complexity Analysis
O(V + E).

## 13. Space Complexity Analysis
O(V + E).

## 14. Clean and Production-Quality Java Code
```java
import java.util.*;

class Solution {
    public boolean canFinish(int numCourses, int[][] prerequisites) {
        List<List<Integer>> graph = new ArrayList<>();
        for (int i = 0; i < numCourses; i++) {
            graph.add(new ArrayList<>());
        }

        int[] indegree = new int[numCourses];
        for (int[] pre : prerequisites) {
            int course = pre[0];
            int need = pre[1];
            graph.get(need).add(course);
            indegree[course]++;
        }

        Queue<Integer> queue = new ArrayDeque<>();
        for (int i = 0; i < numCourses; i++) {
            if (indegree[i] == 0) {
                queue.offer(i);
            }
        }

        int taken = 0;
        while (!queue.isEmpty()) {
            int node = queue.poll();
            taken++;
            for (int next : graph.get(node)) {
                if (--indegree[next] == 0) {
                    queue.offer(next);
                }
            }
        }

        return taken == numCourses;
    }
}
```

## 15. Dry Run Example
Queue starts [0], process 0 lowers indegree(1) to 0, process 1 => taken=2.

## 16. Common Interview Follow-Up Questions
- How to return one valid course order?
- How to detect cycle using DFS colors?

## 17. Alternative Solutions if available
- DFS with visiting/visited states.

## 18. Real Interview Context (why companies ask this problem)
Critical DAG ordering and cycle detection pattern used in dependency systems.
