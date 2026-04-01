# Number of Islands

## 1. Problem Title
Number of Islands

## 2. Difficulty Level (Easy / Medium / Hard)
Medium

## 3. Problem Statement
Given an m x n 2D binary grid of '1's (land) and '0's (water), return the number of islands.

## 4. Input Format
grid: 2D char matrix.

## 5. Output Format
Return integer count of connected components of land (4-directional).

## 6. Constraints (very important for interviews)
- 1 <= m, n <= 300
- grid[i][j] is '0' or '1'

## 7. Example Inputs and Outputs
Example 1
```text
Input: grid=[['1','1','0'],['0','1','0'],['1','0','1']]
Output: 3
Explanation: Three disconnected land components.
```

## 8. Edge Cases
- All water => 0.
- All land => 1.
- Single cell grid.

## 9. Brute Force Approach Explanation
For each land cell, repeatedly search all cells to find connected cells (inefficient).

## 10. Optimal Approach Explanation
Traverse grid; when hitting unvisited land, run DFS/BFS flood-fill and increment island count.

## 11. Step-by-Step Logic
1. Loop through every cell.
2. If cell is land, increment count and DFS/BFS mark all connected land as visited.
3. Continue until all cells processed.

## 12. Time Complexity Analysis
O(m*n).

## 13. Space Complexity Analysis
O(m*n) worst-case recursion/queue.

## 14. Clean and Production-Quality Java Code
```java
class Solution {
    public int numIslands(char[][] grid) {
        int rows = grid.length;
        int cols = grid[0].length;
        int islands = 0;

        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                if (grid[r][c] == '1') {
                    islands++;
                    dfs(grid, r, c);
                }
            }
        }

        return islands;
    }

    private void dfs(char[][] grid, int r, int c) {
        if (r < 0 || c < 0 || r >= grid.length || c >= grid[0].length || grid[r][c] != '1') {
            return;
        }

        grid[r][c] = '0';
        dfs(grid, r + 1, c);
        dfs(grid, r - 1, c);
        dfs(grid, r, c + 1);
        dfs(grid, r, c - 1);
    }
}
```

## 15. Dry Run Example
Encounter first '1', DFS clears its component; next untouched '1' starts another island.

## 16. Common Interview Follow-Up Questions
- How to solve without modifying input grid?
- How to count distinct island shapes?

## 17. Alternative Solutions if available
- BFS flood fill.
- Union-Find on grid cells.

## 18. Real Interview Context (why companies ask this problem)
Foundational graph traversal over implicit grid graph.
