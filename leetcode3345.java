/*
============================================================
LEETCODE 3345 - Smallest Divisible Digit Product I

Problem

You are given two integers

n

and

t.

Starting from

n,

find the smallest integer whose
product of digits is divisible by

t.

Return that integer.

------------------------------------------------------------
Example 1

Input

n = 10

t = 2

Output

10

Explanation

Digit Product

1 × 0 = 0

0 is divisible by 2.

------------------------------------------------------------
Example 2

Input

n = 15

t = 3

Output

16

Explanation

15

1 × 5 = 5

5 is not divisible by 3.

16

1 × 6 = 6

6 is divisible by 3.

Answer = 16

------------------------------------------------------------
Intuition

Start checking numbers one by one
beginning from n.

For each number,

calculate the product of its digits.

If the product is divisible by t,

return that number immediately.

Since the constraints are very small,

checking numbers sequentially is fast.

------------------------------------------------------------
Approach

1. Start from n.

2. Calculate the product of all digits.

3. Check

product % t == 0

4. If true,

return the current number.

Otherwise,

check the next number.

------------------------------------------------------------
Algorithm

1. Loop from

i = n

to infinity.

2. Initialize

product = 1

3. Extract each digit.

4. Multiply the digits.

5. Check

product % t == 0

6. If yes,

return i.

------------------------------------------------------------
Dry Run

Input

n = 15

t = 3

-----------------------------------

i = 15

Digits

1

5

Product

1 × 5 = 5

5 % 3 != 0

-----------------------------------

i = 16

Digits

1

6

Product

1 × 6 = 6

6 % 3 == 0

Return

16

------------------------------------------------------------
Another Example

n = 28

t = 5

-----------------------------------

28

2 × 8 = 16

16 % 5 != 0

-----------------------------------

29

2 × 9 = 18

18 % 5 != 0

-----------------------------------

30

3 × 0 = 0

0 % 5 == 0

Return

30

------------------------------------------------------------
Why It Works

Every number is checked exactly once.

For each number,

its digit product is computed.

The first number satisfying

product % t == 0

is returned.

Because numbers ending in

0

have digit product

0,

the answer is always found quickly.

------------------------------------------------------------
Time Complexity

O(log n)

For each number,

we process all of its digits.

The search examines only a few numbers
because the constraints are small.

------------------------------------------------------------
Space Complexity

O(1)

Only a few variables are used.

------------------------------------------------------------
Functions Used

%

Returns remainder.

/

Removes the last digit.

============================================================
*/
import java.util.*;

public class Main {

    public static int smallestNumber(int n, int t) {

        for (int i = n; ; i++) {

            int product = 1;

            for (int x = i; x > 0; x /= 10) {
                product *= (x % 10);
            }

            if (product % t == 0) {
                return i;
            }
        }
    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.print("Enter n: ");
        int n = sc.nextInt();

        System.out.print("Enter t: ");
        int t = sc.nextInt();

        System.out.println("Answer = " + smallestNumber(n, t));

        sc.close();
    }
}

