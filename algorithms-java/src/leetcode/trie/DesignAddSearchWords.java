package leetcode.trie;

import java.util.*;

/**
 * LeetCode 211: Design Add and Search Words Data Structure
 * 
 * Design a data structure that supports adding new words and finding if a string matches
 * any previously added string.
 * 
 * Implement the WordDictionary class:
 * - WordDictionary() Initializes the object.
 * - void addWord(word) Adds word to the data structure, it can be matched later.
 * - bool search(word) Returns true if there is any string in the data structure that matches word 
 *   or false otherwise. word may contain dots '.' where dots can be matched with any letter.
 * 
 * Example:
 * Input: ["WordDictionary","addWord","addWord","addWord","search","search","search","search"]
 *        [[],["bad"],["dad"],["mad"],["pad"],["bad"],[".ad"],["b.."]]
 * Output: [null,null,null,null,false,true,true,true]
 */
public class DesignAddSearchWords {
    
    /**
     * Approach 1: Trie with Wildcard Support
     * Time: O(M) for addWord, O(N * 26^M) for search in worst case
     * Space: O(ALPHABET_SIZE * N * M) where N = number of words, M = length of word
     */
    static class WordDictionary {
        private TrieNode root;
        
        public WordDictionary() {
            root = new TrieNode();
        }
        
        public void addWord(String word) {
            TrieNode node = root;
            for (char c : word.toCharArray()) {
                int index = c - 'a';
                if (node.children[index] == null) {
                    node.children[index] = new TrieNode();
                }
                node = node.children[index];
            }
            node.isEnd = true;
        }
        
        public boolean search(String word) {
            return searchHelper(word, 0, root);
        }
        
        private boolean searchHelper(String word, int index, TrieNode node) {
            if (index == word.length()) {
                return node.isEnd;
            }
            
            char c = word.charAt(index);
            
            if (c == '.') {
                // Wildcard: try all possible children
                for (TrieNode child : node.children) {
                    if (child != null && searchHelper(word, index + 1, child)) {
                        return true;
                    }
                }
                return false;
            } else {
                // Regular character
                int charIndex = c - 'a';
                if (node.children[charIndex] == null) {
                    return false;
                }
                return searchHelper(word, index + 1, node.children[charIndex]);
            }
        }
        
        class TrieNode {
            TrieNode[] children;
            boolean isEnd;
            
            public TrieNode() {
                children = new TrieNode[26];
                isEnd = false;
            }
        }
    }
    
    /**
     * Approach 2: Optimized with Early Termination
     * Add optimization to reduce unnecessary recursive calls
     */
    static class WordDictionaryOptimized {
        private TrieNode root;
        
        public WordDictionaryOptimized() {
            root = new TrieNode();
        }
        
        public void addWord(String word) {
            TrieNode node = root;
            for (char c : word.toCharArray()) {
                int index = c - 'a';
                if (node.children[index] == null) {
                    node.children[index] = new TrieNode();
                }
                node = node.children[index];
            }
            node.isEnd = true;
            node.word = word; // Store the word for easier debugging
        }
        
        public boolean search(String word) {
            return dfs(word.toCharArray(), 0, root);
        }
        
        private boolean dfs(char[] word, int index, TrieNode node) {
            if (index == word.length) {
                return node.isEnd;
            }
            
            char c = word[index];
            
            if (c != '.') {
                int charIndex = c - 'a';
                TrieNode child = node.children[charIndex];
                return child != null && dfs(word, index + 1, child);
            } else {
                // Wildcard case with early termination
                for (int i = 0; i < 26; i++) {
                    if (node.children[i] != null) {
                        if (dfs(word, index + 1, node.children[i])) {
                            return true;
                        }
                    }
                }
                return false;
            }
        }
        
        /**
         * Additional method: Get all words matching pattern
         */
        public List<String> getAllMatches(String pattern) {
            List<String> result = new ArrayList<>();
            getAllMatchesHelper(pattern.toCharArray(), 0, root, new StringBuilder(), result);
            return result;
        }
        
