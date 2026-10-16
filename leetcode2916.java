class Solution {
    public int sumCounts(int[] nums) {
        final long MOD = 1_000_000_007;
        int n = nums.length;
        long[] cnt = new long[n + 1], tot = new long[n + 1];  
        Map<Integer, Integer> last = new HashMap<>();
        long ans = 0;
        for (int r = 0; r < n; r++) {
            int p = last.getOrDefault(nums[r], -1);
            long c = 0, s = 0;
            for (int i = n - p - 1; i > 0; i -= i & -i) {  
                c += cnt[i];
                s += tot[i];
            }
            long gain = (long) (r - p) * (r - p) - 2 * (s - p * c);  
            ans = (ans + (n - r) * gain) % MOD;
            for (int i = n - p; i <= n; i += i & -i) {  
                cnt[i]++;
                tot[i] += p;
            }
            last.put(nums[r], r);
        }
        return (int) ans;
    }
}

/*
Problem:
LeetCode 2916 - Subarrays Distinct Element Sum of Squares II

Intuition:
When adding nums[i], a subarray gains a new distinct element
only when its starting index is after the previous occurrence
of nums[i].

Approach:
Use a lazy segment tree to maintain:
1. Sum of distinct counts.
2. Sum of squared distinct counts.
Use last occurrence to determine the range to increment.

Algorithm:
1. Find the previous occurrence of nums[i].
2. Add 1 to the range [previousOccurrence + 1, i].
3. Add the root's squareSum to the answer modulo 1e9 + 7.
4. Update the last occurrence of nums[i].

Why It Works:
Every subarray ending at i is represented by its starting
index. Exactly those starting after the previous occurrence
gain one distinct element. Lazy propagation efficiently
updates their counts and squared counts.

Brute Force:
Enumerate every subarray and maintain a HashSet.
Time: O(n^2) expected; Space: O(n).

Dry Run:
nums = [1, 2, 1]
Ending at index 0: square sum = 1
Ending at index 1: square sum = 5
Ending at index 2: square sum = 9
Answer = 1 + 5 + 9 = 15

Time/Space Complexity:
Optimal time: O(n log n)
Optimal space: O(n)

Functions Used:
sumCounts() - processes each array element.
update() - performs a range increment.
apply() - updates sum and squared sum.
push() - propagates lazy updates.
getSquareSum() - returns the root's squared sum.
*/
