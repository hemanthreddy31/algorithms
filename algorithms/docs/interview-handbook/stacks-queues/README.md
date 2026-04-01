# Stacks and Queues Interview Track

These structures appear in parsing, monotonic optimization, streaming windows, and state rollback workflows.

## Pattern Recognition Cues
- Nearest greater/smaller asks for monotonic stack.
- Expression parsing/evaluation typically uses stack.
- Sliding window min/max often uses deque.
- Need O(1) min with stack operations implies augmented stack.

## Common Interview Traps
- Wrong monotonic direction (increasing vs decreasing stack).
- Forgetting to evict out-of-window indices in deque.
- Mismatched operator operand order in RPN.
- Not handling empty-stack operations in design problems.

## Solved Problem Ladder
| # | Problem | Difficulty |
|---|---|---|
| 1 | [Valid Parentheses](problems/valid-parentheses/README.md) | Easy |
| 2 | [Min Stack](problems/min-stack/README.md) | Medium |
| 3 | [Evaluate Reverse Polish Notation](problems/evaluate-reverse-polish-notation/README.md) | Medium |
| 4 | [Daily Temperatures](problems/daily-temperatures/README.md) | Medium |
| 5 | [Sliding Window Maximum](problems/sliding-window-maximum/README.md) | Hard |

## Additional High-Value Variations
- Largest Rectangle in Histogram
- Basic Calculator II
- Implement Queue using Stacks
- Implement Stack using Queues
- Asteroid Collision
- Decode String
- Next Greater Element II
- Online Stock Span

## How to Use This Topic
- Solve the ladder in order from Easy to Hard.
- For each problem, first speak brute force, then optimal, then code.
- Practice writing complexity and edge cases before coding.
