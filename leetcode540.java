/*
============================================================
LEETCODE 540 - Single Element in a Sorted Array

Problem

You are given a sorted array where every element appears
exactly twice except one element which appears only once.

Return the single element.

The solution must run in O(log n) time and O(1) space.

------------------------------------------------------------
Example

Input:

nums = [1,1,2,3,3,4,4,8,8]

Output:

2

------------------------------------------------------------
Intuition

Since the array is sorted, duplicate elements always
appear next to each other.

Before the single element,

every pair starts at an EVEN index.

Example:

Index : 0 1 2 3 4 5 6

Array : 1 1 2 2 3 3 4

        E O E O E O E

After the single element appears,

the pairing pattern changes.

Example:

Index : 0 1 2 3 4 5 6 7 8

Array : 1 1 2 3 3 4 4 8 8

        E O E O E O E O E
              ^
          Single Element

Notice that after the single element,
the first occurrence of every pair shifts
to an ODD index.

We use Binary Search to identify where this
shift occurs.

------------------------------------------------------------
Approach

1. Initialize

left = 0

right = n - 1

2. Find middle index.

3. Make sure mid is EVEN.

If mid is odd,

decrease it by one.

4. Compare

nums[mid]

with

nums[mid + 1]

5. If both are equal,

the single element lies on the right.

Move

left = mid + 2

6. Otherwise,

the single element lies on the left
(including mid).

Move

right = mid

7. Continue until

left == right

8. Return nums[left].

------------------------------------------------------------
Algorithm

1. Set left = 0 and right = n-1.
2. While left < right:
      • Find mid.
      • If mid is odd,
            mid--
      • Compare nums[mid] and nums[mid+1].
      • If equal:
            left = mid + 2
      • Else:
            right = mid
3. Return nums[left].

------------------------------------------------------------
Why It Works

Before the single element,

every pair begins at an even index.

After the single element,

every pair begins at an odd index.

By forcing mid to be even,
we always compare the first element
of a possible pair.

If the pair is correct,

the single element must be on the right.

Otherwise,

the pairing has already broken,
so the single element lies on the left.

Each iteration removes half of the search space,
giving O(log n) complexity.

------------------------------------------------------------
Time Complexity

Binary Search

O(log n)

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

    public static int singleNonDuplicate(int[] nums) {

        int left = 0;
        int right = nums.length - 1;

        while (left < right) {

            int mid = left + (right - left) / 2;

            if (mid % 2 == 1) {
                mid--;
            }

            if (nums[mid] == nums[mid + 1]) {

                left = mid + 2;

            } else {

                right = mid;
            }
        }

        return nums[left];
    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.print("Enter array size: ");
        int n = sc.nextInt();

        int[] nums = new int[n];

        System.out.println("Enter sorted array:");

        for (int i = 0; i < n; i++) {
            nums[i] = sc.nextInt();
        }

        int ans = singleNonDuplicate(nums);

        System.out.println("Single Element = " + ans);

        sc.close();
    }
}
