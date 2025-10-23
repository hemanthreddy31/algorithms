# Dynamic Programming

DP solves problems with optimal substructure and overlapping subproblems by caching sub-results. Choose memoization (top-down) or tabulation (bottom-up) and define state, transition, base cases, and order.

## Design Checklist
- Define state explicitly (indices, capacities, features).
- Write recurrence/transition; confirm no forward dependencies.
- Initialize base cases carefully.
- Choose iteration order or topological order for DAG-like transitions.
- Optimize space where possible (rolling arrays, 1D DP).

## 0/1 Knapsack (Value Maximization)
- Pick items to maximize value under capacity `W`.
- Complexity: `O(nW)` time; `O(nW)` space (or `O(W)` optimized).
```pseudo
Knapsack(W, w[], v[], n):
    dp = array(n+1, W+1, 0)
    for i in 1..n:
        for c in 0..W:
            dp[i][c] = dp[i-1][c]
            if w[i] <= c:
                dp[i][c] = max(dp[i][c], dp[i-1][c-w[i]] + v[i])
    return dp[n][W]
```

Reconstruction (which items picked):
```pseudo
reconstruct():
    i = n; c = W; take = []
    while i > 0:
        if dp[i][c] == dp[i-1][c]: i--
        else: take.append(i); c -= w[i]; i--
    return reverse(take)
```
Space optimization: iterate capacity `c` descending to reuse 1D `dp[c]`.

## Longest Common Subsequence (LCS)
- Complexity: `O(nm)` time, `O(nm)` space; length-only `O(min(n, m))`.
```pseudo
LCS(A, B):
    dp = array(|A|+1, |B|+1, 0)
    for i in 1..|A|:
        for j in 1..|B|:
            if A[i-1] == B[j-1]: dp[i][j] = dp[i-1][j-1] + 1
            else: dp[i][j] = max(dp[i-1][j], dp[i][j-1])
    return dp[|A|][|B|]
```

Reconstruction:
```pseudo
i = |A|; j = |B|; out = []
while i>0 and j>0:
    if A[i-1]==B[j-1]: out.append(A[i-1]); i--; j--
    else if dp[i-1][j] >= dp[i][j-1]: i--
    else: j--
return reverse(out)
```

## Matrix Chain Multiplication (MCM)
- Parenthesize product to minimize scalar multiplications.
- Complexity: `O(n^3)` time, `O(n^2)` space.
```pseudo
MatrixChain(d[0..n]):
    dp = array(n, n, 0)
    for len in 2..n:
        for i in 1..n-len+1:
            j = i + len - 1
            dp[i][j] = INF
            for k in i..j-1:
                cost = dp[i][k] + dp[k+1][j] + d[i-1]*d[k]*d[j]
                dp[i][j] = min(dp[i][j], cost)
    return dp[1][n]
```

Also store split index `split[i][j] = argmin_k(...)` to reconstruct optimal parenthesization.

## Coin Change (Minimum Coins)
- Complexity: `O(amount * |coins|)`.
```pseudo
MinCoins(amount, coins):
    dp = array(amount+1, INF); dp[0] = 0
    for c in coins:
        for a in c..amount:
            dp[a] = min(dp[a], dp[a-c] + 1)
    return dp[amount] if finite else -1
```

Number of ways (combinations):
```pseudo
Ways(amount, coins):
    dp = array(amount+1, 0); dp[0] = 1
    for c in coins:            // order coins outer → combinations
        for a in c..amount:
            dp[a] += dp[a-c]
    return dp[amount]
```

## Longest Increasing Subsequence (LIS)
- Patience sorting method; returns length in `O(n log n)`.
```pseudo
LIS(A):
    tails = []
    for x in A:
        i = lower_bound(tails, x)
        if i == |tails|: tails.append(x)
        else: tails[i] = x
    return |tails|
```

Reconstruct LIS:
```pseudo
LIS_with_path(A):
    tails = []; tails_idx = []; prev = array(|A|, -1)
    for i, x in enumerate(A):
        k = lower_bound(tails, x)
        if k == |tails|:
            tails.append(x); tails_idx.append(i)
        else:
            tails[k] = x; tails_idx[k] = i
        if k > 0: prev[i] = tails_idx[k-1]
    // rebuild
    k = |tails_idx|-1; idx = tails_idx[k]; out = []
    while idx != -1: out.append(A[idx]); idx = prev[idx]
    return reverse(out)
```

## Pitfalls & Tips
- Watch for off-by-one indexing in DP tables.
- Ensure transitions consider all cases (e.g., include/exclude item in knapsack).
- Reconstruct solutions by storing choices or predecessors.
- Use bitset optimizations for large boolean knapsack.

Patterns and optimizations:
- Knuth/Yao optimizations for quadrangle inequality DPs; divide-and-conquer DP for monotone optima.
- Slope trick/convex hull trick for DP with linear transitions and convex envelopes.
- Order states topologically for DAG DPs; for grids, ensure dependencies are satisfied by iteration order.
