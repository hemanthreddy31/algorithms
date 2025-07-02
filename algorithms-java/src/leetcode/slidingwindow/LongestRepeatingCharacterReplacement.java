package leetcode.slidingwindow;

import java.util.*;

/**
 * LeetCode 424: Longest Repeating Character Replacement
 * 
 * You are given a string s and an integer k. You can choose any character of the string
 * and change it to any other uppercase English letter. You can perform this operation
 * at most k times.
 * 
 * Return the length of the longest substring containing the same letter you can get
 * after performing the above operations.
 * 
 * Example:
 * Input: s = "ABAB", k = 2
 * Output: 4
 * Explanation: Replace the two 'A's with two 'B's or vice versa.
 */
public class LongestRepeatingCharacterReplacement {
    
    /**
     * Approach 1: Brute Force with Fixed Character
     * Time Complexity: O(26 * n²) - For each character, check all substrings
     * Space Complexity: O(1) - Only counter variables
     * 
     * Try fixing each character and find longest valid substring
     */
    public int characterReplacementBruteForce(String s, int k) {
        int maxLength = 0;
        
        // Try each character from 'A' to 'Z'
        for (char targetChar = 'A'; targetChar <= 'Z'; targetChar++) {
            // For current target character, find longest valid substring
            for (int i = 0; i < s.length(); i++) {
                int changes = 0;
                for (int j = i; j < s.length(); j++) {
                    if (s.charAt(j) != targetChar) {
                        changes++;
                    }
                    
                    if (changes <= k) {
                        maxLength = Math.max(maxLength, j - i + 1);
                    } else {
                        break; // Too many changes needed
                    }
                }
            }
        }
        
        return maxLength;
    }
    
    /**
     * Approach 2: Sliding Window for Each Character
     * Time Complexity: O(26 * n) - For each character, one pass
     * Space Complexity: O(1) - Only counter variables
     * 
     * For each character, use sliding window to find longest valid substring
     */
    public int characterReplacementPerChar(String s, int k) {
        int maxLength = 0;
        
        // Try each character as the target
        for (char targetChar = 'A'; targetChar <= 'Z'; targetChar++) {
            int left = 0;
            int changes = 0;
            
            for (int right = 0; right < s.length(); right++) {
                // Expand window
                if (s.charAt(right) != targetChar) {
                    changes++;
                }
                
                // Shrink window if too many changes
                while (changes > k) {
                    if (s.charAt(left) != targetChar) {
                        changes--;
                    }
                    left++;
                }
                
                // Update max length
                maxLength = Math.max(maxLength, right - left + 1);
            }
        }
        
        return maxLength;
    }
    
    /**
     * Approach 3: Sliding Window with Frequency Map (Optimal)
     * Time Complexity: O(n) - Single pass with constant work per character
     * Space Complexity: O(1) - Fixed size frequency array
     * 
     * Key insight: We need to find the longest window where
     * (window_size - count_of_most_frequent_char) <= k
     */
    public int characterReplacement(String s, int k) {
        int[] charCount = new int[26]; // Frequency of each character
        int left = 0;
        int maxCount = 0;  // Count of most frequent character in current window
        int maxLength = 0;
        
        for (int right = 0; right < s.length(); right++) {
            // Expand window: add right character
            int rightChar = s.charAt(right) - 'A';
            charCount[rightChar]++;
            
            // Update max count of any character in current window
            maxCount = Math.max(maxCount, charCount[rightChar]);
            
            // If window is invalid (need more than k changes)
            while (right - left + 1 - maxCount > k) {
                // Shrink window: remove left character
                int leftChar = s.charAt(left) - 'A';
                charCount[leftChar]--;
                left++;
                
                // Note: We don't update maxCount here for optimization
                // maxCount might be higher than actual, but it doesn't affect correctness
            }
            
            // Update result
            maxLength = Math.max(maxLength, right - left + 1);
        }
        
        return maxLength;
    }
    
    /**
     * Approach 4: Sliding Window with Accurate Max Count
     * Time Complexity: O(n) - Single pass
     * Space Complexity: O(1) - Fixed size frequency array
     * 
     * This version always maintains accurate maxCount (slightly slower but clearer)
     */
    public int characterReplacementAccurate(String s, int k) {
        int[] charCount = new int[26];
        int left = 0;
        int maxLength = 0;
        
        for (int right = 0; right < s.length(); right++) {
            // Expand window
            charCount[s.charAt(right) - 'A']++;
            
            // Find current max count
            int maxCount = getMaxCount(charCount);
            
            // Shrink window if invalid
            while (right - left + 1 - maxCount > k) {
                charCount[s.charAt(left) - 'A']--;
                left++;
                maxCount = getMaxCount(charCount);
            }
            
            maxLength = Math.max(maxLength, right - left + 1);
        }
        
        return maxLength;
    }
    
    private int getMaxCount(int[] charCount) {
        int maxCount = 0;
        for (int count : charCount) {
            maxCount = Math.max(maxCount, count);
        }
        return maxCount;
    }
    
