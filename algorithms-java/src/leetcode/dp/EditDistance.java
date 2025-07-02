package leetcode.dp;

import java.util.*;

/**
 * LeetCode 72: Edit Distance
 * 
 * Given two strings word1 and word2, return the minimum number of operations
 * required to convert word1 to word2.
 * 
 * You have the following three operations permitted on a word:
 * - Insert a character
 * - Delete a character  
 * - Replace a character
 */
public class EditDistance {
    
    /**
     * Approach 1: 2D DP (Bottom-up)
     * Time: O(m*n), Space: O(m*n)
     */
    public int minDistance(String word1, String word2) {
        int m = word1.length();
        int n = word2.length();
        
        int[][] dp = new int[m + 1][n + 1];
        
        // Initialize base cases
        for (int i = 0; i <= m; i++) dp[i][0] = i; // Delete all from word1
        for (int j = 0; j <= n; j++) dp[0][j] = j; // Insert all to word1
        
        // Fill DP table
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if (word1.charAt(i-1) == word2.charAt(j-1)) {
                    dp[i][j] = dp[i-1][j-1]; // No operation needed
                } else {
                    dp[i][j] = 1 + Math.min(
                        Math.min(dp[i-1][j],    // Delete
                                dp[i][j-1]),     // Insert
                        dp[i-1][j-1]            // Replace
                    );
                }
            }
        }
        
        return dp[m][n];
    }
    
    /**
     * Approach 2: Space Optimized DP
     * Time: O(m*n), Space: O(min(m,n))
     */
    public int minDistanceOptimized(String word1, String word2) {
        int m = word1.length();
        int n = word2.length();
        
        // Use shorter string for space optimization
        if (m < n) {
            return minDistanceOptimized(word2, word1);
        }
        
        int[] prev = new int[n + 1];
        int[] curr = new int[n + 1];
        
        // Initialize first row
        for (int j = 0; j <= n; j++) {
            prev[j] = j;
        }
        
        for (int i = 1; i <= m; i++) {
            curr[0] = i;
            
            for (int j = 1; j <= n; j++) {
                if (word1.charAt(i-1) == word2.charAt(j-1)) {
                    curr[j] = prev[j-1];
                } else {
                    curr[j] = 1 + Math.min(Math.min(prev[j], curr[j-1]), prev[j-1]);
                }
            }
            
            // Swap arrays
            int[] temp = prev;
            prev = curr;
            curr = temp;
        }
        
        return prev[n];
    }
    
    /**
     * Approach 3: Memoization (Top-down)
     * Time: O(m*n), Space: O(m*n)
     */
    public int minDistanceMemo(String word1, String word2) {
        int[][] memo = new int[word1.length()][word2.length()];
        for (int[] row : memo) Arrays.fill(row, -1);
        return helper(word1, word2, 0, 0, memo);
    }
    
    private int helper(String word1, String word2, int i, int j, int[][] memo) {
        if (i == word1.length()) return word2.length() - j;
        if (j == word2.length()) return word1.length() - i;
        
        if (memo[i][j] != -1) return memo[i][j];
        
        if (word1.charAt(i) == word2.charAt(j)) {
            memo[i][j] = helper(word1, word2, i + 1, j + 1, memo);
        } else {
            int insert = helper(word1, word2, i, j + 1, memo);
            int delete = helper(word1, word2, i + 1, j, memo);
            int replace = helper(word1, word2, i + 1, j + 1, memo);
            memo[i][j] = 1 + Math.min(Math.min(insert, delete), replace);
        }
        
        return memo[i][j];
    }
    
    /**
     * Extended Problem: Path Reconstruction
     * Return the actual operations performed
     */
    public List<String> minDistanceWithPath(String word1, String word2) {
        int m = word1.length();
        int n = word2.length();
        
        int[][] dp = new int[m + 1][n + 1];
        char[][] operation = new char[m + 1][n + 1];
        
        // Initialize
        for (int i = 0; i <= m; i++) {
            dp[i][0] = i;
            operation[i][0] = 'D'; // Delete
        }
        for (int j = 0; j <= n; j++) {
            dp[0][j] = j;
            operation[0][j] = 'I'; // Insert
        }
        
        // Fill DP table
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if (word1.charAt(i-1) == word2.charAt(j-1)) {
                    dp[i][j] = dp[i-1][j-1];
                    operation[i][j] = 'M'; // Match
                } else {
                    int delete = dp[i-1][j];
                    int insert = dp[i][j-1];
                    int replace = dp[i-1][j-1];
                    
                    if (delete <= insert && delete <= replace) {
                        dp[i][j] = delete + 1;
                        operation[i][j] = 'D';
                    } else if (insert <= replace) {
                        dp[i][j] = insert + 1;
                        operation[i][j] = 'I';
                    } else {
                        dp[i][j] = replace + 1;
                        operation[i][j] = 'R';
                    }
                }
            }
        }
        
        // Reconstruct path
        List<String> operations = new ArrayList<>();
        int i = m, j = n;
        
        while (i > 0 || j > 0) {
            char op = operation[i][j];
            
            switch (op) {
                case 'M':
                    operations.add("Match " + word1.charAt(i-1));
                    i--; j--;
                    break;
                case 'R':
                    operations.add("Replace " + word1.charAt(i-1) + " with " + word2.charAt(j-1));
                    i--; j--;
                    break;
                case 'D':
                    operations.add("Delete " + word1.charAt(i-1));
                    i--;
                    break;
                case 'I':
                    operations.add("Insert " + word2.charAt(j-1));
                    j--;
                    break;
            }
        }
        
        Collections.reverse(operations);
        return operations;
    }
    
    /**
     * Extended Problem: One Edit Distance (LC 161)
     * Check if strings are exactly one edit away
     */
    public boolean isOneEditDistance(String s, String t) {
        int m = s.length(), n = t.length();
        if (Math.abs(m - n) > 1) return false;
        
        if (m > n) return isOneEditDistance(t, s);
        
        for (int i = 0; i < m; i++) {
            if (s.charAt(i) != t.charAt(i)) {
                if (m == n) {
                    // Replace case
                    return s.substring(i + 1).equals(t.substring(i + 1));
                } else {
                    // Insert case
                    return s.substring(i).equals(t.substring(i + 1));
                }
            }
        }
        
        return m + 1 == n; // All characters match, check if t has one extra
    }
    
    // Test
    public static void main(String[] args) {
        EditDistance solution = new EditDistance();
        
        // Test case 1
        String word1 = "horse", word2 = "ros";
        System.out.println("Edit distance between '" + word1 + "' and '" + word2 + "': " + 
                          solution.minDistance(word1, word2));
        System.out.println("Operations: " + solution.minDistanceWithPath(word1, word2));
        
        // Test case 2
        String word3 = "intention", word4 = "execution";
        System.out.println("Edit distance between '" + word3 + "' and '" + word4 + "': " + 
                          solution.minDistance(word3, word4));
        
        // Test one edit distance
        System.out.println("Is 'ab' one edit from 'abc': " + solution.isOneEditDistance("ab", "abc"));
    }
} 