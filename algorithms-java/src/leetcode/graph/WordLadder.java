package leetcode.graph;

import java.util.*;

/**
 * LeetCode 127: Word Ladder
 * 
 * Given two words, beginWord and endWord, and a dictionary wordList,
 * return the length of shortest transformation sequence from beginWord to endWord.
 * 
 * Rules:
 * 1. Only one letter can be changed at a time
 * 2. Each transformed word must exist in wordList
 * 3. beginWord is not a transformed word
 */
public class WordLadder {
    
    /**
     * Approach 1: BFS (Optimal)
     * Time: O(M²×N) where M = word length, N = wordList size
     * Space: O(M×N) for queue and visited set
     */
    public int ladderLength(String beginWord, String endWord, List<String> wordList) {
        Set<String> wordSet = new HashSet<>(wordList);
        if (!wordSet.contains(endWord)) return 0;
        
        Queue<String> queue = new LinkedList<>();
        Set<String> visited = new HashSet<>();
        
        queue.offer(beginWord);
        visited.add(beginWord);
        
        int level = 1;
        
        while (!queue.isEmpty()) {
            int size = queue.size();
            
            for (int i = 0; i < size; i++) {
                String current = queue.poll();
                
                if (current.equals(endWord)) {
                    return level;
                }
                
                // Try all possible single character changes
                for (int j = 0; j < current.length(); j++) {
                    char[] chars = current.toCharArray();
                    
                    for (char c = 'a'; c <= 'z'; c++) {
                        chars[j] = c;
                        String newWord = new String(chars);
                        
                        if (wordSet.contains(newWord) && !visited.contains(newWord)) {
                            queue.offer(newWord);
                            visited.add(newWord);
                        }
                    }
                }
            }
            level++;
        }
        
        return 0;
    }
    
    /**
     * Approach 2: Bidirectional BFS (More efficient)
     * Time: O(M²×N), Space: O(M×N)
     * Search from both ends to reduce search space
     */
    public int ladderLengthBidirectional(String beginWord, String endWord, List<String> wordList) {
        Set<String> wordSet = new HashSet<>(wordList);
        if (!wordSet.contains(endWord)) return 0;
        
        Set<String> beginSet = new HashSet<>();
        Set<String> endSet = new HashSet<>();
        Set<String> visited = new HashSet<>();
        
        beginSet.add(beginWord);
        endSet.add(endWord);
        
        int level = 1;
        
        while (!beginSet.isEmpty() && !endSet.isEmpty()) {
            // Always expand the smaller set
            if (beginSet.size() > endSet.size()) {
                Set<String> temp = beginSet;
                beginSet = endSet;
                endSet = temp;
            }
            
            Set<String> nextSet = new HashSet<>();
            
            for (String word : beginSet) {
                char[] chars = word.toCharArray();
                
                for (int i = 0; i < chars.length; i++) {
                    char oldChar = chars[i];
                    
                    for (char c = 'a'; c <= 'z'; c++) {
                        chars[i] = c;
                        String newWord = new String(chars);
                        
                        if (endSet.contains(newWord)) {
                            return level + 1;
                        }
                        
                        if (!visited.contains(newWord) && wordSet.contains(newWord)) {
                            nextSet.add(newWord);
                            visited.add(newWord);
                        }
                    }
                    chars[i] = oldChar;
                }
            }
            
            beginSet = nextSet;
            level++;
        }
        
        return 0;
    }
    
    /**
     * Extended: Word Ladder II - Return all shortest paths
     * LeetCode 126: Return all shortest transformation sequences
     */
    public List<List<String>> findLadders(String beginWord, String endWord, List<String> wordList) {
        List<List<String>> result = new ArrayList<>();
        Set<String> wordSet = new HashSet<>(wordList);
        
        if (!wordSet.contains(endWord)) return result;
        
        Map<String, List<String>> neighbors = new HashMap<>();
        Map<String, Integer> distance = new HashMap<>();
        
        // BFS to find distances
        bfsDistance(beginWord, endWord, wordSet, neighbors, distance);
        
        // DFS to find all paths
        List<String> path = new ArrayList<>();
        path.add(beginWord);
        dfsPath(beginWord, endWord, neighbors, distance, path, result);
        
        return result;
    }
    
    private void bfsDistance(String beginWord, String endWord, Set<String> wordSet,
                            Map<String, List<String>> neighbors, Map<String, Integer> distance) {
        Queue<String> queue = new LinkedList<>();
        queue.offer(beginWord);
        distance.put(beginWord, 0);
        
        while (!queue.isEmpty()) {
            boolean found = false;
            int size = queue.size();
            
            for (int i = 0; i < size; i++) {
                String current = queue.poll();
                int curDist = distance.get(current);
                
                List<String> neighborList = getNeighbors(current, wordSet);
                neighbors.put(current, neighborList);
                
                for (String neighbor : neighborList) {
                    if (!distance.containsKey(neighbor)) {
                        distance.put(neighbor, curDist + 1);
                        if (neighbor.equals(endWord)) {
                            found = true;
                        } else {
                            queue.offer(neighbor);
                        }
                    }
                }
            }
            
            if (found) break;
        }
    }
    
    private List<String> getNeighbors(String word, Set<String> wordSet) {
        List<String> neighbors = new ArrayList<>();
        char[] chars = word.toCharArray();
        
        for (int i = 0; i < chars.length; i++) {
            char oldChar = chars[i];
            for (char c = 'a'; c <= 'z'; c++) {
                chars[i] = c;
                String newWord = new String(chars);
                if (wordSet.contains(newWord) && !newWord.equals(word)) {
                    neighbors.add(newWord);
                }
            }
            chars[i] = oldChar;
        }
        
        return neighbors;
    }
    
    private void dfsPath(String current, String endWord, Map<String, List<String>> neighbors,
                        Map<String, Integer> distance, List<String> path, List<List<String>> result) {
        if (current.equals(endWord)) {
            result.add(new ArrayList<>(path));
            return;
        }
        
        for (String neighbor : neighbors.getOrDefault(current, new ArrayList<>())) {
            if (distance.get(neighbor) == distance.get(current) + 1) {
                path.add(neighbor);
                dfsPath(neighbor, endWord, neighbors, distance, path, result);
                path.remove(path.size() - 1);
            }
        }
    }
    
    // Test
    public static void main(String[] args) {
        WordLadder solution = new WordLadder();
        
        // Test case 1
        String beginWord1 = "hit";
        String endWord1 = "cog";
        List<String> wordList1 = Arrays.asList("hot","dot","dog","lot","log","cog");
        
        System.out.println("Test 1 - BFS: " + solution.ladderLength(beginWord1, endWord1, wordList1));
        System.out.println("Test 1 - Bidirectional: " + solution.ladderLengthBidirectional(beginWord1, endWord1, wordList1));
        
        // Test case 2
        String beginWord2 = "hit";
        String endWord2 = "cog";
        List<String> wordList2 = Arrays.asList("hot","dot","dog","lot","log");
        
        System.out.println("Test 2 - BFS: " + solution.ladderLength(beginWord2, endWord2, wordList2));
        
        // Test Word Ladder II
        System.out.println("All paths: " + solution.findLadders(beginWord1, endWord1, wordList1));
    }
} 