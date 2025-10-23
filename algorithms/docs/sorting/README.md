# Sorting Algorithms

Concise reference for comparison and non-comparison sorting. Includes intuition, pseudocode, complexity, and pitfalls.

## QuickSort
- Idea: Partition around a pivot, recursively sort partitions; in-place and cache-friendly.
- Complexity: average `O(n log n)`, worst `O(n^2)`, space `O(log n)` (stack).
- Pitfalls: Bad pivots cause worst-case; use randomization or median-of-3.
```pseudo
QuickSort(A, lo, hi):
    if lo >= hi: return
    p = partition(A, lo, hi)
    QuickSort(A, lo, p-1)
    QuickSort(A, p+1, hi)

partition(A, lo, hi):
    pivot = A[hi]
    i = lo
    for j in lo..hi-1:
        if A[j] <= pivot:
            swap(A[i], A[j]); i++
    swap(A[i], A[hi])
    return i
```

## MergeSort
- Idea: Divide array into halves; sort and merge; stable.
- Complexity: `O(n log n)` time, `O(n)` extra array space; `O(log n)` stack.
- Pitfalls: Memory pressure with arrays; prefer linked lists when possible.
```pseudo
MergeSort(A):
    if |A| <= 1: return A
    mid = |A| / 2
    L = MergeSort(A[0:mid])
    R = MergeSort(A[mid:])
    return merge(L, R)

merge(L, R):
    i=j=0; out=[]
    while i<|L| and j<|R|:
        if L[i] <= R[j]: out.append(L[i]); i++
        else: out.append(R[j]); j++
    append remaining
    return out
```

## HeapSort
- Idea: Build max-heap; repeatedly extract max.
- Complexity: `O(n log n)` time, `O(1)` extra space, unstable.
- Pitfalls: Cache-unfriendly, often slower constants than QuickSort.
```pseudo
HeapSort(A):
    buildMaxHeap(A)
    for i from |A|-1 downto 1:
        swap(A[0], A[i])
        heapify(A, 0, i)

buildMaxHeap(A):
    for i in floor(|A|/2)-1 downto 0:
        heapify(A, i, |A|)

heapify(A, i, n):
    largest = i
    l = 2*i+1; r = 2*i+2
    if l < n and A[l] > A[largest]: largest = l
    if r < n and A[r] > A[largest]: largest = r
    if largest != i: swap(A[i], A[largest]); heapify(A, largest, n)
```

## Counting Sort
- Idea: Count occurrences of integer keys and prefix-sum to positions.
- Complexity: `O(n + k)` time and space (keys in `[0..k]`), stable.
- Pitfalls: Large `k` wastes memory; works best when `k = O(n)`.
```pseudo
CountingSort(A, k):
    C = array(k+1, 0)
    for x in A: C[key(x)]++
    for i in 1..k: C[i] += C[i-1]
    B = array(|A|)
    for x in A reversed:
        i = key(x); C[i]--; B[C[i]] = x
    return B
```

## Radix Sort
- Idea: Sort by digits using a stable inner sort (e.g., counting sort).
- Complexity: `O(d*(n + base))`; often `O(n log_base U)`.
- Pitfalls: Requires stable inner sort; choose base to balance passes vs memory.
```pseudo
RadixSort(A, base, digits):
    for d in 0..digits-1:
        A = StableCountingSortOnDigit(A, d, base)
    return A
```

## Bucket Sort
- Idea: Distribute uniformly random `[0,1)` numbers into buckets; sort each; concatenate.
- Complexity: Expected `O(n)`; worst `O(n^2)` if keys cluster.
- Pitfalls: Requires near-uniform distributions or adaptive bucket sizing.
```pseudo
BucketSort(A):
    n = |A|
    B = array(n) of empty lists
    for x in A: B[floor(n*x)].append(x)
    for i in 0..n-1: sort(B[i])
    return concatenate(B[0..n-1])
```

## When to Use Which Sort
- General-purpose in-memory: randomized QuickSort or Introsort (QuickSort with heap fallback) for speed and cache locality.
- Stable required (e.g., sort by multiple keys): MergeSort or Timsort (Python/Java) which exploits runs.
- Key range small or integers: Counting or Radix sort (non-comparison) for linear time.
- Memory constrained: HeapSort (in-place) when stability not required.
- External/very large data: external merge sort with multi-way merging and replacement selection.

## Comparison Model Lower Bound
- Any comparison-based sort must make Omega(n log n) comparisons in the worst case by the decision-tree argument (n! leaves ⇒ height >= log(n!) = Theta(n log n)).

## Stability and In-place Notes
- Stability preserves the relative order of equal keys (important for multi-key sorts).
- In-place: QuickSort and HeapSort are in-place; array MergeSort is not (linked-list merges are in-place).

## Introsort (Hybrid)
- Start with QuickSort; if recursion depth exceeds 2*floor(log2 n), switch to HeapSort; finish small partitions with InsertionSort. Offers worst-case O(n log n) and great average performance.

## Worked Example: QuickSort Partition
Array: [9, 3, 7, 5, 6, 4, 8, 2] (pivot = last = 2)
- Scan j from lo..hi-1; only values <= 2 are swapped left. Final swap puts 2 at index 0; partitions: [] | 2 | [9,3,7,5,6,4,8]. Recursively sort right side.
- Randomized pivot avoids adversarial inputs (already-sorted arrays).

## External Sorting Sketch
1) Read chunks that fit memory, sort each chunk, write runs to disk.
2) K-way merge runs using a min-heap buffer; minimize random I/O by large block reads.

## Common Bugs and Tips
- Off-by-one when merging or partitioning; carefully handle inclusive/exclusive indices.
- For QuickSort, ensure tail recursion elimination or reorder recursive calls to limit stack depth.
- Counting/Radix: ensure stability in the digit/key pass; beware large base memory usage.
