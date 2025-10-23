# Advanced Topics

Flow networks, matchings, NP-completeness, and approximation algorithms.

## Max Flow: Ford–Fulkerson / Edmonds–Karp
- Augment flow along paths in the residual graph until none exist.
- Edmonds–Karp uses BFS to find shortest augmenting paths; runs in `O(VE^2)`.
- Pitfalls: With irrational capacities, naive DFS can cycle; use integer capacities or Edmonds–Karp.
```pseudo
MaxFlow(G, s, t):
    flow = 0; R = residual graph of G
    while exists path P from s to t in R:
        b = min residual capacity along P
        for each edge (u,v) in P:
            R[u][v] -= b; R[v][u] += b
        flow += b
    return flow
```

Min-cut max-flow theorem:
- In any s-t network, the value of the maximum flow equals the capacity of the minimum s-t cut. Residual graphs characterize optimality (no augmenting path ⇔ flow is max and cut is min).

Dinic’s algorithm:
- Builds level graphs via BFS and finds blocking flows via DFS; runs in O(V^2 E) in general, faster on unit networks.

## Bipartite Matching
- Maximum matching via augmenting paths (`O(VE)`), or Hopcroft–Karp (`O(E√V)`).
```pseudo
HopcroftKarp(G=(L∪R, E)):
    matching = {}
    while BFS_layers():
        for u in L:
            if free(u) and DFS_augment(u):
                // increase matching along a free-to-free path
    return matching
```

Notes:
- For maximum cardinality matching in bipartite graphs, Hopcroft–Karp repeatedly augments along a set of shortest disjoint paths.
- For general (non-bipartite) graphs, Edmonds’ Blossom algorithm finds maximum matching by contracting odd cycles.

## Assignment Problem
- Hungarian algorithm for minimum-cost perfect matching in bipartite graphs: `O(n^3)`.

Min-cost max-flow alternative:
- Reduces to successive shortest augmenting paths with potentials to handle non-negative reduced costs.

## NP, NP-hard, NP-complete
- P: solvable in polynomial time.
- NP: verifiable in polynomial time.
- NP-hard: at least as hard as any problem in NP (no requirement to be in NP).
- NP-complete: in NP and NP-hard.
- Proving NP-completeness: show in NP + polynomial-time reduction from known NP-complete problem.

Common reductions:
- 3-SAT → Independent Set/Vertex Cover/Clique; Partition/Subset Sum → Knapsack variants.
- Directed Hamiltonian Cycle ↔ TSP variants; 3D Matching → Set Packing.

## Approximation Algorithms
- Vertex Cover (unweighted): 2-approx via picking both endpoints of uncovered edges.
- Set Cover: greedy by max uncovered per cost ⇒ `H_n`-approx.
- Metric TSP: Christofides 1.5-approx (MST + min perfect matching + Euler + shortcut).
- Knapsack: FPTAS via scaling item values.
- Pitfalls: Check preconditions (e.g., triangle inequality for metric TSP); bound constants carefully.

Randomization:
- Max-Cut: random partition gives 0.5-approx; Goemans–Williamson uses SDP and randomized rounding for ~0.878-approx.

PTAS/FPTAS distinctions:
- PTAS: for any epsilon>0 returns a (1+epsilon)-approx in poly-time, but exponent may depend on 1/epsilon.
- FPTAS: poly-time in both input size and 1/epsilon (e.g., knapsack via value scaling).
