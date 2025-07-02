package leetcode.tree;

import java.util.*;

/**
 * LeetCode 226: Invert Binary Tree
 * 
 * Given the root of a binary tree, invert the tree, and return its root.
 * 
 * Example:
 * Input:     4              Output:     4
 *           / \                        / \
 *          2   7                      7   2
 *         / \ / \                    / \ / \
 *        1  3 6  9                  9  6 3  1
 */
public class InvertBinaryTree {
    
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
     * Approach 1: Recursive DFS (Optimal)
     * Time Complexity: O(n) - Visit each node once
     * Space Complexity: O(h) - Recursion stack, h is height of tree
     * 
     * Algorithm:
     * 1. Base case: if node is null, return null
     * 2. Swap left and right children
     * 3. Recursively invert left and right subtrees
     */
    public TreeNode invertTreeRecursive(TreeNode root) {
        // Base case: empty tree
        if (root == null) {
            return null;
        }
        
        // Swap left and right children
        TreeNode temp = root.left;
        root.left = root.right;
        root.right = temp;
        
        // Recursively invert left and right subtrees
        invertTreeRecursive(root.left);
        invertTreeRecursive(root.right);
        
        return root;
    }
    
    /**
     * Approach 2: Recursive with simultaneous inversion
     * Same complexity, but swaps after recursive calls
     */
    public TreeNode invertTreeRecursive2(TreeNode root) {
        if (root == null) {
            return null;
        }
        
        // Recursively invert subtrees first
        TreeNode left = invertTreeRecursive2(root.left);
        TreeNode right = invertTreeRecursive2(root.right);
        
        // Then swap the inverted subtrees
        root.left = right;
        root.right = left;
        
        return root;
    }
    
    /**
     * Approach 3: Iterative BFS using Queue
     * Time Complexity: O(n) - Visit each node once
     * Space Complexity: O(w) - Queue size, w is max width of tree
     * 
     * Algorithm:
     * 1. Use queue for level order traversal
     * 2. For each node, swap its children
     * 3. Add children to queue for next level
     */
    public TreeNode invertTreeBFS(TreeNode root) {
        if (root == null) {
            return null;
        }
        
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        
        while (!queue.isEmpty()) {
            TreeNode current = queue.poll();
            
            // Swap left and right children
            TreeNode temp = current.left;
            current.left = current.right;
            current.right = temp;
            
            // Add children to queue if they exist
            if (current.left != null) {
                queue.offer(current.left);
            }
            if (current.right != null) {
                queue.offer(current.right);
            }
        }
        
        return root;
    }
    
    /**
     * Approach 4: Iterative DFS using Stack
     * Time Complexity: O(n) - Visit each node once
     * Space Complexity: O(h) - Stack size, h is height of tree
     * 
     * Algorithm:
     * 1. Use stack to simulate recursion
     * 2. For each node, swap children and add them to stack
     */
    public TreeNode invertTreeDFS(TreeNode root) {
        if (root == null) {
            return null;
        }
        
        Stack<TreeNode> stack = new Stack<>();
        stack.push(root);
        
        while (!stack.isEmpty()) {
            TreeNode current = stack.pop();
            
            // Swap left and right children
            TreeNode temp = current.left;
            current.left = current.right;
            current.right = temp;
            
            // Add children to stack if they exist
            if (current.left != null) {
                stack.push(current.left);
            }
            if (current.right != null) {
                stack.push(current.right);
            }
        }
        
        return root;
    }
    
    /**
     * Extended Problem: Check if two trees are mirror images
     */
    public boolean isMirror(TreeNode left, TreeNode right) {
        // Both are null - mirror
        if (left == null && right == null) {
            return true;
        }
        
        // Only one is null - not mirror
        if (left == null || right == null) {
            return false;
        }
        
        // Values must be same and subtrees must be mirrors
        return left.val == right.val && 
               isMirror(left.left, right.right) && 
               isMirror(left.right, right.left);
    }
    
    /**
     * Extended Problem: Check if tree is symmetric
     * LeetCode 101: A tree is symmetric if it's a mirror of itself
     */
    public boolean isSymmetric(TreeNode root) {
        if (root == null) {
            return true;
        }
        return isMirror(root.left, root.right);
    }
    
    /**
     * Helper method to print tree in level order
     */
    public void printLevelOrder(TreeNode root) {
        if (root == null) {
            System.out.println("Empty tree");
            return;
        }
        
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        
        while (!queue.isEmpty()) {
            int levelSize = queue.size();
            for (int i = 0; i < levelSize; i++) {
                TreeNode node = queue.poll();
                if (node != null) {
                    System.out.print(node.val + " ");
                    queue.offer(node.left);
                    queue.offer(node.right);
                } else {
                    System.out.print("null ");
                }
            }
            System.out.println();
        }
    }
    
    /**
     * Helper method to create sample tree
            4
           / \
          2   7
         / \ / \
        1  3 6  9
     */
    private static TreeNode createSampleTree() {
        TreeNode root = new TreeNode(4);
        root.left = new TreeNode(2);
        root.right = new TreeNode(7);
        root.left.left = new TreeNode(1);
        root.left.right = new TreeNode(3);
        root.right.left = new TreeNode(6);
        root.right.right = new TreeNode(9);
        return root;
    }
    
    /**
     * Helper method to clone a tree (for testing)
     */
    private TreeNode cloneTree(TreeNode root) {
        if (root == null) {
            return null;
        }
        
        TreeNode newNode = new TreeNode(root.val);
        newNode.left = cloneTree(root.left);
        newNode.right = cloneTree(root.right);
        return newNode;
    }
    
    // Test the solutions
    public static void main(String[] args) {
        InvertBinaryTree solution = new InvertBinaryTree();
        
        System.out.println("Testing Invert Binary Tree:");
        
        // Original tree
        TreeNode original = createSampleTree();
        System.out.println("Original tree (level order):");
        solution.printLevelOrder(original);
        
        // Test recursive approach
        System.out.println("\nAfter inverting (Recursive):");
        TreeNode inverted1 = solution.cloneTree(original);
        solution.invertTreeRecursive(inverted1);
        solution.printLevelOrder(inverted1);
        
        // Test BFS approach
        System.out.println("\nAfter inverting (BFS):");
        TreeNode inverted2 = solution.cloneTree(original);
        solution.invertTreeBFS(inverted2);
        solution.printLevelOrder(inverted2);
        
        // Test DFS approach
        System.out.println("\nAfter inverting (DFS Stack):");
        TreeNode inverted3 = solution.cloneTree(original);
        solution.invertTreeDFS(inverted3);
        solution.printLevelOrder(inverted3);
        
        // Test symmetric tree
        System.out.println("\nTesting Symmetric Tree:");
        TreeNode symmetric = new TreeNode(1);
        symmetric.left = new TreeNode(2);
        symmetric.right = new TreeNode(2);
        symmetric.left.left = new TreeNode(3);
        symmetric.left.right = new TreeNode(4);
        symmetric.right.left = new TreeNode(4);
        symmetric.right.right = new TreeNode(3);
        
        System.out.println("Is symmetric: " + solution.isSymmetric(symmetric));
        
        // Test asymmetric tree
        TreeNode asymmetric = new TreeNode(1);
        asymmetric.left = new TreeNode(2);
        asymmetric.right = new TreeNode(2);
        asymmetric.left.right = new TreeNode(3);
        asymmetric.right.right = new TreeNode(3);
        
        System.out.println("Is asymmetric: " + solution.isSymmetric(asymmetric));
    }
} 