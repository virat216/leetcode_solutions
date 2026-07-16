/*
============================================================
LEETCODE 3867 - Sum of GCD of Formed Pairs

Problem

You are given an integer array nums.

Construct another array

prefixGcd

such that

prefixGcd[i] = gcd(nums[i], maximum element from nums[0...i])

After constructing the array,

1. Sort it.

2. Pair the smallest element with the largest element.

3. Pair the second smallest with the second largest.

4. Continue until no more pairs can be formed.

5. Find the GCD of every pair.

Return the sum of all these GCDs.

If the array length is odd,

the middle element remains unused.

------------------------------------------------------------
Example

Input

nums = [3,6,2,8]

Step 1

Running Maximum

3 6 6 8

Step 2

prefixGcd

gcd(3,3)=3

gcd(6,6)=6

gcd(2,6)=2

gcd(8,8)=8

prefixGcd

[3,6,2,8]

Step 3

Sort

[2,3,6,8]

Step 4

Pairs

(2,8)

(3,6)

Step 5

GCDs

gcd(2,8)=2

gcd(3,6)=3

Answer

2+3=5

------------------------------------------------------------
Intuition

The problem can be divided into three independent steps.

1. Construct prefixGcd.

2. Sort the resulting array.

3. Pair smallest with largest and calculate GCD.

Instead of creating another array,

we can directly reuse the original array.

Once prefixGcd is built,

the original values are no longer needed.

------------------------------------------------------------
Approach

Step 1

Traverse the array.

Maintain

running maximum.

For every element

nums[i]

replace it with

gcd(nums[i], runningMaximum)

Now

nums itself becomes

prefixGcd.

------------------------------------------------------------

Step 2

Sort prefixGcd.

------------------------------------------------------------

Step 3

Use two pointers.

left = 0

right = n-1

Compute

gcd(prefixGcd[left], prefixGcd[right])

Add to answer.

Move

left++

right--

Continue until

left >= right.

------------------------------------------------------------
Algorithm

1. Initialize

runningMaximum = 0

2. Traverse array

Update running maximum.

Replace

nums[i]

by

gcd(nums[i], runningMaximum)

3. Sort the array.

4. Initialize

left = 0

right = n-1

5. While

left < right

Add

gcd(nums[left], nums[right])

Move both pointers.

6. Return answer.

------------------------------------------------------------
Why It Works

For every position,

the running maximum represents

maximum element from

0...i

Therefore

gcd(nums[i], runningMaximum)

exactly matches the required definition of

prefixGcd.

Sorting arranges the values in ascending order.

Using two pointers automatically forms

smallest-largest

second smallest-second largest

and so on,

exactly as required.

Thus every required pair is processed exactly once.

------------------------------------------------------------
Time Complexity

Building prefixGcd

O(n × logM)

Sorting

O(n log n)

Pairing

O(n × logM)

where

M = Maximum value in nums

Overall

O(n log n)

------------------------------------------------------------
Space Complexity

O(1)

No extra array is created.

Sorting is performed in-place.

------------------------------------------------------------
Functions Used

Math.max()

Maintains the running maximum.

Arrays.sort()

Sorts prefixGcd.

gcd()

Computes the Greatest Common Divisor using
Euclid's Algorithm.

============================================================
*/




import java.util.*;

public class Main {

    private static int gcd(int a, int b) {

        return b == 0 ? a : gcd(b, a % b);
    }

    public static long gcdSum(int[] nums) {

        int max = 0;

        for (int i = 0; i < nums.length; i++) {

            max = Math.max(max, nums[i]);

            nums[i] = gcd(nums[i], max);
        }

        Arrays.sort(nums);

        long answer = 0;

        int left = 0;
        int right = nums.length - 1;

        while (left < right) {

            answer += gcd(nums[left], nums[right]);

            left++;
            right--;
        }

        return answer;
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

        long ans = gcdSum(nums);

        System.out.println("Sum of GCD of Formed Pairs = " + ans);

        sc.close();
    }
}
