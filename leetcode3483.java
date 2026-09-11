/*
    LeetCode 3483 - Unique 3-Digit Even Numbers


    ============================================================
    BRUTE FORCE APPROACH
    ============================================================

    Choose three different positions from the input array:

        i = hundreds position
        j = tens position
        k = units position

    Make sure:

        i != j
        i != k
        j != k

    Then check:

        1. digits[i] != 0
           because a 3-digit number cannot start with zero.

        2. digits[k] % 2 == 0
           because the number must be even.

    Construct:

        digits[i] * 100
        + digits[j] * 10
        + digits[k]

    Store every valid number in a HashSet.

    The HashSet removes duplicate numbers when the input
    contains duplicate digits.


    Time Complexity:

        O(n^3)

    Space Complexity:

        O(number of distinct valid numbers)


    ============================================================
    OPTIMAL APPROACH
    ============================================================

    We don't really care about the positions of the input
    digits.

    We only care about how many copies of every digit exist.

    Therefore, create:

        freq[10]

    where:

        freq[d] = number of times digit d appears.


    Example:

        digits = [1, 2, 2, 4, 0]

        freq:

            0 -> 1
            1 -> 1
            2 -> 2
            3 -> 0
            4 -> 1


    Now construct the number position by position.


    ------------------------------------------------------------
    STEP 1 - HUNDREDS DIGIT
    ------------------------------------------------------------

    A 3-digit number cannot start with zero.

    Therefore:

        hundreds = 1 ... 9

    If freq[hundreds] == 0, we cannot use it.

    Once selected:

        freq[hundreds]--


    ------------------------------------------------------------
    STEP 2 - TENS DIGIT
    ------------------------------------------------------------

    The tens digit can be anything:

        0 ... 9

    If available:

        freq[tens]--

    We temporarily remove it because each occurrence can
    only be used once.


    ------------------------------------------------------------
    STEP 3 - UNITS DIGIT
    ------------------------------------------------------------

    The number must be even.

    Therefore the units digit must be:

        0, 2, 4, 6, or 8

    If:

        freq[units] > 0

    then we can form one distinct number.

    We increment the answer by 1.


    ============================================================
    WHY DOES THIS HANDLE DUPLICATES?
    ============================================================

    Suppose:

        digits = [0, 2, 2]

    Frequency:

        0 -> 1
        2 -> 2


    Possible numbers:

        202
        220


    When choosing:

        hundreds = 2
        tens = 0
        units = 2

    freq[2] is still positive, so:

        202

    is valid.


    When choosing:

        hundreds = 2
        tens = 2
        units = 0

    the second copy of 2 allows:

        220

    to be formed.


    We don't count the same number multiple times because
    we enumerate DIGIT VALUES, not input indices.


    ============================================================
    DRY RUN
    ============================================================

    Input:

        digits = [1, 2, 3, 4]


    Frequency:

        0 -> 0
        1 -> 1
        2 -> 1
        3 -> 1
        4 -> 1


    Choose hundreds = 1.

    Remaining:

        2, 3, 4


    Tens can be:

        2

    Units can be:

        4

    Number:

        124


    Tens = 3:

        134


    Tens = 4:

        142

    etc.


    Repeating for every possible non-zero hundreds digit
    gives:

        124
        132
        134
        142
        214
        234
        312
        314
        324
        342
        412
        432


    Total:

        12


    ============================================================
    ANOTHER DRY RUN
    ============================================================

    Input:

        digits = [0, 2, 2]


    Frequency:

        0 -> 1
        2 -> 2


    Choose hundreds = 2.

    freq[2] becomes:

        1


    Choose tens = 0.

    freq[0] becomes:

        0


    Possible units:

        0 -> unavailable
        2 -> available

    Therefore:

        202

    is counted.


    Restore tens.


    Choose tens = 2.

    There is still one 2 available for the units position.

    Possible units:

        0 -> available

    Therefore:

        220

    is counted.


    Answer:

        2


    ============================================================
    IMPORTANT OBSERVATION
    ============================================================

    The constraints are:

        3 <= digits.length <= 10

        0 <= digits[i] <= 9

    There are only 10 possible digit values.

    Therefore our loops are bounded by:

        9 × 10 × 5

    regardless of how large the input becomes.


    So the digit-enumeration part is O(1).

    Building the frequency array takes O(n).


    ============================================================
    COMPLEXITY
    ============================================================

    Brute Force:

        Time:
            O(n^3)

        Space:
            O(number of distinct numbers)


    Optimal:

        Frequency construction:
            O(n)

        Digit enumeration:
            O(9 * 10 * 5)
            = O(1)

        Total:

            O(n)

        Space:

            O(10)
            = O(1)


    ============================================================
    KEY PATTERN TO REMEMBER
    ============================================================

    When the input consists of digits from 0 to 9 and the
    problem involves forming numbers:

        Think FREQUENCY ARRAY.

    Instead of working with input indices:

        [i, j, k]

    work with digit frequencies:

        freq[0 ... 9]


    For this problem remember:

        Hundreds:
            1 - 9

        Tens:
            0 - 9

        Units:
            0, 2, 4, 6, 8


    And temporarily decrement the frequency whenever a digit
    is used.

*/
import java.util.*;

public class Main {

    public static int totalNumbers(int[] digits) {

        int[] freq = new int[10];

        // Count how many times each digit occurs.
        for (int digit : digits) {
            freq[digit]++;
        }

        int count = 0;

        // Hundreds digit cannot be zero.
        for (int hundreds = 1; hundreds <= 9; hundreds++) {

            if (freq[hundreds] == 0) {
                continue;
            }

            // Use one occurrence of hundreds digit.
            freq[hundreds]--;

            // Tens digit can be anything from 0 to 9.
            for (int tens = 0; tens <= 9; tens++) {

                if (freq[tens] == 0) {
                    continue;
                }

                // Use one occurrence of tens digit.
                freq[tens]--;

                // Units digit must be even.
                for (int units = 0; units <= 8; units += 2) {

                    if (freq[units] > 0) {
                        count++;
                    }
                }

                // Restore tens digit.
                freq[tens]++;
            }

            // Restore hundreds digit.
            freq[hundreds]++;
        }

        return count;
    }

    public static void main(String[] args) {

        int[] digits = {1, 2, 3, 4};

        int answer = totalNumbers(digits);

        System.out.println(answer);
    }
}
