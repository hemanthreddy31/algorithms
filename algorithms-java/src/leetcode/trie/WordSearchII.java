package leetcode.trie;

import java.util.*;

/**
 * LeetCode 212: Word Search II
 * 
 * Given an m x n board of characters and a list of strings words,
 * return all words on the board.
 * 
 * Each word must be constructed from letters of sequentially adjacent cells,
 * where adjacent cells are horizontally or vertically neighboring.
 * The same letter cell may not be used more than once in a word.
 * 
 * Example:
 * Input: board = [["o","a","a","n"],["e","t","a","e"],["i","h","k","r"],["i","f","l","v"]], 
 *        words = ["oath","pea","eat","rain"]
 * Output: ["eat","oath"]
 */
public class WordSearchII {
    
    /**
     * Approach 1: Trie + DFS (Optimal)
     * Time: O(m * n * 4^L) where L = max word length
     * Space: O(ALPHABET_SIZE * N * L) for trie
     */
    public List<String> findWords(char[][] board, String[] words) {
        List<String> result = new ArrayList<>();
        if (board == null || board.length == 0 || words == null || words.length == 0) {
            return result;
        }
        
        // Build trie
        TrieNode root = buildTrie(words);
        
        // DFS from each cell
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                dfs(board, i, j, root, result);
            }
        }
        
        return result;
    }
    
    private TrieNode buildTrie(String[] words) {
        TrieNode root = new TrieNode();
        for (String word : words) {
            TrieNode node = root;
            for (char c : word.toCharArray()) {
                int index = c - 'a';
                if (node.children[index] == null) {
                    node.children[index] = new TrieNode();
                }
                node = node.children[index];
            }
            node.word = word; // Store word at end node
        }
        return root;
    }
    
    private void dfs(char[][] board, int row, int col, TrieNode node, List<String> result) {
        // Boundary check
        if (row < 0 || row >= board.length || col < 0 || col >= board[0].length) {
            return;
        }
        
        char c = board[row][col];
        if (c == '#' || node.children[c - 'a'] == null) {
            return; // Already visited or no matching trie path
        }
        
        node = node.children[c - 'a'];
        
        // Found a word
        if (node.word != null) {
            result.add(node.word);
            node.word = null; // Avoid duplicates
        }
        
        // Mark as visited
        board[row][col] = '#';
        
        // DFS in 4 directions
        dfs(board, row - 1, col, node, result);
        dfs(board, row + 1, col, node, result);
        dfs(board, row, col - 1, node, result);
        dfs(board, row, col + 1, node, result);
        
        // Restore cell
        board[row][col] = c;
    }
    
    /**
     * Approach 2: Optimized Trie with Pruning
     * Remove nodes that are no longer needed to improve performance
     */
    public List<String> findWordsOptimized(char[][] board, String[] words) {
        List<String> result = new ArrayList<>();
        TrieNode root = buildTrieOptimized(words);
        
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                dfsOptimized(board, i, j, root, result);
            }
        }
        
        return result;
    }
    
    private TrieNode buildTrieOptimized(String[] words) {
        TrieNode root = new TrieNode();
        for (String word : words) {
            TrieNode node = root;
            for (char c : word.toCharArray()) {
                int index = c - 'a';
                if (node.children[index] == null) {
                    node.children[index] = new TrieNode();
                }
                node = node.children[index];
                node.refs++; // Count references
            }
            node.word = word;
        }
        return root;
    }
    
    private void dfsOptimized(char[][] board, int row, int col, TrieNode node, List<String> result) {
        if (row < 0 || row >= board.length || col < 0 || col >= board[0].length) {
            return;
        }
        
        char c = board[row][col];
        if (c == '#' || node.children[c - 'a'] == null) {
            return;
        }
        
        node = node.children[c - 'a'];
        
        if (node.word != null) {
            result.add(node.word);
            node.word = null;
            node.refs--; // Decrease reference count
        }
        
        board[row][col] = '#';
        
        dfsOptimized(board, row - 1, col, node, result);
        dfsOptimized(board, row + 1, col, node, result);
        dfsOptimized(board, row, col - 1, node, result);
        dfsOptimized(board, row, col + 1, node, result);
        
        board[row][col] = c;
        
        // Prune: if no more words can be formed, remove this node
        if (node.refs == 0) {
            node.children[c - 'a'] = null;
        }
    }
    
    /**
     * Approach 3: Early Termination with Character Count
     * Check if board has enough characters to form all words
     */
    public List<String> findWordsEarlyTermination(char[][] board, String[] words) {
        // Count characters in board
        int[] boardCount = new int[26];
        for (char[] row : board) {
            for (char c : row) {
                boardCount[c - 'a']++;
            }
        }
        
        // Filter words that can be formed
        List<String> validWords = new ArrayList<>();
        for (String word : words) {
            if (canFormWord(word, boardCount)) {
                validWords.add(word);
            }
        }
        
        if (validWords.isEmpty()) {
            return new ArrayList<>();
        }
        
        return findWords(board, validWords.toArray(new String[0]));
    }
    
    private boolean canFormWord(String word, int[] boardCount) {
        int[] wordCount = new int[26];
        for (char c : word.toCharArray()) {
            wordCount[c - 'a']++;
        }
        
        for (int i = 0; i < 26; i++) {
            if (wordCount[i] > boardCount[i]) {
                return false;
            }
        }
        
        return true;
    }
    
    /**
     * Extended Problem: Word Search II with Direction Constraints
     * Only allow certain movement directions
     */
    public List<String> findWordsWithDirections(char[][] board, String[] words, int[][] directions) {
        List<String> result = new ArrayList<>();
        TrieNode root = buildTrie(words);
        
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                dfsWithDirections(board, i, j, root, result, directions);
            }
        }
        
        return result;
    }
    
    private void dfsWithDirections(char[][] board, int row, int col, TrieNode node, 
                                  List<String> result, int[][] directions) {
        if (row < 0 || row >= board.length || col < 0 || col >= board[0].length) {
            return;
        }
        
        char c = board[row][col];
        if (c == '#' || node.children[c - 'a'] == null) {
            return;
        }
        
        node = node.children[c - 'a'];
        
        if (node.word != null) {
            result.add(node.word);
            node.word = null;
        }
        
        board[row][col] = '#';
        
        // Use custom directions
        for (int[] dir : directions) {
            dfsWithDirections(board, row + dir[0], col + dir[1], node, result, directions);
        }
        
        board[row][col] = c;
    }
    
    /**
     * Extended Problem: Count All Words Found
     * Count how many times each word appears in the board
     */
    public Map<String, Integer> countWords(char[][] board, String[] words) {
        Map<String, Integer> result = new HashMap<>();
        TrieNode root = buildTrie(words);
        
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                dfsCount(board, i, j, root, result);
            }
        }
        
        return result;
    }
    
    private void dfsCount(char[][] board, int row, int col, TrieNode node, Map<String, Integer> result) {
        if (row < 0 || row >= board.length || col < 0 || col >= board[0].length) {
            return;
        }
        
        char c = board[row][col];
        if (c == '#' || node.children[c - 'a'] == null) {
            return;
        }
        
        node = node.children[c - 'a'];
        
        if (node.word != null) {
            result.put(node.word, result.getOrDefault(node.word, 0) + 1);
        }
        
        board[row][col] = '#';
        
        dfsCount(board, row - 1, col, node, result);
        dfsCount(board, row + 1, col, node, result);
        dfsCount(board, row, col - 1, node, result);
        dfsCount(board, row, col + 1, node, result);
        
        board[row][col] = c;
    }
    
    /**
     * Trie Node with optimization features
     */
    class TrieNode {
        TrieNode[] children;
        String word;
        int refs; // Reference count for pruning
        
        public TrieNode() {
            children = new TrieNode[26];
            refs = 0;
        }
    }
    
    /**
     * Utility: Print Trie Structure
     */
    public void printTrie(TrieNode root) {
        printTrieHelper(root, "", 0);
    }
    
    private void printTrieHelper(TrieNode node, String prefix, int depth) {
        if (node == null) return;
        
        if (node.word != null) {
            System.out.println("  ".repeat(depth) + "WORD: " + node.word);
        }
        
        for (int i = 0; i < 26; i++) {
            if (node.children[i] != null) {
                char c = (char) ('a' + i);
                System.out.println("  ".repeat(depth) + prefix + c);
                printTrieHelper(node.children[i], prefix + c, depth + 1);
            }
        }
    }
    
    // Test cases
    public static void main(String[] args) {
        WordSearchII solution = new WordSearchII();
        
        // Test case 1: Basic example
        char[][] board1 = {
            {'o','a','a','n'},
            {'e','t','a','e'},
            {'i','h','k','r'},
            {'i','f','l','v'}
        };
        String[] words1 = {"oath", "pea", "eat", "rain"};
        
        System.out.println("Test 1 - Basic:");
        System.out.println("Board:");
        for (char[] row : board1) {
            System.out.println(Arrays.toString(row));
        }
        System.out.println("Words: " + Arrays.toString(words1));
        System.out.println("Found: " + solution.findWords(board1, words1));
        System.out.println("Optimized: " + solution.findWordsOptimized(board1, words1));
        System.out.println("Early termination: " + solution.findWordsEarlyTermination(board1, words1));
        
        // Test case 2: Count words
        System.out.println("\nTest 2 - Count words:");
        Map<String, Integer> counts = solution.countWords(board1, words1);
        System.out.println("Word counts: " + counts);
        
        // Test case 3: Custom directions (only right and down)
        int[][] directions = {{0, 1}, {1, 0}}; // Right and down only
        System.out.println("\nTest 3 - Custom directions (right and down only):");
        System.out.println("Found: " + solution.findWordsWithDirections(board1, words1, directions));
        
        // Test case 4: Single character
        char[][] board2 = {{'a'}};
        String[] words2 = {"a"};
        System.out.println("\nTest 4 - Single character:");
        System.out.println("Found: " + solution.findWords(board2, words2));
        
        // Test case 5: No valid words
        String[] words3 = {"xyz", "abc"};
        System.out.println("\nTest 5 - No valid words:");
        System.out.println("Found: " + solution.findWords(board1, words3));
    }
} 