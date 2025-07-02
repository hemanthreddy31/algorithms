package leetcode.stack;

import java.util.*;

/**
 * LeetCode 155: Min Stack
 * 
 * Design a stack that supports push, pop, top, and retrieving the minimum element in constant time.
 * 
 * Implement the MinStack class:
 * - MinStack() initializes the stack object.
 * - void push(int val) pushes the element val onto the stack.
 * - void pop() removes the element on the top of the stack.
 * - int top() gets the top element of the stack.
 * - int getMin() retrieves the minimum element in the stack.
 * 
 * You must implement a solution with O(1) time complexity for each function.
 */
public class MinStack {
    
    /**
     * Approach 1: Two Stacks (Optimal)
     * Time Complexity: O(1) for all operations
     * Space Complexity: O(n) - Two stacks in worst case
     * 
     * Key Insight: Use auxiliary stack to track minimum values
     * Push to min stack only when new minimum is found
     */
    static class MinStackTwoStacks {
        private Stack<Integer> dataStack;
        private Stack<Integer> minStack;
        
        public MinStackTwoStacks() {
            dataStack = new Stack<>();
            minStack = new Stack<>();
        }
        
        public void push(int val) {
            dataStack.push(val);
            
            // Push to min stack if it's empty or val is new minimum
            if (minStack.isEmpty() || val <= minStack.peek()) {
                minStack.push(val);
            }
        }
        
        public void pop() {
            if (dataStack.isEmpty()) return;
            
            int popped = dataStack.pop();
            
            // Pop from min stack if popped element was the minimum
            if (!minStack.isEmpty() && popped == minStack.peek()) {
                minStack.pop();
            }
        }
        
        public int top() {
            return dataStack.peek();
        }
        
        public int getMin() {
            return minStack.peek();
        }
        
        public void display() {
            System.out.println("Data Stack: " + dataStack);
            System.out.println("Min Stack:  " + minStack);
            System.out.println("Current Min: " + (minStack.isEmpty() ? "N/A" : minStack.peek()));
        }
    }
    
    /**
     * Approach 2: Single Stack with Pair/Tuple
     * Time Complexity: O(1) for all operations
     * Space Complexity: O(n) - Store pairs
     * 
     * Each stack element contains value and current minimum
     */
    static class MinStackPairs {
        private Stack<int[]> stack; // [value, currentMin]
        
        public MinStackPairs() {
            stack = new Stack<>();
        }
        
        public void push(int val) {
            if (stack.isEmpty()) {
                stack.push(new int[]{val, val});
            } else {
                int currentMin = Math.min(val, stack.peek()[1]);
                stack.push(new int[]{val, currentMin});
            }
        }
        
        public void pop() {
            if (!stack.isEmpty()) {
                stack.pop();
            }
        }
        
        public int top() {
            return stack.peek()[0];
        }
        
        public int getMin() {
            return stack.peek()[1];
        }
    }
    
    /**
     * Approach 3: Single Stack with Encoding
     * Time Complexity: O(1) for all operations
     * Space Complexity: O(n) - Single stack
     * 
     * Uses mathematical encoding to store both value and minimum
     * Stores 2*val - min when val >= min, otherwise stores val
     */
    static class MinStackEncoded {
        private Stack<Long> stack;
        private long min;
        
        public MinStackEncoded() {
            stack = new Stack<>();
        }
        
        public void push(int val) {
            if (stack.isEmpty()) {
                stack.push((long)val);
                min = val;
            } else {
                if (val >= min) {
                    stack.push((long)val);
                } else {
                    // Store encoded value: 2*val - min
                    stack.push(2L * val - min);
                    min = val; // Update minimum
                }
            }
        }
        
        public void pop() {
            if (stack.isEmpty()) return;
            
            long popped = stack.pop();
            
            // If popped value is less than min, it was encoded
            if (popped < min) {
                // Restore previous minimum: 2*min - popped
                min = 2 * min - popped;
            }
        }
        
        public int top() {
            long topValue = stack.peek();
            
            // If top is less than min, it's encoded
            if (topValue < min) {
                return (int)min; // Return actual minimum value
            }
            return (int)topValue;
        }
        
        public int getMin() {
            return (int)min;
        }
    }
    
    /**
     * Approach 4: Linked List Implementation
     * Time Complexity: O(1) for all operations
     * Space Complexity: O(n)
     * 
     * Each node stores value and minimum up to that point
     */
    static class MinStackLinkedList {
        private class Node {
            int val;
            int min;
            Node next;
            
            Node(int val, int min, Node next) {
                this.val = val;
                this.min = min;
                this.next = next;
            }
        }
        
        private Node head;
        
        public MinStackLinkedList() {
            head = null;
        }
        
        public void push(int val) {
            int newMin = (head == null) ? val : Math.min(val, head.min);
            head = new Node(val, newMin, head);
        }
        
        public void pop() {
            if (head != null) {
                head = head.next;
            }
        }
        
        public int top() {
            return head.val;
        }
        
        public int getMin() {
            return head.min;
        }
    }
    
    /**
     * Extended Problem: Max Stack
     * LeetCode 716: Design stack with O(1) max operation
     */
    static class MaxStack {
        private Stack<Integer> dataStack;
        private Stack<Integer> maxStack;
        
        public MaxStack() {
            dataStack = new Stack<>();
            maxStack = new Stack<>();
        }
        
        public void push(int val) {
            dataStack.push(val);
            
            if (maxStack.isEmpty() || val >= maxStack.peek()) {
                maxStack.push(val);
            }
        }
        
