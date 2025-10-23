# Searching Algorithms

Covers binary, ternary (unimodal), and interpolation search. Includes intuition, pseudocode, complexity, and pitfalls.

## Binary Search
- Use on sorted arrays or monotonic predicates.
- Complexity: `O(log n)` time, `O(1)` space.
- Pitfalls: Off-by-one; overflow in `mid`; unstable on duplicates if lower/upper bound not handled.
```pseudo
BinarySearch(A, x):
    lo = 0; hi = |A|-1
    while lo <= hi:
        mid = lo + (hi - lo)/2
        if A[mid] == x: return mid
        if A[mid] < x: lo = mid + 1
        else: hi = mid - 1
    return not_found
```

Binary search invariants and variants:
- Invariant: if target exists, it lies in A[lo..hi]. Maintain this by moving lo/hi only when mid comparison eliminates half.
- LowerBound/UpperBound: use half-open range [lo, hi) with hi initialized to n for simpler proofs.
- Monotonic predicate search: find min x such that P(x) is true over domain where P is false...false,true...true.

Worked example (lower bound):
- A = [1,2,4,4,5], x = 4 → returns index 2 (first 4).
- Trace mid updates maintaining [lo, hi) until convergence.

Lower bound (first >= x):
```pseudo
LowerBound(A, x):
    lo=0; hi=|A|
    while lo < hi:
        mid = lo + (hi-lo)/2
        if A[mid] < x: lo = mid + 1
        else: hi = mid
    return lo
```

## Ternary Search (Unimodal)
- For unimodal functions `f` on intervals or discrete arrays.
- Complexity: `O(log(1/eps))` iterations (continuous), `O(log n)` (discrete).
- Pitfalls: Only valid for unimodal functions.
```pseudo
TernarySearch(f, lo, hi, eps):
    while hi - lo > eps:
        m1 = lo + (hi - lo)/3
        m2 = hi - (hi - lo)/3
        if f(m1) < f(m2): lo = m1
        else: hi = m2
    return (lo + hi)/2
```

Notes:
- For discrete unimodal arrays, stop when hi - lo <= 3 and scan remaining region.
- For convex/concave real functions, prefer gradient-based methods when derivatives are accessible.

## Interpolation Search
- For uniformly distributed sorted numeric arrays; estimates position by interpolation.
- Complexity: average `O(log log n)`, worst `O(n)`.
- Pitfalls: Division by zero if all keys equal; sensitive to non-uniform distributions.
```pseudo
InterpolationSearch(A, x):
    lo=0; hi=|A|-1
    while lo <= hi and A[lo] <= x and x <= A[hi]:
        if A[hi] == A[lo]:
            if A[lo] == x: return lo
            else: break
        pos = lo + (x - A[lo]) * (hi - lo) / (A[hi] - A[lo])
        if A[pos] == x: return pos
        if A[pos] < x: lo = pos + 1
        else: hi = pos - 1
    return not_found
```

When to use:
- Large sorted numeric arrays with approximately uniform distribution (e.g., hashed buckets with post-sorts).
- Otherwise, stick to binary search for worst-case guarantees.

Common pitfalls:
- Integer overflow in (x - A[lo]) * (hi - lo); consider using 64-bit or floating estimation.
- Guard against A[hi] == A[lo] to avoid division by zero.

## Extended Compendium: Best-Practice Searching

This section augments core methods with a complete toolkit: robust templates, specialized searches, data-structure-based search, and problem patterns. Use it as a playbook to pick and implement the right approach fast and correctly.

### 1) Data Model & Assumptions
- Sorted vs. unsorted: Binary/Interpolation/Jumps require sorted; hashing/linear search work on unsorted.
- Static vs. dynamic: If frequent inserts/deletes, prefer balanced trees, skip lists, hash tables; for static arrays, prefer binary or interpolation search.
- Discrete vs. continuous: For continuous domains with monotonic predicates, use real-valued binary search with epsilon.
- Access cost: In RAM, random access is cheap; on disk/SSD, prefer B-trees and minimize random seeks.

### 2) Linear Search (Baseline)
- Use when n is tiny, data unsorted, or stopping early is likely.
- Complexity: O(n) time; O(1) space; best case O(1) if target near front.
```pseudo
LinearSearch(A, x):
    for i in 0..|A|-1:
        if A[i] == x: return i
    return not_found
```

