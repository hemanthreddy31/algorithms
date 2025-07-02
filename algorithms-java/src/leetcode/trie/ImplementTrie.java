package leetcode.trie;

import java.util.*;

/**
 * LeetCode 208: Implement Trie (Prefix Tree)
 * 
 * A trie (pronounced as "try") or prefix tree is a tree data structure used to 
 * efficiently store and retrieve keys in a dataset of strings. There are various 
 * applications of this data structure, such as autocomplete and spellchecker.
 * 
 * Implement the Trie class:
 * - Trie() Initializes the trie object.
 * - void insert(String word) Inserts the string word into the trie.
 * - boolean search(String word) Returns true if the string word is in the trie, false otherwise.
 * - boolean startsWith(String prefix) Returns true if there is a previously inserted string 
 *   word that has the prefix prefix, false otherwise.
 */
public class ImplementTrie {
    
    /**
     * Approach 1: Array-based Trie Node (Optimal for lowercase letters)
     * Space efficient when alphabet size is small
     */
    class Trie {
        class TrieNode {
            TrieNode[] children;
            boolean isEndOfWord;
            
            TrieNode() {
                children = new TrieNode[26]; // For lowercase a-z
                isEndOfWord = false;
            }
        }
        
        private TrieNode root;
        
        public Trie() {
            root = new TrieNode();
        }
        
        /**
         * Insert a word into the trie
         * Time Complexity: O(m) where m is word length
         * Space Complexity: O(m) in worst case
         */
        public void insert(String word) {
            TrieNode current = root;
            
            for (char c : word.toCharArray()) {
                int index = c - 'a';
                if (current.children[index] == null) {
                    current.children[index] = new TrieNode();
                }
                current = current.children[index];
            }
            
            current.isEndOfWord = true;
        }
        
        /**
         * Search for a word in the trie
         * Time Complexity: O(m) where m is word length
         * Space Complexity: O(1)
         */
        public boolean search(String word) {
            TrieNode node = searchPrefix(word);
            return node != null && node.isEndOfWord;
        }
        
        /**
         * Check if any word starts with given prefix
         * Time Complexity: O(m) where m is prefix length
         * Space Complexity: O(1)
         */
        public boolean startsWith(String prefix) {
            return searchPrefix(prefix) != null;
        }
        
        private TrieNode searchPrefix(String prefix) {
            TrieNode current = root;
            
            for (char c : prefix.toCharArray()) {
                int index = c - 'a';
                if (current.children[index] == null) {
                    return null;
                }
                current = current.children[index];
            }
            
            return current;
        }
    }
    
    /**
     * Approach 2: HashMap-based Trie Node (Flexible for any characters)
     * More flexible but slightly slower
     */
    class TrieHashMap {
        class TrieNode {
            Map<Character, TrieNode> children;
            boolean isEndOfWord;
            
            TrieNode() {
                children = new HashMap<>();
                isEndOfWord = false;
            }
        }
        
        private TrieNode root;
        
        public TrieHashMap() {
            root = new TrieNode();
        }
        
        public void insert(String word) {
            TrieNode current = root;
            
            for (char c : word.toCharArray()) {
                current.children.putIfAbsent(c, new TrieNode());
                current = current.children.get(c);
            }
            
            current.isEndOfWord = true;
        }
        
        public boolean search(String word) {
            TrieNode node = searchPrefix(word);
            return node != null && node.isEndOfWord;
        }
        
        public boolean startsWith(String prefix) {
            return searchPrefix(prefix) != null;
        }
        
        private TrieNode searchPrefix(String prefix) {
            TrieNode current = root;
            
            for (char c : prefix.toCharArray()) {
                if (!current.children.containsKey(c)) {
                    return null;
                }
                current = current.children.get(c);
            }
            
            return current;
        }
    }
    
    /**
     * Extended Trie with additional features
     */
    class ExtendedTrie {
        class TrieNode {
            TrieNode[] children;
            boolean isEndOfWord;
            int count; // Number of words with this prefix
            String word; // Store complete word at end nodes
            
            TrieNode() {
                children = new TrieNode[26];
                isEndOfWord = false;
                count = 0;
                word = null;
            }
        }
        
        private TrieNode root;
        
        public ExtendedTrie() {
            root = new TrieNode();
        }
        
        public void insert(String word) {
            TrieNode current = root;
            
            for (char c : word.toCharArray()) {
                int index = c - 'a';
                if (current.children[index] == null) {
                    current.children[index] = new TrieNode();
                }
                current = current.children[index];
                current.count++; // Increment prefix count
            }
            
            current.isEndOfWord = true;
            current.word = word; // Store complete word
        }
        
        /**
         * Delete a word from trie
         * Time Complexity: O(m) where m is word length
         */
        public boolean delete(String word) {
            return deleteHelper(root, word, 0);
        }
        
        private boolean deleteHelper(TrieNode node, String word, int index) {
            if (index == word.length()) {
                // Word doesn't exist
                if (!node.isEndOfWord) {
                    return false;
                }
                node.isEndOfWord = false;
                // Return true if node has no children (can be deleted)
                return !hasChildren(node);
            }
            
            char c = word.charAt(index);
            int charIndex = c - 'a';
            TrieNode child = node.children[charIndex];
            
            if (child == null) {
                return false; // Word doesn't exist
            }
            
            boolean shouldDeleteChild = deleteHelper(child, word, index + 1);
            
            if (shouldDeleteChild) {
                node.children[charIndex] = null;
                // Return true if current node has no children and is not end of another word
                return !node.isEndOfWord && !hasChildren(node);
            }
            
            return false;
        }
        