        public void pop() {
            if (dataStack.isEmpty()) return;
            
            int popped = dataStack.pop();
            if (!maxStack.isEmpty() && popped == maxStack.peek()) {
                maxStack.pop();
            }
        }
        
        public int top() {
            return dataStack.peek();
        }
        
        public int getMax() {
            return maxStack.peek();
        }
    }
    
    /**
     * Extended Problem: Min/Max Stack Combined
     * Stack that supports both min and max in O(1)
     */
    static class MinMaxStack {
        private Stack<int[]> stack; // [value, min, max]
        
        public MinMaxStack() {
            stack = new Stack<>();
        }
        
        public void push(int val) {
            if (stack.isEmpty()) {
                stack.push(new int[]{val, val, val});
            } else {
                int[] top = stack.peek();
                int newMin = Math.min(val, top[1]);
                int newMax = Math.max(val, top[2]);
                stack.push(new int[]{val, newMin, newMax});
            }
        }
        
        public void pop() {
            if (!stack.isEmpty()) {
                stack.pop();
            }
        }
        
        public int top() {
            return stack.peek()[0];
        }
        
        public int getMin() {
            return stack.peek()[1];
        }
        
        public int getMax() {
            return stack.peek()[2];
        }
    }
    
    // Test the implementations
    public static void main(String[] args) {
        System.out.println("Testing MinStack (Two Stacks Approach):");
        
        MinStackTwoStacks minStack = new MinStackTwoStacks();
        
        System.out.println("\nOperations:");
        
        minStack.push(-2);
        System.out.println("push(-2)");
        minStack.display();
        
        minStack.push(0);
        System.out.println("push(0)");
        minStack.display();
        
        minStack.push(-3);
        System.out.println("push(-3)");
        minStack.display();
        
        System.out.println("getMin(): " + minStack.getMin());
        
        minStack.pop();
        System.out.println("pop()");
        minStack.display();
        
        System.out.println("top(): " + minStack.top());
        System.out.println("getMin(): " + minStack.getMin());
        
        // Test Pairs approach
        System.out.println("\n" + "=".repeat(50));
        System.out.println("Testing MinStack (Pairs Approach):");
        
        MinStackPairs minStackPairs = new MinStackPairs();
        
        minStackPairs.push(5);
        minStackPairs.push(2);
        minStackPairs.push(7);
        minStackPairs.push(1);
        
        System.out.println("After pushing 5, 2, 7, 1:");
        System.out.println("top(): " + minStackPairs.top());
        System.out.println("getMin(): " + minStackPairs.getMin());
        
        minStackPairs.pop();
        System.out.println("After pop():");
        System.out.println("top(): " + minStackPairs.top());
        System.out.println("getMin(): " + minStackPairs.getMin());
        
        // Test Encoded approach
        System.out.println("\n" + "=".repeat(50));
        System.out.println("Testing MinStack (Encoded Approach):");
        
        MinStackEncoded minStackEncoded = new MinStackEncoded();
        
        minStackEncoded.push(3);
        minStackEncoded.push(1);
        minStackEncoded.push(4);
        minStackEncoded.push(0);
        
        System.out.println("After pushing 3, 1, 4, 0:");
        System.out.println("top(): " + minStackEncoded.top());
        System.out.println("getMin(): " + minStackEncoded.getMin());
        
        minStackEncoded.pop();
        System.out.println("After pop():");
        System.out.println("top(): " + minStackEncoded.top());
        System.out.println("getMin(): " + minStackEncoded.getMin());
        
        // Test LinkedList approach
        System.out.println("\n" + "=".repeat(50));
        System.out.println("Testing MinStack (LinkedList Approach):");
        
        MinStackLinkedList minStackLL = new MinStackLinkedList();
        
        minStackLL.push(8);
        minStackLL.push(3);
        minStackLL.push(9);
        minStackLL.push(1);
        
        System.out.println("After pushing 8, 3, 9, 1:");
        System.out.println("top(): " + minStackLL.top());
        System.out.println("getMin(): " + minStackLL.getMin());
        
        minStackLL.pop();
        minStackLL.pop();
        System.out.println("After two pops:");
        System.out.println("top(): " + minStackLL.top());
        System.out.println("getMin(): " + minStackLL.getMin());
        
        // Test Extended Problems
        System.out.println("\n" + "=".repeat(50));
        System.out.println("Testing Extended Problems:");
        
        // MaxStack
        MaxStack maxStack = new MaxStack();
        maxStack.push(5);
        maxStack.push(2);
        maxStack.push(8);
        maxStack.push(3);
        
        System.out.println("MaxStack after pushing 5, 2, 8, 3:");
        System.out.println("top(): " + maxStack.top());
        System.out.println("getMax(): " + maxStack.getMax());
        
        // MinMaxStack
        MinMaxStack minMaxStack = new MinMaxStack();
        minMaxStack.push(7);
        minMaxStack.push(2);
        minMaxStack.push(9);
        minMaxStack.push(1);
        
        System.out.println("MinMaxStack after pushing 7, 2, 9, 1:");
        System.out.println("top(): " + minMaxStack.top());
        System.out.println("getMin(): " + minMaxStack.getMin());
        System.out.println("getMax(): " + minMaxStack.getMax());
        
        minMaxStack.pop();
        System.out.println("After pop():");
        System.out.println("top(): " + minMaxStack.top());
        System.out.println("getMin(): " + minMaxStack.getMin());
        System.out.println("getMax(): " + minMaxStack.getMax());
    }
} 