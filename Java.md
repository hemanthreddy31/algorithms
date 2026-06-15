# Java Notes — Quick Reference

## Contents

1. [Integer MAX / MIN Syntax](#java--integer-max--min-syntax)
2. [Input Classes for Interviews](#java--input-classes-for-interviews)
3. [StringBuilder vs StringBuffer](#java--stringbuilder-vs-stringbuffer)

---

<br>

# Java — Integer MAX / MIN Syntax

## Integer (int) limits

```java
int maxInt = Integer.MAX_VALUE;   //  2147483647  (2^31 - 1)
int minInt = Integer.MIN_VALUE;   // -2147483648  (-2^31)

int bits   = Integer.SIZE;        // 32  (number of bits)
int bytes  = Integer.BYTES;       // 4   (number of bytes)
```

## Long limits

```java
long maxLong = Long.MAX_VALUE;    //  9223372036854775807  (2^63 - 1)
long minLong = Long.MIN_VALUE;    // -9223372036854775808  (-2^63)

int  longBits  = Long.SIZE;       // 64
int  longBytes = Long.BYTES;      // 8
```

## Other numeric type limits

```java
// byte  (8-bit)
byte  maxByte  = Byte.MAX_VALUE;        //  127
byte  minByte  = Byte.MIN_VALUE;        // -128

// short (16-bit)
short maxShort = Short.MAX_VALUE;       //  32767
short minShort = Short.MIN_VALUE;       // -32768

// char  (16-bit, unsigned)
char  maxChar  = Character.MAX_VALUE;   // '\uffff' (65535)
char  minChar  = Character.MIN_VALUE;   // '\u0000' (0)

// float
float maxFloat = Float.MAX_VALUE;       //  3.4028235E38
float minFloat = Float.MIN_VALUE;       //  1.4E-45 (smallest positive)

// double
double maxDouble = Double.MAX_VALUE;    //  1.7976931348623157E308
double minDouble = Double.MIN_VALUE;    //  4.9E-324 (smallest positive)
```

## Common usage in algorithms

```java
// Initialize for finding a minimum
int minSoFar = Integer.MAX_VALUE;
for (int x : arr) {
    minSoFar = Math.min(minSoFar, x);
}

// Initialize for finding a maximum
int maxSoFar = Integer.MIN_VALUE;
for (int x : arr) {
    maxSoFar = Math.max(maxSoFar, x);
}
```

## Overflow caution

```java
// Adding 1 to MAX_VALUE overflows (wraps around to MIN_VALUE)
int overflow = Integer.MAX_VALUE + 1;   // -2147483648

// Use long to avoid overflow
long safe = (long) Integer.MAX_VALUE + 1;   // 2147483648

// Negating MIN_VALUE overflows (stays MIN_VALUE)
int neg = -Integer.MIN_VALUE;           // -2147483648
```

<sub>[↑ Back to top](#contents)</sub>

---

<br>

# Java — Input Classes for Interviews

> In LeetCode-style interviews (Google/Amazon/Salesforce) the input is passed as method arguments, so you rarely parse stdin yourself. Know just these two for the rare cases (online assessments / console-based rounds).

## Quick pick

| Class            | Speed | Use when                                    |
| ---------------- | ----- | ------------------------------------------- |
| `Scanner`        | Slow  | Default. Easy, readable, small input        |
| `BufferedReader` | Fast  | Large input (OA with 10^5+ lines), parsing  |

Rule of thumb: **default to `Scanner`**; switch to `BufferedReader` only if input is large enough to risk TLE.

## 1. Scanner — easy, slow

```java
import java.util.Scanner;

Scanner sc = new Scanner(System.in);
int n      = sc.nextInt();        // int token
long l     = sc.nextLong();       // long token
double d   = sc.nextDouble();     // double token
String word = sc.next();          // single token (no spaces)
String line = sc.nextLine();      // whole line

sc.hasNext();      // more tokens?
sc.hasNextInt();   // next token an int?
sc.close();
```
> Gotcha: `nextInt()` then `nextLine()` — the trailing newline is read by `nextLine()`. Add an extra `sc.nextLine()` to consume it.

## 2. BufferedReader — fast, line based

```java
import java.io.*;
import java.util.*;

BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

int n = Integer.parseInt(br.readLine().trim());     // single int line

// Multiple ints on one line (space separated)
StringTokenizer st = new StringTokenizer(br.readLine());
int a = Integer.parseInt(st.nextToken());
int b = Integer.parseInt(st.nextToken());

// Array on one line
int[] arr = Arrays.stream(br.readLine().trim().split("\\s+"))
                  .mapToInt(Integer::parseInt)
                  .toArray();
```
> Faster than `split` for big input: use `StringTokenizer` to tokenize each line.

### Complete example — read 3 ints from one line

```java
import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        int a = Integer.parseInt(st.nextToken());
        int b = Integer.parseInt(st.nextToken());
        int c = Integer.parseInt(st.nextToken());

        System.out.println(a + " " + b + " " + c);
    }
}
```
> `readLine()` throws `IOException`, so `main` declares `throws IOException`.

## Fast output (only if printing a lot)

```java
// Printing line-by-line with System.out.println is slow in a hot loop.
StringBuilder sb = new StringBuilder();
for (int i = 0; i < n; i++) sb.append(ans[i]).append('\n');
System.out.print(sb);   // build once, print once
```

## Cheat sheet

- Default → `Scanner` (clean and readable; graders care about clarity).
- Large input (online assessment) → `BufferedReader` + `StringTokenizer`.
- Large output → build a `StringBuilder`, print once.

<sub>[↑ Back to top](#contents)</sub>

---

<br>

# Java — StringBuilder vs StringBuffer

**Use `StringBuilder`.** It is the right choice for interviews and ~99% of real code.

| Feature       | `StringBuilder`        | `StringBuffer`              |
| ------------- | ---------------------- | --------------------------- |
| Thread-safe   | No                     | Yes (methods `synchronized`)|
| Speed         | Faster                 | Slower (locking overhead)   |
| Since         | Java 5                 | Java 1.0                    |
| Use when      | Single-threaded (always in DSA) | Shared mutable string across threads (rare) |

Why: in an interview/algorithm you work on one thread, so the synchronization in `StringBuffer` is pure overhead. `StringBuilder` has the same API without the cost.

```java
StringBuilder sb = new StringBuilder();
sb.append("ab").append(1);   // "ab1"
sb.insert(0, "x");           // "xab1"
sb.deleteCharAt(1);          // "xb1"
sb.reverse();                // "1bx"
sb.charAt(0);                // '1'
sb.setCharAt(0, '2');        // "2bx"
sb.length();                 // 3
String s = sb.toString();    // convert to String
```

> Never build strings with `+=` in a loop — that creates a new `String` each iteration (`O(n²)`). Use `StringBuilder` (`O(n)`).

<sub>[↑ Back to top](#contents)</sub>
