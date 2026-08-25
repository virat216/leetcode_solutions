/*
============================================================
LEETCODE 3718 - SMALLEST MISSING MULTIPLE OF K

Problem

Given an integer array nums and an integer k,

return the smallest POSITIVE multiple of k that
does not appear in nums.

A multiple of k is:

k, 2k, 3k, 4k, ...

------------------------------------------------------------
EXAMPLE 1

Input:

nums = [8, 2, 3, 4, 6]
k = 2

Multiples of 2:

2, 4, 6, 8, 10, 12, ...

Check them one by one:

2  -> exists
4  -> exists
6  -> exists
8  -> exists
10 -> missing

Therefore:

answer = 10

------------------------------------------------------------
EXAMPLE 2

nums = [1, 4, 7, 10, 15]
k = 5

Multiples:

5, 10, 15, 20, ...

Check:

5 -> missing

Therefore:

answer = 5

------------------------------------------------------------
INTUITION

We don't care about every number in nums.

We only care about:

k, 2k, 3k, 4k, ...

So the simplest strategy is:

1. Store all elements of nums in a HashSet.
2. Start with k.
3. Check whether k exists.
4. If it exists, check 2k.
5. Continue until we find a missing multiple.

Because we check multiples in increasing order,
the first missing one is automatically the answer.

------------------------------------------------------------
WHY HASHSET?

Suppose we need to check:

Does 20 exist in nums?

If we search the array directly:

for every multiple
    scan entire array

This can take O(n) for every lookup.

Instead, use:

HashSet<Integer>

Then:

set.contains(20)

takes O(1) average time.

------------------------------------------------------------
STEP 1 - BUILD HASHSET

For every number:

nums = [8, 2, 3, 4, 6]

we insert:

8
2
3
4
6

into the set.

So:

set = {2, 3, 4, 6, 8}

------------------------------------------------------------
STEP 2 - START FROM k

Set:

multiple = k

For:

k = 2

we start with:

multiple = 2

------------------------------------------------------------
STEP 3 - CHECK THE MULTIPLE

If:

set.contains(multiple)

then the multiple exists.

Move to the next multiple:

multiple += k

For example:

2 exists

then:

multiple = 2 + 2
        = 4

------------------------------------------------------------
STEP 4 - RETURN FIRST MISSING MULTIPLE

If:

set.contains(multiple) == false

then we have found the smallest missing
positive multiple.

Return it immediately.

------------------------------------------------------------
DRY RUN

nums = [8, 2, 3, 4, 6]
k = 2

HashSet:

{2, 3, 4, 6, 8}

------------------------------------------------------------
multiple = 2

2 exists.

Move:

multiple = 4

------------------------------------------------------------
multiple = 4

4 exists.

Move:

multiple = 6

------------------------------------------------------------
multiple = 6

6 exists.

Move:

multiple = 8

------------------------------------------------------------
multiple = 8

8 exists.

Move:

multiple = 10

------------------------------------------------------------
multiple = 10

10 does NOT exist.

Therefore:

return 10

------------------------------------------------------------
WHY DOES THIS ALWAYS WORK?

We check:

k
2k
3k
4k
...

in increasing order.

Suppose the first missing multiple is:

5k

Then:

k, 2k, 3k, 4k

all exist.

Therefore 5k is the smallest missing multiple.

So returning the first missing value is correct.

------------------------------------------------------------
WHY WILL THE LOOP TERMINATE?

Suppose nums has n elements.

It can contain at most n different multiples of k.

Therefore, among the first n + 1 multiples:

k, 2k, 3k, ..., (n + 1)k

at least one must be missing.

So the loop is guaranteed to terminate.

------------------------------------------------------------
ALGORITHM

1. Create a HashSet.

2. Add every element of nums to the set.

3. Set:

   multiple = k

4. While the set contains multiple:

   multiple += k

5. Return multiple.

------------------------------------------------------------
PSEUDOCODE

set = all elements of nums

multiple = k

while set contains multiple:

    multiple += k

return multiple

------------------------------------------------------------
TIME COMPLEXITY

Building the HashSet:

O(n)

Checking multiples:

O(n) in the worst case

Therefore:

O(n)

average time.

------------------------------------------------------------
SPACE COMPLEXITY

HashSet stores the elements of nums:

O(n)

------------------------------------------------------------
IMPORTANT

Do NOT start from:

multiple = 0

Because 0 is NOT a positive multiple.

Start from:

multiple = k

------------------------------------------------------------
KEY CONCEPTS

✓ HashSet
✓ Hashing
✓ Array
✓ Multiples
✓ Enumeration
✓ O(1) Average Lookup

------------------------------------------------------------
PATTERN

Array
  ↓
HashSet
  ↓
Generate candidates in sorted order
  ↓
k, 2k, 3k, 4k...
  ↓
First candidate not in Set
  ↓
Answer

============================================================
*/


import java.util.*;

public class Main {

    public static int missingMultiple(int[] nums, int k) {

        HashSet<Integer> set = new HashSet<>();

        for (int num : nums) {
            set.add(num);
        }

        int multiple = k;

        while (set.contains(multiple)) {
            multiple += k;
        }

        return multiple;
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

        System.out.print("Enter k: ");
        int k = sc.nextInt();

        int result = missingMultiple(nums, k);

        System.out.println(
            "Smallest missing multiple: " + result
        );

        sc.close();
    }
}
