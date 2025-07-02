package leetcode.hashtable;

import java.util.*;

/**
 * LeetCode 146: LRU Cache
 * 
 * Design a data structure that follows the constraints of a Least Recently Used (LRU) cache.
 * 
 * Implement the LRUCache class:
 * - LRUCache(int capacity) Initialize the LRU cache with positive size capacity.
 * - int get(int key) Return the value of the key if the key exists, otherwise return -1.
 * - void put(int key, int value) Update the value of the key if the key exists. 
 *   Otherwise, add the key-value pair to the cache. If the number of keys exceeds 
 *   the capacity from this operation, evict the least recently used key.
 */
public class LRUCache {
    
    // Doubly linked list node
    class DListNode {
        int key;
        int value;
        DListNode prev;
        DListNode next;
        
        DListNode() {}
        
        DListNode(int key, int value) {
            this.key = key;
            this.value = value;
        }
    }
    
    private Map<Integer, DListNode> cache;
    private int capacity;
    private DListNode head;  // Dummy head
    private DListNode tail;  // Dummy tail
    
    /**
     * Initialize LRU Cache
     * Time Complexity: O(1)
     * Space Complexity: O(capacity)
     */
    public LRUCache(int capacity) {
        this.capacity = capacity;
        this.cache = new HashMap<>();
        
        // Create dummy head and tail
        this.head = new DListNode();
        this.tail = new DListNode();
        head.next = tail;
        tail.prev = head;
    }
    
    /**
     * Get value by key
     * Time Complexity: O(1)
     */
    public int get(int key) {
        DListNode node = cache.get(key);
        
        if (node == null) {
            return -1;
        }
        
        // Move to head (mark as recently used)
        moveToHead(node);
        return node.value;
    }
    
    /**
     * Put key-value pair
     * Time Complexity: O(1)
     */
    public void put(int key, int value) {
        DListNode node = cache.get(key);
        
        if (node == null) {
            DListNode newNode = new DListNode(key, value);
            
            // Add to cache and move to head
            cache.put(key, newNode);
            addToHead(newNode);
            
            // Check capacity
            if (cache.size() > capacity) {
                // Remove least recently used (tail)
                DListNode tail = removeTail();
                cache.remove(tail.key);
            }
        } else {
            // Update existing node
            node.value = value;
            moveToHead(node);
        }
    }
    
    /**
     * Add node right after head
     */
    private void addToHead(DListNode node) {
        node.prev = head;
        node.next = head.next;
        
        head.next.prev = node;
        head.next = node;
    }
    
    /**
     * Remove a node from the list
     */
    private void removeNode(DListNode node) {
        node.prev.next = node.next;
        node.next.prev = node.prev;
    }
    
    /**
     * Move node to head (mark as recently used)
     */
    private void moveToHead(DListNode node) {
        removeNode(node);
        addToHead(node);
    }
    
    /**
     * Remove and return tail node (least recently used)
     */
    private DListNode removeTail() {
        DListNode lastNode = tail.prev;
        removeNode(lastNode);
        return lastNode;
    }
    
    /**
     * Get current cache state for debugging
     */
    public void printCache() {
        System.out.print("Cache state (MRU -> LRU): ");
        DListNode current = head.next;
        while (current != tail) {
            System.out.print("[" + current.key + ":" + current.value + "] ");
            current = current.next;
        }
        System.out.println();
    }
    
    // Test the LRU Cache
    public static void main(String[] args) {
        System.out.println("Testing LRU Cache:");
        
        LRUCache cache = new LRUCache(2);
        
        cache.put(1, 1);
        cache.printCache();
        
        cache.put(2, 2);
        cache.printCache();
        
        System.out.println("get(1): " + cache.get(1)); // returns 1
        cache.printCache();
        
        cache.put(3, 3); // evicts key 2
        cache.printCache();
        
        System.out.println("get(2): " + cache.get(2)); // returns -1 (not found)
        
        cache.put(4, 4); // evicts key 1
        cache.printCache();
        
        System.out.println("get(1): " + cache.get(1)); // returns -1 (not found)
        System.out.println("get(3): " + cache.get(3)); // returns 3
        System.out.println("get(4): " + cache.get(4)); // returns 4
        cache.printCache();
    }
}

/**
 * Alternative Implementation using LinkedHashMap
 */
class LRUCacheLinkedHashMap {
    private LinkedHashMap<Integer, Integer> cache;
    private int capacity;
    
    public LRUCacheLinkedHashMap(int capacity) {
        this.capacity = capacity;
        this.cache = new LinkedHashMap<Integer, Integer>(capacity, 0.75f, true) {
            @Override
            protected boolean removeEldestEntry(Map.Entry<Integer, Integer> eldest) {
                return size() > capacity;
            }
        };
    }
    
    public int get(int key) {
        return cache.getOrDefault(key, -1);
    }
    
    public void put(int key, int value) {
        cache.put(key, value);
    }
} 