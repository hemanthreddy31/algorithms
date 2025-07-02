package leetcode.tree;

/**
 * LeetCode 124: Binary Tree Maximum Path Sum
 * 
 * A path in a binary tree is a sequence of nodes where each pair of adjacent nodes 
 * in the sequence has an edge connecting them. A node can only appear in the sequence 
 * at most once. The path does not need to pass through the root.
 * 
 * The path sum of a path is the sum of the node's values in the path.
 * Given the root of a binary tree, return the maximum path sum of any non-empty path.
 * 
 * Example:
 *     1
 *    / \
 *   2   3
 * 
 * Output: 6 (path: 2 -> 1 -> 3)
 */
public class BinaryTreeMaximumPathSum {
    
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
     * Approach 1: DFS with Global Maximum (Optimal)
     * Time Complexity: O(n) - Visit each node once
     * Space Complexity: O(h) - Recursion stack, h is height
     * 
     * Key Insight: At each node, consider two possibilities:
     * 1. Max path goes through this node (left + node + right)
     * 2. Max path goes through one child and continues upward
     */
    private int maxPathSum = Integer.MIN_VALUE;
    
    public int maxPathSum(TreeNode root) {
        maxPathSum = Integer.MIN_VALUE; // Reset for each call
        maxPathSumHelper(root);
        return maxPathSum;
    }
    
    private int maxPathSumHelper(TreeNode node) {
        if (node == null) {
            return 0;
        }
        
        // Get max path sum from left and right subtrees
        // Use Math.max(0, sum) to ignore negative paths
        int leftSum = Math.max(0, maxPathSumHelper(node.left));
        int rightSum = Math.max(0, maxPathSumHelper(node.right));
        
        // Max path sum passing through current node
        int currentMaxPath = node.val + leftSum + rightSum;
        
        // Update global maximum
        maxPathSum = Math.max(maxPathSum, currentMaxPath);
        
        // Return max path sum that can be extended to parent
        return node.val + Math.max(leftSum, rightSum);
    }
    
    /**
     * Approach 2: DFS with Result Wrapper
     * Same algorithm but uses wrapper class to avoid global variable
     */
    private static class Result {
        int maxSum;
        
        Result(int maxSum) {
            this.maxSum = maxSum;
        }
    }
    
    public int maxPathSumWrapper(TreeNode root) {
        Result result = new Result(Integer.MIN_VALUE);
        maxPathSumHelper(root, result);
        return result.maxSum;
    }
    
    private int maxPathSumHelper(TreeNode node, Result result) {
        if (node == null) {
            return 0;
        }
        
        int leftSum = Math.max(0, maxPathSumHelper(node.left, result));
        int rightSum = Math.max(0, maxPathSumHelper(node.right, result));
        
        int currentMaxPath = node.val + leftSum + rightSum;
        result.maxSum = Math.max(result.maxSum, currentMaxPath);
        
        return node.val + Math.max(leftSum, rightSum);
    }
    
    /**
     * Extended Problem: Binary Tree Maximum Path Sum from Leaf to Leaf
     * Path must start and end at leaf nodes
     */
    public int maxPathSumLeafToLeaf(TreeNode root) {
        int[] maxSum = {Integer.MIN_VALUE};
        maxPathSumLeafToLeafHelper(root, maxSum);
        return maxSum[0];
    }
    
    private int maxPathSumLeafToLeafHelper(TreeNode node, int[] maxSum) {
        if (node == null) {
            return 0;
        }
        
        // If leaf node, return its value
        if (node.left == null && node.right == null) {
            return node.val;
        }
        
        int leftSum = Integer.MIN_VALUE;
        int rightSum = Integer.MIN_VALUE;
        
        if (node.left != null) {
            leftSum = maxPathSumLeafToLeafHelper(node.left, maxSum);
        }
        
        if (node.right != null) {
            rightSum = maxPathSumLeafToLeafHelper(node.right, maxSum);
        }
        
        // If both children exist, we can form a leaf-to-leaf path
        if (node.left != null && node.right != null) {
            int currentPath = leftSum + node.val + rightSum;
            maxSum[0] = Math.max(maxSum[0], currentPath);
            
            // Return the better path to extend upward
            return node.val + Math.max(leftSum, rightSum);
        }
        
        // If only one child exists, extend that path
        return node.val + Math.max(leftSum, rightSum);
    }
    
    /**
     * Extended Problem: Maximum Path Sum with At Most K Nodes
     * Find maximum path sum using at most K nodes
     */
    public int maxPathSumWithKNodes(TreeNode root, int k) {
        int[] maxSum = {Integer.MIN_VALUE};
        maxPathSumWithKNodesHelper(root, k, maxSum);
        return maxSum[0];
    }
    
