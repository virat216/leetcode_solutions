/*
============================================================
LEETCODE 2958 - LENGTH OF LONGEST SUBARRAY WITH AT MOST
K FREQUENCY

Problem

Given an integer array nums and an integer k,

find the length of the longest subarray such that
every element appears at most k times.

------------------------------------------------------------
EXAMPLE

Input:

nums = [1, 2, 2, 1, 1, 3]
k = 2

Output:

5

Explanation:

The longest valid subarray is:

[1, 2, 2, 1, 1]

Frequencies:

1 -> 3
2 -> 2

This is NOT valid for k = 2.

So instead, consider the valid window:

[2, 2, 1, 1, 3]

Frequencies:

2 -> 2
1 -> 2
3 -> 1

Length = 5

------------------------------------------------------------
INTUITION

This is a:

Sliding Window + HashMap

problem.

We maintain a window:

[left ... right]

Inside this window, the frequency of every
number must be <= k.

------------------------------------------------------------
WHY SLIDING WINDOW?

We want the LONGEST subarray satisfying a condition.

We can:

1. Expand the window using right.
2. If the window becomes invalid,
   move left until it becomes valid again.

This avoids checking every possible subarray.

------------------------------------------------------------
HASHMAP

We use:

HashMap<Integer, Integer>

to store:

number -> frequency inside current window

Example:

window = [1, 2, 2, 3]

HashMap:

1 -> 1
2 -> 2
3 -> 1

------------------------------------------------------------
TWO POINTERS

We maintain:

left
right

Initially:

left = 0

right moves from:

0 -> n - 1

The current window is:

[left ... right]

------------------------------------------------------------
STEP 1 - EXPAND WINDOW

For every right:

Add nums[right] to the HashMap.

Example:

nums[right] = 2

Then:

map.put(
    2,
    map.getOrDefault(2, 0) + 1
);

------------------------------------------------------------
STEP 2 - CHECK VALIDITY

Suppose:

k = 2

and the frequency becomes:

2 -> 3

The current window is invalid.

Therefore we move left.

------------------------------------------------------------
STEP 3 - SHRINK WINDOW

While the frequency of the newly added
number is greater than k:

remove nums[left]

and move:

left++

Example:

window:

[1, 2, 2, 2]

Frequency:

2 -> 3

Remove nums[left]:

1

Then:

left++

Window:

[2, 2, 2]

Still invalid.

Remove another element.

Eventually:

[2, 2]

Frequency:

2 -> 2

Now the window is valid.

------------------------------------------------------------
WHY DO WE ONLY CHECK nums[right]?

Before adding nums[right],

the previous window was already valid.

Adding nums[right] can only make ONE
frequency exceed k:

nums[right]

Therefore we only need to check:

map.get(nums[right]) > k

------------------------------------------------------------
DRY RUN

nums = [1, 2, 2, 1, 1, 3]

k = 2

------------------------------------------------------------
right = 0

nums[0] = 1

Window:

[1]

Frequency:

1 -> 1

Valid.

maxLength = 1

------------------------------------------------------------
right = 1

nums[1] = 2

Window:

[1, 2]

Frequency:

1 -> 1
2 -> 1

Valid.

maxLength = 2

------------------------------------------------------------
right = 2

nums[2] = 2

Window:

[1, 2, 2]

Frequency:

1 -> 1
2 -> 2

Valid.

maxLength = 3

------------------------------------------------------------
right = 3

nums[3] = 1

Window:

[1, 2, 2, 1]

Frequency:

1 -> 2
2 -> 2

Valid.

maxLength = 4

------------------------------------------------------------
right = 4

nums[4] = 1

Window:

[1, 2, 2, 1, 1]

Frequency:

1 -> 3
2 -> 2

Invalid because:

1 -> 3

and:

k = 2

------------------------------------------------------------
SHRINK WINDOW

Remove nums[left]:

nums[0] = 1

Frequency:

1 -> 2

Move:

left = 1

Now window:

[2, 2, 1, 1]

Valid.

Length:

4

------------------------------------------------------------
right = 5

nums[5] = 3

Window:

[2, 2, 1, 1, 3]

Frequency:

2 -> 2
1 -> 2
3 -> 1

Valid.

Length:

5

Therefore:

maxLength = 5

------------------------------------------------------------
ALGORITHM

1. Create a HashMap.

2. Set:

   left = 0
   maxLength = 0

3. Traverse the array using right.

4. Add nums[right] to the frequency map.

5. While the frequency of nums[right]
   is greater than k:

   decrement frequency of nums[left]

   left++

6. Calculate:

   right - left + 1

7. Update maxLength.

8. Return maxLength.

------------------------------------------------------------
WHY right - left + 1?

Suppose:

left = 2
right = 5

The window is:

indices:

2, 3, 4, 5

Number of elements:

5 - 2 + 1

= 4

Therefore:

window length = right - left + 1

------------------------------------------------------------
TIME COMPLEXITY

O(n)

The right pointer moves from left to right once.

The left pointer also moves only forward.

Therefore, each element is added and removed
at most once.

------------------------------------------------------------
SPACE COMPLEXITY

O(n)

In the worst case, the HashMap can contain
n different numbers.

------------------------------------------------------------
KEY CONCEPTS

✓ Sliding Window
✓ Two Pointers
✓ HashMap
✓ Frequency Counting
✓ Longest Subarray

------------------------------------------------------------
PATTERN

Longest Subarray
        ↓
Frequency Constraint
        ↓
Sliding Window
        +
HashMap
        ↓
Expand right
        ↓
If invalid
        ↓
Move left
        ↓
Track maximum length

============================================================
*/
import java.util.*;

public class Main {

    public static int maxSubarrayLength(int[] nums, int k) {

        HashMap<Integer, Integer> map = new HashMap<>();

        int left = 0;
        int maxLength = 0;

        for (int right = 0; right < nums.length; right++) {

            map.put(
                nums[right],
                map.getOrDefault(nums[right], 0) + 1
            );

            while (map.get(nums[right]) > k) {

                map.put(
                    nums[left],
                    map.get(nums[left]) - 1
                );

                left++;
            }

            maxLength = Math.max(
                maxLength,
                right - left + 1
            );
        }

        return maxLength;
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

        int result = maxSubarrayLength(nums, k);

        System.out.println("Longest valid subarray length: " + result);

        sc.close();
    }
}

