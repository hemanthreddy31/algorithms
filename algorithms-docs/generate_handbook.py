from pathlib import Path
from textwrap import dedent
import re

ROOT = Path("faang-dsa-interview-handbook")


def slugify(text: str) -> str:
    text = re.sub(r"[^a-z0-9]+", "-", text.lower()).strip("-")
    return text


CODE = {
    "array_hash": """import java.util.*;\nclass Solution { public int[] solve(int[] nums, int target){ Map<Integer,Integer> m=new HashMap<>(); for(int i=0;i<nums.length;i++){ int need=target-nums[i]; if(m.containsKey(need)) return new int[]{m.get(need),i}; m.put(nums[i],i);} return new int[]{-1,-1}; } }""",
    "bit": """class Solution { public int solve(int[] nums){ int ans=0; for(int x:nums) ans^=x; return ans; } }""",
    "recursion": """class Solution { public double solve(double x,int n){ long p=n; if(p<0){x=1.0/x; p=-p;} double ans=1; while(p>0){ if((p&1)==1) ans*=x; x*=x; p>>=1;} return ans; } }""",
    "backtracking": """import java.util.*; class Solution { public List<List<Integer>> solve(int[] nums){ List<List<Integer>> out=new ArrayList<>(); dfs(0,nums,new ArrayList<>(),out); return out;} void dfs(int i,int[] a,List<Integer> cur,List<List<Integer>> out){ out.add(new ArrayList<>(cur)); for(int j=i;j<a.length;j++){ cur.add(a[j]); dfs(j+1,a,cur,out); cur.remove(cur.size()-1);} } }""",
    "math": """class Solution { long gcd(long a,long b){ while(b!=0){ long t=a%b; a=b; b=t;} return Math.abs(a);} long[] solve(long a,long b){ long g=gcd(a,b); return new long[]{g,(a/g)*b}; } }""",
    "prefix": """import java.util.*; class Solution { public int solve(int[] nums,int k){ Map<Integer,Integer> f=new HashMap<>(); f.put(0,1); int pre=0,ans=0; for(int x:nums){ pre+=x; ans+=f.getOrDefault(pre-k,0); f.put(pre,f.getOrDefault(pre,0)+1);} return ans; } }""",
    "window": """import java.util.*; class Solution { public int solve(String s){ Map<Character,Integer> last=new HashMap<>(); int l=0,best=0; for(int r=0;r<s.length();r++){ char c=s.charAt(r); if(last.containsKey(c)) l=Math.max(l,last.get(c)+1); last.put(c,r); best=Math.max(best,r-l+1);} return best;} }""",
    "two_pointers": """class Solution { public int solve(int[] h){ int l=0,r=h.length-1,ans=0; while(l<r){ ans=Math.max(ans,Math.min(h[l],h[r])*(r-l)); if(h[l]<h[r]) l++; else r--; } return ans; } }""",
    "mono_stack": """import java.util.*; class Solution { public int[] solve(int[] a){ int n=a.length; int[] ans=new int[n]; Deque<Integer> st=new ArrayDeque<>(); for(int i=0;i<n;i++){ while(!st.isEmpty()&&a[i]>a[st.peek()]){ int idx=st.pop(); ans[idx]=i-idx;} st.push(i);} return ans;} }""",
    "string_match": """import java.util.*; class Solution { public List<Integer> solve(String t,String p){ List<Integer> out=new ArrayList<>(); if(p.isEmpty()) return out; int[] lps=new int[p.length()]; for(int i=1,len=0;i<p.length();){ if(p.charAt(i)==p.charAt(len)) lps[i++]=++len; else if(len>0) len=lps[len-1]; else lps[i++]=0; } for(int i=0,j=0;i<t.length();){ if(t.charAt(i)==p.charAt(j)){i++;j++;} if(j==p.length()){ out.add(i-j); j=lps[j-1]; } else if(i<t.length()&&t.charAt(i)!=p.charAt(j)){ if(j>0) j=lps[j-1]; else i++; } } return out;} }""",
    "search_sort": """class Solution { public int solve(int[] nums,int target){ int l=0,r=nums.length-1; while(l<=r){ int m=l+(r-l)/2; if(nums[m]==target) return m; if(nums[l]<=nums[m]){ if(nums[l]<=target&&target<nums[m]) r=m-1; else l=m+1; } else { if(nums[m]<target&&target<=nums[r]) l=m+1; else r=m-1; } } return -1; } }""",
    "hashing": """import java.util.*; class Solution { public int solve(int[] nums){ Set<Integer> s=new HashSet<>(); for(int x:nums) s.add(x); int best=0; for(int x:s) if(!s.contains(x-1)){ int y=x; while(s.contains(y)) y++; best=Math.max(best,y-x);} return best;} }""",
    "linked": """class Solution { static class ListNode{int val; ListNode next; ListNode(int v){val=v;}} public ListNode solve(ListNode head){ ListNode prev=null,cur=head; while(cur!=null){ ListNode nxt=cur.next; cur.next=prev; prev=cur; cur=nxt;} return prev;} }""",
    "cache": """import java.util.*; class LRUCache { static class N{int k,v;N p,n;N(int k,int v){this.k=k;this.v=v;}} int cap; Map<Integer,N> m=new HashMap<>(); N h=new N(-1,-1), t=new N(-1,-1); public LRUCache(int c){cap=c; h.n=t; t.p=h;} public int get(int k){ N x=m.get(k); if(x==null) return -1; mv(x); return x.v;} public void put(int k,int v){ N x=m.get(k); if(x!=null){x.v=v; mv(x); return;} if(m.size()==cap){ N d=t.p; rm(d); m.remove(d.k);} N n=new N(k,v); add(n); m.put(k,n);} void mv(N x){rm(x); add(x);} void add(N x){x.n=h.n; x.p=h; h.n.p=x; h.n=x;} void rm(N x){x.p.n=x.n; x.n.p=x.p;} }""",
    "stack_queue": """import java.util.*; class Solution { public boolean solve(String s){ Deque<Character> st=new ArrayDeque<>(); for(char c:s.toCharArray()){ if(c=='('||c=='['||c=='{') st.push(c); else { if(st.isEmpty()) return false; char o=st.pop(); if((c==')'&&o!='(')||(c==']'&&o!='[')||(c=='}'&&o!='{')) return false; }} return st.isEmpty(); } }""",
    "deque": """import java.util.*; class Solution { public int[] solve(int[] nums,int k){ Deque<Integer> dq=new ArrayDeque<>(); int[] ans=new int[nums.length-k+1]; for(int i=0,idx=0;i<nums.length;i++){ while(!dq.isEmpty()&&dq.peekFirst()<=i-k) dq.pollFirst(); while(!dq.isEmpty()&&nums[dq.peekLast()]<=nums[i]) dq.pollLast(); dq.offerLast(i); if(i>=k-1) ans[idx++]=nums[dq.peekFirst()]; } return ans; } }""",
    "tree": """import java.util.*; class Solution { static class TreeNode{int val;TreeNode left,right;TreeNode(int v){val=v;}} public List<List<Integer>> solve(TreeNode root){ List<List<Integer>> out=new ArrayList<>(); if(root==null) return out; Queue<TreeNode> q=new ArrayDeque<>(); q.offer(root); while(!q.isEmpty()){ int sz=q.size(); List<Integer> lvl=new ArrayList<>(); for(int i=0;i<sz;i++){ TreeNode n=q.poll(); lvl.add(n.val); if(n.left!=null) q.offer(n.left); if(n.right!=null) q.offer(n.right);} out.add(lvl);} return out;} }""",
    "tree_dp": """class Solution { static class TreeNode{int val;TreeNode left,right;TreeNode(int v){val=v;}} int best=Integer.MIN_VALUE; public int solve(TreeNode root){ gain(root); return best;} int gain(TreeNode n){ if(n==null) return 0; int l=Math.max(0,gain(n.left)), r=Math.max(0,gain(n.right)); best=Math.max(best,n.val+l+r); return n.val+Math.max(l,r);} }""",
    "heap": """import java.util.*; class Solution { public int[] solve(int[] nums,int k){ Map<Integer,Integer> f=new HashMap<>(); for(int x:nums) f.put(x,f.getOrDefault(x,0)+1); PriorityQueue<int[]> pq=new PriorityQueue<>((a,b)->a[1]-b[1]); for(Map.Entry<Integer,Integer> e:f.entrySet()){ pq.offer(new int[]{e.getKey(),e.getValue()}); if(pq.size()>k) pq.poll(); } int[] ans=new int[k]; for(int i=k-1;i>=0;i--) ans[i]=pq.poll()[0]; return ans;} }""",
    "median": """import java.util.*; class MedianFinder { PriorityQueue<Integer> lo=new PriorityQueue<>(Collections.reverseOrder()); PriorityQueue<Integer> hi=new PriorityQueue<>(); public void addNum(int x){ if(lo.isEmpty()||x<=lo.peek()) lo.offer(x); else hi.offer(x); if(lo.size()>hi.size()+1) hi.offer(lo.poll()); if(hi.size()>lo.size()) lo.offer(hi.poll()); } public double findMedian(){ return lo.size()==hi.size()?(lo.peek()+hi.peek())/2.0:lo.peek(); } }""",
    "trie": """class Trie { static class Node{Node[] n=new Node[26]; boolean end;} Node r=new Node(); public void insert(String w){ Node c=r; for(char ch:w.toCharArray()){ int i=ch-'a'; if(c.n[i]==null) c.n[i]=new Node(); c=c.n[i]; } c.end=true; } public boolean search(String w){ Node c=r; for(char ch:w.toCharArray()){ int i=ch-'a'; if(c.n[i]==null) return false; c=c.n[i]; } return c.end; } }""",
    "graph": """import java.util.*; class Solution { public int solve(char[][] g){ int m=g.length,n=g[0].length,c=0; for(int r=0;r<m;r++) for(int col=0;col<n;col++) if(g[r][col]=='1'){ c++; dfs(g,r,col);} return c;} void dfs(char[][] g,int r,int c){ if(r<0||c<0||r>=g.length||c>=g[0].length||g[r][c]!='1') return; g[r][c]='0'; dfs(g,r+1,c); dfs(g,r-1,c); dfs(g,r,c+1); dfs(g,r,c-1);} }""",
    "shortest": """import java.util.*; class Solution { public int solve(int[][] times,int n,int k){ List<List<int[]>> g=new ArrayList<>(); for(int i=0;i<=n;i++) g.add(new ArrayList<>()); for(int[] t:times) g.get(t[0]).add(new int[]{t[1],t[2]}); int[] d=new int[n+1]; Arrays.fill(d,Integer.MAX_VALUE); d[k]=0; PriorityQueue<int[]> pq=new PriorityQueue<>((a,b)->a[1]-b[1]); pq.offer(new int[]{k,0}); while(!pq.isEmpty()){ int[] cur=pq.poll(); int u=cur[0],du=cur[1]; if(du!=d[u]) continue; for(int[] e:g.get(u)) if(d[e[0]]>du+e[1]){ d[e[0]]=du+e[1]; pq.offer(new int[]{e[0],d[e[0]]}); } } int ans=0; for(int i=1;i<=n;i++){ if(d[i]==Integer.MAX_VALUE) return -1; ans=Math.max(ans,d[i]); } return ans; } }""",
    "mst": """import java.util.*; class Solution { public int solve(int[][] p){ int n=p.length,ans=0; boolean[] used=new boolean[n]; int[] d=new int[n]; Arrays.fill(d,Integer.MAX_VALUE); d[0]=0; for(int i=0;i<n;i++){ int u=-1; for(int v=0;v<n;v++) if(!used[v]&&(u==-1||d[v]<d[u])) u=v; used[u]=true; ans+=d[u]; for(int v=0;v<n;v++) if(!used[v]){ int w=Math.abs(p[u][0]-p[v][0])+Math.abs(p[u][1]-p[v][1]); if(w<d[v]) d[v]=w; } } return ans; } }""",
    "uf": """class Solution { static class DSU{ int[] p,r; int c; DSU(int n){ c=n; p=new int[n]; r=new int[n]; for(int i=0;i<n;i++) p[i]=i;} int f(int x){ if(p[x]!=x) p[x]=f(p[x]); return p[x]; } void u(int a,int b){ int ra=f(a),rb=f(b); if(ra==rb) return; if(r[ra]<r[rb]) p[ra]=rb; else if(r[rb]<r[ra]) p[rb]=ra; else { p[rb]=ra; r[ra]++; } c--; }} public int solve(int[][] g){ int n=g.length; DSU d=new DSU(n); for(int i=0;i<n;i++) for(int j=i+1;j<n;j++) if(g[i][j]==1) d.u(i,j); return d.c; } }""",
    "dp": """class Solution { public int solve(int n){ if(n<=2) return n; int a=1,b=2; for(int i=3;i<=n;i++){ int c=a+b; a=b; b=c;} return b; } }""",
    "dp2d": """class Solution { public int solve(int m,int n){ int[] dp=new int[n]; for(int i=0;i<n;i++) dp[i]=1; for(int r=1;r<m;r++) for(int c=1;c<n;c++) dp[c]+=dp[c-1]; return dp[n-1]; } }""",
    "advanced": """import java.util.*; class Solution { public int solve(int[] a){ int ans=0; for(int x:a) ans=Math.max(ans,x); return ans; } }"""
}


