/*
============================================================
LEETCODE 3513 - Number of Unique XOR Triplets I

Problem

You are given an integer n.

Consider the array

nums = [1, 2, 3, ..., n]

Choose any three indices i, j, and k
(where repetition of indices is allowed).

Compute

nums[i] ^ nums[j] ^ nums[k]

Return the number of distinct XOR values
that can be obtained.

------------------------------------------------------------
Example 1

Input

n = 2

Array

[1,2]

Possible XOR Values

1
2

Output

2

------------------------------------------------------------
Example 2

Input

n = 4

Array

[1,2,3,4]

Output

8

------------------------------------------------------------
Intuition

Brute force would try every possible triplet.

There are

n³

triplets.

This is far too slow.

Observe the pattern.

If

n < 3

there are not enough distinct values,
so the answer is simply

n.

For

n ≥ 3

the XOR operation is capable of generating
every number from

0

to

2^(floor(log2(n))+1)-1

Therefore,

the number of unique XOR values equals

2^(floor(log2(n))+1)

So we only need to determine the position
of the highest set bit in n.

------------------------------------------------------------
Approach

1. If n < 3

return n.

2. Find the position of the highest set bit.

3. Compute

2^(highestBit+1)

4. Return the answer.

------------------------------------------------------------
Algorithm

1. Read n.

2. If n < 3

return n.

3. Find

highestBit =
31 - Integer.numberOfLeadingZeros(n)

4. Return

1 << (highestBit + 1)

------------------------------------------------------------
Dry Run

Example

n = 5

Binary

101

Highest set bit position

2

Answer

1 << (2 + 1)

=

1 << 3

=

8

Possible XOR values

0

1

2

3

4

5

6

7

Count

8

------------------------------------------------------------
Why It Works

For every n ≥ 3,

the XOR operation can generate every value
whose binary length is equal to the binary
length of n.

If the highest set bit is

k,

then every value

0

to

2^(k+1)-1

can be produced.

Hence,

the total number of distinct XOR values is

2^(k+1).

------------------------------------------------------------
Time Complexity

O(1)

Only a few bit operations are performed.

------------------------------------------------------------
Space Complexity

O(1)

No extra memory is used.

------------------------------------------------------------
Functions Used

Integer.numberOfLeadingZeros()

Returns the number of leading zeros in
the 32-bit binary representation.

Left Shift (<<)

Computes powers of 2 efficiently.

============================================================
*/


import java.util.*;

public class Main {

    public static int uniqueXorTriplets(int n) {

        if (n < 3) {
            return n;
        }

        int highestBit = 31 - Integer.numberOfLeadingZeros(n);

        return 1 << (highestBit + 1);
    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.print("Enter n: ");

        int n = sc.nextInt();

        int ans = uniqueXorTriplets(n);

        System.out.println("Number of Unique XOR Triplets = " + ans);

        sc.close();
    }
}
