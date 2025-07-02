package leetcode.dp;

import java.util.*;

/**
 * LeetCode 139: Word Break
 * 
 * Given a string s and a dictionary of strings wordDict, return true if s 
 * can be segmented into a space-separated sequence of one or more dictionary words.
 * 
 * Example:
 * Input: s = "leetcode", wordDict = ["leet","code"]
 * Output: true
 */
public class WordBreak {
    
    /**
     * Approach 1: DP (Bottom-up)
     * Time: O(n² + m*k) where n = s.length, m = wordDict.size, k = avg word length
     * Space: O(n + m*k)
     */
    public boolean wordBreak(String s, List<String> wordDict) {
        Set<String> wordSet = new HashSet<>(wordDict);
        boolean[] dp = new boolean[s.length() + 1];
        dp[0] = true; // Empty string can always be segmented
        
        for (int i = 1; i <= s.length(); i++) {
            for (int j = 0; j < i; j++) {
                if (dp[j] && wordSet.contains(s.substring(j, i))) {
                    dp[i] = true;
                    break;
                }
            }
        }
        
        return dp[s.length()];
    }
    
    /**
     * Approach 2: Memoization (Top-down)
     * Time: O(n²), Space: O(n)
     */
    public boolean wordBreakMemo(String s, List<String> wordDict) {
        Set<String> wordSet = new HashSet<>(wordDict);
        Boolean[] memo = new Boolean[s.length()];
        return helper(s, 0, wordSet, memo);
    }
    
    private boolean helper(String s, int start, Set<String> wordSet, Boolean[] memo) {
        if (start == s.length()) return true;
        if (memo[start] != null) return memo[start];
        
        for (int end = start + 1; end <= s.length(); end++) {
            String word = s.substring(start, end);
            if (wordSet.contains(word) && helper(s, end, wordSet, memo)) {
                memo[start] = true;
                return true;
            }
        }
        
        memo[start] = false;
        return false;
    }
    
    /**
     * Extended Problem: Word Break II (LC 140)
     * Return all possible sentences
     */
    public List<String> wordBreakII(String s, List<String> wordDict) {
        Map<String, List<String>> memo = new HashMap<>();
        return helper(s, new HashSet<>(wordDict), memo);
    }
    
    private List<String> helper(String s, Set<String> wordSet, Map<String, List<String>> memo) {
        if (memo.containsKey(s)) return memo.get(s);
        
        List<String> result = new ArrayList<>();
        
        if (s.isEmpty()) {
            result.add("");
            return result;
        }
        
        for (String word : wordSet) {
            if (s.startsWith(word)) {
                List<String> sublist = helper(s.substring(word.length()), wordSet, memo);
                for (String sub : sublist) {
                    result.add(word + (sub.isEmpty() ? "" : " ") + sub);
                }
            }
        }
        
        memo.put(s, result);
        return result;
    }
    
    /**
     * Extended Problem: Count number of ways to break the word
     */
    public int wordBreakCount(String s, List<String> wordDict) {
        Set<String> wordSet = new HashSet<>(wordDict);
        int[] dp = new int[s.length() + 1];
        dp[0] = 1; // One way to break empty string
        
        for (int i = 1; i <= s.length(); i++) {
            for (int j = 0; j < i; j++) {
                if (dp[j] > 0 && wordSet.contains(s.substring(j, i))) {
                    dp[i] += dp[j];
                }
            }
        }
        
        return dp[s.length()];
    }
    
    // Test
    public static void main(String[] args) {
        WordBreak solution = new WordBreak();
        
        // Test case 1
        String s1 = "leetcode";
        List<String> wordDict1 = Arrays.asList("leet", "code");
        System.out.println("Can break '" + s1 + "': " + solution.wordBreak(s1, wordDict1));
        System.out.println("All ways: " + solution.wordBreakII(s1, wordDict1));
        System.out.println("Count: " + solution.wordBreakCount(s1, wordDict1));
        
        // Test case 2
        String s2 = "catsandog";
        List<String> wordDict2 = Arrays.asList("cats", "dog", "sand", "and", "cat");
        System.out.println("Can break '" + s2 + "': " + solution.wordBreak(s2, wordDict2));
    }
} 