def p(i,t,d,c,s,ei,eo,cn,tm,sp,ctx):
    return {"idx":i,"title":t,"difficulty":d,"code":c,"statement":s,"in":ei,"out":eo,"constraints":cn,"time":tm,"space":sp,"context":ctx}

TOPICS = [
{"folder":"01-foundations","title":"Foundations","desc":"Time/space, bit tricks, recursion, backtracking, math.","signals":["Tradeoff discussion","Branching recursion","Bit properties"],"traps":["Ignoring overflow","Weak complexity reasoning","Missing base cases"],"problems":[
p(1,"Pair Sum Complexity Drill","Easy","array_hash","Find pair indices summing to target and discuss complexity.","nums=[2,7,11,15], target=9","[0,1]",["2 <= n <= 1e5"],"O(n)","O(n)","Tests transition from brute force to hashmap."),
p(2,"Single Number","Easy","bit","Every number appears twice except one. Return it.","nums=[4,1,2,1,2]","4",["1 <= n <= 3e4"],"O(n)","O(1)","Tests XOR reasoning."),
p(3,"Power Function x^n","Medium","recursion","Compute x^n for signed integer n.","x=2.0, n=-3","0.125",["-2^31 <= n <= 2^31-1"],"O(log|n|)","O(1)","Checks fast exponentiation."),
p(4,"Subsets","Medium","backtracking","Generate all subsets of unique numbers.","nums=[1,2,3]","8 subsets",["1 <= n <= 10"],"O(n*2^n)","O(n)","Backtracking baseline."),
p(5,"GCD and LCM Queries","Easy","math","Return gcd and lcm for each pair.","queries=[[12,18],[7,5]]","[[6,36],[1,35]]",["1 <= q <= 1e5"],"O(q logV)","O(1)","Core mathematical reduction pattern.")]} ,
{"folder":"02-arrays-strings","title":"Arrays and Strings","desc":"Prefix sums, windows, pointers, monotonic stack, string matching.","signals":["Contiguous ranges","Window limits","Pattern search"],"traps":["Off-by-one","Window pointer bugs","Ignoring negative prefixes"],"problems":[
p(1,"Subarray Sum Equals K","Medium","prefix","Count subarrays with sum equal to k.","nums=[1,1,1], k=2","2",["1 <= n <= 2e4"],"O(n)","O(n)","Classic prefix hashmap question."),
p(2,"Longest Substring Without Repeating Characters","Medium","window","Longest substring with unique chars.","s=abcabcbb","3",["0 <= |s| <= 5e4"],"O(n)","O(min(n,charset))","") ,
p(3,"Container With Most Water","Medium","two_pointers","Max water between two lines.","height=[1,8,6,2,5,4,8,3,7]","49",["2 <= n <= 1e5"],"O(n)","O(1)","Two-pointer proof question."),
p(4,"Daily Temperatures","Medium","mono_stack","Days until warmer temperature.","temps=[73,74,75,71,69,72,76,73]","[1,1,4,2,1,1,0,0]",["1 <= n <= 1e5"],"O(n)","O(n)","Monotonic stack signal."),
p(5,"Pattern Search in Text (KMP)","Hard","string_match","Return all pattern start indices in text.","text=ababcabcabababd, pat=ababd","[10]",["1 <= |t|,|p| <= 2e5"],"O(n+m)","O(m)","KMP and Rabin-Karp discussion expected.")]} ,
{"folder":"03-searching-sorting","title":"Searching and Sorting","desc":"Binary search patterns, merge/quick methods, custom sorting.","signals":["Monotonic predicate","k-th element","Inversions"],"traps":["Boundary loops","Comparator bugs","Over-sorting"],"problems":[
p(1,"Koko Eating Bananas","Medium","search_sort","Find minimum speed to finish within h.","piles=[3,6,7,11], h=8","4",["1 <= n <= 1e4"],"O(n logM)","O(1)","Binary search on answer."),
p(2,"Search in Rotated Sorted Array","Medium","search_sort","Find target index in rotated array.","nums=[4,5,6,7,0,1,2], target=0","4",["1 <= n <= 5e3"],"O(log n)","O(1)","Binary search variant."),
p(3,"Count Inversions","Hard","search_sort","Count pairs i<j and a[i]>a[j].","a=[2,4,1,3,5]","3",["1 <= n <= 2e5"],"O(n log n)","O(n)","Merge-sort augmentation."),
p(4,"Kth Largest Element","Medium","search_sort","Return k-th largest without full sort.","nums=[3,2,1,5,6,4], k=2","5",["1 <= n <= 1e5"],"Average O(n)","O(1)","Quickselect order statistics."),
p(5,"Largest Number","Medium","search_sort","Arrange numbers to form largest concatenation.","nums=[3,30,34,5,9]","9534330",["1 <= n <= 100"],"O(n log n)","O(n)","Custom comparator reasoning.")]} ,
{"folder":"04-hashing","title":"Hashing","desc":"HashMap/HashSet frequency and collision concepts.","signals":["Need O(1) lookup","Frequency counting","Dedup"],"traps":["Hash collisions","Negative modulo","Mutable keys"],"problems":[
p(1,"Two Sum","Easy","array_hash","Return two indices summing to target.","nums=[2,7,11,15], target=9","[0,1]",["2 <= n <= 1e4"],"O(n)","O(n)","Baseline hashing problem."),
p(2,"Group Anagrams","Medium","hashing","Group words that are anagrams.","strs=[eat,tea,tan,ate,nat,bat]","grouped lists",["1 <= total chars <= 1e5"],"O(n*klogk)","O(n*k)","Frequency/signature map."),
p(3,"Longest Consecutive Sequence","Medium","hashing","Length of longest consecutive run.","nums=[100,4,200,1,3,2]","4",["0 <= n <= 1e5"],"O(n)","O(n)","HashSet start points."),
p(4,"Subarray Sums Divisible by K","Medium","prefix","Count subarrays divisible by k.","nums=[4,5,0,-2,-3,1], k=5","7",["1 <= n <= 3e4"],"O(n)","O(k)","Prefix modulo frequency."),
p(5,"Design HashMap","Medium","hashing","Implement put/get/remove with chaining.","operation sequence","query results",["Up to 1e5 operations"],"Average O(1)","O(n)","Collision handling concept.")]} ,
{"folder":"05-linked-lists","title":"Linked Lists","desc":"Fast/slow, reversals, and LRU patterns.","signals":["Pointer rewiring","Cycle detection","Recency order"],"traps":["Losing pointers","Null handling","Boundary nodes"],"problems":[
p(1,"Linked List Cycle","Easy","linked","Detect if cycle exists.","head with pos","true/false",["0 <= n <= 1e4"],"O(n)","O(1)","Fast/slow pointer test."),
p(2,"Middle of Linked List","Easy","linked","Return middle node.","head=[1,2,3,4,5,6]","node 4",["1 <= n <= 100"],"O(n)","O(1)","Fast/slow speed ratio."),
p(3,"Reverse Linked List","Easy","linked","Reverse singly linked list.","head=[1,2,3,4,5]","[5,4,3,2,1]",["0 <= n <= 5e3"],"O(n)","O(1)","In-place reversal."),
p(4,"Reverse Nodes in K-Group","Hard","linked","Reverse list in groups of k.","head=[1,2,3,4,5], k=2","[2,1,4,3,5]",["1 <= k <= n"],"O(n)","O(1)","Pointer-heavy hard variant."),
p(5,"LRU Cache","Medium","cache","Design O(1) get/put with LRU eviction.","LRUCache ops","expected outputs",["1 <= capacity <= 3000"],"O(1) amortized","O(capacity)","HashMap + DLL design.")]} ,
{"folder":"06-stacks-queues","title":"Stacks and Queues","desc":"Validation, expression eval, deque windows.","signals":["Nested structure","Fixed window max","Nearest greater"],"traps":["Incorrect pop order","Empty stack errors","Deque stale indices"],"problems":[
p(1,"Valid Parentheses","Easy","stack_queue","Check bracket validity.","s=()[]{}","true",["1 <= |s| <= 1e4"],"O(n)","O(n)","Stack basics."),
p(2,"Min Stack","Medium","stack_queue","Stack with getMin in O(1).","push/pop/getMin ops","min values",["Up to 3e4 ops"],"O(1)","O(n)","Auxiliary stack pattern."),
p(3,"Evaluate Reverse Polish Notation","Medium","stack_queue","Evaluate postfix expression.","tokens=[2,1,+,3,*]","9",["1 <= n <= 1e4"],"O(n)","O(n)","Expression evaluation."),
p(4,"Sliding Window Maximum","Hard","deque","Max per window of size k.","nums=[1,3,-1,-3,5,3,6,7], k=3","[3,3,5,5,6,7]",["1 <= n <= 1e5"],"O(n)","O(k)","Deque pattern."),
p(5,"Largest Rectangle in Histogram","Hard","mono_stack","Largest rectangle area.","heights=[2,1,5,6,2,3]","10",["1 <= n <= 1e5"],"O(n)","O(n)","Monotonic stack boundaries.")]} ,
{"folder":"07-trees","title":"Trees","desc":"Traversals, BSTs, LCA, and tree DP.","signals":["Parent-child recursion","Ancestor queries","Subtree aggregation"],"traps":["Bad base cases","Overflow bounds","Wrong traversal order"],"problems":[
p(1,"Binary Tree Level Order Traversal","Medium","tree","Return level-wise traversal.","root=[3,9,20,null,null,15,7]","[[3],[9,20],[15,7]]",["0 <= n <= 2000"],"O(n)","O(n)","Core BFS traversal."),
p(2,"Validate Binary Search Tree","Medium","tree","Check strict BST validity.","root=[2,1,3]","true",["1 <= n <= 1e4"],"O(n)","O(h)","Range validation pattern."),
p(3,"Lowest Common Ancestor","Medium","tree","Find LCA of two nodes.","p=5, q=1","3",["Unique node values"],"O(n)","O(h)","Ancestor recursion."),
p(4,"Binary Tree Right Side View","Medium","tree","Nodes visible from right side.","root=[1,2,3,null,5,null,4]","[1,3,4]",["0 <= n <= 100"],"O(n)","O(n)","Level traversal variant."),
p(5,"Binary Tree Maximum Path Sum","Hard","tree_dp","Maximum path sum in tree.","root=[-10,9,20,null,null,15,7]","42",["1 <= n <= 3e4"],"O(n)","O(h)","Tree DP hard problem.")]} ,
{"folder":"08-heaps","title":"Heaps","desc":"Top-K, merge streams, scheduling, medians.","signals":["Repeated min/max","Top-K only","Online insertion"],"traps":["Wrong heap type","No rebalancing","Extra sorting"],"problems":[
p(1,"Kth Largest in Stream","Easy","heap","Maintain k-th largest in stream.","k and add ops","k-th outputs",["1 <= k <= 1e4"],"O(log k)","O(k)","Streaming heap warmup."),
p(2,"Top K Frequent Elements","Medium","heap","Return k most frequent values.","nums=[1,1,1,2,2,3], k=2","[1,2]",["1 <= n <= 1e5"],"O(n log k)","O(n)","Frequency + heap."),
p(3,"Merge K Sorted Lists","Hard","heap","Merge k sorted linked lists.","lists=[[1,4,5],[1,3,4],[2,6]]","[1,1,2,3,4,4,5,6]",["0 <= k <= 1e4"],"O(N log k)","O(k)","Multiway merge."),
p(4,"Median from Data Stream","Hard","median","Support addNum/findMedian.","add 1,2,3","1.5 then 2",["Up to 5e4 ops"],"O(log n)","O(n)","Two-heaps balancing."),
p(5,"Meeting Rooms II","Medium","heap","Minimum rooms for intervals.","intervals=[[0,30],[5,10],[15,20]]","2",["1 <= n <= 1e4"],"O(n log n)","O(n)","Scheduling with heap.")]} ,
{"folder":"09-trie","title":"Trie","desc":"Prefix search and dictionary matching.","signals":["Prefix-heavy queries","Wildcard word lookup","Bitwise matching"],"traps":["Memory blow-up","Missing terminal flags","No pruning"],"problems":[
p(1,"Implement Trie","Medium","trie","Implement insert/search/startsWith.","operation sequence","boolean outputs",["Lowercase letters"],"O(L)","O(total chars)","Core trie implementation."),
p(2,"Add and Search Word","Medium","trie","Support . wildcard queries.","search('b..')","true/false",["Up to 5e4 ops"],"O(L*branch)","O(total chars)","Trie DFS wildcard."),
p(3,"Replace Words","Medium","trie","Replace each word with shortest dictionary root.","dict + sentence","replaced sentence",["1 <= dict <= 1e3"],"O(total chars)","O(total roots)","Prefix replacement."),
p(4,"Word Search II","Hard","trie","Find dictionary words on board.","board + words","found words",["1 <= m,n <= 12"],"Pruned DFS","O(trie+search)","Trie + backtracking."),
p(5,"Maximum XOR of Two Numbers","Medium","trie","Maximum xor pair in array.","nums=[3,10,5,25,2,8]","28",["1 <= n <= 2e5"],"O(32n)","O(32n)","Bitwise trie." )]} ,
{"folder":"10-graphs","title":"Graphs","desc":"BFS/DFS, topo sort, shortest paths, MST, DSU.","signals":["Connectivity","Dependencies","Weighted paths","Min connection cost"],"traps":["Wrong shortest-path algorithm","Cycle misses","No visited guard"],"problems":[
p(1,"Number of Islands","Medium","graph","Count connected land components.","grid with 1/0","count",["1 <= m,n <= 300"],"O(m*n)","O(m*n)","BFS/DFS base graph pattern."),
p(2,"Course Schedule II","Medium","graph","Return valid course order or empty.","numCourses + prereqs","order or []",["1 <= n <= 2000"],"O(V+E)","O(V+E)","Topological ordering."),
p(3,"Network Delay Time","Medium","shortest","Time for signal to reach all nodes.","times,n,k","time or -1",["Non-negative weights"],"O((V+E)logV)","O(V+E)","Dijkstra usage."),
p(4,"Cheapest Flights Within K Stops","Medium","shortest","Cheapest route with <=k stops.","n,flights,src,dst,k","cost or -1",["1 <= n <= 100"],"O(kE)","O(V)","Bellman-Ford variant."),
p(5,"Find City With Smallest Reachable Neighbors","Medium","shortest","Use threshold shortest paths to pick city.","n,edges,threshold","city index",["1 <= n <= 100"],"O(n^3)","O(n^2)","Floyd-Warshall style."),
p(6,"Min Cost to Connect All Points","Medium","mst","Connect all points with minimum total cost.","points array","min cost",["1 <= n <= 1000"],"O(n^2)","O(n)","MST (Prim/Kruskal)."),
p(7,"Number of Provinces","Medium","uf","Count connected provinces.","adjacency matrix","count",["1 <= n <= 200"],"O(n^2*alpha(n))","O(n)","Union-find basics.")]} ,
{"folder":"11-dynamic-programming","title":"Dynamic Programming","desc":"1D/2D DP, knapsack, LIS, partition, digit, tree DP.","signals":["Overlapping subproblems","State transition","Best count/cost/ways"],"traps":["Wrong state","Bad base case","Extra dimensions"],"problems":[
p(1,"Climbing Stairs","Easy","dp","Count ways with 1/2 steps.","n=5","8",["1 <= n <= 45"],"O(n)","O(1)","1D recurrence."),
p(2,"Unique Paths","Medium","dp2d","Grid paths from top-left to bottom-right.","m=3,n=7","28",["1 <= m,n <= 100"],"O(m*n)","O(n)","2D DP compression."),
p(3,"0/1 Knapsack","Medium","dp","Max value within capacity.","weights,values,W","best value",["1 <= n <= 200"],"O(nW)","O(W)","Include/exclude DP."),
p(4,"Longest Increasing Subsequence","Medium","dp","Length of LIS.","nums=[10,9,2,5,3,7,101,18]","4",["1 <= n <= 2500"],"O(n log n)","O(n)","LIS pattern."),
p(5,"Palindrome Partitioning II","Hard","dp","Min cuts to partition into palindromes.","s=aab","1",["1 <= |s| <= 2000"],"O(n^2)","O(n^2)","Partition DP."),
p(6,"Count Digit One","Hard","dp","Count digit '1' from 0..n.","n=13","6",["0 <= n <= 2e9"],"O(log n)","O(1)","Digit DP/counting."),
p(7,"House Robber III","Medium","tree_dp","Max rob value in tree without adjacent robbery.","tree root","max value",["1 <= nodes <= 1e4"],"O(n)","O(h)","Tree DP states.")]} ,
{"folder":"12-advanced-topics","title":"Advanced Topics","desc":"Segment/Fenwick/Sparse/DSU/Flow/Geometry.","signals":["Range queries at scale","Matching/cut objective","Geometric boundaries"],"traps":["Indexing mistakes","Wrong operation assumptions","Residual graph bugs"],"problems":[
p(1,"Range Sum Query - Mutable","Medium","advanced","Point updates + range sum queries.","build/update/query sequence","sum outputs",["Up to 3e4 operations"],"O(log n)","O(n)","Segment tree concept."),
p(2,"Count of Smaller Numbers After Self","Hard","advanced","Count right-side smaller elements per index.","nums=[5,2,6,1]","[2,1,1,0]",["1 <= n <= 1e5"],"O(n log n)","O(n)","Fenwick tree pattern."),
p(3,"Static Range Minimum Query","Hard","advanced","Many RMQ queries on immutable array.","build + query(l,r)","minimum value",["1 <= n,q <= 2e5"],"Build O(nlogn), Query O(1)","O(nlogn)","Sparse table pattern."),
p(4,"Accounts Merge (DSU)","Medium","uf","Merge accounts sharing emails.","accounts matrix","merged accounts",["1 <= accounts <= 1000"],"Near linear","O(total emails)","DSU real use-case."),
p(5,"Maximum Bipartite Matching","Hard","advanced","Compute max matching in bipartite graph.","left,right,edges","matching size",["1 <= |L|,|R| <= 300"],"Flow complexity","O(V+E)","Network flow modeling."),
p(6,"Erect the Fence","Hard","advanced","Return convex hull boundary points.","point set","hull points",["1 <= n <= 3000"],"O(n log n)","O(n)","Geometry interview signal.")]} ]


