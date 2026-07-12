/*
============================================================
LEETCODE 1331 - Rank Transform of an Array

Problem

Given an integer array arr,

replace every element with its rank.

Rules

1. Rank starts from 1.
2. Larger number gets larger rank.
3. Equal numbers receive the same rank.
4. Ranks should be as small as possible.

------------------------------------------------------------
Example

Input

arr = [40,10,20,30]

Output

[4,1,2,3]

Explanation

Sorted Unique Values

10 -> Rank 1

20 -> Rank 2

30 -> Rank 3

40 -> Rank 4

Replace every element with its rank.

------------------------------------------------------------
Example 2

Input

arr = [100,100,100]

Output

[1,1,1]

------------------------------------------------------------
Intuition

To assign ranks,

we first need the numbers in sorted order.

However,

changing the original array would lose
its original positions.

So,

we make a copy of the array.

After sorting,

we assign ranks only to unique elements
using a HashMap.

Finally,

we traverse the original array and replace
every element with its corresponding rank.

------------------------------------------------------------
Approach

1. Copy the original array.

2. Sort the copied array.

3. Traverse the sorted array.

4. Assign increasing ranks only to
   unique values.

5. Store

      Value -> Rank

   inside a HashMap.

6. Traverse the original array.

7. Replace every element by

      map.get(arr[i])

------------------------------------------------------------
Algorithm

1. Copy the array.

2. Sort the copied array.

3. Initialize rank = 1.

4. Traverse sorted array.

      If current value is not in map,

            map.put(value, rank)

            rank++

5. Traverse original array.

6. Replace every element by its rank.

7. Return the modified array.

------------------------------------------------------------
Why It Works

Sorting places numbers in ascending order.

Example

40 10 20 30

↓

10 20 30 40

Now we assign

10 -> 1

20 -> 2

30 -> 3

40 -> 4

Using a HashMap,

we can obtain the rank of every element
in O(1) time.

Thus,

every element in the original array
is replaced by its correct rank.

------------------------------------------------------------
Time Complexity

Copy Array

O(n)

Sorting

O(n log n)

Building HashMap

O(n)

Replacing Elements

O(n)

Overall

O(n log n)

------------------------------------------------------------
Space Complexity

O(n)

For copied array and HashMap.

------------------------------------------------------------
Functions Used

Arrays.copyOf()

Creates a copy of the original array.

Arrays.sort()

Sorts the copied array.

HashMap.put()

Stores value and rank.

HashMap.get()

Returns the rank of a value.

============================================================
*/


import java.util.*;

public class Main {

    public static int[] arrayRankTransform(int[] arr) {

        int[] sorted = Arrays.copyOf(arr, arr.length);

        Arrays.sort(sorted);

        HashMap<Integer, Integer> map = new HashMap<>();

        int rank = 1;

        for (int num : sorted) {

            if (!map.containsKey(num)) {

                map.put(num, rank);
                rank++;
            }
        }

        for (int i = 0; i < arr.length; i++) {

            arr[i] = map.get(arr[i]);
        }

        return arr;
    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.print("Enter array size: ");

        int n = sc.nextInt();

        int[] arr = new int[n];

        System.out.println("Enter array elements:");

        for (int i = 0; i < n; i++) {
            arr[i] = sc.nextInt();
        }

        int[] ans = arrayRankTransform(arr);

        System.out.println("Rank Transformed Array:");

        for (int num : ans) {
            System.out.print(num + " ");
        }

        sc.close();
    }
}
