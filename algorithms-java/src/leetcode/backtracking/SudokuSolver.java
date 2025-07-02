package leetcode.backtracking;

import java.util.*;

/**
 * LeetCode 37: Sudoku Solver
 * 
 * Write a program to solve a Sudoku puzzle by filling the empty cells.
 * A sudoku solution must satisfy all of the following rules:
 * 1. Each row must contain digits 1-9 with no repetition.
 * 2. Each column must contain digits 1-9 with no repetition.
 * 3. Each 3x3 sub-box must contain digits 1-9 with no repetition.
 * The '.' character indicates empty cells.
 */
public class SudokuSolver {
    
    /**
     * Approach 1: Basic Backtracking
     * Time: O(9^(n*n)), Space: O(n*n)
     */
    public void solveSudoku(char[][] board) {
        solve(board);
    }
    
    private boolean solve(char[][] board) {
        for (int row = 0; row < 9; row++) {
            for (int col = 0; col < 9; col++) {
                if (board[row][col] == '.') {
                    // Try digits 1-9
                    for (char digit = '1'; digit <= '9'; digit++) {
                        if (isValid(board, row, col, digit)) {
                            board[row][col] = digit;
                            
                            if (solve(board)) {
                                return true;
                            }
                            
                            // Backtrack
                            board[row][col] = '.';
                        }
                    }
                    return false; // No valid digit found
                }
            }
        }
        return true; // All cells filled
    }
    
    private boolean isValid(char[][] board, int row, int col, char digit) {
        // Check row
        for (int c = 0; c < 9; c++) {
            if (board[row][c] == digit) {
                return false;
            }
        }
        
        // Check column
        for (int r = 0; r < 9; r++) {
            if (board[r][col] == digit) {
                return false;
            }
        }
        
        // Check 3x3 box
        int boxRow = (row / 3) * 3;
        int boxCol = (col / 3) * 3;
        for (int r = boxRow; r < boxRow + 3; r++) {
            for (int c = boxCol; c < boxCol + 3; c++) {
                if (board[r][c] == digit) {
                    return false;
                }
            }
        }
        
        return true;
    }
    
    /**
     * Approach 2: Optimized with Constraint Sets
     * Time: O(9^(n*n)), Space: O(n*n)
     * Pre-compute valid digits for faster checking
     */
    public void solveSudokuOptimized(char[][] board) {
        Set<Character>[] rowSets = new Set[9];
        Set<Character>[] colSets = new Set[9];
        Set<Character>[] boxSets = new Set[9];
        
        // Initialize sets
        for (int i = 0; i < 9; i++) {
            rowSets[i] = new HashSet<>();
            colSets[i] = new HashSet<>();
            boxSets[i] = new HashSet<>();
        }
        
        // Fill sets with existing digits
        for (int row = 0; row < 9; row++) {
            for (int col = 0; col < 9; col++) {
                if (board[row][col] != '.') {
                    char digit = board[row][col];
                    rowSets[row].add(digit);
                    colSets[col].add(digit);
                    boxSets[getBoxIndex(row, col)].add(digit);
                }
            }
        }
        
        solveOptimized(board, rowSets, colSets, boxSets);
    }
    
    private boolean solveOptimized(char[][] board, Set<Character>[] rowSets,
                                  Set<Character>[] colSets, Set<Character>[] boxSets) {
        for (int row = 0; row < 9; row++) {
            for (int col = 0; col < 9; col++) {
                if (board[row][col] == '.') {
                    int boxIndex = getBoxIndex(row, col);
                    
                    for (char digit = '1'; digit <= '9'; digit++) {
                        if (!rowSets[row].contains(digit) && 
                            !colSets[col].contains(digit) && 
                            !boxSets[boxIndex].contains(digit)) {
                            
                            // Place digit
                            board[row][col] = digit;
                            rowSets[row].add(digit);
                            colSets[col].add(digit);
                            boxSets[boxIndex].add(digit);
                            
                            if (solveOptimized(board, rowSets, colSets, boxSets)) {
                                return true;
                            }
                            
                            // Backtrack
                            board[row][col] = '.';
                            rowSets[row].remove(digit);
                            colSets[col].remove(digit);
                            boxSets[boxIndex].remove(digit);
                        }
                    }
                    return false;
                }
            }
        }
        return true;
    }
    
