/*
    LeetCode 940 - Distinct Subsequences II


    ============================================================
    BRUTE FORCE APPROACH
    ============================================================

    For every character we have two choices:

        1. Include the character.
        2. Exclude the character.

    Therefore, there can be up to:

        2^n

    subsequences.

    We could store all generated subsequences in a HashSet
    to remove duplicates.

    However, generating and storing all subsequences is
    exponential and therefore too slow.

    Time Complexity:

        O(2^n * n)

    because there can be O(2^n) subsequences and constructing
    a subsequence can take O(n).

    Space Complexity:

        O(2^n * n)


    ============================================================
    OPTIMAL APPROACH
    ============================================================

    Instead of generating every subsequence, count them.

    Maintain:

        end[c]

    where end[c] represents the number of distinct non-empty
    subsequences that currently end with character c.

    Also maintain:

        total

    which represents the total number of distinct non-empty
    subsequences.


    Suppose the current character is c.

    Every existing subsequence can append c.

    Also, c itself can form a new subsequence.

    Therefore:

        newCount = total + 1


    HOWEVER, if c has appeared before, some of these subsequences
    are duplicates.

    The old value:

        end[c]

    represents exactly the subsequences that were already created
    ending with c during the previous occurrence.

    Therefore:

        total =
            total
            + newCount
            - end[c]


    Then update:

        end[c] = newCount


    The modulo is applied because the number of subsequences can
    become extremely large.


    ============================================================
    DRY RUN
    ============================================================

    Example:

        s = "aaa"


    Initially:

        total = 0

        end[a] = 0


    ------------------------------------------------------------
    FIRST 'a'
    ------------------------------------------------------------

        newCount = total + 1
                  = 0 + 1
                  = 1

        total = total + newCount - end[a]
              = 0 + 1 - 0
              = 1

        end[a] = 1


    Distinct subsequences:

        "a"

    total = 1


    ------------------------------------------------------------
    SECOND 'a'
    ------------------------------------------------------------

        newCount = total + 1
                  = 1 + 1
                  = 2

    Without duplicate removal, we would have:

        "a"
        "aa"

    But the old "a" already existed.

    Since:

        end[a] = 1

    remove it:

        total = 1 + 2 - 1
              = 2

        end[a] = 2


    Distinct subsequences:

        "a"
        "aa"

    total = 2


    ------------------------------------------------------------
    THIRD 'a'
    ------------------------------------------------------------

        newCount = total + 1
                  = 2 + 1
                  = 3

        end[a] = 2

    Therefore:

        total = 2 + 3 - 2
              = 3

        end[a] = 3


    Distinct subsequences:

        "a"
        "aa"
        "aaa"

    Answer:

        3


    ============================================================
    ANOTHER QUICK EXAMPLE
    ============================================================

    s = "abc"


    Start:

        total = 0


    Process 'a':

        newCount = 1

        total = 0 + 1 - 0
              = 1

        subsequences:
            a


    Process 'b':

        newCount = 1 + 1
                  = 2

        total = 1 + 2 - 0
              = 3

        subsequences:
            a
            b
            ab


    Process 'c':

        newCount = 3 + 1
                  = 4

        total = 3 + 4 - 0
              = 7

        subsequences:
            a
            b
            c
            ab
            ac
            bc
            abc


    Answer:

        7


    ============================================================
    WHY THIS IS OPTIMAL
    ============================================================

    We process every character exactly once.

    For each character we perform only O(1) work.

    The alphabet contains only 26 lowercase English letters,
    so the end[] array has constant size.


    Time Complexity:

        O(n)


    Space Complexity:

        O(26)

        = O(1)


    ============================================================
    KEY PATTERN TO REMEMBER
    ============================================================

    This is a:

        DISTINCT SUBSEQUENCES + DUPLICATE REMOVAL

    problem.

    The most important formula is:

        newCount = total + 1

        total =
            total + newCount - end[currentCharacter]

        end[currentCharacter] = newCount


    The important idea is:

        Every new character can create new subsequences by
        appending itself to every existing subsequence.

        BUT if that character appeared before, some of those
        subsequences have already been counted.

        So subtract the old contribution of that character.


    Final Complexity:

        Time  = O(n)
        Space = O(1)
*/
import java.util.*;

public class Main {

    public static int distinctSubseqII(String s) {

        final long MOD = 1_000_000_007L;

        long[] end = new long[26];

        long total = 0;

        for (char ch : s.toCharArray()) {

            int index = ch - 'a';

            // Number of new subsequences ending with ch
            long newCount = (total + 1) % MOD;

            // Remove duplicates created by the previous
            // occurrence of the same character.
            total = (total + newCount - end[index] + MOD) % MOD;

            // Replace the old count for this character.
            end[index] = newCount;
        }

        return (int) total;
    }

    public static void main(String[] args) {

        String s = "aaa";

        int answer = distinctSubseqII(s);

        System.out.println(answer);
    }
}


