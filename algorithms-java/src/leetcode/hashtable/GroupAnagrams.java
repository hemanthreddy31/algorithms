package leetcode.hashtable;

import java.util.*;

/**
 * LeetCode 49: Group Anagrams
 * 
 * Given an array of strings strs, group the anagrams together. 
 * You can return the answer in any order.
 * 
 * An Anagram is a word or phrase formed by rearranging the letters of a different 
 * word or phrase, typically using all the original letters exactly once.
 * 
 * Example:
 * Input: strs = ["eat","tea","tan","ate","nat","bat"]
 * Output: [["bat"],["nat","tan"],["ate","eat","tea"]]
 */
public class GroupAnagrams {
    
    /**
     * Approach 1: Sort Each String (Optimal)
     * Time Complexity: O(n * k log k) - n strings, k is max length
     * Space Complexity: O(n * k) - Store all strings in map
     * 
     * Algorithm:
     * 1. For each string, sort its characters
     * 2. Use sorted string as key in HashMap
     * 3. Group strings with same sorted form
     */
    public List<List<String>> groupAnagramsSorting(String[] strs) {
        Map<String, List<String>> map = new HashMap<>();
        
        for (String str : strs) {
            // Sort the string to create key
            char[] chars = str.toCharArray();
            Arrays.sort(chars);
            String key = String.valueOf(chars);
            
            // Add to the group
            map.computeIfAbsent(key, k -> new ArrayList<>()).add(str);
        }
        
        return new ArrayList<>(map.values());
    }
    
    /**
     * Approach 2: Character Count Array
     * Time Complexity: O(n * k) - n strings, k is max length
     * Space Complexity: O(n * k)
     * 
     * Algorithm:
     * 1. Count frequency of each character
     * 2. Use frequency array as key
     * 3. More efficient than sorting for long strings
     */
    public List<List<String>> groupAnagramsCounting(String[] strs) {
        Map<String, List<String>> map = new HashMap<>();
        
        for (String str : strs) {
            // Count character frequencies
            int[] count = new int[26]; // Assuming lowercase letters only
            for (char c : str.toCharArray()) {
                count[c - 'a']++;
            }
            
            // Create key from count array
            StringBuilder keyBuilder = new StringBuilder();
            for (int i = 0; i < 26; i++) {
                if (count[i] > 0) {
                    keyBuilder.append((char)('a' + i)).append(count[i]);
                }
            }
            String key = keyBuilder.toString();
            
            map.computeIfAbsent(key, k -> new ArrayList<>()).add(str);
        }
        
        return new ArrayList<>(map.values());
    }
    
    /**
     * Approach 3: Prime Number Hash
     * Time Complexity: O(n * k) - n strings, k is max length
     * Space Complexity: O(n * k)
     * 
     * Algorithm:
     * 1. Assign each letter a unique prime number
     * 2. Product of primes will be unique for anagrams
     * 3. Use product as key
     * 
     * Note: May overflow for very long strings
     */
    public List<List<String>> groupAnagramsPrime(String[] strs) {
        // First 26 prime numbers for a-z
        int[] primes = {2, 3, 5, 7, 11, 13, 17, 19, 23, 29, 31, 37, 41, 
                       43, 47, 53, 59, 61, 67, 71, 73, 79, 83, 89, 97, 101};
        
        Map<Long, List<String>> map = new HashMap<>();
        
        for (String str : strs) {
            long key = 1;
            for (char c : str.toCharArray()) {
                key *= primes[c - 'a'];
            }
            
            map.computeIfAbsent(key, k -> new ArrayList<>()).add(str);
        }
        
        return new ArrayList<>(map.values());
    }
    
    /**
     * Approach 4: Custom Hash Function
     * Time Complexity: O(n * k)
     * Space Complexity: O(n * k)
     * 
     * Uses a custom hash that considers character frequency
     */
    public List<List<String>> groupAnagramsCustomHash(String[] strs) {
        Map<String, List<String>> map = new HashMap<>();
        
        for (String str : strs) {
            String key = getAnagramKey(str);
            map.computeIfAbsent(key, k -> new ArrayList<>()).add(str);
        }
        
        return new ArrayList<>(map.values());
    }
    
    private String getAnagramKey(String str) {
        int[] count = new int[26];
        for (char c : str.toCharArray()) {
            count[c - 'a']++;
        }
        
        // Create a unique string representation
        StringBuilder key = new StringBuilder();
        for (int i = 0; i < 26; i++) {
            if (count[i] > 0) {
                key.append('#').append(count[i]);
            }
        }
        
        return key.toString();
    }
    
