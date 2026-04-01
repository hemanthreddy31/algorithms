from __future__ import annotations

from typing import Any


def p(
    title: str,
    slug: str,
    difficulty: str,
    statement: str,
    input_format: str,
    output_format: str,
    constraints: list[str],
    examples: list[dict[str, str]],
    edge_cases: list[str],
    brute_force: str,
    optimal: str,
    steps: list[str],
    time_complexity: str,
    space_complexity: str,
    java_code: str,
    dry_run: str,
    follow_ups: list[str],
    alternatives: list[str],
    interview_context: str,
) -> dict[str, Any]:
    return {
        "title": title,
        "slug": slug,
        "difficulty": difficulty,
        "statement": statement,
        "input_format": input_format,
        "output_format": output_format,
        "constraints": constraints,
        "examples": examples,
        "edge_cases": edge_cases,
        "brute_force": brute_force,
        "optimal": optimal,
        "steps": steps,
        "time_complexity": time_complexity,
        "space_complexity": space_complexity,
        "java_code": java_code,
        "dry_run": dry_run,
        "follow_ups": follow_ups,
        "alternatives": alternatives,
        "interview_context": interview_context,
    }


PROBLEMS_BY_TOPIC: dict[str, list[dict[str, Any]]] = {
    "foundations": [
        p(
            title="Single Number",
            slug="single-number",
            difficulty="Easy",
            statement="Given a non-empty integer array where every element appears exactly twice except one element that appears once, return the element that appears once.",
            input_format="nums: integer array of length n.",
            output_format="Return a single integer that appears exactly once.",
            constraints=[
                "1 <= n <= 3 * 10^4",
                "-3 * 10^4 <= nums[i] <= 3 * 10^4",
                "Each element appears twice except one element.",
            ],
            examples=[
                {"input": "nums = [4,1,2,1,2]", "output": "4", "explanation": "Pairs cancel out under XOR."}
            ],
            edge_cases=[
                "Array size is 1.",
                "Single element is at start or end.",
                "Contains negative values.",
            ],
            brute_force="Count frequency of each number using a map and return the number with frequency 1. This takes linear extra memory.",
            optimal="Use XOR. Since x ^ x = 0 and x ^ 0 = x, all duplicate pairs cancel and only the unique number remains.",
            steps=[
                "Initialize result = 0.",
                "XOR each number into result.",
                "Return result.",
            ],
            time_complexity="O(n), one pass through the array.",
            space_complexity="O(1), constant extra memory.",
            java_code="""
class Solution {
    public int singleNumber(int[] nums) {
        int result = 0;
        for (int num : nums) {
            result ^= num;
        }
        return result;
    }
}
""",
            dry_run="For [4,1,2,1,2]: (((((0^4)^1)^2)^1)^2) = 4.",
            follow_ups=[
                "What if every element appears three times except one?",
                "Can you solve when two unique numbers exist?",
            ],
            alternatives=[
                "HashMap frequency count (O(n) space).",
                "Sorting and scanning adjacent pairs (O(n log n)).",
            ],
            interview_context="Interviewers use this to test bit manipulation fluency and ability to move from counting to a constant-space trick.",
        ),
        p(
            title="Power of Two",
            slug="power-of-two",
            difficulty="Easy",
            statement="Given an integer n, return true if n is a power of two. Otherwise return false.",
            input_format="n: signed 32-bit integer.",
            output_format="Return true if n = 2^k for some integer k >= 0; otherwise false.",
            constraints=[
                "-2^31 <= n <= 2^31 - 1",
            ],
            examples=[
                {"input": "n = 16", "output": "true", "explanation": "16 = 2^4."},
                {"input": "n = 18", "output": "false", "explanation": "Not a pure power of two."},
            ],
            edge_cases=[
                "n <= 0 should return false.",
                "n = 1 should return true.",
                "Largest positive int boundary.",
            ],
            brute_force="Repeatedly divide n by 2 while divisible; at the end check if n becomes 1.",
            optimal="For positive n, power of two numbers have exactly one set bit. So n & (n - 1) equals 0 only for powers of two.",
            steps=[
                "If n <= 0, return false.",
                "Compute n & (n - 1).",
                "Return whether result is 0.",
            ],
            time_complexity="O(1).",
            space_complexity="O(1).",
            java_code="""
class Solution {
    public boolean isPowerOfTwo(int n) {
        return n > 0 && (n & (n - 1)) == 0;
    }
}
""",
            dry_run="n=16 (10000b), n-1=01111b, AND=0 -> true.",
            follow_ups=[
                "How do you check power of four?",
                "How would this change for 64-bit numbers?",
            ],
            alternatives=[
                "Loop division by 2 until odd.",
                "Use logarithms with precision guard (not preferred).",
            ],
            interview_context="This question checks whether the candidate recognizes a canonical bit pattern quickly.",
        ),
        p(
            title="Pow(x, n)",
            slug="powx-n",
            difficulty="Medium",
            statement="Implement x raised to power n (x^n), where n can be negative.",
            input_format="x: double, n: signed 32-bit integer.",
            output_format="Return x^n as a double.",
            constraints=[
                "-100.0 < x < 100.0",
                "-2^31 <= n <= 2^31 - 1",
                "Either x != 0 or n > 0",
            ],
            examples=[
                {"input": "x = 2.0, n = 10", "output": "1024.0", "explanation": "2 multiplied 10 times."},
                {"input": "x = 2.0, n = -2", "output": "0.25", "explanation": "1 / (2^2)."},
            ],
            edge_cases=[
                "n is Integer.MIN_VALUE (overflow risk for abs).",
                "x = 1 or -1.",
                "Negative exponent.",
            ],
            brute_force="Multiply x by itself |n| times, then invert if n is negative. This is O(|n|).",
            optimal="Use fast exponentiation: x^n = (x^(n/2))^2 when n is even, and x * x^(n-1) when odd. Process bits of exponent in O(log n).",
            steps=[
                "Promote n to long to safely handle Integer.MIN_VALUE.",
                "If exponent is negative, invert base and make exponent positive.",
                "Binary exponentiation: square base every step and multiply answer when bit is set.",
            ],
            time_complexity="O(log |n|).",
            space_complexity="O(1) iterative implementation.",
            java_code="""
class Solution {
    public double myPow(double x, int n) {
        long exp = n;
        if (exp < 0) {
            x = 1.0 / x;
            exp = -exp;
        }

        double result = 1.0;
        while (exp > 0) {
            if ((exp & 1L) == 1L) {
                result *= x;
            }
            x *= x;
            exp >>= 1;
        }
        return result;
    }
}
""",
            dry_run="x=2,n=10: result=1, exp bits 1010 -> multiply at set bits after squaring chain -> 1024.",
            follow_ups=[
                "Can you write the recursive version?",
                "How do you avoid precision issues for very large exponents?",
            ],
            alternatives=[
                "Recursive divide-and-conquer with O(log n) stack depth.",
                "Math library call (not expected in interview).",
            ],
            interview_context="Interviewers test divide-and-conquer reasoning and careful handling of integer edge cases.",
        ),
        p(
            title="Subsets",
            slug="subsets",
            difficulty="Medium",
            statement="Given an integer array of unique elements, return all possible subsets (the power set).",
            input_format="nums: integer array with unique values.",
            output_format="Return a list of all subsets in any order.",
            constraints=[
                "1 <= nums.length <= 10",
                "-10 <= nums[i] <= 10",
                "All elements are unique.",
            ],
            examples=[
                {
                    "input": "nums = [1,2,3]",
                    "output": "[[],[1],[2],[3],[1,2],[1,3],[2,3],[1,2,3]]",
                    "explanation": "Every element is either picked or skipped.",
                }
            ],
            edge_cases=[
                "Single element input.",
                "Negative numbers.",
                "Ordering of subsets can differ.",
            ],
            brute_force="Iterate all bitmasks from 0 to 2^n - 1 and build a subset from set bits.",
            optimal="Backtracking explores inclusion/exclusion decisions recursively and naturally builds subsets.",
            steps=[
                "Start DFS from index 0 with empty path.",
                "Add current path snapshot to answer.",
                "Try each next index, include value, recurse, and backtrack.",
            ],
            time_complexity="O(n * 2^n), because there are 2^n subsets and each copy can take O(n).",
            space_complexity="O(n) recursion stack excluding output.",
            java_code="""
import java.util.*;

class Solution {
    public List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        backtrack(0, nums, new ArrayList<>(), result);
        return result;
    }

    private void backtrack(int start, int[] nums, List<Integer> path, List<List<Integer>> result) {
        result.add(new ArrayList<>(path));
        for (int i = start; i < nums.length; i++) {
            path.add(nums[i]);
            backtrack(i + 1, nums, path, result);
            path.remove(path.size() - 1);
        }
    }
}
""",
            dry_run="nums=[1,2]: [] -> [1] -> [1,2] backtrack -> [2] -> done.",
            follow_ups=[
                "How does solution change if duplicates are allowed?",
                "Can you generate subsets in lexicographic order?",
            ],
            alternatives=[
                "Bitmask generation.",
                "Iterative expansion: for each num, clone existing subsets and append num.",
            ],
            interview_context="This tests recursion tree control and backtracking hygiene (push/pop state).",
        ),
        p(
            title="Count Primes (Sieve of Eratosthenes)",
            slug="count-primes-sieve",
            difficulty="Medium",
            statement="Given an integer n, return the number of prime numbers strictly less than n.",
            input_format="n: integer upper bound.",
            output_format="Return count of primes in range [0, n).",
            constraints=[
                "0 <= n <= 5 * 10^6",
            ],
            examples=[
                {"input": "n = 10", "output": "4", "explanation": "Primes are 2,3,5,7."}
            ],
            edge_cases=[
                "n <= 2 gives 0.",
                "Large n requires optimized marking start i*i.",
                "Need to avoid overflow in i*i for large i.",
            ],
            brute_force="Check primality for each number from 2 to n-1 by trial division up to sqrt(k).",
            optimal="Use sieve: mark multiples of each unmarked number starting from i*i. Unmarked numbers are primes.",
            steps=[
                "Create boolean array isPrime initialized true for [2..n-1].",
                "For each i up to sqrt(n), if prime, mark multiples i*i, i*i+i, ... as non-prime.",
                "Count remaining true values.",
            ],
            time_complexity="O(n log log n).",
            space_complexity="O(n).",
            java_code="""
class Solution {
    public int countPrimes(int n) {
        if (n <= 2) {
            return 0;
        }

        boolean[] isPrime = new boolean[n];
        for (int i = 2; i < n; i++) {
            isPrime[i] = true;
        }

        for (int i = 2; (long) i * i < n; i++) {
            if (!isPrime[i]) {
                continue;
            }
            for (int j = i * i; j < n; j += i) {
                isPrime[j] = false;
            }
        }

        int count = 0;
        for (int i = 2; i < n; i++) {
            if (isPrime[i]) {
                count++;
            }
        }
        return count;
    }
}
""",
            dry_run="n=10: mark multiples of 2 -> 4,6,8; of 3 -> 9. Remaining primes: 2,3,5,7.",
            follow_ups=[
                "How can you reduce memory for very large n?",
                "Can you handle multiple range prime queries?",
            ],
            alternatives=[
                "Segmented sieve for large intervals.",
                "Trial division baseline for small n.",
            ],
            interview_context="This checks mathematical optimization and ability to move from naive primality checks to precomputation.",
        ),
    ],
    "arrays-strings": [
        p(
            title="Range Sum Query - Immutable",
            slug="range-sum-query-immutable",
            difficulty="Easy",
            statement="Design a data structure that supports sumRange(left, right): return the sum of elements between indices left and right inclusive in an immutable array.",
            input_format="Constructor input: nums array. Query input: left and right indices.",
            output_format="For each query, return integer sum of nums[left..right].",
            constraints=[
                "1 <= nums.length <= 10^4",
                "-10^5 <= nums[i] <= 10^5",
                "0 <= left <= right < nums.length",
                "Up to 10^4 queries",
            ],
            examples=[
                {
                    "input": "nums=[-2,0,3,-5,2,-1], sumRange(0,2), sumRange(2,5)",
                    "output": "1, -1",
                    "explanation": "Prefix sums answer each query in O(1).",
                }
            ],
            edge_cases=[
                "Single element range.",
                "All negative values.",
                "Query covers full array.",
            ],
            brute_force="For each query, iterate from left to right and sum values. This is O(n) per query.",
            optimal="Precompute prefix sums where prefix[i] stores sum of first i elements. Then sumRange(l,r)=prefix[r+1]-prefix[l].",
            steps=[
                "Build prefix array of size n+1 with prefix[0]=0.",
                "For each index i, set prefix[i+1] = prefix[i] + nums[i].",
                "Answer query using subtraction.",
            ],
            time_complexity="Build O(n), each query O(1).",
            space_complexity="O(n) for prefix array.",
            java_code="""
class NumArray {
    private final int[] prefix;

    public NumArray(int[] nums) {
        prefix = new int[nums.length + 1];
        for (int i = 0; i < nums.length; i++) {
            prefix[i + 1] = prefix[i] + nums[i];
        }
    }

    public int sumRange(int left, int right) {
        return prefix[right + 1] - prefix[left];
    }
}
""",
            dry_run="nums=[1,3,5], prefix=[0,1,4,9]. sumRange(1,2)=prefix[3]-prefix[1]=8.",
            follow_ups=[
                "How would you support updates too?",
                "How do you extend to 2D matrix range sums?",
            ],
            alternatives=[
                "Fenwick Tree for mutable array.",
                "Segment Tree for mutable range queries.",
            ],
            interview_context="This tests prefix-sum recognition and precomputation tradeoffs.",
        ),
        p(
            title="Minimum Size Subarray Sum",
            slug="minimum-size-subarray-sum",
            difficulty="Medium",
            statement="Given an array of positive integers nums and a positive integer target, return the minimal length of a contiguous subarray of which the sum is at least target. Return 0 if no such subarray exists.",
            input_format="target: integer, nums: positive integer array.",
            output_format="Return minimum valid window length as integer.",
            constraints=[
                "1 <= target <= 10^9",
                "1 <= nums.length <= 10^5",
                "1 <= nums[i] <= 10^4",
            ],
            examples=[
                {"input": "target=7, nums=[2,3,1,2,4,3]", "output": "2", "explanation": "[4,3] has sum 7 with minimum length 2."}
            ],
            edge_cases=[
                "No valid subarray => return 0.",
                "Single element already >= target.",
                "Entire array required.",
            ],
            brute_force="Try every start index and expand until sum>=target, track minimum length. This is O(n^2).",
            optimal="Because all numbers are positive, use sliding window with two pointers and shrink while sum >= target.",
            steps=[
                "Expand right pointer and add nums[right].",
                "While window sum >= target, update answer and shrink from left.",
                "If answer not updated, return 0.",
            ],
            time_complexity="O(n), each pointer moves at most n times.",
            space_complexity="O(1).",
            java_code="""
class Solution {
    public int minSubArrayLen(int target, int[] nums) {
        int left = 0;
        int sum = 0;
        int best = Integer.MAX_VALUE;

        for (int right = 0; right < nums.length; right++) {
            sum += nums[right];
            while (sum >= target) {
                best = Math.min(best, right - left + 1);
                sum -= nums[left++];
            }
        }

        return best == Integer.MAX_VALUE ? 0 : best;
    }
}
""",
            dry_run="target=7: window grows to [2,3,1,2] sum=8, shrink to [3,1,2] len=3 then [1,2] sum=3; later [4,3] gives len=2.",
            follow_ups=[
                "How would this change if numbers could be negative?",
                "Can you return the subarray indices too?",
            ],
            alternatives=[
                "Prefix sum + binary search on each start (O(n log n)).",
            ],
            interview_context="Sliding-window fundamentals are heavily tested in array/string rounds.",
        ),
        p(
            title="3Sum",
            slug="three-sum",
            difficulty="Medium",
            statement="Given an integer array nums, return all unique triplets [nums[i], nums[j], nums[k]] such that i != j != k and nums[i] + nums[j] + nums[k] == 0.",
            input_format="nums: integer array.",
            output_format="Return list of unique triplets with sum zero.",
            constraints=[
                "3 <= nums.length <= 3000",
                "-10^5 <= nums[i] <= 10^5",
            ],
            examples=[
                {
                    "input": "nums=[-1,0,1,2,-1,-4]",
                    "output": "[[-1,-1,2],[-1,0,1]]",
                    "explanation": "Sort and use two pointers while skipping duplicates.",
                }
            ],
            edge_cases=[
                "All positives or all negatives => no triplet.",
                "Many duplicates.",
                "Exactly one valid triplet.",
            ],
            brute_force="Check all i,j,k combinations and use set for deduplication. Complexity O(n^3).",
            optimal="Sort array. Fix i, then solve two-sum with left/right pointers for target -nums[i], skipping duplicates.",
            steps=[
                "Sort nums.",
                "Iterate i from 0 to n-3 and skip duplicate anchors.",
                "Use left=i+1 and right=n-1, move pointers based on sum and dedupe.",
            ],
            time_complexity="O(n^2) after sorting.",
            space_complexity="O(1) extra excluding output.",
            java_code="""
import java.util.*;

class Solution {
    public List<List<Integer>> threeSum(int[] nums) {
        Arrays.sort(nums);
        List<List<Integer>> ans = new ArrayList<>();

        for (int i = 0; i < nums.length - 2; i++) {
            if (i > 0 && nums[i] == nums[i - 1]) {
                continue;
            }

            int left = i + 1;
            int right = nums.length - 1;
            while (left < right) {
                int sum = nums[i] + nums[left] + nums[right];
                if (sum == 0) {
                    ans.add(Arrays.asList(nums[i], nums[left], nums[right]));
                    left++;
                    right--;
                    while (left < right && nums[left] == nums[left - 1]) {
                        left++;
                    }
                    while (left < right && nums[right] == nums[right + 1]) {
                        right--;
                    }
                } else if (sum < 0) {
                    left++;
                } else {
                    right--;
                }
            }
        }
        return ans;
    }
}
""",
            dry_run="After sorting [-4,-1,-1,0,1,2], anchor -1 (index1): left=-1,right=2 -> triplet [-1,-1,2], then [-1,0,1].",
            follow_ups=[
                "How do you solve 3Sum Closest?",
                "How does k-Sum generalization work?",
            ],
            alternatives=[
                "Hashing two-sum per anchor (still O(n^2), harder dedupe).",
            ],
            interview_context="This problem checks sorting + two-pointer control and duplicate handling discipline.",
        ),
        p(
            title="Subarray Sum Equals K",
            slug="subarray-sum-equals-k",
            difficulty="Medium",
            statement="Given an integer array nums and an integer k, return the total number of continuous subarrays whose sum equals k.",
            input_format="nums: integer array (can include negatives), k: integer target.",
            output_format="Return count of subarrays with sum exactly k.",
            constraints=[
                "1 <= nums.length <= 2 * 10^4",
                "-1000 <= nums[i] <= 1000",
                "-10^7 <= k <= 10^7",
            ],
            examples=[
                {"input": "nums=[1,1,1], k=2", "output": "2", "explanation": "Subarrays [1,1] at indices (0,1) and (1,2)."}
            ],
            edge_cases=[
                "Negative numbers break sliding-window monotonicity.",
                "k = 0 with multiple zero sums.",
                "Single element equals k.",
            ],
            brute_force="Compute all subarray sums using nested loops and count matches; O(n^2).",
            optimal="Use prefix sum + HashMap frequency. If current prefix is p, previous prefix p-k indicates a subarray ending here with sum k.",
            steps=[
                "Initialize map with {0:1} to count subarrays starting at index 0.",
                "Accumulate running prefix sum.",
                "Add map[prefix-k] to answer, then increment map[prefix].",
            ],
            time_complexity="O(n).",
            space_complexity="O(n) in worst case for prefix map.",
            java_code="""
import java.util.*;

class Solution {
    public int subarraySum(int[] nums, int k) {
        Map<Integer, Integer> freq = new HashMap<>();
        freq.put(0, 1);

        int prefix = 0;
        int count = 0;
        for (int num : nums) {
            prefix += num;
            count += freq.getOrDefault(prefix - k, 0);
            freq.put(prefix, freq.getOrDefault(prefix, 0) + 1);
        }
        return count;
    }
}
""",
            dry_run="nums=[1,2,3],k=3: prefix 1->map[ -2]=0, prefix 3->map[0]=1 count=1, prefix 6->map[3]=1 count=2.",
            follow_ups=[
                "How do you return longest subarray with sum k?",
                "How does this differ from subarray sum divisible by k?",
            ],
            alternatives=[
                "Nested loops with prefix array O(n^2).",
            ],
            interview_context="It tests whether candidates can apply prefix-hash counting when negatives invalidate sliding window.",
        ),
        p(
            title="Find the Index of the First Occurrence in a String (KMP)",
            slug="first-occurrence-kmp",
            difficulty="Medium",
            statement="Given haystack and needle strings, return the index of the first occurrence of needle in haystack, or -1 if needle is not part of haystack.",
            input_format="haystack: string, needle: string.",
            output_format="Return zero-based index or -1.",
            constraints=[
                "1 <= haystack.length, needle.length <= 10^4",
                "Strings contain lowercase English letters.",
            ],
            examples=[
                {"input": "haystack=\"sadbutsad\", needle=\"sad\"", "output": "0", "explanation": "First match starts at index 0."},
                {"input": "haystack=\"leetcode\", needle=\"leeto\"", "output": "-1", "explanation": "No full match."},
            ],
            edge_cases=[
                "Needle longer than haystack.",
                "Repeated prefix patterns in needle.",
                "Match occurs at the end.",
            ],
            brute_force="Try each start index and compare characters one by one. Worst-case O(n*m).",
            optimal="KMP builds LPS array (longest proper prefix suffix) to avoid rechecking characters and achieves O(n+m).",
            steps=[
                "Build LPS for needle.",
                "Scan haystack with two pointers i (text) and j (pattern).",
                "On mismatch, jump j to LPS[j-1] instead of restarting from zero.",
            ],
            time_complexity="O(n + m).",
            space_complexity="O(m) for LPS.",
            java_code="""
class Solution {
    public int strStr(String haystack, String needle) {
        if (needle.isEmpty()) {
            return 0;
        }

        int[] lps = buildLps(needle);
        int i = 0;
        int j = 0;

        while (i < haystack.length()) {
            if (haystack.charAt(i) == needle.charAt(j)) {
                i++;
                j++;
                if (j == needle.length()) {
                    return i - j;
                }
            } else if (j > 0) {
                j = lps[j - 1];
            } else {
                i++;
            }
        }

        return -1;
    }

    private int[] buildLps(String p) {
        int[] lps = new int[p.length()];
        int len = 0;
        for (int i = 1; i < p.length(); ) {
            if (p.charAt(i) == p.charAt(len)) {
                lps[i++] = ++len;
            } else if (len > 0) {
                len = lps[len - 1];
            } else {
                lps[i++] = 0;
            }
        }
        return lps;
    }
}
""",
            dry_run="haystack='aaaaab', needle='aaab'. LPS enables jump from j=3 to j=2, avoiding full restart and finding index 2.",
            follow_ups=[
                "How would Rabin-Karp compare here?",
                "Can you find all occurrences instead of first?",
            ],
            alternatives=[
                "Rabin-Karp rolling hash average O(n+m).",
                "Naive scan O(n*m).",
            ],
            interview_context="This tests deeper string-pattern matching fundamentals beyond standard library calls.",
        ),
        p(
            title="Rabin-Karp Substring Search (Rolling Hash)",
            slug="rabin-karp-substring-search",
            difficulty="Medium",
            statement="Given text and pattern strings, return the first index where pattern appears in text using rolling hash, or -1 if not found.",
            input_format="text: string, pattern: string.",
            output_format="Return zero-based first match index or -1.",
            constraints=[
                "1 <= text.length, pattern.length <= 10^5",
                "Strings contain lowercase English letters.",
            ],
            examples=[
                {
                    "input": "text='abracadabra', pattern='cada'",
                    "output": "4",
                    "explanation": "Rolling hash windows match at index 4, then verified by substring compare.",
                }
            ],
            edge_cases=[
                "Pattern longer than text.",
                "Multiple hash collisions possible.",
                "Repeated character text.",
            ],
            brute_force="Compare pattern against every window character-by-character. O((n-m+1) * m).",
            optimal="Use polynomial rolling hash for O(1) window hash updates and verify only on hash match.",
            steps=[
                "Precompute base powers and initial hashes for first window and pattern.",
                "Slide window by removing outgoing char and adding incoming char with modular arithmetic.",
                "When hashes match, verify actual substring to avoid collision errors.",
            ],
            time_complexity="Average O(n + m), worst-case O(n*m) with heavy collisions.",
            space_complexity="O(1) extra (excluding power precompute if done on fly).",
            java_code="""
class Solution {
    public int rabinKarp(String text, String pattern) {
        int n = text.length();
        int m = pattern.length();
        if (m > n) {
            return -1;
        }

        long mod = 1_000_000_007L;
        long base = 911382323L;
        long power = 1L;

        long patternHash = 0L;
        long windowHash = 0L;
        for (int i = 0; i < m; i++) {
            patternHash = (patternHash * base + pattern.charAt(i)) % mod;
            windowHash = (windowHash * base + text.charAt(i)) % mod;
            if (i > 0) {
                power = (power * base) % mod;
            }
        }

        for (int i = 0; i <= n - m; i++) {
            if (windowHash == patternHash && text.regionMatches(i, pattern, 0, m)) {
                return i;
            }
            if (i < n - m) {
                long out = (text.charAt(i) * power) % mod;
                windowHash = (windowHash - out + mod) % mod;
                windowHash = (windowHash * base + text.charAt(i + m)) % mod;
            }
        }

        return -1;
    }
}
""",
            dry_run="Window 'abra' hash differs, slide until window 'cada' hash equals pattern hash, then verify and return index 4.",
            follow_ups=[
                "How to return all match indices instead of first?",
                "How to reduce collision probability further?",
            ],
            alternatives=[
                "KMP deterministic O(n+m).",
                "Z-algorithm pattern matching.",
            ],
            interview_context="Interviewers use this to test rolling-hash design and collision-aware implementation.",
        ),
    ],
    "searching-sorting": [
        p(
            title="Binary Search",
            slug="binary-search",
            difficulty="Easy",
            statement="Given a sorted integer array nums and an integer target, return the index of target if it exists, otherwise return -1.",
            input_format="nums: sorted integer array in ascending order, target: integer.",
            output_format="Return target index or -1.",
            constraints=[
                "1 <= nums.length <= 10^4",
                "-10^4 <= nums[i], target <= 10^4",
                "All nums values are unique.",
            ],
            examples=[
                {"input": "nums=[-1,0,3,5,9,12], target=9", "output": "4", "explanation": "Target found at index 4."}
            ],
            edge_cases=[
                "Target smaller than first element.",
                "Target greater than last element.",
                "Single element array.",
            ],
            brute_force="Linearly scan the array and compare each element. O(n).",
            optimal="Use binary search on sorted array and halve the search space each step.",
            steps=[
                "Set lo=0 and hi=n-1.",
                "Compute mid safely as lo + (hi-lo)/2.",
                "Adjust boundaries based on comparison until found or interval empty.",
            ],
            time_complexity="O(log n).",
            space_complexity="O(1).",
            java_code="""
class Solution {
    public int search(int[] nums, int target) {
        int lo = 0;
        int hi = nums.length - 1;

        while (lo <= hi) {
            int mid = lo + (hi - lo) / 2;
            if (nums[mid] == target) {
                return mid;
            }
            if (nums[mid] < target) {
                lo = mid + 1;
            } else {
                hi = mid - 1;
            }
        }
        return -1;
    }
}
""",
            dry_run="nums=[1,3,5,7], target=5 -> mid=1 val=3, move right; mid=2 val=5, return 2.",
            follow_ups=[
                "How do you return insertion position instead?",
                "How do you find first or last occurrence with duplicates?",
            ],
            alternatives=[
                "Recursive binary search.",
            ],
            interview_context="This is a baseline correctness test for loop invariants and boundary control.",
        ),
        p(
            title="Search in Rotated Sorted Array",
            slug="search-rotated-sorted-array",
            difficulty="Medium",
            statement="An integer array nums is sorted in ascending order and then rotated. Given nums and target, return index of target or -1.",
            input_format="nums: rotated sorted integer array with unique values, target: integer.",
            output_format="Return target index or -1.",
            constraints=[
                "1 <= nums.length <= 5000",
                "-10^4 <= nums[i], target <= 10^4",
                "All nums values are unique.",
            ],
            examples=[
                {"input": "nums=[4,5,6,7,0,1,2], target=0", "output": "4", "explanation": "Target is in right sorted half."}
            ],
            edge_cases=[
                "Array not rotated.",
                "Target in left sorted half.",
                "Target absent.",
            ],
            brute_force="Linear scan to find target index. O(n).",
            optimal="Modified binary search: one side is always sorted. Decide whether target lies in sorted side and move accordingly.",
            steps=[
                "Pick mid.",
                "Check whether left half [lo..mid] is sorted or right half [mid..hi] is sorted.",
                "If target lies in sorted half, shrink there; otherwise move to other half.",
            ],
            time_complexity="O(log n).",
            space_complexity="O(1).",
            java_code="""
class Solution {
    public int search(int[] nums, int target) {
        int lo = 0;
        int hi = nums.length - 1;

        while (lo <= hi) {
            int mid = lo + (hi - lo) / 2;
            if (nums[mid] == target) {
                return mid;
            }

            if (nums[lo] <= nums[mid]) {
                if (nums[lo] <= target && target < nums[mid]) {
                    hi = mid - 1;
                } else {
                    lo = mid + 1;
                }
            } else {
                if (nums[mid] < target && target <= nums[hi]) {
                    lo = mid + 1;
                } else {
                    hi = mid - 1;
                }
            }
        }

        return -1;
    }
}
""",
            dry_run="nums=[4,5,6,7,0,1,2], target=0: mid=3(7), left sorted but target not there => lo=4, then mid=5(1), target in left => hi=4, mid=4 hit.",
            follow_ups=[
                "How do duplicates change the logic?",
                "How to find rotation pivot first then binary search?",
            ],
            alternatives=[
                "Find pivot in O(log n), then two binary searches.",
            ],
            interview_context="This tests binary search under non-trivial monotonic structure.",
        ),
        p(
            title="Find First and Last Position of Element in Sorted Array",
            slug="first-and-last-position",
            difficulty="Medium",
            statement="Given a sorted array nums and target, return the starting and ending position of target. If not found, return [-1, -1].",
            input_format="nums: sorted integer array (may contain duplicates), target: integer.",
            output_format="Return int array of size 2: [firstIndex, lastIndex].",
            constraints=[
                "0 <= nums.length <= 10^5",
                "-10^9 <= nums[i], target <= 10^9",
            ],
            examples=[
                {"input": "nums=[5,7,7,8,8,10], target=8", "output": "[3,4]", "explanation": "8 appears from index 3 to 4."}
            ],
            edge_cases=[
                "Empty array.",
                "Single occurrence.",
                "All elements equal target.",
            ],
            brute_force="Scan from left to right and track first/last. O(n).",
            optimal="Use two binary searches: lower bound for first index, upper bound for last index.",
            steps=[
                "Binary search leftmost index where value >= target.",
                "Binary search leftmost index where value > target.",
                "Derive last as upperBound-1 and validate existence.",
            ],
            time_complexity="O(log n).",
            space_complexity="O(1).",
            java_code="""
class Solution {
    public int[] searchRange(int[] nums, int target) {
        int first = lowerBound(nums, target);
        if (first == nums.length || nums[first] != target) {
            return new int[] {-1, -1};
        }
        int last = lowerBound(nums, target + 1) - 1;
        return new int[] {first, last};
    }

    private int lowerBound(int[] nums, int target) {
        int lo = 0;
        int hi = nums.length;
        while (lo < hi) {
            int mid = lo + (hi - lo) / 2;
            if (nums[mid] < target) {
                lo = mid + 1;
            } else {
                hi = mid;
            }
        }
        return lo;
    }
}
""",
            dry_run="target=8 => lowerBound(8)=3, lowerBound(9)=5 => last=4.",
            follow_ups=[
                "Can you do it recursively?",
                "How would you solve for floating-point sorted arrays?",
            ],
            alternatives=[
                "One binary search for match then expand both sides (O(n) worst case).",
            ],
            interview_context="This evaluates mastery of lower/upper bound templates with duplicates.",
        ),
        p(
            title="Kth Largest Element in an Array (Quickselect)",
            slug="kth-largest-quickselect",
            difficulty="Medium",
            statement="Given an integer array nums and an integer k, return the kth largest element in the array.",
            input_format="nums: integer array, k: integer (1-indexed rank from largest).",
            output_format="Return kth largest element value.",
            constraints=[
                "1 <= k <= nums.length <= 10^5",
                "-10^4 <= nums[i] <= 10^4",
            ],
            examples=[
                {"input": "nums=[3,2,1,5,6,4], k=2", "output": "5", "explanation": "2nd largest element is 5."}
            ],
            edge_cases=[
                "k = 1 (max element).",
                "k = n (min element).",
                "Duplicate values.",
            ],
            brute_force="Sort descending and return index k-1. O(n log n).",
            optimal="Quickselect partitions around pivot and recurses only into one side, giving average O(n).",
            steps=[
                "Convert kth largest to target index n-k in ascending order.",
                "Partition array around pivot.",
                "Narrow search range based on pivot index.",
            ],
            time_complexity="Average O(n), worst O(n^2) without randomization.",
            space_complexity="O(1) iterative partitioning.",
            java_code="""
import java.util.*;

class Solution {
    private final Random random = new Random();

    public int findKthLargest(int[] nums, int k) {
        int target = nums.length - k;
        int lo = 0;
        int hi = nums.length - 1;

        while (lo <= hi) {
            int pivotIndex = lo + random.nextInt(hi - lo + 1);
            int p = partition(nums, lo, hi, pivotIndex);
            if (p == target) {
                return nums[p];
            } else if (p < target) {
                lo = p + 1;
            } else {
                hi = p - 1;
            }
        }

        return -1;
    }

    private int partition(int[] nums, int lo, int hi, int pivotIndex) {
        int pivot = nums[pivotIndex];
        swap(nums, pivotIndex, hi);
        int store = lo;
        for (int i = lo; i < hi; i++) {
            if (nums[i] < pivot) {
                swap(nums, store++, i);
            }
        }
        swap(nums, store, hi);
        return store;
    }

    private void swap(int[] nums, int i, int j) {
        int t = nums[i];
        nums[i] = nums[j];
        nums[j] = t;
    }
}
""",
            dry_run="nums=[3,2,1,5,6,4], k=2 => target index 4. Partition narrows until pivot index 4 with value 5.",
            follow_ups=[
                "How to guarantee O(n) worst-case?",
                "How to find kth smallest in stream?",
            ],
            alternatives=[
                "Min-heap of size k: O(n log k).",
                "Full sort O(n log n).",
            ],
            interview_context="This checks order-statistics thinking and in-place partition mechanics.",
        ),
        p(
            title="Merge Intervals",
            slug="merge-intervals",
            difficulty="Medium",
            statement="Given an array of intervals where intervals[i] = [start_i, end_i], merge all overlapping intervals and return non-overlapping intervals covering all input intervals.",
            input_format="intervals: 2D integer array.",
            output_format="Return merged 2D integer array.",
            constraints=[
                "1 <= intervals.length <= 10^4",
                "0 <= start_i <= end_i <= 10^4",
            ],
            examples=[
                {
                    "input": "intervals=[[1,3],[2,6],[8,10],[15,18]]",
                    "output": "[[1,6],[8,10],[15,18]]",
                    "explanation": "[1,3] and [2,6] overlap and merge.",
                }
            ],
            edge_cases=[
                "Already non-overlapping intervals.",
                "One interval fully contains others.",
                "Single interval input.",
            ],
            brute_force="Repeatedly compare every pair and merge until stable; can degrade to O(n^2).",
            optimal="Sort intervals by start, then scan and merge into output list by comparing current interval with last merged interval.",
            steps=[
                "Sort by start time.",
                "Initialize merged list with first interval.",
                "For each interval, either extend last merged end or append as new interval.",
            ],
            time_complexity="O(n log n) due to sorting.",
            space_complexity="O(n) for output list.",
            java_code="""
import java.util.*;

class Solution {
    public int[][] merge(int[][] intervals) {
        Arrays.sort(intervals, Comparator.comparingInt(a -> a[0]));
        List<int[]> merged = new ArrayList<>();

        for (int[] interval : intervals) {
            if (merged.isEmpty() || merged.get(merged.size() - 1)[1] < interval[0]) {
                merged.add(new int[] {interval[0], interval[1]});
            } else {
                merged.get(merged.size() - 1)[1] = Math.max(
                    merged.get(merged.size() - 1)[1],
                    interval[1]
                );
            }
        }

        return merged.toArray(new int[merged.size()][]);
    }
}
""",
            dry_run="Sorted intervals: [1,3],[2,6],[8,10]. Start [1,3], merge with [2,6] -> [1,6], then append [8,10].",
            follow_ups=[
                "How would you insert one new interval then merge?",
                "How to count minimum arrows to burst balloons (interval variant)?",
            ],
            alternatives=[
                "Line sweep with event points.",
            ],
            interview_context="This validates sorting transformation and greedy merge reasoning.",
        ),
    ],
    "hashing": [
        p(
            title="Two Sum",
            slug="two-sum",
            difficulty="Easy",
            statement="Given an array of integers nums and an integer target, return indices of the two numbers such that they add up to target.",
            input_format="nums: integer array, target: integer.",
            output_format="Return int array of size 2 containing valid indices.",
            constraints=[
                "2 <= nums.length <= 10^4",
                "-10^9 <= nums[i], target <= 10^9",
                "Exactly one valid answer exists.",
            ],
            examples=[
                {"input": "nums=[2,7,11,15], target=9", "output": "[0,1]", "explanation": "nums[0] + nums[1] = 9."}
            ],
            edge_cases=[
                "Negative numbers.",
                "Duplicate values used as different indices.",
                "Answer appears near end.",
            ],
            brute_force="Check every pair i<j and return when nums[i]+nums[j]==target. O(n^2).",
            optimal="Store seen value->index in HashMap. For each num, check whether target-num already exists.",
            steps=[
                "Initialize empty map.",
                "For each index i, compute complement=target-nums[i].",
                "If complement exists in map, return [map[complement], i], otherwise store nums[i].",
            ],
            time_complexity="O(n).",
            space_complexity="O(n).",
            java_code="""
import java.util.*;

class Solution {
    public int[] twoSum(int[] nums, int target) {
        Map<Integer, Integer> seen = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            int need = target - nums[i];
            if (seen.containsKey(need)) {
                return new int[] {seen.get(need), i};
            }
            seen.put(nums[i], i);
        }
        return new int[0];
    }
}
""",
            dry_run="i=0 val=2 -> store {2:0}; i=1 val=7, need=2 found -> return [0,1].",
            follow_ups=[
                "How would you solve if array is already sorted?",
                "How do you return all unique pairs?",
            ],
            alternatives=[
                "Sort + two pointers with original indices.",
            ],
            interview_context="Classic map lookup pattern question used in almost all screening loops.",
        ),
        p(
            title="Valid Anagram",
            slug="valid-anagram",
            difficulty="Easy",
            statement="Given two strings s and t, return true if t is an anagram of s and false otherwise.",
            input_format="s and t: lowercase strings.",
            output_format="Return boolean result.",
            constraints=[
                "1 <= s.length, t.length <= 5 * 10^4",
                "s and t consist of lowercase English letters.",
            ],
            examples=[
                {"input": "s='anagram', t='nagaram'", "output": "true", "explanation": "Both contain same letter frequencies."}
            ],
            edge_cases=[
                "Different lengths.",
                "Repeated characters.",
                "Unicode variation in follow-up.",
            ],
            brute_force="Sort both strings and compare. O(n log n).",
            optimal="Count character frequencies using fixed array of size 26 and compare counts.",
            steps=[
                "If lengths differ, return false.",
                "Increment count for chars in s and decrement for chars in t.",
                "If any count non-zero, return false; else true.",
            ],
            time_complexity="O(n).",
            space_complexity="O(1) for fixed alphabet.",
            java_code="""
class Solution {
    public boolean isAnagram(String s, String t) {
        if (s.length() != t.length()) {
            return false;
        }

        int[] freq = new int[26];
        for (int i = 0; i < s.length(); i++) {
            freq[s.charAt(i) - 'a']++;
            freq[t.charAt(i) - 'a']--;
        }

        for (int count : freq) {
            if (count != 0) {
                return false;
            }
        }
        return true;
    }
}
""",
            dry_run="s='ab', t='ba': freq after scan returns all zeros -> true.",
            follow_ups=[
                "How would you handle Unicode input efficiently?",
                "Can this be done for streaming characters?",
            ],
            alternatives=[
                "Sort and compare.",
                "HashMap frequency when alphabet is large.",
            ],
            interview_context="Tests frequency counting and constant-space reasoning.",
        ),
        p(
            title="Group Anagrams",
            slug="group-anagrams",
            difficulty="Medium",
            statement="Given an array of strings strs, group the anagrams together and return grouped lists in any order.",
            input_format="strs: array of lowercase strings.",
            output_format="Return list of groups, where each group contains anagrams.",
            constraints=[
                "1 <= strs.length <= 10^4",
                "0 <= strs[i].length <= 100",
                "strs[i] consists of lowercase English letters.",
            ],
            examples=[
                {
                    "input": "strs=['eat','tea','tan','ate','nat','bat']",
                    "output": "[['eat','tea','ate'],['tan','nat'],['bat']]",
                    "explanation": "Words with same sorted signature share a group.",
                }
            ],
            edge_cases=[
                "Empty string entries.",
                "Single string.",
                "All strings unique.",
            ],
            brute_force="Compare each pair by sorted characters and merge groups manually (slow).",
            optimal="Use HashMap from canonical key to list. A canonical key can be sorted characters or frequency signature.",
            steps=[
                "For each word, build key (sorted string).",
                "Append word into map[key].",
                "Return all map values.",
            ],
            time_complexity="O(n * k log k) with sorted-key approach where k is average word length.",
            space_complexity="O(n * k) for map and groups.",
            java_code="""
import java.util.*;

class Solution {
    public List<List<String>> groupAnagrams(String[] strs) {
        Map<String, List<String>> groups = new HashMap<>();

        for (String word : strs) {
            char[] chars = word.toCharArray();
            Arrays.sort(chars);
            String key = new String(chars);
            groups.computeIfAbsent(key, k -> new ArrayList<>()).add(word);
        }

        return new ArrayList<>(groups.values());
    }
}
""",
            dry_run="'eat','tea','ate' -> sorted key 'aet' -> same bucket.",
            follow_ups=[
                "How can you avoid O(k log k) per word?",
                "How to keep insertion order stable per group?",
            ],
            alternatives=[
                "26-count signature key O(n*k).",
            ],
            interview_context="Checks ability to design canonical hash keys for grouping.",
        ),
        p(
            title="Longest Consecutive Sequence",
            slug="longest-consecutive-sequence",
            difficulty="Medium",
            statement="Given an unsorted array of integers nums, return the length of the longest consecutive elements sequence.",
            input_format="nums: integer array.",
            output_format="Return integer length.",
            constraints=[
                "0 <= nums.length <= 10^5",
                "-10^9 <= nums[i] <= 10^9",
            ],
            examples=[
                {"input": "nums=[100,4,200,1,3,2]", "output": "4", "explanation": "Sequence 1,2,3,4 has length 4."}
            ],
            edge_cases=[
                "Empty array.",
                "All duplicates.",
                "Negative ranges.",
            ],
            brute_force="Sort and scan consecutive run lengths. O(n log n).",
            optimal="Store values in HashSet. Start counting only from numbers with no predecessor (x-1 not in set).",
            steps=[
                "Insert all numbers into set.",
                "For each x, if x-1 exists skip because not sequence start.",
                "Grow x+1, x+2... and track max length.",
            ],
            time_complexity="O(n) expected.",
            space_complexity="O(n).",
            java_code="""
import java.util.*;

class Solution {
    public int longestConsecutive(int[] nums) {
        Set<Integer> set = new HashSet<>();
        for (int num : nums) {
            set.add(num);
        }

        int best = 0;
        for (int num : set) {
            if (set.contains(num - 1)) {
                continue;
            }
            int current = num;
            int len = 1;
            while (set.contains(current + 1)) {
                current++;
                len++;
            }
            best = Math.max(best, len);
        }
        return best;
    }
}
""",
            dry_run="Starts at 1 (since 0 absent), extends to 2,3,4 => length 4.",
            follow_ups=[
                "Can you return the actual sequence too?",
                "How would this work in streaming data?",
            ],
            alternatives=[
                "Sort then linear scan.",
                "Union-Find on values (heavier).",
            ],
            interview_context="Interviewers check if candidate can use hashing to break sorting lower bound for this objective.",
        ),
        p(
            title="Isomorphic Strings",
            slug="isomorphic-strings",
            difficulty="Easy",
            statement="Given strings s and t, determine if they are isomorphic. Characters in s can be replaced to get t with a one-to-one mapping.",
            input_format="s and t: strings of equal length.",
            output_format="Return true if one-to-one mapping exists, else false.",
            constraints=[
                "1 <= s.length <= 5 * 10^4",
                "s.length == t.length",
                "s and t consist of valid ASCII chars.",
            ],
            examples=[
                {"input": "s='egg', t='add'", "output": "true", "explanation": "e->a and g->d works consistently."},
                {"input": "s='foo', t='bar'", "output": "false", "explanation": "o maps to two different chars."},
            ],
            edge_cases=[
                "Repeated characters requiring same mapping.",
                "Two chars in s mapping to same char in t (invalid).",
                "Single-character strings.",
            ],
            brute_force="Build mapping and validate in two passes with nested checks.",
            optimal="Track both forward and reverse maps simultaneously to enforce bijection in one pass.",
            steps=[
                "Iterate index i.",
                "If existing mapping conflicts, return false.",
                "Insert new forward/reverse mapping otherwise.",
            ],
            time_complexity="O(n).",
            space_complexity="O(1) for bounded charset, else O(k).",
            java_code="""
import java.util.*;

class Solution {
    public boolean isIsomorphic(String s, String t) {
        Map<Character, Character> st = new HashMap<>();
        Map<Character, Character> ts = new HashMap<>();

        for (int i = 0; i < s.length(); i++) {
            char a = s.charAt(i);
            char b = t.charAt(i);

            if (st.containsKey(a) && st.get(a) != b) {
                return false;
            }
            if (ts.containsKey(b) && ts.get(b) != a) {
                return false;
            }
            st.put(a, b);
            ts.put(b, a);
        }

        return true;
    }
}
""",
            dry_run="s='paper', t='title': p->t, a->i, p->t valid, e->l, r->e => true.",
            follow_ups=[
                "Can you do this with arrays for ASCII only?",
                "How does this differ from anagram checks?",
            ],
            alternatives=[
                "Store first-seen indices arrays and compare patterns.",
            ],
            interview_context="Useful to test bijection constraints and map consistency.",
        ),
    ],
    "linked-lists": [
        p(
            title="Reverse Linked List",
            slug="reverse-linked-list",
            difficulty="Easy",
            statement="Given the head of a singly linked list, reverse the list and return the new head.",
            input_format="head: head node of singly linked list.",
            output_format="Return head of reversed linked list.",
            constraints=[
                "0 <= number of nodes <= 5000",
                "-5000 <= Node.val <= 5000",
            ],
            examples=[
                {"input": "head = [1,2,3,4,5]", "output": "[5,4,3,2,1]", "explanation": "Pointers are reversed in-place."}
            ],
            edge_cases=[
                "Empty list.",
                "Single node.",
                "Two nodes.",
            ],
            brute_force="Copy node values into array and rewrite nodes in reverse order.",
            optimal="Iteratively reverse next pointers using prev, curr, and next references.",
            steps=[
                "Initialize prev = null and curr = head.",
                "Save curr.next, point curr.next to prev.",
                "Advance prev and curr until curr becomes null.",
            ],
            time_complexity="O(n).",
            space_complexity="O(1).",
            java_code="""
class Solution {
    public ListNode reverseList(ListNode head) {
        ListNode prev = null;
        ListNode curr = head;

        while (curr != null) {
            ListNode next = curr.next;
            curr.next = prev;
            prev = curr;
            curr = next;
        }

        return prev;
    }
}
""",
            dry_run="1->2->3: after first step 1->null, then 2->1->null, then 3->2->1->null.",
            follow_ups=[
                "Can you do this recursively?",
                "How do you reverse nodes in groups of k?",
            ],
            alternatives=[
                "Recursive pointer reversal.",
                "Value swap using stack/array (not in-place).",
            ],
            interview_context="Tests pointer manipulation safety and in-place mutation ability.",
        ),
        p(
            title="Linked List Cycle",
            slug="linked-list-cycle",
            difficulty="Easy",
            statement="Given head of a linked list, determine if the linked list has a cycle.",
            input_format="head: list head pointer.",
            output_format="Return true if cycle exists, else false.",
            constraints=[
                "0 <= number of nodes <= 10^4",
                "-10^5 <= Node.val <= 10^5",
            ],
            examples=[
                {"input": "head=[3,2,0,-4], tail connects to index 1", "output": "true", "explanation": "Fast pointer eventually meets slow pointer."}
            ],
            edge_cases=[
                "Empty list.",
                "Single node without self-loop.",
                "Single node with self-loop.",
            ],
            brute_force="Track visited nodes in HashSet and detect repeats.",
            optimal="Floyd cycle detection uses slow (1x) and fast (2x) pointers; meeting implies cycle.",
            steps=[
                "Initialize slow and fast at head.",
                "Move slow by 1 and fast by 2 while fast and fast.next are not null.",
                "If slow == fast, cycle exists; otherwise no cycle.",
            ],
            time_complexity="O(n).",
            space_complexity="O(1).",
            java_code="""
class Solution {
    public boolean hasCycle(ListNode head) {
        ListNode slow = head;
        ListNode fast = head;

        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
            if (slow == fast) {
                return true;
            }
        }
        return false;
    }
}
""",
            dry_run="In cycle list, fast laps slow and both meet inside cycle.",
            follow_ups=[
                "How do you find the cycle start node?",
                "How do you compute cycle length?",
            ],
            alternatives=[
                "HashSet of visited nodes (O(n) space).",
            ],
            interview_context="Classic fast-slow pointer test for reasoning about pointer speeds.",
        ),
        p(
            title="Remove Nth Node From End of List",
            slug="remove-nth-node-from-end",
            difficulty="Medium",
            statement="Given head of a linked list, remove the nth node from the end and return its head.",
            input_format="head: list head, n: positive integer.",
            output_format="Return new head after deletion.",
            constraints=[
                "The number of nodes is sz.",
                "1 <= sz <= 30",
                "1 <= n <= sz",
            ],
            examples=[
                {"input": "head=[1,2,3,4,5], n=2", "output": "[1,2,3,5]", "explanation": "Node with value 4 is removed."}
            ],
            edge_cases=[
                "Removing head node.",
                "Single-node list.",
                "n equals list length.",
            ],
            brute_force="Compute length, then remove (len-n+1)-th from start in second pass.",
            optimal="Use dummy + two pointers. Move fast n steps first, then move both until fast reaches end.",
            steps=[
                "Create dummy before head.",
                "Advance fast by n nodes.",
                "Move fast and slow together until fast.next is null.",
                "Delete slow.next and return dummy.next.",
            ],
            time_complexity="O(n).",
            space_complexity="O(1).",
            java_code="""
class Solution {
    public ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode dummy = new ListNode(0, head);
        ListNode fast = dummy;
        ListNode slow = dummy;

        for (int i = 0; i < n; i++) {
            fast = fast.next;
        }

        while (fast.next != null) {
            fast = fast.next;
            slow = slow.next;
        }

        slow.next = slow.next.next;
        return dummy.next;
    }
}
""",
            dry_run="head=[1,2,3], n=3: fast advances to 3rd node, loop skips, slow at dummy -> remove head.",
            follow_ups=[
                "Can this be solved without dummy node?",
                "How do you remove multiple nodes from end list of k values?",
            ],
            alternatives=[
                "Two-pass length calculation approach.",
            ],
            interview_context="This checks off-by-one handling and pointer gap invariants.",
        ),
        p(
            title="Merge Two Sorted Lists",
            slug="merge-two-sorted-lists",
            difficulty="Easy",
            statement="Merge two sorted linked lists and return it as one sorted list.",
            input_format="list1 and list2 heads of sorted linked lists.",
            output_format="Return merged sorted list head.",
            constraints=[
                "0 <= number of nodes in each list <= 50",
                "-100 <= Node.val <= 100",
            ],
            examples=[
                {"input": "list1=[1,2,4], list2=[1,3,4]", "output": "[1,1,2,3,4,4]", "explanation": "Repeatedly choose smaller current node."}
            ],
            edge_cases=[
                "One list empty.",
                "Both lists empty.",
                "All values in one list smaller.",
            ],
            brute_force="Copy values to array, sort, build new list.",
            optimal="Use iterative merge with dummy head, reusing original nodes.",
            steps=[
                "Create dummy and tail pointers.",
                "Compare current nodes from both lists and append smaller.",
                "Attach remaining list once one pointer is null.",
            ],
            time_complexity="O(n + m).",
            space_complexity="O(1) extra.",
            java_code="""
class Solution {
    public ListNode mergeTwoLists(ListNode list1, ListNode list2) {
        ListNode dummy = new ListNode(0);
        ListNode tail = dummy;

        while (list1 != null && list2 != null) {
            if (list1.val <= list2.val) {
                tail.next = list1;
                list1 = list1.next;
            } else {
                tail.next = list2;
                list2 = list2.next;
            }
            tail = tail.next;
        }

        tail.next = (list1 != null) ? list1 : list2;
        return dummy.next;
    }
}
""",
            dry_run="1->2 and 1->3: pick first 1, then second 1, then 2, then 3.",
            follow_ups=[
                "How do you merge k sorted lists efficiently?",
                "Can you make it recursive?",
            ],
            alternatives=[
                "Recursive merge O(n+m) with call stack.",
            ],
            interview_context="Foundational linked-list merge used in merge sort and divide-and-conquer list problems.",
        ),
        p(
            title="LRU Cache",
            slug="lru-cache",
            difficulty="Medium",
            statement="Design a data structure that follows Least Recently Used cache policy with get and put operations in O(1) average time.",
            input_format="Initialize with capacity. Operations: get(key), put(key, value).",
            output_format="get returns value if key exists else -1; put returns void.",
            constraints=[
                "1 <= capacity <= 3000",
                "0 <= key <= 10^4",
                "0 <= value <= 10^5",
                "At most 2 * 10^5 operations",
            ],
            examples=[
                {
                    "input": "capacity=2; put(1,1); put(2,2); get(1); put(3,3); get(2)",
                    "output": "1, -1",
                    "explanation": "Key 2 is least recently used and evicted.",
                }
            ],
            edge_cases=[
                "Updating existing key should move it to most recent.",
                "Capacity = 1.",
                "Frequent get operations change recency order.",
            ],
            brute_force="Maintain list order and scan on each access/update. This becomes O(n) per operation.",
            optimal="Use HashMap for O(1) key lookup and doubly linked list for O(1) remove/add to track recency order.",
            steps=[
                "Map key -> node in doubly linked list.",
                "On get, move node to front and return value.",
                "On put, update existing or insert new at front.",
                "If capacity exceeded, remove node at tail and delete from map.",
            ],
            time_complexity="O(1) average for get/put.",
            space_complexity="O(capacity).",
            java_code="""
import java.util.*;

class LRUCache {
    private static class Node {
        int key;
        int value;
        Node prev;
        Node next;

        Node(int key, int value) {
            this.key = key;
            this.value = value;
        }
    }

    private final int capacity;
    private final Map<Integer, Node> map;
    private final Node head;
    private final Node tail;

    public LRUCache(int capacity) {
        this.capacity = capacity;
        this.map = new HashMap<>();
        this.head = new Node(0, 0);
        this.tail = new Node(0, 0);
        head.next = tail;
        tail.prev = head;
    }

    public int get(int key) {
        Node node = map.get(key);
        if (node == null) {
            return -1;
        }
        moveToFront(node);
        return node.value;
    }

    public void put(int key, int value) {
        Node node = map.get(key);
        if (node != null) {
            node.value = value;
            moveToFront(node);
            return;
        }

        Node fresh = new Node(key, value);
        map.put(key, fresh);
        addAfterHead(fresh);

        if (map.size() > capacity) {
            Node lru = tail.prev;
            removeNode(lru);
            map.remove(lru.key);
        }
    }

    private void moveToFront(Node node) {
        removeNode(node);
        addAfterHead(node);
    }

    private void addAfterHead(Node node) {
        node.next = head.next;
        node.prev = head;
        head.next.prev = node;
        head.next = node;
    }

    private void removeNode(Node node) {
        node.prev.next = node.next;
        node.next.prev = node.prev;
    }
}
""",
            dry_run="capacity=2: [1] -> [2,1], get(1) => [1,2], put(3) => evict 2, list [3,1].",
            follow_ups=[
                "How to make it thread-safe?",
                "What is difference between LRU and LFU cache?",
            ],
            alternatives=[
                "Java LinkedHashMap in access-order mode.",
            ],
            interview_context="Common design-interview problem combining hashmap + linked list for strict O(1) operations.",
        ),
    ],
    "stacks-queues": [
        p(
            title="Valid Parentheses",
            slug="valid-parentheses",
            difficulty="Easy",
            statement="Given a string s containing only '(', ')', '{', '}', '[' and ']', determine if input string is valid.",
            input_format="s: string of bracket characters.",
            output_format="Return true if all brackets are balanced and correctly nested, else false.",
            constraints=[
                "1 <= s.length <= 10^4",
                "s consists only of ()[]{}.",
            ],
            examples=[
                {"input": "s='()[]{}'", "output": "true", "explanation": "All pairs close correctly in order."},
                {"input": "s='(]'", "output": "false", "explanation": "Mismatched closing bracket."},
            ],
            edge_cases=[
                "Odd length string cannot be valid.",
                "Closing bracket appears before opening.",
                "Nested and adjacent mixes.",
            ],
            brute_force="Repeatedly remove matching pairs from string until no change. Inefficient due to repeated scans.",
            optimal="Use stack of opening brackets. For each closing bracket, top must match corresponding opening.",
            steps=[
                "Traverse each character.",
                "Push opening brackets.",
                "For closing bracket, verify stack not empty and pair matches.",
                "String is valid only if stack is empty at end.",
            ],
            time_complexity="O(n).",
            space_complexity="O(n) in worst case.",
            java_code="""
import java.util.*;

class Solution {
    public boolean isValid(String s) {
        Deque<Character> stack = new ArrayDeque<>();
        for (char c : s.toCharArray()) {
            if (c == '(' || c == '[' || c == '{') {
                stack.push(c);
            } else {
                if (stack.isEmpty()) {
                    return false;
                }
                char open = stack.pop();
                if ((c == ')' && open != '(')
                    || (c == ']' && open != '[')
                    || (c == '}' && open != '{')) {
                    return false;
                }
            }
        }
        return stack.isEmpty();
    }
}
""",
            dry_run="s='([{}])': push (, [, { then pop for }, ], ) in reverse order -> valid.",
            follow_ups=[
                "How to validate multiple bracket types with custom mapping?",
                "How to return index of first invalid char?",
            ],
            alternatives=[
                "String replacement loop (not efficient).",
            ],
            interview_context="Standard stack problem for balanced parsing correctness.",
        ),
        p(
            title="Min Stack",
            slug="min-stack",
            difficulty="Medium",
            statement="Design a stack that supports push, pop, top, and retrieving minimum element in constant time.",
            input_format="Operations sequence: push(x), pop(), top(), getMin().",
            output_format="top returns latest value, getMin returns minimum so far.",
            constraints=[
                "Values fit in 32-bit signed integer.",
                "At most 3 * 10^4 operations.",
            ],
            examples=[
                {
                    "input": "push(-2), push(0), push(-3), getMin(), pop(), top(), getMin()",
                    "output": "-3, 0, -2",
                    "explanation": "Auxiliary min tracking keeps O(1) min query.",
                }
            ],
            edge_cases=[
                "Duplicate minimum values.",
                "Push increasing or decreasing only.",
                "Operations near empty stack boundaries.",
            ],
            brute_force="Store values in list and scan list for each getMin in O(n).",
            optimal="Store pairs (value, currentMin) on stack so each node knows minimum up to that point.",
            steps=[
                "On push, minSoFar = min(x, currentMin).",
                "Store both x and minSoFar.",
                "top/pop/getMin operate on stack top in O(1).",
            ],
            time_complexity="All operations O(1).",
            space_complexity="O(n).",
            java_code="""
import java.util.*;

class MinStack {
    private static class Node {
        int value;
        int minSoFar;

        Node(int value, int minSoFar) {
            this.value = value;
            this.minSoFar = minSoFar;
        }
    }

    private final Deque<Node> stack = new ArrayDeque<>();

    public void push(int val) {
        int minSoFar = stack.isEmpty() ? val : Math.min(val, stack.peek().minSoFar);
        stack.push(new Node(val, minSoFar));
    }

    public void pop() {
        stack.pop();
    }

    public int top() {
        return stack.peek().value;
    }

    public int getMin() {
        return stack.peek().minSoFar;
    }
}
""",
            dry_run="push 3(min3), push 1(min1), push 2(min1). getMin=1, pop 2, getMin still 1.",
            follow_ups=[
                "How to support getMax as well?",
                "Can this be done without pair objects?",
            ],
            alternatives=[
                "Two stacks: one main, one min stack.",
            ],
            interview_context="Design question for data-structure augmentation under strict complexity constraints.",
        ),
        p(
            title="Evaluate Reverse Polish Notation",
            slug="evaluate-reverse-polish-notation",
            difficulty="Medium",
            statement="Evaluate arithmetic expression in Reverse Polish Notation. Valid operators are +, -, *, /.",
            input_format="tokens: string array representing RPN expression.",
            output_format="Return integer evaluation result with truncation toward zero for division.",
            constraints=[
                "1 <= tokens.length <= 10^4",
                "Each token is integer or one of + - * /.",
                "Expression is always valid.",
            ],
            examples=[
                {"input": "tokens=['2','1','+','3','*']", "output": "9", "explanation": "(2+1)*3 = 9."}
            ],
            edge_cases=[
                "Negative values.",
                "Division truncation toward zero.",
                "Single token expression.",
            ],
            brute_force="Convert RPN to infix and then evaluate with parser. Overkill and error-prone.",
            optimal="Use stack. Push numbers; on operator, pop two operands, apply op, push result.",
            steps=[
                "Traverse tokens left to right.",
                "If token is number, push integer.",
                "If operator, pop b then a, compute a op b, and push result.",
            ],
            time_complexity="O(n).",
            space_complexity="O(n).",
            java_code="""
import java.util.*;

class Solution {
    public int evalRPN(String[] tokens) {
        Deque<Integer> stack = new ArrayDeque<>();

        for (String token : tokens) {
            switch (token) {
                case "+": {
                    int b = stack.pop();
                    int a = stack.pop();
                    stack.push(a + b);
                    break;
                }
                case "-": {
                    int b = stack.pop();
                    int a = stack.pop();
                    stack.push(a - b);
                    break;
                }
                case "*": {
                    int b = stack.pop();
                    int a = stack.pop();
                    stack.push(a * b);
                    break;
                }
                case "/": {
                    int b = stack.pop();
                    int a = stack.pop();
                    stack.push(a / b);
                    break;
                }
                default:
                    stack.push(Integer.parseInt(token));
            }
        }

        return stack.pop();
    }
}
""",
            dry_run="2 1 + 3 * -> push2,push1, + =>3, push3, * =>9.",
            follow_ups=[
                "How to support exponent operator?",
                "How to convert infix to postfix first?",
            ],
            alternatives=[
                "Recursive evaluation from end using call stack.",
            ],
            interview_context="Tests stack simulation and operator operand order correctness.",
        ),
        p(
            title="Daily Temperatures",
            slug="daily-temperatures",
            difficulty="Medium",
            statement="Given daily temperatures, return an array where answer[i] is number of days to wait for a warmer temperature. If none, answer[i]=0.",
            input_format="temperatures: integer array.",
            output_format="Return integer array answer of same length.",
            constraints=[
                "1 <= temperatures.length <= 10^5",
                "30 <= temperatures[i] <= 100",
            ],
            examples=[
                {
                    "input": "temperatures=[73,74,75,71,69,72,76,73]",
                    "output": "[1,1,4,2,1,1,0,0]",
                    "explanation": "Monotonic stack stores unresolved indices.",
                }
            ],
            edge_cases=[
                "Strictly decreasing temperatures -> all zeros.",
                "Strictly increasing temperatures.",
                "Single day input.",
            ],
            brute_force="For each day i, scan forward j>i until warmer day found. O(n^2).",
            optimal="Use decreasing monotonic stack of indices. When current temperature is warmer than stack top, resolve waits.",
            steps=[
                "Initialize answer array and empty stack of indices.",
                "For each index i, pop while temp[i] > temp[stackTop] and fill answer for popped indices.",
                "Push i onto stack.",
            ],
            time_complexity="O(n).",
            space_complexity="O(n).",
            java_code="""
import java.util.*;

class Solution {
    public int[] dailyTemperatures(int[] temperatures) {
        int n = temperatures.length;
        int[] answer = new int[n];
        Deque<Integer> stack = new ArrayDeque<>();

        for (int i = 0; i < n; i++) {
            while (!stack.isEmpty() && temperatures[i] > temperatures[stack.peek()]) {
                int prev = stack.pop();
                answer[prev] = i - prev;
            }
            stack.push(i);
        }

        return answer;
    }
}
""",
            dry_run="At temp 76 (index 6), pop unresolved 72,75 etc and fill waits.",
            follow_ups=[
                "How to find previous warmer day instead of next?",
                "How to adapt for stock span problem?",
            ],
            alternatives=[
                "Backward DP jump pointers.",
            ],
            interview_context="Canonical monotonic-stack interview pattern.",
        ),
        p(
            title="Sliding Window Maximum",
            slug="sliding-window-maximum",
            difficulty="Hard",
            statement="Given an array nums and window size k, return max value in each sliding window of size k.",
            input_format="nums: integer array, k: positive integer.",
            output_format="Return integer array of window maxima.",
            constraints=[
                "1 <= nums.length <= 10^5",
                "-10^4 <= nums[i] <= 10^4",
                "1 <= k <= nums.length",
            ],
            examples=[
                {
                    "input": "nums=[1,3,-1,-3,5,3,6,7], k=3",
                    "output": "[3,3,5,5,6,7]",
                    "explanation": "Deque keeps candidate indices in decreasing value order.",
                }
            ],
            edge_cases=[
                "k = 1 returns original array.",
                "k = n returns single maximum.",
                "Duplicate values.",
            ],
            brute_force="Compute max for each window by scanning k elements => O(nk).",
            optimal="Use deque of indices maintaining decreasing values. Front is current window maximum.",
            steps=[
                "Remove indices outside current window from deque front.",
                "Pop from back while current value >= deque back value.",
                "Push current index; once i >= k-1, record nums[deque front].",
            ],
            time_complexity="O(n).",
            space_complexity="O(k).",
            java_code="""
import java.util.*;

class Solution {
    public int[] maxSlidingWindow(int[] nums, int k) {
        int n = nums.length;
        int[] ans = new int[n - k + 1];
        Deque<Integer> dq = new ArrayDeque<>();

        for (int i = 0; i < n; i++) {
            while (!dq.isEmpty() && dq.peekFirst() <= i - k) {
                dq.pollFirst();
            }
            while (!dq.isEmpty() && nums[dq.peekLast()] <= nums[i]) {
                dq.pollLast();
            }
            dq.offerLast(i);

            if (i >= k - 1) {
                ans[i - k + 1] = nums[dq.peekFirst()];
            }
        }

        return ans;
    }
}
""",
            dry_run="For first window [1,3,-1], deque stores indices [1,2], max=3. As window shifts, outdated indices are removed.",
            follow_ups=[
                "How to support dynamic window sizes?",
                "Can you do this with priority queue and lazy deletion?",
            ],
            alternatives=[
                "Max-heap with stale index removal O(n log k).",
                "Block decomposition (left/right maxima arrays).",
            ],
            interview_context="Advanced queue problem that distinguishes strong candidates in linear-time optimization.",
        ),
    ],
    "trees": [
        p(
            title="Maximum Depth of Binary Tree",
            slug="maximum-depth-binary-tree",
            difficulty="Easy",
            statement="Given root of a binary tree, return its maximum depth.",
            input_format="root: binary tree root node.",
            output_format="Return integer depth (number of nodes along longest root-to-leaf path).",
            constraints=[
                "0 <= number of nodes <= 10^4",
                "-100 <= Node.val <= 100",
            ],
            examples=[
                {"input": "root=[3,9,20,null,null,15,7]", "output": "3", "explanation": "Longest path has 3 nodes."}
            ],
            edge_cases=[
                "Empty tree depth is 0.",
                "Single node depth is 1.",
                "Skewed tree.",
            ],
            brute_force="Traverse all root-to-leaf paths and track maximum length.",
            optimal="DFS recursion: depth(node)=1+max(depth(left), depth(right)).",
            steps=[
                "If node is null return 0.",
                "Recursively compute left and right depths.",
                "Return 1 + max(left, right).",
            ],
            time_complexity="O(n).",
            space_complexity="O(h) recursion stack, h=tree height.",
            java_code="""
class Solution {
    public int maxDepth(TreeNode root) {
        if (root == null) {
            return 0;
        }
        return 1 + Math.max(maxDepth(root.left), maxDepth(root.right));
    }
}
""",
            dry_run="For node 3: left depth=1 (node9), right depth=2 (20->15/7), so total=3.",
            follow_ups=[
                "Can you solve iteratively with BFS?",
                "How to compute minimum depth instead?",
            ],
            alternatives=[
                "Level-order BFS counting levels.",
            ],
            interview_context="Simple recursion baseline for tree traversal fluency.",
        ),
        p(
            title="Binary Tree Level Order Traversal",
            slug="binary-tree-level-order",
            difficulty="Medium",
            statement="Given root of binary tree, return level order traversal of node values.",
            input_format="root: binary tree root node.",
            output_format="Return list of levels, each level is list of integers.",
            constraints=[
                "0 <= number of nodes <= 2000",
                "-1000 <= Node.val <= 1000",
            ],
            examples=[
                {
                    "input": "root=[3,9,20,null,null,15,7]",
                    "output": "[[3],[9,20],[15,7]]",
                    "explanation": "BFS processes one level at a time.",
                }
            ],
            edge_cases=[
                "Empty tree returns empty list.",
                "Skewed tree gives one value per level.",
            ],
            brute_force="DFS and track depth in map/list; then collect by depth.",
            optimal="Use BFS queue. For each loop, process current queue size as one level.",
            steps=[
                "If root null return empty.",
                "Push root to queue.",
                "For each level-size batch, pop nodes, collect values, push children.",
            ],
            time_complexity="O(n).",
            space_complexity="O(w) where w is max width of tree.",
            java_code="""
import java.util.*;

class Solution {
    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> result = new ArrayList<>();
        if (root == null) {
            return result;
        }

        Queue<TreeNode> queue = new ArrayDeque<>();
        queue.offer(root);

        while (!queue.isEmpty()) {
            int size = queue.size();
            List<Integer> level = new ArrayList<>(size);
            for (int i = 0; i < size; i++) {
                TreeNode node = queue.poll();
                level.add(node.val);
                if (node.left != null) {
                    queue.offer(node.left);
                }
                if (node.right != null) {
                    queue.offer(node.right);
                }
            }
            result.add(level);
        }

        return result;
    }
}
""",
            dry_run="Queue: [3] -> level[3], enqueue 9,20 -> next level [9,20].",
            follow_ups=[
                "How to return zigzag level order?",
                "How to compute right side view?",
            ],
            alternatives=[
                "DFS preorder with depth-indexed lists.",
            ],
            interview_context="Core BFS tree traversal used in many system modeling questions.",
        ),
        p(
            title="Validate Binary Search Tree",
            slug="validate-bst",
            difficulty="Medium",
            statement="Given root of a binary tree, determine if it is a valid BST.",
            input_format="root: binary tree root node.",
            output_format="Return true if tree satisfies BST property strictly.",
            constraints=[
                "1 <= number of nodes <= 10^4",
                "-2^31 <= Node.val <= 2^31 - 1",
            ],
            examples=[
                {"input": "root=[2,1,3]", "output": "true", "explanation": "Left < root < right holds for all nodes."},
                {"input": "root=[5,1,4,null,null,3,6]", "output": "false", "explanation": "Node 3 in right subtree violates global bound."},
            ],
            edge_cases=[
                "Duplicate values should fail for strict BST.",
                "Deep violation not near root.",
                "Integer boundary values.",
            ],
            brute_force="For each node, scan entire left and right subtree for violations. O(n^2).",
            optimal="Carry valid min/max bounds during DFS; each node must be in (low, high).",
            steps=[
                "Start with bounds (-inf, +inf).",
                "At node, if value <= low or >= high return false.",
                "Recurse left with high=node.val and right with low=node.val.",
            ],
            time_complexity="O(n).",
            space_complexity="O(h).",
            java_code="""
class Solution {
    public boolean isValidBST(TreeNode root) {
        return dfs(root, Long.MIN_VALUE, Long.MAX_VALUE);
    }

    private boolean dfs(TreeNode node, long low, long high) {
        if (node == null) {
            return true;
        }
        if (node.val <= low || node.val >= high) {
            return false;
        }
        return dfs(node.left, low, node.val) && dfs(node.right, node.val, high);
    }
}
""",
            dry_run="Node 5 right child 4 violates low bound 5 -> false.",
            follow_ups=[
                "Can you validate using inorder traversal?",
                "How to fix a BST with two swapped nodes?",
            ],
            alternatives=[
                "Inorder traversal must be strictly increasing.",
            ],
            interview_context="Tests understanding of global constraints vs local parent-child checks.",
        ),
        p(
            title="Lowest Common Ancestor of a Binary Tree",
            slug="lowest-common-ancestor-bt",
            difficulty="Medium",
            statement="Given a binary tree and two nodes p and q, return their lowest common ancestor.",
            input_format="root: binary tree root, p and q: references to target nodes.",
            output_format="Return TreeNode pointer representing LCA.",
            constraints=[
                "2 <= number of nodes <= 10^5",
                "All Node.val are unique",
                "p and q exist in tree",
            ],
            examples=[
                {"input": "root=[3,5,1,6,2,0,8,null,null,7,4], p=5, q=1", "output": "3", "explanation": "3 is the first node containing both in different subtrees."}
            ],
            edge_cases=[
                "One node is ancestor of the other.",
                "p and q in same subtree.",
                "Highly unbalanced tree.",
            ],
            brute_force="Store parent pointers then build ancestor set for one node and walk other upward.",
            optimal="DFS returns non-null when subtree contains p or q. If both sides return non-null, current node is LCA.",
            steps=[
                "Base: null/p/q -> return node itself.",
                "Recurse left and right.",
                "If both non-null, return current; otherwise return non-null child.",
            ],
            time_complexity="O(n).",
            space_complexity="O(h).",
            java_code="""
class Solution {
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null || root == p || root == q) {
            return root;
        }

        TreeNode left = lowestCommonAncestor(root.left, p, q);
        TreeNode right = lowestCommonAncestor(root.right, p, q);

        if (left != null && right != null) {
            return root;
        }
        return left != null ? left : right;
    }
}
""",
            dry_run="At node 3, left returns 5 and right returns 1, so LCA is 3.",
            follow_ups=[
                "How to solve when tree has parent pointers?",
                "How does solution change for BST?",
            ],
            alternatives=[
                "Parent map + visited ancestor set.",
            ],
            interview_context="Evaluates recursive decomposition and merge logic on trees.",
        ),
        p(
            title="Binary Tree Maximum Path Sum",
            slug="binary-tree-maximum-path-sum",
            difficulty="Hard",
            statement="Given a non-empty binary tree, return the maximum path sum. A path may start and end at any nodes, but must go along parent-child edges.",
            input_format="root: binary tree root.",
            output_format="Return maximum path sum as integer.",
            constraints=[
                "1 <= number of nodes <= 3 * 10^4",
                "-1000 <= Node.val <= 1000",
            ],
            examples=[
                {"input": "root=[-10,9,20,null,null,15,7]", "output": "42", "explanation": "Best path is 15 -> 20 -> 7."}
            ],
            edge_cases=[
                "All negative nodes => best single node.",
                "Best path passes through root or not.",
                "Skewed tree.",
            ],
            brute_force="Enumerate all possible paths between node pairs and compute sum, which is expensive.",
            optimal="Tree DP: each node contributes max one-side gain upward; update global answer with leftGain + node + rightGain.",
            steps=[
                "DFS returns max gain from node to parent (single branch).",
                "Clamp negative child gains to 0.",
                "Update global max with split path through current node.",
            ],
            time_complexity="O(n).",
            space_complexity="O(h).",
            java_code="""
class Solution {
    private int best = Integer.MIN_VALUE;

    public int maxPathSum(TreeNode root) {
        gain(root);
        return best;
    }

    private int gain(TreeNode node) {
        if (node == null) {
            return 0;
        }

        int left = Math.max(0, gain(node.left));
        int right = Math.max(0, gain(node.right));

        best = Math.max(best, node.val + left + right);

        return node.val + Math.max(left, right);
    }
}
""",
            dry_run="At node 20: left gain=15, right gain=7, path sum=42 updates global max.",
            follow_ups=[
                "How to also return the path nodes?",
                "How does this compare with tree diameter logic?",
            ],
            alternatives=[
                "Postorder with explicit stack simulating recursion.",
            ],
            interview_context="Hard tree DP question frequently used to test local/global state separation.",
        ),
    ],
    "heaps": [
        p(
            title="Kth Largest Element in a Stream",
            slug="kth-largest-stream",
            difficulty="Easy",
            statement="Design a class to find the kth largest element in a stream of numbers.",
            input_format="Constructor input: k and initial nums. Method add(val) inserts value.",
            output_format="add returns current kth largest element.",
            constraints=[
                "1 <= k <= 10^4",
                "-10^4 <= nums[i], val <= 10^4",
                "At most 10^4 calls to add",
            ],
            examples=[
                {
                    "input": "k=3, nums=[4,5,8,2], add(3), add(5), add(10)",
                    "output": "4,5,5",
                    "explanation": "Maintain min-heap of k largest seen values.",
                }
            ],
            edge_cases=[
                "Initial nums size smaller than k.",
                "Duplicate values.",
                "Negative values.",
            ],
            brute_force="After each add, sort all numbers and pick kth largest. Too slow for stream.",
            optimal="Maintain min-heap of size k. Heap top is kth largest.",
            steps=[
                "Push initial numbers while trimming heap to size k.",
                "On add, push val and pop if heap size > k.",
                "Return heap.peek().",
            ],
            time_complexity="O(log k) per add.",
            space_complexity="O(k).",
            java_code="""
import java.util.*;

class KthLargest {
    private final int k;
    private final PriorityQueue<Integer> minHeap = new PriorityQueue<>();

    public KthLargest(int k, int[] nums) {
        this.k = k;
        for (int num : nums) {
            add(num);
        }
    }

    public int add(int val) {
        minHeap.offer(val);
        if (minHeap.size() > k) {
            minHeap.poll();
        }
        return minHeap.peek();
    }
}
""",
            dry_run="k=3, heap keeps [4,5,8]. add(10) -> heap [5,8,10], kth largest=5.",
            follow_ups=[
                "How to get median in a stream instead of kth largest?",
                "How to support dynamic k changes?",
            ],
            alternatives=[
                "Balanced BST / TreeMap with counts.",
            ],
            interview_context="Common streaming heap design pattern for real-time ranking queries.",
        ),
        p(
            title="Top K Frequent Elements",
            slug="top-k-frequent-elements-heap",
            difficulty="Medium",
            statement="Given an integer array nums and integer k, return the k most frequent elements.",
            input_format="nums: integer array, k: integer.",
            output_format="Return integer array/list containing k frequent elements.",
            constraints=[
                "1 <= nums.length <= 10^5",
                "-10^4 <= nums[i] <= 10^4",
                "k is in [1, number of unique elements]",
            ],
            examples=[
                {"input": "nums=[1,1,1,2,2,3], k=2", "output": "[1,2]", "explanation": "1 appears 3 times, 2 appears 2 times."}
            ],
            edge_cases=[
                "k equals number of unique elements.",
                "Single unique value.",
                "Frequency ties.",
            ],
            brute_force="Count frequencies, sort all unique elements by frequency descending.",
            optimal="Use frequency map + min-heap of size k over entries.",
            steps=[
                "Build frequency map.",
                "Push each (value,freq) into min-heap by freq and trim to size k.",
                "Extract heap elements as answer.",
            ],
            time_complexity="O(n + u log k), u = unique values.",
            space_complexity="O(u + k).",
            java_code="""
import java.util.*;

class Solution {
    public int[] topKFrequent(int[] nums, int k) {
        Map<Integer, Integer> freq = new HashMap<>();
        for (int num : nums) {
            freq.put(num, freq.getOrDefault(num, 0) + 1);
        }

        PriorityQueue<int[]> minHeap = new PriorityQueue<>(Comparator.comparingInt(a -> a[1]));
        for (Map.Entry<Integer, Integer> entry : freq.entrySet()) {
            minHeap.offer(new int[] {entry.getKey(), entry.getValue()});
            if (minHeap.size() > k) {
                minHeap.poll();
            }
        }

        int[] ans = new int[k];
        for (int i = k - 1; i >= 0; i--) {
            ans[i] = minHeap.poll()[0];
        }
        return ans;
    }
}
""",
            dry_run="freq: {1:3,2:2,3:1}, heap keeps top 2 by freq -> [2,1].",
            follow_ups=[
                "How to solve in O(n) using bucket sort?",
                "How to handle streaming updates?",
            ],
            alternatives=[
                "Bucket sort by frequency O(n).",
            ],
            interview_context="Combines hashing and bounded heap optimization, common in data-platform interviews.",
        ),
        p(
            title="Find Median from Data Stream",
            slug="find-median-from-data-stream",
            difficulty="Hard",
            statement="Design a data structure that supports adding numbers and finding median efficiently.",
            input_format="Operations: addNum(num), findMedian().",
            output_format="findMedian returns middle value (or average of two middles).",
            constraints=[
                "-10^5 <= num <= 10^5",
                "At most 5 * 10^4 operations",
            ],
            examples=[
                {
                    "input": "addNum(1), addNum(2), findMedian(), addNum(3), findMedian()",
                    "output": "1.5, 2.0",
                    "explanation": "Two heaps keep lower and upper halves balanced.",
                }
            ],
            edge_cases=[
                "Even and odd counts alternate.",
                "Negative and duplicate values.",
                "Large operation count.",
            ],
            brute_force="Maintain sorted list and insert in sorted position each time; O(n) insertion.",
            optimal="Use max-heap for lower half and min-heap for upper half, rebalancing sizes.",
            steps=[
                "Insert into max-heap first, then move top to min-heap.",
                "If min-heap becomes larger, move one back to max-heap.",
                "Median is maxHeap top (odd) or average of tops (even).",
            ],
            time_complexity="addNum O(log n), findMedian O(1).",
            space_complexity="O(n).",
            java_code="""
import java.util.*;

class MedianFinder {
    private final PriorityQueue<Integer> lower = new PriorityQueue<>(Collections.reverseOrder());
    private final PriorityQueue<Integer> upper = new PriorityQueue<>();

    public void addNum(int num) {
        lower.offer(num);
        upper.offer(lower.poll());
        if (upper.size() > lower.size()) {
            lower.offer(upper.poll());
        }
    }

    public double findMedian() {
        if (lower.size() > upper.size()) {
            return lower.peek();
        }
        return (lower.peek() + upper.peek()) / 2.0;
    }
}
""",
            dry_run="Add 1 => lower[1]. Add 2 => lower[1],upper[2]. Median=(1+2)/2.",
            follow_ups=[
                "How to support deletion as well?",
                "How to compute percentile instead of median?",
            ],
            alternatives=[
                "Order-statistics tree.",
            ],
            interview_context="Streaming statistics problem testing balanced data structure design.",
        ),
        p(
            title="Merge k Sorted Lists",
            slug="merge-k-sorted-lists",
            difficulty="Hard",
            statement="Given an array of k sorted linked-lists, merge all the linked-lists into one sorted linked-list and return it.",
            input_format="lists: array of ListNode heads.",
            output_format="Return merged sorted list head.",
            constraints=[
                "k == lists.length",
                "0 <= k <= 10^4",
                "0 <= total nodes <= 10^4",
                "-10^4 <= Node.val <= 10^4",
            ],
            examples=[
                {
                    "input": "lists=[[1,4,5],[1,3,4],[2,6]]",
                    "output": "[1,1,2,3,4,4,5,6]",
                    "explanation": "Heap always extracts smallest current node among k heads.",
                }
            ],
            edge_cases=[
                "Empty list array.",
                "Some lists empty.",
                "All values identical.",
            ],
            brute_force="Collect all values, sort them, create new list. O(N log N).",
            optimal="Use min-heap of current list heads. Pop smallest node and push its next.",
            steps=[
                "Initialize min-heap with non-null heads.",
                "Pop smallest node, append to output tail.",
                "If popped node has next, push next to heap.",
            ],
            time_complexity="O(N log k), N total nodes.",
            space_complexity="O(k).",
            java_code="""
import java.util.*;

class Solution {
    public ListNode mergeKLists(ListNode[] lists) {
        PriorityQueue<ListNode> minHeap = new PriorityQueue<>(Comparator.comparingInt(a -> a.val));
        for (ListNode head : lists) {
            if (head != null) {
                minHeap.offer(head);
            }
        }

        ListNode dummy = new ListNode(0);
        ListNode tail = dummy;

        while (!minHeap.isEmpty()) {
            ListNode node = minHeap.poll();
            tail.next = node;
            tail = tail.next;
            if (node.next != null) {
                minHeap.offer(node.next);
            }
        }

        return dummy.next;
    }
}
""",
            dry_run="Heap starts with 1,1,2. Repeatedly pop smallest and push next nodes to build sorted chain.",
            follow_ups=[
                "Can you solve using divide-and-conquer merging?",
                "Which is better when k is very large but each list tiny?",
            ],
            alternatives=[
                "Pairwise merge lists divide-and-conquer O(N log k).",
            ],
            interview_context="High-frequency hard problem on heap-based multiway merge.",
        ),
        p(
            title="Meeting Rooms II",
            slug="meeting-rooms-ii",
            difficulty="Medium",
            statement="Given intervals of meeting time, find minimum number of conference rooms required.",
            input_format="intervals: 2D array where intervals[i] = [start, end].",
            output_format="Return minimum number of rooms as integer.",
            constraints=[
                "1 <= intervals.length <= 10^4",
                "0 <= start < end <= 10^6",
            ],
            examples=[
                {"input": "intervals=[[0,30],[5,10],[15,20]]", "output": "2", "explanation": "Two overlapping meetings require two rooms."}
            ],
            edge_cases=[
                "Non-overlapping meetings -> 1 room.",
                "Fully overlapping meetings -> n rooms.",
                "Meetings touching at boundaries.",
            ],
            brute_force="Track overlaps by checking every pair or timeline simulation with large arrays.",
            optimal="Sort by start time and use min-heap of end times. Reuse room if earliest ending meeting finishes before next starts.",
            steps=[
                "Sort meetings by start.",
                "For each meeting, if min end <= start, pop heap (reuse room).",
                "Push current end; heap size is current rooms used.",
            ],
            time_complexity="O(n log n).",
            space_complexity="O(n).",
            java_code="""
import java.util.*;

class Solution {
    public int minMeetingRooms(int[][] intervals) {
        Arrays.sort(intervals, Comparator.comparingInt(a -> a[0]));
        PriorityQueue<Integer> minEnd = new PriorityQueue<>();

        for (int[] interval : intervals) {
            if (!minEnd.isEmpty() && minEnd.peek() <= interval[0]) {
                minEnd.poll();
            }
            minEnd.offer(interval[1]);
        }

        return minEnd.size();
    }
}
""",
            dry_run="Sorted: [0,30],[5,10],[15,20]. Heap after first two => [10,30], third reuses 10-room.",
            follow_ups=[
                "Can you output actual room assignment?",
                "How does sweep-line with starts/ends compare?",
            ],
            alternatives=[
                "Two sorted arrays starts/ends with two pointers.",
            ],
            interview_context="Scheduling + heap reasoning appears in backend and infra interview scenarios.",
        ),
    ],
    "trie": [
        p(
            title="Implement Trie (Prefix Tree)",
            slug="implement-trie",
            difficulty="Medium",
            statement="Implement a trie with insert(word), search(word), and startsWith(prefix).",
            input_format="Operations with lowercase word/prefix strings.",
            output_format="search and startsWith return boolean.",
            constraints=[
                "1 <= word.length, prefix.length <= 2000",
                "Words contain lowercase English letters.",
                "At most 3 * 10^4 operations.",
            ],
            examples=[
                {
                    "input": "insert('apple'), search('apple'), search('app'), startsWith('app'), insert('app'), search('app')",
                    "output": "true, false, true, true",
                    "explanation": "Prefix and full-word checks differ until 'app' is inserted.",
                }
            ],
            edge_cases=[
                "Insert same word multiple times.",
                "Prefix equals full word.",
                "Single-character words.",
            ],
            brute_force="Store all strings in list and linearly scan for search/prefix checks.",
            optimal="Trie stores characters along edges; each node marks end-of-word for O(L) operations.",
            steps=[
                "Traverse/create nodes for each char in insert.",
                "For search, traverse all chars and check end flag.",
                "For startsWith, traverse prefix only.",
            ],
            time_complexity="O(L) per operation where L is word length.",
            space_complexity="O(total characters inserted).",
            java_code="""
class Trie {
    private static class Node {
        Node[] child = new Node[26];
        boolean end;
    }

    private final Node root = new Node();

    public void insert(String word) {
        Node curr = root;
        for (char c : word.toCharArray()) {
            int i = c - 'a';
            if (curr.child[i] == null) {
                curr.child[i] = new Node();
            }
            curr = curr.child[i];
        }
        curr.end = true;
    }

    public boolean search(String word) {
        Node node = walk(word);
        return node != null && node.end;
    }

    public boolean startsWith(String prefix) {
        return walk(prefix) != null;
    }

    private Node walk(String s) {
        Node curr = root;
        for (char c : s.toCharArray()) {
            int i = c - 'a';
            if (curr.child[i] == null) {
                return null;
            }
            curr = curr.child[i];
        }
        return curr;
    }
}
""",
            dry_run="Insert 'cat' creates c->a->t and marks end at t node. startsWith('ca') walks and returns true.",
            follow_ups=[
                "How to reduce memory for sparse tries?",
                "How to support deletion of words?",
            ],
            alternatives=[
                "HashSet for exact search only (no fast prefix traversal).",
            ],
            interview_context="Core prefix indexing structure in autocomplete and dictionary services.",
        ),
        p(
            title="Design Add and Search Words Data Structure",
            slug="add-and-search-word",
            difficulty="Medium",
            statement="Design a data structure that supports adding words and searching with '.' wildcard (matches any single character).",
            input_format="Operations: addWord(word), search(pattern).",
            output_format="search returns boolean.",
            constraints=[
                "1 <= word.length <= 25",
                "search pattern may contain lowercase letters and '.'.",
                "At most 10^4 operations.",
            ],
            examples=[
                {
                    "input": "addWord('bad'), addWord('dad'), addWord('mad'), search('pad'), search('.ad'), search('b..')",
                    "output": "false, true, true",
                    "explanation": "Wildcard requires branching DFS in trie.",
                }
            ],
            edge_cases=[
                "Pattern is all wildcards.",
                "Pattern length mismatch with inserted words.",
                "Multiple branching wildcard positions.",
            ],
            brute_force="Store all words and test each against pattern char-by-char. O(N*L) per query.",
            optimal="Trie + DFS for wildcard positions. Branch into all children only when pattern char is '.'.",
            steps=[
                "Insert same as normal trie.",
                "Search with recursive DFS(index,node).",
                "If char is letter, follow one child; if '.', try all non-null children.",
            ],
            time_complexity="addWord O(L), search worst-case O(26^w) where w is number of wildcards.",
            space_complexity="O(total inserted characters).",
            java_code="""
class WordDictionary {
    private static class Node {
        Node[] child = new Node[26];
        boolean end;
    }

    private final Node root = new Node();

    public void addWord(String word) {
        Node curr = root;
        for (char c : word.toCharArray()) {
            int i = c - 'a';
            if (curr.child[i] == null) {
                curr.child[i] = new Node();
            }
            curr = curr.child[i];
        }
        curr.end = true;
    }

    public boolean search(String word) {
        return dfs(word, 0, root);
    }

    private boolean dfs(String word, int idx, Node node) {
        if (node == null) {
            return false;
        }
        if (idx == word.length()) {
            return node.end;
        }

        char c = word.charAt(idx);
        if (c == '.') {
            for (Node next : node.child) {
                if (next != null && dfs(word, idx + 1, next)) {
                    return true;
                }
            }
            return false;
        }

        return dfs(word, idx + 1, node.child[c - 'a']);
    }
}
""",
            dry_run="search('.ad') branches from root on all first chars and matches bad/dad/mad.",
            follow_ups=[
                "How to optimize wildcard-heavy queries?",
                "How would you support '*' wildcard (multiple chars)?",
            ],
            alternatives=[
                "Regex matching over all words (slow).",
            ],
            interview_context="Tests trie search with branching recursion under wildcard constraints.",
        ),
        p(
            title="Replace Words",
            slug="replace-words",
            difficulty="Medium",
            statement="Given dictionary of root words and a sentence, replace successors with shortest root that is a prefix.",
            input_format="dictionary: list of root strings, sentence: space-separated words.",
            output_format="Return transformed sentence string.",
            constraints=[
                "1 <= dictionary.length <= 1000",
                "1 <= dictionary[i].length <= 100",
                "1 <= sentence.length <= 10^6",
            ],
            examples=[
                {
                    "input": "dictionary=['cat','bat','rat'], sentence='the cattle was rattled by the battery'",
                    "output": "the cat was rat by the bat",
                    "explanation": "Use shortest prefix root from trie.",
                }
            ],
            edge_cases=[
                "Word has no matching root.",
                "Multiple roots match; choose shortest.",
                "Large sentence.",
            ],
            brute_force="For each word, scan all dictionary roots and choose shortest prefix match.",
            optimal="Build trie from roots and walk each sentence word until first end-of-root marker.",
            steps=[
                "Insert all root words into trie with end markers.",
                "Split sentence by spaces.",
                "For each word, traverse trie and stop at first end marker if found.",
            ],
            time_complexity="O(total root chars + total sentence chars).",
            space_complexity="O(total root chars).",
            java_code="""
import java.util.*;

class Solution {
    private static class Node {
        Node[] child = new Node[26];
        boolean end;
    }

    public String replaceWords(List<String> dictionary, String sentence) {
        Node root = new Node();
        for (String word : dictionary) {
            insert(root, word);
        }

        String[] words = sentence.split(" ");
        for (int i = 0; i < words.length; i++) {
            words[i] = shortestRoot(root, words[i]);
        }

        return String.join(" ", words);
    }

    private void insert(Node root, String word) {
        Node curr = root;
        for (char c : word.toCharArray()) {
            int idx = c - 'a';
            if (curr.child[idx] == null) {
                curr.child[idx] = new Node();
            }
            curr = curr.child[idx];
        }
        curr.end = true;
    }

    private String shortestRoot(Node root, String word) {
        Node curr = root;
        for (int i = 0; i < word.length(); i++) {
            int idx = word.charAt(i) - 'a';
            if (curr.child[idx] == null) {
                return word;
            }
            curr = curr.child[idx];
            if (curr.end) {
                return word.substring(0, i + 1);
            }
        }
        return word;
    }
}
""",
            dry_run="'cattle' traverses c-a-t where trie node has end => replace with 'cat'.",
            follow_ups=[
                "How to support runtime dictionary updates?",
                "How would you handle Unicode dictionaries?",
            ],
            alternatives=[
                "Sort roots by length and check startsWith (slower at scale).",
            ],
            interview_context="Shows practical trie use in text normalization pipelines.",
        ),
        p(
            title="Word Search II",
            slug="word-search-ii",
            difficulty="Hard",
            statement="Given an m x n board of letters and a list of words, return all words from the list that can be formed by sequential adjacent cells.",
            input_format="board: 2D char matrix, words: string array.",
            output_format="Return list of found words (unique).",
            constraints=[
                "1 <= m, n <= 12",
                "1 <= words.length <= 3 * 10^4",
                "1 <= words[i].length <= 10",
            ],
            examples=[
                {
                    "input": "board=[[o,a,a,n],[e,t,a,e],[i,h,k,r],[i,f,l,v]], words=['oath','pea','eat','rain']",
                    "output": "['eat','oath']",
                    "explanation": "Trie prunes invalid DFS paths early.",
                }
            ],
            edge_cases=[
                "No words found.",
                "Duplicate words in dictionary.",
                "Board with repeated characters.",
            ],
            brute_force="For each word run board DFS search independently. Too expensive for large word lists.",
            optimal="Build trie for all words and perform DFS from each board cell, pruning when prefix not in trie.",
            steps=[
                "Insert all words into trie storing complete word at end nodes.",
                "Run DFS from every cell with trie node pointer.",
                "Mark visited cell temporarily and explore neighbors.",
                "When trie node has complete word, add to answer and clear to avoid duplicates.",
            ],
            time_complexity="Roughly O(m*n*4^L) worst-case, but trie pruning drastically reduces search in practice.",
            space_complexity="O(total dictionary chars + recursion depth).",
            java_code="""
import java.util.*;

class Solution {
    private static class Node {
        Node[] child = new Node[26];
        String word;
    }

    public List<String> findWords(char[][] board, String[] words) {
        Node root = buildTrie(words);
        List<String> result = new ArrayList<>();

        for (int r = 0; r < board.length; r++) {
            for (int c = 0; c < board[0].length; c++) {
                dfs(board, r, c, root, result);
            }
        }

        return result;
    }

    private Node buildTrie(String[] words) {
        Node root = new Node();
        for (String word : words) {
            Node curr = root;
            for (char ch : word.toCharArray()) {
                int i = ch - 'a';
                if (curr.child[i] == null) {
                    curr.child[i] = new Node();
                }
                curr = curr.child[i];
            }
            curr.word = word;
        }
        return root;
    }

    private void dfs(char[][] board, int r, int c, Node node, List<String> result) {
        if (r < 0 || c < 0 || r >= board.length || c >= board[0].length) {
            return;
        }

        char ch = board[r][c];
        if (ch == '#') {
            return;
        }

        Node next = node.child[ch - 'a'];
        if (next == null) {
            return;
        }

        if (next.word != null) {
            result.add(next.word);
            next.word = null;
        }

        board[r][c] = '#';
        dfs(board, r + 1, c, next, result);
        dfs(board, r - 1, c, next, result);
        dfs(board, r, c + 1, next, result);
        dfs(board, r, c - 1, next, result);
        board[r][c] = ch;
    }
}
""",
            dry_run="Start at 'o' cell, follow trie path o->a->t->h to record 'oath'.",
            follow_ups=[
                "How to optimize for very large boards?",
                "Can you avoid recursion stack overflow?",
            ],
            alternatives=[
                "Per-word DFS search (baseline, slower).",
            ],
            interview_context="Hard hybrid of trie + backtracking often used in onsite rounds.",
        ),
        p(
            title="Maximum XOR of Two Numbers in an Array",
            slug="maximum-xor-two-numbers",
            difficulty="Medium",
            statement="Given integer array nums, return maximum value of nums[i] XOR nums[j] for 0 <= i <= j < n.",
            input_format="nums: non-negative integer array.",
            output_format="Return maximum XOR integer value.",
            constraints=[
                "1 <= nums.length <= 2 * 10^5",
                "0 <= nums[i] <= 2^31 - 1",
            ],
            examples=[
                {"input": "nums=[3,10,5,25,2,8]", "output": "28", "explanation": "5 XOR 25 = 28."}
            ],
            edge_cases=[
                "Single element array.",
                "All equal values.",
                "Large integer bits.",
            ],
            brute_force="Try all pairs and compute XOR. O(n^2).",
            optimal="Build bitwise trie of numbers. For each number, greedily choose opposite bit at each level to maximize XOR.",
            steps=[
                "Insert each number into binary trie from bit 31 to 0.",
                "For each number, traverse trie preferring opposite bits.",
                "Compute best XOR and track global max.",
            ],
            time_complexity="O(32*n) = O(n).",
            space_complexity="O(32*n).",
            java_code="""
class Solution {
    private static class Node {
        Node zero;
        Node one;
    }

    public int findMaximumXOR(int[] nums) {
        Node root = new Node();
        for (int num : nums) {
            insert(root, num);
        }

        int best = 0;
        for (int num : nums) {
            best = Math.max(best, query(root, num));
        }
        return best;
    }

    private void insert(Node root, int num) {
        Node curr = root;
        for (int bit = 31; bit >= 0; bit--) {
            int b = (num >> bit) & 1;
            if (b == 0) {
                if (curr.zero == null) {
                    curr.zero = new Node();
                }
                curr = curr.zero;
            } else {
                if (curr.one == null) {
                    curr.one = new Node();
                }
                curr = curr.one;
            }
        }
    }

    private int query(Node root, int num) {
        Node curr = root;
        int value = 0;
        for (int bit = 31; bit >= 0; bit--) {
            int b = (num >> bit) & 1;
            if (b == 0) {
                if (curr.one != null) {
                    value |= (1 << bit);
                    curr = curr.one;
                } else {
                    curr = curr.zero;
                }
            } else {
                if (curr.zero != null) {
                    value |= (1 << bit);
                    curr = curr.zero;
                } else {
                    curr = curr.one;
                }
            }
        }
        return value;
    }
}
""",
            dry_run="For num=5 (00101), trie path against 25 (11001) creates XOR bits 11100 => 28.",
            follow_ups=[
                "How to answer max XOR for each query with dynamic inserts?",
                "How does this compare to prefix-hash bitmask approach?",
            ],
            alternatives=[
                "Bitmask + greedy set approach (also O(32n)).",
            ],
            interview_context="Tests advanced trie application over binary representation, common in high-bar rounds.",
        ),
    ],
    "graphs": [
        p(
            title="Number of Islands",
            slug="number-of-islands",
            difficulty="Medium",
            statement="Given an m x n 2D binary grid of '1's (land) and '0's (water), return the number of islands.",
            input_format="grid: 2D char matrix.",
            output_format="Return integer count of connected components of land (4-directional).",
            constraints=[
                "1 <= m, n <= 300",
                "grid[i][j] is '0' or '1'",
            ],
            examples=[
                {
                    "input": "grid=[['1','1','0'],['0','1','0'],['1','0','1']]",
                    "output": "3",
                    "explanation": "Three disconnected land components.",
                }
            ],
            edge_cases=[
                "All water => 0.",
                "All land => 1.",
                "Single cell grid.",
            ],
            brute_force="For each land cell, repeatedly search all cells to find connected cells (inefficient).",
            optimal="Traverse grid; when hitting unvisited land, run DFS/BFS flood-fill and increment island count.",
            steps=[
                "Loop through every cell.",
                "If cell is land, increment count and DFS/BFS mark all connected land as visited.",
                "Continue until all cells processed.",
            ],
            time_complexity="O(m*n).",
            space_complexity="O(m*n) worst-case recursion/queue.",
            java_code="""
class Solution {
    public int numIslands(char[][] grid) {
        int rows = grid.length;
        int cols = grid[0].length;
        int islands = 0;

        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                if (grid[r][c] == '1') {
                    islands++;
                    dfs(grid, r, c);
                }
            }
        }

        return islands;
    }

    private void dfs(char[][] grid, int r, int c) {
        if (r < 0 || c < 0 || r >= grid.length || c >= grid[0].length || grid[r][c] != '1') {
            return;
        }

        grid[r][c] = '0';
        dfs(grid, r + 1, c);
        dfs(grid, r - 1, c);
        dfs(grid, r, c + 1);
        dfs(grid, r, c - 1);
    }
}
""",
            dry_run="Encounter first '1', DFS clears its component; next untouched '1' starts another island.",
            follow_ups=[
                "How to solve without modifying input grid?",
                "How to count distinct island shapes?",
            ],
            alternatives=[
                "BFS flood fill.",
                "Union-Find on grid cells.",
            ],
            interview_context="Foundational graph traversal over implicit grid graph.",
        ),
        p(
            title="Course Schedule",
            slug="course-schedule",
            difficulty="Medium",
            statement="There are numCourses courses labeled 0..numCourses-1. Given prerequisites pairs [a,b] (take b before a), determine if all courses can be finished.",
            input_format="numCourses: integer, prerequisites: 2D integer array.",
            output_format="Return true if DAG (no cycle), else false.",
            constraints=[
                "1 <= numCourses <= 2000",
                "0 <= prerequisites.length <= 5000",
            ],
            examples=[
                {"input": "numCourses=2, prerequisites=[[1,0]]", "output": "true", "explanation": "Order 0->1 is valid."},
                {"input": "numCourses=2, prerequisites=[[1,0],[0,1]]", "output": "false", "explanation": "Cycle exists."},
            ],
            edge_cases=[
                "No prerequisites.",
                "Disconnected graph components.",
                "Self dependency.",
            ],
            brute_force="Try all permutations/topological orders explicitly (infeasible).",
            optimal="Use Kahn's algorithm (BFS topological sort). If processed count equals numCourses, no cycle.",
            steps=[
                "Build adjacency list and indegree array.",
                "Push all indegree-0 nodes into queue.",
                "Pop queue, reduce indegree of neighbors, enqueue new zeros.",
                "Compare processed count with numCourses.",
            ],
            time_complexity="O(V + E).",
            space_complexity="O(V + E).",
            java_code="""
import java.util.*;

class Solution {
    public boolean canFinish(int numCourses, int[][] prerequisites) {
        List<List<Integer>> graph = new ArrayList<>();
        for (int i = 0; i < numCourses; i++) {
            graph.add(new ArrayList<>());
        }

        int[] indegree = new int[numCourses];
        for (int[] pre : prerequisites) {
            int course = pre[0];
            int need = pre[1];
            graph.get(need).add(course);
            indegree[course]++;
        }

        Queue<Integer> queue = new ArrayDeque<>();
        for (int i = 0; i < numCourses; i++) {
            if (indegree[i] == 0) {
                queue.offer(i);
            }
        }

        int taken = 0;
        while (!queue.isEmpty()) {
            int node = queue.poll();
            taken++;
            for (int next : graph.get(node)) {
                if (--indegree[next] == 0) {
                    queue.offer(next);
                }
            }
        }

        return taken == numCourses;
    }
}
""",
            dry_run="Queue starts [0], process 0 lowers indegree(1) to 0, process 1 => taken=2.",
            follow_ups=[
                "How to return one valid course order?",
                "How to detect cycle using DFS colors?",
            ],
            alternatives=[
                "DFS with visiting/visited states.",
            ],
            interview_context="Critical DAG ordering and cycle detection pattern used in dependency systems.",
        ),
        p(
            title="Network Delay Time (Dijkstra)",
            slug="network-delay-time",
            difficulty="Medium",
            statement="Given directed weighted edges times[u,v,w], and source k, return time it takes for all nodes to receive signal. Return -1 if unreachable.",
            input_format="times: 2D array [u,v,w], n: number of nodes (1-indexed), k: source node.",
            output_format="Return minimum time for all nodes to be reached, or -1.",
            constraints=[
                "1 <= n <= 100",
                "1 <= times.length <= 6000",
                "1 <= w <= 100",
            ],
            examples=[
                {"input": "times=[[2,1,1],[2,3,1],[3,4,1]], n=4, k=2", "output": "2", "explanation": "Farthest node receives at time 2."}
            ],
            edge_cases=[
                "Graph disconnected from source.",
                "Single node graph.",
                "Multiple edges between same nodes.",
            ],
            brute_force="Run repeated relaxations until convergence; slower than needed for non-negative weights.",
            optimal="Use Dijkstra with min-heap since all weights are non-negative.",
            steps=[
                "Build adjacency list.",
                "Initialize dist array with infinity except source 0.",
                "Pop smallest distance node from heap; relax outgoing edges.",
                "Answer is maximum finite dist if all reachable.",
            ],
            time_complexity="O((V + E) log V).",
            space_complexity="O(V + E).",
            java_code="""
import java.util.*;

class Solution {
    public int networkDelayTime(int[][] times, int n, int k) {
        List<List<int[]>> graph = new ArrayList<>();
        for (int i = 0; i <= n; i++) {
            graph.add(new ArrayList<>());
        }
        for (int[] e : times) {
            graph.get(e[0]).add(new int[] {e[1], e[2]});
        }

        int[] dist = new int[n + 1];
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[k] = 0;

        PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparingInt(a -> a[1]));
        pq.offer(new int[] {k, 0});

        while (!pq.isEmpty()) {
            int[] top = pq.poll();
            int node = top[0];
            int d = top[1];
            if (d > dist[node]) {
                continue;
            }
            for (int[] edge : graph.get(node)) {
                int next = edge[0];
                int w = edge[1];
                if (dist[next] > d + w) {
                    dist[next] = d + w;
                    pq.offer(new int[] {next, dist[next]});
                }
            }
        }

        int ans = 0;
        for (int i = 1; i <= n; i++) {
            if (dist[i] == Integer.MAX_VALUE) {
                return -1;
            }
            ans = Math.max(ans, dist[i]);
        }
        return ans;
    }
}
""",
            dry_run="From 2: dist(1)=1, dist(3)=1, then from 3 dist(4)=2. Max dist=2.",
            follow_ups=[
                "How to solve with negative edges?",
                "How to retrieve shortest path tree?",
            ],
            alternatives=[
                "Bellman-Ford O(VE).",
            ],
            interview_context="Tests weighted shortest-path modeling and stale-state handling in priority queues.",
        ),
        p(
            title="Cheapest Flights Within K Stops (Bellman-Ford Style)",
            slug="cheapest-flights-k-stops",
            difficulty="Medium",
            statement="Given flights[u,v,price], src, dst, and k, return cheapest price from src to dst with at most k stops. Return -1 if not possible.",
            input_format="n: node count, flights: 2D edge list, src, dst, k integers.",
            output_format="Return minimum cost or -1.",
            constraints=[
                "1 <= n <= 100",
                "0 <= flights.length <= n*(n-1)/2",
                "0 <= price <= 10^4",
                "0 <= k < n",
            ],
            examples=[
                {"input": "n=3, flights=[[0,1,100],[1,2,100],[0,2,500]], src=0, dst=2, k=1", "output": "200", "explanation": "0->1->2 within one stop is cheapest."}
            ],
            edge_cases=[
                "No route exists.",
                "k = 0 allows direct flights only.",
                "Cheaper path may have more edges but within stop limit.",
            ],
            brute_force="DFS all paths up to k+1 edges and track minimum cost. Exponential in branching.",
            optimal="Bellman-Ford limited to k+1 relaxations. Each round uses previous distances to avoid chaining within same round.",
            steps=[
                "Initialize dist[src]=0 and others INF.",
                "Repeat k+1 times: copy dist to next, relax each flight using dist, write into next.",
                "Set dist=next after each round.",
            ],
            time_complexity="O((k+1) * E).",
            space_complexity="O(V).",
            java_code="""
import java.util.*;

class Solution {
    public int findCheapestPrice(int n, int[][] flights, int src, int dst, int k) {
        int inf = 1_000_000_000;
        int[] dist = new int[n];
        Arrays.fill(dist, inf);
        dist[src] = 0;

        for (int i = 0; i <= k; i++) {
            int[] next = Arrays.copyOf(dist, n);
            for (int[] flight : flights) {
                int u = flight[0], v = flight[1], w = flight[2];
                if (dist[u] == inf) {
                    continue;
                }
                next[v] = Math.min(next[v], dist[u] + w);
            }
            dist = next;
        }

        return dist[dst] == inf ? -1 : dist[dst];
    }
}
""",
            dry_run="Round0 direct costs; round1 allows one intermediate stop and updates cheaper route 0->1->2.",
            follow_ups=[
                "Can Dijkstra be adapted with stop count state?",
                "How to return actual route path?",
            ],
            alternatives=[
                "Modified Dijkstra with state (node,stops).",
                "BFS by levels with pruning.",
            ],
            interview_context="Great for testing understanding of constrained shortest path and Bellman-Ford relaxation logic.",
        ),
        p(
            title="Find the City With the Smallest Number of Neighbors at a Threshold Distance (Floyd-Warshall)",
            slug="find-city-floyd-warshall",
            difficulty="Medium",
            statement="Given weighted undirected graph and distanceThreshold, return city with smallest number of reachable cities within threshold (break ties by largest index).",
            input_format="n: number of cities, edges: [u,v,w], distanceThreshold: integer.",
            output_format="Return city index.",
            constraints=[
                "2 <= n <= 100",
                "1 <= edges.length <= n*(n-1)/2",
                "1 <= w, distanceThreshold <= 10^4",
            ],
            examples=[
                {
                    "input": "n=4, edges=[[0,1,3],[1,2,1],[1,3,4],[2,3,1]], threshold=4",
                    "output": "3",
                    "explanation": "Cities 0 and 3 have same reachable count; choose larger index 3.",
                }
            ],
            edge_cases=[
                "Disconnected graph.",
                "Tie in reachable counts.",
                "Dense graph near n=100.",
            ],
            brute_force="Run BFS/DFS from each node for weighted graph (incorrect) or Dijkstra from each node O(n * (E log V)).",
            optimal="Use Floyd-Warshall all-pairs shortest path O(n^3), suitable for n <= 100.",
            steps=[
                "Initialize dist matrix with INF and zero diagonal.",
                "Fill direct edge weights (min if duplicates).",
                "Run triple loop k,i,j to relax via intermediate k.",
                "Count reachable cities per i and apply tie-break.",
            ],
            time_complexity="O(n^3).",
            space_complexity="O(n^2).",
            java_code="""
import java.util.*;

class Solution {
    public int findTheCity(int n, int[][] edges, int distanceThreshold) {
        int inf = 1_000_000_000;
        int[][] dist = new int[n][n];

        for (int i = 0; i < n; i++) {
            Arrays.fill(dist[i], inf);
            dist[i][i] = 0;
        }

        for (int[] e : edges) {
            int u = e[0], v = e[1], w = e[2];
            dist[u][v] = Math.min(dist[u][v], w);
            dist[v][u] = Math.min(dist[v][u], w);
        }

        for (int k = 0; k < n; k++) {
            for (int i = 0; i < n; i++) {
                if (dist[i][k] == inf) {
                    continue;
                }
                for (int j = 0; j < n; j++) {
                    if (dist[k][j] == inf) {
                        continue;
                    }
                    dist[i][j] = Math.min(dist[i][j], dist[i][k] + dist[k][j]);
                }
            }
        }

        int bestCity = -1;
        int minReachable = Integer.MAX_VALUE;

        for (int i = 0; i < n; i++) {
            int count = 0;
            for (int j = 0; j < n; j++) {
                if (i != j && dist[i][j] <= distanceThreshold) {
                    count++;
                }
            }
            if (count <= minReachable) {
                minReachable = count;
                bestCity = i;
            }
        }

        return bestCity;
    }
}
""",
            dry_run="After APSP matrix computed, count threshold-reachable per row and apply largest-index tie rule.",
            follow_ups=[
                "When would n-times Dijkstra be better than Floyd-Warshall?",
                "How to reconstruct shortest paths between pairs?",
            ],
            alternatives=[
                "Run Dijkstra from each city for sparse graphs.",
            ],
            interview_context="Validates all-pairs shortest path understanding and complexity tradeoff decisions.",
        ),
        p(
            title="Min Cost to Connect All Points (MST)",
            slug="min-cost-connect-points-mst",
            difficulty="Medium",
            statement="Given points in 2D, return minimum cost to connect all points where cost between points i,j is Manhattan distance.",
            input_format="points: 2D integer array [x,y].",
            output_format="Return integer minimum spanning tree total cost.",
            constraints=[
                "1 <= points.length <= 1000",
                "-10^6 <= xi, yi <= 10^6",
            ],
            examples=[
                {"input": "points=[[0,0],[2,2],[3,10],[5,2],[7,0]]", "output": "20", "explanation": "MST picks cheapest edges without cycles."}
            ],
            edge_cases=[
                "Single point => cost 0.",
                "Duplicate coordinates.",
                "Dense complete graph (implicit edges).",
            ],
            brute_force="Generate all spanning trees and choose minimum; combinatorially impossible.",
            optimal="Prim's algorithm on implicit complete graph using min distance to current MST.",
            steps=[
                "Maintain inMST array and best known connection cost for each point.",
                "Repeatedly pick non-MST point with smallest cost.",
                "Update neighbor costs via Manhattan distance.",
            ],
            time_complexity="O(n^2) with array-based Prim (no explicit edges).",
            space_complexity="O(n).",
            java_code="""
import java.util.*;

class Solution {
    public int minCostConnectPoints(int[][] points) {
        int n = points.length;
        int[] minDist = new int[n];
        Arrays.fill(minDist, Integer.MAX_VALUE);
        boolean[] inMST = new boolean[n];
        minDist[0] = 0;

        int total = 0;
        for (int i = 0; i < n; i++) {
            int u = -1;
            for (int v = 0; v < n; v++) {
                if (!inMST[v] && (u == -1 || minDist[v] < minDist[u])) {
                    u = v;
                }
            }

            inMST[u] = true;
            total += minDist[u];

            for (int v = 0; v < n; v++) {
                if (!inMST[v]) {
                    int cost = Math.abs(points[u][0] - points[v][0]) + Math.abs(points[u][1] - points[v][1]);
                    if (cost < minDist[v]) {
                        minDist[v] = cost;
                    }
                }
            }
        }

        return total;
    }
}
""",
            dry_run="Start with point0, nearest point1 cost4, then point3 cost3, etc; sum selected edges = 20.",
            follow_ups=[
                "How to solve with Kruskal + DSU when edges are explicit?",
                "How would Euclidean distance change complexity?",
            ],
            alternatives=[
                "Kruskal with all O(n^2) edges and DSU.",
            ],
            interview_context="MST fundamentals are frequently tested for graph optimization reasoning.",
        ),
        p(
            title="Redundant Connection (Union-Find)",
            slug="redundant-connection-union-find",
            difficulty="Medium",
            statement="Given a tree with one extra edge added, return the edge that can be removed so that graph becomes a tree again.",
            input_format="edges: 2D array where edges[i]=[u,v].",
            output_format="Return redundant edge [u,v].",
            constraints=[
                "n == edges.length",
                "3 <= n <= 1000",
                "1 <= u < v <= n",
            ],
            examples=[
                {"input": "edges=[[1,2],[1,3],[2,3]]", "output": "[2,3]", "explanation": "Adding [2,3] closes first cycle."}
            ],
            edge_cases=[
                "Cycle forms late in input order.",
                "Graph size at upper bound.",
            ],
            brute_force="For each edge, remove it and test if graph is tree via DFS/BFS.",
            optimal="Union-Find: if endpoints of an edge already share root, that edge is redundant.",
            steps=[
                "Initialize DSU parents and ranks for 1..n.",
                "For each edge, find roots of endpoints.",
                "If roots same, return edge; else union roots.",
            ],
            time_complexity="O(n * alpha(n)).",
            space_complexity="O(n).",
            java_code="""
class Solution {
    public int[] findRedundantConnection(int[][] edges) {
        int n = edges.length;
        int[] parent = new int[n + 1];
        int[] rank = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            parent[i] = i;
        }

        for (int[] edge : edges) {
            int u = edge[0], v = edge[1];
            int pu = find(parent, u);
            int pv = find(parent, v);
            if (pu == pv) {
                return edge;
            }
            if (rank[pu] < rank[pv]) {
                parent[pu] = pv;
            } else if (rank[pu] > rank[pv]) {
                parent[pv] = pu;
            } else {
                parent[pv] = pu;
                rank[pu]++;
            }
        }

        return new int[0];
    }

    private int find(int[] parent, int x) {
        if (parent[x] != x) {
            parent[x] = find(parent, parent[x]);
        }
        return parent[x];
    }
}
""",
            dry_run="Process [1,2], [1,3] unions; [2,3] endpoints already connected -> redundant.",
            follow_ups=[
                "How to detect redundant edge in directed graph variant?",
                "Can you return all cycle-forming edges in stream?",
            ],
            alternatives=[
                "DFS connectivity checks after edge removals (slow).",
            ],
            interview_context="Direct DSU pattern test for cycle detection in dynamic connectivity.",
        ),
    ],
    "dynamic-programming": [
        p(
            title="Climbing Stairs",
            slug="climbing-stairs",
            difficulty="Easy",
            statement="You are climbing a staircase. It takes n steps to reach the top. Each time you can climb 1 or 2 steps. Return number of distinct ways.",
            input_format="n: integer number of steps.",
            output_format="Return count of distinct ways as integer.",
            constraints=[
                "1 <= n <= 45",
            ],
            examples=[
                {"input": "n=3", "output": "3", "explanation": "Ways: [1+1+1, 1+2, 2+1]."}
            ],
            edge_cases=[
                "n = 1.",
                "n = 2.",
                "Upper bound near int limit for this constraint.",
            ],
            brute_force="Recursive choose 1 or 2 steps from each state -> exponential O(2^n).",
            optimal="DP relation ways[i] = ways[i-1] + ways[i-2] (Fibonacci pattern).",
            steps=[
                "Handle n <= 2 directly.",
                "Iteratively build from step 3 to n using two previous values.",
                "Return current value.",
            ],
            time_complexity="O(n).",
            space_complexity="O(1).",
            java_code="""
class Solution {
    public int climbStairs(int n) {
        if (n <= 2) {
            return n;
        }

        int prev2 = 1;
        int prev1 = 2;
        for (int i = 3; i <= n; i++) {
            int curr = prev1 + prev2;
            prev2 = prev1;
            prev1 = curr;
        }
        return prev1;
    }
}
""",
            dry_run="n=5: 1,2,3,5,8 -> answer 8.",
            follow_ups=[
                "How if you can take up to k steps each time?",
                "How if some steps are blocked?",
            ],
            alternatives=[
                "Top-down memoization.",
                "Matrix exponentiation for Fibonacci.",
            ],
            interview_context="Classic 1D DP state-transition warmup.",
        ),
        p(
            title="Unique Paths",
            slug="unique-paths",
            difficulty="Medium",
            statement="A robot is at top-left of m x n grid and can only move down or right. Return number of unique paths to bottom-right.",
            input_format="m, n integers for grid dimensions.",
            output_format="Return integer count of distinct paths.",
            constraints=[
                "1 <= m, n <= 100",
            ],
            examples=[
                {"input": "m=3, n=7", "output": "28", "explanation": "DP counts paths from top and left neighbors."}
            ],
            edge_cases=[
                "Single row or single column -> only one path.",
                "Square vs rectangular grids.",
            ],
            brute_force="Recursively branch right/down until destination. Exponential.",
            optimal="2D DP: dp[r][c] = dp[r-1][c] + dp[r][c-1].",
            steps=[
                "Initialize first row and first column with 1.",
                "Fill remaining cells using top+left transitions.",
                "Return dp[m-1][n-1].",
            ],
            time_complexity="O(m*n).",
            space_complexity="O(n) with rolling array optimization.",
            java_code="""
class Solution {
    public int uniquePaths(int m, int n) {
        int[] dp = new int[n];
        for (int c = 0; c < n; c++) {
            dp[c] = 1;
        }

        for (int r = 1; r < m; r++) {
            for (int c = 1; c < n; c++) {
                dp[c] += dp[c - 1];
            }
        }

        return dp[n - 1];
    }
}
""",
            dry_run="For 3x3 grid dp rows evolve: [1,1,1] -> [1,2,3] -> [1,3,6].",
            follow_ups=[
                "How do obstacles change state transitions?",
                "Can you derive combinatorial formula directly?",
            ],
            alternatives=[
                "Combinatorics C(m+n-2, m-1).",
            ],
            interview_context="Standard 2D DP transition problem.",
        ),
        p(
            title="Coin Change",
            slug="coin-change",
            difficulty="Medium",
            statement="Given coin denominations and amount, return fewest coins needed to make amount, or -1 if impossible.",
            input_format="coins: integer array, amount: integer.",
            output_format="Return minimum coin count or -1.",
            constraints=[
                "1 <= coins.length <= 12",
                "1 <= coins[i] <= 2^31 - 1",
                "0 <= amount <= 10^4",
            ],
            examples=[
                {"input": "coins=[1,2,5], amount=11", "output": "3", "explanation": "11 = 5 + 5 + 1."}
            ],
            edge_cases=[
                "amount = 0 => 0.",
                "No combination possible.",
                "Large amount with small coins.",
            ],
            brute_force="Try all combinations recursively; exponential.",
            optimal="Unbounded knapsack DP where dp[a] is min coins for amount a.",
            steps=[
                "Initialize dp[0]=0 and others INF.",
                "For each amount from 1..target, try each coin if coin<=amount.",
                "Transition dp[a]=min(dp[a], dp[a-coin]+1).",
            ],
            time_complexity="O(amount * number_of_coins).",
            space_complexity="O(amount).",
            java_code="""
import java.util.*;

class Solution {
    public int coinChange(int[] coins, int amount) {
        int inf = amount + 1;
        int[] dp = new int[amount + 1];
        Arrays.fill(dp, inf);
        dp[0] = 0;

        for (int a = 1; a <= amount; a++) {
            for (int coin : coins) {
                if (coin <= a) {
                    dp[a] = Math.min(dp[a], dp[a - coin] + 1);
                }
            }
        }

        return dp[amount] == inf ? -1 : dp[amount];
    }
}
""",
            dry_run="amount 11 with [1,2,5]: dp[5]=1, dp[10]=2, dp[11]=3.",
            follow_ups=[
                "How to count number of combinations instead of minimum coins?",
                "How to output one optimal combination?",
            ],
            alternatives=[
                "BFS over amounts.",
                "Top-down memoization.",
            ],
            interview_context="Knapsack-style DP with optimization objective.",
        ),
        p(
            title="Longest Increasing Subsequence",
            slug="longest-increasing-subsequence",
            difficulty="Medium",
            statement="Given integer array nums, return length of the longest strictly increasing subsequence.",
            input_format="nums: integer array.",
            output_format="Return LIS length as integer.",
            constraints=[
                "1 <= nums.length <= 2500",
                "-10^4 <= nums[i] <= 10^4",
            ],
            examples=[
                {"input": "nums=[10,9,2,5,3,7,101,18]", "output": "4", "explanation": "One LIS is [2,3,7,101]."}
            ],
            edge_cases=[
                "Strictly decreasing array -> 1.",
                "All equal values -> 1.",
                "Long increasing run.",
            ],
            brute_force="Generate all subsequences and check increasing property. Exponential.",
            optimal="Patience sorting idea: maintain tails array; binary search replace first >= current.",
            steps=[
                "Initialize empty tails list.",
                "For each num, binary search insertion index in tails.",
                "Replace tails[idx] or append if idx at end.",
            ],
            time_complexity="O(n log n).",
            space_complexity="O(n).",
            java_code="""
import java.util.*;

class Solution {
    public int lengthOfLIS(int[] nums) {
        int[] tails = new int[nums.length];
        int size = 0;

        for (int num : nums) {
            int lo = 0, hi = size;
            while (lo < hi) {
                int mid = lo + (hi - lo) / 2;
                if (tails[mid] < num) {
                    lo = mid + 1;
                } else {
                    hi = mid;
                }
            }
            tails[lo] = num;
            if (lo == size) {
                size++;
            }
        }

        return size;
    }
}
""",
            dry_run="nums 10,9,2,5,3,7 -> tails evolves [10],[9],[2],[2,5],[2,3],[2,3,7].",
            follow_ups=[
                "Can you reconstruct one LIS sequence?",
                "How to handle non-decreasing subsequence variant?",
            ],
            alternatives=[
                "O(n^2) DP: dp[i]=max LIS ending at i.",
            ],
            interview_context="Frequent medium-hard DP question emphasizing optimization from O(n^2) to O(n log n).",
        ),
        p(
            title="Partition Equal Subset Sum",
            slug="partition-equal-subset-sum",
            difficulty="Medium",
            statement="Given a non-empty array nums containing positive integers, determine if it can be partitioned into two subsets with equal sum.",
            input_format="nums: positive integer array.",
            output_format="Return true if equal partition exists else false.",
            constraints=[
                "1 <= nums.length <= 200",
                "1 <= nums[i] <= 100",
            ],
            examples=[
                {"input": "nums=[1,5,11,5]", "output": "true", "explanation": "Can split into [1,5,5] and [11]."}
            ],
            edge_cases=[
                "Total sum odd => impossible.",
                "Single element.",
                "Many small numbers.",
            ],
            brute_force="Try assigning each number to one of two sets recursively; O(2^n).",
            optimal="Transform to subset sum target=sum/2 with 0/1 knapsack DP.",
            steps=[
                "Compute total sum, return false if odd.",
                "Set target = sum/2.",
                "Use boolean dp[target+1], iterate nums and update backwards.",
            ],
            time_complexity="O(n * target).",
            space_complexity="O(target).",
            java_code="""
class Solution {
    public boolean canPartition(int[] nums) {
        int sum = 0;
        for (int num : nums) {
            sum += num;
        }
        if ((sum & 1) == 1) {
            return false;
        }

        int target = sum / 2;
        boolean[] dp = new boolean[target + 1];
        dp[0] = true;

        for (int num : nums) {
            for (int s = target; s >= num; s--) {
                dp[s] = dp[s] || dp[s - num];
            }
        }

        return dp[target];
    }
}
""",
            dry_run="target=11: after processing 1,5,11,5, dp[11] becomes true.",
            follow_ups=[
                "Can you return actual subsets too?",
                "How does this relate to 0/1 knapsack?",
            ],
            alternatives=[
                "Top-down memoization over (index, remaining).",
            ],
            interview_context="Tests reduction to knapsack and 1D DP space optimization.",
        ),
        p(
            title="Burst Balloons (Partition DP)",
            slug="burst-balloons",
            difficulty="Hard",
            statement="Given n balloons with numbers, each burst gains nums[left]*nums[i]*nums[right]. Return maximum coins collectable by bursting all balloons optimally.",
            input_format="nums: integer array.",
            output_format="Return maximum coins as integer.",
            constraints=[
                "1 <= nums.length <= 300",
                "0 <= nums[i] <= 100",
            ],
            examples=[
                {"input": "nums=[3,1,5,8]", "output": "167", "explanation": "Best order yields 167."}
            ],
            edge_cases=[
                "Zero-valued balloons.",
                "Single balloon.",
                "Large n requiring O(n^3) DP.",
            ],
            brute_force="Try all burst orders recursively (n! possibilities).",
            optimal="Interval DP: choose last balloon to burst in interval (l,r).",
            steps=[
                "Pad nums with 1 at both ends.",
                "dp[l][r] stores max coins for open interval (l,r).",
                "For each interval length, try every k in (l,r) as last burst.",
            ],
            time_complexity="O(n^3).",
            space_complexity="O(n^2).",
            java_code="""
class Solution {
    public int maxCoins(int[] nums) {
        int n = nums.length;
        int[] arr = new int[n + 2];
        arr[0] = 1;
        arr[n + 1] = 1;
        for (int i = 0; i < n; i++) {
            arr[i + 1] = nums[i];
        }

        int[][] dp = new int[n + 2][n + 2];

        for (int len = 2; len < n + 2; len++) {
            for (int left = 0; left + len < n + 2; left++) {
                int right = left + len;
                for (int k = left + 1; k < right; k++) {
                    int gain = arr[left] * arr[k] * arr[right];
                    dp[left][right] = Math.max(dp[left][right], dp[left][k] + gain + dp[k][right]);
                }
            }
        }

        return dp[0][n + 1];
    }
}
""",
            dry_run="For interval (0,5) in padded array [1,3,1,5,8,1], try each last burst and keep max 167.",
            follow_ups=[
                "How to derive recurrence formally?",
                "Can this be optimized below O(n^3)?",
            ],
            alternatives=[
                "Top-down memoized interval recursion.",
            ],
            interview_context="Classic partition-DP hard problem in top-tier interviews.",
        ),
        p(
            title="Count Digit One (Digit DP)",
            slug="count-digit-one",
            difficulty="Hard",
            statement="Given an integer n, count the total number of digit '1' appearing in all non-negative integers less than or equal to n.",
            input_format="n: non-negative integer.",
            output_format="Return total count of digit '1'.",
            constraints=[
                "0 <= n <= 10^9",
            ],
            examples=[
                {"input": "n=13", "output": "6", "explanation": "1 appears in 1,10,11,12,13 (6 total times)."}
            ],
            edge_cases=[
                "n = 0 => 0.",
                "Powers of 10 boundaries.",
                "Large n with many digits.",
            ],
            brute_force="Iterate from 0 to n and count ones in each number. Too slow for large n.",
            optimal="Digit DP with tight bound and count accumulation by positions.",
            steps=[
                "Convert n to digit array.",
                "DFS over digit index with states (idx, tight, onesSoFar).",
                "Memoize non-tight states to avoid recomputation.",
            ],
            time_complexity="O(d * 10 * d) where d is number of digits (small, <=10).",
            space_complexity="O(d * d * 2) for memo table.",
            java_code="""
import java.util.*;

class Solution {
    private char[] digits;
    private Integer[][][] memo;

    public int countDigitOne(int n) {
        if (n == 0) {
            return 0;
        }
        digits = String.valueOf(n).toCharArray();
        memo = new Integer[digits.length][digits.length + 1][2];
        return dfs(0, 0, 1);
    }

    private int dfs(int idx, int ones, int tight) {
        if (idx == digits.length) {
            return ones;
        }
        if (memo[idx][ones][tight] != null) {
            return memo[idx][ones][tight];
        }

        int limit = tight == 1 ? digits[idx] - '0' : 9;
        int total = 0;
        for (int dig = 0; dig <= limit; dig++) {
            int nextTight = (tight == 1 && dig == limit) ? 1 : 0;
            total += dfs(idx + 1, ones + (dig == 1 ? 1 : 0), nextTight);
        }

        memo[idx][ones][tight] = total;
        return total;
    }
}
""",
            dry_run="n=13: digit DP enumerates 00..13 under tight constraint and accumulates count of placed digit 1.",
            follow_ups=[
                "How to count occurrences of any digit d?",
                "How to count numbers with no repeated digits?",
            ],
            alternatives=[
                "Mathematical place-value counting formula O(log n).",
            ],
            interview_context="Advanced DP topic testing state design under numeric bounds.",
        ),
        p(
            title="House Robber III (Tree DP)",
            slug="house-robber-iii",
            difficulty="Medium",
            statement="Given root of binary tree where each node value is money, maximize amount robbed without robbing directly-linked parent-child houses.",
            input_format="root: binary tree root.",
            output_format="Return maximum robbed amount.",
            constraints=[
                "1 <= number of nodes <= 10^4",
                "0 <= Node.val <= 10^4",
            ],
            examples=[
                {"input": "root=[3,2,3,null,3,null,1]", "output": "7", "explanation": "Rob nodes 3 + 3 + 1."}
            ],
            edge_cases=[
                "Single node tree.",
                "Skewed tree.",
                "All zero values.",
            ],
            brute_force="At each node choose rob/skip recursively without memo -> exponential.",
            optimal="Tree DP returns two values for each node: [robThis, skipThis].",
            steps=[
                "DFS postorder to compute child states.",
                "rob = node.val + left.skip + right.skip.",
                "skip = max(left.rob,left.skip) + max(right.rob,right.skip).",
                "Answer is max(root.rob, root.skip).",
            ],
            time_complexity="O(n).",
            space_complexity="O(h).",
            java_code="""
class Solution {
    public int rob(TreeNode root) {
        int[] state = dfs(root);
        return Math.max(state[0], state[1]);
    }

    // state[0] = rob this node, state[1] = skip this node
    private int[] dfs(TreeNode node) {
        if (node == null) {
            return new int[] {0, 0};
        }

        int[] left = dfs(node.left);
        int[] right = dfs(node.right);

        int rob = node.val + left[1] + right[1];
        int skip = Math.max(left[0], left[1]) + Math.max(right[0], right[1]);

        return new int[] {rob, skip};
    }
}
""",
            dry_run="For leaf 3 => [3,0]. Parent 2 with right 3 => rob=2, skip=3.",
            follow_ups=[
                "How to return robbed node set as well?",
                "What if graph is not a tree?",
            ],
            alternatives=[
                "Memoization by node with rob/notRob flag.",
            ],
            interview_context="Important tree-DP pattern with include/exclude state.",
        ),
    ],
    "advanced-topics": [
        p(
            title="Range Sum Query - Mutable (Segment Tree)",
            slug="range-sum-query-mutable-segment-tree",
            difficulty="Medium",
            statement="Design a data structure supporting update(index, val) and sumRange(left, right) on an integer array.",
            input_format="Constructor input nums array. Query/update operations follow.",
            output_format="sumRange returns sum of nums[left..right] after updates.",
            constraints=[
                "1 <= nums.length <= 3 * 10^4",
                "-100 <= nums[i], val <= 100",
                "0 <= left <= right < nums.length",
                "At most 3 * 10^4 operations",
            ],
            examples=[
                {
                    "input": "nums=[1,3,5], sumRange(0,2), update(1,2), sumRange(0,2)",
                    "output": "9, 8",
                    "explanation": "Segment tree updates one point and answers range sum quickly.",
                }
            ],
            edge_cases=[
                "Single element array.",
                "Repeated updates to same index.",
                "Negative values.",
            ],
            brute_force="Update is O(1) but each range query scans O(n).",
            optimal="Segment tree stores range sums; both point update and range query are O(log n).",
            steps=[
                "Build segment tree over array.",
                "On update, recurse to leaf and recompute sums on path.",
                "On query, return node sum for full overlap and split on partial overlap.",
            ],
            time_complexity="Build O(n), update O(log n), query O(log n).",
            space_complexity="O(4n).",
            java_code="""
class NumArray {
    private final int n;
    private final int[] tree;

    public NumArray(int[] nums) {
        n = nums.length;
        tree = new int[4 * n];
        build(1, 0, n - 1, nums);
    }

    public void update(int index, int val) {
        update(1, 0, n - 1, index, val);
    }

    public int sumRange(int left, int right) {
        return query(1, 0, n - 1, left, right);
    }

    private void build(int node, int l, int r, int[] nums) {
        if (l == r) {
            tree[node] = nums[l];
            return;
        }
        int mid = l + (r - l) / 2;
        build(node * 2, l, mid, nums);
        build(node * 2 + 1, mid + 1, r, nums);
        tree[node] = tree[node * 2] + tree[node * 2 + 1];
    }

    private void update(int node, int l, int r, int idx, int val) {
        if (l == r) {
            tree[node] = val;
            return;
        }
        int mid = l + (r - l) / 2;
        if (idx <= mid) {
            update(node * 2, l, mid, idx, val);
        } else {
            update(node * 2 + 1, mid + 1, r, idx, val);
        }
        tree[node] = tree[node * 2] + tree[node * 2 + 1];
    }

    private int query(int node, int l, int r, int ql, int qr) {
        if (ql <= l && r <= qr) {
            return tree[node];
        }
        if (r < ql || l > qr) {
            return 0;
        }
        int mid = l + (r - l) / 2;
        return query(node * 2, l, mid, ql, qr)
            + query(node * 2 + 1, mid + 1, r, ql, qr);
    }
}
""",
            dry_run="sum(0,2)=9; update index1->2 modifies leaf and ancestors; sum(0,2)=8.",
            follow_ups=[
                "How to support range add updates?",
                "When is Fenwick Tree preferable?",
            ],
            alternatives=[
                "Fenwick Tree for point update + prefix sums.",
                "Sqrt decomposition.",
            ],
            interview_context="Advanced DS design balancing update/query efficiency.",
        ),
        p(
            title="Count of Smaller Numbers After Self (Fenwick Tree)",
            slug="count-smaller-after-self-fenwick",
            difficulty="Hard",
            statement="Given integer array nums, return array counts where counts[i] is number of smaller elements to the right of nums[i].",
            input_format="nums: integer array.",
            output_format="Return integer list counts.",
            constraints=[
                "1 <= nums.length <= 10^5",
                "-10^4 <= nums[i] <= 10^4",
            ],
            examples=[
                {"input": "nums=[5,2,6,1]", "output": "[2,1,1,0]", "explanation": "To right of 5, two numbers are smaller: 2 and 1."}
            ],
            edge_cases=[
                "All equal elements.",
                "Strictly increasing or decreasing arrays.",
                "Negative values require coordinate compression.",
            ],
            brute_force="For each i, scan j>i and count nums[j] < nums[i]. O(n^2).",
            optimal="Process from right to left. Fenwick tree tracks frequencies of seen values with coordinate compression.",
            steps=[
                "Coordinate-compress values to ranks 1..m.",
                "Traverse nums from right.",
                "Query Fenwick prefix(rank-1) for smaller count, then add current rank.",
            ],
            time_complexity="O(n log n).",
            space_complexity="O(n).",
            java_code="""
import java.util.*;

class Solution {
    public List<Integer> countSmaller(int[] nums) {
        int[] sorted = nums.clone();
        Arrays.sort(sorted);

        Map<Integer, Integer> rank = new HashMap<>();
        int r = 1;
        for (int val : sorted) {
            if (!rank.containsKey(val)) {
                rank.put(val, r++);
            }
        }

        Fenwick bit = new Fenwick(r + 2);
        Integer[] ans = new Integer[nums.length];

        for (int i = nums.length - 1; i >= 0; i--) {
            int idx = rank.get(nums[i]);
            ans[i] = bit.query(idx - 1);
            bit.add(idx, 1);
        }

        return Arrays.asList(ans);
    }

    private static class Fenwick {
        int[] bit;

        Fenwick(int n) {
            bit = new int[n + 1];
        }

        void add(int i, int delta) {
            while (i < bit.length) {
                bit[i] += delta;
                i += i & -i;
            }
        }

        int query(int i) {
            int sum = 0;
            while (i > 0) {
                sum += bit[i];
                i -= i & -i;
            }
            return sum;
        }
    }
}
""",
            dry_run="From right: 1->count0, add1; 6->count1, add6; 2->count1; 5->count2.",
            follow_ups=[
                "How to solve using merge sort counting inversion style?",
                "How to handle online updates?",
            ],
            alternatives=[
                "Merge sort based counting O(n log n).",
                "Balanced BST with order statistics.",
            ],
            interview_context="Advanced frequency-query pattern with Fenwick and compression.",
        ),
        p(
            title="Static Range Minimum Query (Sparse Table)",
            slug="static-rmq-sparse-table",
            difficulty="Medium",
            statement="Given immutable array nums and multiple queries [l, r], return minimum value in each range.",
            input_format="nums: integer array; queries: pairs of 0-based indices l, r.",
            output_format="For each query return minimum in nums[l..r].",
            constraints=[
                "1 <= nums.length <= 2 * 10^5",
                "1 <= queries.length <= 2 * 10^5",
                "0 <= l <= r < nums.length",
            ],
            examples=[
                {"input": "nums=[4,6,1,5,7,3], query(1,4)", "output": "1", "explanation": "Minimum in [6,1,5,7] is 1."}
            ],
            edge_cases=[
                "Query length 1.",
                "Full array query.",
                "Many repeated queries.",
            ],
            brute_force="Scan each range for every query. O(n) per query.",
            optimal="Sparse table precomputes range minima for power-of-two lengths; RMQ answered in O(1).",
            steps=[
                "Build log table for lengths.",
                "Build st[k][i] = min for interval length 2^k starting at i.",
                "Query uses two overlapping blocks of length 2^k covering [l,r].",
            ],
            time_complexity="Build O(n log n), each query O(1).",
            space_complexity="O(n log n).",
            java_code="""
import java.util.*;

class SparseTableRMQ {
    private final int[][] st;
    private final int[] log2;

    public SparseTableRMQ(int[] nums) {
        int n = nums.length;
        log2 = new int[n + 1];
        for (int i = 2; i <= n; i++) {
            log2[i] = log2[i / 2] + 1;
        }

        int maxK = log2[n] + 1;
        st = new int[maxK][n];
        System.arraycopy(nums, 0, st[0], 0, n);

        for (int k = 1; k < maxK; k++) {
            int len = 1 << k;
            for (int i = 0; i + len <= n; i++) {
                st[k][i] = Math.min(st[k - 1][i], st[k - 1][i + (len >> 1)]);
            }
        }
    }

    public int queryMin(int l, int r) {
        int len = r - l + 1;
        int k = log2[len];
        return Math.min(st[k][l], st[k][r - (1 << k) + 1]);
    }
}
""",
            dry_run="For [1,4], len=4 => k=2, compare st[2][1] and st[2][1], result 1.",
            follow_ups=[
                "Why does sparse table require idempotent operations for O(1) query?",
                "How to support updates?",
            ],
            alternatives=[
                "Segment tree for mutable queries.",
            ],
            interview_context="Senior-level preprocessing vs query-time tradeoff question.",
        ),
        p(
            title="Number of Provinces (DSU)",
            slug="number-of-provinces-dsu",
            difficulty="Medium",
            statement="Given n x n matrix isConnected representing direct connections between cities, return number of provinces (connected components).",
            input_format="isConnected: adjacency matrix of size n.",
            output_format="Return count of provinces.",
            constraints=[
                "1 <= n <= 200",
                "isConnected[i][i] = 1",
                "isConnected[i][j] = isConnected[j][i]",
            ],
            examples=[
                {"input": "isConnected=[[1,1,0],[1,1,0],[0,0,1]]", "output": "2", "explanation": "Cities {0,1} and {2} form two components."}
            ],
            edge_cases=[
                "All disconnected except self loops.",
                "Fully connected graph.",
                "Single city.",
            ],
            brute_force="Run DFS from every unvisited node (valid O(n^2), but DSU asked in advanced topic).",
            optimal="Union connected city pairs and count distinct DSU roots.",
            steps=[
                "Initialize DSU for n cities.",
                "For each pair (i,j) where isConnected[i][j]=1, union(i,j).",
                "Count unique roots after all unions.",
            ],
            time_complexity="O(n^2 * alpha(n)).",
            space_complexity="O(n).",
            java_code="""
import java.util.*;

class Solution {
    public int findCircleNum(int[][] isConnected) {
        int n = isConnected.length;
        DSU dsu = new DSU(n);

        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                if (isConnected[i][j] == 1) {
                    dsu.union(i, j);
                }
            }
        }

        Set<Integer> roots = new HashSet<>();
        for (int i = 0; i < n; i++) {
            roots.add(dsu.find(i));
        }
        return roots.size();
    }

    private static class DSU {
        int[] parent;
        int[] rank;

        DSU(int n) {
            parent = new int[n];
            rank = new int[n];
            for (int i = 0; i < n; i++) {
                parent[i] = i;
            }
        }

        int find(int x) {
            if (parent[x] != x) {
                parent[x] = find(parent[x]);
            }
            return parent[x];
        }

        void union(int a, int b) {
            int pa = find(a);
            int pb = find(b);
            if (pa == pb) {
                return;
            }
            if (rank[pa] < rank[pb]) {
                parent[pa] = pb;
            } else if (rank[pa] > rank[pb]) {
                parent[pb] = pa;
            } else {
                parent[pb] = pa;
                rank[pa]++;
            }
        }
    }
}
""",
            dry_run="Union(0,1) merges first two cities; city2 remains separate => 2 roots.",
            follow_ups=[
                "How does DSU compare with BFS/DFS here?",
                "How to support dynamic edge additions online?",
            ],
            alternatives=[
                "DFS/BFS connected components on adjacency matrix.",
            ],
            interview_context="DSU is essential for dynamic connectivity and MST-related problems.",
        ),
        p(
            title="Maximum Flow (Edmonds-Karp)",
            slug="maximum-flow-edmonds-karp",
            difficulty="Hard",
            statement="Given directed graph capacities and source s, sink t, compute maximum possible flow from s to t.",
            input_format="n: node count, edges: list [u,v,capacity], s: source, t: sink.",
            output_format="Return integer maximum flow value.",
            constraints=[
                "2 <= n <= 200",
                "0 <= edges.length <= 5000",
                "0 <= capacity <= 10^6",
            ],
            examples=[
                {"input": "n=4, edges=[[0,1,3],[0,2,2],[1,2,1],[1,3,2],[2,3,4]], s=0, t=3", "output": "5", "explanation": "Max flow uses augmenting paths until none remain."}
            ],
            edge_cases=[
                "No path from s to t => 0.",
                "Multiple parallel edges.",
                "Zero-capacity edges.",
            ],
            brute_force="Enumerate all possible flow assignments satisfying constraints (intractable).",
            optimal="Edmonds-Karp repeatedly finds shortest augmenting path in residual graph using BFS.",
            steps=[
                "Build residual capacity matrix.",
                "Run BFS from source to sink storing parent pointers.",
                "Find bottleneck capacity on found path.",
                "Augment flow and update forward/reverse residual capacities.",
            ],
            time_complexity="O(V * E^2).",
            space_complexity="O(V^2) for residual matrix.",
            java_code="""
import java.util.*;

class MaxFlow {
    public int edmondsKarp(int n, int[][] edges, int s, int t) {
        int[][] capacity = new int[n][n];
        List<List<Integer>> graph = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            graph.add(new ArrayList<>());
        }

        for (int[] e : edges) {
            int u = e[0], v = e[1], cap = e[2];
            capacity[u][v] += cap;
            graph.get(u).add(v);
            graph.get(v).add(u);
        }

        int flow = 0;
        int[] parent = new int[n];

        while (bfs(graph, capacity, s, t, parent)) {
            int aug = Integer.MAX_VALUE;
            for (int v = t; v != s; v = parent[v]) {
                int u = parent[v];
                aug = Math.min(aug, capacity[u][v]);
            }

            for (int v = t; v != s; v = parent[v]) {
                int u = parent[v];
                capacity[u][v] -= aug;
                capacity[v][u] += aug;
            }

            flow += aug;
        }

        return flow;
    }

    private boolean bfs(List<List<Integer>> graph, int[][] capacity, int s, int t, int[] parent) {
        Arrays.fill(parent, -1);
        parent[s] = s;
        Queue<Integer> q = new ArrayDeque<>();
        q.offer(s);

        while (!q.isEmpty()) {
            int u = q.poll();
            for (int v : graph.get(u)) {
                if (parent[v] == -1 && capacity[u][v] > 0) {
                    parent[v] = u;
                    if (v == t) {
                        return true;
                    }
                    q.offer(v);
                }
            }
        }

        return false;
    }
}
""",
            dry_run="Path 0->1->3 adds 2, path 0->2->3 adds 2, path 0->1->2->3 adds 1 => total 5.",
            follow_ups=[
                "When to use Dinic instead?",
                "How do max-flow and min-cut relate?",
            ],
            alternatives=[
                "Dinic O(EV^2) worst-case but faster in practice.",
                "Push-relabel for dense graphs.",
            ],
            interview_context="Advanced optimization question for senior algorithm and systems roles.",
        ),
        p(
            title="Erect the Fence (Convex Hull)",
            slug="erect-the-fence-convex-hull",
            difficulty="Hard",
            statement="Given coordinates of trees in a garden, return points on the perimeter of the minimum fence enclosing all trees (including boundary collinear points).",
            input_format="trees: 2D integer array [x,y].",
            output_format="Return list of boundary points in any order.",
            constraints=[
                "1 <= trees.length <= 3000",
                "0 <= xi, yi <= 100",
            ],
            examples=[
                {
                    "input": "trees=[[1,1],[2,2],[2,0],[2,4],[3,3],[4,2]]",
                    "output": "[[1,1],[2,0],[4,2],[3,3],[2,4]]",
                    "explanation": "Monotonic chain convex hull including collinear boundary points.",
                }
            ],
            edge_cases=[
                "All points collinear.",
                "Duplicate points.",
                "Very small point count.",
            ],
            brute_force="Check each point pair as potential edge and test all points on one side. O(n^3).",
            optimal="Use Andrew monotonic chain to build lower and upper hull with cross-product orientation checks.",
            steps=[
                "Sort points lexicographically.",
                "Build lower hull: remove last while making right turn.",
                "Build upper hull similarly in reverse order.",
                "Combine hull points and deduplicate.",
            ],
            time_complexity="O(n log n) due to sorting.",
            space_complexity="O(n).",
            java_code="""
import java.util.*;

class Solution {
    public int[][] outerTrees(int[][] trees) {
        if (trees.length <= 1) {
            return trees;
        }

        Arrays.sort(trees, (a, b) -> a[0] == b[0] ? Integer.compare(a[1], b[1]) : Integer.compare(a[0], b[0]));
        List<int[]> hull = new ArrayList<>();

        for (int[] p : trees) {
            while (hull.size() >= 2 && cross(hull.get(hull.size() - 2), hull.get(hull.size() - 1), p) < 0) {
                hull.remove(hull.size() - 1);
            }
            hull.add(p);
        }

        int lowerSize = hull.size();
        for (int i = trees.length - 2; i >= 0; i--) {
            int[] p = trees[i];
            while (hull.size() > lowerSize && cross(hull.get(hull.size() - 2), hull.get(hull.size() - 1), p) < 0) {
                hull.remove(hull.size() - 1);
            }
            hull.add(p);
        }

        Set<String> seen = new HashSet<>();
        List<int[]> unique = new ArrayList<>();
        for (int[] p : hull) {
            String key = p[0] + "," + p[1];
            if (seen.add(key)) {
                unique.add(p);
            }
        }

        return unique.toArray(new int[unique.size()][]);
    }

    private int cross(int[] a, int[] b, int[] c) {
        return (b[0] - a[0]) * (c[1] - a[1]) - (b[1] - a[1]) * (c[0] - a[0]);
    }
}
""",
            dry_run="Sorted points build lower and upper chains; inward right turns are removed, leaving boundary points.",
            follow_ups=[
                "How to compute polygon area after hull construction?",
                "How to exclude collinear interior points if problem demands strict hull vertices only?",
            ],
            alternatives=[
                "Graham scan.",
                "Jarvis march for small hull sizes.",
            ],
            interview_context="Geometry appears in high-level rounds to test orientation math and algorithmic rigor.",
        ),
    ],
}
