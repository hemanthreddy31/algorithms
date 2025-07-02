package leetcode.slidingwindow;

import java.util.*;

/**
 * LeetCode 3: Longest Substring Without Repeating Characters
 * 
 * Given a string s, find the length of the longest substring without repeating characters.
 * 
 * Example:
 * Input: s = "abcabcbb"
 * Output: 3
 * Explanation: The answer is "abc", with the length of 3.
 */
public class LongestSubstringWithoutRepeating {
    
    /**
     * Approach 1: Brute Force
     * Time Complexity: O(n³) - Check all substrings
     * Space Complexity: O(min(n, m)) - m is size of charset
     * 
     * Check every possible substring
     */
    public int lengthOfLongestSubstringBruteForce(String s) {
        int n = s.length();
        int maxLength = 0;
        
        for (int i = 0; i < n; i++) {
            for (int j = i; j < n; j++) {
                if (hasUniqueCharacters(s, i, j)) {
                    maxLength = Math.max(maxLength, j - i + 1);
                }
            }
        }
        
        return maxLength;
    }
    
    private boolean hasUniqueCharacters(String s, int start, int end) {
        Set<Character> chars = new HashSet<>();
        for (int i = start; i <= end; i++) {
            if (chars.contains(s.charAt(i))) {
                return false;
            }
            chars.add(s.charAt(i));
        }
        return true;
    }
    
    /**
     * Approach 2: Sliding Window with HashSet
     * Time Complexity: O(2n) = O(n) - In worst case, each character visited twice
     * Space Complexity: O(min(n, m)) - m is size of charset
     * 
     * Use two pointers to maintain a sliding window
     */
    public int lengthOfLongestSubstringSet(String s) {
        int n = s.length();
        Set<Character> set = new HashSet<>();
        int maxLength = 0;
        int left = 0;
        
        for (int right = 0; right < n; right++) {
            // Remove characters from left until no duplicate
            while (set.contains(s.charAt(right))) {
                set.remove(s.charAt(left));
                left++;
            }
            
            set.add(s.charAt(right));
            maxLength = Math.max(maxLength, right - left + 1);
        }
        
        return maxLength;
    }
    
    /**
     * Approach 3: Sliding Window with HashMap (Optimal)
     * Time Complexity: O(n) - Single pass
     * Space Complexity: O(min(n, m)) - m is size of charset
     * 
     * Use HashMap to store character's last index
     */
    public int lengthOfLongestSubstring(String s) {
        Map<Character, Integer> charIndex = new HashMap<>();
        int maxLength = 0;
        int left = 0;
        
        for (int right = 0; right < s.length(); right++) {
            char c = s.charAt(right);
            
            // If character seen before and is in current window
            if (charIndex.containsKey(c)) {
                // Move left pointer to skip the duplicate
                left = Math.max(left, charIndex.get(c) + 1);
            }
            
            charIndex.put(c, right);
            maxLength = Math.max(maxLength, right - left + 1);
        }
        
        return maxLength;
    }
    
    /**
     * Approach 4: Optimized for ASCII (Array instead of HashMap)
     * Time Complexity: O(n)
     * Space Complexity: O(1) - Fixed size array
     * 
     * Use array for ASCII characters
     */
    public int lengthOfLongestSubstringArray(String s) {
        int[] charIndex = new int[128]; // ASCII characters
        Arrays.fill(charIndex, -1);
        
        int maxLength = 0;
        int left = 0;
        
        for (int right = 0; right < s.length(); right++) {
            char c = s.charAt(right);
            
            if (charIndex[c] >= left) {
                left = charIndex[c] + 1;
            }
            
            charIndex[c] = right;
            maxLength = Math.max(maxLength, right - left + 1);
        }
        
        return maxLength;
    }
    
    /**
     * Extended Problem: Longest Substring with At Most Two Distinct Characters
     * LeetCode 159 (Premium)
     */
    public int lengthOfLongestSubstringTwoDistinct(String s) {
        Map<Character, Integer> charCount = new HashMap<>();
        int maxLength = 0;
        int left = 0;
        
        for (int right = 0; right < s.length(); right++) {
            char c = s.charAt(right);
            charCount.put(c, charCount.getOrDefault(c, 0) + 1);
            
            // Shrink window if more than 2 distinct characters
            while (charCount.size() > 2) {
                char leftChar = s.charAt(left);
                charCount.put(leftChar, charCount.get(leftChar) - 1);
                if (charCount.get(leftChar) == 0) {
                    charCount.remove(leftChar);
                }
                left++;
            }
            
            maxLength = Math.max(maxLength, right - left + 1);
        }
        
        return maxLength;
    }
    