    /**
     * Approach 5: Using TreeMap for Sorted Output
     * Time Complexity: O(n * k log k + n log n)
     * Space Complexity: O(n * k)
     * 
     * Returns groups in lexicographic order
     */
    public List<List<String>> groupAnagramsSorted(String[] strs) {
        Map<String, List<String>> map = new TreeMap<>();
        
        for (String str : strs) {
            char[] chars = str.toCharArray();
            Arrays.sort(chars);
            String key = String.valueOf(chars);
            
            map.computeIfAbsent(key, k -> new ArrayList<>()).add(str);
        }
        
        // Sort each group
        List<List<String>> result = new ArrayList<>();
        for (List<String> group : map.values()) {
            Collections.sort(group);
            result.add(group);
        }
        
        return result;
    }
    
    /**
     * Extended Problem: Group by Pattern
     * Group strings that follow the same character pattern
     * Example: "abc" and "xyz" have same pattern
     */
    public List<List<String>> groupByPattern(String[] strs) {
        Map<String, List<String>> map = new HashMap<>();
        
        for (String str : strs) {
            String pattern = getPattern(str);
            map.computeIfAbsent(pattern, k -> new ArrayList<>()).add(str);
        }
        
        return new ArrayList<>(map.values());
    }
    
    private String getPattern(String str) {
        Map<Character, Integer> charToIndex = new HashMap<>();
        StringBuilder pattern = new StringBuilder();
        int index = 0;
        
        for (char c : str.toCharArray()) {
            if (!charToIndex.containsKey(c)) {
                charToIndex.put(c, index++);
            }
            pattern.append(charToIndex.get(c)).append(',');
        }
        
        return pattern.toString();
    }
    
    /**
     * Extended Problem: Find All Anagram Pairs
     * Return all pairs of strings that are anagrams
     */
    public List<List<String>> findAnagramPairs(String[] strs) {
        List<List<String>> result = new ArrayList<>();
        Map<String, List<Integer>> map = new HashMap<>();
        
        // Group by sorted string
        for (int i = 0; i < strs.length; i++) {
            char[] chars = strs[i].toCharArray();
            Arrays.sort(chars);
            String key = String.valueOf(chars);
            
            map.computeIfAbsent(key, k -> new ArrayList<>()).add(i);
        }
        
        // Find all pairs
        for (List<Integer> indices : map.values()) {
            if (indices.size() > 1) {
                for (int i = 0; i < indices.size(); i++) {
                    for (int j = i + 1; j < indices.size(); j++) {
                        result.add(Arrays.asList(
                            strs[indices.get(i)], 
                            strs[indices.get(j)]
                        ));
                    }
                }
            }
        }
        
        return result;
    }
    
    // Helper method to print result
    private static void printResult(List<List<String>> result) {
        System.out.print("[");
        for (int i = 0; i < result.size(); i++) {
            System.out.print(result.get(i));
            if (i < result.size() - 1) System.out.print(", ");
        }
        System.out.println("]");
    }
    
    // Test the solutions
    public static void main(String[] args) {
        GroupAnagrams solution = new GroupAnagrams();
        
        // Test case 1
        String[] strs1 = {"eat", "tea", "tan", "ate", "nat", "bat"};
        System.out.println("Test Case 1: [\"eat\",\"tea\",\"tan\",\"ate\",\"nat\",\"bat\"]");
        
        System.out.print("Sorting approach: ");
        printResult(solution.groupAnagramsSorting(strs1));
        
        System.out.print("Counting approach: ");
        printResult(solution.groupAnagramsCounting(strs1));
        
        System.out.print("Prime approach: ");
        printResult(solution.groupAnagramsPrime(strs1));
        
        // Test case 2: Empty string
        String[] strs2 = {""};
        System.out.println("\nTest Case 2: [\"\"]");
        System.out.print("Result: ");
        printResult(solution.groupAnagramsSorting(strs2));
        
        // Test case 3: Single character
        String[] strs3 = {"a"};
        System.out.println("\nTest Case 3: [\"a\"]");
        System.out.print("Result: ");
        printResult(solution.groupAnagramsSorting(strs3));
        
        // Test pattern grouping
        String[] strs4 = {"abc", "xyz", "bcd", "aaa", "abb"};
        System.out.println("\nTest Case 4: Pattern grouping");
        System.out.print("Pattern groups: ");
        printResult(solution.groupByPattern(strs4));
        
        // Test anagram pairs
        System.out.println("\nAnagram pairs from test case 1:");
        List<List<String>> pairs = solution.findAnagramPairs(strs1);
        for (List<String> pair : pairs) {
            System.out.println(pair);
        }
    }
} 