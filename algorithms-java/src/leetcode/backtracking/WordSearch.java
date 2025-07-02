package leetcode.backtracking;

import java.util.*;

/**
 * LeetCode 79: Word Search
 * 
 * Given an m x n grid of characters board and a string word,
 * return true if word exists in the grid.
 * 
 * The word can be constructed from letters of sequentially adjacent cells,
 * where adjacent cells are horizontally or vertically neighboring.
 * The same letter cell may not be used more than once.
 */
public class WordSearch {
    
    /**
     * Approach 1: Backtracking with Grid Traversal
     * Time: O(m * n * 4^L) where L = word.length
     * Space: O(L) for recursion stack
     */
    public boolean exist(char[][] board, String word) {
        if (board == null || board.length == 0) return false;
        
        int rows = board.length;
        int cols = board[0].length;
        
        // Try starting from each cell
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (backtrack(board, word, i, j, 0)) {
                    return true;
                }
            }
        }
        
        return false;
    }
    
    private boolean backtrack(char[][] board, String word, int row, int col, int index) {
        // Base case: found complete word
        if (index == word.length()) {
            return true;
        }
        
        // Boundary checks and character match
        if (row < 0 || row >= board.length || col < 0 || col >= board[0].length ||
            board[row][col] != word.charAt(index)) {
            return false;
        }
        
        // Mark current cell as visited
        char temp = board[row][col];
        board[row][col] = '#'; // Use special character to mark visited
        
        // Explore all 4 directions
        boolean found = backtrack(board, word, row - 1, col, index + 1) ||
                       backtrack(board, word, row + 1, col, index + 1) ||
                       backtrack(board, word, row, col - 1, index + 1) ||
                       backtrack(board, word, row, col + 1, index + 1);
        
        // Restore original character (backtrack)
        board[row][col] = temp;
        
        return found;
    }
    
    /**
     * Approach 2: Optimized with Early Termination
     * Time: O(m * n * 4^L), Space: O(L)
     * Added optimizations for better performance
     */
    public boolean existOptimized(char[][] board, String word) {
        if (board == null || board.length == 0 || word == null || word.length() == 0) {
            return false;
        }
        
        // Early termination: check if all characters exist
        int[] charCount = new int[256];
        for (char[] row : board) {
            for (char c : row) {
                charCount[c]++;
            }
        }
        
        for (char c : word.toCharArray()) {
            if (--charCount[c] < 0) {
                return false;
            }
        }
        
        int rows = board.length;
        int cols = board[0].length;
        
        // Optimization: start from less frequent character end
        char firstChar = word.charAt(0);
        char lastChar = word.charAt(word.length() - 1);
        
        // Count occurrences to decide which end to start from
        int firstCount = 0, lastCount = 0;
        for (char[] row : board) {
            for (char c : row) {
                if (c == firstChar) firstCount++;
                if (c == lastChar) lastCount++;
            }
        }
        
        // Start from the end with fewer matching characters
        if (firstCount > lastCount) {
            word = new StringBuilder(word).reverse().toString();
        }
        
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (backtrackOptimized(board, word, i, j, 0)) {
                    return true;
                }
            }
        }
        
        return false;
    }
    
    private boolean backtrackOptimized(char[][] board, String word, int row, int col, int index) {
        if (index == word.length()) return true;
        
        if (row < 0 || row >= board.length || col < 0 || col >= board[0].length ||
            board[row][col] != word.charAt(index)) {
            return false;
        }
        
        char temp = board[row][col];
        board[row][col] = '#';
        
        boolean found = backtrackOptimized(board, word, row - 1, col, index + 1) ||
                       backtrackOptimized(board, word, row + 1, col, index + 1) ||
                       backtrackOptimized(board, word, row, col - 1, index + 1) ||
                       backtrackOptimized(board, word, row, col + 1, index + 1);
        
        board[row][col] = temp;
        return found;
    }
    
    /**
     * Extended Problem: Word Search II (LC 212)
     * Find all words from dictionary that exist in the board
     */
    public List<String> findWords(char[][] board, String[] words) {
        List<String> result = new ArrayList<>();
        Trie trie = new Trie();
        
        // Build trie from all words
        for (String word : words) {
            trie.insert(word);
        }
        
        int rows = board.length;
        int cols = board[0].length;
        
        // Search for words starting from each cell
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                dfsWithTrie(board, i, j, trie.root, result);
            }
        }
        
        return result;
    }
    
    private void dfsWithTrie(char[][] board, int row, int col, TrieNode node, List<String> result) {
        if (row < 0 || row >= board.length || col < 0 || col >= board[0].length) {
            return;
        }
        
        char c = board[row][col];
        if (c == '#' || node.children[c - 'a'] == null) {
            return;
        }
        
        node = node.children[c - 'a'];
        
        // Found a word
        if (node.word != null) {
            result.add(node.word);
            node.word = null; // Avoid duplicates
        }
        
        board[row][col] = '#'; // Mark as visited
        
        // Search in all 4 directions
        dfsWithTrie(board, row - 1, col, node, result);
        dfsWithTrie(board, row + 1, col, node, result);
        dfsWithTrie(board, row, col - 1, node, result);
        dfsWithTrie(board, row, col + 1, node, result);
        
        board[row][col] = c; // Restore
    }
    
    // Trie data structure for Word Search II
    class Trie {
        TrieNode root;
        
        public Trie() {
            root = new TrieNode();
        }
        
        public void insert(String word) {
            TrieNode node = root;
            for (char c : word.toCharArray()) {
                if (node.children[c - 'a'] == null) {
                    node.children[c - 'a'] = new TrieNode();
                }
                node = node.children[c - 'a'];
            }
            node.word = word;
        }
    }
    
    class TrieNode {
        TrieNode[] children;
        String word;
        
        public TrieNode() {
            children = new TrieNode[26];
        }
    }
    
    /**
     * Extended Problem: Count Paths with Word
     * Count all possible paths that form the given word
     */
    public int countWordPaths(char[][] board, String word) {
        if (board == null || board.length == 0) return 0;
        
        int rows = board.length;
        int cols = board[0].length;
        int count = 0;
        
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                count += countPaths(board, word, i, j, 0);
            }
        }
        
        return count;
    }
    
    private int countPaths(char[][] board, String word, int row, int col, int index) {
        if (index == word.length()) return 1;
        
        if (row < 0 || row >= board.length || col < 0 || col >= board[0].length ||
            board[row][col] != word.charAt(index)) {
            return 0;
        }
        
        char temp = board[row][col];
        board[row][col] = '#';
        
        int paths = countPaths(board, word, row - 1, col, index + 1) +
                   countPaths(board, word, row + 1, col, index + 1) +
                   countPaths(board, word, row, col - 1, index + 1) +
                   countPaths(board, word, row, col + 1, index + 1);
        
        board[row][col] = temp;
        return paths;
    }
    
    /**
     * Extended Problem: Longest Word in Board
     * Find the longest word that can be formed
     */
    public String longestWord(char[][] board, Set<String> dictionary) {
        String longest = "";
        
        for (String word : dictionary) {
            if (word.length() > longest.length() && exist(board, word)) {
                longest = word;
            }
        }
        
        return longest;
    }
    
    /**
     * Extended Problem: Word Square
     * Check if board forms a valid word square
     */
    public boolean validWordSquare(List<String> words) {
        for (int i = 0; i < words.size(); i++) {
            for (int j = 0; j < words.get(i).length(); j++) {
                if (j >= words.size() || i >= words.get(j).length() ||
                    words.get(i).charAt(j) != words.get(j).charAt(i)) {
                    return false;
                }
            }
        }
        return true;
    }
    
    // Test
    public static void main(String[] args) {
        WordSearch solution = new WordSearch();
        
        char[][] board = {
            {'A','B','C','E'},
            {'S','F','C','S'},
            {'A','D','E','E'}
        };
        
        System.out.println("Word Search in board:");
        for (char[] row : board) {
            System.out.println(Arrays.toString(row));
        }
        
        System.out.println("'ABCCED' exists: " + solution.exist(board, "ABCCED"));
        System.out.println("'SEE' exists: " + solution.exist(board, "SEE"));
        System.out.println("'ABCB' exists: " + solution.exist(board, "ABCB"));
    }
} 