        private boolean hasChildren(TrieNode node) {
            for (TrieNode child : node.children) {
                if (child != null) return true;
            }
            return false;
        }
        
        /**
         * Count words with given prefix
         */
        public int countWordsWithPrefix(String prefix) {
            TrieNode node = searchPrefix(prefix);
            return node != null ? countWords(node) : 0;
        }
        
        private int countWords(TrieNode node) {
            int count = 0;
            if (node.isEndOfWord) count++;
            
            for (TrieNode child : node.children) {
                if (child != null) {
                    count += countWords(child);
                }
            }
            
            return count;
        }
        
        /**
         * Get all words with given prefix
         */
        public List<String> getWordsWithPrefix(String prefix) {
            List<String> result = new ArrayList<>();
            TrieNode prefixNode = searchPrefix(prefix);
            
            if (prefixNode != null) {
                collectWords(prefixNode, result);
            }
            
            return result;
        }
        
        private void collectWords(TrieNode node, List<String> result) {
            if (node.isEndOfWord) {
                result.add(node.word);
            }
            
            for (TrieNode child : node.children) {
                if (child != null) {
                    collectWords(child, result);
                }
            }
        }
        
        /**
         * Auto-complete: Get top k words with given prefix
         */
        public List<String> autoComplete(String prefix, int k) {
            List<String> allWords = getWordsWithPrefix(prefix);
            Collections.sort(allWords); // Sort lexicographically
            return allWords.size() > k ? allWords.subList(0, k) : allWords;
        }
        
        private TrieNode searchPrefix(String prefix) {
            TrieNode current = root;
            
            for (char c : prefix.toCharArray()) {
                int index = c - 'a';
                if (current.children[index] == null) {
                    return null;
                }
                current = current.children[index];
            }
            
            return current;
        }
    }
    
    /**
     * LeetCode 211: Design Add and Search Words Data Structure
     * Support '.' as wildcard matching any character
     */
    class WordDictionary {
        class TrieNode {
            TrieNode[] children;
            boolean isEndOfWord;
            
            TrieNode() {
                children = new TrieNode[26];
                isEndOfWord = false;
            }
        }
        
        private TrieNode root;
        
        public WordDictionary() {
            root = new TrieNode();
        }
        
        public void addWord(String word) {
            TrieNode current = root;
            
            for (char c : word.toCharArray()) {
                int index = c - 'a';
                if (current.children[index] == null) {
                    current.children[index] = new TrieNode();
                }
                current = current.children[index];
            }
            
            current.isEndOfWord = true;
        }
        
        public boolean search(String word) {
            return searchHelper(word, 0, root);
        }
        
        private boolean searchHelper(String word, int index, TrieNode node) {
            if (index == word.length()) {
                return node.isEndOfWord;
            }
            
            char c = word.charAt(index);
            
            if (c == '.') {
                // Try all possible children
                for (TrieNode child : node.children) {
                    if (child != null && searchHelper(word, index + 1, child)) {
                        return true;
                    }
                }
                return false;
            } else {
                // Normal character
                int charIndex = c - 'a';
                if (node.children[charIndex] == null) {
                    return false;
                }
                return searchHelper(word, index + 1, node.children[charIndex]);
            }
        }
    }
    
    // Test the implementations
    public static void main(String[] args) {
        ImplementTrie implementation = new ImplementTrie();
        
        // Test basic Trie
        System.out.println("Testing Basic Trie:");
        Trie trie = implementation.new Trie();
        trie.insert("apple");
        System.out.println("search('apple'): " + trie.search("apple"));
        System.out.println("search('app'): " + trie.search("app"));
        System.out.println("startsWith('app'): " + trie.startsWith("app"));
        trie.insert("app");
        System.out.println("search('app'): " + trie.search("app"));
        
        // Test Extended Trie
        System.out.println("\nTesting Extended Trie:");
        ExtendedTrie extTrie = implementation.new ExtendedTrie();
        extTrie.insert("apple");
        extTrie.insert("app");
        extTrie.insert("apricot");
        extTrie.insert("banana");
        
        System.out.println("Words with prefix 'app': " + extTrie.getWordsWithPrefix("app"));
        System.out.println("Count words with prefix 'app': " + extTrie.countWordsWithPrefix("app"));
        System.out.println("Auto-complete 'ap' (top 2): " + extTrie.autoComplete("ap", 2));
        
        // Test Word Dictionary with wildcards
        System.out.println("\nTesting Word Dictionary:");
        WordDictionary dict = implementation.new WordDictionary();
        dict.addWord("bad");
        dict.addWord("dad");
        dict.addWord("mad");
        System.out.println("search('pad'): " + dict.search("pad"));
        System.out.println("search('bad'): " + dict.search("bad"));
        System.out.println("search('.ad'): " + dict.search(".ad"));
        System.out.println("search('b..'): " + dict.search("b.."));
    }
} 