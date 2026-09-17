/*
LeetCode 1477 - Find Two Non-overlapping Sub-arrays
Each With Target Sum


BRUTE FORCE:
------------
Generate all possible subarrays and calculate their sums.

Keep the subarrays whose sum equals target.

Compare every pair of valid subarrays.
If they do not overlap, calculate their combined length
and update the minimum answer.

Time Complexity: O(n^3)
Space Complexity: O(n^2) in the worst case for storing
valid subarrays.


OPTIMAL APPROACH:
-----------------
Use a sliding window to find subarrays with sum = target.

Because all array elements are positive:
- Expand the window by moving right.
- If sum > target, shrink from the left.
- If sum == target, a valid subarray is found.

Maintain:

    best[i] = shortest target-sum subarray
              entirely within arr[0...i]

For a valid current subarray [left, right]:

    length = right - left + 1

If left > 0, combine it with the best valid subarray
ending before left:

    best[left - 1] + length

Update the minimum total length.

Also update the shortest valid subarray found so far
and store it in best[right].


WHY THIS APPROACH:
------------------
The sliding window finds valid subarrays in linear time.

The prefix DP stores the shortest valid subarray from
the already-processed part of the array.

Using best[left - 1] guarantees that the previous
subarray ends strictly before the current subarray,
so they cannot overlap.

This avoids checking every possible pair.


DRY RUN:
--------
arr = [3, 2, 2, 4, 3]
target = 3

At index 0:
    window = [3]
    length = 1
    best[0] = 1

At index 4:
    window = [3]
    length = 1

The best valid subarray before index 4 has length 1.

    answer = 1 + 1 = 2

Return 2.


TIME COMPLEXITY:
----------------
Sliding window: O(n)
Prefix DP updates: O(n)

Overall: O(n)


SPACE COMPLEXITY:
-----------------
The best array stores n values.

Overall: O(n)


PATTERN:
--------
Sliding Window + Prefix DP

Find valid subarrays efficiently, remember the best
solution in the processed prefix, and combine only
non-overlapping candidates.
*/
import java.util.Arrays;

public class Main {

    public static int minSumOfLengths(int[] arr, int target) {

        int n = arr.length;
        int INF = Integer.MAX_VALUE;

        int[] best = new int[n];
        Arrays.fill(best, INF);

        int left = 0;
        int sum = 0;
        int minLength = INF;
        int answer = INF;

        for (int right = 0; right < n; right++) {

            sum += arr[right];

            while (sum > target && left <= right) {
                sum -= arr[left];
                left++;
            }

            if (sum == target) {

                int length = right - left + 1;

                if (left > 0 && best[left - 1] != INF) {
                    answer = Math.min(
                        answer,
                        best[left - 1] + length
                    );
                }

                minLength = Math.min(minLength, length);
            }

            best[right] = minLength;
        }

        return answer == INF ? -1 : answer;
    }

    public static void main(String[] args) {

        int[] arr = {3, 2, 2, 4, 3};
        int target = 3;

        System.out.println(minSumOfLengths(arr, target));
    }
}
