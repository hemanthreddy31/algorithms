package leetcode.stack;

import java.util.*;

/**
 * LeetCode 232: Implement Queue using Stacks
 * 
 * Implement a first in first out (FIFO) queue using only two stacks.
 * The implemented queue should support all the functions of a normal queue (push, peek, pop, and empty).
 * 
 * Implement the MyQueue class:
 * - void push(int x) Pushes element x to the back of the queue.
 * - int pop() Removes the element from the front of the queue and returns it.
 * - int peek() Returns the element at the front of the queue.
 * - boolean empty() Returns true if the queue is empty, false otherwise.
 * 
 * Notes:
 * - You must use only standard operations of a stack -- which means only push to top, 
 *   peek/pop from top, size, and is empty operations are valid.
 */
public class QueueUsingStacks {
    
    /**
     * Approach 1: Two Stacks with Amortized O(1)
     * 
     * Key Insight: Use two stacks - input stack for push, output stack for pop/peek
     * Transfer elements from input to output only when output is empty
     * 
     * Time Complexity:
     * - push: O(1)
     * - pop: Amortized O(1), worst case O(n)
     * - peek: Amortized O(1), worst case O(n)
     * - empty: O(1)
     * 
     * Space Complexity: O(n) - Two stacks
     */
    static class MyQueue {
        private Stack<Integer> inputStack;   // For push operations
        private Stack<Integer> outputStack;  // For pop/peek operations
        
        public MyQueue() {
            inputStack = new Stack<>();
            outputStack = new Stack<>();
        }
        
        /**
         * Push element to the back of queue
         * Always push to input stack - O(1)
         */
        public void push(int x) {
            inputStack.push(x);
        }
        
        /**
         * Remove and return element from front of queue
         * Amortized O(1) - each element moved at most twice
         */
        public int pop() {
            peek(); // Ensure output stack has elements
            return outputStack.pop();
        }
        
        /**
         * Return element at front without removing
         * Amortized O(1) - transfer happens only when needed
         */
        public int peek() {
            // If output stack is empty, transfer all from input
            if (outputStack.isEmpty()) {
                while (!inputStack.isEmpty()) {
                    outputStack.push(inputStack.pop());
                }
            }
            return outputStack.peek();
        }
        
        /**
         * Check if queue is empty - O(1)
         */
        public boolean empty() {
            return inputStack.isEmpty() && outputStack.isEmpty();
        }
        
        /**
         * Get current size of queue - O(1)
         */
        public int size() {
            return inputStack.size() + outputStack.size();
        }
        
        /**
         * Helper method to display queue state
         */
        public void display() {
            System.out.print("Queue (front to back): [");
            
            // First show elements in output stack (in reverse order)
            Stack<Integer> temp = new Stack<>();
            while (!outputStack.isEmpty()) {
                temp.push(outputStack.pop());
            }
            
            boolean first = true;
            while (!temp.isEmpty()) {
                if (!first) System.out.print(", ");
                System.out.print(temp.peek());
                outputStack.push(temp.pop());
                first = false;
            }
            
            // Then show elements in input stack (bottom to top)
            Stack<Integer> inputTemp = new Stack<>();
            while (!inputStack.isEmpty()) {
                inputTemp.push(inputStack.pop());
            }
            
            while (!inputTemp.isEmpty()) {
                if (!first) System.out.print(", ");
                System.out.print(inputTemp.peek());
                inputStack.push(inputTemp.pop());
                first = false;
            }
            
            System.out.println("]");
        }
    }
    
    /**
     * Approach 2: Push Heavy - O(1) pop/peek, O(n) push
     * 
     * Alternative approach where push is expensive but pop/peek are O(1)
     * Every push operation ensures elements are in correct order for popping
     */
    static class MyQueuePushHeavy {
        private Stack<Integer> stack1;
        private Stack<Integer> stack2;
        
        public MyQueuePushHeavy() {
            stack1 = new Stack<>();
            stack2 = new Stack<>();
        }
        
        /**
         * Push element - O(n)
         * Move all elements to auxiliary stack, push new element, move back
         */
        public void push(int x) {
            // Move all elements from stack1 to stack2
            while (!stack1.isEmpty()) {
                stack2.push(stack1.pop());
            }
            
            // Push new element to stack1
            stack1.push(x);
            
            // Move all elements back from stack2 to stack1
            while (!stack2.isEmpty()) {
                stack1.push(stack2.pop());
            }
        }
        
