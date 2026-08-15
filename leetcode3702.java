/*
============================================================
LEETCODE 3702 - LONGEST SUBSEQUENCE WITH NON-ZERO
BITWISE XOR

Problem

Given an integer array nums, return the length of the
longest subsequence whose bitwise XOR is NOT zero.

If no such subsequence exists, return 0.

------------------------------------------------------------
IMPORTANT XOR PROPERTIES

1. x ^ x = 0

2. x ^ 0 = x

3. 0 ^ x = x

4. XOR is reversible:

   If:

   A ^ B = 0

   then:

   A = B

------------------------------------------------------------
CORE OBSERVATION

There are only THREE cases.

CASE 1:
The XOR of the entire array is NOT zero.

CASE 2:
The XOR of the entire array is zero, but there is
at least one non-zero element.

CASE 3:
Every element is zero.

------------------------------------------------------------
CASE 1

Suppose:

nums = [1, 2, 4]

Total XOR:

1 ^ 2 ^ 4 = 7

7 != 0

Therefore, the entire array is already a valid
subsequence.

We cannot find a longer subsequence than the
entire array.

Therefore:

answer = n

------------------------------------------------------------
CASE 2

Suppose:

nums = [1, 2, 3]

Total XOR:

1 ^ 2 ^ 3 = 0

Therefore, the entire array is NOT valid.

So we must remove at least one element.

Suppose we remove 3.

Remaining:

[1, 2]

XOR:

1 ^ 2 = 3

3 != 0

Therefore:

answer = n - 1

------------------------------------------------------------
WHY DOES REMOVING ONE NON-ZERO ELEMENT WORK?

Suppose the XOR of the entire array is:

0

and we remove an element x.

The XOR of the remaining elements is:

0 ^ x

which equals:

x

If x is non-zero:

x != 0

Therefore, the remaining n - 1 elements have
a non-zero XOR.

So whenever the total XOR is zero but the array
contains at least one non-zero element:

answer = n - 1

------------------------------------------------------------
CASE 3

Suppose:

nums = [0, 0, 0]

Total XOR:

0 ^ 0 ^ 0 = 0

Can we remove one element?

Remaining:

[0, 0]

XOR:

0

Can we remove another?

Remaining:

[0]

XOR:

0

Every possible subsequence contains only zeros.

Therefore, no valid subsequence exists.

answer = 0

------------------------------------------------------------
ALGORITHM

We only need two pieces of information:

1. XOR of all elements.

2. Whether at least one element is non-zero.

We can calculate both in one loop.

------------------------------------------------------------
STEP 1

Initialize:

int xor = 0;

int zeroCount = 0;

------------------------------------------------------------
STEP 2

Traverse the array.

For every number:

xor ^= num;

If:

num == 0

increase zeroCount.

------------------------------------------------------------
STEP 3

If total XOR is non-zero:

return n

------------------------------------------------------------
STEP 4

If every element is zero:

return 0

------------------------------------------------------------
STEP 5

Otherwise:

total XOR = 0

but at least one non-zero element exists.

Remove one non-zero element.

Return:

n - 1

------------------------------------------------------------
DRY RUN 1

nums = [1, 2, 3]

n = 3

XOR:

0 ^ 1 = 1

1 ^ 2 = 3

3 ^ 3 = 0

total XOR = 0

But the array contains non-zero elements.

Therefore:

answer = n - 1

answer = 2

------------------------------------------------------------
DRY RUN 2

nums = [2, 3, 4]

XOR:

2 ^ 3 ^ 4 = 5

5 != 0

Therefore the entire array works.

answer = 3

------------------------------------------------------------
DRY RUN 3

nums = [0, 0, 0]

XOR:

0

All elements are zero.

Therefore:

answer = 0

------------------------------------------------------------
WHY IS n - 1 THE MAXIMUM IN CASE 2?

The XOR of the entire array is zero.

Therefore, the complete array cannot be used.

So length n is impossible.

We can remove one non-zero element and get
a valid subsequence of length n - 1.

Therefore:

n - 1 is optimal.

------------------------------------------------------------
ALGORITHM

1. Set:

   xor = 0
   zeroCount = 0

2. Traverse nums.

3. Calculate total XOR.

4. Count zeros.

5. If:

   xor != 0

   return n

6. If:

   zeroCount == n

   return 0

7. Otherwise:

   return n - 1

------------------------------------------------------------
TIME COMPLEXITY

O(n)

We traverse the array once.

------------------------------------------------------------
SPACE COMPLEXITY

O(1)

Only a few variables are used.

------------------------------------------------------------
KEY CONCEPTS

✓ XOR
✓ Bit Manipulation
✓ Subsequence
✓ Mathematical Observation
✓ Array Traversal
✓ Constant Space

------------------------------------------------------------
PATTERN

Calculate total XOR

        ↓

Is XOR non-zero?
   /          \
 YES           NO
  ↓             ↓
return n    All elements zero?
              /       \
            YES        NO
             ↓          ↓
          return 0   return n - 1

============================================================
*/

import java.util.*;

public class Main {

    public static int longestSubsequence(int[] nums) {

        int xor = 0;
        int zeroCount = 0;
        int n = nums.length;

        for (int num : nums) {

            xor ^= num;

            if (num == 0) {
                zeroCount++;
            }
        }

        if (xor != 0) {
            return n;
        }

        if (zeroCount == n) {
            return 0;
        }

        return n - 1;
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

        int result = longestSubsequence(nums);

        System.out.println(
            "Longest valid subsequence length: " + result
        );

        sc.close();
    }
}
