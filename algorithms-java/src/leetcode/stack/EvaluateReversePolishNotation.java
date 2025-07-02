package leetcode.stack;

import java.util.*;

/**
 * LeetCode 150: Evaluate Reverse Polish Notation
 * 
 * Evaluate the value of an arithmetic expression in Reverse Polish Notation.
 * Valid operators are +, -, *, and /. Each operand may be an integer or another expression.
 * 
 * Note that division between two integers should truncate toward zero.
 * It is guaranteed that the given RPN expression is always valid.
 * 
 * Example:
 * Input: tokens = ["2","1","+","3","*"]
 * Output: 9
 * Explanation: ((2 + 1) * 3) = 9
 */
public class EvaluateReversePolishNotation {
    
    /**
     * Approach 1: Stack-based Evaluation (Optimal)
     * Time Complexity: O(n) - Single pass through tokens
     * Space Complexity: O(n) - Stack can contain up to n/2 + 1 operands
     * 
     * Algorithm:
     * 1. For each token:
     *    - If number: push to stack
     *    - If operator: pop two operands, compute, push result
     * 2. Final result is the only element left in stack
     */
    public int evalRPN(String[] tokens) {
        Stack<Integer> stack = new Stack<>();
        
        for (String token : tokens) {
            if (isOperator(token)) {
                // Pop two operands (order matters for - and /)
                int operand2 = stack.pop();
                int operand1 = stack.pop();
                
                // Perform operation and push result
                int result = performOperation(operand1, operand2, token);
                stack.push(result);
            } else {
                // Push operand to stack
                stack.push(Integer.parseInt(token));
            }
        }
        
        // Final result
        return stack.pop();
    }
    
    /**
     * Helper method to check if token is an operator
     */
    private boolean isOperator(String token) {
        return token.equals("+") || token.equals("-") || 
               token.equals("*") || token.equals("/");
    }
    
    /**
     * Helper method to perform arithmetic operation
     */
    private int performOperation(int operand1, int operand2, String operator) {
        switch (operator) {
            case "+":
                return operand1 + operand2;
            case "-":
                return operand1 - operand2;
            case "*":
                return operand1 * operand2;
            case "/":
                // Division truncates toward zero
                return operand1 / operand2;
            default:
                throw new IllegalArgumentException("Invalid operator: " + operator);
        }
    }
    
    /**
     * Approach 2: Stack with Set for Operators
     * Time Complexity: O(n)
     * Space Complexity: O(n)
     * 
     * Uses HashSet for O(1) operator lookup
     */
    public int evalRPNWithSet(String[] tokens) {
        Set<String> operators = new HashSet<>(Arrays.asList("+", "-", "*", "/"));
        Stack<Integer> stack = new Stack<>();
        
        for (String token : tokens) {
            if (operators.contains(token)) {
                int b = stack.pop();
                int a = stack.pop();
                
                switch (token) {
                    case "+": stack.push(a + b); break;
                    case "-": stack.push(a - b); break;
                    case "*": stack.push(a * b); break;
                    case "/": stack.push(a / b); break;
                }
            } else {
                stack.push(Integer.parseInt(token));
            }
        }
        
        return stack.pop();
    }
    
    /**
     * Approach 3: Array-based Stack
     * Time Complexity: O(n)
     * Space Complexity: O(n)
     * 
     * Uses array instead of Stack object for better performance
     */
    public int evalRPNArrayStack(String[] tokens) {
        int[] stack = new int[tokens.length / 2 + 1]; // Max size needed
        int top = -1;
        
        for (String token : tokens) {
            switch (token) {
                case "+":
                    stack[top - 1] += stack[top];
                    top--;
                    break;
                case "-":
                    stack[top - 1] -= stack[top];
                    top--;
                    break;
                case "*":
                    stack[top - 1] *= stack[top];
                    top--;
                    break;
                case "/":
                    stack[top - 1] /= stack[top];
                    top--;
                    break;
                default:
                    stack[++top] = Integer.parseInt(token);
                    break;
            }
        }
        
        return stack[0];
    }
    
    /**
     * Approach 4: Recursive Evaluation (Educational)
     * Time Complexity: O(n)
     * Space Complexity: O(n) - Recursion stack
     * 
     * Processes tokens from right to left recursively
     */
    private int index;
    
    public int evalRPNRecursive(String[] tokens) {
        index = tokens.length - 1;
        return evaluateRecursive(tokens);
    }
    
    private int evaluateRecursive(String[] tokens) {
        String token = tokens[index--];
        
        if (isOperator(token)) {
            int operand2 = evaluateRecursive(tokens);
            int operand1 = evaluateRecursive(tokens);
            return performOperation(operand1, operand2, token);
        } else {
            return Integer.parseInt(token);
        }
    }
    
    /**
     * Extended Problem: Basic Calculator
     * LeetCode 224: Evaluate expression with parentheses
     */
    public int calculateWithParentheses(String s) {
        Stack<Integer> stack = new Stack<>();
        int result = 0;
        int number = 0;
        int sign = 1;
        
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            
            if (Character.isDigit(c)) {
                number = 10 * number + (c - '0');
            } else if (c == '+') {
                result += sign * number;
                number = 0;
                sign = 1;
            } else if (c == '-') {
                result += sign * number;
                number = 0;
                sign = -1;
            } else if (c == '(') {
                // Push result and sign to stack
                stack.push(result);
                stack.push(sign);
                
                // Reset for new sub-expression
                result = 0;
                sign = 1;
            } else if (c == ')') {
                result += sign * number;
                number = 0;
                
                // Pop sign and previous result
                result *= stack.pop(); // sign
                result += stack.pop(); // previous result
            }
        }
        