    private int getBoxIndex(int row, int col) {
        return (row / 3) * 3 + (col / 3);
    }
    
    /**
     * Approach 3: Most Constrained Variable (MCV) Heuristic
     * Choose the cell with fewest possible values first
     */
    public void solveSudokuMCV(char[][] board) {
        solveMCV(board);
    }
    
    private boolean solveMCV(char[][] board) {
        int[] bestCell = findMostConstrainedCell(board);
        if (bestCell == null) {
            return true; // All cells filled
        }
        
        int row = bestCell[0];
        int col = bestCell[1];
        
        for (char digit = '1'; digit <= '9'; digit++) {
            if (isValid(board, row, col, digit)) {
                board[row][col] = digit;
                
                if (solveMCV(board)) {
                    return true;
                }
                
                board[row][col] = '.';
            }
        }
        
        return false;
    }
    
    private int[] findMostConstrainedCell(char[][] board) {
        int minPossibilities = 10;
        int[] bestCell = null;
        
        for (int row = 0; row < 9; row++) {
            for (int col = 0; col < 9; col++) {
                if (board[row][col] == '.') {
                    int possibilities = countPossibilities(board, row, col);
                    if (possibilities < minPossibilities) {
                        minPossibilities = possibilities;
                        bestCell = new int[]{row, col};
                    }
                }
            }
        }
        
        return bestCell;
    }
    
    private int countPossibilities(char[][] board, int row, int col) {
        boolean[] used = new boolean[10]; // index 0 unused, 1-9 for digits
        
        // Mark digits used in row
        for (int c = 0; c < 9; c++) {
            if (board[row][c] != '.') {
                used[board[row][c] - '0'] = true;
            }
        }
        
        // Mark digits used in column
        for (int r = 0; r < 9; r++) {
            if (board[r][col] != '.') {
                used[board[r][col] - '0'] = true;
            }
        }
        
        // Mark digits used in box
        int boxRow = (row / 3) * 3;
        int boxCol = (col / 3) * 3;
        for (int r = boxRow; r < boxRow + 3; r++) {
            for (int c = boxCol; c < boxCol + 3; c++) {
                if (board[r][c] != '.') {
                    used[board[r][c] - '0'] = true;
                }
            }
        }
        
        int count = 0;
        for (int i = 1; i <= 9; i++) {
            if (!used[i]) {
                count++;
            }
        }
        
        return count;
    }
    
    /**
     * LeetCode 36: Valid Sudoku
     * Check if sudoku board is valid
     */
    public boolean isValidSudoku(char[][] board) {
        Set<String> seen = new HashSet<>();
        
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                char digit = board[i][j];
                if (digit != '.') {
                    if (!seen.add(digit + " in row " + i) ||
                        !seen.add(digit + " in col " + j) ||
                        !seen.add(digit + " in box " + i/3 + "-" + j/3)) {
                        return false;
                    }
                }
            }
        }
        
        return true;
    }
    
    // Helper method to print board
    private void printBoard(char[][] board) {
        for (int i = 0; i < 9; i++) {
            if (i % 3 == 0 && i != 0) {
                System.out.println("------+-------+------");
            }
            for (int j = 0; j < 9; j++) {
                if (j % 3 == 0 && j != 0) {
                    System.out.print("| ");
                }
                System.out.print(board[i][j] + " ");
            }
            System.out.println();
        }
    }
    
    // Test
    public static void main(String[] args) {
        SudokuSolver solution = new SudokuSolver();
        
        char[][] board = {
            {'5','3','.','.','7','.','.','.','.'},
            {'6','.','.','1','9','5','.','.','.'},
            {'.','9','8','.','.','.','.','6','.'},
            {'8','.','.','.','6','.','.','.','3'},
            {'4','.','.','8','.','3','.','.','1'},
            {'7','.','.','.','2','.','.','.','6'},
            {'.','6','.','.','.','.','2','8','.'},
            {'.','.','.','4','1','9','.','.','5'},
            {'.','.','.','.','8','.','.','7','9'}
        };
        
        System.out.println("Original Sudoku:");
        solution.printBoard(board);
        
        System.out.println("\nIs valid: " + solution.isValidSudoku(board));
        
        solution.solveSudoku(board);
        System.out.println("\nSolved Sudoku:");
        solution.printBoard(board);
    }
} 