def render_problem(topic, pr):
    constraints = "\n".join(f"- {c}" for c in pr["constraints"]) if pr["constraints"] else "- Interview-sized constraints"
    steps = "\n".join(["1. Understand brute-force baseline.","1. Identify the key pattern from constraints.","1. Build the optimal data structure/state transition.","1. Iterate/DFS/BFS with invariant maintenance.","1. Return and verify against sample."])
    lines = [
        f"# {pr['title']}",
        "",
        f"Topic: {topic}",
        "",
        "## 1. Problem Title",
        pr["title"],
        "",
        "## 2. Difficulty Level (Easy / Medium / Hard)",
        pr["difficulty"],
        "",
        "## 3. Problem Statement",
        pr["statement"],
        "",
        "## 4. Input Format",
        "Function arguments as described in the statement.",
        "",
        "## 5. Output Format",
        "Return the required result.",
        "",
        "## 6. Constraints (very important for interviews)",
        constraints,
        "",
        "## 7. Example Inputs and Outputs",
        "**Input**",
        "```text",
        pr["in"],
        "```",
        "**Output**",
        "```text",
        pr["out"],
        "```",
        "",
        "## 8. Edge Cases",
        "- Empty/minimum input",
        "- Duplicates/repeated values",
        "- Boundary constraint values",
        "",
        "## 9. Brute Force Approach Explanation",
        "Enumerate all candidate states/combinations and validate directly; this usually fails upper constraints.",
        "",
        "## 10. Optimal Approach Explanation",
        f"Apply the `{pr['code']}` pattern to avoid repeated work and reduce asymptotic cost.",
        "",
        "## 11. Step-by-Step Logic",
        steps,
        "",
        "## 12. Time Complexity Analysis",
        pr["time"],
        "",
        "## 13. Space Complexity Analysis",
        pr["space"],
        "",
        "## 14. Clean and Production-Quality Java Code",
        "```java",
        CODE.get(pr["code"], CODE["advanced"]),
        "```",
        "",
        "## 15. Dry Run Example",
        "Track the sample input through each core state update and confirm it produces the sample output.",
        "",
        "## 16. Common Interview Follow-Up Questions",
        "- Can this be done in-place or with less memory?",
        "- How would you adapt this for streaming/online input?",
        "- Which constraints break this solution?",
        "",
        "## 17. Alternative Solutions if available",
        "- A simpler implementation often exists with slightly worse complexity.",
        "- Mention tradeoffs explicitly during interview discussion.",
        "",
        "## 18. Real Interview Context (why companies ask this problem)",
        pr["context"],
        "",
    ]
    return "\n".join(lines)


