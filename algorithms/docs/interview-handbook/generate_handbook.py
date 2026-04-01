from __future__ import annotations

import textwrap
from pathlib import Path

from problems_data import PROBLEMS_BY_TOPIC

ROOT = Path(__file__).resolve().parent


SECTION_ORDER = [
    "Problem Title",
    "Difficulty Level",
    "Problem Statement",
    "Input Format",
    "Output Format",
    "Constraints",
    "Example Inputs and Outputs",
    "Edge Cases",
    "Brute Force Approach Explanation",
    "Optimal Approach Explanation",
    "Step-by-Step Logic",
    "Time Complexity Analysis",
    "Space Complexity Analysis",
    "Clean and Production-Quality Java Code",
    "Dry Run Example",
    "Common Interview Follow-Up Questions",
    "Alternative Solutions if available",
    "Real Interview Context (why companies ask this problem)",
]


def dedent_block(value: str) -> str:
    return textwrap.dedent(value).strip("\n")


TOPIC_INTROS = {
    "foundations": {
        "title": "Foundations",
        "overview": "Complexity reasoning, bit tricks, recursion mindset, backtracking tree design, and interview math are the base layer for every round.",
        "pattern_signals": [
            "If constraints are large, start by writing target complexity before coding.",
            "If operation rules mention XOR/parity/toggle, think bit manipulation.",
            "If question says generate all combinations/permutations, expect recursion/backtracking.",
            "If repeated multiplications, modular arithmetic, or prime checks are involved, use mathematical optimization.",
        ],
        "traps": [
            "Not translating constraints into complexity limits.",
            "Integer overflow in multiplication/exponentiation.",
            "Missing base cases in recursion.",
            "Forgetting to undo state in backtracking.",
        ],
        "extra_variations": [
            "Missing Number",
            "Count Set Bits",
            "Pow Mod M",
            "Permutation Sequence",
            "N-Queens",
            "Greatest Common Divisor of Strings",
            "Fibonacci with Matrix Exponentiation",
            "Happy Number",
        ],
    },
    "arrays-strings": {
        "title": "Arrays and Strings",
        "overview": "Most interview rounds begin here: linear scans, windows, prefix structures, two pointers, and string matching.",
        "pattern_signals": [
            "Contiguous segment language usually means subarray/window/prefix-sum.",
            "Sorted array + pair/triplet target often means two pointers.",
            "Next greater/smaller element hints monotonic stack.",
            "Pattern search in text hints KMP or Rabin-Karp.",
        ],
        "traps": [
            "Incorrect window shrink condition.",
            "Off-by-one in prefix arrays.",
            "Not deduplicating tuples in 3Sum-like problems.",
            "Wrong failure table transitions in KMP.",
        ],
        "extra_variations": [
            "Trapping Rain Water",
            "Product of Array Except Self",
            "Minimum Window Substring",
            "Longest Palindromic Substring",
            "Rabin-Karp Multiple Pattern Search",
            "Longest Repeating Character Replacement",
            "Subarray Product Less Than K",
            "Find All Anagrams in a String",
        ],
    },
    "searching-sorting": {
        "title": "Searching and Sorting",
        "overview": "Binary search templates, sorting-driven transformations, and order statistics are common for correctness + performance checks.",
        "pattern_signals": [
            "Monotonic answer space implies binary search on answer.",
            "Interval overlap/merging implies sort by start/end first.",
            "Need kth element without full sort implies heap or quickselect.",
            "Need first/last occurrence means lower/upper-bound style binary search.",
        ],
        "traps": [
            "Infinite loop due to wrong mid movement.",
            "Unstable custom comparator causing inconsistent order.",
            "Forgetting randomized pivot in quickselect worst-case.",
            "Mixing half-open and closed interval boundaries.",
        ],
        "extra_variations": [
            "Search a 2D Matrix",
            "Median of Two Sorted Arrays",
            "Sort Colors",
            "Largest Number",
            "Find Peak Element",
            "Capacity To Ship Packages Within D Days",
            "Koko Eating Bananas",
            "Divide Chocolate",
        ],
    },
    "hashing": {
        "title": "Hashing",
        "overview": "Hash-based lookups convert many O(n^2) scans into linear passes and are a staple in screening rounds.",
        "pattern_signals": [
            "Need fast membership check -> HashSet.",
            "Need counts/frequencies -> HashMap.",
            "Canonical representation grouping -> hash by normalized key.",
            "Prefix sum + target relation often needs map of seen prefixes.",
        ],
        "traps": [
            "Assuming hash iteration order is stable.",
            "Ignoring collisions/load-factor tradeoffs in design rounds.",
            "Using mutable objects as map keys incorrectly.",
            "Overwriting earliest index needed for longest-range answers.",
        ],
        "extra_variations": [
            "Happy Number",
            "Contains Duplicate II",
            "Longest Substring with At Most K Distinct Characters",
            "First Unique Character in a String",
            "Design HashMap",
            "Randomized Set",
            "Encode and Decode TinyURL",
            "Find Duplicate Files in System",
        ],
    },
    "linked-lists": {
        "title": "Linked Lists",
        "overview": "Pointer discipline problems test precision under pressure: traversal invariants, in-place mutation, and node ownership.",
        "pattern_signals": [
            "Need kth from end -> fast/slow pointers.",
            "Cycle-related language -> Floyd tortoise-hare.",
            "In-place reorder often means split + reverse + merge.",
            "Cache design usually combines hashmap + doubly linked list.",
        ],
        "traps": [
            "Losing next pointer during rewiring.",
            "Forgetting dummy node for head-edge cases.",
            "Not handling 1-node or 2-node lists.",
            "Memory leaks in custom cache node detach/attach.",
        ],
        "extra_variations": [
            "Copy List with Random Pointer",
            "Reverse Nodes in k-Group",
            "Intersection of Two Linked Lists",
            "Palindrome Linked List",
            "Flatten a Multilevel Doubly Linked List",
            "Sort List",
            "Swap Nodes in Pairs",
            "Rotate List",
        ],
    },
    "stacks-queues": {
        "title": "Stacks and Queues",
        "overview": "These structures appear in parsing, monotonic optimization, streaming windows, and state rollback workflows.",
        "pattern_signals": [
            "Nearest greater/smaller asks for monotonic stack.",
            "Expression parsing/evaluation typically uses stack.",
            "Sliding window min/max often uses deque.",
            "Need O(1) min with stack operations implies augmented stack.",
        ],
        "traps": [
            "Wrong monotonic direction (increasing vs decreasing stack).",
            "Forgetting to evict out-of-window indices in deque.",
            "Mismatched operator operand order in RPN.",
            "Not handling empty-stack operations in design problems.",
        ],
        "extra_variations": [
            "Largest Rectangle in Histogram",
            "Basic Calculator II",
            "Implement Queue using Stacks",
            "Implement Stack using Queues",
            "Asteroid Collision",
            "Decode String",
            "Next Greater Element II",
            "Online Stock Span",
        ],
    },
    "trees": {
        "title": "Trees",
        "overview": "Tree rounds test recursion fluency, traversal control, and local-to-global state transitions.",
        "pattern_signals": [
            "Path property from root to leaf -> DFS recursion.",
            "Level-wise output -> BFS queue.",
            "Ancestor relation -> LCA patterns.",
            "Compute best value with child dependencies -> tree DP.",
        ],
        "traps": [
            "Returning wrong null/base values.",
            "Confusing node count vs edge count (diameter-like).",
            "Global state not reset between test cases.",
            "For BST checks, using local parent comparison only instead of range bounds.",
        ],
        "extra_variations": [
            "Serialize and Deserialize Binary Tree",
            "Construct Binary Tree from Preorder and Inorder Traversal",
            "Kth Smallest Element in a BST",
            "Binary Tree Right Side View",
            "Path Sum III",
            "Flatten Binary Tree to Linked List",
            "Count Complete Tree Nodes",
            "Recover Binary Search Tree",
        ],
    },
    "heaps": {
        "title": "Heaps",
        "overview": "Priority queues are core for top-k, scheduling, streaming medians, and multi-source merge workflows.",
        "pattern_signals": [
            "Need repeated smallest/largest extraction -> heap.",
            "Top k among large n -> min-heap of size k.",
            "Online median -> two heaps.",
            "Merge many sorted streams -> heap of heads.",
        ],
        "traps": [
            "Using max-heap when min-heap is needed (or vice versa).",
            "Comparator overflow via subtraction.",
            "Forgetting heap rebalance in two-heap median.",
            "Pushing all elements when bounded heap is enough.",
        ],
        "extra_variations": [
            "IPO",
            "Reorganize String",
            "K Closest Points to Origin",
            "Ugly Number II",
            "Smallest Range Covering Elements from K Lists",
            "Find K Pairs with Smallest Sums",
            "The Skyline Problem",
            "Furthest Building You Can Reach",
        ],
    },
    "trie": {
        "title": "Trie",
        "overview": "Trie questions combine prefix retrieval with constrained DFS and dictionary compression.",
        "pattern_signals": [
            "Prefix-based lookup/auto-complete -> trie.",
            "Wildcard dictionary matching -> trie DFS.",
            "Bulk word matching in board/text -> trie + backtracking.",
            "Bitwise maximize/minimize XOR -> bit trie.",
        ],
        "traps": [
            "High memory from full 26-array nodes when sparse.",
            "Not clearing visited state in board DFS.",
            "Returning duplicates in word-search outputs.",
            "Failing to terminate paths when prefix mismatch occurs.",
        ],
        "extra_variations": [
            "Map Sum Pairs",
            "Longest Word in Dictionary",
            "Stream of Characters",
            "Palindrome Pairs",
            "Prefix and Suffix Search",
            "Word Squares",
            "Implement Magic Dictionary",
            "Search Suggestions System",
        ],
    },
    "graphs": {
        "title": "Graphs",
        "overview": "Graph interviews evaluate modeling, traversal, shortest-path reasoning, DAG ordering, and connectivity design.",
        "pattern_signals": [
            "Unweighted shortest steps -> BFS.",
            "Dependency order -> topological sort.",
            "Weighted non-negative shortest path -> Dijkstra.",
            "Negative edges with bounded relaxations -> Bellman-Ford.",
            "All-pairs shortest path on dense graph -> Floyd-Warshall.",
            "Connectivity under edge additions -> Union-Find / MST.",
        ],
        "traps": [
            "Using DFS when shortest path in unweighted graph is asked.",
            "Not guarding against stale heap states in Dijkstra.",
            "Missing cycle detection for topological ordering.",
            "For MST, sorting by wrong key or wrong union criteria.",
        ],
        "extra_variations": [
            "Pacific Atlantic Water Flow",
            "Word Ladder",
            "Alien Dictionary",
            "Path With Minimum Effort",
            "Critical Connections in a Network",
            "Accounts Merge",
            "Swim in Rising Water",
            "Reconstruct Itinerary",
        ],
    },
    "dynamic-programming": {
        "title": "Dynamic Programming",
        "overview": "DP rounds test state design, transition correctness, and optimization from exponential recursion to polynomial runtime.",
        "pattern_signals": [
            "Overlapping subproblems and optimal substructure -> DP.",
            "Choice at each index/item -> 1D or knapsack-style DP.",
            "Grid movement constraints -> 2D DP.",
            "Digit constraints across ranges -> digit DP.",
            "Tree include/exclude choices -> tree DP.",
        ],
        "traps": [
            "State misses one dimension needed for correctness.",
            "Wrong transition order causing reuse bugs.",
            "Using recursion without memoization and timing out.",
            "Confusing subsequence vs substring transitions.",
        ],
        "extra_variations": [
            "Edit Distance",
            "Distinct Subsequences",
            "Longest Common Subsequence",
            "Regular Expression Matching",
            "Best Time to Buy and Sell Stock with Cooldown",
            "Delete and Earn",
            "Palindrome Partitioning II",
            "Dungeon Game",
        ],
    },
    "advanced-topics": {
        "title": "Advanced Topics",
        "overview": "These topics appear in senior rounds where data structure design, offline preprocessing, and optimization rigor are evaluated.",
        "pattern_signals": [
            "Frequent range queries + point/range updates -> segment/fenwick trees.",
            "Static idempotent range queries -> sparse table.",
            "Dynamic connectivity merging -> DSU.",
            "Capacity-constrained path allocation -> max flow.",
            "Planar point-set boundary/area problems -> computational geometry.",
        ],
        "traps": [
            "Lazy propagation bugs due to missed push-down.",
            "Fenwick off-by-one from zero-based indexing.",
            "Sparse table misuse on non-idempotent operations.",
            "Max-flow residual graph not updated both directions.",
        ],
        "extra_variations": [
            "Range Update and Range Query with Lazy Segment Tree",
            "Offline Queries with Mo's Algorithm",
            "DSU Rollback",
            "Minimum Cut",
            "Half-Plane Intersection",
            "Line Sweep for Rectangle Union Area",
            "Persistent Segment Tree",
            "Heavy-Light Decomposition",
        ],
    },
}