### 3) Binary Search Templates (Robust)
- Prefer half-open ranges [lo, hi) to eliminate mid +/- 1 juggling.
```pseudo
// exact match in [0, n)
BinarySearchExact(A, x):
    lo = 0; hi = |A|
    while lo < hi:
        mid = lo + (hi - lo) / 2
        if A[mid] < x: lo = mid + 1
        else: hi = mid
    // lo == first position >= x
    if lo < |A| and A[lo] == x: return lo
    return not_found

// lower_bound: first index i with A[i] >= x
LowerBound(A, x):
    lo = 0; hi = |A|
    while lo < hi:
        mid = lo + (hi - lo) / 2
        if A[mid] < x: lo = mid + 1
        else: hi = mid
    return lo

// upper_bound: first index i with A[i] > x
UpperBound(A, x):
    lo = 0; hi = |A|
    while lo < hi:
        mid = lo + (hi - lo) / 2
        if A[mid] <= x: lo = mid + 1
        else: hi = mid
    return lo

// nearest: index of value closest to x
Nearest(A, x):
    i = LowerBound(A, x)
    if i == 0: return 0
    if i == |A|: return |A|-1
    return argmin_index({i-1, i}, by |A[idx] - x|)
```

Binary search for monotonic predicates:
```pseudo
// P is monotone false...false,true...true
FindFirstTrue(P, L, R): // domain [L, R)
    lo = L; hi = R
    while lo < hi:
        mid = lo + (hi - lo) / 2
        if P(mid): hi = mid
        else: lo = mid + 1
    return lo // equals R if none true
```

Real-valued binary search (epsilon precision):
```pseudo
BinarySearchReal(f, lo, hi, eps):
    while hi - lo > eps:
        mid = (lo + hi) / 2
        if f(mid) >= 0: hi = mid  // or direction per monotonicity
        else: lo = mid
    return (lo + hi) / 2
```

### 4) Exponential/Galloping Search (Unknown Boundaries)
- Use when array length is unknown or we want fast bound discovery.
- Steps: increase hi exponentially until A[hi] >= x or end; then binary search in (lo, hi].
```pseudo
ExponentialSearch(A, x):
    if |A| == 0: return not_found
    if A[0] == x: return 0
    i = 1
    while i < |A| and A[i] < x:
        i *= 2
    return BinarySearchExactInRange(A, x, i/2, min(i, |A|)-1)

BinarySearchExactInRange(A, x, lo, hi): // inclusive hi
    hi = hi + 1 // convert to half-open
    while lo < hi:
        mid = lo + (hi - lo)/2
        if A[mid] < x: lo = mid + 1
        else: hi = mid
    if lo < |A| and A[lo] == x: return lo
    return not_found
```
- Galloping search in merges/timsort: same doubling idea to find insertion bound before binary searching; excellent on partially ordered data with runs.

### 5) Jump Search (Educational)
- Jump √n steps to find block, then linear scan within block. O(√n) comparisons.
```pseudo
JumpSearch(A, x):
    n = |A|; step = floor(sqrt(n)); prev = 0
    while prev < n and A[min(step, n)-1] < x:
        prev = step; step += floor(sqrt(n))
    for i in prev .. min(step, n)-1:
        if A[i] == x: return i
    return not_found
```
- Rarely preferable in RAM; useful as a teaching tool and in constrained environments.

### 6) Fibonacci Search
- Uses Fibonacci numbers to choose probe positions; arithmetic is addition-only.
- Complexity: O(log n) comparisons; practical only in niche settings.
```pseudo
FibonacciSearch(A, x):
    // setup fibM = smallest Fibonacci >= n
    fibMMm2 = 0; fibMMm1 = 1; fibM = fibMMm1 + fibMMm2
    while fibM < |A|: fibMMm2=fibMMm1; fibMMm1=fibM; fibM=fibMMm1+fibMMm2
    offset = -1
    while fibM > 1:
        i = min(offset + fibMMm2, |A|-1)
        if A[i] < x: fibM=fibMMm1; fibMMm1=fibMMm2; fibMMm2=fibM-fibMMm1; offset=i
        else if A[i] > x: fibM=fibMMm2; fibMMm1=fibMMm1-fibMMm2; fibMMm2=fibM-fibMMm1
        else: return i
    if fibMMm1 == 1 and offset+1 < |A| and A[offset+1] == x: return offset+1
    return not_found
```

