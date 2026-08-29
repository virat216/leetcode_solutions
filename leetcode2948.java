/*
============================================================
LEETCODE 2948 - MAKE LEXICOGRAPHICALLY SMALLEST ARRAY BY
SWAPPING ELEMENTS

Problem

Given an integer array nums and an integer limit.

We can swap nums[i] and nums[j] if:

|nums[i] - nums[j]| <= limit

We can perform any number of swaps.

Return the lexicographically smallest array that can
be obtained.

------------------------------------------------------------
IMPORTANT OBSERVATION

The important thing is that swaps can be performed
MULTIPLE TIMES.

Suppose:

A can swap with B

and:

B can swap with C

Then A, B and C can all be rearranged among their
positions.

This means we need to find groups of values that are
connected through valid swaps.

------------------------------------------------------------
EXAMPLE

nums = [1, 5, 3, 9, 8]

limit = 2

First sort the elements by value while keeping
their original indices.

value    index

1          0
3          2
5          1
8          4
9          3

------------------------------------------------------------
FIND GROUPS

Sorted values:

1, 3, 5, 8, 9

Check consecutive differences:

3 - 1 = 2
5 - 3 = 2
8 - 5 = 3
9 - 8 = 1

Since:

8 - 5 > limit

we have two groups:

Group 1:

1, 3, 5

Group 2:

8, 9

------------------------------------------------------------
WHY CAN 1, 3, 5 BE REARRANGED?

Because:

1 ↔ 3

and:

3 ↔ 5

Therefore all three elements are connected.

Even though:

5 - 1 = 4

and 1 cannot directly swap with 5,

we can use 3 as an intermediate element.

Therefore all three can eventually be rearranged
among their original positions.

------------------------------------------------------------
WHY DO WE SORT THE ORIGINAL INDICES?

For Group 1:

values:

1, 3, 5

original indices:

0, 2, 1

Sort the indices:

0, 1, 2

Now place the sorted values into the sorted indices:

index 0 -> 1
index 1 -> 3
index 2 -> 5

This produces:

[1, 3, 5, 9, 8]

------------------------------------------------------------
GROUP 2

Values:

8, 9

Original indices:

4, 3

Sort indices:

3, 4

Place values:

index 3 -> 8
index 4 -> 9

Final:

[1, 3, 5, 8, 9]

------------------------------------------------------------
WHY DOES THIS PRODUCE THE LEXICOGRAPHICALLY
SMALLEST ARRAY?

Lexicographical order compares elements from
left to right.

Therefore we want the smallest possible value
at the smallest possible index.

Inside a connected group, every value can be
rearranged among all positions belonging to
that group.

Therefore:

smallest value
      ↓
smallest index

second smallest value
      ↓
second smallest index

and so on.

This gives the lexicographically smallest result.

------------------------------------------------------------
STEP 1 - STORE INDICES

Create:

Integer[] indices

where:

indices[i] = i

Example:

nums:

[1, 5, 3, 9, 8]

indices:

[0, 1, 2, 3, 4]

------------------------------------------------------------
STEP 2 - SORT INDICES BY nums VALUE

Sort using:

Arrays.sort(
    indices,
    (a, b) -> Integer.compare(nums[a], nums[b])
);

Now:

indices:

[0, 2, 1, 4, 3]

because their values are:

[1, 3, 5, 8, 9]

------------------------------------------------------------
STEP 3 - FIND EACH GROUP

Start at:

i = 0

Move j forward while:

nums[indices[j]] - nums[indices[j - 1]]
<= limit

When the difference becomes greater than
limit, the current group ends.

------------------------------------------------------------
STEP 4 - SORT ORIGINAL INDICES

For each group:

[ i ... j - 1 ]

collect the original indices.

Example:

values:

1, 3, 5

original indices:

0, 2, 1

Sort:

0, 1, 2

------------------------------------------------------------
STEP 5 - ASSIGN VALUES

The values are already sorted because the entire
indices array was sorted by nums value.

Assign:

smallest value -> smallest original index

------------------------------------------------------------
DRY RUN

nums:

[1, 5, 3, 9, 8]

limit:

2

------------------------------------------------------------
SORT BY VALUE

value:

1  3  5  8  9

index:

0  2  1  4  3

------------------------------------------------------------
GROUP 1

Values:

1, 3, 5

Indices:

0, 2, 1

Sorted indices:

0, 1, 2

Assign:

index 0 = 1
index 1 = 3
index 2 = 5

Array:

[1, 3, 5, 9, 8]

------------------------------------------------------------
GROUP 2

Values:

8, 9

Indices:

4, 3

Sorted:

3, 4

Assign:

index 3 = 8
index 4 = 9

Final:

[1, 3, 5, 8, 9]

------------------------------------------------------------
ALGORITHM

1. Create an array of original indices.

2. Sort indices according to nums values.

3. Traverse the sorted indices.

4. Divide them into groups.

5. A new group starts when:

   nums[indices[j]] - nums[indices[j - 1]]
   > limit

6. For every group:

   a. Sort the original indices.

   b. Assign the sorted values to those indices.

7. Return the resulting array.

------------------------------------------------------------
WHY NOT JUST SORT nums?

Because we are only allowed to swap elements
belonging to the same connected group.

For example:

nums = [1, 10, 3]

limit = 2

1 and 3 can be swapped.

10 cannot participate.

Simply sorting the entire array would give:

[1, 3, 10]

which happens to work here, but in general
we need to preserve the boundaries between
different groups.

Therefore we sort WITH ORIGINAL INDICES.

------------------------------------------------------------
TIME COMPLEXITY

Sorting all indices:

O(n log n)

Sorting indices inside all groups:

O(n log n) in total worst case.

Therefore:

O(n log n)

------------------------------------------------------------
SPACE COMPLEXITY

O(n)

We use:

Integer[] indices
int[] answer

------------------------------------------------------------
KEY CONCEPTS

✓ Sorting
✓ Original Indices
✓ Greedy
✓ Connected Components
✓ Lexicographical Order
✓ Grouping
✓ Array Manipulation

------------------------------------------------------------
PATTERN

Sort values with indices
        ↓
Find connected groups
        ↓
Sort original positions
        ↓
Smallest value → smallest position
        ↓
Lexicographically smallest array

============================================================
*/
import java.util.*;

public class Main {

    public static int[] lexicographicallySmallestArray(
            int[] nums, int limit) {

        int n = nums.length;

        Integer[] indices = new Integer[n];

        for (int i = 0; i < n; i++) {
            indices[i] = i;
        }

        Arrays.sort(
            indices,
            (a, b) -> Integer.compare(nums[a], nums[b])
        );

        int[] answer = new int[n];

        int i = 0;

        while (i < n) {

            int j = i + 1;

            while (
                j < n &&
                (long) nums[indices[j]]
                - nums[indices[j - 1]] <= limit
            ) {
                j++;
            }

            Integer[] group =
                Arrays.copyOfRange(indices, i, j);

            Arrays.sort(group);

            for (int k = 0; k < group.length; k++) {

                answer[group[k]] =
                    nums[indices[i + k]];
            }

            i = j;
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

        System.out.print("Enter limit: ");
        int limit = sc.nextInt();

        int[] result =
            lexicographicallySmallestArray(nums, limit);

        System.out.println(
            "Lexicographically smallest array:"
        );

        System.out.println(
            Arrays.toString(result)
        );

        sc.close();
    }
}

