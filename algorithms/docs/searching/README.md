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
