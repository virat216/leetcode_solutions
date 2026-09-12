/*
    LeetCode 3414 - Maximum Score of Non-overlapping Intervals


    ============================================================
    BRUTE FORCE APPROACH
    ============================================================

    For every interval, we have two choices:

        1. Take the interval.
        2. Skip the interval.

    If we take an interval, we recursively try to select
    compatible intervals after it.

    We also need to make sure that at most 4 intervals are
    selected.

    This results in an exponential number of possibilities.

    Time Complexity:

        O(2^n)

    Space Complexity:

        O(n)

    because of recursion depth.


    ============================================================
    OPTIMAL APPROACH
    ============================================================

    This is a variation of the classic:

        WEIGHTED INTERVAL SCHEDULING

    problem.

    Each interval has:

        start
        end
        weight


    We want:

        maximum total weight

    while selecting:

        at most 4 intervals

    and the selected intervals must not overlap.


    ------------------------------------------------------------
    STEP 1 - SORT BY END TIME
    ------------------------------------------------------------

    Sort all intervals according to their ending time.

    This allows us to efficiently find the last interval that
    can appear before the current interval.


    ------------------------------------------------------------
    STEP 2 - FIND PREVIOUS COMPATIBLE INTERVAL
    ------------------------------------------------------------

    For interval i:

        [start, end, weight]

    we need the last interval whose:

        previousEnd < start

    Notice the STRICT inequality.

    We use:

        previousEnd < start

    because intervals that touch at an endpoint are considered
    overlapping in this problem.


    Since the intervals are sorted by end time, we can find
    the previous compatible interval using binary search.

    Store it in:

        prev[i]


    Therefore:

        prev[i] = last index j such that
                  arr[j].end < arr[i].start


    Binary search takes:

        O(log n)


    ------------------------------------------------------------
    STEP 3 - DP
    ------------------------------------------------------------

    Define:

        dp[i][j]

    as:

        Maximum total weight using the first i intervals
        while selecting at most j intervals.


    j can only be:

        0, 1, 2, 3, 4


    For every interval there are two choices.


    OPTION 1:
    Don't select the current interval.

        dp[i][j] = dp[i-1][j]


    OPTION 2:
    Select the current interval.

    Suppose current interval is i-1.

    The previous compatible interval is:

        prev[i-1]


    Then:

        dp[i][j] =
            dp[prev[i-1] + 1][j-1]
            + currentWeight


    Therefore:

        dp[i][j] = max(
            dp[i-1][j],

            dp[prev[i-1] + 1][j-1]
            + currentWeight
        )


    ============================================================
    WHY DID WE THINK OF THIS APPROACH?
    ============================================================

    The phrase:

        "maximum weight"
        +
        "non-overlapping intervals"

    is a strong signal for:

        WEIGHTED INTERVAL SCHEDULING.


    Then the problem adds:

        "choose at most 4"


    Since 4 is a very small constant, add another DP dimension:

        number of intervals selected


    So the natural state becomes:

        dp[i][count]


    This gives us an O(n) DP after sorting and binary search.


    ============================================================
    DRY RUN
    ============================================================

    Consider:

        intervals:

        [1,3,4]
        [2,5,3]
        [4,6,5]
        [6,8,2]


    Sort by end time:

        [1,3,4]   index 0
        [2,5,3]   index 1
        [4,6,5]   index 2
        [6,8,2]   index 3


    ------------------------------------------------------------
    FIND PREVIOUS COMPATIBLE INTERVALS
    ------------------------------------------------------------

    Interval 0:

        [1,3,4]

        No previous interval.

        prev[0] = -1


    Interval 1:

        [2,5,3]

        Need:

            previousEnd < 2

        None exists.

        prev[1] = -1


    Interval 2:

        [4,6,5]

        Need:

            previousEnd < 4

        Interval 0 ends at 3.

        3 < 4

        Therefore:

            prev[2] = 0


    Interval 3:

        [6,8,2]

        Need:

            previousEnd < 6

        Interval 2 ends at 6.

        6 < 6 is FALSE.

        Interval 1 ends at 5.

        5 < 6 is TRUE.

        Therefore:

            prev[3] = 1


    ============================================================
    DP EXAMPLE
    ============================================================

    Suppose we only select at most 2 intervals for this
    simplified dry run.


    Start with:

        dp[0][0] = 0


    Interval 0:

        [1,3,4]

    Taking it gives:

        4

    So:

        dp[1][1] = 4


    Interval 1:

        [2,5,3]

    We can either:

        skip -> 4

        take -> 3

    Therefore:

        dp[2][1] = 4


    Interval 2:

        [4,6,5]

    It is compatible with interval 0.

    Taking it alone:

        5

    Taking it after interval 0:

        4 + 5 = 9


    Therefore:

        dp[3][2] = 9


    Selected intervals:

        [1,3,4]
        [4,6,5]


    Total weight:

        4 + 5 = 9


    ============================================================
    ANSWER RECONSTRUCTION
    ============================================================

    The DP table gives us the maximum weight, but the problem
    asks for the INDICES of the selected intervals.

    Therefore we store:

        take[i][j]

    which tells us whether interval i-1 was selected when
    calculating dp[i][j].


    Start from:

        i = n
        j = 4


    If:

        take[i][j] == true

    then the current interval was selected.

    Add its original index to the answer.

    Then jump to:

        i = prev[i-1] + 1

        j = j - 1


    Otherwise:

        i--


    Finally reverse the collected indices because reconstruction
    happens from the end of the DP table.


    ============================================================
    COMPLEXITY
    ============================================================

    Sorting:

        O(n log n)


    Finding previous compatible interval:

        O(n log n)


    DP:

        O(n * 4)

        = O(n)


    Reconstruction:

        O(n)


    Overall:

        Time Complexity:
            O(n log n)

        Space Complexity:
            O(n)


    ============================================================
    KEY PATTERN TO REMEMBER
    ============================================================

    Whenever you see:

        intervals
        +
        non-overlapping
        +
        maximum value/weight

    think:

        WEIGHTED INTERVAL SCHEDULING


    Standard approach:

        1. Sort by end time.

        2. For every interval find the previous compatible
           interval using binary search.

        3. Use DP:

               skip current interval

               OR

               take current interval
               + best compatible previous solution


    If the problem also says:

        "select at most K intervals"

    add K as another DP dimension.


    For this problem:

        K = 4


    Therefore:

        dp[i][j]

    where:

        i = number of processed intervals
        j = number of intervals allowed


    Final complexity:

        O(n log n) time
        O(n) space
*/
class Solution {
    public int[] maximumWeight(List<List<Integer>> intervals) {
        int n = intervals.size();
        int[][] iv = new int[n][3];
        for (int i = 0; i < n; i++)
            for (int c = 0; c < 3; c++) iv[i][c] = intervals.get(i).get(c);

        Integer[] order = new Integer[n];
        for (int i = 0; i < n; i++) order[i] = i;
        Arrays.sort(order, (a, b) -> iv[a][1] - iv[b][1]);
        int[] rights = new int[n];
        for (int p = 0; p < n; p++) rights[p] = iv[order[p]][1];

        long[] prevScore = new long[n + 1];
        int[][] prevIds = new int[n + 1][0];
        for (int k = 0; k < 4; k++) {
            long[] curScore = new long[n + 1];
            int[][] curIds = new int[n + 1][0];
            for (int p = 1; p <= n; p++) {
                int i = order[p - 1];  // take next interval
                int l = iv[i][0], w = iv[i][2];
                int lo = 0, hi = n;  // lower_bound: intervals ending before l
                while (lo < hi) { 
                    int mid = (lo + hi) >>> 1; 
                    if (rights[mid] < l) lo = mid + 1; 
                    else hi = mid; 
                }

                long takeScore = prevScore[lo] + w;
                int[] takeIds = insertSorted(prevIds[lo], i);
                if (better(takeScore, takeIds, curScore[p - 1], curIds[p - 1])) {
                    curScore[p] = takeScore; 
                    curIds[p] = takeIds;
                } else {
                    curScore[p] = curScore[p - 1]; 
                    curIds[p] = curIds[p - 1];
                }
            }
            prevScore = curScore; prevIds = curIds;
        }
        return prevIds[n];
    }

    private static boolean better(long s1, int[] a, long s2, int[] b) {
        if (s1 != s2) return s1 > s2;  // higher score wins
        int m = Math.min(a.length, b.length);
        for (int i = 0; i < m; i++)
            if (a[i] != b[i]) return a[i] < b[i];  // then lexicographically smaller
        return a.length < b.length;
    }

    private static int[] insertSorted(int[] ids, int x) {
        int[] out = new int[ids.length + 1];
        int i = 0;
        while (i < ids.length && ids[i] < x) { 
            out[i] = ids[i]; 
            i++; 
        }
        out[i] = x;
        for (; i < ids.length; i++) 
            out[i + 1] = ids[i];
        return out;
    }
}
