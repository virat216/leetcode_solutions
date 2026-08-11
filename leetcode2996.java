/*
============================================================
LEETCODE 2996 - SMALLEST MISSING INTEGER GREATER THAN
SEQUENTIAL PREFIX SUM

Problem

Given an integer array nums:

1. Find the longest sequential prefix.

A sequential prefix means:

nums[i] = nums[i - 1] + 1

2. Calculate the sum of all elements in that
   sequential prefix.

3. Find the smallest integer that is greater than
   or equal to this sum and does NOT appear in nums.

------------------------------------------------------------
EXAMPLE 1

Input:

nums = [1, 2, 3, 2, 5]

------------------------------------------------------------
STEP 1 - FIND SEQUENTIAL PREFIX

Start with:

1

Next:

2 = 1 + 1

Next:

3 = 2 + 1

Next:

2 != 3 + 1

Therefore the sequential prefix is:

[1, 2, 3]

------------------------------------------------------------
STEP 2 - FIND SUM

sum = 1 + 2 + 3

sum = 6

------------------------------------------------------------
STEP 3 - FIND SMALLEST MISSING INTEGER

We need the smallest number >= 6
that does not appear in nums.

nums contains:

1, 2, 3, 5

6 is not present.

Therefore:

answer = 6

------------------------------------------------------------
SECOND EXAMPLE

nums = [3, 4, 5, 6, 7]

Sequential prefix:

3, 4, 5, 6, 7

Sum:

3 + 4 + 5 + 6 + 7 = 25

25 is not present.

Answer:

25

------------------------------------------------------------
IMPORTANT OBSERVATION

We need two things:

1. Calculate the sequential prefix sum.
2. Quickly check whether a number exists in nums.

For the second operation, we use:

HashSet<Integer>

HashSet gives average O(1) lookup.

------------------------------------------------------------
STEP 1 - FIND SEQUENTIAL PREFIX SUM

Start:

int sum = nums[0];

Then start from index 1.

Continue while:

nums[i] == nums[i - 1] + 1

For example:

nums = [3, 4, 5, 2, 8]

Start:

sum = 3

------------------------------------------------------------
i = 1

nums[1] = 4

nums[0] + 1 = 4

They match.

sum:

3 + 4 = 7

------------------------------------------------------------
i = 2

nums[2] = 5

nums[1] + 1 = 5

They match.

sum:

7 + 5 = 12

------------------------------------------------------------
i = 3

nums[3] = 2

nums[2] + 1 = 6

They don't match.

Stop.

Sequential prefix:

[3, 4, 5]

sum:

12

------------------------------------------------------------
STEP 2 - CREATE HASHSET

Add every number from nums into:

HashSet<Integer>

Example:

nums = [3, 4, 5, 2, 8]

Set:

{2, 3, 4, 5, 8}

------------------------------------------------------------
STEP 3 - FIND MISSING INTEGER

Start from:

sum

While sum exists in the set:

sum++

For example:

sum = 5

Set contains 5.

So:

sum = 6

Set does not contain 6.

Return:

6

------------------------------------------------------------
WHY HASHSET?

We need to repeatedly ask:

"Does nums contain this number?"

Using an array can cause problems if nums contains
large values.

For example:

nums contains:

44

but our boolean array might have length:

35

Then:

present[44]

causes:

ArrayIndexOutOfBoundsException

A HashSet doesn't have this limitation.

------------------------------------------------------------
ALGORITHM

1. Set:

   sum = nums[0]

2. Traverse the array from index 1.

3. While:

   nums[i] == nums[i - 1] + 1

   add nums[i] to sum.

4. Create a HashSet.

5. Add every element of nums to the HashSet.

6. While the HashSet contains sum:

   sum++

7. Return sum.

------------------------------------------------------------
DRY RUN

nums = [2, 3, 4, 2, 5]

------------------------------------------------------------
Sequential prefix:

2

3 = 2 + 1

4 = 3 + 1

2 != 4 + 1

So:

prefix = [2, 3, 4]

------------------------------------------------------------
Sum:

2 + 3 + 4 = 9

------------------------------------------------------------
HashSet:

{2, 3, 4, 5}

Check:

9 -> not present

Therefore:

answer = 9

------------------------------------------------------------
ANOTHER DRY RUN

nums = [1, 2, 3, 4, 10]

Sequential prefix:

[1, 2, 3, 4]

Sum:

1 + 2 + 3 + 4 = 10

But:

10 is present.

So increment:

10 -> 11

11 is not present.

Answer:

11

------------------------------------------------------------
TIME COMPLEXITY

Finding sequential prefix:

O(n)

Building HashSet:

O(n)

Searching for missing value:

O(n) in the worst case

Overall:

O(n) average

------------------------------------------------------------
SPACE COMPLEXITY

HashSet stores up to n elements.

Therefore:

O(n)

------------------------------------------------------------
KEY CONCEPTS

✓ Sequential Prefix
✓ Prefix Sum
✓ HashSet
✓ O(1) Average Lookup
✓ Array Traversal

------------------------------------------------------------
PATTERN

Need to repeatedly check:

"Does this number exist in the array?"

↓

HashSet

Need to find:

"Smallest missing number >= X"

↓

Start from X

↓

While set contains X:

X++

============================================================
*/
import java.util.*;

public class Main {

    public static int missingInteger(int[] nums) {

        int sum = nums[0];

        int i = 1;

        while (i < nums.length && nums[i] == nums[i - 1] + 1) {
            sum += nums[i];
            i++;
        }

        HashSet<Integer> set = new HashSet<>();

        for (int num : nums) {
            set.add(num);
        }

        while (set.contains(sum)) {
            sum++;
        }

        return sum;
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

        int result = missingInteger(nums);

        System.out.println("Smallest missing integer: " + result);

        sc.close();
    }
}

