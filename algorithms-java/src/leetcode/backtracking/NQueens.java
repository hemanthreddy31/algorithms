package leetcode.backtracking;

import java.util.*;

/**
 * LeetCode 51: N-Queens
 * 
 * The n-queens puzzle is the problem of placing n queens on an n×n chessboard
 * such that no two queens attack each other.
 * 
 * Example:
 * Input: n = 4
 * Output: [[".Q..","...Q","Q...","..Q."],["..Q.","Q...","...Q",".Q.."]]
 */
public class NQueens {
    
    /**
     * Approach 1: Backtracking with Validation
     * Time: O(N!), Space: O(N²)
     */
    public List<List<String>> solveNQueens(int n) {
        List<List<String>> result = new ArrayList<>();
        char[][] board = new char[n][n];
        
        // Initialize board with dots
        for (int i = 0; i < n; i++) {
            Arrays.fill(board[i], '.');
        }
        
        backtrack(board, 0, result);
        return result;
    }
    
    private void backtrack(char[][] board, int row, List<List<String>> result) {
        int n = board.length;
        
        // Base case: placed all queens
        if (row == n) {
            result.add(constructBoard(board));
            return;
        }
        
        // Try placing queen in each column of current row
        for (int col = 0; col < n; col++) {
            if (isValid(board, row, col)) {
                // Place queen
                board[row][col] = 'Q';
                
                // Recurse for next row
                backtrack(board, row + 1, result);
                
                // Backtrack
                board[row][col] = '.';
            }
        }
    }
    
    private boolean isValid(char[][] board, int row, int col) {
        int n = board.length;
        
        // Check column
        for (int i = 0; i < row; i++) {
            if (board[i][col] == 'Q') {
                return false;
            }
        }
        
        // Check diagonal (top-left to bottom-right)
        for (int i = row - 1, j = col - 1; i >= 0 && j >= 0; i--, j--) {
            if (board[i][j] == 'Q') {
                return false;
            }
        }
        
        // Check anti-diagonal (top-right to bottom-left)
        for (int i = row - 1, j = col + 1; i >= 0 && j < n; i--, j++) {
            if (board[i][j] == 'Q') {
                return false;
            }
        }
        
        return true;
    }
    
    private List<String> constructBoard(char[][] board) {
        List<String> result = new ArrayList<>();
        for (char[] row : board) {
            result.add(new String(row));
        }
        return result;
    }
    
    /**
     * Approach 2: Optimized with Sets
     * Time: O(N!), Space: O(N)
     * Use sets to track attacked positions
     */
    public List<List<String>> solveNQueensOptimized(int n) {
        List<List<String>> result = new ArrayList<>();
        Set<Integer> cols = new HashSet<>();
        Set<Integer> diagonals = new HashSet<>();
        Set<Integer> antiDiagonals = new HashSet<>();
        
        backtrackOptimized(n, 0, new ArrayList<>(), cols, diagonals, antiDiagonals, result);
        return result;
    }
    
    private void backtrackOptimized(int n, int row, List<Integer> queens,
                                   Set<Integer> cols, Set<Integer> diagonals, 
                                   Set<Integer> antiDiagonals, List<List<String>> result) {
        if (row == n) {
            result.add(constructBoardFromQueens(queens, n));
            return;
        }
        
        for (int col = 0; col < n; col++) {
            int diagonal = row - col;
            int antiDiagonal = row + col;
            
            if (cols.contains(col) || diagonals.contains(diagonal) || 
                antiDiagonals.contains(antiDiagonal)) {
                continue;
            }
            
            // Place queen
            queens.add(col);
            cols.add(col);
            diagonals.add(diagonal);
            antiDiagonals.add(antiDiagonal);
            
            backtrackOptimized(n, row + 1, queens, cols, diagonals, antiDiagonals, result);
            
            // Backtrack
            queens.remove(queens.size() - 1);
            cols.remove(col);
            diagonals.remove(diagonal);
            antiDiagonals.remove(antiDiagonal);
        }
    }
    
    private List<String> constructBoardFromQueens(List<Integer> queens, int n) {
        List<String> board = new ArrayList<>();
        for (int row = 0; row < n; row++) {
            char[] rowChars = new char[n];
            Arrays.fill(rowChars, '.');
            rowChars[queens.get(row)] = 'Q';
            board.add(new String(rowChars));
        }
        return board;
    }
    
    /**
     * LeetCode 52: N-Queens II
     * Count total number of solutions
     */
    public int totalNQueens(int n) {
        Set<Integer> cols = new HashSet<>();
        Set<Integer> diagonals = new HashSet<>();
        Set<Integer> antiDiagonals = new HashSet<>();
        
        return countSolutions(n, 0, cols, diagonals, antiDiagonals);
    }
    
    private int countSolutions(int n, int row, Set<Integer> cols, 
                              Set<Integer> diagonals, Set<Integer> antiDiagonals) {
        if (row == n) {
            return 1;
        }
        
        int count = 0;
        for (int col = 0; col < n; col++) {
            int diagonal = row - col;
            int antiDiagonal = row + col;
            
            if (cols.contains(col) || diagonals.contains(diagonal) || 
                antiDiagonals.contains(antiDiagonal)) {
                continue;
            }
            
            cols.add(col);
            diagonals.add(diagonal);
            antiDiagonals.add(antiDiagonal);
            
            count += countSolutions(n, row + 1, cols, diagonals, antiDiagonals);
            
            cols.remove(col);
            diagonals.remove(diagonal);
            antiDiagonals.remove(antiDiagonal);
        }
        
        return count;
    }
    
    /**
     * Approach 3: Bit Manipulation (Most Optimized)
     * Time: O(N!), Space: O(N)
     */
    public int totalNQueensBitwise(int n) {
        return solveBitwise(n, 0, 0, 0, 0);
    }
    
    private int solveBitwise(int n, int row, int cols, int diagonals, int antiDiagonals) {
        if (row == n) {
            return 1;
        }
        
        int count = 0;
        int availablePositions = ((1 << n) - 1) & (~(cols | diagonals | antiDiagonals));
        
        while (availablePositions != 0) {
            int position = availablePositions & (-availablePositions); // Get rightmost bit
            availablePositions &= (availablePositions - 1); // Remove rightmost bit
            
            count += solveBitwise(n, row + 1,
                                 cols | position,
                                 (diagonals | position) << 1,
                                 (antiDiagonals | position) >> 1);
        }
        
        return count;
    }
    
    // Test
    public static void main(String[] args) {
        NQueens solution = new NQueens();
        
        // Test N-Queens
        System.out.println("4-Queens solutions:");
        List<List<String>> solutions = solution.solveNQueens(4);
        for (int i = 0; i < solutions.size(); i++) {
            System.out.println("Solution " + (i + 1) + ":");
            for (String row : solutions.get(i)) {
                System.out.println(row);
            }
            System.out.println();
        }
        
        // Test N-Queens II
        System.out.println("Total 8-Queens solutions: " + solution.totalNQueens(8));
        System.out.println("Total 8-Queens solutions (bitwise): " + solution.totalNQueensBitwise(8));
    }
} 