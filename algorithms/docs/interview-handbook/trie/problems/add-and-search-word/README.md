# Design Add and Search Words Data Structure

## 1. Problem Title
Design Add and Search Words Data Structure

## 2. Difficulty Level (Easy / Medium / Hard)
Medium

## 3. Problem Statement
Design a data structure that supports adding words and searching with '.' wildcard (matches any single character).

## 4. Input Format
Operations: addWord(word), search(pattern).

## 5. Output Format
search returns boolean.

## 6. Constraints (very important for interviews)
- 1 <= word.length <= 25
- search pattern may contain lowercase letters and '.'.
- At most 10^4 operations.

## 7. Example Inputs and Outputs
Example 1
```text
Input: addWord('bad'), addWord('dad'), addWord('mad'), search('pad'), search('.ad'), search('b..')
Output: false, true, true
Explanation: Wildcard requires branching DFS in trie.
```

## 8. Edge Cases
- Pattern is all wildcards.
- Pattern length mismatch with inserted words.
- Multiple branching wildcard positions.

## 9. Brute Force Approach Explanation
Store all words and test each against pattern char-by-char. O(N*L) per query.

## 10. Optimal Approach Explanation
Trie + DFS for wildcard positions. Branch into all children only when pattern char is '.'.

## 11. Step-by-Step Logic
1. Insert same as normal trie.
2. Search with recursive DFS(index,node).
3. If char is letter, follow one child; if '.', try all non-null children.

## 12. Time Complexity Analysis
addWord O(L), search worst-case O(26^w) where w is number of wildcards.

## 13. Space Complexity Analysis
O(total inserted characters).

## 14. Clean and Production-Quality Java Code
```java
class WordDictionary {
    private static class Node {
        Node[] child = new Node[26];
        boolean end;
    }

    private final Node root = new Node();

    public void addWord(String word) {
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
        return dfs(word, 0, root);
    }

    private boolean dfs(String word, int idx, Node node) {
        if (node == null) {
            return false;
        }
        if (idx == word.length()) {
            return node.end;
        }

        char c = word.charAt(idx);
        if (c == '.') {
            for (Node next : node.child) {
                if (next != null && dfs(word, idx + 1, next)) {
                    return true;
                }
            }
            return false;
        }

        return dfs(word, idx + 1, node.child[c - 'a']);
    }
}
```

## 15. Dry Run Example
search('.ad') branches from root on all first chars and matches bad/dad/mad.

## 16. Common Interview Follow-Up Questions
- How to optimize wildcard-heavy queries?
- How would you support '*' wildcard (multiple chars)?

## 17. Alternative Solutions if available
- Regex matching over all words (slow).

## 18. Real Interview Context (why companies ask this problem)
Tests trie search with branching recursion under wildcard constraints.
