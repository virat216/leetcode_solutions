public class Main {

    public static int longestValidParentheses(String s) {

        int left = 0;
        int right = 0;
        int maxLength = 0;

        // Left -> Right
        for (int i = 0; i < s.length(); i++) {

            if (s.charAt(i) == '(') {
                left++;
            } else {
                right++;
            }

            if (left == right) {
                maxLength = Math.max(maxLength, 2 * right);
            }

            else if (right > left) {
                left = 0;
                right = 0;
            }
        }

        left = 0;
        right = 0;

        // Right -> Left
        for (int i = s.length() - 1; i >= 0; i--) {

            if (s.charAt(i) == '(') {
                left++;
            } else {
                right++;
            }

            if (left == right) {
                maxLength = Math.max(maxLength, 2 * left);
            }

            else if (left > right) {
                left = 0;
                right = 0;
            }
        }

        return maxLength;
    }

    public static void main(String[] args) {

        String s = ")()())";

        System.out.println(longestValidParentheses(s));
    }
}

/*
LeetCode 32 - Longest Valid Parentheses


BRUTE FORCE:
------------
Generate every possible substring.

For each substring:
1. Maintain a balance.
2. '(' increases balance.
3. ')' decreases balance.
4. If balance becomes negative, it is invalid.
5. If final balance == 0, it is valid.

There are O(n^2) substrings and checking each substring
can take O(n).

Time:  O(n^3)
Space: O(1)


OPTIMAL APPROACH:
-----------------
Use TWO PASSES with two counters:

    left  = number of '('
    right = number of ')'

PASS 1: LEFT -> RIGHT
---------------------
For every character:

    '(' -> left++
    ')' -> right++

If:

    left == right

we have a valid segment.

Its length is:

    2 * right

Update maxLength.

If:

    right > left

there are too many closing parentheses.

The current segment cannot become valid, so reset:

    left = 0
    right = 0


WHY DO WE NEED A SECOND PASS?
-----------------------------
Consider:

    "(()"

The first scan cannot detect the final "()"
because there are extra opening parentheses.

Therefore, scan again from RIGHT -> LEFT.


PASS 2: RIGHT -> LEFT
----------------------
Again count '(' and ')'.

If:

    left == right

we have a valid segment.

Update:

    maxLength = max(maxLength, 2 * left)

But while scanning backward, if:

    left > right

there are too many opening parentheses.

So reset:

    left = 0
    right = 0


WHY THIS APPROACH:
------------------
Left-to-right handles segments broken by excess ')'.

Right-to-left handles segments broken by excess '('.

Together, both scans find the longest valid
parentheses substring without using additional memory.


DRY RUN:
--------
s = ")()())"

Left -> Right:

')'
left=0, right=1

right > left
reset


'('
left=1, right=0


')'
left=1, right=1

left == right

maxLength = 2


'('
left=2, right=1


')'
left=2, right=2

left == right

maxLength = 4


')'
left=2, right=3

right > left
reset

Final answer = 4


TIME COMPLEXITY:
----------------
The string is scanned twice.

First pass:  O(n)
Second pass: O(n)

Overall:

    O(n)


SPACE COMPLEXITY:
-----------------
Only three integer variables are required:

    left
    right
    maxLength

Therefore:

    O(1)


PATTERN:
--------
Two Directional Scans + Balance

Left -> Right:
    excess ')' -> reset

Right -> Left:
    excess '(' -> reset

Whenever one directional scan handles one imbalance
but misses the opposite imbalance, consider scanning
from both directions.
*/
