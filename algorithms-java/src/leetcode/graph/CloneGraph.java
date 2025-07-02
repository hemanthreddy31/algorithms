package leetcode.graph;

import java.util.*;

/**
 * LeetCode 133: Clone Graph
 * 
 * Given a reference of a node in a connected undirected graph.
 * Return a deep copy (clone) of the graph.
 * 
 * Each node in the graph contains a value (int) and a list (List[Node]) of its neighbors.
 * 
 * Example:
 * Input: adjList = [[2,4],[1,3],[2,4],[1,3]]
 * Output: [[2,4],[1,3],[2,4],[1,3]]
 */
public class CloneGraph {
    
    /**
     * Definition for a Node
     */
    static class Node {
        public int val;
        public List<Node> neighbors;
        
        public Node() {
            val = 0;
            neighbors = new ArrayList<Node>();
        }
        
        public Node(int _val) {
            val = _val;
            neighbors = new ArrayList<Node>();
        }
        
        public Node(int _val, ArrayList<Node> _neighbors) {
            val = _val;
            neighbors = _neighbors;
        }
    }
    
    /**
     * Approach 1: DFS with HashMap (Optimal)
     * Time Complexity: O(V + E) - Visit each node and edge once
     * Space Complexity: O(V) - HashMap and recursion stack
     * 
     * Algorithm:
     * 1. Use HashMap to store mapping from original to cloned nodes
     * 2. For each node, clone it if not already cloned
     * 3. Recursively clone all neighbors
     */
    public Node cloneGraphDFS(Node node) {
        if (node == null) {
            return null;
        }
        
        Map<Node, Node> visited = new HashMap<>();
        return dfsHelper(node, visited);
    }
    
    private Node dfsHelper(Node node, Map<Node, Node> visited) {
        // If already cloned, return the clone
        if (visited.containsKey(node)) {
            return visited.get(node);
        }
        
        // Create clone of current node
        Node clone = new Node(node.val);
        visited.put(node, clone);
        
        // Clone all neighbors
        for (Node neighbor : node.neighbors) {
            clone.neighbors.add(dfsHelper(neighbor, visited));
        }
        
        return clone;
    }
    
    /**
     * Approach 2: BFS with HashMap
     * Time Complexity: O(V + E) - Visit each node and edge once
     * Space Complexity: O(V) - HashMap and queue
     * 
     * Algorithm:
     * 1. Use BFS to traverse the graph
     * 2. Clone nodes level by level
     * 3. Use HashMap to track cloned nodes
     */
    public Node cloneGraphBFS(Node node) {
        if (node == null) {
            return null;
        }
        
        Map<Node, Node> visited = new HashMap<>();
        Queue<Node> queue = new LinkedList<>();
        
        // Clone the first node and add to queue
        Node clone = new Node(node.val);
        visited.put(node, clone);
        queue.offer(node);
        
        while (!queue.isEmpty()) {
            Node current = queue.poll();
            
            // Process all neighbors
            for (Node neighbor : current.neighbors) {
                if (!visited.containsKey(neighbor)) {
                    // Clone neighbor if not already cloned
                    visited.put(neighbor, new Node(neighbor.val));
                    queue.offer(neighbor);
                }
                
                // Add neighbor clone to current clone's neighbors
                visited.get(current).neighbors.add(visited.get(neighbor));
            }
        }
        
        return clone;
    }
    
    /**
     * Approach 3: DFS without separate helper (inline)
     * Time Complexity: O(V + E)
     * Space Complexity: O(V)
     */
    private Map<Node, Node> visited = new HashMap<>();
    
    public Node cloneGraphInline(Node node) {
        if (node == null) {
            return null;
        }
        
        visited.clear(); // Reset for each call
        return dfsInline(node);
    }
    
    private Node dfsInline(Node node) {
        if (visited.containsKey(node)) {
            return visited.get(node);
        }
        
        Node clone = new Node(node.val);
        visited.put(node, clone);
        
        for (Node neighbor : node.neighbors) {
            clone.neighbors.add(dfsInline(neighbor));
        }
        
        return clone;
    }
    
    /**
     * Approach 4: Iterative DFS with Stack
     * Time Complexity: O(V + E)
     * Space Complexity: O(V)
     */
    public Node cloneGraphIterativeDFS(Node node) {
        if (node == null) {
            return null;
        }
        
        Map<Node, Node> visited = new HashMap<>();
        Stack<Node> stack = new Stack<>();
        
        // Create clone of starting node
        visited.put(node, new Node(node.val));
        stack.push(node);
        
        while (!stack.isEmpty()) {
            Node current = stack.pop();
            
            for (Node neighbor : current.neighbors) {
                if (!visited.containsKey(neighbor)) {
                    // Clone neighbor
                    visited.put(neighbor, new Node(neighbor.val));
                    stack.push(neighbor);
                }
                
                // Add neighbor to current clone
                visited.get(current).neighbors.add(visited.get(neighbor));
            }
        }
        
        return visited.get(node);
    }
    
    /**
     * Extended Problem: Clone Graph with Random Pointers
     * Similar to LinkedList with random pointers but for graph
     */
    static class NodeWithRandom {
        public int val;
        public List<NodeWithRandom> neighbors;
        public NodeWithRandom random;
        
        public NodeWithRandom(int val) {
            this.val = val;
            this.neighbors = new ArrayList<>();
            this.random = null;
        }
    }
    
