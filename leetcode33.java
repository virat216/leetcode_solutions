/*
============================================================
LEETCODE 33 - Search in Rotated Sorted Array

Problem

Given a rotated sorted array and a target value,
return the index of the target if it exists.

Otherwise, return -1.

The array contains unique elements.

Example:

Original Array

[0,1,2,4,5,6,7]

After Rotation

[4,5,6,7,0,1,2]

Target = 0

Answer = 4

------------------------------------------------------------
Example

Input:

nums = [4,5,6,7,0,1,2]

target = 0

Output:

4

------------------------------------------------------------
Intuition

A normal Binary Search works only on a completely
sorted array.

Here, the array is rotated.

However, one important property always remains true.

At every iteration,

at least one half of the array is completely sorted.

Example

[4,5,6,7,0,1,2]

Suppose

mid = 3

Array becomes

4 5 6 |7| 0 1 2

Left half

[4,5,6,7]

is sorted.

Using this property, we first identify which half is sorted.

Then we check whether the target lies inside that sorted half.

If yes,

search only that half.

Otherwise,

search the other half.

This reduces the search space by half every iteration.

------------------------------------------------------------
Approach

1. Initialize

left = 0

right = n-1

2. Find middle index.

3. If target is found,

return its index.

4. Check whether the left half is sorted.

If

nums[left] <= nums[mid]

Left half is sorted.

Check whether target lies inside it.

If yes

Search left half.

Else

Search right half.

5. Otherwise,

Right half is sorted.

Check whether target lies inside it.

If yes

Search right half.

Else

Search left half.

6. Continue until

left > right.

7. If target is not found,

return -1.

------------------------------------------------------------
Algorithm

1. Set left = 0 and right = n-1.
2. While left <= right:
      • Find mid.
      • If nums[mid] == target:
            return mid.
      • Check which half is sorted.
      • Decide whether target belongs to that half.
      • Discard the other half.
3. Return -1.

------------------------------------------------------------
Why It Works

In every iteration,

one side of the array is always sorted.

Since that half is sorted,

we can easily determine whether the target belongs
to that half by checking its range.

If the target lies inside the sorted half,

discard the other half.

Otherwise,

discard the sorted half.

Thus,

the search space becomes half after every iteration.

Therefore,

Binary Search works even after rotation.

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

    public static int search(int[] nums, int target) {

        int left = 0;
        int right = nums.length - 1;

        while (left <= right) {

            int mid = left + (right - left) / 2;

            if (nums[mid] == target) {
                return mid;
            }

            if (nums[left] <= nums[mid]) {

                if (target >= nums[left] &&
                    target < nums[mid]) {

                    right = mid - 1;

                } else {

                    left = mid + 1;
                }

            } else {

                if (target > nums[mid] &&
                    target <= nums[right]) {

                    left = mid + 1;

                } else {

                    right = mid - 1;
                }
            }
        }

        return -1;
    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.print("Enter array size: ");
        int n = sc.nextInt();

        int[] nums = new int[n];

        System.out.println("Enter rotated sorted array:");

        for (int i = 0; i < n; i++) {
            nums[i] = sc.nextInt();
        }

        System.out.print("Enter target: ");
        int target = sc.nextInt();

        int ans = search(nums, target);

        System.out.println("Target Index = " + ans);

        sc.close();
    }
}
