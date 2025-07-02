package leetcode.tree;

import java.util.*;

/**
 * LeetCode 98: Validate Binary Search Tree
 * 
 * Given the root of a binary tree, determine if it is a valid binary search tree (BST).
 * 
 * A valid BST is defined as follows:
 * - The left subtree of a node contains only nodes with keys less than the node's key.
 * - The right subtree of a node contains only nodes with keys greater than the node's key.
 * - Both the left and right subtrees must also be binary search trees.
 */
public class ValidateBinarySearchTree {
    
    static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        
        TreeNode() {}
        TreeNode(int val) { this.val = val; }
        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }
    
    /**
     * Approach 1: Bounds Checking (Optimal)
     * Time Complexity: O(n) - Visit each node once
     * Space Complexity: O(h) - Recursion stack, h is height
     * 
     * Algorithm:
     * 1. For each node, maintain valid range (min, max)
     * 2. Root can be any value (Long.MIN_VALUE, Long.MAX_VALUE)
     * 3. Left child must be < current value
     * 4. Right child must be > current value
     */
    public boolean isValidBST(TreeNode root) {
        return isValidBSTHelper(root, Long.MIN_VALUE, Long.MAX_VALUE);
    }
    
    private boolean isValidBSTHelper(TreeNode node, long min, long max) {
        // Empty tree is valid BST
        if (node == null) {
            return true;
        }
        
        // Check if current node violates BST property
        if (node.val <= min || node.val >= max) {
            return false;
        }
        
        // Recursively validate left and right subtrees with updated bounds
        return isValidBSTHelper(node.left, min, node.val) && 
               isValidBSTHelper(node.right, node.val, max);
    }
    
    /**
     * Approach 2: Inorder Traversal (Recursive)
     * Time Complexity: O(n) - Visit each node once
     * Space Complexity: O(h) - Recursion stack
     * 
     * In a valid BST, inorder traversal gives sorted sequence
     */
    private Integer prev = null;
    
    public boolean isValidBSTInorder(TreeNode root) {
        prev = null; // Reset for each call
        return inorderCheck(root);
    }
    
    private boolean inorderCheck(TreeNode node) {
        if (node == null) {
            return true;
        }
        
        // Check left subtree
        if (!inorderCheck(node.left)) {
            return false;
        }
        
        // Check current node against previous
        if (prev != null && node.val <= prev) {
            return false;
        }
        prev = node.val;
        
        // Check right subtree
        return inorderCheck(node.right);
    }
    
    /**
     * Approach 3: Inorder Traversal (Iterative)
     * Time Complexity: O(n)
     * Space Complexity: O(h) - Stack space
     */
    public boolean isValidBSTIterative(TreeNode root) {
        Stack<TreeNode> stack = new Stack<>();
        Integer prev = null;
        TreeNode current = root;
        
        while (current != null || !stack.isEmpty()) {
            // Go to leftmost node
            while (current != null) {
                stack.push(current);
                current = current.left;
            }
            
            // Process current node
            current = stack.pop();
            
            // Check BST property
            if (prev != null && current.val <= prev) {
                return false;
            }
            prev = current.val;
            
            // Move to right subtree
            current = current.right;
        }
        
        return true;
    }
    
    /**
     * Approach 4: Morris Traversal (O(1) space)
     * Time Complexity: O(n)
     * Space Complexity: O(1) - No extra space
     */
    public boolean isValidBSTMorris(TreeNode root) {
        TreeNode current = root;
        Integer prev = null;
        
        while (current != null) {
            if (current.left == null) {
                // Process current node
                if (prev != null && current.val <= prev) {
                    return false;
                }
                prev = current.val;
                current = current.right;
            } else {
                // Find predecessor
                TreeNode predecessor = current.left;
                while (predecessor.right != null && predecessor.right != current) {
                    predecessor = predecessor.right;
                }
                
                if (predecessor.right == null) {
                    // Make threading
                    predecessor.right = current;
                    current = current.left;
                } else {
                    // Remove threading and process current
                    predecessor.right = null;
                    if (prev != null && current.val <= prev) {
                        return false;
                    }
                    prev = current.val;
                    current = current.right;
                }
            }
        }
        
        return true;
    }
    
    /**
     * Approach 5: Collect and Check (Less optimal but intuitive)
     * Time Complexity: O(n)
     * Space Complexity: O(n) - Store all values
     */
    public boolean isValidBSTCollect(TreeNode root) {
        List<Integer> values = new ArrayList<>();
        inorderCollect(root, values);
        
        // Check if collected values are in strictly increasing order
        for (int i = 1; i < values.size(); i++) {
            if (values.get(i) <= values.get(i - 1)) {
                return false;
            }
        }
        
        return true;
    }
    
    private void inorderCollect(TreeNode node, List<Integer> values) {
        if (node == null) return;
        
        inorderCollect(node.left, values);
        values.add(node.val);
        inorderCollect(node.right, values);
    }
    
    /**
     * Extended Problem: Recover Binary Search Tree
     * LeetCode 99: Two nodes are swapped, recover the BST
     */
    private TreeNode firstElement = null;
    private TreeNode secondElement = null;
    private TreeNode prevElement = new TreeNode(Integer.MIN_VALUE);
    
    public void recoverTree(TreeNode root) {
        // Find the two swapped nodes
        traverse(root);
        
        // Swap their values
        int temp = firstElement.val;
        firstElement.val = secondElement.val;
        secondElement.val = temp;
    }
    
    private void traverse(TreeNode root) {
        if (root == null) return;
        
        traverse(root.left);
        
        // Find first violating pair
        if (firstElement == null && prevElement.val >= root.val) {
            firstElement = prevElement;
        }
        
        // Find second violating pair
        if (firstElement != null && prevElement.val >= root.val) {
            secondElement = root;
        }
        
        prevElement = root;
        traverse(root.right);
    }
    
    /**
     * Extended Problem: Kth Smallest Element in BST
     * LeetCode 230: Find kth smallest element
     */
    public int kthSmallest(TreeNode root, int k) {
        Stack<TreeNode> stack = new Stack<>();
        TreeNode current = root;
        int count = 0;
        
        while (current != null || !stack.isEmpty()) {
            while (current != null) {
                stack.push(current);
                current = current.left;
            }
            
            current = stack.pop();
            count++;
            
            if (count == k) {
                return current.val;
            }
            
            current = current.right;
        }
        
        return -1; // Should never reach here if k is valid
    }
    
    /**
     * Helper method to create a valid BST
     *       5
     *      / \
     *     3   8
     *    / \ / \
     *   2  4 7  9
     */
    private static TreeNode createValidBST() {
        TreeNode root = new TreeNode(5);
        root.left = new TreeNode(3);
        root.right = new TreeNode(8);
        root.left.left = new TreeNode(2);
        root.left.right = new TreeNode(4);
        root.right.left = new TreeNode(7);
        root.right.right = new TreeNode(9);
        return root;
    }
    
    /**
     * Helper method to create an invalid BST
     *       5
     *      / \
     *     3   8
     *    / \ / \
     *   2  6 7  9  (6 > 5, violates BST property)
     */
    private static TreeNode createInvalidBST() {
        TreeNode root = new TreeNode(5);
        root.left = new TreeNode(3);
        root.right = new TreeNode(8);
        root.left.left = new TreeNode(2);
        root.left.right = new TreeNode(6); // This violates BST property
        root.right.left = new TreeNode(7);
        root.right.right = new TreeNode(9);
        return root;
    }
    
    /**
     * Helper method to print inorder traversal
     */
    private void printInorder(TreeNode root) {
        if (root == null) return;
        
        printInorder(root.left);
        System.out.print(root.val + " ");
        printInorder(root.right);
    }
    
    // Test the solutions
    public static void main(String[] args) {
        ValidateBinarySearchTree solution = new ValidateBinarySearchTree();
        
        // Test valid BST
        TreeNode validBST = createValidBST();
        System.out.println("Testing Valid BST:");
        System.out.print("Inorder traversal: ");
        solution.printInorder(validBST);
        System.out.println();
        
        System.out.println("Bounds checking: " + solution.isValidBST(validBST));
        System.out.println("Inorder recursive: " + solution.isValidBSTInorder(validBST));
        System.out.println("Inorder iterative: " + solution.isValidBSTIterative(validBST));
        System.out.println("Morris traversal: " + solution.isValidBSTMorris(validBST));
        System.out.println("Collect and check: " + solution.isValidBSTCollect(validBST));
        
        // Test invalid BST
        TreeNode invalidBST = createInvalidBST();
        System.out.println("\nTesting Invalid BST:");
        System.out.print("Inorder traversal: ");
        solution.printInorder(invalidBST);
        System.out.println();
        
        System.out.println("Bounds checking: " + solution.isValidBST(invalidBST));
        System.out.println("Inorder recursive: " + solution.isValidBSTInorder(invalidBST));
        System.out.println("Inorder iterative: " + solution.isValidBSTIterative(invalidBST));
        System.out.println("Morris traversal: " + solution.isValidBSTMorris(invalidBST));
        System.out.println("Collect and check: " + solution.isValidBSTCollect(invalidBST));
        
        // Test edge cases
        System.out.println("\nTesting Edge Cases:");
        
        // Single node
        TreeNode singleNode = new TreeNode(1);
        System.out.println("Single node: " + solution.isValidBST(singleNode));
        
        // Empty tree
        System.out.println("Empty tree: " + solution.isValidBST(null));
        
        // Two nodes
        TreeNode twoNodes = new TreeNode(1);
        twoNodes.left = new TreeNode(2);
        System.out.println("Invalid two nodes [1,2,null]: " + solution.isValidBST(twoNodes));
        
        // Test extended problems
        System.out.println("\nTesting Extended Problems:");
        System.out.println("3rd smallest in valid BST: " + solution.kthSmallest(validBST, 3));
        
        // Test with Integer limits
        TreeNode limitTest = new TreeNode(Integer.MAX_VALUE);
        System.out.println("Max integer node: " + solution.isValidBST(limitTest));
    }
} 