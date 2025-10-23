# Backtracking

Systematically search for solutions by building partial candidates and abandoning those that cannot lead to valid solutions (pruning).

## Design Principles
- Feasible partial state representation (e.g., used columns/diagonals in N-Queens).
- Choices ordering (heuristics like MRV: most constrained variable first).
- Pruning and bounds (forward checking, constraint propagation).

## N-Queens
- Place `n` queens on an `n×n` board so none attack each other.
- State: columns `cols`, diagonals `diag1=r-c`, `diag2=r+c`.
```pseudo
NQueens(n):
    cols, diag1, diag2 = sets
    sol = array(n, -1)
    def place(r):
        if r == n: output(sol); return
        for c in 0..n-1:
            if c∉cols and (r-c)∉diag1 and (r+c)∉diag2:
                add c to cols; add (r-c) to diag1; add (r+c) to diag2
                sol[r] = c
                place(r+1)
                remove c from cols; remove (r-c) from diag1; remove (r+c) from diag2
    place(0)
```

Worked example (n=4):
- Try row 0 col 1; row 1 col 3; row 2 has no valid col → backtrack; try row 0 col 2; continue → one solution [2,0,3,1].

## Sudoku Solver
- Choose an empty cell; try digits consistent with row/col/box; recurse; backtrack.
- Heuristics: pick cell with fewest candidates; maintain bitmasks for speed.
```pseudo
SolveSudoku(board):
    if no empty cell: return true
    (r, c) = select_cell_with_fewest_candidates()
    for d in candidates(r, c):
        if valid(r, c, d):
            place(r, c, d)
            if SolveSudoku(board): return true
            remove(r, c, d)
    return false
```

Optimizations:
- Use 3 arrays of bitmasks (rows, cols, boxes) to test/set/unset constraints in O(1).
- Apply constraint propagation (naked singles, hidden singles) before branching to reduce search.

## Hamiltonian Cycle
- Find a cycle that visits each vertex exactly once (NP-complete).
- Backtrack by extending path with unvisited neighbors that keep graph connected.
```pseudo
HamiltonianCycle(G):
    path = [start]
    def extend():
        if |path| == |V| and edge(path[-1], start): output(path); return true
        for v in neighbors(path[-1]):
            if v not in path and is_feasible(path, v):
                path.append(v)
                if extend(): return true
                path.pop()
        return false
    return extend()
```

Related problems:
- Graph coloring, subset sum, and exact cover (Algorithm X with Dancing Links) are classic backtracking applications.
- For TSP, add lower bounds (1-tree, MST-based) to prune via branch and bound.

## Pitfalls & Tips
- Avoid recomputing constraints from scratch; maintain incremental state.
- Ordering choices well can make exponential search practical on typical instances.
- Add bounds and heuristics (e.g., branch and bound for TSP) when possible.

Complexity notes:
- Backtracking is generally exponential in the worst case; practical performance hinges on pruning strength and variable/value ordering.
