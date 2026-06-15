# 🧭 The Complete Algorithms Atlas

> **A language-agnostic, competitive-programming–grade map of *every* algorithmic topic and subtopic — from first principles to ICPC World Finals depth.**
>
> This is a *taxonomy of ideas*, not a code dump. Each leaf is one technique with a half-line of intuition and its standard complexity. No specific language (Java, C++, Python) is assumed — only the algorithms themselves.

<p align="center">
  <img alt="Sections"  src="https://img.shields.io/badge/sections-31-1f6feb?style=for-the-badge">
  <img alt="Topics"    src="https://img.shields.io/badge/topics-5000%2B-2ea043?style=for-the-badge">
  <img alt="Level"     src="https://img.shields.io/badge/level-intro%20%E2%86%92%20ICPC-8957e5?style=for-the-badge">
  <img alt="Scope"     src="https://img.shields.io/badge/scope-language%20agnostic-db6d28?style=for-the-badge">
</p>

---

## How to Read This Atlas

The atlas is organized as a tree. Skim the headings to see the *shape* of a field; drill into bullets for the concrete techniques.

| Element | Meaning |
| --- | --- |
| `# Part` | A thematic cluster of related sections. |
| `## N. Section` | A major algorithmic domain (31 in total). |
| `### Sub-area` / `#### Group` | Logical groupings inside a domain. |
| `- **Technique** — …` | A single algorithm/idea with a half-line explanation and, where standard, its time/space complexity. |
| `(see …)` | A cross-reference: the topic lives in detail under another domain. |

**Complexity shorthand:** `n` = input size · `V`/`E` = vertices/edges · `m` = pattern/query length · `log` = base-2 unless noted · `α` = inverse-Ackermann · amortized/expected bounds are marked inline. Big-O is implied on every complexity unless stated otherwise.

---

## Table of Contents

**🧮 Foundations**

1. [Algorithmic Foundations & Complexity Analysis](#1-algorithmic-foundations--complexity-analysis)

**🗃️ Data Structures**

2. [Elementary & Linear Data Structures](#2-elementary--linear-data-structures)
3. [Hashing & Hash-Based Structures](#3-hashing--hash-based-structures)
4. [Priority Queues, Heaps & Balanced Search Trees](#4-priority-queues-heaps--balanced-search-trees)
5. [Range-Query Data Structures](#5-range-query-data-structures)
6. [Advanced, Persistent & Specialized Data Structures](#6-advanced-persistent--specialized-data-structures)

**⚙️ Core Techniques & Paradigms**

7. [Sorting Algorithms](#7-sorting-algorithms)
8. [Searching, Selection & Order Statistics](#8-searching-selection--order-statistics)
9. [Bit Manipulation & Bitmasking](#9-bit-manipulation--bitmasking)
10. [Prefix Sums, Two Pointers & Sliding Window](#10-prefix-sums-two-pointers--sliding-window)
11. [Recursion & Divide and Conquer](#11-recursion--divide-and-conquer)
12. [Greedy Algorithms](#12-greedy-algorithms)

**📈 Dynamic Programming**

13. [Dynamic Programming — Foundations & Classic Patterns](#13-dynamic-programming--foundations--classic-patterns)
14. [Dynamic Programming — Advanced & Optimizations](#14-dynamic-programming--advanced--optimizations)

**🕸️ Graphs & Trees**

15. [Graph Fundamentals & Traversal](#15-graph-fundamentals--traversal)
16. [Shortest Path Algorithms](#16-shortest-path-algorithms)
17. [Minimum Spanning Trees, Connectivity & DSU](#17-minimum-spanning-trees-connectivity--dsu)
18. [Network Flow, Cuts & Matching](#18-network-flow-cuts--matching)
19. [Tree Algorithms & Decompositions](#19-tree-algorithms--decompositions)

**🔤 Strings**

20. [String Matching, Periodicity & Hashing](#20-string-matching-periodicity--hashing)
21. [Suffix Structures, Tries & String Automata](#21-suffix-structures-tries--string-automata)

**🔢 Mathematics**

22. [Number Theory & Modular Arithmetic](#22-number-theory--modular-arithmetic)
23. [Combinatorics, Counting & Probability](#23-combinatorics-counting--probability)
24. [Polynomials, FFT/NTT & Linear Algebra](#24-polynomials-fftntt--linear-algebra)

**📐 Geometry & Games**

25. [Computational Geometry](#25-computational-geometry)
26. [Combinatorial Game Theory](#26-combinatorial-game-theory)

**🎲 Search, Randomization & Offline**

27. [Backtracking, Exhaustive Search & Meet-in-the-Middle](#27-backtracking-exhaustive-search--meet-in-the-middle)
28. [Randomized & Probabilistic Algorithms](#28-randomized--probabilistic-algorithms)
29. [Offline Techniques & Query Decomposition](#29-offline-techniques--query-decomposition)

**🧠 Optimization & Intractability**

30. [Mathematical & Continuous Optimization](#30-mathematical--continuous-optimization)
31. [Intractability: NP-Completeness, Approximation & Heuristics](#31-intractability-np-completeness-approximation--heuristics)

**📚 Appendix**

- [Further Reading & Foundational References](#-further-reading--foundational-references)

---

# 🧮 Part — Foundations

## 1. Algorithmic Foundations & Complexity Analysis

### Asymptotic Notation & Growth of Functions

#### Core Notations
- **Big-O (O)** — asymptotic upper bound; f = O(g) if f(n) ≤ c·g(n) eventually (worst-case ceiling).
- **Big-Omega (Ω)** — asymptotic lower bound; f = Ω(g) if f(n) ≥ c·g(n) eventually (guaranteed floor).
- **Big-Theta (Θ)** — tight bound; f = Θ(g) iff f = O(g) and f = Ω(g) (exact growth rate).
- **Little-o (o)** — strict upper bound; f = o(g) means f/g → 0 (g dominates strictly).
- **Little-omega (ω)** — strict lower bound; f = ω(g) means f/g → ∞ (f dominates strictly).
- **Tilde notation (Õ, Θ̃)** — soft-O hiding polylogarithmic factors, e.g. Õ(n) = O(n·polylog n).
- **Asymptotic equivalence (~)** — f ~ g iff f/g → 1 (leading term identical, used in average-case constants).

#### Properties & Manipulation
- **Transitivity / reflexivity / symmetry** — O,Ω,Θ form a partial order; Θ symmetric, o/ω strict and irreflexive.
- **Sum & max rule** — O(f)+O(g)=O(max(f,g)); dominant term absorbs lower-order terms.
- **Product rule** — O(f)·O(g)=O(f·g); used in nested-loop and product-of-phases analysis.
- **Polynomial vs exponential vs logarithmic hierarchy** — log n ≪ n^c ≪ c^n ≪ n! ≪ n^n growth ordering.
- **Limit test for comparison** — lim f/g determines o/Θ/ω classification; L'Hôpital and Stirling as tools.
- **Stirling's approximation** — n! ~ √(2πn)(n/e)^n; gives log(n!) = Θ(n log n) for sorting bounds.
- **Harmonic number bound** — H_n = Σ1/i = ln n + γ + O(1/n); appears in quicksort/treap analysis (Θ(log n)).
- **Iterated logarithm (log* n)** — number of logs to reduce n to ≤1; near-constant (union-find, ≤5 for practical n).
- **Inverse Ackermann (α(n))** — extremely slow-growing; amortized union-find cost (effectively ≤4).
- **Common growth classes** — constant, log, polylog, linear, linearithmic (n log n), quadratic, cubic, polynomial, exponential, factorial.
- **Abuse-of-notation conventions** — "=" as set membership, anonymous functions inside expressions, one-sided equalities.
- **Multivariate asymptotics** — bounds over several parameters (e.g. O(V+E)); subtleties when one variable may dominate.

### Case Analysis of Running Time
- **Best-case complexity** — minimum cost over inputs of size n; rarely the design target (e.g. insertion sort Θ(n)).
- **Worst-case complexity** — maximum cost; the standard guarantee for safety-critical and competitive use.
- **Average-case complexity** — expected cost over an input distribution; requires a stated probability model.
- **Expected complexity (randomized)** — expectation over algorithm's own coin flips, input-independent (e.g. randomized quicksort Θ(n log n)).
- **Amortized complexity** — average cost per operation over a worst-case sequence (no probability involved).
- **Smoothed analysis** — interpolates worst/average via small random input perturbations (explains simplex polynomial behavior).
- **Distributional/probabilistic assumptions** — uniform, Zipfian, or adversarial input models and their effect on bounds.
- **High-probability bounds** — running time holds with probability ≥ 1 − n^{−c} (e.g. skip lists, hashing).
- **Las Vegas vs Monte Carlo** — always-correct/variable-time vs fixed-time/possibly-wrong randomized paradigms.

### Recurrence Relations & Their Solution

#### Forms of Recurrences
- **Divide-and-conquer recurrences** — T(n)=a·T(n/b)+f(n); model recursive splitting cost.
- **Linear recurrences** — T(n)=Σ c_i·T(n−i)+f(n); decrease-by-constant patterns.
- **Full-history recurrences** — T(n) depends on all smaller subproblems (e.g. T(n)=Σ T(i)).
- **Nonhomogeneous / inhomogeneous recurrences** — forcing term f(n) added to homogeneous part.
- **Recurrences with floors/ceilings** — handled by ignoring them (justified) or bounding between ⌊⌋ and ⌈⌉.

#### Solution Methods
1. **Substitution method** — guess a bound, then prove by induction; tighten or weaken the inductive hypothesis as needed.
2. **Recursion-tree method** — sum cost across tree levels to form a good guess (rows × per-row cost).
3. **Master theorem** — solves T(n)=aT(n/b)+f(n) by comparing f(n) to n^{log_b a} (three cases).
4. **Iteration/unrolling** — repeatedly expand the recurrence into a summation, then evaluate.
5. **Characteristic-equation method** — solves homogeneous linear recurrences via roots of the characteristic polynomial.
6. **Generating-function method** — encode sequence as a power series, solve algebraically, extract coefficients.
7. **Change of variables / domain transformation** — e.g. m=log n to linearize T(2^m) type recurrences (Θ-preserving).
- **Master theorem — Case 1** — f(n)=O(n^{log_b a−ε}) ⇒ T=Θ(n^{log_b a}); leaves dominate.
- **Master theorem — Case 2** — f(n)=Θ(n^{log_b a} log^k n) ⇒ T=Θ(n^{log_b a} log^{k+1} n); balanced.
- **Master theorem — Case 3** — f(n)=Ω(n^{log_b a+ε}) with regularity ⇒ T=Θ(f(n)); root dominates.
- **Master theorem gap cases** — non-polynomial separation (e.g. f=n log n vs n) where the theorem does not apply.
- **Akra-Bazzi method** — solves T(x)=Σ a_i T(b_i x)+g(x) with unequal/overlapping subproblem sizes; p solves Σ a_i b_i^p = 1.
- **Akra-Bazzi-Leighton (continuous form)** — integral formulation T=Θ(x^p(1+∫ g(u)/u^{p+1} du)) for general divide-and-conquer.
- **Master theorem for subtract-and-conquer** — T(n)=aT(n−b)+f(n) yields polynomial or exponential growth by a vs 1.
- **Continuous master theorem / general form** — recent unified statements covering broader f(n) classes.
- **Muller-style / Roura's discrete master theorems** — handle ceiling effects and discrete divide-and-conquer rigorously.
- **Linear-recurrence asymptotics (e.g. Fibonacci)** — closed form via golden ratio; Θ(φ^n) growth.

### Amortized Analysis

- **Aggregate method** — bound total cost T(n) of n operations, then divide; amortized cost = T(n)/n.
- **Accounting (banker's) method** — assign amortized charges, store surplus as credit on data-structure elements (credit ≥ 0 invariant).
- **Potential method** — define Φ ≥ Φ₀ over states; amortized cost = actual + (Φ_after − Φ_before); telescopes to real total.
- **Choosing a potential function** — Φ measures "stored work"/disorder; common choices: size, # of set bits, log of size, displacement.
- **Classic examples** — dynamic array doubling (O(1) amortized push), binary counter increment (O(1) amortized), multipop stack.
- **Splay tree / dynamic-tree analysis** — potential = Σ log(subtree size); yields O(log n) amortized per operation.
- **Fibonacci-heap amortization** — Φ = #trees + 2·#marked; gives O(1) amortized decrease-key, O(log n) extract-min.
- **Union-find amortized α(n)** — path compression + union by rank via potential/accounting (near-constant per op).
- **Amortized vs worst-case-per-operation** — amortized guarantees sequence cost, not individual-op cost (deamortization to fix).
- **Deamortization techniques** — convert amortized bounds to worst-case via incremental rebuilding / global rebuilding scheduling.
- **Scapegoat / global rebuilding** — periodic full reconstruction amortized over many updates (O(log n) amortized).

### Models of Computation

- **Turing machine** — formal tape-based model defining computability and complexity classes (canonical reference model).
- **Multi-tape / nondeterministic TM** — variants underpinning NP and time/space class definitions.
- **Random-Access Machine (RAM)** — registers + uniform/log cost; standard model for algorithm time analysis.
- **Word-RAM model** — w-bit words (w ≥ log n), O(1) arithmetic/bitwise on words; basis for o(n log n) integer sorting & bit tricks.
- **Transdichotomous model** — word size scales with input so n fits in O(1) words; used for van Emde Boas, fusion trees.
- **Cell-probe model** — counts only memory accesses; used to prove data-structure lower bounds (e.g. predecessor).
- **Pointer machine** — pointer-following only, no address arithmetic; separates some bounds from RAM.
- **PRAM (parallel RAM)** — EREW/CREW/CRCW shared-memory parallel model (see Parallel & Concurrent Algorithms).
- **Circuit / Boolean model** — gates and depth; AC⁰, NC, parallel-complexity hierarchy (see Complexity Theory).
- **Comparison model** — only key comparisons counted; gives Ω(n log n) sorting and Ω(log n) search lower bounds.
- **Algebraic decision/computation tree model** — real-number operations; lower bounds for element-uniqueness, convex hull.
- **Oracle / black-box model** — query complexity counting calls to a subroutine (decision-tree, query lower bounds).
- **Real-RAM** — exact real arithmetic; standard in computational geometry analysis.
- **Quantum & probabilistic TMs** — models for BQP/BPP (see Complexity Theory).

### Memory-Hierarchy & I/O Complexity

- **External-memory (DAM / I/O) model** — counts block transfers of size B between memory (size M) and disk; cost in I/Os.
- **Cache-aware (B-aware) algorithms** — explicitly tuned to B and M (e.g. B-trees, external mergesort O((n/B)log_{M/B}(n/B))).
- **Cache-oblivious model** — optimal across all levels without knowing B,M; uses recursive/Van-Emde-Boas layouts.
- **Sorting bound in external memory** — Θ((n/B)·log_{M/B}(n/B)) I/Os; the external-memory analogue of n log n.
- **Tall-cache assumption** — M = Ω(B²); required by many cache-oblivious optimality proofs.
- **Cache complexity / work-and-cache analysis** — separate counting of CPU work vs cache misses.
- **Constant factors & memory locality** — spatial/temporal locality, prefetching, false sharing impact on real runtime.
- **Streaming model** — one/few passes, sublinear (polylog) space; sketches and approximate counts (see Sketching/Streaming).
- **Semi-external & I/O-efficient graph algorithms** — when vertices fit in RAM but edges do not.

### Complexity Classes & Reductions

#### Class Hierarchy
- **P** — decidable in deterministic polynomial time; the tractability benchmark.
- **NP** — verifiable in polynomial time / solvable by nondeterministic poly-time TM.
- **co-NP** — complements of NP languages; "no"-certificates checkable in poly time.
- **NP-hard** — at least as hard as every NP problem under poly-time reduction (need not be in NP).
- **NP-complete** — in NP and NP-hard; Cook-Levin (SAT) the seminal complete problem.
- **PSPACE / NPSPACE** — polynomial space; Savitch's theorem gives PSPACE = NPSPACE; QBF is PSPACE-complete.
- **EXP / NEXP / EXPSPACE** — exponential time/space; provably strict separations (time/space hierarchy theorems).
- **L / NL** — log-space classes; NL = co-NL (Immerman-Szelepcsényi); reachability is NL-complete.
- **BPP / RP / ZPP** — bounded-error, one-sided-error, zero-error randomized poly time.
- **PP, #P** — probabilistic majority and counting classes; #P-complete = permanent / counting SAT.
- **BQP** — bounded-error quantum poly time (see Complexity Theory).
- **Polynomial hierarchy (PH)** — Σ_k/Π_k tower above NP; collapses if P=NP.
- **Known inclusions** — L ⊆ NL ⊆ P ⊆ NP ⊆ PSPACE ⊆ EXP; P ⊆ BPP ⊆ ... (most separations open).

#### Reductions & Hardness
- **Many-one (Karp) reduction** — map instances so YES↔YES in poly time; defines NP-completeness.
- **Turing (Cook) reduction** — solve A using an oracle for B; used for NP-hardness of optimization/search.
- **Polynomial-time vs log-space reductions** — finer reductions for P-completeness and L/NL classifications.
- **Approximation-preserving reductions (L-, AP-, gap-reductions)** — transfer inapproximability (see Approximation Algorithms).
- **Self-reducibility** — build a solution from decision queries; search-to-decision equivalence for NP.
- **Gadget reductions** — constructing local widgets to encode constraints (classic NP-hardness proofs).
- **Decision vs optimization vs search/counting** — three problem flavors and their interreducibility.
- **Promise problems** — inputs restricted to a promised subset; basis for gap problems and BPP definitions.

#### Tractability Boundaries
- **Pseudo-polynomial time** — polynomial in numeric value (not bit length), e.g. knapsack DP O(nW).
- **Weak vs strong NP-hardness** — strongly NP-hard ⇒ no pseudo-poly algorithm unless P=NP (e.g. 3-partition).
- **Fixed-parameter tractability (FPT)** — f(k)·poly(n) for parameter k; W-hierarchy for parameterized hardness (see Parameterized Complexity).
- **Subexponential & ETH/SETH** — Exponential/Strong Exponential Time Hypotheses; conditional lower bounds (e.g. 3-SUM, OV).
- **Fine-grained complexity** — tight conditional bounds within P (3-SUM-hard, APSP-hard, OV-hard reductions).
- **Output-sensitive complexity** — cost depends on output size (e.g. convex hull O(n log h), join O(n+out)).
- **Instance/parameterized optimality** — bounds tied to specific input structure rather than worst case.
- **Galactic algorithms** — asymptotically superior but practically useless due to enormous constants.

### Lower Bounds & Adversary Arguments

- **Information-theoretic (decision-tree) lower bounds** — Ω(log(#outcomes)); gives Ω(n log n) comparison sorting.
- **Comparison-model search bound** — Ω(log n) for searching in a comparison-based structure.
- **Adversary argument** — adversary answers queries to keep many inputs consistent, forcing many queries (e.g. finding max needs n−1 comparisons).
- **Adversary lower bounds for selection / merging** — exact comparison counts (e.g. simultaneous min/max in ⌈3n/2⌉−2).
- **Algebraic decision-tree lower bounds** — Ω(n log n) for element uniqueness, set disjointness, closest pair.
- **Cell-probe lower bounds** — predecessor (Beame-Fich / Pătraşcu-Thorup), dynamic connectivity bounds.
- **Communication-complexity lower bounds** — reduce data-structure/streaming bounds to two-party communication (see Complexity Theory).
- **Reduction-based lower bounds** — conditional hardness via 3-SUM, OV, APSP within P (fine-grained).
- **Yao's minimax principle** — randomized lower bound = best deterministic cost on worst input distribution.
- **Ω(n) trivial input-reading bound** — any algorithm must read its input (linear lower bound baseline).
- **Sorting/selection network bounds** — depth/size bounds (AKS, 0-1 principle) for oblivious comparison networks.

### Online Algorithms & Competitive Analysis

- **Online vs offline model** — input revealed incrementally with irrevocable decisions vs full-input knowledge.
- **Competitive ratio** — sup over inputs of ALG(σ)/OPT(σ); c-competitive if ALG ≤ c·OPT + α.
- **Strict vs asymptotic competitiveness** — additive constant α absent vs allowed.
- **Oblivious vs adaptive adversaries** — adversary fixes input upfront vs reacts to algorithm's (randomized) choices.
- **Adaptive-online / adaptive-offline adversaries** — hierarchy for randomized online lower bounds.
- **Potential-function analysis of online algorithms** — bound amortized competitive cost via a potential (e.g. for paging/k-server).
- **Paging / caching** — LRU, FIFO, FWF k-competitive; randomized MARKING O(log k)-competitive; offline OPT = Belady.
- **k-server problem** — generalizes paging on metric spaces; Work Function Algorithm (2k−1)-competitive (conjecture k).
- **List update / self-organizing lists** — Move-To-Front 2-competitive; transpose, frequency-count heuristics.
- **Ski-rental / rent-or-buy** — canonical 2-competitive deterministic, e/(e−1) randomized threshold problem.
- **Online bin packing / scheduling / matching** — First-Fit, Graham's list scheduling, RANKING for bipartite matching (1−1/e).
- **Metrical task systems** — unifying framework for online problems on metric state spaces.
- **Advice complexity & lookahead** — bits of future information needed to reach a competitive ratio.
- **Lower bounds for online problems** — adversary constructions establishing best-possible competitive ratios.

### Correctness, Invariants & Proof Techniques

- **Loop invariant** — property true before/after each iteration; proved by initialization, maintenance, termination.
- **Mathematical induction** — weak, strong, and structural induction over recursive/structural definitions.
- **Recursion correctness proof** — base case + inductive step assuming smaller calls correct.
- **Termination arguments** — strictly decreasing well-founded measure (variant/ranking function) → no infinite loops.
- **Loop variant (ranking function)** — bounds remaining iterations; complements invariant for termination.
- **Partial vs total correctness** — correct-if-it-halts vs correct-and-halts (Hoare logic distinction).
- **Hoare logic / weakest preconditions** — {P} S {Q} triples, assignment/sequence/while rules for formal verification.
- **Pre/postconditions & contracts** — interface-level specification of expected behavior.
- **Exchange argument** — prove greedy optimality by transforming any optimal solution toward the greedy one without worsening it.
- **Cut-and-paste / optimal-substructure argument** — justify DP and greedy by swapping subsolutions.
- **Greedy-stays-ahead argument** — inductively show greedy dominates any other solution at each step.
- **Potential / amortized invariants** — maintain Φ ≥ 0 to certify performance bounds.
- **Monovariant (extremal) arguments** — a quantity monotonically changes, bounding process length (e.g. local-search termination).
- **Invariant-based data-structure correctness** — balance, heap-order, BST-order invariants preserved by every operation.

### Algorithm-Design Paradigms (Foundational Overview)

- **Brute force / exhaustive search** — enumerate all candidates; baseline before optimization.
- **Divide and conquer** — split, recurse, combine; analyzed via master/Akra-Bazzi (see Sorting, D&C domains).
- **Greedy method** — locally optimal choices; correctness via matroids/exchange (see Greedy Algorithms).
- **Dynamic programming** — overlapping subproblems + optimal substructure; memoization vs tabulation (see Dynamic Programming).
- **Backtracking & branch-and-bound** — pruned search with bounding functions (see Search/Combinatorial domains).
- **Randomization** — coin flips for simplicity/speed; Las Vegas & Monte Carlo (see Randomized Algorithms).
- **Approximation** — provable near-optimal for NP-hard problems; ratio bounds (see Approximation Algorithms).
- **Iterative refinement / local search** — improve a solution until a local optimum (see Metaheuristics).
- **Amortized/persistent design** — global rebuilding, lazy propagation, fractional cascading as foundational toolkits.

### Time–Space Tradeoffs & Practical Constants

- **Time–space tradeoff principle** — extra memory (tables, precomputation) can reduce time and vice versa.
- **Meet-in-the-middle** — split exponent in half, trade space for time (e.g. subset-sum O(2^{n/2})).
- **Precomputation / lookup tables** — amortize repeated work into O(1) queries (e.g. RMQ sparse table O(n log n)/O(1)).
- **Memoization vs recomputation** — cache results (space) vs recompute (time) tradeoff in DP.
- **In-place vs auxiliary-space algorithms** — O(1) extra space variants (in-place sort, partition) vs faster O(n)-space ones.
- **Pebbling / space-bounded computation** — formal model of recomputation-vs-storage tradeoffs.
- **Bit-packing & word-level parallelism** — pack data into machine words for constant-factor speedups (bitset O(n/w)).
- **Succinct & compressed structures** — near-information-theoretic space with fast queries (see Data Structures).
- **Constant-factor optimization** — branch prediction, cache lines, SIMD; matters when asymptotics tie.
- **Hidden constants & crossover points** — when an asymptotically worse algorithm wins for practical n (e.g. insertion sort cutoff in mergesort).

### Probabilistic & Randomized Analysis Tools

- **Linearity of expectation** — E[ΣX_i] = ΣE[X_i] regardless of dependence; workhorse for expected-cost analysis.
- **Indicator random variables** — 0/1 variables to count events (e.g. expected comparisons in quicksort).
- **Markov / Chebyshev inequalities** — basic tail bounds from mean and variance.
- **Chernoff / Hoeffding bounds** — exponentially small tail bounds for sums of independent variables (high-probability runtimes).
- **Union bound** — P(∪A_i) ≤ ΣP(A_i); convert per-event failure to overall failure probability.
- **Probabilistic recurrences** — expected-cost recurrences (e.g. randomized quickselect E[T]=O(n)).
- **Coupon collector / balls-into-bins** — Θ(n log n) coverage, max-load Θ(log n/log log n) results.
- **Random-walk / martingale analysis** — drift and stopping-time arguments for randomized processes.
- **Probabilistic method (existence proofs)** — show a good structure exists because random one works with positive probability.
- **Concentration & high-probability bounds** — turning expectation into "w.h.p." guarantees for runtime/correctness.

<sub>[↑ Back to top](#table-of-contents)</sub>

---

# 🗃️ Part — Data Structures

## 2. Elementary & Linear Data Structures

### Arrays — Static & Dynamic

#### Static Arrays
- **Static (fixed-size) array** — contiguous block of equal-size cells with O(1) random access by index and O(1) update (O(n) space).
- **Index arithmetic / address computation** — element address = base + index × element-size, the basis of O(1) indexing.
- **Zero- vs one-based indexing** — choice of starting index; affects boundary handling and off-by-one bugs; competitive code often pads with a dummy slot.
- **Bounds checking** — runtime verification that an index lies in [0, n); trade-off between safety and speed.
- **Array initialization / fill** — set every cell to a constant (O(n)); language zero-init vs explicit fill.
- **Cache locality / sequential access** — contiguous layout gives prefetch-friendly linear scans, a constant-factor advantage over pointer structures.
- **Array of records (AoS) vs structure of arrays (SoA)** — memory-layout choice trading cache efficiency for code clarity in hot loops.

#### Dynamic Arrays (Resizable)
- **Dynamic array / vector** — array that grows/shrinks; O(1) amortized append, O(1) random access, O(n) insert/delete in the middle.
- **Amortized growth by doubling** — capacity ×2 (or ×1.5) on overflow gives O(1) amortized push despite occasional O(n) copies.
- **Amortized analysis (aggregate / banker's / physicist's)** — proves total cost of n appends is O(n); each push "pays" extra credit toward future copies.
- **Growth-factor trade-offs** — factor 2 vs 1.5 (golden-ratio-ish): smaller factor reuses freed blocks better, larger reduces copy frequency.
- **Geometric shrinking with hysteresis** — shrink only when size < ¼ capacity to avoid thrashing on alternating push/pop (keeps amortized O(1)).
- **Capacity vs size distinction** — reserved cells vs occupied cells; `reserve` pre-grows to avoid repeated reallocation.
- **In-place reallocation vs copy-move** — grow in place if adjacent memory is free, else allocate-and-copy.
- **Tombstone / lazy deletion in arrays** — mark slots dead instead of shifting, compacting later in one O(n) pass.

#### Multidimensional & Jagged Arrays
- **Multidimensional array** — n-D grid with O(1) indexed access; logically rectangular.
- **Row-major vs column-major order** — linearization order of multi-D arrays into 1-D memory; determines cache-friendly traversal direction.
- **Index flattening / stride computation** — map (i, j, …) to a single offset via per-dimension strides.
- **Jagged / ragged array (array of arrays)** — rows of differing length; each row a separate buffer (no single contiguous block).
- **Diagonal / triangular packed storage** — store only needed cells of symmetric/triangular matrices to save ~half the space.
- **Coordinate compression on grid indices** — remap sparse large coordinates to dense small ones (see Sorting & Order Statistics).

#### Sparse & Read-Only Arrays
- **Sparse array** — stores only nonzero/non-default entries via a map or coordinate list; O(nnz) space.
- **Sparse-set (dense + sparse pair)** — two arrays giving O(1) insert/delete/membership over a bounded universe with O(1) clear-by-counter.
- **Immutable / persistent array** — versioned array where updates create new versions sharing structure (see Advanced & Specialized Structures).
- **Difference array** — store deltas; range-add in O(1), single rebuild prefix-sum to query (O(n)) — for offline batch range updates.
- **Prefix-sum array (cumulative)** — precompute partial sums for O(1) range-sum queries after O(n) build (see Range Queries).

### Linked Lists

#### Core Variants
- **Singly linked list** — node holds value + next pointer; O(1) head insert/delete, O(n) search and indexed access.
- **Doubly linked list** — nodes carry prev and next; O(1) deletion given a node handle and bidirectional traversal (O(n) space overhead for the extra pointer).
- **Circular linked list** — last node links back to first (singly or doubly); natural for round-robin and ring scheduling.
- **Circular doubly linked list** — combines wrap-around and bidirectional links; clean O(1) splice at any position.
- **XOR linked list** — doubly linked list storing one field = address(prev) XOR address(next), halving pointer memory; needs prior-node to traverse, hard to make cursors.
- **Unrolled linked list** — each node stores a small array of elements, improving cache behavior and reducing pointer overhead.
- **Skip list** — probabilistic multi-level linked list giving O(log n) expected search/insert/delete (see Balanced Search Structures).
- **Self-organizing list** — reorders nodes on access (move-to-front, transpose, count) to exploit locality; competitive ratio bounds for MTF.

#### Sentinels & Handles
- **Sentinel / dummy head node** — non-data node so the list is never empty, removing null-check special cases on insert/delete.
- **Sentinel tail node** — symmetric end guard enabling uniform two-sided splicing.
- **Header (circular sentinel) node** — single sentinel whose next/prev close the ring, unifying empty and non-empty cases.
- **Node handle / position abstraction** — opaque reference to a node enabling O(1) operations without re-search.
- **Intrusive linked list** — link pointers embedded inside the payload object, avoiding separate node allocation and indirection.

#### Linked-List Algorithms
- **Iterative list reversal** — re-point next pointers in one pass (O(n) time, O(1) space).
- **Recursive list reversal** — reverse via recursion (O(n) time, O(n) stack).
- **Floyd's cycle detection (tortoise & hare)** — two-speed pointers detect a loop in O(n) time, O(1) space; find cycle start and length.
- **Brent's cycle detection** — alternative cycle finder, often fewer comparisons than Floyd.
- **Find middle node** — slow/fast pointer reaches midpoint in one pass.
- **Nth-from-end via two pointers** — gap-of-n technique in a single traversal.
- **Merge two sorted lists** — splice in sorted order (O(n+m)); core of bottom-up merge sort on lists.
- **Merge sort on linked lists** — O(n log n) stable sort needing no random access and O(1) extra space (bottom-up).
- **In-place list partition / rearrange** — relink nodes (e.g., odd/even, less/greater) without new allocation.
- **Detect intersection of two lists** — length-difference alignment or pointer-switch trick (O(n+m)).
- **Flatten multilevel / nested list** — splice child lists into the main chain.
- **Copy list with random pointer** — interleave-clone or hash-map mapping originals to copies (O(n)).
- **LRU cache (hash map + doubly linked list)** — O(1) get/put via map to nodes plus MTF ordering.
- **LFU cache (frequency buckets of lists)** — O(1) operations using per-frequency doubly linked lists.

### Stacks

- **Stack (LIFO)** — push/pop/peek at one end, all O(1); array-backed or linked.
- **Array-backed stack** — dynamic-array stack; amortized O(1) push via doubling.
- **Linked stack** — node-per-element; true O(1) push/pop, more memory and worse locality.
- **Bounded / fixed-capacity stack** — overflow detection on a preallocated buffer.
- **Two stacks in one array** — grow from both ends toward the middle to share a buffer.
- **Min-stack / max-stack** — track current extremum in O(1) by storing a paired min/max with each element (or a second stack).
- **Stack with O(1) middle access/delete** — combine a stack with a doubly linked list pointer to the middle.
- **Function call stack / recursion stack** — runtime frames; basis for converting recursion to iteration.
- **Explicit-stack recursion elimination** — simulate recursion with a manual stack to avoid stack overflow on deep inputs.
- **Expression evaluation (postfix/RPN)** — single stack scan evaluates reverse-Polish in O(n).
- **Shunting-yard (infix → postfix)** — Dijkstra's stack-based operator-precedence conversion (O(n)).
- **Balanced-parentheses / bracket matching** — stack verifies nesting in O(n).
- **Largest rectangle in histogram** — monotonic stack of increasing bar heights in O(n) (see Monotonic Structures).
- **Backtracking / undo stack** — store states or moves to revert; basis of editor undo.

### Queues

- **Queue (FIFO)** — enqueue at rear, dequeue at front, both O(1).
- **Linked queue** — head/tail pointers for O(1) ends.
- **Array queue with shifting (naive)** — O(n) dequeue from front shifting; usually avoided.
- **Circular array queue (ring buffer)** — wrap front/rear indices modulo capacity for O(1) ends without shifting.
- **Two-stack queue** — amortized O(1) enqueue/dequeue using an inbox and outbox stack.
- **Bounded blocking queue (conceptual)** — fixed-capacity producer/consumer FIFO (concurrency aspect; see Concurrent/Parallel domain).
- **Priority queue** — order by key, not arrival; heap-backed (see Heaps & Priority Queues).
- **Monotonic queue** — maintains sorted order for sliding-window extrema (see Monotonic Structures).
- **Min-queue / max-queue (queue with extremum)** — track min/max under enqueue/dequeue in O(1) amortized via two min-stacks.

#### Deques
- **Double-ended queue (deque)** — push/pop at both front and rear, all O(1).
- **Array (circular) deque** — ring buffer with two movable ends; amortized O(1) growth.
- **Linked deque** — doubly linked nodes for true O(1) ends.
- **Deque as stack or queue** — single structure subsuming both via end selection.
- **Output-restricted / input-restricted deque** — variants allowing insertion or removal at only one end.

#### Circular / Ring Buffers
- **Circular buffer / ring buffer** — fixed-size FIFO with wrap-around indices; O(1) push/pop, constant memory.
- **Power-of-two capacity masking** — replace modulo with bitwise AND when capacity is 2^k for fast wrap.
- **Full-vs-empty disambiguation** — separate count, one-slot-empty sentinel, or generation bits to distinguish full from empty rings.
- **Overwrite (lossy) ring buffer** — newest writes evict oldest; used for logging and streaming windows.
- **Sliding-window history buffer** — ring of the last k items for windowed statistics.

### Monotonic Structures

- **Monotonic stack** — stack kept increasing/decreasing by popping dominated elements; each element pushed/popped once (O(n) total).
- **Next greater / next smaller element** — single monotonic-stack pass yields nearest larger/smaller neighbor for all indices (O(n)).
- **Previous greater / previous smaller element** — symmetric scan in the other direction (O(n)).
- **Largest rectangle in histogram** — monotonic stack computes maximal area in O(n).
- **Maximal rectangle in binary matrix** — row-by-row histogram + monotonic stack (O(rows × cols)).
- **Stock span / daily temperatures** — count of preceding/following days via monotonic stack (O(n)).
- **Trapping rain water** — two-pointer or monotonic-stack accumulation of trapped volume (O(n)).
- **Sum/contribution of subarray minima/maxima** — monotonic stack counts each element's dominance span (O(n)).
- **Monotonic queue / deque** — deque keeping candidates ordered; front is the window extremum.
- **Sliding-window maximum / minimum** — monotonic deque evicts dominated and expired indices for O(n) total over all windows.
- **Monotonic-deque DP optimization** — speeds up 1-D DP with a windowed min/max transition from O(nk) to O(n) (see Dynamic Programming).
- **Constant-time-extremum stack/queue (min-stack pair)** — auxiliary monotonic stack tracks running min for O(1) queries.

### Strings as Linear Structures

- **String as immutable byte/char array** — fixed sequence with O(1) indexing; mutation creates a new buffer.
- **Mutable string buffer / builder** — amortized O(1) append via dynamic-array growth, avoiding O(n²) concatenation.
- **Character encoding awareness (bytes vs code points)** — byte indexing vs Unicode code-point/grapheme indexing; affects length and slicing semantics.
- **Substring / slice views** — share underlying buffer with offset+length to avoid copying (O(1) slice).
- **Rope (string as balanced tree of chunks)** — O(log n) concatenation, split, and indexed edit for huge editable text (see Advanced & Specialized Structures).
- **Gap buffer** — text-editor buffer with a movable gap giving O(1) localized insert/delete around the cursor.
- **Piece table** — editor representation as a sequence of spans over original + add buffers; cheap edits and undo.
- **String matching / hashing / suffix structures** — pattern search and substring queries (see String Algorithms).

### Iterators & Sequence Abstractions

- **Iterator / cursor** — abstract sequential-access handle decoupling traversal from container layout.
- **External vs internal iteration** — caller-driven `next()` pulling vs container-driven callback pushing.
- **Forward / bidirectional / random-access iterator categories** — capability tiers governing which algorithms apply.
- **Lazy / generator sequences** — produce elements on demand, enabling infinite or streamed sequences with O(1) memory.
- **Iterator invalidation** — structural mutation can break outstanding cursors; rules differ by container.
- **Range / view abstractions** — composable lazy transformations (map/filter/take) without materializing intermediates.
- **Sentinel-terminated ranges** — end marked by a sentinel value rather than a stored length.
- **Two-pointer traversal** — coordinated indices over one or two sequences for O(n) scans (see Two Pointers / Sliding Window).
- **Fast/slow pointer traversal** — different-speed cursors for midpoint and cycle problems.

### Association Lists & Linear Maps

- **Association list (alist)** — list of key–value pairs; O(n) lookup, trivial to build; good for tiny maps.
- **Parallel arrays as a map** — keys and values in index-aligned arrays; cache-friendly small dictionary.
- **Linear-scan dictionary** — unsorted array of pairs with O(n) search, O(1) append; beats hashing for very small n.
- **Sorted-array map (binary search)** — O(log n) lookup, O(n) insert; ideal for static/read-mostly key sets.
- **Move-to-front association list** — self-organizing alist exploiting access locality.
- **Hash table / hash map** — expected O(1) average lookup (see Hashing & Hash Tables).

### Memory Management for Linear Structures

- **Free list** — singly linked chain of recycled blocks for O(1) allocate/free of fixed-size nodes.
- **Memory pool / object pool / arena** — preallocate a big block and hand out slots, eliminating per-node malloc overhead and improving locality.
- **Slab / fixed-block allocator** — pool specialized to one object size with O(1) alloc/free.
- **Bump / linear allocator** — allocate by advancing a pointer; free everything at once (O(1) alloc, bulk reset).
- **Index-based "pointers" (node array + integer links)** — store nodes in an array and link by integer index, avoiding raw pointers and easing serialization/persistence.
- **Handle / generation-index allocator** — slot index + generation counter detects use-after-free of recycled slots.
- **Embedded free list (union of node and next-free)** — overlay the free-link onto a freed node's storage for zero overhead.
- **Reference counting (linear-structure lifetime)** — per-node count for sharing/freeing (see Memory/GC topics in other domains).

### Core In-Place & Linear-Scan Techniques

- **In-place reversal** — swap symmetric pairs of an array in O(n) time, O(1) space.
- **Array rotation by k** — three-reversal trick or juggling (cyclic GCD) rotates in O(n) time, O(1) space.
- **In-place partition (Lomuto / Hoare)** — rearrange around a pivot in one pass; core of quickselect/quicksort.
- **Dutch national flag (3-way partition)** — segregate three categories in one O(n) pass, O(1) space.
- **In-place array compaction / stream filtering (read-write pointer)** — keep wanted elements via two indices in O(n).
- **Remove duplicates from sorted array (in place)** — write-pointer dedup in O(n).
- **Cyclic-sort / placement by value** — put value v at index v in O(n) to find missing/duplicate numbers without extra space.
- **In-place merge** — merge two sorted runs without a buffer (rotation-based, O(n log n)) — niche but space-optimal.
- **Two-pointer in-place transforms** — move-zeros, segregate even/odd, etc., in O(n)/O(1).
- **In-place matrix transpose / rotate** — rotate or transpose a square matrix using swaps, O(1) extra space.
- **Prefix/suffix product without division** — fill answer array via two linear passes (O(n) time, O(1) extra beyond output).
- **Boyer–Moore majority vote** — single-pass O(n)/O(1) candidate for an element occurring > n/2 times.
- **Kadane's algorithm** — running max-subarray sum in one linear pass, O(1) space.
- **Reservoir sampling (stream over linear data)** — uniform k-sample of an unknown-length stream in one pass (see Randomized Algorithms).

<sub>[↑ Back to top](#table-of-contents)</sub>

---

## 3. Hashing & Hash-Based Structures

### Foundations: Hash Functions

#### Concept & Properties
- **Hash function** — deterministic map from arbitrary-size keys to a fixed-range integer (the bucket/slot index), ideally distributing keys uniformly (O(1) expected evaluation).
- **Hash code vs. compression** — two-stage view: map key → large integer (hash code), then reduce to table range via a compression function (modulo or multiply-shift).
- **Uniformity (simple uniform hashing assumption)** — idealized model where each key is equally likely to land in any slot independently; basis for average-case O(1) analysis.
- **Determinism & referential transparency** — equal keys must always produce equal hashes; required for correctness of any hash table.
- **Avalanche effect** — flipping one input bit flips ~half the output bits; a quality target for mixing/finalizer functions.
- **Strict avalanche criterion (SAC)** — each output bit flips with probability 1/2 when any single input bit flips; stronger uniformity guarantee.
- **Collision** — distinct keys mapping to the same slot; unavoidable by pigeonhole when key space exceeds table size.
- **Birthday paradox bound** — collisions become likely after ~√m insertions into m slots; drives sizing of hash ranges (e.g. 2^64 vs 2^128).
- **Hash-code stability across runs** — fixed-seed vs randomized-seed hashing; randomization needed to resist adversarial inputs.

#### Compression / Range Reduction
- **Division (modulo) method** — index = hashcode mod m; pick m prime, avoid powers of two and values near powers of two to dodge clustering (O(1)).
- **Multiplication method** — fractional part of (key·A) scaled to m; A ≈ (√5−1)/2 (Knuth) spreads keys well, m may be a power of two (O(1)).
- **Fibonacci hashing** — multiplication-method special case using the golden-ratio constant 2^w/φ; fast shift-based reduction with good spread (O(1)).
- **Multiply-shift (Dietzfelbinger)** — universal compression: (a·x mod 2^w) >> (w−L); single multiply + shift, provably universal (O(1)).
- **Mod-by-prime vs mask-and-power-of-two** — tradeoff between division cost and clustering resistance.
- **Fastrange / Lemire reduction** — map x into [0,n) via (x·n) >> w, avoiding modulo entirely (O(1)).

#### Hashing Composite & Domain-Specific Keys
- **Integer key mixing** — bit-mixing finalizers (xor-shift-multiply chains) to break low-entropy patterns before compression.
- **String hashing (general-purpose)** — polynomial/accumulator hashes (e.g. djb2, sdbm, FNV-1/FNV-1a) folding bytes into a running value (O(len)).
- **Tuple / pair / record hashing** — combine field hashes via mixing (boost-style hash_combine, multiply-xor) avoiding naive XOR symmetry pitfalls.
- **Sequence / container hashing** — order-sensitive folding for arrays/lists; commutative folding for sets/multisets.
- **Floating-point key hashing** — normalize ±0, NaN canonicalization before hashing to preserve equality semantics.
- **Enum / categorical key hashing** — direct or perfect-mapped small-domain keys.

#### Named General-Purpose Hash Algorithms (non-cryptographic)
- **FNV-1 / FNV-1a** — multiply-by-prime then XOR byte stream (or XOR then multiply); simple, decent distribution (O(len)).
- **djb2 / sdbm** — classic shift-and-add string hashes; fast but weak avalanche (O(len)).
- **MurmurHash2 / MurmurHash3** — multiply-rotate-XOR mixing with 32/128-bit variants; strong avalanche, fast (O(len)).
- **CityHash / FarmHash** — Google hashes optimized for short and long strings on 64-bit CPUs (O(len)).
- **xxHash / XXH3** — extremely fast streaming hash, near memory-bandwidth throughput (O(len)).
- **SipHash** — keyed PRF-style hash designed to resist hash-flooding DoS; default in many language runtimes (O(len)).
- **wyhash** — fast multiply-folding hash with good statistical quality (O(len)).
- **Pearson hashing** — table-lookup byte hash producing small outputs; simple and cache-friendly (O(len)).
- **CRC as a hash** — cyclic-redundancy checks repurposed as hashes; good bit-diffusion for error-detection origins (O(len)).
- **Cryptographic hashes (MD5/SHA-family/BLAKE)** — collision-resistant but slow; used when adversarial integrity matters (see Number Theory / Cryptography).

### Collision Resolution

#### Separate Chaining
- **Separate chaining (linked lists)** — each slot holds a list of colliding entries; expected O(1+α) ops where α is load factor.
- **Chaining with dynamic arrays/vectors** — cache-friendlier buckets than pointer lists at cost of resize churn.
- **Chaining with balanced trees (treeification)** — convert long buckets to a balanced BST to bound worst case at O(log n) under adversarial collisions.
- **Coalesced hashing** — store overflow inside the table itself, linking colliding entries through an embedded "next" index; hybrid of chaining and open addressing.
- **Bucket/array hashing** — fixed small buckets per slot; predictable layout, overflow handling needed.

#### Open Addressing (closed hashing)
- **Open addressing overview** — store all entries in the table array; on collision, probe a deterministic sequence of slots (load factor must stay < 1).
- **Linear probing** — probe slots h, h+1, h+2, …; excellent cache locality but suffers primary clustering (O(1) expected, degrades near full).
- **Quadratic probing** — probe h+1², h+2², …; reduces primary clustering but causes secondary clustering and may not visit all slots unless m and constants chosen carefully.
- **Double hashing** — step size given by a second hash h₂(key); near-uniform probing, minimal clustering, requires h₂ coprime to m.
- **Robin Hood hashing** — on insert, displace entries with smaller probe distance ("steal from the rich"); minimizes variance of probe lengths.
- **Hopscotch hashing** — keep each key within a fixed neighborhood of its home slot using a per-slot bitmap; bounded lookups with good locality.
- **Cuckoo hashing** — two (or k) tables/hash functions; each key lives in one of k candidate slots, kicking out occupants on conflict; O(1) worst-case lookup.
- **2-choice / d-ary cuckoo hashing** — more hash functions or buckets-of-size-b raise achievable load factor toward 1 before failure.
- **Bucketized cuckoo hashing** — slots hold several entries, dramatically improving max load factor and reducing rehash frequency.
- **Stash-augmented cuckoo hashing** — small constant-size overflow stash absorbs rare insertion failures, improving success probability.
- **Random probing / uniform hashing model** — idealized fully-random probe sequence; theoretical baseline for analysis.
- **Last-come-first-served (LCFS) insertion** — variance-reduction insertion discipline for open addressing.
- **Tombstone deletion** — mark removed slots as "deleted" so probe chains stay intact; periodic compaction prevents tombstone buildup.
- **Backward-shift deletion** — for linear probing, shift subsequent entries back to fill the gap, avoiding tombstones.
- **Swiss tables / SIMD group probing** — store control bytes (metadata) in groups and scan a group with SIMD to find matches/empties in parallel (O(1), strong constants).

#### Probe-Sequence & Clustering Theory
- **Primary clustering** — contiguous runs in linear probing inflate probe lengths; key reason quadratic/double hashing exist.
- **Secondary clustering** — keys with the same home slot follow identical quadratic probe sequences.
- **Knuth's linear-probing analysis** — expected probes ≈ ½(1 + 1/(1−α)) for successful search; basis for load-factor limits.
- **Expected-probe formulas (open addressing)** — unsuccessful ≈ 1/(1−α), successful ≈ (1/α)·ln(1/(1−α)) under uniform hashing.

### Load Factor, Resizing & Dynamic Tables
- **Load factor α = n/m** — ratio of entries to slots; governs collision rate and probe length.
- **Rehashing / table resizing** — allocate a larger table and reinsert all entries when α exceeds a threshold; amortized O(1) per insertion.
- **Geometric growth (doubling) & amortization** — doubling capacity makes total reinsertion cost amortize to O(1) per op; potential/accounting argument.
- **Shrinking policy & hysteresis** — shrink only well below the grow threshold to avoid oscillation (thrashing) around a boundary.
- **Incremental / amortized-online rehashing** — migrate a few entries per operation to bound worst-case latency (used in real-time systems and Redis).
- **Capacity selection** — prime sizes (for division method) vs powers of two (for mask/multiply-shift); each pairs with a compression strategy.
- **Tombstone-aware resizing** — trigger rehash on (live + deleted) occupancy, not just live count, in open addressing.
- **Reservoir presizing** — reserve capacity up front when final size is known to skip intermediate rehashes.

### Randomized & Theoretical Hashing

#### Universal & Independent Hashing
- **Universal hash family** — family H where Pr[h(x)=h(y)] ≤ 1/m for x≠y over random h∈H; bounds expected collisions for any input.
- **Carter–Wegman construction** — h(x)=((a·x+b) mod p) mod m with random a,b and prime p>universe; classic universal family (O(1) eval).
- **k-wise independent hashing** — any k keys hash independently/uniformly; polynomial-of-degree-(k−1) mod prime construction; needed by sketches.
- **Strongly universal (2-independent) hashing** — pairs map to uniform independent pairs; stronger than plain universality.
- **Multiply-shift universal hashing** — Dietzfelbinger's hardware-friendly 2-universal scheme with a single multiply and shift.
- **Polynomial hashing for independence** — degree-(k−1) polynomials over a prime field give k-independence (O(k) eval).
- **ε-almost-universal / almost-XOR-universal families** — relaxed collision bound ε; sufficient for MAC-style and sketch applications.

#### Tabulation Hashing
- **Simple tabulation hashing** — split key into c bytes, XOR per-byte random table lookups; 3-independent, very fast, surprisingly strong in practice (O(c) eval).
- **Twisted tabulation** — adds a derived character to boost independence and concentration guarantees beyond simple tabulation.
- **Mixed / double tabulation** — composing tabulation layers achieves higher independence (provably high moments) cheaply.
- **Tabulation for linear probing** — simple tabulation proven to give expected-constant linear-probing performance despite low independence.

#### Perfect Hashing
- **Perfect hash function** — collision-free map for a fixed static key set; O(1) worst-case lookup, no probing.
- **Minimal perfect hashing (MPHF)** — bijection from n keys onto exactly {0,…,n−1}; no wasted slots, ~2–3 bits/key achievable.
- **FKS two-level perfect hashing** — outer universal hash to buckets, inner per-bucket quadratic-size perfect hash; O(n) space, O(1) worst-case lookup.
- **CHD / BPZ (hypergraph-peeling) MPHFs** — construct MPHFs via random hypergraph acyclicity / peeling; near-optimal bits per key.
- **BDZ / MWHC construction** — assign values on a random k-uniform hypergraph so each key's slots XOR/sum to its rank; standard MPHF method.
- **RecSplit** — recursive splitting MPHF reaching close to the 1.44 bits/key information-theoretic lower bound.
- **Order-preserving MPHF** — perfect hash that also preserves a specified key ordering.
- **Dynamic perfect hashing** — supports insert/delete with O(1) worst-case lookup and amortized-expected updates via periodic FKS rebuilds.
- **Cuckoo-based static perfect hashing** — use cuckoo placement to derive a perfect assignment for a known key set.

### Hash-Based Containers (CP usage)
- **Hash map / dictionary / associative array** — key→value store with expected O(1) insert/find/erase; workhorse for memoization and lookups.
- **Hash set** — membership structure; expected O(1) contains/insert/erase.
- **Multimap / multiset (hashed)** — multiple values per key / duplicate keys with count tracking.
- **Frequency / count map** — key→occurrence-count map for tallying, mode-finding, anagram grouping (O(n) build).
- **Ordered vs unordered tradeoff** — hash containers give O(1) average but no ordered iteration; balanced trees give O(log n) ordered (see Balanced BSTs / Order-statistics).
- **Two-pointer + hash set patterns** — distinct-elements-in-window, longest-substring-without-repeat, two-sum/k-sum via complement lookup (O(n)).
- **Hash-based memoization** — cache subproblem results keyed by state tuple in DP/recursion (O(states) total).
- **Coordinate compression via hashing** — map sparse large coordinates to dense indices using a hash map (O(n)) (see also sorting-based compression in Sorting).
- **Index map / inverse map** — value→position lookup enabling O(1) locate in problems like O(1)-randomized-set.
- **Open-addressing hash table for speed** — custom flat tables (e.g. linear-probing with reserve) to beat library overhead in tight CP limits.
- **Custom hash to defeat anti-hash tests** — wrap library map keys with a randomized mixer/seed to avoid worst-case O(n) blowups on adversarial inputs.
- **LRU cache (hash map + linked list)** — O(1) get/put with recency eviction (see Linked Lists / caching).
- **Disjoint-set via hash map parent table** — DSU keyed by arbitrary (non-dense) labels using a hash map (see Union-Find).

### Rolling & Polynomial String Hashing

#### Core Technique
- **Polynomial hash of a string** — H = Σ s[i]·p^i mod m; assigns each string a number with collision probability ≈ 1/m (O(len) build).
- **Prefix-hash precomputation** — store H[i]=hash of prefix to enable O(1) substring-hash queries after O(n) preprocessing.
- **Substring hash from prefix hashes** — combine two prefix hashes with a power-of-p factor to extract any substring's hash in O(1).
- **Rolling update (sliding window)** — update hash when window shifts by removing the outgoing char and adding the incoming one (O(1) per shift).
- **Base (p) selection** — prime base larger than alphabet size (e.g. 31, 131, random base) to reduce structured collisions.
- **Modulus (m) selection** — large prime (e.g. 1e9+7, 1e9+9) or a Mersenne-like prime; balance collision rate vs overflow.
- **Forward vs reverse hashing** — hash a string and its reverse to test palindromes in O(1) per query.
- **Hashing for comparison without storing strings** — equality/ordering proxies via numeric hashes (with verification on match).

#### Robustness & Variants
- **Double / multiple hashing** — maintain two independent (base,mod) pairs so collision probability drops to ≈ 1/(m₁·m₂); standard CP safety.
- **128-bit / 64-bit composite hash** — pack two 32/64-bit hashes into one wide value for fast comparison and lower collision risk.
- **Randomized base/mod selection** — choose parameters at runtime (random seed) to thwart precomputed anti-hash inputs.
- **Mersenne-prime modulus (2^61−1)** — enables fast multiplication via 128-bit or __int128-style reduction with very low collision probability.
- **Hashing with modular arithmetic only (avoid 2^64 overflow)** — never rely on implicit 64-bit overflow as the modulus; Thorup–Pătrașcu-style attacks defeat it.
- **Treap/segment-tree of hashes** — maintain a hash over a dynamic sequence supporting updates and concatenation (see Balanced BSTs / Segment Trees).
- **Mo's algorithm with hashing** — offline distinct/structure queries using hashed signatures (see Offline query techniques).

#### Applications of Rolling Hash
- **Rabin–Karp substring search** — rolling hash to find a pattern's occurrences; O(n+m) average, O(nm) adversarial worst case.
- **Multiple-pattern Rabin–Karp** — hash all patterns of equal length into a set, scan once (O(n + total pattern length) average).
- **Longest common substring via hashing + binary search** — binary search the length, hash all substrings of that length, intersect sets (O(n log n) average).
- **Longest common prefix / extension by hashing** — binary search the LCP length using O(1) substring-hash equality (O(log n) per query).
- **Palindrome checks & counting** — compare forward/reverse substring hashes in O(1); count or test palindromic substrings.
- **String periodicity / smallest period** — detect periods by comparing shifted substring hashes (O(n) or O(n log n)).
- **Distinct substrings counting (hash + sort/set)** — hash every substring of each length and dedup (O(n² log n) baseline; suffix structures are better).
- **2D pattern matching (matrix hashing)** — hash rows then columns (double rolling hash) to find a 2D pattern in a grid (O(R·C) average).
- **Tree hashing / subtree isomorphism (Merkle-style)** — hash subtrees bottom-up to compare/canonicalize tree shapes (O(n)) (see Trees).
- **Suffix-array construction acceleration** — hashing + binary search to compare suffixes in O(log n) (alternative to O(n log n) doubling).
- **Anagram / sorted-signature grouping** — group strings by a frequency-vector or sorted-character signature (hashing avoids O(k log k) sort) (O(total length)).
- **De Bruijn / k-mer hashing** — fixed-length window hashing for bioinformatics-style substring sets (O(n)).
- **Hash + binary search "Z/border" substitutes** — emulate suffix-automaton queries when only hashing is available.

#### Anti-Hash Tests & Hash DoS
- **Anti-hash test** — adversarial input crafted to force many collisions, blowing single-mod polynomial hashing or a known hash map to O(n) per op.
- **Thue–Morse / Mod-collision constructions** — sequences engineered to collide under fixed-base, fixed-mod (especially 2^64) polynomial hashes.
- **Hash-flooding DoS** — attacker sends keys all colliding in a hash map, degrading it to O(n) per insert and hanging the program.
- **Defenses** — randomized seed/base/mod chosen at runtime, double hashing, SipHash-style keyed hashing, or tree-backed buckets (treeification).
- **Birthday-attack collision search** — generating two distinct inputs with equal hash in ~√m attempts; motivates wide/double hashes.
- **Verification on equality (no trust)** — when correctness is critical, confirm a hash match with a direct comparison to eliminate false positives.

### Zobrist Hashing
- **Zobrist hashing** — XOR of per-(piece,position) random 64-bit keys to hash a board/game state incrementally (O(1) per move update).
- **Incremental update by XOR** — applying/undoing a move toggles the relevant random keys, recomputing the state hash in O(1).
- **Set / multiset hashing via XOR or sum** — order-independent hash by XOR-ing (or summing) element hashes; supports O(1) add/remove (subset/symmetric-difference friendly).
- **Transposition tables** — cache game-tree node evaluations keyed by Zobrist hash to prune re-searched positions (see Game Theory / search).
- **Zobrist for grid/state dedup** — fast equality of large configurations (puzzles, automata) by comparing single hashes.
- **Collision handling in Zobrist tables** — store a verification key or full state to detect the rare 64-bit collision.
- **Dynamic connectivity / multiset equality (XOR-hash)** — random labels XORed to test whether two collections are equal (Monte Carlo).

### Probabilistic / Streaming Hash Structures

#### Membership Filters
- **Bloom filter** — k hash functions set/test bits in an m-bit array; no false negatives, tunable false-positive rate, no deletion (O(k) per op).
- **Optimal Bloom parameters** — k = (m/n)·ln2 minimizes false positives ≈ (1−e^(−kn/m))^k; design formula for sizing.
- **Counting Bloom filter** — replace bits with small counters to support deletion at extra space cost (O(k) per op).
- **Scalable / dynamic Bloom filter** — chain growing filters to bound false-positive rate as element count is unknown.
- **Blocked Bloom filter** — confine each element's bits to one cache line for fast queries at slight accuracy loss.
- **Cuckoo filter** — store small fingerprints in a cuckoo table; supports deletion, better locality, lower space than Bloom at low FPR (O(1) amortized).
- **Quotient filter** — store fingerprint quotients with metadata bits in a compact open-addressed array; cache-friendly, mergeable, deletable.
- **Xor filter / Binary fuse filter** — static, immutable membership filters using XOR-satisfying assignments; ~1.23·n bytes, faster/smaller than Bloom (O(1) query).
- **Ribbon filter** — solve a sparse linear system over GF(2) for very space-efficient static filters near the information-theoretic bound.
- **Bloomier filter** — generalize Bloom to associate values (a function) with keys, not just membership.
- **Partitioned / counting cuckoo variants** — bucketized fingerprint filters trading space for higher load and deletion support.

#### Frequency & Cardinality Sketches
- **Count-Min sketch (CMS)** — d×w counter array with d hash rows; estimate counts with one-sided (over)error; supports heavy hitters (O(d) per update).
- **Count-Min with conservative update** — increment only the minimum counters to sharply reduce overestimation in practice.
- **Count-Sketch (AMS-family)** — uses ±1 sign hashes for unbiased frequency estimates and L2/heavy-hitter queries (O(d) per update).
- **AMS sketch (Alon–Matias–Szegedy)** — randomized estimator of frequency moments (e.g. F₂) in one streaming pass (sublinear space).
- **HyperLogLog (HLL)** — estimate distinct-element count from the max leading-zero run of hashed values across registers; ~1.04/√m relative error (O(1) per add).
- **HyperLogLog++** — bias correction and sparse representation improving accuracy at low and mid cardinalities.
- **LogLog / SuperLogLog** — earlier cardinality estimators; HLL refines their register-averaging idea.
- **Linear counting** — bit-array distinct-count estimator, accurate for small cardinalities (often combined with HLL for low ranges).
- **KMV (k-minimum values) / bottom-k sketch** — track the k smallest hash values to estimate cardinality and support set operations.
- **HLL mergeability & set ops** — union via register-wise max; enables distributed/streaming cardinality aggregation.
- **Theta sketch** — KMV-style sketch supporting union, intersection, and difference with error bounds.
- **Frequent / Misra–Gries (Space-Saving) counters** — deterministic heavy-hitter tracking with bounded counters (hash-indexed) (O(1) amortized).
- **Reservoir & weighted sampling with hashing** — hash-based sampling (e.g. min-hash sampling) for uniform stream samples.

#### Similarity & Locality-Sensitive Hashing
- **Locality-sensitive hashing (LSH)** — hash families where similar items collide with high probability; enables sublinear approximate nearest-neighbor search.
- **MinHash** — estimate Jaccard similarity of sets via probability that minimum hash values agree (O(k) per signature element).
- **b-bit MinHash** — keep only low bits of each MinHash to shrink signatures with bounded accuracy loss.
- **SimHash** — sign of random hyperplane projections gives a fingerprint where Hamming distance approximates cosine/angular similarity.
- **Random-hyperplane (cosine) LSH** — hash by sign of dot products with random vectors for angular distance.
- **p-stable / Euclidean LSH** — project onto random Gaussian directions and bucket by quantized value for L2 nearest neighbors.
- **Hamming-distance LSH (bit sampling)** — sample random coordinates so collision probability scales with bit similarity.
- **LSH amplification (AND/OR banding)** — combine multiple hashes (bands × rows) to tune the precision/recall (S-curve) tradeoff.
- **Cross-polytope / spherical LSH** — near-optimal angular-distance LSH on the unit sphere.
- **MinHash signature matrix / banding** — group MinHash signatures into bands so candidate pairs share a band (document dedup, plagiarism).

### Distributed & Systems Hashing
- **Consistent hashing** — map nodes and keys onto a hash ring; a node's failure/addition remaps only ~1/n of keys (O(log n) lookup with sorted ring).
- **Virtual nodes (vnodes)** — assign each physical node many ring points to balance load and smooth redistribution.
- **Rendezvous (highest-random-weight, HRW) hashing** — pick the node maximizing hash(key,node); minimal disruption without an explicit ring (O(n) per lookup).
- **Jump consistent hash** — allocate a key to one of n buckets with O(1) time, O(1) memory, and minimal moves on resize.
- **Maglev hashing** — build a fast lookup table giving even load and minimal disruption for load balancers.
- **Anchor hashing** — consistent hashing variant with low memory and minimal key movement on bucket changes.
- **Sharding / partitioning by hash** — distribute keys across shards by hash(key) mod shards; rebalancing cost motivates consistent hashing.
- **Hash-based deduplication / content-addressed storage** — identify/store blocks by their content hash (Merkle DAGs, rsync rolling-hash chunking).
- **Content-defined chunking (rolling hash)** — split data streams at boundaries where a rolling hash matches a mask, enabling shift-resistant dedup.
- **Merkle trees / hash trees** — tree of hashes for efficient integrity verification and partial proofs (O(log n) proof size) (see Trees / Cryptography).
- **Distributed hash table (DHT)** — peer-to-peer key lookup overlay (Chord, Kademlia) built on consistent/XOR-metric hashing (O(log n) hops).

### Specialized & Advanced Topics
- **Hashing for graph/state canonicalization** — hash canonical forms to dedup isomorphic states in BFS/DFS search (with collision verification).
- **Rolling polynomial hash on trees (Euler tour)** — hash subtree/path signatures over a flattened tree for O(1) comparisons (see Trees / Euler tour).
- **Hashing + Mo's algorithm for distinct counts** — maintain hashed multiset signatures across query reordering (see Offline queries).
- **Karp–Rabin fingerprinting (theory)** — random prime modulus fingerprints with provable low collision probability for string equality.
- **Polynomial identity / Schwartz–Zippel fingerprinting** — randomized equality testing of expressions via random evaluation (hash-like) (see Randomized Algorithms).
- **Feature hashing (the "hashing trick")** — map features to indices via a hash to bound dimensionality without a dictionary (ML preprocessing).
- **Geometric / spatial hashing** — bucket points by grid cell hash for O(1) average neighbor queries in collision detection and range scans.
- **Hashing for cycle detection (Floyd/Brent vs hash set)** — store visited states in a hash set to detect repetition in functional iteration (O(cycle length)).
- **Invertible Bloom Lookup Table (IBLT)** — set-reconciliation structure that can list/recover its contents under bounded load (key-value Bloom variant).
- **Minhash/LSH for clustering & near-duplicate detection** — pipeline combining signatures and banding for large-scale similarity grouping.
- **Cryptographic vs non-cryptographic choice** — adversarial integrity (SHA/BLAKE/SipHash) vs raw speed (xxHash/Murmur); pick by threat model.

<sub>[↑ Back to top](#table-of-contents)</sub>

---

## 4. Priority Queues, Heaps & Balanced Search Trees

### Heap Fundamentals & the Priority Queue ADT

- **Priority queue ADT** — abstract container supporting insert, find-min/max, and extract-min/max; backbone of greedy, scheduling, and shortest-path algorithms.
- **Heap order property** — every node's key dominates (≤ for min-heap, ≥ for max-heap) its children's keys; root holds the extremum.
- **Min-heap vs max-heap** — dual orderings; a min-heap simulates a max-heap by negating keys or inverting the comparator.
- **Mergeable (meldable) priority queue** — extends the ADT with an efficient meld of two heaps; distinguishes "advanced" heaps from the array binary heap.
- **Monotone priority queue** — specialized PQ where extracted keys are non-decreasing (e.g., Dijkstra), enabling bucket/radix implementations (see Graphs).
- **Addressable vs non-addressable heaps** — addressable heaps return handles permitting later decrease-key/delete; array binary heaps are non-addressable without an index map.
- **Stability & tie-breaking** — PQs are not stable by default; encode insertion order in the key to enforce FIFO among equal priorities.
- **Operation menu** — make-heap, insert, find-min, extract-min, decrease-key, delete, and meld; different heaps optimize different subsets of these.

### Binary Heap

- **Implicit array representation** — complete binary tree stored in an array; children of index i at 2i+1, 2i+2 (0-based), parent at (i−1)/2, no pointers needed.
- **Sift-up (bubble-up / percolate-up)** — restore heap order after insertion by swapping a node upward toward the root (O(log n)).
- **Sift-down (bubble-down / percolate-down / heapify-node)** — restore order after root removal or key increase by swapping downward to the smaller child (O(log n)).
- **Insert** — append at the end, then sift-up (O(log n) worst, O(1) amortized for random inputs).
- **Extract-min/max** — return root, move last element to root, sift-down (O(log n)).
- **Peek / find-min** — read the root in O(1).
- **Build-heap (Floyd's heapify)** — sift-down all internal nodes bottom-up; runs in O(n), not O(n log n), via the tighter Σ heights bound.
- **Decrease-key / increase-key** — adjust a key then sift in the appropriate direction (O(log n)); requires a position-tracking index map for addressability.
- **Delete arbitrary element** — set key to −∞ and extract, or replace with last element and sift both ways (O(log n)).
- **Heapsort** — build-heap then repeatedly extract-max into the array tail; in-place, O(n log n), not stable.
- **Merge of two binary heaps** — concatenate arrays and re-heapify in O(n+m); no sublinear meld (a fundamental limitation).
- **Bottom-up sift-down (Floyd's trick)** — sink to a leaf along the min-child path, then sift the value back up; halves comparisons in practice.
- **Lazy deletion in a binary heap** — keep a "removed" multiset/counter and skip stale tops on pop; simplifies deletions when only top access is needed.
- **Two-heap median maintenance** — a max-heap of the lower half and min-heap of the upper half track a running median in O(log n) per update.

### d-ary Heap

- **d-ary heap** — generalizes the binary heap to d children per node; children at di+1..di+d, parent at (i−1)/d.
- **Complexity trade-off** — insert/decrease-key become O(log_d n) but extract-min costs O(d·log_d n); larger d favors decrease-key-heavy workloads (e.g., dense-graph Dijkstra/Prim with d ≈ m/n).
- **Cache efficiency** — wider, shallower trees with d=4 or 8 improve memory locality and reduce cache misses versus binary heaps (the "4-heap" reports large speedups).

### Mergeable Heaps (Meldable Priority Queues)

#### Binomial Heap

- **Binomial tree B_k** — recursively defined tree of 2^k nodes and height k; B_k is two B_{k−1} linked, with root degree k.
- **Binomial heap structure** — forest of binomial trees with at most one tree of each order, mirroring the binary representation of n.
- **Meld (union)** — merge root lists by order and carry-link equal-order trees like binary addition (O(log n)).
- **Insert** — meld a singleton heap; O(log n) worst, O(1) amortized.
- **Find-min** — scan O(log n) tree roots (or cache a min pointer for O(1)).
- **Extract-min** — remove min root, reverse its children into a new heap, meld (O(log n)).
- **Decrease-key** — sift up within its binomial tree (O(log n)).
- **Delete** — decrease-key to −∞ then extract-min (O(log n)).
- **Lazy binomial heap** — defer consolidation: lazy meld/insert just concatenate root lists in O(1) amortized, and the carry-linking "cleanup" is performed only during extract-min; the conceptual stepping stone from binomial to Fibonacci heaps.

#### Fibonacci Heap

- **Fibonacci heap structure** — lazy forest of heap-ordered trees with a min-pointer; trees consolidated only during extract-min.
- **Amortized bounds** — insert, find-min, meld, decrease-key all O(1) amortized; extract-min and delete O(log n) amortized.
- **Cascading cuts** — marking nodes that lost one child; a second loss cuts the node to the root list, capping tree degree at O(log n).
- **Consolidation** — after extract-min, link roots of equal degree until degrees are distinct, using a degree-indexed array.
- **Maximum degree bound D(n) = O(log n)** — proved via Fibonacci numbers: a degree-k node has size ≥ F_{k+2}, hence the name.
- **Role in Dijkstra/Prim** — yields O(E + V log V) shortest paths/MST by exploiting O(1) decrease-key (see Graphs).
- **Practical caveat** — large constants and poor locality make pairing heaps or binary heaps faster in practice despite worse bounds.

#### Strict / Worst-Case Variants

- **Strict Fibonacci heap** — achieves Fibonacci-heap bounds in the worst case (not amortized) via a complex pointer structure of active/passive nodes.
- **Brodal queue** — first meldable heap with worst-case O(1) insert/meld/decrease-key and O(log n) delete-min; theoretically optimal but intricate, largely of theoretical interest.
- **Run-relaxed & rank-relaxed heaps (weak queues)** — relaxed-balance structures giving O(1) worst-case decrease-key.
- **Rank-pairing heap** — combines Fibonacci-heap guarantees with pairing-heap simplicity using rank-based half-trees; O(1) amortized decrease-key.
- **Quake heap** — simpler decrease-key heap using tournament trees and a "seismic" rebuild when balance degrades.
- **Violation heap** — Fibonacci-like bounds with fewer pointers, tracking "violations" instead of marks.

#### Pairing Heap

- **Pairing heap** — self-adjusting multiway heap-ordered tree; the simplest practically fast addressable heap.
- **Meld / link** — attach the larger-root tree as the leftmost child of the smaller root (O(1)).
- **Insert** — meld a singleton (O(1)).
- **Extract-min** — remove root, then two-pass pairing of the children list to recombine (O(log n) amortized).
- **Two-pass pairing** — left-to-right pairwise melds, then right-to-left accumulation; the standard merge schedule.
- **Decrease-key** — cut the node's subtree and meld it back at the root; O(log n) amortized, conjectured o(log n) / O(2^{√log log n}).
- **Multipass / auxiliary-twopass variants** — alternative child-merging orders trading constants and analysis difficulty.

#### Leftist, Skew & Randomized Heaps

- **Leftist heap** — heap-ordered binary tree maintaining the "null path length" (s-value) with left child's s ≥ right child's.
- **Leftist meld** — merge right spines recursively, then swap children to restore the leftist property (O(log n)); insert/extract-min built on meld.
- **s-value (rank / null-path-length)** — distance to the nearest null descendant; bounds the right spine length at O(log n).
- **Skew heap** — self-adjusting leftist heap that unconditionally swaps children during meld, dropping the s-value bookkeeping (O(log n) amortized).
- **Top-down vs bottom-up skew meld** — two implementation styles of the recursive merge of right paths.
- **Randomized meldable heap** — heap-ordered binary tree whose meld recurses into a uniformly random child at each step; expected O(log n) per operation with trivially simple code and no balance fields.

#### Double-Ended Priority Queues

- **Double-ended priority queue (DEPQ)** — supports both find-min and find-max (and extract of each) efficiently.
- **Interval heap (twin heap)** — each node stores an interval [min, max]; left endpoints form a min-heap, right endpoints a max-heap; all ops O(log n).
- **Min-max heap** — single array with alternating min- and max-levels by depth; find-min and find-max O(1), insert/extract O(log n).
- **Deap (double-ended heap)** — two heaps (min and max) sharing a structure with a correspondence between leaves.
- **Min-max-median / generalized priority deques** — extensions giving access to additional order statistics.
- **Dual-heap correspondence technique** — pair a min-heap and max-heap with cross-pointers to make any heap double-ended.

#### Tournament / Selection Trees

- **Tournament tree (selection tree)** — complete binary tree over n players where each internal node holds the result of a "match" between its children; generalizes the heap and yields the next extremum on a leaf-to-root pass.
- **Winner tree** — each internal node stores the *winner* (smaller/greater) of its two children, with the overall winner at the root; replaying after extraction touches one root-to-leaf path (O(log n)).
- **Loser tree** — each internal node stores the *loser* of its match (winner bubbles to root); restructuring after outputting the winner examines only the leaf-to-root path, not siblings, saving comparisons in k-way merging.
- **k-way merge via tournament tree** — the classic external-sort merge engine: n leaves for n runs, each output costs one log₂ n leaf-to-root replay.

#### Cache-Aware & External-Memory PQs

- **Sequence heap** — multi-level cache-aware priority queue stacking small sorted sequences and merge groups so most work hits fast cache levels; near-optimal cache/I-O behavior for large-scale sorting and PQ workloads.
- **Buffer heap / external-memory priority queue** — block-based PQ amortizing I/O over buffered operations for inputs exceeding RAM (see External-Memory Algorithms).
- **Calendar queue / hierarchical timing wheel** — bucketed PQs for discrete-event simulation with O(1) expected enqueue/dequeue.
- **Radix / bucket heap (monotone)** — buckets keyed by value for integer monotone PQs (see Graphs: Dijkstra with bounded weights).

#### Other & Approximate Heaps

- **Soft heap** — approximate heap allowing a bounded fraction of "corrupted" (inflated) keys for O(1) amortized ops; powers the optimal-comparison MST and linear-time selection.
- **Weak heap** — relaxes heap order (only one child constrained) using reverse bits; minimizes comparisons, near-optimal for sorting.
- **Van Emde Boas tree as a PQ** — integer priority queue over a bounded universe U with O(log log U) operations (see String/Integer Structures).
- **Indexed/addressable PQ wrapper** — augment any heap with a key→position map to support decrease-key and delete-by-id.

### Heap Applications & Patterns

- **Heapsort** — see Binary Heap; the canonical in-place comparison sort via the heap.
- **Top-k / k-largest streaming** — maintain a size-k min-heap; O(n log k) selection without full sort.
- **k-way merge** — heap or tournament/loser tree of list heads merges k sorted streams in O(N log k) (external sorting, merge phase).
- **Running median / sliding-window median** — two-heap or balanced-BST/multiset approach with lazy deletion (O(log n) per step).
- **Dijkstra / Prim / Huffman** — heaps drive these greedy graph and coding algorithms (see Graphs / Greedy).
- **Event-driven simulation** — min-heap (or timing wheel) keyed by event time orders future events.
- **Lazy heaps with deletion buffer** — defer removals via a second heap or counter; pop while top is stale.

### Binary Search Trees (Foundations)

- **BST invariant** — for every node, all left-subtree keys < node key < all right-subtree keys; in-order traversal yields sorted order.
- **Search / insert** — descend comparing keys (O(h); O(n) worst on degenerate trees).
- **Deletion (three cases)** — leaf: remove; one child: splice; two children: replace with in-order successor (or predecessor) then delete that node.
- **Successor / predecessor** — next/previous key via subtree min/max or parent pointers (O(h)).
- **In-order / pre-order / post-order / level-order traversal** — enumerate keys; in-order gives sorted sequence.
- **Range / split / join semantics** — conceptual operations every balanced variant must support efficiently.
- **Degeneration to a list** — sorted insertions yield O(n) height, motivating self-balancing.
- **Optimal BST (Knuth)** — DP/Knuth-optimization building the min-expected-cost tree for known access frequencies (O(n²)) (see Dynamic Programming).
- **Tree rotations (left/right)** — local O(1) restructuring preserving BST order; the primitive underlying nearly all balancing.

### Height-Balanced BSTs

#### AVL Tree

- **AVL balance factor** — height difference of subtrees ∈ {−1, 0, +1} at every node; the strictest common balance, height ≤ 1.44 log n.
- **Rebalancing rotations** — single (LL, RR) and double (LR, RL) rotations restore balance after insert/delete (O(log n)).
- **Insert (single rotation suffices)** — at most one rotation cluster fixes an insertion imbalance.
- **Delete (rotations may cascade)** — rebalancing can propagate up to the root, costing O(log n) rotations.
- **Height tracking** — store subtree height or balance factor per node; faster search than red-black due to tighter balance.

#### Red-Black Tree

- **Red-black properties** — root/leaf black, no red-red edge, equal black-height on every root-to-leaf path; guarantees height ≤ 2 log(n+1).
- **Recoloring & rotations on insert** — fix red-red violations by recoloring and at most 2 rotations (O(log n), O(1) amortized rotations).
- **Deletion (double-black resolution)** — propagate a "double black" upward with sibling-color casework and ≤ 3 rotations.
- **Equivalence to 2-3-4 tree** — a red-black tree is an isometry of a B-tree of order 4 (red nodes merged into parent).
- **Left-leaning red-black tree** — a simplified RB variant (Sedgewick) forcing red links to lean left, reducing case count for teaching/implementation.
- **Library backbone** — basis of ordered map/set in most standard libraries due to fast updates and good worst case.

#### Weak / Relaxed AVL

- **WAVL (weak AVL / rank-balanced tree)** — unifies AVL and red-black via rank differences; O(1) amortized rebalancing with red-black-like deletes.
- **AA tree** — simplified red-black variant using a "level" field and only skew/split operations; easier to implement, single right-child reds.

### Multiway / External-Memory Search Trees

- **2-3 tree** — balanced search tree whose internal nodes hold 1 or 2 keys (2 or 3 children); all leaves at equal depth, the simplest multiway balanced tree.
- **2-3-4 tree** — balanced search tree with 1–3 keys (2–4 children) per node; insert splits 4-nodes top-down, delete fuses/borrows, all leaves equidepth — a standalone balanced tree that also models the red-black tree exactly.
- **B-tree** — balanced multiway tree of order m; each node holds up to m−1 keys and m children, height O(log_m n); minimizes disk/block accesses.
- **B-tree operations** — search descends; insert splits full nodes top-down or bottom-up; delete merges/borrows from siblings to keep ≥ ⌈m/2⌉−1 keys.
- **B+-tree** — keys duplicated in internal nodes for routing only; all values in leaves, leaves linked for efficient range scans (database/filesystem standard).
- **B*-tree** — delays splits by redistributing keys to siblings, keeping nodes ≥ 2/3 full for higher fan-out utilization.
- **Fractional cascading on B-trees / fractal trees** — buffer-based write optimization (Bε-tree, LSM-adjacent) reducing amortized I/O cost.
- **Cache-oblivious B-tree / van Emde Boas layout** — recursive memory layout achieving optimal I/O without knowing block size.

### Randomized & Self-Adjusting BSTs

#### Treap (Randomized BST)

- **Treap = tree + heap** — each node has a BST key and a random heap priority; the structure is the unique treap, with expected height O(log n).
- **Insert / delete via rotations** — insert by BST then rotate up while priority violates the heap; delete by rotating the node down to a leaf.
- **Split (by key)** — partition into trees with keys < x and ≥ x in O(log n) expected.
- **Merge (two treaps)** — combine treaps where all keys in one < all in the other, respecting priorities (O(log n) expected).
- **Implicit treap (by index)** — use subtree size as an implicit key so position replaces value; supports array operations.
- **Implicit-treap array ops** — insert/erase at index, reverse a subarray (lazy flag), range add/assign, range sum/min — all O(log n) (rope-like).
- **Persistent treap** — path-copying for immutable versions and functional split/merge (see Persistent / Functional Structures).
- **Cartesian tree** — treap built from a sequence where keys are positions and priorities are values; links to range-minimum and Euler-tour LCA (see Range Queries).
- **Zip tree** — randomized BST using "rank" tags and zip/unzip operations instead of rotations; isomorphic in distribution to a skip list.

#### Splay Tree

- **Splaying** — move an accessed node to the root via zig, zig-zig, and zig-zag double rotations, self-adjusting toward recent accesses.
- **Amortized O(log n)** — all operations are O(log n) amortized; no per-operation worst-case guarantee.
- **Access lemma / potential method** — log-size potential argument proving the amortized bound.
- **Static optimality** — within a constant of the optimal static BST for any access sequence.
- **Working-set & dynamic-finger properties** — fast access to recently or nearby-accessed keys.
- **Dynamic optimality conjecture** — splay trees may be O(1)-competitive with the offline optimal BST (open; Tango trees give O(log log n) competitiveness).
- **Split / join on splay trees** — splay the boundary key to the root, then detach/attach a subtree (amortized O(log n)).
- **Link-cut tree foundation** — splay trees over preferred paths implement dynamic-tree operations (see Trees / Advanced).

#### Scapegoat Tree

- **α-weight balance** — no rebalancing fields; a node is a "scapegoat" when a subtree violates α-weight-balance after an update.
- **Partial rebuild** — rebuild the scapegoat's subtree into a perfectly balanced BST, giving O(log n) amortized insert/delete.
- **No per-node overhead** — stores only size counters globally, trading rebuild cost for memory simplicity.

#### Weight-Balanced Tree (BB[α])

- **Weight-balanced (BB[α]) tree** — balance by subtree sizes (weights) rather than heights; rotations triggered by a weight-ratio threshold.
- **Worst-case O(log n)** — maintains logarithmic height with size-based rotations; subtree sizes available for free.
- **Join-based balancing** — weight-balance integrates cleanly with the generic join framework for parallel/persistent trees.

### Order-Statistics & Augmented Trees

- **Order-statistic tree** — BST augmented with subtree sizes to support select(k) (k-th smallest) and rank(x) in O(log n).
- **Augmentation principle** — maintain a subtree aggregate (size, sum, min/max, GCD) updated on rotation, enabling new queries.
- **rank / select** — count of keys < x, and the k-th element, via subtree-size descent.
- **Range count / range sum** — number or sum of keys in [l, r] using prefix counts (rank(r) − rank(l−1)).
- **k-th order statistic over a dynamic set** — insert/delete plus select(k); the workhorse of "indexed set" problems.
- **Interval tree (BST augmented with max-endpoint)** — store interval set, query all intervals overlapping a point/interval in O(log n + k) (see Range Queries / Geometry).
- **Segment tree / Fenwick alternatives** — array-indexed order statistics via BIT-on-values or merge-sort trees (see Range Queries).
- **Merge-sort tree / wavelet alternatives** — for static rank/select over values (see Range Queries / Strings).

### Indexed Sets & Policy-Based Structures

- **Order-statistics container ("indexed set")** — a balanced-BST-backed ordered set exposing find-by-order and order-of-key in O(log n); the practical face of order-statistic trees.
- **Indexed multiset** — order statistics with duplicates, handled by encoding a tiebreaker or storing counts.
- **Tree-on-values + offline coordinate compression** — emulate an indexed set with a Fenwick/segment tree when keys are known in advance.
- **Sorted-list / skip-list–backed ordered multiset** — alternative indexed-set implementation when a balanced BST is unavailable.

### Skip List

- **Skip list** — layered linked lists with randomized "express lanes"; expected O(log n) search/insert/delete, simpler than balanced BSTs.
- **Level generation** — geometric coin-flips assign a node's height; expected level count O(log n).
- **Search / insert / delete** — descend from the top lane, dropping levels and splicing pointers at each.
- **Indexable skip list** — store span/width counts on forward pointers for order-statistics (rank/select) in O(log n).
- **Deterministic skip list (1-2-3)** — balance gaps deterministically to remove randomness while keeping O(log n).
- **Concurrent / lock-free skip list** — pointer-based structure amenable to fine-grained concurrency (concurrent ordered maps).
- **Skip list vs balanced BST** — comparable bounds with simpler code and easier persistence; constant factors and randomness differ.

### Finger Search & Locality

- **Finger search** — search starting from a "finger" (pointer to a recently accessed element) in O(log d), where d is the rank distance to the target.
- **Finger search trees** — level-linked balanced trees (e.g., level-linked B-trees, (2,4)-trees) supporting O(log d) finger search.
- **Splay-tree dynamic finger** — splaying gives the dynamic-finger property automatically without extra fingers.
- **Finger search on skip lists** — backward/forward fingers via level-linked skip lists for O(log d) neighbor queries.

### Generic Tree Frameworks & Cross-Cutting Techniques

- **Join-based balanced trees** — express insert/delete/split/union/intersect/difference atop a single rebalancing "join"; works uniformly for AVL/RB/WB/treap.
- **Split & join (general)** — the two universal primitives; ordered-set union/intersection/difference run in O(m log(n/m + 1)).
- **Bulk / parallel set operations** — join-based union/intersection/difference parallelize and underpin parallel ordered maps.
- **Lazy propagation on BSTs** — push-down tags (range add/assign, reverse) on implicit treaps/splay trees, mirroring segment-tree lazy tags.
- **Persistence (path copying)** — immutable versioned BSTs/heaps via copying the modified root-to-node path (O(log n) extra space per update) (see Persistent Structures).
- **Euler-tour / linearization of trees** — flatten a tree to an array for BST/Fenwick subtree and path queries (see Trees / Range Queries).
- **Memory pool / arena allocation** — preallocated node arrays with integer indices avoid pointer overhead and improve cache behavior in contest code.
- **Comparator & duplicate handling** — strict-weak-ordering comparators, and storing counts vs distinct nodes for multisets.

### Lower Bounds & Theory

- **Ω(n log n) comparison-sort bound** — heapsort and BST-sort are optimal comparison sorts by the decision-tree argument.
- **Amortized analysis toolkit** — aggregate, accounting, and potential methods used to prove Fibonacci-heap, splay-tree, and scapegoat bounds.
- **Decrease-key lower bounds** — known trade-offs showing Fibonacci-heap-style O(1) decrease-key is essentially optimal in the comparison model.
- **BST competitiveness & dynamic optimality** — Tango trees (O(log log n)-competitive) and the still-open splay dynamic-optimality conjecture.
- **Soft-heap error budget** — formal guarantee that at most εn keys are corrupted, the basis of its constant-amortized operations and MST/selection applications.

<sub>[↑ Back to top](#table-of-contents)</sub>

---

## 5. Range-Query Data Structures

### Foundations & Terminology

- **Range query (general)** — answer an aggregate (sum/min/max/gcd/xor/count/...) over a contiguous index range, possibly with updates (queries/updates typically O(log n) or O(√n)).
- **Associative vs. invertible aggregates** — invertible ops (sum, xor) allow prefix-difference tricks; non-invertible (min, max, gcd) need overlap-friendly structures (affects which structure applies).
- **Idempotent operations** — ops where `f(x,x)=x` (min, max, gcd, and, or) permit overlapping coverage, enabling O(1) sparse-table queries.
- **Semigroup vs. monoid vs. group** — monoid adds an identity (needed for empty ranges); group adds inverses (enables prefix subtraction); semigroup needs non-overlapping decomposition.
- **Static vs. dynamic queries** — static = no updates after build (precompute heavy); dynamic = interleaved updates and queries.
- **Online vs. offline** — online answers each query before seeing the next; offline may reorder/batch queries to reduce cost.
- **Point vs. range update; point vs. range query** — four combinations defining the four canonical problem classes (PU-PQ, PU-RQ, RU-PQ, RU-RQ).
- **Coordinate compression** — remap sparse/large keys to dense ranks `[0,k)` so array-indexed structures apply (O(n log n) preprocess).

### Prefix Sums & Difference Arrays

#### Prefix Sums (Cumulative)

- **1D prefix sum** — precompute `P[i]=a[0]+...+a[i-1]`; range sum `[l,r)` = `P[r]-P[l]` (build O(n), query O(1)).
- **2D prefix sum (summed-area table)** — `P[i][j]` = sum of top-left rectangle; submatrix sum by 4-term inclusion-exclusion (build O(nm), query O(1)).
- **kD prefix sum** — generalize to d dimensions; query uses `2^d` inclusion-exclusion terms (query O(2^d)).
- **Prefix products / prefix xor / prefix gcd** — analogous prefixes for invertible/idempotent monoids (gcd not invertible, so prefix gcd is query-only static).
- **Prefix-of-prefix (double prefix)** — second-order accumulation for weighted/triangular range sums (e.g., `Σ i·a[i]`) in O(1).
- **Prefix sums on trees (subtree/path)** — Euler-tour / ancestor prefix sums for subtree and root-to-node aggregates (see Tree Range Structures).

#### Difference Arrays (Imos / Sweep)

- **1D difference array** — apply many range-add updates as `d[l]+=v; d[r]-=v`, then prefix-sum once to materialize (k updates O(k), finalize O(n)).
- **2D difference array** — range-add over a rectangle via 4 corner deltas, then 2D prefix sum to finalize (update O(1), finalize O(nm)).
- **kD difference array** — `2^d` corner updates per box, then d-dimensional prefix scan (update O(2^d)).
- **Imos method (line/interval sweep)** — counting overlaps / coverage by incrementing at starts, decrementing at ends (O(n+events)).
- **Second-order difference array** — supports range updates that add an arithmetic progression (linear ramp) per range via double differencing (update O(1)).
- **Difference-on-tree (subtree/path add)** — range add over subtree or path then propagate (Euler tour for subtree; partial-sums-on-vertices for path).

### Sparse Table & Idempotent RMQ

- **Sparse table (binary lifting on ranges)** — precompute answers for all `[i, i+2^k)`; idempotent query covers `[l,r]` with two overlapping blocks (build O(n log n), query O(1)).
- **Sparse table for non-idempotent ops** — works but query becomes O(log n) since overlap double-counts (only safe for idempotent without it).
- **Disjoint sparse table** — recursive midpoint split so each query splits at one level into two disjoint halves; supports any associative op in O(1) query (build O(n log n)).
- **Sparse table on 2D (idempotent)** — sparse table over rows then columns for static 2D RMQ (build O(nm log n log m), query O(1)).
- **±1 / ≤1 RMQ structure** — exploit adjacent-difference constraint to block-decompose into O(n) precompute, O(1) query (Method of Four Russians flavor).
- **Cartesian-tree + LCA RMQ** — reduce array RMQ to LCA via Cartesian tree, then LCA to ±1 RMQ for ⟨O(n), O(1)⟩ (see Cartesian Tree section).
- **Mask/lookup RMQ for small blocks** — precompute answers for all block patterns of size ~½log n to remove the log factor (Four Russians).
- **Persistent sparse table** — layer path-copy/version snapshots over the sparse-table layers so each appended-element version is queryable for static O(1) RMQ on its prefix; useful when array grows append-only and historical-prefix RMQ is needed (build O(n log n) total, query O(1) per version, space O(n log n)).

### Fenwick Tree (Binary Indexed Tree, BIT)

- **BIT core (point-update, prefix-query)** — implicit tree via lowest-set-bit jumps; prefix aggregate and point update both O(log n), space O(n).
- **Range query via prefix difference** — range `[l,r]` sum = `prefix(r) - prefix(l-1)` for group operations (O(log n)).
- **BIT with point update, range query (PU-RQ)** — the standard sum-BIT configuration (update O(log n), query O(log n)).
- **BIT with range update, point query (RU-PQ)** — store a difference array inside the BIT; range-add then point-read (both O(log n)).
- **BIT with range update, range query (RU-RQ)** — two BITs maintaining `Σd[i]` and `Σi·d[i]` to recover arbitrary range sums under range-add (O(log n)).
- **2D Fenwick tree** — nested BITs for rectangle sum / point update; inclusion-exclusion over 4 corners (update/query O(log n · log m)).
- **2D BIT with range-update range-query** — four 2D BITs extending the 1D two-BIT trick to rectangles (O(log n log m)).
- **kD Fenwick tree** — d nested BITs (update/query O(log^d n)).
- **BIT on values (frequency BIT)** — index by value rank to support order statistics and rank queries (O(log V) per op).
- **Find k-th / order statistic via binary lifting on BIT** — descend bit positions accumulating counts to locate the k-th element in O(log n) (faster than O(log^2)).
- **BIT-based rank / count-less-than** — prefix count of inserted values for inversion counting and "how many ≤ x" queries (O(log n)).
- **Offline inversion counting with BIT** — process and query frequencies to count inversions/pairs (O(n log n)).
- **Fenwick build in O(n)** — linear bottom-up construction instead of n insertions.
- **Fenwick with non-sum monoids (prefix max)** — works for prefix-direction-only idempotent queries; cannot do arbitrary-range max (no inverse) (O(log n) prefix).
- **Fenwick for range-min with point-decrease only** — restricted prefix-min BIT supporting only monotone updates.
- **Binary-indexed sqrt / fractional cascading hybrids** — niche speedups layering BIT over compressed coordinates.

### Segment Tree (Core)

- **Recursive segment tree (point update, range query)** — balanced binary tree over array segments; supports sum/min/max/gcd/xor and any monoid (build O(n), update/query O(log n), space O(2n..4n)).
- **Range query semantics** — recurse splitting `[l,r]` into O(log n) canonical nodes and combine via the monoid op.
- **Merge function flexibility** — node stores arbitrary mergeable summary (sum, min/max with index, gcd, hash, count, matrix product, affine map).
- **Storing structured node data** — pairs/tuples (e.g., max + count of max, prefix/suffix/best subarray sums for max-subarray queries) (O(log n)).
- **Max-subarray (GSS) segment tree** — node holds total/prefix-best/suffix-best/best to answer maximum-subarray over a range (O(log n)).
- **Iterative (bottom-up) segment tree** — array-indexed `[n,2n)` leaves; very fast point-update range-query without recursion (update/query O(log n)).
- **Iterative segment tree with non-commutative merge** — careful left/right ordering during the bottom-up climb to preserve operand order.
- **Walk-down / descent on segment tree** — locate first/last position satisfying a predicate (e.g., first prefix ≥ x) in O(log n) instead of O(log^2).
- **Segment tree beats prerequisite: tag pushing** — break/tag conditions controlling when to recurse vs. apply lazily (foundation for Beats).

#### Lazy Propagation

- **Lazy propagation (range update, range query)** — defer updates as pending tags pushed down on demand (range update/query O(log n)).
- **Range-add + range-sum/min/max** — additive lazy tag combined into the aggregate (O(log n)).
- **Range-assign (set) + range query** — assignment tag overrides pending add; tag composition rules matter (O(log n)).
- **Composable lazy (add then assign ordering)** — define how assign and add tags compose; assign clears pending add (O(log n)).
- **Affine / linear-map lazy (`x -> a·x + b`)** — function-composition tags for range affine update + range sum (O(log n)).
- **Range-flip / toggle lazy** — boolean lazy for range bit-flip with count-of-ones aggregate (O(log n)).
- **Lazy with size-aware aggregates** — multiply lazy add by segment length for sums; track counts for averages (O(log n)).
- **Tag-pushing correctness** — push before recursing into children, pull (recompute) after returning.

#### Segment Tree Beats

- **Segment Tree Beats (Ji Driver segment tree)** — supports range `chmin`/`chmax` (clamp) with range-sum queries by tracking max, 2nd-max, count-of-max and pruning recursion (amortized O(log^2 n) or O(log n) per op).
- **Range chmin / chmax + range sum** — the canonical Beats application (assign-to-min/max over a range).
- **Beats with additional add tags** — combine range-add, range-chmin/chmax, range-sum/max simultaneously.
- **Historic / extremal value tracking (`max` over time, sum of historic max)** — Beats-style auxiliary tags maintaining historical maxima (amortized polylog).
- **Range-GCD update via auxiliary difference (Beats-style)** — maintain the segment tree over the difference array `d[i]=a[i]-a[i-1]`; since `gcd(a[l..r]) = gcd(a[l], gcd(d[l+1..r]))`, a range-add to `a` becomes two point updates on `d`, and Beats-style pruning/tagging handles range gcd-assign or batched gcd-reduction efficiently (O(log n) amortized; query combines a prefix value with a range gcd).
- **Potential-function amortized analysis** — bounding total work via decreasing distinct-value potential (proves the amortized bound).

### Segment Tree Variants & Augmentations

- **Merge sort tree** — each node stores its segment's elements sorted; answers range "count ≤ x" / k-th-by-binary-search style queries (build O(n log n), query O(log^2 n)).
- **Merge sort tree + fractional cascading** — share sorted-order links to drop a log factor (query O(log n)).
- **Wavelet tree** — recursive bit-vector partition by value; range k-th-smallest, range rank, range frequency (build O(n log σ), query O(log σ)). (see Succinct/Bitvector structures)
- **Segment tree storing multisets/BSTs per node** — node holds a balanced BST/Fenwick for dynamic "count ≤ x in range" with updates (O(log^2 n)).
- **Segment tree with policy ordered-set per node** — supports dynamic rank/select-in-range (O(log^2 n)).
- **Kinetic / Li Chao for linear functions** — see Li Chao Tree & Kinetic Structures.
- **Beats** — see Segment Tree Beats above.

#### Dynamic / Implicit / Sparse Segment Trees

- **Dynamic (implicit) segment tree** — create nodes lazily over a huge coordinate space; only touched nodes allocated (per-op O(log C), space O(q log C)).
- **Sparse segment tree** — synonym for implicit tree over sparse/large key domain without coordinate compression.
- **Pointer-based vs. index-pool node management** — dynamic allocation strategies (pointers, node arrays, or hash maps) for implicit trees.
- **Dynamic segment tree with lazy** — lazy propagation over implicit nodes (range update on large coordinate space) (O(log C)).
- **Garbage-collected / rolling dynamic trees** — reclaim nodes to bound memory in long update streams.

#### Persistent Segment Trees

- **Persistent segment tree (fat-node / path-copy)** — each update clones only the O(log n) touched path, preserving all versions (per-version O(log n) time and space). (see Persistent & Functional data structures)
- **Version-rooted queries** — query any historical root; access prior states in O(log n).
- **Persistent BIT-on-values for k-th in range** — difference of two version roots answers k-th-smallest in `[l,r]` (build O(n log n), query O(log n)).
- **Mergeable / online-offline persistence** — combining persistence with segment-tree merge for subtree value queries.
- **Fully persistent vs. partially persistent** — branching version DAG vs. linear version history.

### Li Chao Tree & Kinetic / Convex-Hull Structures

- **Li Chao tree** — segment tree over x-domain storing the line that is optimal at each node's midpoint; supports insert-line + query-min/max at a point (insert/query O(log C)).
- **Li Chao for line segments** — restrict each inserted line to an x-interval by recursing only over covered nodes (insert O(log^2 C)).
- **Kinetic segment tree (KST) / kinetic tournament** — supports range "advance time" so node-optimal lines re-sort lazily; "melts" (recomputes) at certificate-crossover times; answers min-over-lines at moving query parameter (amortized polylog, near-linear total events for well-behaved inputs).
- **Kinetic heap** — kinetization of a heap for elements whose priorities are continuous functions of time; heap-property certificates on each edge feed an event queue that reschedules on certificate failure to track the moving maximum (per-event O(log n); local since each node sits in ≤3 certificates).
- **Kinetic data structures (KDS) framework** — general scheme: maintain combinatorial "certificates" guaranteeing the answer, schedule their failure times in an event queue, and process events as parameter (time) advances; the certificate set proves correctness between events.
- **KDS quality metrics (responsiveness, locality, compactness, efficiency)** — responsiveness = cost to fix one event, locality = max certificates per element, compactness = total certificate count, efficiency = events processed vs. necessary external changes (analysis vocabulary for kinetic structures).
- **Convex Hull Trick (monotonic)** — maintain lower/upper hull of lines for DP optimization; queries via stack/deque (O(1) amortized when slopes and queries monotone). (see DP optimizations)
- **LineContainer (dynamic CHT via balanced set)** — insert arbitrary-slope lines and query arbitrary x in O(log n) (see DP optimizations).

### Multidimensional Range Structures

- **2D segment tree (segment tree of segment trees)** — outer tree over rows, each node holding an inner tree over columns; point update O(log n log m), rectangle query O(log n log m), space O(nm).
- **2D segment tree with merge / static-build** — build inner structures bottom-up; supports rectangle aggregates (build O(nm), query O(log n log m)).
- **2D dynamic / sparse segment tree** — implicit nesting for large 2D coordinate spaces (per-op O(log C log C)).
- **2D Fenwick tree** — see Fenwick Tree (2D) (O(log n log m)).
- **kD segment tree** — d-level nesting (per-op O(log^d n)) — rarely practical beyond 2-3 dims.
- **Range tree** — multidimensional BST-of-BSTs for orthogonal range reporting/counting (build O(n log^{d-1} n), query O(log^d n + k)).
- **Range tree + fractional cascading** — shave one log from query (query O(log^{d-1} n + k)).
- **k-d tree (range/nearest)** — space-partitioning tree for orthogonal range + nearest-neighbor queries (build O(n log n), range query O(√n + k) in 2D). (see Geometry / spatial structures)
- **Quadtree / region tree** — recursive quadrant subdivision for 2D ranges and aggregates (query depends on distribution). (see Geometry / spatial structures)
- **Persistent segment tree over one axis (2D static)** — sort by one dimension, persist over the other for online rectangle counting (build O(n log n), query O(log n)).
- **BIT of sorted vectors / merge-sort tree 2D** — offline rectangle counting via sweep + BIT-on-values (O((n+q) log n)).

### Tree Range Structures (paths & subtrees)

- **Euler tour + segment tree/BIT (subtree queries)** — flatten subtree to a contiguous range `[tin, tout]`; subtree aggregate/update via range structure (O(log n)).
- **Heavy-Light Decomposition (HLD)** — decompose tree into heavy chains so any path is O(log n) contiguous segments queried via segment tree (path query/update O(log^2 n)). (see Trees / decomposition)
- **Euler tour for path queries (LCA + prefix)** — root-to-node prefix combined with LCA for path aggregates of invertible ops (O(log n)).
- **Centroid decomposition for distance/range** — answer distance-constrained aggregates by layering structures over centroid levels (O(log n) levels). (see Trees / decomposition)
- **Auxiliary / virtual tree** — compress to relevant vertices for batched tree-range queries. (see Trees)
- **Link-Cut Tree (dynamic path aggregates)** — splay-based dynamic trees for path query/update under edge insert/delete (amortized O(log n)). (see Advanced dynamic trees)
- **Top tree / ET-tree** — general dynamic-tree aggregates over paths and subtrees (amortized O(log n)). (see Advanced dynamic trees)
- **Small-to-large (DSU on tree)** — offline subtree multiset aggregates by merging smaller child sets (O(n log n)). (see Trees / offline)
- **Segment tree merging on trees** — merge per-vertex dynamic segment trees up the tree for subtree value queries (amortized O(n log n)).

### Cartesian Tree & RMQ ↔ LCA Equivalence

- **Cartesian tree** — binary tree that is a BST on indices and a heap on values; root is the array min/max (build O(n) via stack).
- **RMQ → LCA reduction** — range min index = LCA of the two positions in the Cartesian tree (O(1) after O(n) build).
- **LCA → ±1 RMQ reduction** — Euler tour of tree gives a ±1 array whose RMQ yields LCA (closes the equivalence cycle).
- **⟨O(n), O(1)⟩ RMQ (Bender–Farach-Colton)** — combine Cartesian-tree/LCA with block + Four-Russians for linear-build constant-query RMQ.
- **Treap (randomized Cartesian tree)** — implicit-key treap for split/merge sequence ops with subtree aggregates (expected O(log n)). (see Balanced BST / implicit sequence)
- **All-nearest-smaller-values via Cartesian tree / monotonic stack** — derive nearest smaller left/right in O(n).

### Sqrt-Family & Block Decomposition

- **Sqrt decomposition (block array)** — split into ~√n blocks with per-block aggregate; range query/update O(√n), point update O(1)/O(√n). (see Offline / Mo's algorithm)
- **Sqrt decomposition with lazy block tags** — per-block lazy for range-add/assign with partial-block fixups (O(√n)).
- **Block linked list / sqrt list** — rebuildable blocks supporting insert/erase in O(√n).
- **Sqrt tree** — multi-level sqrt structure giving O(1) associative range query after O(n log log n) build (update O(√n)).
- **Mo's algorithm (offline range queries)** — reorder queries by √-blocks to amortize add/remove of endpoints (O((n+q)√n)). (see Offline query techniques)
- **Mo's on trees / Mo's with updates (3D Mo)** — variants for path queries and point updates (O(n^{5/3}) for updates). (see Offline query techniques)
- **Sqrt decomposition on values (bucket by value)** — answer k-th / rank by walking value buckets (O(√V)).
- **Decremental / rebuild-every-√ batching** — periodic rebuild to bound amortized cost of hard updates.

### Bitset, Word-Parallel & Succinct Range Tools

- **Bitset range operations** — word-parallel AND/OR/XOR/popcount for range membership/coverage (O(n/word)).
- **Wavelet tree** — see Segment Tree Variants; succinct range k-th/rank/select (query O(log σ)). (see Succinct structures)
- **Wavelet matrix** — cache-friendly reordering of the wavelet tree with the same query set (O(log σ)).
- **Rank/select bit-vector** — O(1) rank/select over a static bitvector underpinning wavelet/succinct structures (space n+o(n)). (see Succinct structures)
- **RMQ via succinct ±1 / range-min trees** — 2n+o(n)-bit RMQ index (Sadakane / Fischer-Heun) (query O(1)). (see Succinct structures)

### Specialized & Advanced Range Techniques

- **Sparse / dynamic segment tree for online compression-free counting** — covered under Dynamic Segment Trees (O(log C)).
- **Color/interval segment maintenance ("Chtholly"/ODT tree)** — store maximal equal-value runs in a balanced map; range-assign collapses runs (amortized near O(n log n) under random data).
- **Interval / segment trees for stabbing & overlap (geometry)** — augmented BST for "which intervals cover point x" / overlap queries (query O(log n + k)). (see Geometry / interval structures)
- **Range mode / range majority query** — frequency structures (sqrt or wavelet-based) for most-frequent element in a range (varies; e.g., O(√n) or O(1) with O(n^2) space).
- **Range distinct-count (number of distinct values)** — offline via BIT after sorting by previous-occurrence; online via persistent segment tree (O(log n)).
- **Range GCD with updates** — store gcd of difference array so point delta = range gcd update; combine BIT(diff) for point-value reconstruction + segment tree of gcds over differences (range-add becomes two point updates) (O(log n)).
- **Range XOR + range-xor-update** — linear over GF(2); analogous two-structure trick to range-add (O(log n)).
- **Range "add arithmetic progression"** — segment tree/Fenwick with slope+intercept lazy or second-order difference (O(log n)).
- **Matrix / transition aggregate over ranges** — segment tree of matrices for range linear-recurrence/automaton composition (merge = matrix multiply) (O(k^3 log n)).
- **Monotonic-stack range structures (largest rectangle, NSV)** — precompute nearest-smaller boundaries for histogram/range extremal queries (O(n)).
- **Fractional cascading (general)** — link sorted lists across structures to answer m correlated searches in O(log n + m) instead of O(m log n).
- **Persistent / mergeable heaps for range-DP** — leftist/skew/pairing heaps supporting meld for offline range optimization (meld O(log n)). (see Heaps)
- **Offline divide-and-conquer on queries (CDQ)** — divide-and-conquer over time/index to answer dynamic multidimensional dominance queries offline (O(n log^2 n)). (see Offline / divide-and-conquer)
- **Time-segment-tree (offline dynamic connectivity / rollback)** — put each element's active interval on a segment tree over time, sweep with rollback DSU (O((n+q) log n α)). (see Offline / DSU rollback)

### Choosing & Combining Structures (Decision Guide)

- **Static + idempotent → sparse table** — O(1) query, no updates (build O(n log n)).
- **Static + any associative → disjoint sparse table / sqrt tree** — O(1) query for non-idempotent ops.
- **Invertible aggregate + point update → Fenwick** — simplest/fastest constant factor (O(log n)).
- **Non-invertible / range update → segment tree (+ lazy)** — most general dynamic structure (O(log n)).
- **Huge/sparse coordinates → implicit/dynamic or coordinate compression** — avoid O(C) memory.
- **Many historical versions / k-th-in-range → persistent segment tree** — O(log n) per version.
- **Range clamp (chmin/chmax) + sum → segment tree beats** — amortized polylog.
- **Moving/time-parametrized optima → kinetic structures (KST / kinetic heap)** — certificate-driven re-sorting as the parameter advances.
- **Offline & no efficient online structure → Mo's / sqrt / CDQ / time-segment-tree** — trade onlineness for simplicity/feasibility.
- **2D static counting → persistent segtree / merge-sort tree / offline BIT sweep** — pick by online-ness and memory.

<sub>[↑ Back to top](#table-of-contents)</sub>

---

## 6. Advanced, Persistent & Specialized Data Structures

### Persistence Fundamentals

#### Persistence Models
- **Partial persistence** — all past versions are readable but only the latest is writable; versions form a linear chain.
- **Full persistence** — any version may be both queried and updated, branching the version history into a tree.
- **Confluent persistence** — versions may be combined/merged (e.g., concatenation), turning version history into a DAG.
- **Functional (purely persistent) persistence** — no destructive updates at all; achieved by structural sharing of immutable nodes.
- **Fat-node method** — store all modifications inside each node with version stamps; binary-search timestamps on access (O(log m) overhead per field).
- **Node-copying / path-copying** — copy every node on the modified root-to-leaf path, sharing untouched subtrees (O(structure-depth) extra nodes per update).
- **Fat-node + splitting (DSST)** — Driscoll–Sarnak–Sleator–Tarjan technique giving O(1) amortized space/time overhead for fully persistent bounded-degree structures.

#### Persistent Linear Structures
- **Persistent stack** — immutable singly linked list; push/pop create a new head while sharing the tail (O(1) per op, O(1) extra space).
- **Persistent queue / deque** — amortized via paired stacks with lazy rebuilding, or real-time via Okasaki banker's/physicist's deques (O(1) amortized, O(1) worst-case with scheduling).
- **Persistent array (van der Linde / balanced-tree backed)** — array semantics over a persistent balanced tree or trie giving O(log n) access and update with full version retention.
- **Persistent / functional random-access list** — skew-binary or numerical-representation lists with O(1) cons and O(log n) indexing.
- **Fully persistent arrays via fat nodes** — Dietz's structure achieving O(log log m) expected access in the fully persistent model.

### Persistent Trees & Tree-Backed Structures

- **Persistent segment tree (version tree)** — path-copy each point update so any historical version is queryable (O(log n) time and O(log n) new nodes per update).
- **Persistent segment tree for k-th order statistics** — build versions over array prefixes; subtract two versions to answer range k-th-smallest / count-in-value-range (O(log n) per query, see Wavelet & Merge-Sort Trees).
- **Persistent segment tree with lazy propagation** — range updates require copying affected nodes and careful lazy-tag immutability (O(log n) per op, larger constant).
- **Persistent BST / persistent balanced tree** — red-black or weight-balanced tree with path copying for ordered-set queries across versions.
- **Persistent treap** — randomized BST made persistent by copying along split/merge spines; supports persistent ordered sets and sequences (O(log n) expected per op).
- **Persistent Fenwick / BIT** — versioned prefix-sum structure (less common; usually superseded by persistent segment tree).
- **Persistent trie / persistent binary (XOR) trie** — versioned bitwise trie for max-XOR queries over a sliding/prefix set of numbers (O(B) per query, B = bit width).
- **Persistent DSU (union-find)** — make parent/rank arrays persistent via persistent arrays; supports querying connectivity in any past version (O(log² n) typical; cannot use plain path compression — pair with union by rank/size).
- **Persistent treap as persistent array** — implicit-key treap with copying to expose array indexing across versions.

### Balanced BSTs & Sequence-Order Structures (core)

- **AVL / red-black / weight-balanced / scapegoat / AA trees** — rotation- or rebuild-balanced BSTs underpinning ordered sets (see Foundational Data Structures).
- **Treap (randomized BST)** — BST-by-key, heap-by-random-priority; expected O(log n) operations; basis for split/merge sequence ops.
- **Implicit-key treap** — key = subtree position, enabling array-like split/merge, insert/erase at index, range reverse and range aggregates (O(log n) per op).
- **Rope** — sequence structure (implicit treap or balanced tree of substrings) supporting concatenate, split, insert, erase on huge strings in O(log n).
- **Cartesian tree** — treap-like tree built from a sequence; min/max of any range maps to LCA; built in O(n) by a stack.
- **Finger tree** — functional 2-3 tree with amortized O(1) ends and O(log n) split/concat; backs persistent sequences and priority deques.
- **Splay tree** — self-adjusting BST with O(log n) amortized ops; building block of link-cut trees and competitive working-set bounds.
- **Order-statistics tree** — augment any balanced BST with subtree sizes for rank/select (k-th element, count-less-than) in O(log n).

### Dynamic Trees (Forest-Maintenance Structures)

#### Link-Cut Trees
- **Link-cut tree (preferred-path / splay-based)** — maintains a forest of rooted trees under link, cut, find-root, path queries via heavy/preferred-path decomposition on splay trees (O(log n) amortized per op).
- **make-tree / link / cut** — create a single-node tree; attach a root to a parent; detach the edge above a node.
- **access / expose** — splay the preferred path to the root so a node-to-root path becomes one auxiliary splay tree (the core primitive).
- **find-root / find-parent** — root identification and ancestor navigation along the represented tree.
- **Path aggregates & lazy ops** — sum/min/max along a root-path, plus subtree-reversal lazy tags to support evert (re-rooting).
- **evert (re-root)** — make an arbitrary node the root via path reversal, enabling arbitrary path queries between two nodes.
- **Subtree aggregates in LCT** — augment with "virtual" (non-preferred) child aggregates to answer subtree queries, not just path queries.
- **LCT for offline/online MST & connectivity** — maintain maximum-edge-on-path to support dynamic minimum spanning forest under edge insert/delete.

#### Euler Tour Trees (ETT)
- **Euler tour tree** — represent a tree by the Euler tour of its (half-)edges stored in a balanced BST/treap; supports link, cut, connectivity, subtree aggregates (O(log n) per op).
- **Subtree queries via ETT** — a subtree is a contiguous Euler-tour interval, so subtree sum/size/add are range ops on the sequence.
- **ETT vs LCT** — ETT excels at subtree/connectivity queries; LCT excels at path queries; both give O(log n) dynamic-forest updates.

#### Top Trees & Higher-Level Cluster Structures
- **Top tree** — hierarchical clustering of a tree into "clusters" exposing two boundary vertices; supports generic path and subtree (non-local) queries in O(log n).
- **expose / link / cut on top trees** — designate two endpoints as the top cluster's boundary, then merge/split clusters to answer aggregate queries.
- **Self-adjusting / amortized top trees (Tarjan–Werneck)** — practical worst-case-friendly top-tree implementations.
- **Topology trees / two-level partition (Frederickson)** — earlier balanced clustering scheme for dynamic trees with O(√m) or O(log n) updates depending on variant.
- **RC-trees (rake-and-compress trees)** — randomized tree-contraction-based dynamic-tree structure supporting batched parallel-style updates.

### Dynamic Connectivity & Rollback

- **DSU with rollback (undo)** — union by rank/size without path compression so each union can be reverted from a stack (O(log n) per op, O(1) undo).
- **DSU with small-to-large / by-size** — controls tree height to O(log n) and makes rollback feasible.
- **Offline dynamic connectivity (segment tree on time)** — place each edge over its alive time interval on a segment tree of the timeline; DFS the tree applying rollback DSU to answer all queries (O((n+q) log q · log n)).
- **Online dynamic connectivity (Holm–de Lichtenberg–Thorup)** — leveled spanning-forest structure with ETT giving O(log² n) amortized update and O(log n / log log n) connectivity query.
- **Dynamic connectivity via LCT** — maintain a spanning forest with replacement-edge search for fully dynamic edge updates.
- **DSU on tree (small-to-large, "sack")** — offline technique merging child subtree info into the largest child to answer subtree queries in O(n log n) (see Tree Algorithms).
- **Parallel / persistent DSU** — combine rollback or persistence to query connectivity in arbitrary versions/time points.
- **Weighted / potential DSU** — store relative offsets to parent for "difference constraints" and bipartiteness/parity checks (near O(α(n)) per op).
- **DSU with bipartite/parity tracking** — augment with edge parity to detect odd cycles online.

### Segment-Tree Family & Range Aggregation

- **Segment tree (point/range, lazy)** — core range-aggregate structure (see Range Query Structures).
- **Merge-sort tree** — segment tree whose each node stores its sorted subarray; answers count-≤-x / k-th in a range in O(log² n) (offline alternatives via persistence/wavelet are faster).
- **Segment tree with fractional cascading on the merge-sort tree** — share sorted-order pointers down the tree to shave a log: O(log n) per range-rank query.
- **Wavelet tree** — recursively partition by alphabet/value, storing rank bitvectors per level; range rank/select/quantile/k-th in O(log σ) and 2D-dominance-style queries.
- **Merge-sort tree vs wavelet vs persistent segtree** — three standard routes to static range k-th/order-statistics with different time/space/online tradeoffs.
- **Segment tree beats (Ji Driver)** — supports range-chmin/chmax with range-sum by tracking max/secondmax (amortized O(log² n) or O(log n)).
- **Li Chao tree** — segment tree over x-coordinates storing lines for min/max-of-lines (convex-hull-trick) queries (O(log range) per op) (see Computational Geometry / CHT).
- **Kinetic segment tree / kinetic heaps** — maintain extremes of moving linear functions (see Kinetic Structures).
- **Sparse / dynamic segment tree** — allocate nodes lazily over a huge index space; O(log U) per op with O(q log U) memory.
- **Implicit segment tree with lazy** — combine sparse allocation with range updates for coordinate ranges too large to materialize.
- **Segment tree on tree (over HLD / Euler tour)** — lay a segment tree over a heavy-light decomposition or Euler tour to answer path/subtree range queries on a static tree in O(log² n) / O(log n) (see Tree Algorithms).
- **2D segment tree / segment tree of segment trees** — nested structure for orthogonal range aggregates (O(log² n) query, O(n log n) space).
- **Fenwick tree (BIT) and 2D BIT** — prefix-sum tree(s) for point-update range-sum (O(log n), O(log² n) in 2D) (see Range Query Structures).
- **BIT with coordinate compression / offline** — handle sparse value spaces for inversion counts and rank queries.
- **Persistent merge-sort tree** — versioned variant for online range-order-statistics.

### Multidimensional & Geometric Search Structures

#### Range / Orthogonal Search
- **k-d tree** — space-partitioning BST alternating split dimensions; nearest-neighbor and orthogonal range search in O(√n + k) (2D, worst case) / O(log n) average for NN.
- **Range tree** — multilevel BST (tree of trees) for orthogonal range reporting in O(logᵈ n + k); with fractional cascading O(log^{d-1} n + k).
- **Layered range tree / fractional cascading on range trees** — drop one log factor by linking sorted lists across the last dimension.
- **kd-range hybrids / range-kd tree** — combine partitioning with secondary structures for mixed query loads.
- **Priority search tree** — answer 3-sided (grounded) range queries and dominance in O(log n + k); combines heap + BST on (x, y).
- **Interval tree** — store intervals keyed by midpoint to report all intervals stabbed by a point or overlapping a query interval in O(log n + k).
- **Segment tree (Bentley, geometric)** — canonical-interval decomposition structure for stabbing/measure problems (distinct from the array segment tree).
- **Quadtree / region & point quadtree** — recursive 2D subdivision into four quadrants for spatial indexing and collision queries.
- **Octree** — 3D analogue of the quadtree for volumetric/space partitioning.
- **R-tree / R*-tree** — bounding-box hierarchy for spatial databases and overlapping objects (mostly DB-oriented).
- **BSP tree** — binary space partition for arbitrary hyperplane splits (rendering/visibility) (see Computational Geometry).
- **Ball tree / VP-tree (vantage-point tree) / metric tree** — metric-space partitioning for nearest-neighbor in general metrics (O(log n) average).
- **Cover tree / M-tree** — metric NN structures with provable bounds for high-dimensional/disk-based search.

#### Fractional Cascading & Layering
- **Fractional cascading** — preprocess a catalog graph of sorted lists so one binary search propagates via fraction pointers, turning k searches into O(log n + k) total.
- **Fractional cascading on segment/merge-sort trees** — apply the above to range-rank trees to save a log factor.
- **Persistence as an alternative to cascading** — partially persistent search trees give the same effect for "iterated search" problems.

### Integer & Bitwise-Keyed Structures

- **van Emde Boas tree (vEB)** — recursive √-universe layout giving predecessor/successor, insert, delete, min/max in O(log log U) (O(U) space, or O(n) with hashing).
- **y-fast trie** — bucket n keys into Θ(log U) groups over an x-fast trie of representatives for O(log log U) predecessor with O(n) space (randomized).
- **x-fast trie** — bit-trie with hash tables per level and a doubly linked leaf list for O(log log U) successor but O(n log U) space.
- **Fusion tree** — B-tree-like node packing w-bit keys for O(log_w n) predecessor in the word-RAM model.
- **Bitwise (binary) trie** — store integers by bits for prefix/XOR navigation; foundation of XOR-max and integer ordered sets.
- **Binary trie for maximum XOR (XOR trie)** — greedily walk opposite bits to find max-XOR pair/subset; O(B) per query; persistent/offline variants for subarray XOR queries.
- **BIT-on-trie / trie augmented with counts** — store subtree counts in the binary trie to support k-th XOR, count-in-XOR-range, and erasures.
- **Radix / PATRICIA trie** — path-compressed bit-trie for compact integer/string sets (see String Algorithms).
- **Bitset tricks & word-parallel structures** — 64-bit-word parallelism for subset/range ops (O(n/w) per op) (see Bit Manipulation).
- **Indexable skip list** — probabilistic ordered structure with O(log n) search/insert and rank support, a simpler alternative to balanced BSTs.

### Succinct & Compressed Data Structures

#### Bitvectors: Rank/Select
- **Rank/select bitvector** — answer rank(i) (count of 1s up to i) and select(k) (position of k-th 1) in O(1) using n + o(n) bits via two-level block/superblock indices.
- **select using lookup tables / clustered sampling** — Clark's and Jacobson's o(n)-bit select index over the rank structure.
- **RRR (Raman–Raman–Rao) compressed bitvector** — store blocks by (class, offset) to reach nH₀ + o(n) bits with O(1) rank/select.
- **Sparse bitvector / Elias–Fano encoding** — encode a monotone integer sequence in n log(U/n) + O(n) bits with O(1) select and fast rank/predecessor.
- **Gap / sd-array (sparse directory) bitvector** — succinct representation tuned for few set bits.

#### Sequences, Trees & Grids
- **Wavelet tree (succinct view)** — n log σ + o(n log σ) bits with O(log σ) rank/select/access; supports 2D grid (point-on-grid) range counting.
- **Wavelet matrix** — cache-friendly level-major reorganization of the wavelet tree with identical complexity and smaller constants.
- **Wavelet trie** — dynamic, alphabet-as-strings variant supporting insert/delete.
- **Succinct trees: LOUDS / BP / DFUDS** — encode an n-node ordinal tree in 2n + o(n) bits supporting navigation (child, parent, subtree size, LCA) in O(1).
- **Range Min-Max tree (RMM)** — succinct support for the balanced-parenthesis tree operations (find-match, enclose) in O(1).
- **Succinct / compressed suffix structures** — FM-index, compressed suffix array/tree, BWT (see String Algorithms).
- **Succinct DAG / graph representations** — compact adjacency via bitvectors and references for planar/sparse graphs.

### Heaps & Priority-Oriented Structures (advanced)

- **Binary / d-ary heap** — array heap with O(log n) push/pop (see Foundational Data Structures).
- **Binomial heap** — forest of binomial trees giving O(log n) merge.
- **Fibonacci heap** — O(1) amortized decrease-key and insert, O(log n) extract-min; speeds Dijkstra/Prim asymptotically.
- **Pairing heap** — simple self-adjusting heap with excellent practical decrease-key behavior (near O(1) amortized in practice).
- **Leftist / skew heap** — mergeable heaps via right-spine merging (O(log n) meld).
- **Brodal / strict Fibonacci heap** — worst-case O(1) decrease-key and O(log n) delete-min.
- **Mergeable heap via treap / randomized meldable heap** — O(log n) expected meld with simple code.
- **Interval / kinetic heap** — maintain min over time-varying keys (see Kinetic Structures).
- **Soft heap** — allow ε-fraction of "corrupted" keys for O(1) amortized ops; powering linear-time selection and MST verification.
- **Persistent / retroactive heap** — query past states or insert operations in the past (see Retroactivity).
- **Double-ended priority queue (min-max heap, interval heap, deap)** — O(log n) access to both extremes.

### Specialized & Niche Structures

#### Time-Travel & History
- **Retroactive data structures** — partially/fully retroactive queues, stacks, priority queues, and union-find that allow inserting/deleting operations in the past (O(√m) – O(log m) per op depending on structure).
- **Temporal / versioned indexes** — interval-indexed structures answering "as-of time t" queries.

#### Aggregation over Streams & Windows
- **Sliding-window minimum/maximum (monotonic deque)** — O(1) amortized window extreme (see Monotonic Stack/Queue).
- **SWAG (sliding-window aggregation)** — two-stack trick for any associative monoid over a sliding window (O(1) amortized).
- **Sqrt decomposition / blocking** — O(√n) per op generic range/update structure; basis for Mo's algorithm (see Sqrt Techniques).
- **Mo's algorithm & Mo's on trees / with updates** — offline query reordering for O((n+q)√n) range queries (see Offline & Sqrt Techniques).

#### Probabilistic & Approximate (cross-domain)
- **Skip list** — randomized layered linked list with O(log n) expected search/insert (also indexable variant above).
- **Bloom / counting / cuckoo filter** — approximate membership in sublinear space (see Hashing & Probabilistic Structures).
- **Count-Min sketch / Count sketch / HyperLogLog** — streaming frequency and cardinality estimation (see Streaming Algorithms).
- **Treap-based hashing / hash-array-mapped trie (HAMT)** — persistent hash map via bit-partitioned trie (O(log₃₂ n) effectively O(1)).

#### Misc Augmented & Domain Structures
- **Disjoint sparse table** — O(1) range queries for any associative (not necessarily idempotent) operation with O(n log n) build.
- **Sparse table** — O(1) idempotent range queries (min/max/gcd) with O(n log n) build (see Range Query Structures).
- **Fenwick-on-tree / Euler-Fenwick** — point-update subtree/path-sum via Euler-tour-indexed BIT.
- **Centroid decomposition structure** — recursive centroid tree enabling path-count and distance queries in O(log n) levels (see Tree Algorithms).
- **Heavy-light / long-path decomposition** — partition tree paths to map path queries onto segment trees (see Tree Algorithms).
- **Auxiliary "small-to-large" map merging** — combine per-subtree maps/sets in O(n log² n) total (see Tree Algorithms).
- **Cartesian-tree / treap-based range structures** — answer range-min plus split/merge in one structure.
- **Persistent / functional union-find for incremental MST & Kruskal reconstruction tree** — build a binary tree over Kruskal merges enabling offline "min bottleneck within weight ≤ w" path queries (O(log n) per query after O(m α(m)) build).

<sub>[↑ Back to top](#table-of-contents)</sub>

---

# ⚙️ Part — Core Techniques & Paradigms

## 7. Sorting Algorithms

### Foundations & Classification

- **Sorting problem definition** — rearrange a sequence into non-decreasing order under a total order (or any comparator); output is a permutation of the input.
- **Total order requirements** — comparator must be reflexive, antisymmetric, transitive, and total (comparable); violating these yields undefined/unstable results.
- **Key vs. record** — sort by a key while carrying satellite data; large records favor sorting pointers/indices to avoid expensive moves.
- **Comparison vs. non-comparison model** — comparison sorts use only pairwise key comparisons; non-comparison sorts exploit key structure (digits, ranges, hashing) to beat the comparison bound.
- **Internal vs. external sorting** — internal: all data fits in RAM; external: data resides on disk/tape and minimizing I/O dominates.
- **Online vs. offline sorting** — online sorts (e.g., insertion) accept elements one at a time; offline sorts need the full input first.

#### Core Properties of Sorting Algorithms

- **Stability** — equal-key elements retain their original relative order; essential for multi-key/lexicographic sorting and radix passes.
- **In-place sorting** — uses O(1) or O(log n) auxiliary space beyond the input array.
- **Adaptiveness** — runtime improves on partially sorted input, approaching O(n) on nearly-sorted data.
- **Comparison count vs. data moves** — distinct cost metrics; selection sort minimizes writes, others minimize comparisons.
- **Deterministic vs. randomized** — randomization (e.g., random pivots) defends against adversarial inputs and gives expected-time guarantees.
- **Cache efficiency / locality** — sequential-access sorts (merge, radix) and cache-oblivious layouts outperform pointer-chasing on real hardware.
- **Recursive vs. iterative** — recursion depth affects stack usage; iterative/bottom-up variants bound auxiliary stack space.

### Theoretical Lower Bounds & Complexity

- **Ω(n log n) comparison lower bound** — any comparison sort needs Ω(n log n) comparisons worst case, via the decision-tree (n! leaves) argument.
- **Decision-tree model** — each comparison is a binary node; tree height ≥ ⌈log₂(n!)⌉ ≈ n log₂ n − n/ln 2 comparisons.
- **Information-theoretic argument** — log₂(n!) bits needed to identify one of n! permutations; Stirling gives the n log n bound.
- **Average-case lower bound** — expected comparisons also Ω(n log n); average path length of any binary tree with n! leaves.
- **Adversary arguments** — lower bounds for min/max, median, and selection via maintaining a worst-case answer set.
- **Sorting with partial information** — sorting under a partial order / poset with known constraints can beat n log n by the number of linear extensions.
- **Beating the bound** — non-comparison models (counting/radix) achieve O(n) by assuming bounded integer/digit keys.
- **Element distinctness reduction** — sorting solves distinctness, giving Ω(n log n) in algebraic decision-tree models.

### Elementary Comparison Sorts (Quadratic)

- **Bubble sort** — repeatedly swap adjacent out-of-order pairs until no swaps; stable, in-place (O(n²); O(n) best on sorted input with early-exit flag).
- **Cocktail shaker sort** — bidirectional bubble sort sweeping alternately forward and backward; reduces turtle effect (O(n²); O(n) best).
- **Comb sort** — bubble sort with a shrinking gap (factor ≈ 1.3) to eliminate small far-from-home elements quickly (O(n²) worst, ~O(n²/2^p) typical).
- **Odd-even (brick) sort** — compare/swap odd then even adjacent pairs; parallel-friendly bubble variant (O(n²) serial, O(n) parallel passes).
- **Insertion sort** — grow a sorted prefix by inserting each new element into place; stable, adaptive, in-place (O(n²); O(n) nearly sorted).
- **Binary insertion sort** — locate insertion point via binary search to cut comparisons, but shifts still cost O(n) (O(n²) moves, O(n log n) comparisons).
- **Selection sort** — repeatedly select the minimum of the unsorted suffix and place it; minimal writes, not adaptive (O(n²); Θ(n) swaps).
- **Double selection sort** — pick both min and max each pass to halve outer iterations (O(n²)).
- **Gnome (stupid) sort** — like insertion but moving via adjacent swaps, stepping back on inversions; tiny code (O(n²); O(n) sorted).
- **Pancake sort** — sort by prefix reversals (flips); minimizes number of flips, studied for flip distance (O(n²) flips; n−1 flips suffice naively).
- **Burnt-pancake sort** — pancake variant where each cake has an orientation that must end correct; flip-count bounds studied combinatorially.
- **Stooge sort** — recursively sort first 2/3, last 2/3, first 2/3; deliberately inefficient pedagogical sort (O(n^2.71)).
- **Bogosort (permutation sort)** — randomly shuffle until sorted; canonical worst algorithm, used only to illustrate (O((n+1)!) expected).
- **Bozosort / slowsort** — joke/anti-algorithms ("multiply and surrender") illustrating bad recursion (super-polynomial).

### Shellsort & Gap Sequences

- **Shellsort** — gapped insertion sort over decreasing gaps, finishing with gap 1; adaptive, in-place, unstable (complexity depends on gap sequence).
- **Shell's original gaps (n/2^k)** — simple halving sequence; weak worst case (O(n²)).
- **Hibbard gaps (2^k − 1)** — gives Θ(n^1.5) worst case.
- **Knuth gaps (3^k formula, (3^k−1)/2)** — practical, Θ(n^1.5) worst case.
- **Sedgewick gaps** — interleaved formulas giving O(n^4/3) worst case.
- **Pratt gaps (2^p·3^q)** — Θ(n log² n) worst case but many gaps in practice.
- **Tokuda / Ciura gaps** — empirically optimal sequences; Ciura's constants are the fastest known for small/medium n.

### Efficient Comparison Sorts (n log n)

#### Merge Sort Family

- **Top-down merge sort** — recursively split, sort halves, merge; stable, predictable (O(n log n) time, O(n) space).
- **Bottom-up (iterative) merge sort** — merge runs of size 1, 2, 4, … without recursion; avoids recursion stack (O(n log n)).
- **Two-way merge operation** — linear merge of two sorted runs with a sentinel or index check (O(n) per merge).
- **k-way merge** — merge k runs using a min-heap or loser tree; core of external sort (O(n log k)).
- **Natural merge sort** — detect existing ascending runs and merge them; adaptive, foundation of Timsort (O(n) on sorted input).
- **In-place merge sort** — merge without O(n) buffer via rotations or block techniques; complex and slower (O(n log² n) typical).
- **Block merge sort (WikiSort / GrailSort)** — stable in-place merge using internal buffers and block rotations (O(n log n) time, O(1) extra).
- **Linked-list merge sort** — merge sort with pointer relinking; no extra array, ideal for lists (O(n log n), O(log n) stack).
- **Parallel merge sort** — split-and-conquer plus parallel merge with rank/co-rank computation (O(n) work, O(log² n) span).

#### Quicksort Family

- **Quicksort (Lomuto partition)** — pick pivot, partition ≤/>, recurse; simple but extra swaps (O(n log n) avg, O(n²) worst).
- **Quicksort (Hoare partition)** — two-pointer partition with fewer swaps and better behavior on duplicates (O(n log n) avg).
- **Pivot: first/last element** — naive choice; O(n²) on sorted/reverse input.
- **Pivot: random element** — randomized pivot gives O(n log n) expected, defeats adversarial order.
- **Pivot: median-of-three** — median of first/middle/last reduces worst-case likelihood and helps presorted data.
- **Pivot: median-of-medians (BFPRT)** — deterministic median selection guaranteeing O(n log n) worst case (high constant).
- **Pivot: ninther (median of medians-of-three)** — Tukey's ninther for large arrays, robust pivot estimate.
- **3-way quicksort (Dutch National Flag)** — partition into <, =, > to handle many duplicates in linear extra work (O(n) on all-equal keys).
- **Dual-pivot quicksort** — two pivots split into three regions; fewer swaps, used in practice (O(n log n) avg, ~5% fewer comparisons).
- **Multi-pivot quicksort** — generalization to k pivots; theoretical interest in cache and comparison tradeoffs.
- **Tail-recursion elimination** — recurse on smaller part, loop on larger to bound stack to O(log n).
- **Cutoff to insertion sort** — switch to insertion sort for small subarrays (threshold ~10–32) for speed.
- **Quicksort worst-case avoidance** — random shuffle / randomized pivots / introsort fallback to escape O(n²).

#### Heap-Based Sorts

- **Heapsort** — build a binary max-heap then repeatedly extract-max into the tail; in-place, unstable, not adaptive (O(n log n), O(1) extra).
- **Bottom-up heap construction (Floyd)** — heapify in O(n) by sifting down from the last internal node.
- **Bottom-up heapsort (Wegener)** — sift with a single downward search then bubble up, reducing comparisons (~n log n + smaller constant).
- **Ternary / d-ary heapsort** — d-ary heaps trade comparisons for fewer levels; tune for cache behavior.
- **Smoothsort** — Dijkstra's heapsort variant using Leonardo heaps; adaptive, O(n) on sorted input (O(n log n) worst, O(1) extra).
- **Weak-heapsort** — uses a weak heap to approach the information-theoretic comparison minimum (n log n − 0.9n comparisons).
- **Poplar sort / Leonardo-heap variants** — related implicit-forest heaps trading adaptiveness for simpler structure.

#### Hybrid & Industrial Sorts

- **Introsort** — quicksort that switches to heapsort past a recursion-depth limit and to insertion sort on small ranges; guarantees O(n log n) (the typical unstable library sort).
- **Timsort** — natural run detection, minrun-sized insertion sort, galloping merge with stack-merge invariants; adaptive, stable (O(n) best, O(n log n) worst, O(n) space).
- **Galloping (exponential search) merge** — Timsort's mode that skips long winning streaks in a merge (sublinear when one run dominates).
- **Merge-stack invariants** — Timsort's run-length balance rules keep merges balanced (the patched invariant fixing the known stack bug).
- **Pattern-defeating quicksort (pdqsort)** — introsort-style sort detecting patterns, using BlockQuicksort partitioning and breaking adversarial inputs (O(n log n), O(n) on common patterns).
- **BlockQuicksort** — branchless partitioning buffering swap indices to avoid branch mispredictions (large constant-factor speedups).
- **Library sort (gapped insertion sort)** — insertion sort leaving gaps for O(n log n) expected via spreading inserts (O(n log n) expected, O(n) extra).
- **Tournament sort** — selection via a winner tree; precursor to heapsort and replacement-selection (O(n log n)).

### Non-Comparison (Integer / Distribution) Sorts

- **Counting sort** — count key frequencies then place by prefix sums; stable, linear for bounded keys (O(n + k) time and space, k = key range).
- **Pigeonhole sort** — place each key directly into its bucket index; counting sort when keys form a dense range (O(n + range)).
- **Bucket sort** — distribute into buckets by value range, sort each, concatenate; great for uniform data (O(n + k) avg, O(n²) worst).
- **LSD radix sort** — stable counting/bucket passes from least- to most-significant digit; needs no recursion (O(d·(n + b)), d digits, base b).
- **MSD radix sort** — recurse most-significant digit first, partitioning into bins; good for variable-length keys/strings (O(d·(n + b))).
- **Radix base / digit-width tuning** — choose base b ≈ n to minimize d·(n+b) passes; tradeoff between passes and bucket overhead.
- **Binary / bitwise radix (radix exchange)** — quicksort-like partition on each bit from MSB; in-place radix (O(d·n)).
- **American flag sort** — in-place MSD radix using counting + cyclic permutation to place elements (O(d·n), O(b) extra).
- **Three-way radix quicksort (multikey quicksort)** — quicksort on the current character with a 3-way split; excellent for string keys (O(n·avg-key-length) typical).
- **Burstsort** — trie of growable buckets ("bursts") for cache-efficient string sorting (near-linear, strong cache behavior).
- **Spreadsort** — hybrid distributing by most-significant bits then recursing/falling back to comparison sort (O(n·(k/d + log d)) style bound).
- **Flashsort** — distribution sort using a classification step into m ≈ 0.43n classes then insertion finish (O(n) average, O(n²) worst, O(n) extra).
- **Interpolation / histogram sort** — bucket sort using the empirical distribution to size buckets adaptively.
- **Proxmap sort** — maps keys to "proxmap" buckets via a key-to-address function, then insertion sort (O(n) average).
- **Recombinant / QR-style sorts** — research distribution sorts combining hashing/bucket/counting/radix ideas (claimed near-linear under assumptions).

### Adaptive & Presortedness-Aware Sorts

- **Measures of presortedness** — Inv (inversions), Runs, Rem (removals to sort), Dis, Exc, SMS, Osc; classify how "sorted" the input is.
- **Adaptive optimality** — an algorithm is optimal w.r.t. a measure if it runs in O(n·(1 + log(measure/n))) or analogous bound.
- **Insertion sort adaptiveness** — runs in O(n + Inv); linear when inversions are O(n).
- **Natural merge / Timsort adaptiveness** — exploit existing runs; O(n) on sorted, O(n log r) with r runs.
- **Patience sorting** — greedily place into piles (each a decreasing stack); pile count = length of longest increasing subsequence (O(n log n)).
- **Patience-based merge (full sort)** — k-way merge the piles to produce the sorted order; also extracts LIS as a byproduct.
- **Smoothsort (adaptive)** — heapsort variant that is O(n) on presorted input (see Heap-Based Sorts).
- **Cartesian-tree / peeksort / powersort** — run-aware mergesorts choosing near-optimal merge order from run lengths (nearly optimal merge cost; powersort is Python's current merge policy).
- **Adaptive heapsort / splaysort** — use a Cartesian tree or splay tree to be adaptive to several presortedness measures.

### Selection, Order Statistics & Partial Sorting

- **Quickselect** — quicksort partition recursing only into the side holding the kth element (O(n) average, O(n²) worst).
- **Randomized quickselect** — random pivot gives O(n) expected for any input.
- **Median-of-medians (BFPRT)** — deterministic O(n) worst-case selection via grouped medians as pivot.
- **Introselect** — quickselect with median-of-medians fallback for guaranteed O(n) (the typical nth-element routine).
- **Partial sort (top-k ordered)** — produce the k smallest in sorted order; heap-based or partition-then-sort (O(n + k log k) or O(n log k)).
- **Partial sort via bounded max-heap** — maintain a size-k heap streaming over n elements (O(n log k), O(k) space).
- **nth_element / partition-around-rank** — place the kth element in final position with both sides partitioned (O(n) average).
- **Heap-based k smallest/largest (streaming top-k)** — online selection from a stream with a fixed-size heap (O(n log k)).
- **Multiple order statistics** — selecting several ranks at once via repeated partitioning / range trees.
- **Selection lower bounds** — finding the median needs ≥ (2+ε)n comparisons; min+max in ⌈3n/2⌉−2 comparisons.

### Inversion Counting & Sorting-Distance Problems

- **Inversion count definition** — number of pairs (i<j) with a[i]>a[j]; equals minimum adjacent swaps to sort.
- **Inversions via merge sort** — count cross-inversions during each merge (O(n log n)).
- **Inversions via BIT/Fenwick** — coordinate-compress then sweep counting earlier-larger elements (O(n log n)).
- **Inversions via order-statistics BST / merge of treaps** — alternative O(n log n) counting structures.
- **Minimum adjacent swaps to sort** — equals inversion count for distinct keys; with duplicates, match equal values greedily.
- **Minimum arbitrary swaps to sort (permutation cycles)** — n minus number of cycles in the permutation.
- **Kendall tau distance** — inversion count between two permutations; measures ranking disagreement.
- **k-inversions / nearly sorted bound** — counting pairs within window; relates to adaptive bounds.
- **Significant inversions / range-bounded inversions** — count pairs with a[i] > c·a[j]; merge-sort variant.

### Sorting Networks & Oblivious Sorting

- **Sorting network model** — fixed sequence of compare-exchange comparators independent of data; oblivious and parallelizable.
- **Comparator (compare-exchange)** — primitive placing min on one wire, max on the other.
- **Zero-one principle** — a network sorts all inputs iff it sorts all 0/1 inputs; basis for correctness proofs.
- **Network metrics: size and depth** — size = total comparators; depth = parallel layers (critical for hardware/GPU).
- **Insertion / bubble networks** — straightforward O(n²)-size, O(n)-depth networks.
- **Odd-even transposition network** — n layers of paired comparators; simple, O(n²) size, O(n) depth.
- **Bitonic sort (Batcher)** — merge bitonic sequences recursively; O(n log² n) comparators, O(log² n) depth, ideal for GPUs.
- **Odd-even mergesort (Batcher)** — recursive oblivious merge; O(n log² n) size, O(log² n) depth.
- **Pairwise sorting network** — Batcher-style network with same asymptotics, fewer comparators in practice.
- **AKS network** — theoretical O(n log n)-size, O(log n)-depth network; enormous constants, impractical.
- **Zig-zag / optimal small networks** — known minimal-comparator networks for n ≤ 16 found by search/SAT.
- **Shellsort networks (Pratt)** — networks from the 2^p3^q gap sequence, O(n log² n) size.

### External & Memory-Hierarchy Sorting

- **External merge sort** — sort memory-sized runs, then k-way merge across passes; minimizes disk I/O (O((n/B)·log_{M/B}(n/B)) I/Os).
- **Run generation (sort phase)** — read M-sized chunks, sort in memory, write initial runs.
- **Replacement selection** — use a heap to produce runs averaging 2M long, halving pass count.
- **Multiway (k-way) merge phase** — merge as many runs as buffers allow with a loser tree to cut comparisons.
- **Loser tree / tournament tree** — efficient structure for repeatedly extracting the minimum across k streams.
- **Polyphase merge sort** — Fibonacci-style tape distribution minimizing passes on limited tape drives.
- **Cascade / balanced merge** — alternative multi-tape merge schedules for sequential media.
- **Cache-oblivious sorting (funnelsort)** — recursive k-merger achieving optimal cache transfers without knowing B, M (O((n/B)·log_{M/B}(n/B))).
- **Distribution-based external sort** — sample splitters, partition to disk, sort partitions (sample-sort on disk).
- **I/O model (external memory / DAM)** — analyze cost in block transfers of size B with internal memory M.
- **Double buffering / async I/O** — overlap computation and disk reads/writes to hide latency.

### Parallel, Distributed & GPU Sorting

- **PRAM sorting** — theoretical shared-memory parallel sorts (Cole's parallel merge sort: O(log n) time, O(n) processors).
- **Cole's pipelined merge sort** — O(log n) time on n processors via pipelined merging.
- **Parallel sample sort** — pick splitters, bucket-partition across processors, locally sort; load-balanced (near-optimal communication).
- **Parallel radix sort** — distributed counting/prefix-sum across nodes for integer keys.
- **Bitonic sort (parallel/GPU)** — fixed comparator schedule maps cleanly to SIMD/GPU lanes (O(log² n) stages).
- **PSRS (Parallel Sorting by Regular Sampling)** — sample-based partitioning with provable load balance.
- **Histogram / bin-based parallel sort** — global histogram then scatter into ordered bins.
- **MapReduce / TeraSort** — range-partition with sampled splitters, sort partitions, concatenate (used in big-data benchmarks).
- **Multiway merge on multicore** — parallel k-way merge with co-ranking to split work evenly.
- **GPU merge path / merge sort** — partition merges using "merge path" diagonals for balanced thread work.
- **Work/span (depth) analysis** — evaluate parallel sorts by total work and critical-path length, not just time.

### Specialized & Constraint-Limited Sorts

- **Cycle sort** — sort by following permutation cycles writing each element once to its final slot; minimizes writes (O(n²) comparisons, Θ(n) writes — optimal for write-limited media).
- **Pancake sort** — sort using only prefix reversals (see Elementary Sorts); minimizing flips is NP-hard in general.
- **Spaghetti (linear/analog) sort** — physical analog selecting the tallest rod; conceptual O(n) model sort.
- **Bead (gravity) sort** — non-comparison analog/parallel sort for positive integers via "falling beads" (O(n) on idealized hardware).
- **Sleep sort** — joke concurrency sort spawning timers proportional to values; illustrates concurrency, not practical.
- **Strand sort** — repeatedly pull ascending subsequences (strands) and merge into output; adaptive on lists (O(n²) worst, O(n) on sorted).
- **Topological sort** — order a DAG so edges go forward; not a comparison sort (see Graph Algorithms).
- **Sorting by reversals / transpositions** — genome-rearrangement distances; signed reversal distance is polynomial, unsigned is NP-hard (see Computational Biology / Strings).
- **Token / pancake-flip puzzles** — sorting under restricted move sets studied combinatorially.

### Practical Engineering & Application Topics

- **Custom comparators & total order** — define a strict weak ordering; non-transitive comparators cause crashes/undefined order.
- **Multi-key / lexicographic sorting** — sort by primary then secondary keys; achieved by stable sort passes (LSD style) or composite comparators.
- **Schwartzian transform / decorate-sort-undecorate** — precompute sort keys once when key computation is costly (O(n) extra space).
- **Indirect / index sorting** — sort an array of indices or pointers to avoid moving large records; produces a permutation.
- **Sorting with ties / secondary stable order** — stability guarantees deterministic tie-breaking without extra keys.
- **Hybrid threshold tuning** — insertion-sort cutoff, introsort depth limit, radix base chosen empirically per platform.
- **Branchless / SIMD-vectorized sorting** — sorting-network kernels and vectorized partitions (e.g., AVX-512 quicksort) for small blocks.
- **Sorting strings vs. fixed-size keys** — variable-length comparisons favor MSD radix / multikey quicksort over generic comparison sorts.
- **Stability-restoring trick** — append original index as a tie-breaker to make any sort stable (O(n) extra space).
- **Verifying sortedness & sortedness testing** — linear scan to verify; sublinear property testing estimates "distance to sorted."
- **Counting comparisons/operations** — instrumenting sorts for analysis, profiling, and adversarial testing.
- **Choosing a sort in practice** — small n → insertion; general unstable → introsort/pdqsort; stable → Timsort; integer keys → radix/counting; huge/disk → external merge.

<sub>[↑ Back to top](#table-of-contents)</sub>

---

## 8. Searching, Selection & Order Statistics

### Linear & Sequential Search

- **Linear (sequential) search** — scan elements left-to-right until a match or end; works on any unsorted/unstructured data (O(n) time, O(1) space).
- **Sentinel linear search** — place the target as a sentinel at the array end to drop the bounds check inside the loop, halving comparisons per iteration (O(n) time, O(1) space).
- **Self-organizing / move-to-front search** — reorder a list on access (move-to-front, transpose, count) so frequently sought keys drift toward the front, lowering expected cost for skewed query distributions (amortized improvement, O(1) per move).
- **Front-and-back (two-pointer) scan** — search from both ends simultaneously to find a key or matching pair in roughly half the passes (O(n) time).
- **Search with early termination on sorted data** — stop once elements exceed the target in a sorted-but-scanned list, expected halving of work but still O(n) worst case.
- **Bidirectional / index-table search** — maintain auxiliary index of sampled positions to skip ahead, a precursor to jump search (O(n) worst case, fewer probes in practice).
- **Probabilistic skip via reservoir/sampling membership** — approximate "is x likely present" using sketches; exact membership belongs to hashing (see Hashing & Hash Tables).

### Binary Search on Sorted Sequences

#### Core binary search

- **Classic binary search** — repeatedly halve a sorted range comparing the midpoint to the target until found or empty (O(log n) time, O(1) iterative space).
- **Recursive vs iterative formulation** — same logarithmic bound; iterative avoids O(log n) stack and overflow-safe midpoint `lo + (hi-lo)/2` prevents integer overflow.
- **Lower bound (first ≥ x)** — leftmost position where x could be inserted keeping order; finds first element not less than x (O(log n)).
- **Upper bound (first > x)** — leftmost position strictly greater than x; `upper - lower` gives multiplicity of x (O(log n)).
- **First / last occurrence of a key** — variants of lower/upper bound to locate boundaries of a run of equal keys (O(log n)).
- **Count of occurrences** — derived as upper_bound(x) − lower_bound(x) over a sorted array with duplicates (O(log n)).
- **Predecessor / successor queries** — largest element < x and smallest > x via bound adjustments (O(log n)).
- **Binary search for insertion point** — position to keep array sorted; basis of insertion into sorted structures (O(log n) search).
- **Branchless / Eytzinger (BFS-order) binary search** — store the array in heap/BFS layout so probes are cache- and branch-predictor-friendly, large constant-factor speedups (O(log n), better locality).
- **Uniform / power-of-two binary search** — fixed step pattern (always halve toward a power of two) to make access pattern data-independent (O(log n)).
- **Overflow-safe and off-by-one discipline** — invariant-based loop design (closed `[lo,hi]` vs half-open `[lo,hi)`) to avoid classic boundary bugs; correctness reasoning via loop invariants.

#### Binary search on the answer (parametric search)

- **Monotonic predicate framing** — recast an optimization problem as "find the boundary where a boolean predicate P flips from false to true," requiring P be monotone in the parameter (O(log(range) · cost(P))).
- **Minimize-the-maximum / maximize-the-minimum** — bisect the answer value and feasibility-check via a greedy/simulation predicate (classic "aggressive cows," "split array largest sum") (O(n log(range))).
- **Binary search on the feasibility check** — predicate often a linear-time greedy, simulation, or counting sweep; total cost = log(answer range) × predicate cost.
- **Discrete parametric search (Megiddo)** — sort/select-driven simulation of an algorithm on an unknown parameter to find optimal value without explicit candidate enumeration (often O(T · log T) or with parallel-sort speedups).
- **Search on a sorted set of candidate answers** — when answers form a finite known set (e.g., pairwise distances), binary-search that sorted candidate list instead of a numeric range.
- **Fractional / ratio optimization via bisection** — binary search the ratio λ in problems reducible to "does there exist a solution with cost − λ·weight ≤ 0" (links to Dinkelbach; see Optimization).
- **Two-level / nested binary search** — outer search on one parameter with an inner search/check, used in geometric and scheduling problems (O(log² ) factor).
- **Binary lifting as binary search on structure** — jump in powers of two over ancestors/successor links to answer level-ancestor or "k-th next" queries (O(log n) per query; see Trees / Graphs).

#### Binary search on real numbers

- **Bisection on continuous monotone predicate** — halve a real interval `[lo,hi]` for a fixed iteration count or until width < ε (O(log((hi−lo)/ε))).
- **Fixed-iteration bisection** — run ~100 iterations instead of an epsilon test to dodge floating-point stalling and guarantee precision.
- **Precision and relative-error pitfalls** — choosing absolute vs relative tolerance; avoiding non-termination from representable-gap issues near the root.
- **Newton / secant hybridization** — switch from bisection to superlinear methods once bracketed for faster convergence (see Numerical Methods).

### Beyond Plain Binary Search

- **Exponential (galloping / doubling) search** — probe indices 1,2,4,8,… to bracket the target, then binary search the found window; ideal for unbounded or infinite sorted streams (O(log i) where i is target position).
- **Galloping in merges (Timsort-style)** — exponentially advance one run into another during merging to skip large equal/ordered stretches (amortized sublinear per merge step).
- **Interpolation search** — estimate the probe position by linear interpolation on key values; O(log log n) expected on uniformly distributed keys, O(n) worst case on skewed data.
- **Quadratic / model-based interpolation** — higher-order or learned position estimators (precursor to learned indexes) for non-uniform distributions.
- **Jump (block) search** — step forward in fixed blocks of size √n, then linear-scan the final block; O(√n) time, useful when jumping back is costly.
- **Doubling jump / two-level jump search** — hierarchical block sizes (√n then √√n …) trading probes for scans.
- **Fibonacci search** — narrow a sorted range using Fibonacci-number splits, using only addition/subtraction (no division) and good locality (O(log n)).
- **Ternary search on unimodal sequences** — locate the extremum of a unimodal function by discarding a third each step (O(log n)); for optimization framing (see Optimization & Numerical Methods).
- **Golden-section search** — ternary-search variant reusing one evaluation per step via the golden ratio, fewer function calls (O(log n)).
- **Search via implicit / virtual arrays** — binary or galloping search over a monotone function f(i) without materializing the array (e.g., "smallest i with f(i)≥x") (O(log range)).

### Searching in Structured & Multidimensional Data

#### Rotated, shifted, and unimodal arrays

- **Search in a rotated sorted array (no duplicates)** — identify the sorted half at each step to decide which side to recurse (O(log n)).
- **Search in a rotated sorted array with duplicates** — same idea but ambiguous midpoints force occasional linear shrink, degrading to O(n) worst case.
- **Find rotation pivot / minimum of rotated array** — binary search the inflection where order breaks (O(log n)).
- **Search in a bitonic / mountain array** — find the peak via ternary/binary search, then binary-search each monotone arm (O(log n)).
- **Peak element finding** — locate any local maximum in an unsorted array by following the uphill neighbor (O(log n)).

#### Sorted matrices and grids

- **Staircase (saddleback) search in a row+column sorted matrix** — start at top-right (or bottom-left) and step left/down based on comparison (O(m + n)).
- **Binary search in a fully sorted matrix (row-major monotone)** — treat as a flattened sorted array of size m·n (O(log(mn))).
- **Divide-and-conquer quadrant search** — recursively eliminate a quadrant in a row/column-sorted matrix (O(n^{log₂3}) ≈ subquadratic for square matrices).
- **k-th smallest in a row+column sorted matrix** — binary search on value with a staircase counter, or a min-heap merge of rows (O(n log(max−min)) or O(k log n)).
- **Median of a row-wise sorted matrix** — binary search on value counting elements ≤ mid across rows (O(rows · log cols · log range)).
- **Search in a Young tableau** — saddleback search / insertion-deletion in a matrix sorted along rows and columns (O(m + n) search).

#### Range and dimensional search structures (cross-references)

- **Binary search inside ordered balanced BSTs / order-statistic trees** — predecessor, successor, rank, and select on dynamic ordered sets (O(log n); see Trees & BSTs).
- **Successor/predecessor over integer universe (van Emde Boas, x/y-fast tries)** — sublogarithmic O(log log U) ordered queries on bounded integer keys (see Advanced Data Structures).
- **Range tree / k-d tree / fractional-cascaded layered search** — orthogonal range search and reporting (see Computational Geometry / Range Search).
- **Skip-list search** — probabilistic multi-level linked structure giving expected O(log n) ordered search and predecessor queries.

### Selection & Order Statistics

#### Definitions and bounds

- **Order statistic / k-th smallest (or largest)** — the element of rank k in sorted order; minimum (k=1), maximum (k=n), median (k=⌈n/2⌉).
- **Selection lower bound** — any comparison-based selection needs Ω(n) comparisons; finding min or max needs n−1, finding both needs ⌈3n/2⌉−2.
- **Simultaneous min and max** — pair up elements and compare pairwise to cut comparisons to ~3n/2 (O(n)).
- **k-th order statistic by full sort** — trivial O(n log n) baseline; only worthwhile when many queries reuse the sorted order.

#### Comparison-based selection algorithms

- **Quickselect (Hoare's selection)** — partition around a pivot and recurse only into the side containing rank k (O(n) expected, O(n²) worst case, O(1) extra with in-place partition).
- **Randomized quickselect** — random pivot makes expected O(n) hold for all inputs with high probability; worst case still O(n²) but vanishingly rare.
- **Median of medians (BFPRT) deterministic selection** — group by 5, take medians, recurse for a pivot guaranteeing balanced splits → worst-case O(n) (large constant, O(log n) recursion space).
- **Introselect** — start with quickselect, fall back to median-of-medians when recursion depth/progress degrades, giving expected fast and worst-case O(n).
- **Floyd–Rivest selection (SELECT)** — sample-based pivot bracketing that selects with n + min(k, n−k) + o(n) expected comparisons, near comparison-optimal in practice.
- **Lazy / repeated quickselect for multiple ranks** — partition once and answer several order statistics from the recorded pivot positions (amortized savings).
- **Partial sorting via selection** — quickselect to position k, then sort the smaller side to get the k smallest in order (O(n + k log k)).
- **Quickselect on streams of comparisons / custom comparators** — selection where the "key" is an expensive comparator or partial order.

#### Heap and tournament methods

- **Heap-select (k smallest via max-heap of size k)** — maintain a size-k heap over a scan to get the k smallest/largest (O(n log k) time, O(k) space).
- **Build-heap then extract-k** — heapify all n then pop k times for the k smallest (O(n + k log n)).
- **Tournament tree for k-th largest / k smallest** — replay loser/winner trees to extract successive extrema, classic for external selection (O(n + k log n)).
- **Median maintenance with two heaps** — a max-heap of the low half and min-heap of the high half give the running median (O(log n) per insert, O(1) query; see streaming below).
- **Soft heaps & approximate selection** — Chazelle's soft heap corrupts a bounded fraction of keys to enable linear-time approximate/exact selection and minimum-spanning-tree subroutines (O(1) amortized per operation).

#### Selection in two or more arrays

- **k-th element in two sorted arrays** — binary search the split point so the left parts together hold k elements, balancing the two arrays (O(log(min(m,n)))).
- **Median of two sorted arrays** — special case of the above with k = (m+n)/2, the classic optimal-partition problem (O(log(min(m,n)))).
- **k-th smallest across many sorted arrays** — binary search on value counting elements ≤ mid in each array, or a k-way merge with a min-heap (O(arrays · log range) or O(k log arrays)).
- **k-th smallest in the sorted pairwise-sum / sorted matrix of sums** — selection over an implicit sorted matrix A[i]+B[j] via value binary search or a heap frontier (O((n+k) log n) styles).
- **Selection in the union of sorted lists with fractional cascading** — speed up repeated rank/successor queries across many lists (see Advanced Techniques).

#### Weighted, approximate, and specialized selection

- **Weighted median** — element where total weight on each side ≤ half; solvable in O(n) by a weighted quickselect-style partition.
- **Weighted / k-th order statistic for facility-location & 1-median** — selection used as a subroutine in min-cost placement problems (O(n)).
- **ε-approximate quantiles / median** — sampling or sketching to get a rank within εn of true rank with high probability (sublinear with sampling).
- **Multi-quantile selection** — find several quantiles together by recursively partitioning around chosen pivots (O(n log q) for q quantiles).
- **Selection by rank in a multiset with duplicates** — careful handling of ties so rank semantics (≤ vs <) stay consistent.

### Selection & Searching over Streams and External Data

- **Reservoir sampling for rank/median estimation** — maintain a uniform sample of a stream to approximate order statistics in one pass (O(1) per item, O(k) space).
- **Running median (online median)** — two-heap or balanced-BST/order-statistic structure to report the median after each insertion (O(log n) per update).
- **Sliding-window median / k-th order statistic** — multiset, two-heap with lazy deletion, or order-statistic tree to track a window's median as it slides (O(log w) per step).
- **Greenwald–Khanna quantile summary** — deterministic ε-approximate quantiles over a stream in O((1/ε) log(εN)) space.
- **t-digest / q-digest / KLL sketches** — mergeable streaming quantile sketches with strong accuracy at extremes and bounded memory (sublinear space).
- **Count-based / frequency selection (heavy hitters)** — Misra–Gries, Space-Saving, Count-Min for top-k frequent items (see Hashing / Sketching).
- **Median-of-medians as a streaming pivot oracle** — approximate-median routines reused for one-pass partitioning.
- **External-memory selection** — block-based quickselect / distribution selection minimizing I/Os when data exceeds RAM (O(N/B) I/Os style; see External-Memory Algorithms).
- **k-th element from a data stream / kth largest in a stream** — maintain a size-k min-heap so the heap root is the running k-th largest (O(log k) per element, O(k) space).
- **Frugal / approximate online quantiles** — single-counter estimators (Frugal-1U) tracking a quantile with O(1) memory.

### Advanced Searching Techniques

- **Fractional cascading** — link k sorted lists so a single O(log n) search in the first list yields all subsequent positions, answering "search x in all lists" in O(k + log n) instead of O(k log n) (O(n) space, O(n) build).
- **Dynamic fractional cascading** — maintain the cascaded structure under insertions/deletions for evolving catalog graphs (O(log n) amortized per update).
- **Fractional cascading on arbitrary catalog graphs** — generalization beyond a path of lists to bounded-degree graphs of sorted sets (linear space, fast multi-list queries).
- **Fractional cascading in range trees** — removes a log factor from layered range-tree queries, giving O(log n + occ) orthogonal range reporting (see Computational Geometry).
- **Wavelet tree rank/select & range k-th** — answer "k-th smallest in a subarray" and rank/select over an alphabet in O(log σ) per query (succinct, near-entropy space; see Strings / Succinct Structures).
- **Persistent segment tree for k-th order statistic in a range** — build versioned prefix trees to answer offline/online range k-th smallest in O(log n) per query (O(n log n) space).
- **Merge-sort tree / sqrt-decomposition for range rank & k-th** — count elements ≤ x or find range k-th via sorted blocks (O(log² n) or O(√n · log) per query).
- **Order-statistic tree (rank/select augmentation)** — augment a balanced BST with subtree sizes for O(log n) rank and select queries on a dynamic set.
- **Binary search over a Fenwick/BIT (walk on tree)** — find the smallest prefix index whose cumulative frequency ≥ k in O(log n), used for dynamic k-th element / order statistics.
- **Parallel and cache-oblivious selection** — work-efficient parallel selection and locality-optimized layouts for selection/search on modern hardware.
- **Learned index search** — train a model to predict a key's position then do a small local search around it; sublinear lookups on learnable distributions (model-dependent, empirically fast).
- **Randomized search / hashing membership** — O(1) expected membership via hash tables (see Hashing & Hash Tables) when order is not required.

### Search Correctness, Variants & Pitfalls

- **Monotonicity verification** — confirming the search space truly satisfies the monotone/unimodal property the algorithm assumes (necessary for correctness).
- **Open vs closed interval invariants** — disciplined `[lo,hi]` vs `[lo,hi)` bookkeeping to eliminate off-by-one and infinite-loop bugs.
- **Handling duplicates and tie semantics** — distinguishing "first ≥", "last ≤", strict vs non-strict bounds to return the intended index.
- **Integer-overflow-safe midpoints and bounds** — `mid = lo + (hi−lo)/2`; guarding against overflow in answer-range bisection on large numeric domains.
- **Floating-point termination strategies** — fixed iteration counts and relative tolerance to ensure progress and avoid precision deadlock.
- **Choosing between value-search and index-search** — deciding whether to bisect on positions or on the answer value, and combining both in nested searches.

<sub>[↑ Back to top](#table-of-contents)</sub>

---

## 9. Bit Manipulation & Bitmasking

### Foundations: Binary Representation & Bitwise Operators

#### Number Representations
- **Unsigned binary representation** — nonnegative integer as a sum of powers of two, bit i contributing 2^i (O(w) bits for word width w).
- **Two's complement** — signed encoding where −x = (~x)+1, giving a single zero and wrap-around arithmetic (O(1) negation).
- **One's complement & sign-magnitude (historical)** — alternative signed encodings with two zeros; explains why two's complement is preferred (O(1)).
- **Sign bit & sign extension** — highest bit denotes sign; widening replicates it to preserve value (O(1)).
- **Zero extension** — widening by filling high bits with zero, used for unsigned widening (O(1)).
- **Bit width / word size** — fixed machine width (32/64) bounds all fixed-size bit operations at O(1); arbitrary precision costs O(w/64) per op.
- **Endianness vs bit ordering** — byte order in memory is distinct from logical bit indexing; algorithms use logical bit i = value & (1<<i) regardless of endianness.
- **Base / radix representations** — interpreting numbers in base b (binary, octal, hex, ternary); digit i = (x / b^i) mod b (O(log_b x)).
- **Ternary (base-3) representation** — used when each item has 3 states; digit extraction via repeated div/mod by 3 (O(log_3 n)).
- **Balanced ternary** — base-3 with digits {−1,0,1}, enabling sign-free representation and weighing-problem solutions (O(log_3 n)).
- **Mixed-radix / factorial-number system** — positional system with varying base per digit; encodes permutation ranks compactly (O(n)).
- **Negabinary (base −2)** — represents signed integers without a sign bit using alternating-sign powers (O(w)).

#### Core Bitwise Operators
- **AND (&)** — bitwise conjunction; masks/clears bits, tests membership, computes common bits (O(1)).
- **OR (|)** — bitwise disjunction; sets bits, merges masks (O(1)).
- **XOR (^)** — bitwise difference; toggles bits, self-inverse, addition over GF(2) without carry (O(1)).
- **NOT (~)** — bitwise complement; flips all bits, relates to negation via −x = ~x+1 (O(1)).
- **Left shift (<<)** — multiply by power of two, build masks 1<<k; undefined/overflow past width (O(1)).
- **Logical right shift** — unsigned divide by power of two, zero-fill from top (O(1)).
- **Arithmetic right shift** — signed divide toward −∞ with sign-fill; differs from logical shift on negatives (O(1)).
- **Rotate left / right** — cyclic shift wrapping bits around the word: (x<<k)|(x>>(w−k)) (O(1)).
- **Funnel / double-width shift** — concatenate two words then shift, extracting a window across a boundary (O(1)).
- **NAND / NOR / XNOR / AND-NOT (x & ~y)** — derived operators; AND-NOT (bit clear) isolates "in x but not y" (O(1)).
- **Operator identities & De Morgan over bits** — ~(a&b)=~a|~b, a^b=(a|b)&~(a&b), a+b=(a^b)+2(a&b) etc. for algebraic simplification.
- **Boolean completeness of {NAND}/{NOR}** — any bit function expressible from a single universal gate; relevant to circuit-style modeling (conceptual).

### Single-Bit & Multi-Bit Manipulation Primitives

- **Test bit** — (x >> i) & 1 or x & (1<<i) to read bit i (O(1)).
- **Set bit** — x | (1<<i) forces bit i to 1 (O(1)).
- **Clear bit** — x & ~(1<<i) forces bit i to 0 (O(1)).
- **Toggle bit** — x ^ (1<<i) flips bit i (O(1)).
- **Update bit to value v** — (x & ~(1<<i)) | (v<<i) sets bit i to arbitrary v∈{0,1} (O(1)).
- **Set/clear/toggle a range of bits** — apply a contiguous mask ((1<<r)−1) ^ ((1<<l)−1) to a window (O(1)).
- **Extract a bit field** — (x >> lo) & ((1<<len)−1) reads a multi-bit subfield (O(1)).
- **Deposit a bit field** — clear target window then OR shifted value in (O(1)).
- **Isolate lowest set bit** — x & −x yields the value of the least-significant 1-bit (O(1)).
- **Turn off lowest set bit (Brian Kernighan)** — x & (x−1) clears the lowest 1; iterate for popcount in O(popcount) (O(1) per step).
- **Mask up to and including lowest set bit** — x ^ (x−1) produces ones from bit 0 through the lowest set bit (O(1)).
- **Mask below lowest set bit** — (x & −x) − 1 yields the trailing-zero run as a mask (O(1)).
- **Isolate lowest 0-bit** — ~x & (x+1) yields the lowest unset bit's value (O(1)).
- **Set lowest 0-bit** — x | (x+1) sets the rightmost zero (O(1)).
- **Turn on trailing zeros** — x | (x−1) fills all bits below the lowest set bit (O(1)).
- **Mask of trailing ones** — x & ~(x+1) extracts a run of low-order 1-bits (O(1)).
- **Propagate / fill below MSB** — repeated x |= x>>k smears the highest set bit downward to all lower bits (O(log w)).
- **Clear all bits above lowest set bit** — keep x&−x; complementary to fill operations (O(1)).
- **Count bits in a range mask** — build window mask then popcount to count set bits within [l,r] (O(1)).

### Counting, Scanning & Logarithms

#### Population Count (popcount)
- **Hardware popcount** — single-instruction count of set bits where available (O(1)).
- **Kernighan iteration** — repeatedly clear lowest set bit, counting steps (O(number of set bits)).
- **SWAR / divide-and-conquer popcount** — parallel bit summation with masks 0x5555…, 0x3333…, 0x0F0F… then a multiply-fold (O(log w), branch-free).
- **Table / lookup popcount** — precomputed byte (or 16-bit) tables summed over chunks (O(w/8)).
- **Popcount of a range / prefix popcount** — maintain prefix counts of set bits for O(1) range queries after O(n) build.
- **Popcount via multiply-and-shift fold** — final SWAR step multiplies by 0x0101… to sum byte counts into the top byte (O(1)).
- **Parallel popcount over an array (vectorized)** — Harley–Seal / CSA-tree accumulation of many words (O(n), small constant).

#### Leading/Trailing Zeros & Index of Bits
- **Count trailing zeros (ctz)** — index of lowest set bit; equals popcount((x&−x)−1) or hardware instruction (O(1)).
- **Count leading zeros (clz)** — number of high-order zeros; gives bit-length and floor(log2) (O(1) hw, O(log w) by smearing).
- **Find-first-set / find-last-set** — 1-based index of lowest/highest set bit (O(1)).
- **De Bruijn sequence bit-index** — multiply isolated bit by a De Bruijn constant and table-lookup to get its index without hardware ctz (O(1)).
- **Integer log2 (floor)** — highest set-bit position = w−1−clz(x); via smearing + popcount when no hw (O(log w)).
- **Integer log2 (ceil)** — floor(log2(x−1))+1 for x>1; used to size segment trees / sparse tables (O(1)).
- **Bit length** — number of bits to represent x = floor(log2 x)+1 (O(1)).
- **Integer square-root seeding via bit length** — clz halves the bit length to seed Newton iteration (O(log log) refinement).
- **Next/previous power of two** — round up via smear-then-+1, round down via clearing all but MSB (O(log w)).
- **Test power of two** — x>0 && (x&(x−1))==0 (O(1)).
- **Test power of four** — power of two with set bit at even position via mask 0x5555… (O(1)).

### Bit Hacks & Branch-Free Tricks

- **Swap two values without temp (XOR swap)** — a^=b; b^=a; a^=b; caution when aliased (O(1)).
- **Conditional swap / min-max branchless** — y ^ ((x^y) & −(x<y)) selects min without a branch (O(1)).
- **Absolute value branchless** — (x ^ (x>>(w−1))) − (x>>(w−1)) using sign-fill mask (O(1)).
- **Sign function** — (x>0) − (x<0) or arithmetic shift tricks (O(1)).
- **Negate conditionally** — (x ^ −flag) + flag negates x when flag=1 (O(1)).
- **Clamp / saturate** — combine sign masks to bound values without branches (O(1)).
- **Detect opposite signs** — (x ^ y) < 0 (O(1)).
- **Branchless select / conditional move** — (a & −cond) | (b & ~(−cond)) picks a or b by a 0/1 condition (O(1)).
- **Round up to multiple of power of two** — (x + m−1) & ~(m−1) (O(1)).
- **Round down / align to power of two** — x & ~(m−1) (O(1)).
- **Modulo by power of two** — x & (m−1) for nonnegative x (O(1)).
- **Divide/multiply by power of two** — shifts replace expensive div/mul (O(1)).
- **Check if addition would overflow** — sign-based bit test on operands and result (O(1)).
- **Average without overflow** — (x&y) + ((x^y)>>1) computes floor mean safely (O(1)); ceil via (x|y) − ((x^y)>>1).
- **Conditional set/clear via mask** — w = w ^ ((−f ^ w) & m) sets bits in m to flag f (O(1)).
- **Merge bits per mask** — (a & ~mask) | (b & mask) blends two words bitwise (O(1)).
- **Reverse bits** — divide-and-conquer swap of bit groups with masks (O(log w)); or table-based byte reversal (O(w/8)).
- **Reverse bytes (byte swap)** — endianness conversion via shifts/masks or hw instruction (O(1)).
- **Parity of a word** — fold via XOR-shifts then read low bit, or popcount&1 (O(log w)).
- **Interleave bits (Morton code / Z-order)** — spread two coordinates into one integer for spatial indexing (O(log w) via magic-number spreading).
- **Deinterleave / extract Morton coordinate** — inverse spreading to recover coordinates (O(log w)).
- **Gather/scatter bits (compress/expand)** — compact selected bits to low end or expand them per mask (O(log w) emulated, O(1) with hw PEXT/PDEP).
- **Compute parity-based gray conversions** — see Gray code below.
- **Snoob / same-popcount successor** — next integer with the same number of set bits (Gosper's hack body) (O(1)).
- **Round-trip bit-field pack/unpack** — pack several small integers into one word and recover them with shifts/masks (O(fields)).

### Hardware Bit-Manipulation Intrinsics Toolkit (BMI1 / BMI2 / ABM)

- **Intrinsics overview** — a coherent set of single-instruction primitives that replace multi-step bit tricks; portable algorithms describe the operation, hardware accelerates it (O(1) each).
- **TZCNT (trailing-zero count)** — hardware ctz returning word width on zero input, replacing De Bruijn lookups (O(1)).
- **LZCNT (leading-zero count, ABM)** — hardware clz for fast floor-log2 / bit-length / normalization (O(1)).
- **POPCNT (ABM)** — hardware population count of a word (O(1)).
- **BLSI (isolate lowest set bit)** — computes x & −x in one instruction (O(1)).
- **BLSR (reset lowest set bit)** — computes x & (x−1), the Kernighan clear, in one instruction (O(1)).
- **BLSMSK (mask up to lowest set bit)** — computes x ^ (x−1), ones through the lowest set bit (O(1)).
- **ANDN (AND-NOT)** — computes ~x & y, the bit-clear operator, fused (O(1)).
- **BEXTR (bit-field extract)** — extract a contiguous field given start and length operands (O(1)).
- **PEXT (parallel bits extract, BMI2)** — gather bits selected by a mask into contiguous low-order positions; generalized deinterleave/compress (O(1) hw; emulation O(popcount) or O(log w)).
- **PDEP (parallel bits deposit, BMI2)** — scatter contiguous low-order bits into positions chosen by a mask; generalized interleave/expand (O(1) hw).
- **BZHI (zero high bits)** — clear all bits at and above a given index, a fast variable-width low mask (O(1)).
- **PDEP/PEXT applications** — fast Morton encode/decode, subset ranking/unranking, compress/expand for sparse iteration, board-square mapping (O(1) per op).
- **RORX / SHRX / SHLX / SARX (flag-free shifts, BMI2)** — shifts/rotates that don't disturb flags, aiding branch-free pipelines (O(1)).
- **Microcode caveat** — PDEP/PEXT are microcoded (slow) on some older AMD parts; algorithm choice should not assume O(1) universally (practical note).

### Subset & Submask Enumeration

- **Set as bitmask** — n-element set encoded in an n-bit integer; element i present iff bit i set (O(1) membership ops).
- **Enumerate all subsets of n elements** — iterate masks 0..2^n−1 (O(2^n)).
- **Enumerate submasks of a fixed mask** — s = (s−1) & mask loop visits every subset of mask (O(number of submasks); total over all masks is O(3^n)).
- **Enumerate supersets of a mask** — iterate over masks containing a fixed mask via complement-submask trick (O(2^(n−popcount))).
- **3^n submask-of-submask identity** — summing 2^popcount(mask) over all masks equals 3^n, the cost of all-submask enumeration.
- **Enumerate subsets of fixed size k (combinations)** — Gosper's hack: next k-subset via lowest-bit and carry arithmetic (O(1) per subset, O(C(n,k)) total).
- **Gosper variant for ranking/unranking** — combinatorial-number-system rank ↔ k-subset mask map for indexed access without full enumeration (O(k·log n) or O(k) per direction).
- **Next/previous same-popcount permutation** — lexicographic successor/predecessor of a bitmask preserving set-bit count (Gosper successor body) (O(1)).
- **Iterate set bits efficiently** — repeatedly process and clear x&−x to visit only present elements (O(popcount)).
- **Iterate cleared bits** — operate on the complement to walk the absent elements (O(n−popcount)).
- **Complement / universe mask** — full = (1<<n)−1; complement = full ^ mask for set difference and "remaining" elements (O(1)).
- **Lexicographic next bit permutation** — same-popcount successor used in combination generation (O(1)).
- **Subset cardinality bucketing** — group masks by popcount for layered DP / convolution passes (O(2^n)).

### Sum / Transform over Subsets (SOS family)

- **Sum over Subsets (SOS) DP** — for each mask, aggregate f over all its submasks via n-dimensional prefix-sum sweep (O(n·2^n)).
- **Sum over Supersets (SOS variant)** — symmetric sweep aggregating over masks that contain each mask (O(n·2^n)).
- **Subset-sum convolution / OR-convolution** — combine two functions over subset/superset relations using SOS forward+inverse transforms (O(n·2^n)).
- **AND-convolution** — multiply pointwise after superset-zeta transform, then invert (Möbius) (O(n·2^n)).
- **XOR-convolution (Walsh–Hadamard transform)** — fast Walsh–Hadamard transform multiplies in the parity domain (O(n·2^n)) (see also Polynomials/FFT).
- **Zeta / Möbius transform over subset lattice** — forward SOS (zeta) and its inverse (Möbius) for inclusion-exclusion over masks (O(n·2^n)).
- **Subset-sum convolution with rank (popcount layering)** — exact set convolution avoiding double counting via popcount-indexed polynomials (O(n^2·2^n)).
- **Supersubset / k-th layer SOS** — partial sweeps restricted to bit positions for incremental queries (O(n·2^n)).
- **Yates' algorithm (general fast zeta)** — unified in-place dimension-by-dimension transform underlying all SOS variants (O(n·2^n)).

### Bitmask Dynamic Programming
- **Bitmask DP over subsets** — state encodes a set; transitions add/remove elements (typically O(2^n·n) or O(3^n)) (see Dynamic Programming).
- **Traveling Salesman (Held–Karp)** — dp[mask][last] over visited set (O(2^n·n^2)) (see Dynamic Programming).
- **Assignment / minimum-cost matching via masks** — dp[mask] assigning first popcount(mask) items (O(2^n·n)) (see Dynamic Programming).
- **Broken-profile / plug DP** — DP across a grid carrying a frontier bitmask (O(cells·2^width)) (see Dynamic Programming).
- **Connection / connectivity-profile DP** — broken-profile with connectivity labels for Hamiltonian/spanning counts (see Dynamic Programming).
- **Subset-sum / partition via bit-DP** — see also bitset speedups below (see Dynamic Programming).
- **SOS-accelerated bitmask DP** — replace O(3^n) submask transitions with O(n·2^n) SOS sweeps (see Dynamic Programming).
- **Set-partition / graph-coloring via subset DP** — iterate submasks as independent sets to count colorings (O(3^n)) (see Dynamic Programming).

### Bitsets & Word-Level Parallelism

- **Bitset data structure** — fixed/dynamic array of bits packed into words; AND/OR/XOR/NOT/shift over n bits in O(n/w).
- **Boolean DP / reachability speedup** — represent rows as bitsets to cut a factor of word width (O(n^2/w)).
- **Bitset subset-sum / knapsack feasibility** — shift-and-OR a reachability bitset to mark attainable sums (O(n·S/w)).
- **Bitset transitive closure (Warshall)** — OR rows when an edge exists (O(V^3/w)) (see Graphs).
- **Bitset string matching (Shift-And / Shift-Or)** — bit-parallel automaton for pattern matching (O(n·⌈m/w⌉)) (see Strings).
- **Bitset edit distance / LCS (bit-parallel)** — Myers' algorithm packs DP columns into words (O(n·⌈m/w⌉)) (see Strings/DP).
- **Bitset count/scan operations** — popcount, find-next-set across words for sparse iteration (O(n/w)).
- **Bitset as a frontier in BFS** — represent visited/queue layers as bitsets for dense graphs (O(V^2/w)) (see Graphs).
- **Block / partial-word handling** — masking the final partial word to ignore padding bits (O(1) per op).
- **Bitset shift by arbitrary k** — cross-word carry propagation for whole-vector shifting (O(n/w)).
- **Bitset maximum matching / Hopcroft–Karp acceleration** — pack adjacency rows to speed augmenting searches (O(E·V/w)) (see Graphs).

### Linear Algebra over GF(2) — XOR Basis

- **XOR linear basis (Gaussian elimination over GF(2))** — maintain ≤w independent vectors; insert by reducing against higher pivots (O(w) per insert, O(n·w) build).
- **Max XOR subset** — greedily XOR basis vectors from high bit to low to maximize result (O(w)).
- **Min XOR / k-th smallest XOR value** — reduced row-echelon basis enumerates the 2^rank values in order (O(w) per query).
- **Count distinct XOR values** — 2^rank reachable values; rank from basis size (O(1) after build).
- **Check representability** — reduce a target against basis; representable iff it reduces to 0 (O(w)).
- **Linear (in)dependence test** — insertion failure indicates dependence (O(w)).
- **Basis intersection / merge** — combine two GF(2) subspaces (merge O(w^2)); intersection via Zassenhaus-style elimination (O(w^2)).
- **Online basis with deletion (segment-tree of bases / offline)** — support insert+query under removals via divide-and-conquer over time (O(n log n · w)).
- **Prefix-XOR basis with positions** — store latest index per pivot to answer "max XOR using a suffix" queries (O(w) per query).
- **Weighted / time-indexed basis (greedy by weight)** — keep the highest-priority vector per pivot for "max XOR within constraints" (O(w) per op).
- **Gaussian elimination over GF(2) (matrices)** — solve linear systems / rank / nullspace mod 2 using bitset rows (O(n·m·k/w)) (see Linear Algebra).
- **Kernel / null space over GF(2)** — free-variable enumeration for solution count 2^(dim ker) (see Linear Algebra).

### Carry-less Arithmetic & GF(2) Polynomials (GF(2) of x)

- **Carry-less multiplication (CLMUL)** — multiply two bit-vectors as polynomials over GF(2): XOR-accumulate shifted copies instead of carrying (O(w^2) shift-XOR, O(1) with hardware PCLMULQDQ).
- **Polynomial addition / subtraction over GF(2)** — both are bitwise XOR; no carries in the polynomial ring (O(1)).
- **Polynomial division & remainder over GF(2)** — schoolbook long division using XOR and degree comparison via bit length (O(deg·w)).
- **Reduction modulo an irreducible polynomial** — fold the carry-less product back into the field GF(2^k) (O(k) with a sparse modulus).
- **GF(2^k) field arithmetic** — multiply via CLMUL then reduce; build multiplicative inverses for finite-field algorithms (O(k) per op).
- **CRC computation via carry-less multiply** — fold data blocks with precomputed constants instead of bit-by-bit shift registers (O(n/block)).
- **GHASH / polynomial hashing over GF(2)** — universal hashing and authenticated-encryption tags built on carry-less products (O(n)).
- **Carry-less prefix-XOR (Hilbert/space-filling helper)** — CLMUL by all-ones computes parity of each bit and bits to its right in one step (O(1) hw).
- **XOR (Nim) multiplication** — carry-less-style product underlying Nimber field arithmetic for combinatorial games (see Game Theory).

### Bit-Board Representation & Attack-Mask Generation

- **Bit-board** — encode a 2D board (e.g., 8×8) as a single 64-bit word, one bit per cell, enabling set operations on whole positions (O(1) per board op).
- **Occupancy / piece bitboards** — separate bitboards per piece type and color; unions/intersections give combined state (O(1)).
- **Square indexing & coordinate mapping** — bit i ↔ (rank, file); shifts move a whole set one step in a direction (O(1)).
- **Directional shifts with wrap masks** — shift then AND a file/rank mask to suppress board-edge wraparound (O(1)).
- **Knight / king / pawn attack masks** — precomputed per-square attack bitboards looked up by square index (O(1) per query, O(64) precompute).
- **Sliding-piece attacks by ray scanning** — walk a direction shifting until a blocker; classical/Kogge–Stone fill computes whole rays branch-free (O(1) per direction).
- **Kogge–Stone parallel-prefix fill** — flood a set of sliders along a direction in log-many shift/OR steps (O(log size)).
- **Magic bitboards** — multiply masked blocker bits by a per-square "magic" constant and shift to index a dense attack table (O(1) query after precompute).
- **Fancy / PEXT bitboards** — replace the magic multiply with hardware PEXT to compress blocker bits into a table index (O(1)).
- **Attack-set aggregation & mobility counts** — OR per-piece attacks then popcount for evaluation features (O(pieces)).
- **Move enumeration via bit iteration** — pop lowest set bit of an attack mask to enumerate destination squares (O(moves)).
- **Flood-fill / connectivity on bit-boards** — iterative shift-OR with a free-cell mask for region growth (O(diameter)) (see Graphs).

### XOR Properties & Prefix Techniques

- **XOR algebra** — associative, commutative, self-inverse (a^a=0), identity 0; enables cancellation tricks (O(1)).
- **Prefix XOR array** — P[i]=a[0]^…^a[i−1]; subarray XOR = P[r]^P[l] (O(n) build, O(1) query).
- **XOR of subarray with hashing** — count subarrays with target XOR via prefix-XOR frequency map (O(n)).
- **Find the single non-paired element** — XOR all elements cancels pairs (O(n)).
- **Find two unpaired elements** — split by a differing bit (lowest set bit of total XOR) then XOR each group (O(n)).
- **Find the element appearing not k times** — per-bit modular counting isolates the odd-one-out for k>2 (O(n·w)).
- **Missing number / duplicate via XOR** — XOR indices with values to isolate the anomaly (O(n)).
- **XOR of 1..n closed form** — pattern depending on n mod 4 (O(1)).
- **XOR range [l,r]** — prefixXor(r) ^ prefixXor(l−1) using the closed form (O(1)).
- **XOR on a trie (binary trie)** — insert numbers bit-by-bit; greedily walk opposite bits for max-XOR-pair queries (O(w) per op).
- **Persistent / mergeable XOR trie** — count XOR values in a range, k-th maximum XOR with prefix tries (O(w log) per query).
- **XOR-pair counting under threshold** — trie subtree-size counts answer "pairs with XOR ≤ k" (O(n·w)).
- **Nim-value / Grundy XOR (game theory)** — XOR of pile sizes determines Nim outcome (O(piles)) (see Game Theory).

### Gray Code & Reflected Orderings

- **Binary-to-Gray conversion** — g = x ^ (x>>1); consecutive codes differ by one bit (O(1)).
- **Gray-to-binary conversion** — cumulative XOR of shifted prefixes (O(log w)).
- **Standard reflected Gray code sequence** — enumerate all 2^n masks changing one bit per step (O(2^n)).
- **k-th Gray code & inverse rank** — direct formula to/from index (O(log w)).
- **Gray code Hamiltonian path on hypercube** — single-bit-flip tour of all subsets, used in min-change enumeration (O(2^n)).
- **Bit which flips at step i** — equals ctz(i+1) in the standard Gray order, enabling incremental DP updates (O(1) amortized).
- **Non-binary / mixed-radix Gray codes** — reflected codes over base-b digits changing one digit per step (O(product of radices)).
- **Balanced / monotone Gray codes** — variants equalizing bit-transition counts or with monotone weight, used in coding theory (construction O(2^n)).

### Sieves, Number Tricks & Misc Bit Techniques

- **Bitwise / packed sieve of Eratosthenes** — store primality in a bitset, skipping evens for memory (O(n log log n) time, n/16 bytes) (see Number Theory).
- **Segmented bit sieve** — sieve windows with a small bitset for large ranges (see Number Theory).
- **Bit tricks for divisibility by 2^k / extracting factors of two** — ctz gives the 2-adic valuation (O(1)).
- **GCD bit-shift speedups (binary/Stein's GCD)** — remove common factors of two via ctz, subtract (O(log^2 max)) (see Number Theory).
- **Fast exponentiation by squaring (binary exponentiation)** — process exponent bits; multiply when bit set (O(log e)) (see Number Theory).
- **Russian-peasant multiplication** — shift-and-add multiplication driven by bits of a multiplier (O(log b)).
- **Popcount-based combinatorial identities** — Kummer/Lucas-style carry counting via popcount of sums (O(w)) (see Number Theory).
- **Subset / mask hashing** — use packed bitmask as a hash-map key for memoized subset DP (O(1) expected per op).
- **Bit-packed state hashing for search** — encode game/grid state in an integer key for visited-set membership (O(1)).

### Meet-in-the-Middle on Bits

- **Split-set meet-in-the-middle (subset sum)** — split n elements into halves, enumerate 2^(n/2) sums each, combine by sort+two-pointer/binary search (O(2^(n/2)·(n/2))).
- **MITM for max-XOR / target-XOR** — enumerate half-subset XORs, query the other half against a trie/hash (O(2^(n/2)·w)).
- **MITM with constraints (capacity, count)** — pair halves under monotone matching for knapsack-like limits (O(2^(n/2) log)).
- **4-sum / k-sum via bitmask halves** — generalize MITM splitting to count solutions (O(2^(n/2)) space) (see also Two Pointers/Hashing).
- **Schroeppel–Shamir refinement** — reduce MITM memory using priority queues over quarter-sets (O(2^(n/2)) time, O(2^(n/4)) space).

### Hard / Advanced & Lesser-Known Topics

- **Fast Walsh–Hadamard Transform (FWHT)** — XOR/AND/OR convolutions in the spectral domain (O(n·2^n)) (see Polynomials & Transforms).
- **Subset convolution via ranked Möbius** — exact set-union convolution (O(n^2·2^n)).
- **Trie-based persistent max-XOR with weights** — answer "max XOR with value ≤ k / within range" using sorted/persistent binary tries (O(w log n)).
- **Linear basis over polynomials / GF(2^k)** — extend XOR-basis ideas to vector spaces over extension fields (O(k^2) per op).
- **Bit-parallel longest common subsequence / approximate matching** — pack DP states into machine words for large speedups (O(n·m/w)) (see Strings).
- **SIMD-within-a-register (SWAR) arithmetic** — perform parallel byte/nibble operations using masks and carries in a single word (O(1) per packed op).
- **Broadword / bit-trick rank-select structures** — succinct rank/select on bit-vectors via popcount-of-block precomputation (O(1) query, o(n) extra space) (see Data Structures).
- **Hamming weight / Hamming distance via popcount(x^y)** — bitwise distance metric for nearest-neighbor and codes (O(w)).
- **Bitmask + SOS hybrid counting** — count pairs/tuples satisfying AND/OR/XOR constraints by combining masks with zeta/Möbius (O(n·2^n)).
- **Sideways addition / parallel-prefix popcount networks** — tree-of-adders summing bits, basis of SWAR popcount (O(log w)).
- **Bit reversal permutation (FFT ordering)** — reverse low log n bits to reorder arrays for in-place transforms (O(n log n) naive, O(n) incremental) (see Polynomials/FFT).
- **Magic-number bit spreading constants** — precomputed masks for interleave/deinterleave, reverse, and field gather/scatter (O(log w)).
- **Hilbert curve index via bit interleave + Gray** — map 2D coordinates to a locality-preserving 1D index using rotated bit pairs (O(log w)) (see Geometry/Spatial).
- **Error-correcting codes over GF(2)** — Hamming/parity-check codes, syndrome decoding via XOR of received bits (O(n)) (see Coding Theory).
- **Bit-sliced / SIMD batch evaluation** — transpose many small instances into bit-planes to evaluate in lockstep (O(work/w)).

<sub>[↑ Back to top](#table-of-contents)</sub>

---

## 10. Prefix Sums, Two Pointers & Sliding Window

### Prefix Sums (1D)

#### Core Prefix Sum
- **Prefix sum array** — precompute cumulative sums so any range sum becomes a single subtraction `P[r]-P[l-1]` (build O(n), query O(1)).
- **Range sum query (static)** — answer arbitrary `[l,r]` sums in O(1) after O(n) preprocessing; no updates supported.
- **1-indexed sentinel convention** — define `P[0]=0` so `sum(l,r)=P[r]-P[l-1]` needs no edge-case branch (O(1) per query).
- **Average / mean of subarray** — derive segment average as `(P[r]-P[l-1])/(r-l+1)` directly from prefix sums (O(1)).
- **Count of elements satisfying predicate** — prefix sum over an indicator array gives count in any range (O(1) per query).
- **Prefix sum of frequency/count array** — cumulative frequency turning "how many ≤ x" into O(1) lookups; basis of counting sort and rank queries.

#### Prefix Variants on Other Operations
- **Prefix product** — cumulative products for range product queries; needs care with zeros and overflow (build O(n), query O(1)).
- **Range product excluding self** — product of all elements except index i via prefix·suffix products without division (O(n)).
- **Prefix XOR** — cumulative XOR enabling range XOR as `X[r]^X[l-1]`; XOR self-inverse so no negative-index issue (O(1) query).
- **Prefix OR / prefix AND (non-invertible)** — monotone bitwise accumulations; not range-decomposable, used with sparse-table-style or sliding logic instead.
- **Prefix GCD / prefix LCM** — cumulative gcd/lcm; not invertible so range queries need sparse table or two-pointer monotonicity (build O(n log V)).
- **Prefix counts modulo k** — track running sum mod k to count subarrays with sum divisible by k (O(n) with a hashmap of residues).
- **Prefix sum under modular arithmetic** — maintain sums mod m; subtraction needs `(P[r]-P[l-1]+m) mod m` to stay non-negative (O(1) query).
- **Weighted / index-weighted prefix sum** — accumulate `i·a[i]` to answer weighted range sums or compute positional contributions (O(1) query).

#### Suffix Sums and Combinations
- **Suffix sum array** — cumulative sums from the right; symmetric to prefix sums for right-anchored queries (build O(n)).
- **Suffix max / suffix min** — running extrema from the right; pairs with prefix extrema for split-point decisions (O(n)).
- **Prefix+suffix split optimization** — precompute both directions to evaluate every partition point in O(1) (total O(n)).
- **Prefix-min / prefix-max array** — running best-so-far; underpins best-buy-sell, max-gap, and many one-pass scans (O(n)).
- **Prefix sum of prefix sums (double prefix)** — second-order accumulation answering sum-of-range-sums or arithmetic-series queries in O(1).

### Prefix Sum Applications and Idioms

- **Subarray-sum-equals-k (hashmap of prefixes)** — count/find subarrays summing to k via complement `P[i]-k` lookups (O(n) time, O(n) space).
- **Longest subarray with sum k** — store earliest prefix index per value to maximize length (O(n)).
- **Subarray sum divisible by k** — group equal prefix-mod residues; count pairs per residue class (O(n)).
- **Equilibrium / pivot index** — find index where left sum equals right sum using total minus prefix (O(n)).
- **Maximum subarray sum via prefix + running min** — `P[r]-min(P[0..r-1])`; equivalent reformulation of Kadane (O(n)) (see Dynamic Programming).
- **Count subarrays with bounded sum (atMost trick)** — `exactly(k)=atMost(k)-atMost(k-1)` over sliding/prefix counts (O(n)).
- **Range update + single query offline** — apply all updates as prefix increments, then one prefix pass materializes values (O(n+q)).
- **Balanced binary string / +1−1 mapping** — map two symbols to ±1 and use prefix sums to find balanced or longest-balanced segments (O(n)).
- **Prefix sum on tree (root-to-node)** — accumulate values along paths; combine with LCA for path sums (build O(n), query via LCA) (see Trees & LCA).
- **Histogram / coordinate frequency prefix** — cumulative bucket counts for percentile, median-in-range, and order-statistic queries (O(1) per query after build).

### 2D and Higher-Dimensional Prefix Sums

#### 2D Prefix Sum
- **2D prefix sum (summed-area table / integral image)** — `S[i][j]` = sum of rectangle from origin; build via inclusion-exclusion (O(nm) build).
- **Submatrix sum query** — any axis-aligned rectangle sum in O(1) by 4-term inclusion-exclusion of corners.
- **Inclusion-exclusion identity** — `S[i][j]=a+S[i-1][j]+S[i][j-1]-S[i-1][j-1]`; the core recurrence and query both rely on it.
- **Integral image in vision** — Viola-Jones / Haar-feature evaluation using summed-area tables for constant-time box filters (O(1) per feature).
- **2D prefix on counts (point-in-rectangle)** — count lattice points or marked cells inside any rectangle in O(1) after O(nm) build.
- **2D prefix XOR / product** — same scheme on invertible ops; XOR needs the inclusion-exclusion adapted to its self-inverse (O(1) query).

#### Beyond 2D
- **3D / k-D prefix sum** — generalized inclusion-exclusion with `2^k` corner terms per query (build O(N), query O(2^k)).
- **Dimension-by-dimension (SOS-style) accumulation** — sweep one axis at a time to build k-D prefix sums in O(k·N) instead of naive blowup.
- **Sum over subsets / superset (SOS DP)** — subset-sum convolution viewed as prefix-sum over the boolean hypercube (O(n·2^n)) (see Dynamic Programming / Bitmask).
- **Rotated / diagonal prefix sums** — prefix sums along diagonals (i+j and i−j) for diamond/rotated-rectangle queries (O(nm) build).
- **Prefix sums on triangular / staircase regions** — specialized accumulation for non-rectangular query shapes via decomposition into rectangles.

### Difference Arrays (Imos Method)

#### 1D Difference Array
- **1D difference array** — store `d[i]=a[i]-a[i-1]`; a range add becomes two point updates, prefix sum reconstructs (update O(1), build O(n)).
- **Range-update / point-query (offline)** — apply many `+v` on `[l,r]` as `d[l]+=v; d[r+1]-=v`, then one prefix pass yields all values (O(n+q)).
- **Imos method (いもす法)** — event-stamp increments at interval boundaries, accumulate once; standard for counting overlaps (O(n+q)).
- **Booking / interval-overlap counting** — max simultaneous active intervals via difference-array stamping plus prefix max (O(n+q)).
- **Difference of difference (second-order imos)** — two-level differencing to apply range additions of linear/ramp functions in O(1) each.
- **Arithmetic-progression range update** — add an AP over a range using second-order difference arrays (O(1) per update, O(n) finalize).

#### 2D / Higher Difference Arrays
- **2D difference array** — submatrix `+v` via four corner stamps `(+,−,−,+)`; reconstruct with 2D prefix sum (update O(1), build O(nm)).
- **2D imos for rectangle stacking** — count overlapping rectangles / paint-fill counts after batched updates (O(nm+q)).
- **k-D difference array** — `2^k` corner stamps per range update mirroring k-D prefix reconstruction (update O(2^k)).
- **Difference array on trees (subtree/path)** — stamp at endpoints + LCA for path updates, finalize by subtree accumulation (O(n+q)) (see Trees & LCA).

### Two Pointers — Opposite Ends (Converging)

- **Converging two pointers** — left/right pointers move inward based on a comparison; each step shrinks the search space (O(n)).
- **Two-sum on sorted array** — pointers from both ends, move by comparing pair sum to target (O(n), O(1) extra).
- **Pair with closest sum to target** — track best pair while converging; update on each comparison (O(n)).
- **Count pairs with sum < / ≤ target (sorted)** — when `a[l]+a[r]≤t`, all `r-l` pairs qualify; advance left (O(n)).
- **Container-with-most-water** — move the pointer at the shorter line inward to possibly increase area (O(n)).
- **Trapping rain water (two pointers)** — track left/right max walls, advance the smaller side (O(n), O(1) space).
- **Valid palindrome check** — converging pointers comparing mirrored chars, optionally with one deletion allowed (O(n)).
- **Reverse array / string in place** — swap converging ends until they meet (O(n), O(1) space).
- **3-sum (sort + two pointers)** — fix one element, two-pointer the remaining sorted suffix; skip duplicates (O(n²)).
- **3-sum closest** — fixed element plus converging pointers minimizing |sum−target| (O(n²)).
- **4-sum / k-sum (recursive reduction)** — recurse fixing elements down to a two-pointer base case; prune by sorted bounds (O(n^{k-1})).
- **Two-pointer merge of sorted partitions** — combine two sorted halves from the ends (used in some in-place schemes) (O(n)).

### Two Pointers — Same Direction

- **Same-direction two pointers** — slow/fast (or read/write) advancing forward, never backtracking; amortized linear (O(n)).
- **Read/write compaction** — in-place removal/dedup where a write pointer trails a read pointer (O(n), O(1) space).
- **Remove duplicates from sorted array** — write pointer keeps unique prefix as read pointer scans (O(n)).
- **Remove element / move zeros** — partition kept vs removed elements with a stable write pointer (O(n)).
- **Two-pointer on two arrays (intersection/union)** — advance the pointer at the smaller element to merge/intersect sorted lists (O(n+m)).
- **Backspace-string compare (two pointers from end)** — process backspaces lazily scanning right-to-left (O(n), O(1) space).
- **Partition by predicate (stable/unstable)** — collect elements satisfying a condition to the front via a fill pointer (O(n)).
- **Strobogrammatic / mirror construction** — build/verify symmetric structures with paired write positions (O(n)).
- **Longest mountain / bitonic scan** — expand around peaks using directional pointers (O(n)).

### Sliding Window — Fixed Size

- **Fixed-size window scan** — maintain an aggregate over exactly k elements, sliding by add-right/remove-left (O(n)).
- **Fixed-window sum / average** — running sum updated in O(1) per slide; max-average-subarray of length k (O(n)).
- **Fixed-window distinct count** — hashmap of counts; track size of map as window slides (O(n)).
- **Fixed-window anagram / permutation match** — compare frequency vectors of pattern vs window via a match-counter (O(n·Σ) or O(n)).
- **Sliding window maximum/minimum** — monotonic deque maintaining candidates; each element pushed/popped once (O(n)).
- **Fixed-window median** — two balanced heaps (or multiset / ordered structure) with lazy deletion as window slides (O(n log k)).
- **Fixed-window mode / most-frequent** — counts plus a max-frequency tracker; handles decrements on eviction (O(n) amortized).
- **Window hashing (rolling hash)** — Rabin-Karp polynomial hash updated in O(1) per slide for substring matching (O(n)) (see Strings).
- **Bitset window** — represent window membership as a bitset for fast set ops over small universes (O(n·U/word)).

### Sliding Window — Variable Size

#### Window Mechanics
- **Variable-size window (expand/contract)** — grow right unconditionally, shrink left while the window violates a constraint (amortized O(n)).
- **Monotone-feasibility requirement** — technique works only when "shrinking restores validity / growing breaks it" is monotone in window length.
- **atMost(k) − atMost(k−1) decomposition** — convert "exactly k" window problems into two "at most" sliding passes (O(n)).
- **Two-pointer with running aggregate** — maintain sum/count/freq incrementally so each move is O(1) (total O(n)).

#### Longest / Shortest Subarray-or-Substring Constraints
- **Longest substring without repeating chars** — last-seen positions; jump left past the duplicate (O(n)).
- **Longest substring with at most k distinct** — hashmap count; shrink while distinct > k (O(n)).
- **Longest substring with at most two distinct** — classic special case of the k-distinct window (O(n)).
- **Longest substring with exactly k distinct** — `atMost(k)−atMost(k−1)` or dual-pointer bookkeeping (O(n)).
- **Smallest subarray with sum ≥ target (positive values)** — expand to reach target, contract to minimize (O(n)).
- **Minimum window substring (covering all chars)** — need/have counts with a satisfied-counter; shrink when complete (O(n+m)).
- **Longest subarray with sum ≤ S / product < P** — shrink on violation; works because values are non-negative/positive (O(n)).
- **Longest subarray with at most k zeros (max ones after k flips)** — window over a binary array bounded by allowed flips (O(n)).
- **Longest substring after at most k character replacements** — track window length minus max-frequency char (O(n)).
- **Fruit-into-baskets (≤2 types)** — applied at-most-2-distinct window (O(n)).
- **Subarrays with bounded max element (atMost trick)** — count windows whose max lies in a range via two thresholds (O(n)).
- **Count subarrays where product < k (positive)** — variable window adding `r-l+1` per valid right end (O(n)).
- **Longest nice / balanced substring** — frequency/bitmask constraints maintained incrementally as window slides (O(n)).

#### Window with Auxiliary Structures
- **Window min/max via monotonic deque** — same structure used inside variable windows to test extremum constraints (amortized O(1) per step).
- **Window with sorted multiset / BST** — maintain order statistics (kth, median, count-in-range) under insert/erase as window moves (O(n log n)).
- **Window with two heaps (lazy deletion)** — median/percentile maintenance with deferred removals (O(n log n)).
- **Window with Fenwick/BIT over values** — count/rank queries inside the current window over a coordinate-compressed domain (O(n log n)) (see Fenwick/Segment Trees).
- **Window with frequency map + threshold counter** — O(1) tests for "all counts ≤ t" or "exactly c satisfied" while sliding (O(n)).
- **Window max of (value − index) or affine forms** — monotonic deque on transformed keys for constrained-distance optimizations (O(n)).

### Monotonic Deque / Queue / Stack

- **Monotonic deque (general)** — keep a deque sorted so the front is the current min/max; pop violating elements from back, expire from front (O(n) total).
- **Decreasing deque for window max** — back pops smaller incoming values; front holds current maximum (O(n)).
- **Increasing deque for window min** — symmetric structure for minima (O(n)).
- **Monotonic queue for DP transitions** — maintain best `dp` candidate in a sliding range to speed 1D DP (O(n)) (see DP optimizations / "sliding-window DP").
- **Monotonic stack (NGE/NSE)** — next/previous greater-or-smaller element via a stack maintaining monotonic order (O(n)).
- **Largest rectangle in histogram** — monotonic stack tracking spans where each bar is the minimum (O(n)).
- **Maximal rectangle / submatrix of 1s** — per-row histogram heights + monotonic stack (O(nm)).
- **Stock span / daily temperatures** — monotonic stack computing distance to next greater element (O(n)).
- **Sum/contribution of subarray minimums-maximums** — count subarrays where each element is the extremum using NGE/NSE spans (O(n)).
- **Sliding window with min and max both bounded** — two monotonic deques to keep `max−min ≤ limit` while sliding (O(n)).

### Two-Sum / k-Sum Pattern Family

- **Two-sum (hashmap, unsorted)** — store complements seen so far for O(n) lookup (O(n) time, O(n) space).
- **Two-sum (sorted, two pointers)** — converging pointers without extra space (O(n), O(1)).
- **Two-sum count / all-pairs** — count pairs with given sum via frequency map, careful with self-pairs and duplicates (O(n)).
- **3-sum (zero/target)** — sort then fix-one + two-pointer; dedup at all levels (O(n²)).
- **3-sum smaller / count triples below target** — two-pointer counting `r-l` per fixed element (O(n²)).
- **4-sum and general k-sum** — recursive reduction to two-sum base, with sorted-bound pruning (O(n^{k-1})).
- **Subset-sum / meet-in-the-middle two-sum** — split set, sort halves, two-pointer or hash to combine (O(2^{n/2})) (see Bitmask/MITM).
- **Two-sum over streaming / data-structure design** — add+find API backed by a frequency map (add O(1), find O(n)).
- **Pair-sum closest across two arrays** — sort both, converge pointers minimizing |a+b−t| (O(n+m)).

### Partitioning and Color/Flag Schemes

- **Dutch national flag (3-way partition)** — three pointers (low/mid/high) sort 0/1/2 (or <,=,> pivot) in one pass (O(n), O(1) space).
- **Lomuto partition (2-way)** — single fill pointer for `< pivot`; basis of quicksort (O(n)).
- **Hoare partition (2-way)** — converging pointers swapping inversions across the pivot; fewer swaps than Lomuto (O(n)).
- **3-way quicksort partition** — Dutch-flag pivoting to handle many duplicates efficiently (O(n) per level) (see Sorting).
- **Partition around k-th / Quickselect step** — single partition pass to place a pivot at its sorted position (O(n) expected) (see Selection).
- **Stable partition (extra space)** — preserve relative order while splitting by predicate (O(n) time, O(n) space).
- **Two-color in-place segregation** — single read/write pointer for binary partition (move zeros/odds-evens) (O(n)).
- **Wiggle / alternating arrangement** — pass enforcing `a[i] ≤/≥ a[i+1]` alternation by local swaps (O(n)).
- **Segregate by sign / parity** — partition pointers grouping categories without full sort (O(n)).

### Fast & Slow Pointers and Cycle Detection

- **Fast & slow (tortoise-hare) pointers** — one pointer advances twice as fast to detect meeting/midpoint (O(n), O(1) space).
- **Cycle detection in linked list / functional graph** — fast/slow meet inside a cycle if one exists (O(n), O(1)).
- **Floyd's cycle-finding (full algorithm)** — detect, then find cycle start by resetting one pointer to head; also returns cycle length (O(n), O(1)).
- **Brent's cycle detection** — power-of-two leap schedule; typically fewer steps than Floyd and reports period directly (O(μ+λ)).
- **Cycle length and entry computation** — derive λ (period) and μ (tail) from meeting point arithmetic (O(λ+μ)).
- **Find duplicate number (index-as-pointer)** — treat values as next-pointers and apply Floyd to locate the repeated value (O(n), O(1)).
- **Middle of linked list** — slow lands on midpoint when fast hits the end (O(n)).
- **Nth-from-end (gap pointers)** — advance lead pointer n steps, then move both until lead ends (O(n)).
- **Happy-number / iterated-function loop detection** — Floyd/Brent on the digit-square map to detect convergence to a cycle (O(log n) per step).
- **Palindrome linked list** — fast/slow to midpoint, reverse second half, compare (O(n), O(1) space).
- **Pollard's rho factorization** — Floyd/Brent cycle detection on a pseudo-random map mod n to find a factor (O(n^{1/4}) expected) (see Number Theory).

### Merging via Two Pointers

- **Merge two sorted arrays** — two read pointers emitting the smaller element (O(n+m)).
- **Merge step of merge sort** — the core two-pointer merge of sorted halves (O(n)) (see Sorting).
- **In-place merge (gap method / Shell-merge)** — merge two adjacent sorted runs with O(1) extra via decreasing gap comparisons (O(n log n) variant).
- **Merge k sorted lists (heap vs pairwise)** — pairwise two-pointer merges or a k-way heap merge (O(N log k)).
- **Two-pointer set operations** — union, intersection, difference, symmetric difference of sorted sequences (O(n+m)).
- **Counting inversions during merge** — accumulate cross-pair inversions in the merge step (O(n log n)) (see Divide & Conquer).
- **Sorted-list median / kth via paired pointers** — advance two pointers to reach the kth smallest across two sorted arrays (O(k)) or binary-search variant (O(log)).
- **Merge intervals** — sort by start, sweep merging overlapping intervals with one pass (O(n log n)).
- **Interval list intersection (two pointers)** — converge two sorted interval lists emitting overlaps (O(n+m)).

### Sweep via Pointers (1D Event Sweep)

- **Line sweep with sorted events** — process start/end events in order maintaining a running active count/state (O(n log n)) (see Sweep Line / Geometry).
- **Two-pointer event sweep** — paired start/end pointers over sorted endpoints without an explicit event queue (O(n log n) sort + O(n) scan).
- **Maximum interval overlap / meeting rooms** — sweep start and end times to find peak concurrency (O(n log n)).
- **Minimum platforms / resource peak** — difference-array or two-pointer sweep over arrivals and departures (O(n log n)).
- **Skyline-style sweep (1D projection)** — sweep x-events updating active heights (O(n log n)) (see Sweep Line).
- **Offline range-query sweep (Mo-adjacent)** — sort queries to reuse a sliding two-pointer window between consecutive queries.
- **Coordinate-compressed sweep** — compress event coordinates to index difference arrays / Fenwick trees (O(n log n)) (see Compression).

### Offline Window/Range Query Techniques

- **Mo's algorithm (sqrt decomposition of queries)** — reorder queries so add/remove pointers move O((n+q)√n) total for offline range queries.
- **Mo's with Hilbert-curve ordering** — order queries along a Hilbert curve to cut pointer movement constant factor.
- **Mo's on trees (Euler-tour flatten)** — map subtree/path queries to array ranges for Mo's processing (O((n+q)√n)) (see Trees).
- **Mo's with updates (3D Mo)** — add a time dimension; complexity O(n^{5/3}) for mixed update/query offline.
- **Two-pointer over sorted queries** — when query ranges are monotone, a single forward/backward window answers all (O(n+q)).
- **Sliding-window aggregate with rollback** — maintain reversible aggregates (sum, xor, monoid via queue-on-stacks) for add-only Mo variants (amortized O(1)).
- **Queue-undo / two-stack monotonic aggregate** — simulate a sliding-window monoid (min/gcd) using two stacks for O(1) amortized push/pop.

### Window/Pointer-Driven Optimization and DP Links

- **Sliding-window DP (monotonic-queue optimization)** — bound DP transition to a moving range, keeping the optimum in a deque (O(n)) (see DP optimizations).
- **Bounded-knapsack via sliding-window deque** — group residue classes and slide a max-window over them (O(nW)) (see Knapsack DP).
- **Prefix-sum + binary search on monotone window** — locate the boundary where a window constraint flips when monotonicity holds (O(n log n)).
- **Two-pointer feasibility for binary-search-on-answer** — inner check that validates a candidate threshold in linear time (O(n) per check) (see Binary Search).
- **Maximum-average / fractional segment search** — binary search the average plus prefix-sum/two-pointer feasibility (O(n log(1/ε))) (see Binary Search).
- **Star-and-bars window counting** — count subarrays/substrings by summing valid right-endpoints per left (O(n)).
- **Contribution technique with prefix/window spans** — sum each element's contribution over all containing subarrays using span counts (O(n)).

### Pitfalls, Invariants, and Edge Cases

- **Negative numbers break sliding window** — variable-window monotonicity fails with negatives; use prefix sums + hashmap/BIT instead.
- **Overflow in prefix sums/products** — cumulative values may exceed range; use wider types or modular reduction.
- **Off-by-one and inclusive/exclusive bounds** — consistent `[l,r]` vs `[l,r)` conventions and sentinel `P[0]=0` avoid boundary bugs.
- **Empty-window / all-shrunk states** — guard against `left > right`, division by zero on empty averages, and uninitialized extrema.
- **Duplicate handling in k-sum/two-pointers** — skip equal neighbors after recording a hit to avoid repeated tuples.
- **Deque expiry vs value-pop order** — distinguish removing out-of-window indices (front) from removing dominated values (back).
- **Stable vs unstable partition trade-offs** — in-place schemes (Dutch flag, Lomuto/Hoare) are unstable; stability needs extra space.
- **Amortization argument** — same-direction pointers/deques are O(n) because each index enters and leaves at most once, not per-step bounded.

<sub>[↑ Back to top](#table-of-contents)</sub>

---

## 11. Recursion & Divide and Conquer

### Recursion Fundamentals

#### Core Concepts
- **Base case** — the non-recursive terminating condition that returns directly; without a correct one, recursion never halts.
- **Recursive case** — the step that reduces the problem toward a base case by calling the function on smaller input.
- **Well-founded recursion** — every recursive call must strictly decrease a measure bounded below, guaranteeing termination.
- **Recursion tree** — tree of all recursive invocations; summing work per level yields the total cost (key to recurrence analysis).
- **Call stack / activation records** — each call pushes a frame storing parameters, locals, and return address; depth bounds memory.
- **Stack depth & overflow** — recursion depth ~ tree height; deep/linear recursion (O(n)) risks stack overflow, so bound or convert it.
- **Top-down vs bottom-up** — top-down expands from the goal recursively; bottom-up builds from base cases (often as iteration/DP).
- **Recursive leap of faith** — assume the recursive call is correct for smaller inputs, then prove the combine step (induction).

#### Forms of Recursion
- **Linear recursion** — at most one recursive call per invocation (e.g. factorial); recursion tree is a path of depth n.
- **Binary / multiple recursion** — two or more self-calls per level (e.g. naive Fibonacci); tree branches, often exponential blow-up.
- **Tree recursion** — branching recursion exploring multiple subproblems; cost equals total nodes in the recursion tree.
- **Tail recursion** — recursive call is the very last action; no pending work after it, enabling constant-space reuse of the frame.
- **Non-tail (body) recursion** — work remains after the recursive call returns, so frames must persist until unwinding.
- **Head recursion** — recursive call precedes all other work; equivalent to processing the tail of the structure first.
- **Mutual / indirect recursion** — functions call each other cyclically (A→B→A); model the combined state machine to reason about it.
- **Nested recursion** — a recursive call appears inside the argument of another call (e.g. Ackermann); grows extremely fast.
- **Anonymous / Y-combinator recursion** — recursion without a name via fixpoint combinator; theoretical lambda-calculus construct.
- **Structural vs generative recursion** — recurse on substructures of the input vs on freshly generated subproblems (D&C is generative).

#### Recursion ↔ Iteration Conversion
- **Tail-call elimination** — rewrite a tail-recursive routine as a loop updating accumulators; O(1) extra space.
- **Accumulator-passing transform** — thread partial results as extra parameters to make non-tail recursion tail-recursive.
- **Explicit-stack simulation** — replace the call stack with a manual stack of saved state; converts any recursion to iteration.
- **Two-loop method** — split work before and after each recursive call into separate phases when emulating non-tail recursion.
- **Continuation-passing style (CPS)** — pass "what to do next" as a function, defunctionalize it into an explicit stack/state.
- **State-machine / trampolining** — bounce returned thunks through a driver loop to avoid growing the native stack.
- **Iterative deepening of recursion** — re-run depth-limited recursion with increasing limits to bound stack while exploring deeper (see search).
- **Memoization wrapper** — cache results to collapse an exponential recursion tree into polynomial work (top-down DP, see DP).

#### Recurrence Analysis (cross-reference)
- **Master theorem** — closed-form for T(n)=aT(n/b)+f(n) D&C recurrences (see Mathematical Foundations).
- **Akra–Bazzi method** — generalizes the master theorem to unequal subproblem sizes and varying split points (see Mathematical Foundations).
- **Substitution & recursion-tree methods** — guess-and-verify and level-summation techniques for solving recurrences (see Mathematical Foundations).

### The Divide-and-Conquer Paradigm

#### General Schema
1. **Divide** — split the instance into two or more independent subproblems, usually of size ~n/b.
2. **Conquer** — solve subproblems recursively; stop at a base-case size handled directly.
3. **Combine** — merge subproblem answers into the answer for the whole, typically the algorithm's signature step.
- **Balanced splitting principle** — even subproblem sizes minimize recursion depth and usually total work (drives O(n log n) bounds).
- **Cutoff to brute force** — switch to a simple O(n²)/insertion method for small n to cut constant-factor and recursion overhead.
- **Independence requirement** — subproblems must not overlap; overlapping subproblems call for DP/memoization instead (see DP).
- **Combine-cost dominates analysis** — the f(n) merge term determines whether leaves, root, or all levels dominate (master theorem).
- **Decrease-and-conquer (contrast)** — reduce to one subproblem (binary search, exponentiation); a degenerate single-branch D&C.

### Sorting via Divide and Conquer

#### Merge Sort Family
- **Merge sort** — split in half, sort each, merge two sorted runs (O(n log n) time, O(n) space, stable).
- **Bottom-up (iterative) merge sort** — merge runs of size 1,2,4,… without recursion, avoiding stack depth (O(n log n)).
- **In-place merge sort** — merge with O(1)/O(log n) extra space via block rotations; complex, larger constants (O(n log n) or O(n log²n)).
- **Natural merge sort** — exploit pre-existing sorted runs; O(n) on already-sorted input, adaptive.
- **k-way / multiway merge** — merge k runs at once with a heap; basis of external sorting (O(n log k) per pass).
- **Timsort** — adaptive hybrid of natural merge sort and insertion sort with galloping merges (O(n log n), O(n) on near-sorted).
- **Parallel merge sort** — recursively sort halves on separate processors, then parallel-merge with co-ranking.

#### Quicksort Family
- **Quicksort** — partition around a pivot, recurse on both sides; O(n log n) average, O(n²) worst, in-place.
- **Partition schemes** — Lomuto (single index, simpler) vs Hoare (two pointers, fewer swaps) partitioning strategies.
- **Pivot selection** — first/last, random, median-of-three, median-of-medians to avoid adversarial O(n²) cases.
- **Randomized quicksort** — random pivot makes O(n²) astronomically unlikely; O(n log n) expected regardless of input.
- **Three-way (Dutch national flag) quicksort** — partition into <, =, > to handle many duplicate keys efficiently.
- **Introsort** — quicksort that switches to heapsort past a depth limit, guaranteeing O(n log n) worst case.
- **Dual-pivot quicksort** — partition with two pivots into three regions; fewer cache misses in practice.
- **Quickselect** — quicksort partitioning to find the k-th smallest in O(n) expected without full sort.
- **Median-of-medians selection** — deterministic O(n) worst-case selection guaranteeing a good pivot (BFPRT algorithm).

### Searching via Divide and Conquer
- **Binary search** — halve a sorted range each step to locate a key or boundary (O(log n)).
- **Lower/upper bound (predicate boundary)** — find first/last index satisfying a monotone predicate; binary search on the answer.
- **Binary search on the answer** — when feasibility is monotone in a parameter, binary-search the parameter (O(log range · check)).
- **Exponential (galloping) search** — double the bound until overshoot, then binary search; O(log i) for unbounded/unknown size.
- **Ternary search** — locate the extremum of a unimodal function by discarding a third each step (O(log n) evaluations).
- **Fractional cascading** — share search positions across many sorted lists to cut repeated binary searches (see Data Structures).
- **Interpolation search** — estimate the position via value interpolation; O(log log n) on uniform data, O(n) worst.
- **Parallel binary search** — answer many queries with binary search on the answer simultaneously over shared events (offline, see offline).

### Fast Exponentiation and Algebraic D&C
- **Binary (fast) exponentiation** — square-and-multiply using the exponent's bits (O(log n) multiplications).
- **Iterative fast power** — bottom-up bit scan version avoiding recursion (O(log n)).
- **Modular exponentiation** — fast power under a modulus; core of RSA, primality, and combinatorics (O(log n) mults).
- **Matrix exponentiation** — raise a transition matrix to the n-th power for linear recurrences like Fibonacci (O(k³ log n)).
- **Fast doubling (Fibonacci)** — compute F(2k), F(2k+1) from F(k), F(k+1) recursively (O(log n)).
- **Exponentiation by squaring on monoids** — same trick for any associative operation (polynomials, permutations, matrices).
- **Montgomery / Barrett-assisted powering** — speed the repeated modular reductions inside fast power (see Number Theory).
- **Repeated-squaring discrete log helpers** — baby-step/giant-step uses powers built by squaring (see Number Theory).

### Fast Multiplication (Integers & Polynomials)

#### Integer Multiplication
- **Schoolbook (long) multiplication** — the O(n²) baseline that D&C methods improve upon.
- **Karatsuba multiplication** — 3 half-size products instead of 4 via (a+b)(c+d) trick (O(n^log₂3) ≈ O(n^1.585)).
- **Toom-Cook (Toom-3, Toom-k)** — split into k parts, evaluate/interpolate at chosen points (Toom-3 ≈ O(n^1.465)).
- **Schönhage–Strassen** — FFT-based integer multiply over a ring of roots of unity (O(n log n log log n)).
- **Harvey–van der Hoeven** — theoretical O(n log n) integer multiplication, the asymptotic optimum.
- **Evaluation–interpolation framework** — unifying view: treat numbers as polynomials, evaluate, multiply pointwise, interpolate.

#### Polynomial Multiplication & FFT (cross-reference)
- **Fast Fourier Transform (FFT)** — D&C splitting polynomial into even/odd coefficients for O(n log n) multiply (see FFT/Number Theory).
- **Number-Theoretic Transform (NTT)** — FFT over a finite field for exact modular convolution (see FFT/Number Theory).
- **Polynomial Karatsuba** — same 3-way recursion applied to polynomials for fast convolution when FFT is overkill.

### Matrix Algorithms via D&C
- **Strassen matrix multiplication** — 7 instead of 8 recursive products of n/2 blocks (O(n^log₂7) ≈ O(n^2.807)).
- **Strassen–Winograd variant** — reduces additions in Strassen's combine step for better constants.
- **Coppersmith–Winograd / Le Gall** — galactic algorithms approaching O(n^2.37); theoretical lower exponent bounds.
- **Block / tiled D&C matrix multiply** — recursive blocking for cache-oblivious locality even at O(n³) arithmetic.
- **D&C matrix inversion & LU** — invert via block (Schur-complement) recursion, reducing to multiplication cost.
- **Recursive determinant via blocks** — Schur complement recursion ties determinant cost to matrix-multiply cost.

### Geometric Divide and Conquer
- **Closest pair of points** — split by x-median, recurse, then check a 7-wide strip around the divide line (O(n log n)).
- **Closest pair (sweep variant)** — strip check using points sorted by y to keep the merge linear per level.
- **Convex hull — divide and conquer** — split points, hull each half, merge with upper/lower tangents (O(n log n)).
- **Convex hull merge (tangent finding)** — locate the two bridges joining two convex hulls in linear time.
- **Delaunay triangulation (D&C)** — Guibas–Stolfi split-and-merge construction (O(n log n)).
- **Voronoi diagram (D&C)** — recursively build and merge diagrams along a dividing chain (O(n log n)).
- **Smallest enclosing circle (D&C flavor)** — Welzl's incremental-randomized method; expected O(n) (often grouped here).
- **Largest empty / point-location splits** — kd-tree and range-tree construction recursively partition space (see Data Structures).
- **Half-plane intersection (D&C)** — split half-planes, intersect each group, merge convex regions (O(n log n)).
- **Rectilinear / segment problems via plane sweep + recursion** — many geometry problems mix sweep with recursive splitting (see Geometry).

### Array & Sequence D&C Problems
- **Maximum subarray (D&C)** — best is in left half, right half, or crossing the mid; combine in O(n) (overall O(n log n)).
- **Maximum subarray (Kadane contrast)** — O(n) DP solution showing D&C is not always optimal (see DP).
- **Counting inversions** — count cross-pairs during a merge-sort merge (O(n log n)).
- **Counting inversions via BIT (contrast)** — Fenwick-tree alternative to the merge-based count (see Data Structures).
- **Significant / important inversions** — count pairs with a[i] > c·a[j] using an augmented merge (O(n log n)).
- **Count of range sums** — count subarray sums in [lower, upper] via merge over prefix sums (O(n log n)).
- **Reverse pairs** — count i<j with a[i] > 2·a[j] in a modified merge sort (O(n log n)).
- **Majority element (D&C)** — find majority by combining halves' candidates (O(n log n); Boyer–Moore is O(n)).
- **Maximum / minimum simultaneously** — pair-and-recurse to find both with ~3n/2 comparisons.
- **The skyline problem** — merge half-skylines by sweeping heights, keeping the max envelope (O(n log n)).
- **Longest common prefix (D&C)** — split the string set, find LCP of halves, combine (O(total chars)).
- **Tromino / L-tile tiling** — recursively tile a 2ⁿ×2ⁿ board with one hole using L-trominoes (O(n²) cells).
- **Tower of Hanoi** — classic doubly-recursive move generation (Θ(2ⁿ) moves).
- **Karatsuba-style sum/convolution tricks** — apply evaluation–interpolation to sequence combine steps.

### D&C on Trees and Graphs (cross-reference)
- **Centroid decomposition** — recursively split a tree at centroids for path/distance queries (O(n log n) build) (see Trees).
- **Centroid decomposition tree (queries)** — answer path-counting/closest-colored queries on the centroid hierarchy (see Trees).
- **Heavy-light decomposition (contrast)** — path decomposition that is not strictly D&C but recursive in spirit (see Trees).
- **Small-to-large merging (DSU on tree)** — merge smaller child sets into larger ones for O(n log n) subtree statistics (see Trees).
- **Static top tree / contraction** — recursive rake-and-compress tree contraction (see Trees).
- **Divide and conquer on tree paths** — centroid-based counting of qualifying paths (e.g. length-k paths) (see Trees).
- **Graph bisection / separator recursion** — planar separator theorem enables √n-balanced recursive splits (see Graphs).

### Offline & DP-Oriented Divide and Conquer (cross-reference)
- **CDQ divide and conquer** — sort by one dimension, recurse, and let the left half contribute to the right (3D partial order, dynamic→static) (see Offline Techniques).
- **CDQ on DP transitions** — resolve dependent DP updates by recursive time-splitting (O(n log²n)) (see Offline/DP).
- **Divide and conquer DP optimization** — exploits monotone optimal split points to speed layered DP (O(kn log n)) (see DP).
- **Knuth / SMAWL & monotone-matrix techniques** — related optimizations leveraging totally-monotone cost matrices (see DP).
- **D&C on the segment tree (segment-tree beats / merging)** — recursive interval splitting underlying segment trees (see Data Structures).
- **Offline dynamic connectivity (D&C on time)** — recurse over a timeline, adding edges valid in each interval with rollback DSU (see Offline Techniques).
- **Parallel binary search (D&C on queries+events)** — bucket queries by candidate answer and recurse (see Offline Techniques).
- **Mo's algorithm (contrast)** — offline query ordering, not D&C but often paired with it (see Offline Techniques).

### Advanced & Theoretical Topics
- **Cache-oblivious D&C** — recursive subdivision yields optimal cache behavior without tuning block sizes.
- **Parallel D&C / fork-join model** — independent subproblems map naturally to parallel tasks; work-span analysis bounds speedup.
- **Work-stealing scheduling** — runtime that balances recursive fork-join tasks across cores.
- **Lower bounds for D&C multiplication** — information-theoretic and ring-based limits on how few products suffice.
- **Tensor rank & matrix-multiply exponent ω** — D&C matrix multiply cost equals the rank of the matrix-multiplication tensor.
- **Recursion with backtracking (contrast)** — systematic recursive enumeration with pruning; structurally recursive search (see Backtracking).
- **Branch and bound (contrast)** — recursive search guided by bounds to prune the recursion tree (see Backtracking/Optimization).
- **Persistent recursion / functional D&C** — immutable structures let recursive calls share subresults safely (see Data Structures).
- **D&C for FFT-based string matching** — recursive transform underlies wildcard/convolution matching (see Strings).
- **Randomized D&C analysis** — expectation over random splits (randomized quicksort/quickselect) via indicator variables.

<sub>[↑ Back to top](#table-of-contents)</sub>

---

## 12. Greedy Algorithms

### Foundations & Greedy Paradigm

- **Greedy paradigm** — build a solution by repeatedly taking the locally optimal choice, never reconsidering; fast but correct only for problems with special structure.
- **Greedy-choice property** — there exists an optimal solution that contains the locally optimal first choice, so committing to it never precludes optimality.
- **Optimal substructure** — an optimal solution contains optimal solutions to subproblems, enabling the recursive "first choice + solve remainder" decomposition.
- **Greedy vs. dynamic programming** — greedy commits irrevocably to one choice (O(n log n)-ish); DP explores all choices (polynomial but heavier); greedy works only when the greedy-choice property holds.
- **Greedy vs. brute force / backtracking** — greedy never backtracks, trading completeness for speed; correctness must be proven, not assumed.
- **Irrevocability principle** — once a greedy decision is made it is final; this distinguishes pure greedy from look-ahead heuristics.
- **Canonical greedy template** — sort/prioritize items by a key, then scan once accepting feasible items; most greedy solutions reduce to choosing the right sort key.
- **Counterexample mindset** — before trusting greed, attempt small adversarial inputs (e.g. 0/1 knapsack with capacity tricks) to refute the candidate greedy rule.

### Correctness Proof Techniques

- **Exchange (swap) argument** — transform any optimal solution into the greedy one by swapping elements without worsening the objective, proving greedy is optimal.
- **Stay-ahead argument** — show by induction that after each greedy step the partial greedy solution is at least as good (e.g. finishes no later) as any other partial solution.
- **Cut-and-paste / optimal-substructure proof** — assume a subproblem solution is suboptimal, paste in a better one to contradict global optimality.
- **Greedy "remains-feasible" invariant** — prove each greedy choice keeps a feasible completion possible, so a full feasible solution is reached.
- **Matroid optimality theorem** — greedy is optimal for max/min-weight independent set iff the structure is a matroid (see Matroid Theory below).
- **Local-ratio theorem** — if a greedy/weight-decomposition step is r-approximate locally, summing local guarantees yields a global r-approximation; unifies many approximation proofs.
- **Primal-dual / dual-fitting view** — construct a feasible dual whose value bounds the greedy primal, certifying optimality or an approximation ratio.
- **Induction on problem size** — prove correctness by reducing an n-item instance to an (n−1)-item one after the first greedy choice.
- **Charging argument** — map each unit of cost/benefit of an alternative solution to greedy's, bounding the ratio (common in interval and scheduling proofs).

### Matroid Theory & Greedy on Matroids

- **Matroid definition** — a ground set with an independent-set family closed under subsets (hereditary) and satisfying the augmentation/exchange property.
- **Exchange (augmentation) property** — if independent X is smaller than independent Y, some element of Y\X can be added to X keeping it independent.
- **Bases and circuits** — bases are maximal independent sets (all equal size = the rank); circuits are minimal dependent sets.
- **Rank function** — submodular function giving the size of the largest independent subset of any set; characterizes the matroid.
- **Graphic (cycle) matroid** — independent sets are forests of a graph; greedy over it yields MST/maximum-spanning-tree (see Graphs domain: Kruskal).
- **Uniform matroid** — independent sets are all subsets of size ≤ k; greedy picks the k heaviest elements.
- **Partition matroid** — ground set partitioned into blocks with per-block capacity bounds; models many scheduling/assignment constraints.
- **Linear (vector/matric) matroid** — independence = linear independence of vectors; greedy builds a max-weight independent vector set.
- **Transversal matroid** — independent sets are endpoints matchable in a bipartite graph; links matroids to matching.
- **Greedy algorithm on a weighted matroid** — sort elements by weight, add each if it preserves independence; provably yields the max-weight basis (O(n log n + n·f) where f is the independence test).
- **Matroid intersection (brief)** — max common independent set of two matroids is poly-time but NOT solvable by simple greedy (see Combinatorial Optimization).
- **Matroid union & partitioning** — combine matroids; greedy/matroid-union algorithms partition a set into minimum independent sets.
- **Greedoids** — generalization relaxing the hereditary property; greedy stays optimal for a broader but more delicate class (e.g. shelling structures).
- **Weighted matroid for scheduling** — unit-time jobs with deadlines/penalties form a matroid, so greedy by penalty solves job sequencing optimally.
- **Why greedy can fail off-matroid** — if the exchange property is absent, weights exist on which greedy is suboptimal (e.g. 0/1 knapsack).

### Interval Scheduling & Selection

- **Activity selection (interval scheduling maximization)** — pick the maximum number of mutually non-overlapping intervals by greedily choosing earliest finishing time (O(n log n)).
- **Earliest-finish-time rule + proof** — stay-ahead argument: greedy's k-th finish is never later than any optimal's, so it fits at least as many activities.
- **Weighted interval scheduling** — maximize total weight of compatible intervals; NOT greedy, solved by DP with binary search on predecessors (O(n log n)) (see Dynamic Programming).
- **Interval point cover / stabbing** — fewest points to stab all intervals: sort by right endpoint, place a point at each uncovered interval's end (O(n log n)).
- **Minimum number of taps/segments to cover a range** — jump-style greedy choosing the farthest reach at each step (O(n) after bucketing).
- **Interval graph independent set** — equivalent to activity selection; greedy by finishing time gives max independent set of an interval graph.
- **Disjoint intervals / erase-overlap** — minimum intervals to remove so the rest are disjoint = total minus max non-overlapping set (O(n log n)).
- **Maximum overlapping intervals at a point** — sweep starts/ends as +1/−1 events to find peak concurrency (O(n log n)).

#### Interval Partitioning & Resource Allocation

- **Interval partitioning / minimum rooms (coloring)** — minimum resources to schedule all intervals = maximum overlap depth; sort endpoints and sweep (O(n log n)).
- **Depth lower bound** — number of mutually overlapping intervals lower-bounds rooms; greedy meets it exactly (matches interval-graph chromatic number).
- **Meeting-rooms / minimum platforms** — count simultaneous trains/meetings via merged start/end event sweep or two sorted pointers (O(n log n)).
- **CPU/processor interval assignment** — assign each interval to any free resource using a min-heap of release times (O(n log n)).
- **Car-pooling / capacity feasibility** — sweep passenger pickup/dropoff deltas to verify capacity never exceeded (O(n log n)).

### Scheduling: Deadlines, Lateness & Penalties

- **Minimizing maximum lateness (EDF)** — schedule jobs by earliest deadline first to minimize the maximum lateness; proven by exchange argument removing inversions (O(n log n)).
- **Exchange-by-inversion proof** — any non-EDF schedule has an inverted adjacent pair whose swap does not increase max lateness, converging to EDF.
- **Job sequencing with deadlines (max profit)** — unit-time jobs each with deadline and profit; greedily take highest-profit jobs into latest free slot (O(n log n) with DSU, else O(n²)).
- **Job sequencing as a matroid** — feasible job subsets form a matroid, certifying the greedy-by-profit rule's optimality.
- **Minimizing total/weighted completion time (SPT/WSPT)** — shortest-processing-time first (or by ratio weight/time) minimizes average completion; Smith's rule (O(n log n)).
- **Smith's ratio rule** — order jobs by descending weight/processing-time to minimize weighted sum of completion times (provably optimal, O(n log n)).
- **Minimizing maximum tardiness on one machine** — EDF-style ordering; ties broken to keep feasibility.
- **Moore-Hodgson algorithm** — minimize the number of late jobs on a single machine: process by deadline, evicting the longest job from a heap when infeasible (O(n log n)).
- **Deadline scheduling with penalties / late-job minimization** — combine heaps and eviction to maximize on-time profit or minimize lateness count.
- **Two-machine flow shop (Johnson's rule)** — sequence jobs to minimize makespan on two machines by a min-stage split-and-sort rule (O(n log n)).
- **Tasks with cooldown / interval reordering** — schedule identical tasks with cooldown by always running the most-frequent available task (greedy with counts/heap, O(n)).
- **Gas-station / circular-tour greedy** — find a feasible start on a circular route by tracking running tank deficit and resetting on failure (O(n)).
- **Minimize makespan on parallel machines (LPT)** — longest-processing-time-first list scheduling gives a (4/3 − 1/3m)-approximation (O(n log n)).

### Classic Greedy Problems

#### Compression & Encoding

- **Huffman coding** — build an optimal prefix-free code by repeatedly merging the two least-frequent symbols using a min-heap (O(n log n)).
- **Huffman optimality proof** — exchange argument: two least-frequent symbols can be made deepest sibling leaves in some optimal tree.
- **k-ary / non-binary Huffman** — generalize merges to k children, padding symbol count to ≡ 1 (mod k−1) with zero-weight dummies.
- **Length-limited Huffman (package-merge)** — optimal prefix code with a max codeword length via the package-merge greedy (O(nL)).
- **Optimal merge pattern / file merging** — minimize total cost of merging sorted files; identical to Huffman on file sizes (O(n log n)).
- **Connect-ropes / minimum cost to combine** — repeatedly join the two cheapest items via a min-heap (O(n log n)).

#### Knapsack-Type & Selection

- **Fractional knapsack** — maximize value under capacity allowing fractions; take items by descending value/weight ratio, splitting the last (O(n log n)).
- **0/1 knapsack is NOT greedy** — greedy by ratio fails; requires DP (counterexample-driven caution) (see Dynamic Programming).
- **Greedy on sorted (general pattern)** — many problems collapse to sorting by a derived key then a linear scan; identifying the key is the core insight.
- **Maximize/minimize pair-sum or assignment** — sort both arrays and match extremes (rearrangement inequality) for max/min weighted sums (O(n log n)).
- **Rearrangement inequality applications** — pair largest-with-largest (max) or largest-with-smallest (min) to optimize sum of products.
- **Two-pointer greedy** — boat/load problems, container assignment, target pairing solved by sorting and converging pointers (O(n log n)).
- **Candy / ratings distribution** — two-pass greedy giving each child more candy than a lower-rated neighbor on both sides (O(n)).
- **Jump game / minimum jumps** — track farthest reachable index per level to decide reachability or minimum jumps (O(n)).
- **Gas / fuel refueling with stops** — maximize reach by greedily refueling from a max-heap of passed stations when empty (O(n log n)).

#### Coin & Number Representations

- **Coin change (greedy, canonical systems)** — repeatedly take the largest coin ≤ remainder; optimal only for canonical coin systems (O(n) given sorted denominations).
- **Canonical coin-system test** — verify a denomination set is canonical (greedy = optimal) by checking small counterexamples up to a bound (Pearson's algorithm).
- **Non-canonical caution** — for arbitrary denominations greedy fails; minimum coins needs DP (see Dynamic Programming).
- **Egyptian fraction (Fibonacci-Sylvester greedy)** — represent a fraction as distinct unit fractions by repeatedly subtracting the largest unit fraction ≤ the value (terminates; may grow fast).
- **Stern-Brocot tree greedy / continued fractions** — navigate the Stern-Brocot tree by greedy left/right moves to find best rational approximations or encode rationals.
- **Binary/representation greedy** — greedily peel the highest set bit or largest power for minimal-term representations and bit tricks.
- **Minimum operations to reach a number (halve/decrement)** — work backwards greedily (e.g. divide when even) to minimize steps (O(log n)).

#### Strings & Lexicographic Greedy

- **Lexicographically smallest/largest result** — build the answer character by character, always choosing the smallest valid next symbol (often with a monotonic stack).
- **Remove k digits / smallest number** — maintain an increasing monotonic stack, popping larger leading digits while budget remains (O(n)).
- **Remove duplicate letters (smallest subsequence)** — monotonic stack keyed by last-occurrence positions to keep one of each letter minimally (O(n)).
- **Largest number from concatenation** — sort strings by the comparator a+b vs b+a to maximize the concatenation (O(n log n)).
- **Reorganize/rearrange string** — place the most frequent character first via a max-heap to avoid adjacent duplicates (O(n log k)).
- **Greedy palindrome / bracket fixing** — minimal insertions/deletions to balance brackets via running counters (O(n)).

### Matching & Assignment Greedy

- **Gale-Shapley stable matching** — deferred-acceptance: each free proposer proposes down their list; proposees keep their best offer, yielding a stable matching (O(n²)).
- **Proposer-optimality theorem** — Gale-Shapley produces the proposer-optimal, receiver-pessimistic stable matching; no stable matching gives every proposer a better partner.
- **Stable roommates problem** — non-bipartite stable matching; Irving's algorithm extends deferred acceptance (may have no stable solution) (O(n²)).
- **Hospitals/residents (many-to-one)** — capacitated Gale-Shapley generalization used in real assignment markets.
- **Greedy maximal matching** — repeatedly add any uncovered edge; yields a 2-approximation to maximum matching and to vertex cover (O(E)).
- **Greedy bipartite assignment heuristics** — sort by weight and assign greedily; exact optimum needs Hungarian/flow methods (see Graphs/Flows).

### Advanced Greedy Methods & Frameworks

- **Local-ratio method** — iteratively subtract a "local" weight function, solve the residual greedily, and combine; yields tight approximations for covering/scheduling.
- **Primal-dual schema** — grow dual variables greedily until constraints go tight, building primal and dual together for provable ratios (e.g. set cover, Steiner).
- **Regret / look-ahead greedy** — choose to minimize the worst-case future regret of a decision; basis of online competitive algorithms.
- **Greedy with exchange argument as design tool** — derive the correct sort key by imagining swapping two adjacent items and seeing which order wins.
- **Submodular maximization (greedy 1−1/e)** — greedily add the element with largest marginal gain; gives a (1−1/e)-approximation for monotone submodular functions under a cardinality constraint.
- **Greedy for set cover (ln n bound)** — repeatedly pick the set covering the most uncovered elements; achieves an H(n)≈ln n approximation (tight) (O(input size)).
- **Greedy vertex cover (2-approx)** — repeatedly add both endpoints of an uncovered edge for a 2-approximation (O(E)).
- **Online greedy & competitive ratio** — analyze greedy decisions made without future knowledge; ski-rental, paging (LRU/FIFO), and bin-packing online bounds.
- **Greedy bin packing (FF/BF/FFD)** — First-Fit/Best-Fit (online) and First-Fit-Decreasing (offline) heuristics with ~11/9·OPT+const guarantees.
- **Hill climbing / greedy local search** — repeatedly move to a better neighbor; risks local optima, mitigated by restarts or randomization (see Optimization/Metaheuristics).
- **Greedy randomized adaptive search (GRASP)** — randomized greedy construction plus local search for hard combinatorial problems (heuristic).

### Heap / Priority-Driven Greedy

- **Min/max-heap greedy** — maintain best-available candidate in a priority queue for "pick the extreme each step" problems (O(n log n)).
- **Two-heap (median/scheduling) technique** — balance a max-heap and min-heap to track a threshold (median, k-th element) under streaming greedy decisions (O(log n) per op).
- **IPO / capital-project selection** — two heaps: min-heap by capital to unlock projects, max-heap by profit to pick the best affordable one (O(n log n)).
- **Scheduling with release times + deadlines** — min-heap of available jobs keyed by deadline/length, advancing time greedily (O(n log n)).
- **Lazy deletion in greedy heaps** — defer removals using a counter/marked map to keep heap operations amortized O(log n).
- **Slope-trick / convex greedy** — maintain a piecewise-linear convex cost with heaps of slope-change points for sequential adjustment problems (O(n log n)) (see Dynamic Programming).
- **Regret-replacement heap greedy** — maintain a heap of past choices and swap out the worst when a better option appears (e.g. course/task selection, O(n log n)).

### Graph Greedy (Cross-References)

- **Dijkstra's algorithm** — greedy shortest paths: always finalize the nearest unsettled vertex (O((V+E) log V)) (see Graphs/Shortest Paths).
- **Prim's algorithm** — greedy MST growing one tree by the cheapest crossing edge (O(E log V)) (see Graphs/MST).
- **Kruskal's algorithm** — greedy MST adding cheapest edges that avoid cycles (matroid greedy) (O(E log E)) (see Graphs/MST).
- **Borůvka's algorithm** — parallel greedy MST contracting each component's cheapest edge per round (O(E log V)) (see Graphs/MST).
- **Reverse-delete MST** — greedy by deleting heaviest non-bridge edges (see Graphs/MST).
- **Greedy graph coloring (Welsh-Powell)** — color vertices in degree order with the smallest available color; ≤ Δ+1 colors (heuristic) (see Graphs/Coloring).
- **Greedy topological / Kahn's algorithm** — repeatedly remove a zero-indegree vertex; lexicographic variant uses a heap (see Graphs/Topological Sort).
- **Maximum spanning tree** — Kruskal/Prim with reversed comparator (graphic matroid greedy) (see Graphs/MST).
- **Greedy edge coloring / arboricity heuristics** — bounded-approximation colorings used as preprocessing (see Graphs).

### Geometry & Sweep Greedy

- **Sweep-line greedy** — process events left-to-right making locally optimal decisions (interval, overlap, and scheduling problems) (O(n log n)) (see Geometry/Sweep).
- **Greedy convex hull insight** — Graham scan's monotonic-stack pruning is a greedy turn-direction decision (see Geometry/Convex Hull).
- **Minimum points to cover / fence problems** — greedy by rightmost feasible placement on a line (O(n log n)).
- **Greedy fractional cascading / nearest selection** — pick locally closest feasible geometric object (heuristic, problem-specific).

### Pitfalls, Heuristics & Practice

- **When greedy fails** — absence of greedy-choice property (0/1 knapsack, longest path, general coin change) demands DP or exact search.
- **Proving before coding** — always validate a greedy rule with an exchange/stay-ahead proof or a brute-force comparison on small inputs.
- **Stress testing greedy** — compare candidate greedy output against brute force on random small cases to detect wrong sort keys.
- **Tie-breaking matters** — secondary sort keys (e.g. deadline then length) are often essential for correctness, not just performance.
- **Greedy as a DP/search subroutine** — greedy choices prune or initialize DP, branch-and-bound, and approximation pipelines.
- **Approximation vs. exact** — distinguish greedy that is provably optimal (matroid/EDF) from greedy that is only a bounded approximation (set cover, bin packing).
- **Multi-criteria greedy** — combine keys via a single comparator (ratio rules, lexicographic order) to respect several objectives at once.

<sub>[↑ Back to top](#table-of-contents)</sub>

---

# 📈 Part — Dynamic Programming

## 13. Dynamic Programming — Foundations & Classic Patterns

### Foundations & Theory of Dynamic Programming

#### Defining Properties

- **Optimal substructure** — an optimal solution is composed of optimal solutions to subproblems; the necessary precondition for any DP/greedy formulation.
- **Overlapping subproblems** — the same subproblem recurs many times in the naive recursion tree, so caching yields polynomial work instead of exponential.
- **Distinction from divide & conquer** — D&C subproblems are disjoint (no reuse); DP exploits reuse, hence memoization adds value only when subproblems overlap.
- **Distinction from greedy** — DP explores all consistent local choices and combines them; greedy commits irrevocably to one choice and needs an exchange-argument/matroid proof of correctness.
- **Principle of optimality (Bellman)** — any tail of an optimal policy is itself optimal for the corresponding subproblem; justifies recursive composition of optima.
- **Acyclic dependency requirement** — the subproblem dependency graph must be a DAG so a valid evaluation order exists; cyclic dependencies need fixed-point/iterative methods (see Graph Algorithms — shortest paths).

#### Anatomy of a DP Solution

1. **State definition** — choose parameters that fully summarize a partial solution (a "sufficient statistic"); minimal yet complete to satisfy the Markov-style memorylessness.
2. **Transition (recurrence)** — express each state's value via already-solved states; the heart of the design and the main driver of complexity.
3. **Base cases** — smallest states solved directly, anchoring the recursion.
4. **Order of evaluation** — a topological order of the state DAG (increasing length, increasing index, etc.) so dependencies are ready before use.
5. **Answer extraction** — identify which state(s) hold the final result; sometimes an aggregate over many terminal states.

#### Implementation Styles

- **Top-down memoization** — recursion + cache; computes only reachable states, natural transitions, risks recursion-depth limits (O(states × transition) time, O(states) memo + stack).
- **Bottom-up tabulation** — iterative fill in dependency order; no recursion overhead, easy to space-optimize, may compute unreachable states (O(states × transition) time).
- **Memoization vs tabulation trade-offs** — top-down skips unreachable states and is easier to write; bottom-up enables rolling arrays, better cache locality, and predictable order.
- **Lazy vs eager evaluation** — compute states on demand vs precompute all; choose by fraction of state space actually needed.

#### Optimization & Reconstruction Tactics

- **Rolling array / dimension reduction** — keep only the last k layers when transitions reach back a bounded distance (O(states) → O(layer width) space).
- **In-place update with traversal direction** — sweep the inner loop forward vs backward to model unbounded vs 0/1 reuse (the knapsack trick).
- **Path reconstruction via parent pointers** — store the chosen predecessor per state to rebuild the optimal solution (O(states) extra space).
- **Reconstruction by re-derivation** — recompute the decision from stored values without parent pointers, trading time for space.
- **Forward vs backward formulation ("push" vs "pull")** — propagate from a state to its successors versus gather from predecessors; affects loop structure and lazy init.
- **State-space reduction** — drop redundant parameters, exploit symmetry, or canonicalize states to shrink the table.
- **Hashing sparse states** — store irregular/large state spaces in a map when only few states are visited (top-down friendly).

### One-Dimensional / Linear DP

- **Fibonacci numbers** — `f(n)=f(n-1)+f(n-2)`; canonical overlapping-subproblems example (O(n) DP, O(log n) via matrix exponentiation — see Math & Number Theory).
- **Climbing stairs / tiling counts** — ways to reach step n with steps from a fixed set; same recurrence family as Fibonacci (O(n)).
- **House robber (max non-adjacent sum)** — `dp[i]=max(dp[i-1], dp[i-2]+a[i])`; no two chosen indices adjacent (O(n)).
- **House robber on a cycle** — first and last are adjacent; run the linear version twice excluding one endpoint each (O(n)).
- **Maximum subarray (Kadane)** — running best-ending-here with reset at negatives; classic linear scan (O(n)).
- **Maximum product subarray** — track both max and min ending here because a negative flips extremes (O(n)).
- **Maximum circular subarray** — answer is `max(linear-max, total − linear-min)` with an all-negative guard (O(n)).
- **Jump game (reachability)** — can index n be reached given per-cell max jump; greedy-DP frontier (O(n)).
- **Jump game II (min jumps)** — fewest jumps to the end via BFS-like level expansion (O(n)).
- **Decode ways** — count digit-string decodings into letters; transitions on one- vs two-digit groups (O(n)).
- **Number of ways to reach a sum with ordered steps** — compositions/Fibonacci-style counting where order matters (O(n·k)).
- **Min/max cost climbing** — minimum total cost to ascend with per-step costs (O(n)).
- **Best time to buy/sell stock (single transaction)** — track min price so far; degenerate 1-state DP (O(n)).

### Knapsack Family

#### Core Variants

- **0/1 knapsack** — each item taken at most once; maximize value under capacity W (O(nW) time, O(W) space with backward sweep).
- **Unbounded knapsack** — unlimited copies per item; forward inner sweep over capacity (O(nW), O(W) space).
- **Bounded / multiple knapsack (naive)** — each item has a count limit c_i; expand copies then 0/1 (O(W·Σc_i)).
- **Bounded knapsack via binary splitting** — represent c_i copies as powers-of-two bundles, then run 0/1 (O(nW log c) — exponentially fewer items).
- **Bounded knapsack via monotonic-deque** — sliding-window-maximum over capacity residues modulo weight (O(nW), optimal).
- **Fractional knapsack** — items divisible; solved greedily by value/weight ratio, NOT DP (O(n log n) — see Greedy Algorithms).

#### Subset / Counting Forms

- **Subset sum (feasibility)** — can a subset hit target T; boolean knapsack with no values (O(nT), O(T) space, bitset speedup O(nT/word)).
- **Partition into two equal-sum subsets** — subset sum for total/2 (O(n·sum)).
- **Count subsets with given sum** — counting variant of subset sum (O(nT)).
- **Count subsets within a sum range / "perfect sum"** — accumulate counts over achievable sums (O(nT)).
- **Minimum subset-sum difference** — find achievable sum closest to total/2, minimize |S − (total−S)| (O(n·sum)).
- **Target sum (+/- assignment)** — assign signs to reach a target; reduces to subset sum on (total+target)/2 (O(n·sum)).
- **K-equal-sum partitions / bin balancing** — bitmask DP over item subsets for small n (O(2^n·n) — see Bitmask DP).

#### Knapsack Generalizations

- **Multi-dimensional / 2D knapsack** — two capacity constraints (e.g., weight and volume) → extra table dimension (O(n·W1·W2)).
- **Knapsack with item dependencies (tree/group)** — items grouped or requiring prerequisites; group knapsack or tree-knapsack (O(n·W) per group).
- **Group ("multiple-choice") knapsack** — choose at most one item from each group (O(n·W)).
- **Profit/weight inversion (large value, small weight)** — index DP on total value, minimize weight, when values are small (O(n·ΣV)).
- **Exact-fill knapsack** — require capacity used exactly; initialize unreachable states to −∞/∞ (O(nW)).
- **Fractional vs integral relaxation bound** — LP/greedy relaxation gives an upper bound used to prune branch-and-bound knapsack solvers.

### Coin Change & Currency DP

- **Minimum coins to make amount** — unbounded knapsack minimizing count; ∞ for unreachable (O(amount × coins)).
- **Count ways to make amount (combinations)** — order-insensitive; iterate coins outer, amount inner (O(amount × coins)).
- **Count ordered ways (compositions/permutations)** — order matters; iterate amount outer, coins inner (O(amount × coins)).
- **Coin change with limited coin counts** — bounded-knapsack variant (binary/deque splitting applies).
- **Maximum/Frobenius reachability** — largest unmakeable amount (Chicken McNugget — see Math & Number Theory) and reachability via DP.
- **Stamp/postage covering** — minimal stamps summing to value; min-coin variant (O(amount × stamps)).

### Subsequence DP

#### Longest Increasing Subsequence and Kin

- **LIS (DP form)** — `dp[i]=1+max(dp[j])` over j<i with a[j]<a[i] (O(n^2)).
- **LIS (patience / binary search)** — maintain tails of increasing subsequences, binary-search the insertion point (O(n log n)).
- **Strictly vs non-decreasing LIS** — use lower_bound vs upper_bound in the patience method to allow equal elements.
- **Longest Decreasing Subsequence** — LIS on the reversed comparator (O(n log n)).
- **Longest Bitonic Subsequence** — combine LIS-from-left and LDS-from-right at each pivot (O(n log n)).
- **Count of LIS** — track both length and number of ways achieving each length (O(n^2) or O(n log n) with BIT).
- **Maximum-sum increasing subsequence** — LIS where the value is the sum, not the count (O(n^2)).
- **Minimum deletions to sort / make increasing** — n − LIS (O(n log n)).
- **Longest chain of pairs / box stacking / Russian doll envelopes** — sort one dimension, LIS on the other (O(n log n)).
- **LIS reconstruction** — store predecessor or the index recorded at each tail length.
- **Number of non-overlapping LIS / Dilworth duality** — minimum antichains/chains relation (Dilworth's theorem) bounds partitions into monotone runs.

#### Common Subsequence / String Distance

- **Longest Common Subsequence (LCS)** — `dp[i][j]` matches extend diagonal, else max of drops (O(nm), O(min(n,m)) space rolling).
- **LCS reconstruction** — backtrack through the table or store directions (O(nm) table, O(n+m) trace).
- **LCS via Hunt–Szymanski** — exploit few matching pairs, LIS on match positions (O((r+n) log n) where r = matches).
- **Longest Common Substring** — contiguous match DP, reset on mismatch (O(nm); also via suffix structures — see String Algorithms).
- **Edit distance (Levenshtein)** — insert/delete/substitute costs (O(nm), O(min(n,m)) space).
- **Damerau–Levenshtein** — adds adjacent transposition as an operation (O(nm)).
- **Weighted / general sequence alignment** — arbitrary substitution and gap costs (O(nm)).
- **Hirschberg's algorithm** — LCS/alignment in linear space via divide-and-conquer on the middle column (O(nm) time, O(min(n,m)) space).
- **Needleman–Wunsch (global) & Smith–Waterman (local) alignment** — bio-sequence DP with affine penalties (O(nm)).
- **Affine gap penalties (Gotoh)** — separate gap-open and gap-extend costs via three coupled DP layers (O(nm)).
- **Shortest Common Supersequence (length & string)** — `n + m − LCS`; reconstruct by merging via the LCS table (O(nm)).
- **Longest Palindromic Subsequence** — LCS of the string with its reverse, or interval DP (O(n^2)).
- **Longest Repeated Subsequence** — LCS of the string with itself excluding the diagonal (O(n^2)).
- **Counting distinct subsequences** — count distinct subsequences (optionally equal to a pattern) with last-occurrence correction (O(nm) or O(n·26)).
- **Regular expression / wildcard matching** — pattern-vs-text DP handling `*`/`?`/`.` (O(nm)).

### Grid / Matrix Path DP

- **Unique paths (lattice counting)** — count monotone paths in a grid; binomial coefficient closed form or DP (O(nm) DP, O(1) combinatorial).
- **Unique paths with obstacles** — zero out blocked cells in the additive DP (O(nm)).
- **Minimum/maximum path sum** — accumulate best cost moving right/down (O(nm), O(min dim) rolling).
- **Min falling path sum** — choose among adjacent cells in the previous row (O(nm)).
- **Maximal square / maximal rectangle of 1s** — `dp` of largest square ending at a cell; rectangle via histogram method (O(nm)).
- **Dungeon game (reverse DP)** — fill from destination to source because survival constraint propagates backward (O(nm)).
- **Cherry pickup (two simultaneous paths)** — synchronized 4-parameter DP for two agents traversing once (O(n^3)).
- **Counting paths with collectible constraints** — extend state with collected-mask or remaining budget (O(nm·2^k)).
- **Grid DP with movement in 3+ directions** — when up/left also allowed, becomes a shortest-path problem on a DAG/graph (see Graph Algorithms).
- **Minimum cost to cut/traverse with diagonal moves** — extend transition set; still layered DP (O(nm)).

### Interval (Range) DP

- **General interval DP template** — `dp[i][j]` over a contiguous range, split at an interior point k, combine subranges (O(n^3) typical, O(n^2) states).
- **Matrix chain multiplication** — minimize scalar multiplications by optimal parenthesization (O(n^3), O(n^2) space).
- **Optimal Binary Search Tree** — minimize expected search cost given access frequencies (O(n^3), O(n^2) with Knuth's optimization).
- **Palindrome partitioning (min cuts)** — fewest cuts so every piece is a palindrome (O(n^2)).
- **Count palindromic substrings / partitions** — interval DP or expand-around-center (O(n^2)).
- **Burst balloons** — choose last balloon to burst in a range; classic "think last, not first" (O(n^3)).
- **Stone game / removing boxes / merge stones** — merging adjacent piles with cost; some need a 4th dimension (count of trailing equal items) (O(n^3)–O(n^4)).
- **Minimum cost to merge stones into one pile** — feasibility `(n−1) mod (k−1)=0`, DP over ranges and group sizes (O(n^3/k)).
- **Polygon triangulation (min weight)** — interval DP picking the apex triangle (O(n^3)).
- **Strange printer / interval coloring** — minimum print operations with overprinting (O(n^3)).
- **Predicting a winner / optimal game value** — two-player range game maximizing score difference (O(n^2)).
- **Zuma-like / interval-merge removal games** — augmented-state interval DP (O(n^4) and up).

### Partition / Segmentation DP

- **Word break (segmentation feasibility & count)** — split a string into dictionary words; boolean/count DP (O(n^2) or O(n·L) with trie).
- **Word break II (enumerate all splits)** — DP feasibility plus backtracking to list segmentations.
- **Minimum / balanced partition into k segments** — split an array into k contiguous parts optimizing an aggregate (O(n^2·k); see optimizations).
- **Painter's partition / book allocation** — minimize the maximum segment sum over k partitions; DP or binary-search-on-answer (O(n^2·k) DP).
- **Largest sum of averages (k partitions)** — maximize sum of segment averages over k cuts (O(n^2·k)).
- **Minimum number of squares/cubes summing to n** — unbounded-knapsack-style partition (O(n·√n)).
- **Integer partition counting (partition function p(n))** — count ways to write n as a sum of positive integers, order-insensitive (O(n·√n) via pentagonal recurrence; O(n^2) basic DP).
- **Restricted partitions (distinct parts, ≤k parts, parts ≤ m)** — constrained partition counting DP (O(n·k) or O(n·m)).
- **Partition into palindromic / monotone runs** — segment DP keyed on run validity (O(n^2)).

### Counting DP & Combinatorial Enumeration

- **Counting lattice paths with restrictions** — paths avoiding obstacles or staying below a line (ballot/Catalan, reflection — see Combinatorics).
- **Catalan-number recurrences** — count BSTs, balanced parentheses, triangulations via `C(n)=ΣC(i)C(n−1−i)` (O(n^2) DP).
- **Counting valid bracket / Dyck sequences** — DP on (position, open-count) prefix balance (O(n^2)).
- **Counting binary strings without forbidden patterns** — DP on automaton/suffix state (e.g., no two adjacent 1s) (O(n·states)).
- **Tiling counts (dominoes, trominoes, L-pieces)** — recurrences for 2×n, 3×n boards; profile/broken-profile DP for general widths (O(n) to O(n·2^m)).
- **Broken-profile (plug) DP for tilings** — column-by-column state encoding protruding cells (O(area·2^width)).
- **Counting paths/walks of length L in a graph** — DP layer-by-layer or matrix power (O(V^3 log L) — see Graph/Math).
- **Counting subsequences/substrings with a property** — accumulate counts under constraints, often with modular arithmetic.
- **Counting with inclusion–exclusion overlay** — combine DP counts via inclusion–exclusion to remove forbidden configurations (see Combinatorics).
- **Modular counting hygiene** — keep all counts modulo a prime, manage subtraction (add modulus), avoid overflow.

### Probability & Expected-Value DP

- **Expected value over states** — `E[s] = Σ p(s→s′)·(cost + E[s′])`; solve on a DAG of states by reverse topological order.
- **Probability propagation DP** — accumulate reachability probabilities forward across stages (O(states × transitions)).
- **Absorbing-state / random-walk expectations** — expected steps to absorption; linear systems when cyclic (Gaussian elimination — see Linear Algebra).
- **Dice / coin sequence problems** — expected throws to reach a sum or pattern (O(target × faces)).
- **Knight/random-walk survival probability** — probability a piece stays on a board after k moves (O(k·cells·moves)).
- **Optimal-strategy expected DP (Markov decision)** — choose action maximizing expected reward per state (O(states × actions)).
- **Gambler's ruin / hitting probabilities** — boundary-value DP recurrences over capital states (O(N)).
- **Linearity-of-expectation decomposition** — sum per-element contribution probabilities instead of enumerating outcomes (often collapses exponential states).
- **Expected value with geometric/self-referential loops** — solve `E = a + b·E` algebraically when a state can return to itself.

### Digit DP

- **Core digit-DP framework** — count numbers in [0, N] (or [L, R] by difference) with a digit property; iterate positions left-to-right (O(digits × states × base)).
- **Tight (bounded) flag** — tracks whether the prefix equals N's prefix, limiting the current digit's range.
- **Leading-zero flag** — distinguishes structural zeros from significant digits (for digit-count/product properties).
- **Carried numeric state** — running sum/remainder/last-digit/mask of digits seen so far as part of the state.
- **Count numbers with digit-sum constraints** — state carries partial digit sum (O(digits × sum × base)).
- **Count numbers divisible by k** — state carries value modulo k (O(digits × k × base)).
- **Count numbers with bounded distinct/repeated digits** — state carries a digit bitmask (O(digits × 2^base × base)).
- **Count numbers avoiding forbidden digits/patterns** — restrict digit choices or carry an automaton state.
- **Sum (not count) of values satisfying a property** — track both count and aggregate value per state for contribution.
- **Digit DP over arbitrary bases / on a fixed-length string mask** — generalize the base and length; same flag machinery.
- **Two-bound (L..R) and 2D digit DP** — handle pairs of numbers or simultaneous bounds with paired tight flags.

### Bitmask DP

- **Subset-state DP fundamentals** — encode a small set (n ≤ ~20–22) as a bitmask; iterate over masks in increasing order (O(2^n × n)).
- **Iterating submasks of a mask** — enumerate all submasks efficiently; total work O(3^n) over all masks.
- **Traveling Salesman (Held–Karp)** — `dp[mask][last]` = min cost visiting `mask` ending at `last` (O(2^n · n^2), O(2^n · n) space).
- **Hamiltonian path/cycle existence & counting** — reachability/count variant of Held–Karp (O(2^n · n^2)).
- **Assignment problem (min-cost perfect matching, small n)** — `dp[mask]` assigns persons to used jobs (O(2^n · n); Hungarian for larger — see Graph Algorithms).
- **Minimum set cover / partition into valid groups** — `dp[mask]` over covered elements iterating submasks (O(2^n · n) or O(3^n)).
- **Counting/min Hamiltonian-style tours with constraints** — add fields (e.g., parity, last-two) to the bitmask state.
- **Bitmask + extra dimension** — combine mask with position/capacity for richer constraints (O(2^n · n · K)).
- **Profile / broken-profile DP** — bitmask over a grid frontier for tilings and connectivity (O(area · 2^width)).
- **Sum over Subsets (SOS) DP** — compute, for every mask, an aggregate over all its submasks via per-bit relaxation (O(n · 2^n)).
- **Superset-sum / zeta & Möbius transforms** — SOS in the superset direction and its inverse for subset convolution (O(n · 2^n)).
- **Subset-sum (fast) convolution** — combine two set functions over subsets using ranked zeta transforms (O(2^n · n^2)).
- **Meet-in-the-middle over subsets** — split n elements into halves, combine via sorting/two-pointer (O(2^(n/2) · n) — see Divide & Conquer).

### DP on Trees (Introduction)

- **Rooted-tree DP template** — root the tree, compute each node's value from its children in a post-order DFS (O(n)).
- **Subtree aggregate DP** — sizes, sums, counts, or max over each subtree (O(n)).
- **Maximum independent set on a tree** — `dp[v][taken/not]` choosing non-adjacent vertices (O(n)).
- **Minimum vertex cover / dominating set on trees** — include/exclude states per node (O(n)).
- **Tree diameter via DP** — longest path through each node = sum of two deepest child depths (O(n)).
- **Longest path / weighted height DP** — track top-two downward extensions per node (O(n)).
- **Rerooting (all-roots) DP** — recompute answers for every root in O(n) using up/down passes (in/out aggregates).
- **Knapsack/grouped DP on trees (tree knapsack)** — combine children subtrees under a budget; small-to-large sizing gives O(n·K) or O(n^2) (see Knapsack Generalizations).
- **Counting matchings/colorings on trees** — multiplicative combination of child counts (O(n·states)).
- **Binary-lifting & sparse-table augmentation** — preprocess ancestor/aggregate jumps for path queries (deeper material — see Graph/Tree Algorithms).

### String DP (Survey)

- **Edit/alignment/LCS family** — covered under Subsequence DP; the core string-distance toolkit.
- **Pattern & wildcard matching DP** — `*`/`?`/`.` matching over text (O(nm)); regex-to-automaton variant.
- **Counting distinct subsequences / matching subsequences** — last-occurrence-corrected counting (O(nm)).
- **Palindrome DP family** — longest palindromic subsequence/substring, min partition cuts, palindrome counting (O(n^2)).
- **Interleaving strings** — can s3 be formed by interleaving s1 and s2 preserving order (O(nm)).
- **Scramble string / recursive string partition** — interval DP over both strings with swap option (O(n^4)).
- **String compression / run-length cost DP** — minimize encoded length over split points (O(n^2)–O(n^3)).
- **Automaton-driven string DP** — DP over states of a KMP/Aho–Corasick automaton to count/avoid patterns (O(n·states) — see String Algorithms).
- **DP on tries** — aggregate or count over a trie's nodes for prefix-structured inputs (O(total length)).

### DP Optimizations (Advanced, Cross-Cutting)

- **Knuth–Yao optimization** — for interval DP with quadrangle inequality and monotone optimal splits, reduce O(n^3) to O(n^2).
- **Divide-and-conquer optimization** — when optimal split points are monotone, partition the cost computation (O(kn log n) from O(kn^2)).
- **Convex Hull Trick (CHT)** — minimize/maximize over linear functions of the state to speed transitions (O(n log n) or O(n) monotone).
- **Li Chao tree** — segment tree of lines supporting arbitrary insertion/query for CHT-style DP (O(n log C)).
- **Lagrangian relaxation / Alien (Aliens) trick** — remove a "use exactly k" constraint via a penalty parameter and binary search (O((n + transition)·log range)).
- **Slope-trick** — maintain a piecewise-linear convex cost function with heaps for 1D optimization DP (O(n log n)).
- **SMAWK / monotone-matrix searching** — find row minima of totally-monotone matrices to accelerate DP (O(n) per matrix).
- **Bitset acceleration** — pack boolean DP rows into machine words for an O(n/word) constant-factor speedup (subset sum, reachability).
- **Matrix-exponentiation on linear recurrences** — express layered/linear DP as a matrix power for huge step counts (O(d^3 log N) — see Math).
- **Sparse / hashed state tables** — store only reachable states when the nominal state space is huge but sparsity is high.
- **Memory-layer rolling for high-dimensional DP** — keep O(1) or O(few) layers when transitions reach back boundedly.

<sub>[↑ Back to top](#table-of-contents)</sub>

---

## 14. Dynamic Programming — Advanced & Optimizations

### Foundations & Prerequisites for Advanced DP

- **DP state & transition recap** — a recurrence over a state space solved by memoization (top-down) or tabulation (bottom-up); optimizations attack either the transition cost or the state count.
- **Transition cost vs. state count** — total time ≈ (#states) × (cost per transition); every optimization here cuts one factor (faster transition) or the other (fewer/cheaper states).
- **Naive transition bottleneck** — many DPs are `dp[i] = min/max over j of (dp[j] + cost(j,i))`, giving O(n²) or O(n²·k); recognizing this shape unlocks CHT, D&C, Knuth, and monotonic-queue speedups.
- **Structural properties enabling speedups** — convexity/concavity of cost, the quadrangle inequality (QI), Monge condition, total monotonicity, and monotone optimal split points; identifying which holds dictates the applicable optimization.
- **Decision monotonicity (opt[i] non-decreasing)** — if the optimal transition index is monotone in the state, sublinear/log-factor speedups (D&C, Knuth, SMAWK) become available.

### Convex Hull Trick (CHT) & Li Chao Tree

#### Core Convex Hull Trick

- **Convex Hull Trick (line-container DP)** — DP of form `dp[i] = min_j(m_j·x_i + b_j)`; maintain lower/upper envelope of lines and query a point (O(n) build amortized, O(log n) or O(1) per query).
- **Lines-as-points duality** — minimizing `m·x+b` over lines at query x equals querying the lower convex hull; geometric view explains why only envelope lines matter.
- **Monotone-slope, monotone-query CHT** — when insertion slopes and query x are both monotone, use a deque/stack with two-pointer queries (O(1) amortized insert and query).
- **Monotone-slope, arbitrary-query CHT** — sorted-slope insertion but unsorted queries; binary-search the envelope per query (O(log n) per query).
- **Bad-line popping (envelope maintenance)** — remove a middle line when its intersection range is dominated by neighbors via the cross/slope test; keeps the hull minimal.
- **Maximum vs. minimum CHT** — negate slopes/intercepts (or maintain the upper hull) to switch between max-envelope and min-envelope queries.

#### Dynamic / Fully General CHT

- **Kinetic Heaps / "Kinetic Segment Tree" CHT** — supports line insertion and point queries with non-monotone slopes by lazy envelope rebuilding (amortized O(log² n) or O(log n)).
- **LineContainer (balanced-BST envelope)** — std::set-style ordered hull supporting arbitrary insert and arbitrary query in O(log n) by storing intersection x-coordinates.
- **Li Chao Tree** — segment tree over the x-domain storing one "dominant" line per node; insert line and query min/max at a point in O(log C) without sorting slopes.
- **Li Chao Tree for segments** — insert a line valid only on an x-interval by descending O(log) tree nodes, each doing a full line insert (O(log² C) per segment).
- **Persistent / dynamic Li Chao Tree** — coordinate-compressed or implicit (dynamic-node) variant over huge/continuous x-ranges; supports offline or online insertion.
- **Generalized non-linear "CHT" (function envelope)** — works for any family closed under the chosen extremum that forms a Davenport–Schinzel-bounded lower envelope.

#### CHT Connections

- **CHT as a special case of D&C / Lagrangian** — CHT applies precisely when the cost has the linear `m_j·x_i` cross-term; relation to Monge/QI structure.
- **Choosing CHT vs. Li Chao** — CHT needs envelope-maintenance care but is fast; Li Chao is simpler to code and handles arbitrary slopes at the cost of a log-domain factor.

### Divide-and-Conquer DP Optimization

- **D&C optimization** — for layered DP `dp_k[i] = min_{j<i}(dp_{k-1}[j] + C(j,i))` with monotone optimal split `opt[i]`, recurse computing the middle index and bounding sub-ranges by its opt (O(k·n·log n)).
- **Monotone optimal-split condition** — requires `opt[i] ≤ opt[i+1]`; implied by the quadrangle inequality / Monge cost (so QI ⇒ D&C applicable).
- **solve(l, r, optl, optr) recursion** — compute `dp[mid]` by scanning only `[optl, optr]`, then recurse left with `[optl, opt[mid]]` and right with `[opt[mid], optr]`.
- **Layer iteration / fixed number of partitions** — typical for "split array into exactly k segments minimizing cost"; one D&C pass per layer.
- **Online cost-function evaluation** — `C(j,i)` often computed via prefix sums, a Mo-like add/remove pointer, or precomputed tables; pointer-based cost adds an amortized factor.
- **D&C vs. Knuth vs. CHT decision** — D&C needs only opt-monotonicity (weaker than QI's `opt[i][j]` 2D monotonicity Knuth needs); CHT needs explicit linear structure.

### Knuth–Yao (Quadrangle Inequality) Optimization

- **Knuth's optimization** — interval DP `dp[i][j] = min_{i<k<j}(dp[i][k] + dp[k][j]) + C(i,j)` reduced from O(n³) to O(n²) using `opt[i][j-1] ≤ opt[i][j] ≤ opt[i+1][j]`.
- **Quadrangle inequality (QI)** — `C(a,c)+C(b,d) ≤ C(a,d)+C(b,c)` for `a≤b≤c≤d`; the structural condition guaranteeing the optimal-split monotonicity.
- **Monge condition** — discrete analog of QI for matrices; a cost array is Monge iff it satisfies the inverse-QI, central to most range-DP speedups.
- **Totally monotone & inverse-monotone cost** — total monotonicity of the cost matrix is the deeper property; QI ⇒ total monotonicity ⇒ Knuth/SMAWK applicability.
- **Knuth-Yao speedup proof sketch** — bounding the inner-loop work by the telescoping width of `opt` ranges yields the amortized O(n²) total.
- **Classic applications** — optimal binary search tree construction, optimal file/matrix merging, and length-limited cost partitioning all satisfy QI.
- **Verifying QI in practice** — checking the 2×2 "adjacent" condition `C(i,j)+C(i+1,j+1) ≤ C(i,j+1)+C(i+1,j)` suffices to prove the full inequality.

### Monotonic-Queue / Deque DP Optimization

- **Sliding-window-minimum DP** — `dp[i] = min over j in [i-k, i-1] of dp[j] (+f(i))`; a monotonic deque keeps candidates and answers each transition in O(1) amortized (O(n) total).
- **Monotonic deque maintenance** — pop dominated tail elements on insert, pop out-of-window head elements on advance; invariant keeps the deque monotone in value.
- **Bounded/grouped knapsack via monotonic queue** — convert bounded-count knapsack into windowed minima over residue classes of the weight, giving O(N·W) instead of O(N·W·count).
- **Constant-shift transitions** — works when the additive term `f(i)` does not depend on j, so comparisons among candidates stay valid as i advances.
- **2D / monotone-matrix sliding window** — row-then-column monotonic-queue passes for windowed minima over a grid DP.
- **Relation to CHT** — monotonic-queue handles constant cross-terms; CHT generalizes to linear cross-terms in x.

### SOS DP (Sum Over Subsets) & Superset Sum

- **Sum over subsets (SOS DP)** — for every mask compute the aggregate over all its submasks in O(n·2ⁿ) by summing one bit-dimension at a time (vs. naive O(3ⁿ)).
- **Superset-sum DP** — symmetric variant aggregating over all supersets of each mask, same O(n·2ⁿ).
- **Dimension-by-dimension (zeta transform) view** — SOS is the subset zeta transform; iterating over bits is a Gauss-like elimination across the Boolean lattice.
- **Möbius transform (SOS inverse)** — invert a subset-sum table to recover original values; subtract instead of add per bit dimension (O(n·2ⁿ)).
- **Subset-sum convolution (OR/AND/subset)** — combine zeta + pointwise multiply + Möbius for AND/OR convolutions; with rank-bookkeeping yields true subset-sum convolution in O(n²·2ⁿ).
- **Counting / contribution over submasks** — typical uses: number of elements whose bits are a submask, max/min over submasks, expected-value aggregation.
- **SOS with extra dimensions** — appending count/parity/rank dimensions to support subset-sum convolution and inclusion–exclusion over masks.

### Bitmask & Subset DP

- **Bitmask DP fundamentals** — encode a subset of ≤ ~20–22 elements as an integer state; transitions add/remove elements (O(2ⁿ · poly) typical).
- **DP over subsets of subsets (3ⁿ enumeration)** — iterate each mask and all its submasks (`sub = (sub-1)&mask`); total work Σ 2^popcount = 3ⁿ; core of set-partition DPs.
- **Set-partition / set-cover DP** — partition a universe into groups, each a valid subset, via submask enumeration (O(3ⁿ)).
- **Traveling Salesman / Hamiltonian-path DP** — `dp[mask][last]` = best path visiting `mask` ending at `last` (O(2ⁿ · n²)).
- **Assignment / minimum-cost matching via bitmask** — assign n tasks to n agents using `dp[mask]` over assigned columns (O(2ⁿ · n)).
- **Counting Hamiltonian / perfect-matching DP** — bitmask counting variants; on bipartite graphs relates to permanent computation.
- **Subset-sum / partition with bitmask reachability** — track reachable subset-sums (overlaps with bitset DP below).
- **Meet-in-the-middle for large subset spaces** — split universe in halves, enumerate 2^(n/2) each, combine by sorting/hashing (O(2^(n/2)·n)); pushes feasible n to ~40 (see Search & Enumeration).
- **Broken-profile vs. full-mask trade-off** — when the natural mask is a grid column, switch to profile/plug DP below.

### Broken-Profile, Connection-Profile (Plug) & General Profile DP

#### Broken-Profile DP

- **Broken-profile (contour) DP** — fill a grid cell-by-cell carrying a "broken profile" bitmask of the frontier between filled and unfilled cells (O(rows·cols·2^cols) typical).
- **Cell-by-cell transition** — at each cell decide tile orientations consistent with the incoming profile bits, producing the outgoing profile; classic for domino/tromino tilings.
- **Profile vs. broken-profile** — full-row profile transitions row-at-a-time (heavier per step); broken-profile advances one cell, keeping the state to a single frontier mask.

#### Connection-Profile / Plug DP

- **Plug DP (connection-profile / contour DP)** — encode connectivity of partial paths/loops crossing the frontier, not just occupancy; counts Hamiltonian cycles, simple-loop coverings, and Steiner-like structures on grids.
- **Bracket-sequence plug encoding** — represent paired path-ends as balanced brackets along the contour; transitions merge/split plugs while preserving matching.
- **Minimum-representation (canonical) connectivity encoding** — relabel connected components to a canonical form so equivalent profiles collapse, drastically cutting states.
- **Hash-map state storage** — profile states are sparse, so store reachable connection-profiles in a hash map rather than a dense array.
- **Applications** — counting Hamiltonian cycles, simple-circuit coverings, maze/loop tilings, and grid spanning-structure counts.

### DP on Trees (Beyond Basic Subtree DP)

- **Subtree (rooted) DP** — combine children's results at each node in one DFS (O(n) or O(n·states)); the baseline for tree DP.
- **Rerooting (all-roots) DP** — compute the answer with every node as root in O(n) total by a downward pass reusing parent contributions after the subtree pass.
- **Prefix/suffix child aggregation for rerooting** — when removing one child's contribution isn't invertible, precompute prefix and suffix combines over the child list (O(deg) per node).
- **In–out / "reroot delta" technique** — push a parent-excluding aggregate down to each child to convert subtree answers into whole-tree answers.
- **Tree knapsack / group-knapsack on trees** — merge child knapsacks with the small-to-large "sibling merge" bound giving O(n²) total over the tree.
- **DP on tree with bounded subtree size (n² merging bound)** — the classic argument that pairwise merging of subtree DPs across the whole tree costs O(n²), not O(n³).
- **Auxiliary / virtual tree DP** — build a compressed tree on k marked vertices (plus LCAs) and DP on it for per-query O(k log k) instead of O(n) (see Trees & LCA).
- **DP on Euler tour / subtree-as-range** — flatten subtrees to contiguous ranges to combine tree DP with segment-tree / BIT range structures.
- **Centroid-decomposition DP** — count/aggregate paths through centroids across recursion layers (O(n log n) typical) (see Trees / Centroid Decomposition).
- **Heavy-light / small-to-large set-merging DP** — propagate per-node multisets up the tree merging the smaller into the larger (O(n log n) or O(n log² n)).
- **DP on trees with matrix/segment-tree-on-HLD transitions** — encode path transitions as matrices composed along heavy paths for dynamic updates.

### Matrix-Exponentiation DP (Linear Recurrences)

- **Matrix exponentiation for linear recurrences** — express `dp` transition as a constant matrix M; the k-th term is `M^k · v₀` via binary exponentiation (O(d³ · log k)).
- **Transition-matrix construction** — build the companion/transfer matrix from the recurrence coefficients or from a state-graph adjacency.
- **Path-counting via adjacency-matrix powers** — number of length-k walks between nodes equals entries of `A^k` (O(V³ · log k)).
- **Affine recurrences (adding a constant)** — augment the state vector with a constant "1" component to handle inhomogeneous `+c` terms.
- **(min,+) / (max,+) tropical matrix exponentiation** — replace +/× with min/+ to compute shortest k-edge paths or DP over fixed step counts (O(V³ · log k)).
- **Kitamasa / Bostan–Mori method** — compute the k-th linear-recurrence term in O(d² log k) (Kitamasa) or O(d log d · log k) with FFT/NTT polynomial mod (Bostan–Mori).
- **Cayley–Hamilton reduction** — reduce `x^k mod` characteristic polynomial to express high powers as low-degree combinations, underpinning Kitamasa.
- **Sparse / structured transfer matrices** — exploit banded, block, or sparse M to multiply faster than O(d³) per step.
- **Berlekamp–Massey + Kitamasa pipeline** — recover the minimal linear recurrence from a prefix of terms, then jump to the k-th term (see Math/Polynomials).

### Slope Trick

- **Slope trick** — maintain a convex piecewise-linear DP function by storing its slope-breakpoints in heaps instead of explicit values (O(n log n) typical).
- **Two-heap representation** — a max-heap for breakpoints left of the minimum and a min-heap for those to the right, plus a running minimum value.
- **Adding `|x − a|` cost** — push the breakpoint into both heaps and rebalance; standard "move to target with cost" transition.
- **Adding a ramp `max(0, x−a)` or `max(0, a−x)`** — insert a one-sided breakpoint affecting only one heap.
- **Prefix-min / clamping operations** — drop the increasing (or decreasing) side to model "free to move in one direction" (e.g., non-decreasing constraints).
- **Global shifts of breakpoints (lazy offset)** — apply ±t to all left/right breakpoints via lazy add tags to model window/feasibility shifts in O(1).
- **Recovering the optimum / argmin** — track the minimum value incrementally; reconstruct the optimal sequence by replaying breakpoints if needed.
- **Applications** — minimum-cost monotone/“make array non-decreasing” problems, assembly/scheduling with absolute-deviation costs, tree slope trick (merging convex functions up a tree).

### Aliens Trick / Lagrangian (Parametric) Optimization

- **Aliens trick (Lagrangian relaxation)** — remove an "exactly k items/segments" constraint by adding a penalty λ per item, binary-searching λ so the unconstrained optimum uses exactly k (O(unconstrained · log range)).
- **Convexity prerequisite** — the optimal value as a function of k must be convex (concave); only then does the λ binary search land on the right count.
- **Penalty-to-count monotonicity** — increasing λ monotonically decreases the chosen count, enabling the binary search on the multiplier.
- **Tie-breaking at the optimal λ** — when integer λ yields a count range straddling k, pick the boundary solution and interpolate or track min/max count achieving each value.
- **Integer vs. real λ search** — using integer λ avoids floating error; careful handling recovers the exact constrained answer at the breakpoint.
- **Combining with other speedups** — the inner unconstrained DP is often itself sped up by CHT/D&C/monotonic-queue, compounding the gains.
- **Relation to parametric search / Lagrangian duality** — λ is the dual multiplier; the technique is parametric search over a piecewise-linear lower-envelope value function.

### Knapsack & Subset-Sum Optimizations

- **0/1 knapsack baseline** — `dp[w]` over capacities with reverse-weight iteration (O(N·W)).
- **Bounded knapsack via binary splitting** — split a count-c item into powers of two so each piece is used 0/1; reduces to 0/1 knapsack (O(N·log(c)·W)).
- **Bounded knapsack via monotonic queue** — residue-class windowed minima for true O(N·W) bounded knapsack (see Monotonic-Queue section).
- **Unbounded knapsack** — forward-weight iteration allowing item reuse (O(N·W)).
- **Bitset subset-sum / feasibility knapsack** — represent reachable sums as a bitset and shift-OR per item, dividing time by the word size (O(N·W / 64)).
- **Divide-by-value / distinct-weights optimization** — with total weight S there are only O(√S) distinct weights; group equal weights to bound work.
- **Partial-sums (prefix) trick for grouped/multiple knapsack** — use prefix sums of the dp row to apply a group of identical items in O(W) per group.
- **Meet-in-the-middle subset-sum** — split items, enumerate 2^(n/2) sums per half, combine by sorting/two-pointer (O(2^(n/2) · n/2)).
- **Knapsack with small number of distinct values** — when item values (not weights) are few, DP over value-classes/counts instead of weights.
- **Fractional / greedy boundary cases** — fractional knapsack is greedy (O(n log n)); note the contrast so the integer-DP scope is clear.
- **Online "knapsack with rollback"** — add/remove items reversibly to answer queries excluding one item (used with “knapsack without an item” tricks).
- **Backtracking the chosen set** — reconstruct which items achieve the optimum from a parent/choice table or by re-deriving from dp rows.

### Memory-Reduction Techniques

- **Rolling array (layer compression)** — keep only the current and previous DP layers (or one layer with careful iteration order) to cut O(n·m) memory to O(m).
- **Single-array in-place updates** — knapsack-style forward/backward sweeps that overwrite one row safely depending on dependency direction.
- **Bit-packing states** — pack Boolean/small DP cells into machine words to shrink memory and speed transitions (with bitset operations).
- **Hirschberg's linear-space alignment** — divide-and-conquer recovery of an optimal edit-distance/LCS alignment in O(n·m) time but O(min(n,m)) space.
- **Linear-space DP traceback (general D&C)** — recover the optimal path of any "monotone progress" DP by recursing on a midpoint computed with rolling arrays.
- **Sliding-window state retention** — keep only the last k layers when transitions look back a bounded distance.
- **Sqrt / block checkpointing** — store every √n-th full layer and recompute intermediate layers on demand to trade time for space.
- **Hashing sparse states** — store only reachable states in a hash map when the dense state space is enormous but actual reachable set is small.

### SMAWK & Totally Monotone Matrix Searching

- **SMAWK algorithm** — find all row minima (or maxima) of a totally monotone n×m matrix in O(n + m) time via REDUCE + interpolate recursion.
- **Total monotonicity (TM) & Monge ⇒ TM** — TM means the row-minimum column index is non-decreasing down rows; every Monge matrix is totally monotone.
- **REDUCE step** — eliminate columns that can never hold a row minimum, shrinking columns to at most rows before recursing.
- **Offline LARSCH / online Monge DP** — the LARSCH algorithm solves the 1D Monge DP (where entries depend on earlier dp results) online in O(n), the online counterpart of SMAWK.
- **SMAWK for D&C-style DP layers** — replace the O(n log n) D&C optimization with O(n) per layer when the cost matrix is totally monotone.
- **Knuth–Yao as a TM special case** — the Knuth speedup is subsumed by totally-monotone-matrix searching; SMAWK generalizes it.
- **Monge & inverse-Monge maxima** — searching for maxima needs inverse total monotonicity; flip signs/orientation accordingly.
- **Applications** — concave/convex least-weight subsequence, gap/affine sequence alignment, k-link shortest paths, Monge-structured transportation DPs.

### Persistent, Online & Dynamic DP

- **Persistent DP structures** — keep historical DP versions (persistent segment tree / Li Chao) to answer queries about prefixes or earlier states (O(log) per access).
- **Dynamic DP (segment-tree-on-sequence / HLD)** — maintain a DP under point updates by composing transition matrices (semiring) over a segment tree; updates in O(log n · matrix-cost).
- **Top-tree / HLD dynamic tree DP** — recompute rerooting/path DP under edge/vertex updates in O(polylog n) using matrix or merge operators on heavy paths.
- **Online D&C / LARSCH** — solve Monge DP without knowing all cost entries up front, revealing dp values as the recursion needs them.
- **Incremental / decremental DP (rollback)** — support adding or undoing the last item via a rollback stack (e.g., knapsack with rollback, DSU-on-tree-style DP).
- **CDQ divide-and-conquer for "DP with data-structure updates"** — split the timeline so earlier states contribute to later transitions via an offline D&C over operations (O(n log² n) typical).

### Other Standard Advanced DP Techniques & Paradigms

#### Counting & Constrained Enumeration

- **Digit DP** — count numbers ≤ N (or in a range) satisfying digit constraints via `dp[position][tight][state]` (O(digits · states · base)).
- **Digit DP with multiple bounds / leading-zero flag** — extend with low/high tightness and a started/leading-zero dimension for ranges and length handling.
- **DP on automata / Aho–Corasick DP** — count strings avoiding/containing patterns by walking the automaton as the DP state (O(len · states · alphabet)).
- **Profile DP for tilings / colorings** — count grid tilings/colorings via column-profile transitions (overlaps with broken-profile section).
- **Inclusion–exclusion DP** — fold inclusion–exclusion sign-bookkeeping into a subset/state DP to count "at least/exactly" configurations.
- **Connected-component / connected-subgraph counting DP** — subset DP combined with inclusion–exclusion to count connected labeled structures (O(3ⁿ) or O(2ⁿ·n)).

#### Probabilistic, Game & Optimization DP

- **Expected-value / probability DP** — states hold expectations; cyclic dependencies solved by Gaussian elimination or convergence iteration.
- **DP with Gaussian elimination (absorbing-chain expectations)** — set up linear equations for expected steps/values when the state graph has cycles (O(states³)).
- **Game / minimax (Sprague–Grundy) DP** — DP over positions with min/max alternation or Grundy values for impartial games (see Game Theory).
- **DP on intervals / range merging** — `dp[l][r]` interval DP (matrix-chain, optimal BST, burst-balloon) often accelerated by Knuth/D&C.
- **DP + binary search ("DP-able predicate")** — binary search an answer and verify feasibility with a linear/greedy DP (O(check · log range)).

#### Convolution & Generating-Function DP

- **Polynomial / generating-function DP** — represent DP layers as polynomials and combine via multiplication (NTT/FFT) for O(n log n) transitions (see Polynomials & FFT).
- **Subset-sum / knapsack as polynomial product** — knapsack counts are coefficients of a product of `(1 + x^{w_i})`; FFT/NTT speeds counting variants.
- **Divide-and-conquer over generating functions (CDQ-NTT)** — online convolution where `dp[i]` depends on earlier coefficients, resolved by D&C + NTT (O(n log² n)).
- **(max,+) / (min,+) convolution DP** — merge convex DP arrays in O(n) via SMAWK when one operand is convex (Monge), else O(n²) naive.
- **Walsh–Hadamard / zeta-Möbius convolutions** — XOR/AND/OR-convolution DP transitions (see SOS DP and Polynomials & FFT).

#### Higher-Order & Mixed Optimizations

- **Combining Aliens + D&C/CHT** — Lagrangian relaxation wrapping a transition-optimized inner DP for "exactly k segments" with linear/convex costs.
- **Two-layered optimizations** — e.g., monotonic queue inside a D&C layer, or CHT inside an Aliens binary search; recognizing nested structure is key at hard level.
- **DP on broken structures with rollback / persistence** — combining profile DP with persistent storage for offline range queries.
- **Sqrt-decomposition DP / Mo's algorithm on DP states** — rebuild DP every √-block to answer offline queries with controlled recomputation (see Sqrt Decomposition).
- **Approximation / scaling DP** — value/weight scaling for FPTAS knapsack-type problems (O(n²·1/ε) style) when exact pseudopolynomial DP is too slow (see Approximation).

<sub>[↑ Back to top](#table-of-contents)</sub>

---

# 🕸️ Part — Graphs & Trees

## 15. Graph Fundamentals & Traversal

### Graph Definitions & Terminology

#### Core Object & Vertex/Edge Vocabulary
- **Graph (G = (V, E))** — set of vertices V and edges E joining vertex pairs; the foundational discrete structure.
- **Vertex / node** — a fundamental graph element; |V| = n is the order of the graph.
- **Edge / arc** — a connection between two vertices; |E| = m is the size of the graph.
- **Endpoint & incidence** — the two vertices an edge joins; an edge is incident to its endpoints, a vertex incident to its edges.
- **Adjacency** — two vertices are adjacent (neighbors) if an edge joins them; two edges adjacent if they share an endpoint.
- **Loop (self-loop)** — an edge whose two endpoints coincide; contributes 2 to undirected degree.
- **Parallel (multiple) edges** — distinct edges sharing the same endpoint pair; forbidden in simple graphs.
- **Simple graph** — no loops and no parallel edges; at most C(n,2) edges undirected.
- **Multigraph** — graph permitting parallel edges (and often loops); needed for Euler-tour and flow modeling.
- **Pseudograph** — multigraph that additionally allows self-loops.
- **Order vs size** — order = number of vertices n, size = number of edges m; key for stating complexity.

#### Edge Types & Orientation
- **Undirected graph** — edges are unordered pairs {u, v}; symmetric adjacency.
- **Directed graph (digraph)** — edges are ordered pairs (u → v) with a tail and a head.
- **Oriented graph** — digraph with at most one of (u→v) or (v→u) for any pair; no 2-cycles.
- **Mixed graph** — contains both directed and undirected edges simultaneously.
- **Weighted graph** — each edge carries a numeric weight/cost; underlies shortest-path and MST domains.
- **Capacitated graph** — edges carry capacities (and optionally costs) for flow/matching models.
- **Labeled / colored graph** — vertices or edges annotated with labels used by problem semantics.

#### Structural Graph Classes
- **Complete graph (Kₙ)** — every pair of distinct vertices adjacent; n(n−1)/2 edges.
- **Empty / edgeless graph** — n vertices, no edges; n trivial components.
- **Regular graph (k-regular)** — every vertex has identical degree k.
- **Bipartite graph** — vertices split into two independent sets with all edges crossing; ⇔ no odd cycle ⇔ 2-colorable.
- **Complete bipartite graph (K_{m,n})** — every left vertex adjacent to every right vertex.
- **k-partite graph** — vertices partitioned into k independent sets.
- **Planar graph** — drawable in the plane without edge crossings; m ≤ 3n−6 (simple, n ≥ 3), m ≤ 2n−4 if bipartite (see Geometry / Planar Graphs).
- **DAG (directed acyclic graph)** — digraph with no directed cycle; admits a topological order.
- **Tree** — connected acyclic undirected graph; exactly n−1 edges, unique path between any two vertices (see Trees).
- **Forest** — disjoint union of trees; acyclic undirected graph with c components and n−c edges.
- **Functional graph** — digraph where every vertex has out-degree exactly 1 (a successor mapping).
- **Tournament** — orientation of a complete graph; every pair has exactly one directed edge.
- **Sparse vs dense graph** — sparse: m = O(n) (favors adjacency lists); dense: m = Θ(n²) (favors matrices).
- **Grid graph** — implicit graph of cells with 4-/8-/6-connectivity; ubiquitous in implicit search.
- **Interval / chordal / cograph / split graph** — special structured classes with linear-time recognition and DP-friendly properties.

#### Walks, Paths, Cycles & Connectivity Terms
- **Walk** — alternating vertex/edge sequence; edges/vertices may repeat.
- **Trail** — a walk with no repeated edge (vertices may repeat).
- **Path (simple path)** — a walk with no repeated vertex; hence no repeated edge.
- **Circuit / closed trail** — a trail that starts and ends at the same vertex.
- **Cycle (simple cycle)** — closed path with no repeated vertex except endpoints; length ≥ 3 in simple undirected graphs.
- **Girth & circumference** — length of the shortest / longest cycle respectively.
- **Distance** — minimum number of edges (or min total weight) on a path between two vertices; ∞ if unreachable.
- **Eccentricity, radius, diameter** — max distance from a vertex; min/max eccentricity over the graph.
- **Connected (undirected)** — a path exists between every vertex pair.
- **Strongly / weakly connected (directed)** — directed path both ways for all pairs / connected when orientation ignored (see SCC domain).
- **Connected component** — maximal connected subgraph.
- **Reachability** — existence of a (directed) path from u to v.
- **Cut vertex (articulation) & bridge** — removal disconnects the graph; biconnectivity concepts (see Connectivity / Bridges & Articulation).

#### Subgraphs & Operations
- **Subgraph** — graph using a subset of vertices and edges of G.
- **Induced subgraph** — all edges of G among a chosen vertex subset.
- **Spanning subgraph** — subgraph containing all vertices of G.
- **Complement graph (Ḡ)** — same vertices, edges exactly where G has none.
- **Transpose / reverse graph (Gᵀ)** — directed graph with every edge reversed; used in SCC and reverse-reachability.
- **Line graph L(G)** — vertices are edges of G, adjacent if they share an endpoint.
- **Contraction / minor** — merge an edge's endpoints; minors underlie planarity (Kuratowski/Wagner) and structure theory.
- **Underlying undirected graph** — digraph with edge directions dropped; used for weak connectivity.
- **Condensation graph** — DAG of strongly connected components (see SCC domain).

### Graph Representations

#### Explicit Representations
- **Adjacency matrix** — n×n boolean/weight matrix; edge query O(1), space O(n²); ideal for dense graphs and bitset tricks.
- **Adjacency list** — per-vertex list of neighbors; space O(n+m), iterate neighbors in O(deg); default for sparse graphs.
- **Edge list** — flat array of (u, v, w) triples; space O(m); used by Kruskal, Bellman–Ford, and input parsing.
- **Incidence matrix** — n×m vertex–edge matrix; mainly theoretical / linear-algebraic uses.
- **Compressed Sparse Row (CSR)** — flat neighbor array plus per-vertex offset array; cache-friendly, fixed after build, fast for huge static graphs.
- **Linked / forward-star adjacency** — head[] plus next[] arrays storing edges; pointer-free list with O(1) edge insertion, common in flow code.
- **Adjacency map (hash-based)** — neighbor lookup via hash sets/maps; O(1) expected edge query for sparse labeled graphs.
- **Doubly-linked edge structure** — supports efficient edge deletion (e.g., Hierholzer, dynamic Euler tours).

#### Paired-Edge & Specialized Encodings
- **Twin-edge / XOR pairing** — store edge e and its reverse at indices 2k and 2k+1 (or e^1) for flow residuals and undirected DFS bridge-finding.
- **Half-edge / DCEL** — doubly-connected edge list encoding planar embeddings and faces (see Geometry / Planar).
- **Bitset adjacency rows** — pack each matrix row into machine words; O(n²/64) BFS/transitive-closure and triangle counting.
- **Implicit / state graph** — vertices are problem states, edges generated on demand by a successor function; never materialized fully.
- **Coordinate / cell encoding for grids** — map (r, c) to r·W + c (and back) to index a grid as a flat graph.
- **Hierarchical / contracted representation** — multilevel graphs (contraction hierarchies, etc.) for fast repeated queries.

#### Representation Trade-offs
- **Dense → matrix, sparse → list** — choose by edge density to balance memory vs neighbor-iteration cost.
- **Static vs dynamic** — CSR/forward-star excel when fixed; hash adjacency or balanced structures when edges mutate.
- **Memory footprint** — matrix O(n²) vs list O(n+m); decisive when n is large but m small.
- **Cache locality** — CSR and flat arrays beat pointer-chasing lists on large traversals despite equal asymptotics.

### Degrees & Elementary Counting

- **Degree (undirected)** — number of incident edge-ends at a vertex; a loop adds 2.
- **In-degree / out-degree (directed)** — count of incoming / outgoing arcs at a vertex.
- **Degree sequence** — multiset of all vertex degrees; Erdős–Gallai / Havel–Hakimi test graphical realizability.
- **Handshake lemma** — sum of all degrees = 2m; hence the count of odd-degree vertices is always even.
- **Directed degree-sum identity** — Σ in-degree = Σ out-degree = m.
- **Source / sink** — vertex with in-degree 0 / out-degree 0; entry/exit points of a DAG.
- **Isolated vertex & pendant (leaf)** — degree 0 / degree 1 vertex.
- **Min/max degree (δ, Δ)** — extremal degrees; bound colorability, connectivity, and Hamiltonicity.
- **Edge count from degrees** — m = (Σ deg)/2 undirected; a quick consistency check on input.
- **Maximum edges** — simple undirected ≤ n(n−1)/2; simple directed ≤ n(n−1); detect "complete-ish" inputs.

### Breadth-First Search (BFS)

#### Core BFS
1. Initialize a queue with the source, mark it visited, set its distance to 0.
2. Dequeue a vertex, scan its neighbors, enqueue each unvisited neighbor while marking visited and setting distance.
3. Repeat until the queue empties, producing a non-decreasing distance (level) order.
- **Standard BFS** — layer-by-layer traversal from a source (O(n+m) time, O(n) space).
- **Shortest path in unweighted graphs** — BFS distances are exact minimum edge counts; correctness from FIFO level ordering.
- **BFS tree & parent array** — predecessor pointers reconstruct one shortest path per vertex; tree edges go between consecutive levels.
- **Level / distance array** — dist[v] gives the BFS layer index; enables level-grouped processing.
- **Visited / discovered marking** — mark on enqueue (not dequeue) to avoid duplicate queue insertions.

#### BFS Variants
- **Multi-source BFS** — seed the queue with several sources at distance 0; computes nearest-source distance to every vertex in one O(n+m) pass.
- **Bidirectional BFS** — search forward from source and backward from target, meeting in the middle (≈ b^{d/2} vs b^d frontier).
- **Boundary-fill / region BFS** — iterative flood fill on grids using a queue (O(cells)).
- **BFS by complement graph** — traverse the complement in O(n+m) total using a global unvisited set, avoiding the dense Ḡ explicitly.
- **0-K BFS via bucket / dial** — small bounded weights bucketed by distance for near-linear shortest paths (see Shortest Paths / Dial's algorithm).
- **Lexicographic BFS (Lex-BFS)** — partition-refinement ordering for chordal-graph recognition and perfect elimination orderings (O(n+m)).
- **BFS on implicit/state graphs** — generate successors on the fly with a visited hash set (e.g., puzzles, word ladders, sliding tiles).
- **Word/string transformation BFS** — states are strings, edges single-character changes; classic shortest-transformation pattern.

#### BFS-Derived Quantities
- **Connected component via BFS** — single BFS labels one component (O(component size)).
- **Shortest cycle / girth (unweighted)** — BFS from each vertex, detect a non-parent revisit; girth in O(n·m).
- **Tree diameter via double BFS/DFS** — farthest vertex from any start, then farthest from it gives the diameter (O(n+m)) (see Trees).
- **Eccentricity / radius / diameter (unweighted)** — BFS from every vertex (O(n·m)); sampling/2-sweep heuristics for large graphs.
- **Counting shortest paths** — accumulate path counts level by level during BFS (mod a prime when large).
- **BFS layering for bipartite check** — odd vs even layer parity; an edge within a layer proves an odd cycle.

### 0-1 BFS (Deque BFS)

- **0-1 BFS** — shortest paths when every edge weight is 0 or 1, using a double-ended queue (O(n+m) time, O(n) space).
1. Push the source with distance 0.
2. Pop the front vertex; relax each edge: weight-0 neighbors are pushed to the front, weight-1 neighbors to the back.
3. Accept a vertex's distance the first time it is finalized (skip stale larger entries).
- **Front/back invariant** — the deque holds at most two consecutive distance values, preserving monotone order without a heap.
- **Lazy stale-entry handling** — discard a popped vertex if its stored distance exceeds the recorded best.
- **Grid teleport / free-move modeling** — encode "free" transitions as 0-edges and "costly" steps as 1-edges.
- **Relation to Dijkstra/Dial** — 0-1 BFS is the binary-weight special case of Dijkstra; generalizes to small integer weights via Dial's buckets (see Shortest Paths).
- **0-1-on-implicit-grid pattern** — e.g., minimum wall breaks / direction changes where turning costs 1 and straight costs 0.

### Depth-First Search (DFS)

#### Core DFS
- **Recursive DFS** — explore as deep as possible, backtracking on dead ends (O(n+m) time, O(n) recursion-stack space).
- **Iterative DFS (explicit stack)** — manual stack to avoid recursion-depth limits on large/deep graphs.
- **Visited coloring** — white (unseen) / gray (on stack) / black (finished) tri-state drives most DFS applications.
- **Discovery & finish times (tin/tout)** — entry/exit timestamps; ancestor test via interval nesting tin[u] ≤ tin[v] ≤ tout[v] ≤ tout[u].
- **Preorder traversal** — process a vertex on first visit (entry order).
- **Postorder traversal** — process a vertex after all descendants finish; reverse postorder gives a topological order on a DAG.
- **DFS tree & forest** — tree edges discovered during DFS; multiple roots form a forest over disconnected graphs.
- **Stack-overflow avoidance** — convert deep recursion to an explicit stack, or raise stack limits, for chains up to ~10^5–10^6.

#### DFS Edge Classification
- **Tree edge** — edge leading to an unvisited (white) vertex; forms the DFS tree.
- **Back edge** — edge to a gray ancestor; its presence certifies a cycle (directed or undirected).
- **Forward edge** — edge to an already-finished descendant (directed graphs only).
- **Cross edge** — edge between vertices in different subtrees / earlier-finished vertex (directed graphs).
- **Undirected case** — only tree and back edges occur (forward/cross collapse into back edges).
- **Classification rule** — use colors plus tin/tout: gray target ⇒ back, black descendant ⇒ forward, otherwise cross.

#### DFS Applications (Fundamental)
- **Connected components** — one DFS per unvisited vertex labels all components (O(n+m)).
- **Path / reachability existence** — DFS from a source determines all reachable vertices.
- **Cycle detection** — back edge ⇒ cycle; recover the cycle via the parent/ancestor chain.
- **Topological sort (DFS-based)** — reverse postorder of a DAG (O(n+m)).
- **Bridges & articulation points** — low-link (lowest reachable tin) computation in one DFS (see Connectivity).
- **Strongly connected components** — Tarjan / Kosaraju via DFS (see SCC domain).
- **Euler tour / subtree flattening** — tin/tout linearize a tree for range queries (see Trees / Euler tour).
- **Generic backtracking search** — DFS over an implicit state tree with pruning (see Search / Backtracking).

### Connected Components & Flood Fill

#### Undirected Connectivity
- **Connected component enumeration** — BFS/DFS from each unvisited vertex assigns component IDs (O(n+m)).
- **Component sizes & count** — tally per-label sizes during traversal; count = number of traversal restarts.
- **Union-Find (DSU) components** — near-O(α(n)) per union/find with path compression + union by rank/size (see Disjoint Set Union).
- **Incremental / online components** — DSU answers connectivity as edges arrive (offline reverse-deletion for deletions) (see DSU).
- **Largest / smallest component queries** — track max/min size while building component labels.
- **Bipartite components** — 2-color each component independently; report per-component bipartiteness.

#### Directed Connectivity
- **Weak components** — components of the underlying undirected graph.
- **Strong components** — maximal mutually reachable vertex sets (Tarjan/Kosaraju) (see SCC domain).
- **Reachability sets** — DFS/BFS from a vertex (or bitset transitive closure for small n).
- **Condensation / DAG of SCCs** — contract each SCC to a node, yielding a DAG for DP (see SCC).

#### Flood Fill
- **Recursive flood fill** — DFS-style fill of a contiguous same-value region on a grid (O(cells)).
- **Iterative (queue/stack) flood fill** — avoids recursion limits on large grids; BFS- or DFS-ordered.
- **4- vs 8-connectivity** — orthogonal neighbors vs orthogonal+diagonal; problem-defined adjacency.
- **Scanline flood fill** — fill maximal horizontal spans per row; far fewer stack operations on large fills.
- **Region labeling / connected-component labeling** — assign distinct IDs to all maximal regions (one fill each).
- **Region statistics** — area, perimeter, bounding box, and centroid accumulated during a fill (e.g., island area / perimeter).
- **Border / boundary flood fill** — fill outward from the frame to mark exterior (e.g., enclosed-region detection).
- **Multi-color / threshold fill** — fill while a tolerance/predicate holds (image-style fills).

### Topological Sort

- **Topological order** — linear vertex order of a DAG with every edge pointing forward; exists iff the graph is acyclic.

#### Algorithms
- **Kahn's algorithm (BFS)** — repeatedly remove in-degree-0 vertices, decrementing successors' in-degrees (O(n+m)).
1. Compute all in-degrees and enqueue every in-degree-0 vertex.
2. Pop a vertex, append it to the order, decrement each successor's in-degree, enqueue any that reach 0.
3. If fewer than n vertices were output, the graph contains a cycle.
- **DFS-based topological sort** — output reverse postorder; emit each vertex after its descendants finish (O(n+m)).
- **Cycle detection via Kahn** — leftover positive in-degrees / output count < n proves a directed cycle.
- **Lexicographically smallest topo order** — replace Kahn's queue with a min-priority queue over ready vertices (O((n+m) log n)).
- **Lexicographically largest topo order** — reverse the graph, take smallest order of the reverse, then reverse the result.
- **All topological orderings** — backtracking enumeration over in-degree-0 choices (exponential; for small n).
- **Uniqueness test** — order is unique iff at every Kahn step exactly one vertex has in-degree 0 (equivalently the order forms a Hamiltonian path in the DAG).

#### Applications
- **DAG dynamic programming** — process vertices in topo order so all dependencies are settled first.
- **Longest path / critical path in a DAG** — DP over topo order (O(n+m)); basis of project scheduling / PERT.
- **Number of paths between vertices in a DAG** — accumulate path counts in topo order.
- **Dependency / build ordering** — task, course, or compilation sequencing.
- **Reachability counting & closure on DAGs** — propagate reachable sets along topo order (bitset for small n).
- **Functional-dependency / 2-SAT implication ordering** — topo order of the implication-graph condensation (see 2-SAT).

### Cycle Detection

#### Directed Graphs
- **Color-based DFS detection** — a gray (on-stack) target during DFS marks a back edge and thus a cycle (O(n+m)).
- **Cycle recovery** — record parents; on a back edge to ancestor a, walk parent pointers from the current vertex back to a.
- **Kahn-based detection** — if topological sort outputs fewer than n vertices, a cycle exists.
- **Recursion-stack membership** — equivalent on-stack flag implementation without explicit colors.
- **Self-loop & 2-cycle detection** — trivial direct checks; often special-cased.

#### Undirected Graphs
- **DFS back-edge detection** — any edge to a visited vertex other than the immediate parent indicates a cycle (handle parallel edges via edge IDs).
- **Union-Find detection** — adding an edge whose endpoints already share a set closes a cycle (O(m α(n))).
- **Parent-aware traversal** — skip the single edge back to the parent to avoid false positives.
- **Cycle recovery (undirected)** — reconstruct via DFS parent chain between the back edge's endpoints.

#### Specialized Cycle Tasks
- **Cycle in a functional graph** — every component has exactly one cycle; find it with the successor pointer (see Functional Graphs).
- **Floyd's tortoise-and-hare** — O(1)-space cycle detection on a single successor sequence (O(λ+μ) where λ,μ are tail/cycle lengths).
- **Brent's cycle algorithm** — faster constant factors than Floyd for finding cycle length/start.
- **Shortest cycle (girth, unweighted)** — BFS from each vertex (O(n·m)).
- **Negative cycle detection** — Bellman–Ford / SPFA / Floyd–Warshall diagonal check (see Shortest Paths).
- **Odd-cycle detection** — equivalent to bipartiteness failure via 2-coloring.
- **Counting cycles / triangles** — triangle counting by adjacency-bitset or degree-ordered enumeration (O(m·√m)) (see Graph Counting).

### Bipartite Check & 2-Coloring

- **2-coloring** — assign one of two colors so no edge is monochromatic; succeeds iff the graph is bipartite.
- **BFS 2-coloring** — color the source, alternate by BFS layer parity; a same-colored edge proves non-bipartite (O(n+m)).
- **DFS 2-coloring** — color along DFS, conflict on a back edge to a same-colored vertex (O(n+m)).
- **Odd-cycle certificate** — extract the offending odd cycle from the conflicting edge's parent chains as proof of non-bipartiteness.
- **Per-component bipartiteness** — test each connected component independently; the graph is bipartite iff all are.
- **DSU-with-parity / weighted DSU** — maintain relative color via edge-parity unions; conflict ⇒ odd cycle (O(m α(n))).
- **Bipartite consequences** — enables Kőnig's theorem, Hall's theorem, and bipartite matching/vertex-cover (see Matching).
- **Two-color generalization (k-coloring)** — proper coloring for k ≥ 3 is NP-hard (see NP-hard / Graph Coloring).

### Eulerian Paths & Circuits

#### Existence Conditions
- **Eulerian circuit (undirected)** — exists iff the graph is connected (over edges) and every vertex has even degree.
- **Eulerian path (undirected)** — exists iff connected and exactly 0 or 2 vertices have odd degree (path endpoints are the odd ones).
- **Eulerian circuit (directed)** — exists iff the graph is connected (one nontrivial SCC over edges) and in-degree = out-degree at every vertex.
- **Eulerian path (directed)** — exists iff connected and at most one vertex has out−in = +1 (start) and one has out−in = −1 (end), all others balanced.
- **Isolated-vertex caveat** — vertices with no edges are ignored when checking edge-connectivity.

#### Construction
- **Hierholzer's algorithm** — splice edge-disjoint cycles into one Euler tour using an iterative stack and edge deletion (O(n+m)).
1. Start at a valid start vertex; follow unused edges, marking them used, until returning/stuck.
2. While the current stack-top has unused edges, branch into a new subtour, then emit vertices on backtrack.
3. The reversed emission order is the Eulerian path/circuit.
- **Fleury's algorithm** — avoid burning a bridge unless forced; simpler to state but O(m²) (rarely used competitively).
- **Edge-used marking & current-arc pointers** — per-vertex "next unused edge" iterator keeps Hierholzer linear.
- **Twin-edge handling (undirected)** — mark both directions of an edge used via paired indices (e^1).
- **Recovering the actual edge sequence** — track edge IDs, not just vertices, when output requires edges.

#### Related Constructions & Variants
- **De Bruijn sequence B(k, n)** — shortest cyclic string over k symbols containing every length-n string exactly once; built as an Euler circuit on the De Bruijn graph (length kⁿ).
- **De Bruijn graph** — vertices are (n−1)-grams, edges are n-grams; Eulerian by construction.
- **Eulerization / route inspection** — add minimum duplicate edges to make all degrees even (Chinese Postman) (see Matching / T-joins).
- **Chinese Postman Problem** — shortest closed walk covering every edge; solved via min-weight matching of odd-degree vertices (see Matching).
- **Edge-disjoint cycle decomposition** — an Eulerian graph decomposes into edge-disjoint cycles (basis of Hierholzer).
- **BEST theorem** — counts Eulerian circuits in a connected Eulerian digraph via arborescences and degrees.
- **Domino / sequence-reconstruction modeling** — many "arrange tiles/words end-to-end" problems reduce to Eulerian path existence.

### Hamiltonian Paths & Cycles
- **Hamiltonian path / cycle** — visits every vertex exactly once / and returns to start; deciding existence is NP-complete (see NP-hard / Hamiltonicity).
- **Bitmask DP (Held–Karp style)** — Hamiltonian path/TSP over subsets in O(2ⁿ·n²) for small n (see Dynamic Programming / Bitmask DP & TSP).
- **Dirac / Ore sufficient conditions** — min degree ≥ n/2 (Dirac) or degree-sum conditions (Ore) guarantee a Hamiltonian cycle.
- **Tournament Hamiltonian path** — every tournament has a Hamiltonian path (constructive insertion in O(n²) / O(n log n)).
- **Gray code as a Hamiltonian cycle** — traversal of the hypercube graph visiting all 2ⁿ bit-strings once.
- **Relation to Eulerian via line graph** — a Hamiltonian path in L(G) corresponds to an Eulerian path in G (structural link, not an easy reduction).

### Grid Graphs & Implicit Search

- **Grid-as-graph modeling** — cells are vertices, allowed moves are edges; never build adjacency explicitly.
- **4-/8-/6-connectivity** — orthogonal, king-move, or hex/triangular neighborhoods chosen per problem.
- **Direction vectors (dx/dy)** — iterate offsets to generate neighbors uniformly.
- **In-bounds & obstacle checks** — guard neighbor generation by grid bounds and blocked cells.
- **Coordinate flattening** — map (r,c) ↔ r·W+c for compact visited arrays and DSU indexing.
- **Multi-source grid BFS** — simultaneous expansion from many seeds (fire/rot spread, nearest-resource distance) (O(cells)).
- **0-1 / weighted grid BFS** — turn-cost, terrain-cost, or wall-break modeling via 0/1 or general edge weights.
- **Layered / state-augmented grids** — replicate the grid per auxiliary state (keys held, fuel, direction, time mod k) to keep search shortest-path-correct.
- **Implicit state-space search** — vertices are encoded states (puzzle boards, configurations); successors generated by a move function with a visited hash set.
- **Visited encoding for huge state spaces** — hashing, Zobrist hashing, or bit-packing of states to fit memory.
- **Grid connected components / flood fill** — island counting, region sizing, and labeling via BFS/DFS over cells.
- **Periodic / toroidal grids** — wrap-around neighbor indexing modulo dimensions.
- **Meet-in-the-middle on state graphs** — bidirectional search to halve the explored radius (see Search).

### Functional Graphs (Successor Graphs)

- **Functional graph** — out-degree exactly 1 everywhere; the successor map next[v] defines the structure.
- **Rho-shape components** — each weakly connected component is a set of "ρ"-shaped trees feeding into a single directed cycle.
- **Cycle finding per component** — detect the unique cycle via visited-coloring DFS or pointer tracing (O(n)).
- **Tortoise-and-hare on functional graphs** — Floyd/Brent locate cycle start and length in O(1) extra space.
- **Tail (rho) length & cycle length** — μ (steps to enter cycle) and λ (cycle length) characterize each starting vertex.
- **Binary lifting / k-th successor** — precompute up[v][j] = 2ʲ-th successor for O(log k) jump queries (O(n log n) build).
- **k-th successor via cycle math** — once on the cycle, take remaining steps modulo λ in O(1).
- **Distance / meeting queries** — does a from b reach a common vertex, and after how many steps, using tail/cycle decomposition.
- **Functional-graph DP** — process trees feeding the cycle, then resolve the cycle separately (common "teleporters"/"successor" problems).
- **Reverse functional graph** — invert next[] (a general graph) to compute, e.g., how many vertices map into each node.
- **Permutation cycle structure** — a bijective functional graph is a permutation; components are pure cycles (cycle decomposition, order via lcm of cycle lengths).
- **Detecting eventual periodicity** — iterated-function/state sequences (e.g., Pollard's rho) reduce to functional-graph cycle detection.

### Cross-Cutting & Advanced Traversal Techniques

- **Iterative deepening DFS (IDDFS)** — repeated depth-limited DFS to get BFS-optimal depth with DFS memory (see Search).
- **Bidirectional search** — meet-in-the-middle frontier expansion for unweighted/uniform graphs.
- **Small-to-large merging on DFS** — merge subtree sets into the larger one for O(n log n) aggregate work (see Trees).
- **Euler tour technique** — flatten a rooted tree by entry/exit times for range structures (see Trees).
- **DFS low-link framework** — unified low[] machinery powering bridges, articulation points, SCC, and biconnected components (see Connectivity / SCC).
- **Strong orientation (Robbins' theorem)** — a bridgeless connected graph can be oriented to be strongly connected via DFS (O(n+m)).
- **2-SAT via implication graph SCCs** — satisfiability from condensation ordering (see 2-SAT).
- **Graph complement traversal trick** — BFS/DFS the complement in O(n+m) using a global unvisited pool, avoiding O(n²) edges.
- **Bitset BFS / transitive closure** — pack frontiers into words for O(n²/64) reachability on small dense graphs.
- **Frontier / level-synchronous BFS** — process whole layers at once for parallelism and vectorization.
- **Visited-stamp (timestamp) trick** — reuse a single int array across many traversals by comparing against a global run id, avoiding O(n) resets.

<sub>[↑ Back to top](#table-of-contents)</sub>

---

## 16. Shortest Path Algorithms

### Foundations & Problem Taxonomy

- **Single-Source Shortest Path (SSSP)** — shortest distances from one source to all vertices; backbone of most algorithms here.
- **Single-Pair / Point-to-Point (s-t) SP** — shortest path between two fixed vertices; enables early termination and bidirectional pruning.
- **Single-Destination SP** — distances from all vertices to one sink; solved by SSSP on the reverse (transpose) graph.
- **All-Pairs Shortest Path (APSP)** — distances between every ordered vertex pair; Floyd-Warshall / Johnson / repeated SSSP.
- **Edge-weight regimes** — unweighted, unit/integer, non-negative real, arbitrary (possibly negative) weights each admit different optimal algorithms.
- **Optimal substructure** — every prefix of a shortest path is itself shortest; the principle underlying all DP/relaxation methods.
- **Relaxation (edge relaxation)** — core update `dist[v] ← min(dist[v], dist[u] + w(u,v))`; tightening tentative distances toward true distances.
- **Shortest-path tree (SPT)** — predecessor/parent structure rooted at the source encoding all shortest paths from it.
- **Triangle inequality / feasibility of potentials** — `dist[v] ≤ dist[u] + w(u,v)`; certifies correctness and underlies reweighting.
- **Negative cycles** — render shortest path undefined (−∞) for reachable vertices; must be detected, not just tolerated.
- **Walks vs. simple paths** — shortest *walks* are polynomial; shortest *simple* paths with negatives are NP-hard (longest path special case).
- **Path reconstruction** — store predecessor pointers (or parent-edge) to recover the actual route, not just its cost.

### Unweighted & Small-Weight Graphs

- **BFS (breadth-first search)** — layer-by-layer expansion gives shortest paths in edge count on unweighted graphs (O(V+E)).
- **Multi-source BFS** — seed the queue with all sources at distance 0 to get nearest-source distances in one pass (O(V+E)).
- **0-1 BFS (deque BFS)** — edges of weight 0/1; push 0-edges to front, 1-edges to back of a double-ended queue (O(V+E)).
- **Dial's algorithm (bucket queue)** — Dijkstra with buckets indexed by distance for small integer weights, max weight C (O(E + V·C)).
- **k-weight BFS generalization** — for edges weighted 0..k, expand each edge into a chain or use a layered/bucketed queue (O(k·(V+E))).
- **Lexicographically smallest shortest path** — among equal-length BFS paths, choose smallest sequence via careful tie-breaking or BFS on labeled states.
- **BFS on implicit / grid graphs** — treat cells as nodes, moves as edges; foundation for maze and tiling shortest-path problems (O(cells)).
- **Word-ladder / state BFS** — model configurations as vertices and transitions as unit edges; shortest transformation via BFS (O(states·branching)).

### Dijkstra's Algorithm (Non-Negative Weights)

- **Core greedy invariant** — repeatedly finalize the unsettled vertex of minimum tentative distance; correct only when weights ≥ 0.
- **Binary-heap Dijkstra** — priority queue of (dist, vertex); the standard competitive implementation (O((V+E) log V)).
- **Lazy-deletion Dijkstra** — push duplicates on relaxation, skip stale popped entries via a finalized check; simplest fast variant (O((V+E) log V)).
- **Decrease-key Dijkstra (indexed heap)** — update keys in place using positions in an indexed binary heap; avoids stale entries (O((V+E) log V)).
- **Balanced-BST / ordered-set Dijkstra** — store (dist, vertex) in a sorted set and erase-then-reinsert on relaxation (O((V+E) log V)).
- **Array (dense) Dijkstra** — linear scan for the minimum each step; optimal for dense graphs (O(V²)).
- **Fibonacci-heap Dijkstra** — amortized O(1) decrease-key yields the classic theoretical optimum (O(E + V log V)).
- **Pairing-heap / d-ary-heap Dijkstra** — practical decrease-key heaps; d-ary tuned to E/V improves constant factors (O(E·log_d V + V·d·log_d V)).
- **Tie-breaking & path selection** — secondary keys (path length, lexicographic) to make the chosen shortest path unique.
- **Early termination** — stop once the target is finalized for single-pair queries; large practical speedup.
- **Lazy vs. eager trade-off** — lazy uses more memory but simpler code; eager (decrease-key) bounds heap size by V.
- **Negative-weight pitfall** — a finalized vertex may be improved later, breaking the greedy invariant; never use plain Dijkstra with negatives.

### Negative Weights & Cycle Detection

- **Bellman-Ford algorithm** — relax all edges V−1 times; handles negative edges and detects negative cycles (O(V·E)).
- **Negative-cycle detection** — a V-th relaxation pass that still improves a distance proves a reachable negative cycle.
- **Negative-cycle extraction** — track predecessors and walk back from an improvable vertex V steps to land inside the cycle, then trace it.
- **Early-stopping Bellman-Ford** — terminate when a full pass makes no relaxation; often far faster than the worst case.
- **Yen's Bellman-Ford optimization** — split edges into forward/backward sets by vertex order; halves passes via alternating sweep directions.
- **SPFA (Shortest Path Faster Algorithm)** — queue-based Bellman-Ford relaxing only vertices whose distance changed (avg fast, worst O(V·E)).
- **SPFA with SLF (Smallest Label First)** — insert at front if smaller than queue head, else back; speeds convergence heuristically.
- **SPFA with LLL (Large Label Last)** — move above-average-distance vertices to the queue tail to defer poor candidates.
- **SPFA negative-cycle detection** — flag a vertex relaxed ≥ V times (or whose relax-path length reaches V) to report a negative cycle.
- **DEsopo-Pape algorithm** — deque-based label-correcting method: new vertices to back, re-activated vertices to front (fast in practice, exponential worst case).
- **Label-setting vs. label-correcting** — Dijkstra sets labels permanently; Bellman-Ford/SPFA/Pape correct labels until stable (taxonomy of all SSSP).
- **Finding any negative cycle in a graph** — add a virtual source with 0-edges to every vertex, then run Bellman-Ford detection (O(V·E)).

### DAG Shortest & Longest Paths

- **Topological-order relaxation** — process vertices in topological order, relaxing out-edges once each; linear and negative-weight-safe (O(V+E)).
- **DAG longest path** — negate weights or maximize during the topological sweep; the tractable cousin of NP-hard general longest path (O(V+E)).
- **Critical-path method (CPM)** — longest path in an activity DAG giving project completion time and slack (see Topological Sorting / DP) (O(V+E)).
- **Counting paths in a DAG** — DP over topological order accumulating path counts; pairs naturally with shortest-path counting (O(V+E)).
- **Shortest path with fixed edge count in a DAG** — layered DP indexed by (vertex, edges used) along topological order.

### All-Pairs Shortest Paths

- **Floyd-Warshall algorithm** — DP over intermediate-vertex sets; relax `d[i][j]` through each k (O(V³), O(V²) space).
- **Floyd-Warshall path reconstruction** — store a `next[i][j]` (or `parent`) matrix updated on improvement to recover routes (O(V²) space).
- **Floyd-Warshall negative-cycle detection** — a negative `d[i][i]` after the run signals a negative cycle through i.
- **Transitive closure (Warshall)** — boolean version computing reachability; speed up with bitsets (O(V³), O(V³/word) with bitset).
- **Min-max / widest-path Floyd-Warshall** — replace (+, min) with (max, min) or (min, max) for bottleneck All-Pairs (O(V³)).
- **Min-plus (tropical) matrix multiplication** — APSP as repeated (min,+) matrix products; basis for exactly-k-edge and bounded-hop paths.
- **APSP via repeated squaring** — log V min-plus matrix powers give shortest paths using ≤ a power-of-two edges (O(V³ log V)).
- **Exactly-k-edges shortest path** — k-th min-plus matrix power gives shortest walks using exactly k edges (O(V³ log k) with binary exponentiation).
- **Johnson's algorithm** — reweight via Bellman-Ford potentials to non-negative, then run Dijkstra from each vertex (O(V·E + V·E log V)).
- **Reweighting with potentials (h-function)** — set `w'(u,v) = w + h(u) − h(v)` from a feasible potential h; preserves shortest paths, removes negatives.
- **Repeated Dijkstra (non-negative APSP)** — simply run Dijkstra from every vertex when no negatives exist (O(V·E log V)).
- **Seidel's algorithm** — APSP on unweighted undirected graphs via fast matrix multiplication (O(V^ω log V)).
- **Fredman / Williams sub-cubic APSP** — theoretical improvements shaving log factors via word tricks (O(V³ / log^c V)).

### A* and Heuristic / Goal-Directed Search

- **A* search** — Dijkstra ordered by `g(n) + h(n)`; with a good heuristic explores far fewer nodes toward a goal (O(E) best case).
- **Admissible heuristic** — never overestimates true remaining cost; guarantees A* optimality.
- **Consistent (monotone) heuristic** — `h(u) ≤ w(u,v) + h(v)`; ensures each node is expanded once, like Dijkstra with reduced costs.
- **Heuristic = potential equivalence** — A* is Dijkstra on reweighted graph `w + h(v) − h(u)`; ties the heuristic to Johnson-style potentials.
- **Weighted / bounded-suboptimal A*** — inflate h by ε for speed at a bounded factor on solution cost (faster, ε-optimal).
- **Greedy best-first search** — order by h(n) alone; fast but non-optimal, a baseline contrast to A*.
- **ALT (A*, Landmarks, Triangle inequality)** — precompute landmark distances to build strong admissible heuristics for road-graph queries.
- **IDA* (iterative-deepening A*)** — DFS with increasing f-cost bound; memory-light A* for huge implicit state graphs.
- **Manhattan / Euclidean / Chebyshev / octile heuristics** — standard admissible distance estimates on grids and geometric graphs.
- **Pattern-database heuristics** — precomputed exact costs of relaxed subproblems used as strong admissible h (puzzle solving).

### Bidirectional & Goal-Directed Speedups

- **Bidirectional BFS** — search forward from s and backward from t; meet in the middle to roughly halve explored frontier (O(b^{d/2}) implicit).
- **Bidirectional Dijkstra** — simultaneous forward/reverse Dijkstra with a correct meeting-and-stopping condition on settled vertices.
- **Bidirectional A*** — both directions heuristic-guided; needs consistent paired heuristics to remain correct.
- **Meeting-condition correctness** — stop when the sum of frontier minima exceeds the best meeting path found; common implementation pitfall.
- **Contraction Hierarchies (CH)** — preprocess by contracting vertices with shortcut edges by importance; very fast s-t queries (near-O(log V) query after heavy preprocessing).
- **Highway / Transit-Node Routing** — precompute access nodes and a distance table for constant-time long-range road queries.
- **Arc flags** — partition graph into regions and tag edges that lie on some shortest path into each region to prune search.
- **Hub labeling (2-hop labels)** — store per-vertex label sets so any distance is a single label intersection (fast query, large precomputation).

### Bottleneck, Widest & Reliability Paths

- **Widest path / maximum-capacity path** — maximize the minimum edge weight on a path; Dijkstra with (max, min) relaxation (O(E log V)).
- **Minimax / bottleneck path** — minimize the maximum edge along a path; same structure with min/max swapped (O(E log V)).
- **MST-based bottleneck answers** — the minimax path between two nodes lies on any minimum spanning tree; query via tree path / LCA (O(log V) per query after MST).
- **Maximum bottleneck via Kruskal + DSU / Kruskal Reconstruction Tree** — answer all-pairs bottleneck queries with a reconstruction tree (O(α) per merge, O(log V) queries).
- **Most-reliable path** — maximize product of edge survival probabilities; take −log to convert into an additive shortest path (O(E log V)).
- **Bottleneck Floyd-Warshall (all-pairs widest)** — O(V³) all-pairs minimax/maximin via the min-max recurrence.

### Shortest Paths with State / Layered & Constrained

- **State-expanded (product) graph** — augment vertices with extra state (parity, fuel, keys, mod-k) and run Dijkstra/BFS on the expansion.
- **Layered graph shortest path** — replicate the graph across layers for budgeted resources (e.g., k free edges, k discounts) and connect layers by special edges.
- **k-edge-discount / free-edges variant** — minimize cost when up to k edges can be skipped/halved; state = (vertex, discounts used).
- **Time-dependent / temporal shortest path** — edge availability/weight depends on arrival time; modified relaxation respecting time-validity.
- **Parity / modular-constraint paths** — find shortest path of even/odd length or length ≡ r (mod m) via state = (vertex, residue).
- **Color / label-constrained paths** — restrict allowed edge labels (e.g., regular-language-constrained) via product with an automaton.
- **Resource-constrained shortest path (RCSP)** — shortest path subject to a side budget (weight/time); NP-hard, solved by labeling + dominance pruning.
- **Multi-criteria / Pareto shortest paths** — maintain Pareto-optimal label sets when optimizing several costs simultaneously (exponential worst case).
- **Shortest path visiting required vertices/states** — bitmask DP over a small required-set combined with pairwise shortest paths (O(2^k · k · ...)) (see Bitmask DP / TSP).
- **Vertex/edge-cost combined** — split each vertex into in/out nodes joined by a vertex-cost edge to fold node weights into edge weights.

### Cycle-Optimization Problems

- **Minimum mean cycle (Karp's algorithm)** — DP over walks of exactly k edges; minimize `max_k (d_n(v) − d_k(v)) / (n − k)` (O(V·E)).
- **Minimum cost-to-time ratio cycle** — binary search on ratio λ, testing for a negative cycle in reweighted graph `cost − λ·time`.
- **Minimum/maximum mean cycle via parametric search** — Lawler/Howard policy-iteration style refinements of Karp's bound.
- **Tightest negative cycle / most-negative cycle** — combine cycle detection with mean-cycle minimization to characterize negativity.
- **Shortest cycle through a vertex** — for each neighbor, run SSSP and combine (or BFS for unweighted girth) (O(V·E) / O(V·(V+E))).
- **Girth (shortest cycle) of a graph** — run BFS from every vertex on unweighted graphs (O(V·E)); see also weighted Dijkstra variants.

### k-Shortest Paths & Alternatives

- **Second shortest path (distinct)** — track best and second-best arrival distance per vertex during a Dijkstra-like relaxation (O((V+E) log V)).
- **Strictly shorter vs. allowing equal length** — careful handling of equal-length alternatives when defining "second" shortest.
- **k-shortest paths allowing loops (Eppstein's algorithm)** — implicit sidetrack representation via a heap of detours from the SPT (O(E + V log V + k)).
- **Lazy Eppstein** — build the sidetrack/path heap on demand, materializing only the k paths actually requested.
- **k-shortest simple (loopless) paths (Yen's algorithm)** — repeatedly compute spur paths forbidding prior prefixes; uses a candidate heap (O(k·V·(E + V log V))).
- **Lawler's improvement to Yen** — reduce redundant spur recomputation by branching only at necessary deviation nodes.
- **Hoffman-Pavley deviation paths** — classic deviation-based enumeration predating Eppstein's compact form.
- **k-shortest walks via min-plus / BFS counting** — enumerate shortest walks (not simple) using A*-ordered expansion with per-node visit caps (O(k) pops past SSSP).
- **k-best with A* expansion** — repeatedly pop from an A*/Dijkstra frontier allowing up to k expansions per node for top-k routes.

### Shortest-Path Counting & Enumeration

- **Counting shortest paths** — alongside Dijkstra/BFS, accumulate counts: equal-distance relaxations add, strictly-shorter ones reset (O((V+E) log V)).
- **Counting under a modulus** — maintain path counts mod p to avoid overflow in competitive settings.
- **Shortest-path DAG (SP-DAG)** — the subgraph of edges (u,v) with `dist[u] + w = dist[v]`; supports counting, enumeration, and disjoint-path arguments.
- **Number of shortest paths through an edge/vertex** — multiply source-side and destination-side counts on the SP-DAG.
- **Enumerating all shortest paths** — DFS/backtracking over the SP-DAG (output-sensitive; exponentially many possible).
- **Edge/vertex on some shortest path** — test membership via forward and reverse distance labels summing to the s-t distance.
- **Edges on every shortest path (necessary edges / SP bridges)** — identify cut-like edges within the SP-DAG (see Bridges / Articulation in Graph domain).

### Distances, Reachability & Structure Queries

- **Tree shortest paths** — unique path between nodes; distance via `depth[u]+depth[v]−2·depth[lca]` (O(log V) per query) (see LCA / Trees).
- **Graph diameter / eccentricity (weighted)** — derive from APSP or multi-source SSSP; longest shortest path in the graph.
- **Tree diameter** — two BFS/DFS sweeps (unweighted) or DP on the tree (weighted) (O(V)) (see Trees).
- **Reachability / connectivity preprocessing** — BFS/DFS, components, transitive closure as a prelude to shortest-path queries.
- **Dynamic / incremental shortest paths** — maintain distances under edge insertions/deletions (decremental APSP, dynamic Dijkstra) (advanced) (see Dynamic Graph Algorithms).
- **Distance oracles (approximate)** — Thorup-Zwick stretch-(2k−1) oracles with subquadratic space and O(k) query (advanced).

### Reductions, Modeling & Connections

- **Difference constraints → shortest paths** — system `x_v − x_u ≤ w` solved as Bellman-Ford SSSP; infeasibility ⇔ negative cycle (O(V·E)).
- **Shortest path as LP / dual potentials** — distances are an LP whose dual gives feasible potentials (reweighting justification).
- **Min-cost flow uses SSSP** — successive-shortest-path / SPFA-based augmentation with Johnson potentials (see Network Flow).
- **0/1 weights → BFS, integer weights → Dial** — choosing the cheapest correct algorithm from the weight structure.
- **Graph transformations** — node-splitting for vertex costs, edge-subdivision for unit-weight reductions, reverse graph for to-sink distances.
- **Eulerian/Chinese Postman connection** — shortest paths between odd-degree vertices to compute route-inspection augmentation (see Graph Theory).
- **Metric / geometric shortest paths** — visibility graphs and continuous Dijkstra for paths amid polygonal obstacles (see Computational Geometry).
- **Word-RAM & integer-priority-queue speedups** — Thorup's O(E) undirected integer-weight SSSP and related theoretical optima (advanced).

<sub>[↑ Back to top](#table-of-contents)</sub>

---

## 17. Minimum Spanning Trees, Connectivity & DSU

### Spanning Tree Fundamentals

- **Spanning tree** — connected acyclic subgraph touching all V vertices using exactly V−1 edges; exists iff graph connected.
- **Minimum spanning tree (MST)** — spanning tree of minimum total edge weight; subject of the core greedy algorithms.
- **Cut property** — for any cut, the unique lightest crossing edge is in every MST; foundational correctness lemma for all greedy MST algorithms.
- **Cycle property** — the strictly heaviest edge on any cycle is in no MST; complementary lemma used to discard edges.
- **MST uniqueness** — MST is unique iff every edge weight is distinct, or more weakly iff each cut/cycle has a unique extremal edge; perturb ties to force uniqueness.
- **MST vs minimum spanning forest** — on disconnected graphs the greedy algorithms produce a minimum forest, one tree per component.
- **Maximum spanning tree** — negate weights (or reverse comparator) and run any MST algorithm unchanged.
- **Matroid / greedy view** — graphic matroid; MST is the min-weight basis, explaining why pure greedy is optimal (foundation for matroid intersection problems).
- **All MSTs share the multiset of edge weights** — every MST of a graph uses the same sorted weight sequence; useful invariant for counting and verification.

### Core MST Construction Algorithms

#### Kruskal's Algorithm

- **Kruskal's algorithm** — sort edges ascending, add each edge whose endpoints lie in different components, using DSU to detect cycles (O(E log E) sort-dominated; O(E α) after sort).
- **Edge-sort vs radix/counting sort** — with small integer weights, replace comparison sort by counting/radix sort to reach near-linear O(E α) construction.
- **Kruskal on dense graphs** — generally inferior to Prim's O(V²) when E ≈ V² because of the sort cost.
- **Partial / incremental Kruskal** — process edges in weight order to answer "components after adding all edges ≤ w" queries; basis of offline connectivity-threshold problems.
- **Kruskal reconstruction (Kruskal tree / merge tree)** — build a binary tree where each internal node is a merge at a given weight; root-to-leaf max edge equals minimax path weight (see bottleneck section).

#### Prim's Algorithm

- **Prim's algorithm (binary heap)** — grow tree from a seed, repeatedly extracting the lightest edge leaving the current tree via a priority queue (O(E log V)).
- **Prim with adjacency matrix (O(V²))** — best for dense graphs; maintain a best-edge array, pick the min unvisited each step without a heap.
- **Prim with Fibonacci heap** — decrease-key in amortized O(1) gives the theoretical O(E + V log V) bound; rarely beats binary heap in practice.
- **Lazy vs eager Prim** — lazy pushes possibly-stale edges and skips them on pop; eager uses decrease-key keeping one entry per vertex.

#### Borůvka's Algorithm

- **Borůvka's algorithm** — each round, every component selects its cheapest outgoing edge and contracts; halves component count per round (O(E log V)).
- **Borůvka parallelism** — rounds are embarrassingly parallel over components; the natural choice for distributed/parallel and GPU MST.
- **Tie-breaking in Borůvka** — must break weight ties consistently (e.g., by edge index) to avoid contracting a cycle of equal edges.
- **Borůvka + Prim/Kruskal hybrids** — run a few Borůvka rounds to shrink the graph, then finish with Prim or Kruskal; underlies linear-time randomized MST.

#### Advanced & Theoretical MST

- **Karger–Klein–Tarjan randomized MST** — expected linear-time O(E) MST via Borůvka steps plus random sampling and an MST-verification oracle.
- **MST verification (linear time)** — given a candidate tree, verify minimality in O(E) (Komlós / King) by answering tree-path max-edge queries; also certifies a claimed MST.
- **Chazelle's deterministic MST** — O(E α(E,V)) using soft heaps; near-linear deterministic, mostly of theoretical interest.
- **Fredman–Tarjan algorithm** — O(E β(E,V)) via Prim with Fibonacci heaps run in phases over a growing heap-size bound.
- **Soft heap** — approximate priority queue with bounded "corruption"; key primitive in linear-expected and near-linear MST algorithms.

### Disjoint Set Union (Union–Find)

#### Core Structure & Optimizations

- **DSU / union-find** — maintains a partition into disjoint sets supporting find-representative and union; backbone of Kruskal and connectivity.
- **Path compression** — point each traversed node directly at the root during find, flattening trees.
- **Union by rank** — attach the shallower tree under the deeper; rank is an upper bound on height.
- **Union by size** — attach the smaller set under the larger; equivalent guarantee, and exposes set sizes directly.
- **Combined complexity** — path compression plus union-by-rank/size gives O(α(n)) amortized per operation, α the inverse Ackermann (effectively ≤ 5).
- **Path splitting / path halving** — one-pass find variants making every node point to its grandparent; same asymptotics, simpler/iterative.
- **Lower bound** — Ω(α(n)) amortized is provably tight for pointer-machine union-find (Tarjan / Fredman–Saks cell-probe).

#### DSU Variants

- **Weighted / potential DSU (DSU with offsets)** — store an offset to the parent so find returns relative value; solves constraints like x−y = c and parity classes.
- **DSU over a group / vector space (XOR-DSU, modular)** — generalize offsets to a group element (e.g., XOR for bipartite/parity, Z_k); detects contradictions.
- **DSU with rollback (undo)** — union by rank/size WITHOUT path compression, push changes to a stack so any union can be undone in O(log n) per op; enables offline divide-and-conquer.
- **Persistent DSU** — back DSU arrays with a persistent array so historical versions are queryable; supports offline/online historical connectivity.
- **Partially persistent DSU** — record union timestamps so "were u,v connected at time t" is answerable, often by binary search on the merge time.
- **DSU with parity / bipartiteness check** — augment with edge-parity to test whether adding an edge keeps the graph bipartite (online).
- **DSU on intervals ("paint"/next-pointer DSU)** — union-find over positions where find jumps to the next unused index; assigns intervals or processes each cell once (near-linear total).
- **Small-to-large merging without DSU** — explicitly merge smaller container into larger; O(n log n) total, a manual cousin of union-by-size.
- **Link/condensation via DSU** — contract SCCs, bridges, or 2ECC into super-nodes by unioning, building quotient structures.

#### DSU Applications

- **Cycle detection in undirected graphs** — an edge whose endpoints already share a set closes a cycle; core of Kruskal.
- **Offline connectivity queries** — sort/union edges then answer same-component queries in any order.
- **Number of connected components / sizes** — maintain a component counter and per-root size as unions happen.
- **Connected components on a grid / flood merge** — union 4- or 8-adjacent same-label cells; classic image-labeling.
- **Kruskal-style "minimum cost to connect"** — merge requirements greedily until everything connected; counts groups, leftover cost.
- **Equivalence-relation maintenance** — merge equality constraints, then check or contradict inequality/parity constraints.
- **Offline LCA (Tarjan)** — DFS with DSU answers all LCA queries in O((V+Q) α) by union of subtrees to their parent on exit.
- **Retroactive/offline "remove smallest edges" problems** — process operations in reverse so deletions become unions.
- **Counting reachable / merging by attribute** — aggregate per-component statistics (sum, max, count) under merges.

### MST Variants & Extensions

- **Second-best MST** — for each non-tree edge, replacing the max-weight edge on its tree path gives a candidate; take the best swap (O(E log E); tree max-edge via LCA binary lifting / RMQ).
- **k-th best spanning tree** — enumerate spanning trees in increasing weight via a partition-and-best-swap branching scheme (Gabow); O(k) MST-like steps.
- **Minimum bottleneck spanning tree (MBST)** — minimizes the maximum edge; every MST is an MBST (converse false). Solvable by MST or by Camerini's median-edge approach (O(E)).
- **Bottleneck / minimax path** — minimum possible maximum edge between two nodes equals their tree-path max in any MST/MBST; answer via LCA or Kruskal reconstruction tree.
- **Kruskal reconstruction tree (KRT)** — binary tree from Kruskal merges; LCA's node weight = bottleneck between two leaves; turns minimax-path queries into LCA queries.
- **Maximum bottleneck (widest path) spanning tree** — maximum spanning tree maximizes the min edge on any path (max-capacity paths).
- **Degree-constrained MST** — MST with bounded vertex degrees; NP-hard in general (see NP-hard / approximation domain).
- **Minimum-degree / minimum-leaf spanning trees** — optimize tree shape rather than weight; mostly NP-hard variants (see NP domain).
- **MST with one mandatory or forbidden edge** — force an edge by contracting it, or forbid by deletion, then recompute / swap.
- **MST sensitivity analysis** — how much each tree edge can increase, or each non-tree edge can decrease, before the MST changes (linear-time via verification machinery).
- **Counting spanning trees** — Kirchhoff's Matrix-Tree theorem; count via Laplacian minor determinant (see linear algebra / counting domain).
- **Counting minimum spanning trees** — group equal-weight edges per Borůvka phase and multiply Matrix-Tree counts on contracted multigraphs.
- **Steiner tree (minimum)** — connect a required terminal subset, possibly using extra Steiner points; NP-hard, FPT in #terminals via Dreyfus–Wagner (see NP-hard domain).
- **Euclidean / geometric MST** — MST of planar points; build on the Delaunay triangulation then MST (O(n log n)) since EMST ⊆ Delaunay.
- **Manhattan MST** — MST under L1 distance; for each point keep nearest neighbor in each of 8 (effectively 4 by symmetry) octants, giving O(n) candidate edges via sweep + BIT, then MST (O(n log n)).
- **Chebyshev / L∞ MST** — reduce L∞ to L1 by the 45° coordinate rotation (x±y), then apply Manhattan MST.
- **Minimum spanning tree in the plane with obstacles** — build a visibility/Delaunay-like graph then MST (geometry-heavy; see computational geometry domain).
- **Online / incremental MST** — maintain MST under edge insertions by detecting the heaviest edge on the tree cycle and swapping (link-cut tree, O(log n) per insertion).
- **Fully dynamic MST** — support insertions and deletions; Holm–de Lichtenberg–Thorup achieves O(log⁴ n) amortized per update.

### Directed Spanning Structures (Arborescences)

- **Minimum spanning arborescence** — minimum-weight directed spanning tree rooted at r with all edges reachable from r (the directed analog of MST).
- **Chu–Liu/Edmonds algorithm** — repeatedly pick each node's cheapest incoming edge, contract any resulting cycle adjusting weights, recurse (O(VE) naive).
- **Tarjan / Gabow–Galil–Spencer fast arborescence** — O(E + V log V) via mergeable heaps and efficient cycle contraction.
- **Reconstructing the arborescence** — after contractions, expand cycles back to drop exactly one in-edge per contracted cycle.
- **Optimum branching (no fixed root)** — add a virtual root with uniform-cost edges to choose the best actual root, or run rootless Edmonds.
- **Maximum arborescence** — negate weights; appears in dependency-parsing / scheduling.

### Strongly Connected Components (Directed)

- **Strongly connected component (SCC)** — maximal vertex set where every pair is mutually reachable; partitions any digraph.
- **Tarjan's SCC algorithm** — single DFS with discovery-time and low-link values plus an on-stack flag; pops an SCC when low-link == index (O(V+E)).
- **Kosaraju–Sharir algorithm** — DFS to get finish-order, then DFS on the transpose in decreasing finish order; each tree is one SCC (two passes, O(V+E)).
- **Gabow's SCC algorithm (path-based)** — single DFS using two stacks instead of low-link numbers; O(V+E), often simplest to reason about.
- **Condensation / SCC DAG** — contract each SCC to a node, yielding a directed acyclic graph; enables DAG DP, longest path, reachability.
- **Topological order of the condensation** — Tarjan emits SCCs in reverse topological order for free; useful for DP over the DAG.
- **Reachability via SCC + DAG** — collapse cycles then do reachability/DP on the DAG (bitset reachability, transitive closure).
- **Minimum edges to make a digraph strongly connected** — on the condensation count sources and sinks; answer max(sources, sinks) when ≥2 SCCs.
- **Semi-connectivity / mutual reachability** — derived from whether the condensation is a Hamiltonian-path-like chain.
- **Functional-graph components** — each node has out-degree 1; components are "rho"/cycle-plus-trees, found by following successors (specialized SCC).

### 2-SAT

- **2-SAT** — satisfiability where each clause has ≤2 literals; reduce to implication digraph and solve with SCC.
- **Implication graph** — clause (a ∨ b) yields edges ¬a→b and ¬b→a; 2n vertices for variable/negation literals.
- **Satisfiability test** — UNSAT iff some variable x and ¬x share an SCC (O(V+E)).
- **Assignment extraction** — set each variable true if its literal's SCC comes later in reverse topological order of the condensation.
- **At-most-one / exactly-one constraints** — encode efficiently with prefix-implication chains to keep the clause count O(n) instead of O(n²).
- **2-SAT modeling tricks** — encode forced values, mutual exclusion, and "if-then" constraints; common in feasibility/geometry-placement problems.

### Undirected Connectivity: Bridges, Cut Vertices, Biconnectivity

#### Bridges & Articulation Points

- **Bridge (cut edge)** — edge whose removal disconnects the graph; found via DFS low-link when low[child] > disc[parent] (O(V+E)).
- **Articulation point (cut vertex)** — vertex whose removal increases component count; root special-cased by child count, others by low[child] ≥ disc[u] (O(V+E)).
- **DFS low-link / tree-edge vs back-edge framework** — discovery times plus low-values underpin bridges, cut vertices, and SCCs alike.
- **Handling multi-edges** — a parallel edge means neither copy is a bridge; track edge identity, not just the parent vertex.
- **Chain decomposition (Schmidt)** — decompose into ear-like chains in one DFS to read off all bridges and cut vertices simultaneously (O(V+E)).

#### Biconnected & Bridge Components

- **Biconnected component (block)** — maximal subgraph with no articulation point (any two edges lie on a common simple cycle); found via an edge stack during DFS (O(V+E)).
- **2-vertex-connectivity** — graph stays connected after removing any single vertex; equivalent to having one block and no cut vertex.
- **2-edge-connected component (2ECC)** — maximal subgraph with no bridge; contract bridges to obtain them.
- **2-edge-connectivity** — graph stays connected after removing any single edge; equivalent to being bridgeless.
- **Block-cut tree (BC tree)** — bipartite tree of blocks and cut vertices; cut vertices link the blocks containing them (O(V+E) to build).
- **Bridge tree (2ECC tree / "bridge forest")** — contract each 2ECC to a node; remaining edges (all bridges) form a tree/forest enabling bridge-path queries.
- **Round trip / ear decomposition** — a graph is 2-edge-connected iff it has an ear decomposition, and 2-vertex-connected iff an open ear decomposition (Whitney).
- **st-numbering** — order vertices so s is first, t last, every other vertex has both a smaller and larger neighbor; exists iff 2-connected (planarity/embedding tool).
- **Triconnectivity & SPQR trees** — decompose a 2-connected graph into 3-connected components; SPQR tree is the standard structure (used in planarity, graph drawing).
- **3-edge-connected components** — found by extending low-link / chain ideas; rarer in contests.

### Higher & General Connectivity

- **Vertex connectivity κ(G)** — minimum vertices to remove to disconnect; computable via max-flow over all source/sink pairs (see flows domain).
- **Edge connectivity λ(G)** — minimum edges to remove to disconnect; equals global min cut.
- **Global minimum cut (Stoer–Wagner)** — undirected min cut without flow via maximum-adjacency orderings (O(V³) or O(VE + V² log V)) (see cuts/flows domain).
- **Karger / Karger–Stein randomized min cut** — repeated random edge contraction; O(V²) per trial, high-probability global min cut (see randomized domain).
- **Gomory–Hu tree** — encodes all-pairs min cuts of an undirected graph in a single tree of V−1 cuts (see flows domain).
- **Menger's theorem** — max number of edge/vertex-disjoint s–t paths equals the min edge/vertex cut; links connectivity to flow.
- **k-connectivity testing** — verify κ(G) ≥ k or λ(G) ≥ k via bounded flow computations.
- **Cactus representation of min cuts** — all global min cuts of a graph encoded compactly in a cactus structure.

### Dynamic & Offline Connectivity

- **Incremental connectivity** — edge insertions only; pure DSU answers connectivity in O(α) amortized.
- **Decremental connectivity** — deletions only; reverse the timeline so deletions become DSU insertions (offline), or use specialized structures online.
- **Offline dynamic connectivity (segment tree on time / "DSU on segtree")** — place each edge on the time intervals it is alive, recurse over a segment tree of the timeline applying rollback-DSU unions, answer queries at leaves (O((n+q) log n · α) with rollback).
- **Divide-and-conquer over queries with rollback DSU** — general technique: each edge contributes to a contiguous query range; DFS the range tree adding/undoing unions.
- **Link-cut tree (LCT)** — represents a forest of rooted trees with O(log n) link/cut/path-aggregate; supports online dynamic-tree connectivity and MST maintenance.
- **Euler tour tree (ETT)** — maintains a tree as a balanced BST over its Euler tour; supports link/cut and subtree aggregates in O(log n); building block of fully dynamic connectivity.
- **Holm–de Lichtenberg–Thorup (HDT) fully dynamic connectivity** — edge insert/delete and connectivity queries in O(log² n) amortized using a hierarchy of spanning forests (ETTs) and edge levels.
- **Thorup's improvement** — fully dynamic connectivity in O(log n (log log n)²) expected amortized; mostly theoretical.
- **Dynamic 2-edge / biconnectivity (Westbrook–Tarjan, HDT)** — maintain bridges/biconnected info under insertions or fully dynamic updates (insertions in O(m α(m,n)); harder fully dynamic bounds).
- **Subgraph connectivity / vertex updates** — toggling vertices on/off (emergency-planning style) reduced via flattening to edge-dynamic structures.
- **Online bridge finding (incremental 2ECC)** — maintain the bridge tree under edge insertions by contracting cycles with DSU; O(α) amortized per edge.
- **Dynamic MST under updates** — combine LCT path-max queries with edge insert/delete; fully dynamic MST in polylog per update.

### Tree-Connectivity & Auxiliary Techniques

- **Euler tour technique for connectivity/subtree** — flatten a tree to an array so subtree = contiguous range and connectivity queries become range/BIT operations.
- **DSU on tree (small-to-large on subtrees)** — answer subtree aggregate queries offline by reusing the heavy child's data and merging light children (O(n log n)) (see tree-algorithms domain).
- **Auxiliary / virtual tree** — compress a tree to only k marked nodes plus their pairwise LCAs for per-query connectivity DP (O(k log k)) (see tree-algorithms domain).
- **Heavy-light / centroid decomposition for path-connectivity queries** — decompose paths/trees for connectivity-flavored aggregate queries (see tree-algorithms domain).
- **Component DP on the block-cut / bridge tree** — run tree DP after contracting biconnected or 2-edge-connected components to a tree.
- **Cut-and-count / connectivity DP on tree decompositions** — handle connectivity constraints in treewidth DP via cut-and-count or rank-based methods (see treewidth / FPT domain).
- **Spanning-tree + non-tree-edge "fundamental cycle" decomposition** — every non-tree edge defines one fundamental cycle; basis for cycle space and many connectivity arguments.
- **Cycle space / cut space (GF(2))** — fundamental cycles and cuts form dual vector spaces over GF(2); tool for parity-of-cycles and XOR-on-paths problems (see linear algebra domain).

<sub>[↑ Back to top](#table-of-contents)</sub>

---

## 18. Network Flow, Cuts & Matching

### Flow Networks: Foundations & Theory

- **Flow network definition** — directed graph with edge capacities, designated source s and sink t; a feasible flow obeys capacity and conservation constraints.
- **Capacity constraint** — flow on each edge satisfies 0 ≤ f(e) ≤ c(e); never exceeds the edge's capacity.
- **Conservation (flow balance)** — at every non-terminal vertex total inflow equals total outflow; net flow accumulates only at s and t.
- **Value of a flow** — net flow out of the source (equivalently net flow into the sink); the quantity max-flow maximizes.
- **Residual graph / residual capacity** — auxiliary network where each edge e has residual c(e)−f(e) and each reverse edge carries f(e), enabling flow to be "pushed back."
- **Augmenting path** — an s–t path in the residual graph with positive bottleneck capacity; augmenting along it strictly increases flow value.
- **Bottleneck capacity** — minimum residual capacity along an augmenting path; the amount of flow that can be pushed in one augmentation.
- **Antiparallel edges & multigraph handling** — split an edge or add a relay vertex so reverse-edge bookkeeping stays consistent; common implementation pitfall.
- **Integrality theorem** — if all capacities are integers, a maximum flow exists in which every edge flow is integral (basis for combinatorial modeling).
- **s–t cut** — partition (S,T) with s∈S, t∈T; its capacity is the sum of capacities of edges crossing from S to T (reverse edges ignored).
- **Net flow across a cut** — equals the flow value for any s–t cut; bounds the flow by the cut capacity (weak duality).

### Maximum Flow Algorithms

#### Augmenting-Path (Ford–Fulkerson family)
- **Ford–Fulkerson method** — repeatedly find any augmenting path and augment; terminates for integer capacities, may loop forever on irrationals (O(E·max_flow)).
- **Edmonds–Karp** — Ford–Fulkerson choosing the shortest augmenting path via BFS; polynomial and capacity-independent (O(V·E²)).
- **Capacity scaling (fattest path scaling)** — only augment along paths with bottleneck ≥ Δ, halving Δ each phase; reduces augmentations (O(E²·log C)).
- **Fattest/widest augmenting path** — pick the maximum-bottleneck path (max-spanning-tree / Dijkstra-style); few augmentations (O(E·log V · log(V·U))).

#### Blocking-Flow / Layered-Network Methods
- **Dinic's (Dinitz) algorithm** — BFS builds a level graph, DFS finds a blocking flow per phase; O(V²·E) general, O(E·√V) on unit-capacity/bipartite, O(E·√E) on planar-like.
- **Level graph (layered network)** — BFS-distance layering of the residual graph; augmenting paths use only forward level edges.
- **Blocking flow** — a flow saturating at least one edge on every s–t path of the level graph; the core subroutine of Dinic/MPM.
- **Current-arc (dead-edge) pointer optimization** — per-vertex "next edge" index in Dinic's DFS skips exhausted arcs, giving the amortized blocking-flow bound.
- **Scaling Dinic** — combine capacity scaling with Dinic phases for better bounds on large capacities (O(V·E·log U)).
- **MPM (Malhotra–Pramodh-Kumar–Maheshwari)** — blocking flow via vertex "potentials," pushing through min-potential vertices; O(V³) regardless of E, good on dense graphs.
- **Dinic on unit-capacity networks** — special O(E·√V) bound; underlies fast bipartite matching and vertex-disjoint path counting.

#### Preflow / Push–Relabel Family
- **Preflow** — relaxes conservation to allow excess at vertices; the invariant maintained throughout push–relabel.
- **Height/label function** — vertex labels lower-bounding residual distance to t; admissible edges go strictly downhill by one.
- **Push operation** — move excess along an admissible edge up to its residual capacity (saturating or non-saturating push).
- **Relabel operation** — raise a vertex's height to one above its lowest admissible neighbor when it has excess but no admissible edge.
- **Generic push–relabel** — apply push/relabel in any order until no active vertex remains (O(V²·E)).
- **FIFO push–relabel** — process active vertices in queue order; discharge each fully (O(V³)).
- **Relabel-to-front** — maintain vertices in a list, move relabeled ones to front, discharge sequentially (O(V³)).
- **Highest-label selection** — always discharge the active vertex of greatest height; strong practical performance (O(V²·√E)).
- **Excess scaling** — bound non-saturating pushes by scaling excess thresholds (O(V·E + V²·log U)).
- **Gap heuristic** — if no vertex has height h, lift all vertices with height in (h,V) to V+1; large practical speedup.
- **Global relabeling heuristic** — periodic BFS from t to reset exact heights; key for real-world speed.
- **Discharge subroutine** — repeatedly push/relabel a single active vertex until its excess is zero or it gets relabeled.
- **Second phase / excess return** — after a max-preflow is found, convert it to a legal flow by routing residual excess back to s (decompose-and-return).

#### Other / Modern Max-Flow
- **ISAP (Improved Shortest Augmenting Path)** — augmenting-path method with vertex distance labels, retreat, and gap heuristic; very fast in practice (O(V²·E)).
- **Karzanov's algorithm** — original O(V²) blocking-flow approach via preflows; historical precursor to push–relabel.
- **King–Rao–Tarjan / Goldberg–Rao binary blocking flow** — length-function scaling giving O(E·min(V^{2/3},√E)·log(V²/E)·log U).
- **Orlin's algorithm** — strongly polynomial max-flow in O(V·E); best known combinatorial strongly-polynomial bound.
- **Electrical-flow / interior-point max-flow** — near-linear and almost-linear-time theoretical algorithms (Madry; Chen et al. O(E^{1+o(1)})); mostly of theoretical interest.

#### Dynamic / Incremental & Parametric Max-Flow
- **Incremental max-flow after capacity increase** — raising one edge's capacity keeps the old flow feasible; resume augmenting only the new residual slack (cheap re-optimization).
- **Decremental max-flow after capacity decrease** — if a saturated edge shrinks, cancel the now-infeasible excess along a residual path back to s/t, then re-augment from the repaired flow.
- **Warm-start / residual reuse** — keep the residual network between related queries and restart augmenting/push–relabel from it instead of recomputing from scratch.
- **Parametric (monotone) max-flow (Gallo–Grigoriadis–Tarjan)** — capacities monotone in a parameter λ; all breakpoints and nested min cuts found in one max-flow's time via a push–relabel sweep.
- **Sensitivity analysis of min cut** — how much an edge capacity can change before the min-cut identity or value changes; read off saturated/critical edges.

### Max-Flow Min-Cut Theorem & Cuts

- **Max-flow min-cut theorem** — the maximum s–t flow value equals the minimum s–t cut capacity (LP strong duality specialized).
- **Finding the min cut from a max flow** — after max flow, S = vertices reachable from s in the residual graph; cut edges go from S to its complement.
- **Recovering all min-cut edges** — an edge is in some min cut iff it is saturated and its endpoints lie in different strongly connected components of the residual graph.
- **Minimum-cardinality min cut** — among min cuts, minimize number of edges by perturbing capacities (add tiny ε to each).
- **Closest / farthest min cut** — S reachable from s gives the source-side (closest) cut; complement of vertices reaching t gives the sink-side (farthest) cut.
- **Counting / enumerating min cuts** — structure captured by a DAG of residual SCCs; min cuts correspond to closed sets (antichains) in that DAG.
- **Vertex (node) connectivity via flow** — min number of vertices to disconnect s,t via node-splitting + unit capacities (Menger's theorem, vertex form).
- **Edge connectivity via flow** — min number of edges to disconnect s,t equals max edge-disjoint paths (Menger's theorem, edge form).

### Minimum-Cost Flows

#### Concepts
- **Min-cost max-flow (MCMF)** — among all maximum flows, find one of minimum total cost (cost = Σ flow·unit-cost).
- **Min-cost flow of given value** — send exactly a target flow value at minimum cost; stop augmenting at the target.
- **Negative-cycle optimality condition** — a flow is min-cost iff its residual graph contains no negative-cost cycle.
- **Reduced costs & node potentials** — potentials π make reduced cost c(u,v)+π(u)−π(v) ≥ 0, enabling Dijkstra and giving a complementary-slackness certificate.
- **Complementary slackness for min-cost flow** — an arc carries flow only if its reduced cost is ≤0 (saturated only if ≥0); the dual feasibility certificate of optimality.
- **ε-optimality (approximate complementary slackness)** — relax the optimality conditions to within ε per arc; the foundation of cost-scaling methods.
- **Convex/piecewise-linear edge costs** — model by splitting an edge into parallel arcs of increasing unit cost (convex cost flow).

#### Algorithms
- **Cycle canceling (Klein)** — start from any max flow, repeatedly cancel negative residual cycles until none remain (O(V·E²·C·U) generic).
- **Minimum-mean-cycle canceling (Goldberg–Tarjan)** — cancel the most-negative-mean cycle each step; strongly polynomial (O(V²·E³·log V)).
- **Successive shortest paths (SSP) with Bellman–Ford/SPFA** — repeatedly augment along the cheapest s–t residual path; handles negative edges directly.
- **SSP with Johnson potentials + Dijkstra** — maintain potentials so each phase uses Dijkstra on nonnegative reduced costs (O(F·E·log V) or O(F·V²)).
- **Bellman–Ford initialization of potentials** — one initial Bellman–Ford pass (the only correct initializer when negative arcs exist) removes initial negative costs so all later phases use Dijkstra.
- **Hopcroft–Karp-style phased SSP for unit-capacity MCMF** — augment a maximal set of edge-disjoint shortest paths per phase (as in Hopcroft–Karp); O(√E) phases give assignment-quality bounds on unit-capacity cost flow.
- **Primal–dual / Busacker–Gowen** — augment along shortest paths and update potentials; classic SSP formulation.
- **Capacity/cost scaling for min-cost flow** — scale capacities or use ε-optimality to get O(E²·log V·log U) or strongly polynomial variants.
- **Out-of-kilter algorithm** — drive each arc toward complementary slackness ("in kilter") one at a time; historical primal–dual method.
- **Network simplex** — specialized simplex on a spanning-tree basis; excellent practical performance, pivots on entering/leaving arcs.
- **Cost-scaling push–relabel (Goldberg–Tarjan)** — ε-optimality with push/relabel and price refinement; strong practical MCMF (O(V²·E·log(V·C))).
- **Lemon/cost-scaling style simulation** — simulating cost flow by greedy potential updates to avoid explicit augmentations (advanced CP trick).

### Bipartite Matching

#### Unweighted Bipartite Matching
- **Bipartite graph & matching basics** — matching = edge set with no shared endpoints; maximum matching maximizes its size.
- **Augmenting path (alternating path) theorem (Berge)** — a matching is maximum iff it admits no augmenting path.
- **Kuhn's algorithm (Hungarian augmenting-path)** — DFS-based augmenting from each left vertex; simple and fast in practice (O(V·E)).
- **Kuhn with greedy initialization & randomized order** — prematch obvious pairs and shuffle to cut constant factors dramatically.
- **Hopcroft–Karp** — BFS layers + multiple shortest augmenting paths per phase; O(E·√V), the standard fast bipartite matcher.
- **Bipartite matching via max-flow (unit capacities)** — add s→left and right→t unit edges; Dinic gives O(E·√V) automatically.
- **Push–relabel for bipartite matching** — push–relabel specialized to the unit-capacity bipartite network; heights stay O(√V), yielding an O(E·√V) matcher competitive with Hopcroft–Karp.
- **Matching in DAG / interval / convex bipartite graphs** — greedy or sweep-line specializations faster than general matching.

#### König, Hall & Cover/Independent-Set Duality
- **König's theorem** — in bipartite graphs, max matching size equals min vertex cover size (LP duality / from max-flow min-cut).
- **König–Egerváry theorem (weighted form)** — the bipartite assignment LP and its dual coincide; vertex potentials (row/column labels) certify a maximum-weight matching.
- **LP-dual / Egerváry certificate of optimality** — a feasible set of vertex potentials whose total equals the matching weight proves the matching optimal (the Hungarian algorithm's stopping certificate).
- **Constructing the min vertex cover** — from a maximum matching, do alternating-path reachability and pick unreachable-left ∪ reachable-right vertices.
- **Maximum independent set in bipartite graphs** — complement of the minimum vertex cover; |V| − (max matching) (polynomial via König).
- **Minimum edge cover** — for a graph with no isolated vertices, |V| − (max matching) (Gallai's identity).
- **Hall's marriage theorem** — a perfect matching saturating one side exists iff every subset S of that side has |N(S)| ≥ |S|.
- **Deficiency version of Hall (defect form)** — max matching size = |L| − max_S(|S| − |N(S)|); quantifies how far from a perfect matching.
- **Finding a Hall violator** — the unmatched-left vertices and their alternating reachability expose a deficient set S.
- **Systems of distinct representatives (SDR)** — existence reduces directly to a Hall/bipartite matching condition.

#### Weighted Bipartite Matching / Assignment
- **Assignment problem** — find a perfect matching of minimum (or maximum) total weight in a complete bipartite graph.
- **Hungarian algorithm (Kuhn–Munkres)** — primal–dual labeling with equality subgraph and augmenting paths; O(V³) (O(V²·E) variant).
- **Hungarian O(n³) row-by-row (incremental) formulation** — add one left vertex at a time and run a Dijkstra-like shortest-augmenting search over reduced costs, updating potentials; a distinct, very clean alternative to the equality-subgraph version (O(n³)).
- **Hungarian via potentials (Jonker–Volgenant style)** — shortest-augmenting-path implementation with dual potentials and dense-array Dijkstra; very fast O(V³) constant factor.
- **Maximum-weight bipartite matching (non-perfect)** — drop the perfect requirement (or pad with zero arcs) and maximize total weight; same Hungarian/MCMF machinery.
- **Assignment via min-cost max-flow** — model as MCMF with unit capacities; SSP+Dijkstra gives O(V³) for n×n.
- **Auction algorithm (Bertsekas)** — bidders raise prices on objects until equilibrium; naturally parallel, ε-scaling for complexity.
- **Rectangular assignment** — fewer agents than tasks; pad with zero-cost dummies or adapt the Hungarian potentials.
- **Bottleneck assignment problem** — minimize the maximum assigned edge weight; binary search + bipartite feasibility matching.
- **k-best assignments / Murty's algorithm** — enumerate the k cheapest assignments by partitioning the solution space.

### General (Non-Bipartite) Matching

- **Blossom / contracted odd cycle** — an odd alternating cycle that blocks naive augmenting; contracting it restores the augmenting-path search.
- **Edmonds' blossom algorithm** — maximum matching in general graphs by finding/shrinking blossoms; O(V³) (or O(V·E·α) with refinements).
- **Micali–Vazirani** — fastest maximum cardinality general matching via "double depth-first" blossom handling; O(E·√V).
- **Gabow's general matching** — efficient blossom implementation with disjoint-set contraction bookkeeping.
- **Tutte's theorem** — a perfect matching exists iff for every U, the number of odd components of G−U is ≤ |U|.
- **Tutte–Berge formula** — max matching = ½(|V| − max_U(odd_components(G−U) − |U|)); the deficiency analogue for general graphs.
- **Tutte matrix / randomized matching (Lovász, Rabin–Vazirani)** — perfect matching exists iff a random skew-symmetric matrix is nonsingular; matrix-multiplication-time O(V^ω) matching.
- **Weighted general matching (Edmonds)** — minimum/maximum weight perfect (or maximum) matching with blossoms and dual variables; O(V³) (Galil/Gabow refinements).
- **Maximum weight matching (not necessarily perfect)** — blossom variant maximizing total weight without the perfect constraint.
- **Christofides use of matching** — min-weight perfect matching on odd-degree vertices inside the TSP 1.5-approximation (see Graph Algorithms / Approximation).
- **Factor-critical graphs & ear decomposition** — structural theory underpinning blossom correctness and the Gallai–Edmonds decomposition.
- **Gallai–Edmonds structure theorem** — canonical decomposition into D (vertices missed by some max matching), A, C; characterizes all maximum matchings.

### Flow with Lower Bounds, Circulations & Variants

- **Flow with lower bounds (demands)** — each edge has [lower, upper] capacity; transform to a standard max-flow feasibility problem.
- **Feasibility via super source/sink** — route mandatory lower-bound flow through new s′,t′ and check saturation for feasibility.
- **Circulation problem** — feasible flow with no source/sink where every node conserves, subject to lower/upper bounds.
- **Lower-bounded min/max s–t flow** — combine the demand transform with a binary search or extra back-edge to get min or max feasible value.
- **Min-cost circulation** — cheapest feasible circulation; equivalent to general min-cost flow, solved by cycle canceling / network simplex.
- **b-flows / supplies and demands (transshipment)** — each node has a supply/demand; balance via a single super source and super sink.
- **Multi-source multi-sink flow** — add a super source to all sources and super sink from all sinks with appropriate capacities.
- **Vertex capacities (node splitting)** — split v into v_in→v_out with capacity = node limit; reduces node constraints to edge constraints.
- **Undirected edges in flow** — replace each undirected edge with two opposing arcs (or a single arc with symmetric residuals).
- **Edge/vertex-disjoint paths** — unit capacities count edge-disjoint paths; node-splitting counts vertex-disjoint paths (Menger).
- **Flow decomposition theorem** — any flow decomposes into ≤ E paths and cycles; algorithmically extract path/cycle flows.
- **Decomposition into simple paths** — repeatedly trace s→t paths to recover an explicit routing from a flow value (O(V·E)).

### Cut Trees & Global Min Cut

- **Global minimum cut** — minimum cut separating the graph into two nonempty parts with no fixed s,t.
- **Stoer–Wagner algorithm** — deterministic global min cut via maximum-adjacency ordering and merging; O(V³) or O(V·E + V²·log V).
- **Maximum adjacency / minimum cut phase** — order vertices by connectivity to the growing set; last two form a "cut-of-the-phase."
- **Karger's randomized contraction** — repeatedly contract a random edge; finds a min cut with probability ≥ 1/C(V,2) per run.
- **Karger–Stein** — recursive contraction with two half-sized recursive calls; O(V²·log³V) with high success probability.
- **Nagamochi–Ibaraki sparsification** — compute a sparse k-edge-connectivity certificate to speed global min cut.
- **Gomory–Hu tree** — a tree on the vertices encoding all-pairs min s–t cuts via V−1 max-flow computations.
- **Gomory–Hu querying** — min cut between u,v is the minimum-weight edge on their tree path; O(1)–O(V) per query after build.
- **Gusfield's simplification** — build an equivalent flow tree with V−1 max-flow runs without explicit graph contractions; easier to implement.
- **Cut equivalence / flow-equivalent trees** — distinction between Gomory–Hu cut trees and weaker flow-equivalent trees.
- **Hao–Orlin algorithm** — global min cut by a single push–relabel-style sweep maintaining a growing sink set (O(V·E·log(V²/E))).

### Flow-Based Optimization Models

- **Project selection / maximum-weight closure** — choose a closed vertex subset of a DAG maximizing total weight; reduces to min cut.
- **Closure problem reduction** — source→positive nodes (capacity = profit), negative nodes→sink (capacity = cost), prerequisite edges ∞; answer = Σpositive − min cut.
- **Maximum-density subgraph** — maximize |E_S|/|S|; binary/parametric search on density g with a min-cut feasibility test.
- **Goldberg's density construction** — build a flow network parameterized by guess g; min cut < m iff a denser subgraph exists.
- **Parametric / monotone max-flow** — capacities vary monotonically in a parameter; reuse flow across values (Gallo–Grigoriadis–Tarjan, same cost as one max flow).
- **Minimum path cover in a DAG (disjoint)** — minimum vertex-disjoint paths covering all vertices = V − (max bipartite matching of the split graph).
- **Dilworth's theorem** — in a poset, minimum chain cover = maximum antichain size; computed via bipartite matching on the comparability/reachability DAG.
- **Mirsky's theorem** — dual of Dilworth: minimum antichain cover = longest chain length (see Order Theory / DP).
- **Minimum path cover (overlapping/general DAG)** — take transitive closure first, then disjoint path cover, to allow shared vertices.
- **Maximum antichain construction** — recover the largest antichain from König's min vertex cover on the matching.
- **Minimum vertex cover in general graphs** — NP-hard; only the bipartite case is polynomial via König (see Graph Algorithms / NP-hard).
- **Maximum-weight independent set in interval/bipartite/perfect graphs** — polynomial via flow/matching or DP; general case NP-hard.
- **Minimum-cost circulation for scheduling** — model time-indexed tasks/resources as a cost circulation (production, transportation).
- **Transportation & transshipment problems** — supply/demand bipartite (or general) min-cost flow specializations.
- **Tournament / sports elimination** — decide if a team can still finish first by checking whether a flow saturates remaining-games edges.
- **Image segmentation / energy minimization (graph cuts)** — submodular pairwise MRF energies minimized exactly by s–t min cut (see Computer Vision / Optimization).
- **Minimum cut for bipartite-like profit models** — partition items into two groups balancing rewards and penalties via min cut.
- **Open-pit mining / precedence-constrained selection** — same structure as maximum closure with precedence edges.

### Flow Modeling Patterns & Reductions

- **Node splitting for per-vertex constraints** — encode visit limits, vertex capacities, or vertex costs by in/out vertex pairs.
- **Edge gadget for "at most one direction"** — model mutually exclusive choices with capacity-1 arcs and infinite back-edges.
- **Lower-bound gadget for "must use"** — force mandatory usage by lower bounds or by pre-saturating and subtracting.
- **Infinite-capacity precedence edges** — represent implications "choosing A forces B" as ∞ arcs so they never appear in a finite cut.
- **Bipartition by min cut (two-class partition)** — when each element pays a cost depending on which side it lands, model as s–t min cut.
- **Turning maximization into min cut** — total profit − min cut, the standard "project selection" reframing.
- **Layered/time-expanded graphs** — replicate the graph per time step to model dynamic flows, evacuations, and scheduling over time.
- **Dynamic / over-time flows (Ford–Fulkerson temporal)** — flows with transit times; quickest-flow and earliest-arrival problems.
- **Matrix rounding / consistent rounding** — round a real matrix to integers preserving row/column sums via integral flow.
- **Doubly stochastic / Birkhoff–von Neumann decomposition** — express a doubly stochastic matrix as a convex combination of permutation matrices via repeated perfect matchings.
- **Degree-constrained subgraph (b-matching)** — find a subgraph meeting per-vertex degree bounds via a flow/matching reduction.
- **Edge coloring of bipartite graphs** — Δ-edge-coloring via repeated perfect matchings / Euler splitting (König's edge-coloring theorem).
- **Scheduling on machines (open shop, preemptive)** — preemptive scheduling and deadline feasibility modeled as bipartite/transportation flow.
- **Baseball/elimination & quota gadgets** — distribute a fixed quantity subject to pairwise caps via a small flow network.
- **Lower/upper bound "exactly k" gadgets** — enforce exact counts by setting equal lower and upper bounds on a controlling edge.

### Complexity, Theory & Special Structures

- **LP duality view of flows** — max-flow/min-cut and min-cost flow are linear programs; cuts and potentials are the dual variables.
- **Total unimodularity** — the node-arc incidence matrix is totally unimodular, guaranteeing integral optimal flows.
- **Strongly vs weakly polynomial** — distinction between capacity-dependent (Edmonds–Karp scaling) and capacity-independent (Orlin) bounds.
- **Unit-capacity / unit-vertex networks** — special faster bounds (O(E·√V)) for matching and disjoint-path problems.
- **Planar max-flow / min-cut** — duality with shortest paths in the planar dual gives near-linear planar algorithms (Itai–Shiloach, Borradaile–Klein).
- **Submodular function minimization (SFM)** — generalizes min cut; minimized in strongly polynomial time, with cut a special submodular case (see Combinatorial Optimization).
- **Multicommodity flow** — multiple source–sink pairs share capacities; LP-solvable, but max-flow min-cut gap exists (approximation/flow–cut gaps).
- **Sparsest cut & expansion** — NP-hard cut objective approximated via multicommodity flow / SDP relaxations (see Approximation Algorithms).
- **Fractional vs integral matching/flow** — fractional optima are integral for bipartite/flow; general matching polytope needs odd-set (blossom) inequalities.
- **Matching polytope & odd-set constraints** — Edmonds' description of the perfect matching polytope by odd cut constraints.

<sub>[↑ Back to top](#table-of-contents)</sub>

---

## 19. Tree Algorithms & Decompositions

### Tree Fundamentals & Properties

#### Basic Structure & Representations
- **Tree definition** — connected acyclic undirected graph on n nodes with exactly n−1 edges; unique simple path between any two nodes.
- **Adjacency-list representation** — store neighbor lists per vertex; canonical storage for traversal (O(n) space).
- **Parent-array representation** — store parent of each node (root = self/null); compact for rooted trees (O(n) space).
- **Children-list representation** — explicit child lists after rooting; supports top-down recursion (O(n) space).
- **First-child / next-sibling encoding** — binarize an arbitrary-arity tree into a left-child right-sibling form (O(n)).
- **Edge list with weights** — store (u, v, w) triples for weighted-tree problems (O(n) space).
- **Implicit forest** — a disjoint union of trees; many tree algorithms generalize per-component.
- **Bracket / parenthesis encoding** — represent an ordered tree as a balanced-bracket sequence; basis for succinct structures.

#### Rooting & Derived Quantities
- **Rooting a tree** — pick a root, run DFS/BFS to orient edges parent→child (O(n)).
- **Parent / children computation** — fill parent[] and child lists in one DFS pass (O(n)).
- **Subtree sizes** — post-order accumulation size[v] = 1 + Σ size[child] (O(n)).
- **Depth (distance from root)** — set depth[child] = depth[v]+1 during DFS (O(n)).
- **Height of a node** — max edge-distance to a leaf in its subtree; height[v] = 1+max child height (O(n)).
- **Height / depth of tree** — max depth over all nodes; longest root-to-leaf path (O(n)).
- **Leaf / internal node detection** — degree-1 (non-root) nodes are leaves; trivial scan (O(n)).
- **Number of leaves / internal nodes** — count by degree after rooting (O(n)).
- **Ancestor / descendant relationship** — u is ancestor of v iff u lies on root→v path (test via tin/tout, see Euler tour).
- **Path between two nodes** — concatenate u→LCA and LCA→v; reconstruct via parent pointers (O(path length)).
- **Sum / aggregate over subtree** — bottom-up DP accumulating any associative value (O(n)).
- **Number of nodes at each depth (level counts)** — bucket by depth during BFS (O(n)).
- **Degree sequence** — multiset of vertex degrees; characterizes many tree properties (O(n)).
- **Sum of subtree depths / vertical-path counts** — accumulate Σ depth and Σ size for averaging and counting tasks (O(n)).

#### Tree Traversals
- **DFS (pre/in/post-order)** — recursive or explicit-stack traversal; backbone of nearly all tree algorithms (O(n)).
- **BFS / level-order traversal** — layer-by-layer visit using a queue; yields shortest hop distances from root (O(n)).
- **Iterative DFS (stack-based)** — avoid recursion-depth limits on deep/degenerate trees (O(n)).
- **Recursion-stack depth pitfalls** — chains give depth Θ(n); prefer explicit stacks or increased limits.
- **Pre-order interval = subtree** — pre-order timestamps map each subtree to a contiguous range (see Euler tour).

### Tree Diameter, Center & Centroid

#### Diameter
- **Diameter (longest path)** — maximum number of edges (or weight) on any path between two nodes.
- **Two-pass BFS/DFS method** — farthest node u from arbitrary start, then farthest from u gives diameter (unweighted, O(n)); valid because the farthest node is always a diameter endpoint.
- **Tree-DP diameter** — at each node combine the two deepest child-branches; track global max (O(n)); works on weighted/negative-free trees.
- **Diameter endpoints recovery** — store predecessor during the second BFS to reconstruct the path (O(n)).
- **Weighted-tree diameter** — same two-DFS / DP approach using edge weights (nonnegative) (O(n)).
- **All eccentricities via diameter endpoints** — ecc(v)=max(dist(v,a),dist(v,b)) for diameter ends a,b (O(n)).
- **Two-diameter (second-best) recovery** — track best/second-best branches per node for near-diameter queries (O(n)).
- **Diameter under edge insertion / merging trees** — new diameter endpoints come from the four old endpoints (O(1) per merge with precomputed distances).

#### Center & Centroid
- **Tree center** — vertex(es) minimizing eccentricity; located as the middle of any diameter path (1 or 2 nodes) (O(n)).
- **Center via leaf-peeling** — repeatedly remove all current leaves; last 1–2 remaining nodes form the center (O(n)).
- **Tree centroid** — vertex whose removal leaves all components of size ≤ ⌊n/2⌋ (1 or 2 centroids) (O(n)).
- **Centroid via subtree sizes** — node where max(largest child subtree, n−size[v]) ≤ n/2 (O(n)).
- **Radius** — minimum eccentricity = eccentricity of the center (O(n)).
- **Center vs centroid distinction** — center minimizes max distance; centroid balances subtree sizes; generally different vertices.
- **Weighted centroid (mass-balanced)** — centroid w.r.t. vertex weights; node where every component's weight ≤ half the total (O(n)).
- **Sum-of-distances centroid (1-median)** — vertex minimizing Σ dist(v,u); reroot DP finds it, and it coincides with the centroid (O(n)).

### Lowest Common Ancestor (LCA)

#### Core Approaches
- **Naïve level-up LCA** — equalize depths, then climb both pointers together (O(n) per query, no preprocessing).
- **Binary lifting (sparse ancestor table)** — precompute up[v][k] = 2^k-th ancestor; jump in powers of two (O(n log n) build, O(log n) query).
- **Euler tour + sparse-table RMQ** — flatten by first-visit order; LCA = min-depth node in the in-range (O(n log n) build, O(1) query).
- **Euler tour + segment-tree RMQ** — same reduction with a segment tree (O(n) build, O(log n) query).
- **Sqrt-decomposition LCA** — block-based ancestor jumps (O(n) build, O(√n) query); simple alternative.
- **Tarjan offline LCA (DSU)** — DFS with union-find answering all queries in one pass (O((n+q) α(n))); offline only.
- **Farach-Colton & Bender (±1 RMQ)** — exploit that adjacent Euler depths differ by ±1 to get O(n) build, O(1) query.
- **HLD-based LCA** — climb heavy chains; LCA emerges when both nodes share a chain (O(log n) query after O(n) build).
- **Schieber–Vishkin LCA** — bit-trick labeling giving O(n) preprocessing and O(1) queries (advanced).
- **Harel–Tarjan / Berkman–Vishkin O(1) LCA** — reduce LCA to ±1 RMQ for O(n) build, O(1) query (theoretically optimal, hard to implement).
- **Sparse-table RMQ on depth without Euler doubling** — alternative reduction; same O(1) query bound.

#### Related Ancestor & Path Queries

- **k-th ancestor (binary lifting)** — climb in powers of two via up[v][k] (O(log n) per query, O(n log n) build).
- **Level ancestor (LA) via jump pointers** — same table answers "ancestor at given depth" (O(log n) per query).
- **Long-path (long-edge) decomposition + ladders** — decompose into longest downward paths, then extend each into a "ladder" of double length; combined with jump pointers gives O(n) space, O(1) level-ancestor queries (the canonical O(1) LA technique).
- **Ladder algorithm standalone** — climb the highest jump pointer then resolve within one ladder in O(1) after the jump (O(n log n) space if jump pointers stored).
- **Macro–micro tree LA** — split into small bottom micro-trees and a macro skeleton for O(n) build, O(1) query (advanced LA).
- **k-th vertex on a path u–v** — split at LCA: if k ≤ dist(u,LCA) take k-th ancestor of u, else take (len−k)-th ancestor of v (O(log n) with binary lifting; O(1) with O(1) LA).
- **k-th vertex via order-augmented binary lifting** — store, with each 2^k jump, the ordered chain info so the k-th node on any path is recovered directly (O(log n) query).
- **Distance between nodes** — dist(u,v)=depth[u]+depth[v]−2·depth[LCA] (O(log n) with LCA, O(1) with O(1) LCA).
- **Whether w lies on path u–v** — true iff dist(u,w)+dist(w,v)=dist(u,v), or w is ancestor of exactly one endpoint and a descendant of LCA (O(log n)).
- **Path intersection (does path a–b meet path c–d)** — compute the four endpoint-pair LCAs and the candidate meeting node max-depth(LCA(a,b)…); paths intersect iff that node lies on both paths, verified by ancestor/distance tests (O(log n) per query).
- **Path–path common segment** — the intersection of two tree paths is itself a path; its endpoints are among {a,b,c,d} LCAs, recoverable in O(log n).
- **Meeting point / Steiner LCA of multiple nodes** — fold pairwise LCAs; basis for virtual-tree construction (O(k log n)).
- **Lowest common ancestor in a forest / multiple roots** — add a virtual super-root or handle per-tree.
- **Online LCA without recomputation under updates** — combine Euler tour with a dynamic RMQ structure.
- **LCA with min/max/sum edge on path** — store the aggregate alongside each 2^k jump in the lifting table (O(log n) query).
- **Frequency / mode along ancestor chains** — binary lifting with merged aggregates per 2^k jump (O(log n) per query, large constant).

### Euler Tour Technique & Flattening

- **Euler tour (entry/exit timestamps)** — DFS recording tin[v] on entry and tout[v] on exit (O(n)).
- **Subtree → contiguous range** — subtree of v occupies indices [tin[v], tout[v]]; turns subtree queries into range queries (O(n) build).
- **Ancestor test via tin/tout** — u ancestor of v iff tin[u] ≤ tin[v] ≤ tout[u] (O(1) per test).
- **Edge/vertex flattening for path queries** — Euler-walk with +/− entries lets prefix sums answer root-path queries (O(n)).
- **Euler tour of edges (2n−2 length)** — list each edge twice (down/up); basis for ±1 RMQ LCA.
- **Subtree point update / subtree range query** — map to Fenwick/segment tree over the flattened order (O(log n) per op).
- **Path-to-root updates via difference on Euler array** — +x at tin[v], −x at tout[v]+1 to add to a whole subtree (O(log n)).
- **First-occurrence array (euler/first[])** — maps each node to its position in the Euler array for RMQ-LCA (O(n)).
- **Tour-based virtual ordering** — sort a node subset by tin to drive auxiliary-tree and centroid algorithms.
- **Sibling / DFS-order neighbor queries** — pre-order indices give "next node in DFS" relationships in O(1).
- **±1 / +1−1 Euler array properties** — adjacent positions differ by one in depth; enables ±1-RMQ optimizations.

### Heavy-Light Decomposition (HLD)

- **Heavy child / heavy edge** — child with the largest subtree; edge to it is heavy, others light (O(n) to mark).
- **Chain decomposition** — partition tree into heavy chains separated by light edges; any root path crosses O(log n) chains.
- **Positional labeling for segment trees** — assign contiguous positions along chains so each chain is a contiguous segment-tree range (O(n)).
- **Path query/update via HLD** — climb chains, querying each chain segment in the data structure (O(log² n) with a segment tree).
- **Subtree query/update via HLD** — chain ordering also makes each subtree contiguous, combining path + subtree ops (O(log n) / O(log² n)).
- **Edge-weight handling (edge-on-vertex trick)** — store each edge's weight on its deeper endpoint; skip the LCA in path queries (O(1) adjustment).
- **Vertex-weight handling** — directly map vertices to positions; include LCA in path queries.
- **LCA via HLD** — repeatedly jump to chain head of the deeper node until chains coincide (O(log n)).
- **HLD with Fenwick (BIT) for sums** — replace segment tree when only invertible aggregates are needed (O(log² n)).
- **Number of light edges on any root path** — provably O(log n), the key complexity guarantee.
- **HLD + lazy propagation** — range path updates (add/assign) plus aggregates (O(log² n)).
- **Non-commutative path aggregates (directional merge)** — keep prefix+suffix values per segment to compose up- and down-segments correctly (O(log² n)).
- **k-th vertex on a path via HLD** — walk chains accumulating lengths to find the k-th node without binary lifting (O(log n)).
- **HLD over single tree once, many query types** — reuse one decomposition for path sum/min/gcd/assign queries.
- **Long-path (vertical) decomposition** — alternative to heavy split based on longest downward paths; underlies O(1) level ancestor (see LA above).

### Centroid Decomposition

- **Centroid decomposition tree** — recursively find centroid, remove it, recurse on components; build a balanced "centroid tree" of depth O(log n) (O(n log n)).
- **Component-size recomputation** — recompute subtree sizes within each remaining component before picking its centroid (O(n) per level).
- **Path-through-centroid principle** — every path lies entirely in some component and passes through that component's centroid; enables counting/aggregating all paths (O(n log n)).
- **Distance-counting on centroid tree** — for each centroid, gather distances of nodes in its component to answer "paths with length/property X" (O(n log² n) typical).
- **Centroid-tree ancestor queries** — each node's centroid ancestors (O(log n) of them) cover all paths through it; supports nearest-marked-node and dynamic distance queries (O(log² n)).
- **Dynamic / closest-marked-vertex (online toggling)** — store best distance at each centroid ancestor; update/query along centroid path (O(log² n) per op).
- **Centroid decomposition for counting pairs** — count node pairs with distance ≤ K, =K, or weight constraints via per-component frequency arrays (O(n log n) or O(n log² n)).
- **Avoiding double counting (inclusion–exclusion)** — subtract contributions within the same child-branch of each centroid.
- **Centroid decomposition with data structures** — attach BIT/segment tree per centroid for weighted/ordered distance queries (O(n log² n)).
- **Persistent / weighted centroid variants** — extend to edge-weighted distances and offline batches.
- **Centroid-tree distance precompute** — store each node's distance to all its O(log n) centroid ancestors for O(log n) distance/aggregate queries (O(n log n) space).
- **Divide-and-conquer on tree (centroid recursion)** — generic D&C over paths via repeated centroid removal (O(n log n)).

### Small-to-Large Merging & DSU on Tree

- **Small-to-large (set merging) principle** — always merge the smaller structure into the larger; total cost O(n log n) because each element is moved O(log n) times.
- **DSU on tree (Sack technique)** — process heavy child last and keep its data; reinsert light-subtree contributions per node (O(n log n)).
- **Subtree color/frequency queries offline** — count distinct colors, most-frequent color, sum of dominant colors per subtree (O(n log n)).
- **Merging maps/sets up the tree** — maintain per-subtree hash maps, multisets, or Fenwick trees via small-to-large (O(n log n) × per-op cost).
- **Merging balanced BSTs / treaps by size** — heuristic merge of order-statistic structures (O(n log² n) amortized).
- **Counting subtree statistics (k-th distinct, mode, etc.)** — generic offline answering using the heavy-keep pass.
- **Comparison with Euler-flatten + offline (Mo's on tree)** — alternative for subtree/path frequency queries (see Range Queries & Sqrt Decomposition).
- **Add-then-clear vs keep-heavy pattern** — explicit add/remove callbacks bounded by light-edge depth for the O(n log n) bound.

### Auxiliary (Virtual) Tree

- **Virtual tree concept** — for a query set S, build the minimal tree on S plus pairwise LCAs preserving ancestor relations (O(|S| log n)).
- **Construction via tin-sort + LCA stack** — sort S by tin, insert consecutive LCAs, link with a monotonic stack (O(k log k + k log n)).
- **Compressed-edge weights** — virtual edges carry original path distances for distance-aware DP (O(k)).
- **DP on the virtual tree** — run tree DP only on O(|S|) relevant nodes, summing per-query cost to O(ΣS log n) over all queries.
- **Auxiliary-tree DP for counting/aggregation** — count/aggregate over only the relevant subset per query (O(|S|) after build).
- **Multi-query offline aggregation** — answer many vertex-subset queries in total near-linear-log time.
- **Auxiliary trees for dynamic forests** — advanced offline structure handling Q log Q forest queries.
- **Steiner-tree-on-subset reduction** — the virtual tree is the contraction of the minimal Steiner tree connecting S.

### Tree Isomorphism & Hashing

#### Isomorphism

- **Rooted-tree isomorphism (AHU encoding)** — canonical parenthesis/label string by sorting children's canonical strings; equal strings ⇔ isomorphic (O(n) with care).
- **AHU canonical form (level labeling)** — assign sorted integer labels level by level to get a canonical signature (O(n log n) or O(n) with radix sort).
- **Unrooted-tree isomorphism** — root both trees at their center(s) (1 or 2) and compare canonical forms (O(n log n)).
- **Tree automorphisms / symmetry detection** — find self-isomorphisms via canonical labeling.
- **Counting distinct subtrees up to isomorphism** — group subtrees by canonical signature (O(n) expected / O(n log n) deterministic).

#### Hashing

- **Polynomial / multiset tree hashing** — combine children hashes with a symmetric random function h(v)=f(multiset of child hashes) for fast equality (O(n)); independent of child order for unordered trees.
- **Subtree hashing** — hash each subtree to detect repeated/duplicate subtrees (O(n) expected).
- **Rerooting hash (hash rooted at every node)** — via up/down rerooting DP, compute the canonical rooted hash for every choice of root in O(n) total; the unrooted canonical form is the min/sorted set of these, and equal rooted hashes across nodes detect vertex-transitive symmetry.
- **Center-rooted canonical hash for unrooted equality** — root at the 1–2 centers and take the combined hash, giving an order-independent canonical form (O(n)).
- **Isomorphism collision hardening** — randomized salts, double/multiple hashing, hashing depths into the combiner, or fallback to full AHU to defeat anti-hash tests.

### Counting, Enumeration & Combinatorics on Trees

- **Cayley's formula** — number of labeled trees on n vertices is n^(n−2).
- **Prüfer sequence (tree → code)** — repeatedly remove the smallest-label leaf, recording its neighbor; yields length-(n−2) code (O(n log n) or O(n) linear).
- **Prüfer decoding (code → tree)** — reconstruct via degree counting and a priority queue/pointer (O(n) with linear method).
- **Prüfer bijection consequences** — proves Cayley's formula and counts labeled forests/trees with fixed degrees (multinomial).
- **Counting labeled trees with given degrees** — (n−2)! / Π(d_i − 1)! via Prüfer (multinomial coefficient).
- **Generalized Cayley (k-tree forest count)** — k·n^(n−k−1) labeled forests of k trees with k specified roots.
- **Counting spanning trees (Kirchhoff / Matrix-Tree theorem)** — determinant of the reduced Laplacian (see Graph Algorithms).
- **Counting rooted/unrooted trees up to isomorphism** — Otter's asymptotics / generating functions (combinatorial, advanced).
- **Catalan-related counts (binary/ordered trees)** — number of shapes via Catalan numbers (see Combinatorics & Number Theory).
- **Random tree generation** — sample a uniform labeled tree via a random Prüfer code (O(n)).
- **Random tree with degree / shape constraints** — Prüfer with fixed multiset, or random recursive trees for biased shapes.

### Tree Dynamic Programming

- **Subtree (rooted) tree DP** — post-order combine of children states; standard framework (O(n × state)).
- **Rerooting DP (all-roots DP)** — compute every node's answer-as-root using up/down passes and prefix/suffix combines (O(n)); see Dynamic Programming.
- **Tree DP with auxiliary dimensions** — states like (node, count, color, parity) for constrained subtree problems (O(n × K)).
- **Maximum-weight independent set on trees** — DP with include/exclude states per node (O(n)).
- **Minimum vertex cover / dominating set on trees** — greedy or DP in linear time (O(n)).
- **Tree knapsack / tree-grouped DP** — combine child knapsacks with size-bounded merging giving O(n²) or O(n·K) via the "tree-knapsack" trick.
- **Counting paths/subtrees with a property** — accumulate via per-node combination of child contributions (O(n) or O(n log n)).
- **DP optimizations on trees** — convex-hull trick, Li Chao, divide-and-conquer, monotonic merging on tree DP (see Dynamic Programming).
- **Auxiliary-tree DP** — restrict tree DP to virtual-tree nodes per query (see Auxiliary Tree).
- **DP on tree for game theory (Grundy on trees)** — compute nimbers/Grundy values bottom-up (see Game Theory).
- **Reroot DP for sum/max distance to all nodes** — compute Σ dist and max dist from every node in O(n) (canonical reroot example).

### Path & Subtree Query Structures (Flattening)

- **Subtree-query via Euler flatten + Fenwick/segment tree** — point update, subtree sum/min/max (O(log n) per op).
- **Path-query via HLD + segment tree** — path sum/min/max/gcd with updates (O(log² n)).
- **Path update + subtree query combos** — Euler difference arrays for additive path-to-root updates (O(log n)).
- **Tree + Mo's algorithm (path/subtree)** — offline path-frequency queries using the Euler/HLD ordering (see Range Queries; O((n+q)√n)).
- **Persistent segment tree on tree paths** — version-per-node along root paths; combine versions of u, v, LCA, parent(LCA) for k-th-on-path / count-on-path queries (O(log n) per query, O(n log n) space).
- **Mergeable segment trees / segment-tree merging** — merge per-node segment trees up the tree for subtree-value queries (O(n log n)).
- **Wavelet-tree / persistent structures for path order statistics** — k-th smallest value on a path (see Advanced Data Structures).
- **Auxiliary BIT-on-Euler for "add to subtree, query node"** — range-update point-query duality (O(log n)).
- **Two-BIT trick for path-add / point-query and point-add / subtree-query** — Euler-based difference encoding (O(log n)).
- **Sparse-table on path (static, no update)** — binary-lifting-stored aggregates give O(log n) static path min/max/gcd without a segment tree.

### Dynamic Trees (Link-Cut & Friends)

- **Link-cut trees (LCT)** — represent a dynamic forest with link/cut and path aggregates via splay-based preferred paths (O(log n) amortized); see Advanced Data Structures.
- **Access / expose operation** — splice the root→v path into one preferred path; core LCT primitive (O(log n) amortized).
- **Path aggregates / lazy values in LCT** — path sums/min/max, lazy add/assign, reversal for re-rooting (O(log n) amortized).
- **Re-rooting (make-root)** — flip path orientation via lazy reverse (O(log n)).
- **Subtree aggregates in LCT** — maintain virtual-subtree sums for fully dynamic subtree queries (O(log n) amortized; advanced).
- **Dynamic LCA / connectivity via LCT** — find-root and path operations answer connectivity and LCA online (O(log n)).
- **Euler-tour trees (ETT)** — represent dynamic forest by its Euler sequence in a balanced BST; supports subtree aggregates and connectivity (O(log n)); see Advanced Data Structures.
- **Top trees** — hierarchical clustering for general dynamic-tree path/subtree queries (O(log n)); advanced.
- **Self-adjusting / RC (rake-compress) trees** — alternative dynamic-tree contraction framework.
- **Offline dynamic connectivity on trees** — divide-and-conquer over time + DSU rollback (see Graph Algorithms).
- **Fully dynamic MST / connectivity** — link-cut / ETT applications (see Graph Algorithms).

### Special Tree Structures & Miscellaneous

- **Binary / k-ary tree properties** — height bounds, complete/perfect/balanced definitions (structural).
- **Weighted-tree distance precomputation** — root distances + LCA for O(1)/O(log n) pairwise distances (O(n log n) build).
- **Tree as metric space / ultrametric** — path distances form a tree metric; four-point condition characterizes tree metrics.
- **Tree-metric reconstruction (additive / 4-point)** — recover the tree from a distance matrix obeying the four-point condition (O(n²)).
- **Lowest common ancestor of edges / on compressed trees** — generalizations used in suffix trees and tries.
- **Tree compression / smart-merging of degree-2 chains** — contract paths of degree-2 nodes to shrink the tree (O(n)).
- **Pendant / leaf-removal orderings** — peeling sequences for greedy and DP arguments (O(n)).
- **Tree coloring / proper 2-coloring (bipartite)** — trees are always bipartite; BFS 2-coloring (O(n)).
- **Maximum matching on trees** — greedy leaf-matching / DP gives maximum matching (O(n)); König duality with vertex cover.
- **Maximum independent set / minimum vertex cover duality** — on trees both solvable in O(n) via DP; complements via König.
- **Tree edit distance** — minimum node insert/delete/relabel to transform one ordered tree into another (Zhang–Shasha, O(n²) to O(n⁴)).
- **Heavy-path / preferred-path indexing for strings** — heavy-path decomposition of suffix trees/tries (see String Algorithms).
- **Tree DP over auxiliary "centroid path"** — combine centroid decomposition with DP for divide-and-conquer on trees.
- **Persistent / functional tree updates** — path-copying for immutable tree snapshots (see Advanced Data Structures).
- **Minimum/maximum spanning tree** — Kruskal/Prim/Borůvka build a tree from a graph (see Graph Algorithms).
- **Steiner tree in trees / on small terminal sets** — connect a terminal set with minimum cost (poly on trees; NP-hard in general graphs, see Graph Algorithms).
- **Tree partitioning / k-component splitting** — DP to cut k−1 edges optimizing component sizes or values (O(n·k)).
- **Longest path / longest increasing path on trees** — reroot or DP variants of the diameter (O(n)).
- **Auxiliary "kth nearest" / nearest special vertex on tree** — centroid-decomposition or LCT-backed nearest-marked queries (O(log² n)).

<sub>[↑ Back to top](#table-of-contents)</sub>

---

# 🔤 Part — Strings

## 20. String Matching, Periodicity & Hashing

### Foundations & Problem Setting

- **Alphabet & string model** — finite ordered alphabet Σ, strings as sequences over Σ; conventions for indexing, length n, empty string ε.
- **Exact single-pattern matching problem** — find all (or first) occurrences of pattern P (length m) in text T (length n); output set of starting positions.
- **Online vs offline matching** — online scans text left-to-right with bounded lookahead; offline may preprocess the whole text (e.g. suffix structures).
- **Text-indexing vs pattern-indexing** — preprocess the pattern (KMP, Z, BM, automaton) versus preprocess the text (suffix tree/array, FM-index) (see Suffix Structures).
- **Comparison model vs RAM model** — number of character comparisons vs word-RAM operations; lower bound Ω(n) comparisons in the worst case.
- **Borders, periods, occurrences** — a border is a proper prefix that is also a suffix; period p satisfies T[i]=T[i+p]; tight duality (period = n − longest border).
- **Primitivity & primitive root** — a string is primitive if it is not an integer power of a shorter string; every string has a unique primitive root.

### Naive / Brute-Force Matching

- **Naive sliding-window matching** — try every alignment, compare characters until mismatch (O(nm) worst case, O(n) best case, O(1) extra space).
- **Naive with early-exit heuristics** — break on first mismatch, optionally compare last char first; same worst case but better average behavior.
- **Expected-time analysis** — over random text the naive scan runs in O(n) expected on large alphabets (≈ n·(1 + 1/(σ−1)) comparisons).

### Prefix Function & Knuth–Morris–Pratt (KMP)

#### Prefix (Failure) Function

- **Prefix function π** — π[i] = length of the longest proper border of prefix T[0..i] (O(n) construction, O(n) space).
- **Incremental computation** — extend by one character, follow failure chain π[π[...]] on mismatch; amortized linear via potential argument.
- **Failure links as a tree** — the π-pointers form the border tree; ancestors of i are exactly the borders of the prefix ending at i.

#### KMP Matching

- **KMP search** — concatenate or run π over P then stream T, shifting by the failure function to never re-examine matched text (O(n+m) time, O(m) space).
- **Avoiding the separator** — run matching directly with the pattern's π array against the text without materializing P#T.
- **Optimized (strong) failure function** — skip links to the same next character to reduce redundant comparisons (KMP "next" optimization).

#### Applications of the Prefix Function

- **All borders of a string** — enumerate the failure chain from π[n−1] (O(number of borders)).
- **Smallest period & full periodicity** — period = n − π[n−1]; string is a power iff n mod period = 0.
- **Count occurrences of each prefix** — DP over π in reverse to tally how often each prefix appears as a substring (O(n)).
- **Number of distinct prefix-borders / automaton transitions** — build prefix-function automaton δ(state,char) for streaming over arbitrary text (O(nσ) build, O(1) transition).
- **String compression / shortest generating block** — detect if T = (block)^k via the period test.
- **Counting distinct substrings incrementally** — append characters and use π of reversed prefixes (alternative to suffix structures).

### Z-Function (Z-Algorithm)

- **Z-array definition** — z[i] = length of longest substring starting at i that matches a prefix of the string (z[0] conventionally 0 or n).
- **Linear Z-construction** — maintain rightmost match window [l,r], reuse z within it, extend beyond r naively (O(n) time, O(n) space).
- **Z-based pattern matching** — compute Z on P#T (separator not in Σ); occurrence wherever z[i] ≥ m (O(n+m)).
- **Z vs prefix function equivalence** — each convertible to the other in O(n); both encode border/period structure.
- **Distinct-substring and period queries via Z** — period detection by scanning divisors i with i + z[i] = n.
- **Longest common prefix of suffixes (single string)** — Z gives LCP of the whole string with each of its suffixes directly.

### Hashing-Based Matching

#### Polynomial String Hashing

- **Polynomial rolling hash** — treat string as base-B polynomial mod M; H(s) = Σ s[i]·B^i mod M (O(n) precompute, O(1) substring hash).
- **Prefix-hash + substring extraction** — store prefix hashes and powers of B to get any substring hash in O(1).
- **Base and modulus choice** — base > alphabet size, coprime to M; large prime modulus (≈ 2^61−1 Mersenne) to limit collisions.
- **Double / multiple hashing** — independent (B,M) pairs to drive collision probability to ~1/M² and resist single-mod attacks.
- **Collision probability analysis** — birthday-bound ≈ q²/(2M) over q compared pairs; choose M accordingly.

#### Rabin–Karp

- **Rabin–Karp rolling-hash search** — slide a fixed-width hash over T, compare to pattern hash, verify candidates (O(n+m) expected, O(nm) worst with verification).
- **Multi-pattern Rabin–Karp** — hash all equal-length patterns into a set, single pass over T checking membership.
- **Monte Carlo vs Las Vegas variants** — skip verification (small error probability) vs verify every hit (always correct, expected linear).

#### Hashing Toolbox & Pitfalls

- **Anti-hash test resistance** — randomize base/modulus at runtime to defeat adversarial collision inputs; avoid fixed (31, 1e9+7).
- **Thue–Morse / adversarial collisions** — known constructions force collisions on small fixed parameters; double hashing mitigates.
- **Hash of concatenation / merge** — combine two segment hashes with precomputed power factor (O(1)) for binary-search and segment-tree use.
- **Binary search on hashes (LCP / LCE)** — find longest common extension between two positions by binary searching matching prefix length (O(log n) per query after O(n) prep).
- **2D / matrix hashing** — row-then-column polynomial hashing for 2D pattern search (O(rows·cols)).
- **Hashing for palindrome/equality tests** — compare forward and reversed hashes to test palindromicity in O(1).
- **Hashing pitfalls** — overflow on multiplication, signed mod, weak base making anagrams collide; use 128-bit or Mersenne mod.

### Boyer–Moore Family (Right-to-Left Matching)

- **Boyer–Moore** — compare right-to-left, shift by max of bad-character and good-suffix rules (sublinear average, O(n+m) with Galil rule, O(nm) naive worst).
- **Bad-character rule** — on mismatch, shift so the text char aligns with its rightmost occurrence in P (or past it) (O(σ+m) preprocessing).
- **Good-suffix (strong) rule** — shift using borders/occurrences of the matched suffix within P (O(m) preprocessing).
- **Galil rule** — remembers matched region to guarantee linear worst-case for repeated patterns.
- **Boyer–Moore–Horspool** — simplified BM using only bad-character on the last window char (O(nm) worst, very fast average, O(σ) space).
- **Sunday (Quick Search)** — shift based on the character just past the window; simple and fast in practice (O(nm) worst).
- **Apostolico–Giancarlo** — BM variant achieving provable ≤1.5n comparisons by recording match lengths.
- **Turbo Boyer–Moore** — combines previous match info with BM shifts for guaranteed linearity and fewer comparisons.

### Finite-Automaton Matching

- **String-matching DFA** — build deterministic automaton with m+1 states recognizing occurrences; transitions δ(q,c) (O(mσ) build/space, O(n) scan).
- **δ-function via prefix function** — construct the DFA transitions directly from KMP's π to avoid O(m²σ) naive build (O(mσ)).
- **Suffix-automaton / DAWG matching** — match by walking the suffix automaton of the text (see Suffix Structures).
- **Factor (suffix) oracle (BOM)** — Backward Oracle Matching, a compact factor automaton giving fast average-case search (O(n) average).

### Aho–Corasick (Multi-Pattern Matching)

- **Trie of patterns (goto function)** — build keyword trie of all patterns; edges are explicit transitions (O(total pattern length · log σ or ·σ)).
- **Failure (suffix) links** — link each node to the longest proper suffix that is a trie node, built by BFS (Σ pattern lengths).
- **Aho–Corasick search** — stream text, follow goto/failure links, report all matches (O(n + total matches), O(states·σ) automaton).
- **Output / dictionary suffix links** — shortcut links to the next dictionary node along the failure chain, enabling O(occ) reporting.
- **Goto automaton (full DFA form)** — precompute δ for every (state,char) so each text step is O(1) (O(states·σ) space).
- **Counting matches without listing** — propagate counts along failure-link tree to total occurrences per pattern.
- **Aho–Corasick + DP on automaton** — count/optimize strings avoiding or containing patterns by DP over automaton states (e.g. forbidden-substring counting).
- **Aho–Corasick on tries / virtual texts** — process many query strings sharing structure against the dictionary.

### Bitap (Shift-Or / Shift-And) & Bit-Parallelism

- **Shift-And** — maintain bitmask of active prefix matches, AND with character mask, shift; reports match when MSB set (O(n·⌈m/w⌉), word size w).
- **Shift-Or** — complemented variant avoiding an initialization OR; same complexity, fewer ops.
- **Bitap with k errors (approximate)** — extend to k mismatches/edits with O(k) bit-vectors per position (Wu–Manber) (O(kn·⌈m/w⌉)).
- **BNDM (Backward Nondeterministic DAWG Matching)** — bit-parallel backward factor search, sublinear average (O(n·⌈m/w⌉)).
- **Bit-parallel for sets / classes** — character classes, wildcards, and small pattern sets encoded as masks.

### Palindromes

- **Manacher's algorithm** — compute longest palindromic radius centered at every position (odd & even) in one pass (O(n) time/space).
- **All palindromic substrings via Manacher** — radii arrays implicitly encode all Θ(n²) palindromic substrings and count of distinct centers (O(n)).
- **Longest palindromic substring** — max radius from Manacher (O(n)); also via hashing + binary search (O(n log n)).
- **Palindrome enumeration by hashing** — expand-around-center with hashed equality checks (O(n log n)).
- **Eertree (palindromic tree)** — automaton of all distinct palindromic substrings, ≤ n+2 nodes, with suffix links (O(n·log σ) build) (see Suffix Structures for the data-structure family).
- **Counting distinct palindromes** — eertree node count gives number of distinct palindromic substrings (O(n)).
- **Palindromic factorization (min cuts)** — DP using eertree series links or Manacher to partition into fewest palindromes (O(n log n)).

### Border & Period Theory

- **Border = period duality** — every period corresponds to a border and vice versa; smallest period = n − longest border.
- **Fine–Wilf periodicity theorem** — if a string of length n has periods p and q with p+q−gcd(p,q) ≤ n, then gcd(p,q) is also a period.
- **Critical factorization theorem** — there is a factorization point where the local period equals the global period (basis of constant-space matching).
- **Weak/strong periodicity lemmas** — conditions under which two periods force a smaller common period.
- **Set of all periods structure** — periods of a string form a structured set; long periods relate to borders via the failure function.
- **Cover & seed (quasiperiodicity)** — shortest cover (substring whose occurrences cover the string) and seeds, computable in O(n).
- **Enhanced cover / λ-cover** — partial-cover generalizations and their linear/near-linear computation.

### Repetitions, Runs & Periodicity Structures

- **Run (maximal periodicity)** — maximal substring with period p and length ≥ 2p; "runs" capture all repetitions compactly.
- **The runs theorem** — number of runs in a string of length n is < n (bound 1.029n), all found in O(n) via Lempel–Ziv or suffix structures.
- **Main–Lorentz algorithm** — find all maximal repetitions via divide-and-conquer with Z-functions (O(n log n)).
- **Crochemore's repetitions algorithm** — report all maximal repetitions in O(n log n).
- **Tandem repeats / squares** — substrings of form XX; counting and listing primitively-rooted squares (O(n log n) to O(n)).
- **Lempel–Ziv factorization (LZ77)** — factor into longest previous-occurrence phrases; foundation for runs and many string stats (O(n) with suffix array/automaton).
- **Number of distinct squares bound** — at most 2n distinct squares (combinatorial result used in counting).
- **Powers and exponent / index of a string** — maximal exponent (critical exponent) and detecting k-th powers via runs.

### Lyndon Words & Factorization

- **Lyndon word** — non-empty string strictly smaller than all its proper rotations (equivalently than all proper suffixes); always primitive.
- **Chen–Fox–Lyndon theorem** — every string factors uniquely into non-increasing Lyndon words.
- **Duval's algorithm** — compute the Lyndon factorization in O(n) time, O(1) extra space.
- **Lexicographically least rotation via Duval** — apply Lyndon factorization to s+s to find minimal-rotation start (O(n)).
- **Booth's algorithm** — least (and greatest) rotation using a modified KMP failure function (O(n) time, O(n) space).
- **Counting / generating Lyndon words** — necklace counting via Möbius/Burnside; generate all Lyndon words in lex order (constant amortized — FKM algorithm).
- **Lyndon array** — for each position, length of the longest Lyndon word starting there; linkable to suffix array/runs (O(n)).
- **Standard factorization & runs connection** — Lyndon factorization underlies the linear-time runs computation (Bannai et al.).
- **Smallest/largest suffix and rotation** — Lyndon theory yields least suffix and least conjugate efficiently.

### Rotations & Conjugacy

- **Minimal / maximal rotation** — least and greatest lexicographic cyclic shift (Booth / Duval, O(n)).
- **Conjugacy (cyclic equivalence) test** — strings are rotations of each other iff one is a substring of the other doubled (KMP/Z on s+s, O(n)).
- **Canonical rotation / necklace normalization** — represent cyclic strings by their minimal rotation for hashing/dedup.
- **LCP between rotations** — longest common prefix of two cyclic shifts via LCE on the doubled string (O(1) after O(n) prep).
- **Cyclic string matching** — find pattern occurrences in a circular text using s+s and standard matchers.

### Wildcards, Don't-Cares & Pattern Classes

- **Wildcard matching (single-char '?')** — match with positions that match any symbol; bit-parallel or automaton handling (O(n·⌈m/w⌉)).
- **Matching with don't-cares (FFT method)** — sum-of-squared-differences with character codes via convolution detects matches with wildcards (O(n log n)).
- **Pattern with '*' wildcard (glob)** — DP table over (text index, pattern index) with star skipping (O(nm), space O(nm) or O(m)).
- **Greedy glob matching** — backtrack-on-star linear-ish heuristic for '*'/'?' patterns (O(nm) worst, near-linear typical).
- **Regular-expression matching (NFA/Thompson)** — build NFA, simulate over text without backtracking (O(nm) for the NFA-simulation form).
- **Regex matching DP** — DP over pattern with '.' and '*' meta-characters (O(nm) time/space).
- **Character classes & bounded gaps** — patterns with [..] sets and {a,b} gaps handled by automaton or bitap extensions.

### Approximate / Error-Tolerant Matching

- **Hamming distance / k-mismatches problem** — find alignments with ≤ k substitutions; naive O(nm), kangaroo method O(n·k) using LCE queries.
- **Kangaroo / LCE jumping** — verify a candidate with ≤ k mismatches in O(k) by jumping over matching runs (O(nk) total).
- **Edit distance / k-differences matching** — approximate matching allowing insertions/deletions/substitutions via banded DP (O(nk)) (full DP see Dynamic Programming domain).
- **Landau–Vishkin** — k-differences approximate matching in O(nk) using LCE and a diagonal DP.
- **Bitap approximate (Wu–Manber)** — k-error matching with bit-parallel vectors (O(kn·⌈m/w⌉)).
- **FFT for k-mismatches** — count matches per alignment per symbol via convolutions (O(σ·n log n) or O(n√k log n) Abrahamson–Kosaraju).
- **q-gram / filtration methods** — index shared substrings to prune candidate regions before exact verification.
- **Fuzzy multi-pattern (Aho–Corasick + errors)** — combine the dictionary automaton with bounded-error DP for approximate dictionary search.

### Counting & Distinct-Substring Problems

- **Count occurrences of a pattern** — total hits via any linear matcher; per-prefix counts via π DP.
- **Count distinct substrings** — n(n+1)/2 minus repeated; computed via suffix automaton/array + LCP (see Suffix Structures) (O(n) / O(n log n)).
- **Count distinct substrings incrementally** — maintain count while appending characters using suffix automaton (amortized O(log σ) per char) (see Suffix Structures).
- **k-th lexicographic / smallest distinct substring** — navigate suffix automaton or suffix array (see Suffix Structures).
- **Substring frequency / number of occurrences** — endpos-set sizes from suffix automaton (see Suffix Structures).

### Specialized & Advanced Techniques

- **Longest common extension (LCE) queries** — answer LCP between arbitrary suffix pairs in O(1) via suffix array + LCP + RMQ, or hashing+binary search (see Suffix Structures).
- **Longest common substring (two+ strings)** — generalized suffix automaton/array or hashing+binary search (O(n) / O(n log n)) (see Suffix Structures).
- **Constant-space exact matching** — Crochemore–Perrin "Two-Way" algorithm using critical factorization (O(n+m) time, O(1) extra space).
- **Galil–Seiferas / Crochemore–Perrin two-way** — linear time, constant space real-time matching families.
- **Real-time matching** — process each text symbol in worst-case O(1) (suffix-automaton or specialized KMP variants).
- **Parameterized matching (p-matching)** — match up to a bijection on a parameter sub-alphabet (Baker); uses prev-encoding + KMP/suffix structures.
- **Order-preserving / order-isomorphic matching** — match by relative order of values rather than exact symbols (failure-function analog, O(n log m)).
- **Jumbled / permutation (Parikh) matching** — find windows whose character multiset equals the pattern's (sliding-window frequency, O(n)).
- **Function / swap / overlap matching** — variants allowing transpositions or position functions, solved via convolution or automata.
- **Compressed pattern matching** — search directly in LZ77/LZW/grammar-compressed text without full decompression.
- **Streaming pattern matching** — match in sublinear o(m) space with hashing (Porat–Porat, O(log m) space, O(log m) time per char, randomized).
- **Dictionary/trie compaction (DAWG of dictionary)** — minimize the keyword automaton for memory-bounded multi-pattern search.

### Cross-References to Adjacent Domains

- **Suffix array, suffix tree, suffix automaton, LCP** — primary text-indexing structures for matching, distinct substrings, LCE, LCS (see Suffix Structures).
- **FM-index / BWT / wavelet tree** — compressed self-indexes for backward search and counting (see Suffix Structures / Succinct Data Structures).
- **FFT / NTT convolution** — engine behind wildcard, don't-care, and k-mismatch counting (see Polynomials & FFT).
- **Edit-distance & sequence-alignment DP** — full approximate-matching cost models (see Dynamic Programming).
- **Trie / radix tree** — base structure for Aho–Corasick and dictionary matching (see Data Structures / Tries).
- **De Bruijn sequences & combinatorics on words** — necklaces, Lyndon-word generation, universal cycles (see Combinatorics).

<sub>[↑ Back to top](#table-of-contents)</sub>

---

## 21. Suffix Structures, Tries & String Automata

### Tries (Prefix Trees) & Variants

#### Core Trie Structures
- **Trie (prefix tree)** — rooted tree where each root-to-node path spells a prefix; children indexed by alphabet symbol, terminal flag marks word ends (insert/lookup O(|key|), space O(total chars · σ)).
- **Trie node representations** — array-of-children (O(σ) per node, fast), hash map per node (sparse, O(1) avg), sorted edge list / linked siblings (memory-lean), bitmap + popcount children (compact) (trade speed for space).
- **Static vs dynamic tries** — frozen DAFSA-style minimization for read-only sets vs pointer/index-allocated tries supporting insert/delete (build O(N), query O(|key|)).
- **Trie with counts & multiplicities** — store subtree word count and pass-through count for frequency, prefix-count, and erase support (insert/delete/count O(|key|)).
- **Coordinate / index-mapped trie** — flatten nodes into arrays (parallel `next[][]`, `cnt[]`) for cache locality and easy persistence (O(1) child step).

#### Compressed & Memory-Optimized Tries
- **Compressed trie / radix tree (Patricia trie)** — collapse non-branching chains into single edges labeled by substrings; nodes ≤ 2·(#keys) (lookup O(|key|), space O(#keys)).
- **PATRICIA (bitwise radix tree)** — branch on the position of the first differing bit, storing skip counts; classic for IP routing tables (lookup O(key bits)).
- **HAT-trie / burst trie** — hybrid of trie nodes and bucketed containers that "burst" when full; cache-efficient string set (near-O(|key|), low constant).
- **Double-array trie** — encode transitions in two integer arrays (base/check) for compact O(1) transitions; common in morphological/NLP dictionaries (lookup O(|key|)).
- **DAFSA / DAWG-of-words (minimal acyclic FSA)** — minimize a trie of a fixed lexicon into a DAG sharing common suffixes; smallest structure recognizing a finite language (build O(N) with incremental minimization).
- **Crit-bit tree** — binary radix tree keyed on the critical (first differing) bit; ordered set with predecessor/successor (operations O(key bits)).

#### Ternary & Specialized Tries
- **Ternary search tree (TST)** — BST-like node per character with lo/eq/hi pointers; balances trie speed with low memory for large alphabets (lookup O(|key| + log σ)).
- **Suffix trie** — trie of all suffixes of a string; conceptually simple but Θ(n²) nodes, the pedagogical precursor to suffix tree/automaton (space O(n²)).
- **Trie of reversed strings** — supports suffix/longest-common-suffix queries and is the basis of generalized constructions over a set.

#### Binary / XOR Tries (Bitwise)
- **Binary (bitwise) trie** — fixed-depth trie over the bits of integers (MSB→LSB), one path per number (insert/query O(B) for B-bit keys).
- **Maximum-XOR query** — greedily descend toward the opposite bit at each level to maximize XOR of a query value against the set (O(B) per query).
- **Minimum-XOR / XOR with constraint** — descend toward matching bits, or compare-against-bound traversal for "count XOR ≤ k" queries (O(B)).
- **XOR trie with counts & deletion** — store subtree counts to support a multiset, erasing elements, and rollback (O(B)).
- **Persistent XOR trie** — path-copy versions per prefix so XOR queries restrict to a value range / index range; powers "max XOR of a subarray-suffix" style problems (O(B) per query, O(B) space per insert).
- **Mergeable / small-to-large XOR trie** — merge tries on tree-DSU or auxiliary-tree problems, charging O(B) per moved leaf (amortized via small-to-large).
- **Trie + offline divide & conquer on bits** — alternative to a persistent trie for batched max-XOR with constraints.

### Suffix Trees

#### Definitions & Properties
- **Suffix tree** — compressed trie of all suffixes of `s$`; n+1 leaves, ≤ n internal branching nodes, edges labeled by substring intervals (space O(n)).
- **Implicit vs explicit suffix tree** — implicit tree (no sentinel) may end suffixes inside edges; appending a unique `$` makes every suffix a distinct leaf.
- **Suffix links** — internal node for string `xα` links to node for `α`; the structural backbone enabling linear construction and traversal.
- **Generalized suffix tree** — single tree over multiple strings (distinct sentinels `$₁,$₂,…`); leaves tagged by source string (build O(Σ|sᵢ|)).

#### Construction Algorithms
- **Ukkonen's algorithm** — online, left-to-right linear construction using the active point (active node/edge/length), suffix links, and skip/count trick (O(n) for constant σ, O(n log σ) general).
- **Three Ukkonen extension rules** — rule 1 extend leaf, rule 2 create new branch/leaf, rule 3 do-nothing (suffix already present → "show stopper" ending the phase).
- **Global end & "open" leaves** — a shared end pointer auto-extends every leaf edge, giving the amortized linear bound.
- **Weiner's algorithm** — first linear-time method; builds by inserting suffixes from shortest to longest using reverse "indicator/link" pointers (O(n) for constant σ).
- **McCreight's algorithm** — inserts suffixes longest-to-shortest, simpler than Weiner, using suffix links and head/tail decomposition (O(n)).
- **Farach's algorithm** — linear-time for integer alphabets via odd/even tree construction and merge; theoretical landmark removing the σ dependence (O(n)).
- **Suffix tree from suffix array + LCP** — bottom-up construction by processing SA with LCP using a stack (Cartesian-tree style) (O(n)).

#### Suffix-Tree Applications
- **Substring search** — match the pattern down from the root; existence in O(m), all occurrences via subtree leaf enumeration (O(m + occ)).
- **Number of distinct substrings** — sum of edge-label lengths over all edges (O(n) after build).
- **Longest repeated substring** — deepest internal node by string-depth (≥ 2 leaves below) (O(n)).
- **Longest common substring of two strings** — deepest internal node whose subtree contains leaves from both colors in the generalized tree (O(n)).
- **Longest common substring of k strings** — deepest node whose subtree covers all k colors; solved with a sliding/color-count DFS (O(kn) or O(n·k)).
- **Matching statistics** — for each position of one string, longest match into another's tree using suffix links (O(n)).
- **Lowest common ancestor → LCP of suffixes** — LCP(i,j) equals string-depth of LCA of the two leaves (O(1) after O(n) LCA preprocessing).
- **Maximal / supermaximal repeats, tandem repeats** — characterized by branching internal nodes and left-diverse conditions (O(n) to O(n log n)).
- **Palindrome detection via generalized tree** — combine tree of `s` and reverse(`s`) with LCA to test/extend palindromes (O(1) per query after build).

### Suffix Arrays

#### Construction
- **Suffix array (definition)** — sorted array of starting indices of all suffixes in lexicographic order; the rank array is its inverse (space O(n)).
- **Naïve sort** — sort suffixes by direct comparison (O(n² log n), trivial baseline).
- **Prefix-doubling, simple** — sort by first 2ᵏ characters, doubling k each round using pairs of previous ranks (O(n log² n) with comparison sort).
- **Prefix-doubling with radix/counting sort** — replace comparison sort by two-pass counting sort on rank pairs (O(n log n)).
- **DC3 / skew algorithm** — recursive linear construction sorting suffixes ≡1,2 mod 3, then merging with ≡0 suffixes (O(n)).
- **SA-IS (induced sorting)** — classify positions as S/L, find LMS substrings, recursively sort a reduced string, then induce full order in two passes (O(n), small constant; current practical standard).
- **SA-DS / induced-sort variants** — alternative induced-copying schemes trading memory for speed (O(n)).
- **Larsson–Sadakane** — refinement of doubling with O(n log n) using ternary-split quicksort on ranks.
- **Online / incremental suffix array** — maintain SA under append (e.g., via dynamic structures); generally heavier than offline (varies).

#### LCP Array & RMQ
- **LCP array** — `lcp[i]` = longest common prefix of `sa[i-1]` and `sa[i]`; encodes branching depth between adjacent sorted suffixes (space O(n)).
- **Kasai's algorithm** — compute LCP in linear time by scanning suffixes in text order and reusing the previous LCP minus one (O(n)).
- **LCP between arbitrary suffixes** — LCP(i,j) = min over the LCP interval = RMQ on the LCP array between their ranks (O(1) per query after preprocessing).
- **Sparse table on LCP (LCP-RMQ)** — O(n log n) build, O(1) range-minimum for arbitrary suffix-pair LCP (the standard "suffix array + sparse table" combo).
- **Permuted LCP (PLCP) & Φ array** — text-order LCP enabling space-light Kasai-style computation and BWT-derived LCP (O(n)).
- **LCP from BWT** — compute LCP in compressed/external settings directly from the Burrows–Wheeler transform (O(n) time, near in-place).
- **Cartesian tree / suffix-tree topology from LCP** — the LCP array is the Cartesian tree skeleton of the suffix tree (O(n) bridge between SA and ST).

#### LCP-Interval Tree & Enhanced Suffix Array
- **LCP-interval tree** — implicit suffix-tree structure over (SA, LCP) using ℓ-intervals; simulates suffix-tree traversal without pointers (O(n)).
- **Child table / NSV-PSV arrays** — next/previous smaller value tables giving constant-time child navigation in the LCP-interval tree (O(n)).
- **Enhanced suffix array (ESA)** — SA + LCP + child table (+ suffix links) replacing suffix trees for most queries with less memory (build O(n)).

#### Suffix-Array Applications
- **Pattern matching / counting** — binary search the SA for the pattern's range; counting = range width, all positions = the range (O(m log n), or O(m + log n) with LCP).
- **Number of distinct substrings** — n(n+1)/2 minus sum of LCP values (O(n) after LCP).
- **Longest repeated substring** — maximum LCP value and its position (O(n)).
- **Longest common substring of two strings** — concatenate with a separator, scan adjacent SA entries from different sources maximizing LCP (O(n)).
- **Longest common substring of k strings** — sliding window over SA requiring all k colors present, minimizing LCP-RMQ in window (O(n log n) / O(nk)).
- **k-th lexicographically smallest distinct substring** — walk SA with cumulative (suffix length − LCP) contributions to locate the k-th substring (O(n) scan + O(answer) output).
- **Comparing two substrings of s** — order/equality of `s[a..]` vs `s[c..]` prefixes via rank arrays and LCP-RMQ (O(1) per query after build).
- **Lexicographically minimal rotation** — build SA of `s+s` (or use Booth's algorithm directly) (O(n)).
- **Suffix-array-based string sorting & multi-pattern queries** — batch pattern locating, document retrieval over generalized arrays.

### Suffix Automaton (SAM) & DAWG

#### Structure & Theory
- **Suffix automaton (SAM)** — minimal deterministic automaton accepting exactly all suffixes (and thus all substrings via any start) of `s`; ≤ 2n−1 states, ≤ 3n−4 transitions (space O(n·σ) or O(n) with maps).
- **Endpos equivalence classes** — states correspond to sets of substrings sharing the same set of ending positions; the foundational invariant.
- **Suffix link tree (link/parent tree)** — `link(v)` points to the state of the longest proper suffix in a different endpos class; forms a tree over states with deep structural meaning.
- **`len` and `link` invariants** — each state stores longest length `len(v)`; it represents substrings of length (len(link(v))+1 … len(v)).
- **DAWG (Directed Acyclic Word Graph)** — the transition DAG of the SAM; equivalently the minimized suffix DAG; counts/paths give substring statistics.
- **CDAWG (compacted DAWG)** — edges with single child merged; the minimal automaton with edge labels, dual to the suffix tree of the reverse string (space O(n)).
- **Relationship: SAM ↔ suffix tree** — the suffix link tree of SAM(s) is isomorphic to the suffix tree of reverse(s) (key duality used to transfer algorithms).

#### Construction
- **Online incremental construction** — extend by one character, creating a new state, cloning a state when a transition's `len` mismatch occurs, and rewiring suffix links (amortized O(n) for constant σ; O(n log σ) with balanced maps).
- **Cloning rule** — when following suffix links a transition to `q` exists, clone `q` (copy transitions/link, set len = len(p)+1) and redirect the relevant transitions (the crux of correctness).
- **Generalized SAM (multiple strings)** — build one automaton over a set: insert each string resetting to the root, with careful cloning when a transition already exists (build O(Σ|sᵢ|)); also built from a trie via BFS.
- **Generalized SAM from a trie** — feed trie nodes in BFS order so shared prefixes are processed once (O(total trie size)).

#### SAM Applications
- **Substring membership / search** — follow transitions for the pattern from the initial state (O(m)).
- **Number of distinct substrings** — sum over states of (len(v) − len(link(v))), or count distinct paths in the DAG (O(n)).
- **Total length of all distinct substrings** — accumulate length sums over states using `len` ranges (O(n)).
- **Number of occurrences of each substring** — endpos-set sizes computed by summing terminal/clone contributions up the suffix-link tree (O(n) preprocessing, O(m) per query).
- **First occurrence position of a pattern** — store `firstpos` per state (set at creation/clone) and read after matching (O(m)).
- **All occurrence positions** — collect endpos sets by traversing the suffix-link subtree of the matched state (O(answer)) or via persistent merging.
- **k-th lexicographically smallest substring** — precompute the number of paths from each state, then descend choosing transitions in sorted order (O(answer·σ) or O(answer log σ)).
- **Smallest cyclic shift / minimal rotation** — build SAM of `s+s` and follow the smallest transition n times (O(n)).
- **Longest common substring of two strings** — run one string through the other's SAM tracking current match length, resetting via suffix links on mismatch (O(n + m)).
- **Longest common substring of k strings via generalized SAM** — for each state track the min over strings of best match length (color propagation up the link tree) (O(total)).
- **Counting distinct substrings across a string set** — distinct-substring formula on the generalized SAM (O(total)).
- **Matching statistics on SAM** — analogous to suffix-tree matching statistics using transitions + suffix links (O(m)).
- **Endpos-set queries with mergeable structures** — attach a small-to-large set / segment-tree-merge / persistent structure per link-tree node for "occurrences in range" queries (O((n+q) log n)).

### Palindromic Structures

#### Palindromic Tree (Eertree)
- **Palindromic tree / eertree** — automaton of all distinct palindromic substrings; ≤ n+2 nodes (two roots: lengths −1 and 0) since a string has ≤ n distinct palindromes (build O(n·σ) or O(n log σ)).
- **Suffix-palindrome links** — each node links to its longest proper palindromic suffix; the traversal that yields linear construction.
- **Online incremental build** — append a character, walk suffix-palindrome links to find the longest palindrome that can be extended, add at most one new node per step (amortized O(1) per char).
- **Two roots (imaginary −1 / empty 0)** — sentinel roots enabling uniform extension of odd- and even-length palindromes.
- **Quick / series links (diff links)** — link to the next palindrome of a different arithmetic progression of suffix-palindrome lengths, for O(n log n) palindromic-factorization DP.

#### Palindromic Applications
- **Count distinct palindromic substrings** — equals (#nodes − 2) of the eertree (O(n)).
- **Count total palindromic substrings (with multiplicity)** — sum of occurrence counts propagated down suffix links (O(n)).
- **Number of palindromes ending/starting at each position** — series-link "palindromic series" decomposition gives O(log n) groups per position (O(n log n) total).
- **Minimum palindromic factorization / palindrome partitioning** — DP over diff/series links splitting a string into fewest palindromes (O(n log n)).
- **Longest palindromic substring (Manacher)** — center-expansion with mirror reuse for the single longest palindrome (O(n)); (see String Matching & Single-String).
- **Palindromic factorization counts & even-palindrome variants** — DP atop eertree series links (O(n log n)).

### Burrows–Wheeler Transform & Compressed Indexes

#### BWT Core
- **Burrows–Wheeler transform (BWT)** — last column of the sorted matrix of all rotations of `s$`; clusters equal characters into runs, making text more compressible (compute O(n) via suffix array: `bwt[i]=s[sa[i]−1]`).
- **BWT inversion (LF-mapping)** — reconstruct the original string by repeatedly applying the Last-to-First column mapping (O(n)).
- **LF-mapping & rank/select duality** — `LF(i) = C[c] + rank_c(i)` ties BWT navigation to character-rank queries (O(1)/O(log σ) per step).
- **C array (cumulative counts)** — count of characters lexicographically smaller than each symbol; half of LF-mapping (O(σ) space).
- **Move-to-front + run-length + entropy coding (bzip2 pipeline)** — classic compression stack built on BWT output (linear passes).
- **Bijective BWT & sentinel-free variants** — Lyndon-factorization-based BWT avoiding the explicit `$` (O(n)).

#### FM-Index & Self-Indexes
- **FM-index** — self-index combining BWT + C array + rank structure to search a pattern without the original text (count in O(m), locate with sampled SA).
- **Backward search** — process the pattern right-to-left, shrinking the SA interval [sp,ep) using rank queries and the C array (O(m) for count).
- **Suffix-array sampling for locate** — store SA values at sampled positions; recover unsampled ones by walking LF until a sample is hit (locate O(occ · sample-rate)).
- **Wavelet tree** — balanced binary partition of the alphabet giving rank/select over a sequence in O(log σ); standard backbone for FM-index rank queries (space n·H₀ + o(n)).
- **Wavelet matrix / Huffman-shaped wavelet tree** — alphabet-skewed or layout-optimized wavelet variants reducing space/time constants (O(log σ) queries).
- **RRR / succinct bit-vector rank-select** — compressed bit vectors with O(1) rank/select underpinning wavelet trees and FM-indexes (o(n) extra space).
- **Compressed suffix array (CSA)** — represent SA via the Ψ function in compressed space with O(log n) access (space O(n·Hₖ)); sibling of the FM-index.
- **Ψ (psi) function** — successor mapping in suffix order, inverse-style counterpart to LF used by CSAs (O(1)/O(log) access).
- **r-index** — run-length BWT index with locate time scaling in the number of BWT runs r, ideal for highly repetitive text (space O(r), locate O(occ + log) typical).
- **FM-index variants for k strings / collections** — multi-string BWT and document-counting extensions for document retrieval.

### Cross-References & Related String Tooling
- **Aho–Corasick automaton** — multi-pattern matching trie augmented with failure (suffix) links and dictionary links (build O(Σ|pᵢ|·σ), match O(text + matches)) (see String Matching: Multi-Pattern).
- **KMP / prefix function & Z-function** — single-pattern automata and border arrays; the prefix-function DFA is a degenerate "automaton on one pattern" (see String Matching: Single-Pattern).
- **Suffix-link automaton view of KMP** — KMP failure links are the suffix-link structure of the pattern's trie path (conceptual bridge to Aho–Corasick).
- **String hashing (polynomial / double hashing)** — O(1) substring comparison alternative to suffix structures for LCP/equality (see Hashing & Randomized String Methods).
- **Rabin–Karp rolling hash** — hashing-based substring search complementing automaton methods (see String Matching).
- **Lyndon words & Duval's algorithm** — Lyndon factorization underlies bijective BWT and minimal-rotation results (see Combinatorics on Words / String Periodicity).
- **Manacher's algorithm** — linear longest-palindrome; companion to the eertree (see String Matching: Single-String).
- **Heavy-path / Euler-tour on suffix-link trees** — apply tree techniques (LCA, small-to-large, segment-tree merge, auxiliary trees) to SAM/suffix-tree link trees for offline substring queries (see Trees & LCA).
- **Persistent segment tree over SA / DAWG** — range-restricted occurrence and distinct-substring queries by versioning along positions (see Persistent Data Structures).

<sub>[↑ Back to top](#table-of-contents)</sub>

---

# 🔢 Part — Mathematics

## 22. Number Theory & Modular Arithmetic

### Foundations: Divisibility, Primes & Arithmetic Functions

#### Divisibility & Basic Number Properties
- **Divisibility relation** — `a | b` iff `b = a*k`; transitive, respects linear combinations (`a|b, a|c ⇒ a|(bx+cy)`).
- **Division algorithm** — unique quotient/remainder with `0 ≤ r < |b|`; foundation of GCD and modular reduction (O(1) per division).
- **Fundamental theorem of arithmetic** — every integer > 1 has a unique prime factorization up to ordering; basis of multiplicative reasoning.
- **Bezout's identity** — `gcd(a,b)` is the smallest positive integer expressible as `ax+by`; constructively yielded by extended Euclid.
- **Coprimality** — `gcd(a,b)=1`; multiplicative independence used throughout CRT and totient.
- **Divisibility rules** — base-dependent shortcuts (mod 3/9 digit sum, mod 11 alternating sum); special cases of `b ≡ r (mod m)` weighting.
- **Number of digits / digit DP links** — `⌊log_b(n)⌋+1` digits; bridges to combinatorics (see Combinatorics & Counting).
- **Integer roots** — exact `⌊n^(1/k)⌋` via binary search + careful overflow check or Newton iteration (O(log n) with bignum care).
- **Perfect-power detection** — test whether `n = a^k` for some `k ≥ 2` by checking each prime exponent up to `log₂n` (O(log²n · root cost)).

#### Prime Numbers & Distribution
- **Primality definition** — exactly two positive divisors; 1 is a unit, not prime.
- **Prime counting function π(n)** — `π(n) ~ n/ln n` (Prime Number Theorem); Meissel-Mertens / Meissel-Lehmer count π(n) sublinearly (≈O(n^(2/3))).
- **Density & gaps** — Bertrand's postulate (a prime in `(n,2n]`); useful for bounding searches.
- **Primorial** — product of first k primes; grows like `e^((1+o(1))p_k)`.
- **Mersenne / Fermat primes** — special forms `2^p−1`, `2^(2^k)+1`; rarely needed but appear in NTT-modulus selection.
- **Mertens' theorems / harmonic prime sums** — `Σ_{p≤n} 1/p ~ ln ln n`; used to bound sieve-style complexities.

#### Sieves (Prime Generation & Precomputation)
- **Sieve of Eratosthenes** — mark composites by crossing multiples; classic prime list up to n (O(n log log n) time, O(n) space).
- **Optimized Eratosthenes** — start crossing at `i*i`, odd-only / wheel factorization (mod 6 or mod 30) to cut constant factor.
- **Linear (Euler) sieve** — each composite struck exactly once via its smallest prime factor (O(n) time, O(n) space).
- **Smallest-prime-factor (SPF) sieve** — store least prime factor per index for O(log n) factorization of any value ≤ n.
- **Segmented sieve** — sieve `[L,R]` using primes up to `√R`; for huge ranges with small window (O((R−L) log log R + √R) time, O(√R+window) space).
- **Bit-packed / wheel sieve** — store odds only in bitset, mod-30 wheel; large constant-factor and memory savings.
- **Sieve of Sundaram / Atkin** — alternative composite-marking schemes; Atkin is O(n/log log n) but high constant, rarely worth it.
- **Block sieving for cache** — process the sieve in L2-sized blocks to avoid cache misses on large n.
- **Lucy_Hedgehog prime-sum sieve** — compute `Σ_{p≤n} p^k` (and π over `⌊n/i⌋` values) via DP over divisor blocks (O(n^(3/4)) time).

#### Integer Factorization
- **Trial division** — test divisors up to `√n` (only primes if a sieve is available) (O(√n) time, O(1) space).
- **SPF-sieve factorization** — repeatedly divide by stored smallest prime factor (O(log n) per query after O(n) precompute).
- **Fermat's factorization** — write `n = a²−b²`; fast when factors are close to `√n` (O(|p−q|) ish).
- **Pollard's rho** — cycle-finding on `x→x²+c mod n`; expected O(n^(1/4)) per factor.
- **Pollard's rho (Brent's improvement)** — Brent cycle detection + batched gcd to slash gcd calls; large constant speedup over Floyd variant.
- **Pollard's p−1 / Williams p+1** — succeed when `p−1` (resp. `p+1`) is smooth; situational.
- **Full factorization driver** — combine Miller-Rabin primality test with Pollard-rho recursion, peel small primes first (expected O(n^(1/4) polylog)).
- **Dixon's method / quadratic sieve / GNFS** — subexponential factorization via smooth relations + linear algebra; far beyond contest scale, listed for completeness.

#### Primality Testing
- **Trial-division test** — deterministic up to `√n`; only for small n.
- **Fermat probable-prime test** — `a^(n−1) ≡ 1`; fooled by Carmichael numbers, so unreliable alone.
- **Miller-Rabin (probabilistic)** — strong probable-prime test; error ≤ 4^(−k) for k random bases (O(k log³n) or O(k log²n) with fast mult).
- **Miller-Rabin (deterministic bases)** — fixed witness sets prove primality: {2,3,5,7,11,13,17,19,23,29,31,37} covers all 64-bit n; {2,3,5,7,11,13,17,19,23,29,31,37} ⇒ n < 3.3·10²⁴.
- **Carmichael numbers & strong pseudoprimes** — composites passing Fermat (resp. some Miller-Rabin bases); motivate witness choice.
- **Baillie-PSW** — combine a base-2 strong test with a Lucas test; no known counterexample, very fast.
- **AKS primality test** — first deterministic polynomial-time test (Õ(log⁶n)); theoretical, never used in contests.

#### Montgomery & Barrett Reduction (Fast Modular Multiplication)
- **Barrett reduction** — replace `mod` with multiply + shift using a precomputed reciprocal; speeds repeated mod by a fixed modulus.
- **Montgomery multiplication** — work in Montgomery form to avoid division entirely; big speedup for Miller-Rabin / Pollard on 64-bit (O(1) per mul after setup).
- **128-bit / double-width mulmod** — overflow-safe `a*b mod m` via wide product or binary mulmod fallback (O(log m) without wide ints).

### GCD, LCM & the Euclidean Algorithm

- **Euclidean algorithm** — `gcd(a,b)=gcd(b, a mod b)`; (O(log(min(a,b))) divisions, worst case consecutive Fibonacci numbers).
- **Binary (Stein's) GCD** — uses subtraction and powers of two, no division; faster on hardware without fast div (O(log²(max)) bit-ops).
- **LCM** — `lcm(a,b)=a/gcd(a,b)·b`; divide first to avoid overflow (O(log) via the gcd).
- **GCD/LCM of arrays** — fold pairwise; gcd-of-array is O(n log V).
- **Extended Euclidean algorithm** — returns `(g,x,y)` with `ax+by=g`; engine for inverses and linear congruences (O(log min(a,b))).
- **General solution of `ax+by=c`** — solvable iff `gcd(a,b)|c`; parametrize all solutions `x=x₀+k·b/g`, `y=y₀−k·a/g`.
- **Minimal nonnegative / bounded solutions** — adjust the free parameter to fit constraints; common contest twist.
- **GCD on segments (range gcd)** — sparse table / segment tree for static or dynamic range-gcd queries (see Range Queries & Segment Trees).
- **Fibonacci-style worst case** — consecutive Fibonacci inputs maximize Euclid steps (used in complexity proofs and Lamé's theorem).

### Modular Arithmetic Core

#### Properties & Reduction
- **Congruence and residue classes** — `a ≡ b (mod m)` is an equivalence; arithmetic respects +, −, × (but not ÷ in general).
- **Ring `Z/mZ` structure** — units are residues coprime to m; multiplicative group has order φ(m).
- **Modular reduction & normalization** — keep representatives in `[0,m)`; careful handling of negative dividends and subtraction underflow.
- **Modular addition/subtraction/multiplication** — reduce after each op; use 128-bit or mulmod to dodge overflow.
- **Cancellation law** — `ac ≡ bc (mod m) ⇒ a ≡ b (mod m/gcd(c,m))`; explains when division is valid.

#### Exponentiation
- **Binary (fast) modular exponentiation** — square-and-multiply over the exponent bits (O(log e) multiplications).
- **Iterative vs recursive exponentiation** — same complexity; iterative avoids stack and is overflow-friendly.
- **Modular exponentiation with huge exponents** — reduce exponent mod `φ(m)` (Euler) or use the generalized (lifting) Euler form for non-coprime bases.
- **Fixed-base / fixed-exponent precomputation** — windowed / k-ary exponentiation and power tables to amortize many powers.
- **Matrix exponentiation** — fast power of a transition matrix for linear recurrences (O(k³ log n)) (see Dynamic Programming / Linear Algebra).
- **Tetration / power towers mod m** — recursive Euler-theorem descent on `a^a^a… mod m` using the generalized Euler theorem (O(log m · iterated φ depth)).

#### Modular Inverse
- **Inverse via Fermat's little theorem** — `a^(m−2) mod m` for prime m (O(log m)).
- **Inverse via extended Euclid** — works for any modulus where `gcd(a,m)=1` (O(log m)); the general-purpose method.
- **Inverse via Euler's theorem** — `a^(φ(m)−1)` for composite coprime m (needs φ(m)).
- **Batch inverse of 1..n mod p** — recurrence `inv[i] = −(p/i)·inv[p mod i] mod p` (O(n)).
- **Inverse of an arbitrary array (prefix-product trick)** — one inversion + linear pass to invert n elements (O(n + log p)).
- **Existence condition** — inverse exists iff `gcd(a,m)=1`; otherwise solve as a linear congruence instead.
- **Division modulo composite** — when inverse doesn't exist, reduce the congruence or split via CRT.

### Linear Congruences & Chinese Remainder Theorem

- **Single linear congruence `ax ≡ b (mod m)`** — solvable iff `g=gcd(a,m)` divides b; yields exactly g solutions mod m (O(log m) via extended Euclid).
- **Chinese Remainder Theorem (coprime moduli)** — unique solution mod ∏mᵢ; reconstruct via CRT coefficients (O(k log M)).
- **CRT for two congruences (merge form)** — iteratively merge pairs `(r₁,m₁),(r₂,m₂)`; clean for streaming many constraints.
- **General CRT (non-coprime moduli)** — solvable iff residues agree on every pairwise gcd; merged modulus is `lcm` (O(k log M)).
- **Garner's algorithm** — represent the answer in mixed-radix over the moduli; recover huge results / do bignum-free CRT (O(k²)).
- **CRT for big-integer-free computation** — carry computations modulo several primes then reconstruct, avoiding bignums (used in NTT, polynomial mult).
- **Simultaneous incongruence / no-solution detection** — verify consistency on gcds before merging.

### Multiplicative Functions & Möbius

#### Euler's Totient
- **Euler totient φ(n)** — count of integers in `[1,n]` coprime to n; `φ(n)=n∏(1−1/p)` (O(√n) single value).
- **Totient via factorization / SPF** — compute from prime factors in O(log n) after a sieve.
- **Totient sieve** — φ for all values ≤ n via linear or Eratosthenes-style sieve (O(n log log n) or O(n)).
- **Totient summatory function Φ(n)=Σφ(i)** — block/divisor-decomposition or Möbius; sublinear with memoization (O(n^(2/3))).
- **Properties of φ** — multiplicative for coprimes; `Σ_{d|n} φ(d)=n`; `φ(p^k)=p^k−p^(k−1)`.

#### Euler & Fermat Theorems
- **Fermat's little theorem** — `a^(p−1) ≡ 1 (mod p)` for prime p, `gcd(a,p)=1`.
- **Euler's theorem** — `a^φ(m) ≡ 1 (mod m)` for `gcd(a,m)=1`; generalizes Fermat.
- **Generalized (lifting) Euler theorem** — for any a and `e ≥ log₂m`, `a^e ≡ a^(e mod φ(m) + φ(m)) (mod m)`; handles non-coprime bases & towers.
- **Carmichael function λ(n)** — smallest universal exponent with `a^λ(n) ≡ 1`; divides φ(n), sometimes the tighter exponent.

#### Möbius & Inversion
- **Möbius function μ(n)** — `0` if not squarefree, else `(−1)^(#prime factors)` (O(√n) single, O(n) sieved).
- **Möbius sieve** — compute μ for all ≤ n alongside a linear sieve.
- **Möbius inversion formula** — if `g(n)=Σ_{d|n} f(d)` then `f(n)=Σ_{d|n} μ(d) g(n/d)`; invert summatory relations.
- **Möbius inversion (divisor & multiple forms)** — both `d|n` and `n|d` variants for counting coprime pairs / gcd sums.
- **Coprime-counting via Möbius** — count pairs with `gcd=1` or `gcd=k` by summing μ over divisors (classic `Σ gcd(i,j)` problems).
- **Mertens function M(n)=Σμ(i)** — computed via divisor blocks / Dirichlet hyperbola (sublinear, O(n^(2/3)) with memoization).

#### Dirichlet Convolution & General Multiplicative Functions
- **Dirichlet convolution** — `(f*g)(n)=Σ_{d|n} f(d) g(n/d)`; the algebra underlying multiplicative-function identities.
- **Identity / unit functions** — `ε`, `1`, `Id`, `Id_k` as convolution building blocks (`1*1=d`, `μ*1=ε`, `φ*1=Id`).
- **Multiplicative function definition & extension** — fully determined by values on prime powers; evaluate fast from factorization.
- **Dirichlet inverse** — every f with `f(1)≠0` has an inverse under convolution; μ is the inverse of the constant 1.
- **Linear sieve for multiplicative functions** — compute any multiplicative f for all ≤ n by handling `p` and `p·prime` cases (O(n)).
- **Dirichlet prefix-sum (Dirichlet hyperbola method)** — evaluate `Σ_{n≤N}(f*g)(n)` in O(√N) using the hyperbola split.
- **Powerful number trick** — express f as `g*h` with h supported on powerful numbers to sum f(n) sublinearly (O(√N) after a prefix of g).
- **Min_25 sieve / extended Eratosthenes** — sublinear prefix sums of multiplicative functions and prime-indexed sums (O(n^(3/4)/log n) or O(n^(2/3))); advanced.
- **Lucy_Hedgehog method** — DP over `⌊n/i⌋` blocks for prime-counting / prime-power sums (O(n^(3/4)) time).

#### Divisor Functions
- **Number of divisors τ(n)=d(n)** — `∏(eᵢ+1)` from factorization (O(√n) single, sieve for all ≤ n).
- **Sum of divisors σ(n)** — `∏ (p^(e+1)−1)/(p−1)`; and general `σ_k` (O(√n) single).
- **Divisor-function sieves** — `d` and `σ` for all ≤ n via `Σ_{multiples}` or linear sieve (O(n log n) naive, O(n) linear).
- **Summatory divisor function D(n)=Σd(i)** — Dirichlet's hyperbola method gives O(√n).
- **Enumerate all divisors** — from prime factorization, generate the divisor lattice (O(τ(n)) after factorization).
- **Highly composite / antichain divisor bounds** — `d(n)` grows subpolynomially; useful for bounding divisor-enumeration loops.

### Orders, Primitive Roots & Discrete Problems

- **Multiplicative order of a mod m** — smallest k with `a^k ≡ 1`; divides φ(m), found by testing divisors of φ(m) (O(√φ + d(φ)·log)).
- **Primitive root** — generator of the multiplicative group; exists iff m ∈ {1,2,4,p^k,2p^k}.
- **Finding a primitive root** — test candidates g by checking `g^(φ(m)/q) ≠ 1` for each prime q | φ(m) (O(d·log·√) per candidate, few candidates).
- **Counting / enumerating primitive roots** — exactly `φ(φ(m))` of them; generate all from one via coprime exponents.
- **Index / discrete-log representation** — using a primitive root to linearize multiplication into addition of indices.
- **Baby-step giant-step (BSGS)** — solve `a^x ≡ b (mod m)` in O(√m) time and space via meet-in-the-middle.
- **BSGS for non-coprime base** — strip factors of `gcd(a,m)` first, then run standard BSGS (handles general modulus).
- **Pohlig-Hellman** — discrete log when group order is smooth: solve per prime-power factor + CRT (O(Σ eᵢ(log n + √pᵢ))).
- **Pollard's rho for discrete log** — birthday-paradox random walk solving DLP in O(√n) space-light, vs BSGS's O(√n) memory.
- **Index calculus method** — subexponential DLP for large prime moduli: collect smooth relations over a factor base, solve the linear system mod group order, then descend the target (L_p[1/2] / GNFS-style L_p[1/3]); beyond contest scale, listed for completeness.
- **Discrete root** — solve `x^k ≡ a (mod m)`: via primitive root reduce to a linear congruence in indices, then enumerate all roots.
- **Number of solutions to `x^k ≡ a`** — `gcd(k,φ)` solutions when a is a k-th power residue, else 0.

### Quadratic Residues & Modular Square Roots

- **Quadratic residue definition** — a is a QR mod p if `x²≡a` solvable; exactly `(p−1)/2` nonzero QRs mod odd prime p.
- **Count of quadratic residues** — odd prime p has `(p−1)/2` QRs and `(p−1)/2` non-residues; for `Z/mZ` count via multiplicativity over prime-power factors.
- **Euler's criterion** — `a^((p−1)/2) ≡ ±1 (mod p)` tells QR (+1) vs non-residue (−1) (O(log p)).
- **Legendre symbol (a/p)** — −1/0/+1 residue indicator for odd prime p; computed via Euler's criterion.
- **Jacobi symbol** — generalization to odd composite n; computed by quadratic-reciprocity flips without factoring (O(log²n)).
- **Quadratic reciprocity & supplements** — reciprocity law plus rules for `(−1/p)`, `(2/p)`; speeds Jacobi computation.
- **Gauss sums (basics)** — character sum `Σ_x ω_p^(a x²)`; `|g(1;p)|=√p` (value `√p` if `p≡1`, `i√p` if `p≡3 mod 4`); count solutions of `x²≡a` and prove reciprocity.
- **Tonelli-Shanks algorithm** — modular square root mod prime p (O(log²p) expected; O(log p) when `p≡3 mod 4`).
- **Simple-case square roots** — for `p≡3 mod 4`, root is `a^((p+1)/4)`; analogous closed forms for `p≡5 mod 8`.
- **Cipolla's algorithm** — modular square root via arithmetic in `F_p[√(t²−a)]` (O(log p) multiplications), alternative to Tonelli-Shanks.
- **Square roots modulo prime powers / composites** — Hensel lifting from mod p to mod p^k, then CRT across prime powers.
- **Hensel's lemma (lifting roots)** — lift a simple root of a polynomial from mod p to mod p^k (O(k log p) per lift).

### Binomial Coefficients & Factorials Modulo

- **Factorials & inverse factorials mod p** — precompute to answer `C(n,k) mod p` in O(1) after O(n) setup.
- **Lucas' theorem** — `C(n,k) mod p` via base-p digits, multiplying small binomials (O(log_p n) per query for prime p).
- **Factorial modulo prime power (Granville / Wilson-style)** — compute `n! mod p^k` with the factors of p removed (O(p^k + log n) ish).
- **Generalized Lucas (prime-power modulus)** — combine factorial-mod-p^k with Legendre's exponent count and CRT for `C(n,k) mod p^k`.
- **C(n,k) mod arbitrary m** — factor m, apply generalized Lucas per prime power, recombine via CRT.
- **Wilson's theorem** — `(p−1)! ≡ −1 (mod p)` iff p prime; theoretical primality test and identity tool.
- **Vandermonde / hockey-stick identities mod p** — combinatorial identities reused for fast counting (see Combinatorics & Counting).

### Prime-Power Exponents & p-adic Tools

- **Legendre's formula** — exponent of prime p in `n!` is `Σ ⌊n/p^i⌋ = (n − s_p(n))/(p−1)` (O(log_p n)).
- **Kummer's theorem** — exponent of p in `C(n,k)` equals the number of carries adding k and n−k in base p.
- **p-adic valuation v_p(n)** — largest power of p dividing n; building block for the above (O(log_p n)).
- **Lifting the exponent (LTE) lemma** — closed form for `v_p(a^n ± b^n)`; handles competition divisibility problems directly.
- **Trailing-zero / factorial-zero counts** — count factors of 10 in n! via min of v_2,v_5 (just v_5) using Legendre's formula.

### Floor/Ceil Arithmetic & Floor Sums

- **Floor & ceiling identities** — `⌈a/b⌉=⌊(a+b−1)/b⌋`, nested-floor `⌊⌊n/a⌋/b⌋=⌊n/(ab)⌋`; safe integer manipulation.
- **Divisor-block decomposition (`⌊n/i⌋` blocking)** — only O(√n) distinct values of `⌊n/i⌋`; enumerate maximal equal-value blocks (O(√n)).
- **Sum `Σ⌊n/i⌋` (divisor summatory)** — via blocking or hyperbola in O(√n).
- **Floor-sum `Σ_{i=0}^{n−1} ⌊(a·i+b)/m⌋`** — Euclidean-like / Stern-Brocot descent swapping the roles of slope and modulus (O(log(max))).
- **Stern-Brocot / continued-fraction modular-inequality solving** — find smallest `x>0` with `(a·x mod m)` in a target range, or count lattice points under a line, via mediant/CF descent on the line's slope (O(log) per step).
- **General lattice-point / floor-sum recursion** — count lattice points under a line `(a*i+b)/m`; extends to `Σ i·⌊…⌋` and `Σ ⌊…⌋²`.
- **Counting integers in [1,n] divisible by any of a set** — inclusion-exclusion over lcms (see Combinatorics & Counting).

### Continued Fractions, Farey & the Stern-Brocot Tree

- **Continued fraction expansion** — `[a₀;a₁,a₂,…]` from the Euclidean algorithm; finite for rationals, periodic for quadratic irrationals (O(log) for rationals).
- **Convergents** — best rational approximations `p_k/q_k` via the recurrence `p_k=a_k p_{k−1}+p_{k−2}`; optimal denominators.
- **Best rational approximation under a bound** — find closest fraction with denominator ≤ N using convergents/semiconvergents.
- **Stern-Brocot tree** — binary tree of all positive reduced fractions in order; navigate by L/R using mediants (O(log) depth per query).
- **Stern-Brocot descent / fraction binary search** — locate or binary-search a fraction (e.g. for modular inequalities) by run-length-compressing L/R moves with the Euclidean coefficients (O(log) per turn).
- **Mediant property** — `(a+c)/(b+d)` lies strictly between `a/b` and `c/d`; powering binary search over fractions.
- **Farey sequence F_n** — ordered reduced fractions in [0,1] with denominator ≤ n; neighbors satisfy `bc−ad=1`.
- **Farey neighbor / next-term recurrence** — generate the next Farey fraction in O(1) from two previous terms.
- **Counting Farey terms** — `1+Σφ(i)`; relates Farey length to totient summation.

### Diophantine Equations & Special Forms

- **Linear Diophantine `ax+by=c`** — full solution set via extended Euclid + parametrization (O(log)).
- **Multi-variable linear Diophantine** — reduce sequentially using pairwise gcds; solvable iff `gcd(all)|c`.
- **Pell's equation `x²−D y²=1`** — fundamental solution from the continued-fraction expansion of `√D`; all solutions by powering (O(period)).
- **Negative Pell `x²−D y²=−1`** — solvable iff the CF period of `√D` is odd; derive from fundamental unit.
- **Generalized Pell `x²−D y²=N`** — find a base set of solutions, then multiply by the fundamental unit to generate families.
- **Pythagorean triples** — primitive triples from `(m²−n²,2mn,m²+n²)` with `m>n`, coprime, opposite parity; scale for all triples.
- **Sum of two squares** — `n` expressible iff every prime `≡3 mod 4` appears to an even power; constructive via Gaussian integers.
- **Sum of four squares (Lagrange)** — every nonnegative integer is a sum of four squares; constructive descent exists.
- **Frobenius / Chicken McNugget (general coin problem)** — largest non-representable amount with coin set; general case NP-hard, solved via residue DP / shortest-path "coin graph" mod the smallest coin (O(a₁ log a₁) Dijkstra).
- **Two-coin Frobenius closed form** — for coprime a,b the Frobenius number is `ab−a−b`, and exactly `(a−1)(b−1)/2` positive amounts are non-representable (O(1)).
- **Postage / numerical semigroup gaps** — count/list non-representable amounts (gaps, genus) via mod-a residue shortest paths.

### Gaussian & Algebraic Integers (Advanced, Mostly Theoretical)

- **Gaussian integers Z[i]** — unique factorization domain; norm `a²+b²`; used to characterize sums of two squares.
- **Gaussian-integer GCD** — Euclidean algorithm with complex rounding; factor primes `≡1 mod 4`.
- **Eisenstein integers Z[ω]** — for problems involving `x²+xy+y²`; cubic-residue analogues.
- **Quadratic field units & class number** — background for Pell and norm-form equations (theory-level reference).

### Numeral Systems & Representation Utilities

- **Base conversion** — convert integers/fractions between radices; Horner evaluation for value-from-digits (O(#digits)).
- **Balanced & negative bases / negabinary** — alternative representations occasionally exploited in constructive problems.
- **Factorial number system (factoradic)** — mixed-radix tied to permutation ranking (see Combinatorics & Counting).
- **Gray code** — reflected binary where successive values differ in one bit (see Bit Manipulation).
- **Arbitrary-precision (bignum) arithmetic** — schoolbook +,−,× plus Karatsuba/FFT multiplication for big integers (FFT mult O(n log n)) (see Polynomials & FFT/NTT).

### Cross-Domain Number-Theoretic Tools (Pointers)

- **Number-Theoretic Transform (NTT) & modular convolution** — FFT over a prime field for polynomial/big-integer multiplication (see Polynomials, FFT & NTT).
- **Multiplicative-function prefix sums via FFT** — convolution-based summation tricks (see Polynomials, FFT & NTT).
- **Modular linear algebra** — Gaussian elimination / matrix inverse over `Z_p`, determinant mod p (see Linear Algebra).
- **GCD-convolution & subset/divisor zeta-Möbius transforms** — fast `Σ over divisors/multiples` transforms (see Bitmask DP / Combinatorics).
- **Hashing with modular arithmetic** — polynomial rolling hashes rely on mod-p exponentiation and inverses (see Strings & Hashing).
- **Randomized number theory** — random bases/seeds for Miller-Rabin & Pollard; anti-hash and anti-rho mitigations (see Randomized Algorithms).

<sub>[↑ Back to top](#table-of-contents)</sub>

---

## 23. Combinatorics, Counting & Probability

### Fundamental Counting Principles

- **Sum rule (rule of addition)** — disjoint choice sets combine additively; total of mutually exclusive cases is the sum of their sizes.
- **Product rule (rule of multiplication)** — independent sequential choices multiply; |A×B| = |A|·|B|, generalizing to k stages.
- **Bijection principle** — counting one set by exhibiting a one-to-one correspondence with an easier-to-count set.
- **Double counting** — count the same quantity two ways and equate the results to derive identities (e.g. handshake lemma, Σdeg = 2E).
- **Pigeonhole principle** — n+1 items in n boxes force a repeated box; basis for existence arguments.
- **Generalized pigeonhole** — n items in k boxes force a box with ≥⌈n/k⌉ items; used for bounds and contradictions.
- **Pigeonhole with structure** — Erdős–Szekeres (monotone subsequence of length >√n), Dirichlet approximation, sum-of-prefixes mod n.
- **Complementary counting** — count the complement and subtract from the total when the complement is simpler.
- **Counting by symmetry / averaging** — exploit a transitive group action so each case is counted equally.
- **Casework partitioning** — partition the sample space into exhaustive disjoint cases, count each, then sum.

### Permutations and Combinations

- **Factorial n!** — number of orderings of n distinct objects (O(n) to tabulate, O(n) factorial table).
- **k-permutations P(n,k)=n!/(n−k)!** — ordered selections of k from n distinct items.
- **Combinations C(n,k)=n!/(k!(n−k)!)** — unordered selections; "n choose k" (O(k) per value or O(n) with tables).
- **Permutations with repetition (nᵏ)** — ordered selections from n types with replacement.
- **Combinations with repetition (multiset coefficient)** — C(n+k−1,k) ways to choose k from n types with repeats.
- **Multiset permutations (multinomial)** — n!/(n₁!n₂!⋯nₖ!) arrangements of a multiset with given multiplicities.
- **Circular permutations** — (n−1)! arrangements around a circle; n!/(2n) for a necklace with reflection.
- **Permutations avoiding adjacency / restricted positions** — via inclusion–exclusion or the rook-polynomial / permanent of a 0-1 board.
- **Lexicographic ranking / unranking of permutations** — factorial number system (Lehmer code) maps permutation ↔ index (O(n) or O(n log n)).
- **Next permutation / previous permutation** — in-place lexicographic successor in O(n) amortized.
- **Combination ranking / unranking (combinatorial number system)** — bijection between k-subsets and integers 0..C(n,k)−1.
- **Gray code enumeration** — order all subsets so consecutive differ by one element (O(2ⁿ) total, O(1) per step).
- **Permutation as product of cycles** — cycle decomposition; number of permutations of given cycle type.

### Binomial Coefficients

- **Pascal's triangle / recurrence** — C(n,k)=C(n−1,k−1)+C(n−1,k); tabulate all up to n in O(n²) time and space.
- **Factorial formula with modular inverses** — precompute factorials and inverse factorials to answer C(n,k) mod p in O(1) after O(n) preprocessing.
- **Single-row computation** — iterative product C(n,k)=C(n,k−1)·(n−k+1)/k computes one coefficient in O(k).
- **Symmetry & basic identities** — C(n,k)=C(n,n−k); C(n,0)=C(n,n)=1.
- **Hockey-stick (Christmas-stocking) identity** — ΣᵢC(i,r)=C(n+1,r+1) for i from r to n.
- **Vandermonde's identity** — C(m+n,k)=Σⱼ C(m,j)C(n,k−j); convolution of rows.
- **Sum of a row / row alternating sum** — ΣₖC(n,k)=2ⁿ and Σₖ(−1)ᵏC(n,k)=0 (n>0).
- **Binomial theorem** — (x+y)ⁿ=ΣₖC(n,k)xᵏyⁿ⁻ᵏ; generating-function viewpoint.
- **Lucas' theorem** — C(n,k) mod prime p via base-p digits, product of digit-wise small binomials (O(logₚ n)).
- **Andrew Granville / Lucas for prime powers** — C(n,k) mod pᵉ using factorials with prime factors stripped and carry counting (Kummer/Legendre).
- **Kummer's theorem** — exponent of prime p in C(n,k) equals number of carries adding k and n−k in base p.
- **Legendre's formula** — exponent of p in n! is Σ⌊n/pⁱ⌋; finds power of p dividing factorials/binomials.
- **CRT recombination for composite modulus** — compute C(n,k) mod each prime power then combine via CRT.
- **Wilson's theorem use** — (p−1)!≡−1 (mod p) for factorial reductions modulo a prime.
- **Generalized / negative-upper-index binomials** — C(−n,k)=(−1)ᵏC(n+k−1,k); Newton's generalized binomial series.
- **Catalan via binomials** — Cₙ=C(2n,n)/(n+1)=C(2n,n)−C(2n,n+1) (computed in O(1) with factorial tables).

#### Multinomial Coefficients

- **Multinomial coefficient** — n!/(k₁!⋯kₘ!) counts ways to partition n items into labeled groups of given sizes.
- **Multinomial theorem** — (x₁+⋯+xₘ)ⁿ expansion coefficients are multinomial coefficients.
- **Multinomial mod prime** — factorial/inverse-factorial precompute gives each value in O(m) after O(n) setup.

### Stars and Bars, Compositions and Distributions

- **Stars and bars (nonnegative solutions)** — x₁+⋯+xₖ=n has C(n+k−1,k−1) nonnegative integer solutions.
- **Positive-solution variant** — x₁+⋯+xₖ=n with xᵢ≥1 has C(n−1,k−1) solutions.
- **Bounded variables** — upper bounds via inclusion–exclusion over which caps are violated.
- **Compositions of n** — ordered sums of positive parts: 2ⁿ⁻¹ total; C(n−1,k−1) into exactly k parts.
- **Weak compositions** — ordered sums allowing zero parts (= stars and bars).
- **Distributing distinct objects into distinct boxes** — kⁿ (surjections via inclusion–exclusion / Stirling 2nd kind).
- **Twelvefold way** — full classification of ball-into-box counts (objects/boxes distinct or not, with constraints any/injective/surjective).

### Inclusion–Exclusion Principle

- **Basic IEP** — |A₁∪⋯∪Aₙ|=Σ|Aᵢ|−Σ|Aᵢ∩Aⱼ|+⋯ alternating over all intersections.
- **Complementary IEP** — count elements in no set as Σ(−1)^|S| |∩Aᵢ| over subsets S (O(2ⁿ·work) for n sets).
- **Surjection counting** — onto functions m→n: Σⱼ(−1)ʲC(n,j)(n−j)ᵐ = n!·S(m,n).
- **Coprime / divisibility counting** — count integers ≤N coprime to a set of primes; Euler's totient as IEP over prime factors.
- **Counting with forbidden positions** — permanent of 0-1 matrix / rook polynomials via IEP.
- **Möbius function as IEP** — μ generalizes inclusion–exclusion over the divisor / subset lattice (see Number Theory).
- **Möbius inversion (poset)** — invert summatory relations over any locally finite partially ordered set.
- **Subset-sum / SOS DP (sum over subsets)** — compute Σ over all subsets/supersets in O(n·2ⁿ) (zeta/Möbius transform).
- **IEP on a bitmask DP** — fast subset convolution and counting under structural constraints (O(2ⁿ·n²) subset convolution).
- **Min–max via IEP (E[max]=ΣE[min over subsets])** — convert hard joint events into manageable intersections.
- **Bonferroni inequalities** — truncated IEP gives alternating upper/lower bounds on union size.

### Derangements and Related Restricted Permutations

- **Derangements Dₙ** — permutations with no fixed point; Dₙ=n!Σ(−1)ᵏ/k! ≈ n!/e (O(n) DP).
- **Derangement recurrences** — Dₙ=(n−1)(Dₙ₋₁+Dₙ₋₂) and Dₙ=n·Dₙ₋₁+(−1)ⁿ.
- **Partial derangements (rencontres numbers)** — permutations with exactly k fixed points: C(n,k)·Dₙ₋ₖ.
- **Permutations with no two consecutive / ménage problem** — seating with adjacency restrictions via IEP and rook polynomials.
- **Problème des ménages numbers** — count seatings alternating two groups with forbidden neighbors.
- **Permutations avoiding a pattern** — pattern-avoidance counts (e.g. 123-avoiding = Catalan); enumerative combinatorics topic.

### Catalan Numbers and Friends

- **Catalan numbers Cₙ** — Cₙ=C(2n,n)/(n+1); recurrence Cₙ₊₁=ΣCᵢCₙ₋ᵢ (O(n) with tables, O(n²) by convolution).
- **Balanced parentheses / Dyck paths** — Cₙ counts valid bracket sequences and monotone lattice paths staying below diagonal.
- **Binary trees / full binary trees** — Cₙ counts shapes of binary trees with n nodes.
- **Triangulations of a polygon** — Cₙ triangulations of an (n+2)-gon.
- **Non-crossing structures** — non-crossing partitions, handshakes, chord diagrams counted by Cₙ.
- **Ballot problem / cycle lemma** — counts paths staying strictly positive; reflection principle derivation.
- **Reflection principle** — count lattice paths crossing a barrier by reflecting, giving C(2n,n)−C(2n,n+1).
- **Super-Catalan / Schröder numbers** — large/little Schröder numbers count lattice paths with diagonal steps and dissections.
- **Motzkin numbers** — paths with up/down/flat steps; recurrence Mₙ₊₁=Mₙ+ΣMᵢMₙ₋₁₋ᵢ.
- **Narayana numbers** — refine Catalan by number of peaks: N(n,k)=(1/n)C(n,k)C(n,k−1).
- **Fuss–Catalan numbers** — generalize Catalan to m-ary trees / (m+1)-gon dissections.

### Stirling, Bell, Eulerian and Partition Numbers

#### Stirling Numbers

- **Stirling second kind S(n,k)** — partitions of n labeled items into k nonempty unlabeled blocks; S(n,k)=k·S(n−1,k)+S(n−1,k−1) (O(nk)).
- **Explicit formula for S(n,k)** — (1/k!)Σⱼ(−1)ʲC(k,j)(k−j)ⁿ (IEP form).
- **Stirling first kind (unsigned) c(n,k)** — permutations of n with exactly k cycles; c(n,k)=(n−1)·c(n−1,k)+c(n−1,k−1) (O(nk)).
- **Signed Stirling first kind** — coefficients of falling factorial xⁿ̲ = Σ s(n,k) xᵏ.
- **Conversion bases (falling/rising factorials)** — Stirling numbers as change-of-basis between powers and factorial polynomials.
- **Stirling identities** — Σₖ S(n,k)=Bₙ; orthogonality of first and second kind matrices.
- **Lah numbers** — connect rising and falling factorials; count partitions into ordered lists: L(n,k)=C(n−1,k−1)n!/k!.

#### Bell Numbers

- **Bell numbers Bₙ** — total partitions of an n-set; Bₙ=ΣₖS(n,k) (O(n²)).
- **Bell triangle (Aitken's array)** — generates Bell numbers row by row in O(n²).
- **Dobinski's formula** — Bₙ=(1/e)Σ kⁿ/k! infinite-series identity.
- **Bell numbers mod p / Touchard congruence** — Bₙ₊ₚ≡Bₙ+Bₙ₊₁ (mod p) and periodicity of Bell numbers modulo a prime.

#### Eulerian Numbers

- **Eulerian numbers ⟨n,k⟩** — permutations of n with exactly k ascents (descents); recurrence ⟨n,k⟩=(k+1)⟨n−1,k⟩+(n−k)⟨n−1,k−1⟩ (O(n²)).
- **Worpitzky's identity** — xⁿ=Σₖ⟨n,k⟩C(x+k,n) expressing powers via Eulerian numbers.
- **Eulerian numbers of the second order** — count permutations of multisets with descent statistics.

#### Integer Partitions

- **Partition function p(n)** — unordered sums of positive integers; DP over parts in O(n·√n) or O(n²).
- **Partitions into parts ≤k / exactly k parts** — restricted-partition DP tables (O(nk)).
- **Distinct-parts partitions q(n)** — partitions into unequal parts; equals partitions into odd parts (Euler).
- **Pentagonal number theorem (Euler)** — p(n)=Σ(−1)ᵏ⁻¹[p(n−gₖ)+p(n−g₋ₖ)] with generalized pentagonal numbers gₖ, giving O(n√n).
- **Ferrers / Young diagrams & conjugation** — visual bijections proving partition identities (self-conjugate ↔ distinct odd parts).
- **Hardy–Ramanujan asymptotic** — p(n)~exp(π√(2n/3))/(4n√3) growth estimate.
- **Partition generating function** — Π1/(1−xᵏ); product form drives DP and identities.
- **Hook length formula** — number of standard Young tableaux of a shape = n!/Πhooks (counting via RSK).

### Burnside's Lemma and Pólya Enumeration

- **Group actions / orbits & stabilizers** — counting distinct configurations under a symmetry group; orbit–stabilizer theorem |orbit|=|G|/|stab|.
- **Burnside's lemma (Cauchy–Frobenius)** — #orbits = (1/|G|)Σ_g |Fix(g)|, average number of fixed configurations (O(|G|·work)).
- **Cycle index polynomial** — Z(G) encodes cycle structure of each group element for systematic counting.
- **Pólya enumeration theorem** — substitute color-generating series into the cycle index to count colorings by type.
- **Necklace counting** — distinct necklaces under rotation: (1/n)Σ_{d|n}φ(d)k^{n/d} (O(d(n))).
- **Bracelet counting** — necklaces under rotation and reflection (dihedral group), separate even/odd reflection terms.
- **Colorings of cube / polyhedra faces & vertices** — apply cycle index of the rotation group to count distinct paintings.
- **Counting graphs up to isomorphism (small n)** — Pólya on the symmetric group acting on vertex pairs.
- **De Bruijn generalization** — Burnside/Pólya with groups acting on both domain and color set.

### Generating Functions

- **Ordinary generating functions (OGF)** — encode sequences as coefficients of a formal power series Σaₙxⁿ.
- **Exponential generating functions (EGF)** — Σaₙxⁿ/n! for labeled structures; product = labeled combination.
- **Operations: sum, product (convolution), shift, derivative, integral** — translate sequence operations into series algebra.
- **Closed forms via partial fractions** — extract coefficients of rational GFs to solve linear recurrences.
- **Coefficient extraction [xⁿ]** — Newton's binomial series, geometric-series, and 1/(1−x)ᵏ expansions.
- **Product / Euler-product GFs for partitions** — Π1/(1−xⁱ) and restricted variants.
- **Catalan / tree functional equations** — C(x)=1+xC(x)² solved via the quadratic formula.
- **Exponential formula** — EGF of structures built from connected components: total = exp(connected EGF).
- **Symbolic method (Flajolet–Sedgewick)** — admissible constructions (sequence, set, cycle, multiset) auto-derive GFs.
- **Lagrange inversion** — extract coefficients of an implicitly defined GF (e.g. tree enumerations).
- **Polynomial GF manipulation with NTT/FFT** — multiply/exponentiate/invert series in O(n log n) (see Number Theory / Polynomials).
- **Probability/moment generating functions** — encode distributions; derivatives at 1 give factorial moments.
- **Dirichlet series GFs** — Σaₙ/nˢ for multiplicative/number-theoretic counting (see Number Theory).

### Linear Recurrences

- **Defining and detecting linear recurrences** — aₙ=Σcᵢaₙ₋ᵢ; characterize a sequence by its recurrence and initial terms.
- **Characteristic-polynomial solution** — distinct/repeated roots give closed forms (e.g. Fibonacci → Binet's formula).
- **Matrix exponentiation** — compute the n-th term of a k-term recurrence in O(k³ log n) via fast power of the companion matrix.
- **Kitamasa / Cayley–Hamilton method** — n-th term in O(k² log n) (or O(k log k log n) with NTT) by polynomial mod reduction.
- **Berlekamp–Massey** — recover the shortest linear recurrence from a sequence's first terms in O(m²); pairs with Kitamasa.
- **Linear recurrence via generating function** — rational GF whose denominator is the characteristic polynomial.
- **Inhomogeneous recurrences** — particular + homogeneous solution; absorb forcing terms into an augmented matrix.
- **Holonomic / P-recursive sequences** — polynomial-coefficient recurrences enabling O(√n) point evaluation of factorial-like terms.

### Probability Foundations

- **Sample space, events, axioms** — Kolmogorov axioms; probability as a normalized measure on events.
- **Equally likely outcomes** — P(A)=|A|/|S|; reduces probability to counting.
- **Complement, union, addition rule** — P(Aᶜ)=1−P(A); P(A∪B)=P(A)+P(B)−P(A∩B).
- **Inclusion–exclusion for probabilities** — union probability over many events as alternating intersection sum.
- **Independence** — P(A∩B)=P(A)P(B); mutual vs pairwise independence.
- **Conditional probability** — P(A|B)=P(A∩B)/P(B); chain rule P(A₁⋯Aₙ)=ΠP(Aᵢ|earlier).
- **Law of total probability** — P(A)=Σ P(A|Bᵢ)P(Bᵢ) over a partition.
- **Bayes' theorem** — P(B|A)=P(A|B)P(B)/P(A); posterior updating from priors and likelihoods.
- **Bayesian updating / odds form** — sequential evidence multiplies likelihood ratios.
- **Birthday paradox** — collision probability among n draws from N; ~√N threshold (basis for collision attacks).
- **Coupon collector** — expected draws to collect all n coupons is nHₙ ≈ n ln n.

### Random Variables, Expectation and Variance

- **Random variable (discrete/continuous)** — function from sample space to reals; pmf/pdf and cdf.
- **Expected value** — E[X]=Σx·P(X=x); the long-run average / mean.
- **Linearity of expectation** — E[ΣXᵢ]=ΣE[Xᵢ] regardless of dependence; the single most useful CP tool.
- **Indicator random variables** — Xᵢ∈{0,1}; E[Xᵢ]=P(event), turning counting into expectation sums.
- **Expectation of a function / LOTUS** — E[g(X)]=Σg(x)P(X=x) without finding g(X)'s distribution.
- **Tail-sum formula** — E[X]=Σ_{k≥1}P(X≥k) for nonnegative integer X.
- **Conditional expectation** — E[X|Y]; law of total expectation E[X]=E[E[X|Y]] (tower rule).
- **Variance & standard deviation** — Var(X)=E[X²]−E[X]²; spread of a distribution.
- **Variance of a sum / covariance** — Var(ΣXᵢ)=ΣVar(Xᵢ)+2ΣCov; independence drops covariance terms.
- **Covariance & correlation** — Cov(X,Y)=E[XY]−E[X]E[Y]; correlation normalizes to [−1,1].
- **Expected products** — E[XY]=E[X]E[Y] only under independence; otherwise needs covariance.
- **Moments and moment generating function** — E[Xᵏ] and MGF M(t)=E[e^{tX}] characterize a distribution.
- **Jensen's inequality** — E[f(X)] vs f(E[X]) for convex/concave f.
- **Markov & Chebyshev inequalities** — tail bounds from mean and variance.
- **Modular probabilities / expected value mod p** — represent fractions as modular inverses to output expectations mod a prime.

### Common Probability Distributions

- **Bernoulli(p)** — single trial; mean p, variance p(1−p).
- **Binomial(n,p)** — number of successes in n independent trials; P=C(n,k)pᵏ(1−p)ⁿ⁻ᵏ, mean np, variance np(1−p).
- **Geometric(p)** — trials until first success; mean 1/p, variance (1−p)/p²; memoryless.
- **Negative binomial** — trials until r-th success; sum of r geometrics.
- **Hypergeometric** — successes when sampling without replacement; finite-population analogue of binomial.
- **Poisson(λ)** — rare-event count; limit of binomial, mean = variance = λ.
- **Uniform (discrete/continuous)** — equal-likelihood outcomes; mean and variance closed forms.
- **Multinomial** — joint counts over m categories in n trials; generalizes binomial.
- **Exponential / continuous geometric** — memoryless waiting time (intro for continuous EV problems).
- **Normal (Gaussian) intro** — CLT limit; rarely needed exactly in CP but underpins approximations.

### Markov Chains and Random Processes

- **Markov chain / transition matrix** — memoryless state process; Pⁿ gives n-step transition probabilities (O(s³ log n) via matrix power).
- **Stationary distribution** — π with πP=π; long-run fractions of time in each state (solve linear system, O(s³)).
- **Classification of states** — transient, recurrent, periodic, absorbing.
- **Absorbing Markov chains** — fundamental matrix N=(I−Q)⁻¹ gives expected steps to absorption and absorption probabilities (O(s³)).
- **Expected hitting / absorption time** — linear system over states; Gaussian elimination on EV equations.
- **Gambler's ruin** — ruin/absorption probabilities and expected duration on a 1-D walk.
- **Random walks on graphs** — hitting/commute times, cover time, relation to electrical networks and effective resistance.
- **Detailed balance / reversibility** — sufficient condition for a stationary distribution.
- **Ergodicity & convergence** — irreducible aperiodic chains converge to a unique stationary distribution.

### Expected-Value and Probability DP

- **Expected-value DP** — states with expected outcomes; solve forward/backward over a DAG of states (see Dynamic Programming).
- **Probability DP** — propagate distributions over states; combine with bitmask/digit/tree DP (see Dynamic Programming).
- **Self-referential EV equations** — states that loop (e.g. retries) solved by algebra E=...E... or Gaussian elimination (see Dynamic Programming).
- **Game-value / optimal-play expectation** — minimax over expectations for randomized games (see Game Theory).
- **Monte Carlo / randomized estimation** — sampling to approximate counts/probabilities (see Randomized Algorithms).

### Probabilistic Method and Existence Arguments

- **First-moment method** — if E[X]<1 (or E[X]=0) an object with X=0 exists; existence via averaging.
- **Expectation argument for existence** — some outcome is at least/at most the mean (e.g. large cut, balanced coloring).
- **Lovász Local Lemma** — bad events with bounded dependency all avoidable if probabilities are small enough.
- **Second-moment method** — variance bounds show X>0 with high probability (threshold phenomena).
- **Alteration / deletion method** — build a random structure then delete few defects to guarantee a good substructure.
- **Union bound (Boole's inequality)** — P(∪Aᵢ)≤ΣP(Aᵢ); bound failure probability of randomized algorithms.
- **Random construction lower bounds** — Ramsey-type and tournament-existence proofs by counting.

### Special Numbers, Sums and Identities

- **Harmonic numbers Hₙ** — Σ1/k ≈ ln n+γ; appear in coupon collector, quickselect/quicksort analysis (O(n) tabulation).
- **Generalized harmonic numbers** — Hₙ^(r)=Σ1/kʳ; relate to ζ-values.
- **Euler–Mascheroni constant γ** — limit of Hₙ−ln n; asymptotic corrections.
- **Fibonacci & Lucas numbers** — F(n) via matrix power / fast doubling in O(log n); Zeckendorf representation, Cassini/Catalan identities.
- **Bernoulli numbers** — appear in Faulhaber's formula for power sums Σkᵐ.
- **Faulhaber's formula / power sums** — closed polynomial for Σ_{k=1}^{n} kᵐ of degree m+1.
- **Telescoping & summation by parts (Abel)** — collapse sums; discrete analogue of integration by parts.
- **Finite differences & Newton's forward formula** — interpolate / sum polynomial sequences; difference table methods.
- **Gosper / Zeilberger (creative telescoping)** — algorithmic proof of hypergeometric summation identities.
- **WZ method** — certify combinatorial identities mechanically via Wilf–Zeilberger pairs.
- **Vandermonde / Chu–Vandermonde, Saalschütz** — standard hypergeometric binomial identities.
- **Möbius / Euler totient sums** — Σφ, Σμ identities bridging counting and number theory (see Number Theory).

### Lattice Paths and Geometric Counting

- **Monotone lattice paths** — paths with unit right/up steps: C(a+b,a) from origin to (a,b).
- **Paths avoiding a diagonal / below line** — reflection principle and cycle lemma (Catalan/ballot counts).
- **Lindström–Gessel–Viennot lemma** — count non-intersecting path families as a determinant (O(k³) plus path counts).
- **Delannoy / Schröder path counts** — paths with diagonal steps; central Delannoy numbers.
- **Counting lattice points under a line** — Σ⌊(a·i+b)/m⌋ via the floor-sum / Euclidean-like algorithm in O(log) (see Number Theory).
- **Pick's theorem** — polygon area from interior and boundary lattice points (see Computational Geometry).

### Advanced Counting Techniques

- **Transfer-matrix method** — count configurations of a 1-D/strip structure (tilings, constrained strings) via matrix power (O(s³ log n)).
- **Tiling and domino/brick counts** — broken-profile / bitmask DP across a grid column (see Dynamic Programming).
- **Matrix-tree theorem (Kirchhoff)** — number of spanning trees = any cofactor of the Laplacian (O(n³)) (see Graph Theory).
- **BEST theorem** — count Eulerian circuits of a directed graph via arborescences and degrees (see Graph Theory).
- **Cayley's formula** — nⁿ⁻² labeled trees on n vertices; Prüfer-sequence bijection (O(n) encode/decode).
- **Prüfer sequences** — bijection between labeled trees and length-(n−2) strings; count trees with given degrees via multinomial.
- **Permanent computation (Ryser's formula)** — permanent of an n×n matrix in O(2ⁿn) via inclusion–exclusion.
- **Counting under constraints with DP over subsets** — Hamiltonian-path/perfect-matching counts in O(2ⁿ·poly) (see Dynamic Programming).
- **Schur / symmetric-function methods** — advanced enumerative tools (RSK correspondence, tableaux counting).
- **Species & analytic combinatorics** — asymptotic coefficient extraction via singularity analysis (Flajolet–Sedgewick).
- **q-analogues / Gaussian binomial coefficients** — count subspaces over finite fields; q-binomial recurrence.

<sub>[↑ Back to top](#table-of-contents)</sub>

---

## 24. Polynomials, FFT/NTT & Linear Algebra

### Polynomial Representation & Basic Operations

#### Representations
- **Coefficient (dense) form** — store the array of coefficients `a[0..n]`; natural for addition, evaluation, and shifting (O(n) space).
- **Sparse form** — store only nonzero `(exponent, coefficient)` pairs; efficient when few terms are nonzero (O(#terms) space).
- **Point-value (evaluation) form** — store `(xᵢ, P(xᵢ))` for `n+1` distinct points; multiplication becomes pointwise O(n), but evaluation/addition need shared nodes.
- **Newton / divided-difference form** — coefficients in the basis of falling products `∏(x−xᵢ)`; supports incremental point addition (O(n) per new point).
- **Root / factored form** — `c·∏(x−rᵢ)`; trivial multiplication and root reading, costly addition.
- **Formal power series (truncated)** — polynomial-like object kept modulo `xⁿ`; the working object for inverse/log/exp/sqrt.

#### Elementary operations
- **Addition / subtraction** — componentwise on coefficients (O(n)).
- **Scalar multiply & negation** — scale every coefficient (O(n)).
- **Schoolbook (naïve) multiplication** — convolve all coefficient pairs (O(n·m)).
- **Horner's method (evaluation)** — nested `((aₙx+aₙ₋₁)x+…)` for a single point (O(n)).
- **Synthetic division by (x−c)** — Horner-style quotient + remainder = `P(c)` (O(n)).
- **Formal derivative & integral** — shift coefficients with index factors; integral needs invertible denominators (O(n)).
- **Polynomial shift / coefficient shift** — multiply/divide by `xᵏ` via index offset (O(n)).
- **Taylor shift `P(x+c)`** — recompute coefficients about a new center via binomial/transform tricks (O(n log n) FFT, O(n²) naïve).
- **Polynomial composition `P(Q(x))`** — substitution; Brent–Kung baby-step/giant-step (O(n²) ~ O((n log n)·√n)); modern O(n log² n) via reversion/Kinoshita–Li.
- **Compositional inverse (power-series reversion)** — find `Q` with `P(Q(x))=x`; Lagrange inversion / Newton (O(n log² n)).
- **Lagrange inversion formula** — extract `[xⁿ]Q` (the reversion) from powers of `x/P` without full reversion.
- **GCD of polynomials (Euclidean)** — repeated polynomial remainder (O(n²) naïve); Half-GCD gives O(n log² n).
- **Resultant & discriminant** — Sylvester determinant / via subresultant or evaluation–interpolation; detects common roots (O(n²)–O(n log² n)).

### Polynomial Multiplication & Convolution

- **Linear (acyclic) convolution** — output `c[k]=Σ a[i]b[k−i]`; the core product operation, length `n+m−1`.
- **Cyclic (circular) convolution** — indices taken mod `n`; exactly what a single DFT of fixed size computes.
- **Karatsuba multiplication** — one fewer recursive product via `(a₀+a₁)(b₀+b₁)` (O(n^log₂3) ≈ O(n^1.585)).
- **Toom–Cook (Toom-3 and general Toom-k)** — split into k parts, evaluate/interpolate at small points (O(n^{log_k(2k−1)}), e.g. Toom-3 ≈ O(n^1.465)).
- **FFT-based multiplication** — pointwise multiply two DFTs and invert (O(n log n)); see FFT below.
- **Schönhage–Strassen** — recursive FFT over a ring with synthetic roots of unity (O(n log n log log n)); classic asymptotic record for integers.
- **Harvey–van der Hoeven** — theoretical O(n log n) integer multiplication; mostly of theoretical interest.
- **Online / relaxed (incremental) convolution** — produce `c[k]` as inputs stream in, for self-referential DP (O(n log² n)).

### Fast Fourier Transform (FFT)

#### Foundations
- **Discrete Fourier Transform (DFT)** — evaluate a polynomial at the `n` complex `n`-th roots of unity; naïve matrix form (O(n²)).
- **Inverse DFT** — same transform with conjugated roots, scaled by `1/n`; recovers coefficients (O(n²) naïve, O(n log n) via FFT).
- **Roots of unity & their lemmas** — halving lemma `ω_{2n}^{2k}=ω_n^k` and `ω_n^{k+n/2}=−ω_n^k` underpin divide-and-conquer.
- **Convolution theorem** — pointwise product in frequency domain equals convolution in time domain; basis of fast multiplication.

#### Algorithms
- **Cooley–Tukey radix-2 (recursive)** — split into even/odd index halves, combine with twiddle factors (O(n log n)); requires `n` a power of two.
- **Iterative FFT with bit-reversal permutation** — reorder by reversed bit index, then butterfly bottom-up; avoids recursion overhead (O(n log n), O(1) extra).
- **Butterfly operation** — the `(u+ωv, u−ωv)` combine step at the heart of every layer.
- **Mixed-radix / radix-4 / split-radix** — fewer multiplications than radix-2; split-radix minimizes operation count (O(n log n) with smaller constant).
- **Bluestein's algorithm (chirp-z transform)** — DFT of arbitrary length `n` by reducing to a convolution of size ≥ `2n−1` (O(n log n)).
- **Prime-factor (Good–Thomas) FFT** — factor `n` into coprime parts, no twiddle factors (O(n log n)).
- **Rader's FFT** — prime-length DFT via primitive root → length `p−1` convolution (O(p log p)).
- **Chirp-Z transform** — evaluate DFT on a geometric/spiral contour of arbitrary points (O(n log n)); generalizes Bluestein.

#### Practical optimizations
- **Precompute twiddle factors / root table** — store powers of `ω` once to avoid repeated trig (constant-factor win).
- **In-place iterative transform** — overwrite the input array; only the bit-reversal permutation needs care.
- **Two-real-via-one-complex trick** — pack two real sequences into one complex FFT and unscramble (≈2× speedup).
- **Real-input FFT (rfft) / Hermitian symmetry** — exploit conjugate symmetry to halve work for real data.
- **Squaring optimization** — multiplying a polynomial by itself needs only one forward transform.
- **Precision concerns** — floating-point error grows with `n` and coefficient size; round results, or split coefficients to bound error.
- **Anti–chirp / padding** — pad to a power of two ≥ `n+m−1` to realize linear (not cyclic) convolution.

### Number-Theoretic Transform (NTT)

- **NTT concept** — DFT over `ℤ_p` using a primitive `n`-th root of unity instead of complex `ω`; exact, no floating error (O(n log n)).
- **NTT-friendly primes** — primes `p = c·2^k + 1` with large `2^k` so power-of-two transform lengths exist (e.g. 998244353 = 119·2²³+1, 167772161, 469762049).
- **Primitive root & order conditions** — need a generator `g` whose order divides `p−1` and yields an `n`-th root; `n | (p−1)`.
- **Inverse NTT** — transform with inverse root, multiply by modular inverse of `n`.
- **Three-primes CRT (arbitrary-modulus NTT)** — run NTT under 3 friendly primes, combine via CRT to recover products fitting in ~`p³` before reducing mod arbitrary `M` (O(n log n)).
- **Two-primes / split-coefficient (MTT) method** — split each coefficient into high/low halves and combine partial convolutions to multiply mod arbitrary `M` (O(n log n)).
- **Garner / mixed-radix CRT reconstruction** — combine residues without large-integer overflow during the merge.
- **FFT vs NTT trade-offs** — FFT handles arbitrary real/complex coefficients but has precision limits; NTT is exact but requires modular arithmetic and special primes.

### Convolution Variants & Transforms

#### Bitwise (set) convolutions
- **Fast Walsh–Hadamard Transform (FWHT)** — diagonalizes XOR convolution; transform, pointwise multiply, invert (O(n log n) over `2ᵏ` length).
- **XOR convolution** — `c[k]=Σ_{i⊕j=k} a[i]b[j]` via FWHT.
- **AND convolution** — superset-sum (zeta over superset) transform, multiply, Möbius inverse (O(n log n)).
- **OR convolution** — subset-sum (zeta) transform, multiply, Möbius inverse (O(n log n)).
- **Sum over Subsets (SOS) DP / subset-zeta transform** — accumulate over all submasks dimension by dimension (O(n·2ⁿ)).
- **Möbius transform (subset/superset inversion)** — inverse of the zeta transform; recovers original from accumulated sums (O(n·2ⁿ)).
- **Subset-sum convolution (ranked/with popcount)** — OR-convolution refined by popcount layers to forbid overlapping bits (O(n²·2ⁿ)).
- **GCD / LCM convolution** — Dirichlet-style zeta/Möbius over divisibility lattice (O(n log log n)).
- **Dirichlet convolution & multiplicative sieve** — `(f∗g)(n)=Σ_{d|n} f(d)g(n/d)` (see Number Theory).

#### Multidimensional & specialized
- **Multidimensional FFT/NTT** — apply 1-D transform along each axis; for 2-D image/grid convolution (O(N log N) over total size N).
- **Cyclic vs linear via zero-padding** — choose transform length to suppress wraparound.
- **Min-plus (tropical) convolution** — `c[k]=min_i a[i]+b[k−i]`; no fast transform in general (O(n²); subquadratic only with convexity).
- **(max,+) and convex/SMAWK speedups** — when one sequence is concave/convex, use Minkowski sum or SMAWK (O(n)).
- **Subset / divisor multidimensional DP** — generalize SOS across multiple lattices.

### Formal Power Series Operations (Newton Iteration)

- **Newton's method on power series** — double the known prefix each step by solving `f(g)=0` mod `x^{2k}`; the engine for inverse/sqrt/log/exp (O(n log n) total via geometric blowup).
- **Polynomial / series inverse** — find `B` with `A·B ≡ 1 (mod xⁿ)`; requires `a₀` invertible (O(n log n)).
- **Polynomial division & remainder (fast)** — reverse coefficients, multiply by inverse of reversed divisor to get quotient, then remainder (O(n log n)).
- **Logarithm of a power series** — `log A = ∫ A'/A`, requires `a₀=1` (O(n log n)).
- **Exponential of a power series** — solve `B' = B·A'` via Newton, requires `a₀=0` (O(n log n)).
- **Square root of a power series** — Newton iteration `B←(B+A/B)/2`; needs a modular square root of `a₀` (O(n log n)).
- **k-th power `A^k mod xⁿ`** — `exp(k·log A)`; handle leading zeros by factoring out `xᵗ` and normalizing (O(n log n)).
- **Fast exponentiation fallback** — when `a₀≠1`, normalize then binary-exponentiate by repeated squaring of the series (O(n log n log k)).
- **Multipoint Taylor shift via exp/transform** — `P(x+c)` through convolution with factorial-weighted reversed coefficients (O(n log n)).
- **Newton iteration for functional equations** — solving general `F(B)=0 mod xⁿ` (e.g. generating-function fixpoints) by lifting precision.

### Multipoint Evaluation, Interpolation & Lagrange

- **Single-point evaluation** — Horner's rule (O(n)); modular evaluation for hashing/CRT.
- **Fast multipoint evaluation** — build a segment tree of product polynomials `∏(x−xᵢ)`, take remainders down the tree (O(n log² n)).
- **Subproduct (remainder) tree** — the divide-and-conquer structure underlying both multipoint eval and interpolation.
- **Fast polynomial interpolation** — combine subproduct tree with derivative weights to invert evaluation (O(n log² n)).
- **Lagrange interpolation (general)** — `P(x)=Σ yᵢ ∏_{j≠i}(x−xⱼ)/(xᵢ−xⱼ)` (O(n²) for one point, O(n²) to build).
- **Lagrange on consecutive integer points** — prefix/suffix products make evaluation at any `x` O(n); ideal for polynomial-sum identities.
- **Newton's divided differences** — incremental interpolation table; add points online (O(n²) build, O(n) per query).
- **Evaluation on a geometric progression** — chirp-z / Bluestein evaluates `P` at `x₀rᵏ` (O(n log n)).
- **Interpolation from geometric points** — inverse of the above (O(n log n)).
- **Vandermonde systems** — interpolation as solving a Vandermonde linear system; structured O(n²) solvers.
- **Sum of powers / Faulhaber via interpolation** — fit the degree-(k+1) polynomial of `Σ iᵏ` from k+2 samples (O(k)).
- **Sample-and-fit principle** — when the answer is known to be polynomial in `n`, compute enough values and interpolate.

### FFT Applications

- **Big-integer multiplication** — treat digits (in some base/limb) as coefficients, convolve, then carry-propagate (O(n log n)).
- **High-precision arithmetic / decimal libraries** — base-10ᵏ limbs multiplied via FFT/NTT.
- **String matching with FFT** — encode characters numerically; matches show as convolution peaks (O(n log n)).
- **Pattern matching with wildcards** — define mismatch score `Σ (p−t)²·p·t`; zeros mark matches allowing `?` wildcards (O(n log n)).
- **Hamming-distance / mismatch counting** — per-character indicator convolutions sum to mismatch counts (O(σ·n log n) or O(n√n log n)).
- **Counting pairs by sum / difference** — frequency-array self-convolution gives counts of achievable sums (O(S log S)).
- **Subset-sum / coin feasibility via convolution** — multiply generating functions of item sets (O(S log S) per merge).
- **Distinct-distance / geometric distance histograms** — indicator convolution over coordinates.
- **Polynomial-coefficient combinatorics** — extract `[xᵏ]` of a product of generating functions for counting problems.
- **All-pairs shifts / autocorrelation** — self-convolution to count aligned coincidences.
- **Necklace/cyclic-correlation matching** — cyclic convolution for rotational alignment.

### Matrix Operations & Fast Exponentiation

- **Matrix representation & storage** — row-major dense; band/sparse forms for special structure.
- **Matrix multiplication (naïve)** — triple loop (O(n³)); cache-blocked variants for constants.
- **Strassen's algorithm** — 7 instead of 8 recursive multiplications (O(n^log₂7) ≈ O(n^2.807)).
- **Coppersmith–Winograd family** — sub-2.38 exponents; theoretical, impractical constants.
- **Matrix exponentiation by squaring** — `Mᵏ` via binary exponentiation (O(n³ log k)).
- **Linear recurrence via matrix power** — encode recurrence as transition matrix, exponentiate for the k-th term (O(d³ log k) for order `d`).
- **Companion-matrix construction** — build transition matrix directly from recurrence coefficients.
- **Fibonacci / fast-doubling** — closed-form doubling identities; special case of matrix power (O(log k)).
- **Kronecker (tensor) product** — block-structured product for product spaces.
- **Sparse matrix–vector product** — iterate nonzeros only (O(nnz)); core of Krylov/black-box methods.
- **Min-plus matrix multiplication** — tropical product for shortest-path DP (O(n³); APSP connection).
- **Boolean matrix multiplication** — bitset-accelerated transitive closure (O(n³/w)).
- **Matrix power for graph walk counting** — `(Aᵏ)_{ij}` counts length-k walks (O(n³ log k)).

### Gaussian Elimination & Linear Systems

- **Forward elimination → row echelon form** — eliminate below pivots column by column (O(n³)).
- **Back substitution / reduced row echelon (Gauss–Jordan)** — eliminate above pivots too for direct solution (O(n³)).
- **Partial pivoting** — swap in the largest-magnitude pivot for numerical stability (over reals).
- **Full pivoting** — pivot over both rows and columns; maximal stability, tracks column permutation.
- **Solving `Ax=b`** — augment with `b`, eliminate, back-substitute; detect unique/none/infinite solutions (O(n³)).
- **Gaussian elimination over a finite field (mod p)** — use modular inverses as pivots; exact, no rounding (O(n³)).
- **Free variables & solution-space parametrization** — read off pivot vs free columns; build particular + homogeneous basis.
- **Consistency / rank checks** — inconsistent if a zero row meets a nonzero augmented entry.
- **LU decomposition** — factor `A=LU` (with permutation `PA=LU`); reuse for many right-hand sides (O(n³) once, O(n²) per solve).
- **Cholesky decomposition** — `A=LLᵀ` for symmetric positive-definite matrices (≈ half the work of LU).
- **QR decomposition (Gram–Schmidt / Householder)** — orthogonal factorization for least squares & stability.
- **Least-squares via normal equations / QR** — solve overdetermined systems minimizing residual.
- **Iterative refinement** — re-solve on the residual to recover lost precision.
- **Tridiagonal / banded solvers (Thomas algorithm)** — specialized elimination (O(n) for tridiagonal).
- **Block / Schur-complement elimination** — partition large structured systems.

### Determinant, Rank & Inverse

- **Determinant via Gaussian elimination** — product of pivots times sign of permutation (O(n³)).
- **Determinant mod p** — same elimination using modular inverses (O(n³)).
- **Determinant mod composite (no field inverse)** — fraction-free / Bareiss-style or Euclidean pivoting to avoid needing inverses (O(n³)).
- **Bareiss algorithm (fraction-free)** — integer-preserving elimination giving exact determinant without fractions (O(n³)).
- **Matrix rank** — count nonzero pivots after elimination (O(n³)); rank over reals, mod p, or GF(2).
- **Matrix inverse** — Gauss–Jordan on `[A | I]` → `[I | A⁻¹]` (O(n³)); inverse mod p analogously.
- **Adjugate / cofactor matrix** — `A⁻¹ = adj(A)/det(A)`; useful symbolically.
- **Cramer's rule** — solution components as determinant ratios; mainly theoretical for small `n` (O(n·n³) naïve).
- **Permanent (hardness note)** — #P-hard in general; Ryser's formula O(2ⁿ·n) for small `n`.
- **Pfaffian** — square-root-like determinant of a skew-symmetric matrix; counts perfect matchings in planar graphs (O(n³)).
- **Determinant of structured matrices** — circulant (via DFT), Vandermonde (product of differences), tridiagonal (recurrence).

### Linear Algebra over GF(2) & Linear Basis

- **XOR linear system (mod 2 Gaussian elimination)** — solve systems of XOR equations with bitset rows (O(n·m/w)).
- **Bitset acceleration** — pack each row into machine words for `n/w` speedup on GF(2) elimination.
- **Linear basis (XOR basis / linear span over GF(2))** — maintain a reduced basis of vectors under XOR (insert O(B) where B = #bits).
- **Maximum XOR subset** — greedily combine basis vectors from the high bit down (O(B)).
- **k-th smallest / number of distinct XOR values** — reduce basis to canonical form, index by subset (O(B)).
- **Querying XOR-representability** — test if a value lies in the span (O(B)).
- **Rank / nullity over GF(2)** — basis size gives rank; remainder gives kernel dimension.
- **Mod-p linear basis (general field)** — analogous greedy reduction over `ℤ_p`.
- **Online basis with deletion (segment-tree on time / offline)** — handle insert+delete via divide-and-conquer over a timeline.
- **Gaussian elimination over GF(2ᵏ) / polynomial fields** — for coding-theory style systems.

### Matrix–Tree Theorem & Spanning Structures

- **Laplacian (Kirchhoff) matrix** — `L = D − A`; degree matrix minus adjacency (O(V²) to build).
- **Matrix-Tree theorem** — number of spanning trees = any cofactor of `L` = determinant of `L` with one row/column deleted (O(V³)).
- **Weighted spanning-tree count** — replace adjacency with edge weights; determinant gives `Σ ∏ weights` over spanning trees.
- **Directed spanning trees (arborescences)** — use in/out Laplacian; cofactor counts rooted arborescences.
- **All-minors / counting forests** — generalized cofactors count rooted spanning forests.
- **Eulerian circuit counting (BEST theorem)** — combine arborescence count with degree factorials for directed Euler tours.
- **Spanning-tree count mod p** — determinant computed under modular arithmetic.

### Non-Intersecting Paths & Determinant Methods

- **Lindström–Gessel–Viennot (LGV) lemma** — signed count of vertex-disjoint path systems = determinant of the path-count matrix `M_{ij}=paths(aᵢ→bⱼ)` (O(k³) plus path counting).
- **Non-intersecting lattice paths** — count tuples of non-crossing monotone grid paths via LGV.
- **Plane partitions / rhombus tilings** — classic LGV applications counting tilings as determinants.
- **Gessel–Viennot for Schur functions** — semistandard tableaux via non-intersecting paths (combinatorics link).
- **Permanent vs determinant duality** — signed (determinant) counting works precisely when intersecting families cancel.

### Linear Recurrences from Sequences

- **Berlekamp–Massey algorithm** — find the shortest linear recurrence generating a given sequence over a field (O(n²)).
- **Reeds–Sloane (over `ℤ_{pᵏ}`)** — Berlekamp–Massey generalized to rings/composite moduli.
- **Kitamasa's algorithm** — compute the n-th term of an order-`d` linear recurrence via polynomial powering mod the characteristic polynomial (O(d² log n), or O(d log d log n) with NTT).
- **Polynomial-modular term extraction** — represent `xⁿ mod C(x)` and dot with initial terms; the engine behind fast Kitamasa.
- **Bostan–Mori algorithm** — extract `[xⁿ]` of a rational generating function `P/Q` by repeated even/odd folding (O(d log d log n)).
- **Cayley–Hamilton linear-recurrence solving** — `Aⁿ` reduces modulo the characteristic polynomial, so `Aⁿv` costs polynomial powering instead of matrix powering (O(d² log n)).
- **Guessing rational generating functions** — fit `P/Q` to a prefix (Padé approximation) to identify the closed form.
- **Padé / rational approximation** — best `P/Q` matching a series prefix; equivalent to extended-Euclid on `(xⁿ, series)`.
- **Combine BM + Kitamasa pipeline** — brute-force first terms, recover recurrence with Berlekamp–Massey, jump to the answer term with Kitamasa.

### Characteristic & Minimal Polynomials

- **Characteristic polynomial** — `det(xI − A)`; roots are eigenvalues (O(n⁴) via interpolation of determinants, O(n³) via Hessenberg reduction).
- **Hessenberg-form reduction** — bring `A` to upper-Hessenberg, then expand the characteristic polynomial by recurrence (O(n³)).
- **Faddeev–LeVerrier algorithm** — compute char-poly coefficients and inverse simultaneously via traces of matrix powers (O(n⁴)).
- **Minimal polynomial** — lowest-degree monic annihilating polynomial; found via Krylov sequence + Berlekamp–Massey (O(n³) or O(n·nnz) black-box).
- **Eigenvalues / eigenvectors** — roots of char-poly and corresponding null spaces (numeric methods mostly out of CP scope).
- **Trace and determinant relations** — sum/product of eigenvalues as char-poly coefficients.
- **Matrix powering via minimal polynomial** — reduce `Aᵏ` modulo the minimal polynomial for fast evaluation.

### Black-Box & Randomized Linear Algebra

- **Wiedemann's algorithm** — solve sparse linear systems / find minimal polynomial using only matrix–vector products + Berlekamp–Massey (O(n·nnz) time, O(n) extra space).
- **Block Wiedemann** — vectorized variant for large sparse systems over finite fields.
- **Lanczos / conjugate-gradient (mod p analog)** — Krylov-subspace solvers for sparse symmetric systems.
- **Sparse determinant / rank via Wiedemann** — recover via minimal polynomial of a randomly preconditioned matrix.
- **Schwartz–Zippel lemma** — random evaluation tests whether a symbolic determinant/polynomial is identically zero (randomized).
- **Randomized rank estimation** — random projections / preconditioners to compute rank with high probability.
- **Tutte matrix & matching existence** — symbolic skew-symmetric matrix whose determinant ≠ 0 iff a perfect matching exists; evaluate at random values (O(n³) randomized).
- **Matrix rank for matching size** — rank of the Tutte/bipartite symbolic matrix equals twice the maximum matching size.

### Numerical & Stability Considerations (overview)
- **Floating-point error growth in FFT** — error scales with transform length and coefficient magnitude; bound by splitting or using NTT.
- **Conditioning of linear systems** — ill-conditioned matrices amplify error; prefer pivoting / QR.
- **Exact vs approximate trade-off** — modular (NTT, mod-p elimination) for exactness; floating-point for arbitrary-real problems.
- **Overflow control in modular convolution** — use 128-bit accumulation, Montgomery/Barrett reduction, or CRT splitting.
- **Montgomery & Barrett reduction** — fast modular multiplication primitives that accelerate NTT and mod-p elimination (see Number Theory).

<sub>[↑ Back to top](#table-of-contents)</sub>

---

# 📐 Part — Geometry & Games

## 25. Computational Geometry

### Primitives: Points, Vectors, and Coordinates
- **Point / position vector** — a coordinate pair (2D) or triple (3D); the atomic geometric object underlying all constructions.
- **Free vector vs. bound vector** — direction-and-magnitude entity vs. anchored arrow from a base point; subtract two points to obtain a free vector.
- **Vector addition / subtraction / scaling** — componentwise operations forming a vector space; basis of translation and parametric forms (O(1)).
- **Vector magnitude (norm)** — Euclidean length sqrt(x²+y²); compare squared norms to avoid sqrt and precision loss (O(1)).
- **Normalization / unit vector** — divide by magnitude to get direction only; degenerate for the zero vector (O(1)).
- **Perpendicular (left/right normal)** — rotate a vector ±90°: (x,y) → (−y,x) or (y,−x); core of orientation and normals (O(1)).
- **Rotation by angle** — apply 2×2 rotation matrix; rotate about a pivot by translate-rotate-translate (O(1)).
- **Polar coordinates (r, θ)** — radius and angle representation; convert via atan2 and (r·cosθ, r·sinθ) (O(1)).
- **atan2 vs. angle comparison by cross product** — atan2 gives true angle but is float-heavy; cross-product comparison stays integer-exact for sorting.
- **Complex-number model of the plane** — treat points as complex numbers; multiplication encodes rotation+scaling, conjugate encodes reflection.

#### Dot and Cross Products
- **Dot product** — a·b = x₁x₂+y₁y₂ = |a||b|cosθ; measures projection/alignment, zero iff perpendicular (O(1)).
- **Cross product (2D scalar)** — a×b = x₁y₂−x₂y₁ = signed parallelogram area; sign gives turn direction, zero iff collinear (O(1)).
- **Cross product (3D vector)** — yields a vector normal to both operands; magnitude is parallelogram area, direction by right-hand rule (O(1)).
- **Triple product (scalar)** — (a×b)·c = signed volume of parallelepiped; zero iff three vectors coplanar (O(1)).
- **Angle between vectors** — recover θ from atan2(cross, dot) for a signed result in (−π, π] (O(1)).
- **Projection and rejection** — decompose a vector into components parallel and perpendicular to another via dot product (O(1)).

#### Orientation Predicates
- **Orientation / CCW test** — sign of cross product of (b−a)×(c−a): +1 left turn, −1 right turn, 0 collinear (O(1)).
- **Collinearity test** — three points collinear iff their orientation is zero (O(1)).
- **Half-plane / side-of-line test** — which side of directed line AB a point lies on, from the orientation sign (O(1)).
- **In-circle predicate** — sign of a 4×4 determinant tells if a point lies inside the circumcircle of three others; key to Delaunay (O(1)).
- **Robust adaptive predicates** — Shewchuk's exact orientation/incircle via adaptive-precision floating point; correct sign without overflow.

### Lines, Rays, and Segments
- **Two-point line representation** — store endpoints A, B; direction = B−A; flexible but no canonical form.
- **Parametric line** — P(t) = A + t·d; t∈[0,1] gives the segment, t∈ℝ the full line (O(1)).
- **Implicit line ax+by+c=0** — coefficients from cross product; a,b is the normal, evaluate sign for side tests (O(1)).
- **Slope-intercept / normal form** — y=mx+k or x·cosα+y·sinα=p; convenient but degenerate for vertical lines.
- **Segment vs. ray vs. line distinction** — same geometry, different parameter domains; affects intersection and distance clamping.

#### Intersections
- **Line-line intersection** — solve via cross products / Cramer's rule; classify unique, parallel (none), or coincident (infinite) (O(1)).
- **Segment-segment intersection (boolean)** — proper crossing by opposite orientations of endpoints, plus collinear-overlap and endpoint-touch cases (O(1)).
- **Segment-segment intersection point** — compute parameter t along one segment, clamp/validate against [0,1] on both (O(1)).
- **Collinear overlap handling** — when segments lie on the same line, intersect by 1D interval overlap on the shared axis (O(1)).
- **Ray-segment / ray-line intersection** — parametric solve with t≥0 constraint on the ray side (O(1)).
- **Line-circle intersection** — substitute parametric line into circle, solve quadratic; 0/1/2 points by discriminant (O(1)).
- **Segment-circle intersection** — line-circle then clamp parameters to the segment domain (O(1)).

#### Point–Object Relations and Distances
- **Point-point distance** — Euclidean norm of the difference; prefer squared distance when only comparing (O(1)).
- **Point on segment test** — collinear (cross=0) and within bounding box / dot-product bounds of endpoints (O(1)).
- **Point-line distance** — |cross(B−A, P−A)| / |B−A|; perpendicular offset (O(1)).
- **Point-segment distance** — project P onto segment, clamp parameter to [0,1], then point-point distance (O(1)).
- **Point-ray distance** — project with t≥0 clamp, else distance to the ray origin (O(1)).
- **Segment-segment distance** — min over endpoint-to-segment distances plus 0 if they intersect (O(1)).
- **Foot of perpendicular / closest point on line** — A + (dot(P−A,d)/dot(d,d))·d (O(1)).
- **Reflection of point across a line** — P plus twice the vector to its perpendicular foot (O(1)).
- **Manhattan / Chebyshev / L_p distances** — alternative metrics; rotate 45° to convert between L1 and L∞.

### Polygons: Representation and Basic Measures
- **Polygon representation** — ordered vertex list (implicit closing edge); orientation (CW/CCW) carries sign information.
- **Simple vs. non-simple polygon** — simple has no self-intersections; many algorithms assume simplicity.
- **Convex vs. concave polygon** — convex iff all turns share one sign; test by scanning orientations (O(n)).
- **Signed area (shoelace formula)** — half the sum of cross products of consecutive vertices; sign encodes orientation (O(n)).
- **Polygon perimeter** — sum of edge lengths around the boundary (O(n)).
- **Centroid of polygon (area centroid)** — area-weighted formula over triangle fans from origin; differs from vertex average (O(n)).
- **Centroid of vertices / of perimeter** — simple averages; distinct from the area centroid and used in different contexts (O(n)).
- **Polygon orientation normalization** — reverse vertex order to enforce CCW based on signed-area sign (O(n)).
- **Diameter / bounding measures** — width, height, axis-aligned bounding box from coordinate extremes (O(n)).

#### Point-in-Polygon
- **Ray casting (even-odd rule)** — shoot a ray, count boundary crossings; odd ⇒ inside; handle vertex/edge grazing carefully (O(n)).
- **Winding number method** — sum signed angle turns / crossings; robust for self-intersecting polygons, distinguishes multiplicity (O(n)).
- **Boundary membership detection** — explicit point-on-edge check before inside/outside classification (O(n)).
- **Point in convex polygon (O(log n))** — binary search by angular fan from one vertex to locate the containing triangle wedge.
- **Point in polygon via crossing parity with sweep** — preprocess for many queries; relates to planar point location.

#### Triangulation and Decomposition
- **Ear clipping triangulation** — repeatedly remove "ears" (convex vertices with empty triangles) from a simple polygon (O(n²)).
- **Monotone polygon triangulation** — triangulate a y-monotone polygon by a stack sweep (O(n)).
- **Monotone decomposition (trapezoidalization)** — split a simple polygon into monotone pieces via a sweep with helper vertices (O(n log n)).
- **Seidel's randomized triangulation** — expected O(n log* n) via randomized trapezoidal decomposition.
- **Chazelle linear-time triangulation** — theoretical O(n) simple-polygon triangulation; complex and rarely implemented.
- **Polygon-to-monotone for arbitrary simple polygons** — handle merge/split vertices to produce monotone subpolygons (O(n log n)).
- **Fan / strip triangulation of convex polygons** — trivial triangulation from one vertex (O(n)).
- **Constrained triangulation** — triangulate respecting required edges (relates to constrained Delaunay) (O(n log n)).

##### Convex Decomposition (Partition into Convex Pieces)
- **Convex decomposition problem** — split a simple (possibly non-convex) polygon into convex subpolygons, with or without added Steiner points.
- **Trivial decomposition via triangulation** — any triangulation is a convex partition, but uses many pieces; baseline only (O(n) given a triangulation).
- **Hertel-Mehlhorn heuristic** — triangulate, then greedily delete diagonals whose removal keeps both incident faces convex; ≤4× the optimal piece count (O(n) after triangulation).
- **Keil's optimal decomposition (diagonals only)** — dynamic programming over subpolygons minimizing convex pieces without Steiner points (O(n³) / O(r²n log n), r = reflex vertices).
- **Keil-Snoeyink optimal partition** — improved DP achieving O(n + r² min(r², n)) time for the minimum-diagonal convex partition.
- **Greene's monotone-based partition** — sweep-line approximation producing a convex partition from a monotone decomposition (O(n log n)).
- **Optimal partition with Steiner points** — Chazelle-Dobkin optimal convex partition allowing added vertices, fewer pieces possible (polynomial, intricate).
- **Reflex-vertex resolution** — every convex piece boundary must eliminate all reflex (interior > 180°) vertices; count of reflex vertices bounds piece count.

#### Polygon Clipping and Boolean Operations
- **Sutherland-Hodgman clipping** — clip a subject polygon against each edge of a convex clip polygon successively (O(n·k)).
- **Weiler-Atherton clipping** — handles concave clip windows and produces multiple output polygons via boundary traversal.
- **Greiner-Hormann clipping** — robust polygon boolean (union/intersection/difference) via intersection linking and entry/exit flags.
- **Vatti / Martinez-Rueda boolean ops** — general clipping for arbitrary (incl. self-intersecting) polygons via sweep-line.
- **Convex polygon intersection** — intersect two convex polygons in linear time by merging boundaries (O(n+m)).
- **Polygon union area** — combine boundaries / inclusion-exclusion; often via sweep over rectangles or general regions.

#### Polygon Offset, Inset, and Straight Skeleton
- **Polygon offset / buffering** — grow/shrink a polygon by distance d; outer offset inflates, inner offset (inset) erodes the region.
- **Rounded offset via Minkowski with a disk** — offset by a disk of radius d: edges translate, convex corners become circular arcs (O(n) edges + arcs).
- **Mitered (sharp-corner) offset** — extend edges to their intersection at convex corners; arbitrarily long spikes at sharp reflex angles.
- **Straight skeleton** — wavefront of edges shrinking at constant speed; bisector traces form a skeleton, partitioning the polygon (O(n log n) typical, O(n²) worst).
- **Wavefront propagation (Aichholzer-Aurenhammer)** — simulate inward edge motion with an event queue over a kinetic triangulation (O(nr + n log n)).
- **Edge events vs. split events** — edge collapses to zero length (edge event) vs. a reflex vertex hitting an opposing edge (split event), splitting the wavefront.
- **Motorcycle graph** — abstraction of reflex-vertex trajectories used to speed split-event computation in faster skeleton algorithms.
- **Mitered offset curves from the skeleton** — slice the straight skeleton at a given inset distance to read off the offset polygon(s) (O(n) per distance after build).
- **Roof / terrain model from skeleton** — interpret skeleton arc-time as height to build a hip-roof polyhedron over the polygon.
- **Medial axis vs. straight skeleton** — medial axis uses circular-arc bisectors (curved for reflex corners); straight skeleton keeps all bisectors straight.

### Convex Hull
- **Convex hull definition** — smallest convex set containing the points; reported as boundary vertices in order.
- **Graham scan** — sort by polar angle about a pivot, push/pop with orientation tests to keep left turns (O(n log n)).
- **Andrew's monotone chain** — sort by (x,y), build lower then upper hull with a turn stack; avoids angle sorting (O(n log n)).
- **Gift wrapping / Jarvis march** — repeatedly pick the most clockwise next point; output-sensitive (O(nh), h = hull size).
- **QuickHull** — divide-and-conquer by farthest point from a partition line; O(n log n) average, O(n²) worst.
- **Incremental hull** — add points one by one, repairing visibility region; basis of randomized incremental method.
- **Chan's algorithm** — combine gift wrapping with grouped Graham scans for optimal output-sensitive O(n log h).
- **Collinear-point handling** — decide whether to keep or drop boundary-collinear points; affects degeneracies downstream.
- **Upper / lower hull separation** — splitting the hull is useful for the convex hull trick and monotone queries.
- **Convex layers (onion peeling)** — iteratively strip hulls to depth-order points (O(n log n)).
- **Convex hull trick (CHT)** — lower/upper envelope of lines for DP optimization; Li Chao tree variant (see Dynamic Programming / Data Structures).

#### Dynamic and Kinetic Hulls
- **Dynamic convex hull (insertions/deletions)** — maintain hull under updates via balanced BST of hull edges (O(log² n) or O(log n) amortized per op).
- **Semi-dynamic (insert-only) hull** — simpler incremental maintenance when no deletions occur.
- **Kinetic convex hull** — maintain the hull as points move continuously along trajectories using a kinetic data structure.
- **Online hull queries** — answer extreme-point / tangent queries against an evolving hull.

#### Rotating Calipers
- **Rotating calipers paradigm** — sweep parallel support lines around a convex polygon, advancing antipodally (O(n) after hull).
- **Polygon diameter (farthest pair)** — maximum distance via antipodal vertex pairs on the hull (O(n)).
- **Polygon width (min strip)** — minimum distance between parallel supporting lines (O(n)).
- **Minimum-area bounding box** — optimal rectangle has a side flush with a hull edge; rotate calipers over edges (O(n)).
- **Minimum-perimeter bounding box** — same caliper sweep optimizing perimeter instead of area (O(n)).
- **Closest pair on convex hull** — antipodal scan over hull vertices (O(n)).
- **Maximum distance between two convex polygons** — synchronized caliper march over both hulls (O(n+m)).
- **Minimum distance between two convex polygons** — co-rotating calipers / boundary merge (O(n+m)).
- **Onion / antipodal pair enumeration** — list all antipodal vertex pairs for width/diameter problems (O(n)).
- **Maximum-area inscribed triangle** — three pointers advancing monotonically around a convex polygon under rotating-calipers monotonicity (O(n), or O(n²) safe variant).
- **Maximum-perimeter inscribed triangle** — analogous three-pointer caliper sweep maximizing perimeter over hull vertices (O(n²) robust / O(n) with care).
- **Maximum-area inscribed polygon (k-gon)** — extend the monotone multi-pointer / DP approach to choose k hull vertices maximizing area (O(kn²) DP, O(n) sweep for triangle).
- **Maximum-perimeter inscribed k-gon** — same selection framework optimizing perimeter instead of area (O(kn²) DP).
- **Minimum-area enclosing triangle** — smallest triangle containing a convex polygon; each side flush with a hull edge, found by rotating calipers (O(n)).

### Closest / Farthest Pairs and Proximity
- **Closest pair (divide and conquer)** — split by x, recurse, merge across the dividing strip checking a bounded neighborhood (O(n log n)).
- **Closest pair (sweep line + balanced BST)** — sweep on x, maintain active set within current min-distance window (O(n log n)).
- **Closest pair (randomized grid hashing)** — bucket by current distance into a grid, expected linear (O(n) expected).
- **Farthest pair of points** — equals convex-hull diameter via rotating calipers (O(n log n)).
- **All-nearest-neighbors** — nearest neighbor for every point, via Delaunay or k-d tree (O(n log n)).
- **Bichromatic closest pair** — closest red-blue pair; D&C or proximity structures (O(n log n)).
- **Diameter of a point set** — max pairwise distance = hull diameter (O(n log n)).
- **Width of a point set** — min slab containing all points = hull width (O(n log n)).

### Line-Sweep Algorithms
- **Sweep-line paradigm** — move a line across the plane processing events in sorted order, maintaining a status structure of active objects.
- **Event queue and status structure** — priority queue of event x/y-coordinates plus a balanced BST ordered along the sweep front.
- **Bentley-Ottmann segment intersection** — report all K intersections of N segments via event-driven neighbor swaps (O((N+K) log N)).
- **Shamos-Hoey intersection detection** — detect whether any two of N segments intersect (no reporting) (O(N log N)).
- **Balaban / Chazelle-Edelsbrunner optimal reporting** — list all K intersections in O(N log N + K) time.
- **Union of intervals (1D)** — sort endpoints, sweep counting coverage to get total covered length (O(n log n)).
- **Area of union of rectangles** — sweep x with a segment tree of y-coverage measuring covered height (O(n log n)).
- **Perimeter of union of rectangles** — sweep tracking covered-length changes to accumulate boundary length (O(n log n)).
- **Klee's measure problem** — measure of union of axis-aligned boxes; sweep + interval tree, hard in high dimensions (O(n log n) in 2D).
- **Rectangle stabbing / point-in-rectangles** — count rectangles containing each query point via sweep + BIT.
- **Maximum overlap of intervals/rectangles** — sweep with a max-coverage segment tree (O(n log n)).
- **Skyline problem** — silhouette of overlapping buildings via sweep + max-heap / divide and conquer (O(n log n)).
- **Closest pair via sweep** — see Closest / Farthest Pairs (sweep variant).
- **Radial / angular sweep** — events ordered by angle about a center; rotating-line variant of the sweep.

#### Angular Techniques
- **Angular sort** — sort points by angle about a center using half-plane + cross-product comparator (integer-exact) (O(n log n)).
- **Half-plane partition by angle** — split into upper/lower (or left/right) half before angular comparison to keep order total.
- **Polar-angle sweep for visibility / counting** — rotate a ray and update as it crosses points (O(n log n)).
- **Angular sweep for maximum points in a half-plane / sector** — two-pointer over sorted angles (O(n log n)).
- **Minimum enclosing angle / narrowest wedge** — sliding window over sorted angles (O(n log n)).

### Half-Plane Intersection and Linear Programming
- **Half-plane representation** — directed line with an "inside" side; intersection of many is a (possibly empty/unbounded) convex region.
- **Sort-and-incremental half-plane intersection** — sort by angle, maintain a deque popping redundant constraints (O(n log n)).
- **Incremental / randomized half-plane intersection** — add constraints one by one, clipping the current region (O(n²) worst, O(n) expected randomized).
- **Divide-and-conquer half-plane intersection** — split, recurse, merge two convex regions (O(n log n)).
- **Duality: points ↔ lines** — map point (a,b) ↔ line y=ax−b; converts hull/half-plane problems into each other.
- **Half-plane intersection via dual convex hull** — lower/upper envelope of dual lines equals the hull of dual points.
- **2D linear programming (Megiddo / Seidel)** — optimize a linear objective over half-planes in expected linear time (O(n) expected, O(n) deterministic via prune-and-search).
- **Linear programming feasibility** — does the half-plane intersection (constraint region) exist (O(n) expected).
- **LP-type / Sharir-Welzl framework** — abstract optimization (min enclosing circle, LP, etc.) solved in expected linear randomized time.
- **Kernel of a polygon** — set of points seeing the whole polygon = intersection of edge half-planes (O(n)).

### Minkowski Sum and Difference
- **Minkowski sum definition** — A⊕B = {a+b}; for convex polygons, merge edges by angle (O(n+m)).
- **Convex Minkowski sum construction** — sort combined edge vectors by angle and chain them (O(n+m)).
- **Minkowski difference / erosion** — A⊖B for collision and clearance; complement-based construction.
- **Collision detection via Minkowski** — two convex shapes intersect iff origin ∈ A⊕(−B); basis of GJK-style tests.
- **Distance between convex polygons via Minkowski** — distance from origin to A⊕(−B) boundary (O(n+m)).
- **Configuration-space obstacles** — inflate obstacles by robot shape using Minkowski sum for motion planning.
- **Sum of point set + convex polygon** — translate hull by each point; used in reachability problems.

### Circles and Conics
- **Circle representation** — center and radius; or general conic x²+y²+Dx+Ey+F=0.
- **Circle through three points (circumcircle)** — solve from perpendicular bisectors / determinant (O(1)).
- **Circle-line intersection** — see Lines: Line-circle intersection.
- **Circle-circle intersection** — radical line + distance check; 0/1/2 points or coincident (O(1)).
- **Radical axis / radical center** — locus of equal power w.r.t. two circles; concurrency point of three (O(1)).
- **Power of a point** — |PO|²−r²; sign tells inside/outside; used for tangent length (O(1)).
- **Tangent lines from external point** — two tangents; tangent length = sqrt(power) (O(1)).
- **Common tangents of two circles (inner/outer)** — up to four lines depending on configuration (O(1)).
- **Tangent circles / Apollonius problem** — circle tangent to three given circles/lines/points; classic construction.
- **Circle-polygon intersection area** — sum signed circular-segment contributions per polygon edge (O(n)).
- **Circle-circle intersection area (lens)** — two circular segments via central angles (O(1)).
- **Arc representation and arc length** — directed arc by center, radius, start/end angle; length = r·Δθ (O(1)).
- **Ellipse / general conic basics** — affine map to a circle to reuse circle algorithms (O(1)).

#### Smallest Enclosing Shapes
- **Smallest enclosing circle (Welzl)** — randomized incremental, defined by ≤3 boundary points; expected linear (O(n) expected).
- **Min enclosing circle (Megiddo)** — deterministic prune-and-search (O(n)).
- **Smallest enclosing ball (3D and dD)** — Welzl generalizes with ≤d+1 support points (O(n) expected for fixed d).
- **Smallest enclosing rectangle** — min-area/min-perimeter bounding box via rotating calipers (O(n) on hull).
- **Largest empty circle** — center at a Voronoi vertex or on the hull (O(n log n)).
- **Largest empty circle in a bounding region** — constrain the center to a box/polygon; candidates are Voronoi vertices and Voronoi-edge/boundary intersections (O(n log n)).
- **Largest inscribed circle in a convex polygon** — Chebyshev center via LP / medial-axis (O(n log n)).
- **Smallest enclosing circle of circles/disks** — minimum disk covering a set of disks; LP-type / Welzl-style with disk support sets, expected linear (O(n) expected).
- **Smallest enclosing annulus / width** — min-width annulus or slab fitting points; LP-type optimization over center (O(n) expected).
- **Maximum-area inscribed polygon in a convex polygon** — largest convex k-gon inside a convex region via rotating-calipers / DP (see Rotating Calipers) (O(kn²)).
- **Maximum-perimeter inscribed polygon** — perimeter-maximizing inscribed polygon, analogous selection over hull vertices (see Rotating Calipers).
- **Minimum enclosing simplex / ellipse** — smallest covering triangle (calipers) or Löwner-John ellipse (convex optimization).

### Voronoi Diagrams and Delaunay Triangulation
- **Voronoi diagram** — partition of the plane into nearest-site cells; O(n) cells, edges, vertices for n sites (O(n log n) to build).
- **Fortune's sweepline algorithm** — beach-line parabola sweep constructing the Voronoi diagram (O(n log n)).
- **Delaunay triangulation** — dual of Voronoi; maximizes the minimum angle, empty-circumcircle property (O(n log n)).
- **Delaunay via divide and conquer** — Guibas-Stolfi split/merge using quad-edge structure (O(n log n)).
- **Delaunay via randomized incremental** — insert points, flip illegal edges using in-circle test (O(n log n) expected).
- **Edge-flip / Lawson legalization** — flip non-Delaunay diagonals until empty-circle holds (O(n²) worst).
- **Delaunay = lower convex hull of lifted points** — lift to paraboloid z=x²+y²; project lower hull (reduces 2D Delaunay to 3D hull).
- **Constrained Delaunay triangulation** — Delaunay respecting required edges (O(n log n)).
- **Voronoi/Delaunay applications** — nearest neighbor, EMST, largest empty circle, motion planning, mesh generation.
- **Euclidean minimum spanning tree (EMST)** — subset of Delaunay edges; MST on those (O(n log n)).
- **Power diagram / weighted Voronoi** — Voronoi with additive weights (Laguerre); dual is the regular triangulation.
- **Higher-order Voronoi (k-nearest)** — cells of the k nearest sites; combinatorially richer.
- **Farthest-point Voronoi diagram** — cells by farthest site; vertices help smallest enclosing circle.

### Spatial Data Structures and Range Queries
- **k-d tree** — alternating-axis space partition for nearest-neighbor and orthogonal range queries (build O(n log n); NN ~O(log n) avg).
- **Nearest neighbor search (k-d tree)** — branch-and-bound with pruning by splitting-plane distance (see Data Structures / Trees).
- **Range tree** — multi-level BST for orthogonal range reporting; with fractional cascading O(log n + k) query.
- **Fractional cascading** — share search positions across levels to drop a log factor in layered range queries.
- **Quadtree / octree** — recursive quadrant/octant subdivision for spatial indexing and collision broad-phase.
- **R-tree** — bounding-box hierarchy for spatial databases / overlap queries.
- **Interval tree** — report intervals/segments overlapping a query (O(log n + k)).
- **Segment tree (stabbing intervals)** — classic 1D structure for sweep-based area/perimeter (see Data Structures).
- **Priority search tree** — 3-sided orthogonal range queries (O(log n + k)).
- **Persistent segment tree for planar dominance** — offline/online 3-sided and k-th queries by sweeping and versioning.
- **Wavelet tree for orthogonal queries** — count/select in coordinate-compressed rectangles (see Data Structures).
- **KD-tree / BBD-tree approximate NN** — approximate nearest neighbor for high dimensions.

#### Point Location and Planar Subdivisions
- **Planar point location** — locate the face of a subdivision containing a query point.
- **Slab decomposition** — vertical slabs + per-slab sorted edges; O(log n) query, O(n²) space.
- **Kirkpatrick's triangulation refinement** — independent-set hierarchy giving O(log n) query, O(n) space.
- **Trapezoidal map + randomized search structure** — incremental, expected O(log n) query, O(n) space.
- **Persistent-segment-tree point location** — sweep building persistent slab structures (O(log n) query).
- **Doubly-connected edge list (DCEL)** — half-edge structure storing faces/edges/vertices of a planar subdivision.
- **Quad-edge / winged-edge structures** — alternative topological representations for subdivisions and duals.
- **Euler's formula (V−E+F=2)** — relates counts in a connected planar subdivision; sanity check and counting tool.

### Visibility, Art Gallery, and Motion
- **Visibility polygon from a point** — region directly visible inside a polygon via angular sweep (O(n log n)).
- **Visibility graph** — nodes = vertices, edges = mutually visible pairs; for shortest paths among obstacles (O(n² log n) or O(n²)).
- **Art gallery theorem** — ⌊n/3⌋ guards always suffice and are sometimes necessary for an n-vertex polygon.
- **Guard placement via triangulation 3-coloring** — color the triangulation graph; smallest color class gives guard set.
- **Shortest path inside a simple polygon (funnel)** — string-pulling through a triangulation sleeve (O(n)).
- **Shortest path among polygonal obstacles** — Dijkstra on the visibility graph (O(n² log n)).
- **Watchman route / coverage** — shortest route seeing the whole polygon.
- **Motion planning / configuration space** — Minkowski-inflated obstacles + visibility/roadmap search.

### Triangle, Quadrilateral, and Lattice Geometry
- **Triangle area** — half the absolute cross product of two edge vectors (O(1)).
- **Triangle centers** — centroid, incenter, circumcenter, orthocenter from standard formulas (O(1)).
- **Barycentric coordinates** — express a point as a weighted combination of triangle vertices; sign gives inside test (O(1)).
- **Heron's formula** — area from side lengths; numerically prefer the stable (sorted) variant (O(1)).
- **Law of sines / cosines** — relate sides and angles; used in arc and tangent geometry (O(1)).
- **Pick's theorem** — area = interior lattice points + boundary/2 − 1 for lattice polygons (O(n)).
- **Lattice points on a segment** — gcd(|Δx|, |Δy|) + 1 endpoints; gcd interior points (O(1)).
- **Lattice points inside polygon** — invert Pick's theorem using area and boundary count (O(n)).
- **Lattice points under a line / in a triangle** — floor-sum / Stern-Brocot counting (relates to Number Theory).
- **Integer-only geometry** — keep cross/area as 64/128-bit integers to stay exact; avoid floats entirely when possible.

### 3D Geometry
- **3D points, vectors, dot and cross** — extend planar primitives; cross gives a normal vector (O(1)).
- **Plane representation** — point+normal or ax+by+cz+d=0; signed distance via normalized normal (O(1)).
- **Point-plane distance and projection** — dot with unit normal; foot of perpendicular by subtracting the offset (O(1)).
- **Line-plane intersection** — parametric line solved against plane equation; parallel/contained special cases (O(1)).
- **Plane-plane intersection (line)** — direction = cross of normals; a point via solving the 2×2 system (O(1)).
- **Three-plane intersection (point)** — solve the 3×3 linear system; degenerate if normals are dependent (O(1)).
- **Line-line in 3D (closest points / distance)** — common-perpendicular construction; skew vs. intersecting vs. parallel (O(1)).
- **Point-line and point-segment distance in 3D** — projection with clamping, analogous to 2D (O(1)).
- **Tetrahedron volume** — one-sixth absolute scalar triple product of edge vectors (O(1)).
- **Polyhedron volume (divergence theorem)** — sum signed tetrahedra from origin over triangulated faces (O(F)).
- **Point in convex polyhedron** — inside iff on the inner side of every face half-space (O(F)).
- **3D convex hull (incremental)** — add points, remove visible faces, build a cone of new faces (O(n²) or O(n log n) randomized).
- **3D convex hull (gift wrapping)** — wrap faces around the boundary; output-sensitive (O(nF)).
- **3D convex hull (divide and conquer)** — split, hull each half, merge along a tangent band (O(n log n)).
- **Quickhull 3D** — recursive farthest-face expansion; widely used in practice (O(n log n) average).
- **Convex polytope volume / surface area** — integrate over hull faces (O(F)).
- **3D Delaunay / Voronoi (intro)** — lift to 4D paraboloid; O(n²) tetrahedra worst case.
- **Rotations: matrices, axis-angle, quaternions** — represent and compose 3D rotations; quaternions avoid gimbal lock (O(1)).
- **Bounding volumes (AABB, OBB, sphere)** — broad-phase collision and culling primitives.
- **Ray-triangle intersection (Möller-Trumbore)** — barycentric solve for ray casting / picking (O(1)).
- **Ray-AABB / ray-sphere intersection** — slab method / quadratic solve (O(1)).

### Spherical and Geographic Geometry
- **Great-circle (orthodromic) distance** — shortest path on a sphere via haversine or spherical law of cosines (O(1)).
- **Haversine formula** — numerically stable great-circle distance for small angles (O(1)).
- **Spherical coordinates ↔ Cartesian** — convert lat/long to unit-sphere vectors for cross/dot reasoning (O(1)).
- **Spherical triangle area (excess)** — area = (sum of angles − π)·r²; Girard's theorem (O(1)).
- **Spherical polygon area** — sum of spherical triangle excesses / signed contributions (O(n)).
- **Point-in-spherical-polygon** — winding/great-circle side tests on the sphere (O(n)).
- **Bearing / initial heading** — azimuth between two lat/long points (O(1)).
- **Loxodrome (rhumb line) basics** — constant-bearing path; longer than great circle (O(1)).

### Numerical Robustness and Degeneracies
- **Epsilon comparison** — compare floats within a tolerance; choose absolute vs. relative carefully.
- **Integer arithmetic preference** — use exact integer cross/dot to dodge rounding when inputs are integral.
- **Overflow management (128-bit / big integers)** — guard cross-product and area products against 64-bit overflow.
- **Coordinate compression** — remap large/sparse coordinates to small indices for sweep/segment-tree structures (O(n log n)).
- **Symbolic perturbation (Simulation of Simplicity)** — consistently break ties to avoid degenerate-case branching.
- **Exact / adaptive predicates (Shewchuk)** — get correct orientation/incircle signs with floating-point speed.
- **Snap rounding** — round intersection points to a grid while preserving topology of arrangements.
- **Handling collinear / coincident / duplicate inputs** — explicit dedup and tie handling before hull/sweep algorithms.
- **Avoiding atan2 in comparisons** — replace angle sorting with cross-product half-plane comparators for exactness.
- **Stability of formulas** — prefer cross-product distance over slope; stable Heron's; avoid catastrophic cancellation.

### Arrangements and Advanced Structures
- **Arrangement of lines** — subdivision induced by n lines: O(n²) vertices/edges/faces; built incrementally (O(n²)).
- **Arrangement of segments / curves** — generalize line arrangements with intersection handling (O((n+k) log n)).
- **Zone theorem** — a new line crosses O(n) cells of an arrangement; bounds incremental construction cost.
- **Levels and k-levels in arrangements** — the k-th level relates to k-nearest and ham-sandwich problems.
- **Ham-sandwich cut** — a hyperplane simultaneously bisecting d point sets in ℝᵈ (O(n) in 2D).
- **Centerpoint theorem** — a point in every half-plane containing > 2n/3 points exists.
- **ε-nets and ε-approximations** — small samples capturing all "heavy" ranges; basis of randomized geometric algorithms.
- **Random sampling / Clarkson-Shor framework** — expected-complexity bounds for randomized geometric divide and conquer.
- **Cuttings (1/r-cuttings)** — partition space into few simple cells each crossed by few objects; speeds range searching.
- **Partition trees / simplicial partitions** — sublinear simplex range queries (O(n^{1−1/d+ε}) query).
- **Well-separated pair decomposition (WSPD)** — O(n) pairs approximating all distances; gives spanners, closest pair, NN (O(n log n)).
- **Geometric spanners** — sparse graphs approximating Euclidean distances (Yao/θ-graphs, WSPD spanners).
- **Coresets** — tiny weighted subsets approximating extent measures (diameter, min enclosing ball).

### Classic Problem Patterns (Competitive)
- **Maximum points on a line** — group by slope (gcd-normalized direction) per anchor point (O(n² log n) or O(n²)).
- **Counting/area of triangles with given properties** — orientation/area predicates over triplets.
- **Largest empty rectangle / square** — sweep + stack (histogram) or maximal-rectangle techniques (O(n log n)).
- **Maximum points covered by a fixed shape** — slide a window/circle using angular events (O(n² log n) for unit circle).
- **Polygon containing the most points / fitting** — rotating-calipers and angular-sweep counting.
- **Two-pointer on sorted angles/coordinates** — many "max points in slab/sector/disk" problems reduce to this.
- **Geometry + DP (convex hull trick / Li Chao)** — envelope of lines for cost optimization (see Dynamic Programming).
- **Geometry + binary search on answer** — parametric search over a radius/width with a feasibility geometric check.
- **Sweepline + offline queries (BIT/segment tree)** — answer rectangle/point queries by sorting events (O(n log n)).
- **Fractional cascading / persistence for stacked queries** — speed repeated layered geometric searches.

<sub>[↑ Back to top](#table-of-contents)</sub>

---

## 26. Combinatorial Game Theory

### Foundations & Game Classification

#### Defining a Combinatorial Game
- **Combinatorial game** — finite, two-player, perfect-information, no-chance game where players alternate moves; the position fully determines legal moves.
- **Perfect information** — both players see the entire state; no hidden information or simultaneous moves.
- **No chance / determinism** — no dice or randomness; outcome depends only on the moves chosen.
- **Position (game state)** — a node in the game graph; encodes everything needed to determine legal continuations.
- **Move / option** — a legal transition from one position to another; the set of options defines the position's children.
- **Game tree vs game DAG** — unfolded tree of plays vs the collapsed graph where transposed positions share a node (memoization domain).
- **Terminal position** — a position with no legal moves; outcome is decided by the play convention.
- **Finiteness / ending condition** — every play must terminate; required for Sprague-Grundy theory (no infinite descent).
- **Loopy games** — positions can repeat (cycles in the graph); ordinary Grundy theory fails, needs draw/loop handling (see Loopy & Cyclic Games).

#### Impartial vs Partisan
- **Impartial game** — same set of moves available to whichever player is to move (Nim, Wythoff); governed by Sprague-Grundy theorem.
- **Partisan game** — the two players have different move sets (Hackenbush with colored edges, Domineering, Chess endgames); needs surreal-number / partizan theory.
- **Short game** — finite number of positions reachable, each with finitely many options; standard CGT assumption.

#### Play Conventions
- **Normal play convention** — the player unable to move (no legal move) loses; the standard setting for Grundy theory.
- **Misere play convention** — the player unable to move wins; far harder, Grundy theory does not directly transfer.
- **Scoring / last-move-value games** — outcome is a numeric score rather than win/lose; needs scoring CGT (see Advanced/Scoring).
- **Maximum / Maker-Breaker games** — one player tries to achieve a target set, the other to prevent it (Tic-Tac-Toe family, Hex); different framework.

### Win/Loss Position Theory

#### N- and P-positions
- **P-position (previous-player win)** — a losing position for the player to move; the Previous player (who just moved) can force a win.
- **N-position (next-player win)** — a winning position for the player to move; the Next player can force a win.
- **Backward induction characterization** — a position is P iff every move leads to an N-position; N iff some move leads to a P-position.
- **Terminal-position labeling** — under normal play a terminal position is P (mover loses); under misere a terminal is N.
- **Outcome class** — the win/loss/draw classification of a position assuming optimal play by both sides.

#### Computing Outcomes
- **Game DP (memoized win/loss)** — recursively label each position N/P by examining children; memoize over the game DAG (O(states + edges)).
- **Retrograde analysis** — BFS/topological labeling from terminal positions backward; track for each position the count of moves still "undecided" (O(V+E)).
- **Retrograde with degree counters** — a position becomes P as soon as one child is known-N; becomes N only after all children resolve to ... (decrement out-degree until it hits 0).
- **Topological / DAG ordering** — process positions in reverse topological order so children are solved before parents (acyclic games only).
- **State encoding / bitmasking** — pack a game state into an integer key for memoization tables (e.g. heap multiset, board occupancy).
- **Symmetry reduction of states** — canonicalize equivalent positions (sorting heaps, board symmetry) to shrink the DP table.

### Sprague-Grundy Theory

#### Grundy / Nim Values
- **Nimber (Grundy number)** — the value g(x) of an impartial position, equal to the size of the equivalent single Nim heap; g=0 iff P-position.
- **mex (minimum excludant)** — smallest non-negative integer not in a set; g(x) = mex{ g(y) : y a move from x } (O(deg) per position).
- **Sprague-Grundy theorem** — every short impartial game under normal play is equivalent to a Nim heap of size equal to its Grundy value.
- **Equivalence (game value)** — two impartial positions are equivalent iff they have equal Grundy values; equal-value positions are interchangeable in any sum.
- **Grundy DP** — compute g over the DAG by mex of children's Grundy values; memoize (O(states · branching)).
- **Grundy of terminal** — terminal positions have Grundy value 0 under normal play.

#### Sums of Games & the XOR Rule
- **Disjunctive sum** — a move plays in exactly one component; the global game is the sum of its independent components.
- **Grundy of a sum (XOR / Nim-sum)** — g(G1 + G2 + ...) = g(G1) XOR g(G2) XOR ...; reduces composite games to one XOR (O(#components)).
- **Nim-sum (XOR) intuition** — binary XOR of heap sizes; nonzero XOR means N-position (mover wins).
- **Winning-move extraction** — from an N-position find a move making the total Nim-sum 0 (target heap = heap XOR total < heap) (O(piles)).
- **Game subtraction / equivalence test** — G - H is a P-position iff G and H have equal value; basis for proving equivalences.

#### Nimber Arithmetic
- **Nim-addition** — XOR of nimbers; the group operation on Grundy values under disjunctive sum.
- **Nim-multiplication** — Conway's field operation making nimbers a field (used in Turning/coin-turning games and Tartan/product games).
- **Fermat-2-powers field structure** — nimbers below 2^(2^k) form a finite field GF(2^(2^k)); enables closed-form nim-products.
- **Nim-multiplication algorithm** — recursive computation via Fermat 2-powers and distributivity (O(polylog) on word-sized values).

### Nim & Its Variants

#### Classic Nim
- **Single-pile Nim** — one heap; mover removes any positive count; trivially an N-position unless empty.
- **Multi-pile Nim (Bouton's theorem)** — P-position iff XOR of all pile sizes = 0; winning strategy via Nim-sum (O(piles)).
- **Misere Nim** — under misere play, win normally unless all heaps are size 1; if all heaps ≤1, parity of heap count decides (O(piles)).

#### Subtraction & Bounded Games
- **Subtraction game S(set)** — remove a number of tokens from a fixed allowed set; Grundy values are eventually periodic (precompute O(n·|S|)).
- **Eventual periodicity of subtraction games** — Grundy sequence becomes periodic; detect period to answer huge n in O(period).
- **Bounded Nim (≤k removal)** — single-pile with cap k; P-position iff size ≡ 0 (mod k+1) (O(1)).
- **Nim with multiplicative / divisor moves** — remove based on divisors/multiples; analyze via prime factorization or precomputed Grundy tables.
- **Mock Turtles** — coin-turning game; turning game where Grundy values relate to "odious" numbers (odd number of 1-bits) (O(n) via turning theory).
- **Ruler / Turning-Turtles family** — coin-turning games whose Grundy values follow ruler/odd-bit patterns; solved by turning-game (lim) theory.

#### Structured Nim Variants
- **Staircase Nim** — tokens on stairs move down; only odd-indexed steps matter, reduce to Nim on odd positions (XOR of odd steps) (O(steps)).
- **Moore's Nim-k (Nim_k)** — remove from up to k piles per move; P-position iff every binary column sum ≡ 0 (mod k+1) (O(piles·bits)).
- **Index-k / multicolumn parity** — generalize Bouton via base-(k+1) digit-sum-of-columns criterion.
- **Lasker's Nim** — remove tokens OR split a heap into two nonempty heaps; Grundy values have a known closed form with period-pattern (O(1) per heap).
- **Kayles** — knock down one or two adjacent pins in a row; Grundy sequence eventually periodic (precompute, period 12 after offset).
- **Dawson's Chess / .137 octal games** — row games with octal-code rules; Grundy values via octal-game machinery, often periodic.
- **Octal games** — general framework: a code d0.d1d2... encodes whether removing i tokens may empty/reduce/split a heap; Grundy via mex with splitting (precompute, check periodicity).
- **Hexadecimal / generalized games** — extension of octal codes allowing splits into more than two heaps.

#### Wythoff & Beatty Games
- **Wythoff's game** — two piles; remove any from one pile OR equal amounts from both; mixes Nim with a "queen move."
- **Wythoff P-positions (Beatty pairs)** — losing positions are (⌊nφ⌋, ⌊nφ²⌋) with golden ratio φ; complementary Beatty sequences (O(1) test).
- **Beatty sequence theorem** — ⌊nα⌋ and ⌊nβ⌋ partition the positive integers when 1/α+1/β=1; underlies Wythoff P-positions.
- **Wythoff Grundy values** — full Grundy function is irregular; no simple closed form, computed numerically for small boards.
- **Variants of Wythoff (translated P-positions)** — modified move sets shift P-positions; analyzed via invariant/Beatty generalizations.
- **Fibonacci Nim / Zeckendorf strategy** — bounded by twice last move; P-positions characterized via Fibonacci/Zeckendorf representation (O(log n)).

### Composite, Equivalent & Bogus Constructions
- **Composite (disjunctive) game analysis** — split a position into independent sub-games, Grundy each, XOR results.
- **Bogus Nim (equivalent positions)** — augment Nim with extra "harmless" reversible moves that do not change Grundy values; used to model real games as Nim.
- **Reversible moves / domination** — moves that the opponent can immediately undo (reverse) or that are dominated can be pruned without changing value.
- **Star (∗) and Nim-heap notation** — ∗n denotes a Nim heap of size n; ∗ = ∗1; building block for impartial values.
- **Equivalent-position reduction** — replace a complicated component by the Nim heap of equal Grundy value to simplify a sum.
- **Sums where one summand is loopy** — handling components that may not terminate (see Loopy Games).

### Partisan Game Theory (Surreal Numbers)

#### Game Values & Surreal Numbers
- **Conway game {L | R}** — a game defined by its sets of Left and Right options; the recursive birthday/inductive definition of all games.
- **Surreal numbers** — the totally ordered field arising from games of the form {L|R} with no Left option ≥ a Right option; contains reals, ordinals, infinitesimals.
- **Number values** — games equal to dyadic rationals (and beyond); "cold" games where neither player wants to move.
- **Simplicity rule** — the value of a number game is the simplest (lowest birthday) number strictly between Left and Right stops.
- **Game order & comparison** — G ≥ 0 (Left wins), ≤ 0, = 0 (P-position), or ‖ 0 (fuzzy / first-player win); the four outcome classes.
- **Negation and addition of games** — −G swaps Left/Right roles; G+H is the disjunctive sum; group structure on game values.
- **Star, up, down, and switches** — ∗ (fuzzy with 0), ↑ = {0|∗}, ↓ = −↑ (infinitesimals), switches like {1|−1} (hot games).
- **Infinitesimals & atomic weight** — ups/downs/stars are infinitesimally close to 0; atomic weight (uptimal) measures them for sums.

#### Hot Games & Thermography
- **Hot game** — both players gain by moving (positive temperature); switches like ±x.
- **Temperature** — how urgently a player wants to move in a component; magnitude of the switch.
- **Thermography / cooling & heating** — cooling a game by t reveals its mean value and temperature; thermographs guide move ordering in sums (constructive but exponential in general).
- **Mean value & left/right stops** — long-run value of repeatedly playing a hot game; stops bound the score from each side.

### Hackenbush

- **Hackenbush (general)** — players cut edges; any edge no longer connected to the ground falls; normal-play impartial or partisan depending on coloring.
- **Blue-Red Hackenbush (partisan)** — Left cuts blue, Right cuts red; positions evaluate to surreal numbers (dyadic rationals).
- **Hackenbush string / sign-expansion** — a path of blue/red edges encodes a real number via its sign sequence (binary-like expansion).
- **Green (impartial) Hackenbush** — all edges green, either player cuts any edge; impartial, solved by Sprague-Grundy.
- **Colon principle** — branches meeting at a vertex can be replaced by a single stalk whose length is the Nim-sum of the branch Grundy values.
- **Fusion principle** — vertices on a cycle may be fused to a single vertex (loops become a single edge / 0), reducing graph Hackenbush to a tree (then apply colon principle).
- **Green Hackenbush on graphs** — combine fusion (cycles) and colon (trees) to compute the Grundy value of an arbitrary green graph (O(V+E)).
- **Blue-Red-Green (full) Hackenbush** — mixed edges; values combine numbers and infinitesimals/stars; analyzed via partizan + Grundy theory.

### Adversarial Search (Heuristic Games)

#### Minimax Family
- **Minimax** — exhaustively evaluate the game tree assuming each player optimizes their own outcome; back up min/max values (O(b^d)).
- **Negamax formulation** — minimax with a single recursion using value negation (cleaner implementation of zero-sum minimax).
- **Alpha-beta pruning** — prune branches that cannot affect the minimax decision via [α, β] window; best case O(b^(d/2)) with good ordering.
- **Move ordering / killer & history heuristics** — order moves to maximize alpha-beta cutoffs (best-first improves pruning dramatically).
- **Iterative deepening** — repeated depth-limited searches feeding move ordering and giving anytime results.
- **Transposition tables (Zobrist hashing)** — cache evaluated positions to avoid re-search of transposed states (O(1) probe per node).
- **Principal variation search / NegaScout** — assume first move is best, verify others with null windows (faster than plain alpha-beta with good ordering).
- **MTD(f)** — drive alpha-beta with zero-width null-window searches around a guess to converge on the minimax value.
- **Quiescence search** — extend search at "noisy" leaf positions to avoid the horizon effect.
- **Evaluation function / heuristic** — static estimate of non-terminal positions for depth-limited search (domain-specific scoring).

#### Stochastic & Other Search
- **Expectimax** — game trees with chance nodes; average over outcomes instead of min/max at chance layers (O(b^d) with branching at chance nodes).
- **Expectiminimax** — combines max, min, and chance (expectation) layers for games with randomness (backgammon-style).
- **Monte Carlo Tree Search (UCT)** — selection/expansion/simulation/backpropagation with UCB1 exploration; for large branching games (see AI/search domain).
- **Proof-number search** — best-first search for proving/disproving win in AND/OR game trees (endgame solving).

### Strategy Techniques (Non-Grundy)
- **Strategy stealing** — argue first player can at least tie/win by stealing a hypothetical second-player strategy (existence proof, non-constructive).
- **Symmetry / mirroring (Tweedledum-Tweedledee)** — second player mirrors first player's moves to guarantee a win/draw in symmetric games.
- **Pairing strategy** — pair up cells/positions; respond within a pair to block the opponent (Maker-Breaker, Hales-Jewett style).
- **Parity argument** — total number of moves is fixed/bounded; parity decides who makes the last move (Misere Nim, domino tilings).
- **Invariant / monovariant** — a quantity preserved or monotonically changed by moves, used to characterize P-positions.
- **Potential-function arguments** — assign a potential that proves termination or determines the winner.
- **Weight / counting arguments** — assign weights to cells (e.g. fractional charge) to prove a player cannot win (Maker-Breaker Erdős-Selfridge).
- **Erdős-Selfridge theorem** — if the sum of 2^(−|winning set|) is below 1/2, Breaker wins a Maker-Breaker game (potential argument).

### Specialized & Advanced Topics

#### Coin-Turning & Product Games
- **Turning games (general)** — flip coins under rules; each game is a sum of single-coin games via the "Mock-Turtle/Ruler" decomposition.
- **Mock Turtles & Ruler** — canonical turning games with Grundy values tied to odious numbers and binary structure.
- **Tartan / product (×) games** — two-dimensional turning games whose Grundy value is the nim-product of the 1-D component values.

#### Misere Theory
- **Misere quotient** — the commutative monoid capturing misere-play equivalence of a game's positions (replaces simple Grundy values).
- **Genus theory** — classify misere octal-game positions by genus (Grundy value plus misere-parity sequence).
- **Tame vs wild games** — tame games behave like Nim under misere; wild games require full misere-quotient machinery.

#### Loopy & Cyclic Games
- **Draws / infinite play** — cyclic game graphs can yield draws; outcomes are Win/Lose/Draw rather than just N/P.
- **Generalized retrograde for loopy games** — three-color (win/lose/draw) backward labeling on cyclic graphs (O(V+E)).
- **Loopy Grundy values (∞, remoteness)** — extend Grundy theory to loopy games via "loopy nimbers" and remoteness/suspense functions.
- **Remoteness & suspense functions** — measure how quickly a player can force a win/loss; refine optimal play in long/loopy games.

#### Scoring & Misc
- **Scoring combinatorial games** — outcome is a final score; theory of well-tempered/scoring games extends CGT beyond win/lose.
- **Geography games (generalized)** — token on a graph, move along edges, no revisits; PSPACE-complete in general, solvable on DAGs by Grundy.
- **Undirected vertex/edge geography** — undirected vertex geography solvable in polynomial time via maximum matching; edge geography is PSPACE-complete.
- **Hardness of games** — many natural games are PSPACE-complete or EXPTIME-complete (Generalized Geography, Hex, generalized chess/checkers/go); recognize when no poly algorithm is expected.
- **Optimal-play game DP** — interval/DP over scores for take-away and partitioning games (e.g. stone-picking from ends) (see Dynamic Programming domain).
- **Game on trees / DAG reachability games** — pursuit/reachability and parity games solved by attractor computation / retrograde (O(V+E) for reachability).

### Competitive-Programming Practice Patterns
- **Identify impartial vs partisan first** — decide whether Grundy applies or a custom min/max DP is needed.
- **Decompose into independent sub-games** — recognize disjunctive sums to apply the XOR rule instead of brute force.
- **Small-case Grundy table + pattern hunt** — brute-force Grundy for small n, then conjecture closed form / periodicity and prove it.
- **Detect periodicity of Grundy sequences** — for subtraction/octal games, find the eventual period to handle large inputs (O(period)).
- **Reduce to known game** — map a problem onto Nim, Wythoff, staircase Nim, Kayles, etc., then reuse the known characterization.
- **Memoized recursion vs retrograde BFS** — choose recursion+memo for acyclic small state spaces, retrograde for dense/cyclic graphs.
- **Constructing the winning move** — beyond deciding the winner, output the actual optimal move (e.g. Nim-sum target heap).

<sub>[↑ Back to top](#table-of-contents)</sub>

---

# 🎲 Part — Search, Randomization & Offline

## 27. Backtracking, Exhaustive Search & Meet-in-the-Middle

### Backtracking Foundations

#### State-Space Model
- **State-space tree** — implicit tree whose nodes are partial solutions and whose edges extend one decision variable; backtracking is a DFS over it.
- **Promising vs non-promising nodes** — a node is promising if a partial solution can still extend to a valid full solution; prune the rest.
- **Solution / answer nodes** — leaves (or internal nodes) that represent complete, feasible solutions to be reported or counted.
- **Implicit vs explicit constraints** — implicit constraints define how candidates are formed; explicit constraints define which combinations are legal.
- **Choice / candidate set** — the set of legal extensions of the current partial assignment at each decision level.
- **Decision variables & domains** — ordered variables each with a finite domain; backtracking assigns them one at a time (CSP framing, see Constraint Satisfaction).
- **Static vs dynamic state-space tree** — fixed branching order vs an order chosen on the fly (variable/value ordering heuristics).
- **Search tree depth = #decisions** — tree height equals the number of variables; branching factor equals average domain size.

#### Generic Backtracking Template
1. **Choose** — pick the next variable / position to assign (the branching decision).
2. **Constrain** — enumerate candidate values consistent with the partial solution so far.
3. **Explore (recurse)** — apply a candidate, descend, and on return undo it (the "backtrack" step).
4. **Goal test** — when all variables are assigned (or a target is met), record/yield the solution.
- **Make–recurse–undo idiom** — mutate shared state, recurse, then restore it on backtrack to avoid copying the whole state.
- **Path vs state passing** — carry the partial solution by reference and undo, or pass immutable copies (simpler but costlier).
- **Find-one vs find-all vs count vs optimize** — early-exit on first solution, enumerate all, count them, or track the best objective.
- **Early termination / short-circuit** — propagate a "found" flag up the recursion to stop once one solution suffices.
- **Symmetry-broken enumeration** — fix an order or anchor a first choice to avoid generating each solution multiple times.

#### Complexity & Correctness
- **Worst-case exponential** — unpruned backtracking explores up to ∏|domain_i| leaves; pruning is what makes it tractable in practice.
- **Effective branching factor** — geometric-mean branching after pruning; the practical measure of search-tree growth.
- **Soundness & completeness** — every reported assignment is valid (sound) and every valid assignment is reachable (complete) given exhaustive branching.
- **Backtracking vs brute force** — both are exhaustive, but backtracking abandons partial solutions early instead of testing only complete ones.

### Pruning, Bounding & Constraint Propagation

#### Pruning Techniques
- **Feasibility pruning** — cut a branch the instant the partial solution violates a hard constraint (no valid completion possible).
- **Bounding (optimality) pruning** — for optimization, prune when the best achievable bound cannot beat the incumbent solution.
- **Dominance pruning** — discard a partial state dominated by another already-explored state with equal-or-better prospects.
- **Symmetry breaking** — impose canonical orderings (lexicographic, fixed representative) so equivalent branches are explored once.
- **Constraint-based candidate filtering** — generate only candidates already consistent, instead of generating-then-testing all.
- **Memoize visited partial states** — cache infeasible/explored signatures (often bitmask keys) to skip duplicate subtrees.
- **Equal-element / multiset pruning** — when values repeat, skip a value at a level if its identical predecessor was skipped, avoiding duplicate combinations.

#### Bounding Functions
- **Lower/upper bound estimators** — admissible optimistic estimate of the best completion of a partial solution, used to prune.
- **Relaxation-based bounds** — bound via a relaxed problem (e.g. LP relaxation, fractional knapsack) that is easy to optimize.
- **Greedy / heuristic bounds** — fast feasible-completion heuristics give bounds and good incumbents to enable earlier pruning.
- **Cost-so-far + admissible-remaining** — split the bound into committed cost plus an admissible estimate of the rest (A*/B&B shared idea).

#### Constraint Propagation
- **Forward checking** — after each assignment, remove inconsistent values from future variables' domains; backtrack if any domain empties.
- **Arc consistency (AC-3)** — enforce that every value of a variable has a consistent partner in each constraint (O(e·d³); see Constraint Satisfaction).
- **Maintaining Arc Consistency (MAC)** — interleave AC-3 with search, re-propagating after every assignment for stronger pruning.
- **Constraint propagation in Sudoku** — naked/hidden singles, locked candidates, etc., applied between guesses to shrink the tree (see Constraint Satisfaction).
- **Unit propagation / Boolean constraint propagation** — for SAT-style backtracking (DPLL), force implied literals before branching (see SAT).
- **Variable ordering: MRV / fail-first** — branch on the most-constrained variable (smallest domain) to fail fast and prune early.
- **Variable ordering: degree / max-degree** — tie-break on the variable involved in the most constraints with unassigned variables.
- **Value ordering: least-constraining-value** — try the value that rules out the fewest future options first, to find solutions sooner.
- **Restart strategies** — randomized restarts with learned ordering escape unlucky early branches in hard CSP/SAT instances.

### Classic Backtracking Problems

#### Board / Placement Puzzles
- **N-Queens (find one / count all)** — place N non-attacking queens row by row; track column and both diagonals (O(N!) worst case, far less with pruning).
- **N-Queens via bitmasks** — represent attacked columns/diagonals as bitmasks; isolate-lowest-bit to iterate free squares (large constant-factor speedup).
- **N-Queens diagonal indexing** — encode diagonals as row±col offsets for O(1) conflict checks during placement.
- **Sudoku solver (backtracking)** — fill empty cells with digits consistent in row/column/box; backtrack on dead ends (exponential, fast with propagation).
- **Sudoku with bitmask candidates** — keep per-row/col/box candidate masks for O(1) legality tests and constant-time updates.
- **Latin-square / completion puzzles** — fill an n×n grid so each symbol appears once per row and column; generalizes Sudoku.
- **Magic square construction** — place 1..n² so all rows, columns, diagonals share one sum; backtracking with partial-sum pruning.
- **Crossword / word-fill** — place words into a grid satisfying intersection letters; branch on slots, propagate letter constraints.
- **Knight's tour (backtracking)** — find a tour visiting every board square once via DFS over knight moves (exponential without heuristics).
- **Knight's tour (Warnsdorff's rule)** — greedily move to the square with fewest onward moves; near-linear in practice but heuristic, not guaranteed.
- **Knight's tour (Warnsdorff + tie-breaking)** — break ties by distance-to-corner / Pohl's rule to raise success rate on large boards.
- **Peg solitaire / sliding puzzles** — DFS over moves with pruning; sliding-tile (15-puzzle) classically solved with IDA* (see below).

#### Combinatorial Generation
- **Permutations (swap method)** — fix each position by swapping in each remaining element; generates all n! orderings (O(n·n!)).
- **Permutations (used-array / mark method)** — pick an unused element per position; supports natural lexicographic order.
- **Next-permutation (lexicographic successor)** — in-place transform to the next ordering; iterating it lists all permutations (O(n) per step).
- **Permutations with duplicates** — sort and skip a repeated value when its identical predecessor is unused, yielding distinct permutations only.
- **Heap's algorithm** — generates all n! permutations with a single swap between successive outputs (O(n!) total, minimal change).
- **Steinhaus–Johnson–Trotter** — permutations by adjacent transpositions, each differing by one swap; supports the "plain changes" sequence.
- **Combinations (choose k)** — pick increasing indices to form all C(n,k) size-k subsets (O(k·C(n,k))).
- **Combinations via next-combination** — iterate k-subsets in lexicographic/co-lex order in O(k) amortized per step, no recursion.
- **Subsets / power set (recursive)** — at each element decide include/exclude, enumerating all 2ⁿ subsets (O(n·2ⁿ)).
- **Subsets via bitmask counting** — let integers 0..2ⁿ−1 index subsets via set bits; iterate without recursion (O(2ⁿ), trivial state).
- **Submask enumeration** — iterate all subsets of a fixed mask m via the `s=(s-1)&m` trick (total Σ over masks = O(3ⁿ)).
- **Gray code (binary reflected)** — order all 2ⁿ subsets so successive ones differ in exactly one bit (formula n^(n>>1); O(1) per code).
- **k-ary / mixed-radix Gray codes** — single-digit-change orderings over non-binary alphabets for odometer-style enumeration.
- **Combinatorial Gray codes (combinations/permutations)** — minimal-change orderings of k-subsets (revolving-door) and permutations (Trotter).
- **Set partitions (restricted growth strings)** — enumerate all ways to partition n elements; count is the Bell number (O(Bell(n))).
- **Integer partitions / compositions** — enumerate ways to write n as an ordered/unordered sum of positive parts via recursive descent.
- **Catalan-structured generation** — enumerate balanced parentheses, binary trees, triangulations by recursive backtracking (Catalan count).
- **Tuples / Cartesian product (counters)** — nested odometer increment to enumerate all combinations over multiple domains.

#### Sum / Selection Search
- **Subset sum (backtracking)** — include/exclude each element, pruning when the running sum overshoots the target (O(2ⁿ), pruned heavily by sorting).
- **Combination sum (reuse allowed)** — choose candidates (with repetition) summing to a target; branch on index, prune by remaining target (exponential).
- **Combination sum (each used once)** — like above but each candidate used at most once; sort and skip duplicates to avoid repeated sets.
- **Partition into k equal-sum subsets** — assign elements to buckets with sum pruning and bucket-symmetry breaking (exponential, strong pruning).
- **Coin change enumeration** — list all ways to make an amount; ordered branching prevents counting permutations of the same multiset.
- **Word break / sentence segmentation (search form)** — branch on split points to enumerate all valid segmentations (memoize to avoid blow-up; see DP).
- **Expression / target-number construction** — insert operators between digits to reach a target (24-game, "Letter combinations") via DFS.

#### Graph Backtracking
- **Graph m-coloring (backtracking)** — assign one of m colors per vertex consistent with edges; backtrack on conflict (O(mⁿ) worst case).
- **Chromatic-number search** — find the minimum m by iterating colorings with bounding and color-class symmetry breaking.
- **Hamiltonian path / cycle (backtracking)** — extend a simple path vertex by vertex, requiring all vertices visited (and return edge for cycle) (O(n!) worst case).
- **Hamiltonian search with pruning** — degree/connectivity (articulation) checks and forced-move detection cut dead branches early.
- **Independent set / clique (backtracking)** — Bron–Kerbosch with pivoting enumerates maximal cliques (see Graph Algorithms for full treatment).
- **Brute-force TSP (permutation search)** — try all (n−1)!/2 tours and keep the cheapest; baseline before B&B/Held–Karp (O(n!)).
- **Constraint subgraph / matching search** — backtracking over edge inclusion for exact subgraph isomorphism (VF2-style; see Graph Algorithms).
- **Maze / path enumeration** — DFS over a grid enumerating all source-to-target simple paths with visited-cell undo.

### Branch and Bound

#### Framework
- **Branch-and-bound paradigm** — systematic search that branches into subproblems and bounds each, pruning those that cannot beat the incumbent.
- **Incumbent / best-so-far** — the best feasible solution found yet; its objective is the pruning threshold.
- **Bounding rule** — compute an optimistic bound per node; fathom (discard) the node if its bound is no better than the incumbent.
- **Branching rule** — how a subproblem is split (e.g. variable fix to 0/1, edge include/exclude) into mutually exclusive children.
- **Node selection / search order** — best-first (lowest bound), depth-first (low memory), or breadth-first traversal of the B&B tree.
- **Best-first B&B** — expand the most promising node (priority queue by bound); fewest nodes but high memory (often exponential frontier).
- **Depth-first B&B** — DFS with incumbent updates; low memory, good for anytime solutions; the common competitive-programming choice.
- **Least-cost (LC) search** — best-first variant using cost+heuristic ranking to guide expansion.
- **Fathoming conditions** — prune a node if infeasible, if its bound ≤ incumbent, or if it yields a complete solution.
- **Anytime behavior** — B&B can return the current incumbent whenever interrupted, improving it over time.

#### Canonical B&B Instances
- **0/1 Knapsack (branch and bound)** — branch include/exclude per item, bound via fractional-knapsack relaxation; prune dominated nodes (much faster than 2ⁿ in practice).
- **Knapsack bound = greedy fractional fill** — sort by value/weight, fill greedily, take fractional last item for an admissible upper bound.
- **TSP branch and bound** — branch on edge inclusion/exclusion; bound via 1-tree, assignment relaxation, or reduced-cost matrix (row/col reduction).
- **TSP reduced-cost-matrix bound** — repeatedly subtract row/column minima; the total reduction lower-bounds any completing tour.
- **Assignment problem B&B** — branch on assignments, bound via the Hungarian/LP relaxation (see Graph/Flow).
- **Job scheduling / sequencing B&B** — minimize makespan/tardiness; bound via relaxed single-machine or preemptive schedules.
- **Integer / 0-1 linear programming B&B** — branch on fractional variables of the LP relaxation; basis of branch-and-cut/branch-and-price (see Optimization).
- **Set cover / facility location B&B** — bound via LP or greedy/Lagrangian relaxations to prune the selection tree.
- **Max-clique B&B (greedy coloring bound)** — bound the remaining clique size by a greedy coloring of the candidate set (Tomita's MCS).

### Iterative Deepening & Informed Exhaustive Search

#### Depth-Limited & Iterative Deepening
- **Depth-limited search (DLS)** — DFS truncated at a depth limit L; complete only within that horizon (O(b^L) time, O(L) space).
- **Iterative deepening DFS (IDDFS)** — repeat DLS with limits 1,2,3,…; combines BFS optimality with DFS memory (O(b^d) time, O(d) space).
- **IDDFS re-expansion overhead** — re-searching shallow levels costs a constant factor ~b/(b−1); negligible for branching factor >1.
- **Iterative broadening** — increase the branching breadth per level instead of depth; useful when the goal is shallow but branching is wide.

#### Heuristic Iterative Deepening
- **IDA\* (iterative deepening A\*)** — repeated cost-bounded DFS using f=g+h; raise the bound to the smallest f that exceeded it (O(d) space, optimal with admissible h).
- **IDA\* threshold update** — next bound = minimum f-value that exceeded the current bound, guaranteeing progress without storing the frontier.
- **Transposition table for IDA\*** — cache visited states/heuristics to suppress duplicate expansions across iterations.
- **Recursive best-first search (RBFS)** — linear-space best-first that backs up the best alternative f-value; fewer re-expansions than IDA* on some graphs.
- **SMA\* / MA\* (memory-bounded A\*)** — A* that drops the worst leaves when memory is full, retaining optimality up to memory limits (see Graph Search).
- **Admissible & consistent heuristics** — never overestimate (admissible) / obey the triangle inequality (consistent); required for IDA*/A* optimality.
- **Pattern database heuristics** — precomputed exact costs of subproblem abstractions used as admissible heuristics (e.g. 15-puzzle, Rubik's cube).
- **Disjoint / additive pattern databases** — partition tiles into disjoint groups so their PDB costs add admissibly for stronger bounds.
- **Walking-distance / Manhattan-distance heuristics** — classic admissible estimates for sliding-tile puzzles driving IDA*.
- **A\* search** — best-first f=g+h frontier search (cross-reference: full treatment in Graph Algorithms / Shortest Paths).

#### Bidirectional & Parallel Exhaustive Search
- **Bidirectional BFS** — search forward from start and backward from goal; meet in the middle to roughly halve the explored radius (O(b^{d/2})).
- **Bidirectional search frontier intersection** — detect when the two frontiers share a state; reconstruct the path by joining halves.
- **Bidirectional heuristic search (MM, NBS)** — meet-in-the-middle A* variants that guarantee meeting near the midpoint with bounds.
- **Bidirectional IDDFS** — depth-bounded DFS from both ends, alternating expansion, for huge state spaces with reversible moves.
- **Parallel / work-stealing tree search** — distribute independent subtrees across workers; load-balance via work stealing for B&B and backtracking.

### Meet-in-the-Middle (MITM)

#### Core Technique
- **Meet-in-the-middle principle** — split decisions into two halves, enumerate each half's outcomes, then combine via sorting/hashing to beat brute force (typically O(2^{n/2}) from O(2ⁿ)).
- **Split-enumerate-combine schema** — generate all 2^{n/2} partial results for each half, sort one side, and match the other against it.
- **Equation rewrite (f(A)=g(B))** — recast a single constraint as equality between functions of disjoint variable sets, then intersect their image sets.
- **Space–time tradeoff** — MITM spends O(2^{n/2}) memory to cut time from O(2ⁿ); the defining tradeoff of the technique.
- **Hashing vs sorting for the merge** — hash one half for O(1) lookups, or sort and two-pointer/binary-search for range/inequality matches.

#### Subset-Sum & Knapsack via MITM
- **Subset sum (Horowitz–Sahni)** — split items, list all subset sums of each half, sort, and two-pointer to hit the target (O(2^{n/2} · n)).
- **Count subsets with a given sum** — match-and-count equal sums across halves via a hash map of sums to multiplicities.
- **Max subset sum ≤ capacity (MITM)** — for each left-half sum, binary-search the largest right-half sum within the remaining capacity (O(2^{n/2} log)).
- **0/1 knapsack value via MITM** — enumerate (weight,value) pairs per half, keep the Pareto frontier (drop dominated pairs), then merge for best value within capacity.
- **Pareto-frontier pruning** — within a half, discard any (weight,value) dominated by a lighter-and-more-valuable pair to shrink the merge set.
- **Schroeppel–Shamir algorithm** — subset sum in O(2^{n/2}) time but only O(2^{n/4}) space, using priority queues over quarter-splits.
- **k-sum / 4-SUM via MITM** — pair sums of two halves and search for complementary pairs (4-SUM in O(n²) via paired-sum tables).
- **3-SUM reductions** — sort + two-pointer (O(n²)); MITM/hashing variants and the 3SUM-hardness baseline (see Two Pointers / lower bounds).

#### Number-Theoretic & Other MITM
- **Baby-step giant-step (discrete log)** — solve aˣ≡b by tabulating baby steps and matching giant steps; MITM in O(√m) time and space (see Number Theory).
- **MITM for modular / hashing attacks** — generic time–memory tradeoff splitting a composition of two functions (cryptanalytic MITM).
- **Equation count / preimage search** — count solutions to f(x)+g(y)=t by hashing one image multiset and probing with the other.
- **MITM over graph paths** — enumerate half-length paths from both endpoints and join at midpoints to count/find long paths (with color-coding; see Graph).
- **Enumerate-and-merge (general pattern)** — any "two independent halves combined by a cheap join" speedup; the umbrella idea behind MITM.
- **Constraint-split for fixed small k** — when only a small subset is "free," brute-force it and solve the rest in closed form, merging results.

### Exact Cover & Dancing Links

- **Exact cover problem** — choose rows of a 0/1 matrix so each column is covered by exactly one chosen row (NP-complete in general).
- **Algorithm X** — Knuth's nondeterministic, recursive, depth-first backtracking scheme for exact cover; choose a column, try each covering row, recurse.
- **Column (constraint) selection heuristic** — pick the column with the fewest 1s (S-heuristic) to minimize branching, like MRV.
- **Dancing Links (DLX)** — sparse representation as circular doubly-linked lists where remove/restore are O(1) reversible pointer updates; efficient Algorithm X engine.
- **Cover / uncover operations** — splice a column and its rows out of the matrix on descent and splice them back exactly on backtrack (the "dancing").
- **Modeling Sudoku as exact cover** — encode cell, row, column, and box constraints as columns; DLX solves general Sudoku rapidly.
- **Modeling N-Queens as exact cover** — rank/file as primary (exact) and diagonals as secondary (at-most-one) columns.
- **Polyomino / pentomino tiling as exact cover** — one column per board cell plus per-piece columns; each placement is a row.
- **Primary vs secondary columns** — primary columns must be covered exactly once; secondary (optional) columns at most once, for "at-most-one" constraints.
- **Generalized cover / multiplicities (Knuth's XCC)** — exact-cover with colors and bounds, handling richer placement constraints.

### Bitmask Exhaustive Search

- **Bitmask brute force** — represent a chosen subset of ≤~25 elements as an integer; loop over all 2ⁿ masks (O(2ⁿ) with O(1)/O(popcount) work each).
- **Subset DP over bitmasks** — iterate masks in increasing order using prior submask results (Hamiltonian/TSP Held–Karp; see DP-Advanced).
- **Submask-sum enumeration (SOS DP)** — sum-over-subsets in O(n·2ⁿ); the bitmask analog of merging halves (see DP-Advanced).
- **Popcount-ordered enumeration** — process masks grouped by number of set bits to enforce size constraints or layered DP.
- **Lowest-set-bit iteration** — extract candidates one bit at a time (`x & -x`) to walk free positions efficiently (used in bitmask N-Queens).
- **Meet-in-the-middle over bitmasks** — split the ground set into two bitmask halves and merge (the MITM realization for set problems).
- **Enumerate complementary submasks** — for each submask s of m, its complement m^s; pairs up splits of a set in O(3ⁿ) total.
- **Bitset-accelerated search** — pack feasibility/candidate sets into machine words for ~64× constant-factor speedups in pruning (see Bit Manipulation).

### Search-Space Reduction & Pruning Heuristics

- **Symmetry breaking (general)** — eliminate equivalent search states via canonical forms, fixed first choices, or ordering constraints.
- **Isomorph rejection** — generate combinatorial objects up to symmetry by canonical augmentation (orderly generation, McKay's method).
- **Lexicographic / value ordering constraints** — force assignments to be lexicographically minimal in their symmetry class to avoid duplicates.
- **Dominance relations** — prune a partial solution if some other reachable one is provably at least as good in all respects.
- **Constraint tightening / preprocessing** — derive implied constraints and reduce domains before search (e.g. propagate bounds, fix forced variables).
- **Look-ahead vs look-back** — forward checking/MAC (look-ahead) vs conflict-directed backjumping and learning (look-back).
- **Backjumping** — on failure, jump back to the most recent variable actually responsible for the conflict, skipping irrelevant levels.
- **Conflict-directed backjumping (CBJ)** — track conflict sets to compute the jump-back target precisely.
- **Dynamic backtracking** — undo only the culprit assignment, preserving unrelated later work (no-good–driven repair).
- **No-good / clause learning** — record failed partial assignments as constraints to prevent revisiting them (CDCL in SAT; see SAT).
- **Branch ordering by promise** — order children by a heuristic so good incumbents appear early, strengthening bounding (B&B speed).
- **Iterative deepening as a pruning enabler** — small depth bounds keep memory tiny while still allowing optimal-depth discovery.

### Heuristic & Approximate Exhaustive Search

- **Beam search** — breadth-limited best-first that keeps only the top-W nodes per level (beam width W); fast but incomplete (O(W·b·d)).
- **Beam width tradeoff** — wider beams approach BFS completeness; narrower beams are faster but more likely to miss the optimum.
- **Beam stack search** — beam search made complete/anytime by backtracking over discarded nodes via a "beam stack."
- **Local beam / stochastic beam search** — keep W states and expand all successors, optionally sampling, to balance exploration (see Local Search).
- **Limited-discrepancy search (LDS)** — explore paths that deviate from the heuristic's greedy choice at most k times, in increasing k.
- **Depth-bounded discrepancy search (DDS)** — combine LDS with depth limits to focus discrepancies near the root where heuristics are least reliable.
- **Greedy best-first search** — expand the node minimizing h alone; fast, not optimal (cross-reference: Graph Search).
- **Weighted A\* / anytime weighted A\*** — inflate the heuristic (f=g+w·h) to trade optimality for speed (see Graph Search).
- **Hill climbing / simulated annealing / genetic search** — non-systematic alternatives when exhaustive search is infeasible (see Local Search / Metaheuristics).

### Cross-References & Related Paradigms

- **Recursion with memoization** — caching subproblem results to collapse exponential search into polynomial DP (see Dynamic Programming).
- **Held–Karp bitmask DP for TSP** — exact O(2ⁿ·n²) DP that supersedes brute-force permutation search (see DP-Advanced).
- **DPLL / CDCL SAT solving** — backtracking search with unit propagation and clause learning for Boolean satisfiability (see SAT/Logic).
- **Constraint satisfaction (CSP) solvers** — backtracking + propagation framework generalizing many problems here (see Constraint Satisfaction).
- **Bron–Kerbosch clique enumeration** — backtracking enumeration of all maximal cliques with pivoting (see Graph Algorithms).
- **Color-coding** — randomized technique reducing path/subgraph search to bitmask DP (often paired with MITM; see Graph/Randomized).
- **Branch-and-cut / branch-and-price** — B&B augmented with cutting planes / column generation for ILP (see Optimization / LP).
- **Alpha-beta / minimax search** — adversarial backtracking with bounding over game trees (see Game Theory / Game Search).

<sub>[↑ Back to top](#table-of-contents)</sub>

---

## 28. Randomized & Probabilistic Algorithms

### Foundations & Classification of Randomized Algorithms

#### Algorithm Classes by Error/Time Guarantee
- **Las Vegas algorithm** — always returns a correct answer; running time is a random variable (expected/high-probability bound).
- **Monte Carlo algorithm** — fixed (worst-case) running time but answer correct only with bounded probability; one-sided or two-sided error.
- **One-sided-error Monte Carlo (RP / co-RP)** — errs in only one direction (e.g. never says "prime" for a composite, may miss); amplifiable by repetition.
- **Two-sided-error Monte Carlo (BPP)** — error possible both ways but bounded below 1/2; majority vote amplifies confidence.
- **Las Vegas ↔ Monte Carlo conversion** — wrap a Las Vegas algo with a time cutoff to get Monte Carlo; repeat a checkable Monte Carlo algo to get Las Vegas.
- **Atlantic City algorithm** — bounded time and bounded two-sided error simultaneously (relaxation of both notions).
- **Sherwood randomization** — randomize input order to smooth a deterministic algorithm's input-dependent variance (e.g. random-pivot quicksort).
- **Error amplification by independent repetition** — run k independent trials and combine (AND/OR/majority) to drive failure probability to delta exponentially in k.
- **Success-probability boosting via median trick** — for numeric estimators, take the median of O(log(1/delta)) independent means to get (eps, delta) guarantees.

#### Probabilistic Analysis Tools
- **Linearity of expectation** — E[sum X_i] = sum E[X_i] regardless of dependence; backbone of expected-cost analysis.
- **Indicator-variable method** — decompose a count into 0/1 indicators, sum their probabilities (e.g. expected comparisons, expected records).
- **Markov inequality** — P(X >= a) <= E[X]/a; crudest tail bound, basis of time cutoffs for Las Vegas algorithms.
- **Chebyshev inequality** — uses variance for two-sided concentration; key when pairwise independence available.
- **Chernoff / Hoeffding bounds** — exponential concentration of sums of independent bounded/Bernoulli variables; core of high-probability guarantees.
- **Union bound (Boole inequality)** — P(union of bad events) <= sum of probabilities; converts per-event failure into global failure.
- **Conditional expectation & method of conditional probabilities** — derandomization by greedily fixing bits to keep a pessimistic estimator bounded.
- **Second-moment method** — variance-based existence/concentration arguments (e.g. thresholds in random structures).
- **Probabilistic method (Erdos)** — prove existence by showing a random object has the property with positive probability; nonconstructive then derandomized.
- **Lovász Local Lemma** — existence under sparse dependency; Moser-Tardos algorithmic version resamples violated events (expected near-linear).
- **Coupon collector bound** — expected ~n ln n draws to see all n types; recurs in cover-time and reservoir analyses.
- **Birthday paradox / birthday bound** — collision expected after ~sqrt(N) draws from N values; underpins collision attacks and rho-style cycle finding.
- **Markov-chain / spectral-gap mixing analysis** — bound time to near-stationary distribution via second eigenvalue / conductance.

### Randomized Sorting, Selection & Order Statistics
- **Randomized quicksort** — choose pivot uniformly at random; expected O(n log n) comparisons, eliminates adversarial worst case.
- **Random-pivot partition (Lomuto / Hoare)** — randomized split step producing balanced halves with high probability.
- **Randomized quickselect** — random-pivot selection of the k-th order statistic in expected O(n), worst O(n^2).
- **Random sampling for pivot (median-of-random-sample)** — pick pivot as median of a random subset to tighten balance and reduce variance.
- **Random shuffle then deterministic algorithm (Sherwood)** — permute input first so any fixed pivot rule behaves like a random one.
- **Randomized median / order statistics by sampling (Floyd-Rivest style)** — sample, bound the rank window, recurse; expected n + O(n^{1/2+}) comparisons.
- **Bucket/distribution sort under random input model** — expected linear time when keys are uniformly distributed.
- **Random binary search trees** — insert in random order so expected depth is O(log n) (analytic basis for treaps).

### Randomized Data Structures (cross-references)
- **Treap (randomized BST / Cartesian tree)** — BST keys + random heap priorities give expected O(log n) operations (see Balanced/Search Trees domain).
- **Skip list** — randomized layered linked list, expected O(log n) search/insert/delete (see Probabilistic Data Structures domain).
- **Randomized splay / scapegoat variants** — randomization to avoid amortization adversaries (see Balanced Trees).
- **Bloom filter** — probabilistic set membership with one-sided false positives (see Probabilistic Data Structures / Hashing).
- **Counting Bloom / Cuckoo filter** — deletable / space-efficient membership variants (see Probabilistic Data Structures).
- **Count-Min sketch & Count-Sketch** — sublinear frequency/heavy-hitter estimation (see Streaming/Sketching domain).
- **HyperLogLog / LogLog / Flajolet-Martin** — probabilistic distinct-element cardinality estimation (see Streaming/Sketching domain).
- **AMS / tug-of-war sketch** — randomized F2 (second-frequency-moment) estimation in streams (see Streaming).
- **MinHash / SimHash (LSH)** — randomized similarity sketches for Jaccard/cosine (see Hashing/Similarity domain).
- **Randomized search tree balancing via hashing of keys** — derive priorities deterministically from a hash (see Hashing).

### Hashing with Randomness (cross-references)
- **Universal hashing (Carter-Wegman)** — random hash from a family guaranteeing low expected collisions (see Hashing domain).
- **k-wise independent / polynomial hashing** — limited-independence families enabling moment bounds (see Hashing).
- **Tabulation hashing** — XOR of random table lookups; strong independence-like guarantees cheaply (see Hashing).
- **Perfect hashing (FKS two-level)** — randomized construction giving O(1) worst-case lookup, O(n) space (see Hashing).
- **Cuckoo hashing** — randomized placement with two/more hash functions, O(1) worst-case lookup, randomized rehash on failure (see Hashing).
- **Randomized string fingerprinting (Rabin-Karp / polynomial hashing)** — random base/modulus to thwart anti-hash adversaries (see Strings/Hashing).
- **Feature/locality-sensitive hashing for ANN** — random projections/hyperplanes for approximate nearest neighbor (see Hashing/Geometry).

### Random Sampling Techniques
#### Permutation & Uniform Sampling
- **Fisher-Yates / Knuth shuffle** — uniform random permutation in O(n) by swapping each element with a random earlier/later index.
- **Inside-out Fisher-Yates** — streaming variant that builds the shuffle while copying, O(n).
- **Sattolo's algorithm** — generates a uniform single-cycle permutation (variant of Fisher-Yates with restricted swap range).
- **Random permutation via sort-by-random-key** — assign random keys and sort; O(n log n), simple but key-collision-sensitive.
- **Uniform sampling without replacement (partial Fisher-Yates)** — first k entries of a shuffle give a uniform k-subset in O(k).
- **Random combination / subset selection (Floyd's algorithm)** — sample k distinct items from n in O(k) without full array.

#### Reservoir & Stream Sampling
- **Reservoir sampling (Algorithm R)** — uniform sample of k items from an unknown-length stream in one pass, O(n) time, O(k) space.
- **Algorithm L (skip-based reservoir)** — geometrically skips elements to reduce random draws to O(k(1+log(n/k))).
- **Weighted reservoir sampling (A-Res, Efraimidis-Spirakis)** — keys = u^{1/w}; keep top-k keys for weighted sampling without replacement.
- **A-ExpJ (exponential jumps)** — accelerated weighted reservoir using exponential skip distances.
- **Distributed / parallel reservoir merging** — combine per-partition reservoirs with reweighting for a global uniform sample.
- **Sliding-window sampling** — maintain a uniform sample over the last w stream elements (priority-based).

#### Weighted & Non-Uniform Sampling
- **Roulette-wheel / cumulative-sum sampling** — prefix sums + binary search, O(log n) per draw after O(n) build.
- **Alias method (Walker / Vose)** — O(1) sampling from a discrete distribution after O(n) preprocessing.
- **Inverse transform sampling** — map a uniform variate through the inverse CDF to sample arbitrary distributions.
- **Rejection sampling** — sample from an envelope and accept with target/envelope ratio; efficiency = acceptance rate.
- **Importance sampling** — sample from a tractable proposal and reweight by density ratio to estimate under the target.
- **Ziggurat / Box-Muller / Marsaglia polar** — efficient generation of Gaussian and other continuous variates.
- **Reservoir sampling with weights vs probability-proportional-to-size** — distinction between with/without-replacement weighted schemes.
- **Sampling a random point in a simplex / on a sphere** — exponential-spacing and normalized-Gaussian tricks for uniform geometric sampling.

#### Pseudorandom Generation (support layer)
- **Linear congruential / xorshift / PCG / Mersenne Twister** — PRNG families; period, equidistribution, and predictability trade-offs.
- **Cryptographic vs statistical PRNGs** — when unpredictability matters versus only distributional quality.
- **Seeding & reproducibility** — fixed seeds for debugging vs entropy seeding to defeat anti-randomized adversaries.
- **Random bits from biased source (von Neumann extractor)** — extract unbiased bits from a biased coin.

### Randomized Graph Algorithms
#### Minimum Cut
- **Karger's contraction algorithm** — repeatedly contract a uniformly random edge until two vertices remain; one run is the min cut with prob >= 2/n^2, O(n^2) per run.
- **Karger's algorithm with amplification** — repeat O(n^2 log n) contractions for high-probability global min cut, total ~O(n^4 log n).
- **Karger-Stein algorithm** — recursive contraction with branching at n/sqrt(2); O(n^2 log^3 n) for high-probability global min cut.
- **Contraction via random edge order (permutation view)** — equivalent formulation contracting in a random edge ranking, links to MST/Borůvka analysis.
- **Counting near-minimum cuts** — Karger's analysis bounds the number of (alpha)-approximate min cuts by O(n^{2 alpha}).
- **Random sampling for cut sparsification (Benczúr-Karger)** — sample edges by connectivity to approximate all cuts in near-linear size.
- **Karger's algorithm for min k-cut (random contraction generalization)** — bound on probability of preserving a fixed k-way partition.

#### Connectivity, Spanning & Flow
- **Random-permutation MST / Karger-Klein-Tarjan** — randomized linear-expected-time minimum spanning tree via sampling and verification.
- **Randomized MST verification** — linear-time check whether a tree is an MST (component of the above).
- **Random contraction for 2-edge/2-vertex connectivity sampling** — probabilistic shortcuts in connectivity certificates.
- **Randomized BFS/DFS tie-breaking** — random adjacency order to avoid adversarial structure and balance work.
- **Color-coding** — random vertex coloring to find paths/cycles/subgraphs of size k in 2^{O(k)} poly(n); derandomizable via perfect hash families.
- **Random orientation / random 2-coloring heuristics** — for discrepancy, MAX-CUT, and balancing problems.
- **Randomized approximate shortest-path / hopset sampling** — sample landmarks/hubs for distance oracles (see Graph/Distance Oracle domain).
- **Ball-growing / random radius region decomposition** — exponential/geometric random radii for low-diameter graph partitioning.

#### Matching & Algebraic Randomization
- **Schwartz-Zippel lemma** — a nonzero low-degree polynomial is nonzero at a random point with high probability; foundation of algebraic testing.
- **Polynomial identity testing (PIT)** — randomized check of polynomial equality by random evaluation.
- **Tutte / symbolic matrix matching test** — random substitution into the Tutte matrix decides perfect-matching existence via determinant.
- **Isolation lemma (Mulmuley-Vazirani-Vazirani)** — random weights make the minimum-weight solution unique with high probability; enables matching/parallel algorithms.
- **Randomized parallel (RNC) maximal independent set (Luby)** — random priorities select an MIS in O(log n) rounds.
- **Freivalds' algorithm** — verify matrix product AB = C by random vector multiplication in O(n^2) with one-sided error.

### Number-Theoretic Randomized Algorithms (cross-references)
- **Miller-Rabin primality test** — randomized strong-pseudoprime test; composite detected with prob >= 3/4 per base (see Number Theory domain).
- **Solovay-Strassen test** — Euler-pseudoprime randomized primality test, error <= 1/2 per witness (see Number Theory).
- **Fermat test & Carmichael caveat** — weak randomized primality with Carmichael false positives (see Number Theory).
- **Pollard's rho factorization** — random-walk cycle detection (Floyd/Brent) factoring in expected O(n^{1/4}) (see Number Theory).
- **Pollard's rho for discrete logarithm** — birthday-style collision walk in a cyclic group (see Number Theory).
- **Pollard p-1 / random base choice** — randomized base selection for smooth-factor extraction (see Number Theory).
- **Random quadratic residue / Tonelli-Shanks randomization** — random nonresidue selection for modular square roots (see Number Theory).
- **Cipolla's algorithm randomization** — random parameter until a non-square is found for square roots mod p (see Number Theory).
- **Random sampling in Dixon / quadratic sieve** — randomized relation collection in factoring (see Number Theory).

### Randomized Computational Geometry
- **Randomized incremental construction (RIC) paradigm** — insert objects in random order, maintain structure + conflict graph; backward analysis bounds expected cost.
- **Backward analysis** — analyze expected work by reasoning about the last inserted element being uniformly random.
- **Welzl's smallest enclosing circle (minidisk)** — randomized incremental, expected O(n) with at most 3 boundary points; generalizes to enclosing ball/ellipsoid.
- **Randomized LP-type / Seidel's randomized linear programming** — expected O(d! n) for fixed-dimension LP via random constraint insertion.
- **Clarkson-Shor sampling framework** — random-sampling bounds on expected size of configuration spaces (basis of many RIC analyses).
- **Randomized incremental Delaunay triangulation / Voronoi** — insert sites in random order with conflict/history (Boris-Delaunay), expected O(n log n).
- **Delaunay history DAG (jump-and-walk / Mucke point location)** — randomized point location during incremental insertion.
- **Randomized incremental convex hull (Clarkson-Shor)** — expected O(n log n) (2D/3D) hull via random insertion + conflict lists.
- **Randomized trapezoidal map / vertical decomposition** — RIC for planar point location, expected O(n log n) build, O(log n) query.
- **Random sampling for epsilon-nets and epsilon-approximations** — small random subsets that hit all "heavy" ranges (VC-dimension theory).
- **Randomized closest pair (Rabin / Khuller-Matias)** — grid hashing with random shifts, expected O(n) closest pair.
- **Random projection / Johnson-Lindenstrauss** — embed n points into O(log n / eps^2) dims preserving distances; speeds geometric/ML tasks.
- **Random rotation / shifting for geometric partitioning** — randomly shifted quadtrees for approximation (e.g. Arora-style TSP/Steiner).

### Monte Carlo Estimation & Numerical Methods
- **Monte Carlo integration** — estimate integrals by averaging f at uniform random points; error ~ O(1/sqrt(N)) independent of dimension.
- **Hit-or-miss (rejection) Monte Carlo** — estimate area/volume/pi by fraction of random points inside a region.
- **Importance sampling for variance reduction** — sample where the integrand is large to shrink estimator variance.
- **Stratified sampling** — partition the domain and sample each stratum to reduce variance.
- **Control variates** — subtract a correlated known-mean quantity to reduce variance.
- **Antithetic variates** — pair negatively correlated samples (u and 1-u) to cancel variance.
- **Quasi-Monte Carlo (low-discrepancy sequences)** — Halton/Sobol/van der Corput points give O((log N)^d / N) error, better than random.
- **Russian roulette & splitting** — unbiased early termination / replication for rare-event and rendering integrals.
- **Estimating counts via sampling (approximate counting)** — DNF counting, permanent estimation, volume of convex bodies via sampling.
- **Morris's approximate counter** — count up to n using O(log log n) bits with probabilistic increments.

### Markov Chain Monte Carlo & Sampling from Complex Distributions
- **Markov chain Monte Carlo (overview)** — construct a Markov chain whose stationary distribution is the target, then sample after mixing.
- **Metropolis-Hastings** — propose-and-accept with acceptance ratio ensuring detailed balance toward the target distribution.
- **Gibbs sampling** — update one coordinate at a time from its conditional; special case of Metropolis-Hastings.
- **Detailed balance / reversibility** — sufficient condition for a chain to have the desired stationary distribution.
- **Mixing time & spectral gap** — number of steps to reach near-stationarity; governed by conductance / second eigenvalue.
- **Coupling & coupling-from-the-past (Propp-Wilson)** — exact (perfect) sampling from the stationary distribution.
- **Simulated tempering / parallel tempering** — multiple temperatures to escape local modes and improve mixing.
- **Random walk on combinatorial structures** — sampling matchings, colorings, spanning trees; basis of approximate counting (FPRAS).
- **Wilson's algorithm (loop-erased random walk)** — sample a uniform spanning tree exactly.
- **Glauber dynamics** — single-site update chain for spin systems / colorings with mixing-time analysis.

### Local Search & Metaheuristics (Randomized Optimization)
#### Trajectory-Based
- **Hill climbing** — iteratively move to a better neighbor; stops at local optima.
- **Random-restart hill climbing** — repeat hill climbing from random starts to escape local optima.
- **Stochastic hill climbing / first-improvement** — randomly pick among improving moves instead of the best.
- **Simulated annealing** — accept worse moves with probability exp(-Δ/T) under a cooling schedule; converges to global optimum in the limit.
- **Cooling schedules** — geometric, logarithmic (Boltzmann), adaptive temperature control governing exploration vs exploitation.
- **Threshold accepting / great deluge / record-to-record** — deterministic-threshold relatives of simulated annealing.
- **Tabu search** — local search with a tabu list forbidding recent moves to avoid cycling; aspiration criteria override it.
- **Iterated local search (ILS)** — perturb a local optimum then re-optimize, keeping the better basin.
- **Variable neighborhood search (VNS)** — systematically change neighborhood structures to escape local optima.
- **GRASP (greedy randomized adaptive search)** — randomized greedy construction + local search, restarted repeatedly.
- **Late-acceptance hill climbing** — compare against a delayed fitness history to accept moves.

#### Population-Based / Evolutionary
- **Genetic algorithms** — population of candidate solutions evolved by selection, crossover, and mutation.
- **Selection operators** — roulette-wheel, tournament, rank, elitism for choosing parents.
- **Crossover operators** — one-point, multi-point, uniform, order/PMX (for permutations).
- **Mutation operators** — bit-flip, swap, inversion; rate balances exploration vs disruption.
- **Evolution strategies (ES) / (mu,lambda) & (mu+lambda)** — real-valued evolution with self-adaptive step sizes.
- **CMA-ES** — covariance-matrix-adaptation evolution strategy for continuous black-box optimization.
- **Differential evolution** — mutate by scaled difference of population vectors plus crossover.
- **Genetic programming** — evolve programs/expression trees rather than fixed-length strings.
- **Estimation of distribution algorithms (EDA)** — build and sample a probabilistic model of good solutions instead of crossover.
- **Memetic algorithms** — genetic algorithm hybridized with local search ("Lamarckian" refinement).
- **Particle swarm optimization** — particles move guided by personal and global bests with stochastic velocity updates.
- **Ant colony optimization** — probabilistic path construction guided by evaporating pheromone trails.
- **Cross-entropy method** — iteratively sample, select elite, refit the sampling distribution toward optima.

#### Practical CP/Heuristic Notes
- **Restart strategies (Luby sequence)** — universal restart schedule minimizing expected runtime of Las Vegas / SAT solvers.
- **Randomized rounding seeds & multi-start** — run a randomized heuristic many times under a time limit, keep the best.
- **Random tie-breaking in greedy/branch-and-bound** — randomize choices to escape adversarial inputs and diversify search.
- **Simulated annealing for layout/TSP/scheduling in contests** — typical "score-maximization" marathon-round technique.

### Randomization in Approximation & Online Algorithms
- **Randomized rounding** — solve an LP relaxation, then round fractional values to 1 with probability equal to their value (see Approximation domain).
- **Randomized rounding for MAX-SAT / set cover** — classic constant-factor approximations via independent rounding (see Approximation).
- **Random hyperplane rounding (Goemans-Williamson)** — SDP + random hyperplane gives 0.878 MAX-CUT approximation (see Approximation).
- **Pipage / dependent rounding** — preserve constraints while rounding fractional solutions (see Approximation).
- **Randomized online algorithms & oblivious adversary** — randomization lowers competitive ratios (e.g. randomized marking for paging, O(log k)) (see Online Algorithms).
- **Randomized k-server / metrical task systems** — tree-embedding + randomization for competitive ratios (see Online Algorithms).
- **Probabilistic tree embeddings (FRT)** — embed any metric into a distribution over trees with O(log n) expected distortion (see Metric/Approximation).
- **Random sampling for streaming approximation** — sublinear-space approximate aggregates (see Streaming domain).

### Random Walks, Markov Chains & Their Parameters
- **Simple random walk on a graph** — move to a uniformly random neighbor each step; stationary distribution proportional to degree.
- **Hitting time** — expected steps to first reach a target vertex from a source.
- **Commute time** — sum of hitting times both ways; equals 2m times effective resistance (electrical-network analogy).
- **Cover time** — expected steps to visit all vertices; between O(n log n) and O(n^3), Matthews-bound estimates.
- **Effective resistance / electrical networks** — random-walk quantities computed via resistor-network analogies; used in spanning-tree sampling and sparsification.
- **PageRank as a random walk** — stationary distribution of a damped random surfer (power iteration / Monte Carlo estimation).
- **Random walk for 2-SAT / k-SAT (Schöning's algorithm)** — random restarts + random flips solve k-SAT in O((2(1-1/k))^n) expected.
- **Random walk USTCON (Aleliunas et al.)** — undirected s-t connectivity in randomized log-space (random walk hits target within polynomial steps).
- **Lazy random walks & mixing** — add self-loops to ensure aperiodicity and well-defined convergence.
- **Metropolis-weighted walks** — bias transition probabilities to target a chosen stationary distribution.

### Probabilistic Counting & Sketching for Streams (cross-references)
- **Flajolet-Martin / Probabilistic Counting (PCSA)** — distinct-element estimation via trailing-zero patterns (see Streaming).
- **HyperLogLog & HLL++** — register-max distinct counting with harmonic-mean bias correction (see Streaming).
- **Count-Min / Count-Sketch** — frequency and heavy-hitter estimation with hashing (see Streaming).
- **Morris counter** — approximate large counts in few bits (also under Monte Carlo estimation above).
- **Bloom-filter family** — membership sketches with tunable false-positive rate (see Probabilistic Data Structures).
- **Reservoir / weighted sampling sketches** — maintain representative stream samples (also under Sampling above).
- **AMS frequency-moment estimators** — randomized F_k moment approximation in streams (see Streaming).

### Collision, Birthday & Cryptographic Probability Arguments
- **Birthday paradox / birthday bound** — collision among k items from N expected at k ~ 1.18 sqrt(N); ~50% at sqrt(2 ln 2 · N).
- **Birthday attack** — generic collision attack on hash functions in O(2^{n/2}) (see Cryptography domain).
- **Pollard rho cycle-finding (birthday-based)** — collision in a pseudorandom sequence after ~sqrt(N) steps (see Number Theory).
- **Meet-in-the-middle as a collision argument** — sqrt-time space-time tradeoffs from collision counting.
- **Random-hash anti-collision in contests** — randomized modulus/base to defeat constructed hash-collision tests (see Strings/Hashing).
- **Probabilistic lower bounds via collisions** — counting/encoding arguments bounding the success of randomized procedures.

### Derandomization & Pseudorandomness
- **Method of conditional probabilities** — derandomize by fixing random choices to keep a pessimistic estimator within bound.
- **Limited independence (k-wise independent sample spaces)** — replace full randomness with small spaces, derandomize by enumeration.
- **Pairwise independence constructions** — small sample spaces sufficient for Chebyshev-based analyses.
- **Pseudorandom generators (PRGs) for derandomization** — stretch few random bits to fool a class of algorithms (e.g. BPP ⊆ P conjecture context).
- **Expander graphs & expander mixing lemma** — pseudorandom graphs for sampling, error reduction, and randomness-efficient repetition.
- **Randomness extractors & dispersers** — distill near-uniform bits from weak sources; used to reduce/clean randomness.
- **epsilon-biased sample spaces** — small spaces fooling parity tests, derandomizing certain algorithms.
- **Discrepancy-based derandomization** — construct low-discrepancy point sets replacing random sampling.

<sub>[↑ Back to top](#table-of-contents)</sub>

---

## 29. Offline Techniques & Query Decomposition

### Foundations: Offline vs Online Paradigm

- **Offline query model** — all queries are known in advance, permitting reordering, batching, and global preprocessing impossible when answering online.
- **Online query model** — queries arrive sequentially and must be answered before seeing the next, often forcing persistence or amortized structures.
- **Forced-online (encoded) queries** — queries XOR-encoded with the previous answer to defeat offline tricks, mandating genuinely online data structures.
- **Reordering legality** — offline techniques require answers be independent of processing order; verify each query reads state, not mutates shared output ordering.
- **Time/space tradeoff principle** — offline solutions trade the freedom to reorder for lower per-query cost and simpler structures versus heavyweight online persistence.

#### When Offline Beats Online

- **Avoiding persistence** — offline lets a single mutable structure be reused via reordering instead of building O(n) persistent versions.
- **Amortization across queries** — total cost bounded by a global potential argument rather than worst-case-per-query.
- **Batch dimensionality reduction** — sorting queries on one coordinate removes a dimension, reducing a d-D problem to (d−1)-D structures.
- **Replacing 2 logs with 1 log or sqrt** — clever ordering often shaves a logarithmic or polynomial factor versus the naive online structure.

### Coordinate Compression & Preprocessing

- **Coordinate compression** — map sparse values to dense ranks via sort + unique + binary search so array-indexed structures fit in O(n) space (O(n log n)).
- **Offline value discretization** — collect all values from queries and updates first, then compress jointly so future updates have predefined slots.
- **Query bucketing / batching** — group queries by a shared key (block, time window, root) to process each group with a tailored pass.
- **Event-list construction** — flatten queries and updates into a unified, sortable list of timestamped events (insert/delete/ask).
- **Sweep-line over sorted events** — process events in coordinate order maintaining an incremental structure (see Geometry / Sweepline domain).
- **Offline-friendly key sorting** — stable multi-key sort (e.g., by right endpoint then left) to enable monotone pointer movement.

### Sqrt Decomposition (Block Decomposition)

- **Block array decomposition** — partition n elements into ~√n blocks of size ~√n; maintain a per-block aggregate for O(√n) range query / O(1) point update (build O(n)).
- **Block size tuning** — optimal block size √(n·costUpdate/costQuery); not always √n when update and query costs differ.
- **Range query, point update** — recompute one block aggregate on update, scan ≤2 partial + O(√n) whole blocks on query (O(√n) per op).
- **Range update, point query** — store per-block lazy delta; point query sums element plus its block delta (O(√n) update, O(1)/O(√n) query).
- **Range update + range query with lazy blocks** — per-block lazy tag plus aggregate; partial blocks rebuilt, whole blocks tagged (O(√n) per op).
- **Block aggregates** — sum, min/max, count, gcd, or any associative monoid stored per block for fast partial recombination.
- **Sqrt buckets for ordered sets** — maintain √n buckets of sorted values supporting insert/delete/k-th/rank in O(√n) with periodic rebalancing.

#### Sqrt Rebuilding (Periodic Reconstruction)

- **Sqrt rebuilding technique** — buffer updates and rebuild the whole structure every √n operations, keeping a small "dirty" list scanned per query (amortized O(√n)).
- **Insert-only / append buffers** — keep a sorted main array plus an unsorted tail of <√n inserts; merge-rebuild when the tail overflows.
- **Deletion via tombstones + rebuild** — lazily mark deletions, physically compact during the next √n-triggered rebuild.
- **Amortized rebuild analysis** — O(rebuildCost / √n) per operation amortized; balances heavy reconstruction against cheap incremental queries.
- **Two-level "buffer + base" pattern** — answer queries against base structure combined with a brute-force scan of the small live buffer.

#### Sqrt Tree

- **Sqrt Tree** — recursive sqrt decomposition giving O(1) static range queries on any associative operation with O(n log log n) build (no inverse needed).
- **Prefix / suffix per block** — store block prefix and suffix folds so a query spanning blocks combines suffix + middle + prefix in O(1).
- **Between-block (sparse) table** — precompute folds over contiguous block ranges so the "middle" of a multi-block query is one lookup.
- **Recursive sub-block layering** — within-block queries solved by recursing the same structure, yielding the log log n depth.
- **Updateable Sqrt Tree** — supports point updates in O(√n) by rebuilding affected block layers while keeping O(1) queries.

#### Sqrt on Sequences & Specialized Operations

- **Block-based k-th order statistic** — keep each block sorted; binary-search a threshold counting elements ≤ x across blocks (O(√n log n) or O(√n)).
- **Range rank / count-less-than** — per-block sorted copies answer "how many ≤ x in [l,r]" by binary search in touched blocks.
- **Chtholly Tree / "ODT" (interval assignment)** — store maximal equal-value runs in a balanced set; range-assign collapses intervals, fast under random/assign-heavy data (amortized near O(n log n)).
- **Block linked list / unrolled list** — sequence stored as √n-sized nodes for O(√n) insert/delete/index with cache-friendly scans.
- **Decremental / additive block tags** — combine multiplicative + additive lazy per block for affine range updates.

### Mo's Algorithm (Offline Range Query Reordering)

- **Mo's algorithm core** — sort queries and move two pointers (l, r) incrementally; total pointer travel O((n+q)√n) using O(1) add/remove (O((n+q)√n)).
- **Add / remove operations** — maintain an answer for the current window supporting insert-element and delete-element in O(1) (or O(log)) each.
- **Block ordering rule** — sort by (l/blockSize) ascending, then by r; r is monotone within a block giving the √n bound.
- **Even-odd r alternation** — sort r ascending in even blocks, descending in odd blocks to halve total r movement (constant-factor speedup).
- **Optimal block size** — block ≈ n/√q minimizes total movement when q ≠ n (tune to n·√(?) precisely).
- **Hilbert-curve ordering** — order queries along a Hilbert space-filling curve over the (l,r) plane for provably near-optimal pointer travel.
- **Add-only vs add-and-remove** — windows that only grow are cheaper; full Mo requires symmetric removal which some aggregates (e.g., max) cannot do.

#### Mo Variants & Extensions

- **Mo with rollback (add-only Mo)** — when removal is impossible (e.g., maintaining maximum/distinct-with-undo), only add and snapshot/rollback at block boundaries (O((n+q)√n)).
- **Rollback block structure** — fix left pointer per block to block-right edge, extend right monotonically, and rebuild left contributions per query with undo stack.
- **Mo with updates (3D Mo)** — add a time dimension; sort by (l-block, r-block, time) and move three pointers, advancing/reverting updates (O(n^{5/3}) with block ≈ n^{2/3}).
- **3D Mo block sizing** — set block size ≈ n^{2/3} so total movement is O(n^{5/3}); update pointer cost dominates.
- **Mo on trees (Euler tour + Mo)** — flatten tree by Euler/first-last occurrence so a path/subtree becomes a contiguous range answerable by Mo.
- **Path queries via LCA correction** — node appears twice in Euler array; XOR-toggle presence, then add LCA explicitly when LCA is not an endpoint.
- **Mo on trees with updates** — combine Euler flattening with the time dimension for path queries under point updates (O(n^{5/3})).
- **Mo + DSU / Mo + small structures** — pair window maintenance with a rollback-capable auxiliary structure for connectivity-style window aggregates.
- **Mo with Fenwick/segment-tree per move** — when add/remove costs O(log n) the bound becomes O((n+q)√n · log n); often still acceptable.
- **Distinct-count / "HH problem"** — canonical Mo application counting distinct elements in a range via per-value frequency counters.
- **Range mode (most frequent)** — Mo maintains frequency-of-frequency buckets; removal-unfriendly so often rollback Mo.

### Divide & Conquer on Queries (Offline D&C)

- **D&C on the query/time axis** — recursively split the operation sequence; let updates in the left half affect queries in the right half (O(T log T · costApply)).
- **CDQ divide & conquer** — sort by first dimension, recurse on index midpoint, and let left-half updates contribute to right-half queries via a structure on the second dimension (O(n log² n)).
- **3D dominance / partial-order counting** — count points dominated in all 3 coordinates: sort dim 1, CDQ on dim 2, Fenwick on dim 3 (O(n log² n)).
- **CDQ for offline updates+queries** — treat (time, position, value) as 3 dimensions; CDQ removes the time dimension by recursion.
- **CDQ on DP (1D/1D optimization)** — resolve dependencies where dp[i] depends on dp[j<i] by computing left half then relaxing right half (often O(n log² n)).
- **D&C with cross-contribution merge** — at each merge step apply left-half effects, answer right-half queries, then undo if needed.
- **Nested CDQ (4D)** — recurse twice to handle four-dimensional partial orders at O(n log³ n).

#### Segment Tree on Time (Offline Dynamic Connectivity)

- **Segment tree over the timeline** — build a segment tree whose leaves are time points; each element/edge alive over [t1,t2) is inserted at O(log T) canonical nodes.
- **Interval-of-existence decomposition** — every update's lifespan is split into O(log T) timeline-segment-tree nodes, total O(M log T) edge insertions.
- **DFS over the time tree with rollback** — DFS the segment tree, applying a node's edges on entry and undoing them on exit, answering queries at leaves.
- **Offline dynamic connectivity** — supports add-edge / remove-edge / connectivity-query offline in O(q log q · α) using DSU with rollback over the time tree.
- **Offline dynamic MST / min-edge** — maintain spanning-forest weight under edge add/remove via rollback DSU on the time segment tree.
- **Offline bipartiteness / parity DSU on time** — track bipartite/odd-cycle status under edge insertions and deletions over the timeline.
- **Lifetime computation for deletions** — pair each insert with its matching delete (or end-of-time) to define the [start,end) interval.

#### Rollback / Undo Structures

- **DSU with rollback (union by rank/size only)** — record (root, old-rank) on each union; undo by popping the stack — no path compression so ops are O(log n).
- **Why no path compression** — compression makes individual undos impossible; union-by-size alone preserves O(log n) and clean rollback.
- **Rollback stack of mutations** — log every primitive write with its previous value; undo replays the log in reverse to a checkpoint.
- **Checkpoint / restore (savepoints)** — record stack length as a checkpoint and truncate-with-undo to return to a prior state.
- **Persistent vs rollback DSU** — rollback gives O(log n) and tree-shaped time travel; fully persistent DSU gives arbitrary-version access at higher cost.
- **Rollback Fenwick / sequence structures** — any structure with logged O(1) primitive writes can be wrapped for undo within D&C frameworks.
- **Time-travel structures** — generic term for rollback/checkpoint machinery enabling DFS-order state restoration in offline D&C.

### Parallel Binary Search

- **Parallel binary search** — binary-search the answer for all queries simultaneously, sharing one monotone sweep of events per round (O((n+q) log n · costStep)).
- **Monotone predicate requirement** — each query's answer is the first time/threshold a monotone condition flips true; enables shared search.
- **Round structure** — maintain a lo/hi per query; each round, process events up to each query's mid in event order, then narrow intervals.
- **Bucketing queries by midpoint** — group queries by current mid so events are applied once globally per round rather than per query.
- **Event application with rollback or rebuild** — reset/replay the structure each of the O(log) rounds; total O(log) full sweeps.
- **Classic application: "kth largest weight to connect"** — find minimum number of inserted edges/weights making two nodes connected (DSU-driven check).
- **PBS vs persistent / merge-sort tree** — PBS converts an online k-th/threshold query into log rounds of offline sweeps, avoiding persistence.
- **Integration with rollback DSU** — common pairing where the per-round check is a connectivity/threshold test.

### Offline Tree & Graph Query Techniques

- **Offline LCA (Tarjan's algorithm)** — single DFS with DSU; answer each (u,v) when the second endpoint is reached, LCA = find(other) (O((n+q)·α)).
- **Tarjan offline LCA mechanics** — color nodes during DFS, union finished subtrees into parent, ancestor of finished node = current find-root.
- **Offline DSU (Tarjan union-find for queries)** — batch connectivity/ancestor queries resolved during a single union-find sweep.
- **Small-to-large merging (sack / DSU on tree)** — merge each child's set into the largest child's set; each element moved O(log n) times (O(n log n) or O(n log² n)).
- **DSU on tree (sack) for subtree queries** — keep the heavy child's contribution, recompute light children, answer subtree queries offline in O(n log n).
- **Heavy-light vs sqrt tradeoffs** — HLD gives O(log² n)/O(log n) path queries online; sqrt-on-tree gives O(√n) with simpler updates and offline flexibility.
- **Sqrt decomposition on trees** — block the tree by jump-pointers/√n-depth chains so path queries skip √n super-nodes (O(√n) per query).
- **Block-tree / tree sqrt buckets** — partition tree into O(√n) connected blocks each of size ~√n for path/subtree aggregation.
- **Euler-tour + offline sweep** — flatten tree to an array (in/out times) and answer subtree queries as range queries with offline sorting.
- **Auxiliary tree / virtual tree (offline batches)** — build the minimal Steiner-like tree on each query's relevant nodes to process multi-query batches cheaply (see Trees domain).
- **Offline path queries via LCA + difference** — answer path(u,v) aggregates by combining root-paths and subtracting at LCA (prefix/difference on tree).

### Sorting, Batching & Persistence Tradeoffs

- **Sort-by-right-endpoint sweep** — process queries in increasing right endpoint while maintaining a Fenwick of "last occurrence" for distinct-count style queries (O((n+q) log n)).
- **Offline distinct-count by last-occurrence** — for each value keep its most recent index; query [l,r] counts indices ≥ l in a Fenwick.
- **Two-pointer offline (monotone windows)** — when query bounds are monotone after sorting, a single forward/backward pass suffices (O(n+q)).
- **Batch by block / by root / by component** — partition queries so each batch shares an expensive precomputation amortized over the batch.
- **Persistence vs offline tradeoff** — persistent segment tree answers online k-th/range queries in O(log n) with O(n log n) space; offline merge-sort-tree/Mo avoids the memory but loses online capability.
- **Merge-sort tree (offline-friendly)** — segment tree of sorted lists answering range-rank queries in O(log² n) without persistence.
- **Wavelet tree (offline/online)** — succinct structure for range rank/select/k-th; alternative to persistence for static arrays (see Strings/Succinct domain).
- **Fractional cascading** — speeds up repeated binary searches across many sorted lists from O(k log n) to O(log n + k) (see Geometry / Data-Structures domain).

### Advanced & Lesser-Known Offline Patterns

- **Offline 2D dominance via Fenwick sweep** — sort points and queries by x, sweep adding to a Fenwick on y, answering prefix-dominance counts (O((n+q) log n)).
- **Offline "color update + count distinct"** — combine Mo-with-updates or segment-tree-on-time for distinct counting under modifications.
- **D&C over value ranges (divide on answer space)** — recursively split the value domain, partitioning elements/queries to each side (e.g., offline k-th on a static multiset).
- **Centroid decomposition for offline path queries** — decompose tree by centroids; answer all paths through a centroid per layer (O(n log n) layers) (see Trees domain).
- **Offline interval/segment stabbing** — sort intervals and query points; sweep maintaining active set for stabbing counts.
- **Sqrt decomposition on queries (block of queries)** — process queries in √q blocks, applying a heavy precompute once per block and brute-forcing within (O((n+q)√q) style).
- **Offline reachability / DAG ancestor batching** — topological sweep answering ancestor/descendant queries with bitsets or DSU.
- **Offline range-mex / smallest-absent** — sort by right endpoint, maintain last-occurrence + segment tree to answer minimum-excluded queries.
- **Time-segment-tree + Li Chao / convex hull** — insert lines with lifespans into the time segment tree for offline dynamic convex-hull-trick queries.
- **Offline KDQ / multidimensional CDQ chains** — chain CDQ layers to strip successive dimensions for high-dimensional dominance.
- **Aggregation by "contribution technique"** — reframe each query as a sum of independent contributions that can be precomputed and combined offline.
- **Square-root on update count (rebuild threshold)** — rebuild an auxiliary index after every √(updates) modifications, querying base + small delta list in between.

#### Complexity Cheat-Sheet (Offline Toolbox)

- **Sqrt decomposition** — O(√n) per operation, O(n) space; simplest range structure with easy updates.
- **Sqrt rebuilding** — amortized O(√n) per op; trades periodic O(n) rebuilds for cheap queries.
- **Sqrt tree** — O(1) static query, O(n log log n) build; O(√n) updateable variant.
- **Mo's algorithm** — O((n+q)√n); 3D Mo O(n^{5/3}); rollback Mo same bound with add-only.
- **CDQ / D&C on queries** — O(n log² n) typical (one log per recursion level, one per inner structure).
- **Segment tree on time + rollback DSU** — O(q log q · α) for offline dynamic connectivity.
- **Parallel binary search** — O((n+q) log n · costStep), log rounds of full sweeps.
- **Tarjan offline LCA** — O((n+q)·α(n)), single DFS.
- **Small-to-large / DSU on tree** — O(n log n) (one extra log if inner ops are logarithmic).

<sub>[↑ Back to top](#table-of-contents)</sub>

---

# 🧠 Part — Optimization & Intractability

## 30. Mathematical & Continuous Optimization

### Foundations: Convexity, Unimodality & Optimization Landscape

#### Convex Sets & Convex Geometry
- **Convex set** — a set where the segment between any two points stays inside; intersections of half-spaces, balls, simplices.
- **Convex hull** — smallest convex set containing a point set; underlies LP feasible regions (see Computational Geometry for construction).
- **Polyhedron / polytope** — intersection of finitely many half-spaces; bounded polyhedron = polytope; LP feasible regions are polyhedra.
- **Vertex / extreme point** — point not expressible as a strict convex combination of others; LP optima occur at vertices.
- **Supporting hyperplane** — hyperplane touching a convex set with the set on one side; basis of separation arguments.
- **Separating hyperplane theorem** — two disjoint convex sets can be separated by a hyperplane; theoretical backbone of LP duality and SVMs.
- **Carathéodory's theorem** — any point in the convex hull of S in R^d is a convex combination of at most d+1 points of S.
- **Minkowski–Weyl theorem** — a polyhedron equals (sum of) a polytope plus a polyhedral cone; vertex vs. half-space duality of representations.
- **Farkas' lemma** — exactly one of {Ax=b, x≥0} or {yᵀA≤0, yᵀb>0} is solvable; certificate of infeasibility, proves LP duality.
- **Recession / characteristic cone** — directions of unboundedness of a polyhedron; detects unbounded LPs.

#### Convex & Concave Functions
- **Convex function** — epigraph is a convex set; f(λx+(1−λ)y) ≤ λf(x)+(1−λ)f(y); local min is global min.
- **Concave function** — negative of a convex function; local max is global max; mirror of convex theory.
- **Strict / strong convexity** — strict inequality / curvature lower bound (mu-strongly convex); guarantees unique minimizer and faster convergence.
- **First-order convexity condition** — f convex iff f(y) ≥ f(x)+∇f(x)ᵀ(y−x); tangent under-estimates the function.
- **Second-order convexity condition** — twice-differentiable f convex iff Hessian ∇²f ⪰ 0 (positive semidefinite) everywhere.
- **Jensen's inequality** — for convex f and weights summing to 1, f(Σwᵢxᵢ) ≤ Σwᵢf(xᵢ); core inequality for bounds and probability.
- **Operations preserving convexity** — nonnegative weighted sums, pointwise max/sup, composition with affine maps, perspective; build convex models compositionally.
- **Quasiconvex / quasiconcave functions** — sublevel/superlevel sets convex; unimodal in higher dimensions; minimizable by bisection on level sets.
- **Unimodal function (1-D)** — single peak/valley; strictly increasing then decreasing (or vice versa); target of ternary/golden-section search.
- **Convex envelope / lower convex hull** — greatest convex under-estimator; appears in DP cost optimization (see Convex Hull Trick under DP/Dynamic Programming).
- **Subgradient & subdifferential** — set of supporting-slope vectors for nondifferentiable convex f; 0 ∈ ∂f(x*) characterizes the minimizer.
- **Proximal operator** — prox_{ηg}(v)=argmin_x g(x)+‖x−v‖²/(2η); closed-form for many regularizers, foundation of proximal methods.

### One-Dimensional & Unconstrained Search

#### Search on Unimodal Functions
- **Ternary search (real)** — repeatedly split [l,r] by two interior points, drop the worse third; finds extremum of a unimodal f in O(log((r−l)/ε)) evaluations.
- **Ternary search (integer)** — discrete version on integer domain; shrink to a small window then scan; careful when m1=m2 to avoid infinite loop, finish by linear check of the last few points.
- **Boundary & equality pitfalls** — handle f(m1)==f(m2) (move both bounds inward only when strictly safe under strict unimodality), off-by-one on integer mid points, plateaus that break strict unimodality.
- **Golden-section search** — reuse one interior evaluation each step using ratio φ=(1+√5)/2; ~1 function eval per iteration vs. 2 for ternary, same O(log) rate; ideal for expensive f.
- **Fibonacci search (optimization)** — golden-section variant using Fibonacci ratios for a fixed, prescribed number of evaluations; optimal interval reduction with discrete steps.
- **Nested ternary search** — ternary over one variable whose inner objective is itself optimized by ternary; solves 2-D (or k-D) jointly-unimodal/convex objectives in O(log² ...).
- **Ternary search over convex-on-a-path objectives** — minimizing time/distance/cost that is convex in a parameter (e.g., meeting point, optimal speed, geometric median in 1-D).

#### Derivative-Based & Numerical 1-D Root/Extremum Finding
- **Bisection method** — bracket a sign change of a monotone function/derivative; halves interval each step, O(log) to tolerance; robust, derivative-free.
- **Newton's method (1-D)** — iterate x ← x − f(x)/f'(x); quadratic local convergence; for optimization apply to f'(x)=0 using f''.
- **Secant / regula falsi** — derivative-free Newton approximations using finite differences; super-linear (golden-ratio order) convergence.
- **Brent's method** — combines bisection, secant, and inverse quadratic interpolation; fast and guaranteed-bracketed; standard robust 1-D minimizer/root finder.

### Multidimensional Unconstrained Optimization (Intro)

- **Gradient descent** — step x ← x − η∇f(x); O(1/k) rate for convex, linear for strongly convex; needs step-size/line-search tuning.
- **Subgradient method** — for nondifferentiable convex f use any subgradient; diminishing step sizes give O(1/√k) convergence; used for LP/SDP relaxations and Lagrangian duals.
- **Line search (exact / backtracking / Armijo)** — choose step length along a descent direction; Wolfe conditions ensure sufficient decrease and curvature.
- **Newton's method (multivariate)** — step uses inverse Hessian H⁻¹∇f; quadratic local convergence; expensive O(n³)/iteration to factor H.
- **Quasi-Newton (BFGS / L-BFGS)** — approximate the inverse Hessian from gradients; super-linear without forming H; L-BFGS uses limited memory for large n.
- **Conjugate gradient** — minimizes convex quadratics in ≤ n steps without storing a matrix; also a linear solver for SPD systems.
- **Coordinate descent** — optimize one coordinate (or block) at a time; simple, effective for separable/sparse problems.
- **Nelder–Mead simplex (derivative-free)** — reflect/expand/contract/shrink a simplex of points; heuristic, no gradients, no convergence guarantee.
- **Momentum & Nesterov acceleration** — add inertia / look-ahead extrapolation to gradient descent; accelerates convex smooth problems to O(1/k²).
- **Convergence rates & conditioning** — role of Lipschitz gradient L and strong-convexity mu; condition number kappa=L/mu controls speed; momentum/Nesterov acceleration (O(1/k²)).

#### Constrained First-Order Methods (Projection / Linearization)

- **Projected gradient method** — take a gradient step then Euclidean-project back onto the convex feasible set; same rates as gradient descent (O(1/k) convex, linear strongly convex) when projection is cheap.
- **Proximal gradient method (forward–backward splitting)** — gradient step on the smooth part then prox of the nonsmooth part; minimizes f+g (smooth+simple), O(1/k) rate; specializes to projected gradient when g is an indicator function.
- **Accelerated proximal gradient (FISTA/ISTA)** — Nesterov-momentum proximal gradient; O(1/k²) for composite convex objectives, standard for L1/lasso sparse regression.
- **Frank–Wolfe (conditional gradient) method** — replace projection with a linear-minimization oracle over the feasible polytope; step toward the LP-minimizing vertex, O(1/k) rate; projection-free, yields sparse iterates, cheap when the LP-oracle is far easier than projection.
- **Frank–Wolfe variants** — away-step / pairwise / fully-corrective FW achieve linear convergence on polytopes; key when projections are expensive but linear optimization is easy.
- **Mirror descent** — gradient descent in a non-Euclidean (Bregman) geometry; entropy mirror map for the simplex improves dimension dependence.

### Linear Programming (LP)

#### Formulations & Geometry
- **Standard / canonical form** — maximize cᵀx s.t. Ax ≤ b, x ≥ 0 (or equality form Ax=b, x≥0); any LP convertible via slack/surplus/free-variable splitting.
- **Slack, surplus & artificial variables** — convert inequalities to equalities and seed an initial basis; artificials drive Phase I feasibility.
- **Feasible region as a polyhedron** — intersection of constraint half-spaces; convex; optimum at a vertex (or along an edge/face if tied).
- **Basic feasible solution (BFS)** — vertex corresponding to a choice of basic columns with x_B = B⁻¹b ≥ 0; correspondence between vertices and bases.
- **Degeneracy** — a BFS with a zero basic variable; multiple bases per vertex; cause of simplex cycling.
- **Bounded / unbounded / infeasible LP** — detect via objective direction in recession cone (unbounded) or empty region (infeasible); certificates via duality/Farkas.

#### Simplex Method
- **Primal simplex algorithm** — move vertex-to-vertex along improving edges by pivoting; choose entering var with favorable reduced cost, leaving var by ratio test; terminates at optimum or proves unboundedness.
- **Tableau / dictionary form** — bookkeeping of basis via row operations; reduced costs row drives entering-variable choice.
- **Reduced costs & optimality test** — c̄ = c − cᵀ_B B⁻¹A; nonpositive (for max) reduced costs certify optimality.
- **Ratio test (minimum-ratio rule)** — pick leaving variable to keep feasibility (x_B ≥ 0) while taking the largest improving step.
- **Pivoting rules** — Dantzig (most-negative reduced cost), steepest edge, Devex; trade per-iteration cost vs. number of pivots.
- **Bland's anti-cycling rule** — always pick the lowest-index eligible entering/leaving variable; provably prevents cycling, guaranteeing termination on degenerate LPs.
- **Lexicographic / perturbation method** — perturb b to break ties; alternative anti-cycling guarantee.
- **Two-phase simplex** — Phase I minimizes sum of artificials to find a BFS; Phase II optimizes the real objective from that BFS.
- **Big-M method** — fold artificial penalty M into the objective to combine the two phases; sensitive to M magnitude/numerical scaling.
- **Revised simplex method** — maintain B⁻¹ (or its factorization) instead of the full tableau; far more efficient and numerically stable for large sparse LPs.
- **Bounded-variable simplex** — handle l ≤ x ≤ u directly without splitting; keeps problem size small.
- **Worst-case complexity (Klee–Minty cube)** — simplex can take exponentially many pivots; yet near-linear in practice (smoothed analysis explains this).

#### Duality & Optimality Conditions
- **LP dual construction** — every primal (max cᵀx, Ax≤b, x≥0) has a dual (min bᵀy, Aᵀy≥c, y≥0); transpose constraints, swap roles of b and c.
- **Weak duality** — any dual-feasible objective bounds any primal-feasible objective; gives instant optimality certificates.
- **Strong duality** — if either problem has a finite optimum, both do and their optima are equal; zero duality gap for LP.
- **Complementary slackness** — at optimum, x_j>0 ⇒ j-th dual constraint tight, and y_i>0 ⇒ i-th primal constraint tight; pairs primal/dual support.
- **Dual simplex method** — maintain dual feasibility & complementary slackness, restore primal feasibility; ideal for re-optimization after adding constraints/cuts.
- **Shadow prices / sensitivity analysis** — dual values = marginal value of relaxing a constraint; ranges over which basis/optimal cost stays valid.
- **Parametric LP** — track how the optimum changes as objective/RHS vary linearly; piecewise-linear value function.

#### Polynomial-Time LP & Advanced Methods
- **Ellipsoid method** — shrinking ellipsoids around the feasible set; first polynomial-time LP algorithm; mainly theoretical, powers separation-oracle results.
- **Interior-point / barrier methods** — traverse the interior via Newton steps on a log-barrier; primal-dual path-following runs in polynomial time, strong on large LPs.
- **Karmarkar's algorithm** — projective interior-point method; practical polynomial-time LP breakthrough.
- **Separation oracle & equivalence** — optimization over a convex body reduces (via ellipsoid) to a separation routine; solves LPs with exponentially many constraints if separation is poly.
- **Seidel's randomized LP (low dimension)** — expected O(d! · n) for n constraints in fixed dimension d; ideal when #variables is tiny (e.g., d ≤ ~10) and #constraints large.
- **LP-type problems (Sharir–Welzl / Clarkson)** — abstract framework (LP, smallest enclosing ball, etc.) solvable in expected linear time for fixed dimension via violation tests.

#### LP Modeling of Regression & Robust Fitting

- **L1 (least-absolute-deviations) regression as LP** — minimize Σ|rᵢ| by adding bounds rᵢ ≤ uᵢ and −rᵢ ≤ uᵢ and minimizing Σuᵢ; robust to outliers, solved by simplex/interior-point.
- **L∞ (Chebyshev / minimax) regression as LP** — minimize the maximum absolute residual t s.t. −t ≤ rᵢ ≤ t for all i; one epigraph variable t turns minimax fitting into a linear program.
- **Piecewise-linear & absolute-value modeling** — replace |x|, max, and convex PWL terms by auxiliary variables and linear inequalities to keep models linear.

#### LP in Competitive Programming
- **Modeling tricks** — encode flows, matchings, scheduling, covering/packing as LPs; recognize when constraints are totally unimodular for integral optima.
- **Total unimodularity (TU)** — if A is TU and b integral, all LP vertices are integral, so LP = ILP (network/bipartite/interval matrices); avoids needing ILP solvers.
- **Half-plane intersection as LP feasibility** — 2-variable LP via intersecting half-planes / incremental randomized method; O(n) expected (see Computational Geometry).
- **Small-dimension LP by ternary/nesting** — for 2–3 variables, nested ternary search over a convex feasible objective beats coding a full simplex.

### Integer & Combinatorial Optimization

#### Integer Linear Programming (ILP)
- **ILP formulation** — LP with integrality constraints on some/all variables; NP-hard in general; expressive for scheduling, covering, assignment.
- **0/1 (binary) ILP** — variables in {0,1}; models selection/SAT/knapsack/set-cover problems.
- **Mixed-integer LP (MILP)** — mix of continuous and integer variables; standard industrial optimization model.
- **LP relaxation** — drop integrality and solve the LP; gives bounds (upper for max), guides rounding and branching; tight when matrix is TU.
- **Integrality gap** — worst-case ratio between ILP optimum and LP-relaxation optimum; measures relaxation quality for approximation.
- **Branch and bound** — recursively split on a fractional variable, prune nodes whose LP bound can't beat the incumbent; exact MILP solving.
- **Cutting planes (Gomory cuts)** — add valid inequalities that cut off fractional LP optima without removing integer points; tighten the relaxation.
- **Branch and cut** — branch-and-bound interleaved with cutting planes; the engine of modern MILP solvers.
- **Branch and price (column generation)** — generate variables (columns) on the fly via a pricing subproblem; solves huge set-partitioning/routing LPs.
- **Lagrangian relaxation** — dualize hard constraints into a penalized objective; solve easy subproblems, optimize multipliers by subgradient ascent for strong bounds.
- **Dantzig–Wolfe decomposition** — reformulate block-structured LPs into a master problem over extreme points solved by column generation.
- **Benders decomposition** — split into a master (integer) and subproblem (continuous), iteratively add feasibility/optimality cuts.

#### Column Generation & Concrete Models
- **Column generation principle** — solve a restricted master LP over a subset of columns, then a pricing subproblem finds a negative-reduced-cost column to add; repeat until none exists.
- **Cutting-stock problem (Gilmore–Gomory)** — minimize stock rolls cut into demanded widths; columns = feasible cutting patterns, pricing subproblem is a bounded knapsack maximizing pattern reduced cost; canonical column-generation model.
- **Vehicle-routing / crew-scheduling set partitioning** — columns = feasible routes/duties, pricing is a resource-constrained shortest path; column generation embedded in branch-and-price.
- **Restricted master + pricing loop** — alternate LP re-optimization (dual prices) with subproblem generation; terminate when the minimum reduced cost is nonnegative.

#### Knapsack & Fractional Relaxations
- **0/1 knapsack via LP relaxation** — relaxing the integrality gives the fractional knapsack bound used to prune branch-and-bound (DP solution: see Dynamic Programming).
- **Fractional (continuous) knapsack** — greedy by value/weight ratio fills items, splitting the last; LP-optimal in O(n log n) (or O(n) with median-of-medians selection).
- **LP bound for knapsack pruning** — sort by ratio, fill greedily, the fractional residual gives an upper bound for B&B search.

### Optimization Reductions for Competitive Programming

#### Binary/Ternary Search on the Answer
- **Binary search on the answer** — monotone feasibility predicate ⇒ search the answer value; turns optimization into repeated decision; O(log(range)·check) (see Searching/Binary Search).
- **Minimize the maximum (minimax) / maximize the minimum** — binary search the bound, greedily test feasibility; classic for scheduling, partitioning, aggressive-cows (cross-ref Searching).
- **Parametric search (Megiddo)** — simulate a decision algorithm with the parameter symbolic, resolving comparisons to binary-search the critical value; converts decision into optimization, O(decision · log) (often replaced in practice by plain binary search).
- **Binary search on real-valued answers** — fixed iteration count or epsilon tolerance; watch precision and comparison directions.

#### Fractional Programming
- **Fractional programming** — optimize a ratio f(x)/g(x) over a feasible set; reduces to a sequence of simpler problems via parameterization.
- **Binary search on the ratio** — guess lambda, test feasibility of f(x) − lambda·g(x) ⪌ 0; monotone in lambda, so bisection finds the optimal ratio; O(log(range)·subproblem).
- **Dinkelbach's algorithm** — Newton-like iteration lambda ← f(x*)/g(x*) after maximizing f(x)−lambda·g(x); super-linear convergence, usually fewer iterations than binary search.
- **Optimal density / minimum mean cycle** — maximize/minimize ratio of sums over a cycle/subgraph (e.g., min mean cycle by Karp, max density subgraph) via the lambda-parameterized test (see Graphs).
- **Maximum-ratio spanning tree / path** — apply ratio search atop an MST/shortest-path subroutine in the lambda-reduced graph.
- **Optimal ratio cycle in DP / on-tree** — fractional objectives over paths solved by parametric/Dinkelbach plus a feasibility DP or shortest-path check.

#### Lagrangian / Aliens Trick
- **Lagrange multipliers (equality constraints)** — at an optimum, ∇f = Σ lambda_i ∇g_i; stationary points of the Lagrangian L = f − Σ lambda_i g_i.
- **Aliens trick / Lagrangian optimization (WQS / Alien DP)** — penalize a "use exactly k" constraint by lambda per use, binary-search lambda so the unconstrained-count optimum hits k; needs convexity of value-vs-k (see Dynamic Programming).
- **Convexity prerequisite & tie handling** — the optimal-value-vs-count curve must be convex/concave; handle the segment where multiple counts share the optimal penalized value to recover exactly k.

#### Slope/Hull Optimization for DP (cross-references)
- **Convex Hull Trick** — minimize/maximize over linear functions of a query parameter; amortized O(1)/O(log) per query (see Dynamic Programming).
- **Li Chao tree** — segment-tree of lines for dynamic min/max of linear functions; O(log) per insert/query (see Dynamic Programming).
- **Divide & Conquer DP & Knuth/SMAWK optimizations** — exploit monotone/convex cost structure to cut a quadratic DP to O(n log n) (see Dynamic Programming).

### Constrained Nonlinear & Conic Optimization (Intro)

#### Optimality Conditions
- **Lagrangian function** — L(x,lambda,mu)=f(x)+Σlambda_i g_i(x)+Σmu_j h_j(x); unifies equality/inequality constraints.
- **Lagrange conditions (equality-only)** — with only equalities g_i(x)=0, optimality is stationarity ∇f=Σlambda_i∇g_i plus feasibility; multipliers lambda are sign-unrestricted, no complementarity required.
- **KKT conditions (with inequalities)** — add inequality multipliers mu_j ≥ 0 (dual feasibility) and complementary slackness mu_j h_j(x)=0; the sign restriction and complementarity are precisely what distinguish inequality KKT from pure-equality Lagrange.
- **KKT sufficiency under convexity** — for convex f over a convex feasible set meeting a constraint qualification, KKT points are global optima; necessary in general for smooth problems.
- **Active-set methods** — guess which inequalities are tight (active), solve the resulting equality-constrained Lagrange system, then add/drop constraints when a multiplier turns negative or feasibility breaks; iterate to the correct active set (basis of QP solvers).
- **Constraint qualifications (LICQ/Slater)** — regularity ensuring KKT multipliers exist; Slater's condition gives strong duality for convex programs.
- **Duality gap & Lagrangian dual** — sup over multipliers of the dual function lower-bounds the primal; zero gap under convexity + Slater.
- **Penalty & barrier reformulations** — replace constraints by soft penalty terms (exterior) or log-barriers (interior) to reduce to unconstrained problems; tie to interior-point methods.
- **Augmented Lagrangian / ADMM** — combine multipliers with a quadratic penalty; ADMM splits across blocks for large-scale/distributed convex problems.

#### Convex Programs & Conic Forms
- **Convex optimization (general)** — minimize a convex objective over a convex set; local optimum is global; solvable in polynomial time to any accuracy.
- **Quadratic programming (QP)** — convex quadratic objective with linear constraints; active-set or interior-point methods; basis of SVMs, portfolio optimization.
- **Second-order cone programming (SOCP)** — linear objective over second-order (ice-cream) cones; models norms, robust LP, and Euclidean-distance constraints.
- **Semidefinite programming (SDP)** — optimize over positive-semidefinite matrix variables; relaxes hard combinatorial problems (Goemans–Williamson MaxCut, Lovász theta).
- **Geometric programming** — posynomial objective/constraints convexified by log-substitution; engineering design.
- **Conic duality** — generalizes LP duality to conic programs with the cone's dual; underpins interior-point solvers.

#### Geometric Optimization Instances
- **Minimum enclosing ball / smallest enclosing circle** — smallest ball covering all points; Welzl's randomized algorithm O(n) expected; an SOCP/LP-type problem (see Computational Geometry).
- **Smallest enclosing ellipse (Löwner–John)** — minimum-volume enclosing ellipsoid; SDP/convex; core of the ellipsoid method.
- **Geometric median (Fermat–Weber point)** — minimize sum of Euclidean distances; convex, solved by Weiszfeld iteration or ternary-in-each-coordinate; no closed form.
- **1-center / Chebyshev center** — center minimizing max distance (largest inscribed ball center of a polytope); an LP/SOCP (see Computational Geometry).
- **Least-squares / linear regression** — least squares = convex QP with closed-form normal equations (AᵀA)x=Aᵀb; ridge adds L2 regularization, lasso (L1) solved by proximal gradient.

### Specialized LP-Structured Problems (cross-references)

- **Assignment problem** — minimum-cost perfect matching in a bipartite graph; Hungarian (Kuhn–Munkres) algorithm O(n³); an integral LP via TU (see Graphs/Flows).
- **Transportation problem** — supply/demand min-cost flow on a bipartite network; solved by network simplex or transportation simplex (NW-corner + MODI/stepping-stone).
- **Transshipment problem** — transportation with intermediate nodes; equivalent to general min-cost flow.
- **Network simplex** — specialization of the simplex method to min-cost-flow LPs using spanning-tree bases; pivots are tree updates, very fast in practice (see Graphs/Flows).
- **Min-cost max-flow / max-flow** — LP-expressible flow optimizations; SSP, cost-scaling, push-relabel (see Graphs/Flows).
- **LP duality of max-flow/min-cut** — max-flow equals min-cut as primal/dual LPs; canonical combinatorial duality (see Graphs/Flows).
- **Covering & packing LPs** — set cover / vertex cover / matching LP relaxations and their integrality (König's theorem from LP duality) (see Graphs and Approximation).
- **Multiplicative weights update (MWU)** — meta-algorithm approximately solving covering/packing LPs and zero-sum games via online learning; fast (1±ε)-approximate LP.
- **Zero-sum games & minimax theorem** — optimal mixed strategies via an LP; von Neumann minimax = LP strong duality (see Game Theory).

<sub>[↑ Back to top](#table-of-contents)</sub>

---

## 31. Intractability: NP-Completeness, Approximation & Heuristics

### Foundations: Problems, Decision vs Optimization, Complexity Measures

- **Decision problem** — yes/no question over instances (e.g., "is there a tour of length ≤ k?"); the native object of study in complexity theory.
- **Optimization problem** — find a feasible solution minimizing/maximizing an objective; comes with search, evaluation, and decision (threshold) variants.
- **Decision ↔ optimization equivalence** — binary search over the objective threshold reduces optimization to polynomially many decision queries (O(log range) calls).
- **Search vs decision (self-reducibility)** — for many NP-complete problems a decision oracle yields an actual solution via polynomial self-reduction (fix variables/vertices one at a time).
- **Function problems (FNP/FP)** — problems whose output is a string/witness rather than a bit; FNP is the search analogue of NP.
- **Counting problems (#P)** — count the number of solutions (e.g., #SAT, permanent); #P-complete problems are at least as hard as NP (see Counting & #P below).
- **Promise problems** — input guaranteed to satisfy a precondition; behavior on out-of-promise inputs unconstrained.
- **Encoding & instance size** — complexity measured in bits of the input; reasonable encodings differ only polynomially; unary vs binary encoding is pivotal (pseudo-polynomiality).
- **Worst-case vs average-case complexity** — classical NP-hardness is worst-case; average-case hardness underlies cryptography (distributional NP, Levin completeness).

### Models of Computation & Resource Classes

- **Deterministic Turing machine (DTM)** — standard model; running time = number of steps as a function of input length n.
- **Nondeterministic Turing machine (NTM)** — branches on each step; accepts if some computation path accepts; basis of NP.
- **Polynomial time P** — languages decidable by a DTM in n^O(1); the working notion of "efficient/tractable."
- **NP** — languages decidable by an NTM in polynomial time; equivalently, problems with polynomially-verifiable certificates.
- **co-NP** — complements of NP languages; "no"-instances have short certificates (e.g., UNSAT, TAUTOLOGY).
- **NP ∩ co-NP** — problems with both yes- and no-certificates (e.g., primality before AKS, linear programming feasibility, parity games suspected).
- **PSPACE / EXPTIME / EXPSPACE** — polynomial/exponential space and time; P ⊆ NP ⊆ PSPACE ⊆ EXPTIME (strict at the ends by hierarchy theorems).
- **Polynomial hierarchy (PH)** — Σ_k^p / Π_k^p tower generalizing NP/co-NP via alternating quantifiers; PH collapses if NP = co-NP.
- **Time/space hierarchy theorems** — strictly more resources decide strictly more languages, giving provable separations (P ⊊ EXPTIME).
- **Relativization & oracle barriers** — oracles A, B with P^A = NP^A and P^B ≠ NP^B show relativizing proofs cannot settle P vs NP (Baker–Gill–Solovay).

#### The P vs NP Question

- **P vs NP** — does every efficiently verifiable problem admit an efficient solver? The central open problem; widely conjectured P ≠ NP.
- **Why it matters** — collapse would break public-key cryptography and trivialize optimization, theorem proving, learning.
- **Consequences of P = NP** — NP-complete problems solvable in polytime; PH collapses to P; search = verification.
- **Ladner's theorem** — if P ≠ NP there exist NP-intermediate problems (neither in P nor NP-complete), e.g., conjecturally graph isomorphism, factoring.

### Certificates, Verifiers & Reductions

- **Certificate / witness** — short string proving a "yes" instance (a satisfying assignment, a Hamiltonian cycle); verifiable in polynomial time.
- **Verifier definition of NP** — L ∈ NP iff a polytime verifier V and polynomial p exist with x ∈ L ⟺ ∃ c, |c| ≤ p(|x|), V(x,c)=1.
- **Many-one (Karp) polynomial-time reduction** — map instances of A to instances of B preserving yes/no with a polytime function; A ≤_p B.
- **Turing (Cook) polynomial-time reduction** — solve A using polynomially many oracle calls to B; more general, used for hardness of optimization/counting.
- **Reduction properties** — reflexive and transitive; composing two polytime reductions stays polytime, enabling chains of hardness proofs.
- **Hardness & completeness via reductions** — B is C-hard if every A ∈ C reduces to B; C-complete if also B ∈ C.
- **Gap-preserving / approximation-preserving reductions** — L-reductions, AP-reductions, PTAS-reductions transfer (in)approximability between problems.
- **Parsimonious reduction** — preserves the exact number of solutions; key for #P-completeness proofs.

### NP-Completeness Theory

- **NP-hard** — at least as hard as every NP problem under polynomial reductions; need not be in NP (may be harder).
- **NP-complete** — in NP and NP-hard; the "hardest" problems in NP; all equivalent under polytime reductions.
- **Cook–Levin theorem** — SAT (Boolean satisfiability) is NP-complete; encodes an NTM computation as a CNF formula (tableau/circuit construction).
- **Circuit-SAT** — satisfiability of a Boolean circuit; the most direct Cook–Levin target, basis for many reductions.
- **Proving NP-completeness (recipe)** — (1) show membership in NP via a certificate; (2) reduce a known NP-complete problem to it in polytime.
- **co-NP-completeness** — TAUTOLOGY, UNSAT are co-NP-complete; NP-complete ∈ co-NP would imply NP = co-NP.
- **Berman–Hartmanis (isomorphism) conjecture** — all NP-complete sets are polynomially isomorphic (open).
- **Sparse-language / Mahaney's theorem** — no sparse set is NP-complete unless P = NP, limiting where hard problems can live.

### Canonical NP-Complete & NP-Hard Problems

#### Logic & Satisfiability

- **SAT** — satisfiability of a CNF formula; the original NP-complete problem (Cook–Levin).
- **3-SAT** — SAT with exactly 3 literals per clause; NP-complete and the standard source for gadget reductions.
- **2-SAT** — clauses of size 2; in P via implication-graph SCCs (see Graphs domain) — boundary of tractability.
- **NAE-SAT / Not-All-Equal-SAT** — each clause must have mixed truth values; NP-complete, monotone variant too.
- **1-in-3 SAT (exactly-one)** — exactly one literal true per clause; NP-complete, useful for partition-style reductions.
- **MAX-SAT / MAX-3-SAT** — maximize number of satisfied clauses; NP-hard, central in approximation/PCP theory.
- **Horn-SAT / dual-Horn / XOR-SAT** — tractable SAT fragments (Horn in linear time, XOR by Gaussian elimination); Schaefer's dichotomy classifies all.
- **Schaefer's dichotomy theorem** — every Boolean CSP is either in P or NP-complete; no intermediate Boolean constraint languages.
- **QBF (TQBF)** — quantified Boolean formulas; PSPACE-complete, the canonical PSPACE problem.

#### Graph Problems

- **CLIQUE** — does a graph have a clique of size ≥ k? NP-complete; reduces from 3-SAT.
- **Independent Set** — set of pairwise non-adjacent vertices of size ≥ k; complement-of-clique, NP-complete.
- **Vertex Cover** — set of ≤ k vertices touching every edge; NP-complete; complement of an independent set.
- **Dominating Set** — ≤ k vertices whose closed neighborhoods cover V; NP-complete; W[2]-complete parameterized by k.
- **Graph k-Coloring** — proper coloring with k colors; 3-Coloring already NP-complete; 2-coloring (bipartiteness) in P.
- **Chromatic number** — minimum colors; NP-hard to compute and to approximate within n^{1−ε}.
- **Hamiltonian Path / Cycle** — spanning path/cycle visiting each vertex once; NP-complete (directed and undirected).
- **Travelling Salesman (TSP), decision** — tour of total weight ≤ k; NP-complete; general TSP NP-hard to approximate.
- **Max-Cut** — partition vertices to maximize crossing edges; NP-complete (Min-Cut is in P — contrast).
- **Feedback Vertex/Arc Set** — minimum vertices/arcs whose removal makes the graph acyclic; NP-complete, both FPT.
- **Steiner Tree (graph/metric)** — minimum tree connecting a terminal subset; NP-complete; admits constant-factor approximations.
- **Longest Path** — path of length ≥ k; NP-hard; FPT by color coding.
- **Graph Bandwidth / Cutwidth / Min Linear Arrangement** — layout problems minimizing stretch/width; NP-hard.
- **Subgraph Isomorphism** — does H appear as a subgraph of G? NP-complete (contains CLIQUE, Hamiltonicity).
- **Maximum Common Subgraph** — largest graph embeddable in two graphs; NP-hard.
- **Clique Cover / Partition into cliques** — cover edges/vertices by fewest cliques; NP-complete.
- **Graph Isomorphism** — suspected NP-intermediate; quasipolynomial algorithm (Babai); not known NP-complete (see Graphs domain).

#### Sets, Numbers & Packing

- **Subset Sum** — does a subset sum to target t? NP-complete; pseudo-polynomial DP exists.
- **Partition** — split a multiset into two equal-sum halves; NP-complete, weakly (special case of Subset Sum).
- **3-Partition** — partition 3m numbers into m triples of equal sum; strongly NP-complete (hard even in unary).
- **Knapsack (0/1), decision** — value ≥ V within weight W? NP-complete; pseudo-polynomial DP and FPTAS exist.
- **Bin Packing** — pack items into ≤ k unit bins; strongly NP-hard; APTAS / additive-O(log²) algorithms exist.
- **Multiprocessor Scheduling / Makespan (P||Cmax)** — minimize completion time on m machines; strongly NP-hard; PTAS exists.
- **Set Cover** — fewest sets whose union covers a universe; NP-complete; greedy ln n approximation, tight.
- **Exact Cover / X3C** — partition the universe using disjoint sets; NP-complete; solved in practice by Dancing Links (Algorithm X).
- **Hitting Set** — fewest elements meeting every set; dual to Set Cover, NP-complete.
- **Set Packing** — most pairwise-disjoint sets; NP-complete; generalizes 3-dimensional matching.
- **3-Dimensional Matching (3DM)** — perfect matching across three sets; NP-complete; 2D matching is in P (contrast).
- **Number Partitioning balanced / Largest-Differencing (Karmarkar–Karp)** — heuristic giving high-quality partitions in O(n log n).

#### Constraint, Geometric & Misc

- **Integer Linear Programming (ILP), feasibility** — NP-complete (LP relaxation in P); fixed dimension in P by Lenstra.
- **General CSP** — constraint satisfaction; NP-complete; CSP dichotomy theorem (Bulatov/Zhuk) gives P vs NP-complete split.
- **Graph Homomorphism (H-coloring)** — NP-complete iff H is non-bipartite (Hell–Nešetřil dichotomy).
- **Quadratic Assignment Problem (QAP)** — assign facilities to locations minimizing cost; strongly NP-hard, notoriously hard in practice.
- **Vehicle Routing (VRP)** — TSP generalization with capacities/fleets; NP-hard.
- **Job-Shop / Flow-Shop Scheduling** — sequence operations across machines; NP-hard for ≥ 3 machines.
- **Facility Location (uncapacitated/capacitated)** — open facilities and assign clients minimizing cost; NP-hard; constant-factor approximations.
- **k-Median / k-Means clustering** — NP-hard; PTAS in fixed dimension, constant-factor in general.
- **Minimum Test Cover, Art Gallery, packing/covering in the plane** — geometric NP-hard problems with PTAS or constant-factor schemes.

### Pseudo-Polynomial Algorithms & Strong NP-Hardness

- **Pseudo-polynomial algorithm** — runtime polynomial in the numeric value of inputs (not their bit-length), e.g., Subset Sum/Knapsack DP in O(nW).
- **Weak vs strong NP-hardness** — weakly NP-hard problems admit pseudo-poly algorithms; strongly NP-hard ones stay hard even with unary-encoded numbers.
- **Strong NP-hardness criterion** — NP-hard even when all numbers are bounded by a polynomial in input length (3-Partition, bin packing, TSP).
- **Consequence for approximation** — a strongly NP-hard problem has no FPTAS unless P = NP; weakly NP-hard ones may (knapsack does).
- **Numeric DP table technique** — index DP states by value/weight/profit; foundation of pseudo-poly algorithms and FPTAS scaling.

### Exact Exponential Algorithms

- **Brute force / exhaustive 2^n enumeration** — try all subsets/assignments; baseline O*(2^n) for many problems (O* hides poly factors).
- **Held–Karp TSP DP** — DP over (subset of visited cities, current city); O(2^n · n²) time, O(2^n · n) space; optimal exact TSP.
- **Bitmask subset-sum DP / SOS DP** — sum-over-subsets transform processes all 2^n masks in O(2^n · n).
- **Meet-in-the-middle** — split into two halves, enumerate each (2^{n/2}), combine by sorting/hashing; O*(2^{n/2}) for Subset Sum, knapsack.
- **Schroeppel–Shamir** — Subset Sum in O*(2^{n/2}) time but only O*(2^{n/4}) space via four-way split with priority queues.
- **Chromatic number via inclusion–exclusion (Björklund–Husfeldt–Koivisto)** — count proper k-colorings as ordered covers by independent sets via IE + fast zeta/Yates transform; chromatic number exactly in O*(2^n) time and space, the canonical exact coloring result.
- **Set partitioning / cover via inclusion–exclusion** — general O*(2^n) framework solving graph k-coloring, domatic number, max k-cut, bin packing, and the chromatic polynomial by IE over subsets.
- **Inclusion–exclusion for Hamiltonicity / partitions** — counts covers/paths via subset sums; basis of many O*(2^n) exact algorithms.
- **Branch and bound** — systematic search pruning subtrees by bounds; exponential worst case but strong in practice (TSP, ILP).
- **Branch and reduce + measure-and-conquer** — branching with reduction rules; nonstandard potential functions tighten the runtime analysis (e.g., Max Independent Set in O*(1.2²ⁿ)).
- **3-SAT in O*(1.3³ⁿ)** — Monien–Speckenmeyer / PPSZ / Schöning's random-walk give sub-2^n exponential SAT solvers.
- **Schöning's randomized k-SAT** — random restarts + local flips; O*((2(k−1)/k)^n) expected, O*(1.334^n) for 3-SAT.
- **Dynamic programming over subsets (general)** — set-partition/cover DP in O*(2^n); steiner tree (Dreyfus–Wagner) in O*(3^k) over terminals.
- **Treewidth-based exact DP** — solve via tree decomposition in time O*(c^{tw}); exponential only in the width (see Parameterized below).
- **Yates/zeta–Möbius (subset convolution)** — fast subset/Möbius transforms enable O*(2^n) covering and packing counts.
- **Strong Exponential Time Hypothesis (SETH)** — k-SAT needs essentially 2^n time as k→∞; basis for fine-grained lower bounds.
- **Exponential Time Hypothesis (ETH)** — 3-SAT requires 2^{Ω(n)}; rules out 2^{o(n)} algorithms and many subexponential schemes.

### Parameterized Complexity & FPT

- **Parameterization** — measure complexity in input size n and a secondary parameter k (solution size, treewidth, etc.).
- **Fixed-Parameter Tractable (FPT)** — solvable in f(k)·n^{O(1)}; the exponential blowup confined to the parameter.
- **XP** — solvable in n^{f(k)}; polynomial for each fixed k but exponent grows; strictly larger than FPT.
- **slice-wise / uniform vs non-uniform FPT** — whether the algorithm family is a single machine or one per k.
- **W-hierarchy (W[1] ⊆ W[2] ⊆ … ⊆ W[P])** — degrees of fixed-parameter intractability via weighted circuit satisfiability.
- **W[1]-hardness** — standard evidence a problem is not FPT (Clique, Independent Set parameterized by k are W[1]-complete).
- **W[2]-completeness** — Dominating Set, Hitting Set parameterized by solution size.
- **para-NP-hardness** — NP-hard already for some fixed k (e.g., 3-Coloring); excludes FPT (and XP) unless P = NP.
- **FPT reductions (parameterized reductions)** — preserve the parameter as g(k); transfer W-hardness.

#### Kernelization

- **Kernelization** — polytime preprocessing producing an equivalent instance (kernel) of size bounded by g(k); FPT ⟺ kernelizable.
- **Reduction rules** — local simplifications that shrink the instance while preserving the answer (degree rules, crown decompositions).
- **Vertex Cover kernel** — Buss's rule + LP/crown gives a 2k-vertex kernel; solved then in O*(1.2738^k).
- **Crown decomposition** — structural lemma yielding linear kernels for covering problems.
- **Sunflower lemma (Erdős–Rado)** — bounds set systems without large sunflowers; yields kernels for d-Hitting Set.
- **Polynomial vs linear kernels** — many problems have poly kernels; some provably do not unless coNP ⊆ NP/poly.
- **Kernel lower bounds (composition/cross-composition)** — rule out polynomial kernels (e.g., k-Path, Steiner Tree by treewidth) under complexity assumptions (coNP ⊄ NP/poly).
- **Turing kernelization** — many small kernels queried adaptively; sometimes possible when classical kernels are not.
- **Lossy kernelization / α-approximate kernels** — polytime preprocessing where a c-approximate solution on the kernel lifts to a (c·α)-approximate solution on the original; admits poly-size kernels for problems with no exact poly kernel (Connected Vertex Cover, Cycle Packing).
- **Approximate-kernel ⟺ FPT-approximation equivalence** — a problem has an α-approximate kernel iff it is FPT-α-approximable; constant-size α-kernel ⟺ polytime α-approximation (Lokshtanov–Panolan–Ramanujan–Saurabh).
- **Protrusion replacement / meta-kernelization** — replace low-treewidth "protrusions" (boundary-bounded subgraphs) by equivalent constant-size gadgets; yields linear kernels for all bidimensional/finite-state CMSO problems on sparse graph classes.
- **Sparsification / linear-kernel meta-theorems** — protrusion decomposition + replacement gives automatic O(k) kernels on planar, bounded-genus, and (apex-)minor-free graphs for a broad problem family.

#### Branching, Search Trees & Compression

- **Bounded search tree (branching)** — recursively branch on a bounded set of choices; size c^k leaves give FPT (Vertex Cover branch on an edge, 2^k).
- **Branching vectors & analysis** — solve the characteristic recurrence to bound the search-tree base; optimize branching rules.
- **Iterative compression** — maintain a size-(k+1) solution and compress to k repeatedly; powerful for FVS, OCT, edge bipartization.
- **Odd Cycle Transversal (OCT)** — FPT in O*(3^k) via iterative compression + max-flow on an auxiliary graph.
- **Important separators** — bounded number of "extremal" cuts; engine for FPT cut problems (Multiway Cut, Directed FVS, Multicut).
- **Multicut / Multiway Cut FPT** — FPT in solution size via important separators / random sampling of cuts.
- **Randomized contractions** — recursive random separations solving cut/partition problems in FPT time.
- **Color coding** — randomly color to make target subgraphs colorful, then DP; Longest Path / k-Path FPT in O*((2e)^k); derandomized by perfect hash families.
- **Chromatic / perfect-hash-family derandomization** — replace random colorings by (n,k)-perfect hash families for deterministic FPT.
- **Cut & count** — randomized DP over tree decompositions for connectivity problems in O*(c^{tw}) (e.g., Hamiltonicity, Steiner Tree).
- **Algebraic / determinant techniques** — multilinear monomial detection (Koutis–Williams) solves k-Path in O*(2^k) via group algebras.
- **Representative families / matroid methods** — kernelize DP tables (Bollobás, matroid truncation) to speed FPT algorithms.

#### Structural Width Parameters

- **Treewidth** — how tree-like a graph is; tree decomposition supports DP solving NP-hard problems in O*(c^{tw}) (see Graphs/Trees domain for construction).
- **Pathwidth** — path-shaped decomposition; ≥ treewidth; DP over linear bags.
- **Branchwidth** — close cousin of treewidth (within a factor 3/2); branch decompositions.
- **Clique-width / rank-width** — width for dense graphs; MSO₁-expressible problems FPT parameterized by clique-width.
- **Treedepth** — minimum elimination-forest height; bounds treewidth/pathwidth from above.
- **Courcelle's theorem** — every MSO₂-definable property is decidable in linear time on bounded-treewidth graphs (FPT in tw + formula size).
- **Bidimensionality** — meta-technique giving subexponential FPT (2^{O(√k)}) and PTASs on planar/minor-closed graphs.
- **Win-win / irrelevant-vertex technique** — either small width (DP) or a large structure (grid minor) gives the answer directly.
- **Above/below-guarantee parameterization** — parameterize by excess over a provable bound (e.g., MaxSAT above m/2).
- **Distance-to-triviality parameters** — vertex-deletion distance to a tractable class as parameter.

### Approximation Algorithms

- **Approximation ratio / factor** — guaranteed bound ρ on (ALG/OPT) for min or (OPT/ALG) for max problems.
- **Absolute vs relative approximation** — additive guarantee (rare, e.g., edge coloring +1) vs multiplicative ratio.
- **Asymptotic approximation ratio** — ratio holding as OPT → ∞ (bin packing AFPTAS).
- **Hardness of approximation overview** — many problems are NP-hard to approximate beyond a threshold (see Inapproximability).

#### Combinatorial / Greedy Approximations

- **Vertex Cover 2-approximation (maximal matching)** — take both endpoints of a maximal matching; ratio 2 (O(V+E)).
- **Vertex Cover 2-approx via LP rounding** — round half-integral LP optimum; matches matching bound.
- **Set Cover greedy (H_n ≈ ln n)** — repeatedly pick the set covering most uncovered elements; ln n approximation, essentially tight.
- **Metric TSP 2-approximation** — double an MST and shortcut the Euler tour; ratio 2 on metric instances.
- **Christofides–Serdyukov 1.5-approximation** — MST + min-weight perfect matching on odd-degree vertices + shortcutting; ratio 3/2 for metric TSP.
- **k-Center 2-approximation (greedy/Gonzalez)** — farthest-point heuristic; ratio 2, and 2 is best possible unless P = NP.
- **k-Means / k-Median local search & seeding (k-means++)** — O(log k) expected and constant-factor local-search guarantees.
- **Max-Cut 0.5 (random / greedy)** — random partition expects half the edges; trivial 2-approximation.
- **Knapsack greedy 2-approx** — sort by value/weight ratio, take prefix or the single best item; ratio 2.
- **Job scheduling list scheduling (Graham)** — greedy makespan ≤ (2 − 1/m)·OPT; LPT improves to 4/3 − 1/(3m).
- **Bin Packing First-Fit / First-Fit-Decreasing** — FF ≤ 1.7·OPT, FFD ≤ 11/9·OPT + const.
- **Steiner Tree 2-approx** — MST on the metric closure of terminals; better ratios (≤ 1.39) via LP/iterative randomized rounding.
- **Feedback Vertex Set 2-approx** — primal–dual / local-ratio gives factor 2 for undirected FVS.
- **Facility Location constant-factor** — primal–dual (JV ratio 3) and LP-rounding/greedy improvements (~1.488).

#### LP-Based Approximation

- **LP relaxation** — drop integrality of an ILP; solve in polytime, then round; bounds OPT.
- **Deterministic LP rounding** — threshold rounding of fractional optima (Vertex Cover ≥ 1/2 rounding, ratio 2).
- **Randomized rounding** — interpret fractional values as probabilities; gives O(log n) for Set Cover and congestion bounds.
- **Integrality gap** — worst ratio between ILP optimum and LP optimum; lower-bounds what LP rounding can achieve.
- **Primal–dual schema** — grow dual variables and build an integral primal simultaneously; Vertex Cover, Steiner forest (ratio 2), facility location.
- **Local-ratio technique** — equivalent to primal–dual; iteratively subtract weight functions; clean 2-approximations.
- **Iterative LP rounding / rounding via extreme points** — exploit sparse basic solutions (degree-bounded MST +1, generalized assignment).
- **Lagrangian relaxation & Lagrangian-multiplier-preserving algorithms** — relax hard constraints; used for k-median rounding.
- **Dependent rounding / pipage rounding** — round correlated variables preserving constraints and submodular objectives.

#### SDP-Based Approximation

- **Semidefinite programming relaxation** — relax to vectors with inner-product constraints; solvable to ε via interior-point/ellipsoid.
- **Goemans–Williamson Max-Cut (0.878)** — SDP + random hyperplane rounding; ratio 0.87856, optimal under the Unique Games Conjecture.
- **Random hyperplane rounding** — cut vectors by a random hyperplane; analysis via the arccosine/θ-π bound.
- **SDP for MAX-2-SAT / graph coloring** — vector colorings (Karger–Motwani–Sudan) color 3-colorable graphs with Õ(n^{1/4}) colors.
- **Lovász theta function** — SDP sandwiched between clique and chromatic numbers; exact for perfect graphs.

#### Approximation Schemes (PTAS / FPTAS)

- **PTAS (Polynomial-Time Approximation Scheme)** — (1±ε)-approximation in time polynomial in n for each fixed ε (exponent may depend on ε).
- **EPTAS (Efficient PTAS)** — runtime f(1/ε)·n^{O(1)}; separates the ε-dependence from n.
- **FPTAS (Fully PTAS)** — runtime polynomial in both n and 1/ε; strongest scheme, exists only for weakly NP-hard problems.
- **Knapsack FPTAS** — scale/round profits by ε·P_max/n then DP on profits; O(n²/ε) time, (1−ε)-approximation.
- **Subset Sum / Partition FPTAS** — trim the reachable-sum list to ε-spacing; polynomial in 1/ε.
- **Euclidean TSP PTAS (Arora / Mitchell)** — randomized dissection + dynamic programming; (1+ε)-approx in geometric settings, n·(log n)^{O(1/ε)}.
- **Bin Packing APTAS/AFPTAS** — round item sizes into O(1) groups + ILP in fixed dimension; OPT + o(OPT) bins.
- **Makespan / scheduling PTAS** — dual approximation + bin-packing-style rounding (Hochbaum–Shmoys).
- **Planar-graph PTAS (Baker's technique / layering)** — peel BFS layers mod k into bounded-outerplanarity slabs, DP each, shift the offset; PTAS for IS, VC, Dominating Set on planar graphs.
- **Baker generalization to bounded-genus & minor-free graphs** — layering extended via genus reduction (Eppstein) and Demaine–Hajiaghayi decompositions; PTASs for the same problems on apex-/H-minor-free classes.
- **Shifting technique (geometric covering/packing PTAS)** — partition the plane into a grid with a shifted offset, solve each cell optimally, average over O(1/ε) shifts; PTAS for disk/square cover, geometric set cover, independent set of intervals/rectangles (Hochbaum–Maass).
- **Local-search PTAS** — for geometric/planar problems, swapping bounded subsets yields (1+ε) approximations (planar/geometric exchange graphs have small separators).
- **No-FPTAS results** — strongly NP-hard problems (bin packing, makespan, TSP) admit no FPTAS unless P = NP.

#### Submodular & Coverage Optimization

- **Submodular function maximization** — diminishing-returns objective; greedy gives (1−1/e) for monotone functions under cardinality constraints.
- **(1−1/e) greedy for Max-Coverage** — choose sets greedily; tight unless P = NP.
- **Continuous greedy / multilinear extension** — (1−1/e) for monotone submodular under matroid constraints; pipage/swap rounding.
- **Submodular minimization** — polynomial-time exactly (Grötschel–Lovász–Schrijver / combinatorial algorithms) — contrast with hard maximization.

### Metaheuristics & Local Search

- **Local search / hill climbing** — iteratively move to an improving neighbor until a local optimum; framework for many heuristics.
- **Neighborhood structure & k-opt** — define moves (swap, insert, 2-opt/3-opt for TSP) shaping the search landscape.
- **2-opt / 3-opt / Lin–Kernighan** — edge-exchange local search for TSP; LK and LKH are state-of-the-art heuristics.
- **Simulated annealing** — accept worsening moves with probability e^{−Δ/T}, cooling T over time to escape local optima.
- **Tabu search** — local search with a tabu list forbidding recent moves to avoid cycling; aspiration criteria override.
- **Genetic / evolutionary algorithms** — population of solutions evolved by selection, crossover, mutation toward better fitness.
- **Memetic algorithms** — genetic algorithms hybridized with local search on offspring.
- **Ant colony optimization** — agents deposit pheromone biasing future probabilistic construction; good for routing/TSP.
- **Particle swarm optimization** — particles move through solution space guided by personal and global bests.
- **GRASP (greedy randomized adaptive search)** — randomized greedy construction + local search, multistart.
- **Variable neighborhood search (VNS)** — systematically change neighborhood structures to escape local optima.
- **Large neighborhood search / ALNS** — destroy-and-repair big chunks of a solution; strong for routing/scheduling.
- **Iterated local search** — perturb a local optimum then re-optimize; simple and effective restart scheme.
- **Beam search** — breadth-limited best-first heuristic search keeping the top-w partial solutions.
- **Cross-entropy & estimation-of-distribution methods** — sample from a learned distribution over good solutions, update it iteratively.
- **No-free-lunch theorem** — averaged over all objective functions, all black-box optimizers perform equally; guarantees are problem-specific.
- **Hyperparameter/landscape considerations** — cooling schedules, neighborhood size, restart policies materially affect quality (engineering, not guarantees).

### Inapproximability & PCP

- **Gap problems** — distinguishing OPT ≥ c from OPT ≤ s; NP-hardness of the gap implies inapproximability beyond c/s.
- **PCP theorem** — NP = PCP(log n, O(1)); every NP proof is checkable reading O(1) bits with logarithmic randomness; foundation of hardness of approximation.
- **Gap-introducing / gap-preserving reductions** — turn exact NP-hardness into a hardness gap, then propagate it.
- **MAX-3-SAT inapproximability (Håstad 7/8)** — NP-hard to approximate better than 7/8; matched by a trivial random assignment.
- **Set Cover (1−o(1))·ln n hardness** — greedy ln n is essentially optimal (Dinur–Steurer).
- **Clique / Independent Set n^{1−ε} hardness** — no n^{1−ε} approximation unless P = NP (Håstad/Zuckerman).
- **Vertex Cover hardness** — no better than 1.36 unless P = NP; no 2−ε under the Unique Games Conjecture.
- **Unique Games Conjecture (UGC)** — conjectured hardness of unique label cover; implies optimal-threshold hardness for Max-Cut (0.878), Vertex Cover (2), and more.
- **APX, APX-hard, APX-complete** — constant-factor-approximable class; APX-hard problems have no PTAS unless P = NP (Max-Cut, metric TSP, Vertex Cover).
- **No-PTAS via APX-hardness** — L-reductions from a known APX-hard problem rule out a PTAS.
- **Approximation-resistant predicates** — CSPs where no algorithm beats random assignment (e.g., 3-LIN under PCP).
- **Class hierarchy (PTAS ⊂ APX ⊂ … under P≠NP)** — FPTAS ⊆ EPTAS ⊆ PTAS ⊆ APX ⊆ NPO; separations conditional on P ≠ NP.

### Counting, Randomized & Approximate Counting

- **#P and #P-completeness** — counting solutions; #SAT, permanent (Valiant), counting perfect matchings are #P-complete.
- **Permanent vs determinant** — determinant in polytime, 0/1 permanent #P-complete despite identical formula structure.
- **Toda's theorem** — PH ⊆ P^{#P}; counting is at least as powerful as the whole polynomial hierarchy.
- **FPRAS** — fully polynomial randomized approximation scheme; (1±ε) with high probability in poly(n,1/ε) (e.g., permanent of nonnegative matrices, DNF counting).
- **Approximate counting via MCMC / Markov chains** — sample near-uniformly through rapidly mixing chains (matchings, colorings).
- **Self-reducibility ↔ sampling/counting equivalence** — Jerrum–Valiant–Vazirani: approximate counting ≈ near-uniform sampling for self-reducible problems.
- **Holant / dichotomy for counting CSPs** — complete classifications of which counting problems are tractable.

### Undecidability & the Limits of Computation (Intro)

- **Decidability vs undecidability** — some problems admit no algorithm at all, a harder barrier than intractability.
- **Halting problem** — undecidable whether an arbitrary program halts on an input (Turing's diagonalization).
- **Reductions for undecidability (mapping reductions)** — reduce the halting problem to show other problems undecidable.
- **Rice's theorem** — every nontrivial semantic property of programs is undecidable.
- **Post Correspondence Problem (PCP)** — tiling/matching problem; undecidable, a common source for further undecidability proofs.
- **Recursively enumerable vs co-RE** — the halting set is RE but not co-RE; complements separate the two.
- **Computability ↔ complexity boundary** — complexity assumes decidability and asks about resources; undecidable problems lie outside the entire time/space hierarchy.
- **Gödel incompleteness (context)** — formal limits on provability paralleling computational limits (informational cross-reference).

### Practical Solvers & Engineering (NP-hard in Practice)

- **SAT solvers (CDCL)** — conflict-driven clause learning with restarts/VSIDS; solves huge industrial SAT instances despite NP-completeness.
- **DPLL backtracking** — unit propagation + pure-literal + branching; ancestor of modern SAT solvers.
- **SMT solvers** — SAT modulo theories (arithmetic, arrays, bit-vectors) via DPLL(T).
- **ILP / MIP solvers (branch-and-cut, branch-and-price)** — cutting planes + branch and bound + column generation for exact optimization.
- **Constraint programming (CP)** — propagation + search over finite-domain constraints; strong for scheduling/assignment.
- **Lagrangian / column generation & cutting planes** — decomposition methods scaling exact methods to large instances.
- **Dancing Links (Algorithm X)** — efficient exact-cover backtracking via doubly-linked-list undo (Sudoku, tilings).
- **Portfolio & parallel solvers** — run diverse strategies concurrently, share learned clauses; robust in practice.
- **Reductions to SAT/ILP/CP** — model an NP-hard problem in a mature solver rather than writing a bespoke search.

<sub>[↑ Back to top](#table-of-contents)</sub>

---

## 📚 Further Reading & Foundational References

### Foundational Textbooks

- **Introduction to Algorithms / Cormen, Leiserson, Rivest, Stein (CLRS), 4th ed. 2022** — The canonical, encyclopedic reference covering data structures, sorting, graph algorithms, dynamic programming, NP-completeness, and rigorous correctness/complexity proofs; the default citation for nearly every classical algorithm.
- **The Algorithm Design Manual / Steven S. Skiena, 3rd ed. 2020** — Practitioner-focused, famous for its "war stories," the catalog of algorithmic problems, and pragmatic advice on which technique to reach for; excellent companion to CLRS for contest preparation.
- **Algorithm Design / Jon Kleinberg & Éva Tardos, 2005** — Teaches algorithm design paradigms (greedy, divide-and-conquer, DP, network flow, NP-hardness) through how-to-think-about-it derivations rather than a catalog; superb for flow and reduction intuition.
- **Algorithms / Robert Sedgewick & Kevin Wayne, 4th ed. 2011** — Clean, implementation-oriented treatment (Java) of fundamental data structures and algorithms with empirical analysis; the basis of the widely used Princeton/Coursera courses.
- **The Art of Computer Programming / Donald E. Knuth (Vols. 1–4A, 1968–present)** — The deepest scholarly treatment of fundamental algorithms, sorting and searching, seminumerical algorithms, and combinatorial generation; rigorous, mathematical, and foundational.
- **Concrete Mathematics / Ronald Graham, Donald Knuth, Oren Patashnik, 2nd ed. 1994** — The mathematical toolkit (sums, recurrences, generating functions, number theory, special numbers) underpinning algorithm analysis and combinatorics-heavy contest problems.
- **Introduction to the Theory of Computation / Michael Sipser, 3rd ed. 2012** — The standard text on automata, computability, and complexity (P, NP, NP-completeness, reductions); grounds the theory behind why certain problems are hard.
- **Computational Geometry: Algorithms and Applications / Mark de Berg, Otfried Cheong, Marc van Kreveld, Mark Overmars, 3rd ed. 2008** — The definitive textbook on geometric algorithms: convex hulls, plane sweep, Voronoi diagrams, Delaunay triangulation, range searching, and arrangements.

### Competitive Programming References

- **Competitive Programming / Steven Halim, Felix Halim & Suhendry Effendy (CP4, 2020)** — The contest-oriented bible mapping ICPC/IOI problem types to techniques and implementations; structured around the UVa/Kattis problem catalog.
- **Competitive Programmer's Handbook / Antti Laaksonen (free, 2018 draft)** — A concise, free PDF covering essentially the full contest syllabus from basics to advanced graph/string/math techniques; the most-recommended free starting point.
- **Guide to Competitive Programming: Learning and Improving Algorithms Through Contests / Antti Laaksonen, 2nd ed. 2020 (Springer)** — The expanded, published successor to the Handbook, with broader coverage and exercises.
- **Programming Challenges: The Programming Contest Training Manual / Steven S. Skiena & Miguel Revilla, 2003** — The classic introduction to contest programming organized around problem categories with companion online judge problems; predecessor in spirit to CP4.

### Online Resources

- **cp-algorithms.com (formerly e-maxx, English translation)** — The single best free encyclopedia of competitive-programming algorithms, with derivations and ready-to-use implementations across all areas.
- **USACO Guide (usaco.guide)** — A free, structured curriculum from Bronze to Platinum/advanced, mapping topics to practice problems with editorial-quality explanations.
- **Codeforces — EDU, blogs & editorials (codeforces.com)** — The largest competitive community; Codeforces EDU offers interactive courses (segment trees, two pointers, etc.) and the blogs/editorials are an essential repository of technique writeups.
- **AtCoder (atcoder.jp)** — High-quality contests (Beginner/Regular/Grand) with clean problems and editorials; the DP Contest and Educational rounds are standard learning material.
- **TopCoder Algorithm Tutorials (topcoder.com)** — A long-standing library of in-depth tutorials (DP, geometry, max flow, segment trees) that shaped a generation of competitors.
- **GeeksforGeeks (geeksforgeeks.org)** — Broad, searchable catalog of algorithm and data-structure articles with example code; useful for quick reference and many implementation variants.
- **Project Euler (projecteuler.net)** — A graded archive of mathematical/computational problems that build number-theory, combinatorics, and optimization intuition.
- **OEIS — The On-Line Encyclopedia of Integer Sequences (oeis.org)** — Indispensable for identifying integer sequences, recurrences, and closed forms when reverse-engineering combinatorial patterns.

### Seminal Papers by Area

**Shortest Paths & Graphs**
- **Dijkstra (1959), "A Note on Two Problems in Connexion with Graphs"** — Introduces Dijkstra's single-source shortest-path algorithm for nonnegative weights (and an MST algorithm).
- **Bellman (1958) & Ford / Moore** — The Bellman-Ford algorithm for shortest paths with negative edge weights and negative-cycle detection.
- **Tarjan (1972), "Depth-First Search and Linear Graph Algorithms"** — DFS-based linear-time strongly connected components (Tarjan's SCC), foundational to graph decomposition.

**Trees, DSU & Dynamic Structures**
- **Tarjan (1979), "Applications of Path Compression on Balanced Trees" / "Efficiency of a Good but Not Linear Set Union Algorithm" (1975)** — The inverse-Ackermann (α) amortized analysis of union-find with path compression and union by rank.
- **Tarjan (1979), offline LCA via union-find; Harel & Tarjan (1984), "Fast Algorithms for Finding Nearest Common Ancestors"** — Foundational lowest-common-ancestor algorithms, including constant-time LCA after linear preprocessing.
- **Sleator & Tarjan (1983), "A Data Structure for Dynamic Trees" (link-cut trees)** — Logarithmic-amortized dynamic tree connectivity/path queries; the basis of link-cut trees.

**Maximum Flow & Matching**
- **Edmonds & Karp (1972), "Theoretical Improvements in Algorithmic Efficiency for Network Flow Problems"** — BFS-based augmenting paths giving the first strongly polynomial max-flow bound.
- **Dinitz/Dinic (1970), blocking-flow algorithm** — Layered-graph blocking flows yielding O(V²E) max flow (and O(E√V) on unit-capacity/bipartite graphs).
- **Goldberg & Tarjan (1988), "A New Approach to the Maximum-Flow Problem" (push-relabel)** — The push-relabel/preflow paradigm, among the fastest practical max-flow methods.
- **Hopcroft & Karp (1973), "An n^{5/2} Algorithm for Maximum Matchings in Bipartite Graphs"** — O(E√V) maximum bipartite matching via simultaneous shortest augmenting paths.
- **Edmonds (1965), "Paths, Trees, and Flowers" (Blossom algorithm)** — The first polynomial-time maximum matching in general (non-bipartite) graphs; introduced the notion of "good" (polynomial) algorithms.
- **Kuhn (1955) & Munkres (1957), the Hungarian algorithm** — Polynomial-time assignment problem / minimum-cost perfect matching in bipartite graphs (Kuhn-Munkres).

**Strings**
- **Knuth, Morris & Pratt (1977), "Fast Pattern Matching in Strings" (KMP)** — Linear-time single-pattern matching via the failure (prefix) function.
- **Aho & Corasick (1975), "Efficient String Matching: An Aid to Bibliographic Search"** — Linear-time multi-pattern matching using an automaton over a trie with failure links.
- **Ukkonen (1995), "On-line Construction of Suffix Trees" (Algorithmica)** — The standard linear-time, online suffix-tree construction algorithm.
- **Manber & Myers (1990/1993), "Suffix Arrays: A New Method for On-Line String Searches" (SIAM J. Comput.)** — Introduces suffix arrays as a space-efficient alternative to suffix trees, with O(n log n) construction.
- **Nong, Zhang & Chan (2009), "Linear Suffix Array Construction by Almost Pure Induced-Sorting" (SA-IS)** — Simple, fast linear-time suffix array construction by induced sorting; the practical standard.

**Number Theory & Arithmetic**
- **Cooley & Tukey (1965), "An Algorithm for the Machine Calculation of Complex Fourier Series" (FFT)** — The fast Fourier transform, enabling O(n log n) polynomial/integer convolution.
- **Karatsuba & Ofman (1962), fast multiplication** — Sub-quadratic O(n^{log₂3}) integer/polynomial multiplication via divide-and-conquer.
- **Strassen (1969), "Gaussian Elimination Is Not Optimal"** — Sub-cubic O(n^{log₂7}) matrix multiplication, opening the field of fast linear algebra.
- **Miller (1976) & Rabin (1980), the Miller-Rabin primality test** — Probabilistic (and conditionally deterministic) primality testing widely used in contests.
- **Pollard (1975), "A Monte Carlo Method for Factorization" (Pollard's rho)** — Efficient randomized integer factorization for moderate-size composites.

**Optimization, Geometry & Theory**
- **Karger (1993), randomized min-cut; Karger & Stein (1996)** — Randomized contraction algorithm for global minimum cut, with near-quadratic improvement.
- **Cook (1971), "The Complexity of Theorem-Proving Procedures" & Levin (1973) (Cook-Levin theorem)** — NP-completeness of SAT, founding the theory of NP-completeness and reductions.
- **Christofides (1976), "Worst-Case Analysis of a New Heuristic for the Travelling Salesman Problem"** — The 3/2-approximation for metric TSP combining MST, matching, and Eulerian tours.
- **Bentley & Ottmann (1979), "Algorithms for Reporting and Counting Geometric Intersections"** — The sweep-line algorithm for line-segment intersection in O((n+k) log n).
- **Welzl (1991), "Smallest Enclosing Disks (Balls and Ellipsoids)"** — Expected linear-time minimum enclosing circle/ball via randomized incremental construction.
- **Sprague (1935) & Grundy (1939), Sprague-Grundy theorem** — Nim-values/Grundy numbers reducing impartial combinatorial games to Nim; the foundation of game-theory contest problems.

<sub>[↑ Back to top](#table-of-contents)</sub>

---

<p align="center"><sub>Compiled as an exhaustive, language-agnostic algorithms taxonomy spanning the standard competitive-programming canon (CLRS · Skiena · Sedgewick · Halim · Laaksonen · cp-algorithms) up to ICPC level. Contributions and corrections welcome.</sub></p>
