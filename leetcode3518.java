/*
============================================================
LEETCODE 3518 - Smallest Palindromic Rearrangement II

Problem

You are given

1. A palindromic string s.
2. An integer k.

Among all distinct palindromic rearrangements
of s arranged in lexicographical order,

return the kth smallest palindrome.

If fewer than k palindromes exist,

return an empty string.

------------------------------------------------------------
Example

Input

s = "aabb"

k = 2

Possible palindromes

1. abba
2. baab

Output

"baab"

------------------------------------------------------------
Intuition

In Problem 3517,

we only needed the smallest palindrome.

Here,

we need the kth smallest palindrome.

Generating every palindrome and sorting them
would be impossible because the number of
palindromes grows exponentially.

Instead,

construct the answer one character
at a time.

For every possible character,

temporarily place it in the current position
and calculate

How many valid palindromes can still be formed?

If that count is at least k,

that character belongs to the answer.

Otherwise,

skip those palindromes

k = k - count

and try the next character.

This is exactly the same idea as finding
the kth lexicographical permutation.

------------------------------------------------------------
Approach

1. Count the frequency of every character.

2. Remove one occurrence of the odd-frequency
character (if any).

It becomes the middle character.

3. Store half frequencies.

These represent the left half
of the palindrome.

4. Build the left half.

For every position,

try characters from

'a'

to

'z'.

Temporarily choose one.

Count how many palindromes can still be formed.

If

count >= k

choose this character.

Else

k -= count

restore frequency

and try the next character.

5. Mirror the left half.

Append

middle

Reverse(leftHalf)

------------------------------------------------------------
How do we count the number of palindromes?

Suppose

Half frequencies

a = 2

b = 1

c = 1

Total positions

4

Number of arrangements

4!

-----------------
2! × 1! × 1!
-----------------

Instead of computing factorials directly,

we compute combinations gradually.

------------------------------------------------------------
Algorithm

1. Count frequencies.

2. Remove middle character if present.

3. Compute half frequencies.

4. Check whether

Total Ways < k

Return ""

5. Otherwise

Build left half greedily.

6. Append

middle

Reverse(leftHalf)

7. Return answer.

------------------------------------------------------------
Dry Run

Input

s = "aabb"

k = 2

Half

a = 1

b = 1

Possible left halves

ab

ba

-----------------------------------

Try 'a'

Remaining

b

Ways = 1

Ways < k

Skip

k = 1

-----------------------------------

Try 'b'

Ways = 1

Ways >= k

Choose 'b'

-----------------------------------

Remaining

a

Answer Left

ba

Palindrome

baab

------------------------------------------------------------
Why It Works

At every position,

characters are checked in
lexicographical order.

For every candidate,

we know exactly how many
palindromes begin with it.

If

count < k

those palindromes are skipped.

Otherwise,

the current character must belong
to the kth palindrome.

Repeating this process builds
the required palindrome directly
without generating all possibilities.

------------------------------------------------------------
Time Complexity

O(n)

The alphabet size is fixed (26 letters).

Each position checks at most
26 characters.

Combination computation is bounded,
making the total complexity linear.

------------------------------------------------------------
Space Complexity

O(n)

Output string requires O(n).

Frequency arrays use O(1).

------------------------------------------------------------
Functions Used

StringBuilder

Efficient string construction.

reverse()

Creates the mirrored half.

============================================================
*/


class Solution {

    private static final long LIMIT = 1_000_001L;

    public String smallestPalindrome(String s, int k) {

        int[] freq = new int[26];

        for (char c : s.toCharArray()) {
            freq[c - 'a']++;
        }

        char middle = 0;

        for (int i = 0; i < 26; i++) {
            if ((freq[i] & 1) == 1) {
                middle = (char) ('a' + i);
                freq[i]--;
                break;
            }
        }

        int[] half = new int[26];
        int length = 0;

        for (int i = 0; i < 26; i++) {
            half[i] = freq[i] / 2;
            length += half[i];
        }

        if (countWays(half, length) < k) {
            return "";
        }

        StringBuilder left = new StringBuilder();

        while (length > 0) {

            for (int c = 0; c < 26; c++) {

                if (half[c] == 0) {
                    continue;
                }

                half[c]--;

                long ways = countWays(half, length - 1);

                if (ways >= k) {

                    left.append((char) ('a' + c));
                    length--;
                    break;
                }

                k -= ways;
                half[c]++;
            }
        }

        StringBuilder answer = new StringBuilder();

        answer.append(left);

        if (middle != 0) {
            answer.append(middle);
        }

        answer.append(new StringBuilder(left).reverse());

        return answer.toString();
    }

    private long countWays(int[] count, int total) {

        long ways = 1;

        int remaining = total;

        for (int value : count) {

            if (value == 0) {
                continue;
            }

            ways *= combination(remaining, value);

            if (ways > LIMIT) {
                return LIMIT;
            }

            remaining -= value;
        }

        return ways;
    }

    private long combination(int n, int r) {

        if (r > n - r) {
            r = n - r;
        }

        long answer = 1;

        for (int i = 1; i <= r; i++) {

            answer = answer * (n - r + i) / i;

            if (answer > LIMIT) {
                return LIMIT;
            }
        }

        return answer;
    }
}
