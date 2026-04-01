# LRU Cache

## 1. Problem Title
LRU Cache

## 2. Difficulty Level (Easy / Medium / Hard)
Medium

## 3. Problem Statement
Design a data structure that follows Least Recently Used cache policy with get and put operations in O(1) average time.

## 4. Input Format
Initialize with capacity. Operations: get(key), put(key, value).

## 5. Output Format
get returns value if key exists else -1; put returns void.

## 6. Constraints (very important for interviews)
- 1 <= capacity <= 3000
- 0 <= key <= 10^4
- 0 <= value <= 10^5
- At most 2 * 10^5 operations

## 7. Example Inputs and Outputs
Example 1
```text
Input: capacity=2; put(1,1); put(2,2); get(1); put(3,3); get(2)
Output: 1, -1
Explanation: Key 2 is least recently used and evicted.
```

## 8. Edge Cases
- Updating existing key should move it to most recent.
- Capacity = 1.
- Frequent get operations change recency order.

## 9. Brute Force Approach Explanation
Maintain list order and scan on each access/update. This becomes O(n) per operation.

## 10. Optimal Approach Explanation
Use HashMap for O(1) key lookup and doubly linked list for O(1) remove/add to track recency order.

## 11. Step-by-Step Logic
1. Map key -> node in doubly linked list.
2. On get, move node to front and return value.
3. On put, update existing or insert new at front.
4. If capacity exceeded, remove node at tail and delete from map.

## 12. Time Complexity Analysis
O(1) average for get/put.

## 13. Space Complexity Analysis
O(capacity).

## 14. Clean and Production-Quality Java Code
```java
import java.util.*;

class LRUCache {
    private static class Node {
        int key;
        int value;
        Node prev;
        Node next;

        Node(int key, int value) {
            this.key = key;
            this.value = value;
        }
    }

    private final int capacity;
    private final Map<Integer, Node> map;
    private final Node head;
    private final Node tail;

    public LRUCache(int capacity) {
        this.capacity = capacity;
        this.map = new HashMap<>();
        this.head = new Node(0, 0);
        this.tail = new Node(0, 0);
        head.next = tail;
        tail.prev = head;
    }

    public int get(int key) {
        Node node = map.get(key);
        if (node == null) {
            return -1;
        }
        moveToFront(node);
        return node.value;
    }

    public void put(int key, int value) {
        Node node = map.get(key);
        if (node != null) {
            node.value = value;
            moveToFront(node);
            return;
        }

        Node fresh = new Node(key, value);
        map.put(key, fresh);
        addAfterHead(fresh);

        if (map.size() > capacity) {
            Node lru = tail.prev;
            removeNode(lru);
            map.remove(lru.key);
        }
    }

    private void moveToFront(Node node) {
        removeNode(node);
        addAfterHead(node);
    }

    private void addAfterHead(Node node) {
        node.next = head.next;
        node.prev = head;
        head.next.prev = node;
        head.next = node;
    }

    private void removeNode(Node node) {
        node.prev.next = node.next;
        node.next.prev = node.prev;
    }
}
```

## 15. Dry Run Example
capacity=2: [1] -> [2,1], get(1) => [1,2], put(3) => evict 2, list [3,1].

## 16. Common Interview Follow-Up Questions
- How to make it thread-safe?
- What is difference between LRU and LFU cache?

## 17. Alternative Solutions if available
- Java LinkedHashMap in access-order mode.

## 18. Real Interview Context (why companies ask this problem)
Common design-interview problem combining hashmap + linked list for strict O(1) operations.
