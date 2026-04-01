# Range Sum Query - Mutable (Segment Tree)

## 1. Problem Title
Range Sum Query - Mutable (Segment Tree)

## 2. Difficulty Level (Easy / Medium / Hard)
Medium

## 3. Problem Statement
Design a data structure supporting update(index, val) and sumRange(left, right) on an integer array.

## 4. Input Format
Constructor input nums array. Query/update operations follow.

## 5. Output Format
sumRange returns sum of nums[left..right] after updates.

## 6. Constraints (very important for interviews)
- 1 <= nums.length <= 3 * 10^4
- -100 <= nums[i], val <= 100
- 0 <= left <= right < nums.length
- At most 3 * 10^4 operations

## 7. Example Inputs and Outputs
Example 1
```text
Input: nums=[1,3,5], sumRange(0,2), update(1,2), sumRange(0,2)
Output: 9, 8
Explanation: Segment tree updates one point and answers range sum quickly.
```

## 8. Edge Cases
- Single element array.
- Repeated updates to same index.
- Negative values.

## 9. Brute Force Approach Explanation
Update is O(1) but each range query scans O(n).

## 10. Optimal Approach Explanation
Segment tree stores range sums; both point update and range query are O(log n).

## 11. Step-by-Step Logic
1. Build segment tree over array.
2. On update, recurse to leaf and recompute sums on path.
3. On query, return node sum for full overlap and split on partial overlap.

## 12. Time Complexity Analysis
Build O(n), update O(log n), query O(log n).

## 13. Space Complexity Analysis
O(4n).

## 14. Clean and Production-Quality Java Code
```java
class NumArray {
    private final int n;
    private final int[] tree;

    public NumArray(int[] nums) {
        n = nums.length;
        tree = new int[4 * n];
        build(1, 0, n - 1, nums);
    }

    public void update(int index, int val) {
        update(1, 0, n - 1, index, val);
    }

    public int sumRange(int left, int right) {
        return query(1, 0, n - 1, left, right);
    }

    private void build(int node, int l, int r, int[] nums) {
        if (l == r) {
            tree[node] = nums[l];
            return;
        }
        int mid = l + (r - l) / 2;
        build(node * 2, l, mid, nums);
        build(node * 2 + 1, mid + 1, r, nums);
        tree[node] = tree[node * 2] + tree[node * 2 + 1];
    }

    private void update(int node, int l, int r, int idx, int val) {
        if (l == r) {
            tree[node] = val;
            return;
        }
        int mid = l + (r - l) / 2;
        if (idx <= mid) {
            update(node * 2, l, mid, idx, val);
        } else {
            update(node * 2 + 1, mid + 1, r, idx, val);
        }
        tree[node] = tree[node * 2] + tree[node * 2 + 1];
    }

    private int query(int node, int l, int r, int ql, int qr) {
        if (ql <= l && r <= qr) {
            return tree[node];
        }
        if (r < ql || l > qr) {
            return 0;
        }
        int mid = l + (r - l) / 2;
        return query(node * 2, l, mid, ql, qr)
            + query(node * 2 + 1, mid + 1, r, ql, qr);
    }
}
```

## 15. Dry Run Example
sum(0,2)=9; update index1->2 modifies leaf and ancestors; sum(0,2)=8.

## 16. Common Interview Follow-Up Questions
- How to support range add updates?
- When is Fenwick Tree preferable?

## 17. Alternative Solutions if available
- Fenwick Tree for point update + prefix sums.
- Sqrt decomposition.

## 18. Real Interview Context (why companies ask this problem)
Advanced DS design balancing update/query efficiency.