        return result + sign * number;
    }
    
    /**
     * Extended Problem: Convert Infix to Postfix
     * Convert infix expression to RPN (Reverse Polish Notation)
     */
    public String infixToPostfix(String infix) {
        Stack<Character> stack = new Stack<>();
        StringBuilder result = new StringBuilder();
        
        for (char c : infix.toCharArray()) {
            if (Character.isLetterOrDigit(c)) {
                result.append(c);
            } else if (c == '(') {
                stack.push(c);
            } else if (c == ')') {
                while (!stack.isEmpty() && stack.peek() != '(') {
                    result.append(stack.pop());
                }
                stack.pop(); // Remove '('
            } else { // Operator
                while (!stack.isEmpty() && precedence(c) <= precedence(stack.peek())) {
                    result.append(stack.pop());
                }
                stack.push(c);
            }
        }
        
        // Pop remaining operators
        while (!stack.isEmpty()) {
            result.append(stack.pop());
        }
        
        return result.toString();
    }
    
    private int precedence(char op) {
        switch (op) {
            case '+':
            case '-':
                return 1;
            case '*':
            case '/':
                return 2;
            default:
                return -1;
        }
    }
    
    /**
     * Extended Problem: Basic Calculator II
     * LeetCode 227: Evaluate expression with +, -, *, /
     */
    public int calculateII(String s) {
        Stack<Integer> stack = new Stack<>();
        int number = 0;
        char operation = '+';
        
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            
            if (Character.isDigit(c)) {
                number = number * 10 + (c - '0');
            }
            
            if (!Character.isDigit(c) && c != ' ' || i == s.length() - 1) {
                switch (operation) {
                    case '+':
                        stack.push(number);
                        break;
                    case '-':
                        stack.push(-number);
                        break;
                    case '*':
                        stack.push(stack.pop() * number);
                        break;
                    case '/':
                        stack.push(stack.pop() / number);
                        break;
                }
                operation = c;
                number = 0;
            }
        }
        
        int result = 0;
        while (!stack.isEmpty()) {
            result += stack.pop();
        }
        
        return result;
    }
    
    /**
     * Helper: Convert RPN to Infix notation (for verification)
     */
    public String rpnToInfix(String[] tokens) {
        Stack<String> stack = new Stack<>();
        
        for (String token : tokens) {
            if (isOperator(token)) {
                String operand2 = stack.pop();
                String operand1 = stack.pop();
                String expression = "(" + operand1 + " " + token + " " + operand2 + ")";
                stack.push(expression);
            } else {
                stack.push(token);
            }
        }
        
        return stack.pop();
    }
    
    // Test the solutions
    public static void main(String[] args) {
        EvaluateReversePolishNotation solution = new EvaluateReversePolishNotation();
        
        // Test case 1: Simple expression
        String[] tokens1 = {"2", "1", "+", "3", "*"};
        System.out.println("Test Case 1: " + Arrays.toString(tokens1));
        System.out.println("RPN Expression: " + solution.rpnToInfix(tokens1));
        System.out.println("Stack-based: " + solution.evalRPN(tokens1));
        System.out.println("Array Stack: " + solution.evalRPNArrayStack(tokens1));
        System.out.println("Recursive: " + solution.evalRPNRecursive(tokens1));
        
        // Test case 2: Division and subtraction
        String[] tokens2 = {"4", "13", "5", "/", "+"};
        System.out.println("\nTest Case 2: " + Arrays.toString(tokens2));
        System.out.println("RPN Expression: " + solution.rpnToInfix(tokens2));
        System.out.println("Result: " + solution.evalRPN(tokens2));
        
        // Test case 3: Complex expression with negative numbers
        String[] tokens3 = {"10", "6", "9", "3", "+", "-11", "*", "/", "*", "17", "+", "5", "+"};
        System.out.println("\nTest Case 3: " + Arrays.toString(tokens3));
        System.out.println("Result: " + solution.evalRPN(tokens3));
        
        // Test case 4: Single number
        String[] tokens4 = {"18"};
        System.out.println("\nTest Case 4: " + Arrays.toString(tokens4));
        System.out.println("Result: " + solution.evalRPN(tokens4));
        
        // Test extended problems
        System.out.println("\nExtended Problems:");
        
        // Infix to Postfix conversion
        String infix = "a+b*(c^d-e)^(f+g*h)-i";
        System.out.println("Infix to Postfix:");
        System.out.println("Infix: " + infix);
        System.out.println("Postfix: " + solution.infixToPostfix(infix));
        
        // Basic Calculator II
        String expression = "3+2*2";
        System.out.println("Basic Calculator II:");
        System.out.println("Expression: " + expression);
        System.out.println("Result: " + solution.calculateII(expression));
        
        // Calculator with parentheses
        String parenExpression = "(1+(4+5+2)-3)+(6+8)";
        System.out.println("Calculator with Parentheses:");
        System.out.println("Expression: " + parenExpression);
        System.out.println("Result: " + solution.calculateWithParentheses(parenExpression));
    }
} 