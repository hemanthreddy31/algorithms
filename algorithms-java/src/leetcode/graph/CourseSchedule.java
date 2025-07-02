package leetcode.graph;

import java.util.*;

/**
 * LeetCode 207: Course Schedule
 * 
 * There are a total of numCourses courses you have to take, labeled from 0 to numCourses - 1.
 * You are given an array prerequisites where prerequisites[i] = [ai, bi] indicates that 
 * you must take course bi first if you want to take course ai.
 * 
 * Return true if you can finish all courses. Otherwise, return false.
 * 
 * Example:
 * Input: numCourses = 2, prerequisites = [[1,0]]
 * Output: true (take course 0, then course 1)
 * 
 * Input: numCourses = 2, prerequisites = [[1,0],[0,1]]
 * Output: false (circular dependency)
 */
public class CourseSchedule {
    
    /**
     * Approach 1: Kahn's Algorithm (BFS Topological Sort)
     * Time Complexity: O(V + E) - Visit each vertex and edge once
     * Space Complexity: O(V + E) - Adjacency list and queue
     * 
     * Algorithm:
     * 1. Build adjacency list and calculate in-degrees
     * 2. Add all nodes with in-degree 0 to queue
     * 3. Process queue: remove node, decrease neighbors' in-degrees
     * 4. If all nodes processed, no cycle exists
     */
    public boolean canFinishKahn(int numCourses, int[][] prerequisites) {
        // Build adjacency list
        List<List<Integer>> graph = new ArrayList<>();
        for (int i = 0; i < numCourses; i++) {
            graph.add(new ArrayList<>());
        }
        
        // Calculate in-degrees
        int[] inDegree = new int[numCourses];
        for (int[] prereq : prerequisites) {
            int course = prereq[0];
            int prerequisite = prereq[1];
            graph.get(prerequisite).add(course);
            inDegree[course]++;
        }
        
        // Initialize queue with nodes having in-degree 0
        Queue<Integer> queue = new LinkedList<>();
        for (int i = 0; i < numCourses; i++) {
            if (inDegree[i] == 0) {
                queue.offer(i);
            }
        }
        
        int processedCourses = 0;
        
        // Process the queue
        while (!queue.isEmpty()) {
            int current = queue.poll();
            processedCourses++;
            
            // Reduce in-degree of neighbors
            for (int neighbor : graph.get(current)) {
                inDegree[neighbor]--;
                if (inDegree[neighbor] == 0) {
                    queue.offer(neighbor);
                }
            }
        }
        
        // If all courses processed, no cycle exists
        return processedCourses == numCourses;
    }
    
    /**
     * Approach 2: DFS Cycle Detection
     * Time Complexity: O(V + E) - Visit each vertex and edge once
     * Space Complexity: O(V + E) - Adjacency list and recursion stack
     * 
     * Algorithm:
     * 1. Build adjacency list
     * 2. Use DFS to detect cycles using three states:
     *    - WHITE (0): unvisited
     *    - GRAY (1): visiting (in current path)
     *    - BLACK (2): visited (completed)
     * 3. If we encounter a GRAY node, cycle detected
     */
    public boolean canFinishDFS(int numCourses, int[][] prerequisites) {
        // Build adjacency list
        List<List<Integer>> graph = new ArrayList<>();
        for (int i = 0; i < numCourses; i++) {
            graph.add(new ArrayList<>());
        }
        
        for (int[] prereq : prerequisites) {
            int course = prereq[0];
            int prerequisite = prereq[1];
            graph.get(prerequisite).add(course);
        }
        
        // 0: WHITE (unvisited), 1: GRAY (visiting), 2: BLACK (visited)
        int[] color = new int[numCourses];
        
        // Check each node for cycles
        for (int i = 0; i < numCourses; i++) {
            if (color[i] == 0 && hasCycle(graph, color, i)) {
                return false;
            }
        }
        
        return true;
    }
    
    private boolean hasCycle(List<List<Integer>> graph, int[] color, int node) {
        // Mark as visiting (GRAY)
        color[node] = 1;
        
        for (int neighbor : graph.get(node)) {
            if (color[neighbor] == 1) {
                // Back edge found - cycle detected
                return true;
            }
            if (color[neighbor] == 0 && hasCycle(graph, color, neighbor)) {
                return true;
            }
        }
        
        // Mark as visited (BLACK)
        color[node] = 2;
        return false;
    }
    
    /**
     * Extended Problem: Course Schedule II (Return the order)
     * LeetCode 210: Return one valid ordering of courses
     */
    public int[] findOrderKahn(int numCourses, int[][] prerequisites) {
        List<List<Integer>> graph = new ArrayList<>();
        for (int i = 0; i < numCourses; i++) {
            graph.add(new ArrayList<>());
        }
        
        int[] inDegree = new int[numCourses];
        for (int[] prereq : prerequisites) {
            graph.get(prereq[1]).add(prereq[0]);
            inDegree[prereq[0]]++;
        }
        
        Queue<Integer> queue = new LinkedList<>();
        for (int i = 0; i < numCourses; i++) {
            if (inDegree[i] == 0) {
                queue.offer(i);
            }
        }
        
        int[] result = new int[numCourses];
        int index = 0;
        
        while (!queue.isEmpty()) {
            int current = queue.poll();
            result[index++] = current;
            
            for (int neighbor : graph.get(current)) {
                inDegree[neighbor]--;
                if (inDegree[neighbor] == 0) {
                    queue.offer(neighbor);
                }
            }
        }
        
        return index == numCourses ? result : new int[0];
    }
    
