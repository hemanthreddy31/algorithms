# Implement Trie (Prefix Tree)

## 1. Problem Title
Implement Trie (Prefix Tree)

## 2. Difficulty Level (Easy / Medium / Hard)
Medium

## 3. Problem Statement
Implement a trie with insert(word), search(word), and startsWith(prefix).

## 4. Input Format
Operations with lowercase word/prefix strings.

## 5. Output Format
search and startsWith return boolean.

## 6. Constraints (very important for interviews)
- 1 <= word.length, prefix.length <= 2000
- Words contain lowercase English letters.
- At most 3 * 10^4 operations.

## 7. Example Inputs and Outputs
Example 1
```text
Input: insert('apple'), search('apple'), search('app'), startsWith('app'), insert('app'), search('app')
Output: true, false, true, true
Explanation: Prefix and full-word checks differ until 'app' is inserted.
```

## 8. Edge Cases
- Insert same word multiple times.
- Prefix equals full word.
- Single-character words.

## 9. Brute Force Approach Explanation
Store all strings in list and linearly scan for search/prefix checks.

## 10. Optimal Approach Explanation
Trie stores characters along edges; each node marks end-of-word for O(L) operations.

## 11. Step-by-Step Logic
1. Traverse/create nodes for each char in insert.
2. For search, traverse all chars and check end flag.
3. For startsWith, traverse prefix only.

## 12. Time Complexity Analysis
O(L) per operation where L is word length.

## 13. Space Complexity Analysis
O(total characters inserted).

## 14. Clean and Production-Quality Java Code
```java
class Trie {
    private static class Node {
        Node[] child = new Node[26];
        boolean end;
    }

    private final Node root = new Node();

    public void insert(String word) {
        Node curr = root;
        for (char c : word.toCharArray()) {
            int i = c - 'a';
            if (curr.child[i] == null) {
                curr.child[i] = new Node();
            }
            curr = curr.child[i];
        }
        curr.end = true;
    }

    public boolean search(String word) {
        Node node = walk(word);
        return node != null && node.end;
    }

    public boolean startsWith(String prefix) {
        return walk(prefix) != null;
    }

    private Node walk(String s) {
        Node curr = root;
        for (char c : s.toCharArray()) {
            int i = c - 'a';
            if (curr.child[i] == null) {
                return null;
            }
            curr = curr.child[i];
        }
        return curr;
    }
}
```

## 15. Dry Run Example
Insert 'cat' creates c->a->t and marks end at t node. startsWith('ca') walks and returns true.

## 16. Common Interview Follow-Up Questions
- How to reduce memory for sparse tries?
- How to support deletion of words?

## 17. Alternative Solutions if available
- HashSet for exact search only (no fast prefix traversal).

## 18. Real Interview Context (why companies ask this problem)
Core prefix indexing structure in autocomplete and dictionary services.
