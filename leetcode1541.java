public class Main {

    public static int minInsertions(String s) {

        int insertions = 0;
        int need = 0;

        for (int i = 0; i < s.length(); i++) {

            char c = s.charAt(i);

            if (c == '(') {

                need += 2;

                if (need % 2 == 1) {
                    insertions++;
                    need--;
                }

            } else {

                need--;

                if (need == -1) {
                    insertions++;
                    need = 1;
                }
            }
        }

        return insertions + need;
    }

    public static void main(String[] args) {

        String s = "(()))";

        System.out.println(minInsertions(s));
    }
}
/*
LeetCode 1541 - Minimum Insertions to Balance a Parentheses String


BRUTE FORCE:
------------
Try inserting parentheses until the string satisfies the
special balancing rule.

Each '(' must be matched by two closing parentheses '))'.

Searching through possible insertion positions and
combinations is inefficient.

A greedy counting strategy solves the problem in O(n).


OPTIMAL APPROACH:
-----------------
Maintain:

    insertions = number of insertions already required
    need       = number of closing parentheses still needed

When encountering '(':

    need += 2

The required number of closing parentheses must be even.
If need becomes odd:

    insertions++
    need--

When encountering ')':

    need--

If need becomes -1, this closing parenthesis has no
opening parenthesis that requires it.

Insert an opening parenthesis and account for the
one additional closing parenthesis it still requires:

    insertions++
    need = 1

After processing the complete string, insert the
remaining required closing parentheses:

    answer = insertions + need


WHY THIS APPROACH:
------------------
Every insertion counted by the algorithm is necessary
to fix an imbalance that cannot be resolved by the
remaining input.

The algorithm tracks requirements instead of actually
modifying the string.


DRY RUN:
--------
s = "(()))"

Initially:
    insertions = 0
    need = 0

First '(':
    need = 2

Second '(':
    need = 4

First ')':
    need = 3

Second ')':
    need = 2

Third ')':
    need = 1

End:
    answer = insertions + need
           = 0 + 1
           = 1


TIME COMPLEXITY:
----------------
Each character is processed once.

    O(n)


SPACE COMPLEXITY:
-----------------
Only two integer variables are maintained.

    O(1)


PATTERN:
--------
Greedy + Counting Required Closings

When each opening symbol requires a fixed number of
closing symbols, track the remaining requirement and
insert only when an imbalance becomes unavoidable.
*/
