/*
============================================================
LEETCODE 3622 - CHECK DIVISIBILITY BY DIGIT SUM AND PRODUCT

Problem

Given a positive integer n,

calculate:

1. Sum of all digits of n
2. Product of all digits of n

Then check whether n is divisible by:

digit sum + digit product

Return:

true  -> if divisible
false -> otherwise

------------------------------------------------------------
EXAMPLE

Input:

n = 123

Digits:

1, 2, 3

Digit sum:

1 + 2 + 3 = 6

Digit product:

1 * 2 * 3 = 6

Therefore:

sum + product = 12

Now check:

123 % 12

Since:

123 % 12 != 0

Answer:

false

------------------------------------------------------------
INTUITION

We need to extract every digit of n.

For a number:

1234

We can extract digits from right to left:

1234 % 10 = 4
123  % 10 = 3
12   % 10 = 2
1    % 10 = 1

After extracting a digit, remove it using:

n = n / 10

------------------------------------------------------------
DIGIT EXTRACTION

Suppose:

temp = 123

First digit:

int digit = temp % 10;

digit = 3

Remove 3:

temp /= 10;

temp = 12

Next:

digit = 2

Then:

digit = 1

Eventually:

temp = 0

------------------------------------------------------------
CALCULATING DIGIT SUM

Initially:

sum = 0

For every digit:

sum += digit;

For:

123

we get:

sum = 0
sum = 3
sum = 5
sum = 6

Final:

sum = 6

------------------------------------------------------------
CALCULATING DIGIT PRODUCT

Initially:

product = 1

Why 1?

Because 1 is the multiplicative identity.

For:

123

we get:

product = 1
product = 1 * 3 = 3
product = 3 * 2 = 6
product = 6 * 1 = 6

Final:

product = 6

------------------------------------------------------------
IMPORTANT

Do NOT initialize product to 0.

If we do:

product = 0

then:

0 * any digit = 0

and the product would always remain zero.

Therefore:

int product = 1;

------------------------------------------------------------
FINAL CHECK

After calculating:

sum
product

we calculate:

sum + product

Then:

n % (sum + product) == 0

If true:

n is divisible

Otherwise:

n is not divisible.

------------------------------------------------------------
DRY RUN

n = 123

temp = 123

sum = 0
product = 1

------------------------------------------------------------
FIRST ITERATION

digit = 123 % 10

digit = 3

sum:

0 + 3 = 3

product:

1 * 3 = 3

temp:

123 / 10 = 12

------------------------------------------------------------
SECOND ITERATION

digit = 12 % 10

digit = 2

sum:

3 + 2 = 5

product:

3 * 2 = 6

temp:

12 / 10 = 1

------------------------------------------------------------
THIRD ITERATION

digit = 1 % 10

digit = 1

sum:

5 + 1 = 6

product:

6 * 1 = 6

temp:

1 / 10 = 0

------------------------------------------------------------
FINAL VALUES

sum = 6

product = 6

sum + product = 12

Check:

123 % 12 == 0

FALSE

Therefore:

return false

------------------------------------------------------------
ANOTHER EXAMPLE

n = 36

Digits:

3, 6

Sum:

3 + 6 = 9

Product:

3 * 6 = 18

Sum + Product:

9 + 18 = 27

Check:

36 % 27

!= 0

Therefore:

false

------------------------------------------------------------
ALGORITHM

1. Store n in a temporary variable.

2. Initialize:

   sum = 0
   product = 1

3. While temp > 0:

   a. Extract the last digit:

      digit = temp % 10

   b. Add digit to sum.

   c. Multiply digit with product.

   d. Remove the last digit:

      temp /= 10

4. Calculate:

   sum + product

5. Check:

   n % (sum + product) == 0

6. Return the result.

------------------------------------------------------------
TIME COMPLEXITY

O(log n)

A number with n digits has O(log n) digits.

We process every digit exactly once.

------------------------------------------------------------
SPACE COMPLEXITY

O(1)

Only a few variables are used.

------------------------------------------------------------
KEY CONCEPTS

✓ Digit Extraction
✓ Modulo Operator
✓ Integer Division
✓ Digit Sum
✓ Digit Product
✓ Basic Number Manipulation

------------------------------------------------------------
IMPORTANT PATTERN

Extract last digit:

digit = n % 10

Remove last digit:

n = n / 10

This pattern is useful for many digit-based
problems.

============================================================
*/
import java.util.*;

public class Main {

    public static boolean checkDivisibility(int n) {

        int temp = n;

        int sum = 0;
        int product = 1;

        while (temp > 0) {

            int digit = temp % 10;

            sum += digit;
            product *= digit;

            temp /= 10;
        }

        return n % (sum + product) == 0;
    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.print("Enter n: ");

        int n = sc.nextInt();

        boolean result = checkDivisibility(n);

        System.out.println(
            "Is divisible: " + result
        );

        sc.close();
    }
}
