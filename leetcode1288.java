/*
============================================================
LEETCODE 1288 - Remove Covered Intervals

Problem

Given an array of intervals where

interval[i] = [start, end]

Return the number of intervals that are NOT covered by
another interval.

An interval [a,b] is covered by [c,d] if

c <= a
and
b <= d

------------------------------------------------------------
Example

Input:

[[1,4],[3,6],[2,8]]

Interval [3,6] is completely inside [2,8].

Remaining intervals

[1,4]
[2,8]

Answer = 2

------------------------------------------------------------
Intuition

To determine whether an interval is covered, we first sort
the intervals.

Sorting Rules:

1. Sort by starting point in ascending order.
2. If two intervals have the same start,
   sort by ending point in descending order.

Why descending order?

Example

[1,8]
[1,5]

If [1,5] comes first, we might incorrectly count it as an
independent interval.

By placing the larger interval first, the smaller interval
will automatically be detected as covered.

After sorting, we only need to keep track of the farthest
ending point seen so far.

If the current interval's end is less than or equal to this
maximum end, then the current interval is covered.

Otherwise,

it becomes a new valid interval.

------------------------------------------------------------
Approach

1. Sort intervals by:
      - Start ascending.
      - End descending (if starts are equal).

2. Initialize

      maxEnd = 0

3. Traverse all intervals.

4. If

      currentEnd > maxEnd

      Current interval is NOT covered.

      Increase answer.

      Update maxEnd.

5. Otherwise,

      Current interval is covered.

6. Return answer.

------------------------------------------------------------
Algorithm

1. Sort intervals.
2. Initialize

      count = 0
      maxEnd = 0

3. Traverse every interval.

4. If

      interval[1] > maxEnd

      count++
      maxEnd = interval[1]

5. Return count.

------------------------------------------------------------
Why It Works

After sorting,

every interval that starts later can only be covered by
one of the previous intervals.

Since we always keep the maximum ending point encountered,

If

currentEnd <= maxEnd

then a previous interval already starts before (or at the
same position) and ends after (or at the same position).

Therefore,

the current interval is completely covered.

------------------------------------------------------------
Time Complexity

Sorting           : O(n log n)

Traversal         : O(n)

Overall           : O(n log n)

------------------------------------------------------------
Space Complexity

Sorting           : O(1)

(Java's Arrays.sort() may use O(log n) recursion stack.)

Overall           : O(log n)

------------------------------------------------------------
Functions Used

Arrays.sort()

Sorts the intervals according to our custom comparator.

Math.max()

(Not used in this solution.)

============================================================
*/

import java.util.*;

public class Main {

    public static int removeCoveredIntervals(int[][] intervals) {

        Arrays.sort(intervals, (a, b) -> {

            if (a[0] == b[0]) {
                return b[1] - a[1];
            }

            return a[0] - b[0];
        });

        int count = 0;
        int maxEnd = 0;

        for (int[] interval : intervals) {

            if (interval[1] > maxEnd) {

                count++;
                maxEnd = interval[1];
            }
        }

        return count;
    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.print("Enter number of intervals: ");
        int n = sc.nextInt();

        int[][] intervals = new int[n][2];

        System.out.println("Enter intervals:");

        for (int i = 0; i < n; i++) {

            intervals[i][0] = sc.nextInt();
            intervals[i][1] = sc.nextInt();
        }

        int ans = removeCoveredIntervals(intervals);

        System.out.println("Remaining Intervals = " + ans);

        sc.close();
    }
}