### 7) Rotated Sorted Array Search
- Array is sorted then rotated by unknown k; search in O(log n) without finding pivot explicitly.
```pseudo
SearchRotated(A, x):
    lo=0; hi=|A|-1
    while lo <= hi:
        mid = lo + (hi - lo)/2
        if A[mid] == x: return mid
        if A[lo] <= A[mid]: // left is sorted
            if A[lo] <= x and x < A[mid]: hi = mid - 1
            else: lo = mid + 1
        else: // right is sorted
            if A[mid] < x and x <= A[hi]: lo = mid + 1
            else: hi = mid - 1
    return not_found
```

### 8) Bitonic Array Search
- A bitonic array increases then decreases. Find peak (unimodal) via binary search, then binary-search both sides.
```pseudo
BitonicSearch(A, x):
    p = PeakIndex(A)
    i = BinarySearchIncreasing(A[0..p], x)
    if i != not_found: return i
    j = BinarySearchDecreasing(A[p..end], x)
    if j != not_found: return j
    return not_found

PeakIndex(A):
    lo=0; hi=|A|-1
    while lo < hi:
        mid = lo + (hi - lo)/2
        if A[mid] < A[mid+1]: lo = mid + 1
        else: hi = mid
    return lo
```

### 9) 2D Matrix Searching
- Case A (matrix sorted by rows and columns ascending): staircase search from top-right in O(n+m).
```pseudo
StaircaseSearch(M, x): // M[r][c] increasing right and down
    r = 0; c = cols(M)-1
    while r < rows(M) and c >= 0:
        if M[r][c] == x: return (r, c)
        if M[r][c] > x: c--
        else: r++
    return not_found
```
- Case B (row-major sorted and row[i][0] > row[i-1][last]): binary search over [0, R*C) with mapping i = mid / C, j = mid % C.

### 10) Data-Structure-Based Search (Dynamic Sets)
- Hash Table: expected O(1) search/insert/delete with good hashing and load factor; worst O(n).
- Balanced BST (AVL/RB): O(log n) search and ordered operations (predecessor/successor, range queries).
- Skip List: randomized linked levels, expected O(log n) search; simple to implement.
- B-/B+ Tree: O(log_B n) I/Os; standard for disks/SSDs and databases.
- Trie: prefix search in O(L) for string length L; supports autocomplete, dictionary lookups.
- van Emde Boas tree: O(log log U) search for integer universe size U; high memory overhead.

### 11) Correctness Patterns
- Invariant method: define and maintain [lo, hi) containing the answer; terminate when lo == hi.
- Monotonic predicate: prove P is monotone; then boundary returned by binary search is correct.
- Reduction: rotated/bitonic cases reduce to standard binary search on subranges.

### 12) Performance Notes
- For tiny arrays, linear search can beat binary search due to branch mispredictions and cache effects.
- Use bounds-checked functions and sentinel values to avoid exceptions in unknown-size searches.
- Prefer integer mid calculation `mid = lo + (hi - lo)/2` to avoid overflow.

### 13) Common Pitfalls
- Off-by-one: mixing inclusive/exclusive ranges; pick [lo, hi) or [lo, hi] and stick to it.
- Infinite loops: ensure lo or hi moves every iteration; beware mid rounding toward lo in some languages.
- Duplicates: exact match may return any occurrence; use lower_bound/upper_bound for first/last.
- Non-uniform distributions: interpolation search can degrade to O(n).

### 14) When To Use What
- Exact lookup in sorted static array: Binary Search (use LowerBound/UpperBound templates).
- Unknown bounds or merging runs: Exponential/Galloping + Binary.
- Numeric root/threshold finding: Real-valued Binary with epsilon; Ternary for unimodal optima.
- Nearly uniform numeric keys: Interpolation Search.
- Disk-based or very large datasets: B-/B+ Trees; avoid random access.
- Dynamic sets with order queries: Balanced BST/Skip List; without order: Hash Table.

### 15) Quick Reference (Complexities)
- Linear: O(n)
- Binary: O(log n)
- Exponential (bound finding) + Binary: O(log p) where p is target position
- Jump: O(√n)
- Fibonacci: O(log n)
- Interpolation: avg O(log log n), worst O(n)
- Ternary (unimodal optimize): O(log n) discrete, O(log(1/eps)) real
- Rotated, Bitonic, 2D sorted: O(log n), O(log n), O(n+m) respectively

### 16) Testing Checklist
- Validate on edge cases: empty array, 1 element, all equal, duplicates, first/last position, not-found.
- For rotated/bitonic: test with k=0 and k=n-1 rotations; degenerate monotone halves.
- Property-based tests: compare results to a linear scan oracle for small n.