    public NodeWithRandom cloneGraphWithRandom(NodeWithRandom node) {
        if (node == null) {
            return null;
        }
        
        Map<NodeWithRandom, NodeWithRandom> visited = new HashMap<>();
        
        // First pass: clone all nodes
        dfsCloneNodes(node, visited);
        
        // Second pass: set random pointers
        dfsSetRandom(node, visited);
        
        return visited.get(node);
    }
    
    private void dfsCloneNodes(NodeWithRandom node, Map<NodeWithRandom, NodeWithRandom> visited) {
        if (visited.containsKey(node)) {
            return;
        }
        
        visited.put(node, new NodeWithRandom(node.val));
        
        for (NodeWithRandom neighbor : node.neighbors) {
            dfsCloneNodes(neighbor, visited);
        }
    }
    
    private void dfsSetRandom(NodeWithRandom node, Map<NodeWithRandom, NodeWithRandom> visited) {
        NodeWithRandom clone = visited.get(node);
        
        // Set neighbors
        for (NodeWithRandom neighbor : node.neighbors) {
            clone.neighbors.add(visited.get(neighbor));
        }
        
        // Set random pointer
        if (node.random != null) {
            clone.random = visited.get(node.random);
        }
    }
    
    /**
     * Helper method to create a sample graph
     * Creates: 1 -- 2
     *          |    |
     *          4 -- 3
     */
    private static Node createSampleGraph() {
        Node node1 = new Node(1);
        Node node2 = new Node(2);
        Node node3 = new Node(3);
        Node node4 = new Node(4);
        
        node1.neighbors.add(node2);
        node1.neighbors.add(node4);
        
        node2.neighbors.add(node1);
        node2.neighbors.add(node3);
        
        node3.neighbors.add(node2);
        node3.neighbors.add(node4);
        
        node4.neighbors.add(node1);
        node4.neighbors.add(node3);
        
        return node1;
    }
    
    /**
     * Helper method to print graph using BFS
     */
    private static void printGraph(Node start) {
        if (start == null) {
            System.out.println("Empty graph");
            return;
        }
        
        Set<Node> visited = new HashSet<>();
        Queue<Node> queue = new LinkedList<>();
        queue.offer(start);
        visited.add(start);
        
        System.out.print("Graph: ");
        while (!queue.isEmpty()) {
            Node current = queue.poll();
            System.out.print(current.val + "[");
            
            for (int i = 0; i < current.neighbors.size(); i++) {
                Node neighbor = current.neighbors.get(i);
                System.out.print(neighbor.val);
                if (i < current.neighbors.size() - 1) {
                    System.out.print(",");
                }
                
                if (!visited.contains(neighbor)) {
                    visited.add(neighbor);
                    queue.offer(neighbor);
                }
            }
            System.out.print("] ");
        }
        System.out.println();
    }
    
    /**
     * Helper method to verify if two graphs are identical
     */
    private static boolean areGraphsIdentical(Node graph1, Node graph2) {
        if (graph1 == null && graph2 == null) {
            return true;
        }
        
        if (graph1 == null || graph2 == null) {
            return false;
        }
        
        Map<Node, Node> mapping = new HashMap<>();
        return dfsCompare(graph1, graph2, mapping);
    }
    
    private static boolean dfsCompare(Node node1, Node node2, Map<Node, Node> mapping) {
        if (node1.val != node2.val) {
            return false;
        }
        
        if (mapping.containsKey(node1)) {
            return mapping.get(node1) == node2;
        }
        
        mapping.put(node1, node2);
        
        if (node1.neighbors.size() != node2.neighbors.size()) {
            return false;
        }
        
        for (int i = 0; i < node1.neighbors.size(); i++) {
            if (!dfsCompare(node1.neighbors.get(i), node2.neighbors.get(i), mapping)) {
                return false;
            }
        }
        
        return true;
    }
    
    // Test the solutions
    public static void main(String[] args) {
        CloneGraph solution = new CloneGraph();
        
        // Test case 1: Connected graph
        Node original = createSampleGraph();
        System.out.println("Original:");
        printGraph(original);
        
        // Test DFS approach
        Node clonedDFS = solution.cloneGraphDFS(original);
        System.out.println("Cloned (DFS):");
        printGraph(clonedDFS);
        System.out.println("Graphs identical: " + areGraphsIdentical(original, clonedDFS));
        System.out.println("Same reference: " + (original == clonedDFS));
        
        // Test BFS approach
        Node clonedBFS = solution.cloneGraphBFS(original);
        System.out.println("\nCloned (BFS):");
        printGraph(clonedBFS);
        System.out.println("Graphs identical: " + areGraphsIdentical(original, clonedBFS));
        
        // Test iterative DFS
        Node clonedIterative = solution.cloneGraphIterativeDFS(original);
        System.out.println("\nCloned (Iterative DFS):");
        printGraph(clonedIterative);
        System.out.println("Graphs identical: " + areGraphsIdentical(original, clonedIterative));
        
        // Test edge cases
        System.out.println("\nEdge Cases:");
        
        // Single node
        Node singleNode = new Node(1);
        Node clonedSingle = solution.cloneGraphDFS(singleNode);
        System.out.println("Single node cloned: " + (clonedSingle != null && clonedSingle.val == 1));
        
        // Empty graph
        Node emptyClone = solution.cloneGraphDFS(null);
        System.out.println("Empty graph cloned: " + (emptyClone == null));
        
        // Self-loop
        Node selfLoop = new Node(1);
        selfLoop.neighbors.add(selfLoop);
        Node clonedSelfLoop = solution.cloneGraphDFS(selfLoop);
        System.out.println("Self-loop handled: " + (clonedSelfLoop.neighbors.get(0) == clonedSelfLoop));
    }
} 