package leetcode.twopointers;

import java.util.*;

/**
 * LeetCode 125: Valid Palindrome
 * 
 * A phrase is a palindrome if, after converting all uppercase letters into lowercase letters
 * and removing all non-alphanumeric characters, it reads the same forward and backward.
 * 
 * Alphanumeric characters include letters and numbers.
 * 
 * Given a string s, return true if it is a palindrome, or false otherwise.
 * 
 * Example:
 * Input: s = "A man, a plan, a canal: Panama"
 * Output: true
 * Explanation: "amanaplanacanalpanama" is a palindrome.
 */
public class ValidPalindrome {
    
    /**
     * Approach 1: Clean String and Compare
     * Time Complexity: O(n) - Two passes through string
     * Space Complexity: O(n) - Extra space for cleaned string
     * 
     * Algorithm:
     * 1. Clean the string by removing non-alphanumeric characters and converting to lowercase
     * 2. Check if cleaned string equals its reverse
     */
    public boolean isPalindromeCleanString(String s) {
        StringBuilder cleaned = new StringBuilder();
        
        // Clean the string
        for (char c : s.toCharArray()) {
            if (Character.isLetterOrDigit(c)) {
                cleaned.append(Character.toLowerCase(c));
            }
        }
        
        String cleanedStr = cleaned.toString();
        String reversedStr = cleaned.reverse().toString();
        
        return cleanedStr.equals(reversedStr);
    }
    
    /**
     * Approach 2: Two Pointers (Optimal)
     * Time Complexity: O(n) - Single pass
     * Space Complexity: O(1) - Only two pointers
     * 
     * Algorithm:
     * 1. Use two pointers from start and end
     * 2. Skip non-alphanumeric characters
     * 3. Compare characters after converting to lowercase
     * 4. Move pointers towards center
     */
    public boolean isPalindrome(String s) {
        int left = 0;
        int right = s.length() - 1;
        
        while (left < right) {
            // Skip non-alphanumeric characters from left
            while (left < right && !Character.isLetterOrDigit(s.charAt(left))) {
                left++;
            }
            
            // Skip non-alphanumeric characters from right
            while (left < right && !Character.isLetterOrDigit(s.charAt(right))) {
                right--;
            }
            
            // Compare characters (case-insensitive)
            if (Character.toLowerCase(s.charAt(left)) != 
                Character.toLowerCase(s.charAt(right))) {
                return false;
            }
            
            left++;
            right--;
        }
        
        return true;
    }
    
    /**
     * Approach 3: Two Pointers with Helper Methods
     * Time Complexity: O(n)
     * Space Complexity: O(1)
     * 
     * Clean implementation with helper methods
     */
    public boolean isPalindromeCleaner(String s) {
        int left = 0;
        int right = s.length() - 1;
        
        while (left < right) {
            left = skipNonAlphanumeric(s, left, 1);
            right = skipNonAlphanumeric(s, right, -1);
            
            if (left < s.length() && right >= 0) {
                if (!isSameCharIgnoreCase(s.charAt(left), s.charAt(right))) {
                    return false;
                }
                left++;
                right--;
            }
        }
        
        return true;
    }
    
    private int skipNonAlphanumeric(String s, int index, int direction) {
        while (index >= 0 && index < s.length() && 
               !Character.isLetterOrDigit(s.charAt(index))) {
            index += direction;
        }
        return index;
    }
    
    private boolean isSameCharIgnoreCase(char a, char b) {
        return Character.toLowerCase(a) == Character.toLowerCase(b);
    }
    
    /**
     * Approach 4: Recursive Solution
     * Time Complexity: O(n)
     * Space Complexity: O(n) - Recursion stack
     * 
     * Recursive approach for educational purposes
     */
    public boolean isPalindromeRecursive(String s) {
        return isPalindromeRecursiveHelper(s, 0, s.length() - 1);
    }
    
    private boolean isPalindromeRecursiveHelper(String s, int left, int right) {
        // Base case
        if (left >= right) {
            return true;
        }
        
        // Skip non-alphanumeric from left
        if (!Character.isLetterOrDigit(s.charAt(left))) {
            return isPalindromeRecursiveHelper(s, left + 1, right);
        }
        
        // Skip non-alphanumeric from right
        if (!Character.isLetterOrDigit(s.charAt(right))) {
            return isPalindromeRecursiveHelper(s, left, right - 1);
        }
        
        // Compare current characters
        if (Character.toLowerCase(s.charAt(left)) != 
            Character.toLowerCase(s.charAt(right))) {
            return false;
        }
        
        // Recurse for inner substring
        return isPalindromeRecursiveHelper(s, left + 1, right - 1);
    }
    
