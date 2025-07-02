package leetcode.graph;

import java.util.*;

/**
 * LeetCode 269: Alien Dictionary (Premium)
 * 
 * Given a list of words sorted lexicographically by the rules of an alien language,
 * derive the order of letters in this alien language.
 * 
 * Example:
 * Input: words = ["wrt","wrf","er","ett","rftt"]
 * Output: "wertf"
 */
public class AlienDictionary {
    
    /**
     * Approach: Topological Sort using Kahn's Algorithm
     * Time: O(C) where C = total number of characters in all words
     * Space: O(1) - at most 26 characters (constant space)
     */
    public String alienOrder(String[] words) {
        if (words == null || words.length == 0) return "";
        
        // Step 1: Build graph and in-degree map
        Map<Character, Set<Character>> graph = new HashMap<>();
        Map<Character, Integer> inDegree = new HashMap<>();
        
        // Initialize all characters
        for (String word : words) {
            for (char c : word.toCharArray()) {
                graph.putIfAbsent(c, new HashSet<>());
                inDegree.putIfAbsent(c, 0);
            }
        }
        
        // Step 2: Build edges by comparing adjacent words
        for (int i = 0; i < words.length - 1; i++) {
            String word1 = words[i];
            String word2 = words[i + 1];
            
            // Check for invalid case: word1 is prefix of word2 but longer
            if (word1.length() > word2.length() && word1.startsWith(word2)) {
                return ""; // Invalid ordering
            }
            
            // Find first different character
            for (int j = 0; j < Math.min(word1.length(), word2.length()); j++) {
                char c1 = word1.charAt(j);
                char c2 = word2.charAt(j);
                
                if (c1 != c2) {
                    if (!graph.get(c1).contains(c2)) {
                        graph.get(c1).add(c2);
                        inDegree.put(c2, inDegree.get(c2) + 1);
                    }
                    break; // Only first difference matters
                }
            }
        }
        
        // Step 3: Topological sort using Kahn's algorithm
        Queue<Character> queue = new LinkedList<>();
        for (char c : inDegree.keySet()) {
            if (inDegree.get(c) == 0) {
                queue.offer(c);
            }
        }
        
        StringBuilder result = new StringBuilder();
        
        while (!queue.isEmpty()) {
            char current = queue.poll();
            result.append(current);
            
            for (char neighbor : graph.get(current)) {
                inDegree.put(neighbor, inDegree.get(neighbor) - 1);
                if (inDegree.get(neighbor) == 0) {
                    queue.offer(neighbor);
                }
            }
        }
        
        // Check if all characters are processed (no cycle)
        return result.length() == inDegree.size() ? result.toString() : "";
    }
    
    /**
     * Approach 2: DFS with Cycle Detection
     * Time: O(C), Space: O(1)
     */
    public String alienOrderDFS(String[] words) {
        if (words == null || words.length == 0) return "";
        
        Map<Character, Set<Character>> graph = new HashMap<>();
        Set<Character> allChars = new HashSet<>();
        
        // Initialize
        for (String word : words) {
            for (char c : word.toCharArray()) {
                graph.putIfAbsent(c, new HashSet<>());
                allChars.add(c);
            }
        }
        
        // Build graph
        for (int i = 0; i < words.length - 1; i++) {
            String word1 = words[i];
            String word2 = words[i + 1];
            
            if (word1.length() > word2.length() && word1.startsWith(word2)) {
                return "";
            }
            
            for (int j = 0; j < Math.min(word1.length(), word2.length()); j++) {
                char c1 = word1.charAt(j);
                char c2 = word2.charAt(j);
                
                if (c1 != c2) {
                    graph.get(c1).add(c2);
                    break;
                }
            }
        }
        
        // DFS with cycle detection
        Map<Character, Integer> color = new HashMap<>(); // 0: white, 1: gray, 2: black
        Stack<Character> stack = new Stack<>();
        
        for (char c : allChars) {
            if (color.getOrDefault(c, 0) == 0) {
                if (hasCycle(graph, color, c, stack)) {
                    return "";
                }
            }
        }
        
        StringBuilder result = new StringBuilder();
        while (!stack.isEmpty()) {
            result.append(stack.pop());
        }
        
        return result.toString();
    }
    
    private boolean hasCycle(Map<Character, Set<Character>> graph, 
                            Map<Character, Integer> color, 
                            char node, Stack<Character> stack) {
        color.put(node, 1); // Mark as gray (visiting)
        
        for (char neighbor : graph.get(node)) {
            if (color.getOrDefault(neighbor, 0) == 1) {
                return true; // Back edge found - cycle
            }
            if (color.getOrDefault(neighbor, 0) == 0 && 
                hasCycle(graph, color, neighbor, stack)) {
                return true;
            }
        }
        
        color.put(node, 2); // Mark as black (visited)
        stack.push(node);
        return false;
    }
    
    /**
     * Extended: Verify Alien Dictionary
     * LeetCode 953: Check if words are sorted according to alien order
     */
    public boolean isAlienSorted(String[] words, String order) {
        int[] charOrder = new int[26];
        for (int i = 0; i < order.length(); i++) {
            charOrder[order.charAt(i) - 'a'] = i;
        }
        
        for (int i = 0; i < words.length - 1; i++) {
            if (compare(words[i], words[i + 1], charOrder) > 0) {
                return false;
            }
        }
        
        return true;
    }
    
    private int compare(String word1, String word2, int[] order) {
        int i = 0, j = 0;
        
        while (i < word1.length() && j < word2.length()) {
            int c1 = order[word1.charAt(i) - 'a'];
            int c2 = order[word2.charAt(j) - 'a'];
            
            if (c1 != c2) {
                return c1 - c2;
            }
            i++;
            j++;
        }
        
        return word1.length() - word2.length();
    }
    
    // Test
    public static void main(String[] args) {
        AlienDictionary solution = new AlienDictionary();
        
        // Test case 1
        String[] words1 = {"wrt", "wrf", "er", "ett", "rftt"};
        System.out.println("Test 1 - Kahn: " + solution.alienOrder(words1));
        System.out.println("Test 1 - DFS: " + solution.alienOrderDFS(words1));
        
        // Test case 2
        String[] words2 = {"z", "x"};
        System.out.println("Test 2: " + solution.alienOrder(words2));
        
        // Test case 3: Invalid case
        String[] words3 = {"z", "x", "z"};
        System.out.println("Test 3 (invalid): " + solution.alienOrder(words3));
        
        // Test case 4: Prefix case
        String[] words4 = {"abc", "ab"};
        System.out.println("Test 4 (prefix invalid): " + solution.alienOrder(words4));
        
        // Test extended problem
        String[] alienWords = {"hello", "leetcode"};
        String alienOrder = "hlabcdefgijkmnopqrstuvwxyz";
        System.out.println("Is alien sorted: " + solution.isAlienSorted(alienWords, alienOrder));
    }
} 