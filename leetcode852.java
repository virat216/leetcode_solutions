/*
============================================================
LEETCODE 852 - Peak Index in a Mountain Array

Problem

A mountain array is an array where

arr[0] < arr[1] < ... < arr[i]

and

arr[i] > arr[i+1] > ... > arr[n-1]

Return the index of the peak element.

------------------------------------------------------------
Example

Input:

arr = [0,2,5,8,10,7,4,1]

Output:

4

Explanation:

10 is the peak element and its index is 4.

------------------------------------------------------------
Intuition

A mountain array consists of two parts:

1. Increasing part
2. Decreasing part

If we are standing at any index,

there are only two possibilities.

Case 1

arr[mid] < arr[mid + 1]

This means we are still on the increasing slope.

The peak must lie on the right side.

Move left pointer.

Case 2

arr[mid] > arr[mid + 1]

This means we are on the decreasing slope.

The peak can either be

1. mid itself
2. somewhere on the left

So we keep mid and move the right pointer.

Instead of searching linearly,
Binary Search cuts the search space into half
every iteration.

------------------------------------------------------------
Approach

1. Initialize

left = 0

right = n - 1

2. Find middle index.

3. Compare

arr[mid]

with

arr[mid + 1]

4. If

arr[mid] < arr[mid + 1]

Peak lies on the right.

Move

left = mid + 1

5. Otherwise

Peak lies on left side or at mid.

Move

right = mid

6. Continue until

left == right

7. Return left.

------------------------------------------------------------
Algorithm

1. Set left = 0 and right = n - 1.
2. While left < right:
      • Find mid.
      • If arr[mid] < arr[mid+1]:
            left = mid + 1
      • Else:
            right = mid
3. Return left.

------------------------------------------------------------
Why It Works

If

arr[mid] < arr[mid+1]

we are definitely on the increasing side.

Therefore,

all elements before or including mid
cannot be the peak.

Move to the right half.

If

arr[mid] > arr[mid+1]

we are on the decreasing side.

The peak may be at mid itself,
so we cannot discard it.

Hence,

right = mid

After every iteration,
the search space becomes half.

Eventually,

left == right

Both pointers point to the peak index.

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

    public static int peakIndexInMountainArray(int[] arr) {

        int left = 0;
        int right = arr.length - 1;

        while (left < right) {

            int mid = left + (right - left) / 2;

            if (arr[mid] < arr[mid + 1]) {

                left = mid + 1;

            } else {

                right = mid;
            }
        }

        return left;
    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.print("Enter array size: ");
        int n = sc.nextInt();

        int[] arr = new int[n];

        System.out.println("Enter mountain array:");

        for (int i = 0; i < n; i++) {
            arr[i] = sc.nextInt();
        }

        int ans = peakIndexInMountainArray(arr);

        System.out.println("Peak Index = " + ans);

        sc.close();
    }
}

