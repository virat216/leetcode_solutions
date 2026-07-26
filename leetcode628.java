/*
============================================================
LEETCODE 628 - Maximum Product of Three Numbers

Problem

Given an integer array nums,

find three numbers whose product is maximum
and return the maximum product.

------------------------------------------------------------
Example 1

Input

nums = [1,2,3]

Output

6

Explanation

1 × 2 × 3 = 6

------------------------------------------------------------
Example 2

Input

nums = [1,2,3,4]

Output

24

Explanation

2 × 3 × 4 = 24

------------------------------------------------------------
Example 3

Input

nums = [-10,-10,5,2]

Output

500

Explanation

(-10) × (-10) × 5 = 500

------------------------------------------------------------
Intuition

A brute-force solution would generate every
possible triplet and calculate their product.

Number of triplets

= nC3

Time Complexity

O(n³)

which is too slow.

Observe that the maximum product can come
from only two possibilities.

Case 1

Three largest numbers.

Example

[1,2,3,4]

Product

4 × 3 × 2

------------------------------------------------------------

Case 2

Largest positive number and two smallest
(negative) numbers.

Example

[-10,-10,5,2]

Product

5 × (-10) × (-10)

= 500

because

Negative × Negative = Positive.

Therefore,

we only need

• Largest three numbers

• Smallest two numbers

------------------------------------------------------------
Approach

Traverse the array only once.

Maintain

max1

Largest number

max2

Second largest number

max3

Third largest number

Also maintain

min1

Smallest number

min2

Second smallest number

Finally compute

max1 × max2 × max3

and

max1 × min1 × min2

Return the larger one.

------------------------------------------------------------
Algorithm

1. Initialize

max1

max2

max3

min1

min2

2. Traverse every element.

3. Update largest three values.

4. Update smallest two values.

5. Return

max(

max1 × max2 × max3,

max1 × min1 × min2

)

------------------------------------------------------------
Dry Run

Input

[-10,-10,5,2]

Initially

Largest

MIN MIN MIN

Smallest

MAX MAX

-----------------------------------

Read -10

Largest

-10

Smallest

-10

-----------------------------------

Read -10

Largest

-10 -10

Smallest

-10 -10

-----------------------------------

Read 5

Largest

5 -10 -10

-----------------------------------

Read 2

Largest

5 2 -10

Smallest

-10 -10

-----------------------------------

Case 1

5 × 2 × (-10)

=

-100

Case 2

5 × (-10) × (-10)

=

500

Answer

500

------------------------------------------------------------
Why It Works

The answer can only come from

1. Three largest numbers.

or

2. Largest number with two smallest numbers.

No other combination can produce a larger
product.

By maintaining these five numbers during one
traversal,

we avoid sorting the array and achieve
optimal efficiency.

------------------------------------------------------------
Time Complexity

O(n)

The array is traversed exactly once.

------------------------------------------------------------
Space Complexity

O(1)

Only five integer variables are used.

------------------------------------------------------------
Functions Used

Math.max()

Returns the larger of the two products.

============================================================
*/


import java.util.*;

public class Main {

    public static int maximumProduct(int[] nums) {

        int max1 = Integer.MIN_VALUE;
        int max2 = Integer.MIN_VALUE;
        int max3 = Integer.MIN_VALUE;

        int min1 = Integer.MAX_VALUE;
        int min2 = Integer.MAX_VALUE;

        for (int num : nums) {

            if (num > max1) {

                max3 = max2;
                max2 = max1;
                max1 = num;

            } else if (num > max2) {

                max3 = max2;
                max2 = num;

            } else if (num > max3) {

                max3 = num;
            }

            if (num < min1) {

                min2 = min1;
                min1 = num;

            } else if (num < min2) {

                min2 = num;
            }
        }

        return Math.max(max1 * max2 * max3,
                        max1 * min1 * min2);
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

        int ans = maximumProduct(nums);

        System.out.println("Maximum Product = " + ans);

        sc.close();
    }
}
