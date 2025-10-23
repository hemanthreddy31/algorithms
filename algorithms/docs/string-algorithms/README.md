# String Algorithms

Core algorithms for pattern matching and text processing.

## Knuth–Morris–Pratt (KMP)
- Avoids rechecking by using the longest proper prefix which is also a suffix (prefix function).
- Complexity: `O(n + m)`.
```pseudo
prefix_function(P):
    pi = array(|P|, 0)
    j = 0
    for i in 1..|P|-1:
        while j > 0 and P[i] != P[j]: j = pi[j-1]
        if P[i] == P[j]: j++
        pi[i] = j
    return pi

KMP(T, P):
    pi = prefix_function(P); j = 0
    for i in 0..|T|-1:
        while j > 0 and T[i] != P[j]: j = pi[j-1]
        if T[i] == P[j]: j++
        if j == |P|: report i - |P| + 1; j = pi[j-1]
```

Intuition:
- `pi[i]` stores the length of the longest proper prefix of P that is also a suffix of P[0..i]. When a mismatch occurs at j, jump to `pi[j-1]` instead of starting over.

Worked example:
- P = “ababaca” → pi = [0,0,1,2,3,0,1]. Use pi to slide pattern over text without backtracking i.

## Rabin–Karp (Rolling Hash)
- Hash-based multi-pattern or single-pattern search; verify on hash match.
- Complexity: expected `O(n + m)`, worst `O(nm)` if collisions.
- Tips: use large mod(s) and double hashing to minimize collisions.
```pseudo
RabinKarp(T, P, base, mod):
    n=|T|; m=|P|; if m>n: return
    h = base^(m-1) mod mod
    pHash = 0; tHash = 0
    for i in 0..m-1:
        pHash = (pHash*base + P[i]) % mod
        tHash = (tHash*base + T[i]) % mod
    for s in 0..n-m:
        if pHash == tHash and T[s..s+m-1] == P: report s
        if s < n-m:
            tHash = (tHash - T[s]*h) % mod
            tHash = (tHash*base + T[s+m]) % mod
            if tHash < 0: tHash += mod
```

Notes:
- Use base around alphabet size (e.g., 256) and a large prime modulus (or two moduli) to reduce collision probability.
- For multiple patterns, hash all patterns of same length and check matches via a hash set.

## Z-Algorithm
- Computes `Z[i]` = LCP of `S` and `S[i..]`; useful for pattern matching with `P#T`.
- Complexity: `O(n)`.
```pseudo
Zalgo(S):
    Z = array(|S|, 0); L=R=0
    for i in 1..|S|-1:
        if i <= R: Z[i] = min(R - i + 1, Z[i - L])
        while i + Z[i] < |S| and S[Z[i]] == S[i + Z[i]]: Z[i]++
        if i + Z[i] - 1 > R: L = i; R = i + Z[i] - 1
    return Z
```

Use cases:
- Pattern matching: compute Z on P#T (with delimiter not in alphabet) and report positions where Z[i] >= |P|.
- String properties: borders, repetitions, and substring equality checks.

## Trie (Prefix Tree)
- Store strings with shared prefixes; operations `O(L)` for string length `L`.
- Pitfalls: memory usage; use compressed tries or DAWG for large dictionaries.
```pseudo
TrieInsert(root, s):
    node = root
    for ch in s:
        if ch not in node.next: node.next[ch] = new node
        node = node.next[ch]
    node.terminal = true

TrieSearch(root, s):
    node = root
    for ch in s:
        if ch not in node.next: return false
        node = node.next[ch]
    return node.terminal
```

Further topics:
- Aho–Corasick automaton: trie + failure links for multi-pattern search in O(n + total matches).
- Suffix array/tree: support substring queries, LCP, and pattern matching in O(m log n) or O(m) with additional structures.