    /**
     * Approach 5: HashMap-based Solution
     * Time Complexity: O(n) - Single pass
     * Space Complexity: O(1) - At most 26 characters in map
     * 
     * Using HashMap instead of array (more general for any character set)
     */
    public int characterReplacementHashMap(String s, int k) {
        Map<Character, Integer> charCount = new HashMap<>();
        int left = 0;
        int maxCount = 0;
        int maxLength = 0;
        
        for (int right = 0; right < s.length(); right++) {
            char rightChar = s.charAt(right);
            charCount.put(rightChar, charCount.getOrDefault(rightChar, 0) + 1);
            maxCount = Math.max(maxCount, charCount.get(rightChar));
            
            // Shrink window if invalid
            while (right - left + 1 - maxCount > k) {
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
     * LeetCode 340: Find longest substring with at most k distinct characters
     */
    public int lengthOfLongestSubstringKDistinct(String s, int k) {
        if (k == 0) return 0;
        
        Map<Character, Integer> charCount = new HashMap<>();
        int left = 0;
        int maxLength = 0;
        
        for (int right = 0; right < s.length(); right++) {
            char rightChar = s.charAt(right);
            charCount.put(rightChar, charCount.getOrDefault(rightChar, 0) + 1);
            
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
     * Extended Problem: Max Consecutive Ones III
     * LeetCode 1004: Longest subarray of 1s after flipping at most k 0s
     */
    public int longestOnes(int[] nums, int k) {
        int left = 0;
        int zeroCount = 0;
        int maxLength = 0;
        
        for (int right = 0; right < nums.length; right++) {
            if (nums[right] == 0) {
                zeroCount++;
            }
            
            // Shrink window if too many zeros
            while (zeroCount > k) {
                if (nums[left] == 0) {
                    zeroCount--;
                }
                left++;
            }
            
            maxLength = Math.max(maxLength, right - left + 1);
        }
        
        return maxLength;
    }
    
    /**
     * Extended Problem: Fruit Into Baskets
     * LeetCode 904: Pick fruits from at most 2 types of trees
     */
    public int totalFruit(int[] fruits) {
        Map<Integer, Integer> basket = new HashMap<>();
        int left = 0;
        int maxFruits = 0;
        
        for (int right = 0; right < fruits.length; right++) {
            basket.put(fruits[right], basket.getOrDefault(fruits[right], 0) + 1);
            
            // Shrink window if more than 2 fruit types
            while (basket.size() > 2) {
                basket.put(fruits[left], basket.get(fruits[left]) - 1);
                if (basket.get(fruits[left]) == 0) {
                    basket.remove(fruits[left]);
                }
                left++;
            }
            
            maxFruits = Math.max(maxFruits, right - left + 1);
        }
        
        return maxFruits;
    }
    
    /**
     * Helper: Get substring for visualization
     */
    private String getLongestSubstring(String s, int k) {
        int[] charCount = new int[26];
        int left = 0;
        int maxCount = 0;
        int maxLength = 0;
        int bestLeft = 0;
        
        for (int right = 0; right < s.length(); right++) {
            charCount[s.charAt(right) - 'A']++;
            maxCount = Math.max(maxCount, charCount[s.charAt(right) - 'A']);
            
            while (right - left + 1 - maxCount > k) {
                charCount[s.charAt(left) - 'A']--;
                left++;
            }
            
            if (right - left + 1 > maxLength) {
                maxLength = right - left + 1;
                bestLeft = left;
            }
        }
        
        return s.substring(bestLeft, bestLeft + maxLength);
    }
    
    // Test the solutions
    public static void main(String[] args) {
        LongestRepeatingCharacterReplacement solution = new LongestRepeatingCharacterReplacement();
        
        // Test case 1: Standard example
        String s1 = "ABAB";
        int k1 = 2;
        System.out.println("Test Case 1: s = \"" + s1 + "\", k = " + k1);
        System.out.println("Brute Force: " + solution.characterReplacementBruteForce(s1, k1));
        System.out.println("Per Character: " + solution.characterReplacementPerChar(s1, k1));
        System.out.println("Optimal: " + solution.characterReplacement(s1, k1));
        System.out.println("HashMap: " + solution.characterReplacementHashMap(s1, k1));
        System.out.println("Longest substring: \"" + solution.getLongestSubstring(s1, k1) + "\"");
        
        // Test case 2: Another example
        String s2 = "AABABBA";
        int k2 = 1;
        System.out.println("\nTest Case 2: s = \"" + s2 + "\", k = " + k2);
        System.out.println("Optimal: " + solution.characterReplacement(s2, k2));
        System.out.println("Longest substring: \"" + solution.getLongestSubstring(s2, k2) + "\"");
        
        // Test case 3: All same characters
        String s3 = "AAAA";
        int k3 = 2;
        System.out.println("\nTest Case 3: s = \"" + s3 + "\", k = " + k3);
        System.out.println("Optimal: " + solution.characterReplacement(s3, k3));
        
        // Test case 4: k = 0
        String s4 = "ABCD";
        int k4 = 0;
        System.out.println("\nTest Case 4: s = \"" + s4 + "\", k = " + k4);
        System.out.println("Optimal: " + solution.characterReplacement(s4, k4));
        
        // Test extended problems
        System.out.println("\nExtended Problems:");
        
        // K distinct characters
        String s5 = "eceba";
        int k5 = 2;
        System.out.println("Longest Substring with " + k5 + " Distinct Characters:");
        System.out.println("\"" + s5 + "\" -> " + solution.lengthOfLongestSubstringKDistinct(s5, k5));
        
        // Max consecutive ones
        int[] nums1 = {1, 1, 1, 0, 0, 0, 1, 1, 1, 1, 0};
        int k6 = 2;
        System.out.println("Longest Ones after flipping " + k6 + " zeros:");
        System.out.println(Arrays.toString(nums1) + " -> " + solution.longestOnes(nums1, k6));
        
        // Fruit into baskets
        int[] fruits = {1, 2, 1};
        System.out.println("Total Fruit (max 2 types):");
        System.out.println(Arrays.toString(fruits) + " -> " + solution.totalFruit(fruits));
    }
} 