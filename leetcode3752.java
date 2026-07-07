/*
============================================================
LEETCODE 3754 - Concatenate Non-Zero Digits and Multiply by Sum I

Problem

Given an integer n,

1. Ignore all the digits equal to 0.
2. Concatenate all the remaining digits to form a new number.
3. Find the sum of all non-zero digits.
4. Return

        concatenatedNumber × sumOfDigits

------------------------------------------------------------
Example

Input:

n = 10203004

Non-zero digits

1 2 3 4

Concatenated Number

1234

Sum

1 + 2 + 3 + 4 = 10

Answer

1234 × 10 = 12340

------------------------------------------------------------
Intuition

The easiest way to access every digit is to convert the
number into a String.

Once converted,

we traverse every character.

If the current digit is not '0',

1. Append it to the new number.
2. Add it to the digit sum.

Finally,

multiply the concatenated number by the sum.

------------------------------------------------------------
Approach

1. Convert integer into String.
2. Traverse every character.
3. Ignore digit '0'.
4. Build the concatenated number.
5. Calculate the sum of non-zero digits.
6. Return

concatenatedNumber × sum

------------------------------------------------------------
Algorithm

1. Convert n to String.
2. Initialize

number = 0
sum = 0

3. Traverse every digit.

4. If digit != '0'

number = number * 10 + digit

sum += digit

5. Return

number × sum

------------------------------------------------------------
Why It Works

Multiplying the previous number by 10 shifts all digits
one place to the left.

Example

number = 123

New digit = 4

number = 123 × 10 + 4

       = 1234

At the same time,

we keep adding every non-zero digit into sum.

Thus,

after one traversal,

we obtain both

• Concatenated Number
• Sum of Digits

The required answer is simply

number × sum.

------------------------------------------------------------
Time Complexity

O(d)

where d is the number of digits.

------------------------------------------------------------
Space Complexity

O(d)

because of the String representation.

------------------------------------------------------------
Functions Used

String.valueOf()

Converts integer into String.

toCharArray()

Converts String into character array for easy traversal.

============================================================
*/

class Solution {

    public long sumAndMultiply(int n) {

        long number = 0;
        long sum = 0;

        String s = String.valueOf(n);

        for (char ch : s.toCharArray()) {

            if (ch != '0') {

                number = number * 10 + (ch - '0');
                sum += (ch - '0');
            }
        }

        return number * sum;
    }
}
