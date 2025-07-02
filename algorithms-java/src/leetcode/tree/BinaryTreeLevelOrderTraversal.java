package leetcode.tree;

import java.util.*;

/**
 * LeetCode 102: Binary Tree Level Order Traversal
 * 
 * Given the root of a binary tree, return the level order traversal of its nodes' values.
 * (i.e., from left to right, level by level).
 * 
 * Example:
 *     3
 *    / \
 *   9   20
 *      /  \
 *     15   7
 * 
 * Output: [[3],[9,20],[15,7]]
 */
public class BinaryTreeLevelOrderTraversal {
    
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
     * Approach 1: BFS with Queue (Optimal)
     * Time Complexity: O(n) - Visit each node once
     * Space Complexity: O(w) - Queue size, w is max width of tree
     * 
     * Algorithm:
     * 1. Use queue for level order traversal
     * 2. Process each level completely before moving to next
     * 3. Track level size to separate levels
     */
    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> result = new ArrayList<>();
        
        if (root == null) {
            return result;
        }
        
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        
        while (!queue.isEmpty()) {
            int levelSize = queue.size();
            List<Integer> currentLevel = new ArrayList<>();
            
            // Process all nodes at current level
            for (int i = 0; i < levelSize; i++) {
                TreeNode node = queue.poll();
                currentLevel.add(node.val);
                
                // Add children for next level
                if (node.left != null) {
                    queue.offer(node.left);
                }
                if (node.right != null) {
                    queue.offer(node.right);
                }
            }
            
            result.add(currentLevel);
        }
        
        return result;
    }
    
    /**
     * Approach 2: DFS with Level Tracking
     * Time Complexity: O(n) - Visit each node once
     * Space Complexity: O(h) - Recursion stack, h is height
     * 
     * Uses DFS but tracks level to build result
     */
    public List<List<Integer>> levelOrderDFS(TreeNode root) {
        List<List<Integer>> result = new ArrayList<>();
        dfsHelper(root, 0, result);
        return result;
    }
    
    private void dfsHelper(TreeNode node, int level, List<List<Integer>> result) {
        if (node == null) {
            return;
        }
        
        // Create new level list if needed
        if (level >= result.size()) {
            result.add(new ArrayList<>());
        }
        
        // Add current node to its level
        result.get(level).add(node.val);
        
        // Recursively process children
        dfsHelper(node.left, level + 1, result);
        dfsHelper(node.right, level + 1, result);
    }
    
    /**
     * Extended Problem: Level Order Traversal II (Bottom-up)
     * LeetCode 107: Return levels from bottom to top
     */
    public List<List<Integer>> levelOrderBottom(TreeNode root) {
        List<List<Integer>> result = levelOrder(root);
        Collections.reverse(result);
        return result;
    }
    
    /**
     * Extended Problem: Zigzag Level Order Traversal
     * LeetCode 103: Alternate left-to-right and right-to-left
     */
    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        List<List<Integer>> result = new ArrayList<>();
        
        if (root == null) {
            return result;
        }
        
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        boolean leftToRight = true;
        
        while (!queue.isEmpty()) {
            int levelSize = queue.size();
            List<Integer> currentLevel = new ArrayList<>();
            
            for (int i = 0; i < levelSize; i++) {
                TreeNode node = queue.poll();
                
                if (leftToRight) {
                    currentLevel.add(node.val);
                } else {
                    currentLevel.add(0, node.val); // Add at beginning for reverse
                }
                
                if (node.left != null) {
                    queue.offer(node.left);
                }
                if (node.right != null) {
                    queue.offer(node.right);
                }
            }
            
            result.add(currentLevel);
            leftToRight = !leftToRight; // Alternate direction
        }
        
        return result;
    }
    
    /**
     * Extended Problem: Right Side View
     * LeetCode 199: Values visible from right side
     */
    public List<Integer> rightSideView(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        
        if (root == null) {
            return result;
        }
        
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        
        while (!queue.isEmpty()) {
            int levelSize = queue.size();
            
            for (int i = 0; i < levelSize; i++) {
                TreeNode node = queue.poll();
                
                // Add rightmost node of each level
                if (i == levelSize - 1) {
                    result.add(node.val);
                }
                
                if (node.left != null) {
                    queue.offer(node.left);
                }
                if (node.right != null) {
                    queue.offer(node.right);
                }
            }
        }
        
        return result;
    }
    
    /**
     * Extended Problem: Average of Levels
     * LeetCode 637: Average value of nodes at each level
     */
    public List<Double> averageOfLevels(TreeNode root) {
        List<Double> result = new ArrayList<>();
        
        if (root == null) {
            return result;
        }
        
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        
        while (!queue.isEmpty()) {
            int levelSize = queue.size();
            long sum = 0; // Use long to avoid overflow
            
            for (int i = 0; i < levelSize; i++) {
                TreeNode node = queue.poll();
                sum += node.val;
                
                if (node.left != null) {
                    queue.offer(node.left);
                }
                if (node.right != null) {
                    queue.offer(node.right);
                }
            }
            
            result.add((double) sum / levelSize);
        }
        
        return result;
    }
    
    /**
     * Extended Problem: Find Largest Value in Each Row
     * LeetCode 515: Maximum value at each level
     */
    public List<Integer> largestValues(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        
        if (root == null) {
            return result;
        }
        
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        
        while (!queue.isEmpty()) {
            int levelSize = queue.size();
            int maxValue = Integer.MIN_VALUE;
            
            for (int i = 0; i < levelSize; i++) {
                TreeNode node = queue.poll();
                maxValue = Math.max(maxValue, node.val);
                
                if (node.left != null) {
                    queue.offer(node.left);
                }
                if (node.right != null) {
                    queue.offer(node.right);
                }
            }
            
            result.add(maxValue);
        }
        
        return result;
    }
    
    /**
     * Helper method to create sample tree
     *     3
     *    / \
     *   9   20
     *      /  \
     *     15   7
     */
    private static TreeNode createSampleTree() {
        TreeNode root = new TreeNode(3);
        root.left = new TreeNode(9);
        root.right = new TreeNode(20);
        root.right.left = new TreeNode(15);
        root.right.right = new TreeNode(7);
        return root;
    }
    
    // Test the solutions
    public static void main(String[] args) {
        BinaryTreeLevelOrderTraversal solution = new BinaryTreeLevelOrderTraversal();
        
        // Test basic level order
        TreeNode root = createSampleTree();
        System.out.println("Basic Level Order Traversal:");
        System.out.println("BFS: " + solution.levelOrder(root));
        System.out.println("DFS: " + solution.levelOrderDFS(root));
        
        // Test extended problems
        System.out.println("\nExtended Problems:");
        System.out.println("Bottom-up: " + solution.levelOrderBottom(root));
        System.out.println("Zigzag: " + solution.zigzagLevelOrder(root));
        System.out.println("Right view: " + solution.rightSideView(root));
        System.out.println("Averages: " + solution.averageOfLevels(root));
        System.out.println("Max values: " + solution.largestValues(root));
        
        // Test with single node
        TreeNode single = new TreeNode(1);
        System.out.println("\nSingle node tests:");
        System.out.println("Level order: " + solution.levelOrder(single));
        System.out.println("Right view: " + solution.rightSideView(single));
        
        // Test with empty tree
        System.out.println("\nEmpty tree tests:");
        System.out.println("Level order: " + solution.levelOrder(null));
        System.out.println("Right view: " + solution.rightSideView(null));
    }
} 