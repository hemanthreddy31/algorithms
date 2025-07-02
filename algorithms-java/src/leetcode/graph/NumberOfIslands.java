package leetcode.graph;

import java.util.*;

/**
 * LeetCode 200: Number of Islands
 * 
 * Given an m x n 2D binary grid which represents a map of '1's (land) and '0's (water),
 * return the number of islands.
 * 
 * An island is surrounded by water and is formed by connecting adjacent lands horizontally
 * or vertically. You may assume all four edges of the grid are all surrounded by water.
 * 
 * Example:
 * Input: grid = [
 *   ["1","1","1","1","0"],
 *   ["1","1","0","1","0"],
 *   ["1","1","0","0","0"],
 *   ["0","0","0","0","0"]
 * ]
 * Output: 1
 */
public class NumberOfIslands {
    
    /**
     * Approach 1: DFS (Depth-First Search)
     * Time Complexity: O(m*n) - Visit each cell once
     * Space Complexity: O(m*n) - Recursion stack in worst case
     * 
     * Algorithm:
     * 1. Iterate through each cell in the grid
     * 2. When we find a '1', increment island count and perform DFS
     * 3. During DFS, mark all connected land cells as visited
     */
    public int numIslandsDFS(char[][] grid) {
        if (grid == null || grid.length == 0) {
            return 0;
        }
        
        int rows = grid.length;
        int cols = grid[0].length;
        int numIslands = 0;
        
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (grid[i][j] == '1') {
                    numIslands++;
                    dfs(grid, i, j);
                }
            }
        }
        
        return numIslands;
    }
    
    private void dfs(char[][] grid, int row, int col) {
        int rows = grid.length;
        int cols = grid[0].length;
        
        // Boundary check and water check
        if (row < 0 || row >= rows || col < 0 || col >= cols || grid[row][col] == '0') {
            return;
        }
        
        // Mark current cell as visited by setting it to '0'
        grid[row][col] = '0';
        
        // Visit all 4 adjacent cells
        dfs(grid, row - 1, col); // up
        dfs(grid, row + 1, col); // down
        dfs(grid, row, col - 1); // left
        dfs(grid, row, col + 1); // right
    }
    
    /**
     * Approach 2: BFS (Breadth-First Search)
     * Time Complexity: O(m*n) - Visit each cell once
     * Space Complexity: O(min(m,n)) - Queue size
     * 
     * Algorithm:
     * 1. Similar to DFS but use a queue for BFS
     * 2. Add all connected land cells to queue
     * 3. Process queue until empty
     */
    public int numIslandsBFS(char[][] grid) {
        if (grid == null || grid.length == 0) {
            return 0;
        }
        
        int rows = grid.length;
        int cols = grid[0].length;
        int numIslands = 0;
        
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (grid[i][j] == '1') {
                    numIslands++;
                    bfs(grid, i, j);
                }
            }
        }
        
        return numIslands;
    }
    
    private void bfs(char[][] grid, int row, int col) {
        int rows = grid.length;
        int cols = grid[0].length;
        
        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[]{row, col});
        grid[row][col] = '0'; // Mark as visited
        
        // Directions: up, down, left, right
        int[][] directions = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
        
        while (!queue.isEmpty()) {
            int[] current = queue.poll();
            
            for (int[] dir : directions) {
                int newRow = current[0] + dir[0];
                int newCol = current[1] + dir[1];
                
                if (newRow >= 0 && newRow < rows && newCol >= 0 && newCol < cols 
                    && grid[newRow][newCol] == '1') {
                    queue.offer(new int[]{newRow, newCol});
                    grid[newRow][newCol] = '0'; // Mark as visited
                }
            }
        }
    }
    
    /**
     * Approach 3: Union Find (Disjoint Set Union)
     * Time Complexity: O(m*n*α(m*n)) - α is inverse Ackermann function
     * Space Complexity: O(m*n) - Parent array
     * 
     * Algorithm:
     * 1. Initialize each land cell as its own set
     * 2. Union adjacent land cells
     * 3. Count number of disjoint sets
     */
    public int numIslandsUnionFind(char[][] grid) {
        if (grid == null || grid.length == 0) {
            return 0;
        }
        
        int rows = grid.length;
        int cols = grid[0].length;
        UnionFind uf = new UnionFind(grid);
        
        // Directions: right and down (enough to connect all)
        int[][] directions = {{0, 1}, {1, 0}};
        
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (grid[i][j] == '1') {
                    for (int[] dir : directions) {
                        int newRow = i + dir[0];
                        int newCol = j + dir[1];
                        
                        if (newRow < rows && newCol < cols && grid[newRow][newCol] == '1') {
                            uf.union(i * cols + j, newRow * cols + newCol);
                        }
                    }
                }
            }
        }
        
        return uf.getCount();
    }
    
    // Union Find data structure
    class UnionFind {
        private int[] parent;
        private int[] rank;
        private int count;
        
        public UnionFind(char[][] grid) {
            int rows = grid.length;
            int cols = grid[0].length;
            parent = new int[rows * cols];
            rank = new int[rows * cols];
            count = 0;
            
            for (int i = 0; i < rows; i++) {
                for (int j = 0; j < cols; j++) {
                    if (grid[i][j] == '1') {
                        int id = i * cols + j;
                        parent[id] = id;
                        rank[id] = 0;
                        count++;
                    }
                }
            }
        }
        
        public int find(int x) {
            if (parent[x] != x) {
                parent[x] = find(parent[x]); // Path compression
            }
            return parent[x];
        }
        
        public void union(int x, int y) {
            int rootX = find(x);
            int rootY = find(y);
            
            if (rootX != rootY) {
                // Union by rank
                if (rank[rootX] < rank[rootY]) {
                    parent[rootX] = rootY;
                } else if (rank[rootX] > rank[rootY]) {
                    parent[rootY] = rootX;
                } else {
                    parent[rootY] = rootX;
                    rank[rootX]++;
                }
                count--;
            }
        }
        
        public int getCount() {
            return count;
        }
    }
    
    /**
     * Approach 4: DFS with separate visited array (preserves input)
     * Time Complexity: O(m*n)
     * Space Complexity: O(m*n) - Visited array
     * 
     * Use this when you cannot modify the input grid
     */
    public int numIslandsNoModify(char[][] grid) {
        if (grid == null || grid.length == 0) {
            return 0;
        }
        
        int rows = grid.length;
        int cols = grid[0].length;
        boolean[][] visited = new boolean[rows][cols];
        int numIslands = 0;
        
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (grid[i][j] == '1' && !visited[i][j]) {
                    numIslands++;
                    dfsWithVisited(grid, visited, i, j);
                }
            }
        }
        
        return numIslands;
    }
    
    private void dfsWithVisited(char[][] grid, boolean[][] visited, int row, int col) {
        int rows = grid.length;
        int cols = grid[0].length;
        
        if (row < 0 || row >= rows || col < 0 || col >= cols 
            || visited[row][col] || grid[row][col] == '0') {
            return;
        }
        
        visited[row][col] = true;
        
        dfsWithVisited(grid, visited, row - 1, col);
        dfsWithVisited(grid, visited, row + 1, col);
        dfsWithVisited(grid, visited, row, col - 1);
        dfsWithVisited(grid, visited, row, col + 1);
    }
    
    /**
     * Approach 5: Iterative DFS using Stack
     * Time Complexity: O(m*n)
     * Space Complexity: O(m*n) - Stack size
     */
    public int numIslandsIterativeDFS(char[][] grid) {
        if (grid == null || grid.length == 0) {
            return 0;
        }
        
        int rows = grid.length;
        int cols = grid[0].length;
        int numIslands = 0;
        
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (grid[i][j] == '1') {
                    numIslands++;
                    iterativeDFS(grid, i, j);
                }
            }
        }
        
        return numIslands;
    }
    
    private void iterativeDFS(char[][] grid, int row, int col) {
        Stack<int[]> stack = new Stack<>();
        stack.push(new int[]{row, col});
        grid[row][col] = '0';
        
        int[][] directions = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
        
        while (!stack.isEmpty()) {
            int[] current = stack.pop();
            
            for (int[] dir : directions) {
                int newRow = current[0] + dir[0];
                int newCol = current[1] + dir[1];
                
                if (newRow >= 0 && newRow < grid.length && 
                    newCol >= 0 && newCol < grid[0].length && 
                    grid[newRow][newCol] == '1') {
                    stack.push(new int[]{newRow, newCol});
                    grid[newRow][newCol] = '0';
                }
            }
        }
    }
    
    // Helper method to print grid
    private static void printGrid(char[][] grid) {
        for (char[] row : grid) {
            System.out.println(Arrays.toString(row));
        }
        System.out.println();
    }
    
    // Helper method to copy grid
    private static char[][] copyGrid(char[][] grid) {
        char[][] copy = new char[grid.length][];
        for (int i = 0; i < grid.length; i++) {
            copy[i] = grid[i].clone();
        }
        return copy;
    }
    
    // Test the solutions
    public static void main(String[] args) {
        NumberOfIslands solution = new NumberOfIslands();
        
        // Test case 1
        char[][] grid1 = {
            {'1','1','1','1','0'},
            {'1','1','0','1','0'},
            {'1','1','0','0','0'},
            {'0','0','0','0','0'}
        };
        
        System.out.println("Test Case 1:");
        printGrid(grid1);
        
        System.out.println("DFS: " + solution.numIslandsDFS(copyGrid(grid1)));
        System.out.println("BFS: " + solution.numIslandsBFS(copyGrid(grid1)));
        System.out.println("Union Find: " + solution.numIslandsUnionFind(copyGrid(grid1)));
        System.out.println("No Modify: " + solution.numIslandsNoModify(copyGrid(grid1)));
        
        // Test case 2: Multiple islands
        char[][] grid2 = {
            {'1','1','0','0','0'},
            {'1','1','0','0','0'},
            {'0','0','1','0','0'},
            {'0','0','0','1','1'}
        };
        
        System.out.println("\nTest Case 2:");
        printGrid(grid2);
        System.out.println("DFS: " + solution.numIslandsDFS(copyGrid(grid2)));
        
        // Test case 3: No islands
        char[][] grid3 = {
            {'0','0','0'},
            {'0','0','0'}
        };
        
        System.out.println("\nTest Case 3:");
        printGrid(grid3);
        System.out.println("DFS: " + solution.numIslandsDFS(copyGrid(grid3)));
    }
} 