        private void getAllMatchesHelper(char[] pattern, int index, TrieNode node, 
                                        StringBuilder current, List<String> result) {
            if (index == pattern.length) {
                if (node.isEnd) {
                    result.add(current.toString());
                }
                return;
            }
            
            char c = pattern[index];
            
            if (c != '.') {
                int charIndex = c - 'a';
                if (node.children[charIndex] != null) {
                    current.append(c);
                    getAllMatchesHelper(pattern, index + 1, node.children[charIndex], current, result);
                    current.setLength(current.length() - 1);
                }
            } else {
                for (int i = 0; i < 26; i++) {
                    if (node.children[i] != null) {
                        char childChar = (char) ('a' + i);
                        current.append(childChar);
                        getAllMatchesHelper(pattern, index + 1, node.children[i], current, result);
                        current.setLength(current.length() - 1);
                    }
                }
            }
        }
        
        class TrieNode {
            TrieNode[] children;
            boolean isEnd;
            String word; // For debugging/extended functionality
            
            public TrieNode() {
                children = new TrieNode[26];
                isEnd = false;
            }
        }
    }
    
    /**
     * Extended Problem: Multi-Wildcard Support
     * Support multiple types of wildcards
     */
    static class WordDictionaryExtended {
        private TrieNode root;
        
        public WordDictionaryExtended() {
            root = new TrieNode();
        }
        
        public void addWord(String word) {
            TrieNode node = root;
            for (char c : word.toCharArray()) {
                int index = getIndex(c);
                if (node.children[index] == null) {
                    node.children[index] = new TrieNode();
                }
                node = node.children[index];
            }
            node.isEnd = true;
        }
        
        /**
         * Search with extended wildcards:
         * '.' = any single character
         * '*' = zero or more of any character
         * '?' = zero or one of any character
         */
        public boolean searchExtended(String pattern) {
            return searchExtendedHelper(pattern, 0, root);
        }
        
        private boolean searchExtendedHelper(String pattern, int index, TrieNode node) {
            if (index == pattern.length()) {
                return node.isEnd;
            }
            
            char c = pattern.charAt(index);
            
            if (c == '.') {
                // Single character wildcard
                for (TrieNode child : node.children) {
                    if (child != null && searchExtendedHelper(pattern, index + 1, child)) {
                        return true;
                    }
                }
                return false;
            } else if (c == '*') {
                // Zero or more characters wildcard
                // Try zero characters (skip this wildcard)
                if (searchExtendedHelper(pattern, index + 1, node)) {
                    return true;
                }
                // Try one or more characters
                for (TrieNode child : node.children) {
                    if (child != null && searchExtendedHelper(pattern, index, child)) {
                        return true;
                    }
                }
                return false;
            } else if (c == '?') {
                // Zero or one character wildcard
                // Try zero characters
                if (searchExtendedHelper(pattern, index + 1, node)) {
                    return true;
                }
                // Try one character
                for (TrieNode child : node.children) {
                    if (child != null && searchExtendedHelper(pattern, index + 1, child)) {
                        return true;
                    }
                }
                return false;
            } else {
                // Regular character
                int charIndex = getIndex(c);
                if (node.children[charIndex] == null) {
                    return false;
                }
                return searchExtendedHelper(pattern, index + 1, node.children[charIndex]);
            }
        }
        
        private int getIndex(char c) {
            return c - 'a';
        }
        
        class TrieNode {
            TrieNode[] children;
            boolean isEnd;
            
            public TrieNode() {
                children = new TrieNode[26];
                isEnd = false;
            }
        }
    }
    
    /**
     * Extended Problem: WordDictionary with Prefix Matching
     * Add methods for prefix-based operations
     */
    static class WordDictionaryWithPrefix {
        private TrieNode root;
        
        public WordDictionaryWithPrefix() {
            root = new TrieNode();
        }
        
        public void addWord(String word) {
            TrieNode node = root;
            for (char c : word.toCharArray()) {
                int index = c - 'a';
                if (node.children[index] == null) {
                    node.children[index] = new TrieNode();
                }
                node = node.children[index];
                node.wordCount++; // Count words passing through this node
            }
            node.isEnd = true;
        }
        
        public boolean search(String word) {
            return searchHelper(word, 0, root);
        }
        
        private boolean searchHelper(String word, int index, TrieNode node) {
            if (index == word.length()) {
                return node.isEnd;
            }
            
            char c = word.charAt(index);
            
            if (c == '.') {
                for (TrieNode child : node.children) {
                    if (child != null && searchHelper(word, index + 1, child)) {
                        return true;
                    }
                }
                return false;
            } else {
                int charIndex = c - 'a';
                if (node.children[charIndex] == null) {
                    return false;
                }
                return searchHelper(word, index + 1, node.children[charIndex]);
            }
        }
        