def render_topic_readme(topic_key: str, topic: dict, problems: list[dict]) -> str:
    lines = []
    lines.append(f"# {topic['title']} Interview Track")
    lines.append("")
    lines.append(topic["overview"])
    lines.append("")
    lines.append("## Pattern Recognition Cues")
    for cue in topic["pattern_signals"]:
        lines.append(f"- {cue}")
    lines.append("")
    lines.append("## Common Interview Traps")
    for trap in topic["traps"]:
        lines.append(f"- {trap}")
    lines.append("")
    lines.append("## Solved Problem Ladder")
    lines.append("| # | Problem | Difficulty |")
    lines.append("|---|---|---|")
    for i, problem in enumerate(problems, 1):
        path = f"problems/{problem['slug']}/README.md"
        lines.append(f"| {i} | [{problem['title']}]({path}) | {problem['difficulty']} |")
    lines.append("")
    lines.append("## Additional High-Value Variations")
    for item in topic["extra_variations"]:
        lines.append(f"- {item}")
    lines.append("")
    lines.append("## How to Use This Topic")
    lines.append("- Solve the ladder in order from Easy to Hard.")
    lines.append("- For each problem, first speak brute force, then optimal, then code.")
    lines.append("- Practice writing complexity and edge cases before coding.")
    return "\n".join(lines).strip() + "\n"


