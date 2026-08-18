/*
============================================================
LEETCODE 3471 - FIND THE LARGEST ALMOST MISSING INTEGER

Problem

Given:

nums = integer array
k = size of the subarray

An integer x is called "almost missing" if x appears
in exactly ONE subarray of size k.

Return the largest almost missing integer.

If no such integer exists:

return -1

------------------------------------------------------------
IMPORTANT OBSERVATION

There are three cases:

1. k == 1
2. k == nums.length
3. 1 < k < nums.length

------------------------------------------------------------
CASE 1: k == 1

Every element itself forms a subarray of size 1.

Example:

nums = [3, 9, 3, 7]

Subarrays of size 1:

[3]
[9]
[3]
[7]

For an integer to appear in exactly one subarray,
it must appear exactly once in the entire array.

Frequencies:

3 -> 2
9 -> 1
7 -> 1

Almost missing integers:

9, 7

Largest:

9

Therefore, when k == 1:

Find the largest element whose frequency is 1.

------------------------------------------------------------
CASE 2: k == n

Suppose:

nums = [3, 9, 2, 1, 7]
k = 5

There is only ONE subarray of size 5:

[3, 9, 2, 1, 7]

Every value in the array appears in exactly one
subarray.

Therefore, simply return:

maximum element

Answer:

9

------------------------------------------------------------
CASE 3: 1 < k < n

This is the important observation.

Only the FIRST and LAST elements of the array
can appear in exactly one subarray of size k.

Why?

Consider:

nums = [3, 9, 2, 1, 7]
k = 3

Subarrays:

[3, 9, 2]
[9, 2, 1]
[2, 1, 7]

Look at 3:

3 occurs only in:

[3, 9, 2]

So 3 can be almost missing.

Look at 7:

7 occurs only in:

[2, 1, 7]

So 7 can be almost missing.

But 9 is an interior element.

9 occurs in:

[3, 9, 2]
[9, 2, 1]

Therefore 9 appears in at least two subarrays.

The same applies to other interior positions.

------------------------------------------------------------
THEREFORE

For:

1 < k < n

we only need to check:

nums[0]

and:

nums[n - 1]

But there is one more condition.

If nums[0] appears somewhere else in the array,
then it will appear in more than one subarray.

Therefore, nums[0] is a candidate only if its
total frequency is exactly 1.

Same for nums[n - 1].

------------------------------------------------------------
EXAMPLE

nums = [3, 9, 2, 1, 7]
k = 3

Frequencies:

3 -> 1
9 -> 1
2 -> 1
1 -> 1
7 -> 1

First element:

3

Frequency = 1

So 3 is a candidate.

Last element:

7

Frequency = 1

So 7 is a candidate.

Largest:

7

Answer:

7

------------------------------------------------------------
ANOTHER EXAMPLE

nums = [3, 9, 7, 2, 1, 7]
k = 4

First element:

3

Frequency:

3 -> 1

So 3 is a candidate.

Last element:

7

Frequency:

7 -> 2

Therefore 7 is NOT a candidate.

Answer:

3

------------------------------------------------------------
ALGORITHM

1. Count the frequency of every number.

2. If:

   k == nums.length

   return the maximum element.

3. If:

   k == 1

   find the maximum element whose frequency
   is exactly 1.

4. Otherwise:

   Check nums[0].

   If frequency[nums[0]] == 1,
   it is a candidate.

5. Check nums[n - 1].

   If frequency[nums[n - 1]] == 1,
   it is a candidate.

6. Return the larger candidate.

7. If neither candidate exists:

   return -1.

------------------------------------------------------------
WHY DO WE NEED FREQUENCY?

Suppose:

nums = [5, 2, 3, 5]
k = 2

First element:

5

Although 5 is at the first position,
5 also appears at the last position.

Therefore 5 appears in more than one
subarray of size 2.

So 5 is NOT almost missing.

The frequency check catches this:

frequency[5] = 2

Therefore:

5 is not a candidate.

------------------------------------------------------------
DRY RUN

nums = [3, 9, 2, 1, 7]
k = 3

n = 5

k != 1
k != n

So we are in:

1 < k < n

Count frequencies:

3 -> 1
9 -> 1
2 -> 1
1 -> 1
7 -> 1

Check first:

nums[0] = 3

frequency[3] = 1

candidate = 3

Check last:

nums[4] = 7

frequency[7] = 1

candidate = 7

Maximum:

7

Return:

7

------------------------------------------------------------
TIME COMPLEXITY

O(n)

We traverse the array to calculate frequencies.

Then we perform only a few additional checks.

------------------------------------------------------------
SPACE COMPLEXITY

O(1)

Because nums[i] is between 0 and 50,
we can use an array of size 51.

------------------------------------------------------------
KEY CONCEPTS

✓ Array
✓ Frequency Counting
✓ Hashing
✓ Case Analysis
✓ Subarray Observation

------------------------------------------------------------
PATTERN

k == 1
    ↓
Find maximum unique element

k == n
    ↓
Return maximum element

1 < k < n
    ↓
Only first and last positions can qualify
    ↓
Check their frequencies
    ↓
Return maximum candidate

============================================================
*/
import java.util.*;

public class Main {

    public static int largestInteger(int[] nums, int k) {

        int n = nums.length;

        int[] freq = new int[51];

        for (int num : nums) {
            freq[num]++;
        }

        if (k == n) {

            int max = -1;

            for (int num : nums) {
                max = Math.max(max, num);
            }

            return max;
        }

        if (k == 1) {

            int max = -1;

            for (int num : nums) {

                if (freq[num] == 1) {
                    max = Math.max(max, num);
                }
            }

            return max;
        }

        int answer = -1;

        if (freq[nums[0]] == 1) {
            answer = Math.max(answer, nums[0]);
        }

        if (freq[nums[n - 1]] == 1) {
            answer = Math.max(answer, nums[n - 1]);
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

        System.out.print("Enter k: ");
        int k = sc.nextInt();

        int result = largestInteger(nums, k);

        System.out.println(
            "Largest almost missing integer: " + result
        );

        sc.close();
    }
}

