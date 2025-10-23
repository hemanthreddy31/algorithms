# Greedy Algorithms

Greedy methods build solutions by making locally optimal choices that lead to a global optimum under the right structure (e.g., matroids, exchange property).

## Design Principles
- Greedy-choice property: can extend a partial optimal solution with a local best choice.
- Optimal substructure: optimal solutions contain optimal subsolutions.
- Exchange argument: show any optimal solution can be transformed to greedy’s without hurting optimality.

## Activity Selection (Interval Scheduling)
- Select maximum number of non-overlapping intervals.
- Strategy: sort by earliest finish time; take compatible intervals greedily.
- Complexity: `O(n log n)` for sorting; linear scan after.
```pseudo
ActivitySelection(intervals):
    sort intervals by finish time ascending
    last_end = -∞; result = []
    for (s, f) in intervals:
        if s >= last_end:
            result.append((s, f))
            last_end = f
    return result
```

Correctness sketch (exchange argument):
- Let G be greedy solution and O be an optimal solution. Consider the first interval where they differ. Greedy picks the earliest finishing interval f*. Replacing O’s first differing interval with f* does not reduce the number of intervals because f* frees up at least as much room for the rest. Repeating yields G without loss of optimality.

Variants:
- Scheduling with weights (maximize total weight of non-overlapping intervals) is not solved by this greedy rule; use DP with binary search over previous-compatible interval.

## Huffman Coding (Optimal Prefix Codes)
- Merge two least frequent nodes repeatedly to build a binary tree.
- Complexity: `O(n log n)` with a min-heap.
```pseudo
Huffman(freq):
    pq = min-heap of (freq, node)
    while |pq| > 1:
        (f1, x) = pop(); (f2, y) = pop()
        z = new node(x, y)
        push (f1 + f2, z)
    return pop() // root
```

Worked example:
- Frequencies: a:5, b:9, c:12, d:13, e:16, f:45
- Merge (5,9)->14, (12,13)->25, (14,16)->30, (25,30)->55, (45,55)->100
- Codes (one valid set): f:0, c:100, d:101, a:1100, b:1101, e:111

## Minimum Spanning Tree (MST)
- Kruskal: sort edges and add if no cycle (DSU) ⇒ `O(E log E)`.
- Prim: grow from a seed via cheapest crossing edge ⇒ `O(E log V)`.

Why greedy works here:
- Cut property: the cheapest edge crossing any cut that respects the MST is always safe to choose.

## Pitfalls & Best Practices
- Verify greedy-choice property (counterexamples often subtle).
- If greedy fails, try dynamic programming or add constraints (e.g., matroid intersection).
- Use canonical Huffman codes for compact serialization.

Common counterexamples:
- Coin change greedy fails for denominations like {1,3,4} to make 6: greedy picks 4+1+1 (3 coins) instead of 3+3 (2 coins).
- Shortest path greedy by locally smallest edge weight fails (need Dijkstra/Bellman-Ford).
