/*
    LeetCode 3904 - Smallest Stable Index II

    ------------------------------------------------------------
    PROBLEM
    ------------------------------------------------------------

    For every index i:

        instability(i)
        = max(nums[0...i]) - min(nums[i...n-1])

    An index is stable if:

        instability(i) <= k

    We need to return the smallest stable index.

    If no stable index exists, return -1.


    ------------------------------------------------------------
    BRUTE FORCE APPROACH
    ------------------------------------------------------------

    For every index i:

    1. Find the maximum from index 0 to i.
    2. Find the minimum from index i to n-1.
    3. Calculate:

           max - min

    4. If the result <= k, return i.

    Example:

        nums = [5, 0, 1, 4]
        k = 3

    At every index we repeatedly scan parts of the array.

    This causes repeated work.

    Time Complexity:
        O(n^2)

    Space Complexity:
        O(1)


    ------------------------------------------------------------
    OPTIMAL APPROACH
    ------------------------------------------------------------

    Notice that for every index we need two things:

        1. max(nums[0...i])
        2. min(nums[i...n-1])

    Instead of calculating them repeatedly, precompute the
    suffix minimum.

    suffixMin[i] represents:

        minimum value from index i to n-1


    Build suffixMin from right to left:

        suffixMin[n-1] = nums[n-1]

        suffixMin[i] =
            min(nums[i], suffixMin[i+1])


    Example:

        nums = [5, 0, 1, 4]

        suffixMin:

        index       0   1   2   3
        nums        5   0   1   4
        suffixMin   0   0   1   4


    We do NOT need a prefix maximum array.

    While traversing from left to right, maintain:

        leftMax

    At every index:

        leftMax = max(leftMax, nums[i])


    Therefore:

        instability =
            leftMax - suffixMin[i]


    If:

        instability <= k

    then i is stable.

    Since we check indices from left to right, the first stable
    index is automatically the smallest stable index.


    ------------------------------------------------------------
    WHY THIS APPROACH?
    ------------------------------------------------------------

    The brute-force solution repeatedly calculates maximums
    and minimums that overlap heavily.

    For example:

        max(nums[0...2])
        max(nums[0...3])

    share almost all their elements.

    Similarly:

        min(nums[2...n-1])
        min(nums[3...n-1])

    also share most of their elements.

    So instead of recalculating these values, we store the
    information once and reuse it.

    Prefix maximum can be maintained using one variable.

    Suffix minimum needs to be known before the left-to-right
    scan, so we store it in an array.


    ------------------------------------------------------------
    DRY RUN
    ------------------------------------------------------------

    Input:

        nums = [5, 0, 1, 4]
        k = 3


    STEP 1: Build suffix minimum

        Start from the right.

        suffixMin[3] = 4

        i = 2:

            suffixMin[2]
            = min(1, 4)
            = 1

        i = 1:

            suffixMin[1]
            = min(0, 1)
            = 0

        i = 0:

            suffixMin[0]
            = min(5, 0)
            = 0


        Result:

            nums:
                5   0   1   4

            suffixMin:
                0   0   1   4


    STEP 2: Scan from left to right

    Initially:

        leftMax = Integer.MIN_VALUE


    i = 0

        nums[0] = 5

        leftMax = max(MIN_VALUE, 5)
                = 5

        suffixMin[0] = 0

        instability = 5 - 0
                    = 5

        5 <= 3 ? NO


    i = 1

        nums[1] = 0

        leftMax = max(5, 0)
                = 5

        suffixMin[1] = 0

        instability = 5 - 0
                    = 5

        5 <= 3 ? NO


    i = 2

        nums[2] = 1

        leftMax = max(5, 1)
                = 5

        suffixMin[2] = 1

        instability = 5 - 1
                    = 4

        4 <= 3 ? NO


    i = 3

        nums[3] = 4

        leftMax = max(5, 4)
                = 5

        suffixMin[3] = 4

        instability = 5 - 4
                    = 1

        1 <= 3 ? YES


    Therefore:

        answer = 3


    ------------------------------------------------------------
    COMPLEXITY
    ------------------------------------------------------------

    Building suffixMin:

        O(n)

    Finding the first stable index:

        O(n)

    Total:

        Time Complexity = O(n)

    Space Complexity = O(n)

    We only need one additional suffix minimum array.

*/
import java.util.*;

public class Main {

    public static int firstStableIndex(int[] nums, int k) {

        int n = nums.length;

        // Store minimum from index i to n - 1
        int[] suffixMin = new int[n];

        suffixMin[n - 1] = nums[n - 1];

        // Build suffix minimum array
        for (int i = n - 2; i >= 0; i--) {
            suffixMin[i] = Math.min(suffixMin[i + 1], nums[i]);
        }

        // Maintain maximum from index 0 to i
        int leftMax = Integer.MIN_VALUE;

        // Find the first stable index
        for (int i = 0; i < n; i++) {

            leftMax = Math.max(leftMax, nums[i]);

            int instability = leftMax - suffixMin[i];

            if (instability <= k) {
                return i;
            }
        }

        return -1;
    }

    public static void main(String[] args) {

        int[] nums = {5, 0, 1, 4};
        int k = 3;

        int answer = firstStableIndex(nums, k);

        System.out.println(answer);
    }
}