    /**
     * Extended Problem: Valid Palindrome II
     * LeetCode 680: Check if string can be palindrome by removing at most one character
     */
    public boolean validPalindromeII(String s) {
        int left = 0;
        int right = s.length() - 1;
        
        while (left < right) {
            if (s.charAt(left) != s.charAt(right)) {
                // Try removing either left or right character
                return isPalindromeRange(s, left + 1, right) || 
                       isPalindromeRange(s, left, right - 1);
            }
            left++;
            right--;
        }
        
        return true; // Already a palindrome
    }
    
    private boolean isPalindromeRange(String s, int left, int right) {
        while (left < right) {
            if (s.charAt(left) != s.charAt(right)) {
                return false;
            }
            left++;
            right--;
        }
        return true;
    }
    
    /**
     * Extended Problem: Longest Palindromic Substring
     * LeetCode 5: Find the longest palindromic substring
     */
    public String longestPalindrome(String s) {
        if (s == null || s.length() < 2) {
            return s;
        }
        
        int start = 0;
        int maxLength = 1;
        
        for (int i = 0; i < s.length(); i++) {
            // Check for odd-length palindromes (center at i)
            int len1 = expandAroundCenter(s, i, i);
            // Check for even-length palindromes (center between i and i+1)
            int len2 = expandAroundCenter(s, i, i + 1);
            
            int currentMax = Math.max(len1, len2);
            if (currentMax > maxLength) {
                maxLength = currentMax;
                start = i - (currentMax - 1) / 2;
            }
        }
        
        return s.substring(start, start + maxLength);
    }
    
    private int expandAroundCenter(String s, int left, int right) {
        while (left >= 0 && right < s.length() && s.charAt(left) == s.charAt(right)) {
            left--;
            right++;
        }
        return right - left - 1;
    }
    
    /**
     * Extended Problem: Palindrome Partitioning
     * LeetCode 131: Partition string into palindromic substrings
     */
    public List<List<String>> partition(String s) {
        List<List<String>> result = new ArrayList<>();
        List<String> current = new ArrayList<>();
        partitionHelper(s, 0, current, result);
        return result;
    }
    
    private void partitionHelper(String s, int start, List<String> current, 
                                List<List<String>> result) {
        if (start == s.length()) {
            result.add(new ArrayList<>(current));
            return;
        }
        
        for (int end = start; end < s.length(); end++) {
            String substring = s.substring(start, end + 1);
            if (isPalindromeRange(substring, 0, substring.length() - 1)) {
                current.add(substring);
                partitionHelper(s, end + 1, current, result);
                current.remove(current.size() - 1);
            }
        }
    }
    
    // Test the solutions
    public static void main(String[] args) {
        ValidPalindrome solution = new ValidPalindrome();
        
        // Test case 1: Valid palindrome
        String s1 = "A man, a plan, a canal: Panama";
        System.out.println("Test Case 1: \"" + s1 + "\"");
        System.out.println("Clean String: " + solution.isPalindromeCleanString(s1));
        System.out.println("Two Pointers: " + solution.isPalindrome(s1));
        System.out.println("Cleaner: " + solution.isPalindromeCleaner(s1));
        System.out.println("Recursive: " + solution.isPalindromeRecursive(s1));
        
        // Test case 2: Not a palindrome
        String s2 = "race a car";
        System.out.println("\nTest Case 2: \"" + s2 + "\"");
        System.out.println("Two Pointers: " + solution.isPalindrome(s2));
        
        // Test case 3: Empty or single character
        String s3 = " ";
        System.out.println("\nTest Case 3: \"" + s3 + "\"");
        System.out.println("Two Pointers: " + solution.isPalindrome(s3));
        
        // Test case 4: Numbers and letters
        String s4 = "Madam, I'm Adam";
        System.out.println("\nTest Case 4: \"" + s4 + "\"");
        System.out.println("Two Pointers: " + solution.isPalindrome(s4));
        
        // Test extended problems
        String s5 = "abc";
        System.out.println("\nValid Palindrome II:");
        System.out.println("\"" + s5 + "\" can be palindrome by removing 1 char: " + 
                          solution.validPalindromeII(s5));
        
        String s6 = "babad";
        System.out.println("\nLongest Palindromic Substring:");
        System.out.println("\"" + s6 + "\" -> \"" + solution.longestPalindrome(s6) + "\"");
        
        String s7 = "aab";
        System.out.println("\nPalindrome Partitioning:");
        System.out.println("\"" + s7 + "\" -> " + solution.partition(s7));
    }
} 