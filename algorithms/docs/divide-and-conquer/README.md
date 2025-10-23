# Divide & Conquer

Split problems into subproblems, solve recursively, and combine results. Analyze with recurrences and the Master Theorem.

## Patterns
- Divide: partition data (by halves, pivots, sampling).
- Conquer: solve subproblems (often identical form, smaller size).
- Combine: merge results (e.g., merging, cross-boundary checks).

## Binary Search
- Repeatedly halve search space in sorted arrays.
- Complexity: `O(log n)`.
```pseudo
BinarySearch(A, x):
    lo = 0; hi = |A|-1
    while lo <= hi:
        mid = lo + (hi-lo)/2
        if A[mid] == x: return mid
        if A[mid] < x: lo = mid + 1
        else: hi = mid - 1
    return not_found
```

## MergeSort
- Divide array; recursively sort; merge.
- Complexity: `O(n log n)` time; `O(n)` extra space.
```pseudo
MergeSort(A):
    if |A| <= 1: return A
    mid = |A| / 2
    L = MergeSort(A[0:mid])
    R = MergeSort(A[mid:])
    return merge(L, R)
```

## QuickSort
- Partition around a pivot; recursively sort partitions.
- Complexity: average `O(n log n)`, worst `O(n^2)`; space `O(log n)`.
```pseudo
QuickSort(A, lo, hi):
    if lo >= hi: return
    p = partition(A, lo, hi)
    QuickSort(A, lo, p-1)
    QuickSort(A, p+1, hi)
```

## Strassen’s Matrix Multiplication
- 7 multiplications for 2x2 block matrices reduce exponent: `O(n^{log2 7}) ≈ O(n^{2.807})`.
- Pitfalls: numerical stability; overhead dominates unless `n` large; prefer classical for small/medium sizes.
```pseudo
Strassen(A, B):
    if n <= base: return classicalMultiply(A, B)
    split A, B into quadrants
    compute 7 products M1..M7 and combine into C
    return C
```

## QuickSelect (Selection in Linear Expected Time)
- Find k-th smallest element by partitioning around random pivot and recursing on the relevant side.
- Expected `O(n)` time; worst `O(n^2)` (use median-of-medians for worst-case linear time).
```pseudo
QuickSelect(A, k, lo, hi):
    if lo == hi: return A[lo]
    p = partition(A, lo, hi)
    if k == p: return A[p]
    if k < p: return QuickSelect(A, k, lo, p-1)
    else: return QuickSelect(A, k, p+1, hi)
```

## Karatsuba Multiplication
- Splits numbers/polynomials and reduces 4 sub-mults to 3: T(n) = 3 T(n/2) + O(n) ⇒ O(n^{log2 3}) ≈ O(n^{1.585}).

## Closest Pair of Points (Planar)
- Sort by x; divide into halves; recursively solve; merge by checking strip of width 2*delta with points sorted by y.
- Runs in O(n log n); combination step checks at most 7 neighbors per point in strip.

## Master Theorem Reminders
- T(n) = a T(n/b) + f(n): compare f(n) with n^{log_b a} and apply appropriate case.
- Use recursion trees when Master doesn’t apply (e.g., variable subproblem sizes).

## Tips
- Balance subproblems for depth `O(log n)` where possible.
- Use tail recursion elimination or iterative forms to control stack usage.
- For cache efficiency, use blocked recursion or in-place partitioning.
