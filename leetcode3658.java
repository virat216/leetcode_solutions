/*
============================================================
LEETCODE 3658 - GCD of Odd and Even Sums

Problem

Given an integer n,

Find

1. Sum of first n odd numbers.
2. Sum of first n even numbers.

Return the GCD of these two sums.

------------------------------------------------------------
Example

Input

n = 4

Odd Numbers

1 3 5 7

Sum = 16

Even Numbers

2 4 6 8

Sum = 20

GCD(16,20) = 4

Output

4

------------------------------------------------------------
Intuition

Instead of calculating the sums every time,

we use two mathematical formulas.

Sum of first n odd numbers

= n²

Sum of first n even numbers

= n(n + 1)

Now,

we need

GCD(n², n(n+1))

------------------------------------------------------------
Approach

Odd Sum

= n²

Even Sum

= n(n+1)

Take common factor n

GCD(n², n(n+1))

= n × GCD(n, n+1)

We know that

Two consecutive numbers are always coprime.

Therefore

GCD(n, n+1)

= 1

Hence

GCD(n², n(n+1))

= n

So the answer is simply

n

------------------------------------------------------------
Mathematical Proof

Odd Sum

n²

Even Sum

n(n+1)

GCD

GCD(n², n(n+1))

Take common factor

= n × GCD(n, n+1)

Since

n

and

n+1

are consecutive integers,

GCD(n, n+1)

= 1

Therefore

Answer

= n

------------------------------------------------------------
Algorithm

1. Read n.

2. Return n.

------------------------------------------------------------
Why It Works

The solution is based entirely on mathematics.

Instead of calculating both sums,

we simplify the GCD expression.

The final expression always becomes

n

Therefore,

no computation is actually required.

------------------------------------------------------------
Time Complexity

O(1)

------------------------------------------------------------
Space Complexity

O(1)

------------------------------------------------------------
Functions Used

None

============================================================
*/

import java.util.*;

public class Main {

    public static int gcdOfOddEvenSums(int n) {

        return n;
    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.print("Enter n: ");

        int n = sc.nextInt();

        int ans = gcdOfOddEvenSums(n);

        System.out.println("Answer = " + ans);

        sc.close();
    }
}
