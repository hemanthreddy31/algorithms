package leetcode.arrays;

import java.util.*;

/**
 * LeetCode 242: Valid Anagram
 * 
 * Given two strings s and t, return true if t is an anagram of s, and false otherwise.
 * An Anagram is a word or phrase formed by rearranging the letters of a different word or phrase,
 * typically using all the original letters exactly once.
 * 
 * Example:
 * Input: s = "anagram", t = "nagaram"
 * Output: true
 * 
 * Input: s = "rat", t = "car"
 * Output: false
 */
public class ValidAnagram {
    
    /**
     * Approach 1: Sorting
     * Time Complexity: O(n log n) - Sorting dominates
     * Space Complexity: O(n) - For character arrays
     * 
     * Algorithm:
     * 1. Sort both strings
     * 2. Compare if they are equal
     */
    public boolean isAnagramSorting(String s, String t) {
        // Different lengths cannot be anagrams
        if (s.length() != t.length()) {
            return false;
        }
        
        // Convert to char arrays and sort
        char[] sArray = s.toCharArray();
        char[] tArray = t.toCharArray();
        Arrays.sort(sArray);
        Arrays.sort(tArray);
        
        // Compare sorted arrays
        return Arrays.equals(sArray, tArray);
    }
    
    /**
     * Approach 2: Character Count Array (Optimal for lowercase letters)
     * Time Complexity: O(n) - Single pass through both strings
     * Space Complexity: O(1) - Fixed size array of 26
     * 
     * Algorithm:
     * 1. Count frequency of each character in first string
     * 2. Decrement count for each character in second string
     * 3. All counts should be zero if anagram
     */
    public boolean isAnagramArray(String s, String t) {
        if (s.length() != t.length()) {
            return false;
        }
        
        // Frequency array for lowercase letters
        int[] count = new int[26];
        
        // Count characters in s and decrement for t
        for (int i = 0; i < s.length(); i++) {
            count[s.charAt(i) - 'a']++;
            count[t.charAt(i) - 'a']--;
        }
        
        // Check if all counts are zero
        for (int c : count) {
            if (c != 0) {
                return false;
            }
        }
        
        return true;
    }
    
    /**
     * Approach 3: HashMap (Flexible for any characters)
     * Time Complexity: O(n) - Single pass
     * Space Complexity: O(k) - k is number of unique characters
     * 
     * More flexible for Unicode characters
     */
    public boolean isAnagramHashMap(String s, String t) {
        if (s.length() != t.length()) {
            return false;
        }
        
        Map<Character, Integer> charCount = new HashMap<>();
        
        // Count characters in s
        for (char c : s.toCharArray()) {
            charCount.put(c, charCount.getOrDefault(c, 0) + 1);
        }
        
        // Decrement counts for characters in t
        for (char c : t.toCharArray()) {
            if (!charCount.containsKey(c)) {
                return false;
            }
            
            int count = charCount.get(c);
            if (count == 1) {
                charCount.remove(c);
            } else {
                charCount.put(c, count - 1);
            }
        }
        
        return charCount.isEmpty();
    }
    
    /**
     * Approach 4: Single Pass with Early Termination
     * Time Complexity: O(n) - Single pass
     * Space Complexity: O(1) - Fixed size array
     * 
     * Optimized to return early if not anagram
     */
    public boolean isAnagramOptimized(String s, String t) {
        if (s.length() != t.length()) {
            return false;
        }
        
        int[] count = new int[26];
        int uniqueChars = 0;
        int completedChars = 0;
        
        // Count characters in s
        for (char c : s.toCharArray()) {
            int index = c - 'a';
            if (count[index] == 0) {
                uniqueChars++;
            }
            count[index]++;
        }
        
        // Process characters in t
        for (char c : t.toCharArray()) {
            int index = c - 'a';
            if (count[index] == 0) {
                return false; // Character not in s
            }
            
            count[index]--;
            
            if (count[index] == 0) {
                completedChars++;
                if (completedChars == uniqueChars) {
                    // Early termination - all characters matched
                    return true;
                }
            }
        }
        
        return false;
    }
    
