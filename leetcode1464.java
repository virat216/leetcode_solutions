/*
============================================================
LEETCODE 1464 - Maximum Product of Two Elements in an Array

Problem

Given an integer array nums,

choose two different indices i and j such that

(nums[i] - 1) * (nums[j] - 1)

is maximum.

Return the maximum value.

------------------------------------------------------------
Example 1

Input

nums = [3,4,5,2]

Output

12

Explanation

Choose

5 and 4

Answer

(5 - 1) × (4 - 1)

= 4 × 3

= 12

------------------------------------------------------------
Example 2

Input

nums = [1,5,4,5]

Output

16

Explanation

Choose

5 and 5

Answer

(5 - 1) × (5 - 1)

= 4 × 4

= 16

------------------------------------------------------------
Intuition

A brute-force solution checks every possible pair.

For every pair,

calculate

(nums[i] - 1) × (nums[j] - 1)

and keep the maximum.

Time Complexity

O(n²)

This is inefficient.

Observe that the answer only depends on the
two largest numbers in the array.

Instead of checking every pair,

simply find

Largest Number

Second Largest Number

Then compute

(max1 - 1) × (max2 - 1)

------------------------------------------------------------
Approach

1. Maintain two variables

max1

Largest element

max2

Second largest element

2. Traverse the array once.

3. If the current number is larger than max1,

move max1 to max2,

and update max1.

4. Otherwise,

if it is larger than max2,

update max2.

5. Return

(max1 - 1) × (max2 - 1)

------------------------------------------------------------
Algorithm

1. Initialize

max1 = Integer.MIN_VALUE

max2 = Integer.MIN_VALUE

2. Traverse every element.

3. Update

max1

and

max2

accordingly.

4. Compute

(max1 - 1) × (max2 - 1)

5. Return the answer.

------------------------------------------------------------
Dry Run

Input

[3,4,5,2]

Initially

max1 = MIN

max2 = MIN

-----------------------------------

Read 3

max1 = 3

max2 = MIN

-----------------------------------

Read 4

max2 = 3

max1 = 4

-----------------------------------

Read 5

max2 = 4

max1 = 5

-----------------------------------

Read 2

No update

-----------------------------------

Answer

(5 - 1) × (4 - 1)

= 4 × 3

= 12

------------------------------------------------------------
Why It Works

The product depends only on the two
largest numbers.

Subtracting one from both numbers
does not change their relative order.

Therefore,

tracking only the largest and second
largest elements is sufficient.

No sorting is required.

------------------------------------------------------------
Time Complexity

O(n)

The array is traversed exactly once.

------------------------------------------------------------
Space Complexity

O(1)

Only two integer variables are used.

------------------------------------------------------------
Functions Used

Enhanced For Loop

Traverses every element of the array.

Integer.MIN_VALUE

Initializes the variables to the
smallest possible integer.

============================================================
*/

import java.util.*;

public class Main {

    public static int maxProduct(int[] nums) {

        int max1 = Integer.MIN_VALUE;
        int max2 = Integer.MIN_VALUE;

        for (int num : nums) {

            if (num > max1) {

                max2 = max1;
                max1 = num;

            } else if (num > max2) {

                max2 = num;
            }
        }

        return (max1 - 1) * (max2 - 1);
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

        int answer = maxProduct(nums);

        System.out.println("Maximum Product = " + answer);

        sc.close();
    }
}