    private int[] maxPathSumWithKNodesHelper(TreeNode node, int k, int[] maxSum) {
        if (node == null || k <= 0) {
            return new int[]{Integer.MIN_VALUE, 0}; // {maxSum, nodeCount}
        }
        
        if (k == 1) {
            maxSum[0] = Math.max(maxSum[0], node.val);
            return new int[]{node.val, 1};
        }
        
        int[] left = maxPathSumWithKNodesHelper(node.left, k - 1, maxSum);
        int[] right = maxPathSumWithKNodesHelper(node.right, k - 1, maxSum);
        
        // Try different combinations
        int currentSum = node.val;
        int currentCount = 1;
        
        // Include left subtree
        if (left[1] > 0) {
            currentSum += left[0];
            currentCount += left[1];
        }
        
        // Include right subtree if we have budget
        if (right[1] > 0 && currentCount + right[1] <= k) {
            currentSum += right[0];
            currentCount += right[1];
        }
        
        maxSum[0] = Math.max(maxSum[0], currentSum);
        
        // Return best single path
        int bestPath = node.val;
        int bestCount = 1;
        
        if (left[1] > 0 && left[0] > 0) {
            bestPath = Math.max(bestPath, node.val + left[0]);
            bestCount = 1 + left[1];
        }
        
        if (right[1] > 0 && right[0] > 0) {
            if (node.val + right[0] > bestPath) {
                bestPath = node.val + right[0];
                bestCount = 1 + right[1];
            }
        }
        
        return new int[]{bestPath, Math.min(bestCount, k)};
    }
    
    /**
     * Extended Problem: Maximum Path Sum from Root to Any Node
     * Path must start from root
     */
    public int maxPathSumFromRoot(TreeNode root) {
        if (root == null) {
            return 0;
        }
        
        return Math.max(root.val, 
                       Math.max(root.val + maxPathSumFromRoot(root.left),
                               root.val + maxPathSumFromRoot(root.right)));
    }
    
    /**
     * Helper method to create sample tree
     *     1
     *    / \
     *   2   3
     */
    private static TreeNode createSampleTree() {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        return root;
    }
    
    /**
     * Helper method to create tree with negative values
     *      -10
     *     /   \
     *    9     20
     *         /  \
     *        15   7
     */
    private static TreeNode createTreeWithNegatives() {
        TreeNode root = new TreeNode(-10);
        root.left = new TreeNode(9);
        root.right = new TreeNode(20);
        root.right.left = new TreeNode(15);
        root.right.right = new TreeNode(7);
        return root;
    }
    
    /**
     * Helper method to create complex tree
     *       5
     *      / \
     *     4   8
     *    /   / \
     *   11  13  4
     *  / \      \
     * 7   2      1
     */
    private static TreeNode createComplexTree() {
        TreeNode root = new TreeNode(5);
        root.left = new TreeNode(4);
        root.right = new TreeNode(8);
        root.left.left = new TreeNode(11);
        root.left.left.left = new TreeNode(7);
        root.left.left.right = new TreeNode(2);
        root.right.left = new TreeNode(13);
        root.right.right = new TreeNode(4);
        root.right.right.right = new TreeNode(1);
        return root;
    }
    
    // Test the solutions
    public static void main(String[] args) {
        BinaryTreeMaximumPathSum solution = new BinaryTreeMaximumPathSum();
        
        // Test case 1: Simple tree
        TreeNode tree1 = createSampleTree();
        System.out.println("Test Case 1: Simple tree [1,2,3]");
        System.out.println("Max path sum: " + solution.maxPathSum(tree1));
        System.out.println("Max path sum (wrapper): " + solution.maxPathSumWrapper(tree1));
        
        // Test case 2: Tree with negative values
        TreeNode tree2 = createTreeWithNegatives();
        System.out.println("\nTest Case 2: Tree with negatives [-10,9,20,null,null,15,7]");
        System.out.println("Max path sum: " + solution.maxPathSum(tree2));
        
        // Test case 3: Single node with negative value
        TreeNode tree3 = new TreeNode(-3);
        System.out.println("\nTest Case 3: Single negative node [-3]");
        System.out.println("Max path sum: " + solution.maxPathSum(tree3));
        
        // Test case 4: Complex tree
        TreeNode tree4 = createComplexTree();
        System.out.println("\nTest Case 4: Complex tree");
        System.out.println("Max path sum: " + solution.maxPathSum(tree4));
        
        // Test case 5: All negative values
        TreeNode tree5 = new TreeNode(-1);
        tree5.left = new TreeNode(-2);
        tree5.right = new TreeNode(-3);
        System.out.println("\nTest Case 5: All negative [-1,-2,-3]");
        System.out.println("Max path sum: " + solution.maxPathSum(tree5));
        
        // Test extended problems
        System.out.println("\n" + "=".repeat(50));
        System.out.println("Extended Problems:");
        
        // Leaf to leaf path
        System.out.println("Leaf to leaf max path: " + solution.maxPathSumLeafToLeaf(tree2));
        
        // Root to any node path
        System.out.println("Root to any node max path: " + solution.maxPathSumFromRoot(tree2));
        
        // Max path with K nodes
        System.out.println("Max path with 3 nodes: " + solution.maxPathSumWithKNodes(tree2, 3));
        
        // Edge case: Empty tree
        System.out.println("\nEdge Case - Empty tree:");
        System.out.println("Max path sum: " + solution.maxPathSum(null));
    }
} 