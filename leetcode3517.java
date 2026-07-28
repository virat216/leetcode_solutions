/*
============================================================
LEETCODE 3517 - Smallest Palindromic Rearrangement I

Problem

You are given a palindromic string s.

Rearrange its characters to form the
lexicographically smallest palindrome.

Return the resulting palindrome.

------------------------------------------------------------
Example 1

Input

s = "zaza"

Output

"azza"

Explanation

Possible palindromes are

"azza"
"zaaz"

The lexicographically smaller one is

"azza"

------------------------------------------------------------
Example 2

Input

s = "babab"

Output

"abbba"

------------------------------------------------------------
Intuition

Since the given string is already a palindrome,

its characters can always be rearranged to form
another palindrome.

A palindrome consists of

Left Half
+
(Optional Middle Character)
+
Mirror of Left Half

To obtain the lexicographically smallest palindrome,

the left half should contain the smallest possible
characters first.

Therefore,

Count the frequency of every character.

Build the left half from 'a' to 'z'.

If a character has an odd frequency,

keep one occurrence for the middle.

Finally,

append the reverse of the left half.

------------------------------------------------------------
Approach

1. Count the frequency of every character.

2. Traverse characters from

'a'

to

'z'.

3. For every pair of characters,

append one character to the left half.

4. If one character remains,

store it as the middle character.

5. Reverse the left half to obtain
the right half.

6. Return

leftHalf + middle + reversedLeftHalf

------------------------------------------------------------
Algorithm

1. Create frequency array of size 26.

2. Count frequency of every character.

3. Create

StringBuilder firstHalf

char middle

4. Traverse all characters.

While frequency >= 2

Append one character to firstHalf.

Decrease frequency by 2.

If one character remains,

store it as middle.

5. Create answer

firstHalf

+

middle (if exists)

+

reverse(firstHalf)

6. Return answer.

------------------------------------------------------------
Dry Run

Input

"babab"

Frequency

a = 2

b = 3

-----------------------------------

Process 'a'

Pair = 1

firstHalf = "a"

-----------------------------------

Process 'b'

Pair = 1

firstHalf = "ab"

Remaining

b = 1

middle = 'b'

-----------------------------------

Reverse

"ba"

Answer

"ab"
+
"b"
+
"ba"

=

"abbba"

------------------------------------------------------------
Why It Works

A palindrome must have

equal characters on both sides.

For every two identical characters,

one goes to the left half

and the other automatically appears
in the mirrored right half.

Building the left half in alphabetical order
guarantees the lexicographically smallest result.

------------------------------------------------------------
Time Complexity

O(n)

Counting frequencies takes O(n).

Building the palindrome also takes O(n).

------------------------------------------------------------
Space Complexity

O(n)

The output string requires O(n) space.

The frequency array uses constant space.

------------------------------------------------------------
Functions Used

toCharArray()

Converts the string into a character array.

append()

Adds characters to StringBuilder.

reverse()

Reverses the left half to create
the mirrored right half.

============================================================
*/

import java.util.*;

public class Main {

    public static String smallestPalindrome(String s) {

        int[] freq = new int[26];

        for (char ch : s.toCharArray()) {
            freq[ch - 'a']++;
        }

        StringBuilder firstHalf = new StringBuilder();
        char middle = 0;

        for (int i = 0; i < 26; i++) {

            while (freq[i] >= 2) {
                firstHalf.append((char) ('a' + i));
                freq[i] -= 2;
            }

            if (freq[i] == 1) {
                middle = (char) ('a' + i);
            }
        }

        StringBuilder result = new StringBuilder();

        result.append(firstHalf);

        if (middle != 0) {
            result.append(middle);
        }

        result.append(new StringBuilder(firstHalf).reverse());

        return result.toString();
    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.print("Enter the string: ");

        String s = sc.next();

        System.out.println("Smallest Palindrome = " + smallestPalindrome(s));

        sc.close();
    }
}