    /**
     * Extended Problem: Course Schedule II using DFS
     */
    public int[] findOrderDFS(int numCourses, int[][] prerequisites) {
        List<List<Integer>> graph = new ArrayList<>();
        for (int i = 0; i < numCourses; i++) {
            graph.add(new ArrayList<>());
        }
        
        for (int[] prereq : prerequisites) {
            graph.get(prereq[1]).add(prereq[0]);
        }
        
        int[] color = new int[numCourses];
        Stack<Integer> stack = new Stack<>();
        
        for (int i = 0; i < numCourses; i++) {
            if (color[i] == 0 && hasCycleDFS(graph, color, i, stack)) {
                return new int[0]; // Cycle detected
            }
        }
        
        int[] result = new int[numCourses];
        for (int i = 0; i < numCourses; i++) {
            result[i] = stack.pop();
        }
        
        return result;
    }
    
    private boolean hasCycleDFS(List<List<Integer>> graph, int[] color, int node, Stack<Integer> stack) {
        color[node] = 1; // GRAY
        
        for (int neighbor : graph.get(node)) {
            if (color[neighbor] == 1 || 
                (color[neighbor] == 0 && hasCycleDFS(graph, color, neighbor, stack))) {
                return true;
            }
        }
        
        color[node] = 2; // BLACK
        stack.push(node);
        return false;
    }
    
    /**
     * Extended Problem: Minimum Course Schedule Length
     * Find minimum number of semesters needed
     */
    public int minimumSemesters(int numCourses, int[][] prerequisites) {
        List<List<Integer>> graph = new ArrayList<>();
        for (int i = 0; i <= numCourses; i++) {
            graph.add(new ArrayList<>());
        }
        
        int[] inDegree = new int[numCourses + 1];
        for (int[] prereq : prerequisites) {
            graph.get(prereq[1]).add(prereq[0]);
            inDegree[prereq[0]]++;
        }
        
        Queue<Integer> queue = new LinkedList<>();
        for (int i = 1; i <= numCourses; i++) {
            if (inDegree[i] == 0) {
                queue.offer(i);
            }
        }
        
        int semesters = 0;
        int studiedCourses = 0;
        
        while (!queue.isEmpty()) {
            int size = queue.size();
            semesters++;
            
            for (int i = 0; i < size; i++) {
                int current = queue.poll();
                studiedCourses++;
                
                for (int neighbor : graph.get(current)) {
                    inDegree[neighbor]--;
                    if (inDegree[neighbor] == 0) {
                        queue.offer(neighbor);
                    }
                }
            }
        }
        
        return studiedCourses == numCourses ? semesters : -1;
    }
    
    /**
     * Helper method to print graph
     */
    private static void printGraph(List<List<Integer>> graph) {
        for (int i = 0; i < graph.size(); i++) {
            System.out.println("Node " + i + ": " + graph.get(i));
        }
    }
    
    // Test the solutions
    public static void main(String[] args) {
        CourseSchedule solution = new CourseSchedule();
        
        // Test case 1: No cycle
        int numCourses1 = 2;
        int[][] prerequisites1 = {{1, 0}};
        
        System.out.println("Test Case 1: numCourses = " + numCourses1);
        System.out.println("Prerequisites: " + Arrays.deepToString(prerequisites1));
        System.out.println("Can finish (Kahn): " + solution.canFinishKahn(numCourses1, prerequisites1));
        System.out.println("Can finish (DFS): " + solution.canFinishDFS(numCourses1, prerequisites1));
        System.out.println("Course order (Kahn): " + Arrays.toString(solution.findOrderKahn(numCourses1, prerequisites1)));
        
        // Test case 2: Cycle exists
        int numCourses2 = 2;
        int[][] prerequisites2 = {{1, 0}, {0, 1}};
        
        System.out.println("\nTest Case 2: numCourses = " + numCourses2);
        System.out.println("Prerequisites: " + Arrays.deepToString(prerequisites2));
        System.out.println("Can finish (Kahn): " + solution.canFinishKahn(numCourses2, prerequisites2));
        System.out.println("Can finish (DFS): " + solution.canFinishDFS(numCourses2, prerequisites2));
        System.out.println("Course order (Kahn): " + Arrays.toString(solution.findOrderKahn(numCourses2, prerequisites2)));
        
        // Test case 3: Complex case
        int numCourses3 = 4;
        int[][] prerequisites3 = {{1, 0}, {2, 0}, {3, 1}, {3, 2}};
        
        System.out.println("\nTest Case 3: numCourses = " + numCourses3);
        System.out.println("Prerequisites: " + Arrays.deepToString(prerequisites3));
        System.out.println("Can finish (Kahn): " + solution.canFinishKahn(numCourses3, prerequisites3));
        System.out.println("Can finish (DFS): " + solution.canFinishDFS(numCourses3, prerequisites3));
        System.out.println("Course order (Kahn): " + Arrays.toString(solution.findOrderKahn(numCourses3, prerequisites3)));
        System.out.println("Course order (DFS): " + Arrays.toString(solution.findOrderDFS(numCourses3, prerequisites3)));
        
        // Test case 4: No prerequisites
        int numCourses4 = 3;
        int[][] prerequisites4 = {};
        
        System.out.println("\nTest Case 4: numCourses = " + numCourses4);
        System.out.println("Prerequisites: " + Arrays.deepToString(prerequisites4));
        System.out.println("Can finish (Kahn): " + solution.canFinishKahn(numCourses4, prerequisites4));
        System.out.println("Course order: " + Arrays.toString(solution.findOrderKahn(numCourses4, prerequisites4)));
        
        // Test extended problem
        System.out.println("\nExtended Problem - Minimum Semesters:");
        int[][] semesterPrereqs = {{1, 2}, {1, 3}, {7, 1}, {4, 7}, {5, 7}, {6, 4}, {6, 5}};
        System.out.println("Minimum semesters for 7 courses: " + solution.minimumSemesters(7, semesterPrereqs));
    }
} 