# Group Anagrams

## 1. Problem Title
Group Anagrams

## 2. Difficulty Level (Easy / Medium / Hard)
Medium

## 3. Problem Statement
Given an array of strings strs, group the anagrams together and return grouped lists in any order.

## 4. Input Format
strs: array of lowercase strings.

## 5. Output Format
Return list of groups, where each group contains anagrams.

## 6. Constraints (very important for interviews)
- 1 <= strs.length <= 10^4
- 0 <= strs[i].length <= 100
- strs[i] consists of lowercase English letters.

## 7. Example Inputs and Outputs
Example 1
```text
Input: strs=['eat','tea','tan','ate','nat','bat']
Output: [['eat','tea','ate'],['tan','nat'],['bat']]
Explanation: Words with same sorted signature share a group.
```

## 8. Edge Cases
- Empty string entries.
- Single string.
- All strings unique.

## 9. Brute Force Approach Explanation
Compare each pair by sorted characters and merge groups manually (slow).

## 10. Optimal Approach Explanation
Use HashMap from canonical key to list. A canonical key can be sorted characters or frequency signature.

## 11. Step-by-Step Logic
1. For each word, build key (sorted string).
2. Append word into map[key].
3. Return all map values.

## 12. Time Complexity Analysis
O(n * k log k) with sorted-key approach where k is average word length.

## 13. Space Complexity Analysis
O(n * k) for map and groups.

## 14. Clean and Production-Quality Java Code
```java
import java.util.*;

class Solution {
    public List<List<String>> groupAnagrams(String[] strs) {
        Map<String, List<String>> groups = new HashMap<>();

        for (String word : strs) {
            char[] chars = word.toCharArray();
            Arrays.sort(chars);
            String key = new String(chars);
            groups.computeIfAbsent(key, k -> new ArrayList<>()).add(word);
        }

        return new ArrayList<>(groups.values());
    }
}
```

## 15. Dry Run Example
'eat','tea','ate' -> sorted key 'aet' -> same bucket.

## 16. Common Interview Follow-Up Questions
- How can you avoid O(k log k) per word?
- How to keep insertion order stable per group?

## 17. Alternative Solutions if available
- 26-count signature key O(n*k).

## 18. Real Interview Context (why companies ask this problem)
Checks ability to design canonical hash keys for grouping.
