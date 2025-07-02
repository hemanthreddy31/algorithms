package leetcode.tree;

import java.util.*;

/**
 * LeetCode 104: Maximum Depth of Binary Tree
 * 
 * Given the root of a binary tree, return its maximum depth.
 * A binary tree's maximum depth is the number of nodes along the longest path 
 * from the root node down to the farthest leaf node.
 * 
 * Example:
 *     3
 *    / \
 *   9   20
 *      /  \
 *     15   7
 * 
 * Output: 3
 */
public class MaximumDepthBinaryTree {
    
    /**
     * Definition for a binary tree node
     */
    static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        
        TreeNode() {}
        
        TreeNode(int val) { 
            this.val = val; 
        }
        
        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }
    
    /**
     * Approach 1: Recursive DFS (Top-Down)
     * Time Complexity: O(n) - Visit each node once
     * Space Complexity: O(h) - Recursion stack, h is height of tree
     * 
     * Algorithm:
     * 1. Base case: if node is null, depth is 0
     * 2. Recursively find depth of left and right subtrees
     * 3. Return 1 + max(leftDepth, rightDepth)
     */
    public int maxDepthRecursive(TreeNode root) {
        // Base case: empty tree has depth 0
        if (root == null) {
            return 0;
        }
        
        // Recursively find depth of left and right subtrees
        int leftDepth = maxDepthRecursive(root.left);
        int rightDepth = maxDepthRecursive(root.right);
        
        // Current node adds 1 to the depth
        return 1 + Math.max(leftDepth, rightDepth);
    }
    
    /**
     * Approach 2: Recursive DFS (One-liner)
     * Same complexity as above, just more concise
     */
    public int maxDepthConcise(TreeNode root) {
        return root == null ? 0 : 1 + Math.max(maxDepthConcise(root.left), maxDepthConcise(root.right));
    }
    
    /**
     * Approach 3: Iterative BFS (Level Order Traversal)
     * Time Complexity: O(n) - Visit each node once
     * Space Complexity: O(w) - Queue size, w is max width of tree
     * 
     * Algorithm:
     * 1. Use a queue for level order traversal
     * 2. Process all nodes at each level
     * 3. Increment depth for each level
     */
    public int maxDepthBFS(TreeNode root) {
        if (root == null) {
            return 0;
        }
        
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        int depth = 0;
        
        while (!queue.isEmpty()) {
            int levelSize = queue.size();
            depth++;
            
            // Process all nodes at current level
            for (int i = 0; i < levelSize; i++) {
                TreeNode node = queue.poll();
                
                // Add children to queue for next level
                if (node.left != null) {
                    queue.offer(node.left);
                }
                if (node.right != null) {
                    queue.offer(node.right);
                }
            }
        }
        
        return depth;
    }
    
    /**
     * Approach 4: Iterative DFS using Stack
     * Time Complexity: O(n) - Visit each node once
     * Space Complexity: O(h) - Stack size, h is height of tree
     * 
     * Algorithm:
     * 1. Use a stack to simulate recursion
     * 2. Store node and its depth in stack
     * 3. Keep track of maximum depth seen
     */
    public int maxDepthDFS(TreeNode root) {
        if (root == null) {
            return 0;
        }
        
        Stack<Pair> stack = new Stack<>();
        stack.push(new Pair(root, 1));
        int maxDepth = 0;
        
        while (!stack.isEmpty()) {
            Pair current = stack.pop();
            TreeNode node = current.node;
            int depth = current.depth;
            
            // Update max depth
            maxDepth = Math.max(maxDepth, depth);
            
            // Add children with incremented depth
            if (node.left != null) {
                stack.push(new Pair(node.left, depth + 1));
            }
            if (node.right != null) {
                stack.push(new Pair(node.right, depth + 1));
            }
        }
        
        return maxDepth;
    }
    
    // Helper class for iterative DFS
    private static class Pair {
        TreeNode node;
        int depth;
        
        Pair(TreeNode node, int depth) {
            this.node = node;
            this.depth = depth;
        }
    }
    
    /**
     * Approach 5: Morris Traversal (No extra space)
     * Time Complexity: O(n) - Each edge traversed at most twice
     * Space Complexity: O(1) - No extra space used
     * 
     * This approach modifies the tree temporarily but restores it
     */
    public int maxDepthMorris(TreeNode root) {
        if (root == null) return 0;
        
        int maxDepth = 0;
        int currentDepth = 0;
        TreeNode current = root;
        
        while (current != null) {
            if (current.left == null) {
                // No left subtree, move to right
                currentDepth++;
                maxDepth = Math.max(maxDepth, currentDepth);
                current = current.right;
                if (current == null || isThreaded(current)) {
                    currentDepth--;
                }
            } else {
                // Find inorder predecessor
                TreeNode predecessor = current.left;
                int steps = 1;
                
                while (predecessor.right != null && predecessor.right != current) {
                    predecessor = predecessor.right;
                    steps++;
                }
                
                if (predecessor.right == null) {
                    // Make threading
                    predecessor.right = current;
                    currentDepth++;
                    current = current.left;
                } else {
                    // Remove threading
                    predecessor.right = null;
                    maxDepth = Math.max(maxDepth, currentDepth + steps);
                    current = current.right;
                    currentDepth--;
                }
            }
        }
        
        return maxDepth;
    }
    
    private boolean isThreaded(TreeNode node) {
        TreeNode current = node;
        while (current.left != null) {
            current = current.left;
        }
        return current.right == node;
    }
    
    /**
     * Extended Problem: Find diameter of binary tree
     * Diameter is the length of the longest path between any two nodes
     */
    public int diameterOfBinaryTree(TreeNode root) {
        int[] diameter = new int[1]; // Use array to pass by reference
        diameterHelper(root, diameter);
        return diameter[0];
    }
    
    private int diameterHelper(TreeNode node, int[] diameter) {
        if (node == null) return 0;
        
        int leftHeight = diameterHelper(node.left, diameter);
        int rightHeight = diameterHelper(node.right, diameter);
        
        // Update diameter if path through current node is longer
        diameter[0] = Math.max(diameter[0], leftHeight + rightHeight);
        
        // Return height of current subtree
        return 1 + Math.max(leftHeight, rightHeight);
    }
    
    // Helper method to create a sample tree
    private static TreeNode createSampleTree() {
        TreeNode root = new TreeNode(3);
        root.left = new TreeNode(9);
        root.right = new TreeNode(20);
        root.right.left = new TreeNode(15);
        root.right.right = new TreeNode(7);
        return root;
    }
    
    // Helper method to create a skewed tree
    private static TreeNode createSkewedTree() {
        TreeNode root = new TreeNode(1);
        root.right = new TreeNode(2);
        root.right.right = new TreeNode(3);
        root.right.right.right = new TreeNode(4);
        return root;
    }
    
    // Test the solutions
    public static void main(String[] args) {
        MaximumDepthBinaryTree solution = new MaximumDepthBinaryTree();
        
        // Test case 1: Balanced tree
        TreeNode tree1 = createSampleTree();
        System.out.println("Test Case 1: Balanced Tree");
        System.out.println("Recursive: " + solution.maxDepthRecursive(tree1));
        System.out.println("BFS: " + solution.maxDepthBFS(tree1));
        System.out.println("DFS Stack: " + solution.maxDepthDFS(tree1));
        
        // Test case 2: Skewed tree
        TreeNode tree2 = createSkewedTree();
        System.out.println("\nTest Case 2: Skewed Tree");
        System.out.println("Recursive: " + solution.maxDepthRecursive(tree2));
        System.out.println("BFS: " + solution.maxDepthBFS(tree2));
        System.out.println("DFS Stack: " + solution.maxDepthDFS(tree2));
        
        // Test case 3: Empty tree
        System.out.println("\nTest Case 3: Empty Tree");
        System.out.println("Recursive: " + solution.maxDepthRecursive(null));
        
        // Test case 4: Single node
        TreeNode tree4 = new TreeNode(1);
        System.out.println("\nTest Case 4: Single Node");
        System.out.println("Recursive: " + solution.maxDepthRecursive(tree4));
        
        // Test diameter
        System.out.println("\nDiameter of balanced tree: " + solution.diameterOfBinaryTree(tree1));
    }
} 