def render_problem_readme(problem: dict) -> str:
    examples_md = []
    for idx, ex in enumerate(problem["examples"], 1):
        examples_md.append(f"Example {idx}")
        examples_md.append("```text")
        examples_md.append(f"Input: {ex['input']}")
        examples_md.append(f"Output: {ex['output']}")
        if ex.get("explanation"):
            examples_md.append(f"Explanation: {ex['explanation']}")
        examples_md.append("```")
    steps = "\n".join(f"{i}. {step}" for i, step in enumerate(problem["steps"], 1))

    md = f"""# {problem['title']}

## 1. Problem Title
{problem['title']}

## 2. Difficulty Level (Easy / Medium / Hard)
{problem['difficulty']}

## 3. Problem Statement
{problem['statement']}

## 4. Input Format
{problem['input_format']}

## 5. Output Format
{problem['output_format']}

## 6. Constraints (very important for interviews)
"""
    md += "\n".join(f"- {c}" for c in problem["constraints"]) + "\n\n"
    md += "## 7. Example Inputs and Outputs\n"
    md += "\n".join(examples_md) + "\n\n"
    md += "## 8. Edge Cases\n"
    md += "\n".join(f"- {e}" for e in problem["edge_cases"]) + "\n\n"
    md += f"## 9. Brute Force Approach Explanation\n{problem['brute_force']}\n\n"
    md += f"## 10. Optimal Approach Explanation\n{problem['optimal']}\n\n"
    md += f"## 11. Step-by-Step Logic\n{steps}\n\n"
    md += f"## 12. Time Complexity Analysis\n{problem['time_complexity']}\n\n"
    md += f"## 13. Space Complexity Analysis\n{problem['space_complexity']}\n\n"
    md += "## 14. Clean and Production-Quality Java Code\n"
    md += "```java\n" + dedent_block(problem["java_code"]) + "\n```\n\n"
    md += f"## 15. Dry Run Example\n{problem['dry_run']}\n\n"
    md += "## 16. Common Interview Follow-Up Questions\n"
    md += "\n".join(f"- {q}" for q in problem["follow_ups"]) + "\n\n"
    md += "## 17. Alternative Solutions if available\n"
    md += "\n".join(f"- {a}" for a in problem["alternatives"]) + "\n\n"
    md += f"## 18. Real Interview Context (why companies ask this problem)\n{problem['interview_context']}\n"
    return md


