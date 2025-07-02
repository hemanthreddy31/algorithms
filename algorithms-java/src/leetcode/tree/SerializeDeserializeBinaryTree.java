package leetcode.tree;

import java.util.*;

/**
 * LeetCode 297: Serialize and Deserialize Binary Tree
 * 
 * Design an algorithm to serialize and deserialize a binary tree.
 * Serialization is the process of converting a data structure into a sequence of bits.
 * Deserialization is the reverse process.
 * 
 * Example:
 *     1
 *    / \
 *   2   3
 *      / \
 *     4   5
 * 
 * Serialized: "1,2,null,null,3,4,null,null,5,null,null"
 */
public class SerializeDeserializeBinaryTree {
    
    static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        
        TreeNode(int x) { val = x; }
    }
    
    /**
     * Approach 1: Preorder Traversal with Recursion (Optimal)
     * Time Complexity: O(n) for both serialize and deserialize
     * Space Complexity: O(n) for storing tree structure
     * 
     * Uses preorder traversal with null markers
     */
    public class Codec {
        private static final String NULL_MARKER = "null";
        private static final String DELIMITER = ",";
        
        // Encodes a tree to a single string
        public String serialize(TreeNode root) {
            StringBuilder sb = new StringBuilder();
            serializeHelper(root, sb);
            return sb.toString();
        }
        
        private void serializeHelper(TreeNode node, StringBuilder sb) {
            if (node == null) {
                sb.append(NULL_MARKER).append(DELIMITER);
                return;
            }
            
            sb.append(node.val).append(DELIMITER);
            serializeHelper(node.left, sb);
            serializeHelper(node.right, sb);
        }
        
        // Decodes your encoded data to tree
        public TreeNode deserialize(String data) {
            Queue<String> nodes = new LinkedList<>(Arrays.asList(data.split(DELIMITER)));
            return deserializeHelper(nodes);
        }
        
        private TreeNode deserializeHelper(Queue<String> nodes) {
            String val = nodes.poll();
            
            if (NULL_MARKER.equals(val)) {
                return null;
            }
            
            TreeNode node = new TreeNode(Integer.parseInt(val));
            node.left = deserializeHelper(nodes);
            node.right = deserializeHelper(nodes);
            
            return node;
        }
    }
    
    /**
     * Approach 2: Level Order Traversal (BFS)
     * Time Complexity: O(n) for both operations
     * Space Complexity: O(n) for queue and result
     * 
     * Uses level order traversal with null markers
     */
    public class CodecBFS {
        private static final String NULL_MARKER = "null";
        private static final String DELIMITER = ",";
        
        public String serialize(TreeNode root) {
            if (root == null) {
                return NULL_MARKER;
            }
            
            StringBuilder sb = new StringBuilder();
            Queue<TreeNode> queue = new LinkedList<>();
            queue.offer(root);
            
            while (!queue.isEmpty()) {
                TreeNode node = queue.poll();
                
                if (node == null) {
                    sb.append(NULL_MARKER).append(DELIMITER);
                } else {
                    sb.append(node.val).append(DELIMITER);
                    queue.offer(node.left);
                    queue.offer(node.right);
                }
            }
            
            return sb.toString();
        }
        
        public TreeNode deserialize(String data) {
            if (NULL_MARKER.equals(data.trim())) {
                return null;
            }
            
            String[] nodes = data.split(DELIMITER);
            Queue<TreeNode> queue = new LinkedList<>();
            
            TreeNode root = new TreeNode(Integer.parseInt(nodes[0]));
            queue.offer(root);
            
            int index = 1;
            while (!queue.isEmpty() && index < nodes.length) {
                TreeNode node = queue.poll();
                
                // Process left child
                if (index < nodes.length && !NULL_MARKER.equals(nodes[index])) {
                    node.left = new TreeNode(Integer.parseInt(nodes[index]));
                    queue.offer(node.left);
                }
                index++;
                
                // Process right child
                if (index < nodes.length && !NULL_MARKER.equals(nodes[index])) {
                    node.right = new TreeNode(Integer.parseInt(nodes[index]));
                    queue.offer(node.right);
                }
                index++;
            }
            
            return root;
        }
    }
    
    /**
     * Approach 3: Postorder Traversal
     * Time Complexity: O(n)
     * Space Complexity: O(n)
     * 
     * Uses postorder for serialization
     */
    public class CodecPostorder {
        private static final String NULL_MARKER = "null";
        private static final String DELIMITER = ",";
        
        public String serialize(TreeNode root) {
            StringBuilder sb = new StringBuilder();
            serializePostorder(root, sb);
            return sb.toString();
        }
        
        private void serializePostorder(TreeNode node, StringBuilder sb) {
            if (node == null) {
                sb.append(NULL_MARKER).append(DELIMITER);
                return;
            }
            
            serializePostorder(node.left, sb);
            serializePostorder(node.right, sb);
            sb.append(node.val).append(DELIMITER);
        }
        
        public TreeNode deserialize(String data) {
            Queue<String> nodes = new LinkedList<>(Arrays.asList(data.split(DELIMITER)));
            return deserializePostorder(nodes);
        }
        
        private TreeNode deserializePostorder(Queue<String> nodes) {
            String val = nodes.poll();
            
            if (NULL_MARKER.equals(val)) {
                return null;
            }
            
            // For postorder, we need to reverse the process
            TreeNode node = new TreeNode(Integer.parseInt(val));
            node.right = deserializePostorder(nodes);
            node.left = deserializePostorder(nodes);
            
            return node;
        }
    }
    
    /**
     * Extended Problem: Serialize BST (Optimized)
     * For BST, we don't need null markers since structure is determined by values
     */
    public class CodecBST {
        private static final String DELIMITER = ",";
        
        public String serialize(TreeNode root) {
            StringBuilder sb = new StringBuilder();
            serializeBST(root, sb);
            return sb.toString();
        }
        
        private void serializeBST(TreeNode node, StringBuilder sb) {
            if (node == null) {
                return;
            }
            
            sb.append(node.val).append(DELIMITER);
            serializeBST(node.left, sb);
            serializeBST(node.right, sb);
        }
        
        public TreeNode deserialize(String data) {
            if (data.isEmpty()) {
                return null;
            }
            
            String[] values = data.split(DELIMITER);
            return deserializeBST(values, 0, values.length - 1);
        }
        
        private TreeNode deserializeBST(String[] values, int start, int end) {
            if (start > end) {
                return null;
            }
            
            int rootVal = Integer.parseInt(values[start]);
            TreeNode root = new TreeNode(rootVal);
            
            // Find the first value greater than root (right subtree start)
            int rightStart = start + 1;
            while (rightStart <= end && Integer.parseInt(values[rightStart]) < rootVal) {
                rightStart++;
            }
            
            root.left = deserializeBST(values, start + 1, rightStart - 1);
            root.right = deserializeBST(values, rightStart, end);
            
            return root;
        }
    }
    
    /**
     * Helper method to create sample tree
     *     1
     *    / \
     *   2   3
     *      / \
     *     4   5
     */
    private static TreeNode createSampleTree() {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.right.left = new TreeNode(4);
        root.right.right = new TreeNode(5);
        return root;
    }
    
    /**
     * Helper method to create sample BST
     *     5
     *    / \
     *   3   7
     *  / \ / \
     * 2  4 6  8
     */
    private static TreeNode createSampleBST() {
        TreeNode root = new TreeNode(5);
        root.left = new TreeNode(3);
        root.right = new TreeNode(7);
        root.left.left = new TreeNode(2);
        root.left.right = new TreeNode(4);
        root.right.left = new TreeNode(6);
        root.right.right = new TreeNode(8);
        return root;
    }
    
    /**
     * Helper method to print tree preorder
     */
    private static void printPreorder(TreeNode root) {
        if (root == null) {
            System.out.print("null ");
            return;
        }
        
        System.out.print(root.val + " ");
        printPreorder(root.left);
        printPreorder(root.right);
    }
    
    /**
     * Helper method to check if two trees are equal
     */
    private static boolean isEqual(TreeNode p, TreeNode q) {
        if (p == null && q == null) {
            return true;
        }
        
        if (p == null || q == null) {
            return false;
        }
        
        return p.val == q.val && isEqual(p.left, q.left) && isEqual(p.right, q.right);
    }
    
    // Test the solutions
    public static void main(String[] args) {
        SerializeDeserializeBinaryTree solution = new SerializeDeserializeBinaryTree();
        
        // Test Preorder approach
        TreeNode original = createSampleTree();
        System.out.println("Original tree (preorder): ");
        printPreorder(original);
        System.out.println();
        
        Codec codec = solution.new Codec();
        String serialized = codec.serialize(original);
        System.out.println("Serialized (Preorder): " + serialized);
        
        TreeNode deserialized = codec.deserialize(serialized);
        System.out.println("Deserialized tree (preorder): ");
        printPreorder(deserialized);
        System.out.println();
        System.out.println("Trees equal: " + isEqual(original, deserialized));
        
        // Test BFS approach
        System.out.println("\n" + "=".repeat(50));
        CodecBFS codecBFS = solution.new CodecBFS();
        String serializedBFS = codecBFS.serialize(original);
        System.out.println("Serialized (BFS): " + serializedBFS);
        
        TreeNode deserializedBFS = codecBFS.deserialize(serializedBFS);
        System.out.println("Deserialized tree (preorder): ");
        printPreorder(deserializedBFS);
        System.out.println();
        System.out.println("Trees equal: " + isEqual(original, deserializedBFS));
        
        // Test BST optimization
        System.out.println("\n" + "=".repeat(50));
        TreeNode bst = createSampleBST();
        System.out.println("Original BST (preorder): ");
        printPreorder(bst);
        System.out.println();
        
        CodecBST codecBST = solution.new CodecBST();
        String serializedBST = codecBST.serialize(bst);
        System.out.println("Serialized BST (no nulls): " + serializedBST);
        
        TreeNode deserializedBST = codecBST.deserialize(serializedBST);
        System.out.println("Deserialized BST (preorder): ");
        printPreorder(deserializedBST);
        System.out.println();
        System.out.println("BSTs equal: " + isEqual(bst, deserializedBST));
        
        // Test edge cases
        System.out.println("\n" + "=".repeat(50));
        System.out.println("Edge Cases:");
        
        // Empty tree
        String emptyTree = codec.serialize(null);
        System.out.println("Empty tree serialized: " + emptyTree);
        TreeNode emptyDeserialized = codec.deserialize(emptyTree);
        System.out.println("Empty tree deserialized: " + (emptyDeserialized == null ? "null" : "not null"));
        
        // Single node
        TreeNode singleNode = new TreeNode(42);
        String singleSerialized = codec.serialize(singleNode);
        System.out.println("Single node serialized: " + singleSerialized);
        TreeNode singleDeserialized = codec.deserialize(singleSerialized);
        System.out.println("Single node value: " + singleDeserialized.val);
        
        // Skewed tree
        TreeNode skewed = new TreeNode(1);
        skewed.right = new TreeNode(2);
        skewed.right.right = new TreeNode(3);
        String skewedSerialized = codec.serialize(skewed);
        System.out.println("Skewed tree serialized: " + skewedSerialized);
        TreeNode skewedDeserialized = codec.deserialize(skewedSerialized);
        System.out.println("Skewed trees equal: " + isEqual(skewed, skewedDeserialized));
    }
} 