        /**
         * Pop element - O(1)
         */
        public int pop() {
            return stack1.pop();
        }
        
        /**
         * Peek front element - O(1)
         */
        public int peek() {
            return stack1.peek();
        }
        
        /**
         * Check if empty - O(1)
         */
        public boolean empty() {
            return stack1.isEmpty();
        }
    }
    
    /**
     * Approach 3: Single Stack with Recursion
     * 
     * Demonstrates queue using only one stack with recursion
     * Not practical but shows the concept
     */
    static class MyQueueRecursive {
        private Stack<Integer> stack;
        
        public MyQueueRecursive() {
            stack = new Stack<>();
        }
        
        public void push(int x) {
            stack.push(x);
        }
        
        public int pop() {
            if (stack.size() == 1) {
                return stack.pop();
            }
            
            // Remove top element and recursively pop from remaining
            int item = stack.pop();
            int result = pop();
            stack.push(item);
            return result;
        }
        
        public int peek() {
            if (stack.size() == 1) {
                return stack.peek();
            }
            
            int item = stack.pop();
            int result = peek();
            stack.push(item);
            return result;
        }
        
        public boolean empty() {
            return stack.isEmpty();
        }
    }
    
    /**
     * Extended Problem: Implement Stack using Queues
     * LeetCode 225: Opposite problem - implement stack using queues
     */
    static class MyStack {
        private Queue<Integer> queue1;
        private Queue<Integer> queue2;
        
        public MyStack() {
            queue1 = new LinkedList<>();
            queue2 = new LinkedList<>();
        }
        
        /**
         * Push element to top - O(n)
         * To maintain LIFO order, we need to rotate elements
         */
        public void push(int x) {
            queue2.offer(x);
            
            // Move all elements from queue1 to queue2
            while (!queue1.isEmpty()) {
                queue2.offer(queue1.poll());
            }
            
            // Swap queue1 and queue2
            Queue<Integer> temp = queue1;
            queue1 = queue2;
            queue2 = temp;
        }
        
        /**
         * Pop top element - O(1)
         */
        public int pop() {
            return queue1.poll();
        }
        
        /**
         * Get top element - O(1)
         */
        public int top() {
            return queue1.peek();
        }
        
        /**
         * Check if empty - O(1)
         */
        public boolean empty() {
            return queue1.isEmpty();
        }
    }
    
    // Test the implementations
    public static void main(String[] args) {
        System.out.println("Testing Queue using Stacks (Approach 1 - Amortized):");
        
        MyQueue queue = new MyQueue();
        
        // Test sequence
        System.out.println("Operations:");
        
        queue.push(1);
        System.out.println("push(1)");
        queue.display();
        
        queue.push(2);
        System.out.println("push(2)");
        queue.display();
        
        System.out.println("peek(): " + queue.peek());
        queue.display();
        
        System.out.println("pop(): " + queue.pop());
        queue.display();
        
        System.out.println("empty(): " + queue.empty());
        
        queue.push(3);
        System.out.println("push(3)");
        queue.display();
        
        queue.push(4);
        System.out.println("push(4)");
        queue.display();
        
        System.out.println("pop(): " + queue.pop());
        queue.display();
        
        System.out.println("pop(): " + queue.pop());
        queue.display();
        
        System.out.println("pop(): " + queue.pop());
        queue.display();
        
        System.out.println("empty(): " + queue.empty());
        
        // Test Push Heavy approach
        System.out.println("\n" + "=".repeat(50));
        System.out.println("Testing Queue using Stacks (Approach 2 - Push Heavy):");
        
        MyQueuePushHeavy queuePushHeavy = new MyQueuePushHeavy();
        
        queuePushHeavy.push(1);
        queuePushHeavy.push(2);
        System.out.println("After push(1), push(2)");
        System.out.println("peek(): " + queuePushHeavy.peek());
        System.out.println("pop(): " + queuePushHeavy.pop());
        System.out.println("empty(): " + queuePushHeavy.empty());
        
        // Test Stack using Queues
        System.out.println("\n" + "=".repeat(50));
        System.out.println("Testing Stack using Queues:");
        
        MyStack stack = new MyStack();
        
        stack.push(1);
        stack.push(2);
        System.out.println("After push(1), push(2)");
        System.out.println("top(): " + stack.top());
        System.out.println("pop(): " + stack.pop());
        System.out.println("empty(): " + stack.empty());
    }
} 