    /**
     * Extended Problem: Longest Substring with At Most K Distinct Characters
     * LeetCode 340 (Premium)
     */
    public int lengthOfLongestSubstringKDistinct(String s, int k) {
        if (k == 0) return 0;
        
        Map<Character, Integer> charCount = new HashMap<>();
        int maxLength = 0;
        int left = 0;
        
        for (int right = 0; right < s.length(); right++) {
            char c = s.charAt(right);
            charCount.put(c, charCount.getOrDefault(c, 0) + 1);
            
            // Shrink window if more than k distinct characters
            while (charCount.size() > k) {
                char leftChar = s.charAt(left);
                charCount.put(leftChar, charCount.get(leftChar) - 1);
                if (charCount.get(leftChar) == 0) {
                    charCount.remove(leftChar);
                }
                left++;
            }
            
            maxLength = Math.max(maxLength, right - left + 1);
        }
        
        return maxLength;
    }
    
    /**
     * Extended Problem: Minimum Window Substring
     * LeetCode 76: Find minimum window containing all characters of t
     */
    public String minWindow(String s, String t) {
        if (s.length() < t.length()) return "";
        
        Map<Character, Integer> tCount = new HashMap<>();
        Map<Character, Integer> windowCount = new HashMap<>();
        
        // Count characters in t
        for (char c : t.toCharArray()) {
            tCount.put(c, tCount.getOrDefault(c, 0) + 1);
        }
        
        int left = 0;
        int formed = 0;
        int required = tCount.size();
        int minLength = Integer.MAX_VALUE;
        int minLeft = 0;
        
        for (int right = 0; right < s.length(); right++) {
            char c = s.charAt(right);
            windowCount.put(c, windowCount.getOrDefault(c, 0) + 1);
            
            if (tCount.containsKey(c) && 
                windowCount.get(c).intValue() == tCount.get(c).intValue()) {
                formed++;
            }
            
            // Try to shrink window
            while (formed == required && left <= right) {
                // Update result if smaller window found
                if (right - left + 1 < minLength) {
                    minLength = right - left + 1;
                    minLeft = left;
                }
                
                char leftChar = s.charAt(left);
                windowCount.put(leftChar, windowCount.get(leftChar) - 1);
                
                if (tCount.containsKey(leftChar) && 
                    windowCount.get(leftChar) < tCount.get(leftChar)) {
                    formed--;
                }
                
                left++;
            }
        }
        
        return minLength == Integer.MAX_VALUE ? "" : 
               s.substring(minLeft, minLeft + minLength);
    }
    
    /**
     * Helper: Get the actual longest substring (not just length)
     */
    public String getLongestSubstring(String s) {
        Map<Character, Integer> charIndex = new HashMap<>();
        int maxLength = 0;
        int maxStart = 0;
        int left = 0;
        
        for (int right = 0; right < s.length(); right++) {
            char c = s.charAt(right);
            
            if (charIndex.containsKey(c)) {
                left = Math.max(left, charIndex.get(c) + 1);
            }
            
            charIndex.put(c, right);
            
            if (right - left + 1 > maxLength) {
                maxLength = right - left + 1;
                maxStart = left;
            }
        }
        
        return s.substring(maxStart, maxStart + maxLength);
    }
    
    // Test the solutions
    public static void main(String[] args) {
        LongestSubstringWithoutRepeating solution = new LongestSubstringWithoutRepeating();
        
        // Test basic problem
        String s1 = "abcabcbb";
        System.out.println("Test Case 1: s = \"" + s1 + "\"");
        System.out.println("Brute Force: " + solution.lengthOfLongestSubstringBruteForce(s1));
        System.out.println("Set: " + solution.lengthOfLongestSubstringSet(s1));
        System.out.println("HashMap: " + solution.lengthOfLongestSubstring(s1));
        System.out.println("Array: " + solution.lengthOfLongestSubstringArray(s1));
        System.out.println("Actual substring: \"" + solution.getLongestSubstring(s1) + "\"");
        
        // Test edge cases
        String s2 = "bbbbb";
        System.out.println("\nTest Case 2: s = \"" + s2 + "\"");
        System.out.println("Length: " + solution.lengthOfLongestSubstring(s2));
        
        String s3 = "pwwkew";
        System.out.println("\nTest Case 3: s = \"" + s3 + "\"");
        System.out.println("Length: " + solution.lengthOfLongestSubstring(s3));
        System.out.println("Actual substring: \"" + solution.getLongestSubstring(s3) + "\"");
        
        // Test extended problems
        String s4 = "eceba";
        System.out.println("\nLongest Substring with At Most 2 Distinct:");
        System.out.println("s = \"" + s4 + "\"");
        System.out.println("Length: " + solution.lengthOfLongestSubstringTwoDistinct(s4));
        
        String s5 = "aa";
        int k = 1;
        System.out.println("\nLongest Substring with At Most " + k + " Distinct:");
        System.out.println("s = \"" + s5 + "\"");
        System.out.println("Length: " + solution.lengthOfLongestSubstringKDistinct(s5, k));
        
        // Test minimum window substring
        String s6 = "ADOBECODEBANC";
        String t = "ABC";
        System.out.println("\nMinimum Window Substring:");
        System.out.println("s = \"" + s6 + "\", t = \"" + t + "\"");
        System.out.println("Min window: \"" + solution.minWindow(s6, t) + "\"");
    }
} 