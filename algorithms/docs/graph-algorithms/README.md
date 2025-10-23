# Graph Algorithms

Covers traversal, shortest paths, all-pairs, and minimum spanning trees, plus topological sorting.

## BFS (Breadth-First Search)
- Unweighted shortest paths; level-order exploration.
- Complexity: `O(V+E)`.
- Pitfall: Mark visited when enqueuing to avoid duplicates.
```pseudo
BFS(G, s):
    dist = array(|V|, INF); dist[s]=0
    parent = array(|V|, -1)
    Q = queue(); Q.enqueue(s)
    while not Q.empty():
        u = Q.dequeue()
        for v in G.adj[u]:
            if dist[v] == INF:
                dist[v] = dist[u] + 1
                parent[v] = u
                Q.enqueue(v)
```

Notes and uses:
- Produces BFS tree; `dist` gives shortest hop-count in unweighted graphs.
- Multi-source BFS: enqueue all sources with distance 0 to compute distance to nearest source.
- Grid shortest paths and level-based processing (frontier expansion) are classic BFS.

## DFS (Depth-First Search)
- Explore deeply; detect cycles; compute finishing times.
- Complexity: `O(V+E)`.
- Pitfall: Use iterative version to avoid stack overflow on deep graphs.
```pseudo
DFS(G):
    visited = {false}
    for u in V:
        if not visited[u]: dfs_visit(u)

dfs_visit(u):
    visited[u]=true
    for v in G.adj[u]:
        if not visited[v]: dfs_visit(v)
```

Notes:
- Timestamps (discovery/finish) classify edges (tree/back/forward/cross) and detect cycles in directed graphs (back edge ⇒ cycle).
- Use DFS finishing times for topological ordering (reverse finishing order).

## Dijkstra (SSSP, Non-negative Weights)
- Compute shortest path distances from source `s`.
- Complexity: adjacency list + heap ⇒ `O((V+E) log V)`.
- Pitfall: Not valid with negative edges.
```pseudo
Dijkstra(G, s):
    dist = array(|V|, INF); dist[s]=0
    parent = array(|V|, -1)
    pq = min-heap(); pq.push((0, s))
    while not pq.empty():
        (d, u) = pq.pop()
        if d != dist[u]: continue
        for (v, w) in G.adj[u]:
            if dist[u] + w < dist[v]:
                dist[v] = dist[u] + w
                parent[v] = u
                pq.push((dist[v], v))
```

Correctness sketch:
- Greedy invariant: when a node u is extracted from the min-heap, `dist[u]` is final (no shorter path exists) because all paths to unexplored nodes are at least as long due to non-negative weights.
- Path reconstruction: store `parent[v]` and backtrack from target.

## Bellman-Ford (SSSP with Negative Edges)
- Handles negative edges; detects negative cycles.
- Complexity: `O(VE)`.
```pseudo
BellmanFord(G, s):
    dist = array(|V|, INF); dist[s]=0
    for i in 1..|V|-1:
        for each edge (u, v, w):
            if dist[u] + w < dist[v]:
                dist[v] = dist[u] + w
    for each edge (u, v, w):
        if dist[u] + w < dist[v]: report negative cycle
```

Tips:
- Early stopping: if an iteration makes no updates, terminate.
- For all-pairs with negative edges but no negative cycles, consider Johnson’s algorithm (reweight via potentials + Dijkstra).

## Floyd–Warshall (All-Pairs Shortest Paths)
- Works with negative edges; no negative cycles.
- Complexity: `O(V^3)`; space `O(V^2)`.
```pseudo
FloydWarshall(dist):
    for k in 1..V:
        for i in 1..V:
            for j in 1..V:
                dist[i][j] = min(dist[i][j], dist[i][k] + dist[k][j])
```

Path reconstruction:
- Maintain `next[i][j]` initialized to j if edge (i,j) exists. When relaxing via k, set `next[i][j] = next[i][k]` to rebuild the path.

## Kruskal (Minimum Spanning Tree)
- Sort edges; add if endpoints are in different components (DSU).
- Complexity: `O(E log E)`.
```pseudo
Kruskal(G):
    sort edges by weight
    DSU init
    MST = []
    for (u, v, w) in edges:
        if find(u) != find(v):
            union(u, v)
            MST.append((u, v, w))
    return MST
```

Cut and cycle properties (why it works):
- Cut property: the lightest edge crossing any cut that respects the MST is safe to add.
- Cycle property: in any cycle, the heaviest edge is not in the MST.

## Prim (Minimum Spanning Tree)
- Grow MST from a seed using a min-edge to the cut.
- Complexity: adjacency list + heap ⇒ `O(E log V)`.
```pseudo
Prim(G, s):
    inMST = {false}
    key = array(|V|, INF); key[s]=0
    parent = array(|V|, -1)
    pq = min-heap(); pq.push((0, s))
    while not pq.empty():
        (k, u) = pq.pop()
        if inMST[u]: continue
        inMST[u] = true
        for (v, w) in G.adj[u]:
            if not inMST[v] and w < key[v]:
                key[v] = w; parent[v] = u
                pq.push((key[v], v))
```

Notes:
- Suitable for dense graphs with O(V^2) implementation (no heap, adjacency matrix).
- For sparse graphs, adjacency list + heap is preferred.

## Topological Sort (DAG)
- Linear order where edges go forward.
- Complexity: `O(V+E)`.
- Pitfall: Only for DAGs; if not all vertices processed, there is a cycle.
```pseudo
TopoSort(G):
    indeg[v] = 0 for all v
    for u in V: for v in G.adj[u]: indeg[v]++
    Q = queue of v with indeg[v]==0
    order = []
    while not Q.empty():
        u = Q.dequeue(); order.append(u)
        for v in G.adj[u]:
            indeg[v]--
            if indeg[v] == 0: Q.enqueue(v)
    if |order| != |V|: report cycle
    return order
```

Alternatives:
- DFS-based topo sort: push nodes to a stack on exit; pop in reverse for order.
- Kahn’s algorithm (above) also detects cycles when output size < V.
