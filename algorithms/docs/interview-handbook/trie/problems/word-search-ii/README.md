# Word Search II

## 1. Problem Title
Word Search II

## 2. Difficulty Level (Easy / Medium / Hard)
Hard

## 3. Problem Statement
Given an m x n board of letters and a list of words, return all words from the list that can be formed by sequential adjacent cells.

## 4. Input Format
board: 2D char matrix, words: string array.

## 5. Output Format
Return list of found words (unique).

## 6. Constraints (very important for interviews)
- 1 <= m, n <= 12
- 1 <= words.length <= 3 * 10^4
- 1 <= words[i].length <= 10

## 7. Example Inputs and Outputs
Example 1
```text
Input: board=[[o,a,a,n],[e,t,a,e],[i,h,k,r],[i,f,l,v]], words=['oath','pea','eat','rain']
Output: ['eat','oath']
Explanation: Trie prunes invalid DFS paths early.
```

## 8. Edge Cases
- No words found.
- Duplicate words in dictionary.
- Board with repeated characters.

## 9. Brute Force Approach Explanation
For each word run board DFS search independently. Too expensive for large word lists.

## 10. Optimal Approach Explanation
Build trie for all words and perform DFS from each board cell, pruning when prefix not in trie.

## 11. Step-by-Step Logic
1. Insert all words into trie storing complete word at end nodes.
2. Run DFS from every cell with trie node pointer.
3. Mark visited cell temporarily and explore neighbors.
4. When trie node has complete word, add to answer and clear to avoid duplicates.

## 12. Time Complexity Analysis
Roughly O(m*n*4^L) worst-case, but trie pruning drastically reduces search in practice.

## 13. Space Complexity Analysis
O(total dictionary chars + recursion depth).

## 14. Clean and Production-Quality Java Code
```java
import java.util.*;

class Solution {
    private static class Node {
        Node[] child = new Node[26];
        String word;
    }

    public List<String> findWords(char[][] board, String[] words) {
        Node root = buildTrie(words);
        List<String> result = new ArrayList<>();

        for (int r = 0; r < board.length; r++) {
            for (int c = 0; c < board[0].length; c++) {
                dfs(board, r, c, root, result);
            }
        }

        return result;
    }

    private Node buildTrie(String[] words) {
        Node root = new Node();
        for (String word : words) {
            Node curr = root;
            for (char ch : word.toCharArray()) {
                int i = ch - 'a';
                if (curr.child[i] == null) {
                    curr.child[i] = new Node();
                }
                curr = curr.child[i];
            }
            curr.word = word;
        }
        return root;
    }

    private void dfs(char[][] board, int r, int c, Node node, List<String> result) {
        if (r < 0 || c < 0 || r >= board.length || c >= board[0].length) {
            return;
        }

        char ch = board[r][c];
        if (ch == '#') {
            return;
        }

        Node next = node.child[ch - 'a'];
        if (next == null) {
            return;
        }

        if (next.word != null) {
            result.add(next.word);
            next.word = null;
        }

        board[r][c] = '#';
        dfs(board, r + 1, c, next, result);
        dfs(board, r - 1, c, next, result);
        dfs(board, r, c + 1, next, result);
        dfs(board, r, c - 1, next, result);
        board[r][c] = ch;
    }
}
```

## 15. Dry Run Example
Start at 'o' cell, follow trie path o->a->t->h to record 'oath'.

## 16. Common Interview Follow-Up Questions
- How to optimize for very large boards?
- Can you avoid recursion stack overflow?

## 17. Alternative Solutions if available
- Per-word DFS search (baseline, slower).

## 18. Real Interview Context (why companies ask this problem)
Hard hybrid of trie + backtracking often used in onsite rounds.