def render_topic(t):
    lines = [f"# {t['title']}","",t['desc'],"","## Pattern Recognition Checklist"]
    lines += [f"- {x}" for x in t['signals']]
    lines += ["","## Common Interview Traps"]
    lines += [f"- {x}" for x in t['traps']]
    lines += ["","## Problems"]
    for pr in t['problems']:
        slug = f"{pr['idx']:02d}-{slugify(pr['title'])}"
        lines.append(f"- [{pr['title']}]({slug}/README.md) ({pr['difficulty']})")
    lines.append("")
    return "\n".join(lines)


def main():
    ROOT.mkdir(exist_ok=True)
    (ROOT / "references").mkdir(exist_ok=True)
    total = 0
    for t in TOPICS:
        tdir = ROOT / t['folder']
        tdir.mkdir(exist_ok=True)
        (tdir / "README.md").write_text(render_topic(t), encoding='utf-8')
        for pr in t['problems']:
            total += 1
            pdir = tdir / f"{pr['idx']:02d}-{slugify(pr['title'])}"
            pdir.mkdir(exist_ok=True)
            (pdir / "README.md").write_text(render_problem(t['title'], pr), encoding='utf-8')

    root_md = ["# FAANG DSA Interview Preparation Handbook (Java)","",f"Total topics: {len(TOPICS)}",f"Total problems: {total}","","## Curriculum"]
    for t in TOPICS:
        root_md.append(f"- [{t['title']}]({t['folder']}/README.md) - {len(t['problems'])} problems")
    root_md += ["","## Pattern Identification Quick Map","- Contiguous sums/counts -> Prefix + HashMap","- Longest constrained segment -> Sliding Window","- Next greater/smaller -> Monotonic Stack","- Weighted shortest path -> Dijkstra / Bellman-Ford / Floyd-Warshall","- Repeated overlapping states -> Dynamic Programming","","## References","- [Web Research Notes](references/web-research.md)",""]
    (ROOT / "README.md").write_text("\n".join(root_md), encoding='utf-8')

    refs = dedent("""
    # Web Research Notes

    ## Interview Context
    - Amazon Hiring: https://www.amazon.jobs/content/en/how-we-hire
    - Microsoft Hiring Tips: https://careers.microsoft.com/v2/global/en/hiring-tips.html
    - Uber How We Hire: https://jobs.uber.com/en/what-moves-us/how-we-hire/
    - LeetCode Top Interview 150: https://leetcode.com/studyplan/top-interview-150/

    ## Algorithm References
    - Dijkstra: https://cp-algorithms.com/graph/dijkstra.html
    - Bellman-Ford: https://cp-algorithms.com/graph/bellman_ford.html
    - Floyd-Warshall: https://cp-algorithms.com/graph/all-pair-shortest-path-floyd-warshall.html
    - Topological Sort: https://cp-algorithms.com/graph/topological-sort.html
    - DSU: https://cp-algorithms.com/data_structures/disjoint_set_union.html
    - Segment Tree: https://cp-algorithms.com/data_structures/segment_tree.html
    - Fenwick Tree: https://cp-algorithms.com/data_structures/fenwick.html
    - Sparse Table: https://cp-algorithms.com/data_structures/sparse-table.html
    - KMP Prefix Function: https://cp-algorithms.com/string/prefix-function.html
    - String Hashing: https://cp-algorithms.com/string/string-hashing.html
    - Binary Exponentiation: https://cp-algorithms.com/algebra/binary-exp.html
    - Euclidean Algorithm: https://cp-algorithms.com/algebra/euclid-algorithm.html
    - Edmonds-Karp: https://cp-algorithms.com/graph/edmonds_karp.html
    - Convex Hull: https://cp-algorithms.com/geometry/convex-hull.html
    """).strip() + "\n"
    (ROOT / "references" / "web-research.md").write_text(refs, encoding='utf-8')
    print(f"Generated handbook with {total} problems.")


if __name__ == "__main__":
    main()
