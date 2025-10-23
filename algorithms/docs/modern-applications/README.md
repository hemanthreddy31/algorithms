# Modern Applications

Algorithms powering AI/ML, big data, and parallel systems; includes complexity, scalability, and practical tips.

## Algorithms in AI/ML
- Stochastic Gradient Descent (SGD): per-iteration cost `O(nnz(features))`; tune learning rate and regularization.
- k-Means (Lloyd): `O(n k d I)` (I iterations); initialize with k-means++.
- Decision Trees: greedy splits by impurity; with presorting features ⇒ `O(n d log n)`.
- Graph Ranking (PageRank): power iteration `O(E I)`; stochastic damping for convergence.
- Pitfalls: feature scaling; ill-conditioning; early stopping and validation.

Worked examples:
- SGD with momentum/Adam: control variance on noisy gradients; use learning-rate schedules and weight decay.
- k-means++ seeding: pick first center uniformly, then sample next centers with probability proportional to squared distance to nearest center.

## Big Data & Streaming
- MapReduce patterns: sort, group-by, join, count, TF-IDF, PageRank.
- External memory: I/O-efficient algorithms; multi-way merge sort; minimize random seeks.
- Streaming approximations: Count-Min Sketch (freq, `O(1)` update), HyperLogLog (distinct), reservoir sampling (uniform sample of a stream).
- Pitfalls: skewed keys; network shuffles; choose partitioners and combiners; monitor memory spill.

Sketch pseudocode:
```pseudo
// d hash functions h_j into width w counters
CMS_Add(x, c):
  for j in 1..d: C[j][h_j(x)] += c

CMS_Query(x):
  return min_j C[j][h_j(x)]

ReservoirSample(stream, k):
  res = first k items
  for i = k+1..n: with prob k/i replace random item in res with item i
  return res
```

## Parallel Algorithms
- Models: Work–Span (T1 work, T∞ span). Brent’s theorem: `Tp ≥ max(T1/p, T∞)`.
- Primitives: map, reduce, scan (prefix sums), filter, sort (sample sort, bitonic for GPUs).
- Graph: frontier-based BFS/SSSP; store CSR for contiguous neighbors.
- Pitfalls: false sharing; load imbalance; synchronization overheads; NUMA effects.

Parallel patterns:
- Parallel scan (prefix-sum) enables parallel DP over associative ops.
- MapReduce joins: repartition by key; use bloom filters for semi-joins to reduce shuffle.
- Graph processing frameworks (Pregel/Giraph): vertex-centric supersteps with message passing.

## Practical Tips
- Favor data locality and cache-friendly access patterns.
- Batch small tasks to amortize overhead (kernel launches, RPCs).
- Use approximate algorithms where exact is too expensive at scale.
- Profile bottlenecks; parallelize only after algorithmic improvements.

Engineering notes:
- Prefer streaming/iterator designs to limit memory usage; spill to disk when needed.
- Use vectorization (SIMD) and cache-blocking for numeric kernels.