        /**
         * Check if any words start with given prefix
         */
        public boolean startsWith(String prefix) {
            TrieNode node = root;
            for (char c : prefix.toCharArray()) {
                int index = c - 'a';
                if (node.children[index] == null) {
                    return false;
                }
                node = node.children[index];
            }
            return true;
        }
        
        /**
         * Count words with given prefix
         */
        public int countWordsWithPrefix(String prefix) {
            TrieNode node = root;
            for (char c : prefix.toCharArray()) {
                int index = c - 'a';
                if (node.children[index] == null) {
                    return 0;
                }
                node = node.children[index];
            }
            return node.wordCount;
        }
        
        /**
         * Get all words with given prefix
         */
        public List<String> getWordsWithPrefix(String prefix) {
            List<String> result = new ArrayList<>();
            TrieNode node = root;
            
            // Navigate to prefix node
            for (char c : prefix.toCharArray()) {
                int index = c - 'a';
                if (node.children[index] == null) {
                    return result; // Empty list
                }
                node = node.children[index];
            }
            
            // Collect all words from this node
            collectWords(node, new StringBuilder(prefix), result);
            return result;
        }
        
        private void collectWords(TrieNode node, StringBuilder current, List<String> result) {
            if (node.isEnd) {
                result.add(current.toString());
            }
            
            for (int i = 0; i < 26; i++) {
                if (node.children[i] != null) {
                    current.append((char) ('a' + i));
                    collectWords(node.children[i], current, result);
                    current.setLength(current.length() - 1);
                }
            }
        }
        
        class TrieNode {
            TrieNode[] children;
            boolean isEnd;
            int wordCount; // Number of words passing through this node
            
            public TrieNode() {
                children = new TrieNode[26];
                isEnd = false;
                wordCount = 0;
            }
        }
    }
    
    // Test cases
    public static void main(String[] args) {
        System.out.println("=== Testing Basic WordDictionary ===");
        
        WordDictionary wd = new WordDictionary();
        wd.addWord("bad");
        wd.addWord("dad");
        wd.addWord("mad");
        
        System.out.println("Search 'pad': " + wd.search("pad")); // false
        System.out.println("Search 'bad': " + wd.search("bad")); // true
        System.out.println("Search '.ad': " + wd.search(".ad")); // true
        System.out.println("Search 'b..': " + wd.search("b..")); // true
        System.out.println("Search '...': " + wd.search("...")); // true
        System.out.println("Search '....': " + wd.search("....")); // false
        
        System.out.println("\n=== Testing Optimized WordDictionary ===");
        
        WordDictionaryOptimized wdo = new WordDictionaryOptimized();
        wdo.addWord("cat");
        wdo.addWord("car");
        wdo.addWord("card");
        wdo.addWord("care");
        wdo.addWord("careful");
        
        System.out.println("Search 'car': " + wdo.search("car")); // true
        System.out.println("Search 'c.r': " + wdo.search("c.r")); // true
        System.out.println("Search 'c.rd': " + wdo.search("c.rd")); // true
        System.out.println("All matches for 'c.r.': " + wdo.getAllMatches("c.r."));
        
        System.out.println("\n=== Testing WordDictionary with Prefix ===");
        
        WordDictionaryWithPrefix wdp = new WordDictionaryWithPrefix();
        wdp.addWord("apple");
        wdp.addWord("app");
        wdp.addWord("apricot");
        wdp.addWord("banana");
        
        System.out.println("Starts with 'app': " + wdp.startsWith("app")); // true
        System.out.println("Starts with 'ban': " + wdp.startsWith("ban")); // true
        System.out.println("Starts with 'orange': " + wdp.startsWith("orange")); // false
        System.out.println("Count words with prefix 'ap': " + wdp.countWordsWithPrefix("ap"));
        System.out.println("Words with prefix 'ap': " + wdp.getWordsWithPrefix("ap"));
        
        System.out.println("\n=== Testing Extended WordDictionary ===");
        
        WordDictionaryExtended wde = new WordDictionaryExtended();
        wde.addWord("hello");
        wde.addWord("help");
        wde.addWord("world");
        
        System.out.println("Search 'hel*': " + wde.searchExtended("hel*")); // Should match "hello" and "help"
        System.out.println("Search 'h?llo': " + wde.searchExtended("h?llo")); // Should match "hello"
    }
} 