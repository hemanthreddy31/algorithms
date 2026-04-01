# Graphs Interview Track

Graph interviews evaluate modeling, traversal, shortest-path reasoning, DAG ordering, and connectivity design.

## Pattern Recognition Cues
- Unweighted shortest steps -> BFS.
- Dependency order -> topological sort.
- Weighted non-negative shortest path -> Dijkstra.
- Negative edges with bounded relaxations -> Bellman-Ford.
- All-pairs shortest path on dense graph -> Floyd-Warshall.
- Connectivity under edge additions -> Union-Find / MST.

## Common Interview Traps
- Using DFS when shortest path in unweighted graph is asked.
- Not guarding against stale heap states in Dijkstra.
- Missing cycle detection for topological ordering.
- For MST, sorting by wrong key or wrong union criteria.

## Solved Problem Ladder
| # | Problem | Difficulty |
|---|---|---|
| 1 | [Number of Islands](problems/number-of-islands/README.md) | Medium |
| 2 | [Course Schedule](problems/course-schedule/README.md) | Medium |
| 3 | [Network Delay Time (Dijkstra)](problems/network-delay-time/README.md) | Medium |
| 4 | [Cheapest Flights Within K Stops (Bellman-Ford Style)](problems/cheapest-flights-k-stops/README.md) | Medium |
| 5 | [Find the City With the Smallest Number of Neighbors at a Threshold Distance (Floyd-Warshall)](problems/find-city-floyd-warshall/README.md) | Medium |
| 6 | [Min Cost to Connect All Points (MST)](problems/min-cost-connect-points-mst/README.md) | Medium |
| 7 | [Redundant Connection (Union-Find)](problems/redundant-connection-union-find/README.md) | Medium |

## Additional High-Value Variations
- Pacific Atlantic Water Flow
- Word Ladder
- Alien Dictionary
- Path With Minimum Effort
- Critical Connections in a Network
- Accounts Merge
- Swim in Rising Water
- Reconstruct Itinerary

## How to Use This Topic
- Solve the ladder in order from Easy to Hard.
- For each problem, first speak brute force, then optimal, then code.
- Practice writing complexity and edge cases before coding.
