# 🧩 Algorithms — Java Solutions

> A growing collection of algorithm problems solved in Java, with clean explanations,
> complexity analysis, and ready-to-run code. Add new problems by following the
> [template](#-template-copy-for-new-problems) at the bottom.

---

## 📑 Table of Contents

| # | Problem | Topic | Difficulty |
| :-: | :-- | :-- | :-: |
| 1 | [Count Zeros, Positives & Negatives](#1-count-zeros-positives--negatives) | Arrays / Counting | 🟢 Easy |

> _Boilerplate:_ [Fast Input Template (FastScanner)](#-fast-input-template-fastscanner)

---

<br>

## 🛠 Fast Input Template (FastScanner)

A reusable fast-input class used across the problems below. Faster than `Scanner`
for large inputs.

```java
import java.io.*;
import java.util.StringTokenizer;

static class FastScanner {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st;

    String next() throws IOException {
        while (st == null || !st.hasMoreElements()) {
            st = new StringTokenizer(br.readLine());
        }
        return st.nextToken();
    }

    int nextInt() throws IOException { return Integer.parseInt(next()); }
    long nextLong() throws IOException { return Long.parseLong(next()); }
    double nextDouble() throws IOException { return Double.parseDouble(next()); }
}
```

<sub>[↑ Back to top](#-table-of-contents)</sub>

---

<br>

## 1. Count Zeros, Positives & Negatives

**Topic:** Arrays / Counting &nbsp;•&nbsp; **Difficulty:** 🟢 Easy

### 📝 Problem
Given `t` integers, count how many are **zeros**, **positives**, and **negatives**,
and print the three counts separated by spaces.

### 💡 Approach
Single pass over the input. For each number, increment the matching counter:
- `n == 0` → zeros
- `n > 0`  → positives
- `n < 0`  → negatives

### ⏱ Complexity
| Time | Space |
| :--: | :--: |
| `O(t)` | `O(1)` |

### 💻 Solution
```java
import java.io.*;
import java.util.StringTokenizer;

public class Main {
    static class FastScanner {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        String next() throws IOException {
            while (st == null || !st.hasMoreElements()) {
                st = new StringTokenizer(br.readLine());
            }
            return st.nextToken();
        }

        int nextInt() throws IOException {
            return Integer.parseInt(next());
        }
    }

    public static void main(String[] args) throws Exception {
        FastScanner fs = new FastScanner();

        int t = fs.nextInt();
        int zeros = 0, positives = 0, negatives = 0;

        while (t-- > 0) {
            int n = fs.nextInt();

            if (n == 0) zeros++;
            else if (n > 0) positives++;
            else negatives++;
        }

        System.out.println(zeros + " " + positives + " " + negatives);
    }
}
```

<sub>[↑ Back to top](#-table-of-contents)</sub>

---

<br>

## 📋 Template (copy for new problems)

```markdown
## N. Problem Title

**Topic:** <topic> &nbsp;•&nbsp; **Difficulty:** 🟢 Easy / 🟡 Medium / 🔴 Hard

### 📝 Problem
<problem statement>

### 💡 Approach
<key idea / steps>

### ⏱ Complexity
| Time | Space |
| :--: | :--: |
| `O(?)` | `O(?)` |

### 💻 Solution
​```java
// code here
​```

<sub>[↑ Back to top](#-table-of-contents)</sub>

---
```

> **When adding a problem:** add a row to the [Table of Contents](#-table-of-contents),
> then paste the filled template above this line.