public class Main {

    public static int scoreOfParentheses(String s) {

        int depth = 0;
        int score = 0;

        for (int i = 0; i < s.length(); i++) {

            if (s.charAt(i) == '(') {

                depth++;

            } else {

                // Current pair is "()"
                if (s.charAt(i - 1) == '(') {
                    score += 1 << (depth - 1);
                }

                depth--;
            }
        }

        return score;
    }

    public static void main(String[] args) {

        String s = "(()(()))";

        System.out.println(scoreOfParentheses(s));
    }
}

/*
LeetCode 856 - Score of Parentheses


BRUTE FORCE:
------------
Recursively evaluate the parentheses structure.

For every balanced group:

    ()      -> 1
    (A)     -> 2 * score(A)
    AB      -> score(A) + score(B)

A straightforward implementation can repeatedly process
substrings and find matching parentheses.

Time:  O(n^2)
Space: O(n)


OPTIMAL APPROACH:
-----------------
Use a single pass and maintain the current nesting depth.

Maintain:

    depth = current number of unmatched '('

Rules:

    '(' -> depth++

    ')' -> depth--

Whenever we encounter:

    "()"

we have found a primitive parentheses pair.

If this primitive pair occurs at depth d,
its contribution is:

    2^(d - 1)


WHY?
----
The primitive "()" has score:

    1

If it is wrapped once:

    (())

its score becomes:

    2

If it is wrapped twice:

    ((()))

its score becomes:

    4

Therefore a primitive "()" at depth d contributes:

    2^(d - 1)


In Java:

    1 << (depth - 1)

is equivalent to:

    2^(depth - 1)


We only add a contribution when:

    s.charAt(i - 1) == '('

because that means the current ')' forms the primitive
pair "()".

A ')' following another ')' does not directly contribute
a new primitive score.


DRY RUN:
--------
s = "(()(()))"

Characters and important states:

    (       depth = 1
    (       depth = 2
    ()      contribution = 2^(2-1) = 2
    (       depth = 2
    (       depth = 3
    ()      contribution = 2^(3-1) = 4
    )       depth decreases
    )       depth decreases

Total:

    2 + 4 = 6


TIME COMPLEXITY:
----------------
The string is scanned exactly once.

    O(n)


SPACE COMPLEXITY:
-----------------
Only depth and score are maintained.

    O(1)


PATTERN:
--------
Nested Structure + Depth

For parentheses problems where nesting changes the
value of a component:

    '(' -> increase depth
    primitive '()' -> calculate contribution using depth
    ')' -> decrease depth

The important observation is that nesting depth tells
us how many times the inner score has been doubled.
*/
