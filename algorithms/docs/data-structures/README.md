# Data Structures & Related Algorithms

Core structures and their typical operations, complexities, and caveats.

## Arrays
- Random access `O(1)`; insert/delete at end `O(1)` amortized (dynamic arrays); middle ops `O(n)`.
- Pitfalls: resizing cost spikes; shifting elements; boundary checks.

## Linked Lists
- Singly/Doubly: insert/delete (given node) `O(1)`; search `O(n)`; no random access.
- Pitfalls: cache-unfriendly; pointer bugs; sentinel nodes simplify edge cases.

## Stacks & Queues
- Stack (LIFO): push/pop `O(1)`.
- Queue (FIFO): enqueue/dequeue `O(1)`; use circular buffer for arrays.

## Hash Tables
- Expected `O(1)` for search/insert/delete with good hash and low load factor.
- Collisions: chaining, open addressing (linear, quadratic, double hashing).
- Pitfalls: clustering, poor hash, resizing; choose load factor (≈0.7–0.8).

## Trees Overview
- BST: average `O(log n)` ops; worst `O(n)` if unbalanced.
- AVL: height-balanced; strict rotations; `O(log n)` guaranteed.
- Red-Black: looser balance; simpler; `O(log n)`.
- Tries: prefix tree for strings; `O(L)` ops; memory heavy.

AVL vs Red-Black specifics:
- AVL maintains balance factor in {-1,0,1}; shallower trees → faster lookups; more rotations on updates.
- Red-Black enforces properties (root black, no two reds adjacent, equal black-height paths) → at most 2 rotations per insert; fewer rotations overall.
- Both guarantee height O(log n); choose AVL for read-heavy, RB for mixed workloads.

## Segment Tree (Range Sum Example)
- Range query/update in `O(log n)`; build in `O(n)`; space `O(n)`.
```pseudo
build(node, l, r):
    if l == r: tree[node] = A[l]
    else:
        m = (l + r) / 2
        build(2*node, l, m)
        build(2*node+1, m+1, r)
        tree[node] = tree[2*node] + tree[2*node+1]

query(node, l, r, ql, qr):
    if qr < l or r < ql: return 0
    if ql <= l and r <= qr: return tree[node]
    m = (l + r) / 2
    return query(2*node, l, m, ql, qr) + query(2*node+1, m+1, r, ql, qr)

update(node, l, r, idx, val):
    if l == r: tree[node] = val
    else:
        m = (l + r) / 2
        if idx <= m: update(2*node, l, m, idx, val)
        else: update(2*node+1, m+1, r, idx, val)
        tree[node] = tree[2*node] + tree[2*node+1]
```

Lazy propagation (range updates):
```pseudo
update_range(node, l, r, ql, qr, delta):
    if lazy[node] != 0:  // push pending updates
        tree[node] += (r-l+1)*lazy[node]
        if l != r: lazy[2*node] += lazy[node]; lazy[2*node+1] += lazy[node]
        lazy[node] = 0
    if qr < l or r < ql: return
    if ql <= l and r <= qr:
        tree[node] += (r-l+1)*delta
        if l != r: lazy[2*node] += delta; lazy[2*node+1] += delta
        return
    m = (l+r)/2
    update_range(2*node, l, m, ql, qr, delta)
    update_range(2*node+1, m+1, r, ql, qr, delta)
    tree[node] = tree[2*node] + tree[2*node+1]
```

## Fenwick Tree (Binary Indexed Tree)
- Prefix sums with `O(log n)` update/query; space `O(n)`.
```pseudo
FenwickBuild(A):
    BIT = array(|A|+1, 0)
    for i in 1..|A|:
        add(i, A[i])
    return BIT

add(i, delta):
    while i <= n:
        BIT[i] += delta
        i += i & -i

prefix_sum(i):
    s = 0
    while i > 0:
        s += BIT[i]
        i -= i & -i
    return s

range_sum(l, r):
    return prefix_sum(r) - prefix_sum(l-1)
```

Notes:
- 2D Fenwick supports rectangle sums with O(log^2 n) operations.
- Use 1-based indexing; i & -i extracts lowest set bit for parent/next indices.

## Disjoint Set Union (Union-Find)
- Maintain components with near-constant operations using path compression + union by rank.
- Complexity: `α(n)` inverse Ackermann per op (amortized).
```pseudo
make_set(v): parent[v]=v; rank[v]=0

find(v):
    if parent[v] != v: parent[v] = find(parent[v])
    return parent[v]

union(a, b):
    a = find(a); b = find(b)
    if a == b: return
    if rank[a] < rank[b]: swap(a, b)
    parent[b] = a
    if rank[a] == rank[b]: rank[a]++
```

Why near-constant time: path compression flattens trees on find, and union-by-rank prevents tall trees. Amortized time is inverse Ackermann alpha(n), < 5 for any practical n.

## Graph Representations
- Adjacency List: `O(V+E)` space; iterate neighbors efficiently; best for sparse graphs.
- Adjacency Matrix: `O(V^2)` space; `O(1)` edge checks; good for dense graphs.

Queues via circular buffers:
- Keep `head`, `tail`, and `size`; increment indices modulo capacity.
- Distinguish full vs empty via size or by leaving one empty slot.

Tries at scale:
- Compress single-child paths (radix tree) to reduce memory.
- Use bitsets/packed structures; DAWG for large dictionaries to share suffixes.

## Best Practices
- Prefer arrays/vectors for tight loops; reduce pointer chasing.
- Use sentinels to simplify list/queue boundary logic.
- Tune hash functions and load factor; monitor rehash costs.
- Choose segment tree vs. Fenwick based on operation needs (range updates/queries).

Common pitfalls:
- For hash tables with open addressing, ensure a proper probe sequence and resize when load factor exceeds threshold.
- For trees, remember to update heights/colors on rotations consistently.
- For segment trees with lazy tags, always push pending updates before descending.
