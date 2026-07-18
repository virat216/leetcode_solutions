/*
============================================================
LEETCODE 1979 - Find Greatest Common Divisor of Array

Problem

Given an integer array nums,

return the Greatest Common Divisor (GCD)
of the smallest and the largest element
present in the array.

The GCD of two numbers is the largest positive
integer that divides both numbers without leaving
any remainder.

------------------------------------------------------------
Example 1

Input

nums = [2,5,6,9,10]

Smallest = 2

Largest = 10

GCD(2,10) = 2

Output

2

------------------------------------------------------------
Example 2

Input

nums = [7,5,6,8,3]

Smallest = 3

Largest = 8

GCD(3,8) = 1

Output

1

------------------------------------------------------------
Intuition

The problem only asks for the GCD of the
minimum and maximum elements.

Notice that we do NOT need the array to be sorted.

Instead,

we can simply traverse the array once to find

• Minimum element

• Maximum element

Then compute their GCD using
Euclid's Algorithm.

This avoids unnecessary sorting and gives
the optimal solution.

------------------------------------------------------------
Approach

1. Initialize

min = nums[0]

max = nums[0]

2. Traverse the array.

Update

min = minimum value seen so far.

max = maximum value seen so far.

3. Compute

gcd(min, max)

using Euclid's Algorithm.

4. Return the answer.

------------------------------------------------------------
Algorithm

1. Set

min = nums[0]

max = nums[0]

2. Traverse every element.

Update min and max.

3. Call

gcd(min, max)

4. Return the result.

------------------------------------------------------------
How Euclid's Algorithm Works

Suppose

gcd(24,18)

24 % 18 = 6

Now compute

gcd(18,6)

18 % 6 = 0

Now compute

gcd(6,0)

Answer = 6

The process continues until
the second number becomes zero.

------------------------------------------------------------
Why It Works

The question only requires the smallest
and largest elements.

A single traversal is enough to find both.

Euclid's Algorithm correctly computes the
Greatest Common Divisor of these two numbers.

Since every array element is visited exactly once,
the solution is optimal.

------------------------------------------------------------
Time Complexity

Finding minimum and maximum

O(n)

Finding GCD

O(log(max))

Overall

O(n + log(max))

Since log(max) is very small compared to n,

Overall Complexity

O(n)

------------------------------------------------------------
Space Complexity

O(1)

Only a few variables are used.

------------------------------------------------------------
Functions Used

Math.min()

Finds the minimum element.

Math.max()

Finds the maximum element.

gcd()

Computes the Greatest Common Divisor using
Euclid's Algorithm.

============================================================
*/

import java.util.*;

public class Main {

    public static int findGCD(int[] nums) {

        int min = nums[0];
        int max = nums[0];

        for (int num : nums) {

            min = Math.min(min, num);
            max = Math.max(max, num);
        }

        return gcd(min, max);
    }

    public static int gcd(int a, int b) {

        while (b != 0) {

            int temp = b;
            b = a % b;
            a = temp;
        }

        return a;
    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.print("Enter array size: ");

        int n = sc.nextInt();

        int[] nums = new int[n];

        System.out.println("Enter array elements:");

        for (int i = 0; i < n; i++) {

            nums[i] = sc.nextInt();
        }

        int ans = findGCD(nums);

        System.out.println("Greatest Common Divisor = " + ans);

        sc.close();
    }
}


