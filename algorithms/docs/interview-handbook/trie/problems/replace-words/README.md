# Replace Words

## 1. Problem Title
Replace Words

## 2. Difficulty Level (Easy / Medium / Hard)
Medium

## 3. Problem Statement
Given dictionary of root words and a sentence, replace successors with shortest root that is a prefix.

## 4. Input Format
dictionary: list of root strings, sentence: space-separated words.

## 5. Output Format
Return transformed sentence string.

## 6. Constraints (very important for interviews)
- 1 <= dictionary.length <= 1000
- 1 <= dictionary[i].length <= 100
- 1 <= sentence.length <= 10^6

## 7. Example Inputs and Outputs
Example 1
```text
Input: dictionary=['cat','bat','rat'], sentence='the cattle was rattled by the battery'
Output: the cat was rat by the bat
Explanation: Use shortest prefix root from trie.
```

## 8. Edge Cases
- Word has no matching root.
- Multiple roots match; choose shortest.
- Large sentence.

## 9. Brute Force Approach Explanation
For each word, scan all dictionary roots and choose shortest prefix match.

## 10. Optimal Approach Explanation
Build trie from roots and walk each sentence word until first end-of-root marker.

## 11. Step-by-Step Logic
1. Insert all root words into trie with end markers.
2. Split sentence by spaces.
3. For each word, traverse trie and stop at first end marker if found.

## 12. Time Complexity Analysis
O(total root chars + total sentence chars).

## 13. Space Complexity Analysis
O(total root chars).

## 14. Clean and Production-Quality Java Code
```java
import java.util.*;

class Solution {
    private static class Node {
        Node[] child = new Node[26];
        boolean end;
    }

    public String replaceWords(List<String> dictionary, String sentence) {
        Node root = new Node();
        for (String word : dictionary) {
            insert(root, word);
        }

        String[] words = sentence.split(" ");
        for (int i = 0; i < words.length; i++) {
            words[i] = shortestRoot(root, words[i]);
        }

        return String.join(" ", words);
    }

    private void insert(Node root, String word) {
        Node curr = root;
        for (char c : word.toCharArray()) {
            int idx = c - 'a';
            if (curr.child[idx] == null) {
                curr.child[idx] = new Node();
            }
            curr = curr.child[idx];
        }
        curr.end = true;
    }

    private String shortestRoot(Node root, String word) {
        Node curr = root;
        for (int i = 0; i < word.length(); i++) {
            int idx = word.charAt(i) - 'a';
            if (curr.child[idx] == null) {
                return word;
            }
            curr = curr.child[idx];
            if (curr.end) {
                return word.substring(0, i + 1);
            }
        }
        return word;
    }
}
```

## 15. Dry Run Example
'cattle' traverses c-a-t where trie node has end => replace with 'cat'.

## 16. Common Interview Follow-Up Questions
- How to support runtime dictionary updates?
- How would you handle Unicode dictionaries?

## 17. Alternative Solutions if available
- Sort roots by length and check startsWith (slower at scale).

## 18. Real Interview Context (why companies ask this problem)
Shows practical trie use in text normalization pipelines.
