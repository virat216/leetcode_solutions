/*
    LeetCode 3870 - Count Commas in Range


    ============================================================
    BRUTE FORCE APPROACH
    ============================================================

    Iterate through every number from 1 to n.

    For every number, determine how many commas are required
    when writing that number in standard number formatting.

    Example:

        999   -> 0 commas
        1000  -> 1 comma
        12345 -> 1 comma

    Add the comma count of every number to the answer.

    Since n <= 100000, this approach is acceptable.

    Time Complexity:
        O(n)

    Space Complexity:
        O(1)


    ============================================================
    OPTIMAL APPROACH
    ============================================================

    Observe where commas start appearing.

        1 ... 999
            -> 0 commas

        1000 ... 100000
            -> exactly 1 comma

    The constraint is:

        n <= 100000

    Therefore, no number in the range [1, n] can contain
    more than one comma.

    So we only need to count how many numbers are greater
    than or equal to 1000.

    Those numbers are:

        1000, 1001, 1002, ..., n

    Number of integers in this inclusive range:

        n - 1000 + 1

    Simplifying:

        n - 999


    If n < 1000:

        There are no numbers containing commas.

        Answer = 0


    Therefore the complete formula is:

        max(0, n - 999)


    ============================================================
    DRY RUN
    ============================================================

    Example:

        n = 1002


    Numbers from 1 to 999:

        0 commas


    Numbers from 1000 to 1002:

        1000 -> 1 comma
        1001 -> 1 comma
        1002 -> 1 comma


    Total:

        1 + 1 + 1 = 3


    Using the formula:

        max(0, n - 999)

        = max(0, 1002 - 999)

        = max(0, 3)

        = 3


    Answer:

        3


    ============================================================
    EDGE CASE
    ============================================================

    n = 998

        max(0, 998 - 999)

        = max(0, -1)

        = 0


    Correct because numbers from 1 to 998 contain no commas.


    Another edge case:

    n = 1000

        max(0, 1000 - 999)

        = 1


    Correct because only "1,000" contains a comma.


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
            O(1)

        Space:
            O(1)


    ============================================================
    KEY PATTERN
    ============================================================

    When a problem asks you to process a range, first look
    for a mathematical boundary where the behavior changes.

    Here:

        1 - 999       -> 0 commas
        1000 - 100000 -> 1 comma

    Because of the constraint n <= 100000, there is only one
    relevant boundary.

    Therefore, instead of iterating through the range, simply
    count how many numbers are in [1000, n].

        answer = max(0, n - 999)
*/
import java.util.*;

public class Main {

    public static int countCommas(int n) {

        return Math.max(0, n - 999);
    }

    public static void main(String[] args) {

        int n = 1002;

        int answer = countCommas(n);

        System.out.println(answer);
    }
}