    /**
     * Extended Problem: Group Anagrams
     * Given an array of strings, group anagrams together
     */
    public List<List<String>> groupAnagrams(String[] strs) {
        Map<String, List<String>> map = new HashMap<>();
        
        for (String str : strs) {
            // Sort string to create key
            char[] chars = str.toCharArray();
            Arrays.sort(chars);
            String key = String.valueOf(chars);
            
            map.computeIfAbsent(key, k -> new ArrayList<>()).add(str);
        }
        
        return new ArrayList<>(map.values());
    }
    
    /**
     * Extended Problem: Find All Anagrams in a String
     * LeetCode 438: Return start indices of all anagrams of p in s
     */
    public List<Integer> findAnagrams(String s, String p) {
        List<Integer> result = new ArrayList<>();
        
        if (s.length() < p.length()) {
            return result;
        }
        
        // Character frequency maps
        int[] pCount = new int[26];
        int[] windowCount = new int[26];
        
        // Count characters in p
        for (char c : p.toCharArray()) {
            pCount[c - 'a']++;
        }
        
        // Sliding window
        for (int i = 0; i < s.length(); i++) {
            // Add current character to window
            windowCount[s.charAt(i) - 'a']++;
            
            // Remove character outside window
            if (i >= p.length()) {
                windowCount[s.charAt(i - p.length()) - 'a']--;
            }
            
            // Check if window is anagram
            if (i >= p.length() - 1 && Arrays.equals(pCount, windowCount)) {
                result.add(i - p.length() + 1);
            }
        }
        
        return result;
    }
    
    /**
     * Extended Problem: Check if two strings are k-anagrams
     * Two strings are k-anagrams if they can become anagrams by changing at most k characters
     */
    public boolean areKAnagrams(String s, String t, int k) {
        if (s.length() != t.length()) {
            return false;
        }
        
        int[] count = new int[26];
        
        // Count character differences
        for (int i = 0; i < s.length(); i++) {
            count[s.charAt(i) - 'a']++;
            count[t.charAt(i) - 'a']--;
        }
        
        // Count positive differences (characters to change)
        int changes = 0;
        for (int c : count) {
            if (c > 0) {
                changes += c;
            }
        }
        
        return changes <= k;
    }
    
    // Test the solutions
    public static void main(String[] args) {
        ValidAnagram solution = new ValidAnagram();
        
        // Test basic anagram checking
        String s1 = "anagram";
        String t1 = "nagaram";
        System.out.println("Test Case 1: s = \"" + s1 + "\", t = \"" + t1 + "\"");
        System.out.println("Sorting: " + solution.isAnagramSorting(s1, t1));
        System.out.println("Array: " + solution.isAnagramArray(s1, t1));
        System.out.println("HashMap: " + solution.isAnagramHashMap(s1, t1));
        System.out.println("Optimized: " + solution.isAnagramOptimized(s1, t1));
        
        // Test non-anagram
        String s2 = "rat";
        String t2 = "car";
        System.out.println("\nTest Case 2: s = \"" + s2 + "\", t = \"" + t2 + "\"");
        System.out.println("Result: " + solution.isAnagramArray(s2, t2));
        
        // Test group anagrams
        String[] strs = {"eat", "tea", "tan", "ate", "nat", "bat"};
        System.out.println("\nGroup Anagrams:");
        System.out.println("Input: " + Arrays.toString(strs));
        System.out.println("Groups: " + solution.groupAnagrams(strs));
        
        // Test find all anagrams
        String s3 = "cbaebabacd";
        String p3 = "abc";
        System.out.println("\nFind All Anagrams:");
        System.out.println("s = \"" + s3 + "\", p = \"" + p3 + "\"");
        System.out.println("Start indices: " + solution.findAnagrams(s3, p3));
        
        // Test k-anagrams
        String s4 = "anagram";
        String t4 = "grammar";
        int k = 3;
        System.out.println("\nK-Anagrams (k = " + k + "):");
        System.out.println("s = \"" + s4 + "\", t = \"" + t4 + "\"");
        System.out.println("Are k-anagrams: " + solution.areKAnagrams(s4, t4, k));
    }
} 