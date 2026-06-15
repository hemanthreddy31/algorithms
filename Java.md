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
