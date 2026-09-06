/*
    LeetCode 115 - Distinct Subsequences


    ============================================================
    BRUTE FORCE APPROACH
    ============================================================

    We need to find how many subsequences of s are equal to t.

    At every character of s, we have two choices:

        1. Take the character.
        2. Don't take the character.

    If s[i] == t[j]:

        We can either:

        - Use s[i] to match t[j]
        - Skip s[i]

        Therefore:

        solve(i, j)
            = solve(i - 1, j - 1)
            + solve(i - 1, j)

    If s[i] != t[j]:

        We cannot use s[i] to match t[j].

        Therefore:

        solve(i, j)
            = solve(i - 1, j)


    BASE CASES:

    If t is empty:

        There is exactly one way to form it:

        choose nothing.

        solve(i, 0) = 1

    If s is empty but t is not:

        It is impossible.

        solve(0, j) = 0


    A pure recursive solution has exponential time because
    the same states are calculated repeatedly.

    Time Complexity:
        O(2^m)

    Space Complexity:
        O(m) recursion stack


    ============================================================
    BETTER APPROACH - 2D DP
    ============================================================

    We can store every recursive state.

    dp[i][j] means:

        Number of ways to form the first j characters of t
        using the first i characters of s.

    If:

        s[i - 1] == t[j - 1]

    then:

        dp[i][j] =
            dp[i - 1][j - 1] + dp[i - 1][j]

    Otherwise:

        dp[i][j] =
            dp[i - 1][j]


    Time Complexity:
        O(m * n)

    Space Complexity:
        O(m * n)


    ============================================================
    OPTIMAL APPROACH - 1D DP
    ============================================================

    Notice that:

        dp[i][j]

    only depends on:

        dp[i - 1][j]
        dp[i - 1][j - 1]

    Therefore, we don't need the entire 2D table.

    We can compress it into:

        dp[j]

    where dp[j] represents the number of ways to form the
    first j characters of t using the characters of s processed
    so far.


    INITIALIZATION:

        dp[0] = 1

    because there is exactly one way to form an empty string:
    choose nothing.


    WHEN CHARACTERS MATCH:

        if s[i - 1] == t[j - 1]:

            dp[j] += dp[j - 1]


    This is equivalent to:

        dp[i][j] =
            dp[i - 1][j] + dp[i - 1][j - 1]


    The current dp[j] represents:

        dp[i - 1][j]

    and dp[j - 1] must represent:

        dp[i - 1][j - 1]


    Therefore, we MUST iterate j backwards.


    ============================================================
    WHY DO WE ITERATE BACKWARDS?
    ============================================================

    Suppose we iterate from left to right:

        j = 1, 2, 3, ...

    Then dp[j - 1] may already have been updated using the
    current character of s.

    That would allow the same character of s to be used multiple
    times, which is not allowed in a subsequence.

    So we iterate:

        j = n -> 1

    This keeps dp[j - 1] from the previous row.


    ============================================================
    DRY RUN
    ============================================================

    s = "rabbbit"
    t = "rabbit"


    Initially:

        dp = [1, 0, 0, 0, 0, 0, 0]


    Process the characters of s one by one.


    After processing "r":

        r matches r

        dp = [1, 1, 0, 0, 0, 0, 0]


    Process first "a":

        a matches a

        dp = [1, 1, 1, 0, 0, 0, 0]


    Process first "b":

        b matches b

        dp = [1, 1, 1, 1, 0, 0, 0]


    Process second "b":

        b can be used as another possible choice for
        matching the b's in t.

        dp = [1, 1, 1, 2, 1, 0, 0]


    Process third "b":

        There are now multiple ways to select the required
        b characters.

        dp = [1, 1, 1, 3, 3, 0, 0]


    Continue processing:

        "i"
        "t"


    Eventually:

        dp = [1, 1, 1, 3, 3, 3, 3]


    Therefore:

        dp[6] = 3


    ANSWER:

        3


    ============================================================
    COMPLEXITY
    ============================================================

    Brute Force:

        Time:
            O(2^m)

        Space:
            O(m) recursion stack


    2D DP:

        Time:
            O(m * n)

        Space:
            O(m * n)


    Optimal 1D DP:

        Time:
            O(m * n)

        Space:
            O(n)


    where:

        m = s.length()
        n = t.length()


    ============================================================
    KEY PATTERN TO REMEMBER
    ============================================================

    This is a classic:

        SUBSEQUENCE + COUNTING DP

    When s[i] == t[j]:

        TAKE + DON'T TAKE

    Therefore:

        dp = take + don'tTake

    When they don't match:

        ONLY DON'T TAKE

    And when converting a 2D DP to 1D DP, always ask:

        "Does the current state depend on the previous row?"

    If yes, space optimization may be possible.

    For this problem, iterate the compressed dimension
    BACKWARDS to prevent using the same source character twice.
*/
import java.util.*;

public class Main {

    public static int numDistinct(String s, String t) {

        int m = s.length();
        int n = t.length();

        long[] dp = new long[n + 1];

        // There is exactly one way to form an empty string:
        // select nothing.
        dp[0] = 1;

        for (int i = 1; i <= m; i++) {

            // Traverse backwards so that dp[j - 1]
            // still represents the previous row.
            for (int j = n; j >= 1; j--) {

                if (s.charAt(i - 1) == t.charAt(j - 1)) {

                    // Two possibilities:
                    //
                    // 1. Do not use s[i - 1]
                    // 2. Use s[i - 1] to match t[j - 1]
                    //
                    // The first possibility is already present
                    // in dp[j].
                    //
                    // The second possibility is dp[j - 1].

                    dp[j] += dp[j - 1];
                }
            }
        }

        return (int) dp[n];
    }

    public static void main(String[] args) {

        String s = "rabbbit";
        String t = "rabbit";

        int answer = numDistinct(s, t);

        System.out.println(answer);
    }
}


