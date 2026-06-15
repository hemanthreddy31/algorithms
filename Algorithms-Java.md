# 🧩 Algorithms — Java Solutions

> A growing collection of algorithm problems solved in Java, with clean explanations,
> complexity analysis, and ready-to-run code. Add new problems by following the
> [template](#-template-copy-for-new-problems) at the bottom.

---

## 📑 Table of Contents

| # | Problem | Topic | Difficulty |
| :-: | :-- | :-- | :-: |
| 1 | [Count Zeros, Positives & Negatives](#1-count-zeros-positives--negatives) | Arrays / Counting | 🟢 Easy |
| 2 | [Reverse an Array](#2-reverse-an-array) | Arrays | 🟢 Easy |
| 3 | [Sum of Odd & Even Numbers](#3-sum-of-odd--even-numbers) | Arrays / Bit Manipulation | 🟢 Easy |

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

## 2. Reverse an Array

**Topic:** Arrays &nbsp;•&nbsp; **Difficulty:** 🟢 Easy

### 📝 Problem
Read `n` followed by `n` integers and print them in **reverse order**, space-separated.

### 💡 Approach
Store the values in an array, then iterate from the last index down to the first,
appending each to a `StringBuilder` for fast output.

> ⚠️ **Compile fix (your error):** `FastScanner.next()` / `nextInt()` declare
> `throws IOException`, so any method that calls them must handle it. The simplest
> fix is to add `throws IOException` to `main` (done below). Common alternatives:
> wrap calls in `try/catch`, or change `main` to `throws Exception`.

### ⏱ Complexity
| Time | Space |
| :--: | :--: |
| `O(n)` | `O(n)` |

### 💻 Solution
```java
import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {   // <-- fixes the error
        FastScanner fs = new FastScanner();
        int n = fs.nextInt();
        int[] ar = new int[n];
        for (int i = 0; i < n; i++) {
            ar[i] = fs.nextInt();
        }
        StringBuilder sb = new StringBuilder();
        for (int i = n - 1; i >= 0; i--) {
            sb.append(ar[i]).append(' ');
        }
        System.out.println(sb.toString().trim());
    }

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
    }
}
```

<sub>[↑ Back to top](#-table-of-contents)</sub>

---

<br>

## 3. Sum of Odd & Even Numbers

**Topic:** Arrays / Bit Manipulation &nbsp;•&nbsp; **Difficulty:** 🟢 Easy

### 📝 Problem
Read `n` followed by `n` integers on the next line. Print the **sum of odd
numbers** and the **sum of even numbers**, in that order, separated by a space.

### 💡 Approach
Single pass over the values. Use the bitwise parity check `(k & 1) == 0` to test
even — it is equivalent to `k % 2 == 0` but slightly faster and avoids issues with
negative `%`.

### ⏱ Complexity
| Time | Space |
| :--: | :--: |
| `O(n)` | `O(1)` |

### 💻 Solution
```java
import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());

        int evenSum = 0;
        int oddSum = 0;

        StringTokenizer st = new StringTokenizer(br.readLine());

        for (int i = 0; i < n; i++) {
            int k = Integer.parseInt(st.nextToken());

            if ((k & 1) == 0) {  // faster parity check
                evenSum += k;
            } else {
                oddSum += k;
            }
        }

        System.out.println(oddSum + " " + evenSum);
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