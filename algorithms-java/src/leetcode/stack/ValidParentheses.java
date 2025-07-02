package leetcode.stack;

import java.util.*;

/**
 * LeetCode 20: Valid Parentheses
 * 
 * Given a string s containing just the characters '(', ')', '{', '}', '[' and ']', 
 * determine if the input string is valid.
 * 
 * An input string is valid if:
 * 1. Open brackets must be closed by the same type of brackets.
 * 2. Open brackets must be closed in the correct order.
 * 3. Every close bracket has a corresponding open bracket of the same type.
 * 
 * Example:
 * Input: s = "()[]{}"
 * Output: true
 * 
 * Input: s = "([)]"
 * Output: false
 */
public class ValidParentheses {
    
    /**
     * Approach 1: Stack with HashMap (Clean and Extensible)
     * Time Complexity: O(n) - Single pass through the string
     * Space Complexity: O(n) - Stack can contain at most n/2 characters
     * 
     * Algorithm:
     * 1. Use a stack to keep track of opening brackets
     * 2. For each closing bracket, check if it matches the top of stack
     * 3. At the end, stack should be empty
     */
    public boolean isValidOptimal(String s) {
        // Map closing brackets to their corresponding opening brackets
        Map<Character, Character> mappings = new HashMap<>();
        mappings.put(')', '(');
        mappings.put('}', '{');
        mappings.put(']', '[');
        
        Stack<Character> stack = new Stack<>();
        
        for (char c : s.toCharArray()) {
            if (mappings.containsKey(c)) {
                // This is a closing bracket
                // Check if stack is empty or top doesn't match
                if (stack.isEmpty() || stack.pop() != mappings.get(c)) {
                    return false;
                }
            } else {
                // This is an opening bracket
                stack.push(c);
            }
        }
        
        // Valid if stack is empty (all brackets matched)
        return stack.isEmpty();
    }
    
    /**
     * Approach 2: Stack with Switch Statement
     * Time Complexity: O(n) - Single pass
     * Space Complexity: O(n) - Stack space
     * 
     * More explicit approach using switch statement
     */
    public boolean isValidSwitch(String s) {
        Stack<Character> stack = new Stack<>();
        
        for (char c : s.toCharArray()) {
            switch (c) {
                case '(':
                case '{':
                case '[':
                    // Opening bracket - push to stack
                    stack.push(c);
                    break;
                case ')':
                    if (stack.isEmpty() || stack.pop() != '(') return false;
                    break;
                case '}':
                    if (stack.isEmpty() || stack.pop() != '{') return false;
                    break;
                case ']':
                    if (stack.isEmpty() || stack.pop() != '[') return false;
                    break;
                default:
                    // Invalid character
                    return false;
            }
        }
        
        return stack.isEmpty();
    }
    
    /**
     * Approach 3: Stack with Early Termination
     * Time Complexity: O(n) - Single pass
     * Space Complexity: O(n) - Stack space
     * 
     * Optimization: Return false early if string length is odd
     */
    public boolean isValidOptimized(String s) {
        // Early termination - odd length strings can't be valid
        if (s.length() % 2 != 0) {
            return false;
        }
        
        Stack<Character> stack = new Stack<>();
        
        for (char c : s.toCharArray()) {
            if (c == '(') {
                stack.push(')');
            } else if (c == '{') {
                stack.push('}');
            } else if (c == '[') {
                stack.push(']');
            } else if (stack.isEmpty() || stack.pop() != c) {
                return false;
            }
        }
        
        return stack.isEmpty();
    }
    
    /**
     * Approach 4: Using Deque (Modern Java)
     * Time Complexity: O(n) - Single pass
     * Space Complexity: O(n) - Deque space
     * 
     * Deque is preferred over Stack in modern Java
     */
    public boolean isValidDeque(String s) {
        Deque<Character> stack = new ArrayDeque<>();
        
        for (char c : s.toCharArray()) {
            if (c == '(' || c == '{' || c == '[') {
                stack.push(c);
            } else {
                if (stack.isEmpty()) return false;
                
                char top = stack.pop();
                if ((c == ')' && top != '(') ||
                    (c == '}' && top != '{') ||
                    (c == ']' && top != '[')) {
                    return false;
                }
            }
        }
        
        return stack.isEmpty();
    }
    
    /**
     * Approach 5: String Replacement (Not Recommended for Interview)
     * Time Complexity: O(n²) - Multiple passes for replacements
     * Space Complexity: O(n) - String manipulation
     * 
     * This approach repeatedly removes valid pairs until none remain
     */
    public boolean isValidReplacement(String s) {
        int previousLength;
        
        do {
            previousLength = s.length();
            s = s.replace("()", "")
                 .replace("{}", "")
                 .replace("[]", "");
        } while (s.length() != previousLength);
        
        return s.isEmpty();
    }
    
    /**
     * Approach 6: Counter-based (Works only for single type)
     * This demonstrates why stack is necessary for multiple bracket types
     */
    public boolean isValidSingleType(String s) {
        // This only works if we have just one type of bracket
        int counter = 0;
        
        for (char c : s.toCharArray()) {
            if (c == '(') {
                counter++;
            } else if (c == ')') {
                counter--;
                if (counter < 0) return false;
            }
        }
        
        return counter == 0;
    }
    
    /**
     * Extended version: Check if string is valid with wildcards
     * '*' can represent '(', ')' or empty string
     */
    public boolean checkValidString(String s) {
        // Track range of possible open brackets
        int minOpen = 0, maxOpen = 0;
        
        for (char c : s.toCharArray()) {
            if (c == '(') {
                minOpen++;
                maxOpen++;
            } else if (c == ')') {
                minOpen = Math.max(0, minOpen - 1);
                maxOpen--;
                if (maxOpen < 0) return false;
            } else { // c == '*'
                minOpen = Math.max(0, minOpen - 1);
                maxOpen++;
            }
        }
        
        return minOpen == 0;
    }
    
    // Test the solutions
    public static void main(String[] args) {
        ValidParentheses solution = new ValidParentheses();
        
        // Test cases
        String[] testCases = {
            "()",
            "()[]{}",
            "(]",
            "([)]",
            "{[]}",
            "",
            "(((",
            ")))",
            "(())",
            "([{}])"
        };
        
        System.out.println("Testing Valid Parentheses:");
        for (String test : testCases) {
            System.out.printf("Input: %-10s => %s%n", 
                test.isEmpty() ? "\"\"" : test, 
                solution.isValidOptimal(test));
        }
        
        // Test wildcard version
        System.out.println("\nTesting with wildcards:");
        String[] wildcardTests = {
            "()",
            "(*)",
            ")(*",
            "(*)))",
            "(*)(*)"
        };
        
        for (String test : wildcardTests) {
            System.out.printf("Input: %-10s => %s%n", 
                test, 
                solution.checkValidString(test));
        }
    }
} 