package leetcode.tree;

import java.util.*;

/**
 * LeetCode 235: Lowest Common Ancestor of a Binary Search Tree
 * 
 * Given a binary search tree (BST), find the lowest common ancestor (LCA) of two given nodes.
 * The LCA is defined as the lowest node in T that has both p and q as descendants 
 * (where we allow a node to be a descendant of itself).
 * 
 * Example:
 *        6
 *       / \
 *      2   8
 *     / \ / \
 *    0  4 7  9
 *      / \
 *     3   5
 * 
 * LCA(2, 8) = 6, LCA(2, 4) = 2
 */
public class LowestCommonAncestorBST {
    
    static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        
        TreeNode(int x) { val = x; }
    }
    
    /**
     * Approach 1: Recursive with BST Properties (Optimal)
     * Time Complexity: O(h) - Height of tree, O(log n) average, O(n) worst
     * Space Complexity: O(h) - Recursion stack
     * 
     * Key Insight: Use BST property to determine direction
     * - If both p and q are less than current, go left
     * - If both p and q are greater than current, go right
     * - Otherwise, current node is the LCA
     */
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        // Base case
        if (root == null) {
            return null;
        }
        
        // If both p and q are smaller than root, LCA is in left subtree
        if (p.val < root.val && q.val < root.val) {
            return lowestCommonAncestor(root.left, p, q);
        }
        
        // If both p and q are greater than root, LCA is in right subtree
        if (p.val > root.val && q.val > root.val) {
            return lowestCommonAncestor(root.right, p, q);
        }
        
        // If one is smaller and one is greater, current root is LCA
        // This also handles the case where one of p or q is the root
        return root;
    }
    
    /**
     * Approach 2: Iterative with BST Properties
     * Time Complexity: O(h) - Height of tree
     * Space Complexity: O(1) - No extra space
     * 
     * Same logic as recursive but iterative
     */
    public TreeNode lowestCommonAncestorIterative(TreeNode root, TreeNode p, TreeNode q) {
        TreeNode current = root;
        
        while (current != null) {
            // Both in left subtree
            if (p.val < current.val && q.val < current.val) {
                current = current.left;
            }
            // Both in right subtree
            else if (p.val > current.val && q.val > current.val) {
                current = current.right;
            }
            // Found LCA
            else {
                return current;
            }
        }
        
        return null; // Should never reach here if p and q exist in tree
    }
    
    /**
     * Approach 3: Path Finding (General approach, works for any binary tree)
     * Time Complexity: O(n) - May visit all nodes
     * Space Complexity: O(h) - Path storage
     * 
     * This doesn't use BST properties, but shows general LCA approach
     */
    public TreeNode lowestCommonAncestorPath(TreeNode root, TreeNode p, TreeNode q) {
        List<TreeNode> pathToP = new ArrayList<>();
        List<TreeNode> pathToQ = new ArrayList<>();
        
        // Find paths to both nodes
        if (!findPath(root, p, pathToP) || !findPath(root, q, pathToQ)) {
            return null; // One of the nodes not found
        }
        
        // Find LCA by comparing paths
        TreeNode lca = null;
        int i = 0;
        while (i < pathToP.size() && i < pathToQ.size() && 
               pathToP.get(i) == pathToQ.get(i)) {
            lca = pathToP.get(i);
            i++;
        }
        
        return lca;
    }
    
    private boolean findPath(TreeNode root, TreeNode target, List<TreeNode> path) {
        if (root == null) {
            return false;
        }
        
        // Add current node to path
        path.add(root);
        
        // If target found, return true
        if (root == target) {
            return true;
        }
        
        // Search in subtrees
        if (findPath(root.left, target, path) || findPath(root.right, target, path)) {
            return true;
        }
        
        // If not found, remove current node from path
        path.remove(path.size() - 1);
        return false;
    }
    
    /**
     * Extended Problem: LCA in Binary Tree (not BST)
     * LeetCode 236: Works for any binary tree
     */
    public TreeNode lowestCommonAncestorBinaryTree(TreeNode root, TreeNode p, TreeNode q) {
        // Base case
        if (root == null || root == p || root == q) {
            return root;
        }
        
        // Recursively search in left and right subtrees
        TreeNode left = lowestCommonAncestorBinaryTree(root.left, p, q);
        TreeNode right = lowestCommonAncestorBinaryTree(root.right, p, q);
        
        // If both left and right are non-null, current node is LCA
        if (left != null && right != null) {
            return root;
        }
        
        // Return non-null result
        return left != null ? left : right;
    }
    
    /**
     * Extended Problem: All Ancestors of a Node
     * Find all ancestors of a given node in BST
     */
    public List<Integer> findAllAncestors(TreeNode root, TreeNode target) {
        List<Integer> ancestors = new ArrayList<>();
        findAncestorsHelper(root, target, ancestors);
        return ancestors;
    }
    
    private boolean findAncestorsHelper(TreeNode root, TreeNode target, List<Integer> ancestors) {
        if (root == null) {
            return false;
        }
        
        if (root == target) {
            return true;
        }
        
        // If target found in subtree, current node is an ancestor
        if (findAncestorsHelper(root.left, target, ancestors) ||
            findAncestorsHelper(root.right, target, ancestors)) {
            ancestors.add(root.val);
            return true;
        }
        
        return false;
    }
    
    /**
     * Extended Problem: Distance between two nodes in BST
     */
    public int findDistance(TreeNode root, TreeNode p, TreeNode q) {
        TreeNode lca = lowestCommonAncestor(root, p, q);
        int distanceP = findDistanceFromNode(lca, p);
        int distanceQ = findDistanceFromNode(lca, q);
        return distanceP + distanceQ;
    }
    
    private int findDistanceFromNode(TreeNode root, TreeNode target) {
        if (root == null) {
            return -1;
        }
        
        if (root == target) {
            return 0;
        }
        
        int leftDist = findDistanceFromNode(root.left, target);
        int rightDist = findDistanceFromNode(root.right, target);
        
        if (leftDist >= 0) {
            return leftDist + 1;
        }
        if (rightDist >= 0) {
            return rightDist + 1;
        }
        
        return -1;
    }
    
    /**
     * Extended Problem: LCA of Multiple Nodes
     * Find LCA of a list of nodes
     */
    public TreeNode lowestCommonAncestorMultiple(TreeNode root, List<TreeNode> nodes) {
        if (nodes.isEmpty()) {
            return null;
        }
        
        TreeNode lca = nodes.get(0);
        for (int i = 1; i < nodes.size(); i++) {
            lca = lowestCommonAncestor(root, lca, nodes.get(i));
        }
        
        return lca;
    }
    
    /**
     * Helper method to create the sample BST
     *        6
     *       / \
     *      2   8
     *     / \ / \
     *    0  4 7  9
     *      / \
     *     3   5
     */
    private static TreeNode createSampleBST() {
        TreeNode root = new TreeNode(6);
        root.left = new TreeNode(2);
        root.right = new TreeNode(8);
        root.left.left = new TreeNode(0);
        root.left.right = new TreeNode(4);
        root.right.left = new TreeNode(7);
        root.right.right = new TreeNode(9);
        root.left.right.left = new TreeNode(3);
        root.left.right.right = new TreeNode(5);
        return root;
    }
    
    /**
     * Helper method to find node by value
     */
    private TreeNode findNode(TreeNode root, int val) {
        if (root == null || root.val == val) {
            return root;
        }
        
        if (val < root.val) {
            return findNode(root.left, val);
        } else {
            return findNode(root.right, val);
        }
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
        LowestCommonAncestorBST solution = new LowestCommonAncestorBST();
        
        // Create sample BST
        TreeNode root = createSampleBST();
        System.out.println("Sample BST (inorder): ");
        solution.printInorder(root);
        System.out.println();
        
        // Test cases
        TreeNode p = solution.findNode(root, 2);
        TreeNode q = solution.findNode(root, 8);
        
        System.out.println("Testing LCA of nodes 2 and 8:");
        TreeNode lca1 = solution.lowestCommonAncestor(root, p, q);
        System.out.println("Recursive: " + (lca1 != null ? lca1.val : "null"));
        
        TreeNode lca2 = solution.lowestCommonAncestorIterative(root, p, q);
        System.out.println("Iterative: " + (lca2 != null ? lca2.val : "null"));
        
        TreeNode lca3 = solution.lowestCommonAncestorPath(root, p, q);
        System.out.println("Path finding: " + (lca3 != null ? lca3.val : "null"));
        
        // Test case 2: One node is ancestor of other
        TreeNode p2 = solution.findNode(root, 2);
        TreeNode q2 = solution.findNode(root, 4);
        
        System.out.println("\nTesting LCA of nodes 2 and 4:");
        TreeNode lca4 = solution.lowestCommonAncestor(root, p2, q2);
        System.out.println("Result: " + (lca4 != null ? lca4.val : "null"));
        
        // Test case 3: Leaf nodes
        TreeNode p3 = solution.findNode(root, 3);
        TreeNode q3 = solution.findNode(root, 5);
        
        System.out.println("\nTesting LCA of nodes 3 and 5:");
        TreeNode lca5 = solution.lowestCommonAncestor(root, p3, q3);
        System.out.println("Result: " + (lca5 != null ? lca5.val : "null"));
        
        // Test extended problems
        System.out.println("\nTesting Extended Problems:");
        
        // All ancestors
        TreeNode target = solution.findNode(root, 5);
        List<Integer> ancestors = solution.findAllAncestors(root, target);
        System.out.println("Ancestors of node 5: " + ancestors);
        
        // Distance between nodes
        int distance = solution.findDistance(root, p2, q2);
        System.out.println("Distance between nodes 2 and 4: " + distance);
        
        // LCA of multiple nodes
        List<TreeNode> nodes = Arrays.asList(
            solution.findNode(root, 3),
            solution.findNode(root, 5),
            solution.findNode(root, 7)
        );
        TreeNode multiLCA = solution.lowestCommonAncestorMultiple(root, nodes);
        System.out.println("LCA of nodes 3, 5, 7: " + (multiLCA != null ? multiLCA.val : "null"));
        
        // Test with different tree structure
        System.out.println("\nTesting with skewed tree:");
        TreeNode skewed = new TreeNode(1);
        skewed.right = new TreeNode(2);
        skewed.right.right = new TreeNode(3);
        
        TreeNode sp = solution.findNode(skewed, 1);
        TreeNode sq = solution.findNode(skewed, 3);
        TreeNode skewedLCA = solution.lowestCommonAncestor(skewed, sp, sq);
        System.out.println("LCA of 1 and 3 in skewed tree: " + (skewedLCA != null ? skewedLCA.val : "null"));
    }
} 