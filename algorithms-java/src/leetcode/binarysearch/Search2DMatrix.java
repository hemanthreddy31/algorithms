package leetcode.binarysearch;

import java.util.*;

/**
 * LeetCode 74: Search a 2D Matrix
 * 
 * Write an efficient algorithm that searches for a value target in an m x n integer matrix.
 * This matrix has the following properties:
 * - Integers in each row are sorted from left to right.
 * - The first integer of each row is greater than the last integer of the previous row.
 * 
 * Example:
 * Input: matrix = [[1,4,7,11],[2,5,8,12],[3,6,9,16],[10,13,14,17]], target = 5
 * Output: true
 */
public class Search2DMatrix {
    
    /**
     * Approach 1: Treat as 1D Array
     * Time: O(log(m*n)), Space: O(1)
     * 
     * Convert 2D coordinates to 1D index and vice versa
     */
    public boolean searchMatrix(int[][] matrix, int target) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return false;
        }
        
        int rows = matrix.length;
        int cols = matrix[0].length;
        int left = 0, right = rows * cols - 1;
        
        while (left <= right) {
            int mid = left + (right - left) / 2;
            int midValue = matrix[mid / cols][mid % cols];
            
            if (midValue == target) {
                return true;
            } else if (midValue < target) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        
        return false;
    }
    
    /**
     * Approach 2: Two-Step Binary Search
     * Time: O(log m + log n), Space: O(1)
     * 
     * First find row, then search within that row
     */
    public boolean searchMatrixTwoStep(int[][] matrix, int target) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return false;
        }
        
        int rows = matrix.length;
        int cols = matrix[0].length;
        
        // Find the row
        int targetRow = -1;
        int top = 0, bottom = rows - 1;
        
        while (top <= bottom) {
            int mid = top + (bottom - top) / 2;
            
            if (target >= matrix[mid][0] && target <= matrix[mid][cols - 1]) {
                targetRow = mid;
                break;
            } else if (target < matrix[mid][0]) {
                bottom = mid - 1;
            } else {
                top = mid + 1;
            }
        }
        
        if (targetRow == -1) {
            return false;
        }
        
        // Search within the row
        int left = 0, right = cols - 1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            
            if (matrix[targetRow][mid] == target) {
                return true;
            } else if (matrix[targetRow][mid] < target) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        
        return false;
    }
    
    /**
     * Extended Problem: Search a 2D Matrix II (LC 240)
     * Rows and columns are sorted, but not globally sorted
     */
    public boolean searchMatrixII(int[][] matrix, int target) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return false;
        }
        
        int rows = matrix.length;
        int cols = matrix[0].length;
        
        // Start from top-right corner
        int row = 0, col = cols - 1;
        
        while (row < rows && col >= 0) {
            if (matrix[row][col] == target) {
                return true;
            } else if (matrix[row][col] > target) {
                col--; // Move left
            } else {
                row++; // Move down
            }
        }
        
        return false;
    }
    
    /**
     * Extended Problem: Find Position in Matrix
     * Return the position of target instead of boolean
     */
    public int[] findPosition(int[][] matrix, int target) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return new int[]{-1, -1};
        }
        
        int rows = matrix.length;
        int cols = matrix[0].length;
        int left = 0, right = rows * cols - 1;
        
        while (left <= right) {
            int mid = left + (right - left) / 2;
            int row = mid / cols;
            int col = mid % cols;
            int midValue = matrix[row][col];
            
            if (midValue == target) {
                return new int[]{row, col};
            } else if (midValue < target) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        
        return new int[]{-1, -1};
    }
    
    /**
     * Extended Problem: Count Elements Smaller Than Target
     */
    public int countSmaller(int[][] matrix, int target) {
        int rows = matrix.length;
        int cols = matrix[0].length;
        int left = 0, right = rows * cols;
        
        while (left < right) {
            int mid = left + (right - left) / 2;
            int midValue = matrix[mid / cols][mid % cols];
            
            if (midValue < target) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }
        
        return left;
    }
    
    /**
     * Extended Problem: Find Kth Smallest Element (LC 378)
     * Find kth smallest element in sorted matrix
     */
    public int kthSmallest(int[][] matrix, int k) {
        int n = matrix.length;
        int left = matrix[0][0];
        int right = matrix[n - 1][n - 1];
        
        while (left < right) {
            int mid = left + (right - left) / 2;
            int count = countSmallerOrEqual(matrix, mid);
            
            if (count < k) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }
        
        return left;
    }
    
    private int countSmallerOrEqual(int[][] matrix, int target) {
        int n = matrix.length;
        int count = 0;
        int row = n - 1, col = 0;
        
        while (row >= 0 && col < n) {
            if (matrix[row][col] <= target) {
                count += row + 1;
                col++;
            } else {
                row--;
            }
        }
        
        return count;
    }
    
    /**
     * Extended Problem: Find Peak Element in 2D Matrix
     */
    public int[] findPeakGrid(int[][] matrix) {
        int rows = matrix.length;
        int cols = matrix[0].length;
        int left = 0, right = cols - 1;
        
        while (left <= right) {
            int mid = left + (right - left) / 2;
            int maxRow = 0;
            
            // Find max element in middle column
            for (int i = 0; i < rows; i++) {
                if (matrix[i][mid] > matrix[maxRow][mid]) {
                    maxRow = i;
                }
            }
            
            boolean leftIsBigger = mid - 1 >= 0 && matrix[maxRow][mid - 1] > matrix[maxRow][mid];
            boolean rightIsBigger = mid + 1 < cols && matrix[maxRow][mid + 1] > matrix[maxRow][mid];
            
            if (!leftIsBigger && !rightIsBigger) {
                return new int[]{maxRow, mid};
            } else if (leftIsBigger) {
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        
        return new int[]{-1, -1};
    }
    
    /**
     * Extended Problem: Search in Row-wise and Column-wise Sorted Matrix
     * More general case where rows and columns are sorted but not globally
     */
    public List<int[]> searchMatrixIIAllPositions(int[][] matrix, int target) {
        List<int[]> positions = new ArrayList<>();
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return positions;
        }
        
        int rows = matrix.length;
        int cols = matrix[0].length;
        
        // Start from top-right corner
        int row = 0, col = cols - 1;
        
        while (row < rows && col >= 0) {
            if (matrix[row][col] == target) {
                positions.add(new int[]{row, col});
                // Continue searching for more occurrences
                row++;
                col--;
            } else if (matrix[row][col] > target) {
                col--; // Move left
            } else {
                row++; // Move down
            }
        }
        
        return positions;
    }
    
    /**
     * Utility: Print Matrix
     */
    public void printMatrix(int[][] matrix) {
        for (int[] row : matrix) {
            System.out.println(Arrays.toString(row));
        }
    }
    
    /**
     * Utility: Generate Test Matrix
     */
    public int[][] generateSortedMatrix(int rows, int cols, int start, int increment) {
        int[][] matrix = new int[rows][cols];
        int value = start;
        
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                matrix[i][j] = value;
                value += increment;
            }
        }
        
        return matrix;
    }
    
    // Test cases
    public static void main(String[] args) {
        Search2DMatrix solution = new Search2DMatrix();
        
        // Test case 1: Standard sorted matrix
        int[][] matrix1 = {
            {1,  4,  7,  11},
            {2,  5,  8,  12},
            {3,  6,  9,  16},
            {10, 13, 14, 17}
        };
        
        System.out.println("Test 1 - Standard matrix:");
        solution.printMatrix(matrix1);
        
        System.out.println("Search 5: " + solution.searchMatrix(matrix1, 5)); // true
        System.out.println("Search 13: " + solution.searchMatrix(matrix1, 13)); // true
        System.out.println("Search 15: " + solution.searchMatrix(matrix1, 15)); // false
        System.out.println("Two-step search 5: " + solution.searchMatrixTwoStep(matrix1, 5)); // true
        
        // Test position finding
        int[] pos = solution.findPosition(matrix1, 5);
        System.out.println("Position of 5: " + Arrays.toString(pos)); // [1, 1]
        
        // Test case 2: Row and column sorted matrix (not globally sorted)
        int[][] matrix2 = {
            {1,  4,  7,  11, 15},
            {2,  5,  8,  12, 19},
            {3,  6,  9,  16, 22},
            {10, 13, 14, 17, 24},
            {18, 21, 23, 26, 30}
        };
        
        System.out.println("\nTest 2 - Row/Column sorted matrix:");
        solution.printMatrix(matrix2);
        
        System.out.println("Search II for 5: " + solution.searchMatrixII(matrix2, 5)); // true
        System.out.println("Search II for 14: " + solution.searchMatrixII(matrix2, 14)); // true
        System.out.println("Search II for 20: " + solution.searchMatrixII(matrix2, 20)); // false
        
        // Test case 3: Generated matrix
        int[][] matrix3 = solution.generateSortedMatrix(3, 4, 1, 2);
        System.out.println("\nTest 3 - Generated matrix:");
        solution.printMatrix(matrix3);
        
        // Test extended problems
        System.out.println("Count elements < 7: " + solution.countSmaller(matrix3, 7));
        System.out.println("3rd smallest: " + solution.kthSmallest(matrix3, 3));
        
        // Test peak finding
        int[] peak = solution.findPeakGrid(matrix3);
        System.out.println("Peak element position: " + Arrays.toString(peak));
        
        // Test all positions
        List<int[]> allPos = solution.searchMatrixIIAllPositions(matrix2, 14);
        System.out.println("All positions of 14:");
        for (int[] position : allPos) {
            System.out.println("  " + Arrays.toString(position));
        }
    }
} 