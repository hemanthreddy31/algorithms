# Foundations: Complexity and Analysis

These notes summarize complexity notations, analysis techniques, and practical guidance. Use them for quick revision and as a reference while designing or analyzing algorithms.

## Key Concepts
- Time complexity: growth of running time with input size `n`.
- Space complexity: extra memory beyond input/output.
- Input model: be explicit about what `n` counts (e.g., items, vertices, characters).
- Word RAM: default model; O(1) arithmetic on word-sized integers.

## Asymptotic Notation
- Big-O: upper bound up to constants. Example: `T(n) = O(n log n)`.
- Big-Theta: tight bound, both upper and lower. Example: `T(n) = Θ(n)`.
- Big-Omega: lower bound. Example: `T(n) = Ω(n)`.
- Little-o / little-omega: strict upper/lower bounds.
- Common growth: `1 < log n < n < n log n < n^2 < n^3 < 2^n < n!`.

## Amortized Analysis
- Aggregate: analyze `m` operations together, divide by `m`.
- Accounting: overcharge some ops to “pay” for costly ones later.
- Potential method: define `Φ(state)` so that
  `amortized_cost(op) = actual_cost(op) + Φ(after) - Φ(before)`.

Example: dynamic array push with capacity doubling
- Total cost of `m` pushes ≤ `3m` ⇒ O(1) amortized per push.

## Recurrences & Master Theorem
For `T(n) = a T(n/b) + f(n)` with `a ≥ 1, b > 1`:
- If `f(n) = O(n^{log_b a - ε})` ⇒ `T(n) = Θ(n^{log_b a})`.
- If `f(n) = Θ(n^{log_b a} log^k n)` ⇒ `T(n) = Θ(n^{log_b a} log^{k+1} n)`.
- If `f(n) = Ω(n^{log_b a + ε})` and regularity holds ⇒ `T(n) = Θ(f(n))`.

## Practical Guidance
- Choose `n` carefully (items vs. vertices vs. edges).
- Check constraints (e.g., `n ≤ 1e5`, weights range, integer sizes).
- Prefer cache-friendly access patterns (contiguous arrays).
- Use randomized choices to avoid adversarial worst-cases where applicable.

## Common Pitfalls
- Off-by-one boundaries in loops and binary search.
- Ignoring hidden constants that dominate at moderate `n`.
- Using Dijkstra with negative edges; relying on unstable sort when stability matters.

## Quick Examples (Pseudocode)

Binary search (overflow-safe mid):
```pseudo
BinarySearch(A, x):
    lo = 0; hi = |A| - 1
    while lo <= hi:
        mid = lo + (hi - lo) / 2
        if A[mid] == x: return mid
        if A[mid] < x: lo = mid + 1
        else: hi = mid - 1
    return not_found
```

Potential method sketch for dynamic array push:
```pseudo
Potential = 2 * size - capacity
// Amortized cost of push <= 3 with doubling strategy
```

---

Use this foundation to guide analysis in subsequent topics: sorting, searching, graphs, DP, greedy, and beyond.

## Deeper Theory and Practice

Cost models and assumptions:
- RAM model: unit-cost arithmetic on word-sized integers; memory access O(1).
- Comparison model: algorithms that only compare elements (e.g., comparison sorting) have an n log n lower bound.
- I/O model (external memory): count block transfers; algorithms optimize sequential access and minimize random I/O.

Lower bounds and decision trees (example: comparison sorting):
- Any comparison sort defines a decision tree with at least n! leaves (distinct permutations).
- Height h of a binary tree with L leaves satisfies L <= 2^h, so n! <= 2^h, and h >= log2(n!).
- Using Stirling’s approximation, log2(n!) = Theta(n log n), hence any comparison-based sort takes Omega(n log n) comparisons in the worst case.

Amortized analysis patterns:
- Dynamic tables (array growth factor g): total cost of m appends is O(m) when g > 1; common choices g in [1.5, 2].
- Decremental operations: support pop/back shrink with hysteresis (e.g., shrink when size < capacity/4) to avoid thrashing.

Recurrences beyond Master Theorem:
- Akra–Bazzi handles unbalanced splits: T(x) = sum a_i T(b_i x) + g(x) with sum a_i b_i^p = 1; then T(x) = Theta(x^p (1 + integral(g(u)/u^{p+1}) du)).
- Use recursion-tree method to visualize contributions per level when closed forms are unclear.

Proof patterns for correctness:
- Loop invariants: specify initialization, maintenance, termination (e.g., binary search maintains A[lo..hi] contains target if present).
- Greedy exchange argument: show any optimal solution can be transformed to the greedy solution without worsening objective.
- DP optimal substructure: argue replacing any subsolution by an optimal one preserves optimality.

Worked analysis example: dynamic array push
- Strategy: double capacity when full; cost of simple push is 1; resize cost is current size (copy).
- Total cost over m pushes <= sum over i of (1 + occasional copy). Copy happens at sizes 1,2,4,8,..., so total copies < 2m.
- Aggregate bound: total < 3m, amortized O(1) per push.

Checklist before finalizing an analysis:
- Define n clearly and the input distribution/model.
- State time and space bounds and any probabilistic guarantees.
- Identify worst, average, and amortized complexities distinctly.
- Note constant factors and cache/memory-access behavior when relevant.

Further reading (standard references):
- Cormen, Leiserson, Rivest, Stein: Introduction to Algorithms (CLRS)
- Kleinberg, Tardos: Algorithm Design
- Knuth: The Art of Computer Programming, Vol. 1–3
