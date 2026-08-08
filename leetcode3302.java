/*
============================================================
LEETCODE 3302 - FIND THE LEXICOGRAPHICALLY SMALLEST
VALID SEQUENCE

Problem

We are given two strings:

word1
word2

We need to choose exactly word2.length() indices
from word1.

The indices must be in increasing order.

The characters selected from word1 should form a
string that is "almost equal" to word2.

Almost equal means:

We can change at most ONE character in the selected
string to make it exactly equal to word2.

Among all valid index arrays, return the
lexicographically smallest one.

If no valid sequence exists, return an empty array.

------------------------------------------------------------
Example

word1 = "vbcca"
word2 = "abc"

Possible answer:

[0, 1, 2]

Characters:

word1[0] = 'v'
word1[1] = 'b'
word1[2] = 'c'

Selected string:

"vbc"

Change 'v' -> 'a'

"vbc" becomes "abc"

Therefore [0,1,2] is valid.

------------------------------------------------------------
IMPORTANT

We want the lexicographically smallest ARRAY of
indices.

For example:

[0, 3, 4]

is smaller than

[1, 2, 3]

because the first element 0 < 1.

So we should try to choose the earliest possible
index at every position.

------------------------------------------------------------
MAIN IDEA

We use:

1. Suffix preprocessing
2. Greedy selection

The difficult question is:

If word1[i] does not match word2[j],

can we use our ONE allowed mismatch at index i?

We need to know whether the remaining part of word2
can still be matched after using this mismatch.

For this we preprocess a suffix array.

------------------------------------------------------------
SUFFIX ARRAY

Let dp[i] represent:

How many characters of word2 can be matched,
in order, using word1 starting from index i?

We calculate this from right to left.

Example:

word1 = "vbcca"
word2 = "abc"

We determine how much of the remaining suffix of
word2 can be matched.

This allows us to answer:

"If I use index i as the mismatch,
can I still complete word2?"

------------------------------------------------------------
GREEDY

We scan word1 from left to right.

Suppose we need word2[j].

Case 1:

word1[i] == word2[j]

Then take index i immediately.

Why?

Because it is the smallest possible index and
we don't need to waste our one mismatch.

------------------------------------------------------------
Case 2:

word1[i] != word2[j]

We have two possibilities:

1. Skip i.

2. Use i as our one allowed mismatch.

We should use i if the remaining suffix of word1
is sufficient to match the remaining characters
of word2.

If it is safe,

take i.

Otherwise,

skip i.

------------------------------------------------------------
WHY GREEDY WORKS

The answer must be lexicographically smallest.

Therefore, at every position j, we want the
smallest possible index.

But we cannot blindly take every early index.

We first check whether taking that index still
allows us to complete the rest of word2.

The suffix DP gives exactly that information.

Therefore:

Earliest safe index = optimal choice.

------------------------------------------------------------
DRY RUN

word1 = "vbcca"
word2 = "abc"

word1 indices:

0 1 2 3 4
v b c c a

word2:

0 1 2
a b c

------------------------------------------------------------
i = 0, j = 0

word1[0] = 'v'
word2[0] = 'a'

Mismatch.

We have not used our mismatch yet.

Can we use index 0?

Yes.

The remaining characters can still form:

b -> index 1
c -> index 2

Therefore:

ans[0] = 0

Use our one mismatch.

------------------------------------------------------------
Now

j = 1

i = 1

word1[1] = 'b'
word2[1] = 'b'

Match.

Take it.

ans[1] = 1

------------------------------------------------------------
Now

j = 2

i = 2

word1[2] = 'c'
word2[2] = 'c'

Match.

Take it.

ans[2] = 2

------------------------------------------------------------
Final Answer

[0, 1, 2]

------------------------------------------------------------
SECOND EXAMPLE

word1 = "bacdc"
word2 = "abc"

word1:

0 1 2 3 4
b a c d c

word2:

a b c

------------------------------------------------------------
i = 0

b != a

We could use mismatch here.

But then we need:

b -> from remaining word1

The earliest b is not available after index 0.

So using index 0 would make completion impossible.

Skip index 0.

------------------------------------------------------------
i = 1

a == a

Take it.

ans[0] = 1

------------------------------------------------------------
i = 2

c != b

Use our one mismatch.

ans[1] = 2

------------------------------------------------------------
i = 4

c == c

ans[2] = 4

Final:

[1,2,4]

------------------------------------------------------------
WHY NOT JUST USE A HASHMAP?

We need to find a subsequence.

A HashMap can tell us whether a character exists,
but it doesn't tell us whether the remaining
characters can be selected in the correct order.

The suffix preprocessing handles the ordering
constraint.

------------------------------------------------------------
TIME COMPLEXITY

Let:

n = word1.length()
m = word2.length()

Suffix preprocessing:

O(n)

Greedy traversal:

O(n)

Total:

O(n + m)

------------------------------------------------------------
SPACE COMPLEXITY

O(n)

for the suffix array.

The answer itself requires O(m).

------------------------------------------------------------
KEY PATTERN

String

↓

Subsequence

↓

At most one mismatch

↓

Lexicographically smallest indices

↓

Suffix preprocessing

↓

Greedy
============================================================
*/

import java.util.*;

public class Main {

    public static int[] validSequence(String word1, String word2) {

        int n = word1.length();
        int m = word2.length();

        int[] dp = new int[n + 1];

        int j = m - 1;

        for (int i = n - 1; i >= 0; i--) {

            dp[i] = dp[i + 1];

            if (j >= 0 && word1.charAt(i) == word2.charAt(j)) {
                dp[i] = dp[i + 1] + 1;
                j--;
            }
        }

        int[] ans = new int[m];

        int i = 0;
        j = 0;

        while (i < n && j < m) {

            if (word1.charAt(i) == word2.charAt(j)) {

                ans[j] = i;
                j++;

            } else {

                if (dp[i + 1] >= m - j - 1) {

                    ans[j] = i;
                    j++;
                    i++;

                    break;
                }
            }

            i++;
        }

        if (j < m && i == n) {
            return new int[0];
        }

        while (i < n && j < m) {

            if (word1.charAt(i) == word2.charAt(j)) {

                ans[j] = i;
                j++;
            }

            i++;
        }

        return j == m ? ans : new int[0];
    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.print("Enter word1: ");
        String word1 = sc.nextLine();

        System.out.print("Enter word2: ");
        String word2 = sc.nextLine();

        int[] result = validSequence(word1, word2);

        System.out.println("Answer:");

        System.out.println(Arrays.toString(result));

        sc.close();
    }
}
