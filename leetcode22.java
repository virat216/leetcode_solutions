import java.util.*;

public class Main {

    public static List<String> generateParenthesis(int n) {

        List<String> result = new ArrayList<>();

        backtrack(
            n,
            0,
            0,
            new StringBuilder(),
            result
        );

        return result;
    }

    private static void backtrack(
        int n,
        int open,
        int close,
        StringBuilder current,
        List<String> result
    ) {

        if (open == n && close == n) {
            result.add(current.toString());
            return;
        }

        if (open < n) {

            current.append('(');

            backtrack(
                n,
                open + 1,
                close,
                current,
                result
            );

            current.deleteCharAt(current.length() - 1);
        }

        if (close < open) {

            current.append(')');

            backtrack(
                n,
                open,
                close + 1,
                current,
                result
            );

            current.deleteCharAt(current.length() - 1);
        }
    }

    public static void main(String[] args) {

        int n = 3;

        List<String> result = generateParenthesis(n);

        System.out.println(result);
    }
}
/*
LeetCode 22 - Generate Parentheses


BRUTE FORCE:
------------
Generate every possible string of length 2n using '('
and ')'.

There are:

    2^(2n) = 4^n

possible strings.

For every generated string:
1. Check that it contains n opening brackets.
2. Check that it contains n closing brackets.
3. Check that the balance never becomes negative.
4. If the final balance is 0, add it to the answer.

Most generated strings are invalid, so this performs
a lot of unnecessary work.

Time:  O(4^n * n)
Space: O(n) excluding the output.


OPTIMAL APPROACH:
-----------------
Use BACKTRACKING.

Maintain:

    open  = number of '(' used
    close = number of ')' used

At every step there are two possible choices.

1. Add '('

   We can add '(' while:

       open < n


2. Add ')'

   We can add ')' only when:

       close < open

   This guarantees that the number of closing
   parentheses never exceeds the number of opening
   parentheses.

When:

    open == n && close == n

we have generated one complete valid combination.

Add it to the result.


WHY THIS APPROACH:
------------------
The brute-force approach generates invalid strings first
and checks them afterward.

Backtracking generates only prefixes that can potentially
become valid.

For example, we never generate:

    ")"
    "())"
    ")))"

because close can never become greater than open.

Therefore, invalid branches are pruned immediately.


DRY RUN:
--------
n = 2

Start:

    ""
    open = 0
    close = 0

Add '(':

    "("
    open = 1
    close = 0

Add '(':

    "(("
    open = 2
    close = 0

Add ')':

    "(()"
    open = 2
    close = 1

Add ')':

    "(())"
    open = 2
    close = 2

Add "(())" to result.

Backtrack to:

    "("

Now add ')':

    "()"
    open = 1
    close = 1

Add '(':

    "()("
    open = 2
    close = 1

Add ')':

    "()()"

Add "()()" to result.

Final result:

    (())
    ()()


TIME COMPLEXITY:
----------------
The number of valid parentheses combinations is the
Catalan number:

    C(n) = 1/(n+1) * C(2n,n)

Each generated string has length 2n.

Therefore:

    O(C(n) * n)

which is approximately:

    O(4^n / sqrt(n))


SPACE COMPLEXITY:
-----------------
The recursion depth is at most 2n:

    O(n)

The returned result itself contains:

    O(C(n) * n)

characters.

Including the output:

    O(C(n) * n)


PATTERN:
--------
Backtracking + Pruning

General pattern:

    Choose
      ↓
    Recurse
      ↓
    Undo

For this problem:

    '(' is allowed when open < n
    ')' is allowed when close < open
*/