def write_all() -> None:
    handbook_root = ROOT
    handbook_root.mkdir(parents=True, exist_ok=True)

    root_lines = [
        "# Ultimate DSA Interview Handbook (Java)",
        "",
        "This handbook is structured topic-by-topic for FAANG-level interview preparation.",
        "Each topic has a curriculum README and per-problem READMEs with full interview treatment.",
        "",
        "## Source-Informed Curation",
        "- LeetCode Top Interview 150: https://leetcode.com/studyplan/top-interview-150/",
        "- HackerRank Interview Preparation Kit: https://www.hackerrank.com/interview/interview-preparation-kit",
        "- GeeksforGeeks SDE Sheet: https://www.geeksforgeeks.org/sde-sheet-a-complete-guide-for-sde-preparation/",
        "",
        "## Topics",
    ]

    ordered_topics = [
        "foundations",
        "arrays-strings",
        "searching-sorting",
        "hashing",
        "linked-lists",
        "stacks-queues",
        "trees",
        "heaps",
        "trie",
        "graphs",
        "dynamic-programming",
        "advanced-topics",
    ]

    for topic_key in ordered_topics:
        topic_meta = TOPIC_INTROS[topic_key]
        root_lines.append(f"- [{topic_meta['title']}](./{topic_key}/README.md)")

        topic_dir = handbook_root / topic_key
        topic_dir.mkdir(parents=True, exist_ok=True)
        problems_dir = topic_dir / "problems"
        problems_dir.mkdir(parents=True, exist_ok=True)

        topic_problems = PROBLEMS_BY_TOPIC.get(topic_key, [])
        topic_readme = render_topic_readme(topic_key, topic_meta, topic_problems)
        (topic_dir / "README.md").write_text(topic_readme, encoding="utf-8")

        for problem in topic_problems:
            pdir = problems_dir / problem["slug"]
            pdir.mkdir(parents=True, exist_ok=True)
            (pdir / "README.md").write_text(render_problem_readme(problem), encoding="utf-8")

    (handbook_root / "README.md").write_text("\n".join(root_lines).strip() + "\n", encoding="utf-8")


if __name__ == "__main__":
    write_all()
