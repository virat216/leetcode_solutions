/*
    LeetCode 3871 - Count Commas in Range II


    ============================================================
    BRUTE FORCE APPROACH
    ============================================================

    Iterate through every number from 1 to n.

    For each number, determine how many commas it contains
    and add that number to the answer.

    For example:

        999       -> 0 commas
        1000      -> 1 comma
        1234567   -> 2 commas


    This works logically, but n can be as large as:

        10^15

    Iterating through 10^15 numbers is impossible.

    Time Complexity:

        O(n)

    Space Complexity:

        O(1)


    ============================================================
    OPTIMAL APPROACH
    ============================================================

    Instead of processing every number individually, count
    each comma position separately.


    First comma:

        Every number >= 1000 has a first comma.

        Contribution:

            n - 1000 + 1


    Second comma:

        Every number >= 1,000,000 has a second comma.

        Contribution:

            n - 1,000,000 + 1


    Third comma:

        Every number >= 1,000,000,000 has a third comma.

        Contribution:

            n - 1,000,000,000 + 1


    Therefore, the thresholds are:

        1000
        1000000
        1000000000
        1000000000000
        ...


    Each threshold is obtained by multiplying the previous
    threshold by 1000.


    For every threshold x <= n:

        answer += n - x + 1


    Then:

        x *= 1000


    ============================================================
    WHY DOES THIS WORK?
    ============================================================

    A number gets one additional comma whenever it reaches
    another power of 1000.

    For example:

        999             -> 0 commas
        1,000           -> 1 comma
        1,000,000       -> 2 commas
        1,000,000,000   -> 3 commas


    Consider:

        n = 1,500,000


    First comma:

        Numbers from 1,000 to 1,500,000

        Count:

            1,500,000 - 1,000 + 1
            = 1,499,001


    Second comma:

        Numbers from 1,000,000 to 1,500,000

        Count:

            1,500,000 - 1,000,000 + 1
            = 500,001


    Total:

        1,499,001 + 500,001
        = 1,999,002


    Notice that 1,000,000 is counted in BOTH groups.

    This is correct because:

        1,000,000

    contains TWO commas.


    ============================================================
    DRY RUN
    ============================================================

    Example:

        n = 1,002


    Initially:

        ans = 0
        x = 1000


    ------------------------------------------------------------
    ITERATION 1
    ------------------------------------------------------------

        x = 1000

        1000 <= 1002

        Numbers containing the first comma:

            1000
            1001
            1002

        Count:

            1002 - 1000 + 1
            = 3


        ans = 0 + 3
            = 3


    Next threshold:

        x = 1000 * 1000
          = 1,000,000


    ------------------------------------------------------------
    ITERATION 2
    ------------------------------------------------------------

        x = 1,000,000

        1,000,000 > 1,002

        Stop.


    Final answer:

        3


    ============================================================
    EDGE CASES
    ============================================================


    n = 998

        First threshold = 1000

        1000 > 998

        No iteration occurs.

        Answer = 0


    ------------------------------------------------------------

    n = 1000

        x = 1000

        contribution:

            1000 - 1000 + 1
            = 1

        Answer = 1


    ------------------------------------------------------------

    n = 1,000,000

        First threshold:

            1000

        contribution:

            1,000,000 - 1,000 + 1
            = 999,001


        Second threshold:

            1,000,000

        contribution:

            1,000,000 - 1,000,000 + 1
            = 1


        Total:

            999,001 + 1
            = 999,002


    This is correct because:

        1,000,000

    contains two commas.


    ============================================================
    WHY USE long IN JAVA?
    ============================================================

    The constraint allows:

        n <= 10^15

    which is much larger than Integer.MAX_VALUE:

        2,147,483,647


    Therefore:

        int

    is NOT sufficient.

    Use:

        long


    The answer can also exceed the range of int, so the
    accumulator must also be long.


    ============================================================
    COMPLEXITY
    ============================================================

    Brute Force:

        Time:
            O(n)

        Space:
            O(1)


    Optimal:

        Time:
            O(log n)

        Space:
            O(1)


    The threshold is multiplied by 1000 after every iteration:

        1000
        1000000
        1000000000
        1000000000000
        ...


    Therefore, only O(log n) iterations are required.


    ============================================================
    KEY PATTERN TO REMEMBER
    ============================================================

    This is a MATH / RANGE COUNTING problem.

    Instead of counting commas for every number:

        Count how many numbers contain the 1st comma.
        Count how many numbers contain the 2nd comma.
        Count how many numbers contain the 3rd comma.
        ...

    The thresholds are powers of 1000:

        1000, 1000000, 1000000000, ...


    For every threshold x:

        contribution = n - x + 1


    Final formula:

        answer =
            SUM(n - x + 1)

    for every:

        x = 1000, 1000000, 1000000000, ...

        where x <= n.


    Final Complexity:

        Time  = O(log n)
        Space = O(1)
*/
import java.util.*;

public class Main {

    public static long countCommas(long n) {

        long ans = 0;
        long x = 1000;

        while (x <= n) {

            // Every number from x to n contains
            // one additional comma at this threshold.
            ans += n - x + 1;

            // Prevent overflow while calculating x * 1000.
            if (x > n / 1000) {
                break;
            }

            x *= 1000;
        }

        return ans;
    }

    public static void main(String[] args) {

        long n = 1002;

        long answer = countCommas(n);

        System.out.println(answer